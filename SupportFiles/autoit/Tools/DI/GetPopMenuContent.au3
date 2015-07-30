#include "Constants.au3"
#include <A3LListView.au3>
#include <A3LTreeView.au3>
#include <A3LMenu.au3>
opt("WinTitleMatchMode",2)
Opt("MustDeclareVars",1)
ClipPut("")
If $CmdLine[0]=0 Then 
	ClipPut(GetReturnStr($Failed,"No Enough Parameters"))
	Sleep(2000)
	Exit
EndIf
If $CmdLine[0]=1 Then
	ClipPut(GetPopMenuContent($CmdLine[1],""))
	Sleep(2000)
	;MsgBox(0,'return:',ClipGet())
	Exit
EndIf

ClipPut(GetPopMenuContent($CmdLine[1],$CmdLine[2]))
Sleep(2000)
;MsgBox(0,'return:',ClipGet())

;-------------------------------------------------------------------------------
;tihs method is used to get the content of folder, file, view or library's right context menu and all menuitems' state
;$parentFolderTitle: the title of open window representing the $folder,which the deleted folder is under,that's to say, you need to open it(by OpenFolder()) before calling this method
;$folder : the fold will be deleted, and it can't be ""
;return: a string indicating the operation result, such as "0	copy:1\paste:0\open:1" or "-1:Failed"
;1: the menu item is available and 0: the menu item is unvaliable
;-------------------notes-------------------------
;if the second parameter equals "", then will get the content of pop-up menu of "Lotus quickr"
;-------------------------------------------------------------------------------
Func GetPopMenuContent($parentFolderTitle,$folder)
	If WinExists($parentFolderTitle) Then
		WinSetState($parentFolderTitle,"",@SW_MAXIMIZE)
		WinActivate($parentFolderTitle)
	EndIf
	
	if WinWaitActive($parentFolderTitle,"",$ResponseTime)=0 Then
		Return GetReturnStr($NoTitle,"Parent folder should be opened firstly")
	EndIf
	
    If $folder="" Then
		Dim $hTree = ControlGetHandle($parentFolderTitle, "", "SysTreeView321")
		Dim $hNode = _TreeView_FindNode($hTree, $LOTUSQUICKR);
		if $hNode = 0 then 
			;WinClose($parentFolderTitle)
			Return GetReturnStr($Failed,"lotus quickr can't existed!")
		EndIf
		_TreeView_Click($hTree, $hNode,"Right",True) ;
	Else
		Dim $listView = ControlGetHandle($parentFolderTitle,"","SysListView321")
		Dim $folderIndex=ControlListView($parentFolderTitle,"","SysListView321","FindItem",$folder,0)
		If $folderIndex=-1 Then
		;MsgBox(0,"Error","File don't exist!",2)
			;winclose($parentFolderTitle)
			Return GetReturnStr($Failed,"The file or view doesn't exist!")
		EndIf
		_ListView_ClickItem($listView,$folderIndex,"Right",True)
	EndIf
	
	Sleep(1000)
	Dim $iPopupItemCount,$iPopupMenu;
	_Lib_PopupScan() ; Auto3Lib internal routine to find popup menus
    $iPopupMenu = _Lib_PopupGetHwnd()
    $iPopupItemCount = _Menu_GetItemCount($iPopupMenu)
	If $iPopupItemCount <= 0 Then 
		;WinClose($parentFolderTitle)
		Return GetReturnStr($Failed,"Can't find context menu!"); Ignore popups with no menu items
	EndIf
	Dim $returnStr="", $text,$state
	For $iI=0 to $iPopupItemCount-1
		$text=_Menu_GetItemText($iPopupMenu, $iI)
		
		$state=_Menu_GetItemStateEx($iPopupMenu, $iI, True)
		 if BitAND($state, $MFS_DISABLED) <> 0 Or BitAND($state, $MFS_GRAYED  ) <> 0 then 
			 $state = 0
		 Else
			 $state =1
		 EndIf
		if  $text<> "" then 
			If StringInStr($text,'&') Then
				$text=StringReplace($text,'&','')
			EndIf
			If $returnStr="" Then
				$returnStr=$text&":"&$state
			Else
				$returnStr=$returnStr&'\'&$text&":"&$state
			EndIf
		EndIf
	Next
	Send("{ESC}")
	Return GetReturnStr($Success,$returnStr)
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

	