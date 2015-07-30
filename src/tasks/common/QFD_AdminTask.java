package tasks.common;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import config.BrowserSetup;
import config.Element;
import config.Users;
import config.Wait;

import exceptions.ElementException;

import util.Assert;
import util.Assert.XPath;
import appobjects.common.QFD_Admin;
import appobjects.common.QFD_CreatePlace;
import appobjects.common.QFD_Login;

public class QFD_AdminTask {

	private WebDriver driver;
	private Logger log;
	private Assert asserter;
	private Actions action;
	private AutoITTask AT = null;
	QFD_LoginTask qfd_LoginTask = null;
	QFD_CreatePlace qfd_CreatePlace = null;
	QFD_CreatePlaceTask qfd_CreatePlaceTask = null;
	QFD_Admin qfd_Admin = null;
	QFD_Login qfd_Login = null;
	
	public QFD_AdminTask(WebDriver driver, Logger log){
		
		this.driver = driver;
		this.log = log;
		this.asserter = new Assert(driver, log);
		this.action = new Actions(driver);
		AT = new AutoITTask(driver, log);
		qfd_LoginTask = new QFD_LoginTask(driver, log);
		qfd_CreatePlace = new QFD_CreatePlace(driver);
		qfd_CreatePlaceTask = new QFD_CreatePlaceTask(driver, log);
		qfd_Admin = new QFD_Admin(driver);
		qfd_Login = new QFD_Login(driver);
		
	}

	public void assignPersonAndGroupCreator(String adminName, String adminPW, String userName, String groupName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Assign Person & Group Place Creator Rights'...");
		Thread.sleep(Wait.MID);
			
		qfd_LoginTask.logIn(adminName, adminPW);
		
		try{	
			
			qfd_Admin.getSiteAdmin().click();
			Thread.sleep(Wait.MID);
			
			qfd_Admin.getSecurity().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='Security']")))
				log.warn("Verified that the Security screen is displayed.");
			
			qfd_Admin.getAddCreator().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='Server Security: Add Access']")))
				log.warn("Verified that the Server Security screen is displayed.");
			
			qfd_Admin.getDirectory().click();
			Thread.sleep(Wait.SHORT);
			
			String handles[] = new String[5];
			driver.getWindowHandles().toArray(handles);
			
			driver.switchTo().window(handles[1]);
			Thread.sleep(Wait.PAUSE);
			
			if(asserter.verifyExist(asserter.new XPath("//div[text()='Directory Search:']")))
				log.warn("Verified that the Search box is displayed.");
			
			qfd_Admin.getDirectoryText().sendKeys(userName);
			Thread.sleep(Wait.SHORT);
			
			qfd_Admin.getSearch().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().frame(qfd_Admin.getSearchFrame());
			Thread.sleep(Wait.PAUSE);
			
			asserter.checkboxClick(asserter.new XPath("//input[contains(@value,'" + userName + "')]"), true);
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			qfd_Admin.getAdd().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().alert().accept();
			log.info("Clicking on OK button.");
			Thread.sleep(Wait.SHORT);
			
			asserter.checkboxClick(asserter.new XPath("//input[@type='radio' and @value='1']"), true);
			
			qfd_Admin.getDirectoryText().clear();
			Thread.sleep(Wait.PAUSE);
			
			qfd_Admin.getDirectoryText().sendKeys(groupName);
			Thread.sleep(Wait.SHORT);
			
			qfd_Admin.getSearch().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().frame(qfd_Admin.getSearchFrame());
			Thread.sleep(Wait.PAUSE);
			
			asserter.checkboxClick(asserter.new XPath("//input[contains(@value,'" + groupName + ",')]"), true);
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			qfd_Admin.getAdd().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().alert().accept();
			log.info("Clicking on OK button.");
			Thread.sleep(Wait.SHORT);
			
			qfd_Admin.getClose().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().window(handles[0]);
			Thread.sleep(Wait.PAUSE);
			
			if(asserter.verifyExist(asserter.new XPath("//td[contains(text(),'" + userName + "')]")))
				log.warn("Verified the single user is added correctly.");
			
			if(asserter.verifyExist(asserter.new XPath("//td[contains(text(),'" + groupName + "')]")))
				log.warn("Verified the group is added correctly.");
			
			qfd_Admin.getNext().click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist(asserter.new XPath("//option[contains(text(),'" + userName + "')]")))
				log.warn("Verified the single user displays correctly.");
			
			if(asserter.verifyExist(asserter.new XPath("//option[contains(text(),'" + groupName + "')]")))
				log.warn("Verified the group displays correctly.");
			
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
		
		log.info("End of 'Assign Person & Group Place Creator Rights'.");
		log.info("************************************");
		
	}

	public void verifyPersonAndGroupCreator(String invalidName, String invalidPW,
			String validName, String validPW, String groupUserName,
			String groupUserPW, String personPlaceName, String groupPlaceName, String desc) throws Exception {
		
		log.info("************************************");
		log.info("Starting 'Verify Person & Group Place Creator Rights'...");
		Thread.sleep(Wait.MID);
			
		qfd_LoginTask.logIn(invalidName, invalidPW);
		
		try{	
			
			if(asserter.verifyFalse(driver.findElement(By.xpath("//a[text()='Create a Place']")).isDisplayed()))
				log.warn("Verified that the Create a Place button is not displayed.");
			
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
		
		qfd_LoginTask.logIn(validName, validPW);
		
		try{	
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Create a Place']")))
				log.warn("Verified that the Create a Place button is displayed.");
		
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		qfd_CreatePlaceTask.verifyCreatePlace(personPlaceName, desc, invalidName, invalidPW);
		
		try{	
			
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
		
		qfd_LoginTask.logIn(groupUserName, groupUserPW);
		
		try{	
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Create a Place']")))
				log.warn("Verified that the Create a Place button is displayed.");
		
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		qfd_CreatePlaceTask.verifyCreatePlace(groupPlaceName, desc, invalidName, invalidPW);
		
		try{	
			
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
		
		log.info("End of 'Verify Person & Group Place Creator Rights'.");
		log.info("************************************");
		
	}
	
	public void removePersonAndGroupCreator(String adminName, String adminPW, String userName, String groupName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Remove Person & Group Place Creator Rights'...");
		Thread.sleep(Wait.MID*3);
			
		qfd_LoginTask.logIn(adminName, adminPW);
		
		try{	
			
			qfd_Admin.getSiteAdmin().click();
			Thread.sleep(Wait.MID*3);
			
			qfd_Admin.getSecurity().click();
			Thread.sleep(Wait.SHORT*3);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='Security']")))
				log.warn("Verified that the Security screen is displayed.");
			
			qfd_Admin.getRemoveCreator().click();
			Thread.sleep(Wait.SHORT*3);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='Server Security: Remove Access']")))
				log.warn("Verified that the Server Security: Remove Access screen is displayed.");
			
			asserter.checkboxClick(asserter.new XPath("//input[contains(@value,'" + userName + "')]"), true);
			asserter.checkboxClick(asserter.new XPath("//input[contains(@value,'" + groupName + "')]"), true);
			
			qfd_Admin.getNext().click();
			Thread.sleep(Wait.SHORT*3);
			
			if(asserter.verifyNotExist(asserter.new XPath("//option[contains(text(),'" + userName + "')]")))
				log.warn("Verified the single user is removed correctly.");
			
			if(asserter.verifyNotExist(asserter.new XPath("//option[contains(text(),'" + groupName + "')]")))
				log.warn("Verified the group is removed correctly.");
			
			WebElement[] e = new WebElement[5];
			if(asserter.verifyTrue(driver.findElements(By.xpath("//input[@name='h_CreateAccess']")).toArray(e)[0].isSelected()))
				log.warn("Verified that 'Anyone can create new places on this server' radio button is selected.");
				
			qfd_Login.getLogOut().click();
			Thread.sleep(Wait.SHORT*3);
			
			driver.switchTo().alert().accept();
			log.info("Clicking on OK button.");
			Thread.sleep(Wait.SHORT*3);
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Remove Person & Group Place Creator Rights'.");
		log.info("************************************");
		
	}
	
	public void assignPersonAndGroupAdmin(String adminName, String adminPW, String userName, String groupName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Assign Person & Group Place Admin Rights'...");
		Thread.sleep(Wait.MID);
			
		qfd_LoginTask.logIn(adminName, adminPW);
		
		try{	
			
			qfd_Admin.getSiteAdmin().click();
			Thread.sleep(Wait.MID);
			
			qfd_Admin.getSecurity().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='Security']")))
				log.warn("Verified that the Security screen is displayed.");
			
			qfd_Admin.getAddAdmin().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='Server Security: Add Access']")))
				log.warn("Verified that the Server Security screen is displayed.");
			
			Thread.sleep(Wait.SHORT*2);
			qfd_Admin.getDirectory().click();
			Thread.sleep(Wait.SHORT);
			
			String handles[] = new String[5];
			driver.getWindowHandles().toArray(handles);
			
			driver.switchTo().window(handles[1]);
			Thread.sleep(Wait.PAUSE);
			
			if(asserter.verifyExist(asserter.new XPath("//div[text()='Directory Search:']")))
				log.warn("Verified that the Search box is displayed.");
			
			qfd_Admin.getDirectoryText().sendKeys(userName);
			Thread.sleep(Wait.SHORT);
			
			qfd_Admin.getSearch().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().frame(qfd_Admin.getSearchFrame());
			Thread.sleep(Wait.PAUSE);
			
			asserter.checkboxClick(asserter.new XPath("//input[contains(@value,'" + userName + "')]"), true);
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			qfd_Admin.getAdd().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().alert().accept();
			log.info("Clicking on OK button.");
			Thread.sleep(Wait.SHORT);
			
			asserter.checkboxClick(asserter.new XPath("//input[@type='radio' and @value='1']"), true);
			
			qfd_Admin.getDirectoryText().clear();
			Thread.sleep(Wait.PAUSE);
			
			qfd_Admin.getDirectoryText().sendKeys(groupName);
			Thread.sleep(Wait.SHORT);
			
			qfd_Admin.getSearch().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().frame(qfd_Admin.getSearchFrame());
			Thread.sleep(Wait.PAUSE);
			
			asserter.checkboxClick(asserter.new XPath("//input[contains(@value,'" + groupName + ",')]"), true);
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			qfd_Admin.getAdd().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().alert().accept();
			log.info("Clicking on OK button.");
			Thread.sleep(Wait.SHORT);
			
			qfd_Admin.getClose().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().window(handles[0]);
			Thread.sleep(Wait.PAUSE);
			
			if(asserter.verifyExist(asserter.new XPath("//td[contains(text(),'" + userName + "')]")))
				log.warn("Verified the single user is added correctly.");
			
			if(asserter.verifyExist(asserter.new XPath("//td[contains(text(),'" + groupName + "')]")))
				log.warn("Verified the group is added correctly.");
			
			qfd_Admin.getNext().click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist(asserter.new XPath("//option[contains(text(),'" + userName + "')]")))
				log.warn("Verified the single user displays correctly.");
			
			if(asserter.verifyExist(asserter.new XPath("//option[contains(text(),'" + groupName + "')]")))
				log.warn("Verified the group displays correctly.");
			
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
		
		log.info("End of 'Assign Person & Group Place Admin Rights'.");
		log.info("************************************");
		
	}
	
	public void verifyPersonAndGroupAdmin(String validName, String validPW, String groupUserName,
			String groupUserPW) throws Exception {
		
		log.info("************************************");
		log.info("Starting 'Verify Person & Group Place Admin Rights'...");
		Thread.sleep(Wait.MID);
			
		qfd_LoginTask.verifyAdmin(validName, validPW);
		
		qfd_LoginTask.verifyAdmin(groupUserName, groupUserPW);
		
		log.info("End of 'Verify Person & Group Place Admin Rights'.");
		log.info("************************************");
		
	}
	
	public void removePersonAndGroupAdmin(String adminName, String adminPW, String userName, String groupName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Remove Person & Group Place Admin Rights'...");
		Thread.sleep(Wait.MID);
			
		qfd_LoginTask.logIn(adminName, adminPW);
		
		try{	
			
			qfd_Admin.getSiteAdmin().click();
			Thread.sleep(Wait.MID);
			
			qfd_Admin.getSecurity().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='Security']")))
				log.warn("Verified that the Security screen is displayed.");
			
			qfd_Admin.getRemoveAdmin().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='Server Security: Remove Access']")))
				log.warn("Verified that the Server Security: Remove Access screen is displayed.");
			
			if(asserter.verifyNotExist(asserter.new XPath("//input[contains(@value,'" + adminName + "')]")))
				log.warn("Verified that local Quickr Admin cannot be selected.");
			
			asserter.checkboxClick(asserter.new XPath("//input[contains(@value,'" + userName + "')]"), true);
			asserter.checkboxClick(asserter.new XPath("//input[contains(@value,'" + groupName + "')]"), true);
			
			qfd_Admin.getNext().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyNotExist(asserter.new XPath("//option[contains(text(),'" + userName + "')]")))
				log.warn("Verified the single user is removed correctly.");
			
			if(asserter.verifyNotExist(asserter.new XPath("//option[contains(text(),'" + groupName + "')]")))
				log.warn("Verified the group is removed correctly.");
			
			if(asserter.verifyExist(asserter.new XPath("//option[contains(text(),'" + adminName + "')]")))
				log.warn("Verified the Quickr Admin is still displayed.");
				
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
		
		log.info("End of 'Remove Person & Group Place Admin Rights'.");
		log.info("************************************");
		
	}

	public void verifySiteBasic(String adminName, String adminPW, String ldapName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Verify Site Administration Page'...");
		Thread.sleep(Wait.MID);
			
		qfd_LoginTask.logIn(adminName, adminPW);
		
		try{	
			
			qfd_Admin.getSiteAdmin().click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='Instructions']")))
				log.warn("Verified the Instructions screen is displayed.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='http://www.ibm.com/developerworks/lotus/documentation/quickr']")))
				log.warn("Verified the link in the Next Step section is correct.");
			
			if(asserter.verifyEquals(driver.findElement(By.xpath("//a[text()='http://www.ibm.com/developerworks/lotus/documentation/quickr']")).getAttribute("href"), "http://www.ibm.com/developerworks/lotus/documentation/quickr"))
				log.warn("Verified the link's target is correct.");
			
			if((asserter.verifyExist(asserter.new XPath("//div[@class='lotusMenu']"))) && (asserter.verifyExist(asserter.new XPath("//div[@class='lotusInfoBox']"))))
				log.warn("Verified that the left pane displays the correct components.");
			
			if(asserter.verifyExist(asserter.new XPath("//h3[text()='Tips']")))
				log.warn("Verified the Tips is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//p[contains(text(),'Click User directory to connect to your LDAP or Domino directory to manage users')]")))
				log.warn("Verified the Text is correct.");
			
			qfd_Admin.getSecurity().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyFalse(driver.findElement(By.xpath("//h3[text()='Tips']")).isDisplayed()))
				log.warn("Verified there is no Tips.");
				
			if(asserter.verifyTrue(driver.findElement(By.xpath("//a[contains(@title,'Click this to allow additional users to create places on this server')]")).isEnabled()))
				log.warn("Verified that the controls are active.");
				
			qfd_Admin.getUserDirectory().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyFalse(driver.findElement(By.xpath("//h3[text()='Tips']")).isDisplayed()))
				log.warn("Verified there is no Tips.");
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//a[text()='Change Directory']")).isEnabled()))
				log.warn("Verified that the controls are active.");
			
			if(asserter.verifyExist(asserter.new XPath("//h1[contains(text(),'User Directory')]")))
				log.warn("Verified the User Directory screen is correct.");
			
			qfd_Admin.getChangeDirectory().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Admin.getType().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Admin.getDominoOption().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//option[text()='Domino Server']")).isSelected()))
				log.warn("Verified that Type is Domino.");
			
			qfd_Admin.getLDAPOption().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//option[text()='LDAP Server']")).isSelected()))
				log.warn("Verified that Type is LDAP Server.");
			
			qfd_Admin.getLDAPName().sendKeys(ldapName);
			Thread.sleep(Wait.SHORT);
			
			asserter.checkboxClick(asserter.new XPath("//input[@type='checkbox' and contains(@title,'Check to use credentials specified below when searching')]"), true);
			
			qfd_Admin.getNext().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='OK with Credentials']")))
				log.warn("Verified the successfully configuration screen displays.");
			
			qfd_Admin.getOtherOptions().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyFalse(driver.findElement(By.xpath("//h3[text()='Tips']")).isDisplayed()))
				log.warn("Verified there is no Tips.");
			
			qfd_Admin.getEditOptions().click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='Other Options']")))
				log.warn("Verified edit screen displays.");
			
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
		
		log.info("End of 'Verify Site Administration Page'.");
		log.info("************************************");
		
	}

	public void verifyPlaceSecurity(String adminName, String adminPW,
			String userName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Verify Place Security'...");
		Thread.sleep(Wait.MID);
			
		qfd_LoginTask.logIn(adminName, adminPW);
		
		try{	
			
			qfd_Admin.getSiteAdmin().click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='Instructions']")))
				log.warn("Verified the Instructions screen is displayed.");
			
			qfd_Admin.getUserDirectory().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[contains(text(),'User Directory')]")))
				log.warn("Verified the User Directory screen is correct.");
			
			qfd_Admin.getChangeDirectory().click();
			Thread.sleep(Wait.MID);
			
			qfd_Admin.getNext().click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='OK with Credentials']")))
				log.warn("Verified the successfully configuration screen displays.");
			
			qfd_Admin.getSecurity().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='Security']")))
				log.warn("Verified that the Security screen is displayed.");
			
			qfd_Admin.getAddCreator().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='Server Security: Add Access']")))
				log.warn("Verified that the Server Security screen is displayed.");
			
			qfd_Admin.getDirectory().click();
			Thread.sleep(Wait.SHORT);
			
			String handles[] = new String[5];
			driver.getWindowHandles().toArray(handles);
			
			driver.switchTo().window(handles[1]);
			Thread.sleep(Wait.PAUSE*2);
			
			if(asserter.verifyExist(asserter.new XPath("//div[text()='Directory Search:']")))
				log.warn("Verified that the Search box is displayed.");
			
			qfd_Admin.getDirectoryText().sendKeys(userName);
			Thread.sleep(Wait.SHORT);
			
			qfd_Admin.getSearch().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().frame(qfd_Admin.getSearchFrame());
			Thread.sleep(Wait.PAUSE);
			
			if(asserter.verifyExist(asserter.new XPath("//input[contains(@value,'" + userName + "')]")))
				log.warn("Verified known user is on LDAP server.");
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			qfd_Admin.getClose().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().window(handles[0]);
			Thread.sleep(Wait.PAUSE);
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify Place Security'.");
		log.info("************************************");
		
	}

	public void createPlaceType(String adminName, String adminPW, String typeName, String url, String placeName, String placeDesc, String userName, String userPW) throws Exception {

		log.info("************************************");
		log.info("Starting 'Create Place Type'...");
		Thread.sleep(Wait.MID);
			
		qfd_LoginTask.logIn(adminName, adminPW);
		
		try{	
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Site Administration']")))
				log.warn("Verified the Site Administration link is available.");
			
			qfd_Admin.getWorkTemplates().click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='PlaceTypes']")))
				log.warn("Verified the PlaceTypes screen is displayed.");
			
			qfd_Admin.getCreatePlaceType().click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='Create PlaceType']")))
				log.warn("Verified the Create PlaceType screen is displayed.");
			
			qfd_Admin.getPlaceTypeName().sendKeys(typeName);
			Thread.sleep(Wait.SHORT);
			
			qfd_Admin.getSourcePlace().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//option[@value='blog_design']")))
				log.warn("Verified there is blog_design option.");
			
			if(asserter.verifyExist(asserter.new XPath("//option[@value='wiki_design']")))
				log.warn("Verified there is wiki_design option.");

			if(asserter.verifyExist(asserter.new XPath("//option[@value='wiki_design']")))
				log.warn("Verified there is wiki_design option.");
			
			new Element(driver.findElement(By.xpath("//option[contains(@value,'uat_templates')]")), "The place created above item").click();
			action.sendKeys(Keys.ENTER).perform();
			Thread.sleep(Wait.SHORT);
			
			qfd_Admin.getNext().click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist(asserter.new XPath("//a/b[contains(text(),'UAT')]")))
				log.warn("Verified the new PlaceType is displayed in the PlaceTypes screen.");
			
			new Element(driver.findElement(By.xpath("//a/b[text()='" + typeName + "']")), typeName + " item").click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='" + typeName + "']")))
				log.warn("Verified the summary scrren for the PlaceType is displayed.");
			
			qfd_Admin.getEdit().click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist(asserter.new XPath("//input[@type='radio']")))
				log.warn("Verified the summary scrren is now in edit mode.");
			
			asserter.checkboxClick(asserter.new XPath("//input[@type='radio' and @value='0']"), true);
			
			qfd_Admin.getTemplateDesc().sendKeys("&");
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Admin.getTemplateURL().sendKeys(url);
			Thread.sleep(Wait.SHORT);
			
			qfd_Admin.getNext().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Admin.getEdit().click();
			Thread.sleep(Wait.MID);			
			
			asserter.checkboxClick(asserter.new XPath("//input[@type='radio' and @value='0']"), true);

			qfd_Admin.getNext().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//td[contains(text(),'&')]")))
				log.warn("Verified the description is updated.");
			
			if(asserter.verifyExist(asserter.new XPath("//td[contains(text(),'" + url + "')]")))
				log.warn("Verified the URL is updated.");
			
			qfd_Admin.getWorkTemplates().click();
			Thread.sleep(Wait.MID);
			
			qfd_Admin.getWorkTemplates().click();
			Thread.sleep(Wait.MID);
			
			qfd_Admin.getMoreInfo().click();
			Thread.sleep(Wait.MID);
			
			asserter.switchToWindow("IBM");
			Thread.sleep(Wait.SHORT);
			System.out.println(driver.getTitle());
			if(asserter.verifyTrue(driver.getTitle().contains("IBM")))
				log.warn("Verified the link 'More Info' workds as expected.");	
				
			//edit on 03/21/2013
			asserter.switchToWindow("lotusquickr");
			Thread.sleep(Wait.SHORT);
			
			qfd_Admin.getWorkTemplates().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_CreatePlace.getCreatePlaceButton().click();
			Thread.sleep(Wait.LONG);
			
			if(asserter.verifyExist(asserter.new XPath("//a[contains(text(),'" + typeName + "')]")))
				log.warn("Verified the New PlaceType is displayed.");
			
			qfd_Admin.getMoreInfoLink().click();
			Thread.sleep(Wait.MID);
			
			asserter.switchToWindow("IBM");
			Thread.sleep(Wait.SHORT);
			System.out.println(driver.getTitle());
			if(asserter.verifyTrue(driver.getTitle().contains("IBM")))
				log.warn("Verified the link 'More Info' workds as expected.");	
			
			asserter.switchToWindow("lotusquickr");
			Thread.sleep(Wait.SHORT);
			
			driver.manage().window().maximize();
			
			new Element(driver.findElement(By.xpath("//a[contains(text(),'" + typeName + "')]")), "New PlaceType item").click();
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
			
			new Element(driver.findElement(By.linkText(placeName)), placeName + " link").click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist(asserter.new XPath("//input[@name='login']")))
				log.warn("Verified the login page displays.");
			
			qfd_Login.getUserName().sendKeys(userName);
			Thread.sleep(Wait.SHORT);
			
			qfd_Login.getUserPWD().sendKeys(userPW);
			Thread.sleep(Wait.SHORT);
			
			qfd_Login.getSubmitBtn().submit();
			Thread.sleep(Wait.LONG);
			
			if(asserter.verifyEquals("Log Out", qfd_Login.getLogOut().getText()))
				log.warn("Verified the link 'Log Out' appears.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Home']")))
				log.warn("Verified the place is a Standard Place and the elements are correct.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Create Place Type'.");
		log.info("************************************");
		
	}

	public void policyManagement(String policyName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Policy Management'...");
		Thread.sleep(Wait.MID);
		
		try{	
			
			qfd_Admin.getPlaceAdmin().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Admin.getPolicyTab().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[contains(text(),'All policies')]")))
				log.warn("Verified Policy page is loaded successfully.");
			
			if(asserter.verifyExist(asserter.new XPath("//h4[text()='!Default Policy']")))
				log.warn("Verified !Default Policy is listed here.");
			
			qfd_Admin.getCreateNewPolicy().click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Create a Policy']")))
				log.warn("Verified Create a Policy dialog is loaded.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Create a Policy']")))
				log.warn("Verified the dialog title is Create a policy.");
			
			if(asserter.verifyFalse(driver.findElement(By.xpath("//input[@id='sizeLockEnable']")).isSelected()) && asserter.verifyFalse(driver.findElement(By.xpath("//input[@id='inactiveArchiveEnable']")).isSelected()))
				log.warn("Verified the two checkboxed are unchecked.");
				
			qfd_Admin.getPolicyName().sendKeys(policyName);
			Thread.sleep(Wait.SHORT);
			
			//June
//			asserter.checkboxClick(asserter.new XPath("//input[@id='sizeLockEnable']"), true);
			WebElement sizeLockEnable = driver.findElement(By.xpath("//input[@id='sizeLockEnable']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", sizeLockEnable);
			
			qfd_Admin.getLockSize().sendKeys("2");
			Thread.sleep(Wait.SHORT);
			
			qfd_Admin.getWarnSize().sendKeys("1");
			Thread.sleep(Wait.SHORT);
			
			//June
//			asserter.checkboxClick(asserter.new XPath("//input[@id='inactiveArchiveEnable']"), true);
			WebElement inactiveArchiveEnable = driver.findElement(By.xpath("//input[@id='inactiveArchiveEnable']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", inactiveArchiveEnable);
			
			qfd_Admin.getLockDays().sendKeys("2");
			Thread.sleep(Wait.SHORT);
			
			qfd_Admin.getWarnDays().sendKeys("1");
			Thread.sleep(Wait.SHORT);
			
			action.sendKeys(Keys.TAB).perform();
			Thread.sleep(Wait.PAUSE*3);
			action.sendKeys(Keys.TAB).perform();
			Thread.sleep(Wait.PAUSE*3);
			action.sendKeys(Keys.TAB).perform();
			Thread.sleep(Wait.PAUSE*3);
			action.sendKeys(Keys.TAB).perform();
			Thread.sleep(Wait.PAUSE*3);
			action.sendKeys(Keys.TAB).perform();
			Thread.sleep(Wait.PAUSE*3);
			action.sendKeys(Keys.TAB).perform();
			Thread.sleep(Wait.PAUSE*3);
			
			//June
//			action.sendKeys(Keys.ENTER).perform();
//			Thread.sleep(Wait.PAUSE*3);

//			qfd_Admin.getOK().click();
//			Thread.sleep(Wait.MID);
			
			//June
			Thread.sleep(Wait.MID);
			WebElement okBtn = driver.findElement(By.xpath("//input[@value='OK']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", okBtn);
//			qfd_Admin.getOK().click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyFalse(driver.findElement(By.xpath(("//span[text()='Create a Policy']"))).isDisplayed()))
				log.warn("Verified Create a Policy dialog is closed.");
			
			if(asserter.verifyExist(asserter.new XPath("//h4[text()='" + policyName + "']")))
				log.warn("Verified new policy is listed correctly.");
			
			if(asserter.verifyExist(asserter.new XPath("//td[text()='2']")) && asserter.verifyExist(asserter.new XPath("//td[text()='1']")) && asserter.verifyExist(asserter.new XPath("//td[text()='2 days']")) && asserter.verifyExist(asserter.new XPath("//td[text()='1 day']")))
				log.warn("Verified the values in each column are correct.");
			
			asserter.checkboxClick(asserter.new XPath("//input[@type='checkbox' and contains(@title,'" + policyName + "')]"), true);
			
			qfd_Admin.getEditPolicy().click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Edit this Policy']")))
				log.warn("Verified Edit this Policy dialog is loaded.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Edit this Policy']")))
				log.warn("Verified the dialog title is Edit this Policy.");
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//input[@id='sizeLockEnable']")).isSelected()) && asserter.verifyTrue(driver.findElement(By.xpath("//input[@id='inactiveArchiveEnable']")).isSelected()))
				log.warn("Verified the two checkboxed are checked.");
			
			qfd_Admin.getPolicyName().clear();
			Thread.sleep(Wait.PAUSE);
			
			qfd_Admin.getPolicyName().sendKeys(policyName + " Updated");
			Thread.sleep(Wait.SHORT);
			
			//June
//			asserter.checkboxClick(asserter.new XPath("//input[@id='inactiveArchiveEnable']"), false);
			inactiveArchiveEnable = driver.findElement(By.xpath("//input[@id='inactiveArchiveEnable']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", inactiveArchiveEnable);
			
			qfd_Admin.getLockSize().clear();
			Thread.sleep(Wait.PAUSE);
			
			qfd_Admin.getLockSize().sendKeys("3");
			Thread.sleep(Wait.SHORT);
			
			qfd_Admin.getOK().click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyFalse(driver.findElement(By.xpath(("//span[text()='Edit this Policy']"))).isDisplayed()))
				log.warn("Verified Edit this Policy dialog is closed.");
			
			if(asserter.verifyExist(asserter.new XPath("//h4[text()='" + policyName + " Updated']")))
				log.warn("Verified the policy name is updated.");
			
			if(asserter.verifyExist(asserter.new XPath("//td[text()='3']")) && asserter.verifyExist(asserter.new XPath("//td[text()='1']")) && asserter.verifyExist(asserter.new XPath("//td[text()='0 day']")) && asserter.verifyExist(asserter.new XPath("//td[text()='1 day']")))
				log.warn("Verified the values in each column are updated.");
			
			asserter.checkboxClick(asserter.new XPath("//input[@type='checkbox' and contains(@title,'" + policyName + " Updated')]"), true);
			
			qfd_Admin.getDeletePolicy().click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Delete this policy']")))
				log.warn("Verified Delete this policy dialog is loaded.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Delete this policy']")))
				log.warn("Verified the dialog title is Delete this policy.");
			
			action.sendKeys(Keys.ENTER).perform();
			log.info("Clicking on Cancel link.");
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyNotExist(asserter.new XPath("//span[text()='Delete this Policy']")))
				log.warn("Verified Delete this policy dialog is closed.");
			
			qfd_Admin.getDeletePolicy().click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Delete this policy']")))
				log.warn("Verified Delete this policy dialog is loaded.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Delete this policy']")))
				log.warn("Verified the dialog title is Delete this policy.");
			
			action.sendKeys(Keys.TAB).perform();
			Thread.sleep(Wait.PAUSE);
			action.sendKeys(Keys.ENTER).perform();
			log.info("Clicking on OK button.");
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyNotExist(asserter.new XPath("//span[text()='Delete this Policy']")))
				log.warn("Verified Delete this policy dialog is closed.");
			
			if(asserter.verifyNotExist(asserter.new XPath("//h4[contains(text(),'" + policyName + "')]")))
				log.warn("Verified the policy disappears from the policy view table.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Policy Management'.");
		log.info("************************************");
		
	}

	public void downloadStatistics() throws Exception {

		log.info("************************************");
		log.info("Starting 'Download Statistics'...");
		Thread.sleep(Wait.MID);
		
		try{	
			
			qfd_Admin.getPlaceAdmin().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Admin.getStatisticsTab().click();
			Thread.sleep(Wait.SHORT);
				
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='Statistics']")))
				log.warn("Verified that the system level statistic items are listed.");
			
			if(asserter.verifyExist(asserter.new XPath("//dt[text()='About Users']")))
				log.warn("Verified About Users statistic exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//dt[text()='About Places']")))
				log.warn("Verified About Places statistic exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//dt[text()='About Templates']")))
				log.warn("Verified About Templates statistic exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//dt[text()='About Documents']")))
				log.warn("Verified About Documents statistic exists.");
			
			qfd_Admin.getDownloadStatistics().click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Download Statistics']")))
				log.warn("Verified Download Statistics dialog opens.");
			
			if(asserter.verifyExist(asserter.new XPath("//input[@id='statDownloadAmount']")))
				log.warn("Verified dialog UI is correct.");
			
			if(BrowserSetup.BrowserType.equalsIgnoreCase("firefox")){
				
				qfd_Admin.getOK().click();
				Thread.sleep(Wait.MID);
				
				AT.setFocusDoKeys("Opening", "!o");
				AT.setFocusDoKeys("Opening", "{ENTER}");
				log.warn("Verified save file dialog of the browser pops up.");
				Thread.sleep(Wait.SHORT);
				
				AT.setFocusDoKeys("Microsoft Excel", "{ESC}");
				AT.setFocusDoKeys("Microsoft Excel", "!{F4}N");
				log.warn("Verified download dialog from browser closed without error.");
				Thread.sleep(Wait.SHORT);
				
			}else if(BrowserSetup.BrowserType.equalsIgnoreCase("internetexplorer")){
				
				AT.setFocusDoKeys("Place", "{TAB}");
				AT.setFocusDoKeys("Place", "{TAB}");
				AT.setFocusDoKeys("Place", "{TAB}");
				AT.setFocusDoKeys("View", "{ENTER}");
				
				AT.setFocusDoKeys("Place", "^j");
				Thread.sleep(Wait.PAUSE);
				
				AT.setFocusDoKeys("View", "{RIGHT}");
				AT.setFocusDoKeys("View", "{ENTER}");
				
				Thread.sleep(Wait.LONG);
					
				AT.setFocusDoKeys("Microsoft Excel", "!{F4}N");
				Thread.sleep(Wait.MID);
				
				AT.setFocusDoKeys("View", "!{F4}N");
				Thread.sleep(Wait.SHORT);
				
			}
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Download Statistics']")))
				log.warn("Verified Download Statistics dialog still opened.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Download Statistics'.");
		log.info("************************************");
		
	}

	public void verifyPlacesList() throws Exception {

		log.info("************************************");
		log.info("Starting 'Verify Places List'...");
		Thread.sleep(Wait.MID);
		
		try{	
			
			qfd_Admin.getPlaceAdmin().click();
			Thread.sleep(Wait.MID);
			
			qfd_Admin.getPlacesTab().click();
			Thread.sleep(Wait.SHORT);
				
			if(asserter.verifyExist(asserter.new XPath("//h1[contains(text(),'All places')]")))
				log.warn("Verified the Places screen is displayed.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Title']")))
				log.warn("Verified the Title column exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Owner']")))
				log.warn("Verified the Owner column exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//th[text()='Locked']")))
				log.warn("Verified the Locked column exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//th[text()='Alert']")))
				log.warn("Verified the Alert column exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Policy name']")))
				log.warn("Verified the Policy name column exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Size(MB)']")))
				log.warn("Verified the Size column exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Last modified']")))
				log.warn("Verified the Last modified column exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//input[@id='filterPlaceKey']")))
				log.warn("Verified the filter by title box exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Locked places']")))
				log.warn("Verified the Locked places link exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Policy alerts']")))
				log.warn("Verified the Policy alerts link exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Tips']")))
				log.warn("Verified the Tips exists.");
			
			if(asserter.verifyTrue(driver.findElements(By.xpath("//a[contains(text(),'" + Users.PlaceName + "')]")).size() == 2))
				log.warn("Verified the two created places all listed.");
				
			qfd_Admin.getLockedPlaces().click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[contains(text(),'Locked places')]")))
				log.warn("Verified the Locked Places screen is displayed.");
			
			if(asserter.verifyNotExist(asserter.new XPath("//a[contains(text(),'" + Users.PlaceName + "')]")))
				log.warn("Verified the two places are not listed.");
			
			qfd_Admin.getAllPlaces().click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyTrue(driver.findElements(By.xpath("//a[contains(text(),'" + Users.PlaceName + "')]")).size() == 2))
				log.warn("Verified the two created places all listed.");
			
			qfd_Admin.getPlaceSearch().sendKeys(Users.PlaceName + " 1");
			Thread.sleep(Wait.PAUSE);
			
			qfd_Admin.getPlaceSearchButton().click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyTrue(driver.findElements(By.xpath("//a[contains(text(),'" + Users.PlaceName + "')]")).size() == 1))
				log.warn("Verified place : " + Users.PlaceName + " 1" + " is in search result, while " + Users.PlaceName + " not in.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify Places List'.");
		log.info("************************************");
		
	}

	public void verifyTemplate(String templateName, String policyName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Verify Template Admin'...");
		Thread.sleep(Wait.MID);
		
		try{	
			
			qfd_Admin.getPlaceAdmin().click();
			Thread.sleep(Wait.MID);
			
			qfd_Admin.getTemplateTab().click();
			Thread.sleep(Wait.SHORT);
				
			if(asserter.verifyExist(asserter.new XPath("//h1[contains(text(),'All templates')]")))
				log.warn("Verified the Template page is loaded.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Title']")))
				log.warn("Verified the table is loaded.");
			
			if(asserter.verifyExist(asserter.new XPath("//h4[text()='Standard place for Teams']")))
				log.warn("Verified the default template is listed.");
			
			qfd_Admin.getHome().click();
			Thread.sleep(Wait.LONG);
			
			qfd_Admin.getWorkTemplates().click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='PlaceTypes']")))
				log.warn("Verified the PlaceTypes screen is displayed.");
			
			qfd_Admin.getCreatePlaceType().click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='Create PlaceType']")))
				log.warn("Verified the Create PlaceType screen is displayed.");
			
			qfd_Admin.getPlaceTypeName().sendKeys(templateName);
			Thread.sleep(Wait.SHORT);
			
			qfd_Admin.getNext().click();
			Thread.sleep(Wait.MID);
			
			qfd_Admin.getPlaceAdmin().click();
			Thread.sleep(Wait.MID);
			
			qfd_Admin.getTemplateTab().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//h4[text()='" + templateName + "']")))
				log.warn("Verified the customized template is listed.");
			
			qfd_Admin.getPolicyTab().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Admin.getCreateNewPolicy().click();
			Thread.sleep(Wait.MID);
				
			qfd_Admin.getPolicyName().sendKeys(policyName);
			Thread.sleep(Wait.SHORT);
			
			asserter.checkboxClick(asserter.new XPath("//input[@id='sizeLockEnable']"), true);
			
			qfd_Admin.getLockSize().clear();
			Thread.sleep(Wait.PAUSE);
			
			qfd_Admin.getLockSize().sendKeys("2");
			Thread.sleep(Wait.SHORT);
			
			qfd_Admin.getOK().click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist(asserter.new XPath("//h4[text()='" + policyName + "']")))
				log.warn("Verified new policy is created successfully.");
			
			qfd_Admin.getTemplateTab().click();
			Thread.sleep(Wait.SHORT);
			
			asserter.checkboxClick(asserter.new XPath("//input[@type='checkbox' and contains(@title,'" + templateName + "')]"), true);
			
			qfd_Admin.getAssignPolicy().click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Assign policy']")))
				log.warn("Verified Assign policy dialog pops up.");
			
			if(asserter.verifyExist(asserter.new XPath("//option[text()='" + policyName + "']")))
				log.warn("Verified new policy is listed correctly.");
			
			new Element(driver.findElement(By.xpath("//option[text()='" + policyName + "']")), policyName + " option").click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Admin.getOK().click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist("was assigned a policy successfully"))
				log.warn("Verified the policy is assigned successfully.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + policyName + "']")))
				log.warn("Verified The policy of the customized template under Assigned policy column is " + policyName + ".");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify Template Admin'.");
		log.info("************************************");
		
	}
	
}
