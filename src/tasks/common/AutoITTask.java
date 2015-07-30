package tasks.common;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import util.Assert;

public class AutoITTask {

	private WebDriver driver;
	private Logger log;
	private Assert logic;
	
	public AutoITTask(WebDriver driver,Logger log){
		
		this.driver = driver;
		this.log = log;
		this.logic = new Assert(driver, log);
		
	}
	
	public boolean saveFileToDisk(String fileName){
		
		String sToolName =  "SupportFiles\\autoit\\SaveFileToC.exe";
		String sCMD="\"" + sToolName  + "\""+ " " + "\"" + fileName + "\"" ;
		
		if(!(new java.io.File(sToolName)).exists()){
			
			log.error("Could not find tool: " + sToolName);
			return false;
			
		}
		
		log.info("Save file: " + fileName + " in c:\\");
		java.lang.Process p;
		
		try{
			
			p= Runtime.getRuntime().exec(sCMD);
			p.waitFor();
			
		}catch(Exception e){
			log.error(e.toString());
		}
		
		String[] resultStr=getResultString();
		
		if(resultStr==null) return false;
		
		int result = Integer.valueOf(resultStr[0]).intValue();
		log.info("Saving file executes  " + (result==0 ? "Successfully" : "Failed"));
		
		if (result == 0) return true;
		log.error("Error Code:  " + result + ", Error Reason: " + resultStr[1]);
		
		return false;
	}
	
	public String[] getResultString(){
		
		String[] resultStr = null;
		Clipboard clipboard=java.awt.Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable contents = clipboard.getContents(this);
        DataFlavor flavor = DataFlavor.stringFlavor;
        
        if(contents.isDataFlavorSupported(flavor)){
        	
        	try{
        		
        		String str = (String)contents.getTransferData(flavor);
        		if(str != null){
        			
        			resultStr = str.split("" + (char)9);
        			
        		}
        		
        	}catch(Exception e){  
        		log.error("Java Methods get unsupported result from AutoIt script " + e.toString());
        	}
        	
        }
        
        return resultStr;
        
	}
	
	public boolean setFocusDoKeys (String sWindowsTitle, String sKeys) {
		
		String sToolName = "SupportFiles\\autoit\\Tools\\UITools\\SetFocusDoKeys.exe";
		String sCMD="\"" + sToolName + "\" \"" + sWindowsTitle + "\" \"" + sKeys + "\"";
		
		log.info("Settting focus to " + sWindowsTitle + " Window and executing the keys " + sKeys);
		
		java.lang.Process p;
		
		try{
			
			p = Runtime.getRuntime().exec(sCMD);
			p.waitFor();
			
		}catch(Exception e){
			log.error(e.toString());
		}
		
		String[] resultStr = getResultString();
		
		if(resultStr == null) return false;
		
		int result = Integer.valueOf(resultStr[0]).intValue();
		log.info("Setting focus for keystrokes executes  " + (result == 0 ? "Successfully" : "Failed"));
		
		if (result == 0) return true;
		log.error("Error Code:  " + result + ", Error Reason: " + resultStr[1]);
		
		return false;
		
	}
	
	public boolean checkForDialog(String sCaption, int iTime) {
		
		String sToolName = "SupportFiles\\AutoIt\\Tools\\UITools\\CheckForDialogBox.exe";

		String sCMD="\"" + sToolName + "\" \"" + sCaption + "\" \"" + iTime + "\"";
		log.info("Looking for dialog with caption " + sCaption);
		java.lang.Process p;
		
		try{
			
			p = Runtime.getRuntime().exec(sCMD);
			p.waitFor();
			
		}catch(Exception e){
			log.error(e.toString());
		}
		
		String[] resultStr = getResultString();
		
		if (resultStr == null)return false;
		
		int result = Integer.valueOf(resultStr[0]).intValue();
		log.info("Looking for dialog routine executed " + (result == 0 ? "Successfully" : "Failed"));
		
		if (result == 0)return true;
		
		log.info("Error Code:  " + result + ", Error reason:  " + resultStr[1]);
		return false;
		
	}
	
	public boolean setFocusClickButton (String sWindowsTitle, String sButton){
	
		String sToolName = "SupportFiles\\AutoIt\\Tools\\UITools\\ClickButton.exe";

		String sCMD="\"" + sToolName  + "\" \"" + sWindowsTitle + "\" \"" + sButton + "\"";
		log.info("Click button " + sButton + " in window " + sWindowsTitle);
		java.lang.Process p;
		
		try{
			
			p= Runtime.getRuntime().exec(sCMD);
			p.waitFor();
			
		}
		catch(Exception e){
			log.error(e.toString());
		}
		
		String[] resultStr = getResultString();
		
		if (resultStr == null) return false;
		
		int result=Integer.valueOf(resultStr[0]).intValue();
		log.info("Clicking button executes  " + (result == 0 ? "Successfully" : "Failed"));
		
		if (result == 0) return true;
		
		log.info("Error Code:  " + result + ", Error reason:  " + resultStr[1]);
		return false;
		
	}
	
	public boolean closeWindow (String sWindowsTitle){
		
		String sToolName = "SupportFiles\\AutoIt\\Tools\\UITools\\WindowClose.exe";
		
		String sCMD="\"" + sToolName  + "\"" + " " + "\"" + sWindowsTitle + "\"";
		log.info("Try to close window with title " + sWindowsTitle);
		java.lang.Process p;
		
		try{
			
			p= Runtime.getRuntime().exec(sCMD);
			p.waitFor();
			
		}catch(Exception e){
			log.error(e.toString());
		}
		
		String[] resultStr = getResultString();
		
		if (resultStr ==   null) return false;	
		
		int result =Integer.valueOf(resultStr[0]).intValue();
		log.info("Close Window routine " + (result == 0 ? "Succeeded" : "Failed"));
		
		if (result==0) return true;
		
		log.info("Error Code:  " + result + ", Error reason:  " + resultStr[1]);
		return false;
		
	}
	
}
