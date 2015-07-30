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
Dim $FlagFile = EnvGet("USERPROFILE") & "\AutoITDone.txt"
;Dim $FeedLink = "http://qpgfvt1.lotus.com:10038/dm/atom/library/7ec1af8043c77b8b8c3fdf089ebd96cd/folder/5554c60043d34c6d8e1ddf089ebd96cd?pageHint=6_O2B60C020GH8802UNJ875H30M0"
Dim $FeedLink = $CmdLine[2]
Dim $Warning = "Warning"
Dim $AlreadyExists = "You have already subscribed to this feed and it exists in database"
Dim $ConnectTo = "Connect to"
;Dim $PW = "Pa88w0rd"
Dim $PW = $CmdLine[4]
;Dim $ID = 	
Dim $ID = $CmdLine[3]
Dim $Confirm = "Confirm"
Dim $ItemNumber

Run($FeedReader)
WinWaitActive($FeedReaderTitle)
$whandle = WinGetHandle($FeedReaderTitle)
; let's maximize
WinSetState ( $FeedReaderTitle, "", @SW_MAXIMIZE)
;before we do anything let's wait for dialog asking us to check for new version
If (WinWaitActive("Information", "", 5)=1) Then
	;if we got this dialog then just click OK and resume
	ControlClick("Information", "", "&No")
EndIf
;http://qpgfvt1.lotus.com:10038/wps/mypoc?uri=dm:34f06a0043d34f008e1edf089ebd96cd&amp;verb=view&amp;pageHint=6_O2B60C020GH8802UNJ875H30M0
;{F3} ket will activate new feed
Send("{F3}")
; wait for 2 secs
sleep(2000);
ControlSend($FeedReaderTitle, "", "TTntEdit.UnicodeClass1", $FeedLink)
;click OK
ControlClick ($FeedReaderTitle, "","TTntButton.UnicodeClass2")
If (WinWaitActive($Warning, "", 2)=1) Then
	;if we got this dialog then just click OK and resume
	ControlClick($Warning, "", "TButton1")
	; then click cancel
	ControlClick ($FeedReaderTitle, "","TTntButton.UnicodeClass1")
EndIf
; password box should come up
If (WinWaitActive($ConnectTo, "", 5)=1) Then
	;if we got this dialog then enter the password and ID
	ControlSend($ConnectTo, "", "TTntEdit.UnicodeClass2", $ID)
	ControlSend($ConnectTo, "", "TTntEdit.UnicodeClass1", $PW)
	ControlClick($ConnectTo, "", "TTntButton.UnicodeClass2")
EndIf

sleep(7000);
;ok sometimes we get a box here that we need to click OK To
If (WinWaitActive($Confirm, "", 3)=1) Then
	;just click yes button
	ControlClick($Confirm, "", "TTntButton.UnicodeClass2")
EndIf

$pos = ControlGetPos($FeedReaderTitle, "", "TtomgradientPanel1")
;click anywhere basically in this pane
MouseClick("",$pos[0]+300, $pos[1]+200)
;then down a bunch to make sure we have it
Send("{DOWN}{DOWN}{DOWN}{DOWN}")
;focus should be on it now
; actually don't need to hit down now that we are going to open in 2nd window
;Send("{ENTER}")

$a = WinGetCaretPos()
$b = _CaretPos()
If Not @error Then ToolTip("Second Method Pos", $b[0], $a[1])
sleep(2000)

;MsgBox(0, "here we are", $b[0] & "," & $b[1])
;ok, we should have the position of the doc we want, so let's right click it and then down 7 and enter
; after that we should get a browser window and control back to RFT

MouseClick("right",$b[0]+10, $b[1] + 10)
Send("{DOWN 6}")
sleep(2000)
Send("{ENTER}")

; browser should now be open with feed, so we should be able to close feedreader
;closing this seems to mess up running a 2nd time
WinClose($FeedReaderTitle, "")
;tell RFT we are done here
$file=FileOpen($FlagFile, 2)
FileClose($file)

; More reliable method to get caret coords in MDI text editors.
Func _CaretPos()
    Local $x_adjust =  5
    Local $y_adjust = 40

    Opt("CaretCoordMode", 0)              ;relative mode
    Local $c = WinGetCaretPos()           ;relative caret coords
    Local $w = WinGetPos("")              ;window's coords
    Local $f = ControlGetFocus("","")     ;text region "handle"
    Local $e = ControlGetPos("", "", $f)  ;text region coords

    Local $t[2]
    If IsArray($c) and IsArray($w) and IsArray($e) Then
        $t[0] = $c[0] + $w[0] + $e[0] + $x_adjust
        $t[1] = $c[1] + $w[1] + $e[1] + $y_adjust
        Return $t     ;absolute screen coords of caret cursor
    Else
        SetError(1)
    EndIf
EndFunc





