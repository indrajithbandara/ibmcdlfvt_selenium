
#include "Constants.au3"
#include <A3LListView.au3>
#include <A3LMenu.au3>

dim $rootPath = "Lotus Quickr"

checkMonitor()

Func checkMonitor()
	

	Run(@WindowsDir&"\explorer.exe")
	Sleep(3000)

	If WinWaitActive($MyDocument_EN,"",$ResponseTime)=0 Then
		ConsoleWrite("can't open windows explorer")
	EndIf

	ControlSetText($MyDocument_EN,"","Edit1",$rootPath)
	ControlSend($MyDocument_EN,"","Edit1","{ENTER}")
	Sleep(3000)
	
	Dim $listView = ControlGetHandle($rootPath,"","SysListView321")
	dim $tRect = _API_GetWindowRect($listView)
	Dim $iX=DllStructGetData($tRect,"Right"),$iY=DllStructGetData($tRect,"Bottom")
	
	_Lib_MouseClick("right", $iX -70, $iY +15, True, 2, 0, True)
	Sleep(2000)
	
	_Lib_MouseClick("left", $iX -70, $iY +15, True, 2, 0, True)
	Sleep(2000)
	
	WinClose("Monitor")
	
EndFunc