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

public class GUAT_EditorBasic extends TestCase {

	private Logger log;
	private WebDriver driver;
	private LogFile logFile;
	private Users user;
	private Task task;
	
	public GUAT_EditorBasic() {

		super.setName("testMain_UAT_EditorBasic");
		
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
	
    public void testMain_UAT_EditorBasic() throws Exception{

    	init();
    	
    	try{
    		
	    	task.qfd_LoginTask.logIn(user.UIDCreator, user.CreatorPW);
			task.qfd_CreatePlaceTask.createPlace("UAT_EditorBasic" + Datefuncs.genDateBasedRandVal(), null);
			
			task.qfd_ForumTask.createForum(user.ForumName, user.Desc);
			task.qfd_ForumTask.createTopicRichText(user.TopicName);
			
			task.qfd_LibraryTask.createRichPage(user.PageName);
			
			task.qfd_CalendarTask.createRichEvent(user.EventName);
			
			task.qfd_TaskTask.createRichTask(user.TaskName);
			
			task.qfd_LibraryTask.createPageUploadImage(user.PageName + " 1", user.ImgPath, user.ImgName);
			
    	}catch(Exception e){
    		
    		Times.st = false;
    		
    	}finally{
    		
    		finish();
    		
    	}
		
    }
	
}
