package tasks.common;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import util.Assert;
import util.Assert.XPath;

import config.Task;
import config.Wait;

import exceptions.AssertFailException;
import exceptions.ElementException;

import junit.framework.*;

import appobjects.common.QFD_Admin;
import appobjects.common.QFD_Login;

public class QFD_LoginTask { 
	
	private WebDriver driver;
	private Logger log;
	private Assert asserter;
	QFD_Login qfd_Login = null;
	QFD_Admin qfd_Admin = null;
	
	public QFD_LoginTask(WebDriver driver, Logger log){
		
		this.driver = driver;
		this.log = log;
		this.asserter = new Assert(driver, log);
		qfd_Login = new QFD_Login(driver);
		qfd_Admin = new QFD_Admin(driver);
		
	}
	
	public void logIn (String userName, String userPW) throws Exception {
		
		log.info("************************************");
		log.info("Starting 'Log In'...");
		Thread.sleep(Wait.MID);
			
		try {		
			
			asserter.elementClick(qfd_Login.getLogIn(), asserter.new XPath("//div[@class='lotusLoginLogo']"), true);	
			Thread.sleep(Wait.SHORT);
			
			qfd_Login.getUserName().sendKeys(userName);
			Thread.sleep(Wait.SHORT);
			
			qfd_Login.getUserPWD().sendKeys(userPW);
			Thread.sleep(Wait.SHORT);
			
			qfd_Login.getSubmitBtn().submit();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyEquals("Log Out", qfd_Login.getLogOut().getText()))
				log.warn("Verified the link 'Log Out' appears.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Log In'.");
		log.info("************************************");
		
	}

	public void logOutAndLogIn(String userName, String userPW) throws Exception {

		log.info("************************************");
		log.info("Starting 'Log Out And Log In'...");
		Thread.sleep(Wait.MID);
			
		try {		
			
			asserter.elementClick(qfd_Login.getLogOut(), asserter.new XPath("//h1[text()='IBM Lotus Quickr']"), true);
//			qfd_Login.getLogOut().click();
			Thread.sleep(Wait.MID);
				
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='IBM Lotus Quickr']")))
				log.warn("Verified Log out link brings user to the Quickr Login Screen.");
			
			qfd_Login.getUserName().click();
			Thread.sleep(Wait.PAUSE);
			qfd_Login.getUserName().sendKeys(userName);
			Thread.sleep(Wait.SHORT);
			
			qfd_Login.getUserPWD().click();
			Thread.sleep(Wait.PAUSE);
			qfd_Login.getUserPWD().sendKeys(userPW);
			Thread.sleep(Wait.SHORT);
			
			qfd_Login.getSubmitBtn().submit();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyEquals("Log Out", qfd_Login.getLogOut().getText()))
				log.warn("Verified the link 'Log Out' appears.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Log Out And Log In'.");
		log.info("************************************");
		
	}

	public void logOutAndLogInAlert(String userName, String userPW) throws Exception {

		log.info("************************************");
		log.info("Starting 'Log Out And Log In'...");
		Thread.sleep(Wait.MID);
			
		try {		
					
			qfd_Login.getLogOut().click();
			Thread.sleep(Wait.SHORT);
				
			driver.switchTo().alert().accept();
			log.info("Clicking on OK button.");
			Thread.sleep(Wait.MID);
			
			//mary
			//qfd_Login.getLogIn().click();
			//Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='IBM Lotus Quickr']")))
				log.warn("Verified Log out link brings user to the Quickr Login Screen.");
			
			qfd_Login.getUserName().click();
			Thread.sleep(Wait.PAUSE);
			qfd_Login.getUserName().sendKeys(userName);
			Thread.sleep(Wait.SHORT);
			
			qfd_Login.getUserPWD().click();
			Thread.sleep(Wait.PAUSE);
			qfd_Login.getUserPWD().sendKeys(userPW);
			Thread.sleep(Wait.SHORT);
			
			qfd_Login.getSubmitBtn().submit();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyEquals("Log Out", qfd_Login.getLogOut().getText()))
				log.warn("Verified the link 'Log Out' appears.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Log Out And Log In'.");
		log.info("************************************");
		
	}
	
	public void logOutAndLogInAlertWiki(String userName, String userPW) throws Exception {

		log.info("************************************");
		log.info("Starting 'Log Out And Log In From Wiki'...");
		Thread.sleep(Wait.MID);
			
		try {		
					
			qfd_Login.getLogOut().click();
			Thread.sleep(Wait.SHORT);
				
			driver.switchTo().alert().accept();
			log.info("Clicking on OK button.");
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='IBM Lotus Quickr']")))
				log.warn("Verified Log out link brings user to the Quickr Login Screen.");
			
			qfd_Login.getUserName().click();
			Thread.sleep(Wait.PAUSE);
			qfd_Login.getUserName().sendKeys(userName);
			Thread.sleep(Wait.SHORT);
			
			qfd_Login.getUserPWD().click();
			Thread.sleep(Wait.PAUSE);
			qfd_Login.getUserPWD().sendKeys(userPW);
			Thread.sleep(Wait.SHORT);
			
			qfd_Login.getSubmitBtn().submit();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyEquals("Log Out", qfd_Login.getLogOut().getText()))
				log.warn("Verified the link 'Log Out' appears.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Log Out And Log In From Wiki'.");
		log.info("************************************");
		
	}
	
	public void verifyNotMember(String userName, String userPW, String validName, String validPW) throws Exception {

		log.info("************************************");
		log.info("Starting 'Verify Not A Place Member'...");
		Thread.sleep(Wait.MID);
			
		try{		
					
			qfd_Login.getLogOut().click();
			Thread.sleep(Wait.MID);
			
			qfd_Login.getUserName().sendKeys(userName);
			Thread.sleep(Wait.SHORT);
			
			qfd_Login.getUserPWD().sendKeys(userPW);
			Thread.sleep(Wait.SHORT);
			
			qfd_Login.getSubmitBtn().submit();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyNotExist(asserter.new XPath("//a[text()='Log Out']")))
				log.warn("Verified " + userName + " is not a member of the place.");
			
			qfd_Login.getUserName().sendKeys(validName);
			Thread.sleep(Wait.SHORT);
			
			qfd_Login.getUserPWD().sendKeys(validPW);
			Thread.sleep(Wait.SHORT);
			
			qfd_Login.getSubmitBtn().submit();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyEquals("Log Out", qfd_Login.getLogOut().getText()))
				log.warn("Verified the link 'Log Out' appears.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify Not A Place Member'.");
		log.info("************************************");
		
	}

	public void verifyAnonymous() throws Exception {

		log.info("************************************");
		log.info("Starting 'Verify Anonymous User'...");
		Thread.sleep(Wait.MID);
			
		try{	
			
			String url = driver.getCurrentUrl();
			
			qfd_Login.getLogOut().click();
			Thread.sleep(Wait.MID);
			
			driver.get(url);
			log.info("Opening the place through URL.");
			Thread.sleep(Wait.MID);
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Library']")))
				log.warn("Verified anonymous user can go directly into the place without logging in.");
			
			if(asserter.verifyNotExist(asserter.new XPath("//a[contains(@title,'Members') and contains(text(),'Members')]")))
				log.warn("Verified Members link is not shown on the TOC.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify Anonymous User'.");
		log.info("************************************");
		
	}

	public void verifyBookmarking(String userName, String userPW) throws Exception {

		log.info("************************************");
		log.info("Starting 'Verify Bookmarking'...");
		Thread.sleep(Wait.MID);
			
		try{	
			
			String url = driver.getCurrentUrl();
			
			qfd_Login.getLogOut().click();
			Thread.sleep(Wait.MID);
			
			driver.get(url);
			log.info("Opening the place through URL.");
			Thread.sleep(Wait.MID);
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			qfd_Login.getUserName().sendKeys(userName);
			Thread.sleep(Wait.SHORT);
			
			qfd_Login.getUserPWD().sendKeys(userPW);
			Thread.sleep(Wait.SHORT);
			
			qfd_Login.getSubmitBtn().submit();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Library']")))
				log.warn("Verified the bookmark brings user directly to the place after authenticating.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify Bookmarking'.");
		log.info("************************************");
		
	}

	public void verifyNonAdmin(String userName, String userPW) throws Exception {

		log.info("************************************");
		log.info("Starting 'Verify Non-Admin User'...");
		Thread.sleep(Wait.MID);
			
		try{	
			
			asserter.elementClick(qfd_Login.getLogIn(), asserter.new XPath("//div[@class='lotusLoginLogo']"), true);	
			Thread.sleep(Wait.SHORT);
			
			qfd_Login.getUserName().sendKeys(userName);
			Thread.sleep(Wait.SHORT);
			
			qfd_Login.getUserPWD().sendKeys(userPW);
			Thread.sleep(Wait.SHORT);
			
			qfd_Login.getSubmitBtn().submit();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Log Out']")))
				log.warn("Verified the link 'Log Out' appears.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Create a Place']")))
				log.warn("Verified user can see Create a Place link in the Footer.");
			
			if(asserter.verifyFalse(driver.findElement(By.xpath("//a[text()='Work with Templates']")).isDisplayed()))
				log.warn("Verified there is no Work with Templates link.");
			
			if(asserter.verifyFalse(driver.findElement(By.xpath("//a[text()='Place Administration']")).isDisplayed()))
				log.warn("Verified there is no Place Administration link.");
			
			if(asserter.verifyFalse(driver.findElement(By.xpath("//a[text()='Site Administration']")).isDisplayed()))
				log.warn("Verified there is no Site Administration link.");
			
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
		
		log.info("End of 'Verify Non-Admin User'.");
		log.info("************************************");
		
	}

	public void verifyInvalid(String userName, String userPW) throws Exception {

		log.info("************************************");
		log.info("Starting 'Verify Invalid User'...");
		Thread.sleep(Wait.MID);
			
		try{	
			
			asserter.elementClick(qfd_Login.getLogIn(), asserter.new XPath("//div[@class='lotusLoginLogo']"), true);	
			Thread.sleep(Wait.SHORT);
			
			qfd_Login.getUserName().sendKeys(userName);
			Thread.sleep(Wait.SHORT);
			
			qfd_Login.getUserPWD().sendKeys(userPW);
			Thread.sleep(Wait.SHORT);
			
			qfd_Login.getSubmitBtn().submit();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//h6[contains(text(),'Invalid username or password was specified')]")))
				log.warn("Verified an error returned.");
			
			qfd_Login.getCancel().click();
			Thread.sleep(Wait.MID);
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify Invalid User'.");
		log.info("************************************");
		
	}

	public void verifyAdmin(String adminName, String adminPW) throws Exception {
			
		log.info("************************************");
		log.info("Starting 'Verify Admin User'...");
		Thread.sleep(Wait.MID);
			
		try{	
			
			asserter.elementClick(qfd_Login.getLogIn(), asserter.new XPath("//div[@class='lotusLoginLogo']"), true);	
			Thread.sleep(Wait.SHORT);
			
			qfd_Login.getUserName().sendKeys(adminName);
			Thread.sleep(Wait.SHORT);
			
			qfd_Login.getUserPWD().sendKeys(adminPW);
			Thread.sleep(Wait.SHORT);
			
			qfd_Login.getSubmitBtn().submit();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Log Out']")))
				log.warn("Verified the link 'Log Out' appears.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Work with Templates']")))
				log.warn("Verified admin user can see Work with Templates link in the Footer.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Place Administration']")))
				log.warn("Verified admin user can see Place Administration link in the Footer.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Site Administration']")))
				log.warn("Verified admin user can see Site Administration link in the Footer.");
			
			qfd_Admin.getPlaceAdmin().click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[contains(text(),'All places on')]")))
				log.warn("Verified the Place Administration screen is displayed.");
			
			qfd_Admin.getHome().click();
			Thread.sleep(Wait.MID);
			
			qfd_Admin.getWorkTemplates().click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='PlaceTypes']")))
				log.warn("Verified the PlaceTypes screen is displayed.");
			
			qfd_Admin.getHomeLink().click();
			Thread.sleep(Wait.MID);
			
			qfd_Admin.getSiteAdmin().click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='Instructions']")))
				log.warn("Verified the Instructions screen is displayed.");
			
			qfd_Admin.getHomeLink().click();
			Thread.sleep(Wait.MID);
			
			qfd_Login.getLogOut().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().alert().accept();
			log.info("Clicking on OK button.");
			Thread.sleep(Wait.SHORT);
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify Admin User'.");
		log.info("************************************");
		
	}

}
