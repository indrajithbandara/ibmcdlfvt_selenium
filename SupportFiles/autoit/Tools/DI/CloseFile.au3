#include <A3LTreeView.au3>
;#include <GUIListView.au3>
#include "Constants.au3"

;test data :Document2.doc;True 
Opt("MustDeclareVars", 1)
Opt("WinTitleMatchMode",2)
ClipPut('')
If $CmdLine[0]<2 Then
	ClipPut(GetReturnStr($Failed,"No Enough Parameters"))
	Sleep(2000)
	Exit
EndIf
ClipPut(ClosePlaceFile($CmdLine[1],$CmdLine[2]))
Sleep(2000)
;MsgBox(0,"Return:",ClipGet())

;-------------------------------------------------------------------------------
;tihs method is used to save an open document to the specified library path
;@$fileTitle: the name of the file waiting to be open
;@$checkInOrNot: the switch to control whether check in the file to the library or not 
;@return: a string including the result code and appendent infomation, in the format of "code:info". "0:Success" for saving successfully, otherwise, the error code and reason will be included
;-------------------Important Notes-------------------
;before call this method, ensure that the file with $fileTitle should be open from a place
;-------------------------------------------------------------------------------

Func ClosePlaceFile($fileTitle,$checkIn)
If not WinExists($fileTitle) Then
	Return GetReturnStr($Failed,"File not open yet, please open it firstly")
EndIf
WinActivate($fileTitle) 
WinSetState ($fileTitle, "", @SW_MAXIMIZE)
MouseClick("left",200,200)
Sleep(1000)
Send("!{F4}")
Sleep(2000)

;great but in Quickr this box is titled differently
if WinExists("Microsoft Word","Do you want to save the changes to") Then
	ControlClick("Microsoft Word","Do you want to save the changes to","Button2")
	Return GetReturnStr($Success,"Close the file and not save it!")
EndIf


While WinWaitActive($LOTUSQUICKR,"",$ResponseTime/10) 
	if $CheckIn = "true" Then
		ControlClick($LOTUSQUICKR,"","Button1") ;check it In
	else
		ControlClick($LOTUSQUICKR,"","Button2") ;dont check it In
	EndIf	
WEnd

sleep(5000)
dim $i=0
While WinExists($fileTitle) And $i < 100
	$i=$i+1
	sleep(1000)
WEnd

If not WinExists($fileTitle) Then
	Return GetReturnStr($Success,"Successful")
Else
	Return GetReturnStr($Failed,"Maybe some files with same title existed!")
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
