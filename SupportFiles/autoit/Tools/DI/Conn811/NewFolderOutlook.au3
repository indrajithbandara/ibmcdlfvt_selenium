#include "Constants.au3"
#include <A3LListView.au3>
#include <A3LTreeView.au3>
#include <A3LMenu.au3>
Opt("WinTitleMatchMode",2)
Opt("MustDeclareVars",1)
ClipPut("")
If $CmdLine[0]<2 Then 
	ClipPut(GetReturnStr($Failed,"No Enough Parameters"))
	Sleep(2000)
	Exit
EndIf

If $CmdLine[0]=2 Then 
	ClipPut(NewFolder($CmdLine[1],$CmdLine[2]))
	Sleep(2000)
	;MsgBox(0,"From clipboard:",ClipGet())
	Exit
EndIf
ClipPut(NewFolder($CmdLine[1],$CmdLine[2],$CmdLine[3]))
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
Func NewFolder($placeName,$createFolderName,$description="")
		If WinExists($MicrosoftOutlook_EN) Then
		WinActivate($MicrosoftOutlook_EN)
		WinSetState($MicrosoftOutlook_EN,"",@SW_MAXIMIZE)
	EndIf
	Dim $handle,$listView,$folderIndex
	Dim $hTree = ControlGetHandle($MicrosoftOutlook_EN, "", "SysTreeView321")
	Dim $hNode = _TreeView_FindNode($hTree, $placeName);
	;right click team place after left clicking it
	_TreeView_Click($hTree, $hNode,"Left",True,1)
	sleep(1000)
	_TreeView_Click($hTree, $hNode,"Right",True,1)
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
	Sleep(3000); if create folder successfully, then need to update the folder to current view
	Dim $hNode = _TreeView_FindNode($hTree, $placeName);
	;right click team place after left clicking it
	_TreeView_Click($hTree, $hNode,"Left",True,2)
	sleep(1000)
	Dim $hNode = _TreeView_FindNode($hTree, $createFolderName);
	If $hNode<>0 Then
		Return GetReturnStr($Success,"Successful")
	Else
		Return GetReturnStr($Failed,"Create folder successfully, but can't find it")
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
