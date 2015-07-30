package testcases.uat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import tasks.common.QFD_LoginTask;
import util.Times;

import config.BrowserSetup;
import config.CaseTask;
import config.Users;

import junit.framework.*;
import log.LogFile;

public class Selenium2Example extends TestCase {
	
	private Logger log;
	private WebDriver driver;
	private LogFile logFile;
	private Users user;
	private QFD_LoginTask qfd_LoginTask;
	
	private void init(){
		
		
		
	}
	
	@SuppressWarnings({ "deprecation", "static-access" })
	private void finish(){
		
		new CaseTask(this, Times.Times);
		log.shutdown();
		logFile.changeLogName(this.getClass().getName());
		
		driver.quit();
		
	}
	
    public void testMain_test() throws Exception{
    	
    	Calendar now = Calendar.getInstance();         		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy"); 
		String day = sdf.format(now.getTime());
		
		System.out.println(day);
		
    }
}