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
ClipPut(CheckIn($CmdLine[1],$CmdLine[2]))
Sleep(2000)
;MsgBox(0,'Return:',ClipGet())

;-------------------------------------------------------------------------------
;tihs method is used to check in the given file( it's not right for folder)
;@$parentFolderTitle: the title of the file's parent folder, that's to say, you need to open the folder (call OpenFolder())firstly which the file is located!
;@$fileName : the file that need to copy link
;@return: a string indicating the result of this operation: "$Success:{link}" or "$Failed:Null"
;-------------------------------------------------------------------------------
Func CheckIn($parentFolderTitle,$fileName)
	If WinExists($parentFolderTitle) Then
		WinSetState($parentFolderTitle,"",@SW_MAXIMIZE)
		WinActivate($parentFolderTitle)
	EndIf
	
	if WinWaitActive($parentFolderTitle,"",$ResponseTime)=0 Then
		Return GetReturnStr($NoTitle,$parentFolderTitle&" folder should be opened firstly")
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
	Dim $itemIndex=_Menu_FindItem($iPopupMenu,"check in") 
	;MsgBox(0,'',$itemIndex)
	if $itemIndex= -1 Then
		;WinClose($parentFolderTitle)
		Send("{ESC}") ;close the pop menu
		Return GetReturnStr($Failed,"can't find check in menu item in the context menu!")
	EndIf
	If not _Menu_GetItemEnabled($iPopupMenu,$itemIndex) then 
		;WinClose($parentFolderTitle)
		Send("{ESC}") ;close the pop menu
		Return GetReturnStr($Failed,"check in menu item in the context menu is disabled!")
	EndIf
	Sleep(1000)
	;_Menu_ClickItem($listView,$iPopupMenu,$itemIndex)
	_Menu_ClickPopup($itemIndex,True)
	
	;Send("C")
	Dim $text=""
	Do
		Sleep(1000)
		If WinExists($LOTUSQUICKR) Then
			$text=ControlGetText($LOTUSQUICKR,"","Static1")
			WinClose($LOTUSQUICKR)
		EndIf
	Until Not WinExists("Checking")
	If $text="" Then
		Return GetReturnStr($Success,'Successful')
	Else
		Return GetReturnStr($Success,$text)
	EndIf
	
EndFunc

;-------------------------------------------------------------------------------
;tihs method is used to format the given string,used for better communication with java process
;@$str: the string will be formatted.':' will be replaced by 'xy', and 'x' will be replaced by 'xz'
;@return: a formated string for java process to resolve to get its content
;-------------------------------------------------------------------------------
Func ToFormatedStr($str)
	;Dim $formatedStr=StringReplace(StringReplace($str,'x','xz'),':','xy')
	Return $str; $formatedStr
EndFunc

Func GetReturnStr($returnCode,$returnStr)
	Return $returnCode&$Separator&ToFormatedStr($returnStr)
EndFunc