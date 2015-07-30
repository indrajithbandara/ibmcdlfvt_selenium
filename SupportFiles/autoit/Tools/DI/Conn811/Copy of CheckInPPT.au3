#include "Constants.au3"

#include <A3LListView.au3>
#include <A3LMenu.au3>
Opt("WinTitleMatchMode",2)
Opt("MustDeclareVars",1)
ClipPut("")
If $CmdLine[0]<1 Then 
	ClipPut(GetReturnStr($Failed,"No Enough Parameters"))
	Sleep(2000)
	Exit
EndIf
ClipPut(CheckIn($CmdLine[1]))
Sleep(2000)
;MsgBox(0,'Return:',ClipGet())

;-------------------------------------------------------------------------------
;tihs method is used to check in the given file( it's not right for folder)
;@$parentFolderTitle: the title of the file's parent folder, that's to say, you need to open the folder (call OpenFolder())firstly which the file is located!
;@$fileName : the file that need to copy link
;@return: a string indicating the result of this operation: "$Success:{link}" or "$Failed:Null"
;-------------------------------------------------------------------------------
Func CheckIn($fileName)
	If WinExists($fileName) Then
		WinSetState($fileName,"",@SW_MAXIMIZE)
		WinActivate($fileName)
	EndIf
	dim $CheckInMess
	WinActivate($fileName)
	;let's get the quickr checkin dialog up as simply as possible
	;a gotcha, the add-ons menu changed in office 2007
	If FileExists("c:\Program Files\microsoft office\office12") Or FileExists("d:\Program Files\microsoft office\office12") Then
		;MsgBox (0,"Office Check", "Office 2007 is installed")
		Send("!h{LEFT 3}")
		Send("{DOWN}{RIGHT}{ENTER}{UP}{UP}{UP}{UP}{UP}{UP}{UP}")
	Else
		;MsgBox (0,"Office Check", "An earlier than Office 2007 version is installed")
		Send("{ALT}{LEFT 2}{UP}{UP}{UP}{UP}{UP}{UP}{UP}{UP}")
	EndIf
	Send("{RIGHT}i")
	If WinWaitActive($LOTUSQUICKROld,"",$ResponseTime/20) Then
		$CheckInMess=WinGetText($LOTUSQUICKROld,"")
		ControlClick($LOTUSQUICKROld,"","Button2")
	EndIf
	;MsgBox(0,"hi",$CheckInMess)	
	
	If StringInStr($CheckInMess,$CheckInGood_EN) > 0 Then
		Return GetReturnStr($Success,'Successful')
	Else
		Return GetReturnStr($Failed,$CheckInMess)
	EndIf
	
EndFunc

;-------------------------------------------------------------------------------
;tihs method is used to format the given string,used for better communication with java process
;@$str: the string will be formatted.':' will be replaced by 'xy', and 'x' will be replaced by 'xz'
;@return: a formated string for java process to resolve to get its content
;-------------------------------------------------------------------------------
Func ToFormatedStr($str)
	;Dim $formatedStr=StringReplace(StringReplace($str,'x','xz'),':','xy')
	Return $str; $formatedStr
EndFunc

Func GetReturnStr($returnCode,$returnStr)
	Return $returnCode&$Separator&ToFormatedStr($returnStr)
EndFunc