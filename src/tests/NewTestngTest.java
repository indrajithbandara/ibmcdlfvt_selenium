/**
 * 
 */
package tests;

/**
 * @author edaixicuijun
 *
 */
import java.lang.reflect.Method;  

import org.testng.annotations.DataProvider;  
import org.testng.annotations.Test;  
  
public class NewTestngTest{  
      
    @DataProvider(name = "dp")    
    public Object[][] createData(Method m) {    
      System.out.println(m.getName());  // print test method name    
      return new Object[][] { new Object[] { "Cedric" }};    
  
    }    
  
         
  
    @Test(dataProvider = "dp")    
    public void test1(String s) {    
        System.out.println(s);  
        assert true;  
    }    
  
         
  
    @Test(dataProvider = "dp")    
    public void test2(String s) {    
         System.out.println(s);  
            assert true;  
    }   
  
    @Test(invocationCount=1000,threadPoolSize=500)  
    public void testMethod() throws Exception{  
        int i = 0;  
        while(i < 100){  
            System.out.println(i++);  
            Thread.sleep(100);  
        }  
    }  
  
}  