#include "Constants.au3"
#include <A3LListView.au3>
#include <A3LMenu.au3>
Opt("MustDeclareVars", 1)
Opt("WinTitleMatchMode",2)
ClipPut("")
If $CmdLine[0]<4 Then 
	;MsgBox(0,"Info",'Please specify the folder name and path you want to create in the format of "OldFolderName" "NewFolderName" "SamePath" ',3)
	ClipPut(GetReturnStr($Failed,"No Enough Parameters"))
	Sleep(2000)
	;MsgBox(0,'return:',ClipGet())
	Exit
EndIf

ClipPut(RenameFile($CmdLine[1],$CmdLine[2],$CmdLine[3],$CmdLine[4]))
Sleep(2000)
;MsgBox(0,'return:',ClipGet())
;-------------------------------------------------------------------------------
;tihs method is used to rename an specific folder(this method can't be right to file)
;$folderParentTitle: the path which the $oldFileName folder is placed in(this is achieved by calling OpenFolder())
;$oldFileName : the fold will be renamed
;$newFileName: the new name of old folder
;$fileOrFolder: true, a file; otherwise, a folder
;@return: a string indicating the operation result
;-------------------------------------------------------------------------------
Func RenameFile($folderParentTitle,$oldFileName,$newFileName,$fileOrFolder)

	If WinExists($folderParentTitle) Then
		WinSetState($folderParentTitle,"",@SW_MAXIMIZE)
		WinActivate($folderParentTitle)
	EndIf
	if WinWaitActive($folderParentTitle,"",$ResponseTime/10)=0 Then
		Return GetReturnStr($NoTitle,"Parent folder should be opened firstly!")
	EndIf
	Dim $handle = WinGetHandle($folderParentTitle) ;
	Dim $listView = ControlGetHandle($folderParentTitle,"","SysListView321")
	Dim $folderIndex=ControlListView($folderParentTitle,"","SysListView321","FindItem",$oldFileName,0)
	If $folderIndex=-1 Then
		;winclose($folderParentTitle)
		Return GetReturnStr($Failed,"The folder doesn't exist!")
	EndIf
	ControlListView($folderParentTitle,"","SysListView321","SelectClear")
	ControlListView($folderParentTitle,"","SysListView321","Select",$folderIndex)
	Sleep(1000)
	_ListView_ClickItem($listView,$folderIndex,"Right",True)
	Sleep(1000)
	
	_Lib_PopupScan() ; Auto3Lib internal routine to find popup menus
    Dim $iPopupMenu = _Lib_PopupGetHwnd()
    Dim $iPopupItemCount = _Menu_GetItemCount($iPopupMenu)
	If $iPopupItemCount <= 0 Then 
		;WinClose($toFolderTitle)
		Return GetReturnStr($Failed,"Can't find context menu!"); Ignore popups with no menu items
	EndIf
	Dim $itemIndex=_Menu_FindItem($iPopupMenu,"Rename") 
	if $itemIndex= -1 Then
		Send("{ESC}") ;close the pop menu
		Return GetReturnStr($Failed,"can't find 'Rename' menu item in the context menu!")
	EndIf
	If not _Menu_GetItemEnabled($iPopupMenu,$itemIndex) then 
		Send("{ESC}") ;close the pop menu
		Return GetReturnStr($Failed,"'Rename' menu item in the context menu is disabled!")
	EndIf
		 
	_Menu_ClickPopup($itemIndex,True); click 'Rename' menuitem
	;Send("{UP 2}{Enter}") 
	Sleep(3000)
	if WinExists($LOTUSQUICKROld,'cannot be renamed') Then ;The selected document is a Draft. Drafts cannot be renamed.  Please select another document.
		Dim $text=ControlGetText($LOTUSQUICKROld,"cannot be renamed","Static1")
		WinClose($LOTUSQUICKROld,'cannot be renamed')
		Return GetReturnStr($Failed,$text)
	EndIf
	
	Dim $result=WinWaitActive($ReNameText_EN,$Location_EN,$ResponseTime)
	if $result=0 Then
		;winclose($folderParentTitle)
		Return GetReturnStr($Failed,"Dialog for renaming doesn't exist!")
	EndIf
	;String
	Dim $newName=$newFileName,$newFileNameWithExt=$newFileName
	If StringInStr($fileOrFolder,'true') Then
		Dim $extLocation = StringInStr($newName, ".", 0, -1) 
		If $extLocation<>0 Then
			$newName = StringTrimRight($newName, StringLen($newName)-$extLocation+1)
		Else
			Dim $extLocation = StringInStr($oldFileName, ".", 0, -1) 
			$newFileNameWithExt=$newFileName&StringTrimLeft($oldFileName,$extLocation-1)
		EndIf
	EndIf
	
	ControlSetText($ReNameText_EN,$Location_EN,"Edit1",$newName)
	ControlClick($ReNameText_EN,$Location_EN,"Button1")
	Sleep(3000)
	
	If WinExists($LOTUSQUICKROld) Then
		Dim $text
		If WinExists($LOTUSQUICKROld,$FolderExistTitle_EN) Then
			$text=ControlGetText($LOTUSQUICKROld,"","Static2")
		Else
			$text=ControlGetText($LOTUSQUICKROld,"","Static1")
		EndIf
		WinClose($LOTUSQUICKROld)
		WinClose($ReNameText_EN,$Location_EN)
		Return GetReturnStr($Failed,$text)
	#comments-start
	If WinExists($LOTUSQUICKR,$FolderInvalidText_EN) Then
		WinClose($LOTUSQUICKR,$FolderInvalidText_EN)
		WinClose($ReNameText_EN,$Path_EN)
		;winclose($folderParentTitle)
		Return GetReturnStr($Failed,"Input invalid charactors")
	ElseIf WinExists($LOTUSQUICKR,$ServerError_EN) Then
		WinClose($LOTUSQUICKR,$ServerError_EN)
		WinClose($ReNameText_EN,$Path_EN)
		;winclose($folderParentTitle)
		Return GetReturnStr($Failed,"Server error message appears!")
	ElseIf WinExists($LOTUSQUICKR,$MaxLength_EN) Then
		WinClose($LOTUSQUICKR,$MaxLength_EN)
		WinClose($ReNameText_EN,$Path_EN)
		;winclose($folderParentTitle)
		Return GetReturnStr($Failed,"Length of input name exceeds max lenghth")
	ElseIf WinExists($LOTUSQUICKR,$FolderExistTitle_EN) Then
		WinClose($LOTUSQUICKR,$FolderExistTitle_EN)
		WinClose($ReNameText_EN,$Path_EN)
		;winclose($folderParentTitle)
		Return GetReturnStr($Failed,"The folder has existed")
	ElseIf WinExists($LOTUSQUICKR,"currently checked out") Then
		WinClose($LOTUSQUICKR,"currently checked out")
		WinClose($ReNameText_EN,$Path_EN)
		Return GetReturnStr($Failed,"The file has been checked out!");Document currently checked out by user liu juntao.
		#comments-end
	Else
		#comments-start
		dim $count=ControlListView($folderParentTitle,"","SysListView321","GetItemCount")
		for $i=0 to $count-1 
			MsgBox(0,$i,ControlListView($folderParentTitle,"","SysListView321","GetText",$i,0))
		Next
		#comments-end	
		;refresh first
		WinActivate($folderParentTitle)
		Send("{F5}")
		sleep(2000)
		Dim $oriExist=ControlListView($folderParentTitle,"","SysListView321","FindItem",$oldFileName,0) ;check the original folder existed or not 
		Dim $newExist=ControlListView($folderParentTitle,"","SysListView321","FindItem",$newFileNameWithExt,0); check the new folder existed or not 
		If $oriExist<>-1 Or $newExist=-1 Then
			;winclose($folderParentTitle)
			;MsgBox(0,$oriExist,$newExist)
			Return GetReturnStr($Failed,"Impossible reason for renaming failed!")
		EndIf
		;winclose($folderParentTitle)
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

	