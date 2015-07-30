#include "Constants.au3"
#cs ----------------------------------------------------------------------------

 AutoIt Version: 3.1.1.0
 Author:         Brian Murray / George zhang

 Script Function:
	Template AutoIt script.

#ce ----------------------------------------------------------------------------
ClipPut("")
If $CmdLine[0]<3 Then 
	ClipPut(GetReturnStr(-1,"No Enough Parameters"))
	Sleep(2000)
	;MsgBox(0,"From clipboard:",ClipGet())
	Exit
EndIf
ClipPut(clickMouse($CmdLine[1],$CmdLine[2],$CmdLine[3]));
Sleep(2000)
; Script Start - Add your code below here
Func clickMouse($WinTitle,$x,$y)
	;get user path 
	Dim $Count = 0
	While WinActivate($WinTitle)=0
		sleep(1000)
		$Count = $Count +1
		If ($Count >60) Then
			Return GetReturnStr($Failed,"Fail to locate window to click Mouse at point ("&$x&","&$y&")")
			ExitLoop
		EndIf
	WEnd
	MouseClick("left", $x, $y )

	Return GetReturnStr($Success,"Success to click Mouse at point ("&$x&","&$y&")")
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

