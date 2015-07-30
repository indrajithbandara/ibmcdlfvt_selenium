#include "Constants.au3"
#include <A3LListView.au3>
#include <A3LMenu.au3>
Opt("WinTitleMatchMode",2)
ClipPut("")
If $CmdLine[0]<2 Then 
	ClipPut(GetReturnStr($Failed,"No Enough Parameters"))
	Sleep(2000)
	Exit
EndIf
ClipPut(EditFile($CmdLine[1],$CmdLine[2]))
Sleep(2000)
;MsgBox(0,'Return:',ClipGet())

;-------------------------------------------------------------------------------
;tihs method is used to open a given file
;@$parentFolderTitle: the title of the file's parent folder, that's to say, you need to open the folder (call OpenFolder())firstly which the file is located!
;@$fileName : the file that will be opened
;@return: a string indicating the result of this operation: "$Success:fileTitle" or "$Failed:{reason}"
;-------------------------------------------------------------------------------
Func EditFile($parentFolderTitle,$fileName)
	If WinExists($parentFolderTitle) Then
		WinSetState($parentFolderTitle,"",@SW_MAXIMIZE)
		WinActivate($parentFolderTitle)
	EndIf
	
	if WinWaitActive($parentFolderTitle,"",$ResponseTime)=0 Then
		Return GetReturnStr($NoTitle,"Parent folder should be opened firstly")
	EndIf
	
	Dim $handle = WinGetHandle($parentFolderTitle) ;
	Dim $listView = ControlGetHandle($parentFolderTitle,"","SysListView321")
	Dim $folderIndex=ControlListView($parentFolderTitle,"","SysListView321","FindItem",$fileName,0)
	
	If $folderIndex=-1 Then
		;winclose($parentFolderTitle)
		Return GetReturnStr($Failed,"The specified file doesn't exist")
	EndIf
	_ListView_ClickItem($listView,$folderIndex,"Right",True)
	Sleep(1000)
	Dim $iPopupItemCount,$iPopupMenu;
	_Lib_PopupScan() ; Auto3Lib internal routine to find popup menus
    $iPopupMenu = _Lib_PopupGetHwnd()
    $iPopupItemCount = _Menu_GetItemCount($iPopupMenu)
	If $iPopupItemCount <= 0 Then 
		;WinClose($parentFolderTitle)
		Return GetReturnStr($Failed,"Can't find context menu!"); Ignore popups with no menu items
	EndIf
	Dim $itemIndex=_Menu_FindItem($iPopupMenu,"edit") 
	
	if $itemIndex= -1 Then
		;WinClose($parentFolderTitle)
		Send("{ESC}") ;close the pop menu
		Return GetReturnStr($Failed,"can't find edit menu item in the context menu!")
	EndIf
	If not _Menu_GetItemEnabled($iPopupMenu,$itemIndex) then 
		;WinClose($parentFolderTitle)
		Send("{ESC}") ;close the pop menu
		Return GetReturnStr($Failed,"edit menu item in the context menu is disabled!")
	EndIf
	;_Menu_ClickItem($listView,$iPopupMenu,$itemIndex)
	_Menu_ClickPopup($itemIndex,True)
	Sleep(2000)
	if WinExists($LOTUSQUICKR) Then
		Dim $text=ControlGetText($LOTUSQUICKR,"","Static1")
		WinClose($LOTUSQUICKR)
		Return GetReturnStr($Failed,$text)
	EndIf
	;if office 2007 we are gonna get another dialog asking about the file format
	if WinExists("Microsoft Office","Do you want to open the file now?") Then
		ControlClick("Microsoft Office","Do you want to open the file now?","Button1")
	EndIf
	;gotta wait a long time sometimes
	If WinWaitActive($fileName,"",$ResponseTime)=0 Then
		Return GetReturnStr($Failed,"Impossible reason for not opening specified file!")
	EndIf
	
	Return GetReturnStr($Success,WinGetTitle(''))

EndFunc

;-------------------------------------------------------------------------------
;tihs method is used to format the given string,used for better communication with java process
;@$str: the string will be formatted.':' will be replaced by 'xy', and 'x' will be replaced by 'xz'
;@return: a formated string for java process to resolve to get its content
;-------------------------------------------------------------------------------
Func ToFormatedStr($str)
	;Dim $formatedStr=StringReplace(StringReplace($str,'x','xz'),':','xy')
	Return $str;$formatedStr
EndFunc

Func GetReturnStr($returnCode,$returnStr)
	Return $returnCode&$Separator&ToFormatedStr($returnStr)
EndFunc