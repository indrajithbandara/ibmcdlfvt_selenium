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

public class GUAT_Upload extends TestCase {
	
	private Logger log;
	private WebDriver driver;
	private LogFile logFile;
	private Users user;
	private Task task;
	
	public GUAT_Upload() {

		super.setName("testMain_UAT_Upload");
		
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
	
    public void testMain_UAT_Upload() throws Exception{

    	init();
    	
    	try{
    		
	    	task.qfd_LoginTask.logIn(user.UIDCreator, user.CreatorPW);
			task.qfd_CreatePlaceTask.createPlace("UAT_Upload" + Datefuncs.genDateBasedRandVal(), null);
			
			task.qfd_MembersTask.addMembers(0, 1, 1, 0, 0);
			
			task.qfd_LoginTask.logOutAndLogIn(user.AuthorName, user.AuthorPW);
			
			task.qfd_LibraryTask.createUpload(user.WordName, user.WordPath, user.Desc);
			task.qfd_PageTask.verifyUpload(user.WordName, user.Desc, user.AuthorName, user.WordName, "word");
			
			task.qfd_PageTask.editUpload(user.WordName, user.Desc);
			task.qfd_PageTask.verifyUpload(user.WordName + " Updated", user.Desc + " Updated", user.AuthorName, user.WordName, "word");
			
			task.qfd_PageTask.replaceUpload(user.WordName + " Updated", user.ExcelPath);
			task.qfd_PageTask.verifyUpload(user.WordName + " Updated", user.Desc + " Updated", user.AuthorName, user.ExcelName, "excel");
			
			task.qfd_PageTask.saveVersionUpload(user.WordName + " Updated", user.AuthorName, user.ImgPath);
			task.qfd_PageTask.restoreVersionUpload(user.WordName + " Updated", user.AuthorName);
			
			task.qfd_PageTask.addComment(user.WordName + " Updated", user.Desc, user.AuthorName);
			
			task.qfd_LoginTask.logOutAndLogIn(user.EditorName, user.EditorPW);
			
			task.qfd_PageTask.replyComment(user.WordName + " Updated", user.Desc, user.EditorName);
			
    	}catch(Exception e){
    		
    		Times.st = false;
    		
    	}finally{
    		
    		finish();
    		
    	}
		
    }
    
}