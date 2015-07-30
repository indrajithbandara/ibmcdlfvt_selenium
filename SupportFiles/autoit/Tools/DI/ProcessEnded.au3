#include "Constants.au3"
Opt("WinTitleMatchMode",2)
ClipPut("")
If $CmdLine[0]<1 Then 
	ClipPut(GetReturnStr($Failed,"No Enough Parameters"))
	Sleep(2000)
	;MsgBox(0,"From clipboard:",clipget())
	Exit
EndIf
If $CmdLine[0]=1 Then
	ClipPut(ProcessEnded($CmdLine[1],""));
Else
	ClipPut(ProcessEnded($CmdLine[1],$CmdLine[2]));
EndIf

Sleep(2000)
;MsgBox(0,"From clipboard:",clipget())

Func ProcessEnded($processName,$timeLimit)
	Dim $waitForEver=False
	If $timeLimit='' Then
		$waitForEver=True
	EndIf
	Dim $result=-1
	If $waitForEver Then
		$result=ProcessWaitClose($processName)
	Else
		$result=ProcessWaitClose($processName,$timeLimit)
	EndIf
	
	If Not $result Then
		ProcessClose($processName)
		Return GetReturnStr($Failed,"Process can't end in "&$timeLimit&" seconds! So, close it forcely!")
	Else
		Return GetReturnStr($Success,'Successful')
	EndIf
EndFunc

;-------------------------------------------------------------------------------
;tihs method is used to format the given string,used for better communication with java process
;@$str: the string will be formatted.':' will be replaced by 'xy', and 'x' will be replaced by 'xz'
;@return: a formated string for java process to resolve to get its content
;-------------------------------------------------------------------------------
Func ToFormatedStr($str)
	;Dim $formatedStr=StringReplace(StringReplace($str,'x','xz'),$Separator,'xy')
	Return $str;$formatedStr
EndFunc

Func GetReturnStr($returnCode,$returnStr)
	Return $returnCode&$Separator&ToFormatedStr($returnStr)
EndFunc
