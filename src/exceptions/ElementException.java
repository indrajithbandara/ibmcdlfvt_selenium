package exceptions;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import config.BrowserSetup;

import util.ScenarioRunning;
import util.Screenshot;
import util.Times;

@SuppressWarnings("serial")
public class ElementException extends Exception {
	
	public ElementException(WebDriver driver, Logger log, Exception er) {
		
		String snapPath = Screenshot.screenShot(driver, ScenarioRunning.scenarioRunning);
		
		try {
			
			BrowserSetup.l.addStatus(ScenarioRunning.scenarioRunning, false);
			Times.st = false;
			
		} catch (IOException e) {
			
		}
		
		log.error("Errors on finding element(s).");
		log.fatal(snapPath);
		log.error(er.getMessage(), er);
		
	}
	
}
