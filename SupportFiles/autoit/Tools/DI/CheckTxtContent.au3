#include "Constants.au3"
#include <A3LListView.au3>
#include <A3LMenu.au3>

Opt("WinTitleMatchMode",2)
Opt("MustDeclareVars",1)
ClipPut("")
If $CmdLine[0]<2 Then 
	ClipPut(GetReturnStr($Failed,"No Enough Parameters"))
	Sleep(2000)
	Exit
EndIf
ClipPut(CheckTxtContent($CmdLine[1],$CmdLine[2]))
Sleep(2000)
;MsgBox(0,"From clipboard:",ClipGet())


;-------------------------------------------------------------------------------
;tihs method is used to check the content of a opened txt file
;@$parentFolderTitle: the title of the folder which has been open to place the new folder, that's to say, call openfolder() firstly
;$content: the text which will be checked in the .txt file
;@return: a string indicating the operation result: "0:{$txtName}" or "-1:reason"
;-------------------------------------------------------------------------------
Func CheckTxtContent($parentFolderTitle,$content)
	If WinExists($parentFolderTitle) Then
		WinActivate($parentFolderTitle)
		WinSetState($parentFolderTitle,"",@SW_MAXIMIZE)
	EndIf
	
	if WinWaitActive($parentFolderTitle,"",$ResponseTime)=0 Then
		Return GetReturnStr($NoTitle,$parentFolderTitle&" file should be opened firstly")
	EndIf
	
	WinSetState ($parentFolderTitle, "", @SW_MAXIMIZE)
	Dim $text=ControlGetText($parentFolderTitle,"","Edit1")
	If StringInStr($text,$content) Then	
		Return GetReturnStr($Success,"Successful")
	Else
		Return GetReturnStr($Failed,"The txt file can't contain contents: "&$content)
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
