#include "Constants.au3"
#include <A3LListView.au3>
#include <A3LMenu.au3>
Opt("WinTitleMatchMode",2)
Opt("MustDeclareVars",1)
ClipPut("")
If $CmdLine[0]<2 Then 
	ClipPut(GetReturnStr($Failed,"No Enough Parameters"))
	Sleep(2000)
	Exit
EndIf

ClipPut(ShowLibView($CmdLine[1],$CmdLine[2]))
Sleep(2000)
;MsgBox(0,"From clipboard:",ClipGet())


;-------------------------------------------------------------------------------
;tihs method is used to set the 'Show views' property for a specified library in the open window
;$libraryName: the folder which will be right-clicked
;$showViewOrNot: "true" for show views and "false" for not show views
;return: a string indicating the operation result: "0:{$name}" or "-1:reason"
;-----------Notes----------
;before calling this method, "lotus quickr" window should be opened firstly!
;-------------------------------------------------------------------------------
Func ShowLibView($libraryName,$showViewOrNot)
	If WinExists($LOTUSQUICKR) Then
		WinActivate($LOTUSQUICKR)
		WinSetState($LOTUSQUICKR,"",@SW_MAXIMIZE)
	EndIf
	
	if WinWaitActive($LOTUSQUICKR,"",$ResponseTime)=0 Then
		Return GetReturnStr($NoTitle,"Context folder should be opened firstly")
	EndIf
	
;	Dim $handle = WinGetHandle($LOTUSQUICKR) ;
	Dim $listView = ControlGetHandle($LOTUSQUICKR,"","SysListView321")
	Dim $libIndex=ControlListView($LOTUSQUICKR,"","SysListView321","FindItem",$libraryName,0)
	
	If $libIndex=-1 Then
		;winclose($LOTUSQUICKR)
		Return GetReturnStr($Failed,"The specified library doesn't exist")
	EndIf
	_ListView_ClickItem($listView,$libIndex,"Right",True)
	Sleep(1000)
	Dim $hPopupItemCount,$hPopupMenu;
	_Lib_PopupScan() ; Auto3Lib internal routine to find popup menus
    $hPopupMenu = _Lib_PopupGetHwnd()
    $hPopupItemCount = _Menu_GetItemCount($hPopupMenu)
	If $hPopupItemCount <= 0 Then 
		;WinClose($LOTUSQUICKR)
		Return GetReturnStr($Failed,"Can't find context menu!"); Ignore popups with no menu items
	EndIf
	Dim $itemIndex=_Menu_FindItem($hPopupMenu,"properties") 
	
	if $itemIndex= -1 Then
		;WinClose($LOTUSQUICKR)
		Return GetReturnStr($Failed,"can't find 'properties' menu item in the context menu!")
	EndIf
	If not _Menu_GetItemEnabled($hPopupMenu,$itemIndex) then 
		;WinClose($LOTUSQUICKR)
		Return GetReturnStr($Failed,"'properties' menu item in the context menu is disabled!")
	EndIf
	;_Menu_ClickItem($listView,$hPopupMenu,$itemIndex)
	_Menu_ClickPopup($itemIndex,True)
	
	if WinWaitActive($LibProperty,$LibPropertyText,$ResponseTime/10)=0 Then
		Return GetReturnStr($Failed,"Dialog for library's properties doesn't appear")
	EndIf
	if StringInStr($showViewOrNot,'True') Then
		ControlCommand($LibProperty,$LibPropertyText,"Button4","check")
	ElseIf	StringInStr($showViewOrNot,'false') Then
		ControlCommand($LibProperty,$LibPropertyText,"Button4","Uncheck")
	Else
		WinClose($LibProperty,$LibPropertyText)
		Return GetReturnStr($Failed,"The second parameter is wrong!")
	EndIf
	Sleep(1000)
	ControlClick($LibProperty,$LibPropertyText,"Button5")
	
	ControlSetText($LOTUSQUICKR,"","Edit1",ControlGetText($LOTUSQUICKR,"","Edit1")&"\"&$libraryName)
	ControlSend($LOTUSQUICKR,"","Edit1","{ENTER}")
	If WinExists($libraryName) Then
		WinActivate($libraryName)
	EndIf
	if WinWaitActive($libraryName,"",$ResponseTime)=0 Then
		Return GetReturnStr($Failed,"Impossible reason for not opening windows explorer, haven't checked the views")
	EndIf
	$listView = ControlGetHandle($libraryName,"","SysListView321")
	Dim $draftIndex=ControlListView($libraryName,"",$listView,"FindItem",$DraftView)
	Dim $personalIndex=ControlListView($libraryName,"",$listView,"FindItem",$PersonalView)
	Dim $sharedIndex=ControlListView($libraryName,"",$listView,"FindItem",$SharedView)
	WinClose($libraryName)
	If StringInStr($showViewOrNot,'True') Then
		If $draftIndex=-1 or $personalIndex=-1 or $sharedIndex=-1 Then
			Return GetReturnStr($Failed,"Can't see all the 3 views!")
		EndIf
		Return GetReturnStr($Success,"Successful")
	Else
		If $draftIndex<>-1 or $personalIndex<>-1 or $sharedIndex<>-1 Then
			Return GetReturnStr($Failed,"Views should be hided, but Still find it!")
		EndIf
		Return GetReturnStr($Success,"Successful")
	EndIf
	
EndFunc

;-------------------------------------------------------------------------------
;tihs method is used to format the given string,used for better communication with java process
;@$str: the string will be formatted.':' will be replaced by 'xy', and 'x' will be replaced by 'xz'
;@return: a formated string for java process to resolve to get its content
;-------------------------------------------------------------------------------
Func ToFormatedStr($str)
;	Dim $formatedStr=StringReplace(StringReplace($str,'x','xz'),':','xy')
	Return $str;$formatedStr
EndFunc

Func GetReturnStr($returnCode,$returnStr)
	Return $returnCode&$Separator&ToFormatedStr($returnStr)
EndFunc
