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

ConsoleWrite(checkSubmittedDraftView($CmdLine[1]))

Func checkSubmittedDraftView($placeName)
	
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
	
	
	$hNode = _TreeView_FindNode($hTree, "Submitted Drafts");
	if $hNode = 0 then 
	;WinClose($parentFolderTitle)
	ConsoleWrite("Submitted Views doesn't exit")
	EndIf
	
	_TreeView_Click($hTree, $hNode,"Left",True)
	Sleep(1000)
	
	WinClose("Submitted Drafts")
	
EndFunc