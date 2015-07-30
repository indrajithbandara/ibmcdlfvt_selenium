package appobjects.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import config.Element;

public class QFD_Login {
	
	private WebDriver driver;
	
	public QFD_Login(WebDriver driver) {
		
		this.driver = driver;
		
	}

	public Element getLogIn() {
		return new Element(driver.findElement(By.linkText("Log In")));
	}

	public Element getUserName() {
		return new Element(driver.findElement(By.id("user")), "user textbox");
	}

	public Element getUserPWD() {
		return new Element(driver.findElement(By.id("password")), "password textbox");
	}

	public Element getSubmitBtn() {
		return new Element(driver.findElement(By.id("button")), "Submit button");
	}

	public Element getLogOut() {
		return new Element(driver.findElement(By.xpath(logOutPath)), "Log Out link");
	}

	public Element getCancel() {
		return new Element(driver.findElement(By.linkText("Cancel")), "Cancel link");
	}
	
	private String logOutPath = "//a[@title='Log out']";
	
}
