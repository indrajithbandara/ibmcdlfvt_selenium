#cs ----------------------------------------------------------------------------

 AutoIt Version: 3.2.4.9
 Author:         myName

 Script Function:
	Template AutoIt script.

#ce ----------------------------------------------------------------------------

; Script Start - Add your code below here

Opt("WinWaitDelay",100)

ConsoleWrite(checkOffice())
;Sleep(1000)
Exit


Func checkOffice()
	if FileExists(@ProgramFilesDir&"\Microsoft Office\OFFICE10") Then
		return 1;
	Else
		return 0;
	EndIf
EndFunc

