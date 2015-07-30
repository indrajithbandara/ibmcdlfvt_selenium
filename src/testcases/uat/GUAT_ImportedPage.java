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

public class GUAT_ImportedPage extends TestCase {

	private Logger log;
	private WebDriver driver;
	private LogFile logFile;
	private Users user;
	private Task task;
	
	public GUAT_ImportedPage() {

		super.setName("testMain_UAT_ImportedPage");
		
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
	
    public void testMain_UAT_ImportedPage() throws Exception{

    	init();
    	
    	try{
    		
	    	task.qfd_LoginTask.logIn(user.UIDCreator, user.CreatorPW);
			task.qfd_CreatePlaceTask.createPlace("UAT_Import" + Datefuncs.genDateBasedRandVal(), null);
			
			task.qfd_LibraryTask.verifyImport();
			
			task.qfd_LibraryTask.createImport(user.WordPath, user.Desc);
			
			task.qfd_LibraryTask.verifyImportedFile(user.WordName, user.Desc);
			
    	}catch(Exception e){
    		
    		Times.st = false;
    		
    	}finally{
    		
    		finish();
    		
    	}
		
    }
	
}
