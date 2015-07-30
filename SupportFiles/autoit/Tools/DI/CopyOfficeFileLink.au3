#include <A3LTreeView.au3>
;#include <GUIListView.au3>
#include "Constants.au3"
;$addPlaceTitle="Add Places to Lotus Quickr connectors"
;test data1 : Document2 - Microsoft Word
Opt("MustDeclareVars", 1)
Opt("WinTitleMatchMode",2)
ClipPut('')
If $CmdLine[0]<1 Then
	ClipPut(GetReturnStr($Failed,"No Enough Parameters"))
	Sleep(2000)
	Exit
EndIf


ClipPut(CopyFileLink($CmdLine[1]))
Sleep(2000)
;MsgBox(0,"Return:",ClipGet())
;-------------------------------------------------------------------------------
;tihs method is used to check out an open document
;@$fileTitle: the name of the file waiting to be checked in
;$downloadOrNot: the switch to control whether download the file from server
;@return: a string including the result code and appendent infomation, in the format of "code:info". "0:Success" for saving successfully, otherwise, the error code and reason will be included
;---Important Notes----
;before call this method, ensure that the file with $fileTitle should be open from a place, and is in checking-in state
;-------------------------------------------------------------------------------

Func CopyFileLink($fileTitle)

If not WinExists($fileTitle) Then
	Return GetReturnStr($Failed,,"File not open yet, please open it firstly")
EndIf
WinActivate($fileTitle) 
WinSetState ($fileTitle, "", @SW_MAXIMIZE)
MouseClick("left",200,200)
Send("!H")
Send("{RIGHT}")
Send("l")
Sleep(3000)
Dim $linkFromClip=ClipGet() ;http://9.125.74.26:10038/lotus/mypoc?uri=dm:8fb14a8046d67435898ab9bca6ae7c30&verb=view
If $linkFromClip="" Then
	Return GetReturnStr($Failed,"Copy operation Failed")
EndIf

If StringInStr($linkFromClip,'http://') Then
	Return GetReturnStr($Success,$linkFromClip)
Else
	Return GetReturnStr($Failed,"Failed, maybe file isn't from library") ;I don't know the reason exactly, maybe other application copy something to the clipboard
EndIf

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