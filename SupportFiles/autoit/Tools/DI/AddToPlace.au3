#include <A3LTreeView.au3>
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
ClipPut(AddToPlace($CmdLine[1],$CmdLine[2],$CmdLine[3],$CmdLine[4]))
Sleep(2000)
;MsgBox(0,"Return:",ClipGet())

;-------------------------------------------------------------------------------
;tihs method is used to add an open document to the specified library path
;@$fileTitle: the window title of the file waiting to be added to
;@$fileNewName: the new name of the file waiting to be added as 
;@$savePath: the path which the file will be placed
;@$overrideOrNot: the switch to control whether replace the already file with same name or not 
;@return: a string including the result code and appendent infomation, in the format of "code:info". "0:Success" for saving successfully, otherwise, the error code and reason will be included
;---Important Notes----
;before call this method, ensure that the file with $fileName should be open from a place
;-------------------------------------------------------------------------------

Func AddToPlace($fileTitle,$fileNewName,$savePath,$overrideOrNot)

If not WinExists($fileTitle) Then
	Return GetReturnStr($Failed,"File not open yet, please open it firstly");$FileNotOpenYet
EndIf
WinActivate($fileTitle) 
WinSetState ($fileTitle, "", @SW_MAXIMIZE)
MouseClick("left",200,200)
Send("!H")
Send("{RIGHT}")
Send("a")

if WinWaitActive($AddToPlace,"",$ResponseTime)=0 Then
	;WinClose($fileName)
	Return GetReturnStr($Failed,"Dialog for adding to library not appear"); $NoAddToPlaceAppear,maybe the file is already from PDM, too
EndIf

 Dim $pathNodes=StringSplit($savePath,"\")
 If StringCompare($pathNodes[1],$LOTUSQUICKR) Then
	 WinClose($AddToPlace)
	 ;WinClose($fileName) ;if user speicify the wrong library, we can give him another chance, so don't need to close the unsaved document
	 ;MsgBox(0,"",$pathNodes[1])
	Return GetReturnStr($Failed,"Root not Lotus quick");$RootLibError
EndIf

 Dim $size=$pathNodes[0]    ;user may input a path ending with '\', even'\\\'
 While $pathNodes[$size]=""
	 $size=$size-1
 WEnd
 
 For $count=2 to $size
	Dim $hTree = ControlGetHandle($AddToPlace, "", "SysTreeView321")
	Dim $hNode = _TreeView_FindNode($hTree, $pathNodes[$count]);
	if $hNode = 0 then 
		WinClose($AddToPlace)
		Return GetReturnStr($Failed,"Library or folder "&$pathNodes[$count]&" can't existed!");$LibNotExisted
	EndIf
	
	_TreeView_Click($hTree, $hNode,"Left",True,1) ;
	Sleep(3000)
	Dim $notLogin=False
	While WinWaitActive($LoginText_EN,"",$ResponseTime/20)
		$notLogin=True
		WinClose($LoginText_EN)
	WEnd
	If $notLogin Then
		Return GetReturnStr($Failed,'user not login server');$NotLoginError
	EndIf
	Sleep(1000)
Next

Dim $listView = ControlGetHandle($AddToPlace, "", "SysListView321")

ControlSetText($AddToPlace,"","Edit1",$fileNewName)
ControlClick($AddToPlace,"","Button2")
Sleep(3000)
If WinWaitActive($LOTUSQUICKR,"already exists",$ResponseTime/10) Then ;the file has existed
	if StringInStr($overrideOrNot,'True') Then ;replace the existed file 
		ControlClick($LOTUSQUICKR,"","Button1")
	ElseIf StringInStr($overrideOrNot,'False') Then ;do not replace the existed file 
		ControlClick($LOTUSQUICKR,"","Button2")
		WinClose($AddToPlace)
		Return GetReturnStr($Success,WinGetTitle(''))
	Else ; parameter is wrong
		Return GetReturnStr($Failed,"This parameter should be true or false");$InputParameterError
	EndIf
	Sleep(1000)
EndIf	
	
; you can't override a file which is in checkout state, so 	return with failure
; if the existed file is in checkin state, then we can replace it 
If WinWaitActive($LOTUSQUICKR,$LockedByOther,$ResponseTime/20) Then ;"Current document is locked by another user and cannot be updated by you."
	Dim $text=ControlGetText($LOTUSQUICKR,$LockedByOther,"Static1")
	ControlClick($LOTUSQUICKR,$LockedByOther,"Button1")
	Return GetReturnStr($Failed,$text);$FailedByLocked
EndIf
; add to place successfully
If WinWaitActive($LOTUSQUICKR,$SaveSuccessText,$ResponseTime/20) Then
	ControlClick($LOTUSQUICKR,$SaveSuccessText,"Button1")
	Return GetReturnStr($Success,WinGetTitle(''))
Else
	Return GetReturnStr($Failed,"Impossible reason for saving failed!")
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
