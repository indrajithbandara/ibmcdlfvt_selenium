#include "Constants.au3"
#include <A3LListView.au3>
#include <A3LMenu.au3>
opt("WinTitleMatchMode",2)
Opt("MustDeclareVars",1)
ClipPut("")
If $CmdLine[0]<2 Then 
	;MsgBox(0,"Info",'Please specify the folder name and path you want to create in the format of "FolderName" "FolderPath" "description[Option]" ',3)
	ClipPut(GetReturnStr($Failed,"No Enough Parameters"))
	Sleep(2000)
	Exit
EndIf

ClipPut(DeleteFolder($CmdLine[1],$CmdLine[2]))
Sleep(2000)
;MsgBox(0,'return:',ClipGet())



;-------------------------------------------------------------------------------
;tihs method is used to delete an specific folder or file
;@$parentFolderTitle: the title of open window representing the folder,which the deleted folder is under,that's to say, you need to open it(by OpenFolder()) before calling this method
;@$folderName : the fold will be deleted, and it can't be ""
;@return: a string indicating the operation result, such as "0:Successful" or "-1:Failed"
;-----------Notes----------
;No matter whether the folder is created successfully, this method will close the $parentFolderTitle
;-------------------------------------------------------------------------------
Func DeleteFolder($parentFolderTitle,$folderName)
	;WinWaitActive($parentFolderTitle)
	If WinExists($parentFolderTitle) Then
		WinActivate($parentFolderTitle)
	EndIf
	if WinWaitActive($parentFolderTitle,"",$ResponseTime)=0 Then
		Return GetReturnStr($NoTitle,"Parent folder should be opened firstly")
	EndIf
	Dim $handle = WinGetHandle($parentFolderTitle) ;
	Dim $listView = ControlGetHandle($parentFolderTitle,"","SysListView321")
	Dim $folderIndex=ControlListView($parentFolderTitle,"","SysListView321","FindItem",$folderName,0)
	;MsgBox(0,'hello',$folderIndex)
	If $folderIndex=-1 Then
		;MsgBox(0,"Warning","The folder you want to delete doesn't exist in current folder: "&$parentFolderTitle,3)
		;winclose($parentFolderTitle)
		Return GetReturnStr($Failed,"The specified folder doesn't exist")
	EndIf
	
	ControlListView($parentFolderTitle,"","SysListView321","SelectClear")
	ControlListView($parentFolderTitle,"","SysListView321","Select",$folderIndex)
	Sleep(1000)
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
	Dim $itemIndex=_Menu_FindItem($hPopupMenu,"delete") 
	if $itemIndex= -1 Then
		;WinClose($parentFolderTitle)
		Send("{ESC}") ;close the pop menu
		Return GetReturnStr($Failed,"can't find delete menu item in the context menu!")
	EndIf
	If not _Menu_GetItemEnabled($hPopupMenu,$itemIndex) then 
		;WinClose($parentFolderTitle)
		Send("{ESC}") ;close the pop menu
		Return GetReturnStr($Failed,"delete menu item in the context menu is disabled!")
	EndIf
	;_Menu_ClickItem($listView,$hPopupMenu,$itemIndex)
	_Menu_ClickPopup($itemIndex,True) ;click the delete button
	
	;Send("{UP}{UP}{UP}{Enter}") ;may be wrong, need further work to be sure
	Dim $result=WinWaitActive($ConfirmDelete_EN,$DeleteText_EN,$ResponseTime) ;Lotus Quickr:Are you sure you want to delete the selected item?
	If $result Then
		ControlSend($ConfirmDelete_EN,$DeleteText_EN,"Button1","{ENTER}")
		sleep(4000);
	Else
		;winclose($parentFolderTitle)
		Return GetReturnStr($Failed,"Dialog for confirming delete doesn't appear")
	EndIf
	$result=WinWaitActive($LOTUSQUICKROld,$HaveAccessText_EN,$ResponseTime/10) ;Lotus Quickr:Sorry, you might not have access to the content you are trying reach.
	If $result Then
		ControlSend($LOTUSQUICKROld,$HaveAccessText_EN,"Button1","{ENTER}")
		;winclose($parentFolderTitle)
		Return GetReturnStr($Failed,"No access right to delete a locked file!")
	EndIf
	Sleep(2000)
	If ControlListView($parentFolderTitle,"","SysListView321","FindItem",$folderName,0)<>-1 Then ;can't delete it from the view
		;winclose($parentFolderTitle)
		Return GetReturnStr($Failed,"The specified file exists yet, delete failed!")
	EndIf
	;winclose($parentFolderTitle)	
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