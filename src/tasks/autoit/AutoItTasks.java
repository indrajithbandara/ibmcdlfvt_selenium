//package tasks.autoit;
//
//import java.awt.datatransfer.Clipboard;
//import java.awt.datatransfer.DataFlavor;
//import java.awt.datatransfer.Transferable;
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//
///**
//* Script Name   : <b>AdminTasks</b>
//* Generated     : <b>November 12, 2006 9:31:11 AM</b>
//* Description   : Portal Admin Task Method Class Library<BR>
//* Original Host : WinNT Version 5.0  Build 2195 (Service Pack 4)
//* 
//* @since  2006/11/12
//* @author bmurray
//*/
//public class AutoItTasks extends AutoItTasksHelper
//{
//	/**Global string for tools path*/
//	public static String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\UITools";
//	
//	/**Global string for user path*/
//	public static String gsFlagFileName = System.getProperty("user.home")+ VisualReporter.gsFileSeparator + "AutoITDone.txt";
//	
//	/**Global string for Lotus Quickr*/
//	public static String gsLotusQuickr811 = "Lotus Quickr\\Team Places";//"IBM Lotus\\Team"; //formerly "Lotus Quickr";
//	
//	/**Global string for Lotus Quickr Personal Files*/
//	public static String gsLotusQuickrEntry811 = "Lotus Quickr\\Personal Files";
//	
//	/**Global string for Lotus Quickr Team Places*/
//	public static String gsTeamPlaces = "Team Places";
//	
//	/**Global string for Lotus Quickr 81*/
//	public static String gsLotusQuickr = "Lotus Quickr"; //formerly "Lotus Quickr";
//
//	/**Global string for Lotus Quickr Version*/
//	public static String gsConnVersion = "811"; //old version...default
//	
//	/** 
//	*  Uses AutoIt executable to set focus on a window
//	* @param  String sWindowsTitle -  Window title to set focus to
//	* @return   	
//	*/
//
//	public boolean setFocus(String sWindowsTitle)
//	{
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\UITools";
//		String sToolName = gsToolsPath + "\\SetFocus.exe";
//
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + sWindowsTitle + "\"";
//		VisualReporter.logScriptInfo("Try to activate window with title "+sWindowsTitle);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();		
//		if (resultStr==null) return false;		
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("SetFocus routine executed "+(result==0? "Successfully":"Failed"));		
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error reason:  "+resultStr[1]);
//		return false;
//	}
//
//	public boolean closeWindow(String sWindowsTitle)
//	{
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\UITools";
//		String sToolName = gsToolsPath + "\\WindowClose.exe";
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + sWindowsTitle + "\"";
//		
//		VisualReporter.logScriptInfo("Try to close window with title "+sWindowsTitle);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();		
//		if (resultStr==null) 
//			return false;		
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("CloseWindow routine "+(result==0? "Succeeded":"Failed"));		
//		if (result==0) 
//			return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error reason:  "+resultStr[1]);
//		return false;
//	}
//	
//	/** 
//	*  Uses AutoIt to check for a tool
//	* @param  String sTool -  Window tool to look for
//	* @return   	
//	*/
//
//	public boolean checkForTool(String sTool)
//	{
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\UITools";
//		String sToolName = gsToolsPath + "\\CheckForQuickr.exe";
//
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + sTool + "\"";
//		
//		VisualReporter.logScriptInfo("Try to check tool exists or not: "+sTool);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();		
//		if (resultStr==null) 
//			return false;		
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("CheckForQuickr routine "+(result==0? "Succeeded":"Failed"));		
//		if (result==0) 
//			return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error reason:  "+resultStr[1]);
//		return false;
//		
//	}
//	
//	/** 
//	*  Uses AutoIt to check for existence of a dialog with specific text
//	* @param  String sCaption -  Dialog caption to look for
//	* @param  String sText -  Dialog text to look for
//	* @param  int iTime -  How long to wait for dialog
//	* @return   	
//	*/
//
//	public boolean checkForDialog(String sText, String sCaption, int iTime)
//	{
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\UITools";
//		String sToolName = gsToolsPath + "\\CheckForDialog.exe";
//		 
//
//		String sCMD="\"" + sToolName + "\" \"" + sCaption + "\" \"" + sText + "\" \"" + iTime + "\"";
//		VisualReporter.logScriptInfo("Looking for dialog with caption " + sCaption + " and text " +sText);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();		
//		if (resultStr==null) 
//			return false;		
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Looking for dialog routine executed "+(result==0? "Successfully":"Failed"));		
//		if (result==0) 
//			return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error reason:  "+resultStr[1]);
//		return false;
//		
//	}
//	
//	/** 
//	*  Uses AutoIt to check for existence of a dialog with specified title
//	* @param  String sCaption -  Dialog caption to look for
//	* @param  int iTime -  How long to wait for dialog
//	* @return   	
//	*/
//
//	public boolean checkForDialog(String sCaption, int iTime)
//	{
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\UITools";
//		String sToolName = gsToolsPath + "\\CheckForDialogBox.exe";
//	
//		String sCMD="\"" + sToolName + "\" \"" + sCaption + "\" \"" + iTime + "\"";
//		VisualReporter.logScriptInfo("Looking for dialog with caption " + sCaption);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();		
//		if (resultStr==null) 
//			return false;		
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Looking for dialog routine executed "+(result==0? "Successfully":"Failed"));		
//		if (result==0) 
//			return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error reason:  "+resultStr[1]);
//		return false;
//		
//	}
//	/** 
//	*  Uses AutoIt executable to set focus on a window and get the item count
//	* @param  String sWindowsTitle -  Window title to set focus to
//	* @return   	
//	*/
//
//	public boolean getSelectCount(String sWindowsTitle)
//	{
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\UITools";
//		String sToolName = gsToolsPath + "\\GetSelectCount.exe";
//		
//		String sCMD="\"" + sToolName  + "\" \"" + sWindowsTitle + "\"";
//		VisualReporter.logScriptInfo("Get select count from window "+sWindowsTitle);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}
//		catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) 
//			return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Getting select count from window executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) 
//			return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error reason:  "+resultStr[1]);
//		return false;
//	}
//
//	
//	/** 
//	*  Uses AutoIt executable to set focus on a window and to select an item in a list
//	* @param  String sWindowsTitle -  Window title to set focus to
//	* @param  String sPlaceNum -  The zero based item to select
//	* @return   	
//	*/
//
//	public boolean selectOne(String sWindowsTitle, String sPlaceNum)
//	{
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\UITools";
//		String sToolName = gsToolsPath + "\\SelectOne.exe";
//		
//		String sCMD="\"" + sToolName  + "\" \"" + sWindowsTitle + "\" \"" + sPlaceNum + "\"";
//		VisualReporter.logScriptInfo("Select item with index "+sPlaceNum+" from window "+sWindowsTitle);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}
//		catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) 
//			return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Selecting item executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) 
//			return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error reason:  "+resultStr[1]);
//		return false;
//	}
//
//	/** 
//	*  Uses AutoIt executable to set focus on a window and to select an item in a list
//	* @param  String sWindowsTitle -  Window title to set focus to
//	* @param  String sString -  The string to choose in list
//	* @return   	
//	*/
//
//	public boolean selectString(String sWindowsTitle, String sString)
//	{
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\UITools";
//		String sToolName = gsToolsPath + "\\SelectString.exe";
//
//		String sCMD="\"" + sToolName  + "\" \"" + sWindowsTitle + "\" \"" + sString + "\"";
//		VisualReporter.logScriptInfo("Select item with name "+sString+" from window "+sWindowsTitle);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}
//		catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) 
//			return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Selecting item executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) 
//			return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error reason:  "+resultStr[1]);
//		return false;
//	}
//	
//	/** 
//	*  Uses AutoIt executable to set focus on a window, set text in a box and click button
//	* @param  String sWindowsTitle -  Window title to set focus to
//	* @param  String sTextBoxName = string associated with the text box
//	* @param  String sSetTextString = String to put in text box
//	* @param  String sButton = Button to click
//	* @return   	
//	*/
//
//	public boolean setTextClickButton (String sWindowsTitle, String sTextBoxName, String sSetTextString, String sButton)
//	{
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\UITools";
//		String sToolName = gsToolsPath + "\\SetTextClickButton.exe";
//		String sCMD="\"" + sToolName + "\" \"" + sWindowsTitle + "\" \"" + sTextBoxName+ "\" \"" + sSetTextString+ "\" \"" + sButton + "\"";
//		
//		VisualReporter.logScriptInfo("Settting focus to " + sWindowsTitle + " Window, input text " + sSetTextString+ " and click button "+sButton);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//			
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) 
//			return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Setting text and clicking button execute  "+(result==0? "Successfully":"Failed"));
//		if (result==0) 
//			return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error Reason: "+resultStr[1]);
//		return false;
//	}
//	
//	/** 
//	*  Uses AutoIt executable to set focus on a window and input keys
//	* @param  String sWindowsTitle -  Window title to set focus to
//	* @param  String sKeys -  Keystrokes to do
//	* @return   	
//	*/
//
//	//need the old version for doing keystroke stuff else it messes up
//	public void setFocusDoKeys2 (String sWindowsTitle, String sKeys)
//	{
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\UITools";
//		String sToolName = gsToolsPath + "\\SetFocusDoKeys2.exe";
//		deleteFlagFile ();
//		VisualReporter.logScriptInfo("Settting focus to " + sWindowsTitle + " Window and executing the keys " + sKeys);
//		run("\"" + sToolName + "\"" + " " + "\"" + sWindowsTitle + "\""  + " " + "\"" + sKeys + "\"", null);
//		waitForFlagFile();
//	}
//
//	
//	/** 
//	*  Uses AutoIt executable to set focus on a window and input keys
//	* @param  String sWindowsTitle -  Window title to set focus to
//	* @param  String sKeys -  Keystrokes to do
//	* @return   	
//	*/
//
//	public boolean setFocusDoKeys (String sWindowsTitle, String sKeys)
//	{
//		//modified to new CSDL way
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\UITools";
//		String sToolName = gsToolsPath + "\\SetFocusDoKeys.exe";
//		String sCMD="\"" + sToolName + "\" \"" + sWindowsTitle + "\" \"" + sKeys + "\"";
//		
//		VisualReporter.logScriptInfo("Settting focus to " + sWindowsTitle + " Window and executing the keys " + sKeys);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//			
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Setting focus for keystrokes executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error Reason: "+resultStr[1]);
//		return false;
//	}
//	
//	/** 
//	*  Uses AutoIt executable to set focus on a window and input keys
//	* @param  String sWindowsTitle -  Window title to set focus to
//	* @param  String sKeys -  Keystrokes to do
//	* @return   	
//	*/
//
//	public boolean setFocusClickButton (String sWindowsTitle, String sButton)
//	{
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\UITools";
//		String sToolName = gsToolsPath + "\\ClickButton.exe";
//
//		String sCMD="\"" + sToolName  + "\" \"" + sWindowsTitle + "\" \"" + sButton + "\"";
//		VisualReporter.logScriptInfo("Click button "+sButton+" in window "+sWindowsTitle);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}
//		catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) 
//			return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Clicking button executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) 
//			return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error reason:  "+resultStr[1]);
//		return false;
//	}
//	
//	 
//
//	/**
//	 * /**
//	 * set preferred language of IE in background. Now support XP/2000, and not for windows 95/98/Me
//	 * @param targetLang - string like zh-CN, en-US
//	 */
//	public void setIE6Language(String targetLang){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\UITools";
//		String sToolName = gsToolsPath + "\\SetIEPreferredLang.exe";
//		String sCMD="\"" + sToolName  + "\""+ " " + "\"" + targetLang + "\"" ;
//		if( !(new java.io.File(sToolName)).exists() )
//		{
//			Logfuncs.errorHandler("could not find tool: " + sToolName);
//			return;
//		}
//		VisualReporter.logScriptInfo("Set IE's language to : "+targetLang);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return ;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Seting preferred IE language executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) {
//			VisualReporter.logScriptInfo("Preferred IE language is set to: "+targetLang);
//		}else{
//			VisualReporter.logScriptInfo("Error Code:  "+result+", Error Reason: "+resultStr[1]);
//		}
//	}
//	/**
//	 * set preferred language of firefox in background. Now support XP/2000, and not for windows 95/98/Me
//	 * @param targetLang - string like zh-CN, en-US
//	 */
//	public void setFFLanguage(String targetLang){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\UITools";
//		String sToolName = gsToolsPath + "\\SetFFPreferredLang.exe";
//		String sCMD="\"" + sToolName  + "\""+ " " + "\"" + targetLang + "\"" ;
//		if( !(new java.io.File(sToolName)).exists() )
//		{
//			Logfuncs.errorHandler("could not find tool: " + sToolName);
//			return;
//		}
//		VisualReporter.logScriptInfo("Set FF's language to : "+targetLang);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return ;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Seting preferred FF language executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) {
//			VisualReporter.logScriptInfo("Preferred FF language is set to: "+targetLang);
//		}else{
//			VisualReporter.logScriptInfo("Error Code:  "+result+", Error Reason: "+resultStr[1]);
//		}
//	}
//
//	/*--------------------------------------------------------------------------------
//	 * liujt, Functions added for installing DI connector  
//	*/
//	/**
//	 * save file from web pages in IE to local root drive c:\.
//	 * For example, when exporting a template, a zip file named "xxx.zip" will be save to c:\ 
//	 * @param fileName - filename with extention
//	 * @return true for saving successfully,otherwise, return false;
//	 */
//	public boolean saveFileToDisk(String fileName){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\UITools";
//		String sToolName = gsToolsPath + "\\SaveFileToC.exe";
//		String sCMD="\"" + sToolName  + "\""+ " " + "\"" + fileName + "\"" ;
//		if( !(new java.io.File(sToolName)).exists() )
//		{
//			Logfuncs.errorHandler("could not find tool: " + sToolName);
//			return false;
//		}
//		VisualReporter.logScriptInfo("Save file: "+fileName+" in c:\\");
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//			
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Saving file executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error Reason: "+resultStr[1]);
//		return false;
//	}
//	/**
//	 * @param String setupFileName - file name you want to save
//	 * @return boolean - true for success, and false for failure
//	 */
//	public boolean saveDISetupFile(String setupFileName){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\SaveDISetupFile.exe";
//		String sCMD="\"" + sToolName  + "\""+ " " + "\"" + setupFileName + "\"" ;
//		VisualReporter.logScriptInfo("Save install file: "+setupFileName+" in c:\\");
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//			
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Saving install file executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error Reason: "+resultStr[1]);
//		return false;
//	}
//	/**
//	 * @param String setupFileName - file name you want to save
//	 * @return boolean - true for success, and false for failure
//	 */
//	//same as above but for 85, alhough truth  be told this might an IE8 issue
//	public boolean saveDISetupFile85(String setupFileName){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\SaveDISetupFile85.exe";
//		String sCMD="\"" + sToolName  + "\""+ " " + "\"" + setupFileName + "\"" ;
//		VisualReporter.logScriptInfo("Save install file: "+setupFileName+" in c:\\");
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//			
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Saving install file executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error Reason: "+resultStr[1]);
//		return false;
//	}
//	/**
//	 * @param String setupFileName - file name you want to save
//	 * @return boolean - true for success, and false for failure
//	 */
//	public boolean saveDISetupFileFF(String setupFileName){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\SaveDISetupFileFF.exe";
//		String sCMD="\"" + sToolName  + "\""+ " " + "\"" + setupFileName + "\"" ;
//		VisualReporter.logScriptInfo("Save install file: "+setupFileName+" in c:\\");
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//			
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Saving install file executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error Reason: "+resultStr[1]);
//		return false;
//	}
//	/**
//	 * uninstall DI connector in silent mode
//	 * @param String qkrconnExe - setup file name
//	 * @return boolean - true for success, and false for failure
//	 */
//	public boolean uninstallDIConnByExe(String qkrconnExe){
//		String gsToolsPath = "c:\\"+qkrconnExe;
//		
//		if( !(new java.io.File(gsToolsPath)).exists() )
//		{
//			Logfuncs.errorHandler("could not find tool: " + gsToolsPath);
//			return false;
//		}
//		String sToolName = gsToolsPath+" /uninstall /norestart"; 
//		String sCMD="cmd /c start "+ sToolName;
//		
//		VisualReporter.logScriptInfo("Uninstall DI connector by "+sCMD);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//			gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//			sToolName = gsToolsPath + "\\UninstallDIConnector.exe";
//			sCMD="\"" + sToolName  + "\"";
//			if( !(new java.io.File(sToolName)).exists() )
//			{
//				Logfuncs.errorHandler("could not find tool: " + sToolName);
//				return false;
//			}
//			p = Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//			if (resultStr!=null){
//			int result=Integer.valueOf(resultStr[0]).intValue();
//			VisualReporter.logScriptInfo("Uninstall Lotus Quickr Connector executes  "+(result==0? "Successfully":"Failed"));
//			if (result!=0){
//				VisualReporter.logScriptInfo("Error Code:  "+result+", Error Reason: "+resultStr[1]);
//				sleep(VisualReporter.giVisualReporterWaitTO);
//			}
//		}
//		return checkDIConnExist();
//	}
//	
//	/**
//	 * uninstall DI connector in silent mode
//	 * @param String qkrconnExe - setup file name
//	 * @return boolean - true for success, and false for failure
//	 */
//	public boolean uninstallDIConnByExe2(String qkrconnExe){
//		String gsToolsPath = "c:\\"+qkrconnExe;
//		
//		if( !(new java.io.File(gsToolsPath)).exists() )
//		{
//			Logfuncs.errorHandler("could not find tool: " + gsToolsPath);
//			return false;
//		}
//		String sToolName = gsToolsPath+" /uninstall /quiet /norestart"; 
//		String sCMD="cmd /c start "+ sToolName;
//		
//		VisualReporter.logScriptInfo("Uninstall DI connector by "+sCMD);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//			//the wait for doesn't work as this is not an autoit thing
//			sleep(VisualReporter.giVisualReporterMedTO);
//			//only wait so long before giving up
//			int iX=0;
//			while(checkDIConnExist()&iX<30)
//			{
//				iX++;
//				sleep(VisualReporter.giVisualReporterShortTO);
//			}
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//	
//		return checkDIConnExist();
//	}
//	
//	/**
//	 * uninstall DI connector via executing msiexec.exe
//	 * @param String productID - productID of Lotus Quickr Connector
//	 * @return true for uninstall successfully, false if failure
//	 * @author liujunt
//	 */
//	public boolean uninstallDIConnByMSI(String productID){
//		
//		java.lang.Process p;
//		try{
//			gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//			String sToolName = gsToolsPath + "\\UninstallDIConnector_E.exe";
//			String sCMD="\"" + sToolName  + "\""+ " " + "\"" + productID + "\"" ;
//			VisualReporter.logScriptInfo("Uninstall DI connector by "+sCMD);
//			if( !(new java.io.File(sToolName)).exists() )
//			{
//				Logfuncs.errorHandler("could not find tool: " + sToolName);
//				return false;
//			}
//			p = Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//			if (resultStr!=null){
//			int result=Integer.valueOf(resultStr[0]).intValue();
//			VisualReporter.logScriptInfo("Uninstall Lotus Quickr Connector executes  "+(result==0? "Successfully":"Failed"));
//			if (result!=0){
//				VisualReporter.logScriptInfo("Error Code:  "+result+", Error Reason: "+resultStr[1]);
//				sleep(VisualReporter.giVisualReporterWaitTO);
//			}
//		}
//		return checkDIConnExist();
//	}
//	
//	/**
//	 * install DI connector by silent mode
//	 * @param String qkrconnExe - uninstall file name
//	 * @return boolean - true for success, and false for failure
//	 */
//	public boolean installDIConnByExe(String qkrconnExe){
//		String gsToolsPath = "c:\\"+qkrconnExe;
//		if( !(new java.io.File(gsToolsPath)).exists() )
//		{
//			Logfuncs.errorHandler("could not find tool: " + gsToolsPath);
//			return false;
//		}
//		String sToolName = gsToolsPath+" /install /norestart ADDLOCAL=WindowsExplorer,MicrosoftOffice"; 
//		String sCMD="cmd /c start "+ sToolName;
//		VisualReporter.logScriptInfo("Install DI connector by "+sCMD);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//			gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//			sToolName = gsToolsPath + "\\InstallDIConnector.exe";
//			sCMD="\"" + sToolName  + "\"";
//			if( !(new java.io.File(sToolName)).exists() )
//			{
//				Logfuncs.errorHandler("could not find tool: " + sToolName);
//				return false;
//			}
//			p = Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr!=null){
//			int result=Integer.valueOf(resultStr[0]).intValue();
//			VisualReporter.logScriptInfo("Install Lotus Quickr Connector executes  "+(result==0? "Successfully":"Failed"));
//			if (result!=0){
//				VisualReporter.logScriptInfo("Error Code:  "+result+", Error Reason: "+resultStr[1]);
//				sleep(VisualReporter.giVisualReporterWaitTO);
//			}
//		}
//		return checkDIConnExist();
//	}
//	
//	/**
//	 * install DI connector by silent mode
//	 * @param String qkrconnExe - uninstall file name
//	 * @return boolean - true for success, and false for failure
//	 */
//	public boolean installDIConnByExe2(String qkrconnExe){
//		String gsToolsPath = "c:\\"+qkrconnExe;
//		if( !(new java.io.File(gsToolsPath)).exists() )
//		{
//			Logfuncs.errorHandler("could not find tool: " + gsToolsPath);
//			return false;
//		}
//		String sToolName = gsToolsPath+" /install /quiet /norestart ADDLOCAL=WindowsExplorer,MicrosoftOffice,MicrosoftOutlook"; 
//		String sCMD="cmd /c start "+ sToolName;
//		VisualReporter.logScriptInfo("Install DI connector by "+sCMD);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//			//the wait for doesn't work as this is not an autoit thing
//			sleep(VisualReporter.giVisualReporterMedTO);
//			//only wait so long before giving up
//			int iX=0;
//			while(!checkDIConnExist()&&iX<30)
//			{
//				iX++;
//				sleep(VisualReporter.giVisualReporterShortTO);
//			}
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		return checkDIConnExist();
//	}	
//
//	/**
//	 * Check whether DI connector has been installed on local machine
//	 * @return true for existed and false for not existed
//	 */
//	public boolean checkDIConnExist(){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\CheckDIConn.exe";
//		String sCMD="\"" + sToolName  + "\"" ;
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("DI connector has "+(result==0? " ":"not ")+"installed");
//		return (result==0);
//	}
//	/**
//	 * check whether process can exit in the given timeouts
//	 * @param String processName - process name needed to check
//	 * @param int timeLimit - specify how long to wait(in seconds)
//	 * @return true : process exits in the given timeout;
//	 * 		   false: process has been termitated after time out
//	 */
//	public boolean processEnded(String processName,int timeLimit){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\ProcessEnded.exe";
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + processName + "\""+ " " + "\"" + String.valueOf(timeLimit) + "\"";
//		VisualReporter.logScriptInfo("Check process "+processName+ " ended or not in " + timeLimit+" seconds");
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Process "+processName+ ((result==0)? "has ended!":"has been closed forcely!"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error Reason: "+resultStr[1]);
//		return false;
//	}
//	/**
//	 * check whether process can exit
//	 * @param String processName - process name needed to check
//	 * @return true for exist, and false for exits by forcely terminated
//	 */
//	public boolean processEnded(String processName){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\ProcessEnded.exe";
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + processName + "\"";
//		VisualReporter.logScriptInfo("Check process "+processName+ " ended or not ");
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Process "+processName+ ((result==0)? "has ended!":"has been closed forcely!"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error Reason: "+resultStr[1]);
//		return false;
//	}
//	
//	/*--------------------------------------------------------------------------------
//	 * liujt
//	 * Functions added for test windows explorer about DI  
//	*/	
//	
//	/**
//	 * Closing any office windows left behind from previous scripts.
//	 *
//	 */
//	public boolean cleanupOfficeWindows()
//	{
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\Conn811\\CloseOfficeFile.exe";
//		if (gsConnVersion.equalsIgnoreCase("850"))
//		{sToolName = gsToolsPath + "\\Conn85\\CloseOfficeFile.exe";}
//		String sCMD="\"" + sToolName + "\"";
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//			String[] resultStr=getResultString();
//			if (resultStr==null) return false;
//			int result=Integer.valueOf(resultStr[0]).intValue();
//			VisualReporter.logScriptInfo("Close office windows "+(result==0 ? "Successfully":"Failed"));
//			if (result==0) return true;
//			VisualReporter.logScriptInfo("Error Code:  "+result+", Error Reason: "+resultStr[1]);
//		}
//		catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		return false;
//
//	}
//	
//	/**
//	 * use autoit to add libraries to local window desktop
//	 * @param String serverURL - the url of quickr server
//	 * @param String userName - the user who will login the quickr server
//	 * @param String userPWD - the user's password 
//	 * @param boolean rememberPWDOrNot - remember userPWD or not
//	 * @param String[] libraries - the list of libraries will be added to local windows
//	 * @return boolean - true for success, false for failure
//	 */
//	public boolean _addPlaces_DI(String serverURL, String userName, String userPWD, boolean rememberPWDOrNot, String[] libraries){
//		if (libraries==null || serverURL==null){
//			VisualReporter.logScriptInfo("addPlaces_DI() method got a null parameter!");
//			return false;
//		}
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\AddPlaces_DI.exe";
//		String rememberStr=rememberPWDOrNot? "True":"False";
//		StringBuffer libStr=new StringBuffer(libraries[0]);
//		for (int i=1;i<libraries.length;i++)
//			libStr.append(":").append(libraries[i]);
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + serverURL + "\""+ " " + "\"" + userName + "\""+ " " + "\"" + userPWD + "\""+ " " + "\"" + rememberStr + "\""+ " " + "\"" + libStr.toString() + "\"";
//		VisualReporter.logScriptInfo("Add libraries: "+libStr.toString()+" from server "+serverURL+ " with user: " + userName + " and password: " + userPWD);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//			String[] resultStr=getResultString();
//			if (resultStr==null) return false;
//			int result=Integer.valueOf(resultStr[0]).intValue();
//			VisualReporter.logScriptInfo("Adding places  executes  "+(result==0 ? "Successfully":"Failed"));
//			if (result==0) return true;
//			VisualReporter.logScriptInfo("Error Code:  "+result+", Error Reason: "+resultStr[1]);
//		}
//		catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		return false;
//	}
//	/**
//	 * use autoit to add libraries to local window desktop
//	 * @param String serverURL - the url of quickr server
//	 * @param String userName - the user who will login the quickr server
//	 * @param String userPWD - the user's password 
//	 * @param boolean rememberPWDOrNot - remember userPWD or not
//	 * @param String[] libraries - the list of libraries will be added to local windows
//	 * @return boolean - true for success, false for failure
//	 */
//	public boolean _addPlace_DI(String serverURL, String userName, String userPWD, boolean rememberPWDOrNot, String library){
//		if (library==null || serverURL==null){
//			VisualReporter.logScriptInfo("addPlace_DI() method got a null parameter!");
//			return false;
//		}
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\AddPlace_DI.exe";
//		if (gsConnVersion.equalsIgnoreCase("811"))
//			{sToolName = gsToolsPath + "\\Conn811\\AddPlace_DI.exe";}
//		if (gsConnVersion.equalsIgnoreCase("850"))
//		{sToolName = gsToolsPath + "\\Conn85\\AddPlace_DI.exe";}		
//		
//		String rememberStr=rememberPWDOrNot? "True":"False";
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + serverURL + "\""+ " " + "\"" + userName + "\""+ " " + "\"" + userPWD + "\""+ " " + "\"" + rememberStr + "\""+ " " + "\"" + library + "\"";
//		VisualReporter.logScriptInfo("Add library: "+library+" from server "+serverURL+ " with user: " + userName + " and password: " + userPWD);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//			String[] resultStr=getResultString();
//			if (resultStr==null) return false;
//			int result=Integer.valueOf(resultStr[0]).intValue();
//			VisualReporter.logScriptInfo("Adding place executes  "+(result==0 ? "Successfully":"Failed"));
//			if (result==0) return true;
//			VisualReporter.logScriptInfo("Error Code:  "+result+", Error Reason: "+resultStr[1]);
//		}
//		catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		return false;
//	}
//	
//	/**
//	 * use autoit to add entry place to local window desktop
//	 * @param String serverURL - the url of quickr server
//	 * @param String userName - the user who will login the quickr server
//	 * @param String userPWD - the user's password 
//	 * @param boolean rememberPWDOrNot - remember userPWD or not
//	 * @param String[] libraries - the list of libraries will be added to local windows
//	 * @return boolean - true for success, false for failure
//	 */
//	public boolean _addEntryPlace_DI(String serverURL, String userName, String userPWD, boolean rememberPWDOrNot, String library){
//		if (library==null || serverURL==null){
//			VisualReporter.logScriptInfo("_addEntryPlace_DI() method got a null parameter!");
//			return false;
//		}
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\AddEntryPlace_DI.exe";
//		if (gsConnVersion.equalsIgnoreCase("811"))
//			{sToolName = gsToolsPath + "\\Conn811\\AddEntryPlace_DI.exe";}
//		if (gsConnVersion.equalsIgnoreCase("850"))
//		{sToolName = gsToolsPath + "\\Conn85\\AddEntryPlace_DI.exe";}
//		String rememberStr=rememberPWDOrNot? "True":"False";
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + serverURL + "\""+ " " + "\"" + userName + "\""+ " " + "\"" + userPWD + "\""+ " " + "\"" + rememberStr + "\""+ " " + "\"" + library + "\"";
//		System.out.println(sCMD);
//		VisualReporter.logScriptInfo("Add library: "+library+" from server "+serverURL+ " with user: " + userName + " and password: " + userPWD);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//			String[] resultStr=getResultString();
//			if (resultStr==null) return false;
//			int result=Integer.valueOf(resultStr[0]).intValue();
//			VisualReporter.logScriptInfo("Adding place executes  "+(result==0 ? "Successfully":"Failed"));
//			if (result==0) return true;
//			VisualReporter.logScriptInfo("Error Code:  "+result+", Error Reason: "+resultStr[1]);
//		}
//		catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		return false;
//	}
//	/**
//	 * use autoit to add libraries to MS Outlook
//	 * @param String serverURL - the url of quickr server
//	 * @param String userName - the user who will login the quickr server
//	 * @param String userPWD - the user's password 
//	 * @param boolean rememberPWDOrNot - remember userPWD or not
//	 * @param String[] libraries - the list of libraries will be added to local windows
//	 * @return boolean - true for success, false for failure
//	 */
//	public boolean _addPlace_Outlook(String serverURL, String userName, String userPWD, boolean rememberPWDOrNot, String library){
//		if (library==null || serverURL==null){
//			VisualReporter.logScriptInfo("addPlace_Outlook() method got a null parameter!");
//			return false;
//		}
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\Conn811\\OutlookPaneAddPlace.exe";
//		if (gsConnVersion.equalsIgnoreCase("850"))
//		{sToolName = gsToolsPath + "\\Conn85\\OutlookPaneAddPlace.exe";}
//		String rememberStr=rememberPWDOrNot? "True":"False";
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + serverURL + "\""+ " " + "\"" + userName + "\""+ " " + "\"" + userPWD + "\""+ " " + "\"" + rememberStr + "\""+ " " + "\"" + library + "\"";
//		VisualReporter.logScriptInfo("Add library: "+library+" from server "+serverURL+ " with user: " + userName + " and password: " + userPWD);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//			String[] resultStr=getResultString();
//			if (resultStr==null) return false;
//			int result=Integer.valueOf(resultStr[0]).intValue();
//			VisualReporter.logScriptInfo("Adding place executes  "+(result==0 ? "Successfully":"Failed"));
//			if (result==0) return true;
//			VisualReporter.logScriptInfo("Error Code:  "+result+", Error Reason: "+resultStr[1]);
//		}
//		catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		return false;
//	}
//	/**
//	 * add places created from server to local window system.
//	 * @param String serverURL - url of quickr server
//	 * @param String user     - user name for logining quickr server
//	 * @param String password - user password for logining quickr server
//	 * @param boolean rememberOrNot - remember password or not
//	 * @param String libList - libraries you want to added to local DI
//	 * @return boolean - true for success, and false for failure
//	 */
//	public boolean addPlacesInDI(String serverURL, String user, String password, boolean rememberOrNot, String[] libList)
//	{
//		//if 811 then reset default string
//		if (gsConnVersion.equalsIgnoreCase("811"))
//			gsLotusQuickr=gsLotusQuickr811;
//		//if 850, the same as 811
//		if (gsConnVersion.equalsIgnoreCase("850"))
//			gsLotusQuickr=gsLotusQuickr811;
//		String rootTitle=openFolder(gsLotusQuickr);
//		if (rootTitle==null) return false;
//		boolean result=_addPlaces_DI(serverURL, user, password, rememberOrNot, libList);
//		closeFolder(rootTitle);
//		return result;
//	}
//	/**
//	 * add places created from server to local window system.
//	 * @param String serverURL - url of quickr server
//	 * @param String user     - user name for logining quickr server
//	 * @param String password - user password for logining quickr server
//	 * @param boolean rememberOrNot - remember password or not
//	 * @param String libList - libraries you want to added to local DI
//	 * @return boolean - true for success, and false for failure
//	 */
//	public boolean addPlaceInDI(String serverURL, String user, String password, boolean rememberOrNot, String lib)
//	{
//		//if 811 then reset default string
//		if (gsConnVersion.equalsIgnoreCase("811"))
//			gsLotusQuickr=gsLotusQuickr811;
//		//if 850, the same as 811
//		if (gsConnVersion.equalsIgnoreCase("850"))
//			gsLotusQuickr=gsLotusQuickr811;
//		String rootTitle=openFolder(gsLotusQuickr);
//		if (rootTitle==null) return false;
//		boolean result=_addPlace_DI(serverURL, user, password, rememberOrNot, lib);
//		closeFolder(rootTitle);
//		return result;
//	}
//	
//	/**
//	 * add places created from server to local window system by place type
//	 * @param placetype - 1:Team Places ;2:Personal Files
//	 * @param String serverURL - url of quickr server
//	 * @param String user     - user name for logining quickr server
//	 * @param String password - user password for logining quickr server
//	 * @param boolean rememberOrNot - remember password or not
//	 * @param String libList - libraries you want to added to local DI
//	 * @return boolean - true for success, and false for failure
//	 */
//	public boolean addPlaceInDI(int placetype, String serverURL, String user, String password, boolean rememberOrNot, String lib)
//	{
//		//if 811 then reset default string
//		String rootTitle=null;
//		
//		boolean result=false;
//		//add team places
//		if(placetype==1){
//			if (gsConnVersion.equalsIgnoreCase("811"))
//				gsLotusQuickr=gsLotusQuickr811;
//			//if 850, the same as 811
//			if (gsConnVersion.equalsIgnoreCase("850"))
//				gsLotusQuickr=gsLotusQuickr811;
//			rootTitle=openFolder(gsLotusQuickr);
//			if (rootTitle==null) 
//			{
//				closeFolder(rootTitle);
//				return false;
//				}
//			result=_addPlace_DI(serverURL, user, password, rememberOrNot, lib);
//		}
//		//add personal files place
//		if(placetype==2){
//			gsLotusQuickr=gsLotusQuickrEntry811;
//			rootTitle=openFolder(gsLotusQuickr);
//			if (rootTitle==null) 
//				{
//				closeFolder(rootTitle);
//				return false;
//				}
//			result=_addEntryPlace_DI(serverURL, user, password, rememberOrNot, lib);
//		}
//		
//		closeFolder(rootTitle);
//		return result;
//	}
//	/**
//	 * Insert a attachment file to an email in already open Outlook
//	 * @param String FilePath - the file path to insert 
// 
//	 */
//	public boolean InsertAttachToOutlook(String FilePath){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\Conn811\\InsertOutlookAtt.exe";
//		if (gsConnVersion.equalsIgnoreCase("850"))
//		{sToolName = gsToolsPath + "\\Conn85\\InsertOutlookAtt.exe";}		
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + FilePath + "\"";
//
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Insert File "+(result==0 ? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+" Error Reason: "+resultStr[1]);
//		return false;
//	}
//	
//	/**
//	 * Composes an email in already open Outlook
//	 * @param String sTo - the recipient of the email
//	 * @param String sSubject - the subject of the email
//	 * @param String sText - the text of the email
//	 * @return boolean - true for successful closing and false for any failture 
//	 */
//	public boolean composeOutlookEmail(String sTo, String sSubject, String sText){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\Conn811\\ComposeOutlookMail.exe";
//		if (gsConnVersion.equalsIgnoreCase("850"))
//		{sToolName = gsToolsPath + "\\Conn85\\ComposeOutlookMail.exe";}		
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + sTo + "\"" + " " + "\"" + sSubject + "\"" + " " + "\"" + sText + "\"";
//		VisualReporter.logScriptInfo("composing mail to "+sTo);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Composing email "+(result==0 ? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+" Error Reason: "+resultStr[1]);
//		return false;
//	}
//	
//	/**
//	 * Displays the Quickr Panel in Outtlok
//	 * @return boolean - true for successful closing and false for any failture 
//	 */
//	public boolean showQuickrPaneOutlook(){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\Conn811\\ShowQuickrPaneOutlook.exe";
//		if (gsConnVersion.equalsIgnoreCase("850"))
//		{sToolName = gsToolsPath + "\\Conn85\\ShowQuickrPaneOutlook.exe";}
//		String sCMD="\"" + sToolName  + "\"";
//		VisualReporter.logScriptInfo("Displaying Quickr Pane");
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Displaying Quickr Pane"+(result==0 ? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+" Error Reason: "+resultStr[1]);
//		return false;
//	}	
//	/**
//	 * Displays or not display the Quickr Panel in Outtlook
//	 * @param boolean sWantVisible. want to display quickr panel or not
//	 * @return boolean - true for successful closing and false for any failture 
//	 */
//	public boolean showQuickrPaneOutlook(boolean sWantVisible){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\Conn811\\ShowQuickrPaneOutlook.exe";
//		if (gsConnVersion.equalsIgnoreCase("850"))
//		{sToolName = gsToolsPath + "\\Conn85\\ShowQuickrPaneOutlook.exe";}
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + sWantVisible + "\"";
//		VisualReporter.logScriptInfo("Displaying Quickr Pane");
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Displaying Quickr Pane"+(result==0 ? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+" Error Reason: "+resultStr[1]);
//		return false;
//	}
//	/**
//	 * Creates a link in an Outlook Email
//	 * @param String sParentPath - the Parent Path
//	 * @param String sFileName - the subject of the email
//	 * @param String sTabs - the text of the email
//	 * @return boolean - true for successful closing and false for any failture 
//	 */
//	public boolean createQuickrLinkOutlook(String sParentPath, String sFileName, String sTabs){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\Conn811\\CreateLinkInOutlook.exe";
//		if (gsConnVersion.equalsIgnoreCase("850"))
//		{sToolName = gsToolsPath + "\\Conn85\\CreateLinkInOutlook.exe";}
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + sParentPath + "\"" + " " + "\"" + sFileName + "\"" + " " + "\"" + sTabs + "\"";
//		VisualReporter.logScriptInfo("inserting link to quickr doc "+sFileName);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Creating Link "+(result==0 ? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+" Error Reason: "+resultStr[1]);
//		return false;
//	}
//	/**
//	 * add places created from server to MS Outlook
//	 * @param String serverURL - url of quickr server
//	 * @param String user     - user name for logining quickr server
//	 * @param String password - user password for logining quickr server
//	 * @param boolean rememberOrNot - remember password or not
//	 * @param String libList - libraries you want to added to local DI
//	 * @return boolean - true for success, and false for failure
//	 */
//	public boolean addPlaceInOutlook(String serverURL, String user, String password, boolean rememberOrNot, String lib)
//	{
//		//if 811 then reset default string
//		if (gsConnVersion.equalsIgnoreCase("811"))
//			gsLotusQuickr=gsLotusQuickr811;
//		//if 850 the same as 811
//		if (gsConnVersion.equalsIgnoreCase("850"))
//			gsLotusQuickr=gsLotusQuickr811;
//		boolean result=_addPlace_Outlook(serverURL, user, password, rememberOrNot, lib);
//		return result;
//	}
//	/**
//	 * publish the given file
//	 * @param String parentFolderTitle - the title of the folder which the file is located!
//	 * @param String fileName - the name of the file will be published
//	 * @return boolean -ture for success, and false for failure
//	 */
//	public boolean _publishDraft_DI(String parentFolderTitle, String fileName){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\PublishDraft_DI.exe";
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + parentFolderTitle + "\""+ " " + "\"" + fileName + "\"";
//		VisualReporter.logScriptInfo("Publish file "+fileName+ " from " + parentFolderTitle);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Publishing Draft executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error Reason: "+resultStr[1]);
//		return false;
//	}
//	/**
//	 * approve the given file
//	 * @param String parentFolderTitle - the title of the folder which the file is located!
//	 * @param String fileName - the name of the file will be approved
//	 * @return boolean -ture for success, and false for failure
//	 */
//	public boolean _approveFile_DI(String parentFolderTitle, String fileName){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\ApproveFile_DI.exe";
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + parentFolderTitle + "\""+ " " + "\"" + fileName + "\"";
//		VisualReporter.logScriptInfo("Approve file "+fileName+ " from " + parentFolderTitle);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Approving file executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error Reason: "+resultStr[1]);
//		return false;
//	}
//	/**
//	 * approve specified file in specified folder in DI
//	 * @param String parentFolderPath - absolute path of file you want to approve (not including filename)
//	 * @param String fileName - name of file you want to approve
//	 * @return boolean - true for success, and false for failure
//	 */
//	public boolean approveFileInDI(String parentFolderPath,String fileName){
//		String title=openFolder(parentFolderPath);
//		if (title==null) return false;
//		boolean result=_approveFile_DI(title,fileName);
//		closeFolder(title);
//		return result;
//	}
//	/**
//	 * cancel check out the specified file
//	 * @param String parentFolderTitle - the title of folder which the file is located!
//	 * @param String fileName - the file which will be canceled checking out
//	 * @return boolean - true for success and false for failure
//	 */
//	public boolean _cancelCheckOut_DI(String parentFolderTitle, String fileName){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\CancelCheckOut_DI.exe";
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + parentFolderTitle + "\""+ " " + "\"" + fileName + "\"";
//		VisualReporter.logScriptInfo("Cancle checking-out file "+fileName+ " from " + parentFolderTitle);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}
//		catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Canceling to check-out file executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error Reason: "+resultStr[1]);
//		return false;
//	}
//	/**
//	 * cancel checked-out file in the specified path
//	 * @param String parentFolderPath - absolute path of file you want to cancel checking out(not including filename) 
//	 * @param String fileName - name of file 
//	 * @return boolean - true for success, and false for failure
//	 */
//	public boolean cancelCheckOutInDI(String parentFolderPath,String fileName){
//		String title=openFolder(parentFolderPath);
//		if (title==null) return false;
//		boolean result=_cancelCheckOut_DI(title,fileName);
//		closeFolder(title);
//		return result;
//	}
//	/**
//	 * check in the specified file 
//	 * @param String parentFolderTitle - the folder which the file is located
//	 * @param String fileName - the file which will be located
//	 * @return boolean - true for success, and false for failure
//	 */
//	public boolean _checkIn_DI(String parentFolderTitle, String fileName){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\CheckIn_DI.exe";
//		if (gsConnVersion.equalsIgnoreCase("811"))
//			{sToolName = gsToolsPath + "\\Conn811\\CheckIn_DI.exe";}
//		if (gsConnVersion.equalsIgnoreCase("850"))
//		{sToolName = gsToolsPath + "\\Conn85\\CheckIn_DI.exe";}
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + parentFolderTitle + "\""+ " " + "\"" + fileName + "\"";
//		VisualReporter.logScriptInfo("CheckIn file "+fileName+ " from " + parentFolderTitle);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}
//		catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Checking In file executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error Reason: "+resultStr[1]);
//		return false;
//	}
//	/**
//	 * checkin file into quickr server
//	 * @param String parentFolderPath - absolute path of file(not including file name) 
//	 * @param String fileName - name of file 
//	 * @return boolean -true for success, and false for failure
//	 */
//	public boolean checkInDI(String parentFolderPath,String fileName){
//		String title=openFolder(parentFolderPath);
//		sleep(VisualReporter.giVisualReporterPause1TO);
//		if (title==null) return false;
//		boolean result=_checkIn_DI(title,fileName);
//		sleep(VisualReporter.giVisualReporterPause1TO);
//		closeFolder(title);
//		return result;
//	}
//	/**
//	 * check out the specified file
//	 * @param String parentFolderTitle - the folder which the file is located
//	 * @param String fileName - the file will be checked out
//	 * @return boolean - true for success and false for failure
//	 */
//	public boolean _checkOut_DI(String parentFolderTitle, String fileName){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\CheckOut_DI.exe";
//		if (gsConnVersion.equalsIgnoreCase("811"))
//			{sToolName = gsToolsPath + "\\Conn811\\CheckOut_DI.exe";}
//		if (gsConnVersion.equalsIgnoreCase("850"))
//		{sToolName = gsToolsPath + "\\Conn85\\CheckOut_DI.exe";}
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + parentFolderTitle + "\""+ " " + "\"" + fileName + "\"";
//		VisualReporter.logScriptInfo("Checkout file "+fileName+ " from " + parentFolderTitle);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Checking out file executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error Reason: "+resultStr[1]);
//		return false;
//	}
//	/**
//	 * check out specified file in the specified path
//	 * @param String parentFolderPath - absolute path of file you want to check out(not including filename)
//	 * @param String fileName - name of file
//	 * @return boolean - true for success and false for failure
//	 */
//	public boolean checkOutInDI(String parentFolderPath,String fileName){
//		String title=openFolder(parentFolderPath);
//		sleep(VisualReporter.giVisualReporterPause1TO);
//		if (title==null) return false;
//		boolean result=_checkOut_DI(title,fileName);
//		closeFolder(title);
//		sleep(VisualReporter.giVisualReporterPause1TO);
//		return result;
//	}
//	/**
//	 * close the given opened folder
//	 * @param String folderName - the folder will be closed
//	 * @return boolean -true for success and false for failure
//	 */
//	public boolean closeFolder_1(String folderName){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\Conn811\\CloseFolder.exe";
//		if (gsConnVersion.equalsIgnoreCase("850"))
//		{sToolName = gsToolsPath + "\\Conn85\\CloseFolder.exe";}
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + folderName + "\"";
//		VisualReporter.logScriptInfo("Close folder "+folderName);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();;
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Closing executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error reason:  "+resultStr[1]);
//		return false;
//	}
//	/**
//	 * close the given opened folder
//	 * @param String folderName - the folder will be closed
//	 * @return boolean -true for success and false for failure
//	 */
//	public boolean closeFolder(String folderName){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\CloseFolder.exe";
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + folderName + "\"";
//		VisualReporter.logScriptInfo("Close folder "+folderName);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();;
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Closing executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error reason:  "+resultStr[1]);
//		return false;
//	}
///**
// * get the url link of the given file 
// * @param String parentFolderTitle - the folder which the file is located
// * @param Stromg fileName - the file whose url will be got 
// * @return String -the url string if success and null if failure exists
// */
//	public String copyFileLink_DI(String parentFolderTitle, String fileName){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\CopyFileLink_DI.exe";
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + parentFolderTitle + "\""+ " " + "\"" + fileName + "\"";
//		VisualReporter.logScriptInfo("Copy link of file "+fileName+ " from " + parentFolderTitle);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}
//		catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return null;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Copying file link executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) return resultStr[1];
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error reason:  "+resultStr[1]);
//		return null;
//	}
//	/**
//	 * Paste to a Folder 
//	 * @param Stromg folderName - the file whose url will be got 
//	 * @return String -success or fail
//	 */
//		public String pasteInOutlook(String folderName,  boolean bCheckIn){
//			String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//			String sToolName = gsToolsPath + "\\Conn811\\PasteInOutlook.exe";
//			if (gsConnVersion.equalsIgnoreCase("850"))
//			{sToolName = gsToolsPath + "\\Conn85\\PasteInOutlook.exe";}
//			String checkinStr=bCheckIn? "True":"False";
//			String sCMD="\"" + sToolName  + "\"" + " " + "\""  + folderName + "\"" + " " + "\""  + checkinStr + "\"";
//			VisualReporter.logScriptInfo("Copy link of file "+folderName);
//			java.lang.Process p;
//			try{
//				p= Runtime.getRuntime().exec(sCMD);
//				p.waitFor();
//			}
//			catch(Exception e){
//				VisualReporter.logScriptInfo(e.toString());
//			}
//			String[] resultStr=getResultString();
//			if (resultStr==null) return null;
//			int result=Integer.valueOf(resultStr[0]).intValue();
//			VisualReporter.logScriptInfo("Pasting executes  "+(result==0? "Successfully":"Failed"));
//			if (result==0) return resultStr[1];
//			VisualReporter.logScriptInfo("Error Code:  "+result+", Error reason:  "+resultStr[1]);
//			return null;
//		}
//		
//		/**
//		 * Paste to a Folder 
//		 * @param Stromg folderName - the file whose url will be got 
//		 * @return boolean -true or false
//		 */
//			public boolean pasteToOutlook(String folderName,  boolean bCheckIn){
//				String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//				String sToolName = gsToolsPath + "\\Conn811\\PasteInOutlook.exe";
//				if (gsConnVersion.equalsIgnoreCase("850"))
//				{sToolName = gsToolsPath + "\\Conn85\\PasteInOutlook.exe";}
//				String checkinStr=bCheckIn? "True":"False";
//				String sCMD="\"" + sToolName  + "\"" + " " + "\""  + folderName + "\"" + " " + "\""  + checkinStr + "\"";
//				VisualReporter.logScriptInfo("Copy link of file "+folderName);
//				java.lang.Process p;
//				try{
//					p= Runtime.getRuntime().exec(sCMD);
//					p.waitFor();
//				}
//				catch(Exception e){
//					VisualReporter.logScriptInfo(e.toString());
//				}
//				
//				String[] resultStr=getResultString();
//				if (resultStr==null) return false;
//				int result=Integer.valueOf(resultStr[0]).intValue();
//				VisualReporter.logScriptInfo("Pasting executes  "+(result==0? "Successfully":"Failed"));
//				if (result==0) return true;
//				VisualReporter.logScriptInfo("Error Code:  "+result+", Error reason:  "+resultStr[1]);
//				return false;
//			}
//	/**
//	 * get file link of specified file in specified path
//	 * @param String parentFolderPath - absolute path of file you want to get url link
//	 * @param String fileName - name of file which you want to get its url
//	 * @return String - the url of file, maybe null
//	 */
//	public String getFileLink(String parentFolderPath,String fileName){
//			String currentTitle=openFolder(parentFolderPath);
//			if (currentTitle==null) 	return null;
//			String fileLink=copyFileLink_DI(currentTitle, fileName);
//			closeFolder(currentTitle);
//			return fileLink;
//		}
//	/**
//	 * copy the given files in the specified folder
//	 * @param String parentFolderTitle - the folder which all the given files are located
//	 * @param String[] fileList - the list of file name
//	 * @return  boolean - true for success, and false for failure
//	 */
//	public boolean _copyPasteFile(String parentFolderTitle, String[] fileList,String targetParentFolderTitle, String targetFolder){
//		if (fileList==null){
//			VisualReporter.logScriptInfo("copyPasteFile() method got a null parameter!");
//			return false;
//		}
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\CopyAndPaste.exe";
//		StringBuffer libStr=new StringBuffer(fileList[0]);
//		for (int i=1;i<fileList.length;i++)
//			libStr.append(":").append(fileList[i]);
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + parentFolderTitle + "\""+ " " + "\"" + libStr.toString() + "\""+ " " + "\"" + targetParentFolderTitle + "\""+ " " + "\"" + targetFolder + "\"";
//		VisualReporter.logScriptInfo("Copy files "+libStr.toString()+ " from " + parentFolderTitle +" to "+targetParentFolderTitle+"\\"+targetFolder);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}
//		catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Copying and pasting files executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error reason:  "+resultStr[1]);
//		return false;
//	}
//	/**
//	 * copy files and folders to another folder.
//	 * @param String srcParentFolderPath - the path of files and folders will be copyed 
//	 * @param String[] fileList - the list of folders and files
//	 * @param String targetParentFolderPath - the path of target folder
//	 * @param String targetFolderName - name of target folder 
//	 * @return boolean true for success and false for failure
//	 */
//	public boolean copyPasteFiles(String srcParentFolderPath, String[] fileList,String targetParentFolderPath, String targetFolderName){
//		String sourceTitle=openFolder(srcParentFolderPath);
//		if (sourceTitle==null)	return false;
//		setViewStyle(sourceTitle, "Details");
//		
//		String targetTitle=openFolder(targetParentFolderPath);
//		if (targetTitle==null )	return false;
//		setViewStyle(targetTitle, "Details");
//				
//		boolean pasteResult=_copyPasteFile(sourceTitle, fileList, targetTitle, targetFolderName);
//		closeFolder(sourceTitle);
//		closeFolder(targetTitle);
//		if (pasteResult) {
//			String title=openFolder(targetParentFolderPath+"\\"+targetFolderName);
//			if (title==null)  return false;
//			setViewStyle(title, "Details");
//			
//			String[] files=_getFileList(title);
//			closeFolder(title);
//			if (files==null) return false;
//			java.util.ArrayList listFiles=new ArrayList();
//			for(int i=0;i<files.length;i++){
//				listFiles.add(files[i].split(":")[0]);
//			}
//			for(int i=0;i<fileList.length;i++)
//				if (listFiles.contains(fileList[i])) {
//					return true;
//				}
//			return false;
//		}
//		return false;
//	}
//
//	/**
//	 * create a new folder in the specified folder
//	 * @param String parentFolderTitle - the folder which the new folder will be located
//	 * @param String folderName - the name of the new folder
//	 * @param String folderDescription - the description of the new folder
//	 * @return boolean -true for success and false for failing to create the folder
//	 */
//	public boolean _createFolder(String parentFolderTitle, String folderName, String folderDescription){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\CreateFolder.exe";
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + parentFolderTitle + "\""+ " " + "\"" + folderName + "\""+ " " + "\"" + folderDescription + "\"";
//		VisualReporter.logScriptInfo("Create folder "+folderName+ " in " + parentFolderTitle + " with description : " + folderDescription);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Creating folder executes  "+(result==0 ? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error reason:  "+resultStr[1]);
//		return false;
//	}
//	/**
//	 * create a new folder in Outlook
//	 * @param String parentFolderTitle - the folder which the new folder will be located
//	 * @param String folderName - the name of the new folder
//	 * @param String folderDescription - the description of the new folder
//	 * @return boolean -true for success and false for failing to create the folder
//	 */
//	public boolean createOutlookFolder2(String placeName, String folderName, String folderDescription){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\Conn811\\NewFolderOutlook.exe";
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + placeName + "\""+ " " + "\"" + folderName + "\""+ " " + "\"" + folderDescription + "\"";
//		VisualReporter.logScriptInfo("Create folder "+folderName+ " in " + placeName + " with description : " + folderDescription);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Creating folder executes  "+(result==0 ? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error reason:  "+resultStr[1]);
//		return false;
//	}
//	/**
//	 * create a new folder in Outlook
//	 * @param String parentFolderTitle - the folder which the new folder will be located
//	 * @param String folderName - the name of the new folder
//	 * @param String folderDescription - the description of the new folder
//	 * @return boolean -true for success and false for failing to create the folder
//	 */
//	public boolean createOutlookFolder(String placeName, String folderName, String folderDescription){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\Conn811\\NewFolderOutlook.exe";
//		if (gsConnVersion.equalsIgnoreCase("850"))
//		{sToolName = gsToolsPath + "\\Conn85\\NewFolderOutlook85.exe";}
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + placeName + "\""+ " " + "\"" + folderName + "\""+ " " + "\"" + folderDescription + "\"";
//		VisualReporter.logScriptInfo("Create folder "+folderName+ " in " + placeName + " with description : " + folderDescription);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Creating folder executes  "+(result==0 ? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error reason:  "+resultStr[1]);
//		return false;
//	}
//	/**
//	 * test if we can create folder successfully, and after creating, close the current window!  
//	 * @param String path - path of folder which new folder is placed
//	 * @param String folderName - name of new folder
//	 * @param String folderDescription - description of new folder
//	 * @return boolean - true for success, and false for failure
//	 */
//	public boolean createNewFolder(String path,String folderName,String folderDescription){
//		String currentTitle=openFolder(path);
//		if (currentTitle==null) 	return false;
//		boolean result=_createFolder(currentTitle, folderName, folderDescription);
//		closeFolder(currentTitle);
//		return result;
//	}
//	/**
//	 * delete a folder or file
//	 * @param String parentFolderTitle - the folder which contains the folder or file will be deleted
//	 * @param String fileName - the folder or file will be deleted
//	 * @return boolean - true for success and false for failure
//	 */
//	public boolean _deleteFolder(String parentFolderTitle, String fileName){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\DeleteFolder.exe";
//		if (gsConnVersion.equalsIgnoreCase("811"))
//			{sToolName = gsToolsPath + "\\Conn811\\DeleteFolder.exe";}
//		if (gsConnVersion.equalsIgnoreCase("850"))
//		{sToolName = gsToolsPath + "\\Conn85\\DeleteFolder.exe";}
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + parentFolderTitle + "\""+ " " + "\"" + fileName + "\"";
//		VisualReporter.logScriptInfo("Delete file or folder "+fileName+ " from " + parentFolderTitle);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Deleting executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error reason:  "+resultStr[1]);
//		return false;
//	}
//	/**
//	 * test if we can delete the file or folder successfully, and close the current window whether
//	 * succsss or failure
//	 * @param String parentFolderTitle - name of folder which deleted file is placed
//	 * @param String fileName - name of file or folder which will be deleted 
//	 * @return boolean - true for success, and false for failure
//	 */
//	public boolean delFolder(String parentFolderPath, String fileName){
//		String title=openFolder(parentFolderPath);
//		if (title==null) return false;
//		boolean result=_deleteFolder(title, fileName);
//		sleep(VisualReporter.giVisualReporterPause2TO);
//		closeFolder(title);
//		return result;
//	}
//	/**
//	 * drag folders and files to another folder which are in the same parent folder
//	 * @param String parentFoldeTitle - the folder which contains fileList and targetFolderName
//	 * @param String fileList - name of folders or files which will be dragged
//	 * @param String targetFolderName - the target folder for the dragging   
//	 * @return boolean - true for success and false for failure
//	 */
//	public boolean _dragDropSimple(String parentFoldeTitle, String[] fileList, String targetFolderName){
//		if (fileList==null){
//			VisualReporter.logScriptInfo("dragDropSimple() method got a null parameter!");
//			return false;
//		}
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\DragDropSV.exe";
//		StringBuffer libStr=new StringBuffer(fileList[0]);
//		for (int i=1;i<fileList.length;i++)
//			libStr.append(":").append(fileList[i]);
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + parentFoldeTitle + "\""+ " " + "\"" + libStr.toString() + "\""+ " " + "\"" + targetFolderName + "\"";
//		VisualReporter.logScriptInfo("Drag folders and files "+libStr.toString()+ " to " + targetFolderName + " in the same path: " + parentFoldeTitle);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//	    }catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//	    String[] resultStr=getResultString();;
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Dragging executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error reason:  "+resultStr[1]);
//        
//       	return false;
//	}
//	/**
//	 * drag folders and files to the target folder in the same parent folder, just for DI
//	 * @param String parentFolder - the folder which contains sourcefileName and targetFolderName
//	 * @param String sourcefileName - name of file which will be dragged
//	 * @param String targetFolderName - name of folder which sourcefileName file will be dragged to
//	 * @return boolean - true for success, and false for failure
//	 */
//	public boolean dragDropSV(String parentFolderPath,String[] fileList,String targetFolderName){
//		String sourcetTitle=openFolder(parentFolderPath);
//		if (sourcetTitle==null){
//			return false;
//		}
//		boolean result=_dragDropSimple(sourcetTitle, fileList, targetFolderName);
//		closeFolder(sourcetTitle);
//		if (result) {
//			String title=openFolder(parentFolderPath+"\\"+targetFolderName);
//			if (title==null)  return false;
//			setViewStyle(title, "Details");
//			
//			String[] files=_getFileList(title);
//			closeFolder(title);
//			if (files==null) return false;
//			java.util.ArrayList listFiles=new ArrayList();
//			for(int i=0;i<files.length;i++){
//				listFiles.add(files[i].split(":")[0]);
//			}
//			for(int i=0;i<fileList.length;i++)
//				if (listFiles.contains(fileList[i])) {
//					return true;
//				}
//			return false;
//		}
//		return false;
//	}
//
//	/**
//	 * drag folders and files to Quickr!
//	 * @param String fromFoldeTitle - the folder which fileList is under
//	 * @param String fileList - the name list of folders and files will be dragged 
//	 * @return boolean - true for success, and false for failure
//	 */
//	public boolean _dragDropQuickr(String fromFoldeTitle, String[] fileList, String X, String Y){
//		String targetQuickrTitle = VisualReporter.gsVisualReporterBrowserBaseStatePage;
//		if (fileList==null){
//			VisualReporter.logScriptInfo("dragDroptoQuickr() method got a null parameter!");
//			return false;
//		}
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\DragDroptoQuickr.exe";
//		StringBuffer libStr=new StringBuffer(fileList[0]);
//		for (int i=1;i<fileList.length;i++)
//			libStr.append(":").append(fileList[i]);
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + fromFoldeTitle + "\""+ " " + "\"" + libStr.toString() + "\""+ " " + "\"" + targetQuickrTitle + "\"" + " " + "\"" + X + "\"" + " " + "\"" + Y + "\"";
//		VisualReporter.logScriptInfo("Drag folders and files  "+libStr.toString()+ " to " + targetQuickrTitle + " in different path.");
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//	    }catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//	    String[] resultStr=getResultString();;
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Dragging executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error reason:  "+resultStr[1]);
//       	return false;
//	}
//	
//	/**
//	 * drag folders and files to Quickr Dom!
//	 * @param String fromFoldeTitle - the folder which fileList is under
//	 * @param String fileList - the name list of folders and files will be dragged 
//	 * @return boolean - true for success, and false for failure
//	 */
//	public boolean _dragDropQuickrDomEdit(String fromFoldeTitle, String[] fileList, String X, String Y){
//		//String targetQuickrTitle = VisualReporter.gsVisualReporterBrowserBaseStatePage;
//		String targetQuickrTitle = "Edit";
//		if (fileList==null){
//			VisualReporter.logScriptInfo("dragDroptoQuickr() method got a null parameter!");
//			return false;
//		}
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\DragDroptoQuickrDom.exe";
//		StringBuffer libStr=new StringBuffer(fileList[0]);
//		for (int i=1;i<fileList.length;i++)
//			libStr.append(":").append(fileList[i]);
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + fromFoldeTitle + "\""+ " " + "\"" + libStr.toString() + "\""+ " " + "\"" + targetQuickrTitle + "\"" + " " + "\"" + X + "\"" + " " + "\"" + Y + "\"";
//		VisualReporter.logScriptInfo("Drag folders and files  "+libStr.toString()+ " to " + targetQuickrTitle + " in different path.");
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//	    }catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//	    String[] resultStr=getResultString();;
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Dragging executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error reason:  "+resultStr[1]);
//       	return false;
//	}
//	/**
//	 * drag folders and files to Quickr Dom!
//	 * @param String fromFoldeTitle - the folder which fileList is under
//	 * @param String fileList - the name list of folders and files will be dragged 
//	 * @return boolean - true for success, and false for failure
//	 */
//	public boolean _dragDropQuickrDom(String fromFoldeTitle, String[] fileList, String X, String Y){
//		//String targetQuickrTitle = VisualReporter.gsVisualReporterBrowserBaseStatePage;
//		String targetQuickrTitle = "Library";
//		if (fileList==null){
//			VisualReporter.logScriptInfo("dragDroptoQuickr() method got a null parameter!");
//			return false;
//		}
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\DragDroptoQuickrDom.exe";
//		StringBuffer libStr=new StringBuffer(fileList[0]);
//		for (int i=1;i<fileList.length;i++)
//			libStr.append(":").append(fileList[i]);
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + fromFoldeTitle + "\""+ " " + "\"" + libStr.toString() + "\""+ " " + "\"" + targetQuickrTitle + "\"" + " " + "\"" + X + "\"" + " " + "\"" + Y + "\"";
//		VisualReporter.logScriptInfo("Drag folders and files  "+libStr.toString()+ " to " + targetQuickrTitle + " in different path.");
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//	    }catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//	    String[] resultStr=getResultString();;
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Dragging executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error reason:  "+resultStr[1]);
//       	return false;
//	}
//
//	/**
//	 * drag folders and files to Quickr Dom!
//	 * @param String fromFoldeTitle - the folder which fileList is under
//	 * @param String fileList - the name list of folders and files will be dragged 
//	 * @return boolean - true for success, and false for failure
//	 */
//	public boolean _dragDropQuickrDom2(String fromFoldeTitle, String sWinTitle, String[] fileList, String X, String Y){
//		//String targetQuickrTitle = VisualReporter.gsVisualReporterBrowserBaseStatePage;
//		String targetQuickrTitle = sWinTitle;
//		if (fileList==null){
//			VisualReporter.logScriptInfo("dragDroptoQuickr() method got a null parameter!");
//			return false;
//		}
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\DragDroptoQuickrDom.exe";
//		StringBuffer libStr=new StringBuffer(fileList[0]);
//		for (int i=1;i<fileList.length;i++)
//			libStr.append(":").append(fileList[i]);
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + fromFoldeTitle + "\""+ " " + "\"" + libStr.toString() + "\""+ " " + "\"" + targetQuickrTitle + "\"" + " " + "\"" + X + "\"" + " " + "\"" + Y + "\"";
//		VisualReporter.logScriptInfo("Drag folders and files  "+libStr.toString()+ " to " + targetQuickrTitle + " in different path.");
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//	    }catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//	    String[] resultStr=getResultString();;
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Dragging executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error reason:  "+resultStr[1]);
//       	return false;
//	}
//	
//	/**
//	 * drag folders and files to Quickr!
//	 * @param String fromFoldeTitle - the folder which fileList is under
//	 * @param String fileList - the name list of folders and files will be dragged 
//	 * @return boolean - true for success, and false for failure
//	 */
//	public boolean _dragDropQuickrEntry(String fromFoldeTitle, String[] fileList, String X, String Y){
//		//String targetQuickrTitle = "Library";
//		String targetQuickrTitle = "Files for";
//		if (fileList==null){
//			VisualReporter.logScriptInfo("dragDroptoQuickrEntry() method got a null parameter!");
//			return false;
//		}
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\DragDroptoQuickrEntry.exe";
//		StringBuffer libStr=new StringBuffer(fileList[0]);
//		for (int i=1;i<fileList.length;i++)
//			libStr.append(":").append(fileList[i]);
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + fromFoldeTitle + "\""+ " " + "\"" + libStr.toString() + "\""+ " " + "\"" + targetQuickrTitle + "\"" + " " + "\"" + X + "\"" + " " + "\"" + Y + "\"";
//		VisualReporter.logScriptInfo("Drag folders and files  "+libStr.toString()+ " to " + targetQuickrTitle + " in different path.");
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//	    }catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//	    String[] resultStr=getResultString();;
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Dragging executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error reason:  "+resultStr[1]);
//       	return false;
//	}
//	
//		
//	/**
//	 * drag folders and files to any other folder!
//	 * @param String fromFoldeTitle - the folder which fileList is under
//	 * @param String fileList - the name list of folders and files will be dragged 
//	 * @param String targetFolderTitle - the target folder for the dragging 
//	 * @return boolean - true for success, and false for failure
//	 */
//	public boolean _dragDropEnhanced(String fromFoldeTitle, String[] fileList, String targetFolderTitle){
//		if (fileList==null){
//			VisualReporter.logScriptInfo("dragDropEnhanced() method got a null parameter!");
//			return false;
//		}
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\DragDropEV.exe";
//		StringBuffer libStr=new StringBuffer(fileList[0]);
//		for (int i=1;i<fileList.length;i++)
//			libStr.append(":").append(fileList[i]);
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + fromFoldeTitle + "\""+ " " + "\"" + libStr.toString() + "\""+ " " + "\"" + targetFolderTitle + "\"";
//		VisualReporter.logScriptInfo("Drag folders and files  "+libStr.toString()+ " to " + targetFolderTitle + " in different path.");
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//	    }catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//	    String[] resultStr=getResultString();;
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Dragging executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error reason:  "+resultStr[1]);
//       	return false;
//	}
//	/**
//	 * drag folders and files to any other folder!
//	 * @param String fromFoldeTitle - the folder which fileList is under
//	 * @param String fileList - the name list of folders and files will be dragged 
//	 * @param String targetFolderTitle - the target folder for the dragging 
//	 * @return boolean - true for success, and false for failure
//	 */
//	public boolean _dragDropEnhanced85(String fromFoldeTitle, String[] fileList, String targetFolderTitle, String sCheckIn){
//		if (fileList==null){
//			VisualReporter.logScriptInfo("dragDropEnhanced() method got a null parameter!");
//			return false;
//		}
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\DragDropEV85.exe";
//		StringBuffer libStr=new StringBuffer(fileList[0]);
//		for (int i=1;i<fileList.length;i++)
//			libStr.append(":").append(fileList[i]);
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + fromFoldeTitle + "\""+ " " + "\"" + libStr.toString() + "\""+ " " + "\"" + targetFolderTitle + "\""+ " " + "\"" + sCheckIn + "\"";
//		VisualReporter.logScriptInfo("Drag folders and files  "+libStr.toString()+ " to " + targetFolderTitle + " in different path.");
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//	    }catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//	    String[] resultStr=getResultString();;
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Dragging executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error reason:  "+resultStr[1]);
//       	return false;
//	}
//	/**
//	 * drag folders and files from one folder to another folder no matter where target folder is 
//	 * @param String  sourceParentFolderPath - the path which fileList is under
//	 * @param String  fileList - the name list of folders and files which will be dragged
//	 * @param String  targetFolderPath - the path of target folder( including the folder itself)
//	 * @return boolean - true for success, and false for failure
//	 */
//	public boolean dragDropEV(String sourceParentFolderPath,String[] fileList,String targetFolderPath){
//		String sourcetParentTitle=openFolder(sourceParentFolderPath);
//		if (sourcetParentTitle==null)	return false;
//		setViewStyle(sourcetParentTitle, "Details");
//		String targetFolderTitle=openFolder(targetFolderPath);
//		if (targetFolderTitle==null) return false;
//		setViewStyle(targetFolderTitle, "Details");
//
//		boolean result=_dragDropEnhanced(sourcetParentTitle, fileList, targetFolderTitle);
//		closeFolder(sourcetParentTitle);
//		if (result) {
//			String[] files=_getFileList(targetFolderTitle);
//			closeFolder(targetFolderTitle);
//			if (files==null) return false;
//			java.util.ArrayList listFiles=new ArrayList();
//			for(int i=0;i<files.length;i++){
//				listFiles.add(files[i].split(":")[0]);
//			}
//			for(int i=0;i<fileList.length;i++)
//				if (listFiles.contains(fileList[i])) {
//					return true;
//				}
//			return false;
//		}
//		closeFolder(targetFolderTitle);
//		return false;
//	}
//	
//	/**
//	 * drag folders and files from one folder to another folder no matter where target folder is 
//	 * @param String  sourceParentFolderPath - the path which fileList is under
//	 * @param String  fileList - the name list of folders and files which will be dragged
//	 * @param String  targetFolderPath - the path of target folder( including the folder itself)
//	 * @return boolean - true for success, and false for failure
//	 */
//	public boolean dragDropEV85(String sourceParentFolderPath,String[] fileList,String targetFolderPath, boolean bCheckIn){
//		String sourcetParentTitle=openFolder(sourceParentFolderPath);
//		if (sourcetParentTitle==null)	return false;
//		setViewStyle(sourcetParentTitle, "Details");
//		String targetFolderTitle=openFolder(targetFolderPath);
//		if (targetFolderTitle==null) return false;
//		setViewStyle(targetFolderTitle, "Details");
//		String checkinStr=bCheckIn? "True":"False";
//		boolean result=_dragDropEnhanced85(sourcetParentTitle, fileList, targetFolderTitle, checkinStr);
//		closeFolder(sourcetParentTitle);
//		if (result) {
//			String[] files=_getFileList(targetFolderTitle);
//			closeFolder(targetFolderTitle);
//			if (files==null) return false;
//			java.util.ArrayList listFiles=new ArrayList();
//			for(int i=0;i<files.length;i++){
//				listFiles.add(files[i].split(":")[0]);
//			}
//			for(int i=0;i<fileList.length;i++)
//				if (listFiles.contains(fileList[i])) {
//					return true;
//				}
//			return false;
//		}
//		closeFolder(targetFolderTitle);
//		return false;
//	}
//
//	/**
//	 * drag folders and files from one folder to another folder in Outlook 
//	 * @param String  sourceParentFolderPath - the path which fileList is under
//	 * @param String  fileList - the name list of folders and files which will be dragged
//	 * @param String  targetFolderPath - the path of target folder( including the folder itself)
//	 * @return boolean - true for success, and false for failure
//	 */
//	public boolean dragDropOutlook(String sourceParentFolderPath,String[] fileList,String targetFolderPath, boolean bCheckIn){
//		String sourcetParentTitle=openFolder(sourceParentFolderPath);
//		if (sourcetParentTitle==null)	return false;
//		setViewStyle(sourcetParentTitle, "Details");
//		String checkinStr=bCheckIn? "True":"False";
//		boolean result=_dragDropOutlook(sourcetParentTitle, fileList, targetFolderPath, checkinStr);
//		closeFolder(sourcetParentTitle);
//		if (result) {return true;}
//		return false;
//	}
//	
//	/**
//	 * Just test the 'edit' menuitem in context menu, to check whether or not a dialog appeared indicating the file is locked 
//	 * @param String parentFolderTitle - name of folder which file is placed
//	 * @param String fileName - name of file which will be edited
//	 * @return boolean - true for success, and false for failure
//	 */
//	public String _editFileIn_DI(String parentFolderTitle, String fileName){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\EditFile_DI.exe";
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + parentFolderTitle + "\""+ " " + "\"" + fileName + "\"";
//		VisualReporter.logScriptInfo("edit file "+fileName+ " from " + parentFolderTitle);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString()+"gogogogo");
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return null;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("editing file executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) return resultStr[1];
//		//VisualReporter.logScriptInfo("Error Code:  "+result+", Error Reason: "+resultStr[1]);
//		return null;
//	}
//	
//	/**
//	 * drag folders and files to any other folder in Outlook
//	 * @param String fromFoldeTitle - the folder which fileList is under
//	 * @param String fileList - the name list of folders and files will be dragged 
//	 * @param String targetFolderTitle - the target folder for the dragging 
//	 * @return boolean - true for success, and false for failure
//	 */
//	public boolean _dragDropOutlook(String fromFoldeTitle, String[] fileList, String targetFolderTitle, String sCheckIn){
//		if (fileList==null){
//			VisualReporter.logScriptInfo("dragDropEnhanced() method got a null parameter!");
//			return false;
//		}
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\Conn811\\DragDroptoOutlook.exe";
//		if (gsConnVersion.equalsIgnoreCase("850"))
//		{sToolName = gsToolsPath + "\\Conn85\\DragDroptoOutlook.exe";}
//		StringBuffer libStr=new StringBuffer(fileList[0]);
//		for (int i=1;i<fileList.length;i++)
//			libStr.append(":").append(fileList[i]);
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + fromFoldeTitle + "\""+ " " + "\"" + libStr.toString() + "\""+ " " + "\"" + targetFolderTitle + "\""+ " " + "\"" + sCheckIn + "\"";
//		VisualReporter.logScriptInfo("Drag folders and files  "+libStr.toString()+ " to " + targetFolderTitle + " in different path.");
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//	    }catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//	    String[] resultStr=getResultString();;
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Dragging executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error reason:  "+resultStr[1]);
//       	return false;
//	}
//	
//	/**
//	 * Brings up the edit Properties dialog from the context menu 
//	 * @param String parentFolderTitle - name of folder which file is placed
//	 * @param String fileName - name of file which will be edited
//	 * @param String title - new titles for file
//	 * @param String desc -  new desc for file
//	 * @return boolean - true for success, and false for failure
//	 */
//	public String _editFilePropsIn_DI(String parentFolderTitle, String fileName, String title, String desc){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\EditFileProperties_DI.exe";
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + parentFolderTitle + "\""+ " " + "\"" + fileName + "\""+ " " + "\"" + title + "\""+ " " + "\"" + desc + "\"";
//		VisualReporter.logScriptInfo("edit file "+fileName+ " from " + parentFolderTitle);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString()+"gogogogo");
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return null;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("editing file properties executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) return resultStr[1];
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error Reason: "+resultStr[1]);
//		return null;
//	}
//	
//	/**
//	 * Brings up the edit Properties dialog from the context menu in office
//	 * @param String fileName - name of file which will be edited
//	 * @param String title - new titles for file
//	 * @param String desc -  new desc for file
//	 * @return boolean - true for success, and false for failure
//	 */
//	public String _editFilePropsIn_Office(String fileName, String title, String desc){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\Conn811\\EditFileProperties_Office.exe";
//		if (gsConnVersion.equalsIgnoreCase("850"))
//		{sToolName = gsToolsPath + "\\Conn85\\EditFileProperties_Office.exe";}
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + fileName + "\""+ " " + "\"" + title + "\""+ " " + "\"" + desc + "\"";
//		VisualReporter.logScriptInfo("edit file "+fileName+ "  properties from Office" );
//		VisualReporter.logScriptInfo(sCMD);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString()+"gogogogo");
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return null;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("editing file properties in office executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) return resultStr[1];
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error Reason: "+resultStr[1]);
//		return null;
//	}
//	/**
//	 * Brings up the edit Properties dialog from the context menu 
//	 * @param String parentFolderTitle - name of folder which file is placed
//	 * @param String fileName - name of file which will be edited
//	 * @param String title - new titles for file
//	 * @param String desc -  new desc for file
//	 * @return boolean - true for success, and false for failure
//	 */
//	public String _editFilePropsIn2_DI(String parentFolderTitle, String fileName, String title, String desc){
//		//same version as above but for a doc not checked in yet
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\EditFileProperties2_DI.exe";
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + parentFolderTitle + "\""+ " " + "\"" + fileName + "\""+ " " + "\"" + title + "\""+ " " + "\"" + desc + "\"";
//		VisualReporter.logScriptInfo("edit file "+fileName+ " from " + parentFolderTitle);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString()+"gogogogo");
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return null;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("editing file properties executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) return resultStr[1];
//		//VisualReporter.logScriptInfo("Error Code:  "+result+", Error Reason: "+resultStr[1]);
//		return null;
//	}
//	/**
//	 * Brings up the edit Properties dialog from the context menu 
//	 * @param String parentFolderTitle - name of folder which file is placed
//	 * @param String fileName - name of file which will be edited
//	 * @param String title - new titles for file
//	 * @param String desc -  new desc for file
//	 * @return boolean - true for success, and false for failure
//	 */
//	public String _editFolderPropsIn_DI(String parentFolderTitle, String fileName, String title, String desc){
//		//same version as above but for a doc not checked in yet
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\EditFolderProperties_DI.exe";
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + parentFolderTitle + "\""+ " " + "\"" + fileName + "\""+ " " + "\"" + title + "\""+ " " + "\"" + desc + "\"";
//		VisualReporter.logScriptInfo("edit file "+fileName+ " from " + parentFolderTitle);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString()+"gogogogo");
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return null;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("editing file properties executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) return resultStr[1];
//		//VisualReporter.logScriptInfo("Error Code:  "+result+", Error Reason: "+resultStr[1]);
//		return null;
//	}
//	/**
//	 * Brings up the edit Properties dialog from the context menu and verifies settings
//	 * @param String parentFolderTitle - name of folder which file is placed
//	 * @param String fileName - name of file which will be edited
//	 * @param String title - new titles for file
//	 * @param String desc -  new desc for file
//	 * @return boolean - true for success, and false for failure
//	 */
//	public String _verifyFilePropsIn_DI(String parentFolderTitle, String fileName, String title, String desc){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\VerifyFileProperties_DI.exe";
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + parentFolderTitle + "\""+ " " + "\"" + fileName + "\""+ " " + "\"" + title + "\""+ " " + "\"" + desc + "\"";
//		VisualReporter.logScriptInfo("edit file "+fileName+ " from " + parentFolderTitle);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString()+"gogogogo");
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return null;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("editing file properties executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) return resultStr[1];
//		//VisualReporter.logScriptInfo("Error Code:  "+result+", Error Reason: "+resultStr[1]);
//		return null;
//	}
//	/**
//	 * Brings up the edit Properties dialog from inside word doc 
//	 * @param String fileName - name of file which will be edited
//	 * @param String title - new titles for file
//	 * @param String desc -  new desc for file
//	 * @return boolean - true for success, and false for failure
//	 */
//	public String _editFilePropsIn_Word(String fileName, String title, String desc){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\EditFilePropertiesInWord.exe";
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + fileName + "\""+ " " + "\"" + title + "\""+ " " + "\"" + desc + "\"";
//		VisualReporter.logScriptInfo("edit file properties for "+fileName);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString()+"gogogogo");
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return null;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("editing file properties executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) return resultStr[1];
//		//VisualReporter.logScriptInfo("Error Code:  "+result+", Error Reason: "+resultStr[1]);
//		return null;
//	}
//	/**
//	 * use the "edit" menuitem when right-click the file in the specified path. 
//	 * @param String parentFolderPath -absolute path of file(not including file's name) 
//	 * @param String fileName - name of file
//	 * @return String - the opened title of the file, if successful; null if "edit" is not available 
//	 * close the window whether "edit" is successful or not. 
//	 */
//	public String editFileInDI(String parentFolderPath,String fileName){
//		String rootTitle=openFolder(parentFolderPath);
//		if (rootTitle==null) return null;
//		String result=_editFileIn_DI(rootTitle,fileName);
//		closeFolder(rootTitle);
//		return result;
//	}
//	
//	/**
//	 * use the "edit" menuitem when right-click the file in the specified path. 
//	 * @param String parentFolderPath -absolute path of file(not including file's name) 
//	 * @param String fileName - name of file
//	 * @param String sTitle - title of file
//	 * @param String sDesc - description of file
//	 * @return String - the opened title of the file, if successful; null if "edit" is not available 
//	 * close the window whether "edit" is successful or not. 
//	 */
//	public String editFilePropsInDI(String parentFolderPath,String fileName, String title, String desc){
//		String rootTitle=openFolder(parentFolderPath);
//		if (rootTitle==null) return null;
//		String result=_editFilePropsIn_DI(rootTitle,fileName,title,desc);
//		closeFolder(rootTitle);
//		return result;
//	}
//	
//	/**
//	 * use the "edit" menuitem when right-click the file in the specified path. 
//	 * @param String fileName - name of file
//	 * @param String sTitle - title of file
//	 * @param String sDesc - description of file
//	 * @return String - the opened title of the file, if successful; null if "edit" is not available 
//	 * close the window whether "edit" is successful or not. 
//	 */
//	public String editFilePropsInOffice(String fileName, String title, String desc){
//
//		String result=_editFilePropsIn_Office(fileName,title,desc);
//		return result;
//	}
//	
//	/**
//	 * use the "edit" menuitem when right-click the file in the specified path. 
//	 * @param String parentFolderPath -absolute path of file(not including file's name) 
//	 * @param String fileName - name of file
//	 * @param String sTitle - title of file
//	 * @param String sDesc - description of file
//	 * @return String - the opened title of the file, if successful; null if "edit" is not available 
//	 * close the window whether "edit" is successful or not. 
//	 */
//	public String editFileProps2InDI(String parentFolderPath,String fileName, String title, String desc){
//		//same as above but for a doc not checked out yet
//		String rootTitle=openFolder(parentFolderPath);
//		if (rootTitle==null) return null;
//		String result=_editFilePropsIn2_DI(rootTitle,fileName,title,desc);
//		closeFolder(rootTitle);
//		return result;
//	}
//	/**
//	 * use the "edit" menuitem when right-click the file in the specified path. 
//	 * @param String parentFolderPath -absolute path of file(not including file's name) 
//	 * @param String fileName - name of file
//	 * @param String sTitle - title of file
//	 * @param String sDesc - description of file
//	 * @return String - the opened title of the file, if successful; null if "edit" is not available 
//	 * close the window whether "edit" is successful or not. 
//	 */
//	public String editFolderPropsInDI(String parentFolderPath,String fileName, String title, String desc){
//		//same as above but for a doc not checked out yet
//		String rootTitle=openFolder(parentFolderPath);
//		if (rootTitle==null) return null;
//		String result=_editFolderPropsIn_DI(rootTitle,fileName,title,desc);
//		closeFolder(rootTitle);
//		return result;
//	}
//	/**
//	 * use the "edit" menuitem when right-click the file in the specified path. 
//	 * @param String fileName - name of file
//	 * @param String sTitle - title of file
//	 * @param String sDesc - description of file
//	 * @return String - the opened title of the file, if successful; null if "edit" is not available 
//	 * close the window whether "edit" is successful or not. 
//	 */
//	public String editFilePropsInWord(String fileName, String title, String desc){
//		String result=_editFilePropsIn_Word(fileName,title,desc);
//		return result;
//	}
//	
//	/**
//	 * use the "edit" menuitem when right-click the file in the specified path. 
//	 * @param String parentFolderPath -absolute path of file(not including file's name) 
//	 * @param String fileName - name of file
//	 * @param String sTitle - title of file
//	 * @param String sDesc - description of file
//	 * @return String - the opened title of the file, if successful; null if "edit" is not available 
//	 * close the window whether "edit" is successful or not. 
//	 */
//	public String verifyFileProps_DI(String parentFolderPath,String fileName, String title, String desc){
//		String rootTitle=openFolder(parentFolderPath);
//		if (rootTitle==null) return null;
//		String result=_verifyFilePropsIn_DI(rootTitle,fileName,title,desc);
//		closeFolder(rootTitle);
//		return result;
//	}
//	/**
//	 * get all the context menu items and all their states of a given file, folder or a view. If fileName equals null, then return
//	 * the content of context menu on "Lotus Quickr" root! 
//	 * @param String parentFolderTitle - the folder which file, folder, or view is located
//	 * @param String fileName - name of a file, folder or a view
//	 * @return  String - string representing all the context menu items and their state(1: enabled; 0: disabled), and using '\' to separate all items;
//	 *          and null if failure appeares. for example: "0	copy:1\paste:0\check in:1\check out:0"
//	 * 
//	 */
//	public String[] _getPopMenuContent(String parentFolderTitle, String fileName){
//		//if 811 then reset default string
//		if (gsConnVersion.equalsIgnoreCase("811"))
//			gsLotusQuickr=gsLotusQuickr811;
//		//if 850, the same as 811
//		if (gsConnVersion.equalsIgnoreCase("850"))
//			gsLotusQuickr=gsLotusQuickr811;
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\GetPopMenuContent.exe";
//		String sCMD=null;
//		if (fileName!=null)
//			sCMD="\"" + sToolName  + "\"" + " " + "\"" + parentFolderTitle + "\""+ " " + "\"" + fileName + "\"";
//		else
//			sCMD="\"" + sToolName  + "\"" + " " + "\"" + parentFolderTitle + "\""+" ";
//		
//		VisualReporter.logScriptInfo("Get the content of context menu on item "+((fileName==null)? gsLotusQuickr:fileName)+ " from " + parentFolderTitle);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return null;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Getting content of context menu executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0)	{
//			String[] menuitems=null;
//			try{
//				menuitems=resultStr[1].split("\\\\");
//			}catch (Exception e){
//				VisualReporter.logScriptInfo("Java Methods failed to get return result from AutoIt script"+e.toString());
//			}
//			return menuitems;
//		}
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error reason:  "+resultStr[1]);
//		return null;
//	}
//	/**
//	 * list all the menuitems and their states for the specified library, file or view
//	 * @param String parentFolderPath - absolute path of file(not including file's name) 
//	 * @param String fileName - name of file 
//	 * @return String[] - string list of menu sub-menu items and their state(enabled or disabled) 
//	 */
//	public String[] getContextMenuItems(String parentFolderPath, String fileName){
//		String title=openFolder(parentFolderPath);
//		if(title==null) return null;
//		setViewStyle(title, "Details");
//		
//		String[] menuItemsAndStates=_getPopMenuContent(title, fileName);
//		closeFolder(title);
//		return menuItemsAndStates;
//		/*
//		VisualReporter.logScriptInfo("Context menu items of " +fileName +" in "+parentFolderPath);
//		String[] menuItemState=null;
//		for(int i=0; i<menuItemsAndStates.length;i++){
//			menuItemState=menuItemsAndStates[i].split(":");
//			VisualReporter.logScriptInfo("----Menu Item["+i+"]: "+menuItemState[0] +", State: "+(menuItemState[1].equals("1") ? "Enabled":"Disabled"));
//		}
//		return true;
//		*/
//	}
//	/**
//	 * get all the files and folders in given folder
//	 * @param String parentFolderTitle - the folder whose content will be got
//	 * @return String[] - the array of string, each element  represents a file, a foler or a view
//	 *  and null if failure or there is no files or folders
//	 */
//	public String[] _getFileList(String parentFolderTitle){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\GetFileList.exe";
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + parentFolderTitle + "\"";
//		VisualReporter.logScriptInfo("Get the file list of folder " + parentFolderTitle);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return null;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Getting file lists executes  "+(result==0? "Successfully":"Failed"));
//		
//		if (result==0)	{
//			String[] files=null;
//			try{
//				files=resultStr[1].split("\\\\");
//			}catch (Exception e){
//				VisualReporter.logScriptInfo("Java Methods failed to get return result from AutoIt script"+e.toString());
//			}
//			return files;
//		}
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error reason:  "+resultStr[1]);
//		return null;
//	}
//	/**
//	 * get lists and files and folders in the given path
//	 * @param String filePath - absolute path of folder whose content will be got
//	 * @return String[] - the list of folders and files in given path
//	 */
//	public String[] getFilesList(String filePath){
//		String rootTitle=openFolder(filePath);
//		if (rootTitle==null) return null;
//		setViewStyle(rootTitle, "Details");
//		String[] libList=_getFileList(rootTitle);
//		if (libList==null) {
//			closeFolder(rootTitle);
//			return null;
//		}
//		for(int i=0;i<libList.length;i++)
//			VisualReporter.logScriptInfo(libList[i]);
//		closeFolder(rootTitle);
//		return libList;
//	}
//	/**
//	 * get all the types which can be used for sorting to the specified folder
//	 * @param String parentFolderTitle - the specified folder
//	 * @return String[] - an array of string, each of them represents a file, a folder or a view
//	 * and null if failure
//	 */
//	public String[] _getSortType(String parentFolderTitle){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\GetSortTypes.exe";
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + parentFolderTitle + "\"";
//		VisualReporter.logScriptInfo("Get the sort types for folder " + parentFolderTitle);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return null;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Getting sort types executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0){
//			String[] files=null;
//			try{
//				files=resultStr[1].split("\\\\");
//				for (int i=0;i<files.length;i++)
//					files[i]=files[i].split(" ")[0];
//			}catch (Exception e){
//				VisualReporter.logScriptInfo("Java Methods failed to get return result from AutoIt script"+e.toString());
//			}
//			return files;
//		}
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error reason:  "+resultStr[1]);
//		return null;
//	}
//	/**
//	 * get list of types used to sort current window
//	 * @param String filePath - absolute path of folder whose sorting types will be got
//	 * @return String[] - list of types can be used to sort current window
//	 */
//	public String[] getSortTypes(String filePath){
//		String rootTitle=openFolder(filePath);
//		if (rootTitle==null) return null;
//		setViewStyle(rootTitle, "Details");
//		String[] libList=_getSortType(rootTitle);
//		closeFolder(rootTitle);
//		return libList;
//	}
///**
// * create a folder using the parent folder's context menu
// * @param String folderTitle - the current opened folder
// * @param String folderName - parent folder which the new folder will be located
// * @param String createFolderName - new folder name
// * @param String description - description of new folder
// * @return boolean -true for success, and false for failure 
// */
//	public boolean _newFolder(String folderTitle, String folderName, String createFolderName,String description){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\NewFolder.exe";
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + folderTitle + "\""+ " " + "\"" + folderName + "\""+ " " + "\"" + createFolderName + "\""+ " " + "\"" + description + "\"";
//		VisualReporter.logScriptInfo("Create folder "+createFolderName+ " in the path: " + folderTitle+"\\"+folderName);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();;
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Copy Folder execute  "+(result==0? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error reason:  "+resultStr[1]);
//        return false;
//	}
//	/**
//	 * create folder via context menu of specified library and folder
//	 * @param String path - absolute path of hostFolderName(not including hostFolerName) 
//	 * @param String hostFolderName - folder name which will be right-clicked
//	 * @param String folderName - name of new folder 
//	 * @param String folderDescription - description of new folder
//	 * @return boolean - true for success, false for failure
//	 */
//	public boolean createFolderByContextMenu(String path,String hostFolderName,String folderName,String folderDescription){
//			String currentTitle=openFolder(path);
//			if (currentTitle==null) 	return false;
//			boolean result=_newFolder(currentTitle, hostFolderName,folderName, folderDescription);
//			closeFolder(currentTitle);
//			return result;
//		}
//	/**
//	 * create a new txt file, and close it! the state of the file is checked-out
//	 * @param String parentFolderTitle - parent folder of new file will be placed 
//	 * @param String fileName - name of the new txt file
//	 * @param String content - the initial content of new txt file. if content equals null, just create an empty new txt file
//	 * @return boolean - true for success, and false for failure
//	 */
//	public boolean _newTxtFileInDI(String parentFolderTitle, String fileName,String content){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\NewTxtFile.exe";
//		String sCMD=null;
//		if (content==null)
//			sCMD="\"" + sToolName  + "\"" + " " + "\"" + parentFolderTitle + "\""+ " " + "\"" + fileName + "\"";
//		else
//			sCMD="\"" + sToolName  + "\"" + " " + "\"" + parentFolderTitle + "\""+ " " + "\"" + fileName + "\""+ " " + "\"" + content + "\"";
//		VisualReporter.logScriptInfo("create txt file "+fileName+ " with initial content: "+content+" in " + parentFolderTitle);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("creating txt file executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error Reason: "+resultStr[1]);
//		return false;
//	}
//	/**
//	 * create a .txt file in the specified path
//	 * @param String parentFolderPath - absolute path of new .txt file will be placed
//	 * @param String fileName - name of new .txt file
//	 * @param String content - initial content of .txt file
//	 * @return boolean - true for success, and false for failure
//	 */
//	public boolean createTxtInDI(String parentFolderPath,String fileName,String content){
//		String currentTitle=openFolder(parentFolderPath);
//		if (currentTitle==null) 	return false;
//		boolean result=_newTxtFileInDI(currentTitle, fileName,content);
//		closeFolder(currentTitle);
//		return result;
//	}
//	/**
//	 * check whether the given content has existed in the .txt file 
//	 * @param String txtFileTitle - the window title of opened .txt file 
//	 * @param String content - the string you want to check
//	 * @return boolean - true for exist, and false for not exist
//	 */
//	public boolean _checkTxtContent(String txtFileTitle, String content){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\CheckTxtContent.exe";
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + txtFileTitle + "\""+ " " + "\"" + content + "\"";
//		VisualReporter.logScriptInfo("check whether content: "+content+ " exists in file: "+txtFileTitle);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("content "+content+(result==0? "exists":"doesn't exist"+"in file: "+txtFileTitle));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error Reason: "+resultStr[1]);
//		return false;
//	}
//	/**
//	 * check content of specified .txt file in specified path
//	 * @param String parentFolderPath - absolute path of .txt file (not including filename)
//	 * @param String fileName - name of .txt file
//	 * @param String content - string need to check 
//	 * @return boolean - true for success, and false for failure
//	 */
//	public boolean checkTxtContentInDI(String parentFolderPath,String fileName,String content){
//			String currentTitle=openFileInDI(parentFolderPath,fileName);
//			if (currentTitle==null) 	return false;
//			boolean result=_checkTxtContent(currentTitle, content);
//			closeFolder(currentTitle);
//			return result;
//	}
//	/**
//	 * create a new word file, then check it in PDM, and close it!
//	 * @param String parentFolderTitle - parent folder of new file will be placed 
//	 * @param String fileName - name of the new word file
//	 * @return boolean - ture for success, and false for failure
//	 */
//	public boolean _newWordInDI(String parentFolderTitle, String fileName, boolean bCheckIn){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\NewWordInDI.exe";
//		if (gsConnVersion.equalsIgnoreCase("811"))
//			{sToolName = gsToolsPath + "\\Conn811\\NewWordInDI.exe";}
//		if (gsConnVersion.equalsIgnoreCase("850"))
//		{sToolName = gsToolsPath + "\\Conn85\\NewWordInDI.exe";}
//		String checkInStr= bCheckIn ? "True":"False";
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + parentFolderTitle + "\""+ " " + "\"" + fileName + "\""+ " " + "\"" + checkInStr + "\"";
//		VisualReporter.logScriptInfo("create word file "+fileName+ " from " + parentFolderTitle);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("creating word file executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error Reason: "+resultStr[1]);
//		return false;
//	}
//
//	/**
//	 * create a new word file, then check it in PDM, and close it!
//	 * @param String parentFolderTitle - parent folder of new file will be placed 
//	 * @param String fileName - name of the new word file
//	 * @return boolean - ture for success, and false for failure
//	 */
//	public boolean _newExcelInDI(String parentFolderTitle, String fileName, boolean bCheckIn){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\NewExcelInDI.exe";
//		if (gsConnVersion.equalsIgnoreCase("811"))
//			{sToolName = gsToolsPath + "\\Conn811\\NewExcelInDI.exe";}
//		if (gsConnVersion.equalsIgnoreCase("850"))
//		{sToolName = gsToolsPath + "\\Conn85\\NewExcelInDI.exe";}
//		String checkInStr= bCheckIn ? "True":"False";
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + parentFolderTitle + "\""+ " " + "\"" + fileName + "\""+ " " + "\"" + checkInStr + "\"";
//		VisualReporter.logScriptInfo("create excel file "+fileName+ " from " + parentFolderTitle);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("creating excel file executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error Reason: "+resultStr[1]);
//		return false;
//	}
//	/**
//	 * create a new word file, then check it in PDM, and close it!
//	 * @param String parentFolderTitle - parent folder of new file will be placed 
//	 * @param String fileName - name of the new word file
//	 * @return boolean - ture for success, and false for failure
//	 */
//	public boolean _newPPTInDI(String parentFolderTitle, String fileName, boolean bCheckIn){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\NewPPTInDI.exe";
//		if (gsConnVersion.equalsIgnoreCase("811"))
//			{sToolName = gsToolsPath + "\\Conn811\\NewPPTInDI.exe";}
//		if (gsConnVersion.equalsIgnoreCase("850"))
//		{sToolName = gsToolsPath + "\\Conn85\\NewPPTInDI.exe";}
//		String checkInStr= bCheckIn ? "True":"False";
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + parentFolderTitle + "\""+ " " + "\"" + fileName + "\""+ " " + "\"" + checkInStr + "\"";
//		VisualReporter.logScriptInfo("create powerpoint file "+fileName+ " from " + parentFolderTitle);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("creating powerpoint file executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error Reason: "+resultStr[1]);
//		return false;
//	}
//	/**
//	 * create a new word file in Word
//	 * @param String parentFolderTitle - parent folder of new file will be placed 
//	 * @param String fileName - name of the new word file
//	 */
//	public boolean _newWordInWord(String parentFolderTitle, String fileName){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\NewWordInWord.exe";
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + parentFolderTitle + "\""+ " " + "\"" + fileName + "\"";
//		VisualReporter.logScriptInfo("create word file "+fileName +" in Word");
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("creating word file executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error Reason: "+resultStr[1]);
//		return false;
//	}
//	/**
//	 * create a new word file in Word
//	 * @param String parentFolderTitle - parent folder of new file will be placed 
//	 * @param String fileName - name of the new word file
//	 */
//	public boolean _newWordInWord811(String sPlaceName, String parentFolderTitle, String fileName){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\Conn811\\NewWordInWord.exe";
//		if (gsConnVersion.equalsIgnoreCase("850"))
//		{sToolName = gsToolsPath + "\\Conn85\\NewWordInWord.exe";}
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + sPlaceName + "\"" + " " + "\"" + parentFolderTitle + "\""+ " " + "\"" + fileName + "\"";
//		VisualReporter.logScriptInfo("create word file "+fileName +" in Word");
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("creating word file executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error Reason: "+resultStr[1]);
//		return false;
//	}
//	
//	
//	/**
//	 *  Add a file to place
//	 * @param String parentFolderTitle - parent folder of new file will be placed 
//	 * @param String fileName - name of the new word file
//	 */
//	public boolean _addToPlace(String parentFolderTitle, String fileName){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\Conn811\\AddToPlace.exe";
//		if (gsConnVersion.equalsIgnoreCase("850"))
//		{sToolName = gsToolsPath + "\\Conn85\\AddToPlace.exe";}
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + parentFolderTitle + "\""+ " " + "\"" + fileName + "\"";
//		VisualReporter.logScriptInfo("create  file "+fileName +" in office, then add to place");
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Adding file to place executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error Reason: "+resultStr[1]);
//		return false;
//	}
//	/**
//	 * create a new excel file in Excel
//	 * @param String parentFolderTitle - parent folder of new file will be placed 
//	 * @param String fileName - name of the new word file
//	 */
//	public boolean _newExcelInExcel811(String sPlaceName, String parentFolderTitle, String fileName){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\Conn811\\NewExcelInExcel.exe";
//		if (gsConnVersion.equalsIgnoreCase("850"))
//		{sToolName = gsToolsPath + "\\Conn85\\NewExcelInExcel.exe";}
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + sPlaceName + "\"" + " " + "\"" + parentFolderTitle + "\""+ " " + "\"" + fileName + "\"";
//		VisualReporter.logScriptInfo("create Excel file "+fileName +" in Excel");
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("creating Excel file executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error Reason: "+resultStr[1]);
//		return false;
//	}
//	/**
//	 * create a new Powerpoint file in Powerpoint
//	 * @param String parentFolderTitle - parent folder of new file will be placed 
//	 * @param String fileName - name of the new word file
//	 */
//	public boolean _newPPTInPPT811(String sPlaceName, String parentFolderTitle, String fileName){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\Conn811\\NewPPTInPPT.exe";
//		if (gsConnVersion.equalsIgnoreCase("850"))
//		{sToolName = gsToolsPath + "\\Conn85\\NewPPTInPPT.exe";}
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + sPlaceName + "\"" + " " + "\"" + parentFolderTitle + "\""+ " " + "\"" + fileName + "\"";
//		VisualReporter.logScriptInfo("create Powerpoint file "+fileName +" in Powerpoint");
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("creating Powerpoint file executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error Reason: "+resultStr[1]);
//		return false;
//	}
//	/**
//	 * open a word file in Word
//	 * @param String parentFolderTitle - parent folder of new file will be opened 
//	 * @param String fileName - name of the new word file
//	 */
//	public boolean _openWordInWord(String parentFolderTitle, String fileName){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\OpenWordInWord.exe";
//		if (gsConnVersion.equalsIgnoreCase("811"))
//			{sToolName = gsToolsPath + "\\Conn811\\OpenWordInWord.exe";}
//		if (gsConnVersion.equalsIgnoreCase("850"))
//		{sToolName = gsToolsPath + "\\Conn85\\OpenWordInWord.exe";}
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + parentFolderTitle + "\""+ " " + "\"" + fileName + "\"";
//		VisualReporter.logScriptInfo("opening word file "+fileName +" in Word");
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("opening word file executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error Reason: "+resultStr[1]);
//		return false;
//	}
//	
//	/**
//	 * open an Excel file in Word
//	 * @param String parentFolderTitle - parent folder of new file will be opened 
//	 * @param String fileName - name of the new word file
//	 */
//	public boolean _openExcelInExcel(String parentFolderTitle, String fileName){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\OpenExcelInExcel.exe";
//		if (gsConnVersion.equalsIgnoreCase("811"))
//			{sToolName = gsToolsPath + "\\Conn811\\OpenExcelInExcel.exe";}
//		if (gsConnVersion.equalsIgnoreCase("850"))
//		{sToolName = gsToolsPath + "\\Conn85\\OpenExcelInExcel.exe";}
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + parentFolderTitle + "\""+ " " + "\"" + fileName + "\"";
//		VisualReporter.logScriptInfo("opening Excel file "+fileName +" in Excel");
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("opening Excel file executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error Reason: "+resultStr[1]);
//		return false;
//	}
//	/**
//	 * open a Powerpoint file in Powerpoint
//	 * @param String parentFolderTitle - parent folder of new file will be opened 
//	 * @param String fileName - name of the new word file
//	 */
//	public boolean _openPPTInPPT(String parentFolderTitle, String fileName){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\OpenPPTInPPT.exe";
//		if (gsConnVersion.equalsIgnoreCase("811"))
//			{sToolName = gsToolsPath + "\\Conn811\\OpenPPTInPPT.exe";}
//		if (gsConnVersion.equalsIgnoreCase("850"))
//		{sToolName = gsToolsPath + "\\Conn85\\OpenPPTInPPT.exe";}
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + parentFolderTitle + "\""+ " " + "\"" + fileName + "\"";
//		VisualReporter.logScriptInfo("opening a Powerpoint file "+fileName +" in Powerpoint");
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("opening Powerpoint file executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error Reason: "+resultStr[1]);
//		return false;
//	}
//	/**
//	 * Add an Outlook Message to a Place
//	 * @param String parentFolderTitle - parent folder of new file will be opened 
//	 * @param String fileName - name of the new word file
//	 */
//	public boolean outlookAddtoPlace(String parentFolderTitle, String fileName){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\Conn811\\OutlookAddtoPlace.exe";
//		if (gsConnVersion.equalsIgnoreCase("850"))
//		{sToolName = gsToolsPath + "\\Conn85\\OutlookAddtoPlace.exe";}
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + parentFolderTitle + "\""+ " " + "\"" + fileName + "\"";
//		VisualReporter.logScriptInfo("adding mail message "+fileName +" to Connectors");
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("adding mail message "+(result==0? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error Reason: "+resultStr[1]);
//		return false;
//	}
//	
//	/**
//	 * Open an Outlook Message to from Place
//	 * @param String parentFolderPath - parent path of new file will be opened 
//	 * @param String fileName - name of the msg to open
//	 */
//	public boolean outlookOpenMsg(String parentFolderPath, String fileName){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\Conn811\\OpenMsgInOutlook.exe";
//		if (gsConnVersion.equalsIgnoreCase("850"))
//		{sToolName = gsToolsPath + "\\Conn85\\OpenMsgInOutlook.exe";}
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + parentFolderPath + "\""+ " " + "\"" + fileName + "\"";
//		VisualReporter.logScriptInfo("opening mail message "+fileName +" to Connectors");
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("opening mail message "+(result==0? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error Reason: "+resultStr[1]);
//		return false;
//	}
//	
//	/**
//	 * create a new word file in the specified path
//	 * @param String parentFolderPath - absolute path which new word file will be placed
//	 * @param String fileName - name of file
//	 * @return boolean - true for success and false for failure
//	 */
//	public boolean createWordInDI(String parentFolderPath,String fileName, boolean bCheckIn){
//		String currentTitle=openFolder(parentFolderPath);
//		if (currentTitle==null) 	return false;
//		boolean result=_newWordInDI(currentTitle, fileName, bCheckIn);
//		closeFolder(currentTitle);
//		return result;
//	}
//	/**
//	 * create a new word file in the specified path
//	 * @param String parentFolderPath - absolute path which new word file will be placed
//	 * @param String fileName - name of file
//	 * @return boolean - true for success and false for failure
//	 */
//	public boolean createExcelInDI(String parentFolderPath,String fileName, boolean bCheckIn){
//		String currentTitle=openFolder(parentFolderPath);
//		if (currentTitle==null) 	return false;
//		boolean result=_newExcelInDI(currentTitle, fileName, bCheckIn);
//		closeFolder(currentTitle);
//		return result;
//	}
//	/**
//	 * create a new word file in the specified path
//	 * @param String parentFolderPath - absolute path which new word file will be placed
//	 * @param String fileName - name of file
//	 * @return boolean - true for success and false for failure
//	 */
//	public boolean createPPTInDI(String parentFolderPath,String fileName, boolean bCheckIn){
//		String currentTitle=openFolder(parentFolderPath);
//		if (currentTitle==null) 	return false;
//		boolean result=_newPPTInDI(currentTitle, fileName, bCheckIn);
//		closeFolder(currentTitle);
//		return result;
//	}
//	/**
//	 * create a new word file in Word
//	 * @param String parentFolderPath - absolute path which new word file will be placed
//	 * @param String fileName - name of file
//	 */
//	public boolean createWordInWord(String parentFolderPath,String fileName){
//		boolean result=_newWordInWord(parentFolderPath, fileName);
//		return result;
//	}
//
//	/**
//	 * create a new word file in Word
//	 * @param String parentFolderPath - absolute path which new word file will be placed
//	 * @param String fileName - name of file
//	 */
//	public boolean createWordInWord811(String sPlaceName, String parentFolderPath,String fileName){
//		boolean result=_newWordInWord811(sPlaceName, parentFolderPath, fileName);
//		return result;
//	}
//
//	/**
//	 * Add a file to place via office connector
//	 * @param String parentFolderPath - absolute path which new word file will be placed
//	 * @param String fileName - name of file
//	 */
//	public boolean AddToPlace( String parentFolderPath,String fileName){
//		boolean result=_addToPlace( parentFolderPath, fileName);
//		return result;
//	}
//	/**
//	 * create a new excel file in Excel
//	 * @param String parentFolderPath - absolute path which new word file will be placed
//	 * @param String fileName - name of file
//	 */
//	public boolean createExcelInExcel811(String sPlaceName, String parentFolderPath,String fileName){
//		boolean result=_newExcelInExcel811(sPlaceName, parentFolderPath, fileName);
//		return result;
//	}
//	
//	/**
//	 * create a new excel file in Excel
//	 * @param String parentFolderPath - absolute path which new word file will be placed
//	 * @param String fileName - name of file
//	 */
//	public boolean createPPTInPPT811(String sPlaceName, String parentFolderPath,String fileName){
//		boolean result=_newPPTInPPT811(sPlaceName, parentFolderPath, fileName);
//		return result;
//	}
//	
//	/**
//	 * open a word file in Word
//	 * @param String parentFolderPath - absolute path which new word file will be opened
//	 * @param String fileName - name of file
//	 */
//	public boolean openWordInWord(String parentFolderPath,String fileName){
//		boolean result=_openWordInWord(parentFolderPath, fileName);
//		return result;
//	}
//	
//	/**
//	 * open an Excel file in Excel
//	 * @param String parentFolderPath - absolute path which new word file will be opened
//	 * @param String fileName - name of file
//	 */
//	public boolean openExcelInExcel(String parentFolderPath,String fileName){
//		boolean result=_openExcelInExcel(parentFolderPath, fileName);
//		return result;
//	}
//	/**
//	 * open a Powerpoint file in Powerpoint
//	 * @param String parentFolderPath - absolute path which new word file will be opened
//	 * @param String fileName - name of file
//	 */
//	public boolean openPPTInPPT(String parentFolderPath,String fileName){
//		boolean result=_openPPTInPPT(parentFolderPath, fileName);
//		return result;
//	}
//	/**
//	 * open a file in one foler which has been opened 
//	 * @param String parentFolderTitle - name of folder which file is placed 
//	 * @param String fileName - name of file which will be opened
//	 * @return String - the title of opened file
//	 */
//	public String _openFileDI(String parentFolderTitle, String fileName){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\OpenFile_DI.exe";
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + parentFolderTitle + "\""+ " " + "\"" + fileName + "\"";
//		VisualReporter.logScriptInfo("open file "+fileName+ " from " + parentFolderTitle);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return null;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("opening file executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) return resultStr[1];
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error Reason: "+resultStr[1]);
//		return null;
//	}
//	/**
//	 * open specified file in specified path
//	 * @param String path - absolute path of specified file(not including file's name) 
//	 * @param String fileName - name of file which will be opened
//	 * @return String - the title of opened file 
//	 */
//	public String openFileInDI(String path,String fileName){
//			String currentTitle=openFolder(path);
//			if (currentTitle==null) 	return null;
//			String result=_openFileDI(currentTitle, fileName);
//			closeFolder(currentTitle);
//			return result;
//	}
//	/**
//	 * open an specified folder
//	 * @param String folderPath - the path of the folder which will be opened
//	 * @return String - the title of the opened folder if success, and null if failure
//	 */
//	public String openFolder(String folderPath){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\OpenFolder.exe";
//		if (gsConnVersion.equalsIgnoreCase("811"))
//			{sToolName = gsToolsPath + "\\Conn811\\OpenFolder.exe";}
//		if (gsConnVersion.equalsIgnoreCase("850"))
//		{sToolName = gsToolsPath + "\\Conn85\\OpenFolder.exe";}
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + folderPath + "\"";
//		VisualReporter.logScriptInfo("Open folder "+folderPath);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}
//		catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return null;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Opening folder executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) return resultStr[1];
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error reason:  "+resultStr[1]);
//		return null;
//	}
//	/**
//	 * refresh folder which has been opened
//	 * @param String folderTitle - the folder which will be refreshed
//	 * @return boolean - true for success, and false for failure
//	 */
//	public boolean refreshView(String folderTitle){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\RefreshView.exe";
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + folderTitle + "\"";
//		VisualReporter.logScriptInfo("Refresh current folder "+folderTitle);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}
//		catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Refreshing folder executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error reason:  "+resultStr[1]);
//		return false;
//	}
//	
//	/**
//	 * reject specified file in DI 
//	 * @param String parentFolderTitle - the folder which the file is located
//	 * @param String fileName - the name of file which will be rejected
//	 * @return boolean - true for success and false for failure
//	 */
//	public boolean _rejectFile_DI(String parentFolderTitle, String fileName){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\RejectFile_DI.exe";
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + parentFolderTitle + "\""+ " " + "\"" + fileName + "\"";
//		VisualReporter.logScriptInfo("Reject file "+fileName+" in " + parentFolderTitle);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Rejecting file executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0)	return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error reason:  "+resultStr[1]);
//		return false;
//	}
//	/**
//	 * reject an specified file in specified path in DI
//	 * @param String parentFolderPath - absolute path of file(not including file's name)
//	 * @param String fileName - name of file which will be rejected
//	 * @return boolean - true for success, and false for failure
//	 */
//	public boolean rejectFileInDI(String parentFolderPath,String fileName){
//		String title=openFolder(parentFolderPath);
//		if (title==null) return false;
//		boolean result=_rejectFile_DI(title,fileName);
//		closeFolder(title);
//		return result;
//	}
//	/**
//	 * remove the libraries from DI
//	 * @param String[] libList - the name list of libraries
//	 * @return boolean - true for success and false for failure
//	 */
//	public boolean _removeLibrary(String[] libList){
//		if (libList==null){
//			VisualReporter.logScriptInfo("removeLibrary() method got a null parameter!");
//			return false;
//		}
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\RemoveLibrary.exe";
//		if (gsConnVersion.equalsIgnoreCase("811"))
//			{sToolName = gsToolsPath + "\\Conn811\\RemoveLibrary.exe";}
//		if (gsConnVersion.equalsIgnoreCase("850"))
//		{sToolName = gsToolsPath + "\\Conn85\\RemoveLibrary.exe";}
//		StringBuffer libStr=new StringBuffer(libList[0]);
//		for (int i=1;i<libList.length;i++)
//			libStr.append(":").append(libList[i]);
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + libStr.toString() + "\"";
//		VisualReporter.logScriptInfo("Remove libraries "+libStr.toString());
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}	
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Removing libraries executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error reason:  "+resultStr[1]);
//		
//		return false;
//	}
//	
//	/**
//	 * remove the libraries from DI. name of libraries contains the full path. such as Lotus Quickr\Personal Files\...
//	 * @param String[] libList - the name list of libraries,name of libraries contains the full path.
//	 * @return boolean - true for success and false for failure
//	 */
//	public boolean _removeLibraryDI(String[] libList){
//		if (libList==null){
//			VisualReporter.logScriptInfo("removeLibrary() method got a null parameter!");
//			return false;
//		}
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\Conn811\\RemoveLibraryFullPath.exe";
//		if (gsConnVersion.equalsIgnoreCase("850"))
//		{sToolName = gsToolsPath + "\\Conn85\\RemoveLibraryFullPath.exe";}
//		StringBuffer libStr=new StringBuffer(libList[0]);
//		for (int i=1;i<libList.length;i++)
//			libStr.append(":").append(libList[i]);
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + libStr.toString() + "\"";
//		VisualReporter.logScriptInfo("Remove libraries "+libStr.toString());
//		VisualReporter.logScriptInfo(sCMD);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}	
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Removing libraries executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error reason:  "+resultStr[1]);
//		
//		return false;
//	}
//	/**
//	 * delete libraries in DI
//	 * @param String[] libList - libraries will be deleted from DI
//	 * @return boolean - true for success,and false for failure
//	 */
//	public boolean delPlacesInDI(String[] libList){
//		//if 811 then reset default string
//		if (gsConnVersion.equalsIgnoreCase("811"))
//			gsLotusQuickr=gsLotusQuickr811;
//		//if 850, the same as811
//		if (gsConnVersion.equalsIgnoreCase("850"))
//			gsLotusQuickr=gsLotusQuickr811;
//		String rootTitle=openFolder(gsLotusQuickr);
//		if (rootTitle==null) return false;
//		boolean result=_removeLibrary(libList);
//		closeFolder(rootTitle);
//		return result;
//	}
//	/**
//	 * rename a file or folder. If you rename a file, the oldFileName must contains .ext name, and newFileName can contain it or not!
//	 * @param String parentFolderTitle - the folder which the file or folder is located
//	 * @param String oldFileName - the file or folder which will be renamed 
//	 * @param String newFileName - the new name of folder or file 
//	 * @param boolean fileOrFolder - true to rename a file, and false to rename a folder 
//	 * @return boolean - true for success, and false for failure
//	 */
//	public boolean _renameFile(String parentFolderTitle,String oldFileName, String newFileName,boolean fileOrFolder){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\RenameFile.exe";
//		if (gsConnVersion.equalsIgnoreCase("811"))
//			{sToolName = gsToolsPath + "\\Conn811\\RenameFile.exe";}
//		if (gsConnVersion.equalsIgnoreCase("850"))
//		{sToolName = gsToolsPath + "\\Conn85\\RenameFile.exe";}
//		String fileFolderStr= fileOrFolder? "True":"False";
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + parentFolderTitle + "\""+ " " + "\"" + oldFileName + "\""+ " " + "\"" + newFileName + "\""+" "+"\""+fileFolderStr+"\"";
//		VisualReporter.logScriptInfo("Rename"+(fileOrFolder?" file ":" folder ")+oldFileName+ " to new name " + newFileName + " in " + parentFolderTitle);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Renaming  executes  "+(result==0 ? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error reason:  "+resultStr[1]);
//		return false;
//	}
//	/**
//	 * test if we can rename an folder successfully, and close the current window whether success or failure
//	 * @param String path - absolute path of folder which will be renamed (not including itsself)
//	 * @param String oldFolderName - folder which will be renamed
//	 * @param String newFolderName - folder's new name 
//	 * @return boolean - ture for success, and false for failure
//	 */
//	public boolean renameFolder(String path,String oldFolderName,String newFolderName){
//		String currentTitle=openFolder(path);
//		if (currentTitle==null){
//			return false;
//		}
//		boolean result=_renameFile(currentTitle, oldFolderName, newFolderName,false);
//		closeFolder(currentTitle);
//		return result;
//	}
//	/**
//	 * test if we can rename an file successfully, and close the current window whether success or failure
//	 * @param String path - absolute path of file which will be reanmed (not including itself)
//	 * @param String oldFileName - file which will be renamed
//	 * @param String newFileName - file's new name
//	 * @return boolean - true for success, and false for failure
//	 */
//	public boolean renameFile(String path,String oldFileName,String newFileName){
//		String currentTitle=openFolder(path);
//		if (currentTitle==null){
//			return false;
//		}
//		boolean result=_renameFile(currentTitle, oldFileName, newFileName,true);
//		closeFolder(currentTitle);
//		return result;
//	}
//	
//	/**
//	 * show or hide the draft/personal/shared views for specified library
//	 * @param String libraryName - the library whose view will be set
//	 * @param boolean showOrNot - true to show views and false to hide views
//	 * @return boolean - true for success and false for failure
//	 * Notes: after operation, it will close the current window!
//	 */
//	public boolean _showLibView(String libraryName, boolean showOrNot){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\ShowLibView810.exe";
//		if (gsConnVersion.equalsIgnoreCase("811"))
//			{sToolName = gsToolsPath + "\\Conn811\\ShowLibView810.exe";}
//		if (gsConnVersion.equalsIgnoreCase("850"))
//		{sToolName = gsToolsPath + "\\Conn85\\ShowLibView810.exe";}
//		String showStr= showOrNot ? "True":"False";
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + libraryName + "\""+ " " + "\"" + showStr + "\"";
//		VisualReporter.logScriptInfo((showOrNot? "Show":"Hide")+" library view in " + libraryName);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo((showOrNot? "Showing":"Hiding")+" views of library executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0)	return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error reason:  "+resultStr[1]);
//		return false;
//	}
//	/**
//	 * set whether or not show library's view
//	 * @param libName - library whose show property will be set
//	 * @param showOrHide - true for showing view, and false for hiding view
//	 * @return boolean - true for success, and false for failure
//	 */
//	public boolean setLibView(String libName,boolean showOrHide){
//		//if 811 then reset default string
//		if (gsConnVersion.equalsIgnoreCase("811"))
//			gsLotusQuickr=gsLotusQuickr811;
//		//if 850, the same as 811
//		if (gsConnVersion.equalsIgnoreCase("850"))
//			gsLotusQuickr=gsLotusQuickr811;
//		String title=openFolder(gsLotusQuickr);
//		if (title==null) return false;
//		return _showLibView(libName, showOrHide);
//	}
//	/**
//	 * set view style for the specified folder or library.
//	 * @param String parentFolderTitle - the folder or library
//	 * @param String viewStyle - the style of viewing, can be "Tiles","Icons","Details","List" (in fact, you can
//	 * specify any string starting with "T" to replace "Tiles", and so on.) 
//	 * @return boolean - true for success and false for failure
//	 */
//	public boolean setViewStyle(String parentFolderTitle, String viewStyle){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\SetViewStyle.exe";
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + parentFolderTitle + "\""+ " " + "\"" + viewStyle + "\"";
//		VisualReporter.logScriptInfo("Set view "+viewStyle+" in " + parentFolderTitle);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Setting view style executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0)	return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error reason:  "+resultStr[1]);
//		return false;
//	}
//	/**
//	 * sort the file list in the specified folder by specified sorting type
//	 * @param String parentFolderTitle - the folder
//	 * @param String sortType - sorting type. (you can get all the sorting types by calling getSortTypes(parentFolderTitle))
//	 * @return boolean - true for success and false for failure
//	 */
//	public boolean _sort(String parentFolderTitle, String sortType){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\Sort.exe";
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + parentFolderTitle + "\""+ " " + "\"" + sortType + "\"";
//		VisualReporter.logScriptInfo("Sort files with  "+sortType+" type in " + parentFolderTitle);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Sorting files executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0)	return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error reason:  "+resultStr[1]);
//		return false;
//	}
//	/**
//	 * This method just supports DI, because 1) there is no 'sort' menu item in context menu in window;
//	 * 2) DI has its own sort function, for example, views are always before folders, and
//	 * folders are always before files. 
//	 * @param String folderPath - absolute path of folder which will be sorted
//	 * @return boolean - true for success, and false for failure
//	 */
//	public boolean sortCurView(String folderPath){
//		String title=openFolder(folderPath);
//		if (title==null) return false;
//		setViewStyle(title, "Details");
//		String[] libList=_getSortType(title);
//		if(libList==null) return false;
//		for (int i=0; i< libList.length;i++){
//			if (!_sort(title, libList[i])) {
//				closeFolder(title);
//				return false;
//			}
//		}
//		closeFolder(title);
//		return true;
//	}
//		
//
//	/*--------------------------------------------------------------------------------
//	 * liujt
//	 * Functions added for test office about DI
//	*/		
//	/**use autoit to add some libraries to DI in office
//	 * @param String wordTitle -  word title for autoit to use to add libraries
//	 * @param String serverURL -  the url of quickr server which libraries are in
//	 * @param String userName -  the name of user who will login the server
//	 * @param String userPWD - the password for the user to login the server
//	 * @param boolean rememberPWDOrNot - remember the password or not 
//	 * @param String[] libraries - the list of libraries
//	 * @return boolean - true for succeessfully adding libraries, and false for failure
//	 */
//	public boolean addPlaces(String wordTitle,String serverURL, String userName, String userPWD, boolean rememberPWDOrNot, String[] libraries){
//		if (libraries==null){
//			VisualReporter.logScriptInfo("addPlaces() method got a null parameter!");
//			return false;
//		}
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\AddPlaces.exe";
//		String rememberStr=rememberPWDOrNot? "True":"False";
//		StringBuffer libStr=new StringBuffer(libraries[0]);
//		for (int i=1;i<libraries.length;i++)
//			libStr.append(":").append(libraries[i]);
//		String sCMD="\"" + sToolName  + "\"" + " " +"\""+wordTitle+"\""+" "+ "\"" + serverURL + "\""+ " " + "\"" + userName + "\""+ " " + "\"" + userPWD + "\""+ " " + "\"" + rememberStr + "\""+ " " + "\"" + libStr.toString() + "\"";
//		VisualReporter.logScriptInfo("Add libraries from places "+serverURL+ " with user: " + userName + " and password: " + userPWD);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//			
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Adding places  execute  "+(result==0 ? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+" Error Reason: "+resultStr[1]);
//		return false;
//	}
//	/**
//	 * use autoit to add the word file to library
//	 * @param String fileTitle - the title of the word file
//	 * @param String fileNewName - the name of the word file which will be added to library
//	 * @param String savePath - the path which the file will be placed
//	 * @param boolean overrideOrNot - if the file has existed in specified library path, true for overriding it and false for not
//	 * @return String -the string of the new word title, if addes file successfully; otherwise, return null 
//	 */
//	public String addToPlace(String fileTitle, String fileNewName, String savePath, boolean overrideOrNot){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\AddToPlace.exe";
//		String overrideStr=overrideOrNot? "True":"False";
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + fileTitle + "\""+ " " + "\"" + fileNewName + "\""+ " " + "\"" + savePath + "\""+ " " + "\"" + overrideStr + "\"";
//		VisualReporter.logScriptInfo("Add file "+fileTitle+ " with new name " + fileNewName + " to library location " + savePath);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return null;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Adding file executes  "+(result==0 ? "Successfully":"Failed"));
//		if (result==0) return resultStr[1];
//		VisualReporter.logScriptInfo("Error Code:  "+result+" Error Reason: "+resultStr[1]);
//		return null;
//	}
//	/**
//	 * use autoit to append some text to the current open file 
//	 * @param String fileTitle - the title of file that has been opened 
//	 * @param String appendentText - the text will be added to the file
//	 * @return String -the string of the word title; if return null, it indicates the fileTitle doesn't existed! 
//	 */
//	public String appendTextToFile(String fileTitle, String appendentText){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\AppendTextToFile.exe";
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + fileTitle + "\""+ " " + "\"" + appendentText + "\"";
//		VisualReporter.logScriptInfo("Append text: "+appendentText+ " to file " +fileTitle );
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//			}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return null;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Appending text to file executes "+(result==0 ? "Successfully":"Failed"));
//		if (result==0) return resultStr[1];
//		VisualReporter.logScriptInfo("Error Code:  "+result+" Error Reason: "+resultStr[1]);
//		return null;
//	}
//	
//	/**
//	 * Remove a file from place via DI
//	 * @param String parentPath - the folder path of the file, should be Lotus Quickr\\...\\
//	 * @param String fileName - the title of file that has been opened 
//	 * @return String -the string of the word title; if return null, it indicates the fileTitle doesn't existed! 
//	 */
//	public String RemoveFileDI(String parentPath, String fileName){
//		String folderTitle = openFolder(parentPath);
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\Conn811\\DeleteFileDI.exe";
//		if (gsConnVersion.equalsIgnoreCase("850"))
//		{sToolName = gsToolsPath + "\\Conn85\\DeleteFileDI.exe";}	
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + folderTitle + "\""+ " " + "\"" + fileName + "\"";
//		VisualReporter.logScriptInfo("Remove file: "+fileName+ " from place " +parentPath );
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//			}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		closeFolder(folderTitle);
//		if (resultStr==null) return null;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Remove file "+(result==0 ? "Successfully":"Failed"));
//		if (result==0) return resultStr[1];
//		VisualReporter.logScriptInfo("Error Code:  "+result+" Error Reason: "+resultStr[1]);
//		return null;
//	}
//	
//	/**
//	 * Verify file exist in parentPath
//	 * @param String parentPath - the folder path of the file, should be Lotus Quickr\\...\\
//	 * @param String fileName - the title of file want to verify
//	 * @return String -the string of the word title; if return null, it indicates the fileTitle doesn't existed! 
//	 */
//	public boolean VerifyFileDI(String parentPath, String fileName){
//		String folderTitle = openFolder(parentPath);
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\Conn811\\VerifyFileDI.exe";
//		if (gsConnVersion.equalsIgnoreCase("850"))
//		{sToolName = gsToolsPath + "\\Conn85\\VerifyFileDI.exe";}
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + folderTitle + "\""+ " " + "\"" + fileName + "\"";
//		VisualReporter.logScriptInfo("Verify file: "+fileName+ " exist in " +parentPath );
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//			}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		closeFolder(folderTitle);
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Verify file: "+(result==0 ? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+" Error Reason: "+resultStr[1]);
//		
//		return false;
//	}
//	/**
//	 * use autoit to append some text to the current open file 
//	 * @param String fileTitle - the title of file that has been opened 
//	 * @param String appendentText - the text will be added to the file
//	 * @return String -the string of the word title; if return null, it indicates the fileTitle doesn't existed! 
//	 */
//	public String appendTextToPPTFile(String fileTitle, String appendentText, String sNewFile){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\AppendTextToPPTFile.exe";
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + fileTitle + "\""+ " " + "\"" + appendentText + "\""+ " " + "\"" + sNewFile + "\"";
//		VisualReporter.logScriptInfo("Append text: "+appendentText+ " to file " +fileTitle );
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//			}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return null;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Appending text to file executes "+(result==0 ? "Successfully":"Failed"));
//		if (result==0) return resultStr[1];
//		VisualReporter.logScriptInfo("Error Code:  "+result+" Error Reason: "+resultStr[1]);
//		return null;
//	}
//	/**
//	 * use autoit to approve the current specified word file
//	 * @param String wordTitle - the current open word file
//	 * @return boolean - true for approving successfully, otherwise, return false 
//	 */
//	public boolean approveFile(String wordTitle){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\ApproveFile.exe";
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + wordTitle + "\"";
//		VisualReporter.logScriptInfo("Approve file with title: "+ wordTitle);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Approving file executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error Reason: "+resultStr[1]);
//		return false;
//	}
//	/**
//	 * cancel check out of the current word file
//	 * @param String wordTitle - the current word file
//	 * @return boolean - true for canceling check-out successfully, otherwise, return false
//	 */
//	public boolean cancelCheckOut(String wordTitle){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\CancelCheckOut.exe";
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + wordTitle + "\"";
//		VisualReporter.logScriptInfo("Cancle checking-out file with title: "+wordTitle);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Canceling to check-out file executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error Reason: "+resultStr[1]);
//		return false;
//	}
//	/**
//	 * check in the current specified word file
//	 * @param String fileTitle - the current opened word file
//	 * @param boolean closeOrNot - after checking in, close the file or not
//	 * @return boolean -true for checking in file successfully, and false for failure
//	 */
//	public boolean checkInFile(String fileTitle, boolean closeOrNot){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\CheckInFile.exe";
//		String closeStr= closeOrNot ? "Ture":"False";
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + fileTitle + "\""+ " " + "\"" + closeStr + "\"";
//		VisualReporter.logScriptInfo("Check in file: "+fileTitle+ " and close status should be: " + closeStr);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//			}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Checking in file executes  "+(result==0 ? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+" Error Reason: "+resultStr[1]);
//		return false;
//	}
//	/**
//	 * check out the current opened file
//	 * @param String fileTitle - the title of current word file 
//	 * @param boolean downloadOrNot - while checking out, download the file from server or not
//	 * @return boolean - true for checking out file successfully, and false for failure
//	 */
//	public boolean checkOutFile(String fileTitle, boolean downloadOrNot){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\CheckOutFile.exe";
//		String downloadStr= downloadOrNot ? "Ture":"False";
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + fileTitle + "\""+ " " + "\"" + downloadStr + "\"";
//		VisualReporter.logScriptInfo("Check out file: "+fileTitle+ " and download status should be: " + downloadStr);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Check out file execute  "+(result==0 ? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+" Error Reason: "+resultStr[1]);
//		return false;
//	}
//	/**
//	 * close the current specified opened word file
//	 * @param String fileTitle - the title of the current file
//	 * @param boolean saveOrNot - while closing file, saven the file or not
//	 * @return boolean - true for successful closing and false for any failure 
//	 */
//	public boolean closeAndSaveFile(String fileTitle, boolean saveOrNot, boolean bCheckIn){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\CloseAndSaveFile.exe";
//		String downloadStr= saveOrNot ? "True":"False";
//		String checkInStr= bCheckIn ? "True":"False";
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + fileTitle + "\""+ " " + "\"" + downloadStr + "\"" + " " + "\"" + checkInStr + "\"";
//		VisualReporter.logScriptInfo("Close file: "+fileTitle+ " and check status before closing is : " + downloadStr);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Closing file executes "+(result==0 ? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+" Error Reason: "+resultStr[1]);
//		return false;
//	}
//	/**
//	 * close the current specified opened word file
//	 * @param String fileTitle - the title of the current file
//	 * @param boolean checkInOrNot - while closing file, check in the file or not
//	 * @return boolean - true for successful closing and false for any failture 
//	 */
//	public boolean closeFile(String fileTitle, boolean checkInOrNot){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\CloseFile.exe";
//		String downloadStr= checkInOrNot ? "True":"False";
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + fileTitle + "\""+ " " + "\"" + downloadStr + "\"";
//		VisualReporter.logScriptInfo("Close file: "+fileTitle+ " and checkin status bbefore closing is : " + downloadStr);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Closing file executes "+(result==0 ? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+" Error Reason: "+resultStr[1]);
//		return false;
//	}
//	/**
//	 * saves the current specified opened word file
//	 * @param String fileTitle - the title of the current file
//	 * @return boolean - true for successful closing and false for any failture 
//	 */
//	public boolean saveWordFile(String fileTitle){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\SaveWordFileDI.exe";
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + fileTitle + "\"";
//		VisualReporter.logScriptInfo("Save file: "+fileTitle);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Saving file executes "+(result==0 ? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+" Error Reason: "+resultStr[1]);
//		return false;
//	}
//	
//	/**
//	 * checks in the currently opened Word file
//	 * @param String fileTitle - the title of the current file
//	 * @return boolean - true for successful closing and false for any failture 
//	 */
//	public boolean checkInWordFile(String fileTitle){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\CheckInWord.exe";
//		if (gsConnVersion.equalsIgnoreCase("811"))
//			{sToolName = gsToolsPath + "\\Conn811\\CheckInWord.exe";}
//		if (gsConnVersion.equalsIgnoreCase("850"))
//		{sToolName = gsToolsPath + "\\Conn85\\CheckInWord.exe";}
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + fileTitle + "\"";
//		VisualReporter.logScriptInfo("Checking in file: "+fileTitle);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Checking in file "+(result==0 ? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+" Error Reason: "+resultStr[1]);
//		return false;
//	}
//	
//	/**
//	 * checks in the currently opened Word file
//	 * @param String fileTitle - the title of the current file
//	 * @return boolean - true for successful closing and false for any failture 
//	 */
//	public boolean publishDraftInOffice(String fileTitle){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\PublishDraftInOffice.exe";
//		if (gsConnVersion.equalsIgnoreCase("811"))
//			{sToolName = gsToolsPath + "\\Conn811\\PublishDraftInOffice.exe";}
//		if (gsConnVersion.equalsIgnoreCase("850"))
//		{sToolName = gsToolsPath + "\\Conn85\\PublishDraftInOffice.exe";}
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + fileTitle + "\"";
//		VisualReporter.logScriptInfo("Publish file: "+fileTitle);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("publish file "+(result==0 ? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+" Error Reason: "+resultStr[1]);
//		return false;
//	}
//	/**
//	 * checks in the currently opened Excel file
//	 * @param String fileTitle - the title of the current file
//	 * @return boolean - true for successful closing and false for any failture 
//	 */
//	public boolean checkInExcelFile(String fileTitle){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\CheckInExcel.exe";
//		if (gsConnVersion.equalsIgnoreCase("811"))
//			{sToolName = gsToolsPath + "\\Conn811\\CheckInExcel.exe";}
//		if (gsConnVersion.equalsIgnoreCase("850"))
//		{sToolName = gsToolsPath + "\\Conn85\\CheckInExcel.exe";}
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + fileTitle + "\"";
//		VisualReporter.logScriptInfo("Checking in file: "+fileTitle);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Checking in file "+(result==0 ? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+" Error Reason: "+resultStr[1]);
//		return false;
//	}
//	/**
//	 * checks in the currently opened Powerpoint file
//	 * @param String fileTitle - the title of the current file
//	 * @return boolean - true for successful closing and false for any failture 
//	 */
//	public boolean checkInPPTFile(String fileTitle){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\CheckInPPT.exe";
//		if (gsConnVersion.equalsIgnoreCase("811"))
//			{sToolName = gsToolsPath + "\\Conn811\\CheckInPPT.exe";}
//		if (gsConnVersion.equalsIgnoreCase("850"))
//		{sToolName = gsToolsPath + "\\Conn85\\CheckInPPT.exe";}
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + fileTitle + "\"";
//		VisualReporter.logScriptInfo("Checking in file: "+fileTitle);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Checking in file "+(result==0 ? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+" Error Reason: "+resultStr[1]);
//		return false;
//	}
//	/**
//	 * checks out the currently opened Word file
//	 * @param String fileTitle - the title of the current file
//	 * @return boolean - true for successful closing and false for any failture 
//	 */
//	public boolean checkOutWordFile(String fileTitle){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\CheckOutWord.exe";
//		if (gsConnVersion.equalsIgnoreCase("811"))
//			{sToolName = gsToolsPath + "\\Conn811\\CheckOutWord.exe";}
//		if (gsConnVersion.equalsIgnoreCase("850"))
//		{sToolName = gsToolsPath + "\\Conn85\\CheckOutWord.exe";}
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + fileTitle + "\"";
//		VisualReporter.logScriptInfo("Checking out file: "+fileTitle);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Checking out file "+(result==0 ? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+" Error Reason: "+resultStr[1]);
//		return false;
//	}
//	/**
//	 * checks out the currently opened Excel file
//	 * @param String fileTitle - the title of the current file
//	 * @return boolean - true for successful closing and false for any failture 
//	 */
//	public boolean checkOutExcelFile(String fileTitle){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\CheckOutExcel.exe";
//		if (gsConnVersion.equalsIgnoreCase("811"))
//			{sToolName = gsToolsPath + "\\Conn811\\CheckOutExcel.exe";}
//		if (gsConnVersion.equalsIgnoreCase("850"))
//		{sToolName = gsToolsPath + "\\Conn85\\CheckOutExcel.exe";}
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + fileTitle + "\"";
//		VisualReporter.logScriptInfo("Checking out file: "+fileTitle);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Checking out file "+(result==0 ? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+" Error Reason: "+resultStr[1]);
//		return false;
//	}
//	/**
//	 * checks out the currently opened Powerpoint file
//	 * @param String fileTitle - the title of the current file
//	 * @return boolean - true for successful closing and false for any failture 
//	 */
//	public boolean checkOutPPTFile(String fileTitle){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\CheckOutPPT.exe";
//		if (gsConnVersion.equalsIgnoreCase("811"))
//			{sToolName = gsToolsPath + "\\Conn811\\CheckOutPPT.exe";}
//		if (gsConnVersion.equalsIgnoreCase("850"))
//		{sToolName = gsToolsPath + "\\Conn85\\CheckOutPPT.exe";}
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + fileTitle + "\"";
//		VisualReporter.logScriptInfo("Checking out file: "+fileTitle);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Checking out file "+(result==0 ? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+" Error Reason: "+resultStr[1]);
//		return false;
//	}
//		
//	/**
//	 * get the given word file's url link in its quickr server 
//	 * @param String fileTitle - the current given word file
//	 * @return String - the url string of the specified word file, otherwise,return null
//	 */
//	public String copyOfficeFileLink(String fileTitle){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\CopyOfficeFileLink.exe";
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + fileTitle + "\"";
//		VisualReporter.logScriptInfo("copy link from file: "+fileTitle);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}	catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return null;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Copying link of file executes  "+(result==0 ? "Successfully":"Failed"));
//		if (result==0) return resultStr[1];
//		VisualReporter.logScriptInfo("Error Code:  "+result+" Error Reason: "+resultStr[1]);
//		return null;
//	}
//	/**
//	 * start word application, meanwhile, create  a new word file unnamed
//	 * @return String - the title of new word file, and null if failure exists
//	 */
//	public String newWordFile(){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\NewWordFile.exe";
//		String sCMD="\"" + sToolName  + "\"" ;
//		VisualReporter.logScriptInfo("Create new word file ");
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return null;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Creating word file executes  "+(result==0 ? "Successfully":"Failed"));
//		if (result==0) return resultStr[1];
//		VisualReporter.logScriptInfo("Error Code:  "+result+" Error Reason: "+resultStr[1]);
//		return null;
//	}
//	/**
//	 * open specified file from quickr server
//	 * @param String fileName - the name of word file will be opened from quickr server 
//	 * @param String filePath - the path of the specified file
//	 * @return String - the tile of the opened file if operation is successful; null if failure existes.
//	 */
//	public String openPlaceFile(String fileName, String filePath){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\OpenPlaceFile.exe";
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + fileName + "\""+ " " + "\"" + filePath + "\"";
//		VisualReporter.logScriptInfo("Open "+fileName+ " file in " + filePath + " and check out it!");
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return null;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Opening file from place executes  "+(result==0 ? "Successfully":"Failed"));
//		if (result==0) return resultStr[1];
//		VisualReporter.logScriptInfo("Error Code:  "+result+" Error Reason: "+resultStr[1]);
//		return null;
//	}
//	/**
//	 * reject the given file
//	 * @param String wordTitle - the title of the word file will be rejected
//	 * @return boolean - true for successful rejection, while false for failure
//	 */
//	public boolean rejectFile(String wordTitle){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\RejectFile.exe";
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + wordTitle + "\"";
//		VisualReporter.logScriptInfo("Reject file with title: "+ wordTitle);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Rejecting file executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0)	return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error reason:  "+resultStr[1]);
//		return false;
//	}
//	/**
//	 * save the current file to the library with a different name
//	 * @param String fileTitle - the title of the current opened file
//	 * @param String fileNewName - the new name of the current opened file 
//	 * @param String savePath - the path which the new file will be placed
//	 * @param boolean overrideOrNot - if file has existed, override it or not
//	 * @return String - the title of the new saved file if save succeeds, and null if failure appears
//	 */
//	public String saveAsToPlace(String fileTitle, String fileNewName, String savePath, boolean overrideOrNot){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\SaveAsToPlace.exe";
//		String overrideStr=overrideOrNot? "True":"False";
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + fileTitle + "\""+ " " + "\"" + fileNewName + "\""+ " " + "\"" + savePath + "\""+ " " + "\"" + overrideStr + "\"";
//		VisualReporter.logScriptInfo("Save file "+fileTitle+ " with new name " + fileNewName + " to library location " + savePath);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return null;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Saving file executes  "+(result==0 ? "Successfully":"Failed"));
//		if (result==0) return resultStr[1];
//		VisualReporter.logScriptInfo("Error Code:  "+result+" Error Reason: "+resultStr[1]);
//		return null;
//	}
//		
//	/**
//	 * Check Whether Office has been installed on the testing machine , or not
//	 * @return true 	: Yes
//	 * 		   false	: No
//	 */
//	public boolean checkOfficeInstalled()
//	{
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\checkOfficeInstalled.exe";
//		String sCMD="\"" + sToolName  + "\"" ;
//		VisualReporter.logScriptInfo("Check Office Installed already , or Not ?");
//		//
//		java.lang.Process p;
//		String bResult;
//		try
//		{
//			p= Runtime.getRuntime().exec(sCMD);
//			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
//			bResult = br.readLine();
//			p.waitFor();
//			
//			if(bResult.equals("1"))
//				return true;
//			else
//				return false;
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		
//		return false;
//		
//	}
//	
//	
//	/**
//	 * Right click lin name to display the property panel
//	 * @param strLibName library name to right click
//	 * @param strServerURL , like this http://youhost.cn.ibm.com
//	 * @return 	true : 
//	 * 			false:
//	 * 
//	 */
//	public boolean displayLibPropertyPanel(String strLibName,String strServerURL)
//	{
//		//if 811 then reset default string
//		if (gsConnVersion.equalsIgnoreCase("811"))
//			gsLotusQuickr=gsLotusQuickr811;
//		//if 850, the same as 811
//		if (gsConnVersion.equalsIgnoreCase("850"))
//			gsLotusQuickr=gsLotusQuickr811;
//		String rootTitle=openFolder(gsLotusQuickr);
//		if (rootTitle==null) return false;
//		
//		String[] strPart = strServerURL.split("/");
//		String[] hostName = strPart[2].split("\\.");
//		strLibName = strLibName + " " + "on" + " " + hostName[0];
//		
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\displayPropertyPanel.exe";
//		
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + strLibName + "\"";
//		VisualReporter.logScriptInfo("Right click library name, and show property panel");
//		
//		//
//		java.lang.Process p;
//		String bResult;
//		try
//		{
//			p= Runtime.getRuntime().exec(sCMD);
//			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
//			bResult = br.readLine();
//			p.waitFor();
//			
//			if(bResult.equals("0"))
//			{
//				return true;
//			}
//			else
//			{
//				VisualReporter.logScriptInfo(bResult);
//				return false;
//			}
//
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		
//		return false;
//	}
//	
//	
//	/**
//	 * Double click a file in open from place dialog, then close it.
//	 * 
//	 * @param strFileName: like Word_123.doc , or Excel124.xls , or PPT886.ppt 
//	 * @param strPlaceName: like BVT_123
//	 * @param strServerURL : like http://youhost.cn.ibm.com
//	 * 
//	 * @return 
//	 */
//	public boolean openWordFileFromPlace(String strFileName, String strPlaceName, String strServerURL)
//	{
//		//if 811 then reset default string
//		if (gsConnVersion.equalsIgnoreCase("811"))
//			gsLotusQuickr=gsLotusQuickr811;
//		//if 850, the same as 811
//		if (gsConnVersion.equalsIgnoreCase("850"))
//			gsLotusQuickr=gsLotusQuickr811;
//		String rootTitle = gsLotusQuickr;
//		
//		String[] strPart = strServerURL.split("/");
//		String[] hostName = strPart[2].split("\\.");
//		strPlaceName = rootTitle + "\\" + strPlaceName + " " + "on" + " " + hostName[0]; 
//	
//		
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\openWordFileFromLibrary.exe";
//		
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + strFileName + "\""+ " " + "\"" + strPlaceName + "\"";
//		VisualReporter.logScriptInfo("Double click a word file in open from place dialog, then close it.");
//		
//		//
//		java.lang.Process p;
//		String bResult;
//		try
//		{
//			p= Runtime.getRuntime().exec(sCMD);
//			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
//			bResult = br.readLine();
//			p.waitFor();
//			
//			if(bResult.equals("0"))
//			{
//				return true;
//			}
//			else
//			{
//				VisualReporter.logScriptInfo(bResult);
//				return false;
//			}
//
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		
//		return false;
//	}
//	
//	/**
//	 * Double click a file in open from place dialog, then close it.
//	 * 
//	 * @param strFileName: like Word_123.doc , or Excel124.xls , or PPT886.ppt 
//	 * @param strPlaceName: like BVT_123
//	 * @param strServerURL : like http://youhost.cn.ibm.com
//	 * 
//	 * @return 
//	 */
//	public boolean openExcelFileFromPlace(String strFileName, String strPlaceName, String strServerURL)
//	{
//		//if 811 then reset default string
//		if (gsConnVersion.equalsIgnoreCase("811"))
//			gsLotusQuickr=gsLotusQuickr811;
//		//if 850, the same as 811
//		if (gsConnVersion.equalsIgnoreCase("850"))
//			gsLotusQuickr=gsLotusQuickr811;
//		String rootTitle = gsLotusQuickr;
//		
//		String[] strPart = strServerURL.split("/");
//		String[] hostName = strPart[2].split("\\.");
//		strPlaceName = rootTitle + "\\" + strPlaceName + " " + "on" + " " + hostName[0]; 
//	
//		
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\openExcelFileFromLibrary.exe";
//		
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + strFileName + "\""+ " " + "\"" + strPlaceName + "\"";
//		VisualReporter.logScriptInfo("Double click a Excel file in open from place dialog, then close it.");
//		
//		//
//		java.lang.Process p;
//		String bResult;
//		try
//		{
//			p= Runtime.getRuntime().exec(sCMD);
//			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
//			bResult = br.readLine();
//			p.waitFor();
//			
//			if(bResult.equals("0"))
//			{
//				return true;
//			}
//			else
//			{
//				VisualReporter.logScriptInfo(bResult);
//				return false;
//			}
//
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		
//		return false;
//		
//	}
//	
//	/**
//	 * Double click a file in open from place dialog, then close it.
//	 * 
//	 * @param strFileName: like Word_123.doc , or Excel124.xls , or PPT886.ppt 
//	 * @param strPlaceName: like BVT_123
//	 * @param strServerURL : like http://youhost.cn.ibm.com
//	 * 
//	 * @return 
//	 */
//	public boolean openPPTFileFromPlace(String strFileName, String strPlaceName, String strServerURL)
//	{
//		//if 811 then reset default string
//		if (gsConnVersion.equalsIgnoreCase("811"))
//			gsLotusQuickr=gsLotusQuickr811;
//		//if 850, the same as 811
//		if (gsConnVersion.equalsIgnoreCase("850"))
//			gsLotusQuickr=gsLotusQuickr811;
//		String rootTitle = gsLotusQuickr;
//		
//		String[] strPart = strServerURL.split("/");
//		String[] hostName = strPart[2].split("\\.");
//		strPlaceName = rootTitle + "\\" + strPlaceName + " " + "on" + " " + hostName[0]; 
//		
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\openPPTFileFromLibrary.exe";
//		
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + strFileName + "\""+ " " + "\"" + strPlaceName + "\"";
//		VisualReporter.logScriptInfo("Double click a PowerPoint file in open from place dialog, then close it.");
//		
//		//
//		java.lang.Process p;
//		String bResult;
//		try
//		{
//			p= Runtime.getRuntime().exec(sCMD);
//			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
//			bResult = br.readLine();
//			p.waitFor();
//			
//			if(bResult.equals("0"))
//			{
//				return true;
//			}
//			else
//			{
//				VisualReporter.logScriptInfo(bResult);
//				return false;
//			}
//
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		
//		return false;
//	}
//	
//	
//	/**
//	 * 
//	 * 
//	 * 
//	 * 
//	 * 
//	 */
//	public boolean createAndCheckInDoc(String strFileName, String strPlaceName, String strServerURL)
//	{
//		//if 811 then reset default string
//		if (gsConnVersion.equalsIgnoreCase("811"))
//			gsLotusQuickr=gsLotusQuickr811;
//		//if 850, the same as 811
//		if (gsConnVersion.equalsIgnoreCase("850"))
//			gsLotusQuickr=gsLotusQuickr811;
//		String rootTitle = gsLotusQuickr;
//		
//		String[] strPart = strServerURL.split("/");
//		String[] hostName = strPart[2].split("\\.");
//		strPlaceName = rootTitle + "\\" + strPlaceName + " " + "on" + " " + hostName[0]; 
//		
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\createAndCheckInDoc.exe";
//		
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + strFileName + "\""+ " " + "\"" + strPlaceName + "\"";
//		VisualReporter.logScriptInfo("Create a word file, check in, and then close it");
//		
//		//
//		java.lang.Process p;
//		String bResult;
//		try
//		{
//			p= Runtime.getRuntime().exec(sCMD);
//			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
//			bResult = br.readLine();
//			p.waitFor();
//			
//			if(bResult.equals("0"))
//			{
//				return true;
//			}
//			else
//			{
//				VisualReporter.logScriptInfo(bResult);
//				return false;
//			}
//
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		
//		
//		return false;
//	}
//	
//	/**
//	 * 
//	 * 
//	 * 
//	 * 
//	 */
//	public boolean changeDocType(String strPlace, String strParentFolder,String strFileName, String strDocTypeName)
//	{
//		//if 811 then reset default string
//		if (gsConnVersion.equalsIgnoreCase("811"))
//			gsLotusQuickr=gsLotusQuickr811;
//		//if 850, the same as 811
//		if (gsConnVersion.equalsIgnoreCase("850"))
//			gsLotusQuickr=gsLotusQuickr811;
//		String strFullPath = gsLotusQuickr + "\\" + strPlace + "\\" + strParentFolder;
//		openFolder(strFullPath);
//		
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\changeDocType.exe";
//		
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + strParentFolder + "\""+ " " + "\"" + strFileName + "\"" + " " + "\"" +strDocTypeName + "\"";
//		VisualReporter.logScriptInfo("Change document type for a document");
//		
//		//
//		java.lang.Process p;
//		String bResult;
//		try
//		{
//			p= Runtime.getRuntime().exec(sCMD);
//			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
//			bResult = br.readLine();
//			p.waitFor();
//			
//			if(bResult != null)
//			{
//				if(bResult.equals("0"))
//				{
//					return true;
//				}
//				else
//				{
//					VisualReporter.logScriptInfo(bResult);
//					return false;
//				}
//			}
//			
//
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		
//		return false;
//	}
//	
//	/**
//	 * 
//	 * 
//	 * 
//	 * 
//	 */
//	public boolean checkMonitor()
//	{
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\checkMonitor.exe";
//		
//		String sCMD="\"" + sToolName  + "\"";
//		VisualReporter.logScriptInfo("Check Monitor Icon");
//		
//		//
//		java.lang.Process p;
//		String bResult;
//		try
//		{
//			
//			p= Runtime.getRuntime().exec(sCMD);
//			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
//			bResult = br.readLine();
//			p.waitFor();
//			if(bResult!=null)
//			{
//				if(bResult.equals("0"))
//				{
//					
//					return true;
//				}
//				else
//				{
//					VisualReporter.logScriptInfo(bResult);
//					
//					return false;
//				}
//			}
//			
//			
//		}catch(Exception e){
//			
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		
//		return false;
//	}
//	
//	/*
//	 * 
//	 * 
//	 */
//	public boolean createNewDocumentByDocType(String strParentFolder, String strFileName, String strDocTypeName)
//	{
//		//if 811 then reset default string
//		if (gsConnVersion.equalsIgnoreCase("811"))
//			gsLotusQuickr=gsLotusQuickr811;
//		//if 850, the same as 811
//		if (gsConnVersion.equalsIgnoreCase("850"))
//			gsLotusQuickr=gsLotusQuickr811;
//		String strFullPath = gsLotusQuickr + "\\" + strParentFolder;
//		openFolder(strFullPath);
//		
//		
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\createDocumentByDocType.exe";
//		
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + strParentFolder + "\""+ " " + "\"" + strFileName + "\"" + " " + "\"" +strDocTypeName + "\"";
//		VisualReporter.logScriptInfo("Create a document by Document Type");
//		
//		//
//		java.lang.Process p;
//		String bResult;
//		try
//		{
//			p= Runtime.getRuntime().exec(sCMD);
//			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
//			bResult = br.readLine();
//			p.waitFor();
//			
//			if(bResult.equals("0"))
//			{
//				return true;
//			}
//			else
//			{
//				VisualReporter.logScriptInfo(bResult);
//				return false;
//			}
//
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		
//		return false;
//	}
//	
//	/**
//	 * 
//	 * 
//	 * 
//	 */
//	public boolean sendLinkByOffice(String strServerURL, String strParentFolder,String strFileName)
//	{
//		//if 811 then reset default string
//		if (gsConnVersion.equalsIgnoreCase("811"))
//			gsLotusQuickr=gsLotusQuickr811;
//		//if 850, the same as 811
//		if (gsConnVersion.equalsIgnoreCase("850"))
//			gsLotusQuickr=gsLotusQuickr811;
//		String strFullPath = gsLotusQuickr + "\\" + strServerURL;
//		openFolder(strFullPath);
//		
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\sendLinkByOffice.exe";
//		
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + strParentFolder + "\""+ " " + "\"" + strFileName + "\"";
//		VisualReporter.logScriptInfo("Open a word document, then send its link");
//		
//		//
//		java.lang.Process p;
//		String bResult;
//		try
//		{
//			p= Runtime.getRuntime().exec(sCMD);
//			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
//			bResult = br.readLine();
//			p.waitFor();
//			
//			if(bResult.equals("0"))
//			{
//				return true;
//			}
//			else
//			{
//				VisualReporter.logScriptInfo(bResult);
//				return false;
//			}
//
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		
//		return false;
//	}
//	
//	
//	/*
//	 * 
//	 * 
//	 * 
//	 */
//	public boolean sendLinkByExplorer(String strParentFolder, String strFileName)
//	{
//		//if 811 then reset default string
//		if (gsConnVersion.equalsIgnoreCase("811"))
//			gsLotusQuickr=gsLotusQuickr811;
//		//if 850, the same as 811
//		if (gsConnVersion.equalsIgnoreCase("850"))
//			gsLotusQuickr=gsLotusQuickr811;
//		String strFullPath = gsLotusQuickr + "\\" + strParentFolder;
//		openFolder(strFullPath);
//		
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\sendLinkByExplorer.exe";
//		
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + strParentFolder + "\""+ " " + "\"" + strFileName + "\"";
//		VisualReporter.logScriptInfo("Send document's link via context meu");
//		
//		//
//		java.lang.Process p;
//		String bResult;
//		try
//		{
//			p= Runtime.getRuntime().exec(sCMD);
//			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
//			bResult = br.readLine();
//			p.waitFor();
//			
//			if(bResult.equals("0"))
//			{
//				return true;
//			}
//			else
//			{
//				VisualReporter.logScriptInfo(bResult);
//				return false;
//			}
//
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		
//		return false;
//	}
//	
//	/**
//	 * 
//	 * 
//	 * 
//	 */
//	public boolean showView(String strPlaceName)
//	{
//		
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\showView.exe";
//		
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + strPlaceName + "\"";
//		
//		VisualReporter.logScriptInfo("Check a 'Show View' check box for a specific place");
//		VisualReporter.logScriptInfo(strPlaceName);
//		//
//		java.lang.Process p;
//		String bResult;
//		try
//		{
//			p= Runtime.getRuntime().exec(sCMD);
//			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
//			bResult = br.readLine();
//			p.waitFor();
//			
//			if(bResult.equals("0"))
//			{
//				return true;
//			}
//			else
//			{
//				VisualReporter.logScriptInfo(bResult);
//				return false;
//			}
//
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		
//		return false;
//	}
//	
//	/**
//	 * 
//	 * 
//	 */
//	public boolean checkWorkingDraftView(String strPlaceName, String strServerURL)
//	{
//		String[] strPart = strServerURL.split("/");
//		String[] hostName = strPart[2].split("\\.");
//		strPlaceName = strPlaceName + " " + "on" + " " + hostName[0]; 
//		
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\checkWorkingDraftView.exe";
//		
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + strPlaceName + "\"";
//		
//		VisualReporter.logScriptInfo("check working drafts view in exlporer");
//		
//		//
//		java.lang.Process p;
//		String bResult;
//		try
//		{
//			p= Runtime.getRuntime().exec(sCMD);
//			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
//			bResult = br.readLine();
//			p.waitFor();
//			
//			if(bResult.equals("0"))
//			{
//				return true;
//			}
//			else
//			{
//				VisualReporter.logScriptInfo(bResult);
//				return false;
//			}
//
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		
//		return false;
//	}
//	
//	/**
//	 * 
//	 * 
//	 */
//	public boolean checkSubmittedDraftView(String strPlaceName, String strServerURL)
//	{
//		String[] strPart = strServerURL.split("/");
//		String[] hostName = strPart[2].split("\\.");
//		strPlaceName = strPlaceName + " " + "on" + " " + hostName[0]; 
//		
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\checkSubmitedDraftView.exe";
//		
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + strPlaceName + "\"";
//		
//		VisualReporter.logScriptInfo("check submitted drafts view in exlporer");
//		
//		//
//		java.lang.Process p;
//		String bResult;
//		try
//		{
//			p= Runtime.getRuntime().exec(sCMD);
//			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
//			bResult = br.readLine();
//			p.waitFor();
//			
//			if(bResult.equals("0"))
//			{
//				return true;
//			}
//			else
//			{
//				VisualReporter.logScriptInfo(bResult);
//				return false;
//			}
//
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		
//		return false;
//	}
//	
//	/**
//	 * 
//	 * 
//	 * 
//	 */
//	public boolean checkSharedView(String strPlaceName, String strServerURL)
//	{
//		String[] strPart = strServerURL.split("/");
//		String[] hostName = strPart[2].split("\\.");
//		strPlaceName = strPlaceName + " " + "on" + " " + hostName[0]; 
//		
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\checkSharedView.exe";
//		
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + strPlaceName + "\"";
//		
//		VisualReporter.logScriptInfo("check shared view in exlporer");
//		
//		//
//		java.lang.Process p;
//		String bResult;
//		try
//		{
//			p= Runtime.getRuntime().exec(sCMD);
//			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
//			bResult = br.readLine();
//			p.waitFor();
//			
//			if(bResult.equals("0"))
//			{
//				return true;
//			}
//			else
//			{
//				VisualReporter.logScriptInfo(bResult);
//				return false;
//			}
//
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		
//		return false;
//	}
//	
//	/**
//	 * 
//	 * 
//	 * 
//	 */
//	public boolean checkPersonalView(String strPlaceName, String strServerURL)
//	{
//		String[] strPart = strServerURL.split("/");
//		String[] hostName = strPart[2].split("\\.");
//		strPlaceName = strPlaceName + " " + "on" + " " + hostName[0]; 
//		
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\checkPersonalView.exe";
//		
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + strPlaceName + "\"";
//		
//		VisualReporter.logScriptInfo("check Personal view in exlporer");
//		
//		//
//		java.lang.Process p;
//		String bResult;
//		try
//		{
//			p= Runtime.getRuntime().exec(sCMD);
//			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
//			bResult = br.readLine();
//			p.waitFor();
//			
//			if(bResult.equals("0"))
//			{
//				return true;
//			}
//			else
//			{
//				VisualReporter.logScriptInfo(bResult);
//				return false;
//			}
//
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		
//		return false;
//	}
//	
//	/**
//	 * 
//	 * 
//	 * 
//	 */
//	public boolean checkDraftToApproveView(String strPlaceName)
//	{
//		String strURL = VisualReporter.gsVisualReporterBaseURL;
//		String[] strPart = strURL.split("/");
//		String[] hostName = strPart[2].split("\\.");
//		strPlaceName = strPlaceName + " " + "on" + " " + hostName[0]; 
//		
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\checkDraftToApproveView.exe";
//		
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + strPlaceName + "\"";
//		
//		VisualReporter.logScriptInfo("check draft2Approve view in exlporer");
//		
//		//
//		java.lang.Process p;
//		String bResult;
//		try
//		{
//			p= Runtime.getRuntime().exec(sCMD);
//			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
//			bResult = br.readLine();
//			p.waitFor();
//			
//			if(bResult.equals("0"))
//			{
//				return true;
//			}
//			else
//			{
//				VisualReporter.logScriptInfo(bResult);
//				return false;
//			}
//
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		
//		return false;
//	}
//	
//	/**
//	 * 
//	 * 
//	 * 
//	 */
//	public boolean addToPlaceByContextMenu(String strServerURL, String strFileName, String strPlace)
//	{
//		//if 811 then reset default string
//		if (gsConnVersion.equalsIgnoreCase("811"))
//			gsLotusQuickr=gsLotusQuickr811;
//		//if 850, the same as 811
//		if (gsConnVersion.equalsIgnoreCase("850"))
//			gsLotusQuickr=gsLotusQuickr811;
//		String[] strPart = strServerURL.split("/");
//		String[] hostName = strPart[2].split("\\.");
//		strPlace = gsLotusQuickr + "\\" + strPlace + " " + "on" + " " + hostName[0]; 
//		
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\addToPlaceByContextMenu.exe";
//		
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + "WordDocs" + "\"" + " " + "\"" + strFileName + "\"" + " " + "\"" + strPlace + "\"";
//		
//		VisualReporter.logScriptInfo("Add file to place by context menu");
//		
//		//
//		java.lang.Process p;
//		String bResult;
//		try
//		{
//			p= Runtime.getRuntime().exec(sCMD);
//			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
//			bResult = br.readLine();
//			p.waitFor();
//			
//			if(bResult.equals("0"))
//			{
//				return true;
//			}
//			else
//			{
//				VisualReporter.logScriptInfo(bResult);
//				return false;
//			}
//
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		
//		return false;
//	}
//	
//	/**
//	 * 
//	 * 
//	 * 
//	 */
//	public boolean approveDraftFile(String sreServerURL, String strFileName)
//	{
//		//
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\approveDraftFile.exe";
//		
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + sreServerURL + "\"" + " " + "\"" + strFileName + "\"";
//		
//		VisualReporter.logScriptInfo("check  approve draft file");
//		
//		//
//		java.lang.Process p;
//		String bResult;
//		try
//		{
//			p= Runtime.getRuntime().exec(sCMD);
//			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
//			bResult = br.readLine();
//			p.waitFor();
//			
//			if(bResult.equals("0"))
//			{
//				return true;
//			}
//			else
//			{
//				VisualReporter.logScriptInfo(bResult);
//				return false;
//			}
//
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		return false;
//	}
//	
//	/**
//	 * 
//	 * 
//	 * 
//	 */
//	
//	public boolean rejectDraftFile(String sreServerURL, String strFileName)
//	{
//		//
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\rejectDraftFile.exe";
//		
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + sreServerURL + "\"" + " " + "\"" + strFileName + "\"";
//		
//		VisualReporter.logScriptInfo("check  approve draft file");
//		
//		//
//		java.lang.Process p;
//		String bResult;
//		try
//		{
//			p= Runtime.getRuntime().exec(sCMD);
//			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
//			bResult = br.readLine();
//			p.waitFor();
//			
//			if(bResult.equals("0"))
//			{
//				return true;
//			}
//			else
//			{
//				VisualReporter.logScriptInfo(bResult);
//				return false;
//			}
//
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		return false;
//	}
//	
//	/**
//	 * 
//	 * 
//	 * 
//	 */
//	public boolean newVersion(String strParentFolder, String strFileName, String strVersionName)
//	{
//		//if 811 then reset default string
//		if (gsConnVersion.equalsIgnoreCase("811"))
//			gsLotusQuickr=gsLotusQuickr811;
//		//if 850, the same as 811
//		if (gsConnVersion.equalsIgnoreCase("850"))
//			gsLotusQuickr=gsLotusQuickr811;
//		String strFullPath = gsLotusQuickr + "\\" + strParentFolder;
//		openFolder(strFullPath);
//		
//		
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\newVersion.exe";
//		
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + strParentFolder + "\""+ " " + "\"" + strFileName + "\"" + " " + "\"" +strVersionName + "\"";
//		VisualReporter.logScriptInfo("Create a new version for the specifiv file");
//		
//		//
//		java.lang.Process p;
//		String bResult;
//		try
//		{
//			p= Runtime.getRuntime().exec(sCMD);
//			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
//			bResult = br.readLine();
//			p.waitFor();
//			
//			if(bResult.equals("0"))
//			{
//				return true;
//			}
//			else
//			{
//				VisualReporter.logScriptInfo(bResult);
//				return false;
//			}
//
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		
//		return false;
//	}
//	
//	
//	/**
//	 * 
//	 * 
//	 * 
//	 */
//	public boolean checkFileProperty(String strParentFolder, String strFileName)
//	{
//		//if 811 then reset default string
//		if (gsConnVersion.equalsIgnoreCase("811"))
//			gsLotusQuickr=gsLotusQuickr811;
//		//if 850, the same as 811
//		if (gsConnVersion.equalsIgnoreCase("850"))
//			gsLotusQuickr=gsLotusQuickr811;
//		String strFullPath = gsLotusQuickr + "\\" + strParentFolder;
//		openFolder(strFullPath);
//		
//		
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\checkFileProperty.exe";
//		
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + strParentFolder + "\""+ " " + "\"" + strFileName + "\"";
//		VisualReporter.logScriptInfo("Check Property Panel for the specifiv file");
//		
//		//
//		java.lang.Process p;
//		String bResult;
//		try
//		{
//			p= Runtime.getRuntime().exec(sCMD);
//			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
//			bResult = br.readLine();
//			p.waitFor();
//			
//			if(bResult.equals("0"))
//			{
//				return true;
//			}
//			else
//			{
//				VisualReporter.logScriptInfo(bResult);
//				return false;
//			}
//
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		
//		return false;
//	}
//	
//	/*--------------------------------------------------------------------------------
//	 * Functions used to complete the communication between java and .exe process, and 
//	 * resolve the return parameters to java method.  
//	*/		
//	/**
//	 * get the content from system clipboard, and resolve it into an array of string, then return it
//	 * @return String[] - list of string pasting from system's clipboard
//	 */
//	public String[]  getResultString(){
//		String[] resultStr=null;
//		Clipboard clipboard=java.awt.Toolkit.getDefaultToolkit().getSystemClipboard();
//		Transferable contents = clipboard.getContents(this);
//        DataFlavor flavor = DataFlavor.stringFlavor;
//        if(contents.isDataFlavorSupported(flavor)){
//        	try{
//        		String str=(String)contents.getTransferData(flavor);
//        		if (str!=null){
//        			resultStr=str.split(""+(char)9);//":");
//        			/*for(int i=0;i<resultStr.length;i++){
//        				String tmp=resultStr[i].replaceAll("xy", ":");
//        				resultStr[i]=tmp.replaceAll("xz", "x");
//        			}*/
//        		}
//        	}catch(Exception e){  
//        		VisualReporter.logScriptInfo("Java Methods get unsupported result from AutoIt script"+e.toString());
//        	}
//        }
//        return resultStr;
//	}
//
//	/**
//	 * After setting firefox preferred language to en, then it's necessary to delete the configuration
//	 * file produced while seeting its preferred language. Now support XP/2000, and not for windows 95/98/Me
//	 */
//	public void clearUpFF(){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\UITools";
//		String sToolName = gsToolsPath + "\\ClearUpFF.exe";
//		String sCMD="\"" + sToolName  + "\"";
//		if( !(new java.io.File(sToolName)).exists() )
//		{
//			Logfuncs.errorHandler("could not find tool: " + sToolName);
//			return;
//		}
//		VisualReporter.logScriptInfo("Begin to restore firefox default language setting");
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return ;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Restore firefox default language setting  "+(result==0? "Successfully":"Failed"));
//		if (result!=0) {
//			VisualReporter.logScriptInfo("Error Code:  "+result+", Error Reason: "+resultStr[1]);
//		}
//	}
//	
//	/**
//	 * return IE version: like "7.0.5730.13"/"7.0.5730.11"/"8.0.6001.18702" and so on
//	 * @return
//	 */
//	public String getIEVersion(){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\UITools";
//		String sToolName = gsToolsPath + "\\GetIEVersion.exe";
//		if( !(new java.io.File(sToolName)).exists() )
//		{
//			Logfuncs.errorHandler("could not find tool: " + sToolName);
//			return "";
//		}
//		VisualReporter.logScriptInfo("Get IE version");
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sToolName);
//			p.waitFor();
//			
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return "";
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Get IE version executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) return resultStr[1];
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error Reason: "+resultStr[1]);
//		return "";
//	}
//
//	public void clearCacheNew ()  
//	{
//		if (VisualReporter.gsVisualReporterTestBrowser.equalsIgnoreCase(VisualReporter.gsMozillaFirefox))
//		{
//			//if Ff don't need to do anything as FF is set to clear cache upon exit
//			return;
//		}
//		Browserfuncs.browserStart();
//		b.getBrowser().deleteCookies();
//		b.getBrowser().inputKeys("%to");
//		if (VisualReporter.gsVisualReporterTestBrowser.equalsIgnoreCase(VisualReporter.gsInternetExplorer7))
//		{
//			//in ie 7 we have to clic delete first
//			//and then title is "Delete Browsing History" and its "Delete &files"
//			setFocusClickButton("Internet Options", "&Delete...");
//			setFocusClickButton("Delete Browsing History", "Delete &files...");
//			setFocusClickButton("Delete Files", "&Yes");
//			sleep(VisualReporter.giVisualReporterMedTO);
//			setFocusClickButton("Delete Browsing History", "&Close");
//		}
//		else if (VisualReporter.gsVisualReporterTestBrowser.equalsIgnoreCase(VisualReporter.gsInternetExplorer8))
//		{
//			//in ie 8 we just click delete as temp files, cookies and history are set to delete by default
//			setFocusClickButton("Internet Options", "&Delete...");
//			//and one more delete
//			sleep(VisualReporter.giVisualReporterPause1TO);
//			setFocusClickButton("Delete Browsing History", "&Delete");
//			sleep(VisualReporter.giVisualReporterMedTO);
//		}
//		else
//		//else its IE6
//		{
//			setFocusClickButton("Internet Options", "Delete &Files...");
//			setFocusDoKeys("Delete Files", " ");
//			//and for ie 7 it is yes not OK and then Close on next one
//			//while its deleteing it has dialog up as "Delete Browsing History"
//			setFocusClickButton("Delete Files", "OK");
//			//need to wait as it might take a while to delete the files
//			sleep(VisualReporter.giVisualReporterMedTO);
//		}
//		setFocusClickButton("Internet Options", "OK");
//	}
//	
//
//	
//	/**
//	 * Clear browser cache of last user, so that we could login with another user
//	 * 
//	 * Now support IE 6/7 (has been verified on XP)
//	 */
//	public void clearCache()  
//	{
//		if (VisualReporter.gsVisualReporterTestBrowser.equalsIgnoreCase(VisualReporter.gsMozillaFirefox))
//		{
//			//if Ff don't need to do anything as FF is set to clear cache upon exit
//			return;
//		}
//		Browser b = new Browser();
//		Browserfuncs.browserStart();
//		b.getBrowser().deleteCookies();
//		b.getBrowser().inputKeys("%to");
//		
//		//get IE release number, IE 6 as default one
//		String sIEReleaseNo = getIEVersion();
//	    
//		//clear cache
//		if (sIEReleaseNo.startsWith("7"))
//		{
//			setFocusClickButton("Internet Options", "&Delete...");
//			setFocusClickButton("Delete Browsing History", "Delete &files...");
//			setFocusClickButton("Delete Files", "&Yes");
//			sleep(VisualReporter.giVisualReporterWaitTO);
//			setFocusClickButton("Delete Browsing History", "&Close");
//		}
//		else
//		//else its IE6
//		{
//			//in ie 7 we have to click delete 1st
//			//and then title is "Delete Browsing History" and its "Delete &files"
//			setFocusClickButton("Internet Options", "Delete &Files...");
//			setFocusDoKeys("Delete Files", " ");
//			//and for ie 7 it is yes not OK and then Close on next one
//			//while its deleteing it has dialog up as "Delete Browsing History"
//			setFocusClickButton("Delete Files", "OK");
//			//need to wait as it might take a while to delete the files
//			sleep(VisualReporter.giVisualReporterWaitTO);
//		}
//		setFocusClickButton("Internet Options", "OK");
//		
//	}
//	
//	/** 
//	*  just deletes the flag file before we run to make sure its gone
//	* @return   	
//	*/
//	public void deleteFlagFile ()
//	{
//		ibm.util.FileIOfuncs.deleteFile(gsFlagFileName);
//	}
//	
//	/** 
//	*  Checks for the existence of the flag file
//	* @return   	
//	*/
//	public boolean checkForFlagFile ()
//	{
//		File f = new File(gsFlagFileName);
//		if (f.exists())
//			return true;
//		else
//			return false;
//	}
//	
//	public void waitForFlagFile()
//	{
//		//this will hang up squash, so let's only wait so long in case AutoIT has frozen up
//		int iX=0;
//		while (!checkForFlagFile()&& iX<120)
//		{
//			sleep(1);
//			iX++;
//		}
//	}
//	
//	public boolean checkForTrue()
//	{
//		String sTemp = ibm.util.FileIOfuncs.getFileContents(gsFlagFileName); 
//		if (sTemp.indexOf("true")>-1)
//			return true;
//		else
//			return false;
//	}
//
//	// create AutoIt task for connector code split
//	/**
//	 * create a new word file, append some content, not save
//	 * @param String fileContent - name of the new word file
//	 * @return boolean - ture for success, and false for failure
//	 */
//	public boolean createWord(String fileContent){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\Conn811\\CreateWordNoSave.exe";
//		if (gsConnVersion.equalsIgnoreCase("850"))
//		{sToolName = gsToolsPath + "\\Conn85\\CreateWordNoSave.exe";}
//
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + fileContent + "\"";
//
//		VisualReporter.logScriptInfo("create word file with content" + fileContent);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("creating word file executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error Reason: "+resultStr[1]);
//		return false;
//	}
//	
//	/**
//	 * create a new excel file, append some content, not save
//	 * @param String fileContent - name of the new excel file
//	 * @return boolean - ture for success, and false for failure
//	 */
//	public boolean createExcel(String fileContent){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\Conn811\\CreateExcelNoSave.exe";
//		if (gsConnVersion.equalsIgnoreCase("850"))
//		{sToolName = gsToolsPath + "\\Conn85\\CreateExcelNoSave.exe";}
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + fileContent + "\"";
//
//		VisualReporter.logScriptInfo("create excel file with content" + fileContent);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("creating excel file executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error Reason: "+resultStr[1]);
//		return false;
//	}
//	
//	/**
//	 * create a new ppt file, append some content, not save
//	 * @param String fileContent - name of the new excel file
//	 * @return boolean - ture for success, and false for failure
//	 */
//	public boolean createPPT(String fileContent){
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\Conn811\\CreatePPTNoSave.exe";
//		if (gsConnVersion.equalsIgnoreCase("850"))
//		{sToolName = gsToolsPath + "\\Conn85\\CreatePPTNoSave.exe";}
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + fileContent + "\"";
//
//		VisualReporter.logScriptInfo("create ppt file with content" + fileContent);
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();
//		if (resultStr==null) return false;
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("creating ppt file executes  "+(result==0? "Successfully":"Failed"));
//		if (result==0) return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error Reason: "+resultStr[1]);
//		return false;
//	}
//	
//	/** 
//	*  Uses AutoIt to check for existence of a dialog with specified title
//	* @param  String sCaption -  Dialog caption to look for
//	* @param  int iTime -  How long to wait for dialog
//	* @return   	
//	*/
//
//	public boolean copyAMailOutlook()
//	{
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\DI";
//		String sToolName = gsToolsPath + "\\Conn811\\ViewInBoxOutLook.exe";
//		if (gsConnVersion.equalsIgnoreCase("850"))
//		{sToolName = gsToolsPath + "\\Conn85\\ViewInBoxOutLook.exe";}
//		String sCMD="\"" + sToolName + "\"";
//		VisualReporter.logScriptInfo("Copy a mail in outlook" );
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();		
//		if (resultStr==null) 
//			return false;		
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Copy a mail in outlook "+(result==0? "Successfully":"Failed"));		
//		if (result==0) 
//			return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error reason:  "+resultStr[1]);
//		return false;
//		
//	}
//	/**	 * Quickr J2ee  admin consol login
//	 * 
//	 * 
//	 */
//	public boolean admConsoleLogin(String strServerURL, String sUserName, String sPWD)
//	{
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\UITools";
//		String sToolName = gsToolsPath + "\\AdmConLogin3.exe";
//		
//		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + strServerURL + "\"" + " " + "\"" + sUserName + "\"" + " " + "\"" + sPWD + "\"";
//
//		VisualReporter.logScriptInfo("log in admin console by " +sUserName);
//		
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();		
//		if (resultStr==null) 
//			return false;		
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("log in admin console "+(result==0? "Successfully":"Failed"));		
//		if (result==0) 
//			return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error reason:  "+resultStr[1]);
//		return false;
//	}
//
//	public boolean admConsoleDownload()
//	{
//		String gsToolsPath = VisualReporter.gsVisualReporterAutoSupportDocs + "Applications\\AutoIt\\Tools\\UITools";
//		String sToolName = gsToolsPath + "\\AdminDownloadDialog.exe";
//		
//		String sCMD="\"" + sToolName  + "\"";
//
//		VisualReporter.logScriptInfo("Click OK by AutoIT");
//		
//		java.lang.Process p;
//		try{
//			p= Runtime.getRuntime().exec(sCMD);
//			p.waitFor();
//		}catch(Exception e){
//			VisualReporter.logScriptInfo(e.toString());
//		}
//		String[] resultStr=getResultString();		
//		if (resultStr==null) 
//			return false;		
//		int result=Integer.valueOf(resultStr[0]).intValue();
//		VisualReporter.logScriptInfo("Handle download dialog "+(result==0? "Successfully":"Failed"));		
//		if (result==0) 
//			return true;
//		VisualReporter.logScriptInfo("Error Code:  "+result+", Error reason:  "+resultStr[1]);
//		return false;
//	}
//	//end of class file	
//}