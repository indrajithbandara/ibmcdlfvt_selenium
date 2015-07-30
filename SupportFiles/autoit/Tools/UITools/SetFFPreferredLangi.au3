Opt("WinTitleMatchMode",2)
$title="Mozilla Firefox";"¿Õ°×Ò³";
ClipPut("")
If $CmdLine[0]<1 Then 
	ClipPut(GetReturnStr(-1,"No Enough Parameters"))
	Sleep(2000)
	;MsgBox(0,"From clipboard:",ClipGet())
	Exit
EndIf
ClipPut(SetFFPerferredLang($CmdLine[1]));zh-cn;ar-MA
Sleep(2000)
;MsgBox(0,"From clipboard:",ClipGet())

Func SetFFPerferredLang($setLang)
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
		Return GetReturnStr(-1, $setLang&" language can't be set successfully for wrong path!")
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
		Return GetReturnStr(-1, $setLang&" language can't be set successfully for wrong path!")
	EndIf
	
	;set preferred language of FF
	$userFile=$prefPath&$foderName&'\user.js'
	$userFileHandle = FileOpen($userFile, 2)
	; Check if file is created successfully
	If $userFileHandle = -1 Then
		Return GetReturnStr(-1, $setLang&" language can't be found!")
	EndIf

	$preferLangStr='user_pref("intl.accept_languages", '&'"'&$setLang&'");'&@LF
	$Write=FileWriteLine($userFileHandle,$preferLangStr)
	FileClose($userFileHandle)
	;return result
	If $Write Then
		Return GetReturnStr(0,"Successful")
	Else
		Return GetReturnStr(-1, $setLang&" language can't be found!")
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
