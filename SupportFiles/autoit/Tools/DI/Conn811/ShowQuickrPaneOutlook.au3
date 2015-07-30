#include "Constants.au3"
#include <A3LListView.au3>
#include <A3LTreeView.au3>
#include <A3LMenu.au3>

Opt("WinTitleMatchMode",2)
Opt("MustDeclareVars",1)
ClipPut("")
;If $CmdLine[0]<3 Then 
;	ClipPut(GetReturnStr($Failed,"No Enough Parameters"))
;	Sleep(2000)
;	Exit
;EndIf	
ClipPut(ShowPane())

Sleep(2000)
;MsgBox(0,"From clipboard:",ClipGet())


;-------------------------------------------------------------------------------
;tihs method is used to Create a link in an Outlook Mail
;@$parentFolderTitle: the title of the folder which has been open to place the new folder, that's to say, call openfolder() firstly
;@$wordName: the name of the new folder waiting to be created, 
;@$description: the description text of the folder created
;@return: a string indicating the operation result: "0:{$wordName}" or "-1:reason"
;-----------Notes----------
;No matter whether the folder is created successfully, this method will close the $parentFolderTitle
;-------------------------------------------------------------------------------
Func ShowPane()
	If WinExists($MicrosoftOutlook_EN) Then
		WinActivate($MicrosoftOutlook_EN)
		WinSetState($MicrosoftOutlook_EN,"",@SW_MAXIMIZE)
	EndIf
	;lets' see of lotus control pane is up yet
	If (ControlCommand($MicrosoftOutlook_EN, "", "[CLASS:MsoCommandBar; TEXT:Lotus Task Pane]","IsVisible")=0) Then
		;MsgBox(0,"test", "QuickrPane is Not displayed")
		;ok display it
		Send("!f")
		Send("{LEFT 2}")
		Send("{DOWN 3}{ENTER}")
		sleep(2000)
		;check to make sure and if not there then error out
		If (ControlCommand($MicrosoftOutlook_EN, "", "[CLASS:MsoCommandBar; TEXT:Lotus Task Pane]","IsVisible")=0) Then
			;MsgBox(0,"test", "QuickrPane is Not displayed")
			Return GetReturnStr($Failed,"Quickr must not be installed")
		EndIf	
	Else
		;MsgBox(0,"test", "QuickrPane is displayed")
	EndIf
	;great we know pane is up, but lets refresh it so it shows Team Places instead of Add Place button
	dim $pos = ControlGetPos($MicrosoftOutlook_EN, "", "[CLASS:MsoCommandBar; TEXT:Lotus Task Pane]")
	MouseClick("left", $pos[0]+$pos[2]-10, $pos[1]+40)
	Send("{DOWN 2}{ENTER")
	Return GetReturnStr($Success,"Successful")

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
