ClipPut("")

If $CmdLine[0]<1 Then 
	ClipPut(GetReturnStr(-1,"No Enough Parameters"))
	Sleep(2000)
	Exit
EndIf
ClipPut(KillProcess($CmdLine));iexplore.exe,firefox.exe
Sleep(2000)
;MsgBox(0,"From clipboard:",ClipGet())

Func KillProcess($processes)
	;close all the firefox browser
	for $i = 1 to $processes[0]
		;consolewrite($i&" : "&$processes[$i])
		While ProcessExists($processes[$i])
			ProcessClose($processes[$i])
		WEnd
	next

	for $i =1 to $processes[0]
		If ProcessExists($processes[$i]) Then
			Return GetReturnStr(-1, $processes[$i]&" can't be killed!")
		endif
	next
	Return GetReturnStr(0,"Successful")
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
	Return $returnCode&Chr(9)&ToFormatedStr($returnStr)
EndFunc
