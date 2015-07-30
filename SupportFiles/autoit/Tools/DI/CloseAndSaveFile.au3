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
ElseIf $CmdLine[0]<3 Then 
	ClipPut(CloseAndSaveFile($CmdLine[1],$CmdLine[2],"true"))
	Sleep(2000)
Else
	ClipPut(CloseAndSaveFile($CmdLine[1],$CmdLine[2],$CmdLine[3]))
	Sleep(2000)
EndIf

;MsgBox(0,"Return:",ClipGet())

;-------------------------------------------------------------------------------
;tihs method is used to save an open document to the specified library path
;@$fileTitle: the name of the file waiting to be open
;@$saveOrNot: the switch to control whether save the file to the library or not 
;@return: a string including the result code and appendent infomation, in the format of "code:info". "0:Success" for saving successfully, otherwise, the error code and reason will be included
;-------------------Important Notes-------------------
;before call this method, ensure that the file with $fileTitle should be open from a place
;-------------------------------------------------------------------------------

Func CloseAndSaveFile($fileTitle,$saveOrNot,$CheckIn)
	If not WinExists($fileTitle) Then
		Return GetReturnStr($Failed,"File not open yet, please open it firstly")
	EndIf
	WinActivate($fileTitle) 
	WinSetState ($fileTitle, "", @SW_MAXIMIZE)
	MouseClick("left",200,200)
	Sleep(1000)
	Send("!{F4}")
	Sleep(2000)

	if WinExists("Microsoft Word","Do you want to save the changes to") Then
		if StringInStr($saveOrNot,"true") Then
			ControlClick("Microsoft Word","Do you want to save the changes to","Button1")
			Return GetReturnStr($Success,"Close the file and save it!")

		Else
			ControlClick("Microsoft Word","Do you want to save the changes to","Button2")
			Return GetReturnStr($Success,"Close the file and not save it!")
		EndIf
	EndIf

	if WinExists($LOTUSQUICKR,"Do you want to save your current changes") Then
		if StringInStr($saveOrNot,"true") Then
			ControlClick($LOTUSQUICKR,"Do you want to save your current changes","Button1")
				While WinWaitActive($LOTUSQUICKR,"",$ResponseTime/10) 
					if $CheckIn = "true" Then
						ControlClick($LOTUSQUICKR,"","Button1") ;check it In
					else
						ControlClick($LOTUSQUICKR,"","Button2") ;dont check it In
					EndIf	
				WEnd
			Return GetReturnStr($Success,"Close the file and save it!")

		Else
			ControlClick($LOTUSQUICKR,"Do you want to save your current changes","Button2")
			Return GetReturnStr($Success,"Close the file and not save it!")
		EndIf
	EndIf

	if WinExists("WordPad","Save changes to") Then
		if StringInStr($saveOrNot,"true") Then
			ControlClick("WordPad","Save changes to","Button1")
			Return GetReturnStr($Success,"Close the file and save it!")
		Else
			ControlClick("WordPad","Save changes to","Button2")
			Return GetReturnStr($Success,"Close the file and not save it!")
		EndIf
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
