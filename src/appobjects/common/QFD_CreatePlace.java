package appobjects.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import config.Element;

public class QFD_CreatePlace {

	private WebDriver driver;
	
	public QFD_CreatePlace(WebDriver driver) {

		this.driver = driver;
		
	}

	public Element getCreatePlaceButton() {
		return new Element(driver.findElement(By.linkText(createPlaceLink)));
	}

	public Element getStandradPlaceItem() {
		return new Element(driver.findElement(By.id(placeID)), "Standard place for teams link");
	}

	public Element getBlogItem() {
		return new Element(driver.findElement(By.xpath("//a[@title='Blog']")), "Blog link");
	}
	
	public Element getWikiItem() {
		return new Element(driver.findElement(By.xpath("//a[@title='Wiki']")), "Wiki link");
	}
	
	public Element getPlaceName() {
		return new Element(driver.findElement(By.name(placeNameString)), "place name textbox");
	}

	public Element getPlaceDescription() {
		return new Element(driver.findElement(By.name(placeDescriptionString)), "place description textbox");
	}

	public Element getCreateButton() {
		return new Element(driver.findElement(By.linkText(createButtonLink)));
	}

	public Element getVerifiedItem() {
		return new Element(driver.findElement(By.xpath(verifiedPath)));
	}
	
	public Element getUserName() {
		return new Element(driver.findElement(By.xpath("//input[@name='h_UserName']")), "user name textbox");
	}
	
	public Element getUserPW() {
		return new Element(driver.findElement(By.xpath("//input[@name='h_SetPassword']")), "user password textbox");
	}
	
	public Element getBackHome() {
		return new Element(driver.findElement(By.xpath("//a[text()='Back to Home']")), "Back to Home button");
	}

	public Element getCreateAnotherPlace() {
		return new Element(driver.findElement(By.xpath("//a[text()='Create Another Place']")), "Create Another Place button");
	}
	
	private String placeID = "ptSelector_0";
	private String createPlaceLink = "Create a Place";
	private String placeNameString = "h_Name";
	private String placeDescriptionString = "h_Description";
	private String createButtonLink = "Create";
	private String verifiedPath = "//div[@id='innerContent']/h4/a[@href='javascript:goToThePlace();']";
	
}
