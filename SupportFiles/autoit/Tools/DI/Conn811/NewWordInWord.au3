#include "Constants.au3"
#include <A3LListView.au3>
#include <A3LTreeView.au3>
#include <A3LMenu.au3>

Opt("WinTitleMatchMode",2)
Opt("MustDeclareVars",1)
ClipPut("")
If $CmdLine[0]<3 Then 
	ClipPut(GetReturnStr($Failed,"No Enough Parameters"))
	Sleep(2000)
	Exit
EndIf	
ClipPut(NewWord($CmdLine[1],$CmdLine[2],$CmdLine[3]))

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
Func NewWord($placename,$parentFolderTitle,$wordName)
	If WinExists($MicrosoftWord_EN) Then
		WinSetState($MicrosoftWord_EN,"",@SW_MAXIMIZE)
		WinActivate($MicrosoftWord_EN)
	EndIf
	
	;let's get the quickr Create from Document Type dialog up as simply as possible
	;a gotcha, the add-ons menu changed in office 2007
	If FileExists("c:\Program Files\microsoft office\office12") Or FileExists("d:\Program Files\microsoft office\office12") Then
		;MsgBox (0,"Office Check", "Office 2007 is installed")
		Send("!h{LEFT 3}{DOWN}{RIGHT}{ENTER}t")
	Else
		;MsgBox (0,"Office Check", "An earlier than Office 2007 version is installed")
		Send("{ALT}{LEFT 2}{DOWN}t")
	EndIf
	;the strings don't work to activate menu because its not standard
	Sleep(1000)

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

	if WinWaitActive($addToPlace,"",$ResponseTime)=0 Then
		;winclose($parentFolderTitle)
		Return GetReturnStr($Failed,"Dialog for Add to Place doesn't appear")
	EndIf
	; fill in word filename
	$wordName=$wordName
	;before we get here we need to get correct path
	; select correct folder
	sleep(1000)
	Dim $listView = ControlGetHandle($addToPlace,"","SysListView321")
	$folderIndex=ControlListView($addToPlace,"","SysListView321","FindItem",$parentFolderTitle,0)
	;MsgBox(0,"hi", $folderIndex)
	If $folderIndex=-1 Then
		Return GetReturnStr($Failed,"The specified file doesn't exist")
	EndIf
	_ListView_ClickItem($listView,$folderIndex,"Left",True,2)
	Sleep(1000)
	ControlSetText($addToPlace,"","Edit1",$wordName)
	ControlClick($addToPlace,"","Button2");here maybe appear many cases
	Sleep(3000)
	;sometimes servers are slow so lets leave wait to max time
	If WinWaitActive($wordName,"",$ResponseTime)=0 Then
			Return GetReturnStr($Failed,"New word application haven't appeared!")
		Else
			Return GetReturnStr($Success,"Successful")
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
