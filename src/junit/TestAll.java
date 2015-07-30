package junit;

import junit.framework.*;

public class TestAll {
	
	public static int count = 0;
	
	public static Test suite(){
		
		TestSuite suite = new TestSuite("testall");			
			
		suite.addTest(Test0.suite());
		suite.addTest(Test1.suite());	
		suite.addTest(Test2.suite());
		
		return suite;
		
	}
	
}
