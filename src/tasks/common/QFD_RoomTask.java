package tasks.common;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import config.Element;
import config.Users;
import config.Wait;
import exceptions.ElementException;

import util.Assert;
import util.Assert.XPath;
import appobjects.common.QFD_Login;
import appobjects.common.QFD_Members;
import appobjects.common.QFD_Place;
import appobjects.common.QFD_Room;
import appobjects.library.QFD_Page;
import appobjects.toc.QFD_Library;

public class QFD_RoomTask {

	private WebDriver driver;
	private Logger log;
	private Assert asserter;
	private Actions action;
	QFD_Place qfd_Place = null;
	QFD_Members qfd_Members = null;
	QFD_Page qfd_Page = null;
	QFD_Library qfd_Library = null;
	QFD_Room qfd_Room = null;
	
	public QFD_RoomTask(WebDriver driver, Logger log){
		
		this.driver = driver;
		this.log = log;
		this.asserter = new Assert(driver, log);
		this.action = new Actions(driver);
		qfd_Place = new QFD_Place(driver);
		qfd_Members = new QFD_Members(driver);
		qfd_Page = new QFD_Page(driver);
		qfd_Library = new QFD_Library(driver);
		qfd_Room= new QFD_Room(driver);
		
	}
	
	public void createRoom(String roomName) throws Exception {
		
		log.info("************************************");
		log.info("Starting 'Create Room'...");
		Thread.sleep(Wait.MID);
		
		try{
		
			qfd_Place.getPlaceAction().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Place.getPlaceActionNewRoom().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Library.getFolderName().sendKeys(roomName);
			Thread.sleep(Wait.SHORT);
			
			asserter.elementClickWait(qfd_Library.getSave(), asserter.new XPath("//a[text()='" + roomName + "']"), true);
			Thread.sleep(Wait.SHORT);	
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + roomName + "']")))
				log.warn("Verified the room " + roomName + " successfully created.");
			
			goRoomIndex();
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Create Room'.");
		log.info("************************************");
		
	}

	public void goRoomIndex() throws Exception {

		asserter.elementClick(qfd_Room.getRoomIndex(), asserter.new XPath("//span[text()='Room Index']"), true);
		Thread.sleep(Wait.SHORT);
		
	}

	public void goRoomMembers() throws Exception {

		asserter.elementClickWait(qfd_Room.getRoomMembers(), asserter.new XPath("//span[text()='Room Members']"), true);
		Thread.sleep(Wait.SHORT);
		
	}
	
	public void goRoom(String roomName) throws Exception {

		asserter.elementClickWait(new Element(driver.findElement(By.xpath("//a[contains(@class,'toc') and contains(@title,'" + roomName + "')]")), roomName + " link on TOC"), asserter.new XPath("//a[text()='" + roomName + "']"), true);
		Thread.sleep(Wait.SHORT);
		
	}
	
	public void returnParent() throws Exception {
		
		asserter.elementClickWait(qfd_Room.getParent(), asserter.new XPath("//a[@title='Place Actions']"), true);
		Thread.sleep(Wait.SHORT);
		
	}
	
	public void newPage() throws Exception {

		asserter.elementClick(qfd_Room.getRoomAction(), asserter.new XPath("//td[text()='New']"), true);
		Thread.sleep(Wait.SHORT);
		
		action.sendKeys(Keys.ENTER).perform();
		Thread.sleep(Wait.VERYSHORT);
		action.sendKeys(Keys.ARROW_DOWN).perform();
		Thread.sleep(Wait.VERYSHORT);
		action.sendKeys(Keys.ENTER).perform();
		Thread.sleep(Wait.VERYSHORT);
		
		log.info("Clicking on New Page item.");
		Thread.sleep(Wait.SHORT);
		
	}

	public void newLink() throws Exception {

		asserter.elementClick(qfd_Room.getRoomAction(), asserter.new XPath("//td[text()='New']"), true);
		Thread.sleep(Wait.SHORT);
		
		action.sendKeys(Keys.ENTER).perform();
		Thread.sleep(Wait.VERYSHORT);
		action.sendKeys(Keys.ARROW_DOWN).perform();
		Thread.sleep(Wait.VERYSHORT);
		action.sendKeys(Keys.ARROW_DOWN).perform();
		Thread.sleep(Wait.VERYSHORT);
		action.sendKeys(Keys.ARROW_DOWN).perform();
		Thread.sleep(Wait.VERYSHORT);
		action.sendKeys(Keys.ENTER).perform();
		Thread.sleep(Wait.VERYSHORT);
		
		log.info("Clicking on New Link item.");
		Thread.sleep(Wait.SHORT);
		
	}
	
	public void newFolder() throws Exception {

		asserter.elementClick(qfd_Room.getRoomAction(), asserter.new XPath("//td[text()='New']"), true);
		Thread.sleep(Wait.SHORT);
		
		action.sendKeys(Keys.ENTER).perform();
		Thread.sleep(Wait.VERYSHORT);
		action.sendKeys(Keys.ARROW_UP).perform();
		Thread.sleep(Wait.VERYSHORT);
		action.sendKeys(Keys.ENTER).perform();
		Thread.sleep(Wait.VERYSHORT);
		
		log.info("Clicking on New Folder item.");
		Thread.sleep(Wait.SHORT);
		
	}
	
	public void newCusLib() throws Exception {

		asserter.elementClick(qfd_Room.getRoomAction(), asserter.new XPath("//td[text()='New']"), true);
		Thread.sleep(Wait.SHORT);
		
		action.sendKeys(Keys.ENTER).perform();
		Thread.sleep(Wait.VERYSHORT);
		action.sendKeys(Keys.ARROW_UP).perform();
		Thread.sleep(Wait.VERYSHORT);
		action.sendKeys(Keys.ARROW_UP).perform();
		Thread.sleep(Wait.VERYSHORT);
		action.sendKeys(Keys.ENTER).perform();
		Thread.sleep(Wait.VERYSHORT);
		
		log.info("Clicking on New Custom Library item.");
		Thread.sleep(Wait.SHORT);
		
	}

	public void verifyRoomInheritMember(String roomName, String editorName, String groupName, String ownerName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Create Room And Verify Members Inherited From Place'...");
		Thread.sleep(Wait.MID);
		
		try{
		
			qfd_Place.getPlaceAction().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Place.getPlaceActionNewRoom().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Create Room']")))
				log.warn("Verified the New Room dialog appears.");
			
			qfd_Library.getFolderName().sendKeys(roomName);
			Thread.sleep(Wait.SHORT);
			
			asserter.checkboxClick(asserter.new XPath("//input[@id='CMI_inherit']"), true);
			
			asserter.elementClickWait(qfd_Library.getSave(), asserter.new XPath("//a[text()='" + roomName + "']"), true);
			Thread.sleep(Wait.SHORT);	
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + roomName + "']")))
				log.warn("Verified the room " + roomName + " successfully created.");
			
			returnParent();
			
			if(asserter.verifyExist(asserter.new XPath("//a[contains(@class,'toc') and contains(@title,'" + roomName + "')]")))
				log.warn("Verified the room " + roomName + " is listed in the TOC and the location is correct.");
			
			goRoom(roomName);
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + roomName + "']")))
				log.warn("Verified the room is opened.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + Users.PlaceName + "']")))
				log.warn("Verified there is a breadcrumb link in the room back to the main place.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[contains(@class,'toc') and contains(@title,'Room Index')]")))
				log.warn("Verified the Room TOC is displayed.");
			
			driver.switchTo().frame(qfd_Page.getTextFrame());
			Thread.sleep(Wait.PAUSE);
			
			if(asserter.verifyExist(asserter.new XPath("//td[contains(text(),'As the room manager')]")))
				log.warn("Verified there are instructions for using the room on home page.");
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			goRoomMembers();
			
			driver.switchTo().frame(qfd_Members.getMembersIFrame());
			Thread.sleep(Wait.PAUSE);
			
			qfd_Members.getAuthorTabRoom().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + groupName + "']")))
				log.warn("Verified the group " + groupName + " has author access.");
			
			qfd_Members.getEditorTabRoom().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + editorName + "']")))
				log.warn("Verified the user " + editorName + " has editor access.");
			
			qfd_Members.getOwnerTabRoom().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + ownerName + "']")))
				log.warn("Verified the user " + ownerName + " has owner access.");
			
			qfd_Members.getReaderTabRoom().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//td[contains(text(),'No members at this access level')]")))
				log.warn("Verified  there are no users listed under Readers.");
			
			qfd_Members.getManagerTabRoom().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//td[contains(text(),'No members at this access level')]")))
				log.warn("Verified  there are no users listed under Managers.");
			
			qfd_Members.getReaderTabRoom().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Members.getAddReaderRoom().click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyTrue(driver.findElements(By.xpath("//input[@type='checkbox']")).size() == 3))
				log.warn("Verified that only the members of the Place are listed.");
				
			if(asserter.verifyExist(asserter.new XPath("//td[text()='" + editorName + "']")))
				log.warn("Verified the user " + editorName + " is in the list.");
				
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + groupName + "']")))
				log.warn("Verified the group " + groupName + " is in the list.");
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			asserter.elementClick(qfd_Members.getCloseButton(), asserter.new XPath("//iframe[@id='contentViewerIframe']"), false);
			Thread.sleep(Wait.SHORT);
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Create Room And Verify Members Inherited From Place'.");
		log.info("************************************");
		
	}

	public void verifyRoomOthers(String pageName, String roomName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Verify Other Room Stuffs'...");
		Thread.sleep(Wait.MID);
		
		try{
		
			newPage();
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Create New Page']")))
				log.warn("Verified new page webpage is displayed.");
			
			qfd_Library.getPageName().sendKeys(pageName);
			Thread.sleep(Wait.SHORT);
			
			asserter.elementClickWait(qfd_Library.getSaveCheckIn(), asserter.new XPath("//a[contains(text(),'Subscribe')]"), true);
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyEquals((driver.findElement(By.xpath("//div[@class='lotusMessage lotusConfirm']")).getText()), "Successfully saved '" + pageName + "'"));
				log.warn("Verified the text : Successfully saved '" + pageName + "' is correct.");
			
			if(asserter.verifyExist("Successfully saved"))
				log.warn("Verified the text : Successfully saved '" + pageName + "' exists.");
			
			goRoomIndex();
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + pageName + "']")))
				log.warn("Verified the new Post is displayed in the Room Index.");
			
			returnParent();
			
			if(asserter.verifyExist(asserter.new XPath("//span/a[text()='" + Users.PlaceName + "']")))
				log.warn("Verified user is taken to the parent Place by the link on the top of TOC.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify Other Room Stuffs'.");
		log.info("************************************");
		
	}

	public void customizeRoom(String roomName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Customize Room'...");
		Thread.sleep(Wait.MID);
		
		try{
		
			goRoom(roomName);
			
			qfd_Place.getCustomize().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().frame(qfd_Place.getCutomizeFrame());
			Thread.sleep(Wait.PAUSE);
			
			qfd_Place.getBasics().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='Basics']")))
				log.warn("Verified the Basics setting page appears.");
			
			qfd_Room.getRoomName().click();
			Thread.sleep(Wait.PAUSE);
			action.sendKeys(Keys.END).perform();
			Thread.sleep(Wait.PAUSE);
			qfd_Room.getRoomName().sendKeys(" Updated");
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
			
			
			action.sendKeys(Keys.TAB).perform();
			Thread.sleep(Wait.PAUSE*3);
			action.sendKeys(Keys.TAB).perform();
			Thread.sleep(Wait.PAUSE*3);
			action.sendKeys(Keys.UP).perform();
			Thread.sleep(Wait.PAUSE);
						
			qfd_Place.getDone().click();
			Thread.sleep(Wait.MID);
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			asserter.elementClickWait(qfd_Place.getClose(), asserter.new XPath("//span[text()='Customize']"), false);
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + roomName + " Updated']")))
				log.warn("Verified the Room has been renamed.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Customize Room'.");
		log.info("************************************");
		
	}

	public void deleteRoom(String roomName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Delete Room'...");
		Thread.sleep(Wait.MID);
		
		try{
			
			qfd_Place.getCustomize().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().frame(qfd_Place.getCutomizeFrame());
			Thread.sleep(Wait.PAUSE);
			
			qfd_Place.getBasics().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='Basics']")))
				log.warn("Verified the Basics setting page appears.");
			
			qfd_Room.getDeleteRoom().click();
			Thread.sleep(Wait.MID);
			
			qfd_Place.getDeleteConfirm().sendKeys("Junk");
			Thread.sleep(Wait.SHORT);
			
			qfd_Room.getDeleteRoom().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().alert().accept();
			log.info("Clicking on OK button.");
			Thread.sleep(Wait.SHORT);
			
			qfd_Place.getDeleteConfirm().clear();
			Thread.sleep(Wait.PAUSE);
			qfd_Place.getDeleteConfirm().sendKeys("yes");
			Thread.sleep(Wait.SHORT);
			
			qfd_Room.getDeleteRoom().click();
			Thread.sleep(Wait.SHORT);
			
			//Mary deleted this on 03/21/2013
//			if(asserter.verifyExist(asserter.new XPath("//h1[text()='Deleting Room']")))
//				log.warn("Verified the Deleting screen is displayed.");
			
			Thread.sleep(Wait.VERYLONG);
			
			//Mary deleted this on 03/21/2013
//			driver.switchTo().defaultContent();
//			Thread.sleep(Wait.PAUSE);
			
			if(asserter.verifyExist(asserter.new XPath("//span/a[text()='" + Users.PlaceName + "']")))
				log.warn("Verified user is taken to the parent Place.");
			
			if(asserter.verifyNotExist(asserter.new XPath("//a[contains(@class,'toc') and contains(@title,'" + roomName + "')]")))
				log.warn("Verified the room is no longer listed in the Place TOC.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Delete Room'.");
		log.info("************************************");
		
	}

	public void createLink(String linkName, String url) throws Exception {

		log.info("************************************");
		log.info("Starting 'Create Link'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			newLink();

			qfd_Library.getLinkPageName().sendKeys(linkName);
			Thread.sleep(Wait.SHORT);
			
			qfd_Library.getLinkURLText().clear();
			Thread.sleep(Wait.VERYSHORT);
			qfd_Library.getLinkURLText().sendKeys(url);
			Thread.sleep(Wait.SHORT);
			
			asserter.elementClickWait(qfd_Library.getSaveCheckIn(), asserter.new XPath("//a[contains(text(),'Subscribe')]"), true);
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist("Successfully saved"))
				log.warn("Verified the text : Successfully saved '" + linkName + "' exists.");	
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Create Link'.");
		log.info("************************************");
		
	}

	public void createFolder(String folderName, String desc) throws Exception {

		log.info("************************************");
		log.info("Starting 'Create Folder'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			newFolder();

			qfd_Library.getFolderName().sendKeys(folderName);
			Thread.sleep(Wait.SHORT);
			
			qfd_Library.getFolderDesc().sendKeys(desc);
			Thread.sleep(Wait.SHORT);
			
			asserter.elementClickWait(qfd_Library.getOK(), asserter.new XPath("//a[text()='Change location']"), false);
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist("The folder was successfully created"))
				log.warn("Verified the text : 'The folder was successfully created' exists.");
			
			goRoomIndex();
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Create Folder'.");
		log.info("************************************");
		
	}
	
}
