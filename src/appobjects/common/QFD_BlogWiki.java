package appobjects.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import config.Element;

public class QFD_BlogWiki {

	private WebDriver driver;
	
	public QFD_BlogWiki(WebDriver driver) {

		this.driver = driver;
		
	}

	public Element getNewEntry() {
		return new Element(driver.findElement(By.xpath("//a[text()='New Blog Entry']")), "New Blog Entry button");
	}

	public Element getEntryTitle() {
		return new Element(driver.findElement(By.xpath("//input[@id='h_Name']")), "Blog Title textbox");
	}

	public Element getWikiTitleEdit() {
		return new Element(driver.findElement(By.xpath("//input[@name='h_Name']")), "Blog Title textbox");
	}
	
	public Element getLocation() {
		return new Element(driver.findElement(By.xpath("//input[@name='location']")), "Location textbox");
	}

	public WebElement getBlogFrame() {
		return driver.findElement(By.xpath("//iframe[contains(@title,'Rich text editor')]"));
	}

	public Element getCheckIn() {
		return new Element(driver.findElement(By.xpath("//a[text()='Check In']")), "Check In button");
	}

	public Element getCreationDate() {
		return new Element(driver.findElement(By.xpath("//input[@id='c_BlogDateTemp']")), "Creation date textbox");
	}

	public Element getBlog() {
		return new Element(driver.findElement(By.xpath("//a[text()='Blog']")), "Blog link");
	}

	public Element getWiki() {
		return new Element(driver.findElement(By.xpath("//a[text()='Wiki']")), "Wiki link");
	}
	
	public Element getAddComment() {
		return new Element(driver.findElement(By.xpath("//a[text()='Add Comment']")), "Add Comment button");
	}

	public Element getHomePage() {
		return new Element(driver.findElement(By.xpath("//input[@id='c_CommentHomepage']")), "Homepage textbox");
	}

	public Element getSubmit() {
		return new Element(driver.findElement(By.xpath("//input[@value='Submit']")), "Submit button");
	}

	public Element getNewPageOrFolder() {
		return new Element(driver.findElement(By.xpath("//a[contains(text(),'New Page or Folder')]")), "New Page or Folder link");
	}

	public Element getBlogEntry() {
		return new Element(driver.findElement(By.xpath("//a[contains(text(),'Blog Entry')]")), "Blog Entry item");
	}

	public Element getAddMore() {
		return new Element(driver.findElement(By.xpath("//a[@id='showHideMoreBlogLink']")), "Add More link");
	}

	public WebElement getMoreFrame() {
		return driver.findElement(By.xpath("//iframe[contains(@title,'oBlogTextMore')]"));
	}

	public Element getDelete() {
		return new Element(driver.findElement(By.xpath("//a[text()='Delete']")), "Delete item");
	}

	public Element getYes() {
		return new Element(driver.findElement(By.xpath("//button[text()='Yes']")), "Yes button");
	}

	public Element getMoreActions() {
		return new Element(driver.findElement(By.xpath("//a[@id='moreActions']")), "More Actions button");
	}

	public Element getNewWikiPage() {
		return new Element(driver.findElement(By.xpath("//a[text()='New Wiki Page']")), "New Wiki Page button");
	}

	public Element getHistory() {
		return new Element(driver.findElement(By.xpath("//a[text()='History']")), "History tab");
	}

	public Element getWikiChangeComment() {
		return new Element(driver.findElement(By.xpath("//textarea[@name='c_EditComments']")), "Comment textbox");
	}

	public Element getWikiPage() {
		return new Element(driver.findElement(By.xpath("//a[contains(text(),'Wiki Page')]")), "Wiki Page item");
	}

	public Element getCheckInOption() {
		return new Element(driver.findElement(By.xpath("//a[contains(text(),'Check In with Options')]")), "Check In with Options button");
	}

	public Element getNext() {
		return new Element(driver.findElement(By.xpath("//a[text()='Next']")), "Next button");
	}
	
	public Element getFileField() {
		return new Element(driver.findElement(By.xpath("//input[@id='fileInput0' and @type='file']")), "Wiki page attachpath textbox");
	}
	
}
