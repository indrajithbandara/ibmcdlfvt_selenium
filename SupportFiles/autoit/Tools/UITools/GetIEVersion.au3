#include "Constants.au3"
Opt("MustDeclareVars",1)
ClipPut("")
Sleep(2000)
ClipPut(GetIEVersion())
Func GetIEVersion() ;$parentFolderTitle should be "Choose File to Upload"(IE8)/"File Upload"(FF2.0)/"Choose file"(IE7)
	Dim $r = RegRead("HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Internet Explorer", "Version")
	;msgbox(4096,"IE version",$r)
	If $r="" Then
		Return GetReturnStr($Failed,"Failed to get IE version on this client!")
	Else
		Return GetReturnStr($Success,$r)
	EndIf
EndFunc


Func ToFormatedStr($str)
	;Dim $formatedStr=StringReplace(StringReplace($str,'x','xz'),':','xy')
	Return $str;$formatedStr
EndFunc

Func GetReturnStr($returnCode,$returnStr)
	Return $returnCode&$Separator&ToFormatedStr($returnStr)
EndFunc