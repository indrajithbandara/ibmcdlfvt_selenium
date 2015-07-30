package appobjects.toc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import config.Element;

public class QFD_Library {

	private WebDriver driver;
	
	public QFD_Library(WebDriver driver) {
		
		this.driver = driver;
		
	}
	
	public Element getLibrary() {
		return new Element(driver.findElement(By.partialLinkText("Library")), "Library link");
	}
	
	public Element getNew() {
		return new Element(driver.findElement(By.xpath("//a[@title='New']")), "New button");
	}
	
	public Element getNewPage() {
		return new Element(driver.findElement(By.xpath("//td[text()='Page']")), "Page item");
	}
	
	public Element getNewLink() {
		return new Element(driver.findElement(By.xpath("//td[text()='Link']")), "Link item");
	}
	
	public Element getLinkPageName() {
		return new Element(driver.findElement(By.name("title")), "name textbox");
	}
	
	public Element getLinkURLButton() {
		return new Element(driver.findElement(By.id("URL_0")), "URL ratio button");
	}
	
	public Element getLinkURLText() {
		return new Element(driver.findElement(By.id("UrlRadioInput")), "URL text box");
	}
	
	public Element getLinkNewWindow() {
		return new Element(driver.findElement(By.id("h_URLNewWindow")), "open URL in new window select box");
	}

	public Element getSaveCheckIn() {
		return new Element(driver.findElement(By.xpath("//input[@value='Save and Check In']")), "'Save and Check In' button");
	}

	public Element getContext() {
		return new Element(driver.findElement(By.xpath("//img[@title='Click for options']")), "context menu");
	}

	public Element getContextView() {
		return new Element(driver.findElement(By.xpath("//td[text()='View']")), "view item");
	}

	public Element getPageName() {
		return new Element(driver.findElement(By.name("title")), "page name textbox");
	}

	public WebElement getRichTextFrame() {
		return driver.findElement(By.xpath("//iframe[@title='Enter the rich text content for this entry']"));
	}

	public Element getPageDesc() {
		return new Element(driver.findElement(By.xpath("//body[@class='cke_show_borders']")), "CK Editor");
	}
	
	public Element getCommentDesc() {
		return new Element(driver.findElement(By.xpath("//td[@class='cke_contents']")), "CK Editor");
	}

	public Element getPageAttach() {
		return new Element(driver.findElement(By.linkText("Attach files to this page")), "add an attachment link");
	}

	public Element getFileField() {
		return new Element(driver.findElement(By.xpath("//input[@name='attachment_0']")), "attachment path textbox");
	}

	public Element getUpload() {
		return new Element(driver.findElement(By.linkText("Upload")), "Upload button");
	}

	public Element getUploadFile() {
		return new Element(driver.findElement(By.xpath("//input[@type='file']")), "upload file path textbox");
	}

	public Element getUploadDesc() {
		return new Element(driver.findElement(By.xpath("//textarea[@name='description']")), "upload description textbox");
	}

	public Element getPageSaveDraft() {
		return new Element(driver.findElement(By.xpath("//span[text()='Save as Draft']")), "Save as Draft link");
	}
	
	public Element getUploadSaveDraft() {
		return new Element(driver.findElement(By.xpath("//a[text()='Save as Draft']")), "Save as Draft link");
	}

	public Element getLibraryTitle() {
		return new Element(driver.findElement(By.xpath("//span[text()='Library']")), "Library title");
	}

	public Element getFolderName() {
		return new Element(driver.findElement(By.xpath("//input[@name='title']")), "name textbox");
	}

	public Element getFolderDesc() {
		return new Element(driver.findElement(By.xpath("//textarea[@name='description']")), "description textbox");
	}

	public Element getOK() {
		return new Element(driver.findElement(By.xpath("//input[@value='OK']")), "OK button");
	}
	
	public Element getListDesc() {
		return new Element(driver.findElement(By.xpath("//textarea[@name='_description']")), "folder description textbox");
	}

	public Element getSave() {
		return new Element(driver.findElement(By.xpath("//input[@value='Save']")), "Save button");
	}

	public Element getListAddItem() {
		return new Element(driver.findElement(By.xpath("//a[@title='Add a new list item']")), "Add Item button");
	}

	public Element getListItemTitle() {
		return new Element(driver.findElement(By.xpath("//input[@id='quickr_lw_widget_ItemField_0_label']")), "List Item title textbox");
	}

	public Element getPageMoreAttach() {
		return new Element(driver.findElement(By.linkText("Attach more files to this page")), "add more attachment link");
	}

	public Element getBold() {
		return new Element(driver.findElement(By.xpath("//a[contains(@class,'cke_button_bold')]")), "Bold button");
	}
	
	public Element getItalic() {
		return new Element(driver.findElement(By.xpath("//a[contains(@class,'cke_button_italic')]")), "Italic button");
	}
	
	public Element getUnderline() {
		return new Element(driver.findElement(By.xpath("//a[contains(@class,'cke_button_underline')]")), "Underline button");
	}
	
	public Element getStrikeThrough() {
		return new Element(driver.findElement(By.xpath("//a[contains(@class,'cke_button_strike')]")), "Strike Through button");
	}

	public Element getBreadcrumbLibrary() {
		return new Element(driver.findElement(By.xpath("//a[text()='Library']")), "Library link in breadcrumb");
	}

	public Element getLinkCancel() {
		return new Element(driver.findElement(By.xpath("//a[text()='Cancel']")), "Cancel link");
	}

	public Element getEditProperty() {
		return new Element(driver.findElement(By.xpath("//td[text()='Edit Properties']")), "Edit Properties item");
	}

	public Element getSubscribe() {
		return new Element(driver.findElement(By.xpath("//a[contains(text(),'Subscribe to')]")), "Subscribe link");
	}

	public Element getSubscribeNow() {
		return new Element(driver.findElement(By.xpath("//*[@id='subscribeButton']")), "Subscribe Now button");
	}

	public Element getUploadName() {
		return new Element(driver.findElement(By.xpath("//input[@id='tempTitle']")), "Upload name textbox");
	}

	public Element getListName() {
		return new Element(driver.findElement(By.xpath("//input[@class='lotusText']")), "name textbox");
	}

	public Element getListDescIE() {
		return new Element(driver.findElement(By.xpath("//textarea[@class='lotusText']")), "description textbox");
	}
	
}
