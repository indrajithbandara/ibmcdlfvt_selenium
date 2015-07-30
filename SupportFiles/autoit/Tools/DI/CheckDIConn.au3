#include "Constants.au3"
#include <A3LListView.au3>
#include <A3LTreeView.au3>
#include <A3LMenu.au3>
opt("WinTitleMatchMode",2)
Opt("MustDeclareVars",1)
ClipPut("")
ClipPut(CheckDIExisted())
Sleep(2000)
;MsgBox(0,'return:',ClipGet())


;-------------------------------------------------------------------------------
;tihs method is used to check if the DI connector is installed or not 
;return: a string indicating the operation result, such as "0:pop menu content" or "-1:Failed"
;-------------------------------------------------------------------------------
Func CheckDIExisted()
	Dim $desktopTitle="Program Manager"
	WinActivate($desktopTitle)

	Dim $handle = WinGetHandle($desktopTitle) ;
	Dim $listView = ControlGetHandle($desktopTitle,"","SysListView321")
	ControlListView($handle,"",$listView,"ViewChange","details")
	Dim $folderIndex=ControlListView($desktopTitle,"","SysListView321","FindItem",$LOTUSQUICKR,0)
	ControlListView($handle,"",$listView,"ViewChange","smallicons")
	If $folderIndex=-1 Then
		Return GetReturnStr($Failed,"DI connector hasn't installed")
	Else
		Return GetReturnStr($Success,"DI connector has installed")
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

	