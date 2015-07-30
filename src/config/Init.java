package config;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Init {

	public static String BrowserTypeStart;
	public static String QuickrURLStart;
	
	public void init(){
		
		Properties props = new Properties();
		try {
			
			InputStream in = new BufferedInputStream (new FileInputStream("config/ServerConfig.properties"));
			props.load(in);
			
		    BrowserTypeStart = props.getProperty("sBrowserType") + " " + props.getProperty("sBrowserVersion");
		    QuickrURLStart = props.getProperty("sQuickrURL"); 
			
	    }catch (Exception e) {
	    	
	    }
		
		
	}
	
}
