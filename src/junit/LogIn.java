package junit;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;


import junit.framework.*;
public class LogIn extends TestCase { 
	private static Logger log = Logger.getLogger(LogIn.class);
	
	public void test() throws InterruptedException{
//    	This line must be add.
    	DOMConfigurator.configure("log4j.xml");
    	
        // Create a new instance of the Firefox driver        
//    	WebDriver driver = new FirefoxDriver();
    	WebDriver driver = new FirefoxDriver(new ProfilesIni().getProfile("default"));

        // And now use this to visit quickr
        log.info("Load http://lwptsvm114.cn.ibm.com/LotusQuickr");
        driver.get("http://lwptsvm114.cn.ibm.com/LotusQuickr");

        // Log in quickr;
        WebElement logIn = driver.findElement(By.linkText("Log In"));
        log.info("Click 'Log In' link");
        logIn.click();
        Thread.sleep(2000);
        WebElement username = driver.findElement(By.id("user"));
        log.info("Type 'susanadams5' for 'User Name'");
        username.sendKeys("susanadams5");
        
        System.out.println("before:"+username.getText()); 
        Actions builder = new Actions(driver);
        Action doubleClick = builder.doubleClick(username).build();
        doubleClick.perform();       
        
        if(!username.getText().isEmpty()) 
        {
            System.out.println(username.getText()); 
            Actions builder2 = new Actions(driver);
            Action doubleClick2 = builder2.doubleClick(username).build();
            doubleClick2.perform();                
        }
        
        WebElement password = driver.findElement(By.id("password"));
        log.info("Type 'Adams5' for 'Password'");
        password.sendKeys("Adams5");
        WebElement submitButton = driver.findElement(By.id("button"));
        log.info("Submit");
        submitButton.submit();
        
        WebElement logOut = driver.findElement(By.xpath("//a[@title='Log out']"));
        assertEquals("Log Out",logOut.getText());

        // Check the title of the page
        log.info("Page title is: " + driver.getTitle());
             
        //Close the browser
        driver.quit();
	}

}
