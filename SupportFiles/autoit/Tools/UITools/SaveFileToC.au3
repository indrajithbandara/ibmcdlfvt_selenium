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
ClipPut(SaveFileToC($CmdLine[1]))
Sleep(2000)
;MsgBox(0,"From clipboard:",ClipGet())


;-------------------------------------------------------------------------------
;tihs method is used to save file from web to c:\
;@return: a string indicating the operation result: "0:{$txtName}" or "-1:reason"
;-------------------------------------------------------------------------------
Func SaveFileToC($SaveAsFileName) ;$parentFolderTitle should be "File Download"
	If WinExists($FileDownloadTitle) Then
		WinActivate($FileDownloadTitle)
	EndIf
	
	Dim $text="Do you want to open or save this file?"
	if WinWaitActive($FileDownloadTitle,$text,$ResponseTime)=0 Then
		Return GetReturnStr($NoTitle,$FileDownloadTitle&" dialog should be opened firstly")
	EndIf
	Sleep(1000)
	Do
		ControlClick($FileDownloadTitle,$text,"Button2") ;save the current file To $path
		Sleep(2000)
	Until Not WinExists($FileDownloadTitle,$text)
	Dim $title="Save As", $text="Save as &type:"
	if WinWaitActive($title,$text,$ResponseTime)=0 Then
		Return GetReturnStr($Failed,$title&" dialog can't appear")
	EndIf
	ControlSetText($title,$text,"Edit1","c:\"&$SaveAsFileName)
	ControlClick($title,$text,"Button2") ;click the save button
	Sleep(2000)

	If WinExists($title,$FolderExistTitle_EN) Then
		ControlClick($title,$FolderExistTitle_EN,"Button1") ;replace the existed file
	EndIf
	$title="Download complete"
	$text="Close this dialog box when download completes"
	Dim $timeLimit=$ResponseTime
	if WinWaitActive($title,$text,$timeLimit) <> 0 Then
		ControlClick($title,$text,"Button4"); click the "download complete" dialog
	EndIf
	Sleep(1000)
	If FileExists("c:\"&$SaveAsFileName) Then
		Return GetReturnStr($Success,"Download file to c:\ Wizard passed")
	Else
		Return GetReturnStr($Failed,$SaveAsFileName&" can't be found in c:\")
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
