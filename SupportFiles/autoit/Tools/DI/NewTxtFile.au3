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
if $CmdLine[0]=2 Then
	ClipPut(NewTxtFile($CmdLine[1],$CmdLine[2]))
	Sleep(2000)
	Exit
EndIf
ClipPut(NewTXTFile($CmdLine[1],$CmdLine[2],$CmdLine[3]))
Sleep(2000)
;MsgBox(0,"From clipboard:",ClipGet())


;-------------------------------------------------------------------------------
;tihs method is used to create a txt file in the specified directory path
;$parentFolderTitle: the folder title which has been open to place the new txt file, that's to say, call openfolder() firstly
;$txtName: the name of the new txt file waiting to be created,without extend name ".txt"
;$content: the text of the new txt file 
;return: a string indicating the operation result: "0:Success" or "-1:{reason}"

Func NewTxtFile($parentFolderTitle,$txtName,$content="")
	If WinExists($parentFolderTitle) Then
		WinActivate($parentFolderTitle)
		WinSetState($parentFolderTitle,"",@SW_MAXIMIZE)
	EndIf
	
	if WinWaitActive($parentFolderTitle,"",$ResponseTime)=0 Then
		Return GetReturnStr($NoTitle,$parentFolderTitle&" folder should be opened firstly")
	EndIf
	
	WinSetState ($parentFolderTitle, "", @SW_MAXIMIZE)
	Dim $handle = WinGetHandle($parentFolderTitle) ;
	Dim $listView = ControlGetHandle($parentFolderTitle,"","SysListView321")
	dim $tRect = _API_GetWindowRect($listView)
	Dim $iX=DllStructGetData($tRect,"Right"),$iY=DllStructGetData($tRect,"Bottom")
	_Lib_MouseClick("right", $iX -250, $iY -250, True, 1, 0, True)
	Sleep(1000)
	Send("{UP 2}{RIGHT}{Down 1}{ENTER}"); select "From Document Type..."
	Dim $selectDocType="Select Document Type", $selectDocText="Select document type from:"
	If WinWaitActive($selectDocType,$selectDocText,$ResponseTime) = 0 Then
		Return GetReturnStr($Failed,$selectDocType&" dialog for Creating word doesn't appear")
	EndIf
	
	ControlCommand($selectDocType,$selectDocText,"ComboBox2","SelectString", 'All Document Types')
	Sleep(2000)
	
	Dim $listView2 = ControlGetHandle($selectDocType,$selectDocText,"SysListView321")
	Dim $folderIndex=ControlListView($selectDocType,$selectDocText,"SysListView321","FindItem","Default",0)
	
	If $folderIndex=-1 Then
		;winclose($parentFolderTitle)
		Return GetReturnStr($Failed,"The specified file doesn't exist")
	EndIf
	; select "Default" document
	_ListView_ClickItem($listView2,$folderIndex)
	
	ControlClick($selectDocType,$selectDocText,"OK")
	
	if WinWaitActive($NewDocumentTitle,$NewDocmentText,$ResponseTime)=0 Then
		;winclose($parentFolderTitle)
		Return GetReturnStr($Failed,"Dialog for Creating word doesn't appear")
	EndIf
	; fill in word filename
	$txtName=$txtName&".txt"
	ControlSetText($NewDocumentTitle,$NewDocmentText,"Edit1",$txtName)
	ControlSend($NewDocumentTitle,$NewDocmentText,"Button2","{ENTER}");here maybe appear many cases
	Sleep(3000)
	; if exception occurs, capture the reason and close dialog for creating txt
	If WinExists($LOTUSQUICKR,"OK") Then
		Dim $text
		If ControlGetHandle($LOTUSQUICKR,"OK","Static2")<>"" Then
			$text=ControlGetText($LOTUSQUICKR,"OK","Static2")
		Else
			$text=ControlGetText($LOTUSQUICKR,"OK","Static1")
		EndIf
		WinClose($LOTUSQUICKR,"OK")
		;close dialog for creating word to avoid affecting subsequent action
		WinClose($NewDocumentTitle)
		;winclose($parentFolderTitle)
		Return GetReturnStr($Failed,$text)
	Else	;create folder successfully
		If WinWaitActive($txtName,"",$ResponseTime/10)=0 Then
			Return GetReturnStr($Failed,"Create txt file:"&$txtName&" failed!")
		EndIf
		ControlSend($txtName,"","Edit1",$content)
		
		WinClose($txtName)
		;Sleep(1000)
		If WinExists("Notepad","Do you want to save the changes?") Then
			ControlClick("Notepad","Do you want to save the changes?","Button1")
		EndIf
		
		While WinWaitActive($LOTUSQUICKR,"",$ResponseTime/10) 
			ControlClick($LOTUSQUICKR,"","Button1")
		WEnd
		
		If ControlListView($handle,"",$listView,"FindItem",$txtName)<>-1 Then
			Return GetReturnStr($Success,"Successful")
		Else
			Return GetReturnStr($Failed,"Create "&$txtName&" successfully, but can't find it")
		EndIf
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
