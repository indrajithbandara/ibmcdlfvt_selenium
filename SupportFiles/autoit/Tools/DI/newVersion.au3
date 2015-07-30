#cs ----------------------------------------------------------------------------

 AutoIt Version: 3.2.4.9
 Author:         myName

 Script Function:
	Template AutoIt script.

#ce ----------------------------------------------------------------------------

; Script Start - Add your code below here

#include "Constants.au3"
#include <A3LListView.au3>
#include <A3LMenu.au3>

;Dim $parentFolderTitle = "BVT_1208925789421 on lwptsthink49"
;Dim $fileName = "Word_BVT_Doc2.doc"
;Dim $versionName = "BVT_Version_One"

If $CmdLine[0]<3 Then
		ConsoleWrite("No Enough Parameters"))
		Sleep(2000)
	Exit
EndIf


ConsoleWrite(newVersion($CmdLine[1],$CmdLine[2],$CmdLine[3]))

Func newVersion($parentFolderTitle,$fileName,$versionName)
		If WinExists($parentFolderTitle) Then
			WinSetState($parentFolderTitle,"",@SW_MAXIMIZE)
			WinActivate($parentFolderTitle)
		EndIf
		
		if WinWaitActive($parentFolderTitle,"",$ResponseTime)=0 Then
			ConsoleWrite($parentFolderTitle&" folder should be opened firstly")
		EndIf
		
	Dim $handle = WinGetHandle($parentFolderTitle) ;
	Dim $listView = ControlGetHandle($parentFolderTitle,"","SysListView321")
	Dim $folderIndex=ControlListView($parentFolderTitle,"","SysListView321","FindItem",$fileName,0)
	
	If $folderIndex=-1 Then
		ConsoleWrite("The specified file doesn't exist")
	EndIf
	
	_ListView_ClickItem($listView,$folderIndex,"Right",True)
	
	Sleep(1000)
	Dim $iPopupItemCount,$iPopupMenu;
	_Lib_PopupScan() ; Auto3Lib internal routine to find popup menus
    $iPopupMenu = _Lib_PopupGetHwnd()
    $iPopupItemCount = _Menu_GetItemCount($iPopupMenu)
	If $iPopupItemCount <= 0 Then 
		;WinClose($parentFolderTitle)
		ConsoleWrite("Can't find context menu!"); Ignore popups with no menu items
	EndIf
	
	Dim $itemIndex=_Menu_FindItem($iPopupMenu,"Versions...") 
	;MsgBox(0,'',$itemIndex)
	if $itemIndex= -1 Then
		;WinClose($parentFolderTitle)
		Send("{ESC}") ;close the pop menu
		ConsoleWrite("can't find check in menu item in the context menu!")
	EndIf
		
	Sleep(1000)
	;_Menu_ClickItem($listView,$iPopupMenu,$itemIndex)
	_Menu_ClickPopup($itemIndex,True)
	
	Sleep(1000)
	
	if WinWaitActive($fileName,"",$ResponseTime)=0 Then
		ConsoleWrite("No dialog for versioning appears")
	EndIf
	
	ControlClick($fileName,"","Button3")
	
	if WinWaitActive($NewVersionTitle,"",$ResponseTime)=0 Then
		ConsoleWrite("No dialog for new version appears")
	EndIf
	
	ControlSetText($NewVersionTitle,"","Edit1",$versionName)
	Sleep(1000);
	ControlClick($NewVersionTitle,"","Button1")
	Sleep(1000);
	ControlClick($fileName,"","Button4")
	
	WinClose($parentFolderTitle)
	
EndFunc