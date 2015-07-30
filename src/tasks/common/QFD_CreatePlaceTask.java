package tasks.common;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import config.Element;
import config.Task;
import config.Users;
import config.Wait;
import exceptions.ElementException;

import util.Assert;

import junit.framework.*;
import appobjects.common.QFD_CreatePlace;
import appobjects.common.QFD_Login;

public class QFD_CreatePlaceTask {
	
	private WebDriver driver;
	private Logger log;
	private Assert asserter;
	QFD_Login qfd_Login = null;
	QFD_CreatePlace qfd_CreatePlace = null;
	
	public QFD_CreatePlaceTask(WebDriver driver,Logger log){
		
		this.driver = driver;
		this.log = log;
		this.asserter = new Assert(driver, log);
		qfd_Login = new QFD_Login(driver);
		qfd_CreatePlace = new QFD_CreatePlace(driver);
		
	}
	
	public void createPlace(String placeName, String placeDesc) throws Exception {
		
		log.info("************************************");
		log.info("Starting 'Create Place'...");
		Thread.sleep(Wait.MID);
		
		try{

			Users.PlaceName = placeName;
			
			qfd_CreatePlace.getCreatePlaceButton().click();
			Thread.sleep(Wait.LONG);
			
			qfd_CreatePlace.getStandradPlaceItem().click();
			Thread.sleep(Wait.LONG);
	
			qfd_CreatePlace.getPlaceName().sendKeys(placeName);
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_CreatePlace.getPlaceDescription().sendKeys(placeDesc);
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_CreatePlace.getCreateButton().click();
			Thread.sleep(Wait.MID);
			Thread.sleep(Wait.VERYLONG);
			
			new Element(driver.findElement(By.linkText(placeName)), placeName + " link").click();
			Thread.sleep(Wait.VERYLONG);
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Create Place'.");
		log.info("************************************");
	
	}

	public void createPlaceNoName(String placeName, String placeDesc) throws Exception {
		
		log.info("************************************");
		log.info("Starting 'Create Place'...");
		Thread.sleep(Wait.MID);
		
		try{
			
			qfd_CreatePlace.getCreatePlaceButton().click();
			Thread.sleep(Wait.LONG);
			
			qfd_CreatePlace.getStandradPlaceItem().click();
			Thread.sleep(Wait.LONG);
	
			qfd_CreatePlace.getPlaceName().sendKeys(placeName);
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_CreatePlace.getPlaceDescription().sendKeys(placeDesc);
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_CreatePlace.getCreateButton().click();
			Thread.sleep(Wait.MID);
			Thread.sleep(Wait.VERYLONG);
			
			new Element(driver.findElement(By.linkText(placeName)), placeName + " link").click();
			Thread.sleep(Wait.VERYLONG);
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Create Place'.");
		log.info("************************************");
	
	}
	
	public void verifyCreatePlace(String placeName, String placeDesc, String userName, String userPW) throws Exception {

		log.info("************************************");
		log.info("Starting 'Verify Create Place'...");
		Thread.sleep(Wait.MID);
		
		try{

			qfd_CreatePlace.getCreatePlaceButton().click();
			Thread.sleep(Wait.LONG);
			
		
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='Create a Place']")))
				log.warn("Verified that the Create a Place screen is displayed");
				
			qfd_CreatePlace.getStandradPlaceItem().click();
			Thread.sleep(Wait.LONG);
	
			qfd_CreatePlace.getPlaceName().sendKeys(placeName);
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_CreatePlace.getPlaceDescription().sendKeys(placeDesc);
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_CreatePlace.getUserName().sendKeys(userName);
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_CreatePlace.getUserPW().sendKeys(userPW);
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_CreatePlace.getCreateButton().click();
			Thread.sleep(Wait.MID);
			Thread.sleep(Wait.VERYLONG);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='Your new place is ready']")))
				log.warn("Verified user see 'Creating your place' page.");
			
			qfd_CreatePlace.getBackHome().click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[contains(text(),'Places')]")))
				log.warn("Verified user is taken back to the Quickr home page.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify Create Place'.");
		log.info("************************************");
		
	}

	public void create2Blogs(String blogName, String desc) throws Exception {

		log.info("************************************");
		log.info("Starting 'Create Two Blogs'...");
		Thread.sleep(Wait.MID);
		
		try{

			Users.PlaceName = blogName;
			
			qfd_CreatePlace.getCreatePlaceButton().click();
			Thread.sleep(Wait.LONG);
			
			qfd_CreatePlace.getBlogItem().click();
			Thread.sleep(Wait.LONG);
	
			qfd_CreatePlace.getPlaceName().sendKeys(blogName);
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_CreatePlace.getPlaceDescription().sendKeys(desc);
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_CreatePlace.getCreateButton().click();
			Thread.sleep(Wait.MID);
			Thread.sleep(Wait.VERYLONG);
			
			qfd_CreatePlace.getCreateAnotherPlace().click();
			Thread.sleep(Wait.MID);
			
			qfd_CreatePlace.getBlogItem().click();
			Thread.sleep(Wait.LONG);
	
			qfd_CreatePlace.getPlaceName().sendKeys(blogName + " 1");
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_CreatePlace.getPlaceDescription().sendKeys(desc);
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_CreatePlace.getCreateButton().click();
			Thread.sleep(Wait.MID);
			Thread.sleep(Wait.VERYLONG);
			
			qfd_CreatePlace.getBackHome().click();
			Thread.sleep(Wait.MID);
			
			qfd_Login.getLogOut().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().alert().accept();
			log.info("Clicking on OK button.");
			Thread.sleep(Wait.MID);
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Create Two Blogs'.");
		log.info("************************************");
		
	}

	public void create25Places(String placeName, String desc) throws Exception {

		log.info("************************************");
		log.info("Starting 'Create 25 Places'...");
		Thread.sleep(Wait.MID);
		
		try{

			Users.PlaceName = placeName;
			
			qfd_CreatePlace.getCreatePlaceButton().click();
			Thread.sleep(Wait.LONG);
			
			qfd_CreatePlace.getStandradPlaceItem().click();
			Thread.sleep(Wait.LONG);
			
			qfd_CreatePlace.getPlaceName().sendKeys(placeName + "0");
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_CreatePlace.getPlaceDescription().sendKeys(desc);
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_CreatePlace.getCreateButton().click();
			Thread.sleep(Wait.VERYLONG);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='Your new place is ready']")))
				log.warn("Verified user see 'Creating your place' page.");
			
			for(int i = 0; i < 24; i++){
				
				qfd_CreatePlace.getCreateAnotherPlace().click();
				Thread.sleep(Wait.MID);
				
				qfd_CreatePlace.getStandradPlaceItem().click();
				Thread.sleep(Wait.LONG);
				
				qfd_CreatePlace.getPlaceName().sendKeys(placeName + (i+1));
				Thread.sleep(Wait.VERYSHORT);
				
				qfd_CreatePlace.getPlaceDescription().sendKeys(desc);
				Thread.sleep(Wait.VERYSHORT);
				
				qfd_CreatePlace.getCreateButton().click();
				Thread.sleep(Wait.VERYLONG);
				
				if(asserter.verifyExist(asserter.new XPath("//h1[text()='Your new place is ready']")))
					log.warn("Verified user see 'Creating your place' page.");
				
			}
			
			qfd_CreatePlace.getBackHome().click();
			Thread.sleep(Wait.MID);
			
			qfd_Login.getLogOut().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().alert().accept();
			log.info("Clicking on OK button.");
			Thread.sleep(Wait.MID);
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Create 25 Places'.");
		log.info("************************************");
		
	}

	public void createBlog(String blogName, String desc) throws Exception {

		log.info("************************************");
		log.info("Starting 'Create Blogs'...");
		Thread.sleep(Wait.MID);
		
		try{

			Users.PlaceName = blogName;
			
			qfd_CreatePlace.getCreatePlaceButton().click();
			Thread.sleep(Wait.LONG);
			
			qfd_CreatePlace.getBlogItem().click();
			Thread.sleep(Wait.LONG);
	
			qfd_CreatePlace.getPlaceName().sendKeys(blogName);
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_CreatePlace.getPlaceDescription().sendKeys(desc);
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_CreatePlace.getCreateButton().click();
			Thread.sleep(Wait.MID);
			Thread.sleep(Wait.VERYLONG);
			
			new Element(driver.findElement(By.linkText(blogName)), blogName + " link").click();
			Thread.sleep(Wait.VERYLONG);
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + Users.PlaceName + "']")))
				log.warn("Verified the place is opened.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Blog Fast Start']")))
				log.warn("Verified the Blog component is correct.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Create Blogs'.");
		log.info("************************************");
		
	}

	public void createWiki(String wikiName, String desc) throws Exception {

		log.info("************************************");
		log.info("Starting 'Create Blogs'...");
		Thread.sleep(Wait.MID);
		
		try{

			Users.PlaceName = wikiName;
			
			qfd_CreatePlace.getCreatePlaceButton().click();
			Thread.sleep(Wait.LONG);
			
			qfd_CreatePlace.getWikiItem().click();
			Thread.sleep(Wait.LONG);
	
			qfd_CreatePlace.getPlaceName().sendKeys(wikiName);
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_CreatePlace.getPlaceDescription().sendKeys(desc);
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_CreatePlace.getCreateButton().click();
			Thread.sleep(Wait.MID);
			Thread.sleep(Wait.VERYLONG);
			
			new Element(driver.findElement(By.linkText(wikiName)), wikiName + " link").click();
			Thread.sleep(Wait.VERYLONG);
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + Users.PlaceName + "']")))
				log.warn("Verified the place is opened.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='New Wiki Page']")))
				log.warn("Verified the Wiki component is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//p[text()='Here we put our Wiki documents']")))
				log.warn("Verified the Summary View is shown as default.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Create Wiki'.");
		log.info("************************************");
		
	}
	
}
