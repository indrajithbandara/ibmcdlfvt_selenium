package tasks.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import config.Element;
import config.Wait;
import exceptions.ElementException;

import appobjects.common.QFD_BlogWiki;
import appobjects.library.QFD_Page;
import appobjects.toc.QFD_Library;

import tasks.toc.QFD_LibraryTask;
import util.Assert;
import util.Assert.XPath;

public class QFD_BlogWikiTask {

	private WebDriver driver;
	private Logger log;
	private Assert asserter;
	private Actions action;
	QFD_LibraryTask qfd_LibraryTask = null;
	QFD_Page qfd_Page = null;
	QFD_Library qfd_Library = null;
	QFD_BlogWiki qfd_WikiBlog = null;
	
	public QFD_BlogWikiTask(WebDriver driver,Logger log){
		
		this.driver = driver;
		this.log = log;
		this.asserter = new Assert(driver, log);
		this.action = new Actions(driver);
		qfd_LibraryTask = new QFD_LibraryTask(driver, log);
		qfd_Page = new QFD_Page(driver);
		qfd_Library = new QFD_Library(driver);
		qfd_WikiBlog = new QFD_BlogWiki(driver);
		
	}

	public void goBlog() throws Exception {
		
		asserter.elementClickWait(qfd_WikiBlog.getBlog(), asserter.new XPath("//a[text()='New Blog Entry']"), true);
		Thread.sleep(Wait.SHORT);
		
	}
	
	public void goWiki() throws Exception {
		
		asserter.elementClickWait(qfd_WikiBlog.getWiki(), asserter.new XPath("//a[text()='New Wiki Page']"), true);
		Thread.sleep(Wait.SHORT);
		
	}
	
	public void goWiki(String wikiName) throws Exception {
		
		asserter.elementClickWait(new Element(driver.findElement(By.xpath("//a[text()='" + wikiName + "']")), "item: " + wikiName), asserter.new XPath("//a[@id='moreActions']"), true);
		Thread.sleep(Wait.SHORT);
		
	}
	
	public void createEntry(String blogName, String desc, String authorName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Create Blog Entry'...");
		Thread.sleep(Wait.SHORT);
		
		try{

			goBlog();
			
			qfd_WikiBlog.getNewEntry().click();
			Thread.sleep(Wait.MID);
			
			qfd_WikiBlog.getEntryTitle().sendKeys(blogName);
			Thread.sleep(Wait.SHORT);
			
			Calendar now = Calendar.getInstance();         		
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy"); 
			String date = sdf.format(now.getTime());
			
			qfd_WikiBlog.getCreationDate().click();
			Thread.sleep(Wait.PAUSE);
			qfd_WikiBlog.getCreationDate().clear();
			Thread.sleep(Wait.PAUSE);
			qfd_WikiBlog.getCreationDate().sendKeys(date);
			Thread.sleep(Wait.SHORT);
			
			qfd_WikiBlog.getLocation().sendKeys("Place");
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().frame(qfd_WikiBlog.getBlogFrame());
			Thread.sleep(Wait.PAUSE);
			
			qfd_Library.getPageDesc().click();
			Thread.sleep(Wait.PAUSE);
			qfd_Library.getPageDesc().sendKeys(desc);
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			asserter.elementClickWait(qfd_WikiBlog.getCheckIn(), asserter.new XPath("//a[text()='Add Comment']"), true);
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Add Comment']")))
				log.warn("Verified the Blog is created successfully.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='" + blogName + "']")))
				log.warn("Verified the title is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//td[contains(text(),'" + date + "')]")))
				log.warn("Verified the date is correct.");
			
			WebElement[] T = new WebElement[5];
			
			if(asserter.verifyTrue(driver.findElements(By.xpath("//div[@class='BlogEntryTitle']/table[@cellpadding='4']/tbody/tr[2]/td")).toArray(T)[0].getText().contains("Place")))
				log.warn("Verified the location is correct.");
			
			if(asserter.verifyTrue(driver.findElements(By.xpath("//div[@class='BlogEntryTitle']/table[@cellpadding='4']/tbody/tr[2]/td")).toArray(T)[1].getText().contains(authorName)))
				log.warn("Verified the author is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//p[contains(text(),'" + desc + "')]")))
				log.warn("Verified the content is correct.");
				
			if(asserter.verifyExist(asserter.new XPath("//img[contains(@alt,'Send email')]")))
				log.warn("Verified the send email icon is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//img[contains(@alt,'formatted for printing')]")))
				log.warn("Verified the formatted for printing icon is correct.");
			
			goBlog();
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + blogName + "']")))
				log.warn("Verified the new post appears in the list.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + blogName + "']")))
				log.warn("Verified the title is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//td[contains(text(),'" + date + "')]")))
				log.warn("Verified the date is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//td[contains(text(),'Place')]")))
				log.warn("Verified the location is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//td[contains(text(),'" + authorName + "')]")))
				log.warn("Verified the author is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//p[contains(text(),'" + desc + "')]")))
				log.warn("Verified the content is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//img[contains(@alt,'Send email')]")))
				log.warn("Verified the send email icon is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//img[contains(@alt,'formatted for printing')]")))
				log.warn("Verified the formatted for printing icon is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//img[contains(@alt,'PermaLink')]")))
				log.warn("Verified the PermaLink icon is correct.");
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//a[contains(text(),'Comments')]")).getText().contains("0")))
				log.warn("Verified the value of Comments is 0.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Create Blog Entry'.");
		log.info("************************************");
		
	}

	public void createComment(String blogName, String comment, String authorName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Create Blog Comment'...");
		Thread.sleep(Wait.SHORT);
		
		try{

			goBlog();
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + blogName + "']")))
				log.warn("Verified the Blog entry displays.");
			
			qfd_LibraryTask.goItem(blogName);
			
			qfd_WikiBlog.getAddComment().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//input[@id='c_CommentName']")).getAttribute("value").equals(authorName)))
				log.warn("Verified the user name is correct.");
			
			if((asserter.verifyTrue(driver.findElement(By.xpath("//input[@id='c_CommentEmail']")).getAttribute("value").contains(authorName.replace(" ", "")))) && (asserter.verifyTrue(driver.findElement(By.xpath("//input[@id='c_CommentEmail']")).getAttribute("value").contains("@cn.ibm.com"))))
				log.warn("Verified the email is correct.");
			
			qfd_WikiBlog.getHomePage().sendKeys("http://www.ibm.com");
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().frame(qfd_WikiBlog.getBlogFrame());
			Thread.sleep(Wait.PAUSE);
			
			qfd_Library.getPageDesc().click();
			Thread.sleep(Wait.PAUSE);
			qfd_Library.getPageDesc().sendKeys(comment);
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			qfd_WikiBlog.getSubmit().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().alert().accept();
			log.info("Clicking on OK button.");
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//div[contains(text(),'Comments')]")))
				log.warn("Verified the comment appears.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[contains(text(),' Comment by " + authorName + "')]")))
				log.warn("Verified the Comment by is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[contains(text(),'http://www.ibm.com')]")))
				log.warn("Verified the Homepage is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//p[contains(text(),'" + comment + "')]")))
				log.warn("Verified the content is correct.");
			
			goBlog();
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//a[contains(text(),'Comments')]")).getText().contains("1")))
				log.warn("Verified the value of Comments is 1.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Create Blog Comment'.");
		log.info("************************************");
		
	}

	public void createResponse(String wikiName, String response, String authorName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Create Wiki Response'...");
		Thread.sleep(Wait.SHORT);
		
		try{

			goWiki();
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + wikiName + "']")))
				log.warn("Verified the Wiki page displays.");
			
			goWiki(wikiName);
			
			qfd_WikiBlog.getMoreActions().click();
			Thread.sleep(Wait.SHORT);
			
			action.sendKeys(Keys.TAB).perform();
			Thread.sleep(Wait.PAUSE);
			action.sendKeys(Keys.ENTER).perform();
			log.info("Clicking on New Response item.");
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[contains(text(),'Edit')]")))
				log.warn("Verified the Edit Wiki page appears.");
			
			if(asserter.verifyTrue(qfd_WikiBlog.getWikiTitleEdit().getAttribute("value").equals("Re: " + wikiName)))
				log.warn("Verified the default response title is correct.");
				
			driver.switchTo().frame(qfd_WikiBlog.getBlogFrame());
			Thread.sleep(Wait.PAUSE);
			
			qfd_Library.getPageDesc().click();
			Thread.sleep(Wait.PAUSE);
			qfd_Library.getPageDesc().sendKeys(response);
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			asserter.elementClickWait(qfd_WikiBlog.getCheckIn(), asserter.new XPath("//a[text()='New Wiki Page']"), true);
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='New Wiki Page']")))
				log.warn("Verified the Wiki page is created successfully.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Re: " + wikiName + "']")))
				log.warn("Verified the Wiki page displays in the list.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Re: " + wikiName + "']")))
				log.warn("Verified the title is correct.");
			
			Calendar now = Calendar.getInstance();         		
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy"); 
			String date = sdf.format(now.getTime());
			
			if(asserter.verifyTrue(driver.findElements(By.xpath("//a[text()='" + date + "']")).size() == 5))
				log.warn("Verified the Created On and Modified On date is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + authorName + "']")))
				log.warn("Verified the Updated By is correct.");
			
			goWiki("Re: " + wikiName);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='Re: " + wikiName + "']")))
				log.warn("Verified the Title is correct.");
			
			if(asserter.verifyTrue(driver.findElements(By.xpath("//span[contains(text(),'" + date + "')]")).size() == 2))
				log.warn("Verified the Created On and Modified On date is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//p[contains(text(),'" + response + "')]")))
				log.warn("Verified the Content is correct.");
			
			goWiki();
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Create Wiki Response'.");
		log.info("************************************");
		
	}
	
	public void editEntry(String blogName, String desc, String authorName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Edit Blog Entry'...");
		Thread.sleep(Wait.SHORT);
		
		try{

			goBlog();
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + blogName + "']")))
				log.warn("Verified the Blog entry displays.");
			
			qfd_LibraryTask.goItem(blogName);
			
			qfd_Page.getCheckOut().click();
			Thread.sleep(Wait.MID);
			
			qfd_WikiBlog.getEntryTitle().click();
			Thread.sleep(Wait.PAUSE);
			action.sendKeys(Keys.END).perform();
			Thread.sleep(Wait.PAUSE);
			qfd_WikiBlog.getEntryTitle().sendKeys(" Updated");
			Thread.sleep(Wait.SHORT);
			
			Calendar now = Calendar.getInstance();   
			now.set(Calendar.DATE, now.get(Calendar.DATE) + 1); 
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy"); 
			String date = sdf.format(now.getTime());
			
			qfd_WikiBlog.getCreationDate().click();
			Thread.sleep(Wait.PAUSE);
			qfd_WikiBlog.getCreationDate().clear();
			Thread.sleep(Wait.PAUSE);
			qfd_WikiBlog.getCreationDate().sendKeys(date);
			Thread.sleep(Wait.SHORT);
			
			qfd_WikiBlog.getLocation().click();
			Thread.sleep(Wait.PAUSE);
			action.sendKeys(Keys.END).perform();
			Thread.sleep(Wait.PAUSE);
			qfd_WikiBlog.getLocation().sendKeys(" Updated");
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().frame(qfd_WikiBlog.getBlogFrame());
			Thread.sleep(Wait.PAUSE);
			
			qfd_Library.getPageDesc().click();
			Thread.sleep(Wait.PAUSE);
			action.sendKeys(Keys.END).perform();
			Thread.sleep(Wait.PAUSE);
			qfd_Library.getPageDesc().sendKeys(" Updated");
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			asserter.elementClickWait(qfd_WikiBlog.getCheckIn(), asserter.new XPath("//a[text()='Add Comment']"), true);
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Add Comment']")))
				log.warn("Verified the Blog is updated successfully.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='" + blogName + " Updated']")))
				log.warn("Verified the title is updated.");
			
			if(asserter.verifyExist(asserter.new XPath("//td[contains(text(),'" + date + "')]")))
				log.warn("Verified the date is updated.");
			
			WebElement[] T = new WebElement[5];
			
			if(asserter.verifyTrue(driver.findElements(By.xpath("//div[@class='BlogEntryTitle']/table[@cellpadding='4']/tbody/tr[2]/td")).toArray(T)[0].getText().contains("Place Updated")))
				log.warn("Verified the location is updated.");
			
			if(asserter.verifyTrue(driver.findElements(By.xpath("//div[@class='BlogEntryTitle']/table[@cellpadding='4']/tbody/tr[2]/td")).toArray(T)[1].getText().contains(authorName)))
				log.warn("Verified the author is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//p[contains(text(),'" + desc + " Updated')]")))
				log.warn("Verified the content is correct.");
				
			if(asserter.verifyExist(asserter.new XPath("//img[contains(@alt,'Send email')]")))
				log.warn("Verified the send email icon is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//img[contains(@alt,'formatted for printing')]")))
				log.warn("Verified the formatted for printing icon is correct.");
			
			goBlog();
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + blogName + " Updated']")))
				log.warn("Verified the new post appears in the list.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + blogName + " Updated']")))
				log.warn("Verified the title is updated.");
			
			if(asserter.verifyExist(asserter.new XPath("//td[contains(text(),'" + date + "')]")))
				log.warn("Verified the date is updated.");
			
			if(asserter.verifyExist(asserter.new XPath("//td[contains(text(),'Place Updated')]")))
				log.warn("Verified the location is updated.");
			
			if(asserter.verifyExist(asserter.new XPath("//td[contains(text(),'" + authorName + "')]")))
				log.warn("Verified the author is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//p[contains(text(),'" + desc + " Updated')]")))
				log.warn("Verified the content is updated.");
			
			if(asserter.verifyExist(asserter.new XPath("//img[contains(@alt,'Send email')]")))
				log.warn("Verified the send email icon is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//img[contains(@alt,'formatted for printing')]")))
				log.warn("Verified the formatted for printing icon is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//img[contains(@alt,'PermaLink')]")))
				log.warn("Verified the PermaLink icon is correct.");
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//a[contains(text(),'Comments')]")).getText().contains("1")))
				log.warn("Verified the value of Comments is 1.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Edit Blog Entry'.");
		log.info("************************************");
		
	}

	public void createMoreEntry(String blogName, String desc, String authorName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Create Blog Entry With A Link To More'...");
		Thread.sleep(Wait.SHORT);
		
		try{

			goBlog();
			
			qfd_WikiBlog.getNewPageOrFolder().click();
			Thread.sleep(Wait.MID);
			
			qfd_WikiBlog.getBlogEntry().click();
			Thread.sleep(Wait.MID);
			
			qfd_WikiBlog.getEntryTitle().sendKeys(blogName);
			Thread.sleep(Wait.SHORT);
			
			Calendar now = Calendar.getInstance();         		
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy"); 
			String date = sdf.format(now.getTime());
			
			qfd_WikiBlog.getCreationDate().click();
			Thread.sleep(Wait.PAUSE);
			qfd_WikiBlog.getCreationDate().clear();
			Thread.sleep(Wait.PAUSE);
			qfd_WikiBlog.getCreationDate().sendKeys(date);
			Thread.sleep(Wait.SHORT);
			
			qfd_WikiBlog.getLocation().sendKeys("Place");
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().frame(qfd_WikiBlog.getBlogFrame());
			Thread.sleep(Wait.PAUSE);
			
			qfd_Library.getPageDesc().click();
			Thread.sleep(Wait.PAUSE);
			qfd_Library.getPageDesc().sendKeys(desc);
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			qfd_WikiBlog.getAddMore().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().frame(qfd_WikiBlog.getMoreFrame());
			Thread.sleep(Wait.PAUSE);
			
			qfd_Library.getPageDesc().click();
			Thread.sleep(Wait.PAUSE);
			qfd_Library.getPageDesc().sendKeys(desc);
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			asserter.elementClickWait(qfd_WikiBlog.getCheckIn(), asserter.new XPath("//a[text()='Add Comment']"), true);
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Add Comment']")))
				log.warn("Verified the Blog is created successfully.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='" + blogName + "']")))
				log.warn("Verified the title is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//td[contains(text(),'" + date + "')]")))
				log.warn("Verified the date is correct.");
			
			WebElement[] T = new WebElement[5];
			
			if(asserter.verifyTrue(driver.findElements(By.xpath("//div[@class='BlogEntryTitle']/table[@cellpadding='4']/tbody/tr[2]/td")).toArray(T)[0].getText().contains("Place")))
				log.warn("Verified the location is correct.");
			
			if(asserter.verifyTrue(driver.findElements(By.xpath("//div[@class='BlogEntryTitle']/table[@cellpadding='4']/tbody/tr[2]/td")).toArray(T)[1].getText().contains(authorName)))
				log.warn("Verified the author is correct.");
			
			if(asserter.verifyTrue(driver.findElements(By.xpath("//p[contains(text(),'" + desc + "')]")).size() == 2))
				log.warn("Verified the both contents are correct.");
				
			if(asserter.verifyExist(asserter.new XPath("//img[contains(@alt,'Send email')]")))
				log.warn("Verified the send email icon is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//img[contains(@alt,'formatted for printing')]")))
				log.warn("Verified the formatted for printing icon is correct.");
			
			goBlog();
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + blogName + "']")))
				log.warn("Verified the new post appears in the list.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + blogName + "']")))
				log.warn("Verified the title is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//td[contains(text(),'" + date + "')]")))
				log.warn("Verified the date is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//td[contains(text(),'Place')]")))
				log.warn("Verified the location is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//td[contains(text(),'" + authorName + "')]")))
				log.warn("Verified the author is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//p[contains(text(),'" + desc + "')]")))
				log.warn("Verified the content of introduction part is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='More...']")))
				log.warn("Verified the More link is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//img[contains(@alt,'Send email')]")))
				log.warn("Verified the send email icon is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//img[contains(@alt,'formatted for printing')]")))
				log.warn("Verified the formatted for printing icon is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//img[contains(@alt,'PermaLink')]")))
				log.warn("Verified the PermaLink icon is correct.");
			
			T = new WebElement[5];
			
			if(asserter.verifyTrue(driver.findElements(By.xpath("//a[contains(text(),'Comments')]")).toArray(T)[1].getText().contains("0")))
				log.warn("Verified the value of Comments is 0.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Create Blog Entry With A Link To More'.");
		log.info("************************************");
		
	}

	public void deleteBlog(String blogName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Delete Blog Entry'...");
		Thread.sleep(Wait.SHORT);
		
		try{

			goBlog();
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + blogName + "']")))
				log.warn("Verified the Blog entry displays.");
			
			qfd_LibraryTask.goItem(blogName);
			
			qfd_WikiBlog.getMoreActions().click();
			Thread.sleep(Wait.SHORT);
			
			action.sendKeys(Keys.TAB).perform();
			Thread.sleep(Wait.PAUSE);
			action.sendKeys(Keys.ENTER).perform();
			log.info("Clicking on Delete item.");
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Confirm Delete']")))
				log.warn("Verified the Confirm Delete dialog appears.");
			
			qfd_WikiBlog.getYes().click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist(asserter.new XPath("//li[@class='lotusSelected']/a[text()='Blog']")))
				log.warn("Verified Blog home page appears.");
			
			if(asserter.verifyNotExist(asserter.new XPath("//a[text()='" + blogName + "]")))
				log.warn("Verified the blog is removed from the list.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Delete Blog Entry'.");
		log.info("************************************");
		
	}

	public void createWiki(String wikiName, String desc, String authorName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Create Wiki Page'...");
		Thread.sleep(Wait.SHORT);
		
		try{

			goWiki();
			
			qfd_WikiBlog.getNewWikiPage().click();
			Thread.sleep(Wait.MID);
			
			qfd_WikiBlog.getEntryTitle().sendKeys(wikiName);
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().frame(qfd_WikiBlog.getBlogFrame());
			Thread.sleep(Wait.PAUSE);
			
			qfd_Library.getPageDesc().click();
			Thread.sleep(Wait.PAUSE);
			qfd_Library.getPageDesc().sendKeys(desc);
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			asserter.elementClickWait(qfd_WikiBlog.getCheckIn(), asserter.new XPath("//a[text()='New Wiki Page']"), true);
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='New Wiki Page']")))
				log.warn("Verified the Wiki page is created successfully.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + wikiName + "']")))
				log.warn("Verified the Wiki page displays in the list.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + wikiName + "']")))
				log.warn("Verified the title is correct.");
			
			Calendar now = Calendar.getInstance();         		
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy"); 
			String date = sdf.format(now.getTime());
			
			if(asserter.verifyTrue(driver.findElements(By.xpath("//a[text()='" + date + "']")).size() == 3))
				log.warn("Verified the Created On and Modified On date is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + authorName + "']")))
				log.warn("Verified the Updated By is correct.");
			
			goWiki(wikiName);
			
			if(asserter.verifyExist(asserter.new XPath("//li[@class='selected']/a[text()='Article']")))
				log.warn("Verified the Article tab is selected by default.");
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//div[@id='content_title']")).getText().contains(wikiName)))
				log.warn("Verified the Title is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//p[contains(text(),'" + desc + "')]")))
				log.warn("Verified the Content is correct.");
			
			qfd_WikiBlog.getHistory().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//td[text()='" + wikiName + "']")))
				log.warn("Verified the versions under History tab are correct.");
			
			goWiki();
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Create Wiki Page'.");
		log.info("************************************");
		
	}

	public void editWiki(String wikiName, String desc, String authorName, String attachPath, String attachName) throws Exception {
		
		log.info("************************************");
		log.info("Starting 'Edit Wiki Page'...");
		Thread.sleep(Wait.SHORT);
		
		try{

			goWiki();
			
			if(asserter.verifyExist(asserter.new XPath("//p[text()='Here we put our Wiki documents']")))
				log.warn("Verified the Wiki home page displays.");
			
			goWiki(wikiName);
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//div[@id='content_title']")).getText().contains(wikiName)))
				log.warn("Verified the Wiki page view displays.");
			
			qfd_Page.getCheckOut().click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='Edit:']")))
				log.warn("Verified the Edit Wiki page appears.");
			
			qfd_WikiBlog.getEntryTitle().click();
			Thread.sleep(Wait.PAUSE);
			action.sendKeys(Keys.END).perform();
			Thread.sleep(Wait.PAUSE);
			qfd_WikiBlog.getEntryTitle().sendKeys(" Updated");
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().frame(qfd_WikiBlog.getBlogFrame());
			Thread.sleep(Wait.PAUSE);
			
			qfd_Library.getPageDesc().click();
			Thread.sleep(Wait.PAUSE);
			action.sendKeys(Keys.END).perform();
			Thread.sleep(Wait.PAUSE);
			qfd_Library.getPageDesc().sendKeys(" Updated");
			Thread.sleep(Wait.SHORT);
			
			//mary
			Thread.sleep(Wait.SHORT);
			Thread.sleep(Wait.SHORT);
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			qfd_WikiBlog.getFileField().sendKeys(attachPath);
			Thread.sleep(Wait.SHORT);
			
			qfd_WikiBlog.getWikiChangeComment().sendKeys(desc);
			Thread.sleep(Wait.SHORT);
			
			asserter.elementClickWait(qfd_WikiBlog.getCheckIn(), asserter.new XPath("//p[text()='Here we put our Wiki documents']"), true);
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + wikiName + " Updated']")))
				log.warn("Verified the Wiki page displays in the list.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + wikiName + " Updated']")))
				log.warn("Verified the title is correct.");
			
			Calendar now = Calendar.getInstance();         		
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy"); 
			String date = sdf.format(now.getTime());
			
			if(asserter.verifyTrue(driver.findElements(By.xpath("//a[text()='" + date + "']")).size() == 5))
				log.warn("Verified the Created On and Modified On date is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + authorName + "']")))
				log.warn("Verified the Updated By is correct.");
			
			goWiki(wikiName + " Updated");
			
			if(asserter.verifyExist(asserter.new XPath("//li[@class='selected']/a[text()='Article']")))
				log.warn("Verified the Article tab is selected by default.");
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//div[@id='content_title']")).getText().contains(wikiName)))
				log.warn("Verified the Title is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//p[contains(text(),'" + desc + "')]")))
				log.warn("Verified the Content is correct.");
			
			qfd_WikiBlog.getHistory().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//td[text()='" + wikiName + " Updated']")))
				log.warn("Verified the versions under History tab are correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + wikiName + "']")))
				log.warn("Verified the pervious vision is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[contains(text(),'" + attachName + "')]")))
				log.warn("Verified the attachment is correct.");
			
			goWiki();
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Edit Wiki Page'.");
		log.info("************************************");
		
	}

	public void createWikiTOC(String wikiName, String desc, String authorName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Create Wiki Page On The Table Of Content'...");
		Thread.sleep(Wait.SHORT);
		
		try{

			goWiki();
			
			qfd_WikiBlog.getNewPageOrFolder().click();
			Thread.sleep(Wait.MID);
			
			qfd_WikiBlog.getWikiPage().click();
			Thread.sleep(Wait.MID);
			
			qfd_WikiBlog.getEntryTitle().sendKeys(wikiName);
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().frame(qfd_WikiBlog.getBlogFrame());
			Thread.sleep(Wait.PAUSE);
			
			qfd_Library.getPageDesc().click();
			Thread.sleep(Wait.PAUSE);
			qfd_Library.getPageDesc().sendKeys(desc);
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			asserter.elementClickWait(qfd_WikiBlog.getCheckInOption(), asserter.new XPath("//h1[contains(text(),'Create with options')]"), true);
			
			asserter.elementClickWait(qfd_WikiBlog.getNext(), asserter.new XPath("//a[text()='New Wiki Page']"), true);
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='New Wiki Page']")))
				log.warn("Verified the Wiki page is created successfully.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + wikiName + "']")))
				log.warn("Verified the Wiki page displays in the list.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + wikiName + "']")))
				log.warn("Verified the title is correct.");
			
			Calendar now = Calendar.getInstance();         		
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy"); 
			String date = sdf.format(now.getTime());
			
			if(asserter.verifyTrue(driver.findElements(By.xpath("//a[text()='" + date + "']")).size() == 7))
				log.warn("Verified the Created On and Modified On date is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + authorName + "']")))
				log.warn("Verified the Updated By is correct.");
			
			goWiki(wikiName);
			
			if(asserter.verifyExist(asserter.new XPath("//li[@class='selected']/a[text()='Article']")))
				log.warn("Verified the Article tab is selected by default.");
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//div[@id='content_title']")).getText().contains(wikiName)))
				log.warn("Verified the Title is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//p[contains(text(),'" + desc + "')]")))
				log.warn("Verified the Content is correct.");
			
			qfd_WikiBlog.getHistory().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//td[text()='" + wikiName + "']")))
				log.warn("Verified the versions under History tab are correct.");
			
			goWiki();
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Create Wiki Page On The Table Of Content'.");
		log.info("************************************");
		
	}

	public void deleteWiki(String wikiName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Delete Wiki Page'...");
		Thread.sleep(Wait.SHORT);
		
		try{

			goWiki();
			
			if(asserter.verifyExist(asserter.new XPath("//p[text()='Here we put our Wiki documents']")))
				log.warn("Verified the Wiki home page displays.");
			
			goWiki(wikiName);
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//div[@id='content_title']")).getText().contains(wikiName)))
				log.warn("Verified the Wiki page view displays.");
			
			qfd_WikiBlog.getMoreActions().click();
			Thread.sleep(Wait.SHORT);
			
			action.sendKeys(Keys.TAB).perform();
			Thread.sleep(Wait.PAUSE);
			action.sendKeys(Keys.TAB).perform();
			Thread.sleep(Wait.PAUSE);
			action.sendKeys(Keys.TAB).perform();
			Thread.sleep(Wait.PAUSE);
			action.sendKeys(Keys.TAB).perform();
			Thread.sleep(Wait.PAUSE);
			action.sendKeys(Keys.TAB).perform();
			Thread.sleep(Wait.PAUSE);
			action.sendKeys(Keys.ENTER).perform();
			log.info("Clicking on Delete item.");
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Confirm Delete']")))
				log.warn("Verified the Confirm Delete dialog appears.");
			
			qfd_WikiBlog.getYes().click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist(asserter.new XPath("//p[text()='Here we put our Wiki documents']")))
				log.warn("Verified Wiki home page appears.");
			
			if(asserter.verifyNotExist(asserter.new XPath("//a[text()='" + wikiName + "]")))
				log.warn("Verified the Wiki page is removed from the list.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Delete Wiki Page'.");
		log.info("************************************");
		
	}
	
}
