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

public class GUAT_Page extends TestCase {
	
	private Logger log;
	private WebDriver driver;
	private LogFile logFile;
	private Users user;
	private Task task;
	
	public GUAT_Page() {

		super.setName("testMain_UAT_Page");
		
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
	
    public void testMain_UAT_Page() throws Exception{

    	init();
    	
    	try{
    		
	    	task.qfd_LoginTask.logIn(user.UIDCreator, user.CreatorPW);
			task.qfd_CreatePlaceTask.createPlace("UAT_Page" + Datefuncs.genDateBasedRandVal(), null);
			
			task.qfd_MembersTask.addMembers(0, 1, 1, 0, 0);
			
			task.qfd_LoginTask.logOutAndLogIn(user.AuthorName, user.AuthorPW);
			
			task.qfd_LibraryTask.createPage(user.PageName, user.Desc, user.WordPath, false);
			task.qfd_PageTask.verifyPage(user.PageName, user.Desc, user.WordName, user.AuthorName, "word");
			task.qfd_PageTask.downloadAttach(user.PageName, "Word");			
			
			task.qfd_PageTask.verifyBusinessCard(user.AuthorName, "Author");
			task.qfd_PageTask.verifyEdit(user.PageName, user.Desc, false);
			
			task.qfd_PageTask.editPage(user.PageName, user.ExcelPath);
			task.qfd_PageTask.verifyPage(user.PageName + " Updated", user.Desc + " Updated", user.ExcelName, user.AuthorName, "excel");
			task.qfd_PageTask.downloadAttach(user.PageName, "Excel");	
			
			task.qfd_LibraryTask.createDraft(user.PageDraftName, user.Desc, user.WordPath, false);
			task.qfd_PageTask.verifyDraft(user.PageDraftName, user.Desc, user.WordName, user.AuthorName);
			
			task.qfd_PageTask.verifyEdit(user.PageDraftName, user.Desc, true);
			task.qfd_PageTask.editDraft(user.PageDraftName, user.ExcelPath);
			task.qfd_PageTask.verifyEditDraft(user.PageDraftName + " Updated", user.Desc, user.WordName, user.ExcelName, user.AuthorName);
			
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