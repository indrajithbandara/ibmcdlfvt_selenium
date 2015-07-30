#include "Constants.au3"
#include <A3LListView.au3>
#include <A3LMenu.au3>

Opt("WinTitleMatchMode",2)
Opt("MustDeclareVars",1)
ClipPut("")
If $CmdLine[0]<1 Then 
	ClipPut(GetReturnStr($Failed,"No Enough Parameters"))
	Sleep(2000)
	Exit
EndIf
ClipPut(SaveDISetupFile($CmdLine[1]))
Sleep(2000)
;MsgBox(0,"From clipboard:",ClipGet())


;-------------------------------------------------------------------------------
;tihs method is used to save setup file for DI to c:\
;@return: a string indicating the operation result: "0:{$txtName}" or "-1:reason"
;-------------------------------------------------------------------------------
Func SaveDISetupFile($setupFileName) ;$parentFolderTitle should be "File Download - Security Warning"
	If WinExists($BeginInstallText_EN) Then
		WinActivate($BeginInstallText_EN)
	EndIf
	
	Dim $text="Do you want to run or save this file"
	if WinWaitActive($BeginInstallText_EN,$text,$ResponseTime)=0 Then
		Return GetReturnStr($NoTitle,$BeginInstallText_EN&" dialog should be opened firstly")
	EndIf
	Sleep(1000)
	Do
		ControlClick($BeginInstallText_EN,$text,"Button2") ;save the current file To $path
		Sleep(2000)
	Until Not WinExists($BeginInstallText_EN,$text)
	;Send("!R")
	Dim $title="Save As", $text="Save as &type:"
	if WinWaitActive($title,$text,$ResponseTime)=0 Then
		Return GetReturnStr($Failed,$title&" dialog can't appear")
	EndIf
	ControlSetText($title,$text,"Edit1","c:\"&$setupFileName)
	ControlClick($title,$text,"Button2") ;click the save button
	Sleep(2000)

	If WinExists($title,$FolderExistTitle_EN) Then
		ControlClick($title,$FolderExistTitle_EN,"Button1") ;replace the existed file
	EndIf
	
	$title="of qkrconn.exe from"
	$text="Close this dialog box when download completes"
	Dim $timeLimit=$ResponseTime*5
	if WinWaitActive($title,$text,$timeLimit)=0 Then
		Return GetReturnStr($Failed,$title&" dialog can't appear in "&$timeLimit&" seconds")
	EndIf
	ControlClick($title,$text,"Button1") ;select the checkbox of close after downloading
	While WinExists($title,$text)
		Sleep(1000)
	WEnd
	Sleep(3000)
	$title = "Download complete"
	if WinExists($title,"") Then
		WinActivate($title, "")
		ControlClick($title, "","Button4")
		Sleep(1000)
	EndIf
	
	Return GetReturnStr($Success,"Download setup file to c:\ Wizard passed")
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
