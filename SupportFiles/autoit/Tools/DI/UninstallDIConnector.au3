#include "Constants.au3"
#include <A3LListView.au3>
#include <A3LMenu.au3>

Opt("WinTitleMatchMode",2)
Opt("MustDeclareVars",1)
ClipPut("")
ClipPut(UninstallConnector())
Sleep(2000)
;MsgBox(0,"From clipboard:",ClipGet())


;-------------------------------------------------------------------------------
;tihs method is used to install quickr
;@return: a string indicating the operation result: "0:{$txtName}" or "-1:reason"
;-------------------------------------------------------------------------------

Func UninstallConnector() 
	;check whether confirm dialog for uninstalling appear
	Dim $i=0
	While WinExists($titleOfUninstallDlg,$textInUninstallDialog)=False
		Sleep(2000)
		$i=$i+1
		if($i>20) Then ExitLoop
	WEnd
	If($i>20) Then
		Return GetReturnStr($Failed,"Window with title: "&$titleOfUninstallDlg&" and text: "&$textInUninstallDialog&" should appear")
	EndIf
	WinActivate($titleOfUninstallDlg,$textInUninstallDialog)
	Sleep(2000)
	;click Yes button to uninstall Connector;
	ControlClick($titleOfUninstallDlg,$textInUninstallDialog,"&Yes")
	;check whether progress bar dialog appear
	If(WinWaitActive($titleWhenUninstall,$textWhenUninstall,$ResponseTime*10)=0) Then
		Return GetReturnStr($Failed,"No Progress bar for uninstall appear!")
	EndIf
	While(WinExists($titleWhenUninstall,$textWhenUninstall))
		Sleep(1000)
		if WinExists($titleWhenUninstall,$textCloseApplication) Then
			ControlClick($titleWhenUninstall,$textCloseApplication,"&Ignore")
		EndIf
	WEnd
	;if error acuur, just click "OK" button
	Sleep(3000)
	If WinExists($titleProblemInfo,$textProblemInfo) Then
		ControlClick($titleProblemInfo,$textProblemInfo,"&OK")
	EndIf
	Sleep(3000)
	;don't reboot here, manually reboot it
	If WinExists($titleProblemInfo,$textRestartClient) Then
		ControlClick($titleProblemInfo,$textRestartClient,"&No")
	EndIf
	Return GetReturnStr($Success,"Install Lotus Quickr Connector successfully")
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
