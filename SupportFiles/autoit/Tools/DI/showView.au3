#cs ----------------------------------------------------------------------------

 AutoIt Version: 3.2.4.9
 Author:         myName

 Script Function:
	Template AutoIt script.

#ce ----------------------------------------------------------------------------

; Script Start - Add your code below here

#include "Constants.au3"

#include <A3LTreeView.au3>
#include <A3LMenu.au3>


;dim $placeName = "BVT_1208428860546 on lwptsthink07"

;dim $placeName

If $CmdLine[0]<1 Then
		ConsoleWrite("No Enough Parameters"))
		Sleep(2000)
	Exit
EndIf


ConsoleWrite(showView($CmdLine[1]))


Func showView($placeName)
	
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
	
	Dim $hItem = _TreeView_FindNode($hTree, $placeName);
	if $hItem = 0 then 
	;WinClose($parentFolderTitle)
	ConsoleWrite("lib doesn't exit")
	EndIf
	
	_TreeView_Click($hTree, $hItem,"Right",True)
	
	Sleep(1000)
	_Lib_PopupScan() ; Auto3Lib internal routine to find popup menus
	Dim $iPopupMenu = _Lib_PopupGetHwnd()
	Dim $iPopupItemCount = _Menu_GetItemCount($iPopupMenu)
	If $iPopupItemCount <= 0 Then 
		;WinClose($LOTUSQUICKR)
		Return GetReturnStr($Failed,"Can't find context menu!"); Ignore popups with no menu items
	EndIf
	
	Dim $itemIndex=_Menu_FindItem($iPopupMenu,"Preferences...") 
	if $itemIndex= -1 Then
		;WinClose($LOTUSQUICKR)
		ConsoleWrite("can't find 'Property' menu item in the context menu!")
	EndIf
	
	If not _Menu_GetItemEnabled($iPopupMenu,$itemIndex) then 
	;WinClose($LOTUSQUICKR)
	ConsoleWrite("'Property' menu item in the context menu is disabled!")
	EndIf

	_Menu_ClickPopup($itemIndex,True); click 'Preferences...' menuitem
	
	WinActivate("Preferences - IBM Lotus Quickr connectors","")
	For $i=1 To 999
		if(ControlGetText("Preferences - IBM Lotus Quickr connectors","","Button"&$i) ="Show Views") Then
			ControlClick("Preferences - IBM Lotus Quickr connectors","","Button"&$i)
		EndIf
	Next
	
	ControlClick("Preferences - IBM Lotus Quickr connectors","","OK")
	
	WinClose($placeName)
	
EndFunc