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

public class GUAT_FolderACL extends TestCase {
	
	private Logger log;
	private WebDriver driver;
	private LogFile logFile;
	private Users user;
	private Task task;
	
	public GUAT_FolderACL() {

		super.setName("testMain_UAT_FolderACL");
		
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
	
	public void testMain_UAT_FolderACL() throws Exception{

    	init();
    	
    	try{
    		
	    	task.qfd_LoginTask.logIn(user.UIDCreator, user.CreatorPW);
			task.qfd_CreatePlaceTask.createPlace("UAT_FolderACL" + Datefuncs.genDateBasedRandVal(), null);
			
			task.qfd_MembersTask.addMembers(2, 4, 4, 4, 0);
			
			task.qfd_FolderTask.enableFolderACL();
			
			task.qfd_FolderTask.createRestrictedFolder("ResFolder", new String[]{user.ReaderName, user.AuthorName, user.EditorName}, new String[]{user.Author1Name, user.Editor1Name}, new String[]{user.Author2Name, user.Editor2Name}, new String[]{user.Author3Name, user.Editor3Name}, new String[]{user.UIDCreator, user.ReaderName, user.AuthorName, user.EditorName, user.ManagerName});
			task.qfd_FolderTask.createRestrictedSubFolder("ResSubFolder");
			
			task.qfd_FolderTask.changeACL(new String[]{user.EditorName, user.Author1Name, user.Editor2Name, user.Author3Name});
			task.qfd_FolderTask.verifyACL(new String[][]{{user.Reader1Name, user.Reader1PW}, {user.Editor1Name, user.Editor1PW}, {user.AuthorName, user.AuthorPW}, {user.Author2Name, user.Author2PW}, {user.Editor3Name, user.Editor3PW}, {user.UIDCreator, user.CreatorPW}});
			
			task.qfd_LoginTask.logOutAndLogIn(user.Editor3Name, user.Editor3PW);
			task.qfd_FolderTask.deleteFolder("ResFolder", "ResSubFolder");
			
    	}catch(Exception e){
    		
    		Times.st = false;
    		
    	}finally{
    		
    		finish();
    		
    	}
		
    }
	
}
