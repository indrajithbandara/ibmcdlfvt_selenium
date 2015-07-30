package util;

import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import util.Assert.XPath;

import config.Element;
import config.Wait;

import exceptions.AssertFailException;
import exceptions.ElementException;
import junit.framework.AssertionFailedError;
import junit.framework.TestCase;

public class Assert extends TestCase{

	private Logger log;
	private WebDriver driver;

	public Assert(WebDriver driver, Logger log){
		
		this.driver = driver;
		this.log = log;
		
	}
	
	public class XPath{
		
		public XPath(String path){
			this.path = path;	
		}
		
		public String getPath(){		
			return path;		
		}
		
		private String path;
		
	}
	
	public boolean switchToWindow (String windowTitle) {
		
		try{
			
			Set<String> handles = driver.getWindowHandles();
			
			for(String s : handles) {
				
				driver.switchTo().window(s);
					
				if(driver.getTitle().contains(windowTitle)){
						
					log.info("Switched to windows whose title contains : " + windowTitle);
						
					return true;
						
				}else continue;
					
			}
			
			return false;
			
		}catch (NoSuchWindowException e) {
			
			return false;
			
		}
		
	}
	
	public boolean verifyTrue(boolean b){
		
		try{
			
			assertTrue(b);
			return true;
			
		}catch(AssertionFailedError e){
			
			e.printStackTrace();
			new AssertFailException(driver, log ,e);
			return false;
			
		}catch(NoSuchElementException e){
			
			e.printStackTrace();
			new AssertFailException(driver, log ,new AssertionFailedError());
			return false;
			
		}
		
	}

	public boolean verifyFalse(boolean b){
		
		try{
			
			assertFalse(b);
			return true;
			
		}catch(AssertionFailedError e){
			
			e.printStackTrace();
			new AssertFailException(driver, log ,e);
			return false;
			
		}catch(NoSuchElementException e){
			
			e.printStackTrace();
			new AssertFailException(driver, log ,new AssertionFailedError());
			return false;
			
		}
		
	}
	
	public boolean verifyEquals(String string, String text) {

		try{
			
			assertEquals(string, text);
			return true;
			
		}catch(AssertionFailedError e){
			
			e.printStackTrace();
			new AssertFailException(driver, log ,e);
			return false;
			
		}
		
	}
	
	public boolean verifyExist(XPath xpath) {
			
		String path = xpath.getPath();
	
		try{
			//June	"//span[@style='color: #2f4f4f']/span[@style='background-color: #000000' and contains(text(),'GREEN ON BLACK')]"
//			WebElement color = driver.findElement(By.xpath(path));
//			assertNotNull(color);
			assertNotNull(driver.findElement(By.xpath(path)));
			return true;
			
		}catch(AssertionFailedError e){
			
			e.printStackTrace();
			new AssertFailException(driver, log ,e);
			return false;
			
		}catch(Exception e){
			
			e.printStackTrace();
			new AssertFailException(driver, log ,new AssertionFailedError());
			return false;
			
		}
		
	}

	public boolean verifyNotExist(XPath xpath) {
		
		String path = xpath.getPath();
	
		try{
			
			assertNotNull(driver.findElement(By.xpath(path)));
			new AssertFailException(driver, log ,new AssertionFailedError());
			return false;
			
		}catch(AssertionFailedError e){
			
			return true;
			
		}catch(Exception e){
			
			return true;
			
		}
		
	}
	
	public boolean verifyNotExist(String text) {
		
		try{
			
			driver.findElement(By.xpath("//div[contains(text(),'" + text + "')]"));
			AssertionFailedError er = new AssertionFailedError();
			er.printStackTrace();
			new AssertFailException(driver, log , er);
			return false;
			
		}catch(Exception e){
			
			try{
				
				driver.findElement(By.xpath("//a[contains(text(),'" + text + "')]"));
				AssertionFailedError er = new AssertionFailedError();
				er.printStackTrace();
				new AssertFailException(driver, log , er);
				return false;
				
			}catch(Exception e1){
				
				try{
					
					driver.findElement(By.xpath("//td[contains(text(),'" + text + "')]"));
					AssertionFailedError er = new AssertionFailedError();
					er.printStackTrace();
					new AssertFailException(driver, log , er);
					return false;
					
				}catch(Exception e2){
					
					try{
						
						driver.findElement(By.xpath("//span[contains(text(),'" + text + "')]"));
						AssertionFailedError er = new AssertionFailedError();
						er.printStackTrace();
						new AssertFailException(driver, log , er);
						return false;
						
					}catch(Exception e3){
						
						return true;
						
					}
					
				}	
				
			}
			
		}
		
	}
	
	public boolean verifyExist(String text){	
	
		try{
			
			driver.findElement(By.xpath("//div[contains(text(),'" + text + "')]"));
			return true;
			
		}catch(Exception e){
			
			try{
				
				driver.findElement(By.xpath("//a[contains(text(),'" + text + "')]"));
				return true;
				
			}catch(Exception e1){
				
				try{
					
					driver.findElement(By.xpath("//td[contains(text(),'" + text + "')]"));
					return true;
					
				}catch(Exception e2){
					
					try{
						
						driver.findElement(By.xpath("//span[contains(text(),'" + text + "')]"));
						return true;
						
					}catch(Exception e3){
						
						e3.printStackTrace();
						new AssertFailException(driver, log ,new AssertionFailedError());
						return false;
						
					}
					
				}	
				
			}
			
		}
			
	}
	
	public boolean elementClick(Element e, XPath path, boolean b) throws InterruptedException {

		int i = 0;
		
		if(b){
			
			do{
				
				e.click();
				Thread.sleep(Wait.SHORT);
				i++;
				
			}while(!elementExistXPath(path) && i < Wait.TIMES);
			
			if(i == Wait.TIMES){
				
				AssertionFailedError er = new AssertionFailedError();
				er.printStackTrace();
				new AssertFailException(driver, log , er);
				return false;
				
			}
			
			else return true;
			
		}else{
			
			do{
				
				e.click();
				Thread.sleep(Wait.SHORT);
				i++;
				
			}while(elementExistXPath(path) && i < Wait.TIMES);
			
			if(i == Wait.TIMES){
				
				AssertionFailedError er = new AssertionFailedError();
				er.printStackTrace();
				new AssertFailException(driver, log , er);
				return false;
				
			}
			
			else return true;
			
		}
		
	}

	private boolean elementExistXPath(XPath path) {
		
		try{
			
			driver.findElement(By.xpath(path.getPath()));
			return true;
			
		}catch(Exception e){
			
			return false;
			
		}
		
	}

	public boolean elementExistXPath(String path) {
		
		try{
			
			driver.findElement(By.xpath(path));
			return true;
			
		}catch(Exception e){
			
			new ElementException(driver, log, e);
			return false;
			
		}
		
	}

	public void elementClickWait(Element e, XPath path, boolean b) throws InterruptedException {

		int i = 0;
		
		if(b){
			
			e.click();
			
			do{
				
				Thread.sleep(Wait.MID);
				i++;
				
			}while(!elementExistXPath(path) && i < Wait.TIMES);
			
			if(i == Wait.TIMES){
				
				AssertionFailedError er = new AssertionFailedError();
				er.printStackTrace();
				new AssertFailException(driver, log , er);
				
			}
			
		}else{

			e.click();
			
			do{
				
				Thread.sleep(Wait.MID);
				i++;
				
			}while(elementExistXPath(path) && i <= Wait.TIMES);
			
			if(i == Wait.TIMES){
				
				AssertionFailedError er = new AssertionFailedError();
				er.printStackTrace();
				new AssertFailException(driver, log , er);
				
			}
			
		}
		
	}

	public void checkboxClick(XPath xpath, boolean b) throws InterruptedException {

		String path = xpath.getPath();
		int i = 0;
		
		Element e = new Element(driver.findElement(By.xpath(path)), "checkbox");
		
		if(b){
			
			do{
				
				e.click();
				Thread.sleep(Wait.SHORT);
				i++;
				
			}while(!e.isSelected() && i < Wait.TIMES);
			
			log.info("Checked the checkbox.");
			
		}else{
			
			do{
				
				e.click();
				Thread.sleep(Wait.SHORT);
				i++;
				
			}while(e.isSelected() && i < Wait.TIMES);
			
			log.info("Unchecked the checkbox.");
			
		}
		
	}

}
