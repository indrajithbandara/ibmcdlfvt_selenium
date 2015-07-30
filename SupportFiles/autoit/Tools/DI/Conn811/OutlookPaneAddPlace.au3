#include "Constants.au3"
#include <GUIListView.au3>
#include <A3LListView.au3>
#include <A3LTreeView.au3>
#include <A3LMenu.au3>

Opt("WinTitleMatchMode",2)
Opt("MustDeclareVars",1)
ClipPut("")
If $CmdLine[0]<5 Then 
	ClipPut(GetReturnStr($Failed,"No Enough Parameters"))
	Sleep(2000)
	Exit
EndIf	
ClipPut(AddPlace($CmdLine[1],$CmdLine[2],$CmdLine[3],$CmdLine[4],$CmdLine[5]))

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
Func AddPlace($serverURL,$userName,$userPassword,$rememberPWDOrNot,$libName)
	If WinExists($MicrosoftOutlook_EN) Then
		WinActivate($MicrosoftOutlook_EN)
		WinSetState($MicrosoftOutlook_EN,"",@SW_MAXIMIZE)
	EndIf
	Dim $handle,$listView,$folderIndex
	Dim $hTree = ControlGetHandle($MicrosoftOutlook_EN, "", "SysTreeView321")
	Dim $hNode = _TreeView_FindNode($hTree, $LOTUSQUICKR);
	If $hNode = 0 Then 
		Return GetReturnStr($Failed,"lotus quickr Team Place doesn't exist!")
	EndIf
	;MsgBox(0,"Test",$hNode)
	;right click team place after left clicking it
	_TreeView_Click($hTree, $hNode,"Left",True,1)
	sleep(1000)
	_TreeView_Click($hTree, $hNode,"Right",True,1)
	sleep(1000)
	Send("{DOWN 3}{ENTER}")
	;;now add a place
	if WinWaitActive($addPlaceTitle,$addPlaceText,$ResponseTime)=0 Then
		Return GetReturnStr($Failed,"No dialog for adding place appears");$NoAddPlaceAppear
	EndIf

	ControlSetText($addPlaceTitle,$addPlaceText,"Edit1",$serverURL)
	ControlSetText($addPlaceTitle,$addPlaceText,"Edit2",$userName)
	ControlSetText($addPlaceTitle,$addPlaceText,"Edit3",$userPassword)
	If StringInStr($rememberPWDOrNot,'true') Then
		ControlCommand($addPlaceTitle,$addPlaceText,"Button1","Check")
	Else 
		StringInStr($rememberPWDOrNot,'false')
	EndIf
	ControlCommand($addPlaceTitle,$addPlaceText,"Button1","Uncheck")

	Sleep(1000); this sentence can't be commented, because it needs to cost some time for the updating controls
	ControlClick($addPlaceTitle,$addPlaceText,"Button4")

	If WinWaitActive($LOTUSQUICKROld,"unknown error",$ResponseTime/10) Then  ;if the server is closed, then there will be a dialog indicating "unknown error"
		Dim $text=ControlGetText($LOTUSQUICKROld,"unknown error","Static1")
		WinClose($LOTUSQUICKROld,"unknown error")
		Return GetReturnStr($Failed,$text);$ServerClosedError
	EndIf

	If WinWaitActive($addPlaceTitle,$selectLibrary,$ResponseTime)=0 Then
		Return GetReturnStr($Failed,'No dialog for selectting libraries appears!');$NoSelectLibAppear
	EndIf
	;list might be long, so lets narrow it down by entering name in searchbox

	Sleep(3000)
	ControlSetText($addPlaceTitle,"","Edit4",$libName)
	Sleep(3000)
	Dim $ctrl = ControlGetHandle($addPlaceTitle,"","SysListView321")
	Dim $index=ControlListView($addPlaceTitle,"",$ctrl,"FindItem",$libName)
	_GUICtrlListViewSetCheckState($ctrl,$index)
	Sleep(2000)

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
