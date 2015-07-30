package config;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

public class Element implements WebElement{

	private WebElement element;
	private String name;
	private Logger log;
	
	public Element(WebElement e){
		
		this(e, null);
		
	}
	
	public Element(WebElement e, String name){
		
		this.element = e;
		this.name = name;
		this.log = BrowserSetup.log;
		
	}
	
	public void setName(String name){
		
		this.name = name;
		
	}
	
	@Override
	public void clear() {
		
		if(this.name != null){
			
			log.info("Clearing the text of " + this.name);
			
		}else{
			
			log.info("Clearing the text of " + this.getText());
			
		}
		
		element.clear();
			
	}

	@Override
	public void click() {
		
		if(this.name != null){
			
			log.info("Clicking on " + this.name);
			
		}else{
			
			log.info("Clicking on " + this.getText());
			
		}
		
		element.click();
		
	}

	@Override
	public WebElement findElement(By arg0) {
		
		return element.findElement(arg0);
		
	}

	@Override
	public List<WebElement> findElements(By arg0) {
		
		return element.findElements(arg0);
		
	}

	@Override
	public String getAttribute(String arg0) {
		
		return element.getAttribute(arg0);
		
	}

	@Override
	public String getCssValue(String arg0) {
		
		return element.getCssValue(arg0);
		
	}

	@Override
	public Point getLocation() {
		
		return element.getLocation();
		
	}

	@Override
	public Dimension getSize() {

		return element.getSize();
		
	}

	@Override
	public String getTagName() {
		
		return element.getTagName();
		
	}

	@Override
	public String getText() {

		return element.getText();
		
	}

	@Override
	public boolean isDisplayed() {


		return element.isDisplayed();
		
	}

	@Override
	public boolean isEnabled() {

		return element.isEnabled();
		
	}

	@Override
	public boolean isSelected() {

		return element.isSelected();
		
	}

	@Override
	public void sendKeys(CharSequence... arg0) {

		log.info("Sending keys: '" + arg0[0] + "' into " + this.name);
		element.sendKeys(arg0);
		
	}

	@Override
	public void submit() {

		log.info("Submitting...");
		element.submit();
		
	}

}
