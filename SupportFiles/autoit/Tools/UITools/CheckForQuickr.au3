#include "Constants.au3"
#cs ----------------------------------------------------------------------------

 AutoIt Version: 3.1.1.0
 Author:         George zhang

 Script Function:
	check whether there is a Quickr in this machine

#ce ----------------------------------------------------------------------------
ClipPut("")
If $CmdLine[0]<1 Then 
	ClipPut(GetReturnStr(-1,"No Enough Parameters"))
	Sleep(2000)
	;MsgBox(0,"From clipboard:",ClipGet())
	Exit
EndIf
ClipPut(checkQuickr($CmdLine[1]));
Sleep(2000)
; Script Start - Add your code below here
Func checkQuickr($Quickr)
Dim $WinTitle = "My Documents"
Dim $WinTitle2 = "Desktop"
; if we don't limit the loop we could get stuck
Run("explorer")
Dim $Count = 0
Dim $ItemNumber
While WinActivate($WinTitle)=0
	sleep(1000)
	$Count = $Count +1
	If ($Count >60) Then
		Return GetReturnStr($Failed,"Could not activate window with title "&$WinTitle)
		ExitLoop
	EndIf
WEnd
Send ("{UP}")
While WinActivate($WinTitle2)=0
	sleep(1000)
	$Count = $Count +1
	If ($Count >60) Then
		Return GetReturnStr($Failed,"Could not activate window with title "&$WinTitle2)
		ExitLoop
	EndIf
WEnd
ControlClick($WinTitle2, "", "SysListView321")
sleep(1000)
$ItemNumber = ControlListView($WinTitle2, "", "SysListView321", "FindItem", $Quickr)
If ($ItemNumber > -1) Then
	;activate it while we are here
	ControlListView($WinTitle2, "", "SysListView321", "Select", $ItemNumber)
	Send ("{ENTER}")
	Return GetReturnStr($Success,"Found Quickr: "&$Quickr)
EndIf
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







