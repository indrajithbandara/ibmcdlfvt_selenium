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

public class GUAT_Blog extends TestCase {
	
	private Logger log;
	private WebDriver driver;
	private LogFile logFile;
	private Users user;
	private Task task;
	
	public GUAT_Blog() {

		super.setName("testMain_UAT_Blog");
		
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
	
    public void testMain_UAT_Blog() throws Exception{

    	init();
    	
    	try{
    		
	    	task.qfd_LoginTask.logIn(user.UIDCreator, user.CreatorPW);
			task.qfd_CreatePlaceTask.createBlog("UAT_Blog" + Datefuncs.genDateBasedRandVal(), null);
			
			task.qfd_MembersTask.addBlogWikiMembers(0, 1, 1, 1, 0);
			
			task.qfd_BlogWikiTask.createEntry("UAT Blog Entry 1", user.Desc, user.UIDCreator);
			
			task.qfd_LoginTask.logOutAndLogInAlert(user.AuthorName, user.AuthorPW);
			task.qfd_BlogWikiTask.createComment("UAT Blog Entry 1", user.Desc + " Comment", user.AuthorName);
			
			task.qfd_LoginTask.logOutAndLogInAlert(user.EditorName, user.EditorPW);
			task.qfd_BlogWikiTask.editEntry("UAT Blog Entry 1", user.Desc, user.UIDCreator);
			
			task.qfd_BlogWikiTask.createMoreEntry("UAT Blog Entry 2", user.Desc, user.EditorName);
			
			task.qfd_LoginTask.logOutAndLogInAlert(user.ManagerName, user.ManagerPW);
			task.qfd_BlogWikiTask.deleteBlog("UAT Blog Entry 2");
			
    	}catch(Exception e){
    		
    		Times.st = false;
    		
    	}finally{
    		
    		finish();
    		
    	}
		
    }
    
}