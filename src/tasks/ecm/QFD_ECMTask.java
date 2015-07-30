package tasks.ecm;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import config.Element;
import config.Task;
import config.Users;
import config.Wait;
import exceptions.ElementException;

import tasks.common.QFD_RoomTask;
import tasks.toc.QFD_LibraryTask;
import util.Assert;
import util.Assert.XPath;

import junit.framework.*;

import appobjects.ecm.QFD_ECM;
import appobjects.toc.QFD_Library;

public class QFD_ECMTask {
	
	private WebDriver driver;
	private Logger log;
	private Assert asserter;
	private Actions action;
	private Users user;
	QFD_LibraryTask qfd_LibraryTask = null;
	QFD_RoomTask qfd_RoomTask = null;
	QFD_Library qfd_Library = null;
	QFD_ECM qfd_ECM = null;
	
	public QFD_ECMTask(WebDriver driver,Logger log){
		
		this.driver = driver;
		this.log = log;
		this.asserter = new Assert(driver, log);
		this.user = new Users();
		qfd_LibraryTask = new QFD_LibraryTask(driver, log);
		action = new Actions(driver);
		qfd_Library = new QFD_Library(driver);
		qfd_RoomTask = new QFD_RoomTask(driver, log);
		qfd_ECM = new QFD_ECM(driver);
		
	}

	public void createDocLinkSummaryAndDownloadPage(String pageName,
			String ECMURL, String ECMUser, String ECMPW, boolean isRoom) throws Exception {

		log.info("************************************");
		log.info("Starting 'Create Document Link'...");
		Thread.sleep(Wait.MID);
		
		try{
			
			if(!isRoom){
				
				qfd_LibraryTask.goLibrary();
				qfd_LibraryTask.newPage();
				
			}else{
				
				qfd_RoomTask.newPage();
				
			}			
			
			qfd_Library.getPageName().sendKeys(pageName);
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().frame(qfd_Library.getRichTextFrame());
			Thread.sleep(Wait.PAUSE);

			driver.switchTo().defaultContent();
			qfd_ECM.getInsertLinkButtonCKEditor().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().frame(qfd_ECM.getInsertLinkFrame());
			Thread.sleep(Wait.PAUSE);
			
			qfd_ECM.getInsertLinkToExtDoc().click();
			Thread.sleep(Wait.MID);
			
			driver.switchTo().defaultContent();
			qfd_ECM.getInsertClose().click();
			Thread.sleep(Wait.SHORT);
			
			action.sendKeys(Keys.ENTER).perform();
			log.info("Close the frame.");
			
			qfd_ECM.getInsertLinkButtonCKEditor().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().frame(qfd_ECM.getInsertLinkFrame());
			Thread.sleep(Wait.PAUSE);
			
			qfd_ECM.getInsertLinkToExtDoc().click();
			Thread.sleep(Wait.SHORT);
			
			logInECMServer(ECMURL, ECMUser, ECMPW);
			
			qfd_ECM.getECM316LibraryItem().click();
			Thread.sleep(Wait.SHORT);
			qfd_ECM.getECMLibraryDoc().click();
			Thread.sleep(Wait.MID);
			
			qfd_ECM.getInsertLinkFooter().click();
			Thread.sleep(Wait.SHORT);

			qfd_ECM.getInsertLinkButton().click();
			Thread.sleep(Wait.SHORT);
			
			action.sendKeys(Keys.ENTER).perform();
			Thread.sleep(Wait.MID);
			log.info("Clicking on Summary Page item.");
			
			qfd_Library.getRichTextFrame().sendKeys(Keys.END);
			Thread.sleep(Wait.VERYSHORT);
			qfd_Library.getRichTextFrame().sendKeys(Keys.ENTER);
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().defaultContent();
			qfd_ECM.getInsertLinkButtonCKEditor().click();
			Thread.sleep(Wait.PAUSE);
			
			driver.switchTo().frame(qfd_ECM.getInsertLinkFrame());
			qfd_ECM.getInsertLinkToExtDoc().click();
			Thread.sleep(Wait.MID);
			
			logInECMServer(ECMURL, ECMUser, ECMPW);
			
			qfd_ECM.getECM316LibraryItem().click();
			Thread.sleep(Wait.SHORT);
			qfd_ECM.getECMLibraryJPG().click();
			Thread.sleep(Wait.MID);
			
			qfd_ECM.getInsertLinkFooter().click();
			Thread.sleep(Wait.SHORT);

			qfd_ECM.getInsertLinkButton().click();
			Thread.sleep(Wait.MID);
			
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ENTER).perform();
			Thread.sleep(Wait.SHORT);
			log.info("Clicking on File Download item.");

			if(!isRoom){
				
				asserter.elementClickWait(qfd_Library.getSaveCheckIn(), asserter.new XPath("//a[text()='Subscribe to this folder']"), true);
				Thread.sleep(Wait.SHORT);
				
			}else{
				
				asserter.elementClickWait(qfd_Library.getSaveCheckIn(), asserter.new XPath("//a[text()='Subscribe to this document']"), true);
				Thread.sleep(Wait.SHORT);
				
			}
			
		
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Create Document Link'.");
		log.info("************************************");
	
	}

	private void logInECMServer(String ECMURL, String ECMUser, String ECMPW) throws Exception {
		
		Thread.sleep(Wait.SHORT);

		driver.switchTo().defaultContent();
		
		qfd_ECM.getECMServerField().click();
		qfd_ECM.getECMServerField().clear();
		
		qfd_ECM.getECMServerField().sendKeys(ECMURL);
		qfd_ECM.getECMServerField().sendKeys(Keys.TAB);
		Thread.sleep(Wait.SHORT);

		asserter.checkboxClick(asserter.new XPath("//*/input[@class='documentPickerLeft']"), false);
		Thread.sleep(Wait.SHORT);

		qfd_ECM.getECMServerUserID().clear();
		qfd_ECM.getECMServerUserID().sendKeys(ECMUser);
		Thread.sleep(Wait.SHORT);

		qfd_ECM.getECMServerUserPwd().clear();
		qfd_ECM.getECMServerUserPwd().sendKeys(ECMPW);
		Thread.sleep(Wait.SHORT);

		qfd_ECM.getECMServerLogInButton().click();
		Thread.sleep(Wait.SHORT);
		
		log.info("Logged into ECM server");
		
	}

	public void configureCusLib(String cusLibName, String ECMURL,
			String ECMUser, String ECMPW) throws Exception {
	
		log.info("************************************");
		log.info("Starting 'Configure Custom Library'...");
		Thread.sleep(Wait.MID);
		
		try{

			qfd_LibraryTask.goItem(cusLibName);
			
			if(asserter.verifyExist(asserter.new XPath("//a[@title='More Actions']")))
				log.warn("Verified the 'More Actions' button exists");
		
			if(asserter.verifyNotExist("Check Out and Edit"))
				log.warn("Verified there is no 'Check Out and Edit' button");
			
			if(asserter.verifyNotExist("Updated by"))
				log.warn("Verified there is no 'Updated by' info");
			
			qfd_ECM.getConfigureLink().click();
			Thread.sleep(Wait.MID);

			logInECMServer(ECMURL, ECMUser, ECMPW);

			qfd_ECM.getECMLibraryItem().click();
			Thread.sleep(Wait.SHORT);

			qfd_ECM.getECMLibraryFolderItem().click();
			Thread.sleep(Wait.SHORT);
			
			asserter.elementClickWait(qfd_Library.getOK(), asserter.new XPath("//a[text()='Download']"), true);
			Thread.sleep(Wait.MID);

			if(asserter.verifyExist(asserter.new XPath("//a[@title='More Actions']")))
				log.warn("Verified the 'More Actions' button exists");

			if(asserter.verifyExist(asserter.new XPath("//a[@title='Upload a file from your computer']")))
				log.warn("Verify the 'Upload a File' button exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[@title='Create a new folder']")))
				log.warn("Verify the 'New Folder' button exists.");

			if(asserter.verifyExist(asserter.new XPath("//button[@title='Views']")))
				log.warn("Verify the 'Views' button exists.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Configure Custom Library'.");
		log.info("************************************");
		
	}

	public void verifyPublishWizard(String pageName, String ECMURL,
			String ECMUser, String ECMPW, boolean isRoom) throws Exception {
		
		log.info("************************************");
		log.info("Starting 'Verify Publish Wizard'...");
		Thread.sleep(Wait.MID);
		
		try{
			
			if(!isRoom)
				qfd_LibraryTask.goLibrary();
			
			else qfd_RoomTask.goRoomIndex();
			
			qfd_LibraryTask.goItem(pageName);
			
			qfd_ECM.getPublishToButton().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_ECM.getCancel().click();
			Thread.sleep(Wait.SHORT);

			if(asserter.verifyNotExist(asserter.new XPath("//span[text()='Publish to External Location']")))
				log.warn("Verify the 'Cancel' link works well.");
	
			qfd_ECM.getPublishToButton().click();
			Thread.sleep(Wait.SHORT);
	
			if(asserter.verifyExist(asserter.new XPath("//img[@alt='Help']")))
				log.warn("Verify the help icon exists.");
	
			if(asserter.elementClick(qfd_ECM.getHelp(), asserter.new XPath("//h3[text()='Server URL:']"), true))
				log.warn("Verify the infomation pops up correctly.");
			
			logInECMServer(ECMURL, ECMUser, ECMPW);		
			
			qfd_ECM.getECMLibraryItem().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_ECM.getECMLibraryFolderItem().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("/html/body/div/table/tbody/tr/td[text()='" + pageName + "']")))
				log.warn("Verify the document's name is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("/html/body/div/table/tbody/tr[2]/td[@dojoattachpoint='publishLocationNode']")))
				log.warn("Verified the document's path is correct.");
			
			qfd_ECM.getPublishNextButton().click();
			Thread.sleep(Wait.SHORT);
	
			if(asserter.elementClick(qfd_ECM.getHelp(), asserter.new XPath("//h3[text()='Replace with a link: ']"), true))
				log.warn("Verified the infomation pops up correctly.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='< Previous']")))
				log.warn("Verified the 'Previous' button exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Cancel']")))
				log.warn("Verified the 'Cancel' link exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//img[@alt='Help']")))
				log.warn("Verified the help icon exists.");
			
			qfd_ECM.getCancel().click();
			Thread.sleep(Wait.SHORT);
			
			if(!isRoom)
				qfd_LibraryTask.goLibrary();
			
			else qfd_RoomTask.goRoomIndex();
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify Publish Wizard'.");
		log.info("************************************");
		
	}

	public void publishToCopy(String pageName, String ECMURL, String ECMUser,
			String ECMPW, boolean isRoom) throws Exception {

		log.info("************************************");
		log.info("Starting 'Publish To Copy'...");
		Thread.sleep(Wait.MID);
		
		try{
			
			publishToProcedure(pageName, ECMURL, ECMUser, ECMPW);

			qfd_ECM.getCopyRadiobox().click();
			Thread.sleep(Wait.SHORT);

			asserter.elementClickWait(qfd_ECM.getPublishDoneButton(), asserter.new XPath("//span[text()='Select Publish Option']"), false);
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Published Externally By Me']")))
				log.warn("Verified the page '" + pageName + "' published successfully.");
			
			if(!isRoom)
				qfd_LibraryTask.goLibrary();
			
			else qfd_RoomTask.goRoomIndex();

			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + pageName + "']")))
				log.warn("Verified the page '" + pageName + "' is still in the Library.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Publish To Copy'.");
		log.info("************************************");
		
	}

	public void publishToProcedure(String pageName, String ECMURL, String ECMUser,
			String ECMPW) throws Exception {

		qfd_LibraryTask.goItem(pageName);
		
		qfd_ECM.getPublishToButton().click();
		Thread.sleep(Wait.SHORT);

		logInECMServer(ECMURL, ECMUser, ECMPW);

		qfd_ECM.getECMLibraryItem().click();
		Thread.sleep(Wait.SHORT);
		
		qfd_ECM.getECMLibraryFolderItem().click();
		Thread.sleep(Wait.SHORT);

		qfd_ECM.getPublishNextButton().click();
		Thread.sleep(Wait.SHORT);
		
	}

	public void verifyDocOnECMExist(String pageName, String cusLibName, boolean hasAttachments, boolean isRoom) throws Exception {
		
		log.info("************************************");
		log.info("Starting 'Verify Document On ECM Server'...");
		Thread.sleep(Wait.MID);
		
		try{
			
			if(!isRoom)
				qfd_LibraryTask.goLibrary();

			else qfd_RoomTask.goRoomIndex();
			
			asserter.elementClickWait(new Element(driver.findElement(By.linkText(cusLibName)), "ECM lib"), asserter.new XPath("//a[text()='Download']"), true);
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist(pageName))
				log.warn("Verified the page '" + pageName + "' has published in ECM server.");
			
			new Element(driver.findElement(By.partialLinkText(pageName)), "Page: " + pageName).click();
			Thread.sleep(Wait.MID);
			
			if(hasAttachments){
				
				if(asserter.verifyExist(asserter.new XPath("//a[@title='Delete this attachment']")))
					log.warn("Verified the attachments are correct.");
				
			}else{
				
				if(asserter.verifyNotExist(asserter.new XPath("//a[@title='Delete this attachment']")))
					log.warn("Verified there is no attachments.");

			}
			
			if(!isRoom)
				qfd_LibraryTask.goLibrary();

			else qfd_RoomTask.goRoomIndex();
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify Document On ECM Server'.");
		log.info("************************************");
		
	}

	public void publishToLink(String pageName, String ECMURL, String ECMUser,
			String ECMPW, boolean isRoom) throws Exception {

		log.info("************************************");
		log.info("Starting 'Publish To Link'...");
		Thread.sleep(Wait.MID);
		
		try{
			
			publishToProcedure(pageName, ECMURL, ECMUser, ECMPW);

			qfd_ECM.getReplaceRadiobox().click();
			Thread.sleep(Wait.SHORT);

			asserter.elementClickWait(qfd_ECM.getPublishDoneButton(), asserter.new XPath("//span[text()='Select Publish Option']"), false);
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Published Externally By Me']")))
				log.warn("Verified the page '" + pageName + "' published successfully.");
			
			if(!isRoom)
				qfd_LibraryTask.goLibrary();

			else qfd_RoomTask.goRoomIndex();
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='link to " + pageName + "']")))
				log.warn("Verified the page '" + pageName + "' has replaced with a link.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Publish To Link'.");
		log.info("************************************");
		
	}

	public void publishToMove(String pageName, String ECMURL, String ECMUser,
			String ECMPW, boolean isRoom) throws Exception {

		log.info("************************************");
		log.info("Starting 'Publish To Move'...");
		Thread.sleep(Wait.MID);
		
		try{
			
			publishToProcedure(pageName, ECMURL, ECMUser, ECMPW);

			qfd_ECM.getMoveRadiobox().click();
			Thread.sleep(Wait.SHORT);

			asserter.elementClickWait(qfd_ECM.getPublishDoneButton(), asserter.new XPath("//span[text()='Select Publish Option']"), false);
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Published Externally By Me']")))
				log.warn("Verified the page '" + pageName + "' published successfully.");
			
			if(!isRoom)
				qfd_LibraryTask.goLibrary();

			else qfd_RoomTask.goRoomIndex();

			if(asserter.verifyNotExist(pageName))			
				log.warn("Verified the page '" + pageName + "' is no longer in the Library.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Publish To Move'.");
		log.info("************************************");
			
	}

	public void verifyDocOnECM(String pageName, String ECMLibName, boolean hasAttachments, boolean isRoom) throws Exception {
		
		log.info("************************************");
		log.info("Starting 'Verify Document On ECM Server'...");
		Thread.sleep(Wait.MID);
		
		try{
			
			if(!isRoom)
				qfd_LibraryTask.goLibrary();
			
			else qfd_RoomTask.goRoomIndex();
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		qfd_LibraryTask.createCusLib(ECMLibName, null, isRoom);
			
		configureCusLib(ECMLibName, user.ECMURL, user.ECMURL, user.ECMPW);
	
		verifyDocOnECMExist(pageName, ECMLibName, hasAttachments, isRoom);
		
		log.info("End of 'Verify Document On ECM Server'.");
		log.info("************************************");
		
	}
	
}