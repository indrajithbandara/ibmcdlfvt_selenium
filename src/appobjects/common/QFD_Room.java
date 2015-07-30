package appobjects.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import config.Element;

public class QFD_Room {

	private WebDriver driver;
		
	public QFD_Room(WebDriver driver) {
			
		this.driver = driver;
			
	}

	public Element getRoomAction() {
		return new Element(driver.findElement(By.xpath("//a[@title='Room Actions']")), "Room Actions button");
	}

	public Element getRoomIndex() {
		return new Element(driver.findElement(By.partialLinkText("Room Index")), "Room Index link");
	}

	public Element getParent() {
		return new Element(driver.findElement(By.xpath("//a[text()='Return to Parent']")), "Return to Parent link");
	}

	public Element getRoomMembers() {
		return new Element(driver.findElement(By.xpath("//a[text()='Room Members']")), "Room Members link");
	}

	public Element getRoomName() {
		return new Element(driver.findElement(By.xpath("//input[@name='h_NewRoomName']")), "Room Name textbox");
	}

	public Element getDeleteRoom() {
		return new Element(driver.findElement(By.xpath("//a[text()='Delete Room']")), "Delete Room button");
	}
	
}
