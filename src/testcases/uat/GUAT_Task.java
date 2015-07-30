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

public class GUAT_Task extends TestCase {
	
	private Logger log;
	private WebDriver driver;
	private LogFile logFile;
	private Users user;
	private Task task;
	
	public GUAT_Task() {

		super.setName("testMain_UAT_Task");
		
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
	
    public void testMain_UAT_Task() throws Exception{

    	init();
    	
    	try{
    		
    		task.qfd_LoginTask.logIn(user.UIDCreator, user.CreatorPW);
            task.qfd_CreatePlaceTask.createPlace("UAT_Task" + Datefuncs.genDateBasedRandVal(), null);
            
            task.qfd_MembersTask.addMembers(0, 1, 1, 0, 0);
            
            task.qfd_TaskTask.createAndVerifyTask(user.TaskName, user.Desc, user.WordPath, user.UIDCreator);
            task.qfd_TaskTask.editTask(user.TaskName, user.Desc);
            
            task.qfd_TaskTask.createSimpleTask("Delete Test");
            task.qfd_TaskTask.createHighAssignTask("task1", user.AuthorName, user.WordPath);
            task.qfd_TaskTask.createMidAssignTask("task2", user.EditorName);
            task.qfd_TaskTask.createLowCompleteTask("task3");
            
            task.qfd_TaskTask.verifyListView();
            task.qfd_TaskTask.verifyGanttView();
            
            task.qfd_TaskTask.verifyDownload();
             
            task.qfd_TaskTask.verifySort(user.TaskName + "Updated");
            
            task.qfd_TaskTask.deleteTask("Delete Test");
            
            task.qfd_TaskTask.viewTaskInCalendar(user.TaskName + "Updated");
            
    	}catch(Exception e){
    		
    		Times.st = false;
    		
    	}finally{
    		
    		finish();
    		
    	}
		
    }
}