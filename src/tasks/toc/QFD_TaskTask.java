package tasks.toc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import junit.framework.AssertionFailedError;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import config.BrowserSetup;
import config.Element;
import config.Wait;
import exceptions.AssertFailException;
import exceptions.ElementException;

import tasks.common.AutoITTask;
import util.Assert;
import util.Assert.XPath;
import appobjects.library.QFD_Page;
import appobjects.toc.QFD_Calendar;
import appobjects.toc.QFD_Library;
import appobjects.toc.QFD_Task;

public class QFD_TaskTask {

	private WebDriver driver;
	private Logger log;
	private Assert asserter;
	private AutoITTask AT;
	Actions action = null;
	QFD_Calendar qfd_Calendar = null;
	QFD_CalendarTask qfd_CalendarTask = null;
	QFD_LibraryTask qfd_LibraryTask = null;
	QFD_Page qfd_Page = null;
	QFD_Library qfd_Library = null;
	QFD_Task qfd_Task = null;
	
	public QFD_TaskTask(WebDriver driver, Logger log){
		
		this.driver = driver;
		this.log = log;
		this.asserter = new Assert(driver, log);
		this.AT = new AutoITTask(driver, log);
		action = new Actions(driver);
		qfd_Calendar = new QFD_Calendar(driver);
		qfd_CalendarTask = new QFD_CalendarTask(driver, log);
		qfd_LibraryTask = new QFD_LibraryTask(driver, log);
		qfd_Page = new QFD_Page(driver);
		qfd_Library = new QFD_Library(driver);
		qfd_Task = new QFD_Task(driver);
		
	}
	
	public void goTask() throws Exception {
		
		asserter.elementClick(qfd_Task.getTask(), asserter.new XPath("//a[contains(text(),'Subscribe to')]"), true);
		Thread.sleep(Wait.SHORT);
		
	}

	public void createRichTask(String taskName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Create Task With Rich Text'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			goTask();
			
			qfd_Task.getNewTask().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Create New Task']")))
				log.warn("Verified the edit view is displayed.");
			
			qfd_Library.getLinkPageName().sendKeys(taskName);
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().frame(qfd_Library.getRichTextFrame());
			Thread.sleep(Wait.PAUSE);
			
			qfd_Library.getPageDesc().click();
			Thread.sleep(Wait.SHORT);
			qfd_Library.getPageDesc().sendKeys("This line is indent level 0");
			Thread.sleep(Wait.SHORT);
			
			action.sendKeys(Keys.ENTER).perform();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Page.getIndent().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().frame(qfd_Library.getRichTextFrame());
			Thread.sleep(Wait.PAUSE);
			
			qfd_Library.getPageDesc().sendKeys("This line is indent level 1");
			Thread.sleep(Wait.SHORT);
			
			action.sendKeys(Keys.ENTER).perform();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Page.getIndent().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().frame(qfd_Library.getRichTextFrame());
			Thread.sleep(Wait.PAUSE);
			
			qfd_Library.getPageDesc().sendKeys("This line is indent level 2");
			Thread.sleep(Wait.SHORT);
			
			action.sendKeys(Keys.ENTER).perform();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Page.getOutdent().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().frame(qfd_Library.getRichTextFrame());
			Thread.sleep(Wait.PAUSE);
			
			qfd_Library.getPageDesc().sendKeys("This line is indent level 1");
			Thread.sleep(Wait.SHORT);
			
			action.sendKeys(Keys.ENTER).perform();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Page.getOutdent().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().frame(qfd_Library.getRichTextFrame());
			Thread.sleep(Wait.PAUSE);
			
			qfd_Library.getPageDesc().sendKeys("This line is indent level 0");
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.VERYSHORT);
			
			asserter.elementClick(qfd_Library.getSave(), asserter.new XPath("//a[text()='Subscribe to these tasks']"), true);
			
			if(asserter.verifyExist("Successfully saved"))
				log.warn("Verified the task successfully created.");
			
			qfd_LibraryTask.goItem(taskName);
			
			if(asserter.verifyTrue(driver.findElements(By.xpath("//p[contains(text(),'This line is indent level 0')]")).size() == 2))
				log.warn("Verified the line 1 and line 5 are correct.");
			
			if(asserter.verifyTrue(driver.findElements(By.xpath("//p[contains(text(),'This line is indent level 1') and contains(@style,'40px')]")).size() == 2))
//			if(asserter.verifyTrue(driver.findElements(By.xpath("//p[contains(text(),'This line is indent level 1') and contains(@style,'margin-left: 40px')]")).size() == 2))
				log.warn("Verified the line 2 and line 4 are correct.");
			
			if(asserter.verifyTrue(driver.findElements(By.xpath("//p[contains(text(),'This line is indent level 2') and contains(@style,'80px')]")).size() == 1))
//			if(asserter.verifyTrue(driver.findElements(By.xpath("//p[contains(text(),'This line is indent level 2') and contains(@style,'margin-left: 80px')]")).size() == 1))
				log.warn("Verified the line 3 is correct.");
			
			qfd_LibraryTask.goLibrary();
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Create Task Event With Rich Text'.");
		log.info("************************************");
		
	}

	public void verifyLiteEditorIcons() throws Exception {


		log.info("************************************");
		log.info("Starting 'Verify CK Editor(Lite) Icons'...");
		Thread.sleep(Wait.SHORT);
		
		try{
		
			goTask();
			
			qfd_Task.getNewTask().click();
			Thread.sleep(Wait.SHORT);
			
			String[] expected = {"Bold", "Italic", "Underline", "Strike Through", "Spellchecker", "Text Color", "Background Color", 
					             "Indent Paragraph", "Outdent", "Numbers", "Bullets", "Insert Link", "Insert Emoticons"};					             
			
			List<WebElement> l = new ArrayList<WebElement>();
			boolean result = true;
			
			l = driver.findElements(By.xpath("//span[contains(@class,'cke')]/a[@role='button']"));			
			
			if(asserter.verifyTrue(l.size() == 13))
				log.warn("Verified there are 13 buttons.");
			
			for(int i = 0; i < l.size(); i++){
				
				if(asserter.verifyEquals(l.get(i).getAttribute("title"), expected[i]))
					log.warn("Verified button " + (i + 1) + " is '" + expected[i] + "'");
				else result = false;
				Thread.sleep(Wait.VERYSHORT);
				
			}
			
			if(asserter.verifyTrue(result))
				log.warn("Verified all 13 buttons are correct.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify CK Editor(Lite) Icons'.");
		log.info("************************************");
		
	}

	public void create4DaysLaterTask(String taskName, String desc) throws Exception {

		log.info("************************************");
		log.info("Starting 'Create Four Days Later Task'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			goTask();
			
			qfd_Task.getNewTask().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Create New Task']")))
				log.warn("Verified the edit view is displayed.");
			
			qfd_Library.getLinkPageName().sendKeys(taskName);
			Thread.sleep(Wait.SHORT);
			
			Calendar now = Calendar.getInstance();   
	        now.set(Calendar.DATE, now.get(Calendar.DATE) + 4);       		
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy"); 
			String day = sdf.format(now.getTime());
			
			qfd_Task.getStartDate().sendKeys(day);
			Thread.sleep(Wait.SHORT);
			
			qfd_Task.getDueDate().sendKeys(day);
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().frame(qfd_Library.getRichTextFrame());
			Thread.sleep(Wait.PAUSE);
			
			qfd_Library.getPageDesc().click();
			Thread.sleep(Wait.PAUSE);
			
			qfd_Library.getPageDesc().sendKeys(desc);
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.VERYSHORT);
			
			asserter.elementClick(qfd_Library.getSave(), asserter.new XPath("//a[text()='Subscribe to these tasks']"), true);
			
			if(asserter.verifyExist("Successfully saved"))
				log.warn("Verified the task successfully created.");
			
			qfd_LibraryTask.goLibrary();
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Create Four Days Later Task'.");
		log.info("************************************");
		
	}

	public void createAndVerifyTask(String taskName, String desc,
			String attachPath, String userName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Create Task And Verify Task UI'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			goTask();
			
			if(asserter.verifyExist(asserter.new XPath("//div[contains(text(),'All Tasks')]")))
				log.warn("Verified the Tasks listing page gets displayed.");
			
			qfd_Task.getNewTask().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Create New Task']")))
				log.warn("Verified the edit view is displayed.");
			
			qfd_Library.getLinkPageName().sendKeys(taskName);
			Thread.sleep(Wait.SHORT);
			
			qfd_Task.getPriority().click();
			Thread.sleep(Wait.SHORT);
			
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.PAUSE);
			action.sendKeys(Keys.ENTER).perform();
			log.info("Clicking on High item.");
			Thread.sleep(Wait.PAUSE);
			
			asserter.checkboxClick(asserter.new XPath("//input[@title='Mark as milestone']"), true);
			
			Calendar now = Calendar.getInstance();   
	        now.set(Calendar.DATE, now.get(Calendar.DATE));       		
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy"); 
			String day = sdf.format(now.getTime());
			
			qfd_Task.getStartDate().sendKeys(day);
			Thread.sleep(Wait.SHORT);
			
			now.set(Calendar.DATE, now.get(Calendar.DATE) + 1);       		
			sdf = new SimpleDateFormat("MM/dd/yyyy"); 
			day = sdf.format(now.getTime());
			
			qfd_Task.getDueDate().sendKeys(day);
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().frame(qfd_Library.getRichTextFrame());
			Thread.sleep(Wait.PAUSE);
			
			qfd_Library.getPageDesc().click();
			Thread.sleep(Wait.PAUSE);
			
			qfd_Library.getPageDesc().sendKeys(desc);
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.VERYSHORT);
			
			asserter.checkboxClick(asserter.new XPath("//input[@title='Display on Calendar']"), true);
			
			qfd_Task.getAssign().click();
			Thread.sleep(Wait.MID);
			
			asserter.checkboxClick(asserter.new XPath("//input[contains(@title,'" + userName + "')]"), true);
			
			qfd_Library.getOK().click();
			Thread.sleep(Wait.MID);
			
			qfd_Library.getPageAttach().click();
			Thread.sleep(Wait.SHORT);

			qfd_Library.getFileField().sendKeys(attachPath);
			Thread.sleep(Wait.SHORT);
			
			asserter.elementClick(qfd_Library.getSave(), asserter.new XPath("//a[text()='Subscribe to these tasks']"), true);
			
			if(asserter.verifyExist("Successfully saved"))
				log.warn("Verified the task successfully created.");
			
			qfd_LibraryTask.goItem(taskName);

			if(asserter.verifyTrue(driver.findElement(By.xpath("//div[contains(text(),'You are in')]")).getText().contains(taskName)))
				log.warn("Verified the breadcrumb of is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='" + taskName + "']")))
				log.warn("Verified the task opened successfully.");
			
			if(asserter.verifyExist(asserter.new XPath("//p[contains(text(),'" + desc + "')]")))
				log.warn("Verified the view page displays correctly.");
			
			goTask();
			
			if(asserter.verifyExist(asserter.new XPath("//h1[contains(text(),'Tasks')]")))
				log.warn("Verified the Tasks listing page gets displayed.");
			
			qfd_Library.getContext().click();
			Thread.sleep(Wait.SHORT);
			
			action.sendKeys(Keys.ENTER).perform();
			log.info("Clicking on Mark Complete item.");
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//img[@title='Complete task']")))
				log.warn("Verified the complete icon displays for the task.");
			
			qfd_Library.getContext().click();
			Thread.sleep(Wait.SHORT);
			
			action.sendKeys(Keys.ENTER).perform();
			log.info("Clicking on Mark Incomplete item.");
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyNotExist(asserter.new XPath("//img[@title='Complete task']")))
				log.warn("Verified the complete disappears.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Create Task And Verify Task UI'.");
		log.info("************************************");
		
	}

	public void editTask(String taskName, String desc) throws Exception {

		log.info("************************************");
		log.info("Starting 'Edit Task'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			qfd_LibraryTask.goItem(taskName);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='" + taskName + "']")))
				log.warn("Verified the task opened successfully.");
			
			qfd_Calendar.getEdit().click();
			Thread.sleep(Wait.MID);
			
			qfd_Library.getLinkPageName().clear();
			Thread.sleep(Wait.PAUSE);
			
			qfd_Library.getLinkPageName().sendKeys(taskName + "Updated");
			Thread.sleep(Wait.PAUSE);
			
			qfd_Task.getPriority().click();
			Thread.sleep(Wait.SHORT);
			
			action.sendKeys(Keys.ARROW_UP).perform();
			Thread.sleep(Wait.PAUSE);
			action.sendKeys(Keys.ENTER).perform();
			log.info("Clicking on null item.");
			Thread.sleep(Wait.PAUSE);
			
			asserter.checkboxClick(asserter.new XPath("//input[@title='Complete']"), true);
			
			driver.switchTo().frame(qfd_Library.getRichTextFrame());
			Thread.sleep(Wait.PAUSE);
			
			qfd_Library.getPageDesc().click();
			Thread.sleep(Wait.PAUSE);
			
			action.sendKeys(Keys.END).perform();
			Thread.sleep(Wait.PAUSE);
			
			qfd_Library.getPageDesc().sendKeys(" Updated");
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.VERYSHORT);
			
			asserter.elementClick(qfd_Library.getSave(), asserter.new XPath("//div[contains(text(),'Successfully saved')]"), true);
			
			if(asserter.verifyExist("Successfully saved"))
				log.warn("Verified the task successfully updated.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='" + taskName + "Updated']")))
				log.warn("Verified it goes back to Task view.");
			
			if(asserter.verifyExist(asserter.new XPath("//p[contains(text(),'" + desc + " Updated')]")))
				log.warn("Verified the updated infomation appear in the task.");
			
			qfd_Calendar.getEdit().click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Edit Task']")))
				log.warn("Verified the Edit page displays.");	
			
			if(asserter.verifyTrue(qfd_Library.getLinkPageName().getAttribute("value").equals(taskName + "Updated")))
				log.warn("Verified the name is correct.");	
			
			Calendar now = Calendar.getInstance();   
	        now.set(Calendar.DATE, now.get(Calendar.DATE));       		
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy"); 
			String day = sdf.format(now.getTime());
			
			//mary
			int temp = day.indexOf('/');
			String day1 = day.substring(0, temp);
			String day2 = day.substring(temp+1);
			
			if(day1.charAt(0) == '0') day1 = day1.substring(1);
			if(day2.charAt(0) == '0') day2 = day2.substring(1);
			
			day1 = day1.concat("/");
			day = day1.concat(day2);
			//mary
			
			
			if(asserter.verifyTrue(qfd_Task.getStartDate().getAttribute("value").equals(day)))
				log.warn("Verified the Start date is correct.");	
			
			now.set(Calendar.DATE, now.get(Calendar.DATE) + 1);       		
			sdf = new SimpleDateFormat("MM/dd/yyyy"); 
			day = sdf.format(now.getTime());
			
			//mary
			temp = day.indexOf('/');
			day1 = day.substring(0, temp);
			day2 = day.substring(temp+1);
			
			if(day1.charAt(0) == '0') day1 = day1.substring(1);
			if(day2.charAt(0) == '0') day2 = day2.substring(1);
			
			day1 = day1.concat("/");
			day = day1.concat(day2);
			//mary
			
			if(asserter.verifyTrue(qfd_Task.getDueDate().getAttribute("value").equals(day)))
				log.warn("Verified the Due date is correct.");	
			
			qfd_Page.getCancel().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().alert().accept();
			log.info("Clicking on OK button.");
			Thread.sleep(Wait.SHORT);
			
			goTask();
			
			if(asserter.verifyExist(asserter.new XPath("//div[contains(text(),'All Tasks')]")))
				log.warn("Verified no errors are generated.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + taskName + "Updated']")))
				log.warn("Verified the Edits are displayed.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Edit Task'.");
		log.info("************************************");
		
	}

	public void createSimpleTask(String taskName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Create Simple Task...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			goTask();
			
			qfd_Task.getNewTask().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Library.getLinkPageName().sendKeys(taskName);
			Thread.sleep(Wait.SHORT);
			
			asserter.elementClick(qfd_Library.getSave(), asserter.new XPath("//a[text()='Subscribe to these tasks']"), true);
			
			if(asserter.verifyExist("Successfully saved"))
				log.warn("Verified the task successfully created.");
			
			if(asserter.verifyTrue(driver.findElements(By.xpath("//span[contains(text(),'Unassigned')]")).size() == 1))
				log.warn("Verified the Assigned to is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//img[@title='Overdue task']")))
				log.warn("Verified the dates are correct.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Create Simple Task'.");
		log.info("************************************");
		
	}

	public void createHighAssignTask(String taskName, String userName,
			String attachPath) throws Exception {

		log.info("************************************");
		log.info("Starting 'Create High Priority Task Assigned To A User...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			goTask();
			
			qfd_Task.getNewTask().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Library.getLinkPageName().sendKeys(taskName);
			Thread.sleep(Wait.SHORT);
			
			qfd_Task.getPriority().click();
			Thread.sleep(Wait.SHORT);
			
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.PAUSE);
			action.sendKeys(Keys.ENTER).perform();
			log.info("Clicking on High item.");
			Thread.sleep(Wait.PAUSE);
			
			asserter.checkboxClick(asserter.new XPath("//input[@title='Mark as milestone']"), true);
			
			Calendar now = Calendar.getInstance();   
	        now.set(Calendar.DATE, now.get(Calendar.DATE));       		
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy"); 
			String day = sdf.format(now.getTime());
			
			qfd_Task.getStartDate().sendKeys(day);
			Thread.sleep(Wait.SHORT);
			
			now.set(Calendar.DATE, now.get(Calendar.DATE) + 1);       		
			sdf = new SimpleDateFormat("MM/dd/yyyy"); 
			day = sdf.format(now.getTime());
			
			qfd_Task.getDueDate().sendKeys(day);
			Thread.sleep(Wait.SHORT);
			
			qfd_Task.getAssign().click();
			Thread.sleep(Wait.MID);
			
			asserter.checkboxClick(asserter.new XPath("//input[contains(@title,'" + userName + "')]"), true);
			
			qfd_Library.getOK().click();
			Thread.sleep(Wait.MID);
			
			qfd_Library.getPageAttach().click();
			Thread.sleep(Wait.SHORT);

			qfd_Library.getFileField().sendKeys(attachPath);
			Thread.sleep(Wait.SHORT);
			
			asserter.elementClick(qfd_Library.getSave(), asserter.new XPath("//a[text()='Subscribe to these tasks']"), true);
			
			if(asserter.verifyExist("Successfully saved"))
				log.warn("Verified the task successfully created.");
			
			if(asserter.verifyTrue(driver.findElements(By.xpath("//th[@class='h-tasksItemTimeline-bg h-taskOn']")).size() == 2))
				log.warn("Verified the dates are correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='High']")))
				log.warn("Verified the priority is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[contains(text(),'" + userName + "')]")))
				log.warn("Verified the Assigned to is correct.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Create High Priority Task Assigned To A User'.");
		log.info("************************************");
		
	}

	public void createMidAssignTask(String taskName, String userName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Create Medium Priority Task Assigned To A User...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			goTask();
			
			qfd_Task.getNewTask().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Library.getLinkPageName().sendKeys(taskName);
			Thread.sleep(Wait.SHORT);
			
			qfd_Task.getPriority().click();
			Thread.sleep(Wait.SHORT);
			
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.PAUSE);
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.PAUSE);
			action.sendKeys(Keys.ENTER).perform();
			log.info("Clicking on Medium item.");
			Thread.sleep(Wait.PAUSE);
			
			Calendar now = Calendar.getInstance();   
	        now.set(Calendar.DATE, now.get(Calendar.DATE));       		
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy"); 
			String day = sdf.format(now.getTime());
			
			qfd_Task.getStartDate().sendKeys(day);
			Thread.sleep(Wait.SHORT);
			
			now.set(Calendar.DATE, now.get(Calendar.DATE) + 1);       		
			sdf = new SimpleDateFormat("MM/dd/yyyy"); 
			day = sdf.format(now.getTime());
			
			qfd_Task.getDueDate().sendKeys(day);
			Thread.sleep(Wait.SHORT);
			
			qfd_Task.getAssign().click();
			Thread.sleep(Wait.MID);
			
			asserter.checkboxClick(asserter.new XPath("//input[contains(@title,'" + userName + "')]"), true);
			
			qfd_Library.getOK().click();
			Thread.sleep(Wait.MID);
			
			asserter.elementClick(qfd_Library.getSave(), asserter.new XPath("//a[text()='Subscribe to these tasks']"), true);
			
			if(asserter.verifyExist("Successfully saved"))
				log.warn("Verified the task successfully created.");
			
			if(asserter.verifyTrue(driver.findElements(By.xpath("//th[@class='h-tasksItemTimeline-bg h-taskOn']")).size() == 3))
				log.warn("Verified the dates are correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Medium']")))
				log.warn("Verified the priority is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[contains(text(),'" + userName + "')]")))
				log.warn("Verified the Assigned to is correct.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Create Medium Priority Task Assigned To A User'.");
		log.info("************************************");
		
	}

	public void createLowCompleteTask(String taskName) throws Exception {
		
		log.info("************************************");
		log.info("Starting 'Create Low Priority Task'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			goTask();
			
			qfd_Task.getNewTask().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Library.getLinkPageName().sendKeys(taskName);
			Thread.sleep(Wait.SHORT);
			
			qfd_Task.getPriority().click();
			Thread.sleep(Wait.SHORT);
			
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.PAUSE);
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.PAUSE);
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.PAUSE);
			action.sendKeys(Keys.ENTER).perform();
			log.info("Clicking on Low item.");
			Thread.sleep(Wait.PAUSE);
			
			Calendar now = Calendar.getInstance();   
	        now.set(Calendar.DATE, now.get(Calendar.DATE));       		
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy"); 
			String day = sdf.format(now.getTime());
			
			qfd_Task.getStartDate().sendKeys(day);
			Thread.sleep(Wait.SHORT);
			
			now.set(Calendar.DATE, now.get(Calendar.DATE) + 1);       		
			sdf = new SimpleDateFormat("MM/dd/yyyy"); 
			day = sdf.format(now.getTime());
			
			qfd_Task.getDueDate().sendKeys(day);
			Thread.sleep(Wait.SHORT);
			
			asserter.elementClick(qfd_Library.getSave(), asserter.new XPath("//a[text()='Subscribe to these tasks']"), true);
			
			if(asserter.verifyExist("Successfully saved"))
				log.warn("Verified the task successfully created.");
			
			if(asserter.verifyTrue(driver.findElements(By.xpath("//th[@class='h-tasksItemTimeline-bg h-taskOn']")).size() == 4))
				log.warn("Verified the dates are correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Low']")))
				log.warn("Verified the priority is correct.");
			
			if(asserter.verifyTrue(driver.findElements(By.xpath("//span[contains(text(),'Unassigned')]")).size() == 2))
				log.warn("Verified the Assigned to is correct.");
			
			qfd_LibraryTask.goItem(taskName);
			
			qfd_Calendar.getEdit().click();
			Thread.sleep(Wait.MID);
			
			asserter.checkboxClick(asserter.new XPath("//input[@title='Complete']"), true);
			
			asserter.elementClick(qfd_Library.getSave(), asserter.new XPath("//div[contains(text(),'Successfully saved')]"), true);
			
			goTask();
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Create Low Priority Task'.");
		log.info("************************************");
		
	}

	public void verifyListView() throws Exception {

		log.info("************************************");
		log.info("Starting 'Verify Tasks List View'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			qfd_Task.getListView().click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist(asserter.new XPath("//img[@alt='Mark as milestone']")))
				log.warn("Verified the list view displayed.");
			
			if(asserter.verifyExist(asserter.new XPath("//div[contains(text(),'All Tasks')]")))
				log.warn("Verified the default Showing is All Tasks.");
			
			qfd_Task.getViews().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//td[text()='Incomplete Tasks']")))
				log.warn("Verified the Incomplete Tasks item is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//td[text()='Milestones']")))
				log.warn("Verified the Milestones item is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//td[text()='All Tasks']")))
				log.warn("Verified the All Tasks item is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//td[text()='Tasks Assigned to Me']")))
				log.warn("Verified the Tasks Assigned to Me item is correct.");
			
			action.sendKeys(Keys.ENTER).perform();
			log.info("Clicking on Incomplete Tasks item.");
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='1-3 of 3']")))
				log.warn("Verified only Incomplete tasks displays.");
			
			qfd_Task.getViews().click();
			Thread.sleep(Wait.SHORT);
			
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.PAUSE);
			action.sendKeys(Keys.ENTER).perform();
			log.info("Clicking on Milestones item.");
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyTrue(driver.findElements(By.xpath("//img[@alt='Mark as milestone']")).size() == 2) && (asserter.verifyExist(asserter.new XPath("//span[text()='1-2 of 2']"))))
				log.warn("Verified only milestones tasks displays.");
			
			qfd_Task.getViews().click();
			Thread.sleep(Wait.SHORT);
			
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.PAUSE);
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.PAUSE);
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.PAUSE);
			action.sendKeys(Keys.ENTER).perform();
			log.info("Clicking on Tasks Assigned to Me item.");
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='1-1 of 1']")))
				log.warn("Verified only tasks which assign to user who is log in displays.");
			
			qfd_Task.getViews().click();
			Thread.sleep(Wait.SHORT);
			
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.PAUSE);
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.PAUSE);
			action.sendKeys(Keys.ENTER).perform();
			log.info("Clicking on All Tasks item.");
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='1-5 of 5']")))
				log.warn("Verified all tasks display in List View.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify Tasks List View'.");
		log.info("************************************");
		
	}

	public void verifyGanttView() throws Exception {

		log.info("************************************");
		log.info("Starting 'Verify Tasks Timeline View'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			qfd_Task.getTimelineView().click();
			Thread.sleep(Wait.MID);
			
			qfd_Task.getViews().click();
			Thread.sleep(Wait.SHORT);
			
			action.sendKeys(Keys.ENTER).perform();
			log.info("Clicking on Incomplete Tasks item.");
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='1-3 of 3']")))
				log.warn("Verified only Incomplete tasks displays.");
			
			qfd_Task.getViews().click();
			Thread.sleep(Wait.SHORT);
			
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.PAUSE);
			action.sendKeys(Keys.ENTER).perform();
			log.info("Clicking on Milestones item.");
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='1-2 of 2']")))
				log.warn("Verified only milestones tasks displays.");
			
			qfd_Task.getViews().click();
			Thread.sleep(Wait.SHORT);
			
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.PAUSE);
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.PAUSE);
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.PAUSE);
			action.sendKeys(Keys.ENTER).perform();
			log.info("Clicking on Tasks Assigned to Me item.");
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='1-1 of 1']")))
				log.warn("Verified only tasks which assign to user who is log in displays.");
			
			qfd_Task.getViews().click();
			Thread.sleep(Wait.SHORT);
			
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.PAUSE);
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.PAUSE);
			action.sendKeys(Keys.ENTER).perform();
			log.info("Clicking on All Tasks item.");
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='1-5 of 5']")))
				log.warn("Verified all tasks display in List View.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify Tasks Timeline View'.");
		log.info("************************************");
		
	}

	public void verifyDownload() throws Exception {

		log.info("************************************");
		log.info("Starting 'Verify Download'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			qfd_Task.getListView().click();
			Thread.sleep(Wait.MID);
			
			qfd_Page.getDownload().click();
			Thread.sleep(Wait.MID);
			
			action.sendKeys(Keys.ENTER).perform();
			Thread.sleep(Wait.SHORT);
			
			if(BrowserSetup.BrowserType.equalsIgnoreCase("firefox")){
				
				AT.setFocusDoKeys("Opening", "!o");
				AT.setFocusDoKeys("Opening", "{ENTER}");
					
				if(AT.checkForDialog("Word", Wait.VERYSHORT)){
						
					AT.setFocusDoKeys("Microsoft Word", "{ESC}");
					
					AT.setFocusDoKeys("Microsoft Word", "!{F4}N");
					Thread.sleep(Wait.SHORT);
				
				}
								
			}else if(BrowserSetup.BrowserType.equalsIgnoreCase("internetexplorer")){
				
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
			
			}
			
			driver.navigate().refresh();
			Thread.sleep(Wait.SHORT);
			
			goTask();
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify Download'.");
		log.info("************************************");
		
	}

	public void verifySort(String taskName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Verify Sorting'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			qfd_Task.getListView().click();
			Thread.sleep(Wait.MID);
			
			WebElement[] T = new WebElement[20];
			
			qfd_Task.getType().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyTrue(driver.findElements(By.xpath("//tr[@class='dojoDndItem']/td[1]/span/h4/a[@class='lotusLeft']")).toArray(T)[0].getAttribute("title").equals("Delete Test")))
				log.warn("Verified sort by Type is correct.");
				
			T = new WebElement[20];
			 
			qfd_Task.getStatus().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyTrue(driver.findElements(By.xpath("//tr[@class='dojoDndItem']/td[1]/span/h4/a[@class='lotusLeft']")).toArray(T)[0].getAttribute("title").equals(taskName)))
				log.warn("Verified sort by Status is correct.");
			
			T = new WebElement[20];
			 
			qfd_Task.getPrioritySort().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyTrue(driver.findElements(By.xpath("//tr[@class='dojoDndItem']/td[1]/span/h4/a[@class='lotusLeft']")).toArray(T)[0].getAttribute("title").equals("task3")))
				log.warn("Verified sort by Priority is correct.");
			
			T = new WebElement[20];
			 
			qfd_Task.getNameSort().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyTrue(driver.findElements(By.xpath("//tr[@class='dojoDndItem']/td[1]/span/h4/a[@class='lotusLeft']")).toArray(T)[0].getAttribute("title").equals("Delete Test")))
				log.warn("Verified sort by Name is correct.");
			
			T = new WebElement[20];
			 
			qfd_Task.getAssignSort().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyTrue(driver.findElements(By.xpath("//tr[@class='dojoDndItem']/td[1]/span/h4/a[@class='lotusLeft']")).toArray(T)[0].getAttribute("title").equals(taskName)))
				log.warn("Verified sort by Assigned To is correct.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify Sorting'.");
		log.info("************************************");
		
	}

	public void deleteTask(String taskName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Delete Task'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			goTask();
			
			new Element(driver.findElement(By.xpath("//a[@title='" + taskName + "']/img[@alt='Click for options']")), taskName + "'s context menu").click();
			Thread.sleep(Wait.SHORT);
			
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ENTER).perform();
			log.info("Clicking on Delete item.");
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().alert().accept();
			log.info("Clicking on OK button.");
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist("was permanently deleted"))
				log.warn("Verified the confirmation info is correct.");
				
			if(asserter.verifyNotExist(asserter.new XPath("//a[text()='" + taskName + "']")))
				log.warn("Verified the task has been deleted.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Delete Task'.");
		log.info("************************************");
		
	}

	public void viewTaskInCalendar(String taskName) throws Exception {

		log.info("************************************");
		log.info("Starting 'View Task In Calendar'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			qfd_CalendarTask.goCalendar();
			
			if(asserter.verifyExist(asserter.new XPath("//div[contains(text(),'" + taskName + "')]")))
				log.warn("Verified that task displays in the calendar.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'View Task In Calendar'.");
		log.info("************************************");
		
	}
	
}
