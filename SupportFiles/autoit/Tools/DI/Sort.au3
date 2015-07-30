#include "Constants.au3"
#include <A3LListView.au3>
#include <A3LMenu.au3>
#include <Date.au3>
; ===============================================================================================================================
; Global const 
; ===============================================================================================================================

Global Const $STRINGTYPE =    0 ; sort by name, description and user name
Global Const $NUMBERTYPE =    1 ; sort by File Size
Global Const $DATETYPE   =    2 ; sort by file modified time 

;-------------------------------------------------------------------------------
;tihs is the main execute process.
;-------------------------------------------------------------------------------
Opt("MustDeclareVars", 1)
Opt("WinTitleMatchMode",2)
ClipPut("")
If $CmdLine[0]<2 Then 
	;MsgBox(0,"Info",'Please specify the folder name and path you want to create in the format of "FolderName" "FolderPath" "description[Option]" ',3)
	ClipPut(GetReturnStr($Failed,"No Enough Parameters"))
	Sleep(2000)
	Exit
EndIf

ClipPut(Sort($CmdLine[1],$CmdLine[2]))
Sleep(2000)
;MsgBox(0,'return:',clipget())


;-------------------------------------------------------------------------------
;tihs method is used to set view style of the specified folder just for lotus quickr DI(not support common windows explorer)
;$folderParentTitle: at which folder the $sortType will be set
;@$sortType: the name of the viewer style 
 ;-------------------------------------------------------------------------------
Func Sort($folderParentTitle,$sortType)
	If WinExists($folderParentTitle) Then
		WinSetState($folderParentTitle,"",@SW_MAXIMIZE)
		WinActivate($folderParentTitle)
	EndIf
	if WinWaitActive($folderParentTitle,"",$ResponseTime/10)=0 Then
		Return GetReturnStr($NoTitle,"Context folder should be opened firstly!")
	EndIf
	
	DIM $header = ControlGetHandle($folderParentTitle,"","SysHeader321")
	if $header = 0 then 
		Return GetReturnStr($Failed,"No type for sorting!")
	EndIf
	
	Dim $sortCount =_Header_GetItemCount($header)
	For $i=0 To $sortCount-1
		If StringInStr(_Header_GetItemText($header,$i),$sortType) Then
			ExitLoop
		EndIf
	Next
	
	If $i=$sortCount Then
		Return GetReturnStr($Failed,"No such sort type!")
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
		Return GetReturnStr($Failed,"Can't find context menu!"); Ignore popups with no menu items
	EndIf
	Dim $itemIndex=_Menu_FindItem($iPopupMenu,"Sort") 
	if $itemIndex= -1 Then
		Send("{ESC}") ;close the pop menu
		Return GetReturnStr($Failed,"can't find 'sort' menu item in the context menu!")
	EndIf
	If not _Menu_GetItemEnabled($iPopupMenu,$itemIndex) then 
		Send("{ESC}") ;close the pop menu
		Return GetReturnStr($Failed,"'sort' menu item in the context menu is disabled!")
	EndIf
		 
	_Menu_ClickPopup($itemIndex,True); click 'sort' menuitem
	;_Menu_ClickPopupAccel("S")
	Sleep(1000)
	Send("{RIGHT}")
	
	Dim $moveDownCount=0
	Do
		Send("{DOWN}")
		Sleep(500)
		$moveDownCount=$moveDownCount+1
	Until $moveDownCount>$i
	Send("{ENTER}")
	Sleep(2000)
	;MsgBox(0,"index: "&$i, "move: "&$moveDownCount)
	If StringInStr($sortType,'name') Then
		If IsSortedByName($folderParentTitle,$i) Then
			Return GetReturnStr($Success,'Successful')
		Else
			Return GetReturnStr($Failed,'Failed to sort!')
		EndIf
	Else
		Dim $typeString=GetSortType($sortType)
		If IsSortedByOthers($folderParentTitle,$i,$typeString) Then
	    	Return GetReturnStr($Success,'Successful')
		Else
			Return GetReturnStr($Failed,'Failed to sort!')
	EndIf
	EndIf
	
EndFunc
Func IsSortedByName($folderTitle,$sortCol)
	Dim $listView = ControlGetHandle($folderTitle,"","SysListView321")
	Dim $count=ControlListView($folderTitle,"",$listView,"GetItemCount",$sortCol)
	if $count <=2 Then	Return True
	Dim $nameArray[$count], $typeArray[$count],$iPopupMenu,$itemIndex
	For $j=0 to $count-1 
		$nameArray[$j]=ControlListView($folderTitle,"",$listView,"GetText",$j,$sortCol)
		_ListView_ClickItem($listView,$j,"Right",True)
		Sleep(1000)
		_Lib_PopupScan() ; Auto3Lib internal routine to find popup menus
		$iPopupMenu = _Lib_PopupGetHwnd()
		$itemIndex=_Menu_FindItem($iPopupMenu,"Properties") 
		if $itemIndex= -1 Then
			$typeArray[$j]=1
		Else
			_Menu_ClickPopup($itemIndex,true)
			Sleep(1000)
			If WinExists("Folder Properties") Then
				$typeArray[$j]=2
			Else
				$typeArray[$j]=3
			EndIf
			;WinClose("Properties")
			Send("{ESC}")
		EndIf
	Next
	Dim $pilot1=-1,$pilot2=-1
	For $k=0 to $count-2
		If $typeArray[$k]>$typeArray[$k+1] Then Return False
		If $typeArray[$k]<$typeArray[$k+1] Then
			If $typeArray[$k]=1 Then
				$pilot1=$k
			Else
				$pilot2=$k
			EndIf
		EndIf
	Next
	If $pilot1=-1 and $pilot2=-1 Then Return True
	
	If $pilot1=-1 and $pilot2<>-1 Then
		Dim $result1=CompareInArray(0,$pilot2,$nameArray)
		Dim $result2=CompareInArray($pilot2+1,$count-1,$nameArray)
		If $result1=-1 or $result2=-1 Then Return False ;sort failed
		If $result1=0 Or $result2=0 then Return True ;
		If $result1=$result2 Then Return True
		Return False
	EndIf
	
	If $pilot1<>-1 and $pilot2=-1 Then
		Dim $result1=CompareInArray(0,$pilot1,$nameArray)
		Dim $result2=CompareInArray($pilot1+1,$count-1,$nameArray)
		If $result1=-1 or $result2=-1 Then Return False ;sort failed
		If $result1=0 Or $result2=0 then Return True ;
		If $result1=$result2 Then Return True
		Return False
	EndIf
			
	If $pilot1<>-1 and $pilot2<>-1 Then
		Dim $result1=CompareInArray(0,$pilot1,$nameArray)
		Dim $result2=CompareInArray($pilot1+1,$pilot2,$nameArray)
		Dim $result3=CompareInArray($pilot2+1,$count-1,$nameArray)
		If $result1=-1 Or $result2=-1 or $result3=-1 Then Return False
		If $result1=$result2 And $result1=0 Then Return True
		If $result1=$result3 And $result2=0 Then Return True
		If $result2=$result3 And $result3=0 Then Return True
		If $result1=$result2 And $result1<>0 and $result3=0 Then Return True
		If $result2=$result3 And $result2<>0 and $result1=0 Then Return True
		If $result1=$result3 And $result1<>0 and $result2=0 Then Return True
		If $result1=$result2 And $result2=$result3 Then Return True
		Return False
	EndIf
EndFunc

Func CompareInArray($start,$end,ByRef $arr)
	If $end-$start<=2 then return 0
	Dim $sorted=CompareResult($arr[$start],$arr[$start+1],$STRINGTYPE)
	Dim $k
	For $k=$start+1 to $end-1 
		If $sorted<>CompareResult($arr[$k],$arr[$k+1],$STRINGTYPE) Then return -1
	Next
	If $sorted Then 
		Return 1
	Else
		Return 2
	EndIf
	
EndFunc


Func IsSortedByOthers($currentTitle,$sortCol,$sortType)
	
	Dim $ctrl = ControlGetHandle($currentTitle,"","SysListView321")
	Dim $total=ControlListView($currentTitle,"",$ctrl,"GetItemCount")
	if $total <=2 Then	Return True
	Dim $nameArray[$total]
	For $j=0 to $total-1 
		$nameArray[$j]=ControlListView($currentTitle,"",$ctrl,"GetText",$j,$sortCol)
	Next
	Dim $count=0
	Dim $prior=$nameArray[$count]
	Dim $str
	Do
		$count=$count+1
		if $count>=$total Then ExitLoop
		$str=$nameArray[$count]
		If $prior==$str Or $prior="" Then 
			$prior=$str
			ContinueLoop
		Else
			ExitLoop
		EndIf
	Until $count>=$total ;this do-Until loop just finds the first two different text, and their comparing result indicates the ascdend or descend order of the column. 
	;Special Notes: if the text got from the column is a "" string, it's always before the String whose lenght>0, so we should ensure the first string is not "".
	;for example:("","0 KB","6 KB","9 KB"). If we can't Control $prior, here it was assigned "", and $str is assigned "0 KB". but the result of comparing $prior and $str is False, according 
	;to CompareResult method. But in fact, we should check the sort order just from "0 KB" and "6 KB". Same case like ("","44 KB","24 KB","12 KB").
	;---------This is very important for the comparing of two digital strings.------------
	If $count>=$total Then Return True
	Dim $sorted=CompareResult($prior,$str,$sortType)
	Do
		$prior=$str
		$count=$count+1
		If $count>=$total Then ExitLoop
		$str=$nameArray[$count]
		If $prior==$str Then ContinueLoop
		If $sorted<>CompareResult($prior,$str,$sortType) Then 
			Return False
		EndIf
	Until $count>=$total
	
	Return True
	
EndFunc



Func CompareResult($strOne,$strOther,$sortType); For different sort type, we give different method to indicate the bigger or letter relationship
	
	If $sortType=$STRINGTYPE Then		Return StringCompare($strOne,$strOther)>0
	If $sortType=$NUMBERTYPE Then		Return GetNumberFromString($strOther)>GetNumberFromString($strOne)
	If $sortType=$DATETYPE   Then		Return _DateDiff('s',$strOne,$strOther)>0
		
EndFunc
	
	
Func GetSortType($sortType);,$referSortCount)
;	If $referSortCount=2 Then Return $STRINGTYPE ; just for the sort operation of the Library: only two sorting types: Name and descriptions
	If StringInStr('size',$sortType) Then Return $NUMBERTYPE
	If StringInStr('modified',$sortType) Then Return $DATETYPE
	Return $STRINGTYPE
EndFunc

Func GetNumberFromString($string) ;Here the format input $string is like: 'xx,xxx,xxx KB', we just need to remove ' KB' and ',' to get the number
	Dim $len=StringLen($string)
	Dim $localStr=StringMid($string,1,$len-3)
	Dim $digtalStr=StringReplace($localStr,",","")
	Dim $abc=Number($digtalStr)
	;MsgBox(0,"Number is:",$abc)
	Return Number($digtalStr)
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
