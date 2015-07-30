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
ClipPut(CheckIn($CmdLine[1]))
Sleep(2000)
;MsgBox(0,'Return:',ClipGet())

;-------------------------------------------------------------------------------
;tihs method is used to check in the given file( it's not right for folder)
;@$parentFolderTitle: the title of the file's parent folder, that's to say, you need to open the folder (call OpenFolder())firstly which the file is located!
;@$fileName : the file that need to copy link
;@return: a string indicating the result of this operation: "$Success:{link}" or "$Failed:Null"
;-------------------------------------------------------------------------------
Func CheckIn($fileName)
	If WinExists($fileName) Then
		WinSetState($fileName,"",@SW_MAXIMIZE)
		WinActivate($fileName)
	EndIf
	dim $CheckOutMess
	WinActivate($fileName)
	Dim $ctrl = ControlGetHandle($fileName,"","[CLASS:MsoCommandBar; TEXT:Lotus Tools]")
	Dim $tRect = _API_GetWindowRect($ctrl)
	;Dim $iX=DllStructGetData($tRect,"Right"),$iY=DllStructGetData($tRect,"Bottom")
	Dim $iX=DllStructGetData($tRect,"Left"),$iY=DllStructGetData($tRect,"Top")
	;MouseMove($iX-110,$iY-10)
	MouseMove($iX+200,$iY+10)
	MouseClick("Left")
	;this only seems to work for check in
	;ControlClick($fileName,"","Lotus Tools")
	;1st we get asked if we want to work with this doc or download a fresh one, choose this one
	If WinWaitActive($LOTUSQUICKR,$preCheckOut_EN,$ResponseTime/20) Then
		ControlClick($LOTUSQUICKR,$preCheckOut_EN,"Button1")
	EndIf
	
	sleep(3000)
	
	If WinWaitActive($LOTUSQUICKR,"",$ResponseTime/20) Then
		$CheckOutMess=WinGetText($LOTUSQUICKR,"")
		ControlClick($LOTUSQUICKR,"","Button1")
	EndIf
	;MsgBox(0,"hi",$CheckOutMess)	
	
	If StringInStr($CheckOutMess,$CheckOutGood_EN) > 0 Then
		Return GetReturnStr($Success,'Successful')
	Else
		Return GetReturnStr($Failed,$CheckOutMess)
	EndIf
	
EndFunc

;-------------------------------------------------------------------------------
;tihs method is used to format the given string,used for better communication with java process
;@$str: the string will be formatted.':' will be replaced by 'xy', and 'x' will be replaced by 'xz'
;@return: a formated string for java process to resolve to get its content
;-------------------------------------------------------------------------------
Func ToFormatedStr($str)
	;Dim $formatedStr=StringReplace(StringReplace($str,'x','xz'),':','xy')
	Return $str; $formatedStr
EndFunc

Func GetReturnStr($returnCode,$returnStr)
	Return $returnCode&$Separator&ToFormatedStr($returnStr)
EndFunc