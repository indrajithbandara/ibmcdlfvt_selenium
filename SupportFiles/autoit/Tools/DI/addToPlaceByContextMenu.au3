#cs ----------------------------------------------------------------------------

 AutoIt Version: 3.2.4.9
 Author:         myName

 Script Function:
	Template AutoIt script.

#ce ----------------------------------------------------------------------------

; Script Start - Add your code below here
#include "Constants.au3"
#include <A3LTreeView.au3>
#include <A3LListView.au3>
#include<A3LMenu.au3>


;dim $parentFolderTitle = "WordDocs"
;dim $fileName = "BVT_AddToPlace.txt"
;dim $placeName = "Lotus Quickr\BVT_1208839537890 on lwptsthink49"

If $CmdLine[0]<3 Then
		ConsoleWrite("No Enough Parameters"))
		Sleep(2000)
	Exit
EndIf


ConsoleWrite(addFileToPlace($CmdLine[1],$CmdLine[2],$CmdLine[3]))

Func addFileToPlace($parentFolderTitle,$fileName,$savePath)
	
	If WinExists($parentFolderTitle) Then
			WinSetState($parentFolderTitle,"",@SW_MAXIMIZE)
			WinActivate($parentFolderTitle)
		EndIf
		
		if WinWaitActive($parentFolderTitle,"",$ResponseTime)=0 Then
			ConsoleWrite($parentFolderTitle&" folder should be opened firstly")
		EndIf
		
	Dim $handle = WinGetHandle($parentFolderTitle) ;
	Dim $listView = ControlGetHandle($parentFolderTitle,"","SysListView321")
	Dim $folderIndex=ControlListView($parentFolderTitle,"","SysListView321","FindItem",$fileName,0)
	
	If $folderIndex=-1 Then
		ConsoleWrite("The specified file doesn't exist")
	EndIf
	
	_ListView_ClickItem($listView,$folderIndex,"Right",True)
	
	Sleep(1000)
	Dim $iPopupItemCount,$iPopupMenu;
	_Lib_PopupScan() ; Auto3Lib internal routine to find popup menus
    $iPopupMenu = _Lib_PopupGetHwnd()
    $iPopupItemCount = _Menu_GetItemCount($iPopupMenu)
	If $iPopupItemCount <= 0 Then 
		;WinClose($parentFolderTitle)
		ConsoleWrite("Can't find context menu!"); Ignore popups with no menu items
	EndIf
	
	Dim $itemIndex=_Menu_FindItem($iPopupMenu,"Add to Place...") 
	
	;MsgBox(0,'',$itemIndex)
	if $itemIndex= -1 Then
		;WinClose($parentFolderTitle)
		Send("{ESC}") ;close the pop menu
		ConsoleWrite("can't find Add to Place...  menu item in the context menu!")
	EndIf
	
	If not _Menu_GetItemEnabled($iPopupMenu,$itemIndex) then 
		;WinClose($parentFolderTitle)
		Send("{ESC}") ;close the pop menu
		ConsoleWrite("Add to Place... menu item in the context menu is disabled!")
	EndIf
	
	Sleep(1000)
	;_Menu_ClickItem($listView,$iPopupMenu,$itemIndex)
	_Menu_ClickPopup($itemIndex,True)
	
	Dim $pathNodes=StringSplit($savePath,"\")
 
	If StringCompare($pathNodes[1],$LOTUSQUICKR) Then
	 WinClose($AddToPlace)
	 ConsoleWrite("Root not Lotus quick");$RootLibError
	EndIf
	
	 Dim $size=$pathNodes[0]    ;user may input a path ending with '\', even'\\\'
	 While $pathNodes[$size]=""
		 $size=$size-1
	 WEnd
	
	 For $count=2 to $size
		 Sleep(1000)
		Dim $hTree = ControlGetHandle($addToPlaceTitle, "", "SysTreeView321")
		Sleep(1000)
		Dim $hNode = _TreeView_FindNode($hTree, $pathNodes[$count]);
		if $hNode = 0 then 
			WinClose($addToPlaceTitle)
			ConsoleWrite("Library or folder "&$pathNodes[$count]&" can't existed!");$LibNotExisted
		EndIf
		
		_TreeView_Click($hTree, $hNode,"Left",True,1) ;
		Sleep(3000)
		
	Next

	Dim $listView = ControlGetHandle($AddToPlace, "", "SysListView321")

	ControlSetText($addToPlaceTitle,"","Edit1",$fileName)
	ControlClick($addToPlaceTitle,"","Button2")

EndFunc

	