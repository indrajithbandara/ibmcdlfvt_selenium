package appobjects.ecm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import config.Element;

public class QFD_ECM {

	private WebDriver driver;
	
	public QFD_ECM(WebDriver driver) {

		this.driver = driver;
		
	}

	public Element getInsertLinkButtonCKEditor() {
		return new Element(driver.findElement(By.xpath("//a[@title='Insert Link' and @role='button']")), "Insert Link button");
	}

	public WebElement getInsertLinkFrame() {
		return driver.findElement(By.xpath("//iframe[@role='application' and @title='undefined']"));
	}

	public Element getInsertLinkToExtDoc() {
		return new Element(driver.findElement(By.linkText("Insert Link to External Document")), "Insert Link to External Document item");
	}

	public Element getInsertClose() {
		return new Element(driver.findElement(By.xpath("//img[@aria-label='close button']")), "Close button");
	}

	public Element getECM316LibraryItem() {
		return new Element(driver.findElement(By.xpath("//span[text()='ecm316Library' and @class='dijitTreeLabel']")), "ecm316Library item");
	}

	public Element getECMLibraryDoc() {
		return new Element(driver.findElement(By.xpath("//span[text()='1ExecutiveSummary.doc' and @class='dijitTreeLabel']")), "Word Doc item");
	}

	public Element getInsertLinkFooter() {
		return new Element(driver.findElement(By.xpath("//div[@class='lotusDialogFooter']")), "frame footer");
	}

	public Element getInsertLinkButton() {
		return new Element(driver.findElement(By.xpath("//button[@class='dijitStretch lotusFormButton' and @type='button']")), "Insert Link button");
	}

	public Element getECMLibraryJPG() {
		return new Element(driver.findElement(By.xpath("//span[text()='2.jpg' and @class='dijitTreeLabel']")), "JPG image item");
	}

	public Element getECMServerField() {
		return new Element(driver.findElement(By.xpath("//*/input[@class='dijitReset lotusLTR' and @type='text']")), "ECM Server textbox");
	}

	public Element getECMServerUserID() {
		return new Element(driver.findElement(By.xpath("//*/input[@dojoattachpoint='pickerUser' and @type='text']")), "ECM User textbox");
	}

	public Element getECMServerUserPwd() {
		return new Element(driver.findElement(By.xpath("//*/input[@dojoattachpoint='pickerPass' and @type='password']")), "ECM password textbox");
	}

	public Element getECMServerLogInButton() {
		return new Element(driver.findElement(By.xpath("//*/button[text()='Log In' and @class='lotusFormButton lotusTiny']")), "Log In button");
	}

	public Element getConfigureLink() {
		return new Element(driver.findElement(By.linkText("Configure")), "Configure link");
	}

	public Element getPublishToButton() {
		return new Element(driver.findElement(By.xpath("//a[text()='Publish To...']")), "Publish To button");
	}

	public Element getCancel() {
		return new Element(driver.findElement(By.linkText("Cancel")), "Cancel link");
	}

	public Element getHelp() {
		return new Element(driver.findElement(By.xpath("//a[@dojoattachpoint='helpNode' and @class='lotusRight']")), "Help icon");
	}

	public Element getECMLibraryItem() {
		return new Element(driver.findElement(By.xpath("//span[text()='QKSmokeLibrary' and @class='dijitTreeLabel']")), "QKSmokeLibrary item");
	}

	public Element getECMLibraryFolderItem() {
		return new Element(driver.findElement(By.xpath("//span[text()='0' and @class='dijitTreeLabel']")), "0 item");
	}

	public Element getPublishNextButton() {
		return new Element(driver.findElement(By.xpath("//a[text()='Next >']")), "Next button");
	}

	public Element getCopyRadiobox() {
		return new Element(driver.findElement(By.id("publishAction-copy")), "Create a copy ratio box");
	}

	public Element getPublishDoneButton() {
		return new Element(driver.findElement(By.linkText("Done")), "Done button");
	}

	public Element getReplaceRadiobox() {
		return new Element(driver.findElement(By.id("publishAction-link")), "Replace with a link ratio box");
	}
	
	public Element getMoveRadiobox() {
		return new Element(driver.findElement(By.id("publishAction-move")), "Move the document ratio box");
	}
}