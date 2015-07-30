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
ClipPut(BrowseFile($CmdLine[1]))
Sleep(2000)
;MsgBox(0,"From clipboard:",ClipGet())


;-------------------------------------------------------------------------------
;tihs method is used to open a file from given path
;@return: a string indicating the operation result: "0:{$txtName}" or "-1:reason"
;-------------------------------------------------------------------------------
Func BrowseFile($openFileName) ;$parentFolderTitle should be "Choose File to Upload"(IE8)/"File Upload"(FF2.0)/"Choose file"(IE7)
	Dim $aArray[4]=["Choose","Upload","Select","File"]
	Dim $title, $element,$text="File &name:"
	FOR $element IN $aArray
		$title = WinGetTitle($element)
		if($title <> "0") Then ExitLoop
		$title = WinGetTitle(StringLower($element))
		if($title <> "0") Then ExitLoop	
	NEXT
	If($title="0") Then
		Return GetReturnStr($Failed,"No windows matching "&$aArray[0]&" or "&$aArray[1]&" or "&$aArray[2]&" or "&$aArray[3])
	EndIf
	
	If WinExists($title) Then
		WinActivate($title)
	EndIf
	
	if WinWaitActive($title,$text,$ResponseTime)=0 Then;
		Return GetReturnStr($NoTitle,"*"&$title&"* dialog should be opened firstly")
	EndIf

	ControlSetText($title,$text,"Edit1",$openFileName)
	ControlClick($title,$text,"Button2") ;click the save button
	WinWaitClose($title,"",$ResponseTime)
	If WinExists($title) Then
		Return GetReturnStr($Failed,"*"&$title&"* should not exist after clicking open button")
	Else
		Return GetReturnStr($Success,"open the file successfully!")
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
