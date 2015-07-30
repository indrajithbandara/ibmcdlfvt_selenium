package exceptions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import util.ScenarioRunning;
import util.Screenshot;

import junit.framework.AssertionFailedError;

@SuppressWarnings("serial")
public class AssertFailException extends AssertionFailedError{

	public AssertFailException(WebDriver driver, Logger log,AssertionFailedError er){
		
		String snapPath = Screenshot.screenShot(driver, ScenarioRunning.scenarioRunning);
		
		log.error("Errors on verification.");
		log.fatal(snapPath);
		log.error(er.getMessage(), er);
		
	}
	
}
