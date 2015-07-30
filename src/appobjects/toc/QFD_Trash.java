package appobjects.toc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import config.Element;

public class QFD_Trash {

	private WebDriver driver;
	
	public QFD_Trash(WebDriver driver) {
		
		this.driver = driver;
		
	}

	public Element getTrash() {
		return new Element(driver.findElement(By.xpath("//a[contains(@title,'Trash')]")), "Trash link");
	}

	public Element getRestore() {
		return new Element(driver.findElement(By.linkText("Restore")), "Restore button");
	}

	public Element getTrashConfirm() {
		return new Element(driver.findElement(By.linkText("Trash")), "Trash link in the confirmation message");
	}

	public Element getRestoreAll() {
		return new Element(driver.findElement(By.linkText("Restore All")), "Restore All button");
	}

	public Element getEmptyTrash() {
		return new Element(driver.findElement(By.linkText("Empty Trash")), "Empty Trash button");
	}

	public Element getRestoreNewName() {
		return new Element(driver.findElement(By.xpath("//input[@id='newName']")), "New Name textbox");
	}

	public Element getCancel() {
		return new Element(driver.findElement(By.linkText("Cancel")), "Cancel link");
	}

	public Element getDeleteItem() {
		return new Element(driver.findElement(By.xpath("//td[text()='Delete']")), "Delete item");
	}

	public Element getDelete() {
		return new Element(driver.findElement(By.linkText("Delete")), "Delete button");
	}
	
}
