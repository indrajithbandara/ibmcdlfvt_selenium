#include <A3LTreeView.au3>
;#include <GUIListView.au3>
#include "Constants.au3"
;$addPlaceTitle="Add Places to Lotus Quickr connectors"
;test data :Document2.doc;True;
;Document2 - Microsoft Word
Opt("MustDeclareVars", 1)
Opt("WinTitleMatchMode",2)
ClipPut('')
If $CmdLine[0]<3 Then
	ClipPut(GetReturnStr($Failed,"No Enough Parameters"))
	Sleep(2000)
	Exit
EndIf


ClipPut(AppendTextToPPTFile($CmdLine[1],$CmdLine[2],$CmdLine[3]))
Sleep(2000)
;MsgBox(0,"Return:",ClipGet())
;-------------------------------------------------------------------------------
;tihs method is used to append some text to an open document
;$fileTitle: the title of the open file
;$appendText: this $appendText will be added to the file after entering the tail
;@return: a string including the result code and appendent infomation, in the format of "code:info". "0:{CurrentTitle}" for saving successfully, otherwise, the error code and reason will be included
;-------------------------------------------------------------------------------

Func AppendTextToPPTFile($fileTitle,$appendText,$NewFile)

If not WinExists($fileTitle) Then
	Return GetReturnStr($Failed," File not open yet, please open it firstly");$FileNotOpenYet
EndIf
WinActivate($fileTitle) 
WinSetState ($fileTitle, "", @SW_MAXIMIZE)
;1st to create if new, if not it won't do anything
If $NewFile = "Yes"  Then
	MouseClick("left",300,300)
	Sleep(2000)
	;now click for text
	MouseClick("left",400,400)
Else
	;not new file so click for subtitle
	MouseClick("left",600,525)
EndIf
Send($appendText)
Sleep(2000)
;two escapes to get out of edit mode so menu changes from drawing object in 2007
Send("{ESC}{ESC}")

Return GetReturnStr($Success,WinGetTitle(''))
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
