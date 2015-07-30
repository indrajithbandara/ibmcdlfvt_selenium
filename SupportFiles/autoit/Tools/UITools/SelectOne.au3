#include "Constants.au3"
#cs ----------------------------------------------------------------------------

 AutoIt Version: 3.1.1.0
 Author:         George zhang

 Script Function:
	select item with specific index of some window

#ce ----------------------------------------------------------------------------
ClipPut("")
If $CmdLine[0]<2 Then 
	ClipPut(GetReturnStr(-1,"No Enough Parameters"))
	Sleep(2000)
	;MsgBox(0,"From clipboard:",ClipGet())
	Exit
EndIf
ClipPut(selectone($CmdLine[1],$CmdLine[2]));
Sleep(2000)

Func selectone($WinTitle,$PlaceIndex)	
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
	ControlClick($WinTitle, "", "SysListView321")
	sleep(1000)
	ControlListView($WinTitle, "", "SysListView321", "Select", $PlaceIndex)
	Return GetReturnStr($Success,"Selected item with index "&$PlaceIndex&" from window "&$WinTitle)
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



