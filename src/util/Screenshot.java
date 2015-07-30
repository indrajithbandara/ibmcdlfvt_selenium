package util;

import java.io.File;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Screenshot {
	
	public static String path = null;
	public static String name = null;
	
	private static File file;
	
	public static String screenShot(WebDriver driver, String scenarioName) {

	    String dir_name = "logs/Screenshots";      
	    
	    if (!(new File(dir_name).isDirectory())) { 
	    	
	        new File(dir_name).mkdir();  
	        
	    }
	 
	    Calendar cal = Calendar.getInstance();
	    
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
	    String time = sdf.format(cal.getTime());  
	    
	    try {
	  
	        File source_file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); 
	        path = dir_name + File.separator + time + "_" + scenarioName.substring(14) + ".png";
	        file = new File(path);
	        name = time + "_" + scenarioName.substring(14) + ".png";
	        FileUtils.copyFile(source_file, file);
	        
	    } catch (IOException e) {
	    	System.out.println("1");
	    }
	    
		return path;
	    
	}
	
}
