#cs ----------------------------------------------------------------------------

 AutoIt Version: 3.2.4.9
 Author:         myName

 Script Function:
	Template AutoIt script.

#ce ----------------------------------------------------------------------------

; Script Start - Add your code below here
#include "Constants.au3"

#include <A3LTreeView.au3>
#include <A3LMenu.au3>

Opt("WinTitleMatchMode",2)

If $CmdLine[0]<1  then
	ConsoleWrite(GetReturnStr($Failed,"No Enough Parameters"))
	Sleep(2000)
	Exit
EndIf


ConsoleWrite(displayPropertyPanel($CmdLine[1]))


Func displayPropertyPanel($libName)
	if WinExists($LOTUSQUICKR) Then
		WinActivate($LOTUSQUICKR)
	Else
		Return $result
	EndIf
	
	Dim $hTree = ControlGetHandle(WinGetTitle(''), "", "SysTreeView321")
	
	Dim $hNode = _TreeView_FindNode($hTree, $LOTUSQUICKR);
	if $hNode = 0 then 
	;WinClose($parentFolderTitle)
	Return GetReturnStr($Failed,"LOtus Quickr doesn't exit")
	EndIf
	
	_TreeView_Click($hTree, $hNode,"Left",True)
	
	Dim $hItem = _TreeView_FindNode($hTree, $libName);
	if $hItem = 0 then 
	;WinClose($parentFolderTitle)
	Return GetReturnStr($Failed,"lib doesn't exit")
	EndIf
	
	_TreeView_Click($hTree, $hItem,"Right",True)
		
	Sleep(1000)
	_Lib_PopupScan() ; Auto3Lib internal routine to find popup menus
	Dim $iPopupMenu = _Lib_PopupGetHwnd()
	Dim $iPopupItemCount = _Menu_GetItemCount($iPopupMenu)
	If $iPopupItemCount <= 0 Then 
		;WinClose($LOTUSQUICKR)
		Return GetReturnStr($Failed,"Can't find context menu!"); Ignore popups with no menu items
	EndIf
	
	Dim $itemIndex=_Menu_FindItem($iPopupMenu,"Properties") 
	if $itemIndex= -1 Then
		;WinClose($LOTUSQUICKR)
		Return GetReturnStr($Failed,"can't find 'Property' menu item in the context menu!")
	EndIf
	
	If not _Menu_GetItemEnabled($iPopupMenu,$itemIndex) then 
	;WinClose($LOTUSQUICKR)
	Return GetReturnStr($Failed,"'Property' menu item in the context menu is disabled!")
	EndIf

	_Menu_ClickPopup($itemIndex,True); click 'Property' menuitem
	
	Sleep(2000)
	ControlClick("BVT","","Button1")
	
	WinClose($LOTUSQUICKR)
	
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
