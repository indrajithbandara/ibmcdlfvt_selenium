#cs ----------------------------------------------------------------------------

 AutoIt Version: 3.2.4.9
 Author:         myName

 Script Function:
	Template AutoIt script.

#ce ----------------------------------------------------------------------------

; Script Start - Add your code below here
#include "Constants.au3"

#include <A3LToolbar.au3>
#include <A3LMenu.au3>
#include <A3LListView.au3>
#include <A3LTreeView.au3>


Opt("WinTitleMatchMode",2)





If $CmdLine[0]<2 Then 
	ConsoleWrite("No Enough Parameters")
	Sleep(2000)
	Exit
EndIf


;OpenPlaceFile("Excel_BVT_DI_4.xls","Lotus Quickr\BVT_1207549311687 on lwptsthink26")

ConsoleWrite(OpenPlaceFile($CmdLine[1],$CmdLine[2]))


Func OpenPlaceFile($fileName,$filePath)
	;
	openApp()
	;
	openFileDialog()
	
	Dim $pathNodes=StringSplit($filePath,"\")
 If StringCompare($pathNodes[1],$LOTUSQUICKR) Then
	 WinClose($openFromPlace)
	 WinClose($wordTitle)
	 ;MsgBox(0,"",$pathNodes[1])
	ConsoleWrite("Root not Lotus quick");$RootLibError
 EndIf
 

Dim $size=$pathNodes[0]    ;user may input a path ending with '\', even'\\\'
While $pathNodes[$size]=""
	 $size=$size-1
 WEnd

	
WinActivate("Microsoft Excel");

 For $count=2 to $size
	Dim $hTree = ControlGetHandle($openFromPlace, "", "SysTreeView321")
	if $hTree  = "" then 
		ConsoleWrite("Tree not exit");$LibNotExisted
	EndIf
	
	Dim $hNode = _TreeView_FindNode($hTree, $pathNodes[$count]);
	if $hNode = 0 then 
		ConsoleWrite("Library or folder "&$pathNodes[$count]&" can't existed!");$LibNotExisted
	 EndIf
	 WinActivate($openFromPlace)
	_TreeView_Click($hTree, $hNode,"Left",True,2) ;
	Sleep(5000)
Next
 
Dim $listView = ControlGetHandle($openFromPlace, "", "SysListView321")

Dim $fileIndex=ControlListView($openFromPlace,"",$listView,"FindItem",$fileName)
If $fileIndex=-1 Then
	WinClose($openFromPlace)
	WinClose($excelTitle)
	ConsoleWrite("File not existed!");$FileNotExisted
EndIf

ControlListView($openFromPlace,"",$listView,"Select",$fileIndex)
Sleep(2000)
ControlClick($openFromPlace,"","Button2")

Sleep(5000)
WinClose($fileName)

EndFunc


Func openApp()
	;start the word application
	Send("#r")
	Sleep(1000)
	;ControlSetText("Run","","Edit1","winword")
	Send("excel{ENTER}")
	Sleep(3000)

	If not WinExists($excelTitle) Then
		ConsoleWrite("Failed")
	EndIf

	WinSetState ($wordTitle, "", @SW_MAXIMIZE)
	WinActivate($wordTitle) 
	
EndFunc


Func openFileDialog()
	
	If not WinExists($excelTitle) Then
		ConsoleWrite("Failed")
		return 0;
	EndIf
	
	MouseClick("left",200,200)
	Send("!H")
	Send("{RIGHT}")
	Send("O")

	
EndFunc