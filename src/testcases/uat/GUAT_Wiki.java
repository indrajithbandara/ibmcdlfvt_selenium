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

public class GUAT_Wiki extends TestCase {
	
	private Logger log;
	private WebDriver driver;
	private LogFile logFile;
	private Users user;
	private Task task;
	
	public GUAT_Wiki() {

		super.setName("testMain_UAT_Wiki");
		
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
	
    public void testMain_UAT_Wiki() throws Exception{

    	init();
    	
    	try{
    		
	    	task.qfd_LoginTask.logIn(user.UIDCreator, user.CreatorPW);
			task.qfd_CreatePlaceTask.createWiki("UAT_Wiki" + Datefuncs.genDateBasedRandVal(), null);
			
			task.qfd_MembersTask.addBlogWikiMembers(0, 1, 1, 1, 0);
			
			task.qfd_BlogWikiTask.createWiki("UAT Wiki Page 1", user.Desc, user.UIDCreator);
			
			task.qfd_LoginTask.logOutAndLogInAlertWiki(user.AuthorName, user.AuthorPW);
			task.qfd_BlogWikiTask.createResponse("UAT Wiki Page 1", user.Desc + " Response", user.AuthorName);
			
			task.qfd_LoginTask.logOutAndLogInAlertWiki(user.EditorName, user.EditorPW);
			task.qfd_BlogWikiTask.editWiki("UAT Wiki Page 1", user.Desc, user.EditorName, user.WordPath, user.WordName);
			
			task.qfd_LoginTask.logOutAndLogInAlertWiki(user.ManagerName, user.ManagerPW);
			task.qfd_BlogWikiTask.createWikiTOC("UAT Wiki page on TOC", user.Desc, user.ManagerName);
			task.qfd_BlogWikiTask.deleteWiki("UAT Wiki page on TOC");
			
    	}catch(Exception e){
    		
    		Times.st = false;
    		
    	}finally{
    		
    		finish();
    		
    	}
		
    }
    
}