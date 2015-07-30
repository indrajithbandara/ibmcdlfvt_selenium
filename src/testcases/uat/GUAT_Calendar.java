package testcases.uat;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import util.Datefuncs;
import util.Times;
import util.Assert.XPath;

import config.BrowserSetup;
import config.CaseTask;
import config.Task;
import config.Users;

import junit.framework.*;
import log.LogFile;

public class GUAT_Calendar extends TestCase {
	
	private Logger log;
	private WebDriver driver;
	private LogFile logFile;
	private Users user;
	private Task task;
	
	public GUAT_Calendar() {

		super.setName("testMain_UAT_Calendar");
		
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
	
    public void testMain_UAT_Calendar() throws Exception{

    	init();
    	
    	try{
    		
    		task.qfd_LoginTask.logIn(user.UIDCreator, user.CreatorPW);
            task.qfd_CreatePlaceTask.createPlace("UAT_Cal" + Datefuncs.genDateBasedRandVal(), null);
            
            task.qfd_MembersTask.addMembers(1, 1, 1, 1, 0);
            
            task.qfd_CalendarTask.createAndVerify(user.EventName, user.Desc, user.WordPath);
            task.qfd_CalendarTask.editEvent(user.EventName, user.Desc);
            
            task.qfd_CalendarTask.create3DaysEvent(user.EventName + "AllDay", user.Desc);
            task.qfd_CalendarTask.verifyViews(user.EventName+"Updated", user.EventName + "AllDay");
            
    	}catch(Exception e){
    		
    		Times.st = false;
    		
    	}finally{
    		
    		finish();
    		
    	}
		
    }
}