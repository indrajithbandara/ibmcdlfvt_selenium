package appobjects.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import config.Element;

public class QFD_Members {

	private WebDriver driver;
	
	public QFD_Members(WebDriver driver) {
		
		this.driver = driver;
		
	}
	
	public Element getMembersTOC() {
		return new Element(driver.findElement(By.xpath("//a[@id='membersBtn' and contains(text(),'Members')]")), "Members link");
	}

	public Element getMembersBlogWiki() {
		return new Element(driver.findElement(By.linkText("Members")), "Members link");
	}
	
	public Element getAddMembers() {
		return new Element(driver.findElement(By.xpath(addMembersPath)), "Add Members button");
	}
	
	public Element getReaderTab() {
		return new Element(driver.findElement(By.id("ReaderTab")), "Reader tab");
	}
	
	public Element getReaderCreateTab() {
		return new Element(driver.findElement(By.id("ReaderCreateTab")), "Reader tab");
	}
	
	public Element getReaderChangeTab() {
		return new Element(driver.findElement(By.id("ReaderChangeTab")), "Reader tab");
	}
	
	public Element getReaderTabRoom() {
		return new Element(driver.findElement(By.id("h_tab_Reader")), "Reader tab");
	}
	
	public Element getAuthorTab() {
		return new Element(driver.findElement(By.id("AuthorTab")), "Author tab");
	}
	
	public Element getAuthorCreateTab() {
		return new Element(driver.findElement(By.id("AuthorCreateTab")), "Author tab");
	}
	
	public Element getAuthorTabRoom() {
		return new Element(driver.findElement(By.id("h_tab_Author")), "Author tab");
	}
	
	public Element getEditorTab() {
		return new Element(driver.findElement(By.id("EditorTab")), "Editor tab");
	}
	
	public Element getEditorCreateTab() {
		return new Element(driver.findElement(By.id("EditorCreateTab")), "Editor tab");
	}
	
	public Element getEditorTabRoom() {
		return new Element(driver.findElement(By.id("h_tab_Editor")), "Author tab");
	}
	
	public Element getManagerTab() {
		return new Element(driver.findElement(By.id("ManagerTab")), "Manager tab");
	}
	
	public Element getManagerCreateTab() {
		return new Element(driver.findElement(By.id("ManagerCreateTab")), "Manager tab");
	}
	
	public Element getManagerTabRoom() {
		return new Element(driver.findElement(By.id("h_tab_Manager")), "Manager tab");
	}
	
	public Element getOwnerTab() {
		return new Element(driver.findElement(By.id("OwnerTab")), "Owner tab");	
	}
	
	public Element getOwnerTabRoom() {
		return new Element(driver.findElement(By.id("h_tab_SuperUser")), "Owner tab");	
	}
	
	public Element getAddReaderInput() {
		return new Element(driver.findElement(By.id("memberAddReader")), "reader input textbox");
	}
	
	public Element getAddAuthorInput() {
		return new Element(driver.findElement(By.id("memberAddAuthor")), "author input textbox");
	}
	
	public Element getAddEditorInput() {
		return new Element(driver.findElement(By.id("memberAddEditor")), "editor input textbox");
	}
	
	public Element getAddManagerInput() {
		return new Element(driver.findElement(By.id("memberAddManager")), "manager input textbox");
	}
	
	public Element getAddOwnerInput() {
		return new Element(driver.findElement(By.id("memberAddOwner")), "owner input textbox");
	}
	
	public Element getOKButton() {
		return new Element(driver.findElement(By.name("submitAdd")), "OK button");
	}
	
	public Element getCreateOK() {
		return new Element(driver.findElement(By.name("submitCreate")), "OK button");
	}
	
	public Element getChangeOK() {
		return new Element(driver.findElement(By.xpath("//button[@name='submitChange' and @type='button']")), "OK button");
	}
	
	public Element getDeleteOK() {
		return new Element(driver.findElement(By.name("submitRemove")), "OK button");
	}
	
	public Element getAccessOK() {
		return new Element(driver.findElement(By.name("submitAnonDef")), "OK button");
	}
	
	public Element getCloseButton() {
		return new Element(driver.findElement(By.xpath("//input[@value='Close']")), "Close button");
	}
	
	public WebElement getMembersIFrame() {
		return driver.findElement(By.id("contentViewerIframe"));
	}
	
	public Element getVerifyUser() {
		return new Element(driver.findElement(By.xpath("//input[@value='Close']")), "Close button");
	}
	
	public Element getVerifyReader() {
		return new Element(driver.findElement(By.xpath(sVerifyReaderPath)));
	}
	
	public Element getVerifyAuthor() {
		return new Element(driver.findElement(By.xpath(sVerifyAuthorPath)));
	}
	
	public Element getVerifyEditor() {
		return new Element(driver.findElement(By.xpath(sVerifyEditorPath)));
	}
	
	public Element getVerifyManager() {
		return new Element(driver.findElement(By.xpath(sVerifyManagerPath)));
	}
	
	public Element getVerifyOwner() {
		return new Element(driver.findElement(By.xpath(sVerifyOwnerPath)));
	}
	
	public Element getMoreActions() {
		return new Element(driver.findElement(By.xpath("//a[@id='moreActions']")), "More Actions button");
	}
	
	public Element getGroupRadio() {
		return new Element(driver.findElement(By.xpath("//input[@type='radio' and @title='Group']")), "Group radio button");
	}

	public Element getCreateMembers() {
		return new Element(driver.findElement(By.partialLinkText("Create Members")), "Create Members button");
	}

	public Element getReaderCreateName() {
		return new Element(driver.findElement(By.xpath("//input[@type='text' and contains(@id,'ReaderName')]")), "Reader Name textbox");
	}
	
	public Element getReaderCreatePW() {
		return new Element(driver.findElement(By.xpath("//input[@type='password' and contains(@id,'ReaderPass')]")), "Reader Password textbox");
	}
	
	public Element getReaderCreatePWRe() {
		return new Element(driver.findElement(By.xpath("//input[@type='password' and contains(@id,'ReaderVPass')]")), "Reader Re-enter Password textbox");
	}
	
	public Element getAuthorCreateName() {
		return new Element(driver.findElement(By.xpath("//input[@type='text' and contains(@id,'AuthorName')]")), "Author Name textbox");
	}
	
	public Element getAuthorCreatePW() {
		return new Element(driver.findElement(By.xpath("//input[@type='password' and contains(@id,'AuthorPass')]")), "Author Password textbox");
	}
	
	public Element getAuthorCreatePWRe() {
		return new Element(driver.findElement(By.xpath("//input[@type='password' and contains(@id,'AuthorVPass')]")), "Author Re-enter Password textbox");
	}
	
	public Element getEditorCreateName() {
		return new Element(driver.findElement(By.xpath("//input[@type='text' and contains(@id,'EditorName')]")), "Editor Name textbox");
	}
	
	public Element getEditorCreatePW() {
		return new Element(driver.findElement(By.xpath("//input[@type='password' and contains(@id,'EditorPass')]")), "Editor Password textbox");
	}
	
	public Element getEditorCreatePWRe() {
		return new Element(driver.findElement(By.xpath("//input[@type='password' and contains(@id,'EditorVPass')]")), "Editor Re-enter Password textbox");
	}
	
	public Element getManagerCreateName() {
		return new Element(driver.findElement(By.xpath("//input[@type='text' and contains(@id,'ManagerName')]")), "Manager Name textbox");
	}
	
	public Element getManagerCreatePW() {
		return new Element(driver.findElement(By.xpath("//input[@type='password' and contains(@id,'ManagerPass')]")), "Manager Password textbox");
	}
	
	public Element getManagerCreatePWRe() {
		return new Element(driver.findElement(By.xpath("//input[@type='password' and contains(@id,'ManagerVPass')]")), "Manager Re-enter Password textbox");
	}
	
	public Element getChangeRole() {
		return new Element(driver.findElement(By.xpath("//a[contains(text(),'Change Role')]")), "Change Role item");
	}

	public Element getDelete() {
		return new Element(driver.findElement(By.xpath("//a[contains(text(),'Delete')]")), "Delete item");
	}

	public Element getAccessEveryone() {
		return new Element(driver.findElement(By.xpath("//a[contains(text(),'Grant Access to Everyone')]")), "Grant Access to Everyone item");
	}
	
	public Element getAddReaderRoom() {
		return new Element(driver.findElement(By.xpath("//a[contains(text(),'Add Reader')]")), "Add Reader button");
	}
	
	public Element getMemberPicker() {
		return new Element(driver.findElement(By.xpath("//a[@id='toFieldLink']")), "Member Picker link");
	}
	
	public Element getPickerJump() {
		return new Element(driver.findElement(By.xpath("//input[@name='jumptopage']")), "Jump to Page textbox");
	}

	public Element get20() {
		return new Element(driver.findElement(By.xpath("//a[text()='20']")), "20 link");
	}	

	public Element getUserName() {
		return new Element(driver.findElement(By.xpath("//a[text()='User Name']")), "sort by User Name link");
	}

	public Element getPickerTwisty() {
		return new Element(driver.findElement(By.xpath("//a[@title='click to expand section']")), "twisty of group");
	}
	
	public Element getPickerTwistyCollapse() {
		return new Element(driver.findElement(By.xpath("//a[@title='click to collapse section']")), "collapse twisty of group");
	}
	
	public Element getOK() {
		return new Element(driver.findElement(By.xpath("//input[@type='button' and @value='OK']")), "OK button");
	}

	public Element getMemberFilter() {
		return new Element(driver.findElement(By.xpath("//input[@id='memberFilter']")), "Member Filter textbox");
	}

	private String addMembersPath = "//a[@class='actionBtn' and @alt='Add members from a directory']";
	private String sVerifyReaderPath = "//td[text()='Reader' and @class='h-folderItem-text']";
	private String sVerifyAuthorPath = "//td[text()='Author' and @class='h-folderItem-text']";
	private String sVerifyEditorPath = "//td[text()='Editor' and @class='h-folderItem-text']";
	private String sVerifyManagerPath = "//td[text()='Manager' and @class='h-folderItem-text']";
	private String sVerifyOwnerPath = "//td[text()='Owner' and @class='h-folderItem-text']";

}
