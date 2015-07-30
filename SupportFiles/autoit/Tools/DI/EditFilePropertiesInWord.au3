#include "Constants.au3"
#include <A3LListView.au3>
#include <A3LMenu.au3>
Opt("WinTitleMatchMode",2)
ClipPut("")
If $CmdLine[0]<3 Then 
	ClipPut(GetReturnStr($Failed,"No Enough Parameters"))
	Sleep(2000)
	Exit
EndIf
ClipPut(EditFileProps($CmdLine[1],$CmdLine[2],$CmdLine[3]))
Sleep(2000)
;MsgBox(0,'Return:',ClipGet())

;-------------------------------------------------------------------------------
;tihs method is used to open a given file
;@$parentFolderTitle: the title of the file's parent folder, that's to say, you need to open the folder (call OpenFolder())firstly which the file is located!
;@$fileName : the file that will be opened
;@return: a string indicating the result of this operation: "$Success:fileTitle" or "$Failed:{reason}"
;-------------------------------------------------------------------------------
Func EditFileProps($fileName,$fileTitle,$fileDesc)
	If WinExists($MicrosoftWord_EN) Then
		WinSetState($MicrosoftWord_EN,"",@SW_MAXIMIZE)
		WinActivate($MicrosoftWord_EN)
	EndIf
	
	;let's get the quickr properties dialog up as simply as possible
	Send("{ALT}{LEFT 2}{DOWN}r")
	;the strings don't work to activate menu because its not standard

	If WinWaitActive($fileName,"",$ResponseTime/10)=0 Then
		;MsgBox(1,"test2",WinGetTitle(''))
		Return GetReturnStr($Failed,"Impossible reason for not opening specified file!")
	EndIf
	Sleep(2000)
	;fill in new title and desc.
	ControlSetText($fileName,"","Edit1",$fileTitle)
	ControlSetText($fileName,"","Edit2",$fileDesc)
	ControlClick($fileName,"","Button3")
	If not WinExists($fileTitle) Then
		Return GetReturnStr($Success,"Successful")
	Else
		Return GetReturnStr($Failed,"Maybe some files with same title existed!")
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