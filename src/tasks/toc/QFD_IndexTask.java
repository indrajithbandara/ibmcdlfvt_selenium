package tasks.toc;

import org.apache.log4j.Logger;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import config.BrowserSetup;
import config.Element;
import config.Wait;

import exceptions.ElementException;

import tasks.common.AutoITTask;
import util.Assert;
import util.Assert.XPath;

import appobjects.common.QFD_Login;
import appobjects.common.QFD_Place;
import appobjects.toc.QFD_Index;
import appobjects.toc.QFD_Library;
import appobjects.toc.QFD_Trash;

public class QFD_IndexTask {

	private WebDriver driver;
	private Logger log;
	private Assert asserter;
	private Actions action;
	private AutoITTask AT = null;
	QFD_Index qfd_Index = null;
	QFD_Place qfd_Place = null;
	QFD_Library qfd_Library = null;
	QFD_Trash qfd_Trash = null;
	QFD_LibraryTask qfd_LibraryTask = null;
	QFD_TrashTask qfd_TrashTask = null;
	
	public QFD_IndexTask(WebDriver driver, Logger log){
		
		this.driver = driver;
		this.log = log;
		this.asserter = new Assert(driver, log);
		qfd_Index = new QFD_Index(driver);
		qfd_Place = new QFD_Place(driver);
		qfd_Library = new QFD_Library(driver);
		qfd_Trash = new QFD_Trash(driver);
		qfd_LibraryTask = new QFD_LibraryTask(driver, log);
		qfd_TrashTask = new QFD_TrashTask(driver, log);
		AT = new AutoITTask(driver, log);
		action = new Actions(driver);
		
	}
	
	public void goIndex() throws Exception {
		
		asserter.elementClick(qfd_Index.getIndex(), asserter.new XPath("//span[text()='Index']"), true);
		Thread.sleep(Wait.SHORT);	
		
	}
	
	public void verifyIndex() throws Exception {
		
		log.info("************************************");
		log.info("Starting 'Verify Index'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			goIndex();
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Index']")))
				log.warn("Verified the Index name is correct");
			
			if(asserter.verifyExist("You are in: "))
				log.warn("Verified the Breadcrumb is correct");
				
			//Mary deleted this on 03/21/2013
//			if(asserter.verifyExist(asserter.new XPath("//a[@title='Views']")))
//				log.warn("Verified the 'Views' button is correct");
			
			if(asserter.verifyExist(asserter.new XPath("//a[@title='More Actions']")))
				log.warn("Verified the 'More Actions' button is correct");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify Index'.");
		log.info("************************************");			
		
	}

	public void addDataToIndex(String uploadPath, String desc, String pageName,
			String attachPath, String linkName, String URL, 
			String folderName, String upload1Path, String upload1Name,
			String listName, String itemTitle) throws Exception {

		log.info("************************************");
		log.info("Starting 'Add Data To Index'...");
		Thread.sleep(Wait.SHORT);
		
		try{	
			
			qfd_LibraryTask.upLoad(uploadPath, desc);
			
			saveDraft(pageName, desc, attachPath);
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
			
		qfd_LibraryTask.createLinkPage(linkName, URL);
			
		qfd_LibraryTask.createFolder(folderName, desc);
		
		try{
			
			qfd_LibraryTask.draftUploadInFolder(folderName, upload1Path, desc);
			
			verifyNoFolder(folderName, upload1Name);
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		qfd_LibraryTask.createListAndItem(listName, desc, itemTitle);
		
		try{
			
			verifyNoList(listName, itemTitle);
			
			verifyFolderItem(folderName, upload1Name);
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Add Data To Index'.");
		log.info("************************************");			
		
		
	}

	private void saveDraft(String pageName, String desc, String attachPath) throws Exception{
	
		qfd_Place.getPlaceAction().click();
		Thread.sleep(Wait.SHORT);
			
		qfd_Place.getPlaceActionNew().click();
		Thread.sleep(Wait.SHORT);
			
		//June
		action.sendKeys(Keys.ENTER).perform();
		Thread.sleep(Wait.MID);
		
		action.sendKeys(Keys.ARROW_DOWN).perform();
		Thread.sleep(Wait.VERYSHORT);
		action.sendKeys(Keys.ENTER).perform();
		Thread.sleep(Wait.MID);
		log.info("Clicking on Page item.");
			
		//June
		Thread.sleep(Wait.SHORT);
		qfd_Library.getPageName().sendKeys(pageName);
		Thread.sleep(Wait.SHORT);
			
		driver.switchTo().frame(qfd_Library.getRichTextFrame());
		Thread.sleep(Wait.PAUSE);
			
		qfd_Library.getPageDesc().sendKeys(desc);
		Thread.sleep(Wait.SHORT);
			
		driver.switchTo().defaultContent();
			
		qfd_Library.getPageAttach().click();
		Thread.sleep(Wait.SHORT);

		qfd_Library.getFileField().sendKeys(attachPath);
		Thread.sleep(Wait.SHORT);
			
		asserter.elementClickWait(qfd_Library.getPageSaveDraft(), asserter.new XPath("//a[text()='Subscribe to this folder']"), true);
		Thread.sleep(Wait.SHORT);
		
		if(asserter.verifyExist(asserter.new XPath("//img[@alt='Word Processing Document']")))
			log.warn("Verified the type icon is correct");
			
		if(asserter.verifyExist(asserter.new XPath("//img[@alt='Checked out by you']")))
			log.warn("Verified the lock icon is correct");
			
		if(asserter.verifyExist(asserter.new XPath("//img[@alt='Click for attachments']")))
			log.warn("Verified the download icon is correct");
		
	}
	
	public void verifyNoFolder(String folderName, String itemName) throws Exception {
		
		goIndex();
		
		if(asserter.verifyNotExist(folderName))
			log.warn("Verified folders will not appear in Index.");
		
		if(asserter.verifyExist(itemName))
			log.warn("Verified the content in the folder appears in Index.");
		
	}
	
	public void verifyNoList(String listName, String itemName) throws Exception {
		
		goIndex();
		
		if(asserter.verifyNotExist(listName))
			log.warn("Verified the list will not appear in Index.");
		
		if(asserter.verifyNotExist(itemName))
			log.warn("Verified the list item will not appears in Index.");
		
	}
	
	public void verifyFolderItem(String folderName, String itemName) throws Exception {
		
		goIndex();
		
		new Element(driver.findElement(By.linkText(itemName))).click();
		Thread.sleep(Wait.SHORT);
		
		if(asserter.verifyExist(asserter.new XPath("//span[text()='" + itemName + "']")))
			log.warn("Verified brought to the upload page.");
		
		if(asserter.verifyExist(folderName))
			log.warn("Verified the breadcrumb infomation is correct.");
		
		if(asserter.verifyExist("Check In"))
			log.warn("Verified the 'Check In' button appears.");
		
	}

	public void verifyMoreActions(String linkName, String user, String PW) throws Exception {

		log.info("************************************");
		log.info("Starting 'Verify More Actions In Index'...");
		Thread.sleep(Wait.SHORT);
		
		try{	
			
			goIndex();
			
			qfd_Index.getMoreActions().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist("Delete"))
				log.warn("Verified Delete item exists.");
			
			if(asserter.verifyExist("Index"))
				log.warn("Verified Index item exists.");
			
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ENTER).perform();
			Thread.sleep(Wait.SHORT);
			log.info("Clicking on Index item.");
			
			if(asserter.verifyExist("Send Link"))
				log.warn("Verified Send Link sub-item exists.");
			
			if(asserter.verifyExist("Print"))
				log.warn("Verified Print sub-item exists.");
			
			asserter.checkboxClick(asserter.new XPath("//input[@title='Test Link']"), true);	
			Thread.sleep(Wait.SHORT);
			
			qfd_Index.getMoreActions().click();
			Thread.sleep(Wait.SHORT);
				
			action.sendKeys(Keys.ENTER).perform();
			Thread.sleep(Wait.SHORT);
			log.info("Clicking on Delete item.");

			driver.switchTo().alert().accept();
			log.info("Clicking on OK button.");
			Thread.sleep(Wait.SHORT);
				
			qfd_TrashTask.verifyTrashItem(linkName);
					
			qfd_TrashTask.restoreOneItem(linkName);
		
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
			
		try{
			
			goIndex();
			
			if(asserter.verifyExist(linkName))
				log.warn("Verified the " + linkName + " appears in the Index.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify More Actions In Index'.");
		log.info("************************************");
		
	}

	public void verifySortAndNavigate() throws Exception {
		
		log.info("************************************");
		log.info("Starting 'Verify Sorting and Navigational Items in the Index View'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify Sorting and Navigational Items in the Index View'.");
		log.info("************************************");
		
	}
	
}
