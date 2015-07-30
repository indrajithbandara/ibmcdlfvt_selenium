#include <A3LTreeView.au3>
;#include <GUIListView.au3>
#include "Constants.au3"
;$addPlaceTitle="Add Places to Lotus Quickr connectors"
;test data :Document2.doc "Lotus Quickr\TestCases on 9.125.74.26" True 
;test data2: asdfasdkfad asdfasdkfad.doc "Lotus Quickr\LibReader1 on lwptszpro01" True
Opt("MustDeclareVars", 1)
Opt("WinTitleMatchMode",2)
ClipPut('')
If $CmdLine[0]<4 Then
	ClipPut(GetReturnStr($Failed,"No Enough Parameters"))
	Sleep(2000)
	Exit
EndIf


ClipPut(SaveAsToPlace($CmdLine[1],$CmdLine[2],$CmdLine[3],$CmdLine[4]))
Sleep(2000)
;MsgBox(0,"Return:",ClipGet())
;-------------------------------------------------------------------------------
;tihs method is used to save an open document to the specified library path
;@$fileTitle: the window title of the file waiting to be saved as
;@$fileNewName: the new name of the file waiting to be saved as 
;@$savePath: the path which the file will be placed
;@$overrideOrNot: the switch to control whether replace the already file with same name or not 
;@return: a string including the result code and appendent infomation, in the format of "code:info". "0:Success" for saving successfully, otherwise, the error code and reason will be included
;---Important Notes----
;before call this method, ensure that the file with $fileName should be open from a place
;-------------------------------------------------------------------------------

Func SaveAsToPlace($fileTitle,$fileNewName,$savePath,$overrideOrNot)

If not WinExists($fileTitle) Then
	Return GetReturnStr($Failed,"File not open yet, please open it firstly")
EndIf
WinActivate($fileTitle) 
WinSetState ($fileTitle, "", @SW_MAXIMIZE)
MouseClick("left",200,200)
Send("!H")
Send("{RIGHT}")
Send("S")

if WinWaitActive($SaveAsToPlace,"",$ResponseTime)=0 Then
	;WinClose($fileName)
	Return GetReturnStr($Failed,"Dialog for saving to library not appear"); maybe you open a file not from the PDM, it's just a local file
EndIf

 Dim $pathNodes=StringSplit($savePath,"\")
 If StringCompare($pathNodes[1],$LOTUSQUICKR) Then
	 WinClose($SaveAsToPlace)
	 ;WinClose($fileName) ;if user speicify the wrong library, we can give him another chance, so don't need to close the unsaved document
	 ;MsgBox(0,"",$pathNodes[1])
	Return GetReturnStr($Failed,"Root not Lotus quick")
EndIf

 Dim $size=$pathNodes[0]    ;user may input a path ending with '\', even'\\\'
 While $pathNodes[$size]=""
	 $size=$size-1
 WEnd
 
 For $count=2 to $size
	Dim $hTree = ControlGetHandle($SaveAsToPlace, "", "SysTreeView321")
	Dim $hNode = _TreeView_FindNode($hTree, $pathNodes[$count]);
	if $hNode = 0 then 
		WinClose($saveAsToPlace)
		Return GetReturnStr($Failed,"Library or folder "&$pathNodes[$count]&" can't existed!"
	EndIf
	
	_TreeView_Click($hTree, $hNode,"Left",True,2) ;
	
	Dim $loginText=""
	While WinWaitActive($LoginText_EN,"",$ResponseTime/20)
		$loginText=ControlGetText($LoginText_EN,"","Static1")
		WinClose($LoginText_EN)
	WEnd
	If $loginText<>"" Then
		Return GetReturnStr($Failed,$loginText)
	EndIf
	Sleep(5000)
Next

Dim $listView = ControlGetHandle($SaveAsToPlace, "", "SysListView321")

ControlSetText($SaveAsToPlace,"","Edit1",$fileNewName)
ControlClick($SaveAsToPlace,"","Button2")
Sleep(3000)
If WinWaitActive($LOTUSQUICKR,"already exists",$ResponseTime/10) Then ;the file has existed
	if StringInStr($overrideOrNot,'True') Then
		ControlClick($LOTUSQUICKR,"","Button1")
	ElseIf StringInStr($overrideOrNot,'False') Then
		ControlClick($LOTUSQUICKR,"","Button2")
		WinClose($SaveAsToPlace)
		Return GetReturnStr($Success,WinGetTitle(''))
	Else
		WinClose($LOTUSQUICKR,"already exists")
		Return GetReturnStr($Failed,"This parameter should be true or false")
	EndIf
	Sleep(1000)
EndIf	
;"save as to place" operation can replace files in checkout and checkin state,but can't replace files waiting to be approved	
If WinWaitActive($LOTUSQUICKR,$SubmittedWarning,$ResponseTime/20) Then ;"Current document is submitted and cannot be updated."
	ControlClick($LOTUSQUICKR,$SubmittedWarning,"Button1")
	Return GetReturnStr($Failed,WinGetTitle(''))
EndIf

If WinWaitActive($LOTUSQUICKR,$SaveSuccessText,$ResponseTime/20) Then
	ControlClick($LOTUSQUICKR,$SaveSuccessText,"Button1")
	Return GetReturnStr($Success,WinGetTitle(''))
Else
	Return GetReturnStr($Failed,"Impossible reason for saving failed")
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
