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
#include <A3LTreeView.au3>



;dim $placeName = "BVT_1208851864531 on lwptsthink49"
;dim $fileName = "Word_BVT_DI_4.doc"
;dim $placeName

If $CmdLine[0]<2 Then 
	MsgBox(0,"Info",'Please specify the folder name and path you want to enter in the format of "FolderPath"',3)
	ConsoleWrite("No Enough Parameters"))
	Sleep(2000)
	Exit
EndIf


ConsoleWrite(approveFile($CmdLine[1],$CmdLine[2]))


Func approveFile($placeName,$fileName)
	
	If WinExists($MyDocument_EN) Then
		WinActivate($MyDocument_EN)
	Else
		Run(@WindowsDir&"\explorer.exe")	
	EndIf
	Sleep(2000)
	If WinWaitActive($MyDocument_EN,"",$ResponseTime)=0 Then
		ConsoleWrite("can't open windows explorer")
	EndIf
	
	ControlSetText($MyDocument_EN,"","Edit1",$LOTUSQUICKR)
	ControlSend($MyDocument_EN,"","Edit1","{ENTER}")
	Sleep(5000)
	
	Dim $hTree = ControlGetHandle(WinGetTitle(''), "", "SysTreeView321")
	
	Dim $hNode = _TreeView_FindNode($hTree, $LOTUSQUICKR);
	if $hNode = 0 then 
	;WinClose($parentFolderTitle)
	ConsoleWrite("Lotus Quickr doesn't exit")
	EndIf
	
	_TreeView_Click($hTree, $hNode,"Left",True)
	Sleep(1000)
	
	$hNode = _TreeView_FindNode($hTree, $placeName);
	if $hNode = 0 then 
	;WinClose($parentFolderTitle)
	ConsoleWrite($placeName&" doesn't exit")
	EndIf

	_TreeView_Click($hTree, $hNode,"Left",True)
	Sleep(1000)
	
	$hNode = _TreeView_FindNode($hTree, "Drafts Views");
	if $hNode = 0 then 
	;WinClose($parentFolderTitle)
	ConsoleWrite("Drafts Views doesn't exit")
	EndIf
	
	_TreeView_Click($hTree, $hNode,"Left",True)
	Sleep(1000)
	
	
	$hNode = _TreeView_FindNode($hTree, "Drafts to Approve");
	if $hNode = 0 then 
	;WinClose($parentFolderTitle)
	ConsoleWrite("Submitted Views doesn't exit")
	EndIf
	
	_TreeView_Click($hTree, $hNode,"Left",True)
	Sleep(1000)
	
	;;
	Dim $handle = WinGetHandle("Drafts to Approve") ;
	Dim $listView = ControlGetHandle("Drafts to Approve","","SysListView321")
	
	Dim $folderIndex=ControlListView("Drafts to Approve","","SysListView321","FindItem",$fileName,0)
	
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
	
	Dim $itemIndex=_Menu_FindItem($iPopupMenu,"Approve") 
	;MsgBox(0,'',$itemIndex)
	if $itemIndex= -1 Then
		;WinClose($parentFolderTitle)
		Send("{ESC}") ;close the pop menu
		ConsoleWrite("can't find Approve menu item in the context menu!")
	EndIf
		
	Sleep(1000)
	;_Menu_ClickItem($listView,$iPopupMenu,$itemIndex)
	_Menu_ClickPopup($itemIndex,True)
	
	Sleep(1000)
	
EndFunc