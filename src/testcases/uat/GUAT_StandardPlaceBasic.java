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

public class GUAT_StandardPlaceBasic extends TestCase {
	
	private Logger log;
	private WebDriver driver;
	private LogFile logFile;
	private Users user;
	private Task task;
	
	public GUAT_StandardPlaceBasic() {

		super.setName("testMain_UAT_StandardPlaceBasic");
		
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
	
	public void testMain_UAT_StandardPlaceBasic() throws Exception{

    	init();
    	
    	try{
    		
    		task.qfd_LoginTask.logIn(user.UIDCreator, user.CreatorPW);
			task.qfd_CreatePlaceTask.createPlace("UAT_SPBasic" + Datefuncs.genDateBasedRandVal(), null);
    		
    		task.qfd_MembersTask.addMembers(0, 1, 0, 0, 0);
    		
    		//task.qfd_PlaceTask.verifyHeaderLinks(user.UIDCreator, user.CreatorPW, user.AuthorName, user.AuthorPW);
    		//task.qfd_PlaceTask.verifyFooterLinks(user.UIDCreator, user.CreatorPW, user.AuthorName, user.AuthorPW);
    		
    		task.qfd_PlaceTask.verifyTOC();
    		
    		task.qfd_FolderTask.createTOCTopFolder(user.FolderName, user.Desc);
    		task.qfd_PlaceTask.verifyPlaceActions(user.FolderName);
    		task.qfd_LoginTask.logOutAndLogIn(user.AuthorName, user.AuthorPW);
    		task.qfd_PlaceTask.verifyPlaceActions(user.FolderName);
    		task.qfd_LoginTask.logOutAndLogIn(user.UIDCreator, user.CreatorPW);
    		task.qfd_PlaceTask.deleteFolderAndLibrary(user.FolderName);
    		task.qfd_PlaceTask.verifyPlaceActionsNoFolder(user.AuthorName, user.AuthorPW);
    		task.qfd_PlaceTask.verifyHeaderLinks(user.UIDCreator, user.CreatorPW, user.AuthorName, user.AuthorPW);
    		task.qfd_PlaceTask.verifyFooterLinks(user.UIDCreator, user.CreatorPW, user.AuthorName, user.AuthorPW);
    		
    	}catch(Exception e){
    		
    		Times.st = false;
    		
    	}finally{
    		
    		finish();
    		
    	}
		
    }
	
}
