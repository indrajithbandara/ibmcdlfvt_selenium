#cs ----------------------------------------------------------------------------

 AutoIt Version: 3.2.4.9
 Author:         myName

 Script Function:
	Template AutoIt script.

#ce ----------------------------------------------------------------------------

; Script Start - Add your code below here

#include "Constants.au3"

#include <A3LTreeView.au3>

If $CmdLine[0]<2 Then 
	ConsoleWrite("No Enough Parameters")
	Sleep(2000)
	Exit
EndIf


;createAndCheckInDoc("Word_BVT_DI_12","Lotus Quickr\BVT_1207549311687 on lwptsthink26")

ConsoleWrite(createAndCheckInDoc($CmdLine[1],$CmdLine[2]))


Func createAndCheckInDoc($fileName,$filePath)
	
	;start the word application
	Send("#r")
	Sleep(1000)
	;ControlSetText("Run","","Edit1","winword")
	Send("winword{ENTER}")
	Sleep(3000)

	If not WinExists($wordTitle) Then
		ConsoleWrite("Failed")
	EndIf

	WinSetState ($wordTitle, "", @SW_MAXIMIZE)
	WinActivate($wordTitle) 
	
	If not WinExists($wordTitle) Then
		ConsoleWrite("Failed")
		return 0;
	EndIf
	
	Send("This is some text.")
	
	MouseClick("left",200,200)
	Send("!H")
	Send("{RIGHT}")
	Send("{ENTER}")
	
	Dim $pathNodes=StringSplit($filePath,"\")
	 If StringCompare($pathNodes[1],$LOTUSQUICKR) Then
		 WinClose($addToPlace)
		 WinClose($wordTitle)
		 ;MsgBox(0,"",$pathNodes[1])
		ConsoleWrite("Root not Lotus quick");$RootLibError
	 EndIf
 

	Dim $size=$pathNodes[0]    ;user may input a path ending with '\', even'\\\'
	While $pathNodes[$size]=""
		 $size=$size-1
	 WEnd

	
	WinActivate("Microsoft Word");
	Sleep(2000)
	
	 For $count=2 to $size
		Dim $hTree = ControlGetHandle($addToPlace, "", "SysTreeView321")
		if $hTree  = "" then 
			ConsoleWrite("Tree not exit") ;$LibNotExisted
		EndIf
		
		Dim $hNode = _TreeView_FindNode($hTree, $pathNodes[$count]);
		if $hNode = 0 then 
			ConsoleWrite("Library or folder "&$pathNodes[$count]&" can't existed!");$LibNotExisted
		 EndIf
		 WinActivate($addToPlace)
		_TreeView_Click($hTree, $hNode,"Left",True,2) ;
		Sleep(5000)
	 Next
	
	ControlSend($addToPlace, "", "Edit1", $fileName)
	Sleep(2000)
	ControlClick($addToPlace,"","Button2")
	
	MouseClick("left",200,200)
	Send("!H")
	Send("{RIGHT}") ; Lotus Quickr
	Sleep(1000)
	Send("{DOWN}{DOWN}{DOWN}{DOWN}") ;Actions
	Sleep(1000)
	Send("{RIGHT}") ; Check In
	Sleep(1000)
	Send("{ENTER}") ; Enter
	Sleep(1000)
	Send("{ENTER}") ; OK
	Sleep(1000)
	WinClose("Microsoft Word")
	
EndFunc