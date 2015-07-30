#cs ----------------------------------------------------------------------------

 AutoIt Version: 3.2.4.9
 Author:         myName

 Script Function:
	log in admin Console

#ce ----------------------------------------------------------------------------

; Script Start - Add your code below here
#include "Constants.au3"
ClipPut("")
If $CmdLine[0]<3 Then 
	ClipPut(GetReturnStr(-1,"No Enough Parameters"))
	Sleep(2000)
	;MsgBox(0,"From clipboard:",ClipGet())
	Exit
EndIf
Sleep(3000)
ClipPut(LoginAdmConsole($CmdLine[1],$CmdLine[2],$CmdLine[3]));
Sleep(2000)

Func LoginAdmConsole($serverURL,$userName,$userPassword)
	;get part of host name to active the login window
	Dim $wintitle = StringMid($serverURL,8,15)
	;MsgBox(0,"From clipboard:",$wintitle)
	If WinExists($wintitle) Or WinExists("Authentication Required") Then
		WinActivate($wintitle)
	EndIf
	
;~ 	Sleep(2000)
;~ ControlSetText($wintitle,"","Edit2",$userName)
;~ 	Sleep(1000)
;~ ControlSetText($wintitle,"","Edit3",$userPassword)	
;~ 	Sleep(1000)
;~ ControlClick($wintitle,"","Button2")
	Sleep(2000)
	Send($userName)
	Send("{TAB}")
	Sleep(1000)
	Send($userPassword)
	Sleep(1000)
	Send("{TAB}{ENTER}")
Return GetReturnStr($Success,"Log in admin console with "&$userName)
EndFunc

;------------------------------------------------------------
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