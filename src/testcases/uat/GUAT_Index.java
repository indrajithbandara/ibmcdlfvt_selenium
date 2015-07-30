package testcases.uat;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import util.Datefuncs;
import util.Times;

import config.BrowserSetup;

import config.CaseTask;
import config.Task;
import config.Users;

import junit.framework.*;
import log.LogFile;

public class GUAT_Index extends TestCase {
	
	private Logger log;
	private WebDriver driver;
	private LogFile logFile;
	private Users user;
	private Task task;
	
	public GUAT_Index() {

		super.setName("testMain_UAT_Index");
		
	}
	
	private void init(){
		
		log = Logger.getLogger(this.getClass());
		logFile = new LogFile();
		BrowserSetup b = new BrowserSetup(logFile, log);
    	driver = b.launchBrowser(this.getClass().getName());
    	user = b.getUsers();
    	task = new Task(driver, log, user);
    	
	}
	
	@SuppressWarnings({ "deprecation", "static-access" })
	private void finish(){
		
		new CaseTask(this, Times.Times);
		log.shutdown();
		logFile.changeLogName(this.getClass().getName());
		
		driver.quit();
		
	}
	
    public void testMain_UAT_Index() throws Exception{

    	init();
    	
    	try{
    		
	    	task.qfd_LoginTask.logIn(user.UIDCreator, user.CreatorPW);
			task.qfd_CreatePlaceTask.createPlace("UAT_Index" + Datefuncs.genDateBasedRandVal(), null);
			
			task.qfd_IndexTask.verifyIndex();
			
			task.qfd_IndexTask.addDataToIndex(user.WordPath, user.Desc, user.PageName, 
					user.WordPath, user.LinkName, user.URLIBM, user.FolderName, user.Word1Path,
					user.Word1Name, user.ListName, user.ListItemTitle);
			
			task.qfd_IndexTask.verifyMoreActions(user.LinkName, user.UIDCreator, user.CreatorPW);
			
			task.qfd_IndexTask.verifySortAndNavigate();
			
    	}catch(Exception e){
    		
    		Times.st = false;
    		
    	}finally{
    		
    		finish();
    		
    	}
		
    }
}