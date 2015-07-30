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

public class GUAT_Folders extends TestCase {
	
	private Logger log;
	private WebDriver driver;
	private LogFile logFile;
	private Users user;
	private Task task;
	
	public GUAT_Folders() {

		super.setName("testMain_UAT_Folders");
		
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
	
	public void testMain_UAT_Folders() throws Exception{

    	init();
    	
    	try{
    		
	    	task.qfd_LoginTask.logIn(user.UIDCreator, user.CreatorPW);
			task.qfd_CreatePlaceTask.createPlace("UAT_Folder" + Datefuncs.genDateBasedRandVal(), null);
			
			task.qfd_LibraryTask.createFolder("simple list folder1", user.Desc);
			task.qfd_LibraryTask.createFolder("destination", user.Desc);
			
			task.qfd_FolderTask.verifySimpleFolder("GUAT Simple Folder", user.Desc, user.UIDCreator);
			task.qfd_FolderTask.verifyUploadAndSubfolder(user.SubFolderName, user.Desc, user.WordPath, user.WordName, "GUAT Simple Folder");
			
			task.qfd_FolderTask.verifyTabbedFolder("Tabbed Folder", user.Desc, user.UIDCreator);
			task.qfd_FolderTask.verifyTabbedUploadAndPage(user.PageName, user.Desc, user.WordPath, user.WordName);
			
			task.qfd_FolderTask.verifySlideFolder("Slide Show", user.Desc, user.UIDCreator);
			task.qfd_FolderTask.verifySlideSubfolderAndLink(user.SubFolderName, user.Desc, user.LinkName, user.URLIBM, "Slide Show");
			
			task.qfd_FolderTask.propertiesTest("GUAT Simple Folder", user.Desc);
			
			task.qfd_FolderTask.verifyThashRestore("Slide Show");
			
			task.qfd_FolderTask.createNavigatorFolder("Folder1", user.Desc);
			
			task.qfd_FolderTask.copyToDifferentPosition("Folder1");
			
			task.qfd_FolderTask.moveToDifferentPosition("Folder1");
			
			task.qfd_FolderTask.copyLibraryFolderToNavigator("simple list folder1", user.Desc);
			
			task.qfd_FolderTask.moveNavigatorFolderToLibraryFolder("simple list folder1", "destination", user.Desc);
			
    	}catch(Exception e){
    		
    		Times.st = false;
    		
    	}finally{
    		
    		finish();
    		
    	}
		
    }
	
}
