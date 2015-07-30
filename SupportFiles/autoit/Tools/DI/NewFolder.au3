#include "Constants.au3"
#include <A3LListView.au3>
#include <A3LMenu.au3>
Opt("WinTitleMatchMode",2)
Opt("MustDeclareVars",1)
ClipPut("")
If $CmdLine[0]<3 Then 
	ClipPut(GetReturnStr($Failed,"No Enough Parameters"))
	Sleep(2000)
	Exit
EndIf

If $CmdLine[0]=3 Then 
	ClipPut(NewFolder($CmdLine[1],$CmdLine[2],$CmdLine[3]))
	Sleep(2000)
	;MsgBox(0,"From clipboard:",ClipGet())
	Exit
EndIf
ClipPut(NewFolder($CmdLine[1],$CmdLine[2],$CmdLine[3],$CmdLine[4]))
Sleep(2000)
;MsgBox(0,"From clipboard:",ClipGet())


;-------------------------------------------------------------------------------
;tihs method is used to create a folder while right-clicking a specified folder in the open window
;$parentFolderTitle: the title of the folder which has been open to place the new folder, that's to say, call openfolder() firstly
;$folerName: the folder which will be right-clicked
;$createFolderName: the name of the new folder waiting to be created
;$description: the description text of the folder created
;return: a string indicating the operation result: "0:{$name}" or "-1:reason"
;-----------Notes----------
;No matter whether the folder is created successfully, this method will close the $parentFolderTitle
;-------------------------------------------------------------------------------
Func NewFolder($parentFolderTitle,$folerName,$createFolderName,$description="")
	If WinExists($parentFolderTitle) Then
		WinActivate($parentFolderTitle)
		WinSetState($parentFolderTitle,"",@SW_MAXIMIZE)
	EndIf
	
	if WinWaitActive($parentFolderTitle,"",$ResponseTime)=0 Then
		Return GetReturnStr($NoTitle,"Parent folder should be opened firstly")
	EndIf
	
	WinSetState ($parentFolderTitle, "", @SW_MAXIMIZE)
;	Dim $handle = WinGetHandle($parentFolderTitle) ;
	Dim $listView = ControlGetHandle($parentFolderTitle,"","SysListView321")
	Dim $folderIndex=ControlListView($parentFolderTitle,"","SysListView321","FindItem",$folerName,0)
	
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
	Dim $itemIndex=_Menu_FindItem($hPopupMenu,"new folder...") 
	
	if $itemIndex= -1 Then
		;WinClose($parentFolderTitle)
		Send("{ESC}") ;close the pop menu
		Return GetReturnStr($Failed,"can't find 'new folder' menu item in the context menu!")
	EndIf
	If not _Menu_GetItemEnabled($hPopupMenu,$itemIndex) then 
		;WinClose($parentFolderTitle)
		Send("{ESC}") ;close the pop menu
		Return GetReturnStr($Failed,"'new folder' menu item in the context menu is disabled!")
	EndIf
	;_Menu_ClickItem($listView,$hPopupMenu,$itemIndex)
	_Menu_ClickPopup($itemIndex,True)
	
	
	if WinWaitActive($NewCreateFolderTitle_EN,"",$ResponseTime)=0 Then
		;winclose($parentFolderTitle)
		Return GetReturnStr($Failed,"Dialog for Creating folder doesn't appear")
	EndIf
	ControlSetText($NewCreateFolderTitle_EN,"","Edit1",$createFolderName)
	ControlSetText($NewCreateFolderTitle_EN,"","Edit2",$description)
	ControlSend($NewCreateFolderTitle_EN,"","Button1","{ENTER}");here maybe appear many cases
	Sleep(5000); if create folder successfully, then need to update the folder to current view
	If WinExists($LOTUSQUICKR,"OK") Then
		Dim $text=ControlGetText($LOTUSQUICKR,"","Static1")
		WinClose($LOTUSQUICKR,"OK")
		;MsgBox(0,"Info","Create Folder Failed for file existed",3)
		WinClose($NewCreateFolderTitle_EN)
		;winclose($parentFolderTitle)
		Return GetReturnStr($Failed,$text)
		#comments-start
	If WinExists($LOTUSQUICKR,$NoRightToAccessTitle_EN) Then ;No right to create the folder
		
		WinClose($LOTUSQUICKR,$NoRightToAccessTitle_EN)
		;MsgBox(0,"Info","Create Folder Failed for no sufficent right",1) ;we can record this in log file
		;ControlClick($NewCreateFolderTitle_EN,"","Button2","{ENTER}")
		WinClose($NewCreateFolderTitle_EN)
		winclose($parentFolderTitle)
		Return GetReturnStr($Failed,"you have't login the library")
	ElseIf WinExists($LOTUSQUICKR,$HaveAccessText_EN) Then ;The user may be a reader, so It can't create folder!
		WinClose($LOTUSQUICKR,$HaveAccessText_EN)
		;MsgBox(0,"Info","Create Folder Failed for file existed",3)
		WinClose($NewCreateFolderTitle_EN)
		winclose($parentFolderTitle)
		Return GetReturnStr($Failed,"No right to create folder!")
	ElseIf WinExists($LOTUSQUICKR,$FolderExistTitle_EN) Then ;The folder has existed, so return false
		WinClose($LOTUSQUICKR,$FolderExistTitle_EN)
		;MsgBox(0,"Info","Create Folder Failed for file existed",3)
		WinClose($NewCreateFolderTitle_EN)
		winclose($parentFolderTitle)
		Return GetReturnStr($Failed,"The folder has existed")
	ElseIf WinExists($LOTUSQUICKR,$FolderInvalidText_EN)Then ;The folder name contains invalid char, so return false
		;MsgBox(0,"Info","Create Folder Failed for invalid char",1)
		WinClose($LOTUSQUICKR,$FolderInvalidText_EN)
		;MsgBox(0,"Info","Create Folder Failed for invalid char",1)
		;WinWaitActive($NewCreateFolderTitle_EN)
		winclose($parentFolderTitle)
		WinClose($NewCreateFolderTitle_EN)
		Return GetReturnStr($Failed,"Input invalid charactors")
	ElseIf WinExists($LOTUSQUICKR,$MaxLength_EN) Then ;The folder name contains more than 180 chars,so return false
		WinClose($LOTUSQUICKR,$MaxLength_EN)
		;MsgBox(0,"Info","Create Folder Failed for too long name",1)
		WinClose($NewCreateFolderTitle_EN)
		winclose($parentFolderTitle)
		Return GetReturnStr($Failed,"Length of input name exceeds max lenghth")
		#comments-end
	Else	;create folder successfully
		#comments-start ;Here just show the folders after creating new folder
		Dim $total=ControlListView($handle,"",$listView,"GetItemCount")
		For $i=0 To $total-1
			_Lib_ConsoleWrite($i&": "&ControlListView($handle,"",$listView,"GetText",$i,0))
		Next
		#comments-end
		ControlSetText($parentFolderTitle,"","Edit1",ControlGetText($parentFolderTitle,"","Edit1")&"\"&$folerName)
		ControlSend($parentFolderTitle,"","Edit1","{ENTER}")
		If WinExists($folerName) Then
			WinActivate($folerName)
		EndIf
		if WinWaitActive($folerName,"",$ResponseTime)=0 Then
			Return GetReturnStr($Failed,"Impossible reason for not opening windows explorer, haven't checked the create operation")
		EndIf
		$listView = ControlGetHandle($folerName,"","SysListView321")
		If ControlListView($folerName,"",$listView,"FindItem",$createFolderName)<>-1 Then
			;MsgBox(0,"Oh","create "&$createFolderName&" folder successfully!")
			;winclose($parentFolderTitle)
			Return GetReturnStr($Success,"Successful")
		Else
			;MsgBox(0,"Oh","create successfully, but can't find it",1)
			;winclose($parentFolderTitle)
			Return GetReturnStr($Failed,"Create folder successfully, but can't find it")
		EndIf
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
