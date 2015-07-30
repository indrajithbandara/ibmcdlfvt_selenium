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

public class GUAT_PlaceCatalog extends TestCase {
	
	private Logger log;
	private WebDriver driver;
	private LogFile logFile;
	private Users user;
	private Task task;
	
	public GUAT_PlaceCatalog() {

		super.setName("testMain_UAT_PlaceCatalog");
		
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
	
	public void testMain_UAT_PlaceCatalog() throws Exception{

    	init();
    	
    	try{
    		
    		task.qfd_LoginTask.logIn(user.Owner3Name, user.Owner3PW);
    		task.qfd_CreatePlaceTask.create25Places("UAT_PlaceCata" + Datefuncs.genDateBasedRandVal(), null);
    		
    		task.qfd_LoginTask.logIn(user.Owner4Name, user.Owner4PW);
    		task.qfd_CreatePlaceTask.createPlaceNoName("UAT_PublicPlace" + Datefuncs.genDateBasedRandVal(), user.Desc);
    		task.qfd_MembersTask.accessEveryone();
    		
    		task.qfd_PlaceTask.verifyPlacesCatalog(user.Owner3Name, user.Owner4Name, user.Owner3PW);
    		
    		task.qfd_PlaceTask.verifyPermanentURL(user.Owner3Name, user.Owner3PW);
    		
    	}catch(Exception e){
    		
    		Times.st = false;
    		
    	}finally{
    		
    		finish();
    		
    	}
		
    }
	
}
