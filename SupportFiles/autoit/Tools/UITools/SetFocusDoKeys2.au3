#cs ----------------------------------------------------------------------------

 AutoIt Version: 3.1.1.0
 Author:         Brian Murray

 Script Function:
	Template AutoIt script.

#ce ----------------------------------------------------------------------------
Opt("WinTitleMatchMode",2)
; Script Start - Add your code below here
Dim $WinTitle = $CmdLine[1]
Dim $Keys = $CmdLine[2]
;get user path
Dim $FlagFile = EnvGet("USERPROFILE") & "\AutoITDone.txt"
; if we don't limit the loop we could get stuck
Dim $Count = 0
While WinActivate($WinTitle)=0
	sleep(1000)
	$Count = $Count +1
	If ($Count >120) Then
		ExitLoop
	EndIf
WEnd
sleep(1000)
Send($Keys)
;tell RFT we are done here
$file=FileOpen($FlagFile, 2)
FileClose($file)