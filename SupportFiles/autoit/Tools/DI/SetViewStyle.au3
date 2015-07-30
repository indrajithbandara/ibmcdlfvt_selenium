#include "Constants.au3"
#include <A3LListView.au3>
#include <A3LMenu.au3>
Opt("WinTitleMatchMode",2)
opt("MustDeclareVars",1)
ClipPut("")
If $CmdLine[0]<2 Then 
	;MsgBox(0,"Info",'Please specify the folder name and path you want to create in the format of "FolderName" "FolderPath" "description[Option]" ',3)
	ClipPut(GetReturnStr($Failed,"No Enough Parameters"))
	Sleep(2000)
	Exit
EndIf

ClipPut(SetViewStyle($CmdLine[1],$CmdLine[2]))
Sleep(2000)
;MsgBox(0,"From clipboard:",clipget())


;-------------------------------------------------------------------------------
;tihs method is used to set view style of the specified folder just for lotus quickr DI(not support common windows explorer)
;@$viewType: the name of the viewer style 
;@$FolderPath: at which folder the $viewType will be set 
;-------------------------------------------------------------------------------
Func SetViewStyle($folderParentTitle,$viewType)
	If WinExists($folderParentTitle) Then
		WinSetState($folderParentTitle,"",@SW_MAXIMIZE)
		WinActivate($folderParentTitle)
	EndIf
	if WinWaitActive($folderParentTitle,"",$ResponseTime/10)=0 Then
		Return GetReturnStr($NoTitle,"Parent folder should be opened firstly!")
	EndIf
	Dim $cmd=stringleft($viewType,1)
	If $cmd<>'d' and $cmd<>'i' And $cmd<>'l' And $cmd<>'t' Then Return GetReturnStr($Failed,"No such sort type!")
		
	Dim $listView = ControlGetHandle($folderParentTitle,"","SysListView321")
	dim $tRect = _API_GetWindowRect($listView)
	Dim $iX=DllStructGetData($tRect,"Right"),$iY=DllStructGetData($tRect,"Bottom")
	_Lib_MouseClick("right", $iX -50, $iY -50, True, 1, 0, True)
	Sleep(1000)
	
	_Lib_PopupScan() ; Auto3Lib internal routine to find popup menus
    Dim $iPopupMenu = _Lib_PopupGetHwnd()
    Dim $iPopupItemCount = _Menu_GetItemCount($iPopupMenu)
	If $iPopupItemCount <= 0 Then 
		;WinClose($parentFolderTitle)
		Return GetReturnStr($Failed,"Can't find context menu!"); Ignore popups with no menu items
	EndIf
	Dim $itemIndex=_Menu_FindItem($iPopupMenu,"View")
	
	if $itemIndex= -1 Then
		;WinClose($parentFolderTitle)
		Return GetReturnStr($Failed,"can't find 'sort' menu item in the context menu!")
	EndIf
	If not _Menu_GetItemEnabled($iPopupMenu,$itemIndex) then 
		;WinClose($parentFolderTitle)
		Return GetReturnStr($Failed,"'sort' menu item in the context menu is disabled!")
	EndIf
		 
	_Menu_ClickPopup($itemIndex,True); click 'view' menuitem
	Sleep(1000)
	#comments-start
	Switch StringLeft($viewType,1)
		case "Details" ;detail infomation
			Send("D")
		case "Icon";Tu Biao
			Send("I")
		case "List"   ;list
			Send("L")
		case "Tile"
			Send("T")
	EndSwitch
	#comments-end
	Send($cmd)
	Send("{ENTER}")
	Sleep(2000)
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