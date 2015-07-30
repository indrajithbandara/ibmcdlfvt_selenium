#include <A3LTreeView.au3>
;#include <GUIListView.au3>
#include "Constants.au3"
;test data1 : Document2.doc "Lotus Quickr\TestCases on 9.125.74.26" False 
;test data2: Document2.doc "Lotus Quickr\Hui Jian's web2.0 Library Î»ÓÚ quickr" False
;test data3: documentWillBeApproved.doc "Lotus Quickr\UAT Document Liujt on 9.125.74.26\Drafts Views\Drafts to Approve" False
;test data4: Hello,DocFile.doc "Lotus Quickr\UAT Document Liujt on 9.125.74.26\Custom" true
Opt("MustDeclareVars", 1)
Opt("WinTitleMatchMode",2)
ClipPut('')
If $CmdLine[0]<2 Then
	ClipPut(GetReturnStr($Failed,"No Enough Parameters"))
	Sleep(2000)
	Exit
EndIf


ClipPut(OpenPlaceFile($CmdLine[1],$CmdLine[2]))
Sleep(2000)
;MsgBox(0,"Return:",ClipGet())
;-------------------------------------------------------------------------------
;tihs method is used to open a document from the specified library path
;@$fileName: the name of the file waiting to be open
;@$filePath: the path which the file is placed
;@return: a string including the result code and appendent infomation, in the format of "code:info". "0:$fileName" for saving successfully, otherwise, the error code and reason will be included
;------------------Noete---------------
; This file must be opened with check-out state!
;-------------------------------------------------------------------------------

Func OpenPlaceFile($fileName,$filePath)
;start the word application
Send("#r")
Sleep(1000)
;ControlSetText("Run","","Edit1","winword")
Send("winword{ENTER}")
Sleep(3000)

If not WinExists($wordTitle) Then
	Return GetReturnStr($Failed,"can't launch word application")
EndIf

WinSetState ($wordTitle, "", @SW_MAXIMIZE)
WinActivate($wordTitle) 
MouseClick("left",200,200)
Send("!H")
Send("{RIGHT}")
Send("O")

if WinWaitActive($openFromPlace,"",$ResponseTime)=0 Then
	WinClose($wordTitle)
	Return GetReturnStr($Failed,"Dialog for adding libraries not appear");$NoAddPlaceAppear
EndIf

 Dim $pathNodes=StringSplit($filePath,"\")
 If StringCompare($pathNodes[1],$LOTUSQUICKR) Then
	 WinClose($openFromPlace)
	 WinClose($wordTitle)
	 ;MsgBox(0,"",$pathNodes[1])
	Return GetReturnStr($Failed,"Root not Lotus quick");$RootLibError
 EndIf

 Dim $size=$pathNodes[0]    ;user may input a path ending with '\', even'\\\'
 While $pathNodes[$size]=""
	 $size=$size-1
 WEnd
 
 
 For $count=2 to $size
	Dim $hTree = ControlGetHandle($openFromPlace, "", "SysTreeView321")
	Dim $hNode = _TreeView_FindNode($hTree, $pathNodes[$count]);
	if $hNode = 0 then 
		Return GetReturnStr($Failed,"Library or folder "&$pathNodes[$count]&" can't existed!");$LibNotExisted
	EndIf
	_TreeView_Click($hTree, $hNode,"Left",True,2) ;
	Sleep(5000)
	#comments-start
	Dim $notLogin=False
	While WinWaitActive($LoginText_EN,"",$ResponseTime/20)
		$notLogin=True
		WinClose($LoginText_EN)
	WEnd
	If $notLogin Then
		Return GetReturnStr($Failed,' user not login server!')
	EndIf
	#comments-end
Next

Dim $listView = ControlGetHandle($openFromPlace, "", "SysListView321")

Dim $fileIndex=ControlListView($openFromPlace,"",$listView,"FindItem",$fileName)
If $fileIndex=-1 Then
	WinClose($openFromPlace)
	WinClose($wordTitle)
	Return GetReturnStr($Failed,"File not existed!");$FileNotExisted
EndIf

ControlListView($openFromPlace,"",$listView,"Select",$fileIndex)
Sleep(2000)
ControlClick($openFromPlace,"","Button2")
;MsgBox(0,"successfull",'')

If WinWaitActive($LOTUSQUICKR,$LocalFileLockedText,$ResponseTime/20) Then
	Dim $text=ControlGetText($LOTUSQUICKR,$LocalFileLockedText,"Static1")
	ControlClick($LOTUSQUICKR,$LocalFileLockedText,"Button1")
	WinClose($wordTitle)
	Return GetReturnStr($Failed,$text); $LocalFileLockedError
EndIf
If WinWaitActive($FileInUse,"",$ResponseTime/20) Then
	Do
		Sleep(2000)
		Send("{ESC}")
	Until WinWaitActive($FileInUse,"",$ResponseTime/20)=0
	If WinWaitActive($LOTUSQUICKR,"",$ResponseTime/20) Then
		WinClose($LOTUSQUICKR)
	EndIf
	Return GetReturnStr($Failed,"Format of the document may be unkown to this application");$FileNotOpen
EndIf
If WinWaitActive($LOTUSQUICKR,"submitted for approval",$ResponseTime/20) Then ;if a dialog indicating the document waiting for approving, just click ok!
	ControlClick($LOTUSQUICKR,'submitted for approval',"Button1")
EndIf

Dim $fileNameAndExt=StringSplit($fileName,'.')
Dim $fileNameWithoutExt=$fileNameAndExt[1]
if WinWaitActive($fileNameWithoutExt,"",$ResponseTime/20)=0 Then
	WinClose($wordTitle)
	Return GetReturnStr($Failed,"Impossible Reason for not opening the file!")
EndIf

Return GetReturnStr($Success,WinGetTitle(''))

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