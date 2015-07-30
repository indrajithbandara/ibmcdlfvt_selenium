#cs ----------------------------------------------------------------------------

 AutoIt Version: 3.1.1.0
 Author:         Brian Murray

 Script Function:
	Template AutoIt script.

#ce ----------------------------------------------------------------------------

; Script Start - Add your code below here
;Dim $FeedReader = "C:\Program Files\FeedReader30\feedreader.exe"
Dim $AddonTitle = "Add-ons"

WinActivate("Add-ons")
;WinWaitActive($AddonTitle, "")
;WinActivate ($AddonTitle, "")
; button doesn't work
;ControlClick($AddonTitle, "", "&Restart Firefox")
;try send keys
Send("{TAB}{ENTER}")