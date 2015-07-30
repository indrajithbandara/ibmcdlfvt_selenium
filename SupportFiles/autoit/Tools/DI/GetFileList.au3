#include "Constants.au3"
#include <A3LListView.au3>
#include <A3LTreeView.au3>
#include <A3LMenu.au3>
opt("WinTitleMatchMode",2)
Opt("MustDeclareVars",1)
ClipPut("")
If $CmdLine[0]=0 Then 
	ClipPut(GetReturnStr($Failed,"No Enough Parameters"))
	Sleep(2000)
	Exit
EndIf
ClipPut(GetFileList($CmdLine[1]))
Sleep(2000)
;MsgBox(0,'return:',ClipGet())


;-------------------------------------------------------------------------------
;tihs method is used to get the file list of a specified folder, view or library 
;$parentFolderTitle: the title of open window representing the $folder,which the deleted folder is under,that's to say, you need to open it(by OpenFolder()) before calling this method
;$folder : the fold will be deleted, and it can't be ""
;return: a string indicating the operation result, such as "0:pop menu content" or "-1:Failed"
;-------------------notes-------------------------
;if the second parameter equals "", then will get the content of pop-up menu of "Lotus quickr"
;-------------------------------------------------------------------------------
Func GetFileList($parentFolderTitle)
	If WinExists($parentFolderTitle) Then
		WinSetState($parentFolderTitle,"",@SW_MAXIMIZE)
		WinActivate($parentFolderTitle)
	EndIf
	
	if WinWaitActive($parentFolderTitle,"",$ResponseTime)=0 Then
		Return GetReturnStr($NoTitle,"Parent folder should be opened firstly")
	EndIf
	;refresh the view before doing this as sometimes its failing
	Send("{F5}")
	sleep(2000)
	Dim $listView = ControlGetHandle($parentFolderTitle,"","SysListView321")
	Dim $count=ControlListView($parentFolderTitle,"",$listView,"GetItemCount")
	If $count=0 Then
		Return GetReturnStr($Failed,"No folder or files in this "&$parentFolderTitle)
	EndIf
	
	Dim $returnStr="", $text,$iPopupMenu,$itemIndex,$type
	For $iI=0 to $count-1
		$text=ControlListView($parentFolderTitle,"","SysListView321","GetText",$iI,0)
		if  $text<> "" then 
			_ListView_ClickItem($listView,$iI,"Right",True)
			Sleep(1000)
			_Lib_PopupScan() ; Auto3Lib internal routine to find popup menus
			$iPopupMenu = _Lib_PopupGetHwnd()
			$itemIndex=_Menu_FindItem($iPopupMenu,"Properties") 
			if $itemIndex= -1 Then
				$type=$ViewType
			Else
				_Menu_ClickPopup($itemIndex,true)
			Sleep(1000)
			If WinExists("Folder Properties") Then
				$type=$FolderType
			Elseif WinExists("Properties","Checked out by") Then
				$type=$FileType
			Else
				$type=$LibraryType
			EndIf
			;WinClose("Properties") ;if we use this statement, we can't close the pop menu of views, because they can't have "Properties" dialog, just a pop menu
			Send("{ESC}") ;"ESC" can close "Properties" dialog for folder and file,but also popmenu for view.
		EndIf
			If $returnStr="" Then
				$returnStr=$text&":"&$type
			Else
				$returnStr=$returnStr&'\'&$text&":"&$type
			EndIf
		EndIf
	Next
	Return GetReturnStr($Success,$returnStr)
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

	