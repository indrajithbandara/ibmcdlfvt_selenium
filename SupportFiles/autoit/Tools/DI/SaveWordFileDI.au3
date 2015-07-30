#include <A3LTreeView.au3>
;#include <GUIListView.au3>
#include "Constants.au3"
;$addPlaceTitle="Add Places to Lotus Quickr connectors"
;test data :Document2.doc "Lotus Quickr\TestCases on 9.125.74.26" True 
;test data2: asdfasdkfad asdfasdkfad.doc "Lotus Quickr\LibReader1 on lwptszpro01" True
Opt("MustDeclareVars", 1)
Opt("WinTitleMatchMode",2)
ClipPut('')
If $CmdLine[0]<1 Then
	ClipPut(GetReturnStr($Failed,"No Enough Parameters"))
	Sleep(2000)
	Exit
EndIf


ClipPut(Save($CmdLine[1]))
;Save("GUATWordDocTest.doc")
Sleep(2000)
;MsgBox(0,"Return:",ClipGet())
;-------------------------------------------------------------------------------
;tihs method is used to save an open document to the specified library path
;@$fileTitle: the window title of the file waiting to be saved as
;@$fileNewName: the new name of the file waiting to be saved as 
;@$savePath: the path which the file will be placed
;@$overrideOrNot: the switch to control whether replace the already file with same name or not 
;@return: a string including the result code and appendent infomation, in the format of "code:info". "0:Success" for saving successfully, otherwise, the error code and reason will be included
;---Important Notes----
;before call this method, ensure that the file with $fileName should be open from a place
;-------------------------------------------------------------------------------

Func Save($fileTitle)

If not WinExists($fileTitle) Then
	Return GetReturnStr($Failed,"File not open yet, please open it firstly")
EndIf
WinActivate($fileTitle) 
WinSetState ($fileTitle, "", @SW_MAXIMIZE)
Send("^s")
sleep(2000)
;Office 7 pops up a dialog here to save in different format..click yes
if WinExists("Microsoft Office","To overwrite, click Yes.") Then
	ControlClick("Microsoft Office","To overwrite, click Yes.","Button1")
EndIf
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
