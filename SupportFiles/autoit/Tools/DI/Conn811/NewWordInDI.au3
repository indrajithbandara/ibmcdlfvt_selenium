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
ElseIf $CmdLine[0]<3 Then 
	ClipPut(NewWord($CmdLine[1],$CmdLine[2], "true"))
Else
	ClipPut(NewWord($CmdLine[1],$CmdLine[2], $CmdLine[3]))
EndIf

Sleep(2000)
;MsgBox(0,"From clipboard:",ClipGet())


;-------------------------------------------------------------------------------
;tihs method is used to create a folder in the specified directory path
;@$parentFolderTitle: the title of the folder which has been open to place the new folder, that's to say, call openfolder() firstly
;@$wordName: the name of the new folder waiting to be created, no need to includes ".doc"
;@$description: the description text of the folder created
;@return: a string indicating the operation result: "0:{$wordName}" or "-1:reason"
;-----------Notes----------
;No matter whether the folder is created successfully, this method will close the $parentFolderTitle
;-------------------------------------------------------------------------------
Func NewWord($parentFolderTitle,$wordName, $CheckIn)
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
	Dim $NewDocumentTitle="New"
	Dim $NewDocumentText="Create a new document in"
	if WinWaitActive($NewDocumentTitle,$NewDocumentText,$ResponseTime)=0 Then
		;winclose($parentFolderTitle)
		Return GetReturnStr($Failed,"Dialog for Creating word doesn't appear")
	EndIf
	; fill in word filename
	$wordName=$wordName&".doc"
	ControlSetText($NewDocumentTitle,$NewDocumentText,"Edit1",$wordName)
	ControlSend($NewDocumentTitle,$NewDocumentText,"Button2","{ENTER}");here maybe appear many cases
	Sleep(3000)
	; if exception occurs, capture the reason and close dialog for creating word
	If WinExists($LOTUSQUICKR,"OK") Then
		Dim $text
		If WinExists($LOTUSQUICKR,$FolderExistTitle_EN) Then
			$text=ControlGetText($LOTUSQUICKR,"OK","Static2")
		Else
			$text=ControlGetText($LOTUSQUICKR,"OK","Static1")
		EndIf
		WinClose($LOTUSQUICKR,"OK")
		;close dialog for creating word to avoid affecting subsequent action
		WinClose($NewDocumentTitle)
		Return GetReturnStr($Failed,$text)
	Else	;create folder successfully
		;sometimes servers are slow so lets leave wait to max time
		If WinWaitActive($wordName,"",$ResponseTime)=0 Then
			MsgBox(0,"Oh",$wordName,1)
			Return GetReturnStr($Failed,"New word application haven't appeared!")
		EndIf
		WinClose($wordName)
		
		While WinWaitActive($LOTUSQUICKROld,"",$ResponseTime/10) 
			if $CheckIn = "true" Then
				ControlClick($LOTUSQUICKROld,"","Button1") ;check it In
			else
				ControlClick($LOTUSQUICKROld,"","Button2") ;dont check it In
			EndIf	
		WEnd
		
		;WinSetState($parentFolderTitle,"",@SW_MAXIMIZE)
		If ControlListView($handle,"",$listView,"FindItem",$wordName)<>-1 Then
			;MsgBox(0,"Oh","create "&$wordName&" folder successfully!")
			;winclose($parentFolderTitle)
			Return GetReturnStr($Success,"Successful")
		Else
			;MsgBox(0,"Oh","create successfully, but can't find it",1)
			;winclose($parentFolderTitle)
			Return GetReturnStr($Failed,"Create word file:"&$wordName&" successfully, but can't find it")
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
