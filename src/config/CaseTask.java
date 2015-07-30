package config;

import util.Times;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class CaseTask {

	public static TestSuite ReRunSuite1 = new TestSuite("ReRun");
	public static TestSuite ReRunSuite2 = new TestSuite("ReRun");
	
	public CaseTask(TestCase tc, int time){
			
		if(time == 0){
			
			if(!Times.st){
				
				try {
					
					ReRunSuite1.addTest(tc.getClass().newInstance());
					
				} catch (InstantiationException e) {
					
				} catch (IllegalAccessException e) {
					
				}
				
			}
			
		}else if(time == 1){
			
			if(!Times.st){
				
				try {
					
					ReRunSuite2.addTest(tc.getClass().newInstance());
					
				} catch (InstantiationException e) {
					
				} catch (IllegalAccessException e) {
					
				}
				
			}
			
		}else{
			
			
			
		}
			
	}
	
}
