#include "Constants.au3"
#include <A3LMenu.au3>

Opt("WinTitleMatchMode",2)
Opt("MustDeclareVars",1)
ClipPut("")
If $CmdLine[0]<3 Then 
	ClipPut(GetReturnStr($Failed,"No Enough Parameters"))
	Sleep(2000)
	Exit
EndIf
ClipPut(ComposeMail($CmdLine[1],$CmdLine[2],$CmdLine[3]))
Sleep(2000)
;MsgBox(0,"From clipboard:",ClipGet())


;-------------------------------------------------------------------------------
;this method is used to compose a mail in MS Outlook
;it does not send it just fills in to, subject and text of message
;@$To: the person to send the mail to
;@$Subject: the subject of the mail
;@$Text: the text of the email
;@return: a string indicating the operation result: "0:{$wordName}" or "-1:reason"
;-----------Notes----------
;-------------------------------------------------------------------------------
Func ComposeMail($To,$Subject, $Text)
	
	If WinExists($MicrosoftOutlook_EN) Then
		WinActivate($MicrosoftOutlook_EN)
		WinSetState($MicrosoftOutlook_EN,"",@SW_MAXIMIZE)
	EndIf
	
	if WinWaitActive($MicrosoftOutlook_EN,"",$ResponseTime)=0 Then
		Return GetReturnStr($NoTitle,$MicrosoftOutlook_EN&" folder should be opened first")
	EndIf
	;cntrl-n to do new mail
	Send("^n")
		If WinExists($MSOutlookMessage_EN) Then
		WinActivate($MSOutlookMessage_EN)
		WinSetState($MSOutlookMessage_EN,"",@SW_MAXIMIZE)
	EndIf
	sleep(2000)
	ControlSetText($MSOutlookMessage_EN,"","RichEdit20WPT2",$To)
	sleep(2000)
	;default place is to
	;Send($To)
	;two tab to subject
	Send("{TAB}")
	Send("{TAB}")
	Send($Subject)
	;ControlFocus($MSOutlookMessage_EN,"","RichEdit20WPT5")
	;;ControlSetText($MSOutlookMessage_EN,"","RichEdit20WPT5",$Subject)
	;tab to email text
	Send("{TAB}")
	Send($Text)
	Return GetReturnStr($Success,'Successful')
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
