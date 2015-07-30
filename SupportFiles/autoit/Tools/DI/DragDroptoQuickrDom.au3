#include "Constants.au3"
#include <A3LListView.au3>
#include<A3LMenu.au3>

Opt("WinTitleMatchMode",2)
Opt("MouseClickDragDelay",3000)
Opt("MustDeclareVars",1)
ClipPut("")
;problems:
;1. Using autoit to copy the specified folder or file, but on the target folder, the paste menu item is 
;not found! so strange
;2. If target folder is not in detail view, then we can't right-click the folder rightly!
If $CmdLine[0]<5 Then 
	;MsgBox(0,"Info",'Please specify the folder name and path you want to create in the format of "SourceFolder" "TargetFolder" "SamePath" ',3)
	ClipPut(GetReturnStr($Failed,"No Enough Parameters"))
	Sleep(2000)
	Exit
EndIf

;MsgBox(0,"Info","copy folder to target folder successfully",3)

ClipPut(DragDropQuickr($CmdLine[1],$CmdLine[2],$CmdLine[3],$CmdLine[4],$CmdLine[5]))
Sleep(2000)
;MsgBox(0,"return:",ClipGet())

;-------------------------------------------------------------------------------
;$fromFolderTitle: the window title of which the $fileList is under (need to call OpenFolder() firstly)
;$fileList: the name of folder or file will be copyed
;$toFolderTitle: the parent folder of $targetFolder which $fileList will be placed in (need to call OpenFolder() firstly)
;$targetFolder: the $fileList will be placed in this folder
;return: a string indicating the operation result,such as "0:Success" or "-1:Failed"
;--------------------------------Notes-----------------------------------
;if the file or folder has existed in the target folder, then this operation will overwrite it
;-------------------------------------------------------------------------------
;drag and drop enhanced version: drag files and folders in the different parent folders!
Func DragDropQuickr($fromFolderTitle,$fileList,$toQuickrTitle,$x,$y)
	If WinExists($fromFolderTitle) Then
		WinSetState($fromFolderTitle,"",@SW_MAXIMIZE)
		WinActivate($fromFolderTitle)
	EndIf
	
	if WinWaitActive($fromFolderTitle,"",$ResponseTime)=0 Then
		Return GetReturnStr($NoTitle,"Source folder should be opened firstly!")
	EndIf
	
;	Dim $handle = WinGetHandle($fromFolderTitle) ;
	Dim $libraries=StringSplit($fileList,":")
	Dim $ctrl = ControlGetHandle($fromFolderTitle,"","SysListView321")
	;_GUICtrlListViewSetCheckState($ctrl,-1,0) no need to deselect all firstly
	ControlListView($fromFolderTitle,"",$ctrl,"SelectClear")
	Dim $index,$rightClickItem=-1
	For $i=1 To $libraries[0]
		$index=ControlListView($fromFolderTitle,"",$ctrl,"FindItem",$libraries[$i])
		If $index<>-1 Then
			;MsgBox(0,$index,"")
			ControlListView($fromFolderTitle,"",$ctrl,"Select",$index)
			$rightClickItem=$index
		EndIf
		Sleep(1000)
	Next
	If $rightClickItem=-1 Then
		Return GetReturnStr($Failed,"All the specified libraries don't exist!"); Ignore popups with no menu items
	EndIf
	
	Dim $startRect  = _ListView_GetItemRectEx($ctrl, $rightClickItem)
	Dim $startPoint = _Lib_PointFromRect($startRect)
	Dim $startX,$startY,$endX,$endY
	_API_ClientToScreen($ctrl, $startPoint)
	_Lib_GetXYFromPoint($startPoint, $startX, $startY)
	;MouseClickDrag("Left",$startX,$startY,$startX+20,$startY,50)
	MouseMove($startX,$startY)
	MouseDown("left")
	
	dim $tRect = _API_GetWindowRect($ctrl)
	Dim $iX=DllStructGetData($tRect,"Right"),$iY=DllStructGetData($tRect,"Bottom")
	MouseMove($iX-50,$iY-50)
	
	If WinExists($toQuickrTitle) Then
		WinSetState($toQuickrTitle,"",@SW_MAXIMIZE)
		WinActivate($toQuickrTitle)
	EndIf
	if WinWaitActive($toQuickrTitle,"",$ResponseTime)=0 Then
		Return GetReturnStr($Failed,"Quickr Target should be opened firstly!")
	EndIf
	$ctrl = ControlGetHandle($toQuickrTitle,"","SysListView321")
	ControlListView($toQuickrTitle,"",$ctrl,"SelectClear")
	Sleep(1000)
	;need to move Mouse to middle of quickr screen
	;dim $tRect = _API_GetWindowRect($ctrl)
	;Dim $iX=DllStructGetData($tRect,"Left"),$iY=DllStructGetData($tRect,"Top")
	;MsgBox(0,$iX,$iY)
	;Dim $iW=DllStructGetData($tRect,"Right"),$iH=DllStructGetData($tRect,"Bottom")
	;MsgBox(0,$iW,$iH)
	MouseMove($X,$Y)
	MouseUp("left")
	Sleep(5000)
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

	