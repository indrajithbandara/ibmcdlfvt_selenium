package tasks.toc;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import config.Element;
import config.Wait;
import exceptions.ElementException;

import util.Assert;
import util.Assert.XPath;
import appobjects.library.QFD_Page;
import appobjects.toc.QFD_Forum;
import appobjects.toc.QFD_Library;

public class QFD_ForumTask {

	private WebDriver driver;
	private Logger log;
	private Assert asserter;
	Actions action = null;
	QFD_LibraryTask qfd_LibraryTask = null;
	QFD_Library qfd_Library = null;
	QFD_Page qfd_Page = null;
	QFD_Forum qfd_Forum = null;
	
	public QFD_ForumTask(WebDriver driver, Logger log){
		
		this.driver = driver;
		this.log = log;
		this.asserter = new Assert(driver, log);
		action = new Actions(driver);
		qfd_LibraryTask = new QFD_LibraryTask(driver, log);
		qfd_Library = new QFD_Library(driver);
		qfd_Page = new QFD_Page(driver);
		qfd_Forum = new QFD_Forum(driver);
		
	}

	public void goForums() throws Exception {
		
		asserter.elementClick(qfd_Forum.getForum(), asserter.new XPath("//h1[text()='Forums']"), true);
		Thread.sleep(Wait.SHORT);
		
	}
	
	public void goForum(String forumName) throws Exception {
		
		asserter.elementClick(new Element(driver.findElement(By.linkText(forumName)), "forum : " + forumName), asserter.new XPath("//h2[text()='" + forumName + "']"), true);
		Thread.sleep(Wait.SHORT);
		
	}
	
	public void goTopic(String topicName) throws Exception {
		
		asserter.elementClick(new Element(driver.findElement(By.linkText(topicName)), "topic : " + topicName), asserter.new XPath("//h4[text()='" + topicName + "']"), true);
		Thread.sleep(Wait.SHORT);
		
	}
	
	public void createForum(String forumName, String desc) throws Exception {

		log.info("************************************");
		log.info("Starting 'Create Forum'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			goForums();
			
			qfd_Forum.getNewForum().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Library.getLinkPageName().sendKeys(forumName);
			Thread.sleep(Wait.SHORT);
			
			qfd_Library.getFolderDesc().sendKeys(desc);
			Thread.sleep(Wait.SHORT);
			
			qfd_Library.getOK().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//h2[text()='" + forumName + "']")))
				log.warn("Verified the forum view is displayed.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='" + desc + "']")))
				log.warn("Verified the description is correct.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Create Forum'.");
		log.info("************************************");
		
	}

	public void createTopicRichText(String topicName) throws Exception {
		
	
		log.info("************************************");
		log.info("Starting 'Create Topic With Rich Text'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			qfd_Forum.getNewTopic().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Library.getLinkPageName().sendKeys(topicName);
			Thread.sleep(Wait.SHORT);
			
			qfd_Library.getBold().click();
			Thread.sleep(Wait.SHORT);
			
			action.sendKeys("This line is bold").perform();
			log.info("Sending keys: 'This line is bold' into CKEditor.");
			Thread.sleep(Wait.SHORT);
			
//			driver.switchTo().frame(qfd_Library.getRichTextFrame());
//			Thread.sleep(Wait.PAUSE);
//			
//			qfd_Library.getPageDesc().click();
//			Thread.sleep(Wait.VERYSHORT);
//			
//			qfd_Library.getPageDesc().sendKeys("This line is bold");
//			Thread.sleep(Wait.SHORT);
//			
//			driver.switchTo().defaultContent();
//			Thread.sleep(Wait.VERYSHORT);
			
			asserter.elementClick(qfd_Library.getBold(), asserter.new XPath("//a[@class='cke_button_bold cke_off']"), true);
			
			qfd_Library.getItalic().click();
			Thread.sleep(Wait.SHORT);
			
			action.sendKeys(Keys.ENTER).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys("This line is italic").perform();
			log.info("Sending keys: 'This line is italic' into CKEditor.");
			Thread.sleep(Wait.SHORT);
			
//			driver.switchTo().frame(qfd_Library.getRichTextFrame());
//			Thread.sleep(Wait.PAUSE);
//			
//			qfd_Library.getPageDesc().click();
//			Thread.sleep(Wait.VERYSHORT);
//			action.sendKeys(Keys.END).perform();
//			Thread.sleep(Wait.VERYSHORT);
//			action.sendKeys(Keys.ENTER).perform();
//			Thread.sleep(Wait.VERYSHORT);
//			action.sendKeys("This line is italic");
//			Thread.sleep(Wait.VERYSHORT);
//			log.info("Sending keys: 'This line is italic' into CK Editor.");
//			
//			driver.switchTo().defaultContent();
//			Thread.sleep(Wait.VERYSHORT);
			
			asserter.elementClick(qfd_Library.getBold(), asserter.new XPath("//a[@class='cke_button_bold cke_off']"), true);
			asserter.elementClick(qfd_Library.getItalic(), asserter.new XPath("//a[@class='cke_button_italic cke_off']"), true);
			
			qfd_Library.getUnderline().click();
			Thread.sleep(Wait.SHORT);
			
			action.sendKeys(Keys.ENTER).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys("This line is underscore").perform();
			log.info("Sending keys: 'This line is underscore' into CKEditor.");
			Thread.sleep(Wait.SHORT);
			
//			driver.switchTo().frame(qfd_Library.getRichTextFrame());
//			Thread.sleep(Wait.PAUSE);
//			
//			qfd_Library.getPageDesc().click();
//			Thread.sleep(Wait.VERYSHORT);
//			action.sendKeys(Keys.END).perform();
//			Thread.sleep(Wait.VERYSHORT);
//			action.sendKeys(Keys.ARROW_DOWN).perform();
//			Thread.sleep(Wait.VERYSHORT);
//			action.sendKeys(Keys.END).perform();
//			Thread.sleep(Wait.VERYSHORT);
//			action.sendKeys(Keys.ENTER).perform();
//			Thread.sleep(Wait.VERYSHORT);
//			action.sendKeys("This line is underscore");
//			Thread.sleep(Wait.VERYSHORT);
//			log.info("Sending keys: 'This line is underscore' into CK Editor.");
//			
//			driver.switchTo().defaultContent();
//			Thread.sleep(Wait.VERYSHORT);
		
			asserter.elementClick(qfd_Library.getBold(), asserter.new XPath("//a[@class='cke_button_bold cke_off']"), true);
			asserter.elementClick(qfd_Library.getItalic(), asserter.new XPath("//a[@class='cke_button_italic cke_off']"), true);
			asserter.elementClick(qfd_Library.getUnderline(), asserter.new XPath("//a[@class='cke_button_underline cke_off']"), true);
			
			qfd_Library.getStrikeThrough().click();
			Thread.sleep(Wait.SHORT);

			action.sendKeys(Keys.ENTER).perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys("This line is strikethrough").perform();
			log.info("Sending keys: 'This line is strikethrough' into CKEditor.");
			Thread.sleep(Wait.SHORT);
			
//			driver.switchTo().frame(qfd_Library.getRichTextFrame());
//			Thread.sleep(Wait.PAUSE);
//			
//			qfd_Library.getPageDesc().click();
//			Thread.sleep(Wait.VERYSHORT);
//			action.sendKeys(Keys.END).perform();
//			Thread.sleep(Wait.VERYSHORT);
//			action.sendKeys(Keys.ARROW_DOWN).perform();
//			Thread.sleep(Wait.VERYSHORT);
//			action.sendKeys(Keys.ARROW_DOWN).perform();
//			Thread.sleep(Wait.VERYSHORT);
//			action.sendKeys(Keys.END).perform();
//			Thread.sleep(Wait.VERYSHORT);
//			action.sendKeys(Keys.ENTER).perform();
//			Thread.sleep(Wait.VERYSHORT);
//			action.sendKeys("This line is strikethrough");
//			Thread.sleep(Wait.VERYSHORT);
//			log.info("Sending keys: 'This line is strikethrough' into CK Editor.");
//			
//			driver.switchTo().defaultContent();
//			Thread.sleep(Wait.SHORT);
			
			asserter.elementClickWait(qfd_Library.getOK(), asserter.new XPath("//a[text()='Subscribe to this topic']"), true);
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist("Successfully saved"))
				log.warn("Verified the topic posted successfully.");
			
			if(asserter.verifyExist(asserter.new XPath("//strong[text()='This line is bold']")))
				log.warn("Verified the bold line is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//em[text()='This line is italic']")))
				log.warn("Verified the italic line is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//u[text()='This line is underscore']")))
				log.warn("Verified the underlined line is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//strike[text()='This line is strikethrough']")))
				log.warn("Verified the stroken through line is correct.");
			
			qfd_LibraryTask.goLibrary();
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Create Topic With Rich Text'.");
		log.info("************************************");
	
	}

	public void createTopic(String topicName, String desc, String attachPath) throws Exception {

		log.info("************************************");
		log.info("Starting 'Create Topic'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			qfd_Forum.getNewTopic().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Library.getLinkPageName().sendKeys(topicName);
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().frame(qfd_Library.getRichTextFrame());
			Thread.sleep(Wait.PAUSE);
			
			qfd_Library.getPageDesc().click();
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Library.getPageDesc().sendKeys(desc);
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
			
			asserter.elementClickWait(qfd_Library.getOK(), asserter.new XPath("//a[text()='Subscribe to this topic']"), true);
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist("Successfully saved"))
				log.warn("Verified the topic posted successfully.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Create Topic'.");
		log.info("************************************");
		
	}

	public void createResponse(String forumName, String topicName, String response) throws Exception {

		log.info("************************************");
		log.info("Starting 'Create Response'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			goForums();
			
			goForum(forumName);
			
			goTopic(topicName);
			
			qfd_Forum.getReply().click();
			Thread.sleep(Wait.SHORT);
			
			driver.navigate().refresh();
			
			qfd_Forum.getReply().click();
			Thread.sleep(Wait.SHORT);
			
			action.sendKeys(response).perform();
			log.info("Sending keys : " + response + " into CK Editor");
			Thread.sleep(Wait.SHORT);
			
			qfd_Forum.getPostReply().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			if(asserter.verifyExist("Your reply was successfully posted"))
				log.warn("Verified the reply posted successfully.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Create Response'.");
		log.info("************************************");
		
	}

	public void editForum(String forumName, String desc) throws Exception {

		log.info("************************************");
		log.info("Starting 'Edit Forum'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			qfd_Forum.getBreadcrumbForum().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='Forums']")))
				log.warn("Verified user is brought back to the Forums view.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + forumName + "']")))
				log.warn("Verified the Forum: " + forumName + " is at the top of the list.");
			
			if(asserter.verifyExist(asserter.new XPath("//td[text()='0']")))
				log.warn("Verified the Posts is 0.");
			
			if(asserter.verifyExist(asserter.new XPath("//div[text()='0']")))
				log.warn("Verified the Topics is 0.");
			
			goForum(forumName);
			
			qfd_Page.getMoreActions().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Library.getEditProperty().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Library.getLinkPageName().clear();
			Thread.sleep(Wait.PAUSE);
			
			qfd_Library.getLinkPageName().sendKeys(forumName + " Updated");
			Thread.sleep(Wait.SHORT);
			
			qfd_Library.getFolderDesc().clear();
			Thread.sleep(Wait.PAUSE);
			
			qfd_Library.getFolderDesc().sendKeys(desc + " Updated");
			Thread.sleep(Wait.SHORT);
			
			qfd_Library.getOK().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//h2[text()='" + forumName + " Updated']")))
				log.warn("Verified the forum name is updated.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='" + desc + " Updated']")))
				log.warn("Verified the description is updated.");
			
			qfd_Forum.getBreadcrumbForum().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='Forums']")))
				log.warn("Verified user is brought back to the Forums view.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + forumName + " Updated']")))
				log.warn("Verified the forum name is updated.");
			
			if(asserter.verifyExist(asserter.new XPath("//div[text()='" + desc + " Updated']")))
				log.warn("Verified the description is updated.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Edit Forum'.");
		log.info("************************************");
		
	}

	public void createTopicFromHome(String forumName, String topicName,
			String desc, String attachPath, String authorName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Create Topic From Home'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			goForums();
			
			goForum(forumName);
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		createTopic(topicName, desc, attachPath);
		
		try{
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + authorName + "']")))
				log.warn("Verified the author is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//h4[text()='" + topicName + "']")))
				log.warn("Verified the topic name is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//p[contains(text(),'" + desc + "')]")))
				log.warn("Verified the content is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[contains(text(),'Today')]")))
				log.warn("Verified the date is correct.");
			
			qfd_Forum.getBreadcrumbForum().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='Forums']")))
				log.warn("Verified user is brought back to the Forums view.");
			
			if(asserter.verifyExist(asserter.new XPath("//td[text()='1']")))
				log.warn("Verified the Posts is 1.");
			
			if(asserter.verifyExist(asserter.new XPath("//div[text()='1']")))
				log.warn("Verified the Topics is 1.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + forumName + "']")))
				log.warn("Verified the forum name is correct.");
			
			
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + authorName + "']")))
				log.warn("Verified the author is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + topicName + "']")))
				log.warn("Verified the topic name is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//div[contains(text(),'Today')]")))
				log.warn("Verified the date is correct.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Create Topic From Home'.");
		log.info("************************************");
		
	}

	public void editTopic(String forumName, String topicName, String desc) throws Exception {

		log.info("************************************");
		log.info("Starting 'Edit Topic'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			goForum(forumName);
			
			goTopic(topicName);
			
			qfd_Forum.getEdit().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Library.getLinkPageName().clear();
			Thread.sleep(Wait.PAUSE);
			
			qfd_Library.getLinkPageName().sendKeys(topicName + " Updated");
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().frame(qfd_Library.getRichTextFrame());
			Thread.sleep(Wait.PAUSE);
			
			qfd_Library.getPageDesc().click();
			Thread.sleep(Wait.VERYSHORT);
			
			action.sendKeys(Keys.CONTROL + "a").perform();
			Thread.sleep(Wait.VERYSHORT);
			action.sendKeys(Keys.BACK_SPACE).perform();
			Thread.sleep(Wait.VERYSHORT);
			log.info("Clearing the CK Editor.");
			
			qfd_Library.getPageDesc().sendKeys(desc + " Updated");
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			asserter.elementClickWait(qfd_Library.getOK(), asserter.new XPath("//a[text()='Subscribe to this topic']"), true);
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist("Successfully saved"))
				log.warn("Verified the topic posted successfully.");
			
			if(asserter.verifyExist(asserter.new XPath("//h4[text()='" + topicName + " Updated']")))
				log.warn("Verified the topic name is updated.");
			
			if(asserter.verifyExist(asserter.new XPath("//p[contains(text(),'" + desc + " Updated')]")))
				log.warn("Verified the content is updated.");
			
			new Element(driver.findElement(By.linkText(forumName)), forumName + " link on breadcrumb").click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//h2[text()='" + forumName + "']")))
				log.warn("Verified user is brought back to the Topics view.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + topicName + " Updated']")))
				log.warn("Verified the topic name is updated.");
			
			if(asserter.verifyExist(asserter.new XPath("//div[contains(text(),'Today')]")))
				log.warn("Verified the date is updated.");
			
			qfd_Forum.getBreadcrumbForum().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='Forums']")))
				log.warn("Verified user is brought back to the Forums view.");
			
			if(asserter.verifyExist(asserter.new XPath("//div[contains(text(),'Today')]")))
				log.warn("Verified the date is updated.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Edit Topic'.");
		log.info("************************************");
		
	}

	public void verifyReply(String forumName, String desc, String authorName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Verify Reply'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='" + desc + "']")))
				log.warn("Verified the reply's content is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='" + authorName + "']")))
				log.warn("Verified the author is correct.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[contains(text(),'Today')]")))
				log.warn("Verified the date is correct.");
			
			qfd_Forum.getReply().click();
			Thread.sleep(Wait.SHORT);
			
			action.sendKeys(desc + " Another").perform();
			log.info("Sending keys : " + desc + " Another" + " into CK Editor");
			Thread.sleep(Wait.SHORT);
			
			qfd_Forum.getPostReply().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			if(asserter.verifyExist("Your reply was successfully posted"))
				log.warn("Verified the reply posted successfully.");
			
			new Element(driver.findElement(By.linkText(forumName)), forumName + " link on breadcrumb").click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//h2[text()='" + forumName + "']")))
				log.warn("Verified user is brought back to the Topics view.");
			
			if(asserter.verifyExist(asserter.new XPath("//td[text()='2']")))
				log.warn("Verified the Replies' count has increased by 2.");
			
			qfd_Forum.getBreadcrumbForum().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='Forums']")))
				log.warn("Verified user is brought back to the Forums view.");
			
			if(asserter.verifyExist(asserter.new XPath("//td[text()='3']")))
				log.warn("Verified the Posts' count has increased by 2.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Verify Reply'.");
		log.info("************************************");
		
	}

	public void deleteReply(String forumName, String topicName, String reply, String authorName) throws Exception {
		
		log.info("************************************");
		log.info("Starting 'Delete Reply'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			goForums();
			
			goForum(forumName);
			
			goTopic(topicName);
			
			new Element(driver.findElement(By.xpath("//span[@title='" + reply + "']")), "Reply : " + reply).click();
			Thread.sleep(Wait.SHORT);
			
			new Element(driver.findElement(By.xpath("//a[@title='Delete' and contains(@href,'" + authorName + "')]")), "Delete link").click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().alert().accept();
			log.info("Clicking on OK button.");
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyNotExist(asserter.new XPath("//span[text()='" + reply + "']")))
				log.warn("Verified the reply is deleted.");
			
			new Element(driver.findElement(By.linkText(forumName)), forumName + " link on breadcrumb").click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//h2[text()='" + forumName + "']")))
				log.warn("Verified user is brought back to the Topics view.");
			
			if(asserter.verifyExist(asserter.new XPath("//td[text()='1']")))
				log.warn("Verified the Replies' count has decreased by 1.");
			
			qfd_Forum.getBreadcrumbForum().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='Forums']")))
				log.warn("Verified user is brought back to the Forums view.");
			
			if(asserter.verifyExist(asserter.new XPath("//td[text()='2']")))
				log.warn("Verified the Posts' count has decreased by 1.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Delete Reply'.");
		log.info("************************************");
		
	}

	public void deleteTopic(String forumName, String topicName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Delete Topic'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			goForum(forumName);
			
			goTopic(topicName);
			
			qfd_Forum.getDelete().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().alert().accept();
			log.info("Clicking on OK button.");
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyNotExist(asserter.new XPath("//a[text()='" + topicName + "']")))
				log.warn("Verified the topic is deleted.");
			
			qfd_Forum.getBreadcrumbForum().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='Forums']")))
				log.warn("Verified user is brought back to the Forums view.");
			
			if(asserter.verifyExist(asserter.new XPath("//td[text()='0']")))
				log.warn("Verified the Posts' count has decreased by 2.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Delete Topic'.");
		log.info("************************************");
		
	}

	public void deleteForum(String forumName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Delete Forum'...");
		Thread.sleep(Wait.SHORT);
		
		try{
			
			goForum(forumName);
			
			qfd_Page.getMoreActions().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Forum.getDeleteForum().click();
			Thread.sleep(Wait.SHORT);
			
			driver.switchTo().alert().accept();
			log.info("Clicking on OK button.");
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='Forums']")))
				log.warn("Verified user is brought back to the Forums view.");
			
			if(asserter.verifyNotExist(asserter.new XPath("//a[text()='" + forumName + "']")))
				log.warn("Verified the forum is deleted.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Delete Forum'.");
		log.info("************************************");
		
	}
	
}
