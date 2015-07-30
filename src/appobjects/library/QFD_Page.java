package appobjects.library;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import config.Element;

public class QFD_Page {
	
	private WebDriver driver;
	
	public QFD_Page(WebDriver driver) {
		
		this.driver = driver;
		
	}

	public Element getDownload() {
		return new Element(driver.findElement(By.xpath("//img[@title='Click for attachments']")), "download icon");
	}

	public Element getAbout() {
		return new Element(driver.findElement(By.xpath("//a[@title='About']")), "About tab");
	}

	public Element getAttachments() {
		return new Element(driver.findElement(By.xpath("//a[contains(@title,'Attachments')]")), "Attachments tab");
	}
	
	public Element getComments() {
		return new Element(driver.findElement(By.partialLinkText("Comments")), "Comments tab");
	}
	
	public Element getVersions() {
		return new Element(driver.findElement(By.partialLinkText("Versions")), "Versions tab");
	}
	
	public WebElement getTextFrame() {
		return driver.findElement(By.xpath("//iframe[@class='quickrd_richtext']"));
	}

	public WebElement getRichTextFrameNext() {
		return driver.findElement(By.xpath("//iframe[contains(@title,'Rich text editor')]"));
	}
	
	public Element getCard() {
		return new Element(driver.findElement(By.xpath("//div[@title='Click here to view business card']")), "view business card link");
	}

	public Element getCardClose() {
		return new Element(driver.findElement(By.xpath("//img[@title='Close' and @alt='Close']")), "Close icon");
	}

	public Element getCheckOut() {
		return new Element(driver.findElement(By.linkText("Check Out and Edit")), "Check Out and Edit button");
	}

	public Element getCancel() {
		return new Element(driver.findElement(By.xpath("//span[text()='Cancel']")), "Cancel link");
	}

	public Element getAttachDelete() {
		return new Element(driver.findElement(By.xpath("//img[contains(@title,'Remove Attachment')]")), "remove icon");
	}

	public Element getEdit() {
		return new Element(driver.findElement(By.linkText("Edit")), "Edit button");
	}

	public Element getCheckIn() {
		return new Element(driver.findElement(By.linkText("Check In")), "Check In button");
	}

	public Element getSaveVersion() {
		return new Element(driver.findElement(By.xpath("//span[text()='Save Current as New Version']")), "Save Current as New Version link");
	}

	public Element getVersionsText() {
		return new Element(driver.findElement(By.xpath("//input[contains(@id,'qkr_changeSum')]")), "Version summary textbox");
	}

	public Element getRestoreVersion() {
		return new Element(driver.findElement(By.xpath("//span[text()='Restore']")), "Restore link");
	}

	public Element getAddComment() {
		return new Element(driver.findElement(By.linkText("Add Comment")), "Add Comment link");
	}

	public Element getPostComment() {
		return new Element(driver.findElement(By.xpath("//input[@value='Post Comment']")), "Post Comment button");
	}

	public Element getCommentMore() {
		return new Element(driver.findElement(By.xpath("//img[contains(@class,'lotusIcon') and @alt='More']")), "More icon");
	}

	public Element getReply() {
		return new Element(driver.findElement(By.linkText("Reply")), "Reply link");
	}

	public Element getReplyContent() {
		return new Element(driver.findElement(By.xpath("//span[text()='Test Text Reply']")), "Reply content");
	}

	public Element getFont() {
		return new Element(driver.findElement(By.xpath("//a[@title='Font Name']")), "Font select box");
	}

	public Element getSize() {
		return new Element(driver.findElement(By.xpath("//a[@title='Font Size']")), "Size select box");
	}

	public Element getTextColor() {
		return new Element(driver.findElement(By.xpath("//a[@title='Text Color']")), "Text Color button");
	}

	public Element getBGColor() {
		return new Element(driver.findElement(By.xpath("//a[@title='Background Color']")), "Background Color button");
	}

	public Element getIndent() {
		return new Element(driver.findElement(By.xpath("//a[@title='Indent Paragraph']")), "Indent Paragraph button");
	}

	public Element getOutdent() {
		return new Element(driver.findElement(By.xpath("//a[@title='Outdent']")), "Outdent button");
	}

	public Element getInsertImage() {
		return new Element(driver.findElement(By.xpath("//a[@title='Insert Image']")), "Insert Image button");
	}

	public Element getImageUpload() {
		return new Element(driver.findElement(By.xpath("//a[@title='Upload']")), "Upload tab");
	}

	public WebElement getUploadFrame() {
		return driver.findElement(By.xpath("//iframe[@title='Send it to the server']"));
	}

	public Element getImgUpload() {
		return new Element(driver.findElement(By.xpath("//input[@name='upload']")), "upload image path textbox");
	}

	public Element getSendToServer() {
		return new Element(driver.findElement(By.xpath("//span[text()='Send it to the server']")), "Send it to the server button");
	}

	public Element getImgOK() {
		return new Element(driver.findElement(By.xpath("//span[text()='OK']")), "OK button");
	}

	public Element getSpellchecker() {
		return new Element(driver.findElement(By.xpath("//a[@title='Spellchecker']")), "Spellchecker button");
	}

	public Element getSkip() {
		return new Element(driver.findElement(By.xpath("//span[text()='Skip']")), "Skip button");
	}

	public Element getSkipAll() {
		return new Element(driver.findElement(By.xpath("//span[text()='Skip All']")), "Skip All button");
	}

	public Element getReplace() {
		return new Element(driver.findElement(By.xpath("//span[text()='Replace' and contains(@class,'dialog')]")), "Replace button");
	}
	
	public Element getReplaceAll() {
		return new Element(driver.findElement(By.xpath("//span[text()='Replace All' and contains(@class,'dialog')]")), "Replace All button");
	}

	public Element getDialogCancel() {
		return new Element(driver.findElement(By.xpath("//span[text()='Cancel' and contains(@class,'dialog')]")), "Calcel link");
	}
	
	public Element getMoreActions() {
		return new Element(driver.findElement(By.xpath("//a[contains(text(),'More Actions')]")), "More Actions button");
	}

	public Element getMoveToTrash() {
		return new Element(driver.findElement(By.xpath("//td[text()='Move To Trash']")), "Move To Trash item");
	}

	public Element getEditProperties() {
		return new Element(driver.findElement(By.xpath("//td[text()='Edit Properties']")), "Edit Properties item");
	}
	
	public Element getUploadReplace() {
		return new Element(driver.findElement(By.xpath("//a[text()='Replace']")), "Replace button");
	}

	public Element getReplaceVersionsText() {
		return new Element(driver.findElement(By.xpath("//textarea[@id='h_VersionComments']")), "Version Summary textbox");
	}
	
}
