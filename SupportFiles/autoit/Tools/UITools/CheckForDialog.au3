#include "Constants.au3"
#cs ----------------------------------------------------------------------------

 AutoIt Version: 3.1.1.0
 Author:         George zhang

 Script Function:
	set focus to some window and type keys

#ce ----------------------------------------------------------------------------
ClipPut("")
If $CmdLine[0]<3 Then 
	ClipPut(GetReturnStr(-1,"No Enough Parameters"))
	Sleep(2000)
	;MsgBox(0,"From clipboard:",ClipGet())
	Exit
EndIf
ClipPut(checkfordialog($CmdLine[1],$CmdLine[2],$CmdLine[3]));
Sleep(2000)

Func checkfordialog($DialogTitle,$DialogText,$WaitTime)
Dim $Temp
Dim $Flag = false
; if we don't limit the loop we could get stuck
Dim $Count = 0
;check for up to wait Time
While ($Flag=false)
	If WinActivate($DialogTitle)=1 Then
		$Temp = WinGetText($DialogTitle)
		If (StringInStr($Temp, $DialogText)>0) Then
			$Flag=true
		EndIF
	EndIF	
	sleep(1000)
	$Count = $Count +1
	If ($Count >$WaitTime) Then
		return GetReturnStr($Failed,"Could not find such a dialog with title caption "&$DialogTitle&" and text "&$DialogText)
		ExitLoop
	EndIf
WEnd


Return GetReturnStr($Success,"Success to find such a dialog with title caption "&$DialogTitle&" and text "&$DialogText)

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





