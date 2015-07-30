package testcases;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

public class test {

	public static void main(String[] args){
		
		Selenium s = null;
		s = new DefaultSelenium("localhost", 4444, "firefox", "about:blank");
		s.start();
		s.open("http://dq13m.cn.ibm.com/lotusquickr");
		
	}
	
}
