#include <A3LTreeView.au3>
;#include <GUIListView.au3>
#include "Constants.au3"
;$addPlaceTitle="Add Places to Lotus Quickr connectors"
;test data :Document2.doc;True; True 
Opt("MustDeclareVars", 1)
Opt("WinTitleMatchMode",2)
ClipPut('')
If $CmdLine[0]<2 Then
	ClipPut(GetReturnStr($Failed,"No Enough Parameters"))
	Sleep(2000)
	Exit
EndIf

ClipPut(CheckInFile($CmdLine[1],$CmdLine[2]))
Sleep(2000)
;MsgBox(0,"Return:",ClipGet())
;-------------------------------------------------------------------------------
;tihs method is used to check in an open document
;@$fileTitle: the name of the file waiting to be checked in
;@$closeOrNot: after checking in, closing the file or not is decided by this switch.
;@return: a string including the result code and appendent infomation, in the format of "code:info". "0:Success" for saving successfully, otherwise, the error code and reason will be included
;---Important Notes----
;before call this method, ensure that the file with $fileTitle should be open from a place, and is in checking-out state
;-------------------------------------------------------------------------------

Func CheckInFile($fileTitle,$closeOrNot)

If not WinExists($fileTitle) Then
	Return GetReturnStr($Failed,"File not open yet, please open it firstly")
EndIf
WinActivate($fileTitle) 
WinSetState ($fileTitle, "", @SW_MAXIMIZE)
MouseClick("left",200,200)
Send("!H")
Send("{RIGHT}")
Send("c")

if WinWaitActive($LOTUSQUICKR,$saveOrNotText,$ResponseTime/20) Then
	ControlClick($LOTUSQUICKR,$saveOrNotText,"Button2")
	Return GetReturnStr($Failed,"Please save file firstly!")
EndIf

If WinWaitActive($LOTUSQUICKR,$HasCheckedIn,$ResponseTime/20) Then
	If StringInStr($closeOrNot,'true') Then
		ControlClick($LOTUSQUICKR,$HasCheckedIn,"Button1")
	ElseIf StringInStr($closeOrNot,'false') Then
		ControlClick($LOTUSQUICKR,$HasCheckedIn,"Button2")
	Else
		WinClose($LOTUSQUICKR,$HasCheckedIn)
		Return GetReturnStr($Failed,"The second parameter is wrong!")
	EndIf
	Sleep(1000)
	Return GetReturnStr($Success,"Successful")
EndIf
Return GetReturnStr($Failed,"Maybe the check-in menuitem is invalid!")
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
