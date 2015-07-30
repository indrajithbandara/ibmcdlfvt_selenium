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

public class GUAT_Authentication extends TestCase {
	
	private Logger log;
	private WebDriver driver;
	private LogFile logFile;
	private Users user;
	private Task task;
	
	public GUAT_Authentication() {

		super.setName("testMain_UAT_Authentication");
		
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
	
	public void testMain_UAT_Authentication() throws Exception{

    	init();
    	
    	try{
    		
    		task.qfd_PlaceTask.verifyPlacesScreen();
   
	    	task.qfd_CreatePlaceTask.verifyCreatePlace("UAT_Auth" + Datefuncs.genDateBasedRandVal(), user.Desc, user.UIDCreator, user.CreatorPW);
	    	
	    	task.qfd_LoginTask.verifyNonAdmin(user.UIDCreator, user.CreatorPW);
	    	task.qfd_LoginTask.verifyInvalid("invalid", "invalid");
	    	task.qfd_LoginTask.verifyAdmin(user.AdminName, user.AdminPW);
	    	
	    	task.qfd_AdminTask.assignPersonAndGroupCreator(user.AdminName, user.AdminPW, user.CreatorSearch, user.GroupName);
	    	task.qfd_AdminTask.verifyPersonAndGroupCreator(user.AuthorName, user.AuthorPW, user.UIDCreator, user.CreatorPW, user.GroupUserName, user.GroupUserPW, "UAT Creator Rights - Person" + Datefuncs.genDateBasedRandVal(), "UAT Creator Rights - Group" + Datefuncs.genDateBasedRandVal(), user.Desc);
	    	task.qfd_AdminTask.removePersonAndGroupCreator(user.AdminName, user.AdminPW, user.CreatorSearch, user.GroupName);
	    	
	    	task.qfd_AdminTask.assignPersonAndGroupAdmin(user.AdminName, user.AdminPW, user.CreatorSearch, user.GroupName);
	    	task.qfd_AdminTask.verifyPersonAndGroupAdmin(user.UIDCreator, user.CreatorPW, user.GroupUserName, user.GroupUserPW);
	    	task.qfd_AdminTask.removePersonAndGroupAdmin(user.AdminName, user.AdminPW, user.CreatorSearch, user.GroupName);
	    	
    	}catch(Exception e){
    		
    		Times.st = false;
    		
    	}finally{
    		
    		finish();
    		
    	}
		
    }
	
}
