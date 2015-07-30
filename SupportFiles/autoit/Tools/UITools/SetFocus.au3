#include "Constants.au3"
#cs ----------------------------------------------------------------------------

 AutoIt Version: 3.1.1.0
 Author:         Brian Murray

 Script Function:
	Template AutoIt script.

#ce ----------------------------------------------------------------------------
ClipPut("")
If $CmdLine[0]<1 Then 
	ClipPut(GetReturnStr(-1,"No Enough Parameters"))
	Sleep(2000)
	;MsgBox(0,"From clipboard:",ClipGet())
	Exit
EndIf
ClipPut(setFocus($CmdLine[1]));
Sleep(2000)
; Script Start - Add your code below here
Func setFocus($WinTitle)
	;get user path
	;Dim $FlagFile = EnvGet("USERPROFILE") & "\AutoITDone.txt"
	; if we don't limit the loop we could get stuck
	Dim $Count = 0
	Opt("WinTitleMatchMode",2)
	While WinActivate($WinTitle)=0
		sleep(1000)
		$Count = $Count +1
		If ($Count >60) Then
			Return GetReturnStr($Failed,"Could not activate window with title "&$WinTitle)
		EndIf
	WEnd
	;tell RFT we are done here
	;$file=FileOpen($FlagFile, 2)
	;FileClose($file)
	Opt("WinTitleMatchMode",1)
	Return GetReturnStr($Success,"Activated window with title "&$WinTitle)
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
