#include <A3LListView.au3>
#include <GUIListView.au3>
#include <A3LMenu.au3>
#include "Constants.au3"

Opt("WinTitleMatchMode",2)
Opt("MustDeclareVars",1)
ClipPut("")
If $CmdLine[0]<1 Then
	ClipPut(GetReturnStr($Failed,"No Enough Parameters"))
	Sleep(2000)
	Exit
EndIf
ClipPut(RemoveLibrary($CmdLine[1]))
Sleep(2000)
;MsgBox(0,'hello',ClipGet())

;-------------------------------------------------------------------------------
;this method is used to delete some libraries in DI
;$libNameList: the libraries user wants to add to office, in the format of "libName1:libName2:libName3"
;----------notes----------
;1.Before this operation, please ensure the windows title of "lotus quickr" has been opened!
;2.After this operation, word window will be closed, no matter whether add place successfully or not!
;-------------------------------------------------------------------------------

Func RemoveLibrary($libNameList)

If not WinExists($LOTUSQUICKR) Then
	Return GetReturnStr($NoTitle,"Lotus quickr window should be opened firstly!")
EndIf
WinSetState ($LOTUSQUICKR, "", @SW_MAXIMIZE)
WinActivate($LOTUSQUICKR) 

Dim $libraries=StringSplit($libNameList,":")
Dim $ctrl = ControlGetHandle($LOTUSQUICKR,"","SysListView321")
;_GUICtrlListViewSetCheckState($ctrl,-1,0) no need to deselect all firstly
Dim $index,$rightClickItem=-1
For $i=1 To $libraries[0]
	$index=ControlListView($LOTUSQUICKR,"",$ctrl,"FindItem",$libraries[$i])
	If $index<>-1 Then
		;MsgBox(0,$index,"")
		ControlListView($LOTUSQUICKR,"",$ctrl,"Select",$index)
		$rightClickItem=$index
	EndIf
	Sleep(1000)
Next
If $rightClickItem=-1 Then
	Return GetReturnStr($Failed,"All the specified libraries don't exist!"); Ignore popups with no menu items
EndIf

_ListView_ClickItem($ctrl,$rightClickItem,"Right",True)

_Lib_PopupScan() ; Auto3Lib internal routine to find popup menus
Dim $iPopupMenu = _Lib_PopupGetHwnd()
Dim $iPopupItemCount = _Menu_GetItemCount($iPopupMenu)
If $iPopupItemCount <= 0 Then 
	;WinClose($LOTUSQUICKR)
	Return GetReturnStr($Failed,"Can't find context menu!"); Ignore popups with no menu items
EndIf
Dim $itemIndex=_Menu_FindItem($iPopupMenu,"Remove") 
if $itemIndex= -1 Then
	;WinClose($LOTUSQUICKR)
	Send("{ESC}") ;close the pop menu
	Return GetReturnStr($Failed,"can't find 'Remove' menu item in the context menu!")
EndIf
If not _Menu_GetItemEnabled($iPopupMenu,$itemIndex) then 
	;WinClose($LOTUSQUICKR)
	Send("{ESC}") ;close the pop menu
	Return GetReturnStr($Failed,"'Remove' menu item in the context menu is disabled!")
EndIf
_Menu_ClickPopup($itemIndex,True); click Remove menuitem

if WinWaitActive($LOTUSQUICKR,$DeleteText_EN,$ResponseTime)=0 Then
	Return GetReturnStr($Failed,"No dialog for ensuring removal of place appears");$NoAddPlaceAppear
EndIf
ControlClick($LOTUSQUICKR,$DeleteText_EN,'Button1')
While WinWaitActive($LOTUSQUICKR,$DeleteLibText_EN,$ResponseTime/10) ;delete the content of the given libraries
	ControlClick($LOTUSQUICKR,$DeleteLibText_EN,"Button1")
	;Sleep(1000)
WEnd

Return GetReturnStr($Success,'Successful')
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
