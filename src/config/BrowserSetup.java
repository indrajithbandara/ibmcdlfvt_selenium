package config;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import log.LogFile;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;

import util.Assert;
import util.ScenarioRunning;
import util.Times;

public class BrowserSetup {

	private String QuickrURL;
	private WebDriver driver;
	private WaitTime wt = new WaitTime();
	private Users u = new Users();
	public static Logger log;
	public static LogFile l = new LogFile();
	public static String BrowserType, BrowserTypeOutput, QuickrURLOutput;
	
	public BrowserSetup(LogFile l, Logger log){
		
		this.log = log;
		this.l = l;
		Times.st = true;
		
	}
	
	private void getConfig(String name) {
		
		ScenarioRunning.scenarioRunning = name;
		
		Properties props = new Properties();
		try {
			
			InputStream in = new BufferedInputStream (new FileInputStream("config/ServerConfig.properties"));
			props.load(in);
			
			BrowserType = props.getProperty("sBrowserType");
		    QuickrURL = props.getProperty("sQuickrURL") + "/LotusQuickr";
		    BrowserTypeOutput = BrowserType + " " + props.getProperty("sBrowserVersion");
		    QuickrURLOutput = props.getProperty("sQuickrURL");
			
	    }catch (Exception e) {
	    	
	    }
		
	}

	public WebDriver launchBrowser(String name){
		
		getConfig(name);
		
		wt.setWaitTime();
		
		u.setUsers();
		
		l.loadLogger();
		
		
		
		if(BrowserType.equalsIgnoreCase("FireFox")){ 
			
			driver = new FirefoxDriver(new ProfilesIni().getProfile("default"));
			driver.manage().window().maximize();
	        driver.get(QuickrURL);
	        
	        return driver;
	        
		}
		else if(BrowserType.equalsIgnoreCase("InternetExplorer")){ 
			
			//June
			System.setProperty("webdriver.ie.driver", "C:/software/IEDriverServer_x64_2.41.0/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
			//June
			driver.manage().timeouts().setScriptTimeout(300, TimeUnit.SECONDS);
	        driver.get(QuickrURL);
	        
	        return driver;
	        
		}
		else if(BrowserType.equalsIgnoreCase("Chrome")){ 
			
			driver = new ChromeDriver();
			driver.manage().window().maximize();
	        driver.get(QuickrURL);
	        
	        return driver;
	        
		}
		else {
			 
			driver = new FirefoxDriver(new ProfilesIni().getProfile("default"));
			driver.manage().window().maximize();
	        driver.get(QuickrURL);
	        
	        return driver;
	        
		}
		
	}

	public Users getUsers() {
		
		return u;

	}
	
}
