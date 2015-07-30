#include "Constants.au3"
Opt("WinTitleMatchMode",2)
ClipPut("")
If $CmdLine[0]<1 Then 
	ClipPut(GetReturnStr($Failed,$NoEnoughParameter))
	Sleep(2000)
	Exit
EndIf

ClipPut(CloseFolder($CmdLine[1]));
Sleep(2000)
;MsgBox(0,"From clipboard:",clipget())

Func CloseFolder($folderTitle)
	 If WinExists($folderTitle) Then 
		 WinActivate($folderTitle)
		 WinClose($folderTitle)
		 Return GetReturnStr($Success,'Successful')
	 Else
		 Return GetReturnStr($Failed,"the folder doesn't open!")
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
