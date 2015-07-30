package config;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class WaitTime {
	
	public static int veryshort, shorttime, mid, longtime, verylong;
	public static int times;
	
	public void setWaitTime(){
	
		Properties props = new Properties();
		try {
			
			InputStream in = new BufferedInputStream (new FileInputStream("config/WaitTime.properties"));
			props.load(in);
			
			String v = props.getProperty("veryshort");
			veryshort = Integer.valueOf(v).intValue();
			
			String c = props.getProperty("shorttime");
			shorttime = Integer.valueOf(c).intValue();
			
			String l = props.getProperty("mid");
			mid = Integer.valueOf(l).intValue();
			
			String a = props.getProperty("longtime");
			longtime = Integer.valueOf(a).intValue();
			
			String k = props.getProperty("verylong");
			verylong = Integer.valueOf(k).intValue();
			
			String t = props.getProperty("times");
			times = Integer.valueOf(t).intValue();
			
	    }catch (Exception e) {
	    	
	    }
	
	}
}
