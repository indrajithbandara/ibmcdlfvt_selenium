#include <A3LListView.au3>
#include <GUIListView.au3>
#include <A3LMenu.au3>
#include <A3LTreeView.au3>
#include "Constants.au3"

Opt("WinTitleMatchMode",2)
Opt("MustDeclareVars",1)
ClipPut("")
If $CmdLine[0]<5 Then
	ClipPut(GetReturnStr($Failed,"No Enough Parameters"))
	Sleep(2000)
	Exit
EndIf
;MsgBox(0,'hello',AddPlacesInDI($CmdLine[1],$CmdLine[2],$CmdLine[3],$CmdLine[4],$CmdLine[5]))
ClipPut(AddPlacesInDI($CmdLine[1],$CmdLine[2],$CmdLine[3],$CmdLine[4],$CmdLine[5]))
Sleep(2000)
;-------------------------------------------------------------------------------
;tihs method is used to add a library in DI, but via context menu of "Lotus Quickr", not via context menu of right blank pane
;@$serverURL: the url of pdm server
;@$userName: the user name for login server
;@$userPassword: password for user to login server
;@$rememberPWDOrNot: whether or not remember the input login information
;@$libNameList: the libraries user wants to add to office, in the format of "libName1:libName2:libName3"
;----------notes----------
;1.Before this operation, please ensure the windows title of "lotus quickr" has been opened!
;2.After this operation, word window will be closed, no matter whether add place successfully or not!
;-------------------------------------------------------------------------------

Func AddPlacesInDI($serverURL,$userName,$userPassword,$rememberPWDOrNot,$library)
	;#comments-start
If not WinExists($LOTUSQUICKR) Then
	Return GetReturnStr($NoTitle,$LOTUSQUICKR&" window should be opened firstly!")
EndIf
WinSetState ($LOTUSQUICKR, "", @SW_MAXIMIZE)
WinActivate($LOTUSQUICKR) 
;#comments-end
Dim $hTree = ControlGetHandle(WinGetTitle(''), "", "SysTreeView321")
;Dim $hTree = ControlGetHandle($LOTUSQUICKR, "", "SysTreeView321")
Dim $hNode = _TreeView_FindNode($hTree, $LOTUSQUICKR);
if $hNode = 0 then 
	;WinClose($parentFolderTitle)
	Return GetReturnStr($Failed,"lotus quickr can't existed!")
EndIf
_TreeView_Click($hTree, $hNode,"Right",True) ;
Sleep(4000)
_Lib_PopupScan() ; Auto3Lib internal routine to find popup menus
Dim $iPopupMenu = _Lib_PopupGetHwnd()
Dim $iPopupItemCount = _Menu_GetItemCount($iPopupMenu)
If $iPopupItemCount <= 0 Then 
	;WinClose($LOTUSQUICKR)
	Return GetReturnStr($Failed,"Can't find context menu!"); Ignore popups with no menu items
EndIf
Dim $itemIndex=_Menu_FindItem($iPopupMenu,"Add Team Places...") 
if $itemIndex= -1 Then
	;WinClose($LOTUSQUICKR)
	Return GetReturnStr($Failed,"can't find 'Add Places' menu item in the context menu!")
EndIf
If not _Menu_GetItemEnabled($iPopupMenu,$itemIndex) then 
	;WinClose($LOTUSQUICKR)
	Return GetReturnStr($Failed,"'Add Places' menu item in the context menu is disabled!")
EndIf
_Menu_ClickPopup($itemIndex,True); click 'Add Places' menuitem

if WinWaitActive($addPlaceTitle,$addPlaceText,$ResponseTime)=0 Then
	;WinClose($LOTUSQUICKR)
	Return GetReturnStr($Failed,"No dialog for adding place appears");$NoAddPlaceAppear
EndIf

ControlSetText($addPlaceTitle,$addPlaceText,"Edit1",$serverURL)
ControlSetText($addPlaceTitle,$addPlaceText,"Edit2",$userName)
ControlSetText($addPlaceTitle,$addPlaceText,"Edit3",$userPassword)
If StringInStr($rememberPWDOrNot,'true') Then
	ControlCommand($addPlaceTitle,$addPlaceText,"Button1","Check")
ElseIf StringInStr($rememberPWDOrNot, 'false') Then
	ControlCommand($addPlaceTitle,$addPlaceText,"Button1","Uncheck")
Else
	WinClose($addPlaceTitle)
	Return GetReturnStr($Failed,"The forth parameter was wrong!");$ServerClosedError
EndIf
Sleep(1000); this sentence can't be commented, because it needs to cost some time for the updating controls
ControlClick($addPlaceTitle,$addPlaceText,"Button3")
#comments-start
If WinWaitActive($LOTUSQUICKR,"unknown error",$ResponseTime/10) Then  ;if the server is closed, then there will be a dialog indicating "unknown error"
	WinClose($LOTUSQUICKR,"unknown error")
	WinClose($addPlaceTitle)
	Return GetReturnStr($Failed,"Connection to server Failed,maybe Server is closed or url is wrong!");$ServerClosedError
EndIf
If WinWaitActive($LOTUSQUICKR,"Authorization failed",$ResponseTime/10) Then  ;if the server is closed, then there will be a dialog indicating "unknown error"
	WinClose($LOTUSQUICKR,"Authorization failed")
	WinClose($addPlaceTitle)
	Return GetReturnStr($Failed,"Connection to server Failed,because user/pwd is wrong!");$ServerClosedError
EndIf
#comments-end
If WinWaitActive($LOTUSQUICKR,"OK",$ResponseTime/10) Then  ;if the server is closed, the rul, user/pwd is wrong, then there will be a dialog including "OK", so close it, and return 
	Dim $text=ControlGetText($LOTUSQUICKR,"OK","Static1")
	WinClose($LOTUSQUICKR,"OK") ; here "OK" must be capital, otherwise it can't work! maybe we can use opt() method to set this option
	WinClose($addPlaceTitle)
	Return GetReturnStr($Failed,$text);$ServerClosedError
EndIf
If WinWaitActive($addPlaceTitle,$selectLibrary,$ResponseTime)=0 Then
	;WinClose($LOTUSQUICKR)
	Return GetReturnStr($Failed,'No dialog for selectting libraries appears!');$NoSelectLibAppear
EndIf
ControlSetText($addPlaceTitle,"","Edit4",$library)
;_GUICtrlListViewSetCheckState($ctrl,-1,0) no need to deselect all firstly
Dim $ctrl = ControlGetHandle($addPlaceTitle,"","SysListView321")
Dim $index=ControlListView($addPlaceTitle,"",$ctrl,"FindItem",$library)
_GUICtrlListViewSetCheckState($ctrl,$index)
sleep(1000)
#comments-start
Dim $checkState=_GUICtrlListViewSetCheckState($ctrl,-1)
If $checkState=$LV_ERR then ;/************************************important****************************/
	MsgBox(0,"Bug","This line can't be commented") ;So strange that I can't understand: if there is not this sentence, the listitems will not be checked!
	Return $SelectLibError ;/*************************************************************************/
EndIf
#comments-end
Sleep(2000)
ControlClick($addPlaceTitle,$selectLibrary,"Button7")
If WinWaitActive($LOTUSQUICKR,"",$ResponseTime/20) Then  ;tell you whether or not you want to delete the libraries that unselected!
	ControlClick($LOTUSQUICKR,"","Button1")
EndIf
If $index=-1 Then
	Return GetReturnStr($Failed,"Specified libraries don't exist")
Else
	Return GetReturnStr($Success,'Successful')
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
