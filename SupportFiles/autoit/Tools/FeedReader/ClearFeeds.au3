#cs ----------------------------------------------------------------------------

 AutoIt Version: 3.1.1.0
 Author:         Brian Murray

 Script Function:
	Template AutoIt script.

#ce ----------------------------------------------------------------------------

; Script Start - Add your code below here
;Dim $FeedReader = "C:\Program Files\FeedReader30\feedreader.exe"
Dim $FeedReader = $CmdLine[1]
Dim $FeedReaderTitle = "Feedreader"
Dim $Confirm = "Confirm"
Dim $FlagFile = EnvGet("USERPROFILE") & "\AutoITDone.txt"

Run($FeedReader)
WinWaitActive($FeedReaderTitle)
;WinActivate($FeedReaderTitle)
$whandle = WinGetHandle($FeedReaderTitle)
; let's maximize
WinSetState ( $FeedReaderTitle, "", @SW_MAXIMIZE)
;let's get in the menu, this doesn't work so use send
;before we do anything let's wait for dialog asking us to check for new version
If (WinWaitActive("Information", "", 5)=1) Then
	;if we got this dialog then just click OK and resume
	ControlClick("Information", "", "&No")
EndIf
;WinMenuSelectItem ( $FeedReaderTitle, "", "&File" , "&Manage feeds")
send ("{ALT}FM")
;ok wait for dialog and then click delete feed
sleep(2000)
ControlClick($FeedReaderTitle, "", "Delete feed")
;dialog asking for confirm
If (WinWaitActive($Confirm, "", 2)=1) Then
	ControlClick($Confirm, "", "&Yes")
EndIf

WinClose ($FeedReaderTitle)
;tell RFT we are done here
$file=FileOpen($FlagFile, 2)
FileClose($file)
