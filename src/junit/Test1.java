package junit;

import java.io.IOException;

import util.Times;

import config.CaseTask;
import config.Init;
import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;
import log.LogFile;

public class Test1 extends TestCase {

	public static int count = 0;
	
	public static Test suite(){
	
		Init init = new Init();
		
		LogFile logFile = new LogFile();
		
		TestSuite suite = CaseTask.ReRunSuite1;
		suite.setName("test1");
		
		try {
			
			init.init();
			logFile.start();
			
		} catch (IOException e) {
			
		}
		
		count = suite.countTestCases();
		
		TestSetup wrapper = new TestSetup(suite) {
	  
	        protected void tearDown() {
	        	
	            oneTimeTearDown();
	            
	        }
	        
	    };
	    
		return wrapper;
		
	}
	
	public static void oneTimeTearDown() {
		
		Times.Times ++;
		
	}
		
}
