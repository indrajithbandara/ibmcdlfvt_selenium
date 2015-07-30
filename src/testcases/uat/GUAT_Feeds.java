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

public class GUAT_Feeds extends TestCase {
	
	private Logger log;
	private WebDriver driver;
	private LogFile logFile;
	private Users user;
	private Task task;
	
	public GUAT_Feeds() {

		super.setName("testMain_UAT_Feeds");
		
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
	
	public void testMain_UAT_Feeds() throws Exception{

    	init();
    	
    	try{
    		
    		task.qfd_LoginTask.logIn(user.UIDCreator, user.CreatorPW);
			task.qfd_CreatePlaceTask.createPlace("UAT_Feeds" + Datefuncs.genDateBasedRandVal(), null);
			
			task.qfd_MembersTask.addMembers(1, 0, 0, 0, 0);
			
			task.qfd_LibraryTask.createPage(user.PageName, user.Desc, null, false);

			task.qfd_LoginTask.logOutAndLogIn(user.ReaderName, user.ReaderPW);
			
			task.qfd_LibraryTask.subscribe(user.PageName);
			task.qfd_LibraryTask.verifyLibraryFeed(user.PageName);
		
			task.qfd_LoginTask.logOutAndLogIn(user.UIDCreator, user.CreatorPW);
		
			task.qfd_LibraryTask.createPage(user.PageName + " 2", user.Desc, null, false);
			
			
			task.qfd_LoginTask.logOutAndLogIn(user.ReaderName, user.ReaderPW);
			task.qfd_LibraryTask.verifyLibraryFeedFromLibrary(user.PageName + " 2");
			
    	}catch(Exception e){
    		
    		Times.st = false;
    		
    	}finally{
    		
    		finish();
    		
    	}
		
    }
	
}
