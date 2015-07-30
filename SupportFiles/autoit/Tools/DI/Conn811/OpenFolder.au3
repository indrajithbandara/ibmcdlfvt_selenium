#include "Constants.au3"

Opt("MustDeclareVars", 1)
Opt("WinTitleMatchMode",2)
ClipPut("")
If $CmdLine[0]<1 Then 
	;MsgBox(0,"Info",'Please specify the folder name and path you want to enter in the format of "FolderPath"',3)
	ClipPut(GetReturnStr($Failed,"No Enough Parameters"))
	Sleep(2000)
	Exit
EndIf

ClipPut(OpenFolder($CmdLine[1]))
Sleep(2000)
;MsgBox(0,'return:',ClipGet())

;-------------------------------------------------------------------------------
;tihs method is used to enter an specific folder
;$folderPath: the name of the folder which you want to enter
;return: a string indicating the operation result, such as "0:{$folderPath}" or "-1: some reason"
;-------------------------------------------------------------------------------
Func OpenFolder($folderPath)
	If WinExists($MyDocument_EN) Then
		WinActivate($MyDocument_EN)
	Else
		Run(@WindowsDir&"\explorer.exe")	
	EndIf
	Sleep(2000)
	WinActivate($MyDocument_EN)
	If WinWaitActive($MyDocument_EN,"",$ResponseTime)=0 Then
		Return GetReturnStr($Failed,"can't open windows explorer")
	EndIf
	
	ControlSetText($MyDocument_EN,"","Edit1",$folderPath)
	ControlSend($MyDocument_EN,"","Edit1","{ENTER}")
	Sleep(5000)
	;MsgBox(0,'hi',2)
	If WinExists("Microsoft Internet Explorer","file:") Then
		;MsgBox(0,"Error","Directory can't exist",3)
		WinClose("Microsoft Internet Explorer","file:")
		Return GetReturnStr($Failed,"The specified path doesn't exist")
	EndIf
	
	If WinExists("Live Search") Then ; if you specified an error folder, then 
		WinClose("Live Search")
		Return GetReturnStr($Failed,"Error folder path, please check it!")
	EndIf
	
	If WinExists("Address Bar","Check the spelling") Then ;input "\\\"
		WinClose("Address Bar","Check the spelling")
		Return GetReturnStr($Failed,"Windows can't find specified path.  Check the spelling and try again")
	EndIf
	
	If WinExists($LoginText_EN) Then ;Not login
		While WinWaitActive($LoginText_EN,"",5)
			WinClose($LoginText_EN)
		WEnd
		Return GetReturnStr($Failed,"Please login the library firstly, or add it again after removing it!")
	EndIf
	
	Dim $folderArray=StringSplit($folderPath,"\"),$index
	for $index=$folderArray[0] to 1 Step -1
		If $folderArray[$index]<>'' Then ExitLoop
	Next
	
	If $index<1 Then return GetReturnStr($Failed,"Folder specified error!")
	;if you input "D:\abc\ABC\", and you have "D:\abc\abc\" in windows, then the following code will display "Impossible reason",because window
	;with title "abc", so they dont' match
	#comments-start
	If WinWaitActive($folderArray[$index],"",5)=0 Then 
		Return GetReturnStr($Failed,"Impossible reason for not opening this folder")
	Else
		Return GetReturnStr($Success,WinGetTitle(''))
	EndIf
	#comments-end
	Sleep(3000)
	Dim $popTitle=WinGetTitle('')
	If $index>1 Then
		;MsgBox(0,'hi',$popTitle)
		;MsgBox(0,'hi',$folderArray[$index])
		If StringCompare($popTitle,$folderArray[$index])=0 Then
			;MsgBox(0,'hi',$popTitle)
			;MsgBox(0,'hi',$folderArray[$index])
			Return GetReturnStr($Success,$popTitle)
		Else
			Return GetReturnStr($Failed,"Impossible reason for not opening this folder")
		EndIf
	Else
		Return GetReturnStr($Success,$popTitle)
	EndIf
EndFunc

;-------------------------------------------------------------------------------
;tihs method is used to format the given string,used for better communication with java process
;@$str: the string will be formatted.':' will be replaced by 'xy', and 'x' will be replaced by 'xz'
;@return: a formated string for java process to resolve to get its content
;-------------------------------------------------------------------------------
Func ToFormatedStr($str)
	;Dim $formatedStr=StringReplace(StringReplace($str,'x','xz'),$Separator,'xy')
	Return $str;$formatedStr
EndFunc

Func GetReturnStr($returnCode,$returnStr)
	Return $returnCode&$Separator&ToFormatedStr($returnStr)
EndFunc
