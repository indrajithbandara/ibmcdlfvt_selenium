#cs ----------------------------------------------------------------------------

 AutoIt Version: 3.2.4.9
 Author:         myName

 Script Function:
	Template AutoIt script.

#ce ----------------------------------------------------------------------------

; Script Start - Add your code below here

#include "Constants.au3"
#include <A3LListView.au3>
#include <A3LMenu.au3>
#include <Date.au3>


;Dim $folderParentTitle = "BVT_1208832522218 on lwptsthink49"
;Dim $newFileName = "BVT_Test_NewFile3.doc"
;Dim $docTypeName = "BVT_DocType1"
;Dim $folderParentTitle
;Dim $newFileName
;Dim $docTypeName

If $CmdLine[0]<3 Then 
	ConsoleWrite("No Enough Parameters")
	Sleep(2000)
	Exit
EndIf


ConsoleWrite(createDocumentByDocType($CmdLine[1],$CmdLine[2],$CmdLine[3]))

Func createDocumentByDocType($folderParentTitle,$newFileName,$docTypeName)
	
	ConsoleWrite($folderParentTitle)
	ConsoleWrite("--")
	ConsoleWrite($newFileName)
	ConsoleWrite("--")
	ConsoleWrite($newFileName)
	ConsoleWrite("--")
	
	
	If WinExists($folderParentTitle) Then
		WinSetState($folderParentTitle,"",@SW_MAXIMIZE)
		WinActivate($folderParentTitle)
	EndIf

	Dim $listView = ControlGetHandle($folderParentTitle,"","SysListView321")
	dim $tRect = _API_GetWindowRect($listView)
	Dim $iX=DllStructGetData($tRect,"Right"),$iY=DllStructGetData($tRect,"Bottom")

	_Lib_MouseClick("right", $iX -50, $iY -50, True, 1, 0, True)
	Sleep(1000)
	
	_Lib_PopupScan() ; Auto3Lib internal routine to find popup menus
    Dim $iPopupMenu = _Lib_PopupGetHwnd()
    Dim $iPopupItemCount = _Menu_GetItemCount($iPopupMenu)
	If $iPopupItemCount <= 0 Then 
		;WinClose($toFolderTitle)
		ConsoleWrite("Can't find context menu!"); Ignore popups with no menu items
	EndIf
	Dim $itemIndex=_Menu_FindItem($iPopupMenu,"New") 
	if $itemIndex= -1 Then
		Send("{ESC}") ;close the pop menu
		ConsoleWrite("can't find 'sort' menu item in the context menu!")
	EndIf
	If not _Menu_GetItemEnabled($iPopupMenu,$itemIndex) then 
		Send("{ESC}") ;close the pop menu
		ConsoleWrite("'sort' menu item in the context menu is disabled!")
	EndIf
	
	_Menu_ClickPopup($itemIndex,True); click 'sort' menuitem
	;_Menu_ClickPopupAccel("S")
	Sleep(1000)
	Send("{RIGHT}")
	
	Send("{DOWN}")
	Send("{DOWN}")
	Sleep(1000)
	
	Send("{ENTER}")
	
	Sleep(2000)
	if WinWaitActive($ChangeDocType,"",$ResponseTime)=0 Then
		ConsoleWrite($ChangeDocType&" dialog does not appear")
	EndIf
	
	Dim $typeListView = ControlGetHandle($ChangeDocType,"","SysListView321")
	Dim $typeIndex=ControlListView($ChangeDocType,"","SysListView321","FindItem",$docTypeName,0)
	
	If $typeIndex=-1 Then
		ConsoleWrite("The specified Document Type doesn't exist")
	EndIf
	
	_ListView_ClickItem($typeListView,$typeIndex,"Left",True)
	
	ControlClick($ChangeDocType,"","Button2")
	Sleep(3000)
	
	if WinWaitActive($NewDocTitle,"",$ResponseTime)=0 Then
		ConsoleWrite("New dialog does not appear")
	EndIf
	
	ControlSetText($NewDocTitle,"","Edit1",$newFileName)
	ControlClick($NewDocTitle,"","Button2")
	
	Sleep(3000)
	WinClose($newFileName)
	
	If WinExists($LOTUSQUICKR) Then
			ControlClick($LOTUSQUICKR,"","Button1")
		EndIf
	
	Sleep(1000)
	Send("{ENTER}")
	
EndFunc