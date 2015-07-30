package appobjects.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import config.Element;

public class QFD_Place {
	
	private WebDriver driver;
	
	public QFD_Place(WebDriver driver) {
		
		this.driver = driver;
		
	}
	
	public Element getPlaceAction() {
		return new Element(driver.findElement(By.partialLinkText("Place Actions")), "Place Actions button");
	}

	public Element getPlaceActionNew() {
		return new Element(driver.findElement(By.xpath("//td[text()='New']")),"New item");
	}
	
	public Element getPlaceActionNewRoom() {
		return new Element(driver.findElement(By.xpath("//td[text()='New Room']")),"New Room item");
	}

	public Element getCustomize() {
		return new Element(driver.findElement(By.xpath("//a[text()='Customize']")),"Customize button");
	}

	public Element getPlaceTypeOption() {
		return new Element(driver.findElement(By.xpath("//a[@title='PlaceType Options']")),"PlaceType Options link");
	}

	public Element getNext() {
		return new Element(driver.findElement(By.xpath("//a[text()='Next']")),"Next item");
	}

	public WebElement getCutomizeFrame() {
		return driver.findElement(By.xpath("//iframe[@id='contentViewerIframe' and @title='Modal Dialog Content']"));
	}

	public Element getClose() {
		return new Element(driver.findElement(By.xpath("//input[@value='Close']")),"Close button");
	}

	public Element getMyPlaces() {
		return new Element(driver.findElement(By.xpath("//a[text()='My Places']")),"My Places link");
	}

	public Element getBasics() {
		return new Element(driver.findElement(By.xpath("//a[@title='Basics']")),"Basics link");
	}

	public Element getPlaceName() {
		return new Element(driver.findElement(By.xpath("//input[@title='Place title']")),"Place name textbox");
	}

	public Element getPlaceDesc() {
		return new Element(driver.findElement(By.xpath("//textarea[@title='Description']")),"Place description textbox");
	}

	public Element getDone() {
		return new Element(driver.findElement(By.xpath("//a[text()='Done']")),"Done button");
	}

	public Element getDeletePlace() {
		return new Element(driver.findElement(By.xpath("//a[contains(text(),'Delete this place')]")),"Delete this place button");
	}

	public Element getDeleteConfirm() {
		return new Element(driver.findElement(By.xpath("//input[@title='Confirm delete']")),"Delete Confirm textbox");
	}

	public Element getDelete() {
		return new Element(driver.findElement(By.xpath("//a[text()='Delete Place']")),"Delete Place button");
	}

	public Element getLogoMaker() {
		return new Element(driver.findElement(By.xpath("//a[text()='Logo Maker']")),"Logo Maker button");
	}

	public Element getLogoText() {
		return new Element(driver.findElement(By.xpath("//input[@name='h_LogoText']")),"Logo text textbox");
	}

	public Element getLogoAnimation() {
		return new Element(driver.findElement(By.xpath("//select[@name='h_LogoAnimation']")),"Logo Animation selector");
	}

	public Element getLogoFontSize() {
		return new Element(driver.findElement(By.xpath("//select[@name='h_LogoFontSize']")),"Logo Font Size selector");
	}

	public Element getTasksOption() {
		return new Element(driver.findElement(By.xpath("//option[text()='Tasks']")),"Tasks option");
	}

	public Element getForumsOption() {
		return new Element(driver.findElement(By.xpath("//option[text()='Forums']")),"Forums option");
	}

	public Element getTopHelp() {
		return new Element(driver.findElement(By.xpath("//a[text()='Help' and contains(@id,'top')]")),"Help button");
	}

	public Element getFooterHelp() {
		return new Element(driver.findElement(By.xpath("//a[text()='Help' and contains(@id,'bottom')]")),"Help button");
	}

	public Element getDownload() {
		return new Element(driver.findElement(By.xpath("//a[text()='Download' and @id='connectorURL']")),"Download link");
	}

	public Element getLearnMore() {
		return new Element(driver.findElement(By.xpath("//span[text()='Learn More']")),"Learn More link");
	}

	public Element getAbout() {
		return new Element(driver.findElement(By.xpath("//a/span/span[text()='About']")),"About link");
	}

	public Element getAboutIE() {
		return new Element(driver.findElement(By.xpath("//a/span/span/span[text()='About']")),"About link");
	}
	
	public Element getCloseImage() {
		return new Element(driver.findElement(By.xpath("//a[@title='Close']/img[@alt='Close']")),"X icon");
	}

	public Element get20() {
		return new Element(driver.findElement(By.xpath("//a[@title='Show 20 items at a time']")),"Show 20 items at a time link");
	}
	
	public Element get10() {
		return new Element(driver.findElement(By.xpath("//a[@title='Show 10 items at a time']")),"Show 10 items at a time link");
	}
	
	public Element get50() {
		return new Element(driver.findElement(By.xpath("//a[@title='Show 50 items at a time']")),"Show 50 items at a time link");
	}
	
	public Element get100() {
		return new Element(driver.findElement(By.xpath("//a[@title='Show 100 items at a time']")),"Show 100 items at a time link");
	}

	public Element getPrevious() {
		return new Element(driver.findElement(By.xpath("//a[text()='Previous']")),"Previous item");
	}

	public Element getSortByOwner() {
		return new Element(driver.findElement(By.xpath("//a[text()='Owners']")),"Sort By Owners link");
	}

	public Element getSortByTitle() {
		return new Element(driver.findElement(By.xpath("//a[text()='Title']")),"Sort By Title link");
	}

	public Element getWhatsNew() {
		return new Element(driver.findElement(By.xpath("//td[text()=\"What's New\"]")),"What's New item");
	}

	public Element getEventTaskTab() {
		return new Element(driver.findElement(By.xpath("//a[contains(text(),'Events')]")),"Events & Tasks tab");
	}

	public Element getThisWeek() {
		return new Element(driver.findElement(By.xpath("//a[text()='This Week']")),"This Week link");
	}

	public Element getForm() {
		return new Element(driver.findElement(By.xpath("//a[@title='Forms']")),"Forms link");
	}

	public Element getNewForm() {
		return new Element(driver.findElement(By.xpath("//a[text()='New Form']")),"New Form button");
	}

	public Element getName() {
		return new Element(driver.findElement(By.xpath("//input[@name='h_Name']")),"Form name textbox");
	}

	public Element getAdd() {
		return new Element(driver.findElement(By.xpath("//a[@title='Add fields to the form']")),"Add button");
	}

	public Element getDeleteForm() {
		return new Element(driver.findElement(By.xpath("//a[text()='Delete Form']")),"Delete Form button");
	}
	
}
