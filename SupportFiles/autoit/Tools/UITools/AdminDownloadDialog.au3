#cs ----------------------------------------------------------------------------

 AutoIt Version: 3.2.4.9
 Author:         myName

 Script Function:
	Handle Download Dialog from browser

#ce ----------------------------------------------------------------------------

; Script Start - Add your code below here
#include "Constants.au3"
ClipPut("")
Sleep(3000)
ClipPut(HandleDownloadDlg());
Sleep(2000)

Func HandleDownloadDlg()
If WinExists("Opening ") Then
	WinActivate("Opening")
	Send("{TAB}")
	Sleep(1000)
	Send("{TAB}")
	Sleep(1000)
	Send("{DOWN}")
	Sleep(1000)
	Send("!{TAB}")
	Sleep(1000)
	Send("!{TAB}")
	Sleep(1000)
	Send("{ENTER}")
EndIf
Return GetReturnStr($Success,"Test")
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