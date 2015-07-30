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

public class GUAT_Links extends TestCase {
	
	private Logger log;
	private WebDriver driver;
	private LogFile logFile;
	private Users user;
	private Task task;
	
	public GUAT_Links() {

		super.setName("testMain_UAT_Links");
		
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
	
    public void testMain_UAT_Links() throws Exception{

    	init();
    	
    	try{
    		
	    	task.qfd_LoginTask.logIn(user.UIDCreator, user.CreatorPW);
			task.qfd_CreatePlaceTask.createPlace("UAT_Links" + Datefuncs.genDateBasedRandVal(), null);
			
			task.qfd_MembersTask.addMembers(0, 1, 1, 0, 0);
			
			task.qfd_LoginTask.logOutAndLogIn(user.AuthorName, user.AuthorPW);
			
			task.qfd_LibraryTask.createLinkPage(user.LinkName, user.URLIBM);
			
			task.qfd_LibraryTask.verifyLinkMessage(user.LinkName, user.AuthorName);
			
			task.qfd_LibraryTask.clickLink(user.LinkName);
			
			task.qfd_LibraryTask.verifyLinkPage(user.LinkName, user.AuthorName, user.URLIBM);
			
    	}catch(Exception e){
    		
    		Times.st = false;
    		
    	}finally{
    		
    		finish();
    		
    	}
		
    }
}