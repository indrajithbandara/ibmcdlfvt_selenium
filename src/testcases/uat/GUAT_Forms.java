package testcases.uat;

import java.io.File;

import junit.framework.TestCase;
import log.LogFile;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import util.Datefuncs;
import util.Times;

import config.BrowserSetup;
import config.CaseTask;
import config.Task;
import config.Users;

public class GUAT_Forms extends TestCase {
	
	private Logger log;
	private WebDriver driver;
	private LogFile logFile;
	private Users user;
	private Task task;
	
	public GUAT_Forms() {

		super.setName("testMain_UAT_Forms");
		
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
	
	public void testMain_UAT_Forms() throws Exception{

    	init();
    	
    	try{
    		
	    	task.qfd_LoginTask.logIn(user.UIDCreator, user.CreatorPW);
			task.qfd_CreatePlaceTask.createPlace("UAT_Forms5" + Datefuncs.genDateBasedRandVal(), null);
			
			task.qfd_PlaceTask.createForms("UAT Simple Form", "UAT Description Title");
			task.qfd_LibraryTask.verifyForm("UAT Simple Form");
			
			task.qfd_PlaceTask.editForm("UAT Simple Form");
			task.qfd_LibraryTask.createFormPage(user.PageName, user.Desc, user.WordPath, "UAT Simple Form");
			
    	}catch(Exception e){
    		
    		Times.st = false;
    		
    	}finally{
    		
    		finish();
    		
    	}
		
    }
	
}
