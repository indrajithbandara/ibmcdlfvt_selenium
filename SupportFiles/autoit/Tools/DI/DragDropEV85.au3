#include "Constants.au3"
#include <A3LListView.au3>
#include<A3LMenu.au3>

Opt("WinTitleMatchMode",2)
Opt("MouseClickDragDelay",3000)
Opt("MustDeclareVars",1)
ClipPut("")
;problems:
;1. Using autoit to copy the speicified folder or file, but on the target folder, the paste menu item is 
;not found! so strange
;2. If target folder is not in detail view, then we can't right-click the folder rightly!
If $CmdLine[0]<4 Then 
	;MsgBox(0,"Info",'Please specify the folder name and path you want to create in the format of "SourceFolder" "TargetFolder" "SamePath" ',3)
	ClipPut(GetReturnStr($Failed,"No Enough Parameters"))
	Sleep(2000)
	Exit
EndIf

;MsgBox(0,"Info","copy folder to target folder successfully",3)

ClipPut(DragDropEV($CmdLine[1],$CmdLine[2],$CmdLine[3],$CmdLine[4]))
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
Func DragDropEV($fromFolderTitle,$fileList,$toFolderTitle,$checkIn)
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
	
	If WinExists($toFolderTitle) Then
		WinSetState($toFolderTitle,"",@SW_MAXIMIZE)
		WinActivate($toFolderTitle)
	EndIf
	if WinWaitActive($toFolderTitle,"",$ResponseTime)=0 Then
		Return GetReturnStr($Failed,"Target folder should be opened firstly!")
	EndIf
	$ctrl = ControlGetHandle($toFolderTitle,"","SysListView321")
	ControlListView($toFolderTitle,"",$ctrl,"SelectClear")
	Sleep(1000)
	MouseUp("left")
	Sleep(5000)
	If WinExists($LOTUSQUICKR,"are the same") Then ;new harvest: if you copy the files which are in the target folder already, then this dialog will appear!
		Dim $text=ControlGetText($LOTUSQUICKR,"are the same","Static1")
		WinClose($LOTUSQUICKR,"are the same")
		Return GetReturnStr($Failed,$text)
	EndIf
	Dim $text1=""
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
		If WinExists($LOTUSQUICKR) Then  ;now here are two cases: 1) the file or folder has existed; 2) can't copy to the current folder(may be a bug); 3) invalid characters(window is ok, but DI can't support!)
			$text1=ControlGetText($LOTUSQUICKR,"","Static1")
			;WinClose($LOTUSQUICKR) ; a dialog may appear: Item already exists.Do you wish to continue? yes or no button
			ControlClick($LOTUSQUICKR,"","Button1")
			;Return GetReturnStr($Failed,"The file has existed or includes invalid characters!")
		ElseIf WinExists("Overwrite","already contains") or WinExists("Replace","already contains")Then ;copy to DI
			;ControlClick("Overwrite","already contains","Button3")
			Send("A")
		EndIf
	Until not WinExists($Copying_EN)
	
	If $text1<>"" Then
		Return GetReturnStr($Failed,$text1)
	Else
		Return GetReturnStr($Success,"Successful")
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

	