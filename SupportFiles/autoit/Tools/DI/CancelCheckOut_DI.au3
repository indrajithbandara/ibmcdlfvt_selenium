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
ClipPut(CancelCheckOut($CmdLine[1],$CmdLine[2]))
Sleep(2000)
;MsgBox(0,'Return:',ClipGet())

;-------------------------------------------------------------------------------
;tihs method is check out a file
;@$parentFolderTitle: the title of the file's parent folder, that's to say, you need to open the folder (call OpenFolder())firstly which the file is located!
;@$fileName : the file that need to be checked out
;@return: a string indicating the result of this operation: "$Success:{link}" or "$Failed:Null"
;-------------------------------------------------------------------------------
Func CancelCheckOut($parentFolderTitle,$fileName)
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
	Dim $hPopupItemCount,$hPopupMenu;
	_Lib_PopupScan() ; Auto3Lib internal routine to find popup menus
    $hPopupMenu = _Lib_PopupGetHwnd()
    $hPopupItemCount = _Menu_GetItemCount($hPopupMenu)
	If $hPopupItemCount <= 0 Then 
		;WinClose($parentFolderTitle)
		Return GetReturnStr($Failed,"Can't find context menu!"); Ignore popups with no menu items
	EndIf
	Dim $itemIndex=_Menu_FindItem($hPopupMenu,"Cancel Checkout") 
	
	if $itemIndex= -1 Then
		;WinClose($parentFolderTitle)
		Send("{ESC}") ;close the pop menu
		Return GetReturnStr($Failed,"can't find 'Cancel Checkout' menu item in the context menu!")
	EndIf
	If not _Menu_GetItemEnabled($hPopupMenu,$itemIndex) then 
		;WinClose($parentFolderTitle)
		Send("{ESC}") ;close the pop menu
		Return GetReturnStr($Failed,"'Cancel Checkout' menu item in the context menu is disabled!")
	EndIf
	;_Menu_ClickItem($listView,$hPopupMenu,$itemIndex)
	_Menu_ClickPopup($itemIndex,True)
	If WinWaitActive($LOTUSQUICKR,"want to cancel",$ResponseTime) Then ;Are you sure you want to cancel? Any changes you have made will be lost.
		ControlClick($LOTUSQUICKR,"want to cancel","Button1")
		Sleep(2000)
	Else
		Return GetReturnStr($Failed,"No dialog for confirming cancel-check-out appears!")
	EndIf
	
	While WinExists("Canceling") ;wait the canceling dialog to be close
		Sleep(1000)
		If WinExists($LOTUSQUICKR,"OK") Then
			Dim $text=ControlGetText($LOTUSQUICKR,"OK","Static1")
			WinClose($LOTUSQUICKR,"OK")
			Return GetReturnStr($Failed,$text)
		EndIf
		
	WEnd
	Return GetReturnStr($Success,"Successful")
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