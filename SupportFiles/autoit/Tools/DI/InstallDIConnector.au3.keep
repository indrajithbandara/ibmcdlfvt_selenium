#include "Constants.au3"
#include <A3LListView.au3>
#include <A3LMenu.au3>

Opt("WinTitleMatchMode",2)
Opt("MustDeclareVars",1)
ClipPut("")
Sleep(2000)
ClipPut(InstallConnector())
Sleep(2000)
;MsgBox(0,"From clipboard:",ClipGet())
;-------------------------------------------------------------------------------
;tihs method is used to install quickr
;@return: a string indicating the operation result: "0:{$txtName}" or "-1:reason"
;-------------------------------------------------------------------------------
Func InstallConnector() 
	;welcome window
	Dim $i=0
	While WinExists($InstallTitle,$step1Text)=False
		Sleep(2000)
		$i=$i+1
		if($i>20) Then ExitLoop
	WEnd
	If($i>20) Then
		Return GetReturnStr($Failed,"Window with title: "&$InstallTitle&" and text: "&$step1Text&" should appear")
	EndIf
	WinActivate($InstallTitle,$step1Text)
	
	Sleep(2000)
	ControlClick($InstallTitle,$step1Text,$nextText)
	;license agreement window
	if WinWaitActive($InstallTitle,$step2Text,$ResponseTime*5)=0 Then
		Return GetReturnStr($Failed,"Window with title: "&$InstallTitle&" and text: "&$step2Text&" should appear")
	EndIf
	;click agree radiobutton
	Sleep(2000)
	ControlClick($InstallTitle,$step2Text,"Button3")
	;click "Next" button
	ControlClick($InstallTitle,$step2Text,$nextText)
	;custom window to select Window explorer, Window Office, Notes, or Sametime
	if WinWaitActive($InstallTitle,$step3Text,$ResponseTime*5)=0 Then
		Return GetReturnStr($Failed,"Window with title: "&$InstallTitle&" and text: "&$step3Text&" should appear")
	EndIf
	Sleep(2000)
	;just click "Next"
	ControlClick($InstallTitle,$step3Text,$nextText)
	;
	if WinWaitActive($InstallTitle,$step4Text,$ResponseTime*5)=0 Then
		Return GetReturnStr($Failed,"Window with title: "&$InstallTitle&" and text: "&$step4Text&" should appear")
	EndIf
	Sleep(2000)
	ControlClick($InstallTitle,$step4Text,"&Install")
	
	if WinWaitActive($InstallTitle,$step5Text,$ResponseTime*5)=0 Then
		Return GetReturnStr($Failed,"Window with title: "&$InstallTitle&" and text: "&$step5Text&" should appear")
	EndIf
	While(WinExists($InstallTitle,$step5Text))
		Sleep(1000);
	WEnd;
	if WinWaitActive($InstallTitle,$step6Text,$ResponseTime*5)=0 Then
		Return GetReturnStr($Failed,"Window with title: "&$InstallTitle&" and text: "&$step6Text&" should appear")
	EndIf
	Sleep(2000)
	ControlClick($InstallTitle,$step6Text,"Button3")
	ControlClick($InstallTitle,$step6Text,"Button5")
	ControlClick($InstallTitle,$step6Text,"&Finish")
	
	Return GetReturnStr($Success,"Install Quickr Connector successfully")
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
