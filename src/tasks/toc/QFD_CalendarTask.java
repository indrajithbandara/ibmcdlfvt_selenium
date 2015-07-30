package tasks.toc;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import appobjects.library.QFD_Page;
import appobjects.toc.QFD_Calendar;
import appobjects.toc.QFD_Library;

import util.Assert;
import util.Assert.XPath;

import config.Element;
import config.Wait;
import exceptions.ElementException;

public class QFD_CalendarTask {

	private WebDriver driver;
	private Logger log;
	private Assert asserter;
	Actions action = null;
	QFD_LibraryTask qfd_LibraryTask = null;
	QFD_Page qfd_Page = null;
	QFD_Library qfd_Library = null;
	QFD_Calendar qfd_Calendar = null;
	
	public QFD_CalendarTask(WebDriver driver, Logger log){
		
		this.driver = driver;
		this.log = log;
		this.asserter = new Assert(driver, log);
		action = new Actions(driver);
		qfd_LibraryTask = new QFD_LibraryTask(driver, log);
		qfd_Page = new QFD_Page(driver);
		qfd_Library = new QFD_Library(driver);
		qfd_Calendar = new QFD_Calendar(driver);
		
	}

	public void goCalendar() throws Exception {
		
		asserter.elementClick(qfd_Calendar.getCalendar(), asserter.new XPath("//h1[text()='Calendar']"), true);
		Thread.sleep(Wait.SHORT);
		
	}

	public void createRichEvent(String eventName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Create Calender Event With Rich Text'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			goCalendar();
			
			qfd_Calendar.getNewEvent().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='New Calendar Event']")))
				log.warn("Verified the edit view is displayed.");
			
			qfd_Library.getLinkPageName().sendKeys(eventName);
			Thread.sleep(Wait.SHORT);
			
			qfd_Page.getTextColor().click();
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
			log.info("Clicking on Green item.");
			
			qfd_Page.getBGColor().click();
			Thread.sleep(Wait.SHORT);
			
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ENTER).perform();
			Thread.sleep(Wait.SHORT);
			log.info("Clicking on Black item.");
			
			driver.switchTo().frame(qfd_Library.getRichTextFrame());
			Thread.sleep(Wait.PAUSE);
			
			qfd_Library.getPageDesc().sendKeys("GREEN ON BLACK");
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Page.getTextColor().click();
			Thread.sleep(Wait.SHORT);
			
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
			log.info("Clicking on Yellow item.");
			
			qfd_Page.getBGColor().click();
			Thread.sleep(Wait.SHORT);
			
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ENTER).perform();
			Thread.sleep(Wait.SHORT);
			log.info("Clicking on Red item.");
			
			driver.switchTo().frame(qfd_Library.getRichTextFrame());
			Thread.sleep(Wait.PAUSE);
			Thread.sleep(Wait.PAUSE);
			Thread.sleep(Wait.PAUSE);
			
			action.sendKeys(Keys.ENTER).perform();
			Thread.sleep(Wait.SHORT);
			Thread.sleep(Wait.PAUSE);
			Thread.sleep(Wait.PAUSE);
			
			qfd_Library.getPageDesc().sendKeys("YELLOW ON RED");
			Thread.sleep(Wait.SHORT);
			Thread.sleep(Wait.PAUSE);
			Thread.sleep(Wait.PAUSE);
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.VERYSHORT);
			Thread.sleep(Wait.PAUSE);
			
			qfd_Page.getTextColor().click();
			Thread.sleep(Wait.SHORT);
			Thread.sleep(Wait.SHORT);
			Thread.sleep(Wait.SHORT);
			
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ENTER).perform();
			Thread.sleep(Wait.SHORT);
			log.info("Clicking on Black item.");
			
			qfd_Page.getBGColor().click();
			Thread.sleep(Wait.SHORT);
			
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ENTER).perform();
			Thread.sleep(Wait.SHORT);
			log.info("Clicking on White item.");
			
			driver.switchTo().frame(qfd_Library.getRichTextFrame());
			Thread.sleep(Wait.PAUSE);
			
			action.sendKeys(Keys.ENTER).perform();
			Thread.sleep(Wait.SHORT);
			
			qfd_Library.getPageDesc().sendKeys("BLACK ON WHITE");
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.VERYSHORT);
			
			asserter.elementClick(qfd_Library.getSave(), asserter.new XPath("//a[text()='Subscribe to this calendar']"), true);
			
			if(asserter.verifyExist("Successfully saved"))
				log.warn("Verified the event successfully created.");
			
			goEvent(eventName);
			Thread.sleep(Wait.VERYSHORT*5);
			if(asserter.verifyExist(asserter.new XPath("//span[contains(@style,'#2f4f4f')]/span[contains(@style,'#000000') and contains(text(),'GREEN ON BLACK')]")))
//			if(asserter.verifyExist(asserter.new XPath("//span[contains(@style,'(47, 79, 79)')]/span[contains(@style,'(0, 0, 0)') and contains(text(),'GREEN ON BLACK')]")))
				log.warn("Verified the GREEN ON BLACK line is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[contains(@style,'#8b4513')]/span[contains(@style,'#800000') and contains(text(),'YELLOW ON RED')]")))
//			if(asserter.verifyExist(asserter.new XPath("//span[contains(@style,'(139, 69, 19)')]/span[contains(@style,'(128, 0, 0)') and contains(text(),'YELLOW ON RED')]")))
				log.warn("Verified the YELLOW ON RED line is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//p[contains(text(),'BLACK ON WHITE')]")))
				log.warn("Verified the BLACK ON WHITE line is correct.");
			
			qfd_LibraryTask.goLibrary();
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Create Calender Event With Rich Text'.");
		log.info("************************************");
		
	}

	public void goEvent(String eventName) throws Exception {

		Thread.sleep(Wait.SHORT);
		
		goCalendar();
		
		Thread.sleep(Wait.SHORT*5);
		
		WebElement event = driver.findElement(By.xpath("//div[contains(text(),'" + eventName + "')]"));
		
		
		action.doubleClick(event).perform();
		log.info("Clicking on event : " + eventName);
		Thread.sleep(Wait.SHORT);
		
	}

	public void createAllDayEvent(String eventName, String desc) throws Exception {

		log.info("************************************");
		log.info("Starting 'Create All Day Event'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			goCalendar();
			
			qfd_Calendar.getNewEvent().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='New Calendar Event']")))
				log.warn("Verified the edit view is displayed.");
			
			qfd_Library.getLinkPageName().sendKeys(eventName);
			Thread.sleep(Wait.SHORT);
			
			asserter.checkboxClick(asserter.new XPath("//input[@alt='All-day event']"), true);
			
			driver.switchTo().frame(qfd_Library.getRichTextFrame());
			Thread.sleep(Wait.PAUSE);
			
			qfd_Library.getPageDesc().click();
			Thread.sleep(Wait.PAUSE);
			
			qfd_Library.getPageDesc().sendKeys(desc);
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.VERYSHORT);
			
			asserter.elementClick(qfd_Library.getSave(), asserter.new XPath("//a[text()='Subscribe to this calendar']"), true);
			
			if(asserter.verifyExist("Successfully saved"))
				log.warn("Verified the event successfully created.");
			
			qfd_LibraryTask.goLibrary();
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Create All Day Event'.");
		log.info("************************************");
		
	}

	public void createAndVerify(String eventName, String desc, String attachPath) throws Exception {

		log.info("************************************");
		log.info("Starting 'Create Event And Verify Calendar UI'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			goCalendar();
			
			Calendar now = Calendar.getInstance();         		
			SimpleDateFormat sdf = new SimpleDateFormat("MMMM"); 
			String day = sdf.format(now.getTime());
			
			qfd_Calendar.getNewEvent().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='New Calendar Event']")))
				log.warn("Verified the edit view is displayed.");
			
			qfd_Library.getLinkPageName().sendKeys(eventName);
			Thread.sleep(Wait.SHORT);
			
			sdf = new SimpleDateFormat("MM/dd/yyyy"); 
			day = sdf.format(now.getTime());
			
			qfd_Calendar.getDate().clear();
			Thread.sleep(Wait.PAUSE);
			qfd_Calendar.getDate().sendKeys(day);
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().frame(qfd_Library.getRichTextFrame());
			Thread.sleep(Wait.PAUSE);
			
			qfd_Library.getPageDesc().click();
			Thread.sleep(Wait.PAUSE);
			
			qfd_Library.getPageDesc().sendKeys(desc);
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Library.getPageAttach().click();
			Thread.sleep(Wait.SHORT);

			qfd_Library.getFileField().sendKeys(attachPath);
			Thread.sleep(Wait.SHORT);
			
			asserter.elementClick(qfd_Library.getSave(), asserter.new XPath("//a[text()='Subscribe to this calendar']"), true);
			
			if(asserter.verifyExist("Successfully saved"))
				log.warn("Verified the event successfully created.");
			
			sdf = new SimpleDateFormat("MMMM"); 
			day = sdf.format(now.getTime());
			sdf = new SimpleDateFormat("yyyy"); 
			String year = sdf.format(now.getTime());
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//div[@id='calView-navigator-current']")).getText().contains(day + " " + year)))
				log.warn("Verified user is in one month view.");
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//div[contains(text(),'You are in')]")).getText().contains("Calendar")))
				log.warn("Verified the breadcrumb of is correct.");
			
			goCalendar();
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//div[@id='calView-navigator-current']")).getText().contains(day + " " + year)))
				log.warn("Verified user is in one month view.");
			
			if(asserter.verifyExist(asserter.new XPath("//div[contains(text(),'" + eventName + "')]")))
				log.warn("Verified time and name display correctly.");
			
			goCalendar();
			
			new Element(driver.findElement(By.xpath("//div[contains(text(),'" + eventName + "')]")), eventName + " item").click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//span[contains(text(),'" + eventName + "')]")))
				log.warn("Verified time and name pops up correctly.");	
			
			goEvent(eventName);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='" + eventName + "']")))
				log.warn("Verified the view page opened.");	
			
			if(asserter.verifyExist(asserter.new XPath("//p[contains(text(),'" + desc + "')]")))
				log.warn("Verified the view page displays correctly.");	
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//div[contains(text(),'You are in')]")).getText().contains(eventName)))
				log.warn("Verified the breadcrumb of is correct.");
			
			goCalendar();
			
			WebElement event = driver.findElement(By.xpath("//div[contains(text(),'" + eventName + "')]"));
			
			action.contextClick(event).perform();
			log.info("Context clicking on event : " + eventName);
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//td[text()='View']")))
				log.warn("Verified the context menu displays as following.");	
			
			qfd_Calendar.getView().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='" + eventName + "']")))
				log.warn("Verified the view page opened.");	
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Create Event And Verify Calendar UI'.");
		log.info("************************************");
		
	}

	public void editEvent(String eventName, String desc) throws Exception {

		log.info("************************************");
		log.info("Starting 'Edit Event'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			Calendar now = Calendar.getInstance();   
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy"); 
			String day = sdf.format(now.getTime());
			
			qfd_Calendar.getEdit().click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Edit Calendar Event']")))
				log.warn("Verified the Edit page displays.");	
			
			if(asserter.verifyTrue(qfd_Library.getLinkPageName().getAttribute("value").equals(eventName)))
				log.warn("Verified the name is correct.");	
			
			//mary
			int temp = day.indexOf('/');
			String day1 = day.substring(0, temp);
			String day2 = day.substring(temp+1);
			
			if(day1.charAt(0) == '0') day1 = day1.substring(1);
			if(day2.charAt(0) == '0') day2 = day2.substring(1);
			
			day1 = day1.concat("/");
			day = day1.concat(day2);
			//mary
			
			if(asserter.verifyTrue(qfd_Calendar.getDate().getAttribute("value").equals(day)))
				log.warn("Verified the date is correct.");	
				
			qfd_Library.getLinkPageName().clear();
			Thread.sleep(Wait.PAUSE);
			
			qfd_Library.getLinkPageName().sendKeys(eventName + "Updated");
			Thread.sleep(Wait.PAUSE);
			
			sdf = new SimpleDateFormat("MM/dd/yyyy"); 
			now.set(Calendar.DATE, now.get(Calendar.DATE) + 1);
			day = sdf.format(now.getTime());
			
			qfd_Calendar.getDate().clear();
			Thread.sleep(Wait.PAUSE);
			qfd_Calendar.getDate().sendKeys(day);
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().frame(qfd_Library.getRichTextFrame());
			Thread.sleep(Wait.PAUSE);
			
			if(asserter.verifyExist(asserter.new XPath("//p[text()='" + desc + "']")))
				log.warn("Verified the description is correct.");	
			
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
				log.warn("Verified the event successfully saved.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='" + eventName + "Updated']")))
				log.warn("Verified the view page opened.");	
			
			if(asserter.verifyExist(asserter.new XPath("//p[contains(text(),'" + desc + " Updated')]")))
				log.warn("Verified the description updated correctly.");	
			
			sdf = new SimpleDateFormat("MMM"); 
			String month= sdf.format(now.getTime());
			sdf = new SimpleDateFormat("dd"); 
			String newDay = sdf.format(now.getTime());
			
			if(newDay.startsWith("0"))
				newDay = newDay.substring(1);
			
			if(asserter.verifyExist(asserter.new XPath("//span[contains(text(),'" + month + " " + newDay + "')]")))
				log.warn("Verified the date updated correctly.");
			
			goCalendar();
			
			if(asserter.verifyExist(asserter.new XPath("//div[contains(text(),'" + eventName + "Updated')]")))
				log.warn("Verified time and name display correctly.");
				
			qfd_Calendar.getSummaryView().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//div[text()='" + eventName + "Updated']")))
				log.warn("Verified the event displays in Summary view.");	
			
			WebElement event = driver.findElement(By.xpath("//div[text()='" + eventName + "Updated']"));
			
			action.contextClick(event).perform();
			log.info("Context clicking on event : " + eventName);
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//td[text()='View']")))
				log.warn("Verified the context menu displays as following.");	
			
			action.sendKeys(Keys.ENTER).perform();
			log.info("Clicking on View item.");
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='" + eventName + "Updated']")))
				log.warn("Verified the view page opened.");	
			
			goCalendar();
			
			qfd_Calendar.getSummaryView().click();
			Thread.sleep(Wait.SHORT);
			
			event = driver.findElement(By.xpath("//div[text()='" + eventName + "Updated']"));
			
			action.contextClick(event).perform();
			log.info("Context clicking on event : " + eventName);
			Thread.sleep(Wait.SHORT);
			
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ENTER).perform();
			log.info("Clicking on Edit item.");
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Edit Calendar Event']")))
				log.warn("Verified the Edit page displays.");	
			
			asserter.elementClick(qfd_Library.getSave(), asserter.new XPath("//div[contains(text(),'Successfully saved')]"), true);
			
			if(asserter.verifyExist("Successfully saved"))
				log.warn("Verified the event successfully saved.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Edit Event'.");
		log.info("************************************");
		
	}

	public void create3DaysEvent(String eventName, String desc) throws Exception {

		log.info("************************************");
		log.info("Starting 'Create All Day Event For 3 Days'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			goCalendar();
			
			qfd_Calendar.getNewEvent().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='New Calendar Event']")))
				log.warn("Verified the edit view is displayed.");
			
			qfd_Library.getLinkPageName().sendKeys(eventName);
			Thread.sleep(Wait.SHORT);
			
			asserter.checkboxClick(asserter.new XPath("//input[@alt='All-day event']"), true);
			
			qfd_Calendar.getRepeat().click();
			Thread.sleep(Wait.SHORT);
			
			action.sendKeys(Keys.DOWN).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ENTER).perform();
			log.info("Clicking on Daily item.");
			Thread.sleep(Wait.SHORT);
			
			qfd_Calendar.getRepeatDays().sendKeys("3");
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().frame(qfd_Library.getRichTextFrame());
			Thread.sleep(Wait.PAUSE);
			
			qfd_Library.getPageDesc().click();
			Thread.sleep(Wait.PAUSE);
			
			qfd_Library.getPageDesc().sendKeys(desc);
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.VERYSHORT);
			
			asserter.elementClick(qfd_Library.getSave(), asserter.new XPath("//a[text()='Subscribe to this calendar']"), true);
			
			if(asserter.verifyExist("Successfully saved"))
				log.warn("Verified the event successfully created.");
			
			if(asserter.verifyTrue(driver.findElements(By.xpath("//div[contains(@class,'s-cv-entry-innerframe') and contains(text(),'" + eventName + "')]")).size() == 3))
				log.warn("Verified the event displays correctly in month view.");
			
			goEvent(eventName);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='" + eventName + "']")))
				log.warn("Verified the view page opened.");	
			
			if(asserter.verifyExist(asserter.new XPath("//p[contains(text(),'" + desc + "')]")))
				log.warn("Verified the view page displays correctly.");	
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//div[contains(text(),'You are in')]")).getText().contains(eventName)))
				log.warn("Verified the breadcrumb of is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[contains(text(),'Repeats daily')]")))
				log.warn("Verified the event is all-day event.");	
			
			goCalendar();
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Create All Day Event For 3 Days'.");
		log.info("************************************");
		
	}

	public void verifyViews(String eventName, String allDayName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Verify Calendar Views'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			qfd_Calendar.getOneDayView().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//div[contains(text(),'" + allDayName + "')]")))
				log.warn("Verified the all-day event is in this view.");
			
			if(asserter.verifyNotExist(asserter.new XPath("//div[contains(text(),'" + eventName + "')]")))
				log.warn("Verified the regular event is not in this view.");
			
			qfd_Calendar.getTwoDaysView().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyTrue(driver.findElements(By.xpath("//div[contains(text(),'" + allDayName + "')]")).size() == 2))
				log.warn("Verified the all-day event displays correctly in this view.");
			
			if(asserter.verifyExist(asserter.new XPath("//div[contains(text(),'" + eventName + "')]")))
				log.warn("Verified the regular event is in this view.");
			
			qfd_Calendar.getOneWeekView().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyTrue(driver.findElements(By.xpath("//div[contains(text(),'" + allDayName + "')]")).size() == 3))
				log.warn("Verified the all-day event displays correctly in this view.");
			
			if(asserter.verifyExist(asserter.new XPath("//div[contains(text(),'" + eventName + "')]")))
				log.warn("Verified the regular event is in this view.");
			
			qfd_Calendar.getTwoWeeksView().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyTrue(driver.findElements(By.xpath("//div[contains(text(),'" + allDayName + "')]")).size() == 3))
				log.warn("Verified the all-day event displays correctly in this view.");
			
			if(asserter.verifyExist(asserter.new XPath("//div[contains(text(),'" + eventName + "')]")))
				log.warn("Verified the regular event is not in this view.");
			
			qfd_Calendar.getOneMonthView().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyTrue(driver.findElements(By.xpath("//div[contains(text(),'" + allDayName + "')]")).size() == 3))
				log.warn("Verified the all-day event displays correctly in this view.");
			
			if(asserter.verifyExist(asserter.new XPath("//div[contains(text(),'" + eventName + "')]")))
				log.warn("Verified the regular event is not in this view.");
			
			qfd_Calendar.getOneYearView().click();
			Thread.sleep(Wait.SHORT);
			
			/****original 
			Calendar now = Calendar.getInstance();   
			SimpleDateFormat sdf = new SimpleDateFormat("dd"); 
			 
			String day = sdf.format(now.getTime());
			
			if(asserter.verifyExist(asserter.new XPath("//td[contains(@id,'" + day + "') and text()='" + day + "']")))
				log.warn("Verified the One Year View displays correctly.");
			*/
			
			//try
			Calendar now = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); 
			String day1 = sdf.format(now.getTime());
			
			//mary modified this
			if(asserter.verifyTrue(driver.findElement(By.xpath("//td[contains(@id,'" + day1 + "')]")).getAttribute("style").contains("bold")))
				log.warn("Verified the One Year View displays correctly.");
			//end
			qfd_Calendar.getOneDayView().click();
			Thread.sleep(Wait.SHORT);
			
			
			sdf = new SimpleDateFormat("dd"); 
			//String day = sdf.format(now.getTime());
			day1 = sdf.format(now.getTime());
			sdf = new SimpleDateFormat("MMMM"); 
			String month = sdf.format(now.getTime());
			sdf = new SimpleDateFormat("yyyy"); 
			String year = sdf.format(now.getTime());
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//div[@id='calView-navigator-current']")).getText().contains(month + " " + day1 + ", " + year)))
					log.warn("Verified user is in one day view.");
			
			qfd_Calendar.getOneMonthView().click();
			Thread.sleep(Wait.SHORT);
			
			WebElement event = driver.findElement(By.xpath("//div[contains(text(),'" + eventName + "')]"));
			
			action.contextClick(event).perform();
			log.info("Context clicking on event : " + eventName);
			Thread.sleep(Wait.SHORT);
			
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.ENTER).perform();
			log.info("Clicking on Delete item.");
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().alert().dismiss();
			log.info("Clicking on Cancel button.");
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//div[contains(text(),'" + eventName + "')]")))
				log.warn("Verified the event has not been deleted.");
			
			action.contextClick(event).perform();
			log.info("Context clicking on event : " + eventName);
			Thread.sleep(Wait.SHORT);
			
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
				
			//mary
			driver.navigate().refresh();
			Thread.sleep(Wait.SHORT*2);
			
			goCalendar();
			
			if(asserter.verifyNotExist(asserter.new XPath("//div[contains(text(),'" + eventName + "')]")))
				log.warn("Verified the event has been deleted.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify Calendar Views'.");
		log.info("************************************");
		
	}
	
}
