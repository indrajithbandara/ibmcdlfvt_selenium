#include "Constants.au3"
#include <A3LListView.au3>
#include <A3LTreeView.au3>
#include <A3LMenu.au3>
Opt("WinTitleMatchMode",2)
Opt("MustDeclareVars",1)
;ClipPut("")
If $CmdLine[0]<2 Then 
	ClipPut(GetReturnStr($Failed,"No Enough Parameters"))
	Sleep(2000)
	Exit
EndIf
ClipPut(PasteFileLink($CmdLine[1], $CmdLine[2]))
Sleep(2000)
;MsgBox(0,'Return:',ClipGet())

;-------------------------------------------------------------------------------
;tihs method is used to copy link to the given file( it's not right for folder)
;@$parentFolderTitle: the title of the file's parent folder, that's to say, you need to open the folder (call OpenFolder())firstly which the file is located!
;@$fileName : the file that need to copy link
;@return: a string indicating the result of this operation: "$Success:{link}" or "$Failed:Null"
;-------------------------------------------------------------------------------
Func PasteFileLink($folderName, $checkIn)
	If WinExists($MicrosoftOutlook_EN) Then
		WinActivate($MicrosoftOutlook_EN)
		WinSetState($MicrosoftOutlook_EN,"",@SW_MAXIMIZE)
	EndIf
	Dim $handle,$listView,$folderIndex
	Dim $hTree = ControlGetHandle($MicrosoftOutlook_EN, "", "SysTreeView321")
	Dim $hNode = _TreeView_FindNode($hTree, $folderName);
	;right click team place after left clicking it
	_TreeView_Click($hTree, $hNode,"Left",True,1)
	sleep(1000)
	_TreeView_Click($hTree, $hNode,"Right",True,1)
	Sleep(1000)
	Dim $iPopupItemCount,$iPopupMenu;
	_Lib_PopupScan() ; Auto3Lib internal routine to find popup menus
    $iPopupMenu = _Lib_PopupGetHwnd()
	
    $iPopupItemCount = _Menu_GetItemCount($iPopupMenu)
	If $iPopupItemCount <= 0 Then 
		;WinClose($parentFolderTitle)
		Return GetReturnStr($Failed,"Can't find context menu!"); Ignore popups with no menu items
	EndIf
	Dim $itemIndex=_Menu_FindItem($iPopupMenu,"Paste") 
	if $itemIndex= -1 Then
		;WinClose($parentFolderTitle)
		Send("{ESC}") ;close the pop menu
		Return GetReturnStr($Failed,"can't find 'Paste' menu item in the context menu!")
	EndIf
	If not _Menu_GetItemEnabled($iPopupMenu,$itemIndex) then 
		;WinClose($parentFolderTitle)
		Send("{ESC}") ;close the pop menu
		Return GetReturnStr($Failed,"'Paste' menu item in the context menu is disabled!")
	EndIf
		 
	_Menu_ClickPopup($itemIndex,True); click 'Paste menuitem
	;;have to deal with dialogs
	
	Sleep(1000)
		If WinExists($DocumentAdd) Then
			;MsgBox(0,"hi",$checkIn)
			If StringCompare($checkIn, "true", 0) = 0 Then
				ControlClick($DocumentAdd,"","Button2")	
			Else
				ControlClick($DocumentAdd,"","Button3")
			EndIf	
			ControlClick($DocumentAdd,"","Button5")
		EndIf
		Sleep(1000)
		If WinExists($LOTUSQUICKROld) Then  ;now here are two cases: 1) the file or folder has existed; 2) can't copy to the current folder(may be a bug); 3) invalid characters(window is ok, but DI can't support!)
			dim $text1=ControlGetText($LOTUSQUICKROld,"","Static1")
			;WinClose($LOTUSQUICKR) ; a dialog may appear: Item already exists.Do you wish to continue? yes or no button
			ControlClick($LOTUSQUICKROld,"","Button1")
			;Return GetReturnStr($Failed,"The file has existed or includes invalid characters!")
		ElseIf WinExists("Overwrite","already contains") or WinExists("Replace","already contains")Then ;copy to DI
			;ControlClick("Overwrite","already contains","Button3")
			Send("A")
		EndIf
		
	Return GetReturnStr($Success,"OK")
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