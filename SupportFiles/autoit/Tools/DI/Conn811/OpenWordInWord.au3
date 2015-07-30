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
ClipPut(OpenWord($CmdLine[1],$CmdLine[2]))

Sleep(2000)
;MsgBox(0,"From clipboard:",ClipGet())


;-------------------------------------------------------------------------------
;tihs method is used to open a file in Word from the specified directory path
;@$parentFolderTitle: the title of the folder which has been open to place the new folder, that's to say, call openfolder() firstly
;@$wordName: the name of the new folder waiting to be created, 
;@$description: the description text of the folder created
;@return: a string indicating the operation result: "0:{$wordName}" or "-1:reason"
;-----------Notes----------
;No matter whether the folder is created successfully, this method will close the $parentFolderTitle
;-------------------------------------------------------------------------------
Func OpenWord($parentFolderTitle,$wordName)
	If WinExists($MicrosoftWord_EN) Then
		WinSetState($MicrosoftWord_EN,"",@SW_MAXIMIZE)
		WinActivate($MicrosoftWord_EN)
	EndIf
	;MsgBox (0,"Ready", "Click Menu")
	sleep(1000)
	;let's get the quickr Open from Place dialog up as simply as possible
	;a gotcha, the add-ons menu changed in office 2007
	If FileExists("c:\Program Files\microsoft office\office12") Or FileExists("d:\Program Files\microsoft office\office12") Then
		;MsgBox (0,"Office Check", "Office 2007 is installed")
		Send("{ALT}{LEFT 3}{DOWN}{RIGHT 1}{ENTER}o")
	Else
		;MsgBox (0,"Office Check", "An earlier than Office 2007 version is installed")
		Send("{ALT}{LEFT 2}{DOWN}o")
	EndIf
	;the strings don't work to activate menu because its not standard
	Sleep(1000)
	Dim $handle,$listView,$folderIndex
	; might be multiple directories we have to go through
	$handle = WinGetHandle($openFromPlace) ;
	$listView = ControlGetHandle($openFromPlace,"","SysListView321")
	;new for 811 1st need to click team
	$listView = ControlGetHandle($openFromPlace,"","SysListView321")
	$folderIndex=ControlListView($openFromPlace,"","SysListView321","FindItem",$LotusQuickr,0)
	_ListView_ClickItem($listView,$folderIndex, "Left", True,2)	
	Dim $folderArray=StringSplit($parentFolderTitle,"\"),$index
	for $index=1 to $folderArray[0] Step 1
		sleep(1000)
		;MsgBox(0,"hi", $folderArray[$index])
		$handle = WinGetHandle($openFromPlace) ;
		$listView = ControlGetHandle($openFromPlace,"","SysListView321")
		$folderIndex=ControlListView($openFromPlace,"","SysListView321","FindItem",$folderArray[$index],0)
		If $folderIndex = -1 Then
			;close dialog and return failure
			;MsgBox(0,"hi", $folderArray[$index])
			ControlClick($openFromPlace,"","Button3")
			Return GetReturnStr($Failed,"File path was invalid")
		EndIf	
		_ListView_ClickItem($listView,$folderIndex, "Left", True,2)	
		sleep(1000)
	Next
	
	If $index<1 Then return GetReturnStr($Failed,"Folder specified error!")

	Sleep(3000)
	$handle = WinGetHandle($openFromPlace) ;
	$listView = ControlGetHandle($openFromPlace,"","SysListView321")
	$folderIndex=ControlListView($openFromPlace,"","SysListView321","FindItem",$wordName,0)
	;MsgBox(0,"hi", $wordName)
	;MsgBox(0,"hi", $folderIndex)
	sleep(3000)
	_ListView_ClickItem($listView,$folderIndex, "Left", True,1)	
	ControlClick($openFromPlace,"","Button2")
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
