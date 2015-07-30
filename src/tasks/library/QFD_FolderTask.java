package tasks.library;

import junit.framework.AssertionFailedError;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import config.Element;
import config.Wait;
import exceptions.AssertFailException;
import exceptions.ElementException;

import tasks.common.QFD_PlaceTask;
import tasks.toc.QFD_LibraryTask;
import tasks.toc.QFD_TrashTask;
import util.Assert;
import util.Assert.XPath;
import appobjects.common.QFD_Place;
import appobjects.library.QFD_Folder;
import appobjects.library.QFD_Page;
import appobjects.toc.QFD_Home;
import appobjects.toc.QFD_Library;

public class QFD_FolderTask {

	private WebDriver driver;
	private Logger log;
	private Assert asserter;
	private Actions action = null;
	QFD_Place qfd_Place = null;
	QFD_Library qfd_Library = null;
	QFD_Folder qfd_Folder = null;
	QFD_LibraryTask qfd_LibraryTask = null;
	QFD_PlaceTask qfd_PlaceTask = null;
	QFD_TrashTask qfd_TrashTask = null;
	QFD_Page qfd_Page = null;
	QFD_Home qfd_Home = null;
	
	public QFD_FolderTask(WebDriver driver, Logger log){
		
		this.driver = driver;
		this.log = log;
		this.asserter = new Assert(driver, log);
		action = new Actions(driver);
		qfd_Place = new QFD_Place(driver);
		qfd_Folder = new QFD_Folder(driver);
		qfd_LibraryTask = new QFD_LibraryTask(driver, log);
		qfd_PlaceTask = new QFD_PlaceTask(driver, log);
		qfd_TrashTask = new QFD_TrashTask(driver, log);
		qfd_Library = new QFD_Library(driver);
		qfd_Page = new QFD_Page(driver);
		qfd_Home = new QFD_Home(driver);
		
	}

	public void verifySimpleFolder(String folderName, String desc, String creatorName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Verify Simple Folder'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			qfd_LibraryTask.goLibrary();
			
			qfd_LibraryTask.newFolder();
			
			qfd_Library.getFolderName().sendKeys(folderName);
			Thread.sleep(Wait.SHORT);
			
			qfd_Library.getFolderDesc().sendKeys(desc);
			Thread.sleep(Wait.SHORT);
			
			asserter.elementClickWait(qfd_Library.getOK(), asserter.new XPath("//a[text()='Change location']"), false);
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist("The folder was successfully created"))
				log.warn("Verified the text : 'The folder was successfully created' exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='" + folderName + "']")))
				log.warn("Verified user is in the newly created folder.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Library']")))
				log.warn("Verified the current location in breadcrumb is correct.");
			
			qfd_LibraryTask.goLibrary();
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + folderName + "']")))
				log.warn("Verified the folder is listed in Library.");
			
			qfd_LibraryTask.goItem(folderName);
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Upload']")))
				log.warn("Verified the Upload button exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[contains(text(),'New')]")))
				log.warn("Verified the New button exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[contains(text(),'More Actions')]")))
				log.warn("Verified the More Actions button exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='" + desc + "']")))
				log.warn("Verified the description is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + creatorName + "']")))
				log.warn("Verified Create by is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//div[contains(text(),'Public')]")))
				log.warn("Verified Folder status is Public.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify Simple Folder'.");
		log.info("************************************");
		
	}

	public void verifyUploadAndSubfolder(String subfolderName, String desc,
			String uploadPath, String uploadName, String folderName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Verify Upload And Simple Subfolder'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			qfd_Library.getUpload().click();
			Thread.sleep(Wait.SHORT);
				
			qfd_Library.getUploadFile().sendKeys(uploadPath);
			Thread.sleep(Wait.SHORT);
				
			qfd_Library.getUploadDesc().sendKeys(desc);
			Thread.sleep(Wait.SHORT);
				
			asserter.elementClickWait(qfd_Library.getSaveCheckIn(), asserter.new XPath("//a[text()='Change location']"), false);
			Thread.sleep(Wait.SHORT);	
			
			if(asserter.verifyExist("Successfully saved"))
				log.warn("Verified the file uploaded successfully.");
			
			qfd_LibraryTask.newFolder();
			
			qfd_Library.getFolderName().sendKeys(subfolderName);
			Thread.sleep(Wait.SHORT);
			
			qfd_Library.getFolderDesc().sendKeys(desc);
			Thread.sleep(Wait.SHORT);
			
			asserter.elementClickWait(qfd_Library.getOK(), asserter.new XPath("//a[text()='Change location']"), false);
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist("The folder was successfully created"))
				log.warn("Verified the text : 'The folder was successfully created' exists.");
			
			qfd_LibraryTask.goLibrary();
			
			qfd_LibraryTask.goItem(folderName);
			
			if((asserter.verifyExist(asserter.new XPath("//a[text()='" + subfolderName + "']"))) && (asserter.verifyExist(asserter.new XPath("//a[text()='" + uploadName + "']"))))
				log.warn("Verified Upload and Folder show as a list in the folder.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify Upload And Simple Subfolder'.");
		log.info("************************************");
		
	}

	public void verifyTabbedFolder(String folderName, String desc, String creatorName) throws Exception {
		
		log.info("************************************");
		log.info("Starting 'Verify Tabbed Folder'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			qfd_LibraryTask.goLibrary();
			
			qfd_PlaceTask.placeNewFolder();
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='New Folder']")))
				log.warn("Verified New Folder dialog displays.");
			
			qfd_Library.getFolderName().sendKeys(folderName);
			Thread.sleep(Wait.SHORT);
			
			qfd_Library.getFolderDesc().sendKeys(desc);
			Thread.sleep(Wait.SHORT);
			
			qfd_Home.getCustomize().click();
			Thread.sleep(Wait.SHORT);
			
			asserter.checkboxClick(asserter.new XPath("//input[@alt='Tabbed']"), true);
			
			asserter.elementClickWait(qfd_Library.getOK(), asserter.new XPath("//a[text()='Change location']"), false);
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist("The folder was successfully created"))
				log.warn("Verified the text : 'The folder was successfully created' exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='" + folderName + "']")))
				log.warn("Verified user is in the newly created folder.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Library']")))
				log.warn("Verified the current location in breadcrumb is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[contains(@title,'click to expand section')]")))
				log.warn("Verified there is a collapse icon.");
			
			qfd_Folder.getTabbedCollapse().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist("today"))
				log.warn("Verified the date is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + creatorName + "']")))
				log.warn("Verified the creator's name is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//div[contains(text(),'Public')]")))
				log.warn("Verified Folder status is Public.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='" + desc + "']")))
				log.warn("Verified the description is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Upload']")))
				log.warn("Verified the Upload button exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[contains(text(),'New')]")))
				log.warn("Verified the New button exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[contains(text(),'Folder Actions')]")))
				log.warn("Verified the Folder Actions button exists.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify Tabbed Folder'.");
		log.info("************************************");
		
	}

	public void verifyTabbedUploadAndPage(String pageName, String desc,
			String uploadPath, String uploadName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Verify Upload And Page In A Tabbed Folder'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			qfd_PlaceTask.placeNewPage();
			
			qfd_Library.getPageName().sendKeys(pageName);
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().frame(qfd_Library.getRichTextFrame());
			Thread.sleep(Wait.PAUSE);
			
			qfd_Library.getPageDesc().click();
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Library.getPageDesc().sendKeys(desc);
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			asserter.elementClickWait(qfd_Library.getSaveCheckIn(), asserter.new XPath("//a[text()='Subscribe to this document']"), true);
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist("Successfully saved"))
				log.warn("Verified the text : Successfully saved '" + pageName + "' exists.");
			
			qfd_PlaceTask.placeNewUpload();
			
			qfd_Library.getUploadFile().sendKeys(uploadPath);
			Thread.sleep(Wait.SHORT);
				
			qfd_Library.getUploadDesc().sendKeys(desc);
			Thread.sleep(Wait.SHORT);
				
			asserter.elementClickWait(qfd_Library.getSaveCheckIn(), asserter.new XPath("//a[text()='Change location']"), false);
			Thread.sleep(Wait.SHORT);	
			
			if(asserter.verifyExist("Successfully saved"))
				log.warn("Verified the file uploaded successfully.");
			
			driver.navigate().refresh();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + uploadName + "']")))
				log.warn("Verified Upload appear as Tabs in the folder.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + pageName + "']")))
				log.warn("Verified Page appear as Tabs in the folder.");
			
			new Element(driver.findElement(By.xpath("//a[text()='" + pageName + "']")), "Page tab").click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='" + pageName + "']")))
				log.warn("Verified user can navigate through the tabs.");
			
			new Element(driver.findElement(By.xpath("//a[text()='" + uploadName + "']")), "Upload tab").click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='" + uploadName + "']")))
				log.warn("Verified clicking on the Upload tab shows the Upload page.");
			
			new Element(driver.findElement(By.xpath("//a[text()='" + pageName + "']")), "Page tab").click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='" + pageName + "']")))
				log.warn("Verified clicking on the Page tab shows the Page.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify Upload And Page In A Tabbed Folder'.");
		log.info("************************************");
		
	}

	public void verifySlideFolder(String folderName, String desc, String creatorName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Verify Slide Show Folder'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			qfd_LibraryTask.goLibrary();
			
			qfd_PlaceTask.placeNewFolder();
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='New Folder']")))
				log.warn("Verified New Folder dialog displays.");
			
			qfd_Library.getFolderName().sendKeys(folderName);
			Thread.sleep(Wait.SHORT);
			
			qfd_Library.getFolderDesc().sendKeys(desc);
			Thread.sleep(Wait.SHORT);
			
			qfd_Home.getCustomize().click();
			Thread.sleep(Wait.SHORT);
			
			asserter.checkboxClick(asserter.new XPath("//input[@alt='Slide Show']"), true);
			
			asserter.elementClickWait(qfd_Library.getOK(), asserter.new XPath("//a[text()='Change location']"), false);
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist("The folder was successfully created"))
				log.warn("Verified the text : 'The folder was successfully created' exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='" + folderName + "']")))
				log.warn("Verified user is in the newly created folder.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Library']")))
				log.warn("Verified the current location in breadcrumb is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[contains(@title,'click to expand section')]")))
				log.warn("Verified there is a collapse icon.");
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			new Element(driver.findElement(By.xpath("//span[text()='Slide Show']")), "Collapse icon").click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist("today"))
				log.warn("Verified the date is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + creatorName + "']")))
				log.warn("Verified the creator's name is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//div[contains(text(),'Public')]")))
				log.warn("Verified Folder status is Public.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='" + desc + "']")))
				log.warn("Verified the description is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Upload']")))
				log.warn("Verified the Upload button exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[contains(text(),'New')]")))
				log.warn("Verified the New button exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[contains(text(),'Folder Actions')]")))
				log.warn("Verified the Folder Actions button exists.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify Slide Show Folder'.");
		log.info("************************************");
		
	}

	public void verifySlideSubfolderAndLink(String subfolderName, String desc,
			String linkName, String url, String folderName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Verify Slide Show Subfolder And Link'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			qfd_PlaceTask.placeNewFolder();
			
			qfd_Library.getFolderName().sendKeys(subfolderName);
			Thread.sleep(Wait.SHORT);
			
			qfd_Library.getFolderDesc().sendKeys(desc);
			Thread.sleep(Wait.SHORT);
			
			qfd_Home.getCustomize().click();
			Thread.sleep(Wait.SHORT);
			
			asserter.checkboxClick(asserter.new XPath("//input[@alt='Slide Show']"), true);
			
			asserter.elementClickWait(qfd_Library.getOK(), asserter.new XPath("//a[text()='Change location']"), false);
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist("The folder was successfully created"))
				log.warn("Verified the text : 'The folder was successfully created' exists.");
			
			qfd_LibraryTask.goLibrary();
			
			qfd_LibraryTask.goItem(folderName);
			
			qfd_PlaceTask.placeNewLink();
			
			qfd_Library.getLinkPageName().sendKeys(linkName);
			Thread.sleep(Wait.SHORT);
			
			qfd_Library.getLinkURLText().clear();
			Thread.sleep(Wait.VERYSHORT);
			qfd_Library.getLinkURLText().sendKeys(url);
			Thread.sleep(Wait.SHORT);
			
			qfd_Library.getLinkNewWindow().click();
			Thread.sleep(Wait.PAUSE);
			
			action.sendKeys(Keys.DOWN).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ENTER).perform();
			Thread.sleep(Wait.SHORT);
			
			asserter.elementClickWait(qfd_Library.getSaveCheckIn(), asserter.new XPath("//a[text()='Previous']"), true);
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist("Successfully saved"))
				log.warn("Verified the text : Successfully saved '" + linkName + "' exists.");	
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Previous']")))
				log.warn("Verified subfolder and link appears in the folder as an Slide Show.");	
			
			qfd_Folder.getPrevious().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist("Folder item 1 of 2"))
				log.warn("Verified Folder item shows Folder item 1 of 2.");	
			
			if(asserter.verifyExist("Folders can not be displayed inside this style of folder"))
				log.warn("Verified user can navigate through the items by clicking Previous link.");	
			
			qfd_Folder.getNext().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist("Folder item 2 of 2"))
				log.warn("Verified the link item shows Folder item 2 of 2.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='" + linkName + "']")))
				log.warn("Verified user can navigate through the items by clicking Next link.");	
			
			qfd_Folder.getPrevious().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='here']")))
				log.warn("Verified the folder item shows message that contains the link 'here'.");	
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify Slide Show Subfolder And Link'.");
		log.info("************************************");
		
	}

	public void propertiesTest(String folderName, String desc) throws Exception {

		log.info("************************************");
		log.info("Starting 'Properties Tests On Simple Folder'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			qfd_LibraryTask.goLibrary();
			
			qfd_LibraryTask.goItem(folderName);
			
			qfd_Page.getMoreActions().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Folder.getThisFolderItem().click();
			Thread.sleep(Wait.SHORT);
			
			action.sendKeys(Keys.ENTER).perform();
			log.info("Clicking on Edit Folder Properties item.");
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().frame(qfd_Folder.getPropertiesFrame());
			Thread.sleep(Wait.PAUSE);
			
			qfd_Folder.getFolderTitle().sendKeys(folderName + " Updated");
			Thread.sleep(Wait.SHORT);
			
			qfd_Folder.getFolderDesc().click();
			Thread.sleep(Wait.VERYSHORT);
			qfd_Folder.getFolderDesc().clear();
			Thread.sleep(Wait.VERYSHORT);
			qfd_Folder.getFolderDesc().sendKeys(desc + " Updated");
			Thread.sleep(Wait.SHORT);
			
			asserter.checkboxClick(asserter.new XPath("//input[@title='Change order']"), true);
			
			action.sendKeys(Keys.HOME).perform();
			Thread.sleep(Wait.SHORT);
			
			qfd_Folder.getNextButton().click();
			Thread.sleep(Wait.MID);
			
			qfd_Folder.getDownloadOption().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Folder.getUpArrow().click();
			Thread.sleep(Wait.PAUSE);
			qfd_Folder.getUpArrow().click();
			Thread.sleep(Wait.PAUSE);
			qfd_Folder.getUpArrow().click();
			Thread.sleep(Wait.PAUSE);
			
			qfd_Folder.getNextButton().click();
			Thread.sleep(Wait.MID);
			
			asserter.elementClickWait(qfd_Folder.getNextButton(), asserter.new XPath("//span[text()='" + folderName + " Updated']"), true);
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='" + folderName + " Updated']")))
				log.warn("Verified the folder name changed.");	
			
			if(asserter.verifyExist(asserter.new XPath("//a[@title='Click for attachments']")))
				log.warn("Verified the download column is first.");	
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Properties Tests On Simple Folder'.");
		log.info("************************************");
		
	}

	public void verifyThashRestore(String folderName) throws Exception {
			
		log.info("************************************");
		log.info("Starting 'Verify Thash And Restore Slide Show Folder'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			qfd_LibraryTask.goLibrary();
			
			qfd_LibraryTask.goItem(folderName);
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			new Element(driver.findElement(By.xpath("//span[text()='Slide Show']")), "Collapse icon").click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Folder.getFolderAcions().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Folder.getMoveFolderTrash().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().alert().accept();
			log.info("Clicking on OK button.");
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist("successfully moved to"))
				log.warn("Verified the moved to trash message exists.");	
			
			if(asserter.verifyNotExist(asserter.new XPath("//a[text()='" + folderName + "']")))
				log.warn("Verified the folder is no longer in the Library View.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		qfd_TrashTask.verifyTrashItem(folderName);
		
		qfd_TrashTask.restoreOneItem(folderName);
		
		try{
			
			qfd_LibraryTask.goLibrary();
			
			if(asserter.verifyExist("restored to"))                    
				log.warn("Verified the restored message exists.");
			
			if(asserter.verifyExist(folderName))
				log.warn("Verified the folder is back in the Library View.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify Thash And Restore Slide Show Folder'.");
		log.info("************************************");
		
	}

	public void createNavigatorFolder(String folderName, String desc) throws Exception {
		
		log.info("************************************");
		log.info("Starting 'Create Folder On Navigator'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			qfd_PlaceTask.placeNewFolder();
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='New Folder']")))
				log.warn("Verified New Folder dialog displays.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Library']")))
				log.warn("Verified default location is Library.");
			
			qfd_Folder.getChangeLocation().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Save to']")))
				log.warn("Verified Save to dialog displays.");
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//input[@value='folders']")).isSelected() == true))
				log.warn("Verified Folders option is selected as default.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Library']")))
				log.warn("Verified user can only see Library folder in the list.");
			
			asserter.checkboxClick(asserter.new XPath("//input[@value='navigator']"), true);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='The item will display here']")))
				log.warn("Verified the default position for new item is at the very end.");
			
			asserter.elementClickWait(new Element(driver.findElement(By.xpath("//input[@value='OK' and contains(@id,'quickr_widgets_modal')]")), "OK button"), asserter.new XPath("//span[text()='Save to']"), false);
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyNotExist(asserter.new XPath("//span[text()='Save to']")))
				log.warn("Verified Save to dialog closed.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Navigator']")))
				log.warn("Verified the location is updated to Navigator.");
			
			qfd_Library.getFolderName().sendKeys(folderName);
			Thread.sleep(Wait.SHORT);
			
			qfd_Library.getFolderDesc().sendKeys(desc);
			Thread.sleep(Wait.SHORT);
			
			asserter.elementClickWait(qfd_Library.getOK(), asserter.new XPath("//a[text()='Change location']"), false);
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist("The folder was successfully created"))
				log.warn("Verified the text : 'The folder was successfully created' exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='" + folderName + "']")))
				log.warn("Verified user is in the newly created folder.");
			
			if(asserter.verifyNotExist(asserter.new XPath("//a[text()='Library']")))
				log.warn("Verified the current location in breadcrumb is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[contains(text(),'" + folderName + "')]")))
				log.warn("Verified the folder is on the Navigator at the very end.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Create Folder On Navigator'.");
		log.info("************************************");
		
	}

	public void copyToDifferentPosition(String folderName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Copy A Folder To Defferent Position On Navigator'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			qfd_Page.getMoreActions().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Folder.getThisFolderItem().click();
			Thread.sleep(Wait.SHORT);
			
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ENTER).perform();
			log.info("Clicking on Copy Folder To item.");
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Copy to']")))
				log.warn("Verified Copy to dialog displays.");
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//input[@value='folders']")).isSelected() == true))
				log.warn("Verified Inside a Folder option is selected as default.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='" + folderName + "']")))
				log.warn("Verified the default location is " + folderName + " .");
			
			asserter.checkboxClick(asserter.new XPath("//input[@value='navigator']"), true);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='The item will display here']")))
				log.warn("Verified the default position for new item is at the very end.");
			
			qfd_Folder.getUp().click();
			Thread.sleep(Wait.PAUSE);
			qfd_Folder.getUp().click();
			Thread.sleep(Wait.PAUSE);
			qfd_Folder.getUp().click();
			Thread.sleep(Wait.PAUSE);
			qfd_Folder.getUp().click();
			Thread.sleep(Wait.PAUSE);
			qfd_Folder.getUp().click();
			Thread.sleep(Wait.PAUSE);
			qfd_Folder.getUp().click();
			Thread.sleep(Wait.PAUSE);
			qfd_Folder.getUp().click();
			Thread.sleep(Wait.PAUSE);
			qfd_Folder.getUp().click();
			Thread.sleep(Wait.PAUSE);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='The item will display here']")))
				log.warn("Verified the position for new item is right below Home.");
			
			asserter.elementClickWait(qfd_Library.getOK(), asserter.new XPath("//span[text()='Copy to']"), false);
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyNotExist(asserter.new XPath("//span[text()='Copy to']")))
				log.warn("Verified Copy to dialog closed.");
			
			if(asserter.verifyExist("An item with the same name already exists"))
				log.warn("Verified the error message appears.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Copy A Folder To Defferent Position On Navigator'.");
		log.info("************************************");
		
	}

	public void moveToDifferentPosition(String folderName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Move A Folder To Defferent Position On Navigator'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			qfd_Page.getMoreActions().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Folder.getThisFolderItem().click();
			Thread.sleep(Wait.SHORT);
			
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ENTER).perform();
			log.info("Clicking on Move Folder To item.");
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Move to']")))
				log.warn("Verified Move to dialog displays.");
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//input[@value='folders']")).isSelected() == true))
				log.warn("Verified Inside a Folder option is selected as default.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='" + folderName + "']")))
				log.warn("Verified the default location is " + folderName + " .");
			
			asserter.checkboxClick(asserter.new XPath("//input[@value='navigator']"), true);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='The item will display here']")))
				log.warn("Verified the default position for new item is at the very end.");
			
			qfd_Folder.getUp().click();
			Thread.sleep(Wait.PAUSE);
			qfd_Folder.getUp().click();
			Thread.sleep(Wait.PAUSE);
			qfd_Folder.getUp().click();
			Thread.sleep(Wait.PAUSE);
			qfd_Folder.getUp().click();
			Thread.sleep(Wait.PAUSE);
			qfd_Folder.getUp().click();
			Thread.sleep(Wait.PAUSE);
			qfd_Folder.getUp().click();
			Thread.sleep(Wait.PAUSE);
			qfd_Folder.getUp().click();
			Thread.sleep(Wait.PAUSE);
			qfd_Folder.getUp().click();
			Thread.sleep(Wait.PAUSE);
			qfd_Folder.getUp().click();
			Thread.sleep(Wait.PAUSE);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='The item will display here']")))
				log.warn("Verified the position for new item is above Home.");
			
			asserter.elementClickWait(qfd_Library.getOK(), asserter.new XPath("//span[text()='Move to']"), false);
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyNotExist(asserter.new XPath("//span[text()='Move to']")))
				log.warn("Verified Move to dialog closed.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='" + folderName + "']")))
				log.warn("Verified user has been taken directly to the folder in the new location.");
			
			if(asserter.verifyNotExist(asserter.new XPath("//a[text()='Library']")))
				log.warn("Verified the current location in breadcrumb is correct.");
			
			if(asserter.verifyNotExist(asserter.new XPath("//a[contains(text(),'" + folderName + "']")))
				log.warn("Verified the folder has been moved above Home.");
			
			if(asserter.verifyExist("has been successfully moved to"))
				log.warn("Verified the successfully moved message appears.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Move A Folder To Defferent Position On Navigator'.");
		log.info("************************************");
		
	}

	public void copyLibraryFolderToNavigator(String folderName, String desc) throws Exception {

		log.info("************************************");
		log.info("Starting 'Copy A Folder In Library To Navigator'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			qfd_LibraryTask.goLibrary();
			
			qfd_LibraryTask.goItem(folderName);
			
			qfd_Page.getMoreActions().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Folder.getThisFolderItem().click();
			Thread.sleep(Wait.SHORT);
			
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ENTER).perform();
			log.info("Clicking on Copy Folder To item.");
			Thread.sleep(Wait.SHORT);
			
			asserter.checkboxClick(asserter.new XPath("//input[@value='navigator']"), true);
			
			qfd_Folder.getUp().click();
			Thread.sleep(Wait.PAUSE);
			qfd_Folder.getUp().click();
			Thread.sleep(Wait.PAUSE);
			qfd_Folder.getUp().click();
			Thread.sleep(Wait.PAUSE);
			qfd_Folder.getUp().click();
			Thread.sleep(Wait.PAUSE);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='The item will display here']")))
				log.warn("Verified the position for new item is right below Calendar.");
			
			asserter.elementClickWait(qfd_Library.getOK(), asserter.new XPath("//span[text()='Copy to']"), false);
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyNotExist(asserter.new XPath("//span[text()='Copy to']")))
				log.warn("Verified Copy to dialog closed.");
			
			if(asserter.verifyExist("has been successfully copied to"))
				log.warn("Verified the successfully copied message appears.");
			
			if((asserter.verifyExist(asserter.new XPath("//a[text()='Library']"))) && (asserter.verifyExist(asserter.new XPath("//span[text()='" + folderName + "']"))))
				log.warn("Verified user is still in Library view.");
			
			qfd_Folder.getLocation().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='" + folderName + "']")))
				log.warn("Verified user is brought to the " + folderName + " copied on the navigator.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[contains(text(),'" + folderName + "')]")))
				log.warn("Verified the folder and all its contents was copied to TOC, below Calendar.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Copy A Folder In Library To Navigator'.");
		log.info("************************************");
		
	}

	public void moveNavigatorFolderToLibraryFolder(String folderName,
			String destinationFolderName, String desc) throws Exception{

		log.info("************************************");
		log.info("Starting 'Move A Folder On Navigator To A Folder In Library'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			new Element(driver.findElement(By.xpath("//a[contains(@title,'" + folderName + "') and contains(@class,'qkrToc')]")), folderName + " link");
			Thread.sleep(Wait.SHORT);
			
			qfd_Page.getMoreActions().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Folder.getThisFolderItem().click();
			Thread.sleep(Wait.SHORT);
			
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ENTER).perform();
			log.info("Clicking on Move Folder To item.");
			Thread.sleep(Wait.SHORT);
			
			new Element(driver.findElement(By.xpath("//span[text()='Library' and @role='treeitem']")), "Library item").click();
			Thread.sleep(Wait.MID);
			
			new Element(driver.findElement(By.xpath("//span[text()='" + destinationFolderName + "' and @role='treeitem']")), destinationFolderName + " item").click();
			Thread.sleep(Wait.MID);
			
			asserter.elementClickWait(qfd_Library.getOK(), asserter.new XPath("//span[text()='Move to']"), false);
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyNotExist(asserter.new XPath("//span[text()='Move to']")))
				log.warn("Verified Move to dialog closed.");
			
			if(asserter.verifyExist("has been successfully moved to"))
				log.warn("Verified the successfully moved message appears.");
			
			qfd_LibraryTask.goLibrary();
			
			qfd_LibraryTask.goItem(destinationFolderName);
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + folderName + "']")))
				log.warn("Verified " + folderName + " and all its contents was moved.");
			
			qfd_LibraryTask.goItem(folderName);
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + destinationFolderName + "']")))
				log.warn("Verified user is in the moved folder.");
			
			if(asserter.verifyNotExist(asserter.new XPath("//a[contains(text(),'" + folderName + "']")))
				log.warn("Verified the folder is no longer on the navigator.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Move A Folder On Navigator To A Folder In Library'.");
		log.info("************************************");
		
	}

	public void createTOCTopFolder(String folderName, String desc) throws Exception {

		log.info("************************************");
		log.info("Starting 'Create Folder On Top Of TOC'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			qfd_PlaceTask.placeNewFolder();
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='New Folder']")))
				log.warn("Verified New Folder dialog displays.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Library']")))
				log.warn("Verified default location is Library.");
			
			qfd_Folder.getChangeLocation().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Save to']")))
				log.warn("Verified Save to dialog displays.");
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//input[@value='folders']")).isSelected() == true))
				log.warn("Verified Folders option is selected as default.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Library']")))
				log.warn("Verified user can only see Library folder in the list.");
			
			asserter.checkboxClick(asserter.new XPath("//input[@value='navigator']"), true);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='The item will display here']")))
				log.warn("Verified the default position for new item is at the very end.");
			
			qfd_Folder.getUp().click();
			Thread.sleep(Wait.PAUSE);
			qfd_Folder.getUp().click();
			Thread.sleep(Wait.PAUSE);
			qfd_Folder.getUp().click();
			Thread.sleep(Wait.PAUSE);
			qfd_Folder.getUp().click();
			Thread.sleep(Wait.PAUSE);
			qfd_Folder.getUp().click();
			Thread.sleep(Wait.PAUSE);
			qfd_Folder.getUp().click();
			Thread.sleep(Wait.PAUSE);
			qfd_Folder.getUp().click();
			Thread.sleep(Wait.PAUSE);
			
			asserter.elementClickWait(new Element(driver.findElement(By.xpath("//input[@value='OK' and contains(@id,'quickr_widgets_modal')]")), "OK button"), asserter.new XPath("//span[text()='Save to']"), false);
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyNotExist(asserter.new XPath("//span[text()='Save to']")))
				log.warn("Verified Save to dialog closed.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Navigator']")))
				log.warn("Verified the location is updated to Navigator.");
			
			//
			Thread.sleep(Wait.SHORT);
			qfd_Library.getFolderName().sendKeys(folderName);
			Thread.sleep(Wait.SHORT);
			
			qfd_Library.getFolderDesc().sendKeys(desc);
			Thread.sleep(Wait.SHORT);
			
			asserter.elementClickWait(qfd_Library.getOK(), asserter.new XPath("//a[text()='Change location']"), false);
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist("The folder was successfully created"))
				log.warn("Verified the text : 'The folder was successfully created' exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='" + folderName + "']")))
				log.warn("Verified user is in the newly created folder.");
			
			if(asserter.verifyNotExist(asserter.new XPath("//a[text()='Library']")))
				log.warn("Verified the current location in breadcrumb is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[contains(text(),'" + folderName + "')]")))
				log.warn("Verified the folder is on the Navigator at the very end.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Create Folder On Top Of TOC'.");
		log.info("************************************");
		
	}

	public void enableFolderACL() throws Exception {

		log.info("************************************");
		log.info("Starting 'Enable Folder ACL'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			qfd_LibraryTask.goLibrary();
			
			qfd_LibraryTask.newFolder();
			
			if(asserter.verifyNotExist(asserter.new XPath("//h3[text()='Who Can Access This']")))
				log.warn("Verified user can not see Access field.");
			
			qfd_Place.getCloseImage().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Place.getCustomize().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().frame(qfd_Place.getCutomizeFrame());
			Thread.sleep(Wait.PAUSE);
			
			qfd_Place.getBasics().click();
			Thread.sleep(Wait.SHORT);
			
			driver.findElement(By.xpath("//h1[text()='Basics']")).click();
			Thread.sleep(Wait.PAUSE);
			
			action.sendKeys(Keys.PAGE_DOWN).perform();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//input[@title='Folder ACL']")))
				log.warn("Verified user can see Folder ACL in the list.");
			
			asserter.checkboxClick(asserter.new XPath("//input[@title='Folder ACL']"), true);
			
			qfd_Place.getDone().click();
			Thread.sleep(Wait.MID);
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			asserter.elementClickWait(qfd_Place.getClose(), asserter.new XPath("//span[text()='Customize']"), false);
			
			qfd_LibraryTask.goLibrary();
			
			qfd_LibraryTask.newFolder();
			
			if(asserter.verifyExist(asserter.new XPath("//h3[text()='Who Can Access This']")))
				log.warn("Verified user can see Access field.");
			
			qfd_Place.getCloseImage().click();
			Thread.sleep(Wait.SHORT);
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Enable Folder ACL'.");
		log.info("************************************");
		
	}

	public void createRestrictedFolder(String folderName, String[] readers,
			String[] authors, String[] editors, String[] managers,
			String[] users) throws Exception {

		log.info("************************************");
		log.info("Starting 'Create Restricted Folder'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			qfd_LibraryTask.goLibrary();
			
			qfd_LibraryTask.newFolder();

			qfd_Library.getFolderName().sendKeys(folderName);
			Thread.sleep(Wait.SHORT);
			
			qfd_Folder.getAccess().click();
			Thread.sleep(Wait.SHORT);
			
			asserter.checkboxClick(asserter.new XPath("//input[@type='radio' and contains(@title,'Specific place members')]"), true);
			
			asserter.elementClickWait(qfd_Library.getOK(), asserter.new XPath("//a[text()='Change location']"), false);
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist("The folder was successfully created"))
				log.warn("Verified the text : 'The folder was successfully created' exists.");
				
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Create Restricted Folder'.");
		log.info("************************************");
		
	}

	public void createRestrictedSubFolder(String subFolderName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Create Restricted Sub Folder'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			qfd_LibraryTask.newFolder();

			qfd_Library.getFolderName().sendKeys(subFolderName);
			Thread.sleep(Wait.SHORT);
			
			qfd_Folder.getAccess().click();
			Thread.sleep(Wait.SHORT);
			
			asserter.elementClickWait(qfd_Library.getOK(), asserter.new XPath("//a[text()='Change location']"), false);
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist("The folder was successfully created"))
				log.warn("Verified the text : 'The folder was successfully created' exists.");
				
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Create Restricted Sub Folder'.");
		log.info("************************************");
		
	}

	public void changeACL(String[] users) throws Exception {

		log.info("************************************");
		log.info("Starting 'Change Restricted Folder ACL'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			
				
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Change Restricted Folder ACL'.");
		log.info("************************************");
		
	}

	public void verifyACL(String[][] users) throws Exception {

		log.info("************************************");
		log.info("Starting 'Verify Restricted Folder ACL'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			
				
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify Restricted Folder ACL'.");
		log.info("************************************");
		
	}

	public void deleteFolder(String folderName, String subFolderName) throws Exception {
		
		log.info("************************************");
		log.info("Starting 'Delete Restricted Folder'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			
				
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Delete Restricted Folder'.");
		log.info("************************************");
		
	}
	
}
