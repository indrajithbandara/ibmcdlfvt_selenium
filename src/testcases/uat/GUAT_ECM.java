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

public class GUAT_ECM extends TestCase {
	
	private Logger log;
	private WebDriver driver;
	private LogFile logFile;
	private Users user;
	private Task task;
	
	public GUAT_ECM() {

		super.setName("testMain_UAT_ECM");
		
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
	
    public void testMain_UAT_ECM() throws Exception{

    	init();
    	
    	try{
    		
    		task.qfd_LoginTask.logIn(user.UIDCreator, user.CreatorPW);
            task.qfd_CreatePlaceTask.createPlace("UAT_ECM" + Datefuncs.genDateBasedRandVal(), null);
            
            task.qfd_ECMTask.createDocLinkSummaryAndDownloadPage(user.PageName, user.ECMURL, user.ECMUser, user.ECMPW, false);
            
            task.qfd_LibraryTask.createCusLib(user.ECMLibName, user.Desc, false);
            task.qfd_ECMTask.configureCusLib(user.ECMLibName, user.ECMURL, user.ECMUser, user.ECMPW);
            
            task.qfd_LibraryTask.createPage("Page To Copy", user.Desc, user.WordPath, false);
            task.qfd_ECMTask.verifyPublishWizard("Page To Copy", user.ECMURL, user.ECMUser, user.ECMPW, false);
            task.qfd_ECMTask.publishToCopy("Page To Copy", user.ECMURL, user.ECMUser, user.ECMPW, false);
            task.qfd_ECMTask.verifyDocOnECMExist("Page To Copy", user.ECMLibName, true, false);
            
            task.qfd_LibraryTask.createPage("Page To Replace", user.Desc, user.WordPath, false);
            task.qfd_ECMTask.publishToLink("Page To Replace", user.ECMURL, user.ECMUser, user.ECMPW, false);
            task.qfd_ECMTask.verifyDocOnECMExist("Page To Replace", user.ECMLibName, true, false);
            
            task.qfd_LibraryTask.createPage("Page To Move", user.Desc, user.WordPath, false);
            task.qfd_ECMTask.publishToMove("Page To Move", user.ECMURL, user.ECMUser, user.ECMPW, false);
            task.qfd_ECMTask.verifyDocOnECMExist("Page To Move", user.ECMLibName, true, false);
			
    	}catch(Exception e){
    		
    		Times.st = false;
    		
    	}finally{
    		
    		finish();
    		
    	}
		
    }
}