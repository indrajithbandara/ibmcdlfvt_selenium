;#include <A3LTreeView.au3>
#include "Constants.au3"
Opt("MustDeclareVars", 1)
Opt("WinTitleMatchMode",2)
ClipPut('')
ClipPut(NewFile())
Sleep(2000)
;MsgBox(0,'return:',ClipGet())
;-------------------------------------------------------------------------------
;tihs method is used to new an office file or start word application!
;@return: a string including the result code and appendent infomation, in the format of "code:info". "0:$fileTitle" for saving successfully, otherwise, the error code and reason will be included
;---------Notes---------------
;This method is so important that if you want to operate in office DI, you must use it firstly!
;-------------------------------------------------------------------------------
Func NewFile()
;start the word application
Send("#r")
Sleep(1000)
;ControlSetText("Run","","Edit1","winword")
Send("winword{ENTER}")
Sleep(3000)
If not WinExists($wordTitle) Then
	Return GetReturnStr($Failed,"can't launch word application") ;$CanNotStartWord
EndIf
WinSetState ($wordTitle, "", @SW_MAXIMIZE)
WinActivate($wordTitle) 
MouseClick("left",200,200)
Return GetReturnStr($Success,WinGetTitle(""))
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