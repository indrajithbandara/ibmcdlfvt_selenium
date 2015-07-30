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
;tihs method is used to Add an Outlook message to a place
;@$parentFolderTitle: the title of the folder which has been open to place the new folder, that's to say, call openfolder() firstly
;@$wordName: the name of the file to title the outlook message
;@return: a string indicating the operation result: "0:{$wordName}" or "-1:reason"
;-------------------------------------------------------------------------------
Func OpenWord($parentFolderTitle,$wordName)
	If WinExists($MicrosoftOutlook_EN) Then
		WinActivate($MicrosoftOutlook_EN)
		WinSetState($MicrosoftOutlook_EN,"",@SW_MAXIMIZE)
	EndIf
	Send("!f{LEFT 2}{ENTER}")
	;the strings don't work to activate menu because its not standard
	Sleep(1000)
	Dim $handle,$listView,$folderIndex
	; might be multiple directories we have to go through
	$handle = WinGetHandle($openFromPlaceOutlook) ;
	$listView = ControlGetHandle($openFromPlaceOutlook,"","SysListView321")
	;new for 811 1st need to click team
	$listView = ControlGetHandle($openFromPlaceOutlook,"","SysListView321")
	$folderIndex=ControlListView($openFromPlaceOutlook,"","SysListView321","FindItem",$LotusQuickr,0)
	_ListView_ClickItem($listView,$folderIndex, "Left", True,2)	
	;MsgBox(0,"1","stop 1")
	Dim $folderArray=StringSplit($parentFolderTitle,"\"),$index
	for $index=1 to $folderArray[0] Step 1
		sleep(1000)
		;MsgBox(0,"hi", $folderArray[$index])
		$handle = WinGetHandle($openFromPlaceOutlook) ;
		$listView = ControlGetHandle($openFromPlaceOutlook,"","SysListView321")
		$folderIndex=ControlListView($openFromPlaceOutlook,"","SysListView321","FindItem",$folderArray[$index],0)
		If $folderIndex = -1 Then
			;close dialog and return failure
			;MsgBox(0,"hi", $folderArray[$index])
			ControlClick($openFromPlaceOutlook,"","Button3")
			Return GetReturnStr($Failed,"File path was invalid")
		EndIf	
		_ListView_ClickItem($listView,$folderIndex, "Left", True,2)	
		sleep(1000)
	Next
	
	If $index<1 Then return GetReturnStr($Failed,"Folder specified error!")
	;have to enter file name in text box in this case
	ControlSetText($openFromPlaceOutlook,"","Edit1",$wordName)
	ControlClick($openFromPlaceOutlook,"","Button2")
	Return GetReturnStr($Success,"Successful")

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
