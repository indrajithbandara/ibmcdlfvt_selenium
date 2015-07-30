package tasks.common;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.opera.core.systems.scope.protos.ExecProtos.ActionList.Action;

import config.BrowserSetup;
import config.Element;
import config.Task;
import config.Users;
import config.Wait;
import exceptions.ElementException;

import tasks.toc.QFD_LibraryTask;
import util.Assert;
import util.Assert.XPath;

import appobjects.common.QFD_Members;
import appobjects.library.QFD_Folder;
import appobjects.library.QFD_Page;

public class QFD_MembersTask {

	private WebDriver driver;
	private Logger log;
	private Assert asserter;
	private Users user;
	Actions action;
	QFD_Folder qfd_Folder = null;
	QFD_Page qfd_Page = null;
	QFD_LibraryTask qfd_LibraryTask = null;
	QFD_Members qfd_Members = null;
	
	public QFD_MembersTask(WebDriver driver, Logger log, Users user){
		
		this.driver = driver;
		this.log = log;
		this.asserter = new Assert(driver, log);
		this.user = user;
		action = new Actions(driver);
		qfd_Folder = new QFD_Folder(driver);
		qfd_Page = new QFD_Page(driver);
		qfd_LibraryTask = new QFD_LibraryTask(driver, log);
		qfd_Members = new QFD_Members(driver);
		
	}
	
	public void goMembers() throws Exception {
		
		asserter.elementClick(qfd_Members.getMembersTOC(), asserter.new XPath("//iframe[@id='contentViewerIframe']"), true);
		Thread.sleep(Wait.SHORT*3);
		
	}
	
	public void addAuthorMember(String sUserInputName, String sUserTitle,
			String sUserName) throws Exception {

		
		log.info("************************************");
		log.info("Starting 'Add Author Member'...");
		
		try{
				
			driver.switchTo().frame(qfd_Members.getMembersIFrame());
			Thread.sleep(Wait.PAUSE);
			
			qfd_Members.getAddMembers().click();
			Thread.sleep(Wait.SHORT*2);
			
			qfd_Members.getAuthorTab().click();
			Thread.sleep(Wait.SHORT);
	
			qfd_Members.getAddAuthorInput().click();
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Members.getAddAuthorInput().clear();
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Members.getAddAuthorInput().sendKeys(sUserInputName);
			Thread.sleep(Wait.SHORT);
	
			qfd_Members.getOKButton().click();
			Thread.sleep(Wait.SHORT);
	
			String sVerifyUserPath = "//a[@title='" + sUserTitle + "']";
			Element sVerifyUser = null;
			
			if(asserter.elementExistXPath(sVerifyUserPath)){
				
				sVerifyUser = new Element(driver.findElement(By.xpath(sVerifyUserPath)));
				
				if(asserter.verifyEquals(sUserName, sVerifyUser.getText()))
					log.warn("Verified the author's name is correct.");
				Thread.sleep(Wait.SHORT);
				
				if(asserter.verifyEquals("Author", qfd_Members.getVerifyAuthor().getText()))
					log.warn("Verified " + sUserName + " successfully added as an author");
				Thread.sleep(Wait.SHORT);
				
			}
	
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.VERYSHORT);
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Add Author Member'.");
		log.info("************************************");
		
	}

	public void addReaderMember(String sUserInputName, String sUserTitle,
			String sUserName) throws Exception {
		
		log.info("************************************");
		log.info("Starting 'Add Reader Member'...");
		
		try{
				
			driver.switchTo().frame(qfd_Members.getMembersIFrame());
			Thread.sleep(Wait.PAUSE);
			
			qfd_Members.getAddMembers().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Members.getReaderTab().click();
			Thread.sleep(Wait.SHORT);
	
			qfd_Members.getAddReaderInput().click();
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Members.getAddReaderInput().clear();
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Members.getAddReaderInput().sendKeys(sUserInputName);
			Thread.sleep(Wait.SHORT);
	
			qfd_Members.getOKButton().click();
			Thread.sleep(Wait.SHORT);
	
			String sVerifyUserPath = "//a[@title='" + sUserTitle + "']";
			Element sVerifyUser = null;
			
			if(asserter.elementExistXPath(sVerifyUserPath)){
				
				sVerifyUser = new Element(driver.findElement(By.xpath(sVerifyUserPath)));
				Thread.sleep(Wait.SHORT);
				if(asserter.verifyEquals(sUserName, sVerifyUser.getText()))
					log.warn("Verified the reader's name is correct.");
				Thread.sleep(Wait.SHORT);
				
				if(asserter.verifyEquals("Reader", qfd_Members.getVerifyReader().getText()))
					log.warn("Verified " + sUserName + " successfully added as a reader");
				Thread.sleep(Wait.SHORT);
				
			}
	
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.VERYSHORT);
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Add Reader Member'.");
		log.info("************************************");
		
	}

	public void addManagerMember(String sUserInputName, String sUserTitle,
			String sUserName) throws Exception {
		
		log.info("************************************");
		log.info("Starting 'Add Manager Member'...");
		
		try{
				
			driver.switchTo().frame(qfd_Members.getMembersIFrame());
			Thread.sleep(Wait.PAUSE);
			
			qfd_Members.getAddMembers().click();
			Thread.sleep(Wait.SHORT*2);
			
			qfd_Members.getManagerTab().click();
			Thread.sleep(Wait.SHORT);
	
			qfd_Members.getAddManagerInput().click();
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Members.getAddManagerInput().clear();
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Members.getAddManagerInput().sendKeys(sUserInputName);
			Thread.sleep(Wait.SHORT);
	
			qfd_Members.getOKButton().click();
			Thread.sleep(Wait.SHORT);
	
			String sVerifyUserPath = "//a[@title='" + sUserTitle + "']";
			Element sVerifyUser = null;
			
			if(asserter.elementExistXPath(sVerifyUserPath)){
				
				sVerifyUser = new Element(driver.findElement(By.xpath(sVerifyUserPath)));
				
				if(asserter.verifyEquals(sUserName, sVerifyUser.getText()))
					log.warn("Verified the manager's name is correct.");
				Thread.sleep(Wait.SHORT);
				
				if(asserter.verifyEquals("Manager", qfd_Members.getVerifyManager().getText()))
					log.warn("Verified " + sUserName + " successfully added as a manager");
				Thread.sleep(Wait.SHORT);
				
			}
	
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.VERYSHORT);
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Add Manager Member'.");
		log.info("************************************");
		
	}
	
	private void addOwnerMember(String sUserInputName, String sUserTitle,
			String sUserName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Add Owner Member'...");
		
		try{
				
			driver.switchTo().frame(qfd_Members.getMembersIFrame());
			Thread.sleep(Wait.PAUSE);
			
			qfd_Members.getAddMembers().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Members.getOwnerTab().click();
			Thread.sleep(Wait.SHORT);
	
			qfd_Members.getAddOwnerInput().click();
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Members.getAddOwnerInput().clear();
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Members.getAddOwnerInput().sendKeys(sUserInputName);
			Thread.sleep(Wait.SHORT);
	
			qfd_Members.getOKButton().click();
			Thread.sleep(Wait.SHORT);
	
			String sVerifyUserPath = "//a[@title='" + sUserTitle + "']";
			Element sVerifyUser = null;
			
			if(asserter.elementExistXPath(sVerifyUserPath)){
				
				sVerifyUser = new Element(driver.findElement(By.xpath(sVerifyUserPath)));
				
				if(asserter.verifyEquals(sUserName, sVerifyUser.getText()))
					log.warn("Verified the owner's name is correct.");
				Thread.sleep(Wait.SHORT);
				
				if(asserter.verifyEquals("Owner", qfd_Members.getVerifyOwner().getText()))
					log.warn("Verified " + sUserName + " successfully added as an owner");
				Thread.sleep(Wait.SHORT);
				
			}
	
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.VERYSHORT);
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Add Owner Member'.");
		log.info("************************************");
		
	}
	
	public void addEditorMember(String sUserInputName, String sUserTitle,
			String sUserName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Add Editor Member'...");
		
		try{
	
			driver.switchTo().frame(qfd_Members.getMembersIFrame());
			Thread.sleep(Wait.PAUSE*3);
			
			qfd_Members.getAddMembers().click();
			Thread.sleep(Wait.SHORT*3);
			
			qfd_Members.getEditorTab().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Members.getAddEditorInput().click();
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Members.getAddEditorInput().clear();
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Members.getAddEditorInput().sendKeys(sUserInputName);
			Thread.sleep(Wait.SHORT);
	
			qfd_Members.getOKButton().click();
			Thread.sleep(Wait.SHORT);
	
			String sVerifyUserPath = "//a[@title='" + sUserTitle + "']";
			Element sVerifyUser = null;
			
			if(asserter.elementExistXPath(sVerifyUserPath)){
				
				sVerifyUser = new Element(driver.findElement(By.xpath(sVerifyUserPath)));
				
				if(asserter.verifyEquals(sUserName, sVerifyUser.getText()))
					log.warn("Verified the editor's name is correct.");
				Thread.sleep(Wait.SHORT);
				
				if(asserter.verifyEquals("Editor", qfd_Members.getVerifyEditor().getText()))
					log.warn("Verified " + sUserName + " successfully added as an editor");
				Thread.sleep(Wait.SHORT);
				
			}
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.VERYSHORT);
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Add Editor Member'.");
		log.info("************************************");
		
	}
	
	public void addAuthorBlogWiki(String sUserInputName, String sUserTitle,
			String sUserName) throws Exception {

		
		log.info("************************************");
		log.info("Starting 'Add Author Member'...");
		
		try{
			
			Thread.sleep(Wait.PAUSE);
			
			qfd_Members.getAddMembers().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Members.getAuthorTab().click();
			Thread.sleep(Wait.SHORT);
	
			qfd_Members.getAddAuthorInput().click();
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Members.getAddAuthorInput().clear();
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Members.getAddAuthorInput().sendKeys(sUserInputName);
			Thread.sleep(Wait.SHORT);
	
			qfd_Members.getOKButton().click();
			Thread.sleep(Wait.SHORT);
	
			String sVerifyUserPath = "//a[@title='" + sUserTitle + "']";
			Element sVerifyUser = null;
			
			if(asserter.elementExistXPath(sVerifyUserPath)){
				
				sVerifyUser = new Element(driver.findElement(By.xpath(sVerifyUserPath)));
				
				if(asserter.verifyEquals(sUserName, sVerifyUser.getText()))
					log.warn("Verified the author's name is correct.");
				Thread.sleep(Wait.SHORT);
				
				if(asserter.verifyEquals("Author", qfd_Members.getVerifyAuthor().getText()))
					log.warn("Verified " + sUserName + " successfully added as an author");
				Thread.sleep(Wait.SHORT);
				
			}
			
			Thread.sleep(Wait.PAUSE);
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Add Author Member'.");
		log.info("************************************");
		
	}

	public void addReaderBlogWiki(String sUserInputName, String sUserTitle,
			String sUserName) throws Exception {
		
		log.info("************************************");
		log.info("Starting 'Add Reader Member'...");
		
		try{
			
			Thread.sleep(Wait.PAUSE);
			
			qfd_Members.getAddMembers().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Members.getReaderTab().click();
			Thread.sleep(Wait.SHORT);
	
			qfd_Members.getAddReaderInput().click();
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Members.getAddReaderInput().clear();
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Members.getAddReaderInput().sendKeys(sUserInputName);
			Thread.sleep(Wait.SHORT);
	
			qfd_Members.getOKButton().click();
			Thread.sleep(Wait.SHORT);
	
			String sVerifyUserPath = "//a[@title='" + sUserTitle + "']";
			Element sVerifyUser = null;
			
			if(asserter.elementExistXPath(sVerifyUserPath)){
				
				sVerifyUser = new Element(driver.findElement(By.xpath(sVerifyUserPath)));
				
				if(asserter.verifyEquals(sUserName, sVerifyUser.getText()))
					log.warn("Verified the reader's name is correct.");
				Thread.sleep(Wait.SHORT);
				
				if(asserter.verifyEquals("Reader", qfd_Members.getVerifyReader().getText()))
					log.warn("Verified " + sUserName + " successfully added as a reader");
				Thread.sleep(Wait.SHORT);
				
			}
	
			Thread.sleep(Wait.PAUSE);
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Add Reader Member'.");
		log.info("************************************");
		
	}

	public void addManagerBlogWiki(String sUserInputName, String sUserTitle,
			String sUserName) throws Exception {
		
		log.info("************************************");
		log.info("Starting 'Add Manager Member'...");
		
		try{
			
			Thread.sleep(Wait.PAUSE);
			
			qfd_Members.getAddMembers().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Members.getManagerTab().click();
			Thread.sleep(Wait.SHORT);
	
			qfd_Members.getAddManagerInput().click();
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Members.getAddManagerInput().clear();
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Members.getAddManagerInput().sendKeys(sUserInputName);
			Thread.sleep(Wait.SHORT);
	
			qfd_Members.getOKButton().click();
			Thread.sleep(Wait.SHORT);
	
			String sVerifyUserPath = "//a[@title='" + sUserTitle + "']";
			Element sVerifyUser = null;
			
			if(asserter.elementExistXPath(sVerifyUserPath)){
				
				sVerifyUser = new Element(driver.findElement(By.xpath(sVerifyUserPath)));
				
				if(asserter.verifyEquals(sUserName, sVerifyUser.getText()))
					log.warn("Verified the manager's name is correct.");
				Thread.sleep(Wait.SHORT);
				
				if(asserter.verifyEquals("Manager", qfd_Members.getVerifyManager().getText()))
					log.warn("Verified " + sUserName + " successfully added as a manager");
				Thread.sleep(Wait.SHORT);
				
			}
	
			Thread.sleep(Wait.PAUSE);
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Add Manager Member'.");
		log.info("************************************");
		
	}
	
	private void addOwnerBlogWiki(String sUserInputName, String sUserTitle,
			String sUserName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Add Owner Member'...");
		
		try{
		
			Thread.sleep(Wait.PAUSE);
			
			qfd_Members.getAddMembers().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Members.getOwnerTab().click();
			Thread.sleep(Wait.SHORT);
	
			qfd_Members.getAddOwnerInput().click();
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Members.getAddOwnerInput().clear();
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Members.getAddOwnerInput().sendKeys(sUserInputName);
			Thread.sleep(Wait.SHORT);
	
			qfd_Members.getOKButton().click();
			Thread.sleep(Wait.SHORT);
	
			String sVerifyUserPath = "//a[@title='" + sUserTitle + "']";
			Element sVerifyUser = null;
			
			if(asserter.elementExistXPath(sVerifyUserPath)){
				
				sVerifyUser = new Element(driver.findElement(By.xpath(sVerifyUserPath)));
				
				if(asserter.verifyEquals(sUserName, sVerifyUser.getText()))
					log.warn("Verified the owner's name is correct.");
				Thread.sleep(Wait.SHORT);
				
				if(asserter.verifyEquals("Owner", qfd_Members.getVerifyOwner().getText()))
					log.warn("Verified " + sUserName + " successfully added as an owner");
				Thread.sleep(Wait.SHORT);
				
			}
	
			Thread.sleep(Wait.PAUSE);
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Add Owner Member'.");
		log.info("************************************");
		
	}
	
	public void addEditorBlogWiki(String sUserInputName, String sUserTitle,
			String sUserName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Add Editor Member'...");
		
		try{
	
			Thread.sleep(Wait.PAUSE);
			
			qfd_Members.getAddMembers().click();
			Thread.sleep(Wait.SHORT);
			
			//mary
			Thread.sleep(Wait.SHORT);
			Thread.sleep(Wait.SHORT);
			qfd_Members.getEditorTab().click();
			Thread.sleep(Wait.SHORT);
			//mary
			Thread.sleep(Wait.SHORT);
			Thread.sleep(Wait.SHORT);
			Thread.sleep(Wait.SHORT);
			
			qfd_Members.getAddEditorInput().click();
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Members.getAddEditorInput().clear();
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Members.getAddEditorInput().sendKeys(sUserInputName);
			Thread.sleep(Wait.SHORT);
	
			qfd_Members.getOKButton().click();
			Thread.sleep(Wait.SHORT);
	
			String sVerifyUserPath = "//a[@title='" + sUserTitle + "']";
			Element sVerifyUser = null;
			
			if(asserter.elementExistXPath(sVerifyUserPath)){
				
				sVerifyUser = new Element(driver.findElement(By.xpath(sVerifyUserPath)));
				
				if(asserter.verifyEquals(sUserName, sVerifyUser.getText()))
					log.warn("Verified the editor's name is correct.");
				Thread.sleep(Wait.SHORT);
				
				if(asserter.verifyEquals("Editor", qfd_Members.getVerifyEditor().getText()))
					log.warn("Verified " + sUserName + " successfully added as an editor");
				Thread.sleep(Wait.SHORT);
				
			}
			
			Thread.sleep(Wait.PAUSE);
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Add Editor Member'.");
		log.info("************************************");
		
	}
	
	public void addMembers(int readers, int authors, int editors, int managers, int owners) throws Exception {
		
		log.info("************************************");
		log.info("Starting 'Add Members'...");
		
		try{
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			goMembers();
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		if(readers == 1){
			
			addReaderMember(user.ReaderInputName, user.ReaderTitle, user.ReaderName);
			
		}else if(readers == 2){
			
			addReaderMember(user.ReaderInputName, user.ReaderTitle, user.ReaderName);
			addReaderMember(user.Reader1InputName, user.Reader1Title, user.Reader1Name);
			
		}else if(readers == 3){
			
			addReaderMember(user.ReaderInputName, user.ReaderTitle, user.ReaderName);
			addReaderMember(user.Reader1InputName, user.Reader1Title, user.Reader1Name);
			addReaderMember(user.Reader2InputName, user.Reader2Title, user.Reader2Name);
			
		}else if(readers == 4){
			
			addReaderMember(user.ReaderInputName, user.ReaderTitle, user.ReaderName);
			addReaderMember(user.Reader1InputName, user.Reader1Title, user.Reader1Name);
			addReaderMember(user.Reader2InputName, user.Reader2Title, user.Reader2Name);
			addReaderMember(user.Reader3InputName, user.Reader3Title, user.Reader3Name);
			
		}
		
		if(authors == 1){
			
			addAuthorMember(user.AuthorInputName, user.AuthorTitle, user.AuthorName);
			
		}else if(authors == 2){
			
			addAuthorMember(user.AuthorInputName, user.AuthorTitle, user.AuthorName);
			addAuthorMember(user.Author1InputName, user.Author1Title, user.Author1Name);
			
		}else if(authors == 3){
			
			addAuthorMember(user.AuthorInputName, user.AuthorTitle, user.AuthorName);
			addAuthorMember(user.Author1InputName, user.Author1Title, user.Author1Name);
			addAuthorMember(user.Author2InputName, user.Author2Title, user.Author2Name);
			
		}else if(authors == 4){
			
			addAuthorMember(user.AuthorInputName, user.AuthorTitle, user.AuthorName);
			addAuthorMember(user.Author1InputName, user.Author1Title, user.Author1Name);
			addAuthorMember(user.Author2InputName, user.Author2Title, user.Author2Name);
			addAuthorMember(user.Author3InputName, user.Author3Title, user.Author3Name);
			
		}	
		
		if(editors == 1){
			
			addEditorMember(user.EditorInputName, user.EditorTitle, user.EditorName);
			
		}else if(editors == 2){
			
			addEditorMember(user.EditorInputName, user.EditorTitle, user.EditorName);
			addEditorMember(user.Editor1InputName, user.Editor1Title, user.Editor1Name);
			
		}else if(editors == 3){
			
			addEditorMember(user.EditorInputName, user.EditorTitle, user.EditorName);
			addEditorMember(user.Editor1InputName, user.Editor1Title, user.Editor1Name);
			addEditorMember(user.Editor2InputName, user.Editor2Title, user.Editor2Name);
			
		}else if(editors == 4){
			
			addEditorMember(user.EditorInputName, user.EditorTitle, user.EditorName);
			addEditorMember(user.Editor1InputName, user.Editor1Title, user.Editor1Name);
			addEditorMember(user.Editor2InputName, user.Editor2Title, user.Editor2Name);
			addEditorMember(user.Editor3InputName, user.Editor3Title, user.Editor3Name);
			
		}
		
		if(managers == 1){
			
			addManagerMember(user.ManagerInputName, user.ManagerTitle, user.ManagerName);
			
		}else if(managers == 2){
			
			addManagerMember(user.ManagerInputName, user.ManagerTitle, user.ManagerName);
			addManagerMember(user.Manager1InputName, user.Manager1Title, user.Manager1Name);
			
		}else if(managers == 3){
			
			addManagerMember(user.ManagerInputName, user.ManagerTitle, user.ManagerName);
			addManagerMember(user.Manager1InputName, user.Manager1Title, user.Manager1Name);
			addManagerMember(user.Manager2InputName, user.Manager2Title, user.Manager2Name);
			
		}else if(managers == 4){
			
			addManagerMember(user.ManagerInputName, user.ManagerTitle, user.ManagerName);
			addManagerMember(user.Manager1InputName, user.Manager1Title, user.Manager1Name);
			addManagerMember(user.Manager2InputName, user.Manager2Title, user.Manager2Name);
			addManagerMember(user.Manager3InputName, user.Manager3Title, user.Manager3Name);
			
		}
		
		if(owners == 1){
			
			addOwnerMember(user.OwnerInputName, user.OwnerTitle, user.OwnerName);
			
		}else if(owners == 2){
			
			addOwnerMember(user.OwnerInputName, user.OwnerTitle, user.OwnerName);
			addOwnerMember(user.Owner1InputName, user.Owner1Title, user.Owner1Name);
			
		}else if(owners == 3){
			
			addOwnerMember(user.OwnerInputName, user.OwnerTitle, user.OwnerName);
			addOwnerMember(user.Owner1InputName, user.Owner1Title, user.Owner1Name);
			addOwnerMember(user.Owner2InputName, user.Owner2Title, user.Owner2Name);
			
		}else if(owners == 4){
			
			addOwnerMember(user.OwnerInputName, user.OwnerTitle, user.OwnerName);
			addOwnerMember(user.Owner1InputName, user.Owner1Title, user.Owner1Name);
			addOwnerMember(user.Owner2InputName, user.Owner2Title, user.Owner2Name);
			addOwnerMember(user.Owner3InputName, user.Owner3Title, user.Owner3Name);
			
		}
		
		try{
			
			asserter.elementClick(qfd_Members.getCloseButton(), asserter.new XPath("//iframe[@id='contentViewerIframe']"), false);
			Thread.sleep(Wait.SHORT);
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Add Members'...");
		log.info("************************************");
		
	}

	public void addBlogWikiMembers(int readers, int authors, int editors, int managers, int owners) throws Exception {
		
		log.info("************************************");
		log.info("Starting 'Add Members'...");
		
		try{
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE*5);
			
			asserter.elementClick(qfd_Members.getMembersBlogWiki(), asserter.new XPath("//h1[text()='Members']"), true);
			Thread.sleep(Wait.SHORT);
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		if(readers == 1){
			
			addReaderBlogWiki(user.ReaderInputName, user.ReaderTitle, user.ReaderName);
			
		}else if(readers == 2){
			
			addReaderBlogWiki(user.ReaderInputName, user.ReaderTitle, user.ReaderName);
			addReaderBlogWiki(user.Reader1InputName, user.Reader1Title, user.Reader1Name);
			
		}else if(readers == 3){
			
			addReaderBlogWiki(user.ReaderInputName, user.ReaderTitle, user.ReaderName);
			addReaderBlogWiki(user.Reader1InputName, user.Reader1Title, user.Reader1Name);
			addReaderBlogWiki(user.Reader2InputName, user.Reader2Title, user.Reader2Name);
			
		}else if(readers == 4){
			
			addReaderBlogWiki(user.ReaderInputName, user.ReaderTitle, user.ReaderName);
			addReaderBlogWiki(user.Reader1InputName, user.Reader1Title, user.Reader1Name);
			addReaderBlogWiki(user.Reader2InputName, user.Reader2Title, user.Reader2Name);
			addReaderBlogWiki(user.Reader3InputName, user.Reader3Title, user.Reader3Name);
			
		}
		
		if(authors == 1){
			
			addAuthorBlogWiki(user.AuthorInputName, user.AuthorTitle, user.AuthorName);
			
		}else if(authors == 2){
			
			addAuthorBlogWiki(user.AuthorInputName, user.AuthorTitle, user.AuthorName);
			addAuthorBlogWiki(user.Author1InputName, user.Author1Title, user.Author1Name);
			
		}else if(authors == 3){
			
			addAuthorBlogWiki(user.AuthorInputName, user.AuthorTitle, user.AuthorName);
			addAuthorBlogWiki(user.Author1InputName, user.Author1Title, user.Author1Name);
			addAuthorBlogWiki(user.Author2InputName, user.Author2Title, user.Author2Name);
			
		}else if(authors == 4){
			
			addAuthorBlogWiki(user.AuthorInputName, user.AuthorTitle, user.AuthorName);
			addAuthorBlogWiki(user.Author1InputName, user.Author1Title, user.Author1Name);
			addAuthorBlogWiki(user.Author2InputName, user.Author2Title, user.Author2Name);
			addAuthorBlogWiki(user.Author3InputName, user.Author3Title, user.Author3Name);
			
		}	
		
		if(editors == 1){
			
			addEditorBlogWiki(user.EditorInputName, user.EditorTitle, user.EditorName);
			
		}else if(editors == 2){
			
			addEditorBlogWiki(user.EditorInputName, user.EditorTitle, user.EditorName);
			addEditorBlogWiki(user.Editor1InputName, user.Editor1Title, user.Editor1Name);
			
		}else if(editors == 3){
			
			addEditorBlogWiki(user.EditorInputName, user.EditorTitle, user.EditorName);
			addEditorBlogWiki(user.Editor1InputName, user.Editor1Title, user.Editor1Name);
			addEditorBlogWiki(user.Editor2InputName, user.Editor2Title, user.Editor2Name);
			
		}else if(editors == 4){
			
			addEditorBlogWiki(user.EditorInputName, user.EditorTitle, user.EditorName);
			addEditorBlogWiki(user.Editor1InputName, user.Editor1Title, user.Editor1Name);
			addEditorBlogWiki(user.Editor2InputName, user.Editor2Title, user.Editor2Name);
			addEditorBlogWiki(user.Editor3InputName, user.Editor3Title, user.Editor3Name);
			
		}
		
		if(managers == 1){
			
			addManagerBlogWiki(user.ManagerInputName, user.ManagerTitle, user.ManagerName);
			
		}else if(managers == 2){
			
			addManagerBlogWiki(user.ManagerInputName, user.ManagerTitle, user.ManagerName);
			addManagerBlogWiki(user.Manager1InputName, user.Manager1Title, user.Manager1Name);
			
		}else if(managers == 3){
			
			addManagerBlogWiki(user.ManagerInputName, user.ManagerTitle, user.ManagerName);
			addManagerBlogWiki(user.Manager1InputName, user.Manager1Title, user.Manager1Name);
			addManagerBlogWiki(user.Manager2InputName, user.Manager2Title, user.Manager2Name);
			
		}else if(managers == 4){
			
			addManagerBlogWiki(user.ManagerInputName, user.ManagerTitle, user.ManagerName);
			addManagerBlogWiki(user.Manager1InputName, user.Manager1Title, user.Manager1Name);
			addManagerBlogWiki(user.Manager2InputName, user.Manager2Title, user.Manager2Name);
			addManagerBlogWiki(user.Manager3InputName, user.Manager3Title, user.Manager3Name);
			
		}
		
		if(owners == 1){
			
			addOwnerBlogWiki(user.OwnerInputName, user.OwnerTitle, user.OwnerName);
			
		}else if(owners == 2){
			
			addOwnerBlogWiki(user.OwnerInputName, user.OwnerTitle, user.OwnerName);
			addOwnerBlogWiki(user.Owner1InputName, user.Owner1Title, user.Owner1Name);
			
		}else if(owners == 3){
			
			addOwnerBlogWiki(user.OwnerInputName, user.OwnerTitle, user.OwnerName);
			addOwnerBlogWiki(user.Owner1InputName, user.Owner1Title, user.Owner1Name);
			addOwnerBlogWiki(user.Owner2InputName, user.Owner2Title, user.Owner2Name);
			
		}else if(owners == 4){
			
			addOwnerBlogWiki(user.OwnerInputName, user.OwnerTitle, user.OwnerName);
			addOwnerBlogWiki(user.Owner1InputName, user.Owner1Title, user.Owner1Name);
			addOwnerBlogWiki(user.Owner2InputName, user.Owner2Title, user.Owner2Name);
			addOwnerBlogWiki(user.Owner3InputName, user.Owner3Title, user.Owner3Name);
			
		}
		
		try{
			
			Thread.sleep(Wait.SHORT);
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
			
		}
		
		log.info("End of 'Add Members'...");
		log.info("************************************");
		
	}

	public void verifyMembers(String userName) throws Exception {
		
		log.info("************************************");
		log.info("Starting 'Verify Members'...");
		
		try{
			
			goMembers();
			
			driver.switchTo().frame(qfd_Members.getMembersIFrame());
			Thread.sleep(Wait.PAUSE);
			
			if(asserter.verifyExist(asserter.new XPath("//h1[text()='Members']")))
				log.warn("Verified the Members view appears");
			
			if(asserter.verifyExist(asserter.new XPath("//input[@type='checkbox' and contains(@value,'Domino Testuser101') and @disabled]")))
				log.warn("Verified the checkbox next to the owner is grayed out.");
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.VERYSHORT);
			
			asserter.elementClick(qfd_Members.getCloseButton(), asserter.new XPath("//iframe[@id='contentViewerIframe']"), false);
			Thread.sleep(Wait.SHORT);
			
		}catch(Exception e){
		
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
		
		}
		
		log.info("End of 'Verify Members'...");
		log.info("************************************");
			
	}

	public void verifyMembersOptions(String role) throws Exception {
		
		log.info("************************************");
		log.info("Starting 'Verify Members Options'...");
		
		try{
			
			goMembers();
			
			driver.switchTo().frame(qfd_Members.getMembersIFrame());
			Thread.sleep(Wait.PAUSE);
			
			if(role.equalsIgnoreCase("owner")){
				
				if(asserter.verifyExist(asserter.new XPath("//a[contains(text(),'Add Members')]")))
					log.warn("Verified the Add Members button exists.");
				
				if(asserter.verifyExist(asserter.new XPath("//a[contains(text(),'Create Members')]")))
					log.warn("Verified the Create Members button exists.");
				
				if(asserter.verifyExist(asserter.new XPath("//a[contains(text(),'More Actions')]")))
					log.warn("Verified the More Actions button exists.");
			
				qfd_Members.getMoreActions().click();
				Thread.sleep(Wait.PAUSE);
				
				if(asserter.verifyExist(asserter.new XPath("//a[contains(text(),'Delete')]")))
					log.warn("Verified the Delete item exists.");
				
				if(asserter.verifyExist(asserter.new XPath("//a[contains(text(),'Change Role')]")))
					log.warn("Verified the Change Role item exists.");
				
				if(asserter.verifyExist(asserter.new XPath("//a[contains(text(),'Grant Access to Everyone')]")))
					log.warn("Verified the Grant Access to Everyone item exists.");
				
				if(asserter.verifyExist(asserter.new XPath("//a[contains(text(),'Work with Groups')]")))
					log.warn("Verified the Work with Groups item exists.");
				
				if(asserter.verifyExist(asserter.new XPath("//a[contains(text(),'Notify')]")))
					log.warn("Verified the Notify item exists.");
				
				if(asserter.verifyExist(asserter.new XPath("//a[contains(text(),'Print')]")))
					log.warn("Verified the Print item exists.");
				
			}else if(role.equalsIgnoreCase("manager")){
				
				if(asserter.verifyExist(asserter.new XPath("//a[contains(text(),'Add Members')]")))
					log.warn("Verified the Add Members button exists.");
				
				if(asserter.verifyExist(asserter.new XPath("//a[contains(text(),'Create Members')]")))
					log.warn("Verified the Create Members button exists.");
				
				if(asserter.verifyExist(asserter.new XPath("//a[contains(text(),'More Actions')]")))
					log.warn("Verified the More Actions button exists.");
				
				qfd_Members.getMoreActions().click();
				Thread.sleep(Wait.PAUSE);
				
				if(asserter.verifyExist(asserter.new XPath("//a[contains(text(),'Delete')]")))
					log.warn("Verified the Delete item exists.");
				
				if(asserter.verifyExist(asserter.new XPath("//a[contains(text(),'Change Role')]")))
					log.warn("Verified the Change Role item exists.");
				
				if(asserter.verifyExist(asserter.new XPath("//a[contains(text(),'Grant Access to Everyone')]")))
					log.warn("Verified the Grant Access to Everyone item exists.");
				
				if(asserter.verifyExist(asserter.new XPath("//a[contains(text(),'Work with Groups')]")))
					log.warn("Verified the Work with Groups item exists.");
				
				if(asserter.verifyExist(asserter.new XPath("//a[contains(text(),'Notify')]")))
					log.warn("Verified the Notify item exists.");
				
				if(asserter.verifyExist(asserter.new XPath("//a[contains(text(),'Print')]")))
					log.warn("Verified the Print item exists.");
				
			}else if(role.equalsIgnoreCase("editor")){
				
				if(asserter.verifyFalse(driver.findElement(By.xpath("//a[contains(text(),'Add Members')]")).isDisplayed()))
					log.warn("Verified the Add Members button does not exist.");
				
				if(asserter.verifyFalse(driver.findElement(By.xpath("//a[contains(text(),'Create Members')]")).isDisplayed()))
					log.warn("Verified the Create Members button does not exist.");
				
				if(asserter.verifyExist(asserter.new XPath("//a[contains(text(),'More Actions')]")))
					log.warn("Verified the More Actions button exists.");
				
				qfd_Members.getMoreActions().click();
				Thread.sleep(Wait.PAUSE);
				
				if(asserter.verifyNotExist(asserter.new XPath("//a[contains(text(),'Delete')]")))
					log.warn("Verified the Delete item does not exist.");
				
				if(asserter.verifyNotExist(asserter.new XPath("//a[contains(text(),'Change Role')]")))
					log.warn("Verified the Change Role item does not exist.");
				
				if(asserter.verifyNotExist(asserter.new XPath("//a[contains(text(),'Grant Access to Everyone')]")))
					log.warn("Verified the Grant Access to Everyone item does not exist.");
				
				if(asserter.verifyExist(asserter.new XPath("//a[contains(text(),'Work with Groups')]")))
					log.warn("Verified the Work with Groups item exists.");
				
				if(asserter.verifyExist(asserter.new XPath("//a[contains(text(),'Notify')]")))
					log.warn("Verified the Notify item exists.");
				
				if(asserter.verifyExist(asserter.new XPath("//a[contains(text(),'Print')]")))
					log.warn("Verified the Print item exists.");
				
			}else if(role.equalsIgnoreCase("author")){
				
				if(asserter.verifyFalse(driver.findElement(By.xpath("//a[contains(text(),'Add Members')]")).isDisplayed()))
					log.warn("Verified the Add Members button does not exist.");
				
				if(asserter.verifyFalse(driver.findElement(By.xpath("//a[contains(text(),'Create Members')]")).isDisplayed()))
					log.warn("Verified the Create Members button does not exist.");
				
				if(asserter.verifyExist(asserter.new XPath("//a[contains(text(),'More Actions')]")))
					log.warn("Verified the More Actions button exists.");
				
				qfd_Members.getMoreActions().click();
				Thread.sleep(Wait.PAUSE);
				
				if(asserter.verifyNotExist(asserter.new XPath("//a[contains(text(),'Delete')]")))
					log.warn("Verified the Delete item does not exist.");
				
				if(asserter.verifyNotExist(asserter.new XPath("//a[contains(text(),'Change Role')]")))
					log.warn("Verified the Change Role item does not exist.");
				
				if(asserter.verifyNotExist(asserter.new XPath("//a[contains(text(),'Grant Access to Everyone')]")))
					log.warn("Verified the Grant Access to Everyone item does not exist.");
				
				if(asserter.verifyExist(asserter.new XPath("//a[contains(text(),'Work with Groups')]")))
					log.warn("Verified the Work with Groups item exists.");
				
				if(asserter.verifyExist(asserter.new XPath("//a[contains(text(),'Notify')]")))
					log.warn("Verified the Notify item exists.");
				
				if(asserter.verifyExist(asserter.new XPath("//a[contains(text(),'Print')]")))
					log.warn("Verified the Print item exists.");
				
			}else if(role.equalsIgnoreCase("reader")){
				
				if(asserter.verifyFalse(driver.findElement(By.xpath("//a[contains(text(),'Add Members')]")).isDisplayed()))
					log.warn("Verified the Add Members button does not exist.");
				
				if(asserter.verifyFalse(driver.findElement(By.xpath("//a[contains(text(),'Create Members')]")).isDisplayed()))
					log.warn("Verified the Create Members button does not exist.");
				
				if(asserter.verifyExist(asserter.new XPath("//a[contains(text(),'More Actions')]")))
					log.warn("Verified the More Actions button exists.");
				
				qfd_Members.getMoreActions().click();
				Thread.sleep(Wait.PAUSE);
				
				if(asserter.verifyNotExist(asserter.new XPath("//a[contains(text(),'Delete')]")))
					log.warn("Verified the Delete item does not exist.");
				
				if(asserter.verifyNotExist(asserter.new XPath("//a[contains(text(),'Change Role')]")))
					log.warn("Verified the Change Role item does not exist.");
				
				if(asserter.verifyNotExist(asserter.new XPath("//a[contains(text(),'Grant Access to Everyone')]")))
					log.warn("Verified the Grant Access to Everyone item does not exist.");
				
				if(asserter.verifyExist(asserter.new XPath("//a[contains(text(),'Work with Groups')]")))
					log.warn("Verified the Work with Groups item exists.");
				
				if(asserter.verifyExist(asserter.new XPath("//a[contains(text(),'Notify')]")))
					log.warn("Verified the Notify item exists.");
				
				if(asserter.verifyExist(asserter.new XPath("//a[contains(text(),'Print')]")))
					log.warn("Verified the Print item exists.");
				
			}
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.VERYSHORT);
			
			asserter.elementClick(qfd_Members.getCloseButton(), asserter.new XPath("//iframe[@id='contentViewerIframe']"), false);
			Thread.sleep(Wait.SHORT);
			
		}catch(Exception e){
		
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
		
		}
		
		log.info("End of 'Verify Members Options'...");
		log.info("************************************");
		
	}

	public void addAuthorGroup() throws Exception {
		
		log.info("************************************");
		log.info("Starting 'Add Author Group'...");
		
		try{
		
			goMembers();
			
			driver.switchTo().frame(qfd_Members.getMembersIFrame());
			Thread.sleep(Wait.PAUSE*3);
			
			qfd_Members.getAddMembers().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Members.getAuthorTab().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Members.getGroupRadio().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Members.getAddAuthorInput().click();
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Members.getAddAuthorInput().clear();
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Members.getAddAuthorInput().sendKeys(user.GroupInputName);
			Thread.sleep(Wait.SHORT);
	
			qfd_Members.getOKButton().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.elementExistXPath("//a[contains(text(),'" + user.GroupName + "')]"))
				log.warn("Verified the group " + user.GroupName + " has added successfully.");
	
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.VERYSHORT);
			
			asserter.elementClick(qfd_Members.getCloseButton(), asserter.new XPath("//iframe[@id='contentViewerIframe']"), false);
			Thread.sleep(Wait.SHORT);
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
		
		}
		
		log.info("End of 'Add Author Group'...");
		log.info("************************************");
			
	}

	public void addEditorGroup() throws Exception {
		
		log.info("************************************");
		log.info("Starting 'Add Editor Group'...");
		
		try{
		
			goMembers();
			
			driver.switchTo().frame(qfd_Members.getMembersIFrame());
			Thread.sleep(Wait.PAUSE);
			
			qfd_Members.getAddMembers().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Members.getEditorTab().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Members.getGroupRadio().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Members.getAddEditorInput().click();
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Members.getAddEditorInput().clear();
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Members.getAddEditorInput().sendKeys(user.GroupInputName);
			Thread.sleep(Wait.SHORT);
	
			qfd_Members.getOKButton().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.elementExistXPath("//a[contains(text(),'" + user.GroupName + "')]"))
				log.warn("Verified the group " + user.GroupName + " has added successfully.");
	
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.VERYSHORT);
			
			asserter.elementClick(qfd_Members.getCloseButton(), asserter.new XPath("//iframe[@id='contentViewerIframe']"), false);
			Thread.sleep(Wait.SHORT);
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
		
		}
		
		log.info("End of 'Add Editor Group'...");
		log.info("************************************");
			
	}
	
	public void createReader(String userName, String userPW) throws Exception {

		log.info("************************************");
		log.info("Starting 'Create Reader Member'...");
		
		try{
			
			goMembers();
			
			driver.switchTo().frame(qfd_Members.getMembersIFrame());
			Thread.sleep(Wait.PAUSE);
			
			qfd_Members.getCreateMembers().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Members.getReaderCreateTab().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Members.getReaderCreateName().click();
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Members.getReaderCreateName().sendKeys(userName);
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Members.getReaderCreatePW().click();
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Members.getReaderCreatePW().sendKeys(userPW);
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Members.getReaderCreatePWRe().click();
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Members.getReaderCreatePWRe().sendKeys(userPW);
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Members.getCreateOK().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.elementExistXPath("//a[contains(text(),'" + userName + "')]"))
				log.warn("Verified the reader " + userName + " has created successfully.");
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.VERYSHORT);
			
			asserter.elementClick(qfd_Members.getCloseButton(), asserter.new XPath("//iframe[@id='contentViewerIframe']"), false);
			Thread.sleep(Wait.SHORT);
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
		
		}
		
		log.info("End of 'Create Reader Member'...");
		log.info("************************************");
		
	}
	
	public void createReaderSpecial(String userName, String userPW) throws Exception {

		log.info("************************************");
		log.info("Starting 'Create Reader Member'...");
		
		try{
			
			goMembers();
			
			driver.switchTo().frame(qfd_Members.getMembersIFrame());
			Thread.sleep(Wait.PAUSE);
			
			qfd_Members.getCreateMembers().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Members.getReaderCreateTab().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Members.getReaderCreateName().click();
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Members.getReaderCreateName().sendKeys(userName);
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Members.getReaderCreatePW().click();
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Members.getReaderCreatePW().sendKeys(userPW);
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Members.getReaderCreatePWRe().click();
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Members.getReaderCreatePWRe().sendKeys(userPW);
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Members.getCreateOK().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.elementExistXPath("//a[contains(text(),\"" + userName + "\")]"))
				log.warn("Verified the reader " + userName + " has created successfully.");
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.VERYSHORT);
			
			asserter.elementClick(qfd_Members.getCloseButton(), asserter.new XPath("//iframe[@id='contentViewerIframe']"), false);
			Thread.sleep(Wait.SHORT);
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
		
		}
		
		log.info("End of 'Create Reader Member'...");
		log.info("************************************");
		
	}
	
	public void createAuthor(String userName, String userPW) throws Exception {

		log.info("************************************");
		log.info("Starting 'Create Author Member'...");
		
		try{
			
			goMembers();
			
			driver.switchTo().frame(qfd_Members.getMembersIFrame());
			Thread.sleep(Wait.PAUSE);
			
			qfd_Members.getCreateMembers().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Members.getAuthorCreateTab().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Members.getAuthorCreateName().click();
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Members.getAuthorCreateName().sendKeys(userName);
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Members.getAuthorCreatePW().click();
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Members.getAuthorCreatePW().sendKeys(userPW);
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Members.getAuthorCreatePWRe().click();
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Members.getAuthorCreatePWRe().sendKeys(userPW);
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Members.getCreateOK().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.elementExistXPath("//a[contains(text(),'" + userName + "')]"))
				log.warn("Verified the author " + userName + " has created successfully.");
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.VERYSHORT);
			
			asserter.elementClick(qfd_Members.getCloseButton(), asserter.new XPath("//iframe[@id='contentViewerIframe']"), false);
			Thread.sleep(Wait.SHORT);
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
		
		}
		
		log.info("End of 'Create Author Member'...");
		log.info("************************************");
		
	}
	
	public void createEditor(String userName, String userPW) throws Exception {

		log.info("************************************");
		log.info("Starting 'Create Editor Member'...");
		
		try{
			
			goMembers();
			
			driver.switchTo().frame(qfd_Members.getMembersIFrame());
			Thread.sleep(Wait.PAUSE);
			
			qfd_Members.getCreateMembers().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Members.getEditorCreateTab().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Members.getEditorCreateName().click();
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Members.getEditorCreateName().sendKeys(userName);
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Members.getEditorCreatePW().click();
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Members.getEditorCreatePW().sendKeys(userPW);
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Members.getEditorCreatePWRe().click();
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Members.getEditorCreatePWRe().sendKeys(userPW);
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Members.getCreateOK().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.elementExistXPath("//a[contains(text(),'" + userName + "')]"))
				log.warn("Verified the editor " + userName + " has created successfully.");
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.VERYSHORT);
			
			asserter.elementClick(qfd_Members.getCloseButton(), asserter.new XPath("//iframe[@id='contentViewerIframe']"), false);
			Thread.sleep(Wait.SHORT);
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
		
		}
		
		log.info("End of 'Create Editor Member'...");
		log.info("************************************");
		
	}

	public void createManager(String userName, String userPW) throws Exception {

		log.info("************************************");
		log.info("Starting 'Create Manager Member'...");
		
		try{
			
			goMembers();
			
			driver.switchTo().frame(qfd_Members.getMembersIFrame());
			Thread.sleep(Wait.PAUSE);
			
			qfd_Members.getCreateMembers().click();
			Thread.sleep(Wait.SHORT*2);
			
			qfd_Members.getManagerCreateTab().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Members.getManagerCreateName().click();
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Members.getManagerCreateName().sendKeys(userName);
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Members.getManagerCreatePW().click();
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Members.getManagerCreatePW().sendKeys(userPW);
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Members.getManagerCreatePWRe().click();
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Members.getManagerCreatePWRe().sendKeys(userPW);
			Thread.sleep(Wait.VERYSHORT);
			
			qfd_Members.getCreateOK().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.elementExistXPath("//a[contains(text(),'" + userName + "')]"))
				log.warn("Verified the manager " + userName + " has created successfully.");
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.VERYSHORT);
			
			asserter.elementClick(qfd_Members.getCloseButton(), asserter.new XPath("//iframe[@id='contentViewerIframe']"), false);
			Thread.sleep(Wait.SHORT);
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
		
		}
		
		log.info("End of 'Create Manager Member'...");
		log.info("************************************");
		
	}
	
	public void changeRole(String userName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Change Role'...");
		
		try{
		
			goMembers();
			
			driver.switchTo().frame(qfd_Members.getMembersIFrame());
			Thread.sleep(Wait.PAUSE);
			
			asserter.checkboxClick(asserter.new XPath("//input[@type='checkbox' and @title='" + userName + "']"), true);
			
			qfd_Members.getMoreActions().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Members.getChangeRole().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//span[@id='memberChangeDialog_title' and text()='Change']")))
				log.warn("Verified the Change Role dialog pops up.");
			
			if(asserter.verifyExist(asserter.new XPath("//li[@id='OwnerChangeTab']")))
				log.warn("Verified the Owner tab is available.");
			
			qfd_Members.getReaderChangeTab().click();
			Thread.sleep(Wait.SHORT);
			
			if((asserter.verifyExist(asserter.new XPath("//h6[contains(text(),'Readers can read pages only')]"))) && (asserter.verifyExist(asserter.new XPath("//p[contains(text(),'" + userName + "')]"))))
				log.warn("Verified the in-line message displays.");
			
			qfd_Members.getChangeOK().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//td[text()='Reader']")))
				log.warn("Verified that " + userName + " is reassigned to Reader role.");
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.PAUSE);
			
			asserter.elementClick(qfd_Members.getCloseButton(), asserter.new XPath("//iframe[@id='contentViewerIframe']"), false);
			Thread.sleep(Wait.SHORT);
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
		
		}
		
		log.info("End of 'Change Role'...");
		log.info("************************************");
		
	}

	public void deleteMember(String userName) throws Exception {

		log.info("************************************");
		log.info("Starting 'Delete Member'...");
		
		try{
		
			goMembers();
			
			driver.switchTo().frame(qfd_Members.getMembersIFrame());
			Thread.sleep(Wait.PAUSE);
			
			asserter.checkboxClick(asserter.new XPath("//input[@type='checkbox' and @title='" + userName + "']"), true);
		
			qfd_Members.getMoreActions().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Members.getDelete().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//span[@id='memberRemoveDialog_title' and text()='Delete Members']")))
				log.warn("Verified a confirmation dialog pops up.");
			
			qfd_Members.getDeleteOK().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyNotExist(asserter.new XPath("//a[text()='" + userName + "']")))
				log.warn("Verified Member view is refreshed and " + userName + " is removed from the list.");
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.VERYSHORT);
			
			asserter.elementClick(qfd_Members.getCloseButton(), asserter.new XPath("//iframe[@id='contentViewerIframe']"), false);
			Thread.sleep(Wait.SHORT);
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
		
		}
		
		log.info("End of 'Delete Member'...");
		log.info("************************************");
		
	}

	public void accessEveryone() throws Exception {
		
		log.info("************************************");
		log.info("Starting 'Grant Access To Everyone'...");
		
		try{
		
			goMembers();
			
			driver.switchTo().frame(qfd_Members.getMembersIFrame());
			Thread.sleep(Wait.PAUSE);
			
			qfd_Members.getMoreActions().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Members.getAccessEveryone().click();
			Thread.sleep(Wait.SHORT);

			if(asserter.verifyExist(asserter.new XPath("//span[@id='memberAnonDefDialog_title' and text()='Grant Access to Everyone']")))
				log.warn("Verified the Grant Access to Everyone dialog pops up.");
			
			asserter.checkboxClick(asserter.new XPath("//input[@type='checkbox' and @id='h_GrantAnonymous']"), true);
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//input[@type='checkbox' and @id='h_GrantDefault']")).isSelected()))
				log.warn("Verified 'Allow non-member to access this place after logging in' is also checked.");
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//input[@type='radio' and @value='Author']")).isSelected()))
				log.warn("Verified 'Author access' is selected by default.");
			
			qfd_Members.getAccessOK().click();
			Thread.sleep(Wait.SHORT);
			
			if((asserter.verifyExist(asserter.new XPath("//h3[contains(text(),'This place is visible to non-members')]"))) && (asserter.verifyExist(asserter.new XPath("//p[contains(text(),'To change this setting, click More Actions')]"))))
				log.warn("Verified the in-line message displays on Members dialog.");
			
			driver.switchTo().defaultContent();
			Thread.sleep(Wait.VERYSHORT);
			
			asserter.elementClick(qfd_Members.getCloseButton(), asserter.new XPath("//iframe[@id='contentViewerIframe']"), false);
			Thread.sleep(Wait.SHORT);
			
			qfd_LibraryTask.goLibrary();
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
		
		}
		
		log.info("End of 'Grant Access To Everyone'...");
		log.info("************************************");
		
	}

	public void verifyPicker(String ownerName, String authorName, String editorName, String specialName, String groupUserName, String filterSearch, String managerName) throws Exception {
	
		log.info("************************************");
		log.info("Starting 'Verify Member Picker'...");
		
		try{
		
			qfd_LibraryTask.goLibrary();
			
			qfd_Page.getMoreActions().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Folder.getThisFolderItem().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Folder.getSendLinkItem().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Members.getMemberPicker().click();
			Thread.sleep(Wait.MID);
			
			/*
			 * Added 3/22/2013 Li Er Nan
			 */
			driver.navigate().refresh();
			Thread.sleep(Wait.SHORT);
			
			qfd_Page.getMoreActions().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Folder.getThisFolderItem().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Folder.getSendLinkItem().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Members.getMemberPicker().click();
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='Members']")))
				log.warn("Verified the Member Picker dialog pops up.");
			
			if(asserter.verifyExist(asserter.new XPath("//span[text()='11 Members']")))
				log.warn("Verified the member count is 11.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='10' and @class='lotusBtnDisabled']")))
				log.warn("Verified the Member list is shown as 10 items per page by default.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Previous' and @class='lotusBtnDisabled']")))
				log.warn("Verified the Previous is grayed out on the first page.");
			
			qfd_Members.getUserName().click();
			Thread.sleep(Wait.SHORT);
			
			if(BrowserSetup.BrowserType.equalsIgnoreCase("firefox")){
				
				action.moveToElement(driver.findElement(By.xpath("//tr[@title='" + ownerName + "']/td[2]/span/a[@class='lotusPerson vcard fn hasHover']"))).perform();
				Thread.sleep(Wait.PAUSE);
				
			}else if(BrowserSetup.BrowserType.equalsIgnoreCase("internetexplorer")){
				
				action.moveToElement(driver.findElement(By.xpath("//li[@class='lotusFirst' and contains(text(),'Show')]"))).perform();
				Thread.sleep(Wait.SHORT);
				
				action.moveToElement(driver.findElement(By.xpath("//tr[@title='" + ownerName + "']/td[2]/span/span/a[@class='lotusPerson vcard fn hasHover']"))).perform();
				Thread.sleep(Wait.PAUSE);
				
			}
			
			qfd_Page.getCard().click();
			Thread.sleep(Wait.SHORT);
			
			//Mary added
			Thread.sleep(Wait.SHORT);
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//h2[text()='" + ownerName + "']")))
				log.warn("Verified Quickr Person Card pops up when hover over an user name.");
			
			driver.findElement(By.xpath("//span[text()='Members']")).click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Folder.getNext().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyTrue(driver.findElements(By.xpath("//input[@type='checkbox' and @class='q-selectMember']")).size() == 1))
				log.warn("Verified there is one member listed on the second page.");
				
			qfd_Members.getPickerJump().click();
			Thread.sleep(Wait.PAUSE);
			
			qfd_Members.getPickerJump().clear();
			Thread.sleep(Wait.PAUSE);
			
			qfd_Members.getPickerJump().sendKeys("1");
			Thread.sleep(Wait.PAUSE);
			
			action.sendKeys(Keys.ENTER).perform();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyExist(asserter.new XPath("//li/span[text()='1']")))
				log.warn("Verified the first page is displayed.");
			
			driver.findElement(By.xpath("//span[contains(text(),'Jump to page')]")).click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Members.get20().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyTrue(driver.findElements(By.xpath("//input[@type='checkbox' and @class='q-selectMember']")).size() == 11))
				log.warn("Verified all 11 members are displayed in one page.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Next' and @class='lotusBtnDisabled']")))
				log.warn("Verified the Next link is grayed out.");
			
			if(asserter.verifyExist(asserter.new XPath("//a[text()='Previous' and @class='lotusBtnDisabled']")))
				log.warn("Verified the Previous link is grayed out.");
			
			qfd_Members.getUserName().click();
			Thread.sleep(Wait.SHORT);
			
			WebElement[] T = new WebElement[20];
			if(asserter.verifyTrue(driver.findElements(By.xpath("//input[@type='checkbox' and @class='q-selectMember']")).toArray(T)[0].getAttribute("title").contains("Author")))
				log.warn("Verified user names sorted correctly.");
			
			asserter.checkboxClick(asserter.new XPath("//input[@type='checkbox' and @class='q-selectMember' and contains(@title,'" + authorName + "')]"), true);
			asserter.checkboxClick(asserter.new XPath("//input[@type='checkbox' and @class='q-selectMember' and contains(@title,'" + editorName + "')]"), true);
			asserter.checkboxClick(asserter.new XPath("//input[@type='checkbox' and @class='q-selectMember' and contains(@value,\"" + specialName + "\")]"), true);
			
			qfd_Members.getPickerTwisty().click();
			Thread.sleep(Wait.SHORT);
			
			asserter.checkboxClick(asserter.new XPath("//input[@type='checkbox' and @class='q-selectMember' and contains(@title,'" + groupUserName + "')]"), true);
			
			qfd_Members.getPickerTwistyCollapse().click();
			Thread.sleep(Wait.SHORT);
			
			asserter.elementClick(qfd_Members.getOK(), asserter.new XPath("//span[text()='Members']"), false);
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//input[@id='linkTo_displayName']")).getAttribute("value").contains(authorName)))
				log.warn("Verified user " + authorName + " is added.");
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//input[@id='linkTo_displayName']")).getAttribute("value").contains(editorName)))
				log.warn("Verified user " + editorName + " is added.");
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//input[@id='linkTo_displayName']")).getAttribute("value").contains(specialName)))
				log.warn("Verified user " + specialName + " is added.");
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//input[@id='linkTo_displayName']")).getAttribute("value").contains(groupUserName)))
				log.warn("Verified user " + groupUserName + " is added.");
			
			qfd_Members.getMemberPicker().click();
			Thread.sleep(Wait.MID);
			
			driver.findElement(By.xpath("//span[contains(text(),'Jump to page')]")).click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Members.get20().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Members.getPickerTwisty().click();
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//input[@type='checkbox' and @class='q-selectMember' and contains(@title,'" + authorName + "')]")).isSelected()))
				log.warn("Verified user " + authorName + " is pre-selected.");
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//input[@type='checkbox' and @class='q-selectMember' and contains(@title,'" + editorName + "')]")).isSelected()))
				log.warn("Verified user " + editorName + " is pre-selected.");
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//input[@type='checkbox' and @class='q-selectMember' and contains(@value,\"" + specialName + "\")]")).isSelected()))
				log.warn("Verified user " + specialName + " is pre-selected.");
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//input[@type='checkbox' and @class='q-selectMember' and contains(@title,'" + groupUserName + "')]")).isSelected()))
				log.warn("Verified user " + groupUserName + " is pre-selected.");
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//input[@id='memberFilter']")).getAttribute("value").equals("Type to filter this list")))
				log.warn("Verified the help text for the filter text box is correct.");
			
			qfd_Members.getPickerTwistyCollapse().click();
			Thread.sleep(Wait.SHORT);
			
			qfd_Members.getMemberFilter().click();
			Thread.sleep(Wait.PAUSE);
			
			qfd_Members.getMemberFilter().sendKeys(filterSearch);
			Thread.sleep(Wait.MID);
			
			if(asserter.verifyTrue(driver.findElements(By.xpath("//input[@type='checkbox' and @class='q-selectMember']")).size() == 5))
				log.warn("Verified the search result is correct.");
			
			asserter.checkboxClick(asserter.new XPath("//input[@type='checkbox' and @class='q-selectMember' and contains(@title,'" + managerName + "')]"), true);
			
			asserter.elementClick(qfd_Members.getOK(), asserter.new XPath("//span[text()='Members']"), false);
			Thread.sleep(Wait.SHORT);
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//input[@id='linkTo_displayName']")).getAttribute("value").contains(authorName)))
				log.warn("Verified user " + authorName + " is added.");
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//input[@id='linkTo_displayName']")).getAttribute("value").contains(editorName)))
				log.warn("Verified user " + editorName + " is added.");
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//input[@id='linkTo_displayName']")).getAttribute("value").contains(specialName)))
				log.warn("Verified user " + specialName + " is added.");
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//input[@id='linkTo_displayName']")).getAttribute("value").contains(groupUserName)))
				log.warn("Verified user " + groupUserName + " is added.");
			
			if(asserter.verifyTrue(driver.findElement(By.xpath("//input[@id='linkTo_displayName']")).getAttribute("value").contains(managerName)))
				log.warn("Verified user " + managerName + " is added.");
			
		}catch(Exception e){
			
			e.printStackTrace();
			new ElementException(driver, log, e);
			throw(e);
		
		}
		
		log.info("End of 'Verify Member Picker'...");
		log.info("************************************");
		
	}
	
}
