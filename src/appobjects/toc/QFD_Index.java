package appobjects.toc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import config.Element;

public class QFD_Index {

	private WebDriver driver;
	
	public QFD_Index(WebDriver driver) {
		
		this.driver = driver;
		
	}

	public Element getIndex() {
		return new Element(driver.findElement(By.xpath("//a[contains(@title,'Index')]")), "Index link");
	}

	public Element getMoreActions() {
		return new Element(driver.findElement(By.xpath("//a[@title='More Actions']")), "More Actions button");
	}
	
}