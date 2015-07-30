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

public class GUAT_Minitest extends TestCase {
	
	private Logger log;
	private WebDriver driver;
	private LogFile logFile;
	private Users user;
	private Task task;
	
	public GUAT_Minitest() {

		super.setName("testMain_UAT_Minitest");
		
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
	
	public void testMain_UAT_Minitest() throws Exception{

    	init();
    	
    	try{
    		
	    	task.qfd_LoginTask.logIn(user.UIDCreator, user.CreatorPW);
			task.qfd_CreatePlaceTask.createPlace("UAT_Mini" + Datefuncs.genDateBasedRandVal(), null);
			
			task.qfd_MembersTask.addMembers(0, 1, 0, 0, 0);
			
			task.qfd_LibraryTask.createPage(user.PageName, user.Desc, user.WordPath, false);
			task.qfd_PageTask.editPage(user.PageName, user.ExcelPath);
			
			task.qfd_LibraryTask.createFolder(user.FolderName, user.Desc);
			task.qfd_LibraryTask.createPageInFolder(user.PageName, user.Desc, user.WordPath, user.FolderName);
			
			task.qfd_ForumTask.createForum(user.ForumName, user.Desc);
			task.qfd_ForumTask.createTopic(user.TopicName, user.Desc, null);
			
			task.qfd_LoginTask.logOutAndLogIn(user.AuthorName, user.AuthorPW);
			
			task.qfd_PageTask.addComment(user.PageName + " Updated", user.Desc, user.AuthorName);
			
			task.qfd_LibraryTask.createPage(user.PageName + " By Author", user.Desc, user.WordPath, false);
			task.qfd_PageTask.editPage(user.PageName + " By Author", user.ExcelPath);
			
			task.qfd_ForumTask.createResponse(user.ForumName, user.TopicName, user.Desc);
			
    	}catch(Exception e){
    		
    		Times.st = false;
    		
    	}finally{
    		
    		finish();
    		
    	}
		
    }
	
}
