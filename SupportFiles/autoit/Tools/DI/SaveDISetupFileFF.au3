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
	If WinExists($BeginInstallTextFF_EN) Then
		WinActivate($BeginInstallTextFF_EN)
	EndIf
	
	;Dim $text="You have chosen to open"
	;if WinWaitActive($BeginInstallTextFF_EN,$text,$ResponseTime)=0 Then
		;Return GetReturnStr($NoTitle,$BeginInstallTextFF_EN&" dialog should be opened firstly")
	;EndIf
	Sleep(1000)
	;default button should be save as
	Send("{ENTER}")
	;Send("!R")
	Dim $title="Enter name of file to save to...", $text="Save as type:"
	if WinWaitActive($title,"",$ResponseTime)=0 Then
	;if (WinExists($title,"")=false) Then
		Return GetReturnStr($Failed,$title&" dialog can't appear")
	EndIf
	ControlSetText($title,"","Edit1","c:\"&$setupFileName)
	ControlClick($title,"","Button2") ;click the save button
	Sleep(2000)

	If WinExists($title,$FolderExistTitle_EN) Then
		ControlClick($title,$FolderExistTitle_EN,"Button1") ;replace the existed file
	EndIf
	$title="Download complete"
	$text="Close this dialog box when download completes"
	Dim $timeLimit=$ResponseTime*5
	if WinWaitActive($title,$text,$timeLimit)=0 Then
		Return GetReturnStr($Failed,$title&" dialog can't appear in "&$timeLimit&" seconds")
	EndIf
	Sleep(1000)
	ControlClick($title,$text,"Button4"); click the "download complete" dialog

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
