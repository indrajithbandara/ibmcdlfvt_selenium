package appobjects.library;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import config.Element;

public class QFD_Folder {
	
	private WebDriver driver;
	
	public QFD_Folder(WebDriver driver) {
		
		this.driver = driver;
		
	}

	public Element getTabbedCollapse() {
		return new Element(driver.findElement(By.xpath("//a[contains(@title,'click to expand section')]")), "Collapse icon");
		
	}

	public Element getPrevious() {
		return new Element(driver.findElement(By.xpath("//a[text()='Previous']")), "Previous link");
	}

	public Element getNext() {
		return new Element(driver.findElement(By.xpath("//a[text()='Next']")), "Next link");
	}

	public Element getThisFolderItem() {
		return new Element(driver.findElement(By.xpath("//td[contains(text(),'This Folder')]")), "This Folder item");
	}

	public Element getFolderTitle() {
		return new Element(driver.findElement(By.xpath("//input[@title='Folder title']")), "Folder title textbox");
	}

	public Element getFolderDesc() {
		return new Element(driver.findElement(By.xpath("//textarea[@title='Folder description']")), "Folder description textbox");
	}

	public Element getNextButton() {
		return new Element(driver.findElement(By.xpath("//a[text()='Next']")), "Next button");
	}

	public Element getDownloadOption() {
		return new Element(driver.findElement(By.xpath("//option[text()='Download']")), "Download option");
	}

	public Element getUpArrow() {
		return new Element(driver.findElement(By.xpath("//img[@name='reorderUp']")), "Up arrow");
	}

	public WebElement getPropertiesFrame() {
		return driver.findElement(By.xpath("//iframe[@id='contentViewerIframe']"));
	}

	public Element getFolderAcions() {
		return new Element(driver.findElement(By.xpath("//a[contains(text(),'Folder Actions')]")), "Folder Actions button");
	}

	public Element getMoveFolderTrash() {
		return new Element(driver.findElement(By.xpath("//td[contains(text(),'Move Folder To Trash')]")), "Move Folder To Trash item");
	}

	public Element getChangeLocation() {
		return new Element(driver.findElement(By.xpath("//a[text()='Change location']")), "Change location link");
	}

	public Element getUp() {
		return new Element(driver.findElement(By.xpath("//img[@alt='Up']")), "Up arrow");
	}

	public Element getLocation() {
		return new Element(driver.findElement(By.xpath("//a[text()='location']")), "location link");
	}

	public Element getDownArrow() {
		return new Element(driver.findElement(By.xpath("//img[@name='reorderDown']")), "Down arrow");
	}

	public Element getSendLinkItem() {
		return new Element(driver.findElement(By.xpath("//td[contains(text(),'Send Link')]")), "Send Link To Folder item");
	}

	public Element getAccess() {
		return new Element(driver.findElement(By.xpath("//h3[text()='Who Can Access This']")), "Access link");
	}
	
}
