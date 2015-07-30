#include "Constants.au3"
#include <A3LListView.au3>
#include <A3LMenu.au3>

Opt("WinTitleMatchMode",2)
Opt("MustDeclareVars",1)
ClipPut("")
ClipPut(SaveDISetupFile())
Sleep(2000)
;MsgBox(0,"From clipboard:",ClipGet())

;-------------------------------------------------------------------------------
;tihs method is used to save setup file for DI to c:\
;@return: a string indicating the operation result: "0:{$txtName}" or "-1:reason"
;-------------------------------------------------------------------------------
Func SaveDISetupFile() ;$parentFolderTitle should be "File Download - Security Warning"
	Dim $title="Download complete"
	Dim $text="Close this dialog box when download completes"
	Dim $timeLimit=10
	if WinWaitActive($title,"Download Complete",$timeLimit)=0 Then
		Return GetReturnStr($Failed,$title&" dialog can't appear in "&$timeLimit&" seconds")
	EndIf
	ControlClick($title,"Download Complete","Button1") ;select the checkbox of close after downloading
	While WinExists($title,"Download Complete")
		Sleep(1000)
	WEnd
	Sleep(3000)
	$title = "Download complete"
	if WinExists($title,"Download Complete") Then
		WinActivate($title, "Download Complete")
		ControlClick($title, "Download Complete","Button4")
		Sleep(1000)
	EndIf
	
	Return GetReturnStr($Success,"Download setup file to c:\ Wizard passed")
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
