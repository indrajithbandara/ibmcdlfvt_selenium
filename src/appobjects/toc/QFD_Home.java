package appobjects.toc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import config.Element;

public class QFD_Home {

	private WebDriver driver;
	
	public QFD_Home(WebDriver driver) {
		
		this.driver = driver;
		
	}

	public Element getHome() {
		return new Element(driver.findElement(By.xpath("//a[contains(@title,'Home')]")), "Home link");
	}

	public Element getMoreActions() {
		return new Element(driver.findElement(By.xpath("//a[@title='More Actions']")), "More Actions button");
	}

	public Element getACL() {
		return new Element(driver.findElement(By.xpath("//h3[text()='Who Can Access This']")), "Who Can Access This button");
	}

	public Element getCustomize() {
		return new Element(driver.findElement(By.xpath("//h3[text()='Customize']")), "Customize button");
	}
	
}
