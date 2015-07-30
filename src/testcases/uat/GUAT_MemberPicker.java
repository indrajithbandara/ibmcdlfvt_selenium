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

public class GUAT_MemberPicker extends TestCase {
	
	private Logger log;
	private WebDriver driver;
	private LogFile logFile;
	private Users user;
	private Task task;
	
	public GUAT_MemberPicker() {

		super.setName("testMain_UAT_MemberPicker");
		
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
	
	public void testMain_UAT_MemberPicker() throws Exception {

    	init();
    	
    	try{
    		
    		task.qfd_LoginTask.logIn(user.UIDCreator, user.CreatorPW);
    		//(UAT_MemberPicker03201714489);
    		
    		task.qfd_CreatePlaceTask.createPlace("UAT_MemberPicker" + Datefuncs.genDateBasedRandVal(), null);
			
			task.qfd_MembersTask.addMembers(1, 1, 0, 1, 1);
			task.qfd_MembersTask.addEditorGroup();
			task.qfd_MembersTask.createReaderSpecial("Mary O'Connor-Pitt", "passw0rd");
			task.qfd_MembersTask.createReader("Local Reader", "passw0rd");
			task.qfd_MembersTask.createAuthor("Author Tester", "passw0rd");
			task.qfd_MembersTask.createEditor("Editor Tester", "passw0rd");
			task.qfd_MembersTask.createManager("Manager Tester", "passw0rd");
			
    		task.qfd_LoginTask.logOutAndLogIn("Author Tester", "passw0rd");
			task.qfd_LoginTask.logOutAndLogIn("Author Tester", "passw0rd");
			
			task.qfd_MembersTask.verifyPicker(user.UIDCreator, user.AuthorName, "Editor Tester", "Mary O'Connor-Pitt", user.GroupUserName, user.FilterSearch, user.ManagerName);
			
    	}catch(Exception e){
    		
    		Times.st = false;
    		
    	}finally{
    		
    		finish();
    		
    	}
		
    }
	
}
