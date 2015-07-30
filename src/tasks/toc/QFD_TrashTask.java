package tasks.toc;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import config.BrowserSetup;
import config.Element;
import config.Wait;
import exceptions.ElementException;

import appobjects.common.QFD_Place;
import appobjects.library.QFD_Folder;
import appobjects.library.QFD_Page;
import appobjects.toc.QFD_Library;
import appobjects.toc.QFD_Trash;

import tasks.common.QFD_RoomTask;
import util.Assert;

public class QFD_TrashTask {

	private WebDriver driver;
	private Logger log;
	private Assert asserter;
	QFD_LibraryTask qfd_LibraryTask = null;
	QFD_RoomTask qfd_RoomTask = null;
	QFD_Library qfd_Library = null;
	QFD_Place qfd_Place = null;
	QFD_Page qfd_Page = null;
	QFD_Folder qfd_Folder = null;
	QFD_Trash qfd_Trash = null;
	
	public QFD_TrashTask(WebDriver driver, Logger log){
		
		this.driver = driver;
		this.log = log;
		this.asserter = new Assert(driver, log);
		qfd_LibraryTask = new QFD_LibraryTask(driver, log);
		qfd_RoomTask = new QFD_RoomTask(driver, log);
		qfd_Library = new QFD_Library(driver);
		qfd_Place = new QFD_Place(driver);
		qfd_Page = new QFD_Page(driver);
		qfd_Folder = new QFD_Folder(driver);
		qfd_Trash = new QFD_Trash(driver);
		
	}

	public void goTrash() throws Exception {
		
		asserter.elementClick(qfd_Trash.getTrash(), asserter.new XPath("//h1[text()='Trash']"), true);
		Thread.sleep(Wait.SHORT);
		
	}

	public void verifyTrashItem(String itemName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Verify Trash Item'...");
		Thread.sleep(Wait.SHORT);
		
		try{
		
			goTrash();
			
			if(asserter.verifyExist(asserter.new XPath("//h4[text()='" + itemName + "']")))
				log.warn("Verified the " + itemName + " is in the Trash.");
		
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify Trash Item'.");
		log.info("************************************");
		
	}

	public void restoreOneItem(String itemName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Retore One Trash Item'...");
		Thread.sleep(Wait.SHORT);
		
		try{
		
			goTrash();
			
			asserter.checkboxClick(asserter.new XPath("//input[@title='" + itemName + "']"), true);
			Thread.sleep(Wait.SHORT);
			
			qfd_Trash.getRestore().click();
			Thread.sleep(Wait.SHORT);
				
			driver.switchTo().alert().accept();
			Thread.sleep(Wait.SHORT);
			log.info("Clicking on OK button.");
			
			if(asserter.verifyNotExist(asserter.new XPath("//h4[text()='" + itemName + "']")))
				log.warn("Verified the " + itemName + " restores successfully.");
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.MID);
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Retore One Trash Item'.");
		log.info("************************************");
		
	}

	public void deleteItemMoreAction(String itemName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Delete A Item From Library By More Actions'...");
		Thread.sleep(Wait.SHORT);
		
		try{
		
			qfd_LibraryTask.goItem(itemName);
			
			qfd_Page.getMoreActions().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Page.getMoveToTrash().click();
			Thread.sleep(Wait.SHORT);
			
			delete();
			
			qfd_Trash.getTrashConfirm().click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='Trash']")))
				log.warn("Verified user is brought to the Trash folder.");
			
			if(asserter.verifyExist(asserter.new XPath("//h4[text()='" + itemName + "']")))
				log.warn("Verified the " + itemName + " is in the Trash.");
			
			qfd_LibraryTask.goLibrary();
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Delete A Item From Library By More Actions'.");
		log.info("************************************");
		
	}
	
	public void delete() throws Exception {
		
		driver.switchTo().alert().accept();
		log.info("Clicking on OK button.");
		Thread.sleep(Wait.SHORT);
		
		if(asserter.verifyExist("was successfully moved to"))
			log.warn("Verified the item is successfully move to Trash.");
		
	}

	public void deleteItemOption(String itemName) throws Exception {
		
		log.info("************************************");
		log.info("Starting 'Delete A Item From Library By Options'...");
		Thread.sleep(Wait.SHORT);
		
		try{
		
			new Element(driver.findElement(By.xpath("//a[@title='" + itemName + "']/img[@alt='Click for options']")), itemName + "'s options menu").click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Page.getMoveToTrash().click();
			Thread.sleep(Wait.SHORT);
			
			delete();
			
			qfd_Trash.getTrashConfirm().click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='Trash']")))
				log.warn("Verified user is brought to the Trash folder.");
			
			if(asserter.verifyExist(asserter.new XPath("//h4[text()='" + itemName + "']")))
				log.warn("Verified the " + itemName + " is in the Trash.");
			
			//Mary
			//old
			//qfd_RoomTask.goRoomIndex();
			//new
			qfd_LibraryTask.goLibrary();
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Delete A Item From Library By Options'.");
		log.info("************************************");
		
	}

	public void restoreOneItemToLirary(String itemName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Retore One Trash Item To Library'...");
		Thread.sleep(Wait.SHORT);
		
		restoreOneItem(itemName);
		
		try{
		
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Library']")))
				log.warn("Verified the source location is correct.");
			
			qfd_LibraryTask.goLibrary();
			
			if(asserter.verifyExist(asserter.new XPath("//a[@title='" + itemName + "']")))
				log.warn("Verified the item : " + itemName + " has restored to Library.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Retore One Trash Item To Library'.");
		log.info("************************************");
		
	}

	public void restoreTwoItemsToLibrary(String item1Name, String item2Name) throws Exception {

		log.info("************************************");
		log.info("Starting 'Retore Two Trash Items To Library'...");
		Thread.sleep(Wait.SHORT);
		
		try{
		
			goTrash();
			
			asserter.checkboxClick(asserter.new XPath("//input[@title='" + item1Name + "']"), true);
			asserter.checkboxClick(asserter.new XPath("//input[@title='" + item2Name + "']"), true);
			
			qfd_Trash.getRestore().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().alert().accept();
			Thread.sleep(Wait.SHORT);
			log.info("Clicking on OK button.");
				
			if(asserter.verifyNotExist(asserter.new XPath("//h4[text()='" + item1Name + "']")))
				log.warn("Verified the " + item1Name + " restores successfully.");
			
			if(asserter.verifyNotExist(asserter.new XPath("//h4[text()='" + item2Name + "']")))
				log.warn("Verified the " + item2Name + " restores successfully.");
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist("Successfully restored 2 items"))
				log.warn("Verified confirmation message is correct.");
			
			qfd_LibraryTask.goLibrary();
			
			if(asserter.verifyExist(asserter.new XPath("//a[@title='" + item1Name + "']")))
				log.warn("Verified the item : " + item1Name + " has restored to Library.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[@title='" + item2Name + "']")))
				log.warn("Verified the item : " + item2Name + " has restored to Library.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Retore Two Trash Items To Library'.");
		log.info("************************************");
		
	}

	public void emptyTrash(String[] itemNames) throws Exception {

		log.info("************************************");
		log.info("Starting 'Empty Trash'...");
		Thread.sleep(Wait.SHORT);
		
		try{
		
			goTrash();
			
			qfd_Trash.getEmptyTrash().click();
			Thread.sleep(Wait.SHORT);
				
			driver.switchTo().alert().accept();
			Thread.sleep(Wait.SHORT);
			log.info("Clicking on OK button.");
			
			boolean result = true;
			for(int i = 0; i < itemNames.length; i++){
				
				if(asserter.verifyNotExist(asserter.new XPath("//h4[text()='" + itemNames[i] + "']")))
					log.warn("Verified the " + itemNames[i] + " deleted successfully.");
				else result = false;
			
			}
			
			if(asserter.verifyTrue(result))
				log.warn("Verified Trash is now empty.");
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist("Successfully deleted all items in trash"))
				log.warn("Verified confirmation message is correct.");
			
			qfd_LibraryTask.goLibrary();
			
			for(int i = 0; i < itemNames.length; i++){
				
				if(asserter.verifyNotExist(asserter.new XPath("//a[@title='" + itemNames[i] + "']")))
					log.warn("Verified the " + itemNames[i] + " has deleted successfully.");
			
			}
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Empty Trash'.");
		log.info("************************************");
		
	}

	public void restoreAllToLibrary(String[] itemNames) throws Exception {

		log.info("************************************");
		log.info("Starting 'Retore All Trash Items To Library'...");
		Thread.sleep(Wait.SHORT);
		
		try{
		
			goTrash();
			
			qfd_Trash.getRestoreAll().click();
			Thread.sleep(Wait.SHORT);
				
			driver.switchTo().alert().accept();
			Thread.sleep(Wait.SHORT);
			log.info("Clicking on OK button.");
			
			for(int i = 0; i < itemNames.length; i++){
				
				if(asserter.verifyNotExist(asserter.new XPath("//h4[text()='" + itemNames[i] + "']")))
					log.warn("Verified the " + itemNames[i] + " restores successfully.");
			
			}
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist("Successfully restored all items in trash"))
				log.warn("Verified confirmation message is correct.");
			
			qfd_LibraryTask.goLibrary();
			
			for(int i = 0; i < itemNames.length; i++){
				
				if(asserter.verifyExist(asserter.new XPath("//a[@title='" + itemNames[i] + "']")))
					log.warn("Verified the " + itemNames[i] + " has restored to Library.");
			
			}
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Retore All Trash Items To Library'.");
		log.info("************************************");
		
	}

	public void verifyRename(String itemName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Verify Rename UI'...");
		Thread.sleep(Wait.SHORT);
		
		try{
		
			goTrash();
			
			asserter.checkboxClick(asserter.new XPath("//input[@title='" + itemName + "']"), true);
			
			qfd_Trash.getRestore().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().alert().accept();
			Thread.sleep(Wait.SHORT);
			log.info("Clicking on OK button.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Item Already Exists']")))
				log.warn("Verified the Item Already Exists dialog is displayed.");
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//input[@title='Rename the item to restore']")).isSelected()))
				log.warn("Verified rename is the default choice.");
			
			qfd_Library.getOK().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//strong[text()='New name required']")))
				log.warn("Verified the user is warned and forced to enter a new name.");
			
			asserter.checkboxClick(asserter.new XPath("//input[@title='Replace the existing item']"), true);
			
			if(asserter.verifyExist(asserter.new XPath("//input[@id='newName' and @disabled]")))
				log.warn("Verified the new name textbox is disabled.");
			
			asserter.checkboxClick(asserter.new XPath("//input[@title='Rename the item to restore']"), true);
			
			qfd_Trash.getRestoreNewName().sendKeys("Page One Restored");
			Thread.sleep(Wait.SHORT);
			
			qfd_Library.getOK().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist("Page One Restored"))
				log.warn("Verified the restore message appears with the new name and location.");
			
			if(asserter.verifyNotExist(asserter.new XPath("//h4[text()='Page One']")))
				log.warn("Verified the Page restores successfully.");
			
			qfd_LibraryTask.goLibrary();
			
			if(asserter.verifyExist(asserter.new XPath("//a[@title='Page One Restored']")))
				log.warn("Verified the Page has restored to Library with new name.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify Rename UI'.");
		log.info("************************************");
		
	}

	public void deleteSecondPage(String pageName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Delete Second Duplicate Page'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			WebElement[] T = new WebElement[5];
		
			new Element(driver.findElements(By.xpath("//h4/a[@title='" + pageName + "']")).toArray(T)[1], pageName + " link").click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Page.getMoreActions().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Page.getMoveToTrash().click();
			Thread.sleep(Wait.SHORT);
			
			delete();
			
			qfd_Trash.getTrashConfirm().click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='Trash']")))
				log.warn("Verified user is brought to the Trash folder.");
			
			if(asserter.verifyExist(asserter.new XPath("//h4[text()='" + pageName + "']")))
				log.warn("Verified the " + pageName + " is in the Trash.");
			
			qfd_LibraryTask.goLibrary();
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Delete Second Duplicate Page'.");
		log.info("************************************");
		
	}

	public void replace(String pageName, String desc) throws Exception {

		log.info("************************************");
		log.info("Starting 'Replace Existing Item'...");
		Thread.sleep(Wait.SHORT);
		
		try{
		
			goTrash();
			
			asserter.checkboxClick(asserter.new XPath("//input[@title='" + pageName + "']"), true);
			
			qfd_Trash.getRestore().click();
			Thread.sleep(Wait.SHORT);
				
			driver.switchTo().alert().accept();
			Thread.sleep(Wait.SHORT);
			log.info("Clicking on OK button.");
			
			asserter.checkboxClick(asserter.new XPath("//input[@title='Replace the existing item']"), true);
			
			if(asserter.verifyExist(asserter.new XPath("//input[@id='newName' and @disabled]")))
				log.warn("Verified the new name textbox is disabled.");
			
			qfd_Library.getOK().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist("Page One"))
				log.warn("Verified the restore message appears correctly.");
			
			if(asserter.verifyNotExist(asserter.new XPath("//h4[text()='Page One']")))
				log.warn("Verified the Page restores successfully.");
			
			qfd_LibraryTask.goLibrary();
			
			qfd_LibraryTask.goItem(pageName);
			
			driver.switchTo().frame(qfd_Page.getTextFrame());
			Thread.sleep(Wait.PAUSE);
			
			if(asserter.verifyExist(asserter.new XPath("//p[contains(text(),'" + desc + "')]")))
				log.warn("Verified the text in the body of the page shown the page was replaced correctly.");
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			qfd_LibraryTask.goLibrary();
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Replace Existing Item'.");
		log.info("************************************");
		
		
	}

	public void restoreAllAndRename(String renameItem, String otherItem) throws Exception {

		log.info("************************************");
		log.info("Starting 'Retore All Trash Items To Library And Rename'...");
		Thread.sleep(Wait.SHORT);
		
		try{
		
			goTrash();
			
			qfd_Trash.getRestoreAll().click();
			Thread.sleep(Wait.SHORT);
				
			driver.switchTo().alert().accept();
			Thread.sleep(Wait.SHORT);
			log.info("Clicking on OK button.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Item Already Exists']")))
				log.warn("Verified the Item Already Exists dialog is displayed.");
			
			qfd_Trash.getRestoreNewName().sendKeys("Page Two");
			Thread.sleep(Wait.SHORT);
			
			qfd_Library.getOK().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist("Successfully restored all items in trash"))
				log.warn("Verified the restore message appears.");
			
			if(asserter.verifyNotExist(asserter.new XPath("//h4[text()='" + renameItem + "']")))
				log.warn("Verified the " + renameItem + " restores successfully.");
			
			if(asserter.verifyNotExist(asserter.new XPath("//h4[text()='" + otherItem + "']")))
				log.warn("Verified the " + otherItem + " restores successfully.");
			
			qfd_LibraryTask.goLibrary();
			
			if(asserter.verifyExist(asserter.new XPath("//a[@title='" + otherItem + "']")))
				log.warn("Verified the " + otherItem + " has restored to Library.");
					
			if(asserter.verifyExist(asserter.new XPath("//a[@title='Page Two']")))
				log.warn("Verified the duplicate page has restored to Library with new name.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Retore All Trash Items To Library And Rename'.");
		log.info("************************************");
		
	}

	public void restoreAllAndReplace(String replaceItem, String otherItem, String desc) throws Exception {

		log.info("************************************");
		log.info("Starting 'Retore All Trash Items To Library And Replace'...");
		Thread.sleep(Wait.SHORT);
		
		try{
		
			goTrash();
			
			qfd_Trash.getRestoreAll().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().alert().accept();
			Thread.sleep(Wait.SHORT);
			log.info("Clicking on OK button.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Item Already Exists']")))
				log.warn("Verified the Item Already Exists dialog is displayed.");
			
			asserter.checkboxClick(asserter.new XPath("//input[@title='Replace the existing item']"), true);
			
			if(asserter.verifyExist(asserter.new XPath("//input[@id='newName' and @disabled]")))
				log.warn("Verified the new name textbox is disabled.");
			
			qfd_Library.getOK().click();
			Thread.sleep(Wait.SHORT*2);//June
			
			if(asserter.verifyExist("Successfully restored all items in trash"))
				log.warn("Verified the restore message appears.");
			
			Thread.sleep(Wait.SHORT*2);//June
			if(asserter.verifyNotExist(asserter.new XPath("//h4[text()='" + replaceItem + "']")))
				log.warn("Verified the " + replaceItem + " restores successfully.");
			
			Thread.sleep(Wait.SHORT*2);//June
			if(asserter.verifyNotExist(asserter.new XPath("//h4[text()='" + otherItem + "']")))
				log.warn("Verified the " + otherItem + " restores successfully.");
			
			qfd_LibraryTask.goLibrary();
			
			Thread.sleep(Wait.SHORT*2);//June
			if(asserter.verifyExist(asserter.new XPath("//a[@title='" + otherItem + "']")))
				log.warn("Verified the " + otherItem + " has restored to Library.");
				
			Thread.sleep(Wait.SHORT*2);//June
			qfd_LibraryTask.goItem(replaceItem);
			
			driver.switchTo().frame(qfd_Page.getTextFrame());
			Thread.sleep(Wait.PAUSE);
			
			if(asserter.verifyExist(asserter.new XPath("//p[contains(text(),'" + desc + "')]")))
				log.warn("Verified the text in the body of the page shown the page was replaced correctly.");
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			qfd_LibraryTask.goLibrary();
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Retore All Trash Items To Library And Replace'.");
		log.info("************************************");
		
	}

	public void verifyRenameOthers(String itemName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Verify Rename Other Functions'...");
		Thread.sleep(Wait.SHORT);
		
		try{
		
			goTrash();
			
			asserter.checkboxClick(asserter.new XPath("//input[@title='" + itemName + "']"), true);
			
			qfd_Trash.getRestore().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().alert().accept();
			Thread.sleep(Wait.SHORT);
			log.info("Clicking on OK button.");
				
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Item Already Exists']")))
				log.warn("Verified the Item Already Exists dialog is displayed.");
			
			qfd_Trash.getRestoreNewName().sendKeys("Page Two");
			Thread.sleep(Wait.SHORT);
			
			qfd_Library.getOK().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Item Already Exists']")))
				log.warn("Verified the Item Already Exists dialog appears again.");
			
			qfd_Place.getCloseImage().click();
			Thread.sleep(Wait.SHORT);
			
			asserter.checkboxClick(asserter.new XPath("//input[@title='" + itemName + "']"), true);
			
			qfd_Trash.getRestore().click();
			Thread.sleep(Wait.SHORT);
				
			driver.switchTo().alert().accept();
			Thread.sleep(Wait.SHORT);
			log.info("Clicking on OK button.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Item Already Exists']")))
				log.warn("Verified the Item Already Exists dialog is displayed.");
			
			qfd_Trash.getCancel().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyNotExist(asserter.new XPath("//span[text()='Item Already Exists']")))
				log.warn("Verified the Item Already Exists dialog is closed.");
			
			if(asserter.verifyExist(asserter.new XPath("//h4[text()='" + itemName + "']")))
				log.warn("Verified rename does not happen.");
			
			asserter.checkboxClick(asserter.new XPath("//input[@title='" + itemName + "']"), true);
			
			qfd_Trash.getRestore().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().alert().accept();
			Thread.sleep(Wait.SHORT);
			log.info("Clicking on OK button.");
				
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Item Already Exists']")))
				log.warn("Verified the Item Already Exists dialog is displayed.");
			
			qfd_Place.getCloseImage().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyNotExist(asserter.new XPath("//span[text()='Item Already Exists']")))
				log.warn("Verified the Item Already Exists dialog is closed.");
			
			if(asserter.verifyExist(asserter.new XPath("//h4[text()='" + itemName + "']")))
				log.warn("Verified rename does not happen.");
			
			asserter.checkboxClick(asserter.new XPath("//input[@title='" + itemName + "']"), true);
			
			qfd_Trash.getRestore().click();
			Thread.sleep(Wait.SHORT);
				
			driver.switchTo().alert().accept();
			Thread.sleep(Wait.SHORT);
			log.info("Clicking on OK button.");
				
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Item Already Exists']")))
				log.warn("Verified the Item Already Exists dialog is displayed.");
			
			qfd_Trash.getRestoreNewName().sendKeys("#$%");
			Thread.sleep(Wait.SHORT);
			
			qfd_Library.getOK().click();
			Thread.sleep(Wait.SHORT);
			
			//mary
		
			if(asserter.verifyExist("#$"))
				log.warn("Verified the restore message appears.");
			
			if(asserter.verifyNotExist(asserter.new XPath("//h4[text()='" + itemName + "']")))
				log.warn("Verified the " + itemName + " restores successfully.");
			
			qfd_LibraryTask.goLibrary();
			
			if(asserter.verifyExist(asserter.new XPath("//a[@title='#$%']")))
				log.warn("Verified special characters on the rename are handled correctly.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify Rename Other Functions'.");
		log.info("************************************");
		
	}

	public void deleteItemRoomIndex(String itemName) throws Exception {
		
		log.info("************************************");
		log.info("Starting 'Delete A Item From Room Index'...");
		Thread.sleep(Wait.SHORT);
		
		try{
		
			asserter.checkboxClick(asserter.new XPath("//input[@title='" + itemName + "']"), true);
			
			qfd_Page.getMoreActions().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Trash.getDeleteItem().click();
			Thread.sleep(Wait.SHORT);
			
			delete();
			
			qfd_Trash.getTrashConfirm().click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='Trash']")))
				log.warn("Verified user is brought to the Trash folder.");
			
			if(asserter.verifyExist(asserter.new XPath("//h4[text()='" + itemName + "']")))
				log.warn("Verified the " + itemName + " is in the Trash.");
			
			qfd_RoomTask.goRoomIndex();
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Delete A Item From Room Index'.");
		log.info("************************************");
		
		
	}

	public void deleteRoomFolder(String folderName) throws Exception {
		
		log.info("************************************");
		log.info("Starting 'Delete A Room Folder'...");
		Thread.sleep(Wait.SHORT);
		
		try{
		
			new Element(driver.findElement(By.xpath("//a[contains(@title,'" + folderName + "')]")), folderName + " link on TOC").click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Page.getMoreActions().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Folder.getThisFolderItem().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Folder.getMoveFolderTrash().click();
			Thread.sleep(Wait.SHORT);
			
			delete();
			
			qfd_Trash.getTrashConfirm().click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='Trash']")))
				log.warn("Verified user is brought to the Trash folder.");
			
			if(asserter.verifyExist(asserter.new XPath("//h4[text()='" + folderName + "']")))
				log.warn("Verified the " + folderName + " is in the Trash.");
			
			qfd_RoomTask.goRoomIndex();
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Delete A Room Folder'.");
		log.info("************************************");
		
	}

	public void restoreRoomFolder(String folderName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Restore A Room Folder'...");
		Thread.sleep(Wait.SHORT);
		
		try{
		
			goTrash();
			
			asserter.checkboxClick(asserter.new XPath("//input[@title='" + folderName + "']"), true);
			Thread.sleep(Wait.SHORT);
			
			qfd_Trash.getRestore().click();
			Thread.sleep(Wait.SHORT);
				
			driver.switchTo().alert().accept();
			Thread.sleep(Wait.SHORT);
			log.info("Clicking on OK button.");
			
			if(asserter.verifyExist("successfully restored"))
				log.warn("Verified confirmation message is correct.");
			
			if(asserter.verifyNotExist(asserter.new XPath("//h4[text()='" + folderName + "']")))
				log.warn("Verified the " + folderName + " restores successfully.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[contains(@title,'" + folderName + "')]")))
				log.warn("Verified the " + folderName + " is back to the TOC.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Restore A Room Folder'.");
		log.info("************************************");
		
	}
	
	public void restoreAllToRoomIndex(String[] itemNames) throws Exception {

		log.info("************************************");
		log.info("Starting 'Retore All Trash Items To Room Index'...");
		Thread.sleep(Wait.SHORT);
		
		try{
		
			goTrash();
			
			for(int i = 0; i < itemNames.length; i++){
				
				asserter.checkboxClick(asserter.new XPath("//input[@title='" + itemNames[i] + "']"), true);
				
			}
			
			qfd_Trash.getRestore().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().alert().accept();
			Thread.sleep(Wait.SHORT);
			log.info("Clicking on OK button.");
				
			for(int i = 0; i < itemNames.length; i++){
				
				if(asserter.verifyNotExist(asserter.new XPath("//h4[text()='" + itemNames[i] + "']")))
					log.warn("Verified the " + itemNames[i] + " restores successfully.");
			
			}
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist("Successfully restored"))
				log.warn("Verified confirmation message is correct.");
			
			qfd_RoomTask.goRoomIndex();
			
			for(int i = 0; i < itemNames.length; i++){
				
				if(asserter.verifyExist(asserter.new XPath("//a[@title='" + itemNames[i] + "']")))
					log.warn("Verified the " + itemNames[i] + " has restored to Room Index.");
			
			}
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Retore All Trash Items To Room Index'.");
		log.info("************************************");
		
	}

	public void deleteOneItem(String itemName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Delete One Item From Trash'...");
		Thread.sleep(Wait.SHORT);
		
		try{
		
			goTrash();
			
			asserter.checkboxClick(asserter.new XPath("//input[@title='" + itemName + "']"), true);
			Thread.sleep(Wait.SHORT);
			
			qfd_Trash.getDelete().click();
			Thread.sleep(Wait.SHORT);
				
			driver.switchTo().alert().accept();
			Thread.sleep(Wait.SHORT);
			log.info("Clicking on OK button.");
			
			if(asserter.verifyNotExist(asserter.new XPath("//h4[text()='" + itemName + "']")))
				log.warn("Verified the " + itemName + " is deleted successfully.");
			
			if(asserter.verifyExist("permanently deleted"))
				log.warn("Verified confirmation message is correct.");
			
			qfd_RoomTask.goRoomIndex();
			
			if(asserter.verifyNotExist(asserter.new XPath("//a[@title='" + itemName + "']")))
				log.warn("Verified the " + itemName + " is no longer Room Index.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Delete One Item From Trash'.");
		log.info("************************************");
		
	}

	public void deleteTwoItems(String item1Name, String item2Name) throws Exception {
		
		log.info("************************************");
		log.info("Starting 'Delete Two Items From Trash'...");
		Thread.sleep(Wait.SHORT);
		
		try{
		
			goTrash();
			
			asserter.checkboxClick(asserter.new XPath("//input[@title='" + item1Name + "']"), true);
			asserter.checkboxClick(asserter.new XPath("//input[@title='" + item2Name + "']"), true);
			
			qfd_Trash.getDelete().click();
			Thread.sleep(Wait.SHORT);
				
			driver.switchTo().alert().accept();
			Thread.sleep(Wait.SHORT);
			log.info("Clicking on OK button.");
				
			if(asserter.verifyNotExist(asserter.new XPath("//h4[text()='" + item1Name + "']")))
				log.warn("Verified the " + item1Name + " is deleted successfully.");
			
			if(asserter.verifyNotExist(asserter.new XPath("//h4[text()='" + item2Name + "']")))
				log.warn("Verified the " + item2Name + " is deleted successfully.");
			
			if(asserter.verifyExist("permanently"))
				log.warn("Verified confirmation message is correct.");
			
			qfd_RoomTask.goRoomIndex();
			
			if(asserter.verifyNotExist(asserter.new XPath("//a[@title='" + item1Name + "']")))
				log.warn("Verified the " + item1Name + " is no longer Room Index.");
			
			if(asserter.verifyNotExist(asserter.new XPath("//a[@title='" + item2Name + "']")))
				log.warn("Verified the " + item2Name + " is no longer Room Index.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Delete Two Items From Trash'.");
		log.info("************************************");
		
	}
	
	public void emptyTrashRoom(String[] itemNames) throws Exception {

		log.info("************************************");
		log.info("Starting 'Empty Trash In Room'...");
		Thread.sleep(Wait.SHORT);
		
		try{
		
			goTrash();
			
			qfd_Trash.getEmptyTrash().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().alert().accept();
			Thread.sleep(Wait.SHORT);
			log.info("Clicking on OK button.");
			
			boolean result = true;
			for(int i = 0; i < itemNames.length; i++){
				
				if(asserter.verifyNotExist(asserter.new XPath("//h4[text()='" + itemNames[i] + "']")))
					log.warn("Verified the " + itemNames[i] + " deleted successfully.");
				else result = false;
			
			}
			
			if(asserter.verifyTrue(result))
				log.warn("Verified Trash is now empty.");
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist("Successfully deleted all items in trash"))
				log.warn("Verified confirmation message is correct.");
			
			qfd_RoomTask.goRoomIndex();
			
			for(int i = 0; i < itemNames.length; i++){
				
				if(asserter.verifyNotExist(asserter.new XPath("//a[@title='" + itemNames[i] + "']")))
					log.warn("Verified the " + itemNames[i] + " has deleted successfully.");
			
			}
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Empty Trash In Room'.");
		log.info("************************************");
		
	}
	
}
