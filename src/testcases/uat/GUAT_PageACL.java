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

public class GUAT_PageACL extends TestCase {
	
	private Logger log;
	private WebDriver driver;
	private LogFile logFile;
	private Users user;
	private Task task;
	
	public GUAT_PageACL() {

		super.setName("testMain_UAT_PageACL");
		
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
	
	public void testMain_UAT_PageACL() throws Exception{

    	init();
    	
    	try{
    		
	    	task.qfd_LoginTask.logIn(user.UIDCreator, user.CreatorPW);
			task.qfd_CreatePlaceTask.createPlace("UAT_PageACL" + Datefuncs.genDateBasedRandVal(), null);
			
			task.qfd_MembersTask.addMembers(1, 2, 1, 0, 0);
			task.qfd_MembersTask.addAuthorGroup();
			task.qfd_MembersTask.createAuthor("Local Author", "password");
			task.qfd_MembersTask.createReader("Local Reader", "password");
			
			task.qfd_LoginTask.logOutAndLogIn(user.AuthorName, user.AuthorPW);
			
			task.qfd_LibraryTask.createPage(user.PageName, user.Desc, user.WordPath, false);
			task.qfd_PageTask.verifyPage(user.PageName, user.Desc, user.WordName, user.AuthorName, "word");			
			
			task.qfd_LibraryTask.createDraft(user.PageDraftName, user.Desc, user.WordPath, false);
			task.qfd_PageTask.verifyDraft(user.PageDraftName, user.Desc, user.WordName, user.AuthorName);
			
			task.qfd_PageTask.verifyEdit(user.PageDraftName, user.Desc, true);
			task.qfd_PageTask.editDraft(user.PageDraftName, user.ExcelPath);
			task.qfd_PageTask.verifyEditDraft(user.PageDraftName + " Updated", user.Desc, user.WordName, user.WordName, user.AuthorName);
			
			task.qfd_PageTask.saveVersion(user.PageDraftName + " Updated", user.AuthorName);
			task.qfd_PageTask.restoreVersion(user.PageDraftName + " Updated Again", user.PageDraftName + " Updated", user.AuthorName);
			
			task.qfd_PageTask.addComment(user.PageDraftName + " Updated", user.Desc, user.AuthorName);
			
			task.qfd_LoginTask.logOutAndLogIn(user.EditorName, user.EditorPW);
			
			task.qfd_PageTask.replyComment(user.PageDraftName + " Updated", user.Desc, user.EditorName);
			
    	}catch(Exception e){
    		
    		Times.st = false;
    		
    	}finally{
    		
    		finish();
    		
    	}
		
    }
	
}
