package appobjects.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import config.Element;

public class QFD_Admin {

	private WebDriver driver;
	
	public QFD_Admin(WebDriver driver) {
		
		this.driver = driver;
		
	}

	public Element getPlaceAdmin() {
		return new Element(driver.findElement(By.xpath("//a[text()='Place Administration']")), "Place Administration link");
	}

	public Element getWorkTemplates() {
		return new Element(driver.findElement(By.xpath("//a[text()='Work with Templates']")), "Work with Templates link");
	}

	public Element getSiteAdmin() {
		return new Element(driver.findElement(By.xpath("//a[text()='Site Administration']")), "Site Administration link");
	}

	public Element getHome() {
		return new Element(driver.findElement(By.xpath("//span[text()='Home']")), "Home link on footer");
	}

	public Element getHomeLink() {
		return new Element(driver.findElement(By.xpath("//a[text()='Home']")), "Home link on footer");
	}

	public Element getSecurity() {
		return new Element(driver.findElement(By.xpath("//a[text()='Security']")), "Security link");
	}

	public Element getAddCreator() {
		return new Element(driver.findElement(By.xpath("//a[contains(@title,'Click this to allow additional users to create places on this server')]")), "Add button");
	}

	public Element getDirectory() {
		return new Element(driver.findElement(By.xpath("//input[@alt='Select names from the directory']")), "Directory button");
	}

	public Element getDirectoryText() {
		return new Element(driver.findElement(By.xpath("//input[@name='h_LookupText' and @type='text']")), "Search name textbox");
	}

	public Element getAdd() {
		return new Element(driver.findElement(By.xpath("//input[@value='Add' and @type='button']")), "Add button");
	}

	public Element getClose() {
		return new Element(driver.findElement(By.xpath("//input[@value='Close' and @type='button']")), "Close button");
	}

	public Element getSearch() {
		return new Element(driver.findElement(By.xpath("//input[@value='Search' and @type='button']")), "Search button");
	}

	public WebElement getSearchFrame() {
		return driver.findElement(By.xpath("//iframe[@id='LookupResults']"));
	}

	public Element getNext() {
		return new Element(driver.findElement(By.xpath("//a[text()='Next']")), "Next button");
	}

	public Element getRemoveCreator() {
		return new Element(driver.findElement(By.xpath("//a[contains(@title,'privilege to create places on this server')]")), "Remove button");
	}

	public Element getAddAdmin() {
		return new Element(driver.findElement(By.xpath("//a[contains(@title,'Click this to allow additional users to administer this Quickr server')]")), "Add button");
	}

	public Element getRemoveAdmin() {
		return new Element(driver.findElement(By.xpath("//a[contains(@title,'privilege to administer this Quickr server')]")), "Remove button");
	}

	public Element getUserDirectory() {
		return new Element(driver.findElement(By.xpath("//a[text()='User Directory']")), "User Directory link");
	}

	public Element getChangeDirectory() {
		return new Element(driver.findElement(By.xpath("//a[text()='Change Directory']")), "Change Directory link");
	}

	public Element getType() {
		return new Element(driver.findElement(By.xpath("//select[@name='h_UserDirType']")), "User type selector");
	}

	public Element getDominoOption() {
		return new Element(driver.findElement(By.xpath("//option[text()='Domino Server']")), "Domino Server option");
	}
	
	public Element getLDAPOption() {
		return new Element(driver.findElement(By.xpath("//option[text()='LDAP Server']")), "LDAP Server option");
	}

	public Element getBack() {
		return new Element(driver.findElement(By.xpath("//a[text()='Back']")), "Back button");
	}

	public Element getLDAPName() {
		return new Element(driver.findElement(By.xpath("//input[@name='h_UserDirName']")), "LDAP name textbox");
	}

	public Element getOtherOptions() {
		return new Element(driver.findElement(By.xpath("//a[text()='Other Options']")), "Other Options link");
	}

	public Element getEditOptions() {
		return new Element(driver.findElement(By.xpath("//a[text()='Edit Options']")), "Edit Options button");
	}

	public Element getCreatePlaceType() {
		return new Element(driver.findElement(By.xpath("//a[contains(text(),'Create PlaceType')]")), "Create PlaceType button");
	}

	public Element getPlaceTypeName() {
		return new Element(driver.findElement(By.xpath("//input[@name='h_Name']")), "PlaceType name textbox");
	}

	public Element getSourcePlace() {
		return new Element(driver.findElement(By.xpath("//select[@name='h_SourcePlaceName']")), "Source Place selector");
	}

	public Element getEdit() {
		return new Element(driver.findElement(By.xpath("//a[text()='Edit']")), "Edit button");
	}

	public Element getTemplateDesc() {
		return new Element(driver.findElement(By.xpath("//textarea[@name='h_Description']")), "description textbox");
	}

	public Element getTemplateURL() {
		return new Element(driver.findElement(By.xpath("//input[@name='h_InfoUrl']")), "URL textbox");
	}

	public Element getMoreInfo() {
		return new Element(driver.findElement(By.xpath("//a[contains(text(),'More info')]")), "More info link");
	}
	
	public Element getMoreInfoLink() {
		return new Element(driver.findElement(By.xpath("//span[contains(text(),'More info')]")), "More info link");
	}

	public Element getPolicyTab() {
		return new Element(driver.findElement(By.xpath("//a[text()='Policies']")), "Policies tab");
	}

	public Element getCreateNewPolicy() {
		return new Element(driver.findElement(By.xpath("//a[text()='Create new policy']")), "Create new policy button");
	}

	public Element getPolicyName() {
		return new Element(driver.findElement(By.xpath("//input[@id='policyName']")), "Policy name textbox");
	}

	public Element getLockSize() {
		return new Element(driver.findElement(By.xpath("//input[@id='maxSizeLock']")), "Lock size textbox");
	}

	public Element getWarnSize() {
		return new Element(driver.findElement(By.xpath("//input[@id='maxSizeWarn']")), "Warn size textbox");
	}

	public Element getLockDays() {
		return new Element(driver.findElement(By.xpath("//input[@id='inactiveDaysArchive']")), "Lock days textbox");
	}

	public Element getWarnDays() {
		return new Element(driver.findElement(By.xpath("//input[@id='inactiveDaysWarn']")), "Warn days textbox");
	}

	public Element getOK() {
		return new Element(driver.findElement(By.xpath("//input[@value='OK']")), "OK button");
	}

	public Element getEditPolicy() {
		return new Element(driver.findElement(By.xpath("//a[text()='Edit policy']")), "Edit policy button");
	}

	public Element getDeletePolicy() {
		return new Element(driver.findElement(By.xpath("//a[text()='Delete policy']")), "Delete policy button");
	}

	public Element getCancel() {
		return new Element(driver.findElement(By.xpath("//h1/a[contains(@title,'close dialog')]")), "Cancel link");
	}
	                  
	public Element getStatisticsTab() {
		return new Element(driver.findElement(By.xpath("//a[text()='Statistics']")), "Statistics tab");
	}

	public Element getDownloadStatistics() {
		return new Element(driver.findElement(By.xpath("//a[text()='Download Statistics']")), "Download Statistics button");
	}

	public Element getPlacesTab() {
		return new Element(driver.findElement(By.xpath("//a[@dojoattachpoint='tabNode' and text()='Places']")), "Places tab");
	}

	public Element getLockedPlaces() {
		return new Element(driver.findElement(By.xpath("//a[text()='Locked places']")), "Locked places link");
	}

	public Element getAllPlaces() {
		return new Element(driver.findElement(By.xpath("//a[text()='All places']")), "All places link");
	}

	public Element getPlaceSearch() {
		return new Element(driver.findElement(By.xpath("//input[@id='filterPlaceKey']")), "Places search keyword textbox");
	}

	public Element getPlaceSearchButton() {
		return new Element(driver.findElement(By.xpath("//input[@class='lotusSearchButton']")), "Places search button");
	}

	public Element getTemplateTab() {
		return new Element(driver.findElement(By.xpath("//a[text()='Templates']")), "Templates tab");
	}

	public Element getAssignPolicy() {
		return new Element(driver.findElement(By.xpath("//a[text()='Assign policy']")), "Assign policy button");
	}
	
}
