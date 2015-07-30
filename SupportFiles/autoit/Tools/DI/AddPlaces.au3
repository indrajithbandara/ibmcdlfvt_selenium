#include <GUIListView.au3>
#include "Constants.au3"

Opt("WinTitleMatchMode",2)
Opt("MustDeclareVars",1)
ClipPut('')
If $CmdLine[0]<6 Then
		ClipPut(GetReturnStr($Failed,"No Enough Parameters"))
		Sleep(2000)
	Exit
EndIf

ClipPut(AddPlaces($CmdLine[1],$CmdLine[2],$CmdLine[3],$CmdLine[4],$CmdLine[5],$CmdLine[6]))
Sleep(2000)
;MsgBox(0,'Return:',ClipGet())

;-------------------------------------------------------------------------------
;tihs method is used to add some libraries to the office
;@$serverURL: the url of pdm server
;@$userName: the user name for login server
;@$userPassword: password for user to login server
;@$rememberPWDOrNot: whether or not remember the input login information
;@$libNameList: the libraries user wants to add to office, in the format of "libName1:libName2:libName3"
;----------notes----------
;After this operation, word window will be closed, no matter whether add place successfully or not!
;-------------------------------------------------------------------------------

Func AddPlaces($wordStartedTitle,$serverURL,$userName,$userPassword,$rememberPWDOrNot,$libNameList)

If not WinExists($wordStartedTitle) Then
	Return GetReturnStr($Failed,"Please start word application firstly");$FileNotOpenYet
EndIf
WinActivate($wordStartedTitle) 
WinSetState ($wordStdTitle, "", @SW_MAXIMIZE)
WinActivate($wordStdTitle) 
MouseClick("left",200,200)
Send("!H")
Send("{RIGHT}")
Send("P")

if WinWaitActive($addPlaceTitle,$addPlaceText,$ResponseTime)=0 Then
	WinClose($wordStdTitle)
	Return GetReturnStr($Failed,"No dialog for adding place appears");$NoAddPlaceAppear
EndIf

ControlSetText($addPlaceTitle,$addPlaceText,"Edit1",$serverURL)
ControlSetText($addPlaceTitle,$addPlaceText,"Edit2",$userName)
ControlSetText($addPlaceTitle,$addPlaceText,"Edit3",$userPassword)
If StringInStr($rememberPWDOrNot,'true') Then
	ControlCommand($addPlaceTitle,$addPlaceText,"Button1","Check")
Else StringInStr($rememberPWDOrNot,'false')
	ControlCommand($addPlaceTitle,$addPlaceText,"Button1","Uncheck")
Else
	WinClose($addPlaceTitle,$addPlaceText)
	Return GetReturnStr($Failed,"The forth parameter is wrong!");$ServerClosedError
EndIf
Sleep(1000); this sentence can't be commented, because it needs to cost some time for the updating controls
ControlClick($addPlaceTitle,$addPlaceText,"Button4")

If WinWaitActive($LOTUSQUICKR,"unknown error",$ResponseTime/10) Then  ;if the server is closed, then there will be a dialog indicating "unknown error"
	Dim $text=ControlGetText($LOTUSQUICKR,"unknown error","Static1")
	WinClose($LOTUSQUICKR,"unknown error")
	Return GetReturnStr($Failed,$text);$ServerClosedError
EndIf

If WinWaitActive($addPlaceTitle,$selectLibrary,$ResponseTime)=0 Then
	WinClose($wordStdTitle)
	Return GetReturnStr($Failed,'No dialog for selectting libraries appears!');$NoSelectLibAppear
EndIf
Dim $libraries=StringSplit($libNameList,":")
Dim $ctrl = ControlGetHandle($addPlaceTitle,"","SysListView321")
;_GUICtrlListViewSetCheckState($ctrl,-1,0) no need to deselect all firstly
Sleep(3000)
For $i=1 To $libraries[0]
	Dim $index=ControlListView($addPlaceTitle,"",$ctrl,"FindItem",$libraries[$i])
	If $index<>-1 Then
		;MsgBox(0,$index,"")
		_GUICtrlListViewSetCheckState($ctrl,$index)
	EndIf
	Sleep(2000)
Next
#comments-start
Dim $checkState=_GUICtrlListViewSetCheckState($ctrl,-1)
If $checkState=$LV_ERR then ;/************************************important****************************/
	MsgBox(0,"Bug","This line can't be commented") ;So strange that I can't understand: if there is not this sentence, the listitems will not be checked!
	Return $SelectLibError ;/*************************************************************************/
EndIf
#comments-end
ControlClick($addPlaceTitle,$selectLibrary,"Button8")
If WinWaitActive($LOTUSQUICKR,"",$ResponseTime/20) Then
	ControlClick($LOTUSQUICKR,"","Button1")
EndIf

WinClose($wordStdTitle)	
Return GetReturnStr($Success,'Successful')
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
