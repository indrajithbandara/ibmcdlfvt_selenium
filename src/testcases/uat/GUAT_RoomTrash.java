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

public class GUAT_RoomTrash extends TestCase {
	
	private Logger log;
	private WebDriver driver;
	private LogFile logFile;
	private Users user;
	private Task task;
	
	public GUAT_RoomTrash() {

		super.setName("testMain_UAT_RoomTrash");
		
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
	
	public void testMain_UAT_RoomTrash() throws Exception{

    	init();
    	
    	try{
    		
    		task.qfd_LoginTask.logIn(user.UIDCreator, user.CreatorPW);
			task.qfd_CreatePlaceTask.createPlace("UAT_RoomTrash" + Datefuncs.genDateBasedRandVal(), null);
			
			task.qfd_RoomTask.createRoom(user.RoomName);
			
			task.qfd_LibraryTask.createPage(user.PageName, null, null, true);
			task.qfd_RoomTask.createLink(user.LinkName, user.URLIBM);
			task.qfd_RoomTask.createFolder(user.FolderName, user.Desc);
			
			task.qfd_TrashTask.deleteItemRoomIndex(user.PageName);
			task.qfd_TrashTask.deleteItemRoomIndex(user.LinkName);
			task.qfd_TrashTask.deleteRoomFolder(user.FolderName);
			
			task.qfd_TrashTask.restoreRoomFolder(user.FolderName);
			task.qfd_TrashTask.restoreAllToRoomIndex(new String[]{user.PageName, user.LinkName});
			
			task.qfd_TrashTask.deleteItemRoomIndex(user.PageName);
			task.qfd_TrashTask.deleteItemRoomIndex(user.LinkName);
			task.qfd_TrashTask.deleteRoomFolder(user.FolderName);
			
			task.qfd_TrashTask.deleteOneItem(user.FolderName);
			task.qfd_TrashTask.deleteTwoItems(user.PageName, user.LinkName);
			
			task.qfd_LibraryTask.createPage(user.PageName, null, null, true);
			task.qfd_RoomTask.createLink(user.LinkName, user.URLIBM);
			task.qfd_TrashTask.deleteItemRoomIndex(user.PageName);
			task.qfd_TrashTask.deleteItemRoomIndex(user.LinkName);
			
			task.qfd_TrashTask.emptyTrashRoom(new String[]{user.PageName, user.LinkName});
			
    	}catch(Exception e){
    		
    		Times.st = false;
    		
    	}finally{
    		
    		finish();
    		
    	}
		
    }
	
}
