package junit;

import java.io.IOException;

import util.Times;

import config.CaseTask;
import config.Init;
import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import log.LogFile;

public class Test2 extends TestCase {

	public static int count = 0;
	
	public static Test suite(){
	
		Init init = new Init();
		
		LogFile logFile = new LogFile();
		
		TestSuite suite = CaseTask.ReRunSuite2;
		suite.setName("test2");
		
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
