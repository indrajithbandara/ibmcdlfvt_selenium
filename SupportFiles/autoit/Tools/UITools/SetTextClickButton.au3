#include "Constants.au3"
#cs ----------------------------------------------------------------------------

 AutoIt Version: 3.1.1.0
 Author:         George zhang

 Script Function:
	set focus to some window and type keys

#ce ----------------------------------------------------------------------------
ClipPut("")
If $CmdLine[0]<4 Then 
	ClipPut(GetReturnStr(-1,"No Enough Parameters"))
	Sleep(2000)
	;MsgBox(0,"From clipboard:",ClipGet())
	Exit
EndIf
ClipPut(setTextClickButton($CmdLine[1],$CmdLine[2],$CmdLine[3],$CmdLine[4]));
Sleep(2000)

Func setTextClickButton($WinTitle,$TextControl,$SetTextSting,$ButtonString)

; if we don't limit the loop we could get stuck
Dim $Count = 0
While WinActivate($WinTitle)=0
	sleep(1000)
	$Count = $Count +1
	If ($Count >60) Then
		Return GetReturnStr($Failed,"Could not activate window with title "&$WinTitle)
		ExitLoop
	EndIf
WEnd
sleep(1000)
ControlSetText($WinTitle, "", $TextControl, $SetTextSting )
ControlClick($WinTitle, "", $ButtonString)
Return GetReturnStr($Success,"Success to set text '"&$SetTextSting&"' and click button "&$ButtonString)

EndFunc



;------------------------------------------------------------
;this method is used to format the given string,used for better communication with java process
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
