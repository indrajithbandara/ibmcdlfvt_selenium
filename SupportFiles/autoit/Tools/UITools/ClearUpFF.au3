Opt("WinTitleMatchMode",2)
$title="Mozilla Firefox";
ClipPut("")
ClipPut(ClearUpFF())
Sleep(2000)
;MsgBox(0,"From clipboard:",ClipGet())

Func ClearUpFF()
	;close all the firefox browser
	While WinExists($title)
		WinClose($title);
	WEnd
	
	;We need to find the path of user.js
	$prefPath=@AppDataDir&'\Mozilla\Firefox\Profiles\'
	$searchPath=$prefPath&"*.*"
	$search = FileFindFirstFile($searchPath)  
	; Check if the search was successful
	If $search = -1 Then
		Return GetReturnStr(0, "Successful")
	EndIf
	;search folder ********.default folder
	While 1
		$foderName = FileFindNextFile($search) 
		If StringRegExp($foderName,".{8}.default$",0) Then
			ExitLoop;
		EndIf
		If @error Then ExitLoop
	WEnd
	; Close the search handle
	FileClose($search)
	If @error Then
		Return GetReturnStr(0, "Successful")
	EndIf
	
	;set preferred language of FF
	$userFile=$prefPath&$foderName&'\user.js'
	Dim $deleted=True
	If FileExists($userFile) Then
		$deleted=FileDelete($userFile)
	EndIf
	;return result
	If $deleted Then
		Return GetReturnStr(0,"Successful")
	Else
		Return GetReturnStr(-1, "Please delete file: "&$userFile&" manually!")
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
	Return $returnCode&Chr(9)&ToFormatedStr($returnStr)
EndFunc
