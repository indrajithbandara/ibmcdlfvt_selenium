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

public class GUAT_Membership extends TestCase {
	
	private Logger log;
	private WebDriver driver;
	private LogFile logFile;
	private Users user;
	private Task task;
	
	public GUAT_Membership() {

		super.setName("testMain_UAT_Membership");
		
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
		
//		driver.quit();
		
	}
	
	public void testMain_UAT_Membership() throws Exception{

    	init();
    	
    	try{
    		
	    	task.qfd_LoginTask.logIn(user.UIDCreator, user.CreatorPW);
			task.qfd_CreatePlaceTask.createPlace("UAT_Membership" + Datefuncs.genDateBasedRandVal(), null);
			
			task.qfd_MembersTask.verifyMembers(user.UIDCreator);
			
			task.qfd_LibraryTask.createPage(user.PageName, user.Desc, user.WordPath, false);
			
			task.qfd_MembersTask.addMembers(1, 1, 1, 1, 1);
			
			task.qfd_LoginTask.logOutAndLogIn(user.OwnerName, user.OwnerPW);
			task.qfd_MembersTask.verifyMembersOptions("owner");
			
			task.qfd_LoginTask.logOutAndLogIn(user.ManagerName, user.ManagerPW);
			task.qfd_MembersTask.verifyMembersOptions("manager");
			
			task.qfd_LoginTask.logOutAndLogIn(user.EditorName, user.EditorPW);
			task.qfd_PageTask.verifyPageOptions(user.PageName, "editor");
			task.qfd_MembersTask.verifyMembersOptions("editor");
			
			task.qfd_LoginTask.logOutAndLogIn(user.AuthorName, user.AuthorPW);
			task.qfd_PageTask.verifyPageOptions(user.PageName, "author");
			task.qfd_MembersTask.verifyMembersOptions("author");
			
			task.qfd_LoginTask.logOutAndLogIn(user.ReaderName, user.ReaderPW);
			task.qfd_PageTask.verifyPageOptions(user.PageName, "reader");
			task.qfd_MembersTask.verifyMembersOptions("reader");
			
			task.qfd_LoginTask.logOutAndLogIn(user.OwnerName, user.OwnerPW);
			task.qfd_MembersTask.addAuthorGroup();
			
			task.qfd_LoginTask.logOutAndLogIn(user.GroupUserName, user.GroupUserPW);
			task.qfd_PageTask.verifyPageOptions(user.PageName, "author");
			task.qfd_MembersTask.verifyMembersOptions("author");
			
			task.qfd_LoginTask.logOutAndLogIn(user.OwnerName, user.OwnerPW);
			task.qfd_MembersTask.createEditor("UAT Tester", "password");
			
			task.qfd_LoginTask.logOutAndLogIn("UAT Tester", "password");
			task.qfd_PageTask.verifyPageOptions(user.PageName, "editor");
			task.qfd_MembersTask.verifyMembersOptions("editor");
			
			task.qfd_LoginTask.logOutAndLogIn(user.UIDCreator, user.CreatorPW);
			task.qfd_MembersTask.changeRole(user.OwnerName);
			
			task.qfd_LoginTask.logOutAndLogIn(user.OwnerName, user.OwnerPW);
			task.qfd_PageTask.verifyPageOptions(user.PageName, "reader");
			task.qfd_MembersTask.verifyMembersOptions("reader");
			
			task.qfd_LoginTask.logOutAndLogIn(user.ManagerName, user.ManagerPW);
			task.qfd_MembersTask.deleteMember(user.AuthorName);
			
			task.qfd_LoginTask.verifyNotMember(user.AuthorName, user.AuthorPW, user.ManagerName, user.ManagerPW);
			
			task.qfd_MembersTask.accessEveryone();
			
			task.qfd_LoginTask.verifyAnonymous();
			task.qfd_LibraryTask.verifyAnonymous(user.PageName);
			
			task.qfd_PlaceTask.deletePlaceAfterUse(user.UIDCreator, user.CreatorPW);
			
    	}catch(Exception e){
    		
    		Times.st = false;
    		
    	}finally{
    		
    		finish();
    		
    	}
		
    }
	
}
