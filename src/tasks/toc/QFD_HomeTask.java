package tasks.toc;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import config.Wait;
import exceptions.ElementException;

import util.Assert;
import util.Assert.XPath;
import appobjects.library.QFD_Page;
import appobjects.toc.QFD_Home;
import appobjects.toc.QFD_Library;

public class QFD_HomeTask {

	private WebDriver driver;
	private Logger log;
	private Assert asserter;
	Actions action = null;
	QFD_LibraryTask qfd_LibraryTask = null;
	QFD_Page qfd_Page = null;
	QFD_Library qfd_Library = null;
	QFD_Home qfd_Home = null;
	
	public QFD_HomeTask(WebDriver driver, Logger log){
		
		this.driver = driver;
		this.log = log;
		this.asserter = new Assert(driver, log);
		action = new Actions(driver);
		qfd_LibraryTask = new QFD_LibraryTask(driver, log);
		qfd_Page = new QFD_Page(driver);
		qfd_Library = new QFD_Library(driver);
		qfd_Home = new QFD_Home(driver);
		
	}

	public void verifyHome() throws Exception {
		
		log.info("************************************");
		log.info("Starting 'Verify Home Page'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			goHome();
			
			if(asserter.verifyExist(asserter.new XPath("//div[contains(text(),'You are in')]")))
				log.warn("Verified the infomation on breadcurmbs is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Home']")))
				log.warn("Verified the title is Home.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Check Out and Edit']")))
				log.warn("Verified the Check Out and Edit button exists.");
			
			qfd_Home.getMoreActions().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Home']")))
				log.warn("Verified the title is Home.");
			
			if(asserter.verifyExist(asserter.new XPath("//td[contains(text(),'Check Out')]")))
				log.warn("Verified the the Check Out item is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//td[contains(text(),'Copy To.')]")))
				log.warn("Verified the the Copy To item is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//td[contains(text(),'Move To.')]")))
				log.warn("Verified the the Move To item is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//td[contains(text(),'Move To Trash')]")))
				log.warn("Verified the the Move To Trash item is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//td[contains(text(),'Send Link')]")))
				log.warn("Verified the the Send Link item is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//td[contains(text(),'Print')]")))
				log.warn("Verified the the Print item is correct.");
			
			driver.switchTo().frame(qfd_Page.getTextFrame());
			Thread.sleep(Wait.PAUSE);

			if(asserter.verifyExist(asserter.new XPath("//b[text()='Get Started']")))
				log.warn("Verified the header is Get Started.");
			
			if(asserter.verifyExist(asserter.new XPath("//h3[text()='Library']")))
				log.warn("Verified the heading: Library exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//h3[text()='Forums']")))
				log.warn("Verified the heading: Forums exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//h3[text()='Lists']")))
				log.warn("Verified the heading: Lists exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//h3[text()='Calendar']")))
				log.warn("Verified the heading: Calendar exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//h3[text()='Tasks']")))
				log.warn("Verified the heading: Tasks exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//h3[text()='Customization']")))
				log.warn("Verified the heading: Customization exists.");
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Subscribe to this document']")))
				log.warn("Verified the Subscribe to this document link exists.");
			
			goHome();
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify Home Page'.");
		log.info("************************************");
		
	}
	
	public void goHome() throws Exception {
		
		asserter.elementClick(qfd_Home.getHome(), asserter.new XPath("//span[text()='Home']"), true);
		Thread.sleep(Wait.SHORT);
		
	}

	public void verifyEditHome() throws Exception {
		
		log.info("************************************");
		log.info("Starting 'Verify Edit Home Page'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			goHome();
			
			qfd_Page.getCheckOut().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//a[contains(text(),'UAT_Home')]")))
				log.warn("Verified the place name is correct.");
			
			if(asserter.verifyExist("This page is checked out while you edit it"))
				log.warn("Verified the infomation message is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Edit Page']")))
				log.warn("Verified the title is Edit Page.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Change location']")))
				log.warn("Verified the Change location link exists.");
			
			if(asserter.verifyEquals(qfd_Library.getLinkPageName().getAttribute("value"), "Home"))
				log.warn("Verified the Name field contains 'Home'.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Attach files to this page']")))
				log.warn("Verified the Attach files to this page link is available.");
			
			if(asserter.verifyExist(asserter.new XPath("//h3[text()='Who Can Access This']")))
				log.warn("Verified the ACL Controls is available.");
			
			qfd_Home.getACL().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//input[@title='Give reader permissions to all place members']")))
				log.warn("Verified the All place members(public) selector exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//input[@title='Give reader permissions to other users']")))
				log.warn("Verified the Specific place members(restricted) selector exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//input[@title='Restrict reader access to only you']")))
				log.warn("Verified the You(private) selector exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Select Readers']")))
				log.warn("Verified the Select Readers link exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Select Readers']")))
				log.warn("Verified the Select Readers link exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//h3[text()='Customize']")))
				log.warn("Verified the Customize is available.");
			
			qfd_Home.getCustomize().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//input[@title='Show Page Header']")))
				log.warn("Verified the Show Page Header checkbox exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//input[@title='Show About Tab']")))
				log.warn("Verified the Show About Tab checkbox exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//input[@title='Show Comments Tab']")))
				log.warn("Verified the Show Comments Tab checkbox exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//input[@title='Show Versions Tab']")))
				log.warn("Verified the Show Versions Tab checkbox exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//input[@value='Save and Check In' and @type='button']")))
				log.warn("Verified the Save and Check In button exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Save as Draft']")))
				log.warn("Verified the Save as Draft link exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Cancel']")))
				log.warn("Verified the Cancel link exists.");
			
			qfd_Page.getCancel().click();
			Thread.sleep(Wait.PAUSE);
			
			driver.switchTo().alert().accept();
			log.info("Clicking on OK button");
			Thread.sleep(Wait.PAUSE);
			
			goHome();
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify Edit Home Page'.");
		log.info("************************************");
		
	}

	public void editHome(String imgPath, String attachPath, String attachName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Edit Home Page'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			goHome();
			
			qfd_Page.getCheckOut().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Library.getLinkPageName().clear();
			Thread.sleep(Wait.PAUSE);
			
			qfd_Library.getLinkPageName().sendKeys("Home Edit");
			Thread.sleep(Wait.PAUSE);
			
			qfd_Page.getInsertImage().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Page.getImageUpload().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().frame(qfd_Page.getUploadFrame());
			Thread.sleep(Wait.PAUSE);
			
			qfd_Page.getImgUpload().sendKeys(imgPath);
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			qfd_Page.getSendToServer().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Page.getImgOK().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Library.getPageAttach().click();
			Thread.sleep(Wait.SHORT);

			qfd_Library.getFileField().sendKeys(attachPath);
			Thread.sleep(Wait.SHORT);
			
			qfd_Home.getCustomize().click();
			Thread.sleep(Wait.SHORT);
			
			asserter.checkboxClick(asserter.new XPath("//input[@title='Show About Tab']"), true);
			Thread.sleep(Wait.SHORT);
			
			asserter.elementClickWait(qfd_Library.getSaveCheckIn(), asserter.new XPath("//a[text()='Subscribe to this document']"), true);
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist("Successfully saved"))
				log.warn("Verified the Home page has checked in.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Home Edit']")))
				log.warn("Verified the title has changed to Home Edit.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[contains(text(),'Home Edit')]")))
				log.warn("Verified the title on TOC has changed to Home Edit.");
			
			driver.switchTo().frame(qfd_Page.getTextFrame());
			Thread.sleep(Wait.PAUSE);
			
			if(asserter.verifyExist(asserter.new XPath("//img[contains(@src,'.jpg')]")))
				log.warn("Verified the rich text editing is displayed in the Home Page body.");
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			qfd_Page.getAbout().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='This page is published and checked in']")))
				log.warn("Verified the About tab is visible and contains update infomation.");
			
			qfd_Page.getAttachments().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//div[contains(text(),'" + attachName + "')]")))
				log.warn("Verified the Word doc is attached.");
			
			goHome();
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Edit Home Page'.");
		log.info("************************************");
	}
	
}
