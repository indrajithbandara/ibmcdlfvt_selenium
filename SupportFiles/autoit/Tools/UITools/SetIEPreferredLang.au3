#include <A3LListbox.au3>
Opt("WinTitleMatchMode",2)
$title="- Microsoft Internet Explorer"
$language="Language Preference"
$mainPageTitle="Internet Options"
$ieProcess="iexplore.exe"
$addLanguage="Add Language"
$setLang=$CmdLine[1];zh-cn;ar-MA

ClipPut("")
If $CmdLine[0]<1 Then 
	ClipPut(GetReturnStr(-1,"No Enough Parameters"))
	Sleep(2000)
	;MsgBox(0,"From clipboard:",ClipGet())
	Exit
EndIf
ClipPut(SetIEPerferredLang($CmdLine[1]));zh-cn;ar-MA
Sleep(2000)
;MsgBox(0,"From clipboard:",ClipGet())
Func SetIEPerferredLang($setLang)
	
	#comments-start
	While WinExists($title)
			WinClose($title);
			Sleep(1000)
		WEnd
	While ProcessExists($ieProcess)	
		ProcessClose($ieProcess)
		Sleep(1000)
	WEnd
	#comments-end
	
	if Not WinExists($title) Then
		Run(@ProgramFilesDir&"\Internet Explorer\iexplore.exe")
	Else
		WinActivate($title)
	EndIf
	
	if(WinWaitActive($title,"",60)=0) Then
		Return GetReturnStr(-1, $title&" window can't launch!")
	EndIf
	
	$i=0;
	Do
		WinActivate($title)
		Send("!")
		Sleep(1000)
		Send("!T");Select "Tools"
		sleep(1000)	
		Send("o");Select "Internet Option"
		$i=$i+1;
		if $i>3 Then 
			ExitLoop
		EndIf
	Until WinWaitActive($mainPageTitle,"",3)<>0
		
	if($i>3) Then
		WinClose($title)
		Return GetReturnStr(-1, $mainPageTitle&" window can't pop up!")
	EndIf
	$i=0
	Do 
		WinActivate($mainPageTitle)
		Sleep(1000)
		Send("!L");Select "Language Setting"
		$i=$i+1
		if($i>3) Then 
			ExitLoop
		EndIf
	Until WinWaitActive($language,"",3)<>0
	
	if($i>3) Then
		WinWaitActive($mainPageTitle)
		ControlSend($mainPageTitle,"","Button12","{ENTER}") ;complete the setting, and click the ok button 
		WinWaitActive($title)
		WinClose($title) ;close the IE
		Return GetReturnStr(-1, $language&" window can't pop up!")
	EndIf
	
	$handle = WinGetHandle($language) ;
	$hList = ControlGetHandle($language,"","ListBox1")
	$count=_Listbox_GetCount($hList)-1
	;_Lib_ConsoleWrite("Language Count1="&$count)
	
	_Listbox_SetCurSel($hList,0);
	_Listbox_ClickItem($hList,0);
	for $iI = 0 to $count
		Sleep(100)
		;click "Remove" button
		ControlClick($language,"","Button3") 
	next
	 
	; add given from language list
	;click "Add..." button
	ControlClick($language,"","Button4");
	Sleep(1000)
	If WinWaitActive($addLanguage,"",3)=0 Then
		return GetReturnStr(-1, $addLanguage&" window can't pop up!");
	EndIf
	$foundFlag = 0
	$handle = WinGetHandle($addLanguage) ;
	$hList = ControlGetHandle($addLanguage,"","ListBox1")
	$count=_Listbox_GetCount($hList)-1
	for $iI = 0 to $count
		 $str=_Listbox_GetText($hList, $iI)
		 ;_Lib_ConsoleWrite("Language "&$iI&": "&$str)
		 If StringInStr ($str,"["&$setLang&"]") Then ;if find the given language, then mark flag and $iI is the index
			;_Lib_ConsoleWrite("Match Language "&$iI&": "&$str)
			 $foundFlag = 1
			 ExitLoop
		 EndIf
	 next
	 
	 If $foundFlag < 1 Then ;if the long language name was not found, we will use short name
		 for $iI = 0 to $count
			$str=_Listbox_GetText($hList, $iI)
			;_Lib_ConsoleWrite("Language "&$iI&": "&$str)
			If StringInStr ($str,"["&StringLeft($setLang, 2)&"]") Then ;if find the given language, then select it
				 $foundFlag = 1
				 ;_Lib_ConsoleWrite("Match Language2 "&$iI&": "&$str)
				 ExitLoop
			EndIf
		 Next
	 EndIf
	if $foundFlag =1 Then
		Sleep(1000)
		;_Listbox_SetCurSel($hList,0)
		_Listbox_ClickItem($hList,0)
		for $i=0 to $iI-1
			Send("{DOWN}")
			Sleep(200)
		Next
		;_Lib_ConsoleWrite("Language "&$iI&": "&_Listbox_GetCurSel($hList))
	EndIf
	;click OK to end adding all the condidate lanuages
	ControlClick($addLanguage,"","Button1")
	Sleep(1000)
	WinWaitActive($language)
	;click "OK" button on "Language Preference"
	ControlClick($language,"","Button5")
	;activate "Internet Options" and click OK
	WinWaitActive($mainPageTitle)
	ControlSend($mainPageTitle,"","Button12","{ENTER}") ;complete the setting, and click the ok button 
	;close IE
	WinClose($title) ;close the IE
	if $foundFlag <1 Then
		Return GetReturnStr(-1, $setLang&" can't be found!")
	EndIf
	Return GetReturnStr(0,"Successful")

EndFunc


Func ToFormatedStr($str)
	;Dim $formatedStr=StringReplace(StringReplace($str,'x','xz'),':','xy')
	Return $str;$formatedStr
EndFunc

Func GetReturnStr($returnCode,$returnStr)
	Return $returnCode&Chr(9)&ToFormatedStr($returnStr)
EndFunc