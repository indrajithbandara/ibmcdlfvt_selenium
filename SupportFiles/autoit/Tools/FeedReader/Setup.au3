#include "Constants.au3"
#cs ----------------------------------------------------------------------------

 AutoIt Version: 3.1.1.0
 Author:         George zhang

 Script Function:
	check whether there is a Quickr in this machine

#ce ----------------------------------------------------------------------------
ClipPut("")
If $CmdLine[0]<1 Then 
	ClipPut(GetReturnStr(-1,"No Enough Parameters"))
	Sleep(2000)
	;MsgBox(0,"From clipboard:",ClipGet())
	Exit
EndIf
ClipPut(setupFeedReader($CmdLine[1]));
Sleep(2000)
; Script Start - Add your code below here
Func setupFeedReader($FeedReader)
	; Script Start - Add your code below here
	;Dim $FeedReader = "C:\Program Files\FeedReader30\feedreader.exe"
	Dim $FeedReaderTitle = "Feedreader"
	Dim $Confirm = "Confirm"
	Run($FeedReader)
	WinWaitActive($FeedReaderTitle)
	$whandle = WinGetHandle($FeedReaderTitle)
	; let's maximize
	WinSetState ( $FeedReaderTitle, "", @SW_MAXIMIZE)
	;before we do anything let's wait for dialog asking us to check for new version
	If (WinWaitActive("Information", "", 13)=1) Then
		;if we got this dialog then just click OK and resume
		ControlClick("Information", "", "&No")
	Else
		Return GetReturnStr($Failed,"Could not activate information window.")
	EndIf
	WinClose ($FeedReaderTitle)
	Return GetReturnStr($Success,"Complete setup work for feedreader")
EndFunc

;------------------------------------------------------------
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



