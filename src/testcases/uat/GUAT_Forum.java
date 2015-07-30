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

public class GUAT_Forum extends TestCase {
	
	private Logger log;
	private WebDriver driver;
	private LogFile logFile;
	private Users user;
	private Task task;
	
	public GUAT_Forum() {

		super.setName("testMain_UAT_Forum");
		
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
	
	public void testMain_UAT_Forum() throws Exception{

    	init();
    	
    	try{
    		
	    	task.qfd_LoginTask.logIn(user.UIDCreator, user.CreatorPW);
			task.qfd_CreatePlaceTask.createPlace("UAT_Forum" + Datefuncs.genDateBasedRandVal(), null);
			
			task.qfd_MembersTask.addMembers(0, 1, 1, 0, 0);
			
			task.qfd_ForumTask.createForum(user.ForumName, user.Desc);
			task.qfd_ForumTask.editForum(user.ForumName, user.Desc);
			
			task.qfd_LoginTask.logOutAndLogIn(user.EditorName, user.EditorPW);
			
			task.qfd_ForumTask.createTopicFromHome(user.ForumName + " Updated", user.TopicName, user.Desc, user.WordPath, user.EditorName);
			task.qfd_ForumTask.editTopic(user.ForumName + " Updated", user.TopicName, user.Desc);
			
			task.qfd_LoginTask.logOutAndLogIn(user.AuthorName, user.AuthorPW);
			
			task.qfd_ForumTask.createResponse(user.ForumName + " Updated", user.TopicName + " Updated", user.Desc);
			task.qfd_ForumTask.verifyReply(user.ForumName + " Updated", user.Desc, user.AuthorName);
			
			task.qfd_LoginTask.logOutAndLogIn(user.UIDCreator, user.CreatorPW);
			
			task.qfd_ForumTask.deleteReply(user.ForumName + " Updated", user.TopicName + " Updated", user.Desc, user.AuthorName);
			task.qfd_ForumTask.deleteTopic(user.ForumName + " Updated", user.TopicName + " Updated");
			task.qfd_ForumTask.deleteForum(user.ForumName + " Updated");
			
    	}catch(Exception e){
    		
    		Times.st = false;
    		
    	}finally{
    		
    		finish();
    		
    	}
		
    }
	
}
