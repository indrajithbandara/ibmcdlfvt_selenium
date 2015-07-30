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
ClipPut(CopyFileLink($CmdLine[1],$CmdLine[2]))
Sleep(2000)
;MsgBox(0,'Return:',ClipGet())

;-------------------------------------------------------------------------------
;tihs method is used to copy link to the given file( it's not right for folder)
;@$parentFolderTitle: the title of the file's parent folder, that's to say, you need to open the folder (call OpenFolder())firstly which the file is located!
;@$fileName : the file that need to copy link
;@return: a string indicating the result of this operation: "$Success:{link}" or "$Failed:Null"
;-------------------------------------------------------------------------------
Func CopyFileLink($parentFolderTitle,$fileName)
	If not WinExists($parentFolderTitle) Then
		Return GetReturnStr($NoTitle,"Parent folder should be opened firstly")
	EndIf
	WinSetState($parentFolderTitle,"",@SW_MAXIMIZE)
	WinActivate($parentFolderTitle)
	
	Dim $handle = WinGetHandle($parentFolderTitle) ;
	Dim $listView = ControlGetHandle($parentFolderTitle,"","SysListView321")
	Dim $folderIndex=ControlListView($parentFolderTitle,"","SysListView321","FindItem",$fileName,0)
	
	If $folderIndex=-1 Then
		;winclose($parentFolderTitle)
		Return GetReturnStr($Failed,"The specified file doesn't exist")
	EndIf
	#comments-start
	Dim $filePath=ControlGetText($parentFolderTitle,"","Edit1")
	Dim $attrib = FileGetAttrib($filePath&'\'&$fileName)
	MsgBox(0,$attrib,$filePath&'\'&$fileName)
	If @error Then
		MsgBox(4096,"Error", "Could not obtain attributes.")
    Exit
	If StringInStr($attrib, "d") Then
		Return GetReturnStr($Failed,"The specified parameter isn't a file")
	EndIf
	#comments-end
	_ListView_ClickItem($listView,$folderIndex,"Right",True)
	Sleep(1000)
	Dim $iPopupItemCount,$iPopupMenu
	_Lib_PopupScan() ; Auto3Lib internal routine to find popup menus
    $iPopupMenu = _Lib_PopupGetHwnd()
    $iPopupItemCount = _Menu_GetItemCount($iPopupMenu)
	If $iPopupItemCount <= 0 Then 
		;WinClose($parentFolderTitle)
		Return GetReturnStr($Failed,"Can't find context menu!"); Ignore popups with no menu items
	EndIf
	Dim $itemIndex=_Menu_FindItem($iPopupMenu,"Copy Link") 
	if $itemIndex= -1 Then
		;WinClose($parentFolderTitle)
		Send("{ESC}") ;close the pop menu
		Return GetReturnStr($Failed,"can't find 'copy link' menu item in the context menu!")
	EndIf
	If not _Menu_GetItemEnabled($iPopupMenu,$itemIndex) then 
		;WinClose($parentFolderTitle)
		Send("{ESC}") ;close the pop menu
		Return GetReturnStr($Failed,"'copy link' menu item in the context menu is disabled!")
	EndIf
		 
	_Menu_ClickPopup($itemIndex,True); click 'Copy Link' menuitem
	sleep(2000)
	;WinClose($parentFolderTitle)
	Dim $str=ClipGet()
	If $str="" Then
		;MsgBox(0,"FAIL","Test")
		Return GetReturnStr($Failed,"'Copy Link' (null) operation Failed")
	EndIf
	;Now the link of file has been in the clipboard, so we can get it use ClipGet()method, but how to return it two Java process? need other methods to think
	If StringInStr($str,'http://') Then
		Return GetReturnStr($Success,$str)
		;MsgBox(0,"PASS","Test")
	Else
		Return GetReturnStr($Failed,"Failed, maybe file isn't from library")
EndIf

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
	;MsgBox(0,$returnCode,$returnStr)
	Return $returnCode&$Separator&ToFormatedStr($returnStr)
EndFunc