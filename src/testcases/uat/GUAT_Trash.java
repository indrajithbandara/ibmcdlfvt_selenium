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

public class GUAT_Trash extends TestCase {
	
	private Logger log;
	private WebDriver driver;
	private LogFile logFile;
	private Users user;
	private Task task;
	
	public GUAT_Trash() {

		super.setName("testMain_UAT_Trash");
		
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
	
	public void testMain_UAT_Trash() throws Exception{

    	init();
    	
    	try{
    		
    		task.qfd_LoginTask.logIn(user.UIDCreator, user.CreatorPW);
			task.qfd_CreatePlaceTask.createPlace("UAT_Trash" + Datefuncs.genDateBasedRandVal(), null);
			
			task.qfd_LibraryTask.createPage(user.PageName, user.Desc, null, false);
			task.qfd_LibraryTask.createListAndItem(user.ListName, user.Desc, user.ListItemTitle);
			task.qfd_LibraryTask.createLinkPage(user.LinkName, user.URLIBM);
			task.qfd_LibraryTask.createFolder(user.FolderName, user.Desc);
			task.qfd_LibraryTask.createImport(user.WordPath, user.Desc);
			
			task.qfd_TrashTask.deleteItemMoreAction(user.PageName);
			task.qfd_TrashTask.deleteItemOption(user.LinkName);
			
			task.qfd_TrashTask.restoreOneItemToLirary(user.PageName);
			
			task.qfd_TrashTask.deleteItemOption(user.PageName);
			task.qfd_TrashTask.deleteItemOption(user.ListName);
			task.qfd_TrashTask.deleteItemOption(user.FolderName);
			task.qfd_TrashTask.deleteItemOption(user.WordName);
			
			task.qfd_TrashTask.restoreOneItemToLirary(user.PageName);
			task.qfd_TrashTask.restoreTwoItemsToLibrary(user.ListName, user.FolderName);
			task.qfd_TrashTask.restoreAllToLibrary(new String[]{user.LinkName, user.WordName});
			
			task.qfd_TrashTask.deleteItemMoreAction(user.PageName);
			task.qfd_TrashTask.deleteItemOption(user.LinkName);
			
			task.qfd_TrashTask.emptyTrash(new String[]{user.PageName, user.LinkName});
			
			task.qfd_LibraryTask.createPage("Page One", null, null, false);
			task.qfd_LibraryTask.createPage("Page One", null, null, false);
			task.qfd_LibraryTask.verifyDuplicate("Page One");
			
			task.qfd_TrashTask.deleteItemMoreAction("Page One");
			task.qfd_TrashTask.verifyRename("Page One");
			
			task.qfd_LibraryTask.createPage("Page One", user.Desc, null, false);
			task.qfd_TrashTask.deleteSecondPage("Page One");
			task.qfd_TrashTask.replace("Page One", user.Desc);
			
			task.qfd_LibraryTask.createPage("Page One", null, null, false);
			task.qfd_TrashTask.deleteSecondPage("Page One");
			task.qfd_TrashTask.deleteItemOption("Page One Restored");
			task.qfd_TrashTask.restoreAllAndRename("Page One", "Page One Restored");
			
			task.qfd_LibraryTask.createPage("Page One", user.Desc + " Updated", null, false);
			task.qfd_TrashTask.deleteSecondPage("Page One");
			task.qfd_TrashTask.deleteItemOption("Page One Restored");
			task.qfd_TrashTask.restoreAllAndReplace("Page One", "Page One Restored", user.Desc + " Updated");
			
			task.qfd_LibraryTask.createPage("Page One Restored", null, null, false);
			task.qfd_TrashTask.deleteSecondPage("Page One Restored");
			task.qfd_TrashTask.verifyRenameOthers("Page One Restored");
			
    	}catch(Exception e){
    		
    		Times.st = false;
    		
    	}finally{
    		
    		finish();
    		
    	}
		
    }
	
}
