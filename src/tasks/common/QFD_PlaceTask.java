package tasks.common;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import config.BrowserSetup;
import config.Element;
import config.Wait;
import config.Users;
import exceptions.ElementException;

import tasks.library.QFD_PageTask;
import tasks.toc.QFD_LibraryTask;
import util.Assert;
import util.Assert.XPath;
import appobjects.common.QFD_Admin;
import appobjects.common.QFD_Login;
import appobjects.common.QFD_Place;
import appobjects.library.QFD_Folder;
import appobjects.library.QFD_Page;
import appobjects.toc.QFD_Library;

public class QFD_PlaceTask {

	private WebDriver driver;
	private Logger log;
	private Assert asserter;
	private Actions action;
	private AutoITTask AT;
	QFD_Place qfd_Place = null;
	QFD_Folder qfd_Folder = null;
	QFD_Login qfd_Login = null;
	QFD_Admin qfd_Admin = null;
	QFD_Library qfd_Library = null;
	QFD_Page qfd_Page = null;
	QFD_PageTask qfd_PageTask = null;
	QFD_LibraryTask qfd_LibraryTask = null;
	QFD_LoginTask qfd_LoginTask = null;
	
	public QFD_PlaceTask(WebDriver driver, Logger log){
		
		this.driver = driver;
		this.log = log;
		this.asserter = new Assert(driver, log);
		this.action = new Actions(driver);
		this.AT = new AutoITTask(driver, log);
		qfd_Place = new QFD_Place(driver);
		qfd_Folder = new QFD_Folder(driver);
		qfd_Login = new QFD_Login(driver);
		qfd_Admin = new QFD_Admin(driver);
		qfd_Library = new QFD_Library(driver);
		qfd_Page = new QFD_Page(driver);
		qfd_PageTask = new QFD_PageTask(driver, log);
		qfd_LibraryTask = new QFD_LibraryTask(driver, log);
		qfd_LoginTask = new QFD_LoginTask(driver, log);
		
	}

	public void placeNewFolder() throws Exception {

		//mary
		//qfd_Place.getPlaceAction().click();
		asserter.elementClick(qfd_Place.getPlaceAction(),asserter.new XPath("//td[text()='New']"), true);
		
		Thread.sleep(Wait.SHORT);
		//mary
		Thread.sleep(Wait.VERYSHORT);
		Thread.sleep(Wait.VERYSHORT);
		//mary
		Thread.sleep(Wait.VERYSHORT);
		Thread.sleep(Wait.VERYSHORT);

		qfd_Place.getPlaceActionNew().click();
		Thread.sleep(Wait.SHORT);
		
		//June
		action.sendKeys(Keys.ENTER).perform();
		Thread.sleep(Wait.VERYSHORT);
		Thread.sleep(Wait.VERYSHORT);
		Thread.sleep(Wait.VERYSHORT);
		Thread.sleep(Wait.VERYSHORT);
		
		//mary
		Thread.sleep(Wait.VERYSHORT);
		Thread.sleep(Wait.VERYSHORT);
		//mary
		Thread.sleep(Wait.VERYSHORT);
		Thread.sleep(Wait.VERYSHORT);

		action.sendKeys(Keys.ARROW_DOWN).perform();
		Thread.sleep(Wait.VERYSHORT);
		//mary
		Thread.sleep(Wait.VERYSHORT);
		Thread.sleep(Wait.VERYSHORT);
		//mary
		Thread.sleep(Wait.VERYSHORT);
		Thread.sleep(Wait.VERYSHORT);
		
		action.sendKeys(Keys.ARROW_DOWN).perform();
		Thread.sleep(Wait.VERYSHORT);
		//mary
		Thread.sleep(Wait.VERYSHORT);
		Thread.sleep(Wait.VERYSHORT);
		//mary
		Thread.sleep(Wait.VERYSHORT);
		Thread.sleep(Wait.VERYSHORT);
		
		action.sendKeys(Keys.ARROW_DOWN).perform();
		Thread.sleep(Wait.VERYSHORT);
		//mary
		Thread.sleep(Wait.VERYSHORT);
		Thread.sleep(Wait.VERYSHORT);
		//mary
		Thread.sleep(Wait.VERYSHORT);
		Thread.sleep(Wait.VERYSHORT);
		
		action.sendKeys(Keys.ARROW_DOWN).perform();
		Thread.sleep(Wait.VERYSHORT);
		//mary
		Thread.sleep(Wait.VERYSHORT);
		Thread.sleep(Wait.VERYSHORT);
		//mary
		Thread.sleep(Wait.VERYSHORT);
		Thread.sleep(Wait.VERYSHORT);
		
		action.sendKeys(Keys.ARROW_DOWN).perform();
		Thread.sleep(Wait.VERYSHORT);
		//mary
		Thread.sleep(Wait.VERYSHORT);
		Thread.sleep(Wait.VERYSHORT);
		//mary
		Thread.sleep(Wait.VERYSHORT);
		Thread.sleep(Wait.VERYSHORT);
		
		action.sendKeys(Keys.ENTER).perform();
		//mary
		Thread.sleep(Wait.VERYSHORT);
		Thread.sleep(Wait.VERYSHORT);
		//mary
		Thread.sleep(Wait.VERYSHORT);
		Thread.sleep(Wait.VERYSHORT);

		log.info("Clicking on Folder item.");
		Thread.sleep(Wait.SHORT);
		
	}

	public void placeNewPage() throws Exception {

		//mary
		//qfd_Place.getPlaceAction().click();
		asserter.elementClick(qfd_Place.getPlaceAction(),asserter.new XPath("//td[text()='New']"), true);
		
		Thread.sleep(Wait.SHORT);
		
		//June
		Thread.sleep(Wait.VERYSHORT);
		//mary
		Thread.sleep(Wait.VERYSHORT);
		Thread.sleep(Wait.VERYSHORT);
		//mary
		Thread.sleep(Wait.VERYSHORT);
		Thread.sleep(Wait.VERYSHORT);
		
		action.sendKeys(Keys.ENTER).perform();
		
		//mary
		Thread.sleep(Wait.VERYSHORT);
		Thread.sleep(Wait.VERYSHORT);
		
		qfd_Place.getPlaceActionNew().click();
		Thread.sleep(Wait.SHORT);
		
		//mary
		Thread.sleep(Wait.VERYSHORT);
		Thread.sleep(Wait.VERYSHORT);
		//mary
		Thread.sleep(Wait.VERYSHORT);
		Thread.sleep(Wait.VERYSHORT);
		
		action.sendKeys(Keys.ARROW_DOWN).perform();
		Thread.sleep(Wait.VERYSHORT);
		//mary
		Thread.sleep(Wait.VERYSHORT);
		Thread.sleep(Wait.VERYSHORT);
		//mary
		Thread.sleep(Wait.VERYSHORT);
		Thread.sleep(Wait.VERYSHORT);
		
		action.sendKeys(Keys.ENTER).perform();
		log.info("Clicking on Page item.");
		Thread.sleep(Wait.SHORT);
		
	}
	
	public void placeNewUpload() throws Exception {

		qfd_Place.getPlaceAction().click();
		Thread.sleep(Wait.SHORT);
		
		qfd_Place.getPlaceActionNew().click();
		Thread.sleep(Wait.SHORT);
		
		action.sendKeys(Keys.ENTER).perform();
		log.info("Clicking on Upload item.");
		Thread.sleep(Wait.SHORT);
		
	}

	public void placeNewLink() throws Exception{

		//mary
		//qfd_Place.getPlaceAction().click();
		asserter.elementClick(qfd_Place.getPlaceAction(),asserter.new XPath("//td[text()='New']"), true);
		Thread.sleep(Wait.SHORT);
		
		qfd_Place.getPlaceActionNew().click();
		Thread.sleep(Wait.SHORT);
		
		action.sendKeys(Keys.ARROW_DOWN).perform();
		Thread.sleep(Wait.VERYSHORT);
		action.sendKeys(Keys.ARROW_DOWN).perform();
		Thread.sleep(Wait.VERYSHORT);
		action.sendKeys(Keys.ARROW_DOWN).perform();
		Thread.sleep(Wait.VERYSHORT);
		action.sendKeys(Keys.ENTER).perform();
		log.info("Clicking on Link item.");
		Thread.sleep(Wait.SHORT);
		
	}

	public void placeNewImport() throws Exception {

		qfd_Place.getPlaceAction().click();
		Thread.sleep(Wait.SHORT);
		
		qfd_Place.getPlaceActionNew().click();
		Thread.sleep(Wait.SHORT);
		
		action.sendKeys(Keys.ARROW_DOWN).perform();
		Thread.sleep(Wait.VERYSHORT);
		//mary
		Thread.sleep(Wait.VERYSHORT);
		Thread.sleep(Wait.VERYSHORT);
		//mary
		Thread.sleep(Wait.VERYSHORT);
		Thread.sleep(Wait.VERYSHORT);
		
		action.sendKeys(Keys.ARROW_DOWN).perform();
		Thread.sleep(Wait.VERYSHORT);
		//mary
		Thread.sleep(Wait.VERYSHORT);
		Thread.sleep(Wait.VERYSHORT);
		//mary
		Thread.sleep(Wait.VERYSHORT);
		Thread.sleep(Wait.VERYSHORT);
		
		action.sendKeys(Keys.ENTER).perform();
		log.info("Clicking on Imported File item.");
		Thread.sleep(Wait.SHORT);
		
	}
	
	public void placeNewList() throws Exception {

		qfd_Place.getPlaceAction().click();
		Thread.sleep(Wait.SHORT);
		//mary
		Thread.sleep(Wait.VERYSHORT);
		Thread.sleep(Wait.VERYSHORT);
		//mary
		Thread.sleep(Wait.VERYSHORT);
		Thread.sleep(Wait.VERYSHORT);
		
		qfd_Place.getPlaceActionNew().click();
		Thread.sleep(Wait.SHORT);
		//mary
		Thread.sleep(Wait.VERYSHORT);
		Thread.sleep(Wait.VERYSHORT);
		//mary
		Thread.sleep(Wait.VERYSHORT);
		Thread.sleep(Wait.VERYSHORT);
		
		action.sendKeys(Keys.ARROW_DOWN).perform();
		Thread.sleep(Wait.VERYSHORT);
		//mary
		Thread.sleep(Wait.VERYSHORT);
		Thread.sleep(Wait.VERYSHORT);
		//mary
		Thread.sleep(Wait.VERYSHORT);
		Thread.sleep(Wait.VERYSHORT);
		
		action.sendKeys(Keys.ARROW_DOWN).perform();
		Thread.sleep(Wait.VERYSHORT);
		//mary
		Thread.sleep(Wait.VERYSHORT);
		Thread.sleep(Wait.VERYSHORT);
		//mary
		Thread.sleep(Wait.VERYSHORT);
		Thread.sleep(Wait.VERYSHORT);
		
		action.sendKeys(Keys.ARROW_DOWN).perform();
		Thread.sleep(Wait.VERYSHORT);
		//mary
		Thread.sleep(Wait.VERYSHORT);
		Thread.sleep(Wait.VERYSHORT);
		//mary
		Thread.sleep(Wait.VERYSHORT);
		Thread.sleep(Wait.VERYSHORT);
		
		action.sendKeys(Keys.ARROW_DOWN).perform();
		Thread.sleep(Wait.VERYSHORT);
		//mary
		Thread.sleep(Wait.VERYSHORT);
		Thread.sleep(Wait.VERYSHORT);
		//mary
		Thread.sleep(Wait.VERYSHORT);
		Thread.sleep(Wait.VERYSHORT);
		
		action.sendKeys(Keys.ENTER).perform();
		log.info("Clicking on List item.");
		Thread.sleep(Wait.SHORT);
		
	}
	
	public void verifyPlacesScreen() throws Exception {

		log.info("************************************");
		log.info("Starting 'Verify Places Screen'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			if(asserter.verifyFalse(driver.findElement(By.xpath("//a[text()='Work with Templates']")).isDisplayed()))
				log.warn("Verified there is no Work with Templates link.");
			
			if(asserter.verifyFalse(driver.findElement(By.xpath("//a[text()='Place Administration']")).isDisplayed()))
				log.warn("Verified there is no Place Administration link.");
			
			if(asserter.verifyFalse(driver.findElement(By.xpath("//a[text()='Site Administration']")).isDisplayed()))
				log.warn("Verified there is no Site Administration link.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify Places Screen'.");
		log.info("************************************");
		
	}

	public void allowPlaceType() throws Exception {

		log.info("************************************");
		log.info("Starting 'Allow Place Type Option'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			qfd_Place.getCustomize().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().frame(qfd_Place.getCutomizeFrame());
			Thread.sleep(Wait.PAUSE);
			
			qfd_Place.getPlaceTypeOption().click();
			Thread.sleep(Wait.SHORT);
			
			asserter.checkboxClick(asserter.new XPath("//input[@name='h_AllowPtCreation' and @type='radio']"), true);
			
			qfd_Place.getNext().click();
			Thread.sleep(Wait.MID);
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			asserter.elementClickWait(qfd_Place.getClose(), asserter.new XPath("//span[text()='Customize']"), false);
			
			qfd_Place.getMyPlaces().click();
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
		
		log.info("End of 'Allow Place Type Option'.");
		log.info("************************************");
		
	}

	public void editPlaceName() throws Exception {

		log.info("************************************");
		log.info("Starting 'Edit Place Name'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			qfd_Place.getCustomize().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().frame(qfd_Place.getCutomizeFrame());
			Thread.sleep(Wait.PAUSE);
			
			qfd_Place.getBasics().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='Basics']")))
				log.warn("Verified the Basics setting page appears.");
			
			qfd_Place.getPlaceName().click();
			Thread.sleep(Wait.PAUSE);
			action.sendKeys(Keys.END).perform();
			Thread.sleep(Wait.PAUSE);
			qfd_Place.getPlaceName().sendKeys(" Updated");
			Thread.sleep(Wait.SHORT);
			
			qfd_Place.getPlaceDesc().click();
			Thread.sleep(Wait.PAUSE);
			qfd_Place.getPlaceDesc().sendKeys("/this is");
			Thread.sleep(Wait.PAUSE);
			action.sendKeys(Keys.ENTER).perform();
			Thread.sleep(Wait.PAUSE);
			qfd_Place.getPlaceDesc().sendKeys("my 'place\"");
			Thread.sleep(Wait.PAUSE);
			action.sendKeys(Keys.ENTER).perform();
			Thread.sleep(Wait.PAUSE);
			qfd_Place.getPlaceDesc().sendKeys("<description>");
			Thread.sleep(Wait.PAUSE);
			
			qfd_Place.getDone().click();
			Thread.sleep(Wait.MID);
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			asserter.elementClickWait(qfd_Place.getClose(), asserter.new XPath("//span[text()='Customize']"), false);
			
			if(asserter.verifyExist(asserter.new XPath("//a[contains(text(),'Updated')]")))
				log.warn("Verified the Place has been renamed.");
			
			qfd_Place.getMyPlaces().click();
			Thread.sleep(Wait.MID);
			
			Users.PlaceName = Users.PlaceName + " Updated";
			
			if(asserter.verifyExist(asserter.new XPath("//a[contains(text(),'Updated')]")))
				log.warn("Verified the Place has been renamed.");
			
			if((asserter.verifyExist(asserter.new XPath("//p[contains(text(),'/this')]"))) && (asserter.verifyExist(asserter.new XPath("//p[contains(text(),'des')]"))) && (asserter.verifyExist(asserter.new XPath("//p[contains(text(),'place')]"))))
				log.warn("Verified the description is updated.");
			
			new Element(driver.findElement(By.xpath("//a[text()='" + Users.PlaceName + "']")), "Place link").click();
			Thread.sleep(Wait.MID);
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Edit Place Name'.");
		log.info("************************************");
		
	}

	public void deletePlace() throws Exception {

		log.info("************************************");
		log.info("Starting 'Delete Place'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			String handles[] = new String[5];
			System.out.println(driver.getWindowHandles().size());
			driver.getWindowHandles().toArray(handles);
			
			if(driver.getWindowHandles().size() == 2){
				
				driver.switchTo().window(handles[1]);
				Thread.sleep(Wait.PAUSE);
				
				driver.manage().window().maximize();
				Thread.sleep(Wait.PAUSE);
				
			}
			
			qfd_Place.getCustomize().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().frame(qfd_Place.getCutomizeFrame());
			Thread.sleep(Wait.PAUSE);
			
			qfd_Place.getBasics().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Place.getDeletePlace().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='Delete place']")))
				log.warn("Verified the Delete place screen is displayed.");
			
			qfd_Place.getDeleteConfirm().sendKeys("Junk");
			Thread.sleep(Wait.SHORT);
			
			qfd_Place.getDelete().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().alert().accept();
			log.info("Clicking on OK button.");
			Thread.sleep(Wait.SHORT);
			
			qfd_Place.getDeleteConfirm().sendKeys("yes");
			Thread.sleep(Wait.SHORT);
			
			
			qfd_Place.getDelete().click();
			Thread.sleep(Wait.SHORT);
			
			//Mary deleted this on 03/21/2013
//			if(asserter.verifyExist(asserter.new XPath("//h1[text()='Deleting your place']")))
//				log.warn("Verified the Deleting screen is displayed.");
			
			Thread.sleep(Wait.VERYLONG);
			
			//Mary deleted this on 03/21/2013
//			driver.switchTo().defaultContent();
//			Thread.sleep(Wait.PAUSE);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='My Places']")))
				log.warn("Verified user is returned to My Places.");
			
			if(asserter.verifyNotExist(asserter.new XPath("//a[text()='" + Users.PlaceName + "']")))
				log.warn("Verified the place is nolonger listed.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Delete Place'.");
		log.info("************************************");
		
	}

	public void editPlace() throws Exception {

		log.info("************************************");
		log.info("Starting 'Edit Place'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			qfd_Place.getCustomize().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().frame(qfd_Place.getCutomizeFrame());
			Thread.sleep(Wait.PAUSE);
			
			qfd_Place.getBasics().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Place.getLogoMaker().click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='Logo Maker']")))
				log.warn("Verified the Logo Maker screen is displayed.");
			
			qfd_Place.getLogoText().sendKeys("NewLogo");
			Thread.sleep(Wait.SHORT);
			
			asserter.checkboxClick(asserter.new XPath("//input[@value='Blur']"), true);
			asserter.checkboxClick(asserter.new XPath("//input[@value='1']"), true);
			
			action.sendKeys(Keys.END).perform();
			Thread.sleep(Wait.PAUSE);
			
			qfd_Place.getLogoAnimation().click();
			Thread.sleep(Wait.PAUSE);
			
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ENTER).perform();
			log.info("Clicking on Slide from bottom item.");
			Thread.sleep(Wait.PAUSE);
			
			qfd_Place.getLogoFontSize().click();
			Thread.sleep(Wait.PAUSE);
			
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ENTER).perform();
			log.info("Clicking on 16 item.");
			Thread.sleep(Wait.PAUSE);
			
			asserter.checkboxClick(asserter.new XPath("//input[@value='ffff33']"), true);
			
			action.sendKeys(Keys.HOME).perform();
			Thread.sleep(Wait.PAUSE);
			
			qfd_Place.getNext().click();
			Thread.sleep(Wait.MID);		
			
			qfd_Place.getTasksOption().click();		
			Thread.sleep(Wait.SHORT);
			
			action.sendKeys(Keys.ARROW_DOWN).perform();
			log.info("Clicking on Index item.");
			Thread.sleep(Wait.SHORT);
			
			qfd_Folder.getUpArrow().click();
			Thread.sleep(Wait.PAUSE);
			qfd_Folder.getUpArrow().click();
			Thread.sleep(Wait.PAUSE);
			qfd_Folder.getUpArrow().click();
			Thread.sleep(Wait.PAUSE);
			qfd_Folder.getUpArrow().click();
			Thread.sleep(Wait.PAUSE);
			qfd_Folder.getUpArrow().click();
			Thread.sleep(Wait.PAUSE);
			
			qfd_Place.getForumsOption().click();		
			Thread.sleep(Wait.SHORT);
			
			qfd_Folder.getDownArrow().click();
			Thread.sleep(Wait.PAUSE);
			qfd_Folder.getDownArrow().click();
			Thread.sleep(Wait.PAUSE);
			qfd_Folder.getDownArrow().click();
			Thread.sleep(Wait.PAUSE);
			qfd_Folder.getDownArrow().click();
			Thread.sleep(Wait.PAUSE);
			qfd_Folder.getDownArrow().click();
			Thread.sleep(Wait.PAUSE);
			
			asserter.checkboxClick(asserter.new XPath("//input[@type='checkbox' and @name='h_GetCalendar']"), false);
			asserter.checkboxClick(asserter.new XPath("//input[@type='checkbox' and @name='h_GetTasks']"), false);
			
			action.sendKeys(Keys.HOME).perform();
			Thread.sleep(Wait.PAUSE);
			
			qfd_Place.getDone().click();
			Thread.sleep(Wait.MID);
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			asserter.elementClickWait(qfd_Place.getClose(), asserter.new XPath("//span[text()='Customize']"), false);
			
			if(asserter.verifyExist(asserter.new XPath("//img[contains(@alt,'" + Users.PlaceName + "')]")))
				log.warn("Verified the New Logo appears.");
			
			WebElement[] T = new WebElement[50];
			driver.findElements(By.xpath("//ul[@class='qlist']/li/a[contains(@class,'toc')]")).toArray(T);
			
			if(asserter.verifyTrue(T[0].getText().contains("Index")))
				log.warn("Verified Index is moved to the top of the TOC.");
			
			if(asserter.verifyTrue(T[5].getText().contains("Forum")))
				log.warn("Verified Forums is moved to the bottom of the TOC.");
			
			if(asserter.verifyNotExist(asserter.new XPath("//a[contains(text(),'Tasks')]")))
				log.warn("Verified Tasks is not in the TOC.");
			
			if(asserter.verifyNotExist(asserter.new XPath("//a[contains(text(),'Calendar')]")))
				log.warn("Verified Calendar is not in the TOC.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Edit Place'.");
		log.info("************************************");
		
	}

	public void verifyHeaderLinks(String ownerName, String ownerPW, String authorName, String authorPW) throws Exception {

		log.info("************************************");
		log.info("Starting 'Verify Header Links'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			String url = driver.getCurrentUrl();
			
			qfd_Place.getMyPlaces().click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='My Places']")))
				log.warn("Verified My Places link brings uses to place catalog.");
			
			driver.get(url);
			log.info("Back to the previous page.");
			Thread.sleep(Wait.SHORT);
			
			new Element(driver.findElement(By.xpath("//a[contains(text(),'" + Users.PlaceName + "')]")), Users.PlaceName + " link").click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist(asserter.new XPath("//span[@title='Home']")))
				log.warn("Verified Place name link brings user to Home page of place.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		qfd_PageTask.verifyBusinessCard(ownerName, "Owner");
		
		try{
			
			qfd_Place.getTopHelp().click();
			Thread.sleep(Wait.MID);
			
			String handles[] = new String[5];
			driver.getWindowHandles().toArray(handles);
			
			driver.switchTo().window(handles[1]);
			Thread.sleep(Wait.PAUSE);
			
			if(asserter.verifyTrue(driver.getTitle().contains("Index")))
				log.warn("Verified Help link brings user to the onlie help.");
			
			driver.switchTo().window(handles[0]);
			Thread.sleep(Wait.PAUSE);
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		qfd_LoginTask.logOutAndLogIn(authorName, authorPW);
		
		try{
			
			String url = driver.getCurrentUrl();
			
			qfd_Place.getMyPlaces().click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='My Places']")))
				log.warn("Verified My Places link brings uses to place catalog.");
			
			driver.get(url);
			log.info("Back to the previous page.");
			Thread.sleep(Wait.SHORT);
			
			new Element(driver.findElement(By.xpath("//a[contains(text(),'" + Users.PlaceName + "')]")), Users.PlaceName + " link").click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist(asserter.new XPath("//span[@title='Home']")))
				log.warn("Verified Place name link brings user to Home page of place.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		qfd_PageTask.verifyBusinessCard(authorName, "Author");
		
		try{
			
			qfd_Place.getTopHelp().click();
			Thread.sleep(Wait.MID);
			
			String handles[] = new String[5];
			driver.getWindowHandles().toArray(handles);
			
			driver.switchTo().window(handles[2]);
			Thread.sleep(Wait.PAUSE);
			
			if(asserter.verifyTrue(driver.getTitle().contains("Index")))
				log.warn("Verified Help link brings user to the onlie help.");
			
			driver.switchTo().window(handles[0]);
			Thread.sleep(Wait.PAUSE);
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		qfd_LoginTask.logOutAndLogIn(ownerName, ownerPW);
		
		log.info("End of 'Verify Header Links'.");
		log.info("************************************");
		
	}
	
	public void verifyFooterLinks(String ownerName, String ownerPW, String authorName, String authorPW) throws Exception {

		log.info("************************************");
		log.info("Starting 'Verify Footer Links'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			qfd_Admin.getHome().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Home']")))
				log.warn("Verified Home link brings user to Home page.");
			
			qfd_Place.getFooterHelp().click();
			Thread.sleep(Wait.MID);
			
			String handles[] = new String[5];
			driver.getWindowHandles().toArray(handles);
			
			driver.switchTo().window(handles[3]);
			Thread.sleep(Wait.PAUSE);
			
			if(asserter.verifyTrue(driver.getTitle().contains("Index")))
				log.warn("Verified Help link brings user to the onlie help.");
			
			driver.switchTo().window(handles[0]);
			Thread.sleep(Wait.PAUSE);
			
			if(BrowserSetup.BrowserType.equalsIgnoreCase("firefox")){
				
				if(asserter.verifyExist(asserter.new XPath("//a[@href='http://www.lotus.com/ldd/lqwiki.nsf']/span/span[text()='Best Practices Wiki']")))
					log.warn("Verified Best Practices link launches correct page.");
				
			}else if(BrowserSetup.BrowserType.equalsIgnoreCase("internetexplorer")){
				
				if(asserter.verifyExist(asserter.new XPath("//a[@href='http://www.lotus.com/ldd/lqwiki.nsf']/span/span/span[text()='Best Practices Wiki']")))
					log.warn("Verified Best Practices link launches correct page.");
							
			}
			
			if(BrowserSetup.BrowserType.equalsIgnoreCase("firefox")){
				
				if(asserter.verifyExist(asserter.new XPath("//a[@id='submitFeedback' and contains(@href,'www.lotus.com')]/span/span[text()='Submit Feedback']")))
					log.warn("Verified Submit Feedback link launches correct page.");
				
			}else if(BrowserSetup.BrowserType.equalsIgnoreCase("internetexplorer")){
				
				if(asserter.verifyExist(asserter.new XPath("//a[@id='submitFeedback' and contains(@href,'www.lotus.com')]/span/span/span[text()='Submit Feedback']")))
					log.warn("Verified Submit Feedback link launches correct page.");
							
			}
			
			if(BrowserSetup.BrowserType.equalsIgnoreCase("firefox")){
				
				qfd_Place.getDownload().click();
				Thread.sleep(Wait.SHORT);
				
				AT.setFocusDoKeys("Opening", "{ENTER}");
				log.warn("Verified Download link launches Download dialog for qkrconn.exe.");
				Thread.sleep(Wait.SHORT);
				
			}else if(BrowserSetup.BrowserType.equalsIgnoreCase("internetexplorer")){
				
				if(asserter.verifyExist(asserter.new XPath("//a[text()='Download' and contains(@href,'exe')]")))
					log.warn("Verified Download link launches Download dialog for qkrconn.exe.");
				
				Thread.sleep(Wait.SHORT);
							
			}
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			qfd_Place.getLearnMore().click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist(asserter.new XPath("//div[text()='Lotus Quickr Connectors']")))
				log.warn("Verified Learn More link launches Quick Learn More page.");
			
			driver.navigate().back();
			log.info("Back to the previous page.");
			Thread.sleep(Wait.MID);
			
			driver.navigate().refresh();
			Thread.sleep(Wait.PAUSE);
			
			if(BrowserSetup.BrowserType.equalsIgnoreCase("firefox")){
			
				qfd_Place.getAbout().click();
				Thread.sleep(Wait.MID);
				
			}else if(BrowserSetup.BrowserType.equalsIgnoreCase("internetexplorer")){
				
				qfd_Place.getAboutIE().click();
				Thread.sleep(Wait.MID);
				
			}
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='IBM Lotus Quickr']")))
				log.warn("Verified About link launches Quickr About page.");
			
			driver.navigate().back();
			log.info("Back to the previous page.");
			Thread.sleep(Wait.MID);
			
			if(BrowserSetup.BrowserType.equalsIgnoreCase("firefox")){
				
				if(asserter.verifyExist(asserter.new XPath("//a[@href='http://www.ibm.com/lotus/quickr']/span/span[text()='Lotus Quickr on ibm.com']")))
					log.warn("Verified Lotus Quickr on ibm.com link launches correct page.");
				
			}else if(BrowserSetup.BrowserType.equalsIgnoreCase("internetexplorer")){
				
				if(asserter.verifyExist(asserter.new XPath("//a[@href='http://www.ibm.com/lotus/quickr']/span/span/span[text()='Lotus Quickr on ibm.com']")))
					log.warn("Verified Lotus Quickr on ibm.com link launches correct page.");
							
			}
			
			qfd_Admin.getHome().click();
			Thread.sleep(Wait.SHORT);
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		qfd_LoginTask.logOutAndLogIn(authorName, authorPW);
		
		try{
			
			qfd_Admin.getHome().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Home']")))
				log.warn("Verified Home link brings user to Home page.");
			
			qfd_Place.getFooterHelp().click();
			Thread.sleep(Wait.MID);
			
			String handles[] = new String[5];
			driver.getWindowHandles().toArray(handles);
			
			driver.switchTo().window(handles[3]);
			Thread.sleep(Wait.PAUSE);
			
			if(asserter.verifyTrue(driver.getTitle().contains("Index")))
				log.warn("Verified Help link brings user to the onlie help.");
			
			driver.switchTo().window(handles[0]);
			Thread.sleep(Wait.PAUSE);
			
			if(BrowserSetup.BrowserType.equalsIgnoreCase("firefox")){
				
				if(asserter.verifyExist(asserter.new XPath("//a[@href='http://www.lotus.com/ldd/lqwiki.nsf']/span/span[text()='Best Practices Wiki']")))
					log.warn("Verified Best Practices link launches correct page.");
				
			}else if(BrowserSetup.BrowserType.equalsIgnoreCase("internetexplorer")){
				
				if(asserter.verifyExist(asserter.new XPath("//a[@href='http://www.lotus.com/ldd/lqwiki.nsf']/span/span/span[text()='Best Practices Wiki']")))
					log.warn("Verified Best Practices link launches correct page.");
							
			}
			
			if(BrowserSetup.BrowserType.equalsIgnoreCase("firefox")){
				
				if(asserter.verifyExist(asserter.new XPath("//a[@id='submitFeedback' and contains(@href,'www.lotus.com')]/span/span[text()='Submit Feedback']")))
					log.warn("Verified Submit Feedback link launches correct page.");
				
			}else if(BrowserSetup.BrowserType.equalsIgnoreCase("internetexplorer")){
				
				if(asserter.verifyExist(asserter.new XPath("//a[@id='submitFeedback' and contains(@href,'www.lotus.com')]/span/span/span[text()='Submit Feedback']")))
					log.warn("Verified Submit Feedback link launches correct page.");
							
			}

			if(BrowserSetup.BrowserType.equalsIgnoreCase("firefox")){
				
				qfd_Place.getDownload().click();
				Thread.sleep(Wait.SHORT);
				
				AT.setFocusDoKeys("Opening", "{ENTER}");
				log.warn("Verified Download link launches Download dialog for qkrconn.exe.");
				Thread.sleep(Wait.SHORT);
				
			}else if(BrowserSetup.BrowserType.equalsIgnoreCase("internetexplorer")){
				
				if(asserter.verifyExist(asserter.new XPath("//a[text()='Download' and contains(@href,'exe')]")))
					log.warn("Verified Download link launches Download dialog for qkrconn.exe.");
				
				Thread.sleep(Wait.SHORT);
							
			}
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			qfd_Place.getLearnMore().click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist(asserter.new XPath("//div[text()='Lotus Quickr Connectors']")))
				log.warn("Verified Learn More link launches Quick Learn More page.");
			
			driver.navigate().back();
			log.info("Back to the previous page.");
			Thread.sleep(Wait.MID);
			
			if(BrowserSetup.BrowserType.equalsIgnoreCase("firefox")){
				
				qfd_Place.getAbout().click();
				Thread.sleep(Wait.MID);
				
			}else if(BrowserSetup.BrowserType.equalsIgnoreCase("internetexplorer")){
				
				qfd_Place.getAboutIE().click();
				Thread.sleep(Wait.MID);
				
			}
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='IBM Lotus Quickr']")))
				log.warn("Verified About link launches Quickr About page.");
			
			driver.navigate().back();
			log.info("Back to the previous page.");
			Thread.sleep(Wait.MID);
			
			if(BrowserSetup.BrowserType.equalsIgnoreCase("firefox")){
				
				if(asserter.verifyExist(asserter.new XPath("//a[@href='http://www.ibm.com/lotus/quickr']/span/span[text()='Lotus Quickr on ibm.com']")))
					log.warn("Verified Lotus Quickr on ibm.com link launches correct page.");
				
			}else if(BrowserSetup.BrowserType.equalsIgnoreCase("internetexplorer")){
				
				if(asserter.verifyExist(asserter.new XPath("//a[@href='http://www.ibm.com/lotus/quickr']/span/span/span[text()='Lotus Quickr on ibm.com']")))
					log.warn("Verified Lotus Quickr on ibm.com link launches correct page.");
							
			}
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		qfd_LoginTask.logOutAndLogIn(ownerName, ownerPW);
		
		log.info("End of 'Verify Footer Links'.");
		log.info("************************************");
		
	}

	public void verifyTOC() throws Exception {
			
		log.info("************************************");
		log.info("Starting 'Verify Items On Table Of Contents'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			if(asserter.verifyExist(asserter.new XPath("//a[contains(@class,'toc') and contains(@title,'Home')]")))
				log.warn("Verified there is Home item on TOC.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[contains(@class,'toc') and contains(@title,'Library')]")))
				log.warn("Verified there is Library item on TOC.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[contains(@class,'toc') and contains(@title,'Forums')]")))
				log.warn("Verified there is Forums item on TOC.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[contains(@class,'toc') and contains(@title,'Calendar')]")))
				log.warn("Verified there is Calendar item on TOC.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[contains(@class,'toc') and contains(@title,'Tasks')]")))
				log.warn("Verified there is Tasks item on TOC.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[contains(@class,'toc') and contains(@title,'Index')]")))
				log.warn("Verified there is Index item on TOC.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[contains(@class,'toc') and contains(@title,'Members')]")))
				log.warn("Verified there is Members item on TOC.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[contains(@class,'toc') and contains(@title,'Trash')]")))
				log.warn("Verified there is Trash item on TOC.");
			
			new Element(driver.findElement(By.xpath("//a[contains(@class,'toc') and contains(@title,'Home')]")), "Home link on TOC").click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//div[contains(text(),'You are in')]")).getText().contains("Home")))
				log.warn("Verified the breadcrumb of Home item is correct.");
			
			new Element(driver.findElement(By.xpath("//a[contains(@class,'toc') and contains(@title,'Library')]")), "Library link on TOC").click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//div[contains(text(),'You are in')]")).getText().contains("Library")))
				log.warn("Verified the breadcrumb of Library item is correct.");
			
			new Element(driver.findElement(By.xpath("//a[contains(@class,'toc') and contains(@title,'Forums')]")), "Forums link on TOC").click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//div[contains(text(),'You are in')]")).getText().contains("Forums")))
				log.warn("Verified the breadcrumb of Forums item is correct.");
			
			new Element(driver.findElement(By.xpath("//a[contains(@class,'toc') and contains(@title,'Calendar')]")), "Calendar link on TOC").click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//div[contains(text(),'You are in')]")).getText().contains("Calendar")))
				log.warn("Verified the breadcrumb of Calendar item is correct.");
			
			new Element(driver.findElement(By.xpath("//a[contains(@class,'toc') and contains(@title,'Tasks')]")), "Tasks link on TOC").click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//div[contains(text(),'You are in')]")).getText().contains("Tasks")))
				log.warn("Verified the breadcrumb is of Tasks item correct.");
			
			new Element(driver.findElement(By.xpath("//a[contains(@class,'toc') and contains(@title,'Index')]")), "Index link on TOC").click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//div[contains(text(),'You are in')]")).getText().contains("Index")))
				log.warn("Verified the breadcrumb of Index item is correct.");
			
			new Element(driver.findElement(By.xpath("//a[contains(@class,'toc') and contains(@title,'Members')]")), "Members link on TOC").click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist(asserter. new XPath("//span[text()='Members']")))
				log.warn("Verified the Members dialog pops up.");
			
			qfd_Admin.getClose().click();
			Thread.sleep(Wait.MID);
			
			new Element(driver.findElement(By.xpath("//a[contains(@class,'toc') and contains(@title,'Trash')]")), "Trash link on TOC").click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//div[contains(text(),'You are in')]")).getText().contains("Trash")))
				log.warn("Verified the breadcrumb of Trash item is correct.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
				
		log.info("End of 'Verify Items On Table Of Contents'.");
		log.info("************************************");
		
	}

	public void verifyPlaceActions(String folderName) throws Exception {
		
		log.info("************************************");
		log.info("Starting 'Verify Items On Place Actions'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			placeNewUpload();
			
			if(asserter.verifyExist(asserter.new XPath("//strong/span[text()='" + folderName + "']")))
				log.warn("Verified the default location of Upload is correct.");
			
			qfd_Place.getCloseImage().click();
			Thread.sleep(Wait.SHORT);
			
			placeNewPage();
			
			if(asserter.verifyExist(asserter.new XPath("//strong/span[text()='" + folderName + "']")))
				log.warn("Verified the default location of Page is correct.");
			
			driver.navigate().back();
			Thread.sleep(Wait.PAUSE);
			driver.switchTo().alert().accept();
			log.info("Clicking on OK button.");
			Thread.sleep(Wait.SHORT);
			
			placeNewImport();
			
			if(asserter.verifyExist(asserter.new XPath("//strong/span[text()='" + folderName + "']")))
				log.warn("Verified the default location of Imported File is correct.");
			
			qfd_Place.getCloseImage().click();
			Thread.sleep(Wait.SHORT);
			
			placeNewLink();
			
			if(asserter.verifyExist(asserter.new XPath("//strong/span[text()='" + folderName + "']")))
				log.warn("Verified the default location of Link is correct.");
			
			driver.navigate().back();
			Thread.sleep(Wait.PAUSE);
			driver.switchTo().alert().accept();
			log.info("Clicking on OK button.");
			Thread.sleep(Wait.SHORT);
			
			placeNewList();
			
			if(asserter.verifyExist(asserter.new XPath("//b/span[text()='" + folderName + "']")))
				log.warn("Verified the default location of List is correct.");
			
			qfd_Place.getCloseImage().click();
			Thread.sleep(Wait.SHORT);
			
			placeNewFolder();
			
			if(asserter.verifyExist(asserter.new XPath("//strong/span[text()='" + folderName + "']")))
				log.warn("Verified the default location of Folder is correct.");
			
			qfd_Place.getCloseImage().click();
			Thread.sleep(Wait.SHORT);
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
				
		log.info("End of 'Verify Items On Place Actions'.");
		log.info("************************************");
		
	}

	public void deleteFolderAndLibrary(String folderName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Delete All Folders On TOC'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			qfd_LibraryTask.goLibrary();
			
			qfd_Page.getMoreActions().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Folder.getThisFolderItem().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Folder.getMoveFolderTrash().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().alert().accept();
			log.info("Clicking on OK button.");
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist("successfully moved to"))
				log.warn("Verified the moved to trash message exists.");	
			
			if(asserter.verifyNotExist(asserter.new XPath("//a[contains(text(),'Library')]")))
				log.warn("Verified the Library is removed from TOC.");
			
			new Element(driver.findElement(By.xpath("//a[contains(@title,'" + folderName + "')]")), folderName + " link on TOC").click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Page.getMoreActions().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Folder.getThisFolderItem().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Folder.getMoveFolderTrash().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().alert().accept();
			log.info("Clicking on OK button.");
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist("successfully moved to"))
				log.warn("Verified the moved to trash message exists.");	
			
			if(asserter.verifyNotExist(asserter.new XPath("//a[contains(text(),'" + folderName + "')]")))
				log.warn("Verified the " + folderName + " on TOC is removed.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
				
		log.info("End of 'Delete All Folders On TOC'.");
		log.info("************************************");
		
	}

	public void verifyPlaceActionsNoFolder(String authorName, String authorPW) throws Exception {

		log.info("************************************");
		log.info("Starting 'Verify Items On Place Actions With No Folder on TOC'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			placeNewUpload();
			
			if(asserter.verifyExist(asserter.new XPath("//strong/span[text()='Index']")))
				log.warn("Verified the default location of Upload is correct.");
			
			qfd_Place.getCloseImage().click();
			Thread.sleep(Wait.SHORT);
			
			placeNewPage();
			
			if(asserter.verifyExist(asserter.new XPath("//strong/span[text()='Index']")))
				log.warn("Verified the default location of Page is correct.");
			
			driver.navigate().back();
			Thread.sleep(Wait.PAUSE);
			driver.switchTo().alert().accept();
			log.info("Clicking on OK button.");
			Thread.sleep(Wait.SHORT);
			
			placeNewImport();
			
			if(asserter.verifyExist(asserter.new XPath("//strong/span[text()='Index']")))
				log.warn("Verified the default location of Imported File is correct.");
			
			qfd_Place.getCloseImage().click();
			Thread.sleep(Wait.SHORT);
			
			placeNewLink();
			
			if(asserter.verifyExist(asserter.new XPath("//strong/span[text()='Index']")))
				log.warn("Verified the default location of Link is correct.");
			
			driver.navigate().back();
			Thread.sleep(Wait.PAUSE);
			driver.switchTo().alert().accept();
			log.info("Clicking on OK button.");
			Thread.sleep(Wait.SHORT);
			
			placeNewList();
			
			if(asserter.verifyExist(asserter.new XPath("//b/span[text()='Navigator']")))
				log.warn("Verified the default location of List is correct.");
			
			qfd_Place.getCloseImage().click();
			Thread.sleep(Wait.SHORT);
			
			placeNewFolder();
			
			if(asserter.verifyExist(asserter.new XPath("//strong/span[text()='Navigator']")))
				log.warn("Verified the default location of Folder is correct.");
			
			qfd_Place.getCloseImage().click();
			Thread.sleep(Wait.SHORT);
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		qfd_LoginTask.logOutAndLogIn(authorName, authorPW);
		
		try{
			
			placeNewUpload();
			
			if(asserter.verifyExist(asserter.new XPath("//strong/span[text()='Index']")))
				log.warn("Verified the default location of Upload is correct.");
			
			qfd_Place.getCloseImage().click();
			Thread.sleep(Wait.SHORT);
			
			placeNewPage();
			
			if(asserter.verifyExist(asserter.new XPath("//strong/span[text()='Index']")))
				log.warn("Verified the default location of Page is correct.");
			
			driver.navigate().back();
			Thread.sleep(Wait.PAUSE);
			driver.switchTo().alert().accept();
			log.info("Clicking on OK button.");
			Thread.sleep(Wait.SHORT);
			
			placeNewImport();
			
			if(asserter.verifyExist(asserter.new XPath("//strong/span[text()='Index']")))
				log.warn("Verified the default location of Imported File is correct.");
			
			qfd_Place.getCloseImage().click();
			Thread.sleep(Wait.SHORT);
			
			placeNewLink();
			
			if(asserter.verifyExist(asserter.new XPath("//strong/span[text()='Index']")))
				log.warn("Verified the default location of Link is correct.");
			
			driver.navigate().back();
			Thread.sleep(Wait.PAUSE);
			driver.switchTo().alert().accept();
			log.info("Clicking on OK button.");
			Thread.sleep(Wait.SHORT);
			
			placeNewList();
			
			if(asserter.verifyNotExist(asserter.new XPath("//span[text()='Create New List']")))
				log.warn("Verified the List item is grayed out.");
			
			new Element(driver.findElement(By.xpath("//a[contains(@class,'toc') and contains(@title,'Home')]")), "Home link on TOC").click();
			Thread.sleep(Wait.MID);
			
			placeNewFolder();
			
			if(asserter.verifyNotExist(asserter.new XPath("//span[text()='New Folder']")))
				log.warn("Verified the Folder item is grayed out.");
			
			new Element(driver.findElement(By.xpath("//a[contains(@class,'toc') and contains(@title,'Home')]")), "Home link on TOC").click();
			Thread.sleep(Wait.MID);
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify Items On Place Actions With No Folder on TOC'.");
		log.info("************************************");
		
	}

	public void verifyPlacesCatalog(String userName, String otherUserName, String userPW) throws Exception {
		
		log.info("************************************");
		log.info("Starting 'Verify Places Catalog'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			qfd_Place.getMyPlaces().click();
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
		
		qfd_LoginTask.logIn(userName, userPW);
		
		//String temp = Users.PlaceName;
			
		try{

			Thread.sleep(Wait.MID);//Wu Jun
			
//mary
//			if(asserter.verifyNotExist(asserter.new XPath("//li[text()='20']"))){
//				
//				qfd_Place.get20().click();
//				Thread.sleep(Wait.SHORT);
//				
//			}
			//mary
			action.sendKeys(Keys.END).perform();
			
			if(asserter.verifyExist(asserter.new XPath("//li[text()='20']")))
				log.warn("Verified the default is to show 20 places per page.");
			
			if(asserter.verifyNotExist(asserter.new XPath("//a[text()='20']")))
				log.warn("Verified the show number '20' is grayed.");
			
			if(asserter.verifyExist(asserter.new XPath("//li[text()='Previous']")))
				log.warn("Verified the Previous link is not available.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Next']")))
				log.warn("Verified the Next link is available.");
			
			qfd_Place.get10().click();
//			Thread.sleep(Wait.SHORT);
			Thread.sleep(Wait.LONG);//Wu Jun
			Thread.sleep(Wait.LONG);
			
			
			//String temp = Users.PlaceName;
			//String temp1 = By.xpath("//a[contains(text(),'" + Users.PlaceName + "')]"));
			
			
			String temp = "UAT_PlaceCata";
			//if(asserter.verifyTrue(driver.findElements(By.xpath("//a[contains(text(),'" + Users.PlaceName + "')]")).size() == 10))
			if(asserter.verifyTrue(driver.findElements(By.xpath("//a[contains(text(),'" + temp + "')]")).size() == 10))
				log.warn("Verified only 10 places are shown in the list.");
			
			if(asserter.verifyExist(asserter.new XPath("//li[text()='10']")))
				log.warn("Verified the show number '10' is grayed.");
			
			if(asserter.verifyExist(asserter.new XPath("//li[text()='Previous']")))
				log.warn("Verified the Previous link is not available.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Next']")))
				log.warn("Verified the Next link is available.");
			
			qfd_Place.getNext().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Place.getNext().click();
//			Thread.sleep(Wait.SHORT);
			Thread.sleep(Wait.LONG);//Wu Jun
			
			//mary
			//if(asserter.verifyTrue(driver.findElements(By.xpath("//a[contains(text(),'" + Users.PlaceName + "')]")).size() == 5))
			if(asserter.verifyTrue(driver.findElements(By.xpath("//a[contains(text(),'" + temp + "')]")).size() >= 5))
				log.warn("Verified user sees last 5 places on the list.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Previous']")))
				log.warn("Verified the Previous link is available.");

			Thread.sleep(Wait.LONG);//Wu Jun
			
			//mary debug
//			if(asserter.verifyExist(asserter.new XPath("//li[text()='Next']")))
//				log.warn("Verified the Next link is not available.");
			
			qfd_Place.getPrevious().click();
//			Thread.sleep(Wait.SHORT);
			Thread.sleep(Wait.LONG);//Wu Jun
			
			//mary
			//if(asserter.verifyTrue(driver.findElements(By.xpath("//a[contains(text(),'" + Users.PlaceName + "')]")).size() == 10))
			if(asserter.verifyTrue(driver.findElements(By.xpath("//a[contains(text(),'" + temp + "')]")).size() == 10))
				log.warn("Verified user see previous 10 places on the list.");
			
			qfd_Place.get50().click();
//			Thread.sleep(Wait.SHORT);
			Thread.sleep(Wait.LONG);//Wu Jun
			
			//mary
			action.sendKeys(Keys.END).perform();
			
			//mary
			//if(asserter.verifyTrue(driver.findElements(By.xpath("//a[contains(text(),'" + Users.PlaceName + "')]")).size() == 25))
			if(asserter.verifyTrue(driver.findElements(By.xpath("//a[contains(text(),'" + temp + "')]")).size() == 25))
				log.warn("Verified all places are shown in the list.");
			
			if(asserter.verifyExist(asserter.new XPath("//li[text()='50']")))
				log.warn("Verified the show number '50' is grayed.");
			
			if(asserter.verifyExist(asserter.new XPath("//li[text()='Previous']")))
				log.warn("Verified the Previous link is not available.");

			Thread.sleep(Wait.LONG);//Wu Jun
			
			//mary debug
//			if(asserter.verifyExist(asserter.new XPath("//li[text()='Next']")))
//				log.warn("Verified the Next link is not available.");
			
			qfd_Place.get100().click();
			Thread.sleep(Wait.SHORT);
			
			//mary
			action.sendKeys(Keys.END).perform();
			
			//mary
			//if(asserter.verifyTrue(driver.findElements(By.xpath("//a[contains(text(),'" + Users.PlaceName + "')]")).size() == 25))
			if(asserter.verifyTrue(driver.findElements(By.xpath("//a[contains(text(),'" + temp + "')]")).size() == 25))
				log.warn("Verified all places are shown in the list.");

			//mary
			action.sendKeys(Keys.END).perform();
			
			Thread.sleep(Wait.LONG);//Wu Jun
			if(asserter.verifyExist(asserter.new XPath("//li[text()='100']")))
				log.warn("Verified the show number '50' is grayed.");
			
			if(asserter.verifyExist(asserter.new XPath("//li[text()='Previous']")))
				log.warn("Verified the Previous link is not available.");

			Thread.sleep(Wait.LONG);//Wu Jun
			//Mary
//			if(asserter.verifyExist(asserter.new XPath("//li[text()='Next']")))
//				log.warn("Verified the Next link is not available.");
			
			qfd_Place.getSortByOwner().click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//table[@class='lotusTable']/tbody/tr[1]/td/div[@class='lotusMeta']/a")).getText().contains(userName)))
				log.warn("Verified the places are sorted in ascending order by owner name.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[contains(text(),'UAT_PublicPlace')]")))
				log.warn("Verified the public place created above appears in the list.");
			
			qfd_Place.getSortByOwner().click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//table[@class='lotusTable']/tbody/tr[1]/td/div[@class='lotusMeta']/a")).getText().contains(otherUserName)))
				log.warn("Verified the places are sorted in descending order by owner name.");
			
			qfd_Place.getSortByTitle().click();
//			Thread.sleep(Wait.MID);
			Thread.sleep(Wait.LONG*3);//Wu Jun
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//table[@class='lotusTable']/tbody/tr[1]/td/h4/a")).getText().contains(Users.PlaceName)))
				log.warn("Verified the places are sorted in ascending order by placename.");
			
			qfd_Place.getSortByTitle().click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//table[@class='lotusTable']/tbody/tr[1]/td/h4/a")).getText().contains("UAT_PublicPlace")))
				log.warn("Verified the places are sorted in descending order by placename.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify Places Catalog'.");
		log.info("************************************");
		
	}

	public void verifyPermanentURL(String userName, String userPW) throws Exception {

		log.info("************************************");
		log.info("Starting 'Verify Permanent URL'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			qfd_Login.getLogOut().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().alert().accept();
			log.info("Clicking on OK button.");
			Thread.sleep(Wait.MID);
			
			
			String url = BrowserSetup.QuickrURLOutput + "/" + Users.PlaceName + "0";
			log.info("Opening the private place through URL.");
			driver.get(url);
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			qfd_Login.getUserName().sendKeys(userName);
			Thread.sleep(Wait.SHORT);
			
			qfd_Login.getUserPWD().sendKeys(userPW);
			Thread.sleep(Wait.SHORT);
			
			qfd_Login.getSubmitBtn().submit();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Home']")))
				log.warn("Verified user is brought to the correct place.");
			
			qfd_Place.getMyPlaces().click();
			Thread.sleep(Wait.MID);
			
			qfd_Login.getLogOut().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().alert().accept();
			log.info("Clicking on OK button.");
//			Thread.sleep(Wait.MID);

			Thread.sleep(Wait.LONG);//Wu Jun
			String placeName = driver.findElement(By.xpath("//a[contains(text(),'UAT_PublicPlace')]")).getText();
			url = BrowserSetup.QuickrURLOutput + "/" + placeName;
			log.info("Opening the public place through URL.");
			driver.get(url);
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Home']")))
				log.warn("Verified user is brought to the correct place without having to log in.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify Permanent URL'.");
		log.info("************************************");
		
	}

	public void deletePlaceAfterUse(String userName, String userPW) throws Exception {
		
		log.info("************************************");
		log.info("Starting 'Delete Place After Use'...");
		Thread.sleep(Wait.SHORT);
		
		qfd_LoginTask.logIn(userName, userPW);
		
		try{
			
			qfd_Place.getCustomize().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().frame(qfd_Place.getCutomizeFrame());
			Thread.sleep(Wait.PAUSE);
			
			qfd_Place.getBasics().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Place.getDeletePlace().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Place.getDeleteConfirm().sendKeys("yes");
			Thread.sleep(Wait.SHORT);
			
			qfd_Place.getDelete().click();
			Thread.sleep(Wait.SHORT);
			
			//Mary deleted this on 03/21/2013
//			if(asserter.verifyExist(asserter.new XPath("//h1[text()='Deleting your place']")))
//				log.warn("Verified the Deleting screen is displayed.");
	
			Thread.sleep(Wait.VERYLONG);
			
			//Mary deleted this on 03/21/2013
//			driver.switchTo().defaultContent();
//			Thread.sleep(Wait.PAUSE);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='My Places']")))
				log.warn("Verified user is returned to My Places.");
			
			if(asserter.verifyNotExist(asserter.new XPath("//a[text()='" + Users.PlaceName + "']")))
				log.warn("Verified the place is nolonger listed.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Delete Place After Use'.");
		log.info("************************************");
		
	}

	public void verifyWhatsNew(String ownerName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Verify What's New'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			qfd_Place.getPlaceAction().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Place.getWhatsNew().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//div[contains(text(),'You are in')]")).getText().contains("What's New Results")))
				log.warn("Verified the breadcrumb is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()=\"What's New\"]")))
				log.warn("Verified the title is What's New.");
			
			qfd_Page.getMoreActions().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//td[text()='Print']")))
				log.warn("Verified the Print item is correct.");
			
			driver.findElement(By.xpath("//span[text()=\"What's New\"]")).click();
			Thread.sleep(Wait.PAUSE);
			
			if(asserter.verifyExist(asserter.new XPath("//a[@title='This Week']")))
				log.warn("Verified the This Week link is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Today']")))
				log.warn("Verified the Today view is default.");

			if(asserter.verifyExist(asserter.new XPath("//a[contains(text(),'Events')]")))
				log.warn("Verified the Events & Tasks tab is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//li[contains(@class,'lotusSelected')]/a[text()='Updates']")))
				log.warn("Verified the Updates tab has the focus by default.");
			
			if(asserter.verifyExist(asserter.new XPath("//div[text()='" + ownerName + " joined this place']")))
				log.warn("Verified the owner's name is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//div[contains(text(),'Today')]")))
				log.warn("Verified the time is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Subscribe to this feed']")))
				log.warn("Verified the Subscribe to this feed link is correct.");
			
			qfd_Place.getEventTaskTab().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//div[contains(text(),'You are in')]")).getText().contains("What's New Results")))
				log.warn("Verified the breadcrumb is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()=\"What's New\"]")))
				log.warn("Verified the title is What's New.");
			
			qfd_Page.getMoreActions().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//td[text()='Print']")))
				log.warn("Verified the Print item is correct.");
			
			driver.findElement(By.xpath("//span[text()=\"What's New\"]")).click();
			Thread.sleep(Wait.PAUSE);
			
			if(asserter.verifyExist(asserter.new XPath("//a[@title='This Week']")))
				log.warn("Verified the This Week link is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Today']")))
				log.warn("Verified the Today view is default.");
			
			if(asserter.verifyExist(asserter.new XPath("//li[contains(@class,'lotusSelected')]/a[contains(text(),'Events')]")))
				log.warn("Verified the Events & Tasks tab is selected.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Updates']")))
				log.warn("Verified the Updates tab is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//h3[text()='Tasks Due']")))
				log.warn("Verified the Tasks Due heading is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//div[contains(text(),'No tasks are due for this time period')]")))
				log.warn("Verified no Events is displayed.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Subscribe to this feed']")))
				log.warn("Verified the Subscribe to this feed link is correct.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify What's New'.");
		log.info("************************************");
		
	}

	public void verifyNewContent(String ownerName, String pageName, String eventName, String taskName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Verify New Content What's New'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			qfd_Place.getPlaceAction().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Place.getWhatsNew().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//div[contains(text(),'You are in')]")).getText().contains("What's New Results")))
				log.warn("Verified the breadcrumb is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()=\"What's New\"]")))
				log.warn("Verified the title is What's New.");
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//div[contains(text(),'The page')]")).getText().contains(ownerName)))
				log.warn("Verified the Page info is correct.");
			
			if(asserter.verifyTrue(driver.findElements(By.xpath("//a[text()='" + pageName + "']")).size() == 2))
				log.warn("Verified the Page link is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//div[contains(text(),'1 new response')]")))
				log.warn("Verified the response info is correct.");
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//div[contains(text(),'The event')]")).getText().contains(ownerName)))
				log.warn("Verified the Event info is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + eventName + "']")))
				log.warn("Verified the Event link is correct.");
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//div[contains(text(),'The task')]")).getText().contains(ownerName)))
				log.warn("Verified the Task info is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + taskName + "']")))
				log.warn("Verified the Task link is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//div[text()='" + ownerName + " joined this place']")))
				log.warn("Verified the owner's name is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Subscribe to this feed']")))
				log.warn("Verified the Subscribe to this feed link is correct.");
			
			qfd_Place.getEventTaskTab().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + eventName + "']")))
				log.warn("Verified the Event link is correct.");
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//div[contains(text(),'All Day')]")).getText().contains(ownerName)))
				log.warn("Verified the Event info is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//h3[text()='Tasks Due']")))
				log.warn("Verified the Tasks Due heading is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//div[contains(text(),'No tasks are due for this time period')]")))
				log.warn("Verified no task is displayed.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Subscribe to this feed']")))
				log.warn("Verified the Subscribe to this feed link is correct.");
			
			qfd_Place.getThisWeek().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//div[contains(text(),'You are in')]")).getText().contains("What's New Results")))
				log.warn("Verified the breadcrumb is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()=\"What's New\"]")))
				log.warn("Verified the title is What's New.");
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//div[contains(text(),'The page')]")).getText().contains(ownerName)))
				log.warn("Verified the Page info is correct.");
			
			if(asserter.verifyTrue(driver.findElements(By.xpath("//a[text()='" + pageName + "']")).size() == 2))
				log.warn("Verified the Page link is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//div[contains(text(),'1 new response')]")))
				log.warn("Verified the response info is correct.");
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//div[contains(text(),'The event')]")).getText().contains(ownerName)))
				log.warn("Verified the Event info is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + eventName + "']")))
				log.warn("Verified the Event link is correct.");
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//div[contains(text(),'The task')]")).getText().contains(ownerName)))
				log.warn("Verified the Task info is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + taskName + "']")))
				log.warn("Verified the Task link is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//div[text()='" + ownerName + " joined this place']")))
				log.warn("Verified the owner's name is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Subscribe to this feed']")))
				log.warn("Verified the Subscribe to this feed link is correct.");
			
			qfd_Place.getEventTaskTab().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + eventName + "']")))
				log.warn("Verified the Event link is correct.");
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//div[contains(text(),'All Day')]")).getText().contains(ownerName)))
				log.warn("Verified the Event info is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + taskName + "']")))
				log.warn("Verified the Task link is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//div[contains(text(),'due')]")))
				log.warn("Verified the Task info is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Subscribe to this feed']")))
				log.warn("Verified the Subscribe to this feed link is correct.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify New Content What's New'.");
		log.info("************************************");
		
	}

	public void createForms(String formName, String textTitle) throws Exception {

		log.info("************************************");
		log.info("Starting 'Create Forms'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			qfd_Place.getCustomize().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().frame(qfd_Place.getCutomizeFrame());
			Thread.sleep(Wait.PAUSE);
			
			qfd_Place.getForm().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Place.getNewForm().click();
			Thread.sleep(Wait.SHORT);
			
//			qfd_Place.getNext().click();
//			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='New Form']")))
				log.warn("Verified the New Form screen displays.");
			
			qfd_Place.getName().sendKeys(formName);
			Thread.sleep(Wait.SHORT);
			
			qfd_Place.getAdd().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='Add Field']")))
				log.warn("Verified the Add Field screen displays.");
			
			asserter.checkboxClick(asserter.new XPath("//input[@title='Text Area']"), true);
			
			qfd_Place.getNext().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='Text Area Options']")))
				log.warn("Verified the Text Area Options screen displays.");
			
			qfd_Place.getName().sendKeys(textTitle);
			Thread.sleep(Wait.SHORT);

			asserter.checkboxClick(asserter.new XPath("//input[@title='Required field']"), true);
			
			qfd_Place.getNext().click();
			Thread.sleep(Wait.MID);
			
			qfd_Place.getDone().click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + formName + "']")))
				log.warn("Verified the form created successfully.");
			
			qfd_Place.getNewForm().click();
			Thread.sleep(Wait.SHORT);
			
//			qfd_Place.getNext().click();
//			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='New Form']")))
				log.warn("Verified the New Form screen displays.");
			
			qfd_Place.getName().sendKeys(formName + " 2");
			Thread.sleep(Wait.SHORT);
			
			qfd_Place.getAdd().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='Add Field']")))
				log.warn("Verified the Add Field screen displays.");
			
			asserter.checkboxClick(asserter.new XPath("//input[@title='Text Area']"), true);
			
			qfd_Place.getNext().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='Text Area Options']")))
				log.warn("Verified the Text Area Options screen displays.");
			
			qfd_Place.getName().sendKeys(textTitle);
			Thread.sleep(Wait.SHORT);

			asserter.checkboxClick(asserter.new XPath("//input[@title='Required field']"), true);
			
			qfd_Place.getNext().click();
			Thread.sleep(Wait.MID);
			
			qfd_Place.getDone().click();
			Thread.sleep(Wait.MID);
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			asserter.elementClickWait(qfd_Place.getClose(), asserter.new XPath("//span[text()='Customize']"), false);
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Create Forms'.");
		log.info("************************************");
		
	}

	public void editForm(String formName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Edit Forms'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			qfd_Place.getCustomize().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().frame(qfd_Place.getCutomizeFrame());
			Thread.sleep(Wait.PAUSE);
			
			qfd_Place.getForm().click();
			Thread.sleep(Wait.SHORT);
			
			asserter.checkboxClick(asserter.new XPath("//input[contains(@title,'" + formName + " 2')]"), true);
			
			qfd_Place.getDeleteForm().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().alert().accept();
			log.info("Clicking on OK button.");
			Thread.sleep(Wait.LONG);
			
			if(asserter.verifyNotExist(asserter.new XPath("//a[text()='" + formName + " 2']")))
				log.warn("Verified the form is deleted successfully.");
			
			new Element(driver.findElement(By.xpath("//a[text()='" + formName + "']")), formName + " link").click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='Edit Form']")))
				log.warn("Verified the Edit Form screen displays.");
			
			qfd_Place.getAdd().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='Add Field']")))
				log.warn("Verified the Add Field screen displays.");
			
			asserter.checkboxClick(asserter.new XPath("//input[@title='Attachments']"), true);
			
			qfd_Place.getNext().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='Attachments Options']")))
				log.warn("Verified the Attachments Options screen displays.");
			
			new Element(driver.findElement(By.xpath("//input[@name='h_NameSetting']"))).sendKeys("Attachment");
			Thread.sleep(Wait.SHORT);
			
			qfd_Place.getNext().click();
			Thread.sleep(Wait.MID);
			
			qfd_Place.getDone().click();
			Thread.sleep(Wait.MID);	
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			asserter.elementClickWait(qfd_Place.getClose(), asserter.new XPath("//span[text()='Customize']"), false);
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Edit Forms'.");
		log.info("************************************");
		
	}
	
}
