package appobjects.toc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import config.Element;

public class QFD_Task {

	private WebDriver driver;
	
	public QFD_Task(WebDriver driver) {
		
		this.driver = driver;
		
	}

	public Element getTask() {
		return new Element(driver.findElement(By.partialLinkText("Tasks")), "Tasks link");
	}

	public Element getNewTask() {
		return new Element(driver.findElement(By.linkText("New Task")), "New Task button");
	}

	public Element getStartDate() {
		return new Element(driver.findElement(By.xpath("//input[@title='Start Date']")), "Start Date textbox");
	}
	
	public Element getDueDate() {
		return new Element(driver.findElement(By.xpath("//input[@title='Due Date']")), "Due Date textbox");
	}

	public Element getPriority() {
		return new Element(driver.findElement(By.xpath("//select[@title='Priority']")), "Priority selector");
	}

	public Element getAssign() {
		return new Element(driver.findElement(By.xpath("//a[@title='Click here to assign this task to a user']")), "Assign link");
	}

	public Element getListView() {
		return new Element(driver.findElement(By.xpath("//a[@title='View task as a list']")), "list view button");
	}

	public Element getViews() {
		return new Element(driver.findElement(By.xpath("//a[contains(text(),'Views')]")), "Views button");
	}

	public Element getTimelineView() {
		return new Element(driver.findElement(By.xpath("//a[@title='View task as a timeline']")), "timeline view button");
	}

	public Element getType() {
		return new Element(driver.findElement(By.xpath("//a[text()='Type']")), "sort by Type link");
	}

	public Element getStatus() {
		return new Element(driver.findElement(By.xpath("//a[text()='Status']")), "sort by Status link");
	}

	public Element getPrioritySort() {
		return new Element(driver.findElement(By.xpath("//a[text()='Priority']")), "sort by Priority link");
	}

	public Element getNameSort() {
		return new Element(driver.findElement(By.xpath("//a[text()='Name']")), "sort by Name link");
	}

	public Element getAssignSort() {
		return new Element(driver.findElement(By.xpath("//a[text()='Assigned To']")), "sort by Assigned To link");
	}
	
}
