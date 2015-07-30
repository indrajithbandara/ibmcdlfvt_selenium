package appobjects.toc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import config.Element;

public class QFD_Calendar {

	private WebDriver driver;
	
	public QFD_Calendar(WebDriver driver) {
		
		this.driver = driver;
		
	}

	public Element getCalendar() {
		return new Element(driver.findElement(By.partialLinkText("Calendar")), "Calendar link");
	}

	public Element getNewEvent() {
		return new Element(driver.findElement(By.linkText("New Event")), "New Event button");
	}

	public Element getDate() {
		return new Element(driver.findElement(By.xpath("//input[@title='Date']")), "Event date textbox");
	}

	public Element getView() {
		return new Element(driver.findElement(By.xpath("//td[text()='View']")), "View item");
	}

	public Element getEdit() {
		return new Element(driver.findElement(By.xpath("//a[text()='Edit']")), "Edit button");
	}

	public Element getSummaryView() {
		return new Element(driver.findElement(By.xpath("//a[@id='Summary']")), "Summary view button");
	}

	public Element getEditItem() {
		return new Element(driver.findElement(By.xpath("//td[text()='Edit']")), "Edit item");
	}

	public Element getRepeat() {
		return new Element(driver.findElement(By.xpath("//select[@title='Repeat frequency']")), "Repeat frequency selector");
	}

	public Element getRepeatDays() {
		return new Element(driver.findElement(By.xpath("//input[@title='Repeat events']")), "Repeat days textbox");
	}

	public Element getOneDayView() {
		return new Element(driver.findElement(By.xpath("//a[@title='One Day View']")), "One Day View button");
	}
	
	public Element getTwoDaysView() {
		return new Element(driver.findElement(By.xpath("//a[@title='Two Day View']")), "Two Day View button");
	}
	
	public Element getOneWeekView() {
		return new Element(driver.findElement(By.xpath("//a[@title='One Week View']")), "One Week View button");
	}
	
	public Element getTwoWeeksView() {
		return new Element(driver.findElement(By.xpath("//a[@title='Two Week View']")), "Two Week View button");
	}
	
	public Element getOneMonthView() {
		return new Element(driver.findElement(By.xpath("//a[@title='One Month View']")), "One Month View button");
	}
	
	public Element getOneYearView() {
		return new Element(driver.findElement(By.xpath("//a[@title='One Year View']")), "One Year View button");
	}

	public Element getDelete() {
		return new Element(driver.findElement(By.xpath("//td[text()='Delete']")), "Delete item");
	}
	
}
