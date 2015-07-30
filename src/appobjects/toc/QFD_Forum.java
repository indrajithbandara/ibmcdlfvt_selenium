package appobjects.toc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import config.Element;

public class QFD_Forum {

	private WebDriver driver;
	
	public QFD_Forum(WebDriver driver) {
		
		this.driver = driver;
		
	}

	public Element getForum() {
		return new Element(driver.findElement(By.partialLinkText("Forums")), "Forums link");
	}

	public Element getNewForum() {
		return new Element(driver.findElement(By.linkText("New Forum")), "New Forum button");
	}

	public Element getNewTopic() {
		return new Element(driver.findElement(By.linkText("New Topic")), "New Topic button");
	}

	public Element getReply() {
		return new Element(driver.findElement(By.linkText("Reply")), "Reply link");
	}

	public Element getPostReply() {
		return new Element(driver.findElement(By.xpath("//input[@type='button' and @value='Post Reply']")), "Post Reply button");
	}

	public Element getBreadcrumbForum() {
		return new Element(driver.findElement(By.linkText("Forums")), "Forums link on breadcrumb");
	}

	public Element getEdit() {
		return new Element(driver.findElement(By.linkText("Edit")), "Edit link");
	}

	public Element getDelete() {
		return new Element(driver.findElement(By.linkText("Delete")), "Delete link");
	}

	public Element getDeleteForum() {
		return new Element(driver.findElement(By.xpath("//td[text()='Delete Forum']")), "Delete Forum item");
	}
	
}
