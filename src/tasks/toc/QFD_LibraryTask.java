package tasks.toc;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import config.BrowserSetup;
import config.Element;
import config.Users;
import config.Wait;
import exceptions.ElementException;

import appobjects.common.QFD_Place;
import appobjects.library.QFD_Page;
import appobjects.toc.QFD_Home;
import appobjects.toc.QFD_Library;

import tasks.common.AutoITTask;
import tasks.common.QFD_RoomTask;
import util.Assert;
import util.Assert.XPath;

public class QFD_LibraryTask {

	private WebDriver driver;
	private Logger log;
	private Assert asserter;
	private Actions action;
	private AutoITTask AT;
	QFD_RoomTask qfd_RoomTask = null;
	QFD_Place qfd_Place = null;
	QFD_Page qfd_Page = null;
	QFD_Home qfd_Home = null;
	QFD_Library qfd_Library = null;
	
	public QFD_LibraryTask(WebDriver driver, Logger log){
		
		this.driver = driver;
		this.log = log;
		this.asserter = new Assert(driver, log);
		AT = new AutoITTask(driver, log);
		qfd_Library = new QFD_Library(driver);
		qfd_Place = new QFD_Place(driver);
		qfd_Page = new QFD_Page(driver);
		qfd_Home = new QFD_Home(driver);
		qfd_RoomTask = new QFD_RoomTask(driver, log);
		action = new Actions(driver);
		
	}
	
	public void goLibrary() throws Exception {
		
		asserter.elementClick(qfd_Library.getLibrary(), asserter.new XPath("//span[text()='Library']"), true);
		Thread.sleep(Wait.SHORT);
		
	}
	
	public void goItem(String itemName) throws Exception {
		
		asserter.elementClick(new Element(driver.findElement(By.linkText(itemName)), "item : " + itemName), asserter.new XPath("//span[text()='" + itemName + "']"), true);
		Thread.sleep(Wait.SHORT);
		
	}
	
	public void newLink() throws Exception {
		
		qfd_Library.getLibraryTitle().click();
		Thread.sleep(Wait.SHORT);
		
		asserter.elementClick(qfd_Library.getNew(), asserter.new XPath("//td[text()='Link']"), true);
		Thread.sleep(Wait.SHORT);
		
		action.sendKeys(Keys.ARROW_DOWN).perform();
		Thread.sleep(Wait.VERYSHORT);
		action.sendKeys(Keys.ARROW_DOWN).perform();
		Thread.sleep(Wait.VERYSHORT);
		action.sendKeys(Keys.ENTER).perform();
		log.info("Clicking on Link item");
		Thread.sleep(Wait.MID);
		
	}
	
	public void newPage() throws Exception {
		
		asserter.elementClick(qfd_Library.getNew(), asserter.new XPath("//td[text()='Link']"), true);
		Thread.sleep(Wait.SHORT);
		
		action.sendKeys(Keys.ENTER).perform();
		log.info("Clicking on Page item");
		Thread.sleep(Wait.MID);
		
	}
	
	public void newImport() throws Exception {
		
		asserter.elementClick(qfd_Library.getNew(), asserter.new XPath("//td[text()='Link']"), true);
		Thread.sleep(Wait.SHORT);
		
		action.sendKeys(Keys.ARROW_DOWN).perform();
		Thread.sleep(Wait.VERYSHORT);
		action.sendKeys(Keys.ENTER).perform();
		log.info("Clicking on Imported File item");
		Thread.sleep(Wait.MID);
		
	}
	
	public void newFolder() throws Exception {
		
		asserter.elementClick(qfd_Library.getNew(), asserter.new XPath("//td[text()='Link']"), true);
		Thread.sleep(Wait.SHORT);
		
		action.sendKeys(Keys.ARROW_UP).perform();
		Thread.sleep(Wait.VERYSHORT);
		action.sendKeys(Keys.ENTER).perform();
		log.info("Clicking on Folder item");
		Thread.sleep(Wait.MID);
		
	}
	
	public void newCusLib() throws Exception {
		
		asserter.elementClick(qfd_Library.getNew(), asserter.new XPath("//td[text()='Link']"), true);
		Thread.sleep(Wait.SHORT);
		
		action.sendKeys(Keys.ARROW_UP).perform();
		Thread.sleep(Wait.VERYSHORT);
		action.sendKeys(Keys.ARROW_UP).perform();
		Thread.sleep(Wait.VERYSHORT);
		action.sendKeys(Keys.ENTER).perform();
		log.info("Clicking on Custom Library item");
		Thread.sleep(Wait.MID);
		
	}
	
	public void newList() throws Exception {
		
		asserter.elementClick(qfd_Library.getNew(), asserter.new XPath("//td[text()='Link']"), true);
		Thread.sleep(Wait.SHORT);
		
		action.sendKeys(Keys.ARROW_DOWN).perform();
		Thread.sleep(Wait.VERYSHORT);
		action.sendKeys(Keys.ARROW_DOWN).perform();
		Thread.sleep(Wait.VERYSHORT);
		action.sendKeys(Keys.ARROW_DOWN).perform();
		Thread.sleep(Wait.VERYSHORT);
		action.sendKeys(Keys.ENTER).perform();
		log.info("Clicking on List item");
		Thread.sleep(Wait.MID);
		
	}
	
	public void createLinkPage(String linkName, String URL) throws Exception {
		
		log.info("************************************");
		log.info("Starting 'Create Link Page'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			goLibrary();
			
			newLink();

			qfd_Library.getLinkPageName().sendKeys(linkName);
			Thread.sleep(Wait.SHORT);
			
			qfd_Library.getLinkURLText().clear();
			Thread.sleep(Wait.VERYSHORT);
			qfd_Library.getLinkURLText().sendKeys(URL);
			Thread.sleep(Wait.SHORT);
			
			qfd_Library.getLinkNewWindow().click();
			Thread.sleep(Wait.PAUSE);
			
			action.sendKeys(Keys.DOWN).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ENTER).perform();
			Thread.sleep(Wait.SHORT);
			
			asserter.elementClickWait(qfd_Library.getSaveCheckIn(), asserter.new XPath("//a[text()='Subscribe to this folder']"), true);
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//div[@class='lotusMessage lotusConfirm']")))
				log.warn("Verified the link page " + linkName + " created successfully.");
			
			if(asserter.verifyEquals((driver.findElement(By.xpath("//div[@class='lotusMessage lotusConfirm']")).getText()), "Successfully saved '" + linkName + "'"));
				log.warn("Verified the text : Successfully saved '" + linkName + "' is correct.");
			
			if(asserter.verifyExist("Successfully saved"))
				log.warn("Verified the text : Successfully saved '" + linkName + "' exists.");	
			
			if(asserter.verifyExist(asserter.new XPath("//img[@alt='Link Page']")))
				log.warn("Verified the type icon is correct");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Create Link Page'.");
		log.info("************************************");
		
	}

	public void clickLink(String linkName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Click Link'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			goLibrary();
			
			new Element(driver.findElement(By.xpath("//span[text()='" + linkName + "']")), "the link").click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyTrue(driver.getTitle().contains("IBM")))
				log.warn("Verified the link is correct.");
			
			driver.navigate().back();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Library']")))
				log.warn("Verified back to the previous page.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Click Link'.");
		log.info("************************************");
		
	}

	public void verifyLinkPage(String linkName, String authorName, String URL) throws Exception {

		log.info("************************************");
		log.info("Starting 'Verify Link Page'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			goLibrary();
			
			asserter.elementClick(qfd_Library.getContext(), asserter.new XPath("//td[text()='View']"), true);
			Thread.sleep(Wait.PAUSE);
			
			qfd_Library.getContextView().click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Public']")))
				log.warn("Verified the link is public.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[@title='" + linkName + "' and text()='" + linkName + "']")))
				log.warn("Verified the link name is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + authorName + "']")))
				log.warn("Verified the author name is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[contains(@href,'" + URL + "')]")))
				log.warn("Verified the URL is correct.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify Link Page'.");
		log.info("************************************");
		
	}

	public void verifyLinkMessage(String linkName, String authorName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Verify Link Message'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			goLibrary();
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='" + linkName + "']")))
				log.warn("Verified the link name is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Today']")))
				log.warn("Verified the 'Last Modified' time is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + authorName + "']")))
				log.warn("Verified the author name is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//img[@alt='Link Page']")))
				log.warn("Verified the link icon is correct.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify Link Message'.");
		log.info("************************************");
		
	}

	public void createPage(String pageName, String pageDesc, String attachPath, boolean isRoom) throws Exception {

		log.info("************************************");
		log.info("Starting 'Create Page'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			if(!isRoom){
				
				goLibrary();
				
				newPage();
				
			}else{
				
				qfd_RoomTask.newPage();
				
			}

			qfd_Library.getPageName().sendKeys(pageName);
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().frame(qfd_Library.getRichTextFrame());
			Thread.sleep(Wait.PAUSE);
			
			qfd_Library.getPageDesc().click();
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Library.getPageDesc().sendKeys(pageDesc);
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			try{
				
				attachPath.getBytes();
				
				qfd_Library.getPageAttach().click();
				Thread.sleep(Wait.SHORT);

				qfd_Library.getFileField().sendKeys(attachPath);
				Thread.sleep(Wait.SHORT);
				
			}catch(NullPointerException e){
				
			}
			
			asserter.elementClickWait(qfd_Library.getSaveCheckIn(), asserter.new XPath("//a[text()='Subscribe to this folder']"), true);
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//div[@class='lotusMessage lotusConfirm']")))
				log.warn("Verified the page " + pageName + " created successfully.");
			
			if(asserter.verifyEquals((driver.findElement(By.xpath("//div[@class='lotusMessage lotusConfirm']")).getText()), "Successfully saved '" + pageName + "'"));
				log.warn("Verified the text : Successfully saved '" + pageName + "' is correct.");
			
			if(asserter.verifyExist("Successfully saved"))
				log.warn("Verified the text : Successfully saved '" + pageName + "' exists.");
					
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Create Page'.");
		log.info("************************************");
		
	}
	
	public void upLoad(String path, String upLoadDesc) throws Exception {	
			
		goLibrary();
			
		qfd_Library.getUpload().click();
		Thread.sleep(Wait.SHORT);
			
		qfd_Library.getUploadFile().sendKeys(path);
		Thread.sleep(Wait.SHORT);
			
		qfd_Library.getUploadDesc().sendKeys(upLoadDesc);
		Thread.sleep(Wait.SHORT);
			
		asserter.elementClickWait(qfd_Library.getSaveCheckIn(), asserter.new XPath("//a[text()='Change location']"), false);
		Thread.sleep(Wait.SHORT);	
		
		if(asserter.verifyExist(asserter.new XPath("//img[@alt='Word Processing Document']")))
			log.warn("Verified the type icon is correct");
			
	}

	public void uploadDraft(String path, String desc) throws Exception {
		
		qfd_Library.getUpload().click();
		Thread.sleep(Wait.SHORT);
			
		qfd_Library.getUploadFile().sendKeys(path);
		Thread.sleep(Wait.SHORT);
			
		qfd_Library.getUploadDesc().sendKeys(desc);
		Thread.sleep(Wait.SHORT);
			
		asserter.elementClickWait(qfd_Library.getUploadSaveDraft(), asserter.new XPath("//a[text()='Change location']"), false);
		Thread.sleep(Wait.SHORT);	
		
		if(asserter.verifyExist("Successfully saved a private draft"))
			log.warn("Verified the upload file successfully saved as a draft.");	
			
	}
	
	public void createFolder(String folderName, String folderDesc) throws Exception {
		
		log.info("************************************");
		log.info("Starting 'Create Folder'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			goLibrary();
			
			newFolder();

			qfd_Library.getFolderName().sendKeys(folderName);
			Thread.sleep(Wait.SHORT);
			
			qfd_Library.getFolderDesc().sendKeys(folderDesc);
			Thread.sleep(Wait.SHORT);
			
			asserter.elementClickWait(qfd_Library.getOK(), asserter.new XPath("//a[text()='Change location']"), false);
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist("The folder was successfully created"))
				log.warn("Verified the text : 'The folder was successfully created' exists.");
				
			goLibrary();
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Create Folder'.");
		log.info("************************************");
		
	}

	public void draftUploadInFolder(String folderName, String uploadPath,
			String desc) throws Exception {

		goItem(folderName);
		
		uploadDraft(uploadPath, desc);
		
	}

	public void createListAndItem(String listName, String listDesc, String itemTitle) throws Exception{
		
		log.info("************************************");
		log.info("Starting 'Create List And Item'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			goLibrary();
			
			newList();

			if(BrowserSetup.BrowserType.equalsIgnoreCase("firefox")){
				
				qfd_Library.getFolderName().sendKeys(listName);
				Thread.sleep(Wait.SHORT);
				
				qfd_Library.getListDesc().sendKeys(listDesc);
				Thread.sleep(Wait.SHORT);
				
			}else if(BrowserSetup.BrowserType.equalsIgnoreCase("internetexplorer")){
				
				qfd_Library.getListName().sendKeys(listName);
				Thread.sleep(Wait.SHORT);
			
				qfd_Library.getListDescIE().sendKeys(listDesc);
				Thread.sleep(Wait.SHORT);
				
			}
			
			
			
			asserter.elementClickWait(qfd_Library.getSave(), asserter.new XPath("//a[text()='Change location']"), false);
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().defaultContent();
			
			if(asserter.verifyExist(listName))
				log.warn("Verified the list created successfully.");
				
			qfd_Library.getListAddItem().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().defaultContent();
			
			qfd_Library.getListItemTitle().sendKeys(itemTitle);
			Thread.sleep(Wait.SHORT);
			
			asserter.elementClickWait(qfd_Library.getOK(), asserter.new XPath("//a[text()='Subscribe to this list']"), true);
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(itemTitle))
				log.warn("Verified the list item added successfully.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Create List And Item'.");
		log.info("************************************");
		
	}

	public void createCusLib(String cusLibName, String desc, boolean isRoom) throws Exception {
		
		log.info("************************************");
		log.info("Starting 'Create Custom Library'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			if(!isRoom){
				
				goLibrary();
				
				newCusLib();
				
			}else{
				
				qfd_RoomTask.newCusLib();
				
			}

			qfd_Library.getFolderName().sendKeys(cusLibName);
			Thread.sleep(Wait.SHORT);
			
			qfd_Library.getFolderDesc().sendKeys(desc);
			Thread.sleep(Wait.SHORT);
			
			asserter.elementClickWait(qfd_Library.getSave(), asserter.new XPath("//a[text()='Change location']"), false);
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().defaultContent();
			
			if(asserter.verifyExist(cusLibName))
				log.warn("Verified the custom library created successfully.");
				
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Create Custom Library'.");
		log.info("************************************");
		
	}

	public void createDraft(String pageDraftName, String pageDesc, String attachPath,
			boolean isRoom) throws Exception {
	
		log.info("************************************");
		log.info("Starting 'Create Page Draft'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			if(!isRoom){
				
				goLibrary();
				
				newPage();
				
			}else{
				
				qfd_RoomTask.newPage();
				
			}

			qfd_Library.getPageName().sendKeys(pageDraftName);
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().frame(qfd_Library.getRichTextFrame());
			Thread.sleep(Wait.PAUSE);
			
			qfd_Library.getPageDesc().click();
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Library.getPageDesc().sendKeys(pageDesc);
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().defaultContent();
			
			qfd_Library.getPageAttach().click();
			Thread.sleep(Wait.SHORT);

			qfd_Library.getFileField().sendKeys(attachPath);
			Thread.sleep(Wait.SHORT);
			
			asserter.elementClickWait(qfd_Library.getPageSaveDraft(), asserter.new XPath("//a[text()='Subscribe to this folder']"), true);
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist("Successfully saved"))
				log.warn("Verified the text : Successfully saved a private draft of '" + pageDraftName + "' exists.");
					
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Create Page Draft'.");
		log.info("************************************");
		
	}

	public void createRichPage(String pageName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Create Page With Rich Text'...");
		Thread.sleep(Wait.SHORT);
		
		try{

			goLibrary();
			
			newPage();
			
			qfd_Library.getPageName().sendKeys(pageName);
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().frame(qfd_Library.getRichTextFrame());
			Thread.sleep(Wait.PAUSE);
			
			qfd_Library.getPageDesc().click();
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Library.getPageDesc().sendKeys("default sans serif, size 10");
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			qfd_Page.getFont().click();
			Thread.sleep(Wait.SHORT);
			
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ENTER).perform();
			Thread.sleep(Wait.SHORT);
			log.info("Clicking on Comic Sans MS item.");
			
			qfd_Page.getSize().click();
			Thread.sleep(Wait.SHORT);
			
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
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ENTER).perform();
			Thread.sleep(Wait.SHORT);
			log.info("Clicking on 16 item.");
			
			driver.switchTo().frame(qfd_Library.getRichTextFrame());
			Thread.sleep(Wait.PAUSE);
			
			action.sendKeys(Keys.ENTER).perform();
			Thread.sleep(Wait.SHORT);
			
			qfd_Library.getPageDesc().sendKeys("Comic Sans MS, size 16");
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			qfd_Page.getFont().click();
			Thread.sleep(Wait.SHORT);
			
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
			Thread.sleep(Wait.SHORT);
			log.info("Clicking on Times New Roman item.");
			
			qfd_Page.getSize().click();
			Thread.sleep(Wait.SHORT);
			
			action.sendKeys(Keys.ARROW_UP).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ARROW_UP).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ARROW_UP).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ARROW_UP).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ARROW_UP).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ENTER).perform();
			Thread.sleep(Wait.SHORT);
			log.info("Clicking on 9 item.");
			
			driver.switchTo().frame(qfd_Library.getRichTextFrame());
			Thread.sleep(Wait.PAUSE);
			
			action.sendKeys(Keys.ENTER).perform();
			Thread.sleep(Wait.SHORT);
			
			qfd_Library.getPageDesc().sendKeys("Times New Roman, size 9");
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			qfd_Page.getFont().click();
			Thread.sleep(Wait.SHORT);
			
			action.sendKeys(Keys.ARROW_UP).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ARROW_UP).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ARROW_UP).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ARROW_UP).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ARROW_UP).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ARROW_UP).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ENTER).perform();
			Thread.sleep(Wait.SHORT);
			log.info("Clicking on Arial item.");
			
			qfd_Page.getSize().click();
			Thread.sleep(Wait.SHORT);
			
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
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ENTER).perform();
			Thread.sleep(Wait.SHORT);
			log.info("Clicking on 20 item.");
			
			driver.switchTo().frame(qfd_Library.getRichTextFrame());
			Thread.sleep(Wait.PAUSE);
			
			action.sendKeys(Keys.ENTER).perform();
			Thread.sleep(Wait.SHORT);
			
			qfd_Library.getPageDesc().sendKeys("Arial, size 20");
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			qfd_Page.getFont().click();
			Thread.sleep(Wait.SHORT);
			
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ENTER).perform();
			Thread.sleep(Wait.SHORT);
			log.info("Clicking on Courier New item.");
			
			qfd_Page.getSize().click();
			Thread.sleep(Wait.SHORT);
			
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ENTER).perform();
			Thread.sleep(Wait.SHORT);
			log.info("Clicking on 24 item.");
			
			driver.switchTo().frame(qfd_Library.getRichTextFrame());
			Thread.sleep(Wait.PAUSE);
			
			action.sendKeys(Keys.ENTER).perform();
			Thread.sleep(Wait.SHORT);
			
			qfd_Library.getPageDesc().sendKeys("Courier, size 24");
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			asserter.elementClickWait(qfd_Library.getSaveCheckIn(), asserter.new XPath("//a[text()='Subscribe to this folder']"), true);
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//div[@class='lotusMessage lotusConfirm']")))
				log.warn("Verified the page " + pageName + " created successfully.");
			
			if(asserter.verifyEquals((driver.findElement(By.xpath("//div[@class='lotusMessage lotusConfirm']")).getText()), "Successfully saved '" + pageName + "'"));
				log.warn("Verified the text : Successfully saved '" + pageName + "' is correct.");
			
			if(asserter.verifyExist("Successfully saved"))
				log.warn("Verified the text : Successfully saved '" + pageName + "' exists.");
					
			goItem(pageName);
			
			driver.switchTo().frame(qfd_Page.getTextFrame());
			Thread.sleep(Wait.PAUSE);
			
			
			//June
//			Thread.sleep(Wait.PAUSE*2);
			
			if(asserter.verifyExist(asserter.new XPath("//p[contains(text(),'default sans serif, size 10')]")))
				log.warn("Verified the default line is correct.");

			//June
//			Thread.sleep(Wait.PAUSE*2);
			
			if(asserter.verifyExist(asserter.new XPath("//span[contains(@style,'comic')]/span[contains(@style,'16') and contains(text(),'Comic Sans MS, size 16')]")))
				log.warn("Verified the Comic line is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[contains(@style,'times')]/span[contains(@style,'9') and contains(text(),'Times New Roman, size 9')]")))
				log.warn("Verified the Times New Roman line is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[contains(@style,'arial')]/span[contains(@style,'20') and contains(text(),'Arial, size 20')]")))
				log.warn("Verified the Arial line is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[contains(@style,'courier')]/span[contains(@style,'24') and contains(text(),'Courier, size 24')]")))
				log.warn("Verified the Courier line is correct.");
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			goLibrary();
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Create Page With Rich Text'.");
		log.info("************************************");
		
	}

	public void createPageUploadImage(String pageName, String imgPath, String imgName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Upload Image'...");
		Thread.sleep(Wait.SHORT);
		
		try{
		
			goLibrary();
			
			newPage();
			
			qfd_Library.getPageName().sendKeys(pageName);
			Thread.sleep(Wait.SHORT);
				
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
			
			asserter.elementClickWait(qfd_Library.getSaveCheckIn(), asserter.new XPath("//a[text()='Change location']"), false);
			Thread.sleep(Wait.SHORT);	
			
			if(asserter.verifyExist("Successfully saved"))
				log.warn("Verified the image successfully uploaded.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Library']")))
				log.warn("Verified the Library view is displayed.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + pageName + "']")))
				log.warn("Verified the page is listed.");
			
			goItem(pageName);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='" + pageName + "']")))
				log.warn("Verified the page view is displayed.");
			
			driver.switchTo().frame(qfd_Page.getTextFrame());
			Thread.sleep(Wait.PAUSE);
			
			if(asserter.verifyExist(asserter.new XPath("//img[contains(@src,'.jpg')]")))
				log.warn("Verified the image is displayed.");
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			goLibrary();
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Upload Image'.");
		log.info("************************************");
		
	}

	public void verifyEditorIcons() throws Exception {
		
		log.info("************************************");
		log.info("Starting 'Verify CK Editor Icons'...");
		Thread.sleep(Wait.SHORT);
		
		try{
		
			goLibrary();
			
			newPage();
			
			String[] expected = {"Paste", "Paste as plain text", "Paste Special", "Paste Notes Document Link", "Print", "Undo", "Redo", "Find", "Replace", "Align Left",
								 "Align Center", "Align Right", "Align Justify", "Indent Paragraph", "Outdent", "Spellchecker", "Select All", "Insert Image", "Insert Table", "Insert Link",
					             "Insert Emoticons", "Font Name", "Font Size", "Text Color", "Background Color", "Bold", "Italic", "Underline", "Strike Through", "Numbers",
					             "Bullets", "Remove Formatting", "Subscript", "Superscript", "Insert Linebreak", "Special Character", "Block Quote", "Insert Anchor"};
			
			List<WebElement> l = new ArrayList<WebElement>();
			boolean result = true;
			
			l = driver.findElements(By.xpath("//span[contains(@class,'cke')]/a[@role='button']"));			
			
			if(asserter.verifyTrue(l.size() == 38))
				log.warn("Verified there are 38 buttons.");
			
			for(int i = 0; i < l.size(); i++){
				
				if(asserter.verifyEquals(l.get(i).getAttribute("title"), expected[i]))
					log.warn("Verified button " + (i + 1) + " is '" + expected[i] + "'");
				else result = false;
				Thread.sleep(Wait.VERYSHORT);
				
			}
			
			if(asserter.verifyTrue(result))
				log.warn("Verified all 38 buttons are correct.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify CK Editor Icons'.");
		log.info("************************************");
			
	}

	public void verifyAnonymous(String pageName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Verify Anonymous User In Library'...");
		Thread.sleep(Wait.MID);
			
		try{	
			
			goLibrary();
			
			goItem(pageName);
			
			if(asserter.verifyNotExist(asserter.new XPath("//a[text()='Check Out and Edit']")))
				log.warn("Verified the Check Out and Edit button does not exist.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[contains(text(),'More Actions')]")))
				log.warn("Verified the More Actions button exists.");
			
			qfd_Page.getMoreActions().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyNotExist(asserter.new XPath("//td[contains(text(),'Check Out')]")))
				log.warn("Verified the Check Out item does not exist.");
			
			if(asserter.verifyNotExist(asserter.new XPath("//td[contains(text(),'Copy To')]")))
				log.warn("Verified the Copy To item does not exist.");
			
			if(asserter.verifyNotExist(asserter.new XPath("//td[contains(text(),'Move To')]")))
				log.warn("Verified the Move To item does not exist.");
			
			if(asserter.verifyNotExist(asserter.new XPath("//td[contains(text(),'Move To Trash')]")))
				log.warn("Verified the Move To Trash item does not exist.");
			
			if(asserter.verifyNotExist(asserter.new XPath("//td[contains(text(),'Send Link')]")))
				log.warn("Verified the Send Link item does not exist.");
			
			if(asserter.verifyExist(asserter.new XPath("//td[contains(text(),'Print')]")))
				log.warn("Verified the Print item exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[contains(text(),'Download')]")))
				log.warn("Verified the Attachments tab is selected by default.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[contains(text(),'About')]")))
				log.warn("Verified the About tab exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[contains(text(),'Comments')]")))
				log.warn("Verified the Comments tab exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[contains(text(),'Versions')]")))
				log.warn("Verified the Versions tab exists.");
			
			qfd_Page.getComments().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyNotExist(asserter.new XPath("//a[text()='Add Comment']")))
				log.warn("Verified the Add Comment link does not exist.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Expand all comments']")))
				log.warn("Verified the Expand all comments link exists.");
			
			goLibrary();
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify Anonymous User In Library'.");
		log.info("************************************");
		
		
	}

	public void createSubFolder(String folderName, String subFolderName,
			String desc) throws Exception {
		
		log.info("************************************");
		log.info("Starting 'Create Sub Folder'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			goLibrary();
			
			goItem(folderName);
			
			newFolder();

			qfd_Library.getFolderName().sendKeys(subFolderName);
			Thread.sleep(Wait.SHORT);
			
			qfd_Library.getFolderDesc().sendKeys(desc);
			Thread.sleep(Wait.SHORT);
			
			asserter.elementClickWait(qfd_Library.getOK(), asserter.new XPath("//a[text()='Change location']"), false);
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist("The folder was successfully created"))
				log.warn("Verified the text : 'The folder was successfully created' exists.");
				
			goLibrary();
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Create Folder'.");
		log.info("************************************");
		
	}

	public void verifyBreadcrumbs(String folderName, String subFolderName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Verify Breadcrumbs'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			goLibrary();
			
			goItem(folderName);
			
			goItem(subFolderName);
			
			if((asserter.verifyExist(asserter.new XPath("//a[text()='" + folderName + "']"))) && (asserter.verifyExist(asserter.new XPath("//a[text()='Library']"))))
				log.warn("Verified the breadcrumb 'You are in' correctly displays.");
				
			qfd_Library.getBreadcrumbLibrary().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Library']")))
				log.warn("Verified user is brought back to the main library page.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify Breadcrumbs'.");
		log.info("************************************");
		
	}

	public void verifyBrowserHistory(String folderName, String subFolderName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Verify Browser History'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			goLibrary();
			
			goItem(folderName);
			
			goItem(subFolderName);
			
			driver.navigate().back();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='" + folderName + "']")))
				log.warn("Verified user is brought back to the folder level.");
			
			driver.navigate().forward();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='" + subFolderName + "']")))
				log.warn("Verified user is brought back to the subfolder level.");
			
			goLibrary();
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify Browser History'.");
		log.info("************************************");
		
	}

	public void createPageInFolder(String pageName, String desc,
			String attachPath, String folderName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Create Page In Folder'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			goItem(folderName);
			
			newPage();
			
			qfd_Library.getPageName().sendKeys(pageName);
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().frame(qfd_Library.getRichTextFrame());
			Thread.sleep(Wait.PAUSE);
			
			qfd_Library.getPageDesc().click();
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Library.getPageDesc().sendKeys(desc);
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().defaultContent();
			
			if(!attachPath.equals(null)){
				
				qfd_Library.getPageAttach().click();
				Thread.sleep(Wait.SHORT);

				qfd_Library.getFileField().sendKeys(attachPath);
				Thread.sleep(Wait.SHORT);
				
			}
			
			asserter.elementClickWait(qfd_Library.getSaveCheckIn(), asserter.new XPath("//a[text()='Subscribe to this folder']"), true);
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//div[@class='lotusMessage lotusConfirm']")))
				log.warn("Verified the page " + pageName + " created successfully.");
			
			if(asserter.verifyEquals((driver.findElement(By.xpath("//div[@class='lotusMessage lotusConfirm']")).getText()), "Successfully saved '" + pageName + "'"));
				log.warn("Verified the text : Successfully saved '" + pageName + "' is correct.");
			
			if(asserter.verifyExist("Successfully saved"))
				log.warn("Verified the text : Successfully saved '" + pageName + "' exists.");
			
			goLibrary();
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("Create Page In Folder'.");
		log.info("************************************");
		
	}

	public void verifyImport() throws Exception {

		log.info("************************************");
		log.info("Starting 'Verify Import'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			goLibrary();
			
			newImport();
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Imported File']")))
				log.warn("Verified the title is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//img[@alt='Close']")))
				log.warn("Verified 'X' is displayed on the top right corner");
			
			if(asserter.verifyExist(asserter.new XPath("//span[contains(text(),'Import file to this location')]")))
				log.warn("Verified 'Import file to this location' is displayed.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Change location']")))
				log.warn("Verified the 'Change location' link exists.");
			
			if(asserter.verifyTrue(driver.findElements(By.xpath("//span[@class='lotusFormRequired' and text()='*']")).size() == 2))
				log.warn("Verified the two red asterisks are correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//label[text()='File:']")))
				log.warn("Verified 'File:' exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//input[@name='attachment_0' and @type='file']")))
				log.warn("Verified there is an edit box and the word Browse.");
			
			if(asserter.verifyExist(asserter.new XPath("//label[text()='Name:']")))
				log.warn("Verified 'Name:' exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//input[@name='tempTitle' and @type='text']")))
				log.warn("Verified under Name there is an edit box.");
			
			if(asserter.verifyExist(asserter.new XPath("//label[text()='Description:']")))
				log.warn("Verified 'Description:' exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//textarea[@name='description']")))
				log.warn("Verified under Description there is an edit box.");
			
			if(asserter.verifyExist(asserter.new XPath("//h3[text()='Who Can Access This']")))
				log.warn("Verified the ACL Controls is available.");
			
			if(asserter.verifyExist(asserter.new XPath("//h3[text()='Customize']")))
				log.warn("Verified the Customize is available.");
			
			if(asserter.verifyExist(asserter.new XPath("//input[@title='Send Notification']")))
				log.warn("Verified the Send Notification checkbox exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//input[@value='Save and Check In' and @type='button']")))
				log.warn("Verified the Save and Check In button exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Save as Draft']")))
				log.warn("Verified the Save as Draft link exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Cancel']")))
				log.warn("Verified the Cancel link exists.");
			
			qfd_Library.getLinkCancel().click();
			Thread.sleep(Wait.SHORT);
			
			goLibrary();
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify Import'.");
		log.info("************************************");
		
	}

	public void createImport(String filePath, String desc) throws Exception {

		log.info("************************************");
		log.info("Starting 'Create Imported File'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			goLibrary();
			
			newImport();
			
			qfd_Library.getFileField().sendKeys(filePath);
			Thread.sleep(Wait.SHORT);
			
			qfd_Library.getFolderDesc().sendKeys(desc);
			Thread.sleep(Wait.SHORT);
				
			asserter.elementClickWait(qfd_Library.getSaveCheckIn(), asserter.new XPath("//a[text()='Subscribe to this folder']"), true);
			Thread.sleep(Wait.SHORT);
			
			goLibrary();
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Create Imported File'.");
		log.info("************************************");
		
	}

	public void verifyImportedFile(String fileName, String desc) throws Exception {

		log.info("************************************");
		log.info("Starting 'Verify Imported File'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			goLibrary();
			
			goItem(fileName);
			
			if(asserter.verifyExist(asserter.new XPath("//span[@title='" + fileName + "']")))
				log.warn("Verified the title is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[@title='" + desc + "']")))
				log.warn("Verified the description is correct.");
				
			if(asserter.verifyExist(asserter.new XPath("//li[contains(@id,'preview') and @class='lotusSelected']")))
				log.warn("Verified the Preview tab is selected.");
			
			qfd_Page.getMoreActions().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Library.getEditProperty().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyEquals(fileName, driver.findElement(By.xpath("//input[@name='tempTitle']")).getAttribute("value")))
				log.warn("Verified that the file gets added and the name field is autopopulated less the filename extension.");
			
			if(asserter.verifyEquals(desc, qfd_Library.getFolderDesc().getText()))
				log.warn("Verified user can type in a description.");
			
			asserter.elementClickWait(qfd_Library.getSaveCheckIn(), asserter.new XPath("//a[text()='Subscribe to this document']"), true);
			Thread.sleep(Wait.SHORT);
			
			goLibrary();
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify Imported File'.");
		log.info("************************************");
		
	}

	public void verifyDuplicate(String pageName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Verify Duplicate Pages'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			goLibrary();
			
			if(asserter.verifyTrue(driver.findElements(By.xpath("//h4/a[@title='" + pageName + "']")).size() == 2))
				log.warn("Verified duplicate items created.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify Duplicate Pages'.");
		log.info("************************************");
		
	}

	public void subscribe(String pageName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Subscribe Library'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			goLibrary();
			
			qfd_Library.getSubscribe().click();
			Thread.sleep(Wait.SHORT);
			
			String handles[] = new String[5];
			driver.getWindowHandles().toArray(handles);
			
			qfd_Place.getMyPlaces().click();
			Thread.sleep(Wait.MID);
			
			driver.switchTo().window(handles[1]);
			Thread.sleep(Wait.PAUSE);
			
			if(BrowserSetup.BrowserType.equalsIgnoreCase("firefox")){
				
				if(AT.checkForDialog("Library", Wait.VERYSHORT)){
					
					AT.setFocusDoKeys("Library", "{TAB}");
					AT.setFocusDoKeys("Library", "{TAB}");			
					AT.setFocusDoKeys("Library", "{TAB}");			
					AT.setFocusDoKeys("Library", "{ENTER}");
				
				}
				
				if(AT.checkForDialog("Subscribe", Wait.VERYSHORT)){
					
					AT.setFocusDoKeys("Live Bookmark", Users.PlaceName);
					AT.setFocusDoKeys("Live Bookmark", "{ENTER}");
					
				}
				
			}else if(BrowserSetup.BrowserType.equalsIgnoreCase("internetexplorer")){
				
				driver.manage().window().maximize();
				
				if(AT.checkForDialog("Library", Wait.VERYSHORT)){
					
					AT.setFocusDoKeys("Library", "{TAB}");
					AT.setFocusDoKeys("Library", "{TAB}");
					AT.setFocusDoKeys("Library", "{TAB}");			
					AT.setFocusDoKeys("Library", "{TAB}");	
					AT.setFocusDoKeys("Library", "{TAB}");
					AT.setFocusDoKeys("Library", "{TAB}");			
					AT.setFocusDoKeys("Library", "{TAB}");			
					AT.setFocusDoKeys("Library", "{ENTER}");
				
				}
				
				if(AT.checkForDialog("Subscribe", Wait.VERYSHORT)){
					
					AT.setFocusDoKeys("Subscribe", Users.PlaceName);
					AT.setFocusDoKeys("Subscribe", "{ENTER}");
					
				}
				
			}
				
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Subscribe Library'.");
		log.info("************************************");
		
	}

	public void verifyLibraryFeed(String pageName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Verify Library Feed'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			if(BrowserSetup.BrowserType.equalsIgnoreCase("firefox")){
				
				AT.setFocusDoKeys("Library", "^b");
				
				AT.setFocusDoKeys("Library", Users.PlaceName);
				
				AT.setFocusDoKeys("Library", "{TAB}");
				AT.setFocusDoKeys("Library", "{UP}");
				AT.setFocusDoKeys("Library", "{ENTER}");
				Thread.sleep(Wait.MID);
				
				if(asserter.verifyExist(asserter.new XPath("//span[text()='" + pageName + "']")))
					log.warn("Verified the feed brings user to the correct page.");
				
				AT.setFocusDoKeys("Lotus Quickr", "^b");
				
			}else if(BrowserSetup.BrowserType.equalsIgnoreCase("internetexplorer")){
				
				AT.setFocusDoKeys("Library", "^i");
				
				AT.setFocusDoKeys("Library", "{TAB}");
				AT.setFocusDoKeys("Library", "{TAB}");
				AT.setFocusDoKeys("Library", "{TAB}");
				AT.setFocusDoKeys("Library", "{TAB}");
				AT.setFocusDoKeys("Library", "{RIGHT}");
				AT.setFocusDoKeys("Library", "{ENTER}");
				AT.setFocusDoKeys("Library", "{ENTER}");
				
				driver.navigate().refresh();

				AT.setFocusDoKeys("Library", "{TAB}");
				AT.setFocusDoKeys("Library", "{TAB}");
				AT.setFocusDoKeys("Library", "{TAB}");
				AT.setFocusDoKeys("Library", "{TAB}");
				AT.setFocusDoKeys("Library", "{TAB}");
				AT.setFocusDoKeys("Library", "{TAB}");
				AT.setFocusDoKeys("Library", "{ENTER}");
//				Thread.sleep(Wait.MID);

				Thread.sleep(Wait.LONG);//Wu Jun
				if(asserter.verifyExist(asserter.new XPath("//span[text()='" + pageName + "']")))
					log.warn("Verified the feed brings user to the correct page.");
			}
			
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify Library Feed'.");
		log.info("************************************");
		
	}

	public void verifyLibraryFeedFromLibrary(String pageName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Verify Library Feed'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			if(BrowserSetup.BrowserType.equalsIgnoreCase("firefox")){
				
				AT.setFocusDoKeys("Lotus Quickr", "^b");
				
				AT.setFocusDoKeys("Lotus Quickr", Users.PlaceName);
				
				AT.setFocusDoKeys("Lotus Quickr", "{TAB}");
				AT.setFocusDoKeys("Lotus Quickr", "{UP}");
				AT.setFocusDoKeys("Lotus Quickr", "{ENTER}");
				Thread.sleep(Wait.MID);
				
				AT.setFocusDoKeys("Lotus Quickr", "^b");
				
				goLibrary();
				
				if(asserter.verifyExist(asserter.new XPath("//a[text()='" + pageName + "']")))
					log.warn("Verified the feed is updated.");
				
			}else if(BrowserSetup.BrowserType.equalsIgnoreCase("internetexplorer")){
				
				AT.setFocusDoKeys("Lotus Quickr", "^i");
				
				AT.setFocusDoKeys("Lotus Quickr", "{TAB}");
				AT.setFocusDoKeys("Lotus Quickr", "{TAB}");
				AT.setFocusDoKeys("Lotus Quickr", "{TAB}");
				AT.setFocusDoKeys("Lotus Quickr", "{TAB}");
				AT.setFocusDoKeys("Lotus Quickr", "{RIGHT}");
				AT.setFocusDoKeys("Lotus Quickr", "{ENTER}");
				AT.setFocusDoKeys("Lotus Quickr", "{ENTER}");
				
				driver.navigate().refresh();

				AT.setFocusDoKeys("Library", "{TAB}");
				AT.setFocusDoKeys("Library", "{TAB}");
				AT.setFocusDoKeys("Library", "{TAB}");
				AT.setFocusDoKeys("Library", "{TAB}");
				AT.setFocusDoKeys("Library", "{TAB}");
				AT.setFocusDoKeys("Library", "{TAB}");
				AT.setFocusDoKeys("Library", "{TAB}");
				AT.setFocusDoKeys("Library", "{ENTER}");
				Thread.sleep(Wait.MID);
				
				if(asserter.verifyExist(asserter.new XPath("//span[text()='" + pageName + "']")))
					log.warn("Verified the feed brings user to the correct page.");
			}
			
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify Library Feed'.");
		log.info("************************************");
		
	}

	public void createUpload(String fileName, String filePath, String desc) throws Exception {

		log.info("************************************");
		log.info("Starting 'Create Upload'...");
		Thread.sleep(Wait.SHORT);
		
		try{

			goLibrary();
			
			qfd_Library.getUpload().click();
			Thread.sleep(Wait.SHORT);
				
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Upload File']")))
				log.warn("Verified Upload Files dialog displays.");
			
			if(asserter.verifyExist(asserter.new XPath("//strong/span[text()='Library']")))
				log.warn("Verified the default location is Library.");
			
			qfd_Library.getUploadFile().sendKeys(filePath);
			Thread.sleep(Wait.SHORT);
				
			qfd_Library.getUploadDesc().sendKeys(desc);
			Thread.sleep(Wait.SHORT);
				
			asserter.elementClickWait(qfd_Library.getSaveCheckIn(), asserter.new XPath("//a[text()='Change location']"), false);
			Thread.sleep(Wait.SHORT);	
			
			if(asserter.verifyExist(asserter.new XPath("//img[@alt='Word Processing Document']")))
				log.warn("Verified the type icon is correct");
			
			if(asserter.verifyExist("Successfully saved"))
				log.warn("Verified the in-line message appears.");
					
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Create Upload'.");
		log.info("************************************");
		
	}

	public void verifyForm(String formName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Verify Form'...");
		Thread.sleep(Wait.SHORT);
		
		try{

			goLibrary();
			
			asserter.elementClick(qfd_Library.getNew(), asserter.new XPath("//td[text()='Link']"), true);
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//td[text()='" + formName + "']")))
				log.warn("Verified the Form is in the list.");
			
			goLibrary();
					
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify Form'.");
		log.info("************************************");
		
	}

	public void createFormPage(String pageName, String desc, String attachPath, String formName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Create Page With Form'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			goLibrary();
			
			asserter.elementClick(qfd_Library.getNew(), asserter.new XPath("//td[text()='Link']"), true);
			Thread.sleep(Wait.SHORT);
			
			new Element(driver.findElement(By.xpath("//td[text()='" + formName + "']")), formName + " item").click();
			Thread.sleep(Wait.SHORT);

			qfd_Library.getPageName().sendKeys(pageName);
			Thread.sleep(Wait.SHORT);
			
			new Element(driver.findElement(By.xpath("//textarea[contains(@name,'UAT')]")), "description textbox").click();
			Thread.sleep(Wait.SHORT);
			
			new Element(driver.findElement(By.xpath("//textarea[contains(@name,'UAT')]")), "description textbox").sendKeys(desc);
			Thread.sleep(Wait.SHORT);
				
			qfd_Library.getPageAttach().click();
			Thread.sleep(Wait.SHORT);

			qfd_Library.getFileField().sendKeys(attachPath);
			Thread.sleep(Wait.SHORT);
			
			asserter.elementClickWait(qfd_Library.getSaveCheckIn(), asserter.new XPath("//a[text()='Subscribe to this folder']"), true);
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//div[@class='lotusMessage lotusConfirm']")))
				log.warn("Verified the page " + pageName + " created successfully.");
			
			if(asserter.verifyEquals((driver.findElement(By.xpath("//div[@class='lotusMessage lotusConfirm']")).getText()), "Successfully saved '" + pageName + "'"));
				log.warn("Verified the text : Successfully saved '" + pageName + "' is correct.");
			
			if(asserter.verifyExist("Successfully saved"))
				log.warn("Verified the text : Successfully saved '" + pageName + "' exists.");
					
			goItem(pageName);
			
			if(asserter.verifyExist(asserter.new XPath("//label[contains(text(),'UAT')]")))
				log.warn("Verified the customized textarea is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//ul[contains(text(),'" + desc + "')]")))
				log.warn("Verified the text is correct.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Create Page With Form'.");
		log.info("************************************");
		
	}
	
}
