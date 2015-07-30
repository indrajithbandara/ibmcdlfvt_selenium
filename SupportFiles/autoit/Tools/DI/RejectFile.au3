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

ClipPut(RejectFile($CmdLine[1]))
Sleep(2000)
;MsgBox(0,"Return:",ClipGet())
;-------------------------------------------------------------------------------
;tihs method is used to reject the file in office
;@$fileTitle: the name of the file waiting to be checked in
;@return: a string including the result code and appendent infomation, in the format of "code:info". "0:Success" for saving successfully, otherwise, the error code and reason will be included
;---Important Notes----
;before call this method, ensure that the file with $fileTitle should be open from a place
;-------------------------------------------------------------------------------

Func RejectFile($fileTitle)

If not WinExists($fileTitle) Then
	Return GetReturnStr($Failed,"File not open yet, please open it firstly")
EndIf
WinActivate($fileTitle) 
WinSetState ($fileTitle, "", @SW_MAXIMIZE)
MouseClick("left",200,200)
Send("!H")
Send("{RIGHT}")
Send("r")
If WinWaitActive($LOTUSQUICKR,"has been rejected",$ResponseTime/20)=0 Then ;The document has been rejected.  Would you like to close the current document?  
	Return GetReturnStr($Failed,"Maybe the reject menuitem is invalid!")
Else
	ControlClick($LOTUSQUICKR,"has been rejected","Button2")
	Return GetReturnStr($Success,"Successful")
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
