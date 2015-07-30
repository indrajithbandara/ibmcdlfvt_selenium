package testcases.uat;

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

public class GUAT_Templates extends TestCase {
	
	private Logger log;
	private WebDriver driver;
	private LogFile logFile;
	private Users user;
	private Task task;
	
	public GUAT_Templates() {

		super.setName("testMain_UAT_Templates");
		
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
	
	public void testMain_UAT_Templates() throws Exception{

    	init();
    	
    	try{
    		
    		task.qfd_LoginTask.logIn(user.UIDCreator, user.CreatorPW);
    		task.qfd_CreatePlaceTask.createPlace("UAT_Templates" + Datefuncs.genDateBasedRandVal(), null);
    		
    		task.qfd_PlaceTask.allowPlaceType();
    		
    		task.qfd_AdminTask.createPlaceType(user.AdminName, user.AdminPW, "UAT New Standard Place Type" + Datefuncs.genDateBasedRandVal(), user.URLIBM, "UAT New StandardType" + Datefuncs.genDateBasedRandVal(), user.Desc, user.UIDCreator, user.CreatorPW);
    		
    	}catch(Exception e){
    		
    		Times.st = false;
    		
    	}finally{
    		
    		finish();
    		
    	}
		
    }
	
}
