#include "Constants.au3"
#include <A3LListView.au3>
Opt("WinTitleMatchMode",2)
ClipPut("")
If $CmdLine[0]<1 Then 
	ClipPut(GetReturnStr($Failed,"No Enough Parameters"))
	Sleep(2000)
	Exit
EndIf

ClipPut(GetSortTypes($CmdLine[1]))
Sleep(2000)
;MsgBox(0,"From clipboard:",clipget())
Exit

;-------------------------------------------------------------------------------
;tihs method is used to get all the types for sorting in DI(not support common windows explorer)
;$folderParentTitle: context environment that you want to get all the sort types.
 ;-------------------------------------------------------------------------------
Func GetSortTypes($folderParentTitle)
	If WinExists($folderParentTitle) Then
		WinSetState($folderParentTitle,"",@SW_MAXIMIZE)
		WinActivate($folderParentTitle)
	EndIf
	if WinWaitActive($folderParentTitle,"",$ResponseTime/10)=0 Then
		Return GetReturnStr($NoTitle,"Context folder should be opened firstly!")
	EndIf
	
	DIM $header = ControlGetHandle($folderParentTitle,"","SysHeader321")
	if $header = 0 then 
		Return GetReturnStr($Failed,"Can't find any types for sorting!")
	EndIf
	
	Dim $sortCount =_Header_GetItemCount($header),$typeStr=""
	For $i=0 To $sortCount-1
		If $typeStr="" Then
			$typeStr=_Header_GetItemText($header,$i)
		Else
			$typeStr=$typeStr&'\'&_Header_GetItemText($header,$i)
		EndIf
	Next
	Return GetReturnStr($Success,$typeStr)
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
