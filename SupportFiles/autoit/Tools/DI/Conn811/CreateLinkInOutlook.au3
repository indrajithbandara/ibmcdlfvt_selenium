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
ClipPut(CreateLink($CmdLine[1],$CmdLine[2],$CmdLine[3]))

Sleep(2000)
;MsgBox(0,"From clipboard:",ClipGet())


;-------------------------------------------------------------------------------
;tihs method is used to Create a link in an Outlook Mail
;@$parentFolderTitle: the title of the folder which has been open to place the new folder, that's to say, call openfolder() firstly
;@$wordName: the name of the new folder waiting to be created, 
;@$description: the description text of the folder created
;@return: a string indicating the operation result: "0:{$wordName}" or "-1:reason"
;-----------Notes----------
;No matter whether the folder is created successfully, this method will close the $parentFolderTitle
;-------------------------------------------------------------------------------
Func CreateLink($parentFolderTitle,$docName, $TabsToIcon)
	If WinExists("Message") Then
		WinSetState("Message","",@SW_MAXIMIZE)
		WinActivate("Message")
	EndIf
	;MsgBox(0,"hi", "bringing up the menu")
	sleep(1000)
	;let's get the quickr insert link dialog open
	Send("{ALT}")
	Send("{TAB " + $TabstoIcon + "}")
	;the strings don't work to activate menu because its not standard
	Sleep(1000)
	Dim $handle,$listView,$folderIndex
	; might be multiple directories we have to go through
	$handle = WinGetHandle($insertQuickrLink) ;
	
	Dim $hTree = ControlGetHandle(WinGetTitle(''), "", "SysTreeView321")
	Dim $hNode = _TreeView_FindNode($hTree, $LOTUSQUICKR);
	If $hNode = 0 Then 
		WinClose($insertQuickrLink)
		Return GetReturnStr($Failed,"lotus quickr doesn't exist!")
	EndIf
	;don't need to click this as it should already be xpanded
	;MsgBox(0,"hi", "clicking team places")
	If Not _TreeView_GetExpanded($hTree, $hNode) Then
		_TreeView_Click($hTree, $hNode,"Left",True,2)
	EndIf
	
	Dim $folderArray=StringSplit($parentFolderTitle,"\"),$index
	for $index=1 to $folderArray[0] Step 1
		sleep(1000)
		;MsgBox(0,"hi", $folderArray[$index])
		Dim $hNode = _TreeView_FindNode($hTree, $folderArray[$index]);
		If $hNode = 0 Then 
			WinClose($insertQuickrLink)
			Return GetReturnStr($Failed,"path doesn't exist!")
		EndIf
		_TreeView_Click($hTree, $hNode,"Left",True,2)
		sleep(1000)
	Next
	
	If $index<1 Then return GetReturnStr($Failed,"Folder specified error!")

	Sleep(3000)
	Dim $hNode = _TreeView_FindNode($hTree, $docName);
		if $hNode = 0 then 
		WinClose($insertQuickrLink)
		Return GetReturnStr($Failed,"lotus quickr doc doesn't exist!")
	EndIf
	_TreeView_Click($hTree, $hNode,"Left",True,2)
	sleep(1000)
	ControlClick($insertQuickrLink,"","Button2")
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
