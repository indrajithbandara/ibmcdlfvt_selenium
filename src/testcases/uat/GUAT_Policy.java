package testcases.uat;

import junit.framework.TestCase;
import log.LogFile;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import util.Times;

import config.BrowserSetup;
import config.CaseTask;
import config.Task;
import config.Users;

public class GUAT_Policy extends TestCase {
	
	private Logger log;
	private WebDriver driver;
	private LogFile logFile;
	private Users user;
	private Task task;
	
	public GUAT_Policy() {

		super.setName("testMain_UAT_Policy");
		
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
	
	public void testMain_UAT_Policy() throws Exception{

    	init();
    	
    	try{
    		
    		task.qfd_LoginTask.logIn(user.AdminName, user.AdminPW);
    		
    		task.qfd_AdminTask.policyManagement("adminPolicy");
    		
    	}catch(Exception e){
    		
    		Times.st = false;
    		
    	}finally{
    		
    		finish();
    		
    	}
		
    }
	
}
