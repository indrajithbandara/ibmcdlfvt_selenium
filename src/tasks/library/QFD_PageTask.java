package tasks.library;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import junit.framework.AssertionFailedError;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.opera.core.systems.scope.protos.ExecProtos.ActionList.Action;

import config.BrowserSetup;
import config.Element;
import config.Wait;
import exceptions.AssertFailException;
import exceptions.ElementException;

import appobjects.library.QFD_Page;
import appobjects.toc.QFD_Library;

import tasks.common.AutoITTask;
import tasks.toc.QFD_LibraryTask;
import util.Assert;
import util.Assert.XPath;

public class QFD_PageTask {

	private WebDriver driver;
	private Logger log;
	private Assert asserter;
	private Actions action = null;
	private AutoITTask AT = null;
	QFD_Library qfd_Library = null;
	QFD_LibraryTask qfd_LibraryTask = null;
	QFD_Page qfd_Page = null;
	
	public QFD_PageTask(WebDriver driver, Logger log){
		
		this.driver = driver;
		this.log = log;
		this.asserter = new Assert(driver, log);
		action = new Actions(driver);
		AT = new AutoITTask(driver, log);
		qfd_LibraryTask = new QFD_LibraryTask(driver, log);
		qfd_Library = new QFD_Library(driver);
		qfd_Page = new QFD_Page(driver);
		
	}

	public void downloadAttach(String pageName, String fileType) throws Exception {

		log.info("************************************");
		log.info("Starting 'Download Page Attachment'...");
		Thread.sleep(Wait.MID);
		
		try{

			driver.switchTo().defaultContent();
			
			qfd_Page.getDownload().click();
			Thread.sleep(Wait.MID);
			
			action.sendKeys(Keys.ENTER).perform();
			Thread.sleep(Wait.SHORT);
			
			if(BrowserSetup.BrowserType.equalsIgnoreCase("firefox")){
				
				if(fileType.equalsIgnoreCase("Word")){
					
					AT.setFocusDoKeys("Opening", "!o");
					AT.setFocusDoKeys("Opening", "{ENTER}");
					
					if(AT.checkForDialog("Word", Wait.VERYSHORT)){
						
						AT.setFocusDoKeys("Microsoft Word", "{ESC}");
					
						AT.setFocusDoKeys("Microsoft Word", "!{F4}N");
						Thread.sleep(Wait.SHORT);
				
					}
					
				}else if(fileType.equalsIgnoreCase("Excel")){
					
					AT.setFocusDoKeys("Opening", "!o");
					AT.setFocusDoKeys("Opening", "{ENTER}");
					
					if(AT.checkForDialog("Microsoft", Wait.VERYSHORT)){
						
						AT.setFocusDoKeys("Microsoft Excel", "{ESC}");
					
						AT.setFocusDoKeys("Microsoft Excel", "!{F4}N");
						Thread.sleep(Wait.SHORT);
				
					}
					
				}else{
					
					new AssertFailException(driver, log, new AssertionFailedError("Wrong file type!"));
					
				}
				
			}else if(BrowserSetup.BrowserType.equalsIgnoreCase("internetexplorer")){
				
				if(fileType.equalsIgnoreCase("word")){
				
					AT.setFocusDoKeys("Lotus", "^j");
					Thread.sleep(Wait.PAUSE);
					
					AT.setFocusDoKeys("View", "{TAB}");
					AT.setFocusDoKeys("View", "{RIGHT}");
					AT.setFocusDoKeys("View", "{ENTER}");
					
					Thread.sleep(Wait.LONG);
						
					AT.setFocusDoKeys("Microsoft Word", "!{F4}N");
					Thread.sleep(Wait.MID);
					
					AT.setFocusDoKeys("View", "!{F4}N");
					Thread.sleep(Wait.SHORT);
			
				}else if(fileType.equalsIgnoreCase("Excel")){
					
					AT.setFocusDoKeys("Lotus", "^j");
					Thread.sleep(Wait.PAUSE);
					
					AT.setFocusDoKeys("View", "{RIGHT}");
					AT.setFocusDoKeys("View", "{ENTER}");
					
					Thread.sleep(Wait.LONG);
						
					AT.setFocusDoKeys("Microsoft Excel", "!{F4}N");
					Thread.sleep(Wait.MID);
					
					AT.setFocusDoKeys("View", "!{F4}N");
					Thread.sleep(Wait.SHORT);
					
				}else{
					
					new AssertFailException(driver, log, new AssertionFailedError("Wrong file type!"));
					
				}
				
			}
			
			driver.navigate().refresh();
			Thread.sleep(Wait.SHORT);
			
			qfd_LibraryTask.goLibrary();
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Download Page Attachment'.");
		log.info("************************************");
		
	}

	public void verifyPage(String pageName, String pageDesc, String attachName, String authorName, String type) throws Exception {
	
		log.info("************************************");
		log.info("Starting 'Verify Page'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			qfd_LibraryTask.goLibrary();
			
			if(asserter.verifyExist(asserter.new XPath("//img[@alt='Page with Attachments']")))
				log.warn("Verified the type icon is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + pageName + "']")))
				log.warn("Verified the page name is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Today']")))
				log.warn("Verified the last modified time is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + authorName + "']")))
				log.warn("Verified the author's name is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//img[@alt='Click for attachments']")))
				log.warn("Verified the download icon is correct.");
			
			if(asserter.verifyNotExist(asserter.new XPath("//img[contains(@alt,'Checked out')]")))
				log.warn("Verified there is no lock icon.");
			
			qfd_LibraryTask.goItem(pageName);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='" + pageName + "']")))
				log.warn("Verified the page title is correct.");
			
			if(asserter.verifyExist("today"))
				log.warn("Verified the date is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + authorName + "']")))
				log.warn("Verified the author's name is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Check Out and Edit']")))
				log.warn("Verified the Check Out and Edit button is active.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[@title='More Actions']")))
				log.warn("Verified the More Actions button is active.");
			
			if(type.equalsIgnoreCase("word")){
				
				if(asserter.verifyExist(asserter.new XPath("//img[@alt='Word Processing Document']")))
					log.warn("Verified the type icon is correct.");
				
			}else if(type.equalsIgnoreCase("excel")){
				
				if(asserter.verifyExist(asserter.new XPath("//img[@alt='Spreadsheet']")))
					log.warn("Verified the type icon is correct.");
				
			}else{
				
				new AssertFailException(driver, log, new AssertionFailedError("Invalid file type"));
				
			}
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Preview']")))
				log.warn("Verified the Preview button is active.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[contains(@title,'Download " + attachName + "')]")))
				log.warn("Verified the Download button is active.");
			
			qfd_Page.getAbout().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='This page is published and checked in']")))
				log.warn("Verified the publish status is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Created: ']")))
				log.warn("Verified the Created infomation is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Modified: ']")))
				log.warn("Verified the Modified infomation is correct.");
			
			qfd_Page.getComments().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Add Comment']")))
				log.warn("Verified the Add Comment link is correct.");
			
			qfd_Page.getVersions().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Save Current as New Version']")))
				log.warn("Verified the Save Current as New Version link is correct.");
			
			driver.switchTo().frame(qfd_Page.getTextFrame());
			Thread.sleep(Wait.VERYSHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//p[contains(text(),'" + pageDesc + "')]")))
				log.warn("Verified the context " + pageDesc + " is correct.");
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_LibraryTask.goLibrary();
			Thread.sleep(Wait.SHORT);
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify Page'.");
		log.info("************************************");
		
	}

	public void verifyUpload(String fileName, String desc, String authorName, String attachName, String type) throws Exception {
		
		log.info("************************************");
		log.info("Starting 'Verify Upload'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			qfd_LibraryTask.goLibrary();
			
			if(type.equalsIgnoreCase("word")){
				
				if(asserter.verifyExist(asserter.new XPath("//img[@alt='Word Processing Document']")))
					log.warn("Verified the type icon is correct.");
				
			}else if(type.equalsIgnoreCase("excel")){
				
				if(asserter.verifyExist(asserter.new XPath("//img[@alt='Spreadsheet']")))
					log.warn("Verified the type icon is correct.");
				
			}else{
				
				new AssertFailException(driver, log, new AssertionFailedError("Invalid file type"));
				
			}
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + fileName + "']")))
				log.warn("Verified the name is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Today']")))
				log.warn("Verified the last modified time is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + authorName + "']")))
				log.warn("Verified the author's name is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//img[@alt='Click for attachments']")))
				log.warn("Verified the download icon is correct.");
			
			if(asserter.verifyNotExist(asserter.new XPath("//img[contains(@alt,'Checked out')]")))
				log.warn("Verified there is no lock icon.");
			
			qfd_LibraryTask.goItem(fileName);
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//div[contains(text(),'You are in')]")).getText().contains(fileName)))
				log.warn("Verified the breadcrumb of is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='" + fileName + "']")))
				log.warn("Verified the page title is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='This page is published and checked in']")))
				log.warn("Verified the publish status is correct.");
			
			Calendar now = Calendar.getInstance();         		
			SimpleDateFormat sdf = new SimpleDateFormat("MMMM"); 
			String month = sdf.format(now.getTime());
			//June
			sdf = new SimpleDateFormat("d"); 
//			sdf = new SimpleDateFormat("dd"); 
			String day = sdf.format(now.getTime());
			
			if(asserter.verifyTrue(driver.findElements(By.xpath("//span[contains(text(),'" + month + " " + day + "')]")).size() == 2))
				log.warn("Verified the Create date and Last Modified date are correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + authorName + "']")))
				log.warn("Verified the author's name is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Replace']")))
				log.warn("Verified the Replace button is active.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[@title='More Actions']")))
				log.warn("Verified the More Actions button is active.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Preview']")))
				log.warn("Verified the Preview link is active.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[contains(@title,'Download " + attachName + "')]")))
				log.warn("Verified the Download link is active.");
			
			if(type.equalsIgnoreCase("word")){
				
				if(asserter.verifyExist(asserter.new XPath("//img[@alt='Word Processing Document']")))
					log.warn("Verified the type icon is correct.");
				
			}else if(type.equalsIgnoreCase("excel")){
				
				if(asserter.verifyExist(asserter.new XPath("//img[@alt='Spreadsheet']")))
					log.warn("Verified the type icon is correct.");
				
			}else{
				
				new AssertFailException(driver, log, new AssertionFailedError("Invalid file type"));
				
			}
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Created: ']")))
				log.warn("Verified the Created infomation is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Modified: ']")))
				log.warn("Verified the Modified infomation is correct.");
			
			qfd_Page.getComments().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Add Comment']")))
				log.warn("Verified the Add Comment link is correct.");
			
			qfd_Page.getVersions().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Save Current as New Version']")))
				log.warn("Verified the Save Current as New Version link is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//div[contains(text(),'" + desc + "')]")))
				log.warn("Verified the context " + desc + " is correct.");
			
			//Old, Mary
			/*
			if(asserter.verifyNotExist(asserter.new XPath("//span[text()='Summary of Changes']")))
				log.warn("Verified no version is created.");
			*/
			//new, Mary
			if(!asserter.verifyExist(asserter.new XPath("//span[text()='Summary of Changes']")))
				log.warn("Verified no version is created.");
			
			qfd_LibraryTask.goLibrary();
			Thread.sleep(Wait.SHORT);
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify Upload'.");
		log.info("************************************");
		
	}
	
	public void verifyBusinessCard (String authorName, String role) throws Exception {
		
		log.info("************************************");
		log.info("Starting 'Verify Business Card'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			action.moveToElement(driver.findElement(By.linkText(authorName))).perform();
			Thread.sleep(Wait.PAUSE);
			
			qfd_Page.getCard().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//h2[text()='" + authorName + "']")))
				log.warn("Verified the display name is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='" + role + "']")))
				log.warn("Verified the role is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[contains(text(),'@cn.ibm.com')]")))
				log.warn("Verified the email address is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//img[@alt='Close']")))
				log.warn("Verified the close icon is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Send Mail']")))
				log.warn("Verified the Send Mail link is correct.");
			
			qfd_Page.getCardClose().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_LibraryTask.goLibrary();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyFalse(driver.findElement(By.xpath("//span[text()='Send Mail']")).isDisplayed()))
				log.warn("Verified the Business Card disappears.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify Business Card'.");
		log.info("************************************");
		
	}

	public void verifyEdit(String pageName, String pageDesc, boolean isDraft) throws Exception {

		log.info("************************************");
		log.info("Starting 'Verify Edit Page'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			qfd_LibraryTask.goLibrary();
			
			qfd_LibraryTask.goItem(pageName);
			
			if(!isDraft)
				qfd_Page.getCheckOut().click();
			
			else qfd_Page.getEdit().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist("This page is checked out while you edit it"))
				log.warn("Verified the In-line message is correct.");
	
			if(asserter.verifyEquals(pageName, qfd_Library.getPageName().getAttribute("value")))
				log.warn("Verified the page name is correct.");
			
			driver.switchTo().frame(qfd_Library.getRichTextFrame());
			Thread.sleep(Wait.PAUSE);
			
			qfd_Library.getPageDesc().click();
			Thread.sleep(Wait.VERYSHORT);
			
			if(asserter.verifyEquals(pageDesc, qfd_Library.getPageDesc().getText()))
				log.warn("Verified the content in the CKEditor is correct.");
			
			driver.switchTo().defaultContent();
			
			if(asserter.verifyExist(asserter.new XPath("//img[contains(@title,'Remove Attachment')]")))
				log.warn("Verified the attachment is correct.");
			
			qfd_Page.getCancel().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().alert().accept();
			Thread.sleep(Wait.SHORT);
			log.info("Clicking on OK button.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify Edit Page'.");
		log.info("************************************");
		
	}

	public void editPage(String pageName, String attachPath) throws Exception {
		
		log.info("************************************");
		log.info("Starting 'Edit Page'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			qfd_LibraryTask.goLibrary();
			
			qfd_LibraryTask.goItem(pageName);
			
			qfd_Page.getCheckOut().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Library.getLinkPageName().clear();
			Thread.sleep(Wait.VERYSHORT);	
			
			qfd_Library.getLinkPageName().sendKeys(pageName + " Updated");
			Thread.sleep(Wait.VERYSHORT);	
			
			driver.switchTo().frame(qfd_Library.getRichTextFrame());
			Thread.sleep(Wait.PAUSE);
			
			qfd_Library.getPageDesc().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Library.getPageDesc().sendKeys(Keys.CONTROL + "a");
			Thread.sleep(Wait.VERYSHORT);
			qfd_Library.getPageDesc().sendKeys(Keys.BACK_SPACE);
			Thread.sleep(Wait.VERYSHORT);
			log.info("Clearing the CKEditor.");
			
			qfd_Library.getPageDesc().sendKeys("Test Text Updated");
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().defaultContent();
			
			qfd_Page.getAttachDelete().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Library.getPageMoreAttach().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Library.getFileField().sendKeys(attachPath);
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().defaultContent();
			
			asserter.elementClickWait(qfd_Library.getSaveCheckIn(), asserter.new XPath("//a[contains(text(),'Subscribe to')]"), true);
			Thread.sleep(Wait.SHORT);
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Edit Page'.");
		log.info("************************************");
		
	}

	public void verifyDraft(String pageDraftName, String pageDesc, String attachName,
			String authorName) throws Exception {
		
		log.info("************************************");
		log.info("Starting 'Verify Page Draft'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			qfd_LibraryTask.goLibrary();
			
			if(asserter.verifyExist(asserter.new XPath("//img[@alt='Page with Attachments']")))
				log.warn("Verified the type icon is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + pageDraftName + "']")))
				log.warn("Verified the page name is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Today']")))
				log.warn("Verified the last modified time is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + authorName + "']")))
				log.warn("Verified the author's name is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//img[@alt='Click for attachments']")))
				log.warn("Verified the download icon is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//img[contains(@alt,'Checked out')]")))
				log.warn("Verified the lock icon is correct.");
			
			qfd_LibraryTask.goItem(pageDraftName);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='" + pageDraftName + "']")))
				log.warn("Verified the page title is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Draft' and @class='qkrDraftIndicator']")))
				log.warn("Verified the page status is correct.");
			
			if(asserter.verifyExist("today"))
				log.warn("Verified the date is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + authorName + "']")))
				log.warn("Verified the author's name is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Edit']")))
				log.warn("Verified the Edit button is active.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Check In']")))
				log.warn("Verified the Check In button is active.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[@title='More Actions']")))
				log.warn("Verified the More Actions button is active.");
			
			if(asserter.verifyExist(asserter.new XPath("//img[@alt='Word Processing Document']")))
				log.warn("Verified the type icon is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Preview']")))
				log.warn("Verified the Preview button is active.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[contains(@title,'Download " + attachName + "')]")))
				log.warn("Verified the Download button is active.");
			
			qfd_Page.getAbout().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='This page is a draft']")))
				log.warn("Verified the publish status is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Created: ']")))
				log.warn("Verified the Created infomation is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Modified: ']")))
				log.warn("Verified the Modified infomation is correct.");
			
			if(asserter.verifyNotExist("Comments"))
				log.warn("Verified there is no Comments tab.");
			
			if(asserter.verifyNotExist("Versions"))
				log.warn("Verified there is no Versions tab.");
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_LibraryTask.goLibrary();
			Thread.sleep(Wait.SHORT);
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify Page Draft'.");
		log.info("************************************");
		
	}

	public void editDraft(String pageDraftName, String attachPath) throws Exception {

		log.info("************************************");
		log.info("Starting 'Edit Page Draft'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			qfd_LibraryTask.goLibrary();
			
			qfd_LibraryTask.goItem(pageDraftName);
			
			qfd_Page.getEdit().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Library.getLinkPageName().clear();
			Thread.sleep(Wait.VERYSHORT);	
			
			qfd_Library.getLinkPageName().sendKeys(pageDraftName + " Updated");
			Thread.sleep(Wait.VERYSHORT);	
			
			driver.switchTo().frame(qfd_Library.getRichTextFrame());
			Thread.sleep(Wait.PAUSE);
			
			qfd_Library.getPageDesc().click();
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Library.getPageDesc().sendKeys(Keys.CONTROL + "a");
			Thread.sleep(Wait.VERYSHORT);
			qfd_Library.getPageDesc().sendKeys(Keys.BACK_SPACE);
			Thread.sleep(Wait.VERYSHORT);
			log.info("Clearing the CKEditor.");
			
			qfd_Library.getPageDesc().sendKeys("Test Text Updated");
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().defaultContent();
			
			qfd_Library.getPageMoreAttach().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Library.getFileField().sendKeys(attachPath);
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().defaultContent();
			
			asserter.elementClickWait(qfd_Library.getPageSaveDraft(), asserter.new XPath("//a[contains(text(),'Subscribe to')]"), true);
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist("Successfully saved a private draft"))
				log.warn("Verified the text : Successfully saved a private draft of '" + pageDraftName + "' exists.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Edit Page Draft'.");
		log.info("************************************");
		
	}

	public void verifyEditDraft(String pageDraftName, String pageDesc, String wordName, String excelName, String authorName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Verify Page Draft Edited'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			qfd_LibraryTask.goLibrary();
			
			if(asserter.verifyExist(asserter.new XPath("//img[@alt='Page with Attachments']")))
				log.warn("Verified the type icon is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + pageDraftName + "']")))
				log.warn("Verified the page name is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Today']")))
				log.warn("Verified the last modified time is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + authorName + "']")))
				log.warn("Verified the author's name is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//img[@alt='Click for attachments']")))
				log.warn("Verified the download icon is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//img[contains(@alt,'Checked out')]")))
				log.warn("Verified the lock icon is correct.");
			
			qfd_Page.getDownload().click();
			Thread.sleep(Wait.PAUSE);
			
			if(asserter.verifyExist(asserter.new XPath("//img[contains(@alt,'Checked out')]")))
				log.warn("Verified the lock icon is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//td[contains(text(),'" + wordName + "')]")) 
					&& asserter.verifyExist(asserter.new XPath("//td[contains(text(),'" + excelName + "')]")))
				log.warn("Verified the files are correct.");
			
			qfd_LibraryTask.goItem(pageDraftName);
			
			qfd_Page.getCheckIn().click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='" + pageDraftName + "']")))
				log.warn("Verified the page title is correct.");
			
			if(asserter.verifyExist("today"))
				log.warn("Verified the date is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + authorName + "']")))
				log.warn("Verified the author's name is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Check Out and Edit']")))
				log.warn("Verified the Check Out and Edit button is active.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[@title='More Actions']")))
				log.warn("Verified the More Actions button is active.");
			
			if(asserter.verifyExist(asserter.new XPath("//img[@alt='Word Processing Document']")))
				log.warn("Verified the type icon is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Preview']")))
				log.warn("Verified the Preview button is active.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[contains(@title,'Download " + wordName + "')]")) 
					&& asserter.verifyExist(asserter.new XPath("//a[contains(@title,'Download " + excelName + "')]")))
				log.warn("Verified the Download button is active.");
			
			qfd_Page.getAbout().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='This page is published and checked in']")))
				log.warn("Verified the publish status is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Created: ']")))
				log.warn("Verified the Created infomation is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Modified: ']")))
				log.warn("Verified the Modified infomation is correct.");
			
			qfd_Page.getComments().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Add Comment']")))
				log.warn("Verified the Add Comment link is correct.");
			
			qfd_Page.getVersions().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Save Current as New Version']")))
				log.warn("Verified the Save Current as New Version link is correct.");
			
			driver.switchTo().frame(qfd_Page.getTextFrame());
			Thread.sleep(Wait.VERYSHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//p[contains(text(),'" + pageDesc + "')]")))
				log.warn("Verified the context " + pageDesc + " is correct.");
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_LibraryTask.goLibrary();
			Thread.sleep(Wait.SHORT);
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify Page Draft Edited'.");
		log.info("************************************");
		
	}

	public void saveVersion(String pageName, String authorName) throws Exception {
		
		log.info("************************************");
		log.info("Starting 'Save Current As New Version'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			qfd_LibraryTask.goItem(pageName);
			
			qfd_Page.getVersions().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Page.getSaveVersion().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().defaultContent();
			
			qfd_Page.getVersionsText().sendKeys("Test");
			Thread.sleep(Wait.SHORT);
			
			qfd_Library.getOK().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Save Current as New Version']")))
				log.warn("Verified Save Current as New Version is greyed out.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Delete Version']")))
				log.warn("Verified Delete Version is greyed out.");
			
			if(asserter.verifyExist(asserter.new XPath("//input[@type='checkbox' and contains(@title,'1') and @disabled]")))
				log.warn("Verified the checkbox is greyed out.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Current']")))
				log.warn("Verified the status is Current.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + authorName + "']")))
				log.warn("Verified the author's name is correct.");
		
			if(asserter.verifyExist(asserter.new XPath("//div[text()='Test']")))
				log.warn("Verified the version summary is correct.");
			
			qfd_Page.getCheckOut().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Library.getLinkPageName().clear();
			Thread.sleep(Wait.VERYSHORT);	
			
			qfd_Library.getLinkPageName().sendKeys(pageName + " Again");
			Thread.sleep(Wait.VERYSHORT);	
			
			driver.switchTo().defaultContent();
			
			asserter.elementClickWait(qfd_Library.getSaveCheckIn(), asserter.new XPath("//a[contains(text(),'Subscribe to')]"), true);
			Thread.sleep(Wait.SHORT);
			
			qfd_Page.getVersions().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Page.getSaveVersion().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().defaultContent();
			
			qfd_Page.getVersionsText().sendKeys("Test");
			Thread.sleep(Wait.SHORT);
			
			qfd_Library.getOK().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Save Current as New Version']")))
				log.warn("Verified Save Current as New Version is greyed out.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Delete Version']")))
				log.warn("Verified Delete Version is greyed out.");
			
			if(asserter.verifyExist(asserter.new XPath("//input[@type='checkbox' and contains(@title,'2') and @disabled]")))
				log.warn("Verified the checkbox is greyed out.");
			
			if(asserter.verifyExist(asserter.new XPath("//input[@type='checkbox']")))
				log.warn("Verified there are 2 versions.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Current']")))
				log.warn("Verified the status is Current.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + authorName + "']")))
				log.warn("Verified the author's name is correct.");
		
			if(asserter.verifyExist(asserter.new XPath("//div[text()='Test']")))
				log.warn("Verified the version summary is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//input[@type='checkbox']")))
				log.warn("Verified Version 1's checkbox is clickable.");
		
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Restore']")))
				log.warn("Verified Version 1's status is Restore.");
			
			qfd_LibraryTask.goLibrary();
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Save Current As New Version'.");
		log.info("************************************");
		
	}

	public void saveVersionUpload(String uploadName, String authorName, String filePath) throws Exception {
		
		log.info("************************************");
		log.info("Starting 'Save Upload Current As New Version'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			qfd_LibraryTask.goItem(uploadName);
			
			qfd_Page.getVersions().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Page.getSaveVersion().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().defaultContent();
			
			qfd_Page.getVersionsText().sendKeys("Test");
			Thread.sleep(Wait.SHORT);
			
			qfd_Library.getOK().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Save Current as New Version']")))
				log.warn("Verified Save Current as New Version is greyed out.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Delete Version']")))
				log.warn("Verified Delete Version is greyed out.");
			
			if(asserter.verifyExist(asserter.new XPath("//input[@type='checkbox' and contains(@title,'1') and @disabled]")))
				log.warn("Verified the checkbox is greyed out.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Current']")))
				log.warn("Verified the status is Current.");
			
			Calendar now = Calendar.getInstance();         		
			SimpleDateFormat sdf = new SimpleDateFormat("MMM"); 
			String month = sdf.format(now.getTime());
			//June
			sdf = new SimpleDateFormat("d"); 
//			sdf = new SimpleDateFormat("dd"); 
			String day = sdf.format(now.getTime());
			
			if(asserter.verifyExist(asserter.new XPath("//span[contains(text(),'" + month + " " + day + "')]")))
				log.warn("Verified the date is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + authorName + "']")))
				log.warn("Verified the author's name is correct.");
		
			if(asserter.verifyExist(asserter.new XPath("//div[text()='Test']")))
				log.warn("Verified the version summary is correct.");
			
			qfd_Page.getUploadReplace().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Replace']")))
				log.warn("Verified Replace dialog displays.");
			
			if(asserter.verifyFalse(driver.findElement(By.xpath("//input[@id='h_VersionSave']")).isSelected()))
				log.warn("Verified Create new version option is not checked by default.");				
				
			asserter.checkboxClick(asserter.new XPath("//input[@id='h_VersionSave']"), true);
			
			qfd_Page.getReplaceVersionsText().sendKeys("Test");
			Thread.sleep(Wait.SHORT);
			
			qfd_Library.getUploadFile().sendKeys(filePath);
			Thread.sleep(Wait.SHORT);	
			
			asserter.elementClickWait(qfd_Library.getSaveCheckIn(), asserter.new XPath("//a[text()='Change location']"), false);
			Thread.sleep(Wait.SHORT);	
			
			if(asserter.verifyExist(asserter.new XPath("//img[@alt='Image']")))
				log.warn("Verified the type icon is correct");
			
			if(asserter.verifyExist("Successfully saved"))
				log.warn("Verified the in-line message appears.");
			
			qfd_Page.getVersions().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Version 2']")))
				log.warn("Verified a new Version is created.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Save Current as New Version']")))
				log.warn("Verified Save Current as New Version is greyed out.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Delete Version']")))
				log.warn("Verified Delete Version is greyed out.");
			
			if(asserter.verifyExist(asserter.new XPath("//input[@type='checkbox' and contains(@title,'2') and @disabled]")))
				log.warn("Verified the checkbox is greyed out.");
			
			if(asserter.verifyExist(asserter.new XPath("//input[@type='checkbox']")))
				log.warn("Verified there are 2 versions.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Current']")))
				log.warn("Verified the status is Current.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + authorName + "']")))
				log.warn("Verified the author's name is correct.");
		
			if(asserter.verifyExist(asserter.new XPath("//span[contains(text(),'" + month + " " + day + "')]")))
				log.warn("Verified the date is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//div[text()='Test']")))
				log.warn("Verified the version summary is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//input[@type='checkbox']")))
				log.warn("Verified Version 1's checkbox is clickable.");
		
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Restore']")))
				log.warn("Verified Version 1's status is Restore.");
			
			qfd_LibraryTask.goLibrary();
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Save Upload Current As New Version'.");
		log.info("************************************");
		
	}
	
	public void restoreVersion(String pageName, String pageNameRestored, String authorName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Restore Version'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			qfd_LibraryTask.goItem(pageName);
			
			qfd_Page.getVersions().click();
			Thread.sleep(Wait.SHORT);
			
			asserter.elementClick(qfd_Page.getRestoreVersion(), asserter.new XPath("//input[@value='OK']"), true);
			
//			qfd_Page.getRestoreVersion().click();
//			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().defaultContent();
			
			qfd_Library.getOK().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Page.getVersions().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Save Current as New Version']")))
				log.warn("Verified Save Current as New Version is greyed out.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Delete Version']")))
				log.warn("Verified Delete Version is greyed out.");
			
			if(asserter.verifyExist(asserter.new XPath("//input[@type='checkbox' and contains(@title,'3') and @disabled]")))
				log.warn("Verified the checkbox is greyed out.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Current']")))
				log.warn("Verified the status is Current.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + authorName + "']")))
				log.warn("Verified the author's name is correct.");
		
			if(asserter.verifyExist(asserter.new XPath("//div[text()='Restored from version: 1']")))
				log.warn("Verified the version summary is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='" + pageNameRestored + "']")))
				log.warn("Verified the data has restored.");
			
			if(asserter.verifyTrue(driver.findElements(By.xpath("//span[text()='Restore']")).size() == 2))
				log.warn("Verified the Version number is 3.");
			
			if(asserter.verifyTrue(driver.findElements(By.xpath("//span[text()='Restore']")).size() == 2))
				log.warn("Verified the previous Versions status are Restore.");
			
			qfd_LibraryTask.goLibrary();
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Restore Version'.");
		log.info("************************************");
		
	}

	public void restoreVersionUpload(String uploadName, String authorName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Restore Upload Version'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			qfd_LibraryTask.goItem(uploadName);
			
			qfd_Page.getVersions().click();
			Thread.sleep(Wait.SHORT);
			
			asserter.elementClick(qfd_Page.getRestoreVersion(), asserter.new XPath("//input[@value='OK']"), true);
			
//			qfd_Page.getRestoreVersion().click();
//			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().defaultContent();
			
			qfd_Library.getOK().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Page.getVersions().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Save Current as New Version']")))
				log.warn("Verified Save Current as New Version is greyed out.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Delete Version']")))
				log.warn("Verified Delete Version is greyed out.");
			
			if(asserter.verifyExist(asserter.new XPath("//input[@type='checkbox' and contains(@title,'3') and @disabled]")))
				log.warn("Verified the checkbox is greyed out.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Current']")))
				log.warn("Verified the status is Current.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + authorName + "']")))
				log.warn("Verified the author's name is correct.");
		
			Calendar now = Calendar.getInstance();         		
			SimpleDateFormat sdf = new SimpleDateFormat("MMM"); 
			String month = sdf.format(now.getTime());
			//June
			sdf = new SimpleDateFormat("d");
//			sdf = new SimpleDateFormat("dd"); 
			String day = sdf.format(now.getTime());
			
			if(asserter.verifyExist(asserter.new XPath("//span[contains(text(),'" + month + " " + day + "')]")))
				log.warn("Verified the date is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//div[text()='Restored from version: 1']")))
				log.warn("Verified the version summary is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//img[@alt='Spreadsheet']")))
				log.warn("Verified the data has restored.");
			
			if(asserter.verifyTrue(driver.findElements(By.xpath("//span[text()='Restore']")).size() == 2))
				log.warn("Verified the Version number is 3.");
			
			if(asserter.verifyTrue(driver.findElements(By.xpath("//span[text()='Restore']")).size() == 2))
				log.warn("Verified the previous Versions' status are Restore.");
			
			if(asserter.verifyTrue((driver.findElements(By.xpath("//input[contains(@title,'Version')]")).size()) - (driver.findElements(By.xpath("//input[contains(@title,'Version') and @disabled]")).size()) == 2))
				log.warn("Verified the previous Versions' checkbox are clickable.");
			
			qfd_LibraryTask.goLibrary();
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Restore Upload Version'.");
		log.info("************************************");
		
	}
	
	public void addComment(String pageName, String comment, String authorName) throws Exception {
		
		log.info("************************************");
		log.info("Starting 'Add A Comment'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			qfd_LibraryTask.goLibrary();
			
			qfd_LibraryTask.goItem(pageName);
			
			qfd_Page.getComments().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Page.getAddComment().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//input[@value='Post Comment']")))
				log.warn("Verified the Post Comment button exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Cancel']")))
				log.warn("Verified the Cancel link exists.");
			
			action.sendKeys(comment).perform();
			Thread.sleep(Wait.VERYSHORT);
			log.info("Sending keys : " + comment + " into CK Editor.");
			
			qfd_Page.getPostComment().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().defaultContent();
			
			if(asserter.verifyExist("Your comment was successfully posted"))
				log.warn("Verified the In-line message exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Add Comment']")))
				log.warn("Verified the Add Comment link exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//img[contains(@class,'entryIcon')]")))
				log.warn("Verified the Comment icon exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + authorName + "']")))
				log.warn("Verified the author's name is correct.");
			
			if(asserter.verifyExist("Today"))
				log.warn("Verified the date is correct.");
			
			if(asserter.verifyExist(comment))
				log.warn("Verified the content is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//img[contains(@class,'lotusIcon')]")))
				log.warn("Verified the More icon exists.");
			
			qfd_Page.getCommentMore().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//img[@alt='Hide']")))
				log.warn("Verified the More icon changes.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + authorName + "']")))
				log.warn("Verified the author's name is correct.");
			
			if(asserter.verifyExist("Today"))
				log.warn("Verified the date is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//p[contains(text(),'" + comment + "')]")))
				log.warn("Verified the content is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Reply']")))
				log.warn("Verified the Reply link exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Delete']")))
				log.warn("Verified the Delete link exists.");
			
			driver.switchTo().defaultContent();
			
			qfd_LibraryTask.goLibrary();
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Add A Comment'.");
		log.info("************************************");
		
	}

	public void replyComment(String pageName, String reply, String authorName) throws Exception {
		
		log.info("************************************");
		log.info("Starting 'Add A Reply'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			qfd_LibraryTask.goLibrary();
			
			qfd_LibraryTask.goItem(pageName);
			
			qfd_Page.getComments().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Page.getCommentMore().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Page.getReply().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//input[@value='Post Comment']")))
				log.warn("Verified the Post Comment button exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Cancel']")))
				log.warn("Verified the Cancel link exists.");
			
			action.sendKeys(reply + " Reply").perform();
			Thread.sleep(Wait.VERYSHORT);
			log.info("Sending keys : " + reply + " Reply" +" into CK Editor.");
			
			qfd_Page.getPostComment().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().defaultContent();
			
			if(asserter.verifyExist("Your comment was successfully posted"))
				log.warn("Verified the In-line message exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Add Comment']")))
				log.warn("Verified the Add Comment link exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//img[contains(@class,'entryIcon')]")))
				log.warn("Verified the Comment icon exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + authorName + "']")))
				log.warn("Verified the author's name is correct.");
			
			if(asserter.verifyExist("Today"))
				log.warn("Verified the date is correct.");
			
			if(asserter.verifyExist(reply))
				log.warn("Verified the content is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//img[contains(@class,'lotusIcon')]")))
				log.warn("Verified the More icon exists.");
			
			qfd_Page.getReplyContent().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//img[@alt='Hide']")))
				log.warn("Verified the More icon changes.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + authorName + "']")))
				log.warn("Verified the author's name is correct.");
			
			if(asserter.verifyExist("Today"))
				log.warn("Verified the date is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//p[contains(text(),'" + reply + " Reply" + "')]")))
				log.warn("Verified the content is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Reply']")))
				log.warn("Verified the Reply link exists.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Delete']")))
				log.warn("Verified the Delete link exists.");
			
			qfd_LibraryTask.goLibrary();
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Add A Reply'.");
		log.info("************************************");
		
	}

	public void verifyPageSpellchecker(String pageName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Verify Spellchecker Of Full CK Editor'...");
		Thread.sleep(Wait.SHORT);
		
		try{
		
			qfd_LibraryTask.goLibrary();
			
			qfd_LibraryTask.newPage();
			
			qfd_Library.getPageName().sendKeys(pageName);
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().frame(qfd_Library.getRichTextFrame());
			Thread.sleep(Wait.PAUSE);
			
			qfd_Library.getPageDesc().click();
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Library.getPageDesc().sendKeys("existanse");
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Library.getPageDesc().sendKeys(Keys.ENTER);
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Library.getPageDesc().sendKeys("forword");
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Library.getPageDesc().sendKeys(Keys.ENTER);
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Library.getPageDesc().sendKeys("haras");
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Library.getPageDesc().sendKeys(Keys.ENTER);
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Library.getPageDesc().sendKeys("inadvartant");
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Library.getPageDesc().sendKeys(Keys.ENTER);
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Library.getPageDesc().sendKeys("indispensabel");
			Thread.sleep(Wait.VERYSHORT);
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			qfd_Page.getSpellchecker().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			for(int i = 0; i < 5; i++){
			
				qfd_Page.getSkip().click();
				Thread.sleep(Wait.PAUSE);
				
			}
			
			driver.switchTo().alert().accept();
			log.info("Clicking on OK button.");
			Thread.sleep(Wait.PAUSE);
			
			qfd_Page.getDialogCancel().click();
			Thread.sleep(Wait.PAUSE);
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			driver.switchTo().frame(qfd_Page.getRichTextFrameNext());
			Thread.sleep(Wait.PAUSE);
			
			if(asserter.verifyExist(asserter.new XPath("//p[contains(text(),'existanse')]" )) &&
			   asserter.verifyExist(asserter.new XPath("//p[contains(text(),'forword')]" )) &&
			   asserter.verifyExist(asserter.new XPath("//p[contains(text(),'haras')]" )) &&
			   asserter.verifyExist(asserter.new XPath("//p[contains(text(),'inadvartant')]" )) &&
			   asserter.verifyExist(asserter.new XPath("//p[contains(text(),'indispensabel')]" )))
				log.warn("Verified that the content of the documents is unchanged.");
				
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			qfd_Page.getSpellchecker().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			for(int i = 0; i < 5; i++){
				
				qfd_Page.getSkipAll().click();
				Thread.sleep(Wait.PAUSE);
				
			}
			
			driver.switchTo().alert().accept();
			log.info("Clicking on OK button.");
			Thread.sleep(Wait.PAUSE);
			
			qfd_Page.getDialogCancel().click();
			Thread.sleep(Wait.PAUSE);
		
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			driver.switchTo().frame(qfd_Page.getRichTextFrameNext());
			Thread.sleep(Wait.PAUSE);
			
			if(asserter.verifyExist(asserter.new XPath("//p[contains(text(),'existanse')]" )) &&
			   asserter.verifyExist(asserter.new XPath("//p[contains(text(),'forword')]" )) &&
			   asserter.verifyExist(asserter.new XPath("//p[contains(text(),'haras')]" )) &&
			   asserter.verifyExist(asserter.new XPath("//p[contains(text(),'inadvartant')]" )) &&
			   asserter.verifyExist(asserter.new XPath("//p[contains(text(),'indispensabel')]" )))
				log.warn("Verified that the content of the documents is unchanged.");
				
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			qfd_Page.getSpellchecker().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			for(int i = 0; i < 5; i++){
				
				qfd_Page.getReplace().click();
				Thread.sleep(Wait.PAUSE);
				
			}
			
			driver.switchTo().alert().accept();
			log.info("Clicking on OK button.");
			Thread.sleep(Wait.PAUSE);
			
			if(asserter.verifyExist(asserter.new XPath("//option[@value='indispensable']")))
				log.warn("Verified that the Spellchecker begins again with the original list of words.");
			
			qfd_Page.getDialogCancel().click();
			Thread.sleep(Wait.PAUSE);
		
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			driver.switchTo().frame(qfd_Page.getRichTextFrameNext());
			Thread.sleep(Wait.PAUSE);
			
			if(asserter.verifyExist(asserter.new XPath("//p[contains(text(),'existanse')]" )) &&
			   asserter.verifyExist(asserter.new XPath("//p[contains(text(),'forword')]" )) &&
		       asserter.verifyExist(asserter.new XPath("//p[contains(text(),'haras')]" )) &&
			   asserter.verifyExist(asserter.new XPath("//p[contains(text(),'inadvartant')]" )) &&
			   asserter.verifyExist(asserter.new XPath("//p[contains(text(),'indispensabel')]" )))
				log.warn("Verified that the content of the documents is unchanged.");
				
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			qfd_Page.getSpellchecker().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			for(int i = 0; i < 5; i++){
				
				qfd_Page.getReplaceAll().click();
				Thread.sleep(Wait.PAUSE);
				
			}
			
			driver.switchTo().alert().accept();
			log.info("Clicking on OK button.");
			Thread.sleep(Wait.PAUSE);
			
			if(asserter.verifyExist(asserter.new XPath("//option[@value='indispensable']")))
				log.warn("Verified that the Spellchecker begins again with the original list of words.");
				
			qfd_Page.getImgOK().click();
			Thread.sleep(Wait.PAUSE);
		
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			asserter.elementClickWait(qfd_Library.getSaveCheckIn(), asserter.new XPath("//a[text()='Subscribe to this folder']"), true);
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist("Successfully saved"))
				log.warn("Verified the page: " + pageName + " is created successfully.");
			
			qfd_LibraryTask.goItem(pageName);
			
			qfd_Page.getCheckOut().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Page.getSpellchecker().click();
			Thread.sleep(Wait.PAUSE);
			
			driver.switchTo().alert().accept();
			log.info("Clicking on OK button.");
			log.warn("Verified the Spellchecker dialog and Spell Check Complete dialog box is immediately displayed");
			Thread.sleep(Wait.SHORT);
			
			action.sendKeys(Keys.TAB).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.TAB).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.TAB).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.TAB).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.TAB).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.TAB).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.TAB).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.TAB).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ENTER).perform();
			Thread.sleep(Wait.VERYSHORT);
			log.info("Clicking on Cancel link.");
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			qfd_Page.getCancel().click();
			Thread.sleep(Wait.PAUSE);
			
			driver.switchTo().alert().accept();
			log.info("Clicking on OK button.");
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().frame(qfd_Page.getTextFrame());
			Thread.sleep(Wait.PAUSE);
			
			if(asserter.verifyExist(asserter.new XPath("//p[contains(text(),'existence')]" )) &&
			   asserter.verifyExist(asserter.new XPath("//p[contains(text(),'foreword')]" )) &&
			   asserter.verifyExist(asserter.new XPath("//p[contains(text(),'harass')]" )) &&
		       asserter.verifyExist(asserter.new XPath("//p[contains(text(),'inadvertent')]" )) &&
			   asserter.verifyExist(asserter.new XPath("//p[contains(text(),'indispensable')]" )))
				log.warn("Verified that the words in the edit area of the document match the corrected words.");
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			qfd_LibraryTask.goLibrary();
			Thread.sleep(Wait.SHORT);
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify Spellchecker Of Full CK Editor'.");
		log.info("************************************");
		
	}

	public void verifyCommentSpellchecker(String pageName) throws Exception {
		

		log.info("************************************");
		log.info("Starting 'Verify Spellchecker Of Lite CK Editor'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			qfd_LibraryTask.goLibrary();
			
			qfd_LibraryTask.goItem(pageName);
			
			qfd_Page.getComments().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Page.getAddComment().click();
			Thread.sleep(Wait.SHORT);
			
			action.sendKeys("existanse").perform();
			Thread.sleep(Wait.VERYSHORT);
			log.info("Sending keys : 'existanse' into CK Editor.");
			action.sendKeys(Keys.ENTER).perform();
			Thread.sleep(Wait.VERYSHORT);
			
			action.sendKeys("forword").perform();
			Thread.sleep(Wait.VERYSHORT);
			log.info("Sending keys : 'forword' into CK Editor.");
			action.sendKeys(Keys.ENTER).perform();
			Thread.sleep(Wait.VERYSHORT);
			 
			action.sendKeys("haras").perform();
			Thread.sleep(Wait.VERYSHORT);
			log.info("Sending keys : 'haras' into CK Editor.");
			action.sendKeys(Keys.ENTER).perform();
			Thread.sleep(Wait.VERYSHORT);
			 
			action.sendKeys("inadvartant").perform();
			Thread.sleep(Wait.VERYSHORT);
			log.info("Sending keys : 'inadvartant' into CK Editor.");
			action.sendKeys(Keys.ENTER).perform();
			Thread.sleep(Wait.VERYSHORT);
			
			action.sendKeys("indispensabel").perform();
			Thread.sleep(Wait.VERYSHORT);
			log.info("Sending keys : 'indispensabel' into CK Editor.");
			 
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			qfd_Page.getSpellchecker().click();
			Thread.sleep(Wait.SHORT);
			
			action.sendKeys(Keys.TAB).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.TAB).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.TAB).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.TAB).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.TAB).perform();
			Thread.sleep(Wait.VERYSHORT);
			
			for(int i = 0; i < 5; i++){
				
				action.sendKeys(Keys.ENTER).perform();
				log.info("Clicking on Skip button.");
				Thread.sleep(Wait.PAUSE);
				
			}
			
			driver.switchTo().alert().accept();
			log.info("Clicking on OK button.");
			Thread.sleep(Wait.PAUSE);
			
			action.sendKeys(Keys.TAB).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.TAB).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.TAB).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ENTER).perform();
			log.info("Clicking on Cancel link.");
			Thread.sleep(Wait.PAUSE);
			
			driver.switchTo().frame(qfd_Page.getRichTextFrameNext());
			Thread.sleep(Wait.PAUSE);
			
			if(asserter.verifyExist(asserter.new XPath("//p[contains(text(),'existanse')]" )) &&
			   asserter.verifyExist(asserter.new XPath("//p[contains(text(),'forword')]" )) &&
			   asserter.verifyExist(asserter.new XPath("//p[contains(text(),'haras')]" )) &&
			   asserter.verifyExist(asserter.new XPath("//p[contains(text(),'inadvartant')]" )) &&
		       asserter.verifyExist(asserter.new XPath("//p[contains(text(),'indispensabel')]" )))
				log.warn("Verified that the content of the documents is unchanged.");
				
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			qfd_Page.getSpellchecker().click();
			Thread.sleep(Wait.SHORT);
			
			action.sendKeys(Keys.TAB).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.TAB).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.TAB).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.TAB).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.TAB).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.TAB).perform();
			Thread.sleep(Wait.VERYSHORT);
			
			for(int i = 0; i < 5; i++){
				
				action.sendKeys(Keys.ENTER).perform();
				log.info("Clicking on Skip All button.");
				Thread.sleep(Wait.PAUSE);
				
			}
			
			driver.switchTo().alert().accept();
			log.info("Clicking on OK button.");
			Thread.sleep(Wait.PAUSE);
			
			action.sendKeys(Keys.TAB).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.TAB).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ENTER).perform();
			log.info("Clicking on Cancel link.");
			Thread.sleep(Wait.PAUSE);

			driver.switchTo().frame(qfd_Page.getRichTextFrameNext());
			Thread.sleep(Wait.PAUSE);
			
			if(asserter.verifyExist(asserter.new XPath("//p[contains(text(),'existanse')]" )) &&
			   asserter.verifyExist(asserter.new XPath("//p[contains(text(),'forword')]" )) &&
			   asserter.verifyExist(asserter.new XPath("//p[contains(text(),'haras')]" )) &&
			   asserter.verifyExist(asserter.new XPath("//p[contains(text(),'inadvartant')]" )) &&
			   asserter.verifyExist(asserter.new XPath("//p[contains(text(),'indispensabel')]" )))
				log.warn("Verified that the content of the documents is unchanged.");
				
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			qfd_Page.getSpellchecker().click();
			Thread.sleep(Wait.SHORT);
			
			action.sendKeys(Keys.TAB).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.TAB).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.TAB).perform();
			Thread.sleep(Wait.VERYSHORT);
			
			for(int i = 0; i < 5; i++){
				
				action.sendKeys(Keys.ENTER).perform();
				log.info("Clicking on Replace button.");
				Thread.sleep(Wait.PAUSE);
				
			}
			
			driver.switchTo().alert().accept();
			log.info("Clicking on OK button.");
			Thread.sleep(Wait.PAUSE);
			
			if(asserter.verifyExist(asserter.new XPath("//option[@value='indispensable']")))
				log.warn("Verified that the Spellchecker begins again with the original list of words.");
			
			action.sendKeys(Keys.TAB).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.TAB).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.TAB).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.TAB).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.TAB).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ENTER).perform();
			log.info("Clicking on Cancel link.");
			Thread.sleep(Wait.PAUSE);
		
			driver.switchTo().frame(qfd_Page.getRichTextFrameNext());
			Thread.sleep(Wait.PAUSE);
			
			if(asserter.verifyExist(asserter.new XPath("//p[contains(text(),'existanse')]" )) &&
			   asserter.verifyExist(asserter.new XPath("//p[contains(text(),'forword')]" )) &&
			   asserter.verifyExist(asserter.new XPath("//p[contains(text(),'haras')]" )) &&
		       asserter.verifyExist(asserter.new XPath("//p[contains(text(),'inadvartant')]" )) &&
			   asserter.verifyExist(asserter.new XPath("//p[contains(text(),'indispensabel')]" )))
				log.warn("Verified that the content of the documents is unchanged.");
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			qfd_Page.getSpellchecker().click();
			Thread.sleep(Wait.SHORT);
			
			action.sendKeys(Keys.TAB).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.TAB).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.TAB).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.TAB).perform();
			Thread.sleep(Wait.VERYSHORT);
			
			for(int i = 0; i < 5; i++){
				
				action.sendKeys(Keys.ENTER).perform();
				log.info("Clicking on Replace All button.");
				Thread.sleep(Wait.PAUSE);
				
			}
			
			driver.switchTo().alert().accept();
			log.info("Clicking on OK button.");
			Thread.sleep(Wait.PAUSE);
			
			if(asserter.verifyExist(asserter.new XPath("//option[@value='indispensable']")))
				log.warn("Verified that the Spellchecker begins again with the original list of words.");
				
			action.sendKeys(Keys.TAB).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.TAB).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.TAB).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ENTER).perform();
			log.info("Clicking on OK button.");
			Thread.sleep(Wait.PAUSE);
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);

			qfd_Page.getPostComment().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			qfd_Page.getCommentMore().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//p[contains(text(),'existence')]" )) &&
			   asserter.verifyExist(asserter.new XPath("//p[contains(text(),'foreword')]" )) &&
			   asserter.verifyExist(asserter.new XPath("//p[contains(text(),'harass')]" )) &&
			   asserter.verifyExist(asserter.new XPath("//p[contains(text(),'inadvertent')]" )) &&
			   asserter.verifyExist(asserter.new XPath("//p[contains(text(),'indispensable')]" )))
				log.warn("Verified that the words in the edit area of the document match the corrected words.");
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			qfd_LibraryTask.goLibrary();
			Thread.sleep(Wait.SHORT);
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify Spellchecker Of Lite CK Editor'.");
		log.info("************************************");
	}

	public void verifyPageOptions(String pageName, String role) throws Exception {

		log.info("************************************");
		log.info("Starting 'Verify Page Options'...");
		
		try{
			
			qfd_LibraryTask.goLibrary();
			
			qfd_LibraryTask.goItem(pageName);
			
			if(role.equalsIgnoreCase("editor")){
				
				if(asserter.verifyExist(asserter.new XPath("//a[text()='Check Out and Edit']")))
					log.warn("Verified the Check Out and Edit button exists.");
				
				if(asserter.verifyExist(asserter.new XPath("//a[contains(text(),'More Actions')]")))
					log.warn("Verified the More Actions button exists.");
				
				qfd_Page.getMoreActions().click();
				Thread.sleep(Wait.SHORT);
				
				if(asserter.verifyExist(asserter.new XPath("//td[contains(text(),'Check Out')]")))
					log.warn("Verified the Check Out item exists.");
				
				if(asserter.verifyExist(asserter.new XPath("//td[contains(text(),'Copy To')]")))
					log.warn("Verified the Copy To item exists.");
				
				if(asserter.verifyExist(asserter.new XPath("//td[contains(text(),'Move To')]")))
					log.warn("Verified the Move To item exists.");
				
				if(asserter.verifyExist(asserter.new XPath("//td[contains(text(),'Move To Trash')]")))
					log.warn("Verified the Move To Trash item exists.");
				
				if(asserter.verifyExist(asserter.new XPath("//td[contains(text(),'Send Link')]")))
					log.warn("Verified the Send Link item exists.");
				
				if(asserter.verifyExist(asserter.new XPath("//td[contains(text(),'Print')]")))
					log.warn("Verified the Print item exists.");
				
				action.sendKeys(Keys.ESCAPE).perform();
				Thread.sleep(Wait.VERYSHORT);
				
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
				
				if(asserter.verifyExist(asserter.new XPath("//a[text()='Add Comment']")))
					log.warn("Verified the Add Comment link exists.");
				
				if(asserter.verifyExist(asserter.new XPath("//span[text()='Expand all comments']")))
					log.warn("Verified the Expand all comments link exists.");
				
			}else if(role.equalsIgnoreCase("author")){
				
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
				
				if(asserter.verifyExist(asserter.new XPath("//td[contains(text(),'Send Link')]")))
					log.warn("Verified the Send Link item exists.");
				
				if(asserter.verifyExist(asserter.new XPath("//td[contains(text(),'Print')]")))
					log.warn("Verified the Print item exists.");
				
				action.sendKeys(Keys.ESCAPE).perform();
				Thread.sleep(Wait.VERYSHORT);
				
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
				
				if(asserter.verifyExist(asserter.new XPath("//a[text()='Add Comment']")))
					log.warn("Verified the Add Comment link exists.");
				
				if(asserter.verifyExist(asserter.new XPath("//span[text()='Expand all comments']")))
					log.warn("Verified the Expand all comments link exists.");
				
			}else if(role.equalsIgnoreCase("reader")){
				
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
				
				if(asserter.verifyExist(asserter.new XPath("//td[contains(text(),'Send Link')]")))
					log.warn("Verified the Send Link item exists.");
				
				if(asserter.verifyExist(asserter.new XPath("//td[contains(text(),'Print')]")))
					log.warn("Verified the Print item exists.");
				
				action.sendKeys(Keys.ESCAPE).perform();
				Thread.sleep(Wait.VERYSHORT);
				
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
				
			}
			
			qfd_LibraryTask.goLibrary();
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
		
		}
		
		log.info("End of 'Verify Page Options'...");
		log.info("************************************");
		
	}

	public void editUpload(String fileName, String desc) throws Exception {
		
		log.info("************************************");
		log.info("Starting 'Edit Upload'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			qfd_LibraryTask.goLibrary();
			
			qfd_LibraryTask.goItem(fileName);
			
			qfd_Page.getMoreActions().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Page.getEditProperties().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyTrue(qfd_Library.getUploadName().getAttribute("value").equals(fileName)))
				log.warn("Verified the name is correct.");
				
			if(asserter.verifyTrue(qfd_Library.getUploadDesc().getAttribute("value").equals(desc)))
				log.warn("Verified the description is correct.");
				
			qfd_Library.getUploadName().click();
			Thread.sleep(Wait.PAUSE);	
			action.sendKeys(Keys.END).perform();
			Thread.sleep(Wait.PAUSE);
			qfd_Library.getUploadName().sendKeys(" Updated");
			Thread.sleep(Wait.SHORT);	
			
			qfd_Library.getUploadDesc().click();
			Thread.sleep(Wait.PAUSE);	
			action.sendKeys(Keys.END).perform();
			Thread.sleep(Wait.PAUSE);
			qfd_Library.getUploadDesc().sendKeys(" Updated");
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
		
		log.info("End of 'Edit Upload'.");
		log.info("************************************");
		
	}

	public void replaceUpload(String fileName, String filePath) throws Exception {

		log.info("************************************");
		log.info("Starting 'Replace Upload'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			qfd_LibraryTask.goLibrary();
			
			qfd_LibraryTask.goItem(fileName);
			
			qfd_Page.getUploadReplace().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Replace']")))
				log.warn("Verified Replace dialog displays.");
			
			if(asserter.verifyFalse(driver.findElement(By.xpath("//input[@id='h_VersionSave']")).isSelected()))
				log.warn("Verified Create new version option is not checked by default.");				
				
			qfd_Library.getUploadFile().sendKeys(filePath);
			Thread.sleep(Wait.SHORT);	
			
			asserter.elementClickWait(qfd_Library.getSaveCheckIn(), asserter.new XPath("//a[text()='Change location']"), false);
			Thread.sleep(Wait.SHORT);	
			
			if(asserter.verifyExist(asserter.new XPath("//img[@alt='Spreadsheet']")))
				log.warn("Verified the type icon is correct");
			
			if(asserter.verifyExist("Successfully saved"))
				log.warn("Verified the in-line message appears.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Replace Upload'.");
		log.info("************************************");
		
	}
	
}
