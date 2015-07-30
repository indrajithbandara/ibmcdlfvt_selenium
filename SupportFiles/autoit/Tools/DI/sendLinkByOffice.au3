#cs ----------------------------------------------------------------------------

 AutoIt Version: 3.2.4.9
 Author:         myName

 Script Function:
	Template AutoIt script.

#ce ----------------------------------------------------------------------------

; Script Start - Add your code below here

#include "Constants2.au3"
#include <A3LListView.au3>
#include <A3LMenu.au3>


;Dim $parentFolderTitle = "BVT_1208312966406 on lwptsthink07"
;Dim $fileName = "Word_BVT_Doc1.doc"

Dim $parentFolderTitle
Dim $fileName

sendLinkByOffice($parentFolderTitle,$fileName)

Func sendLinkByOffice($parentFolderTitle,$fileName)
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
	
	_ListView_ClickItem($listView,$folderIndex,"Left",True,2)
	
	Sleep(2000)
	
	Send("!H")
	Send("{RIGHT}")
	Send("{DOWN}")
	Send("{DOWN}")
	Send("{DOWN}")
	Send("{DOWN}")
	Send("{DOWN}")
	
	Send("{RIGHT}")
	Send("{DOWN}")
	Send("{ENTER}")
	
	Sleep(3000)
	
	WinActivate($fileName) 
	
	WinClose($fileName)
	
	If WinExists($LOTUSQUICKR) Then
			ControlClick($LOTUSQUICKR,"","Button2")
		EndIf
	
EndFunc