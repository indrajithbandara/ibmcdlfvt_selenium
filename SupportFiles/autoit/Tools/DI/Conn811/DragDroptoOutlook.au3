#include "Constants.au3"
#include <A3LListView.au3>
#include <A3LTreeView.au3>
#include<A3LMenu.au3>

Opt("WinTitleMatchMode",2)
Opt("MouseClickDragDelay",3000)
Opt("MustDeclareVars",1)
ClipPut("")

If $CmdLine[0]<4 Then 
	ClipPut(GetReturnStr($Failed,"No Enough Parameters"))
	Sleep(2000)
	Exit
EndIf

;MsgBox(0,"Info","copy folder to target folder successfully",3)

ClipPut(DragDropQuickr($CmdLine[1],$CmdLine[2],$CmdLine[3],$CmdLine[4]))
Sleep(2000)
;MsgBox(0,"return:",ClipGet())

;-------------------------------------------------------------------------------
;$fromFolderTitle: the window title of which the $fileList is under (need to call OpenFolder() firstly)
;$fileList: the name of folder or file will be copyed
;$folder: the folder in Outlook pane that is already visible
;return: a string indicating the operation result,such as "0:Success" or "-1:Failed"
;--------------------------------Notes-----------------------------------
;if the file or folder has existed in the target folder, then this operation will overwrite it
;-------------------------------------------------------------------------------
;drag and drop enhanced version: drag files and folders in the different parent folders!
Func DragDropQuickr($fromFolderTitle,$fileList, $folder, $checkIn)
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
	
	If WinExists($MicrosoftOutlook_EN) Then
		WinSetState($MicrosoftOutlook_EN,"",@SW_MAXIMIZE)
		WinActivate($MicrosoftOutlook_EN)
	EndIf
	if WinWaitActive($MicrosoftOutlook_EN,"",$ResponseTime)=0 Then
		Return GetReturnStr($Failed,"MS Outlook should be opened firstly!")
	EndIf
	;get coords of folder
	Dim $hTree = ControlGetHandle($MicrosoftOutlook_EN, "", "SysTreeView321")
	dim $posTree = ControlGetPos($MicrosoftOutlook_EN, "", "SysTreeView321")
	Dim $hNode = _TreeView_FindNode($hTree, $folder);
	If $hNode=0 Then
		Return GetReturnStr($Failed,"folder doesn't exist, please create or display folder first")
	EndIf
	dim $pos = _TreeView_DisplayRect($hTree, $hNode, true)
	;MsgBox(0,$pos[0],$pos[1])
	MouseMove($posTree[0]+$pos[0]+30,$posTree[1]+$pos[1]+20)
	MouseUp("left")
	Sleep(5000)
	Dim $text1="test"
	
	Do ;no matter from local to local, local to di, di to local or di to di, "copying..." dialog appears firstly
		Sleep(1000)
		If WinExists($DocumentAdd) Then
			;MsgBox(0,"hi",$checkIn)
			If StringCompare($checkIn, "true", 0) = 0 Then
				ControlClick($DocumentAdd,"","Button2")	
			Else
				ControlClick($DocumentAdd,"","Button3")
			EndIf	
			ControlClick($DocumentAdd,"","Button5")
		EndIf
		Sleep(1000)
		If WinExists($LOTUSQUICKROld) Then  ;now here are two cases: 1) the file or folder has existed; 2) can't copy to the current folder(may be a bug); 3) invalid characters(window is ok, but DI can't support!)
			$text1=ControlGetText($LOTUSQUICKROld,"","Static1")
			;WinClose($LOTUSQUICKR) ; a dialog may appear: Item already exists.Do you wish to continue? yes or no button
			ControlClick($LOTUSQUICKROld,"","Button1")
			;Return GetReturnStr($Failed,"The file has existed or includes invalid characters!")
		ElseIf WinExists("Overwrite","already contains") or WinExists("Replace","already contains")Then ;copy to DI
			;ControlClick("Overwrite","already contains","Button3")
			Send("A")
		EndIf
	Until not WinExists($Copying_EN)
	;look for file or folder
	$hNode = _TreeView_FindNode($hTree, $folder);
	For $i=1 To $libraries[0]
		$hNode = _TreeView_FindNode($hTree, $libraries[$i]);
		If $hNode=0 Then
			Return GetReturnStr($Failed,"folder doesn't exist, please create or display folder first")
		EndIf
	Next
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

	