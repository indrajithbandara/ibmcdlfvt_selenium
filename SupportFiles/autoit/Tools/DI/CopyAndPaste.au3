#include "Constants.au3"
#include <A3LListView.au3>
#include<A3LMenu.au3>

Opt("WinTitleMatchMode",2)
Opt("MustDeclareVars",1)
ClipPut("")
;problems:
;1. Using autoit to copy the speicified folder or file, but on the target folder, the paste menu item is 
;not found! so strange
;2. If target folder is not in detail view, then we can't right-click the folder rightly!
If $CmdLine[0]<4 Then 
	;MsgBox(0,"Info",'Please specify the folder name and path you want to create in the format of "SourceFolder" "TargetFolder" "SamePath" ',3)
	ClipPut(GetReturnStr($Failed,"No Enough Parameters"))
	Sleep(2000)
	Exit
EndIf

;MsgBox(0,"Info","copy folder to target folder successfully",3)
ClipPut(CopyAndPaste($CmdLine[1],$CmdLine[2],$CmdLine[3],$CmdLine[4]))
Sleep(2000)
;MsgBox(0,"return:",ClipGet())

;-------------------------------------------------------------------------------
;$fromFolderTitle: the window title of which the $fileList is under (need to call OpenFolder() firstly)
;$fileList: the name of folder or file will be copyed
;$toFolderTitle: the parent folder of $targetFolder which $fileList will be placed in (need to call OpenFolder() firstly)
;$targetFolder: the $fileList will be placed in this folder
;return: a string indicating the operation result,such as "0:Success" or "-1:Failed"
;--------------------------------Notes-----------------------------------
;if the file or folder has existed in the target folder, then this operation will overwrite it
;-------------------------------------------------------------------------------
Func CopyAndPaste($fromFolderTitle,$fileList,$toFolderTitle,$targetFolder)
	If WinExists($fromFolderTitle) Then
		WinSetState($fromFolderTitle,"",@SW_MAXIMIZE)
		WinActivate($fromFolderTitle)
	EndIf
	
	if WinWaitActive($fromFolderTitle,"",$ResponseTime)=0 Then
		Return GetReturnStr($NoTitle,"Source folder should be opened firstly")
	EndIf
	
;	Dim $handle = WinGetHandle($fromFolderTitle) ;
	Dim $libraries=StringSplit($fileList,":")
	Dim $ctrl = ControlGetHandle($fromFolderTitle,"","SysListView321")
	;_GUICtrlListViewSetCheckState($ctrl,-1,0) no need to deselect all firstly
	Dim $index,$rightClickItem=-1
	For $i=1 To $libraries[0]
		$index=ControlListView($fromFolderTitle,"",$ctrl,"FindItem",$libraries[$i])
		If $index<>-1 Then
			;MsgBox(0,$index,"")
			ControlListView($fromFolderTitle,"",$ctrl,"Select",$index)
			$rightClickItem=$index
		EndIf
		Sleep(1000)
	Next
	If $rightClickItem=-1 Then
		Return GetReturnStr($Failed,"All the specified libraries don't exist!"); Ignore popups with no menu items
	EndIf
	_ListView_ClickItem($ctrl,$rightClickItem,"Right",True)
	;Sleep(1000)
	
	Sleep(1000)
	Dim $iPopupItemCount,$iPopupMenu;
	_Lib_PopupScan() ; Auto3Lib internal routine to find popup menus
    $iPopupMenu = _Lib_PopupGetHwnd()
    $iPopupItemCount = _Menu_GetItemCount($iPopupMenu)
	If $iPopupItemCount <= 0 Then 
		;WinClose($fromFolderTitle)
		Return GetReturnStr($Failed,"Can't find context menu!"); Ignore popups with no menu items
	EndIf
	Dim $itemIndex=_Menu_FindItem($iPopupMenu,"copy") 
	;Sleep(3000)
	;MsgBox(0,'menu',_Menu_GetItemText($iPopupMenu,$itemIndex))
	if $itemIndex= -1 Then
		;WinClose($fromFolderTitle)
		Send("{ESC}") ;close the pop menu
		Return GetReturnStr($Failed,"can't find copy menu item in the context menu!")
	EndIf
	If not _Menu_GetItemEnabled($iPopupMenu,$itemIndex) then 
		;WinClose($fromFolderTitle)
		Send("{ESC}") ;close the pop menu
		Return GetReturnStr($Failed,"copy menu item in the context menu is disabled!")
	EndIf
	
	_Menu_ClickPopup($itemIndex,True); click copy menuitem
	
	If WinExists($toFolderTitle) Then
		WinSetState($toFolderTitle,"",@SW_MAXIMIZE)
		WinActivate($toFolderTitle)
	EndIf
	if WinWaitActive($toFolderTitle,"",$ResponseTime)=0 Then
		Return GetReturnStr($NoTitle,"Target folder should be opened firstly")
	EndIf
	
	$ctrl = ControlGetHandle($toFolderTitle,"","SysListView321")
	Dim $folderIndex=ControlListView($toFolderTitle,"","SysListView321","FindItem",$targetFolder,0)
	If $folderIndex=-1 Then
		;winclose($toFolderTitle)
		Return GetReturnStr($Failed,"The target folder doesn't exist!")
	EndIf
	_ListView_ClickItem($ctrl,$folderIndex,"Right",True)
	_Lib_PopupScan() ; Auto3Lib internal routine to find popup menus
    $iPopupMenu = _Lib_PopupGetHwnd()
    $iPopupItemCount = _Menu_GetItemCount($iPopupMenu)
	If $iPopupItemCount <= 0 Then 
		Return GetReturnStr($Failed,"Can't find context menu!"); Ignore popups with no menu items
	EndIf
	Dim $itemIndex=_Menu_FindItem($iPopupMenu,"paste") 
	
	if $itemIndex= -1 Then
		Send("{ESC}") ;close the pop menu
		Return GetReturnStr($Failed,"can't find paste menu item in the context menu!")
	EndIf
	If not _Menu_GetItemEnabled($iPopupMenu,$itemIndex) then 
		Send("{ESC}") ;close the pop menu
		Return GetReturnStr($Failed,"paste menu item in the context menu is disabled!")
	EndIf
	_Menu_ClickPopup($itemIndex,True); click paste menuitem
	
	Sleep(5000)
	If WinExists($LOTUSQUICKR,"are the same") Then ;new harvest: if you copy the files which are in the target folder already, then this dialog will appear!
		Dim $text=ControlGetText($LOTUSQUICKR,"are the same","Static1")
		WinClose($LOTUSQUICKR,"are the same")
		Return GetReturnStr($Failed,$text)
	EndIf
	Dim $text1=""
	 Do ;no matter from local to local, local to di, di to local or di to di, "copying..." dialog appears firstly
		Sleep(1000)
		If WinExists($LOTUSQUICKR) Then  ;now here are two cases: 1) the file or folder has existed; 2) can't copy to the current folder(may be a bug); 3) invalid characters(window is ok, but DI can't support!)
			;ControlClick($LOTUSQUICKR,"","Button1")
			$text1=ControlGetText($LOTUSQUICKR,"","Static1")
			WinClose($LOTUSQUICKR)
			;Return GetReturnStr($Failed,"The file has existed or includes invalid characters!")
		ElseIf WinExists("Overwrite","already contains") or WinExists("Replace","already contains")Then ;copy to DI
			;ControlClick("Overwrite","already contains","Button3")
			Send("A")
		EndIf
	Until Not WinExists($Copying_EN)
	
	If $text1<>"" Then
		Return GetReturnStr($Failed,$text1)
	Else
		Return GetReturnStr($Success,"Successful")
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
	Return $returnCode&$Separator&ToFormatedStr($returnStr)
EndFunc

	