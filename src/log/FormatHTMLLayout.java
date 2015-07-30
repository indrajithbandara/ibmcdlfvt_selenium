package log;

import java.io.File;
import java.text.SimpleDateFormat;   
  
import org.apache.log4j.HTMLLayout;   
import org.apache.log4j.Layout;   
import org.apache.log4j.Level;   
import org.apache.log4j.helpers.Transform;     
import org.apache.log4j.spi.LoggingEvent;   

import config.BrowserSetup;

import util.ScenarioRunning;
import util.Screenshot;
import util.Times;
  
  
public class FormatHTMLLayout extends HTMLLayout {   
  
    public FormatHTMLLayout() {   
    }   
  
    protected final int BUF_SIZE = 256;   
  
    protected final int MAX_CAPACITY = 1024;   
  
    static String TRACE_PREFIX = "<br>&nbsp;&nbsp;&nbsp;&nbsp;";   
  
    private StringBuffer sbuf = new StringBuffer(BUF_SIZE);   
       
    String title = ScenarioRunning.scenarioRunning;   

    public static final String TITLE_OPTION = "Title";   
  
    boolean locationInfo = true;   
       
    public String format(LoggingEvent event) { 
    	
        if (sbuf.capacity() > MAX_CAPACITY) { 
        	
            sbuf = new StringBuffer(BUF_SIZE); 
            
        } else {   
        	
            sbuf.setLength(0);   
            
        }   
        
        sbuf.append(Layout.LINE_SEP + "<tr>" + Layout.LINE_SEP);   
                   
        sbuf.append("<td>");   
        sbuf.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()));   
        sbuf.append("</td>" + Layout.LINE_SEP);   
  
        sbuf.append("<td>"); 
        
        if (event.getLevel().equals(Level.FATAL)) { 
        	
            sbuf.append("<font color=\"red\">");   
            sbuf.append("Snap link");   
            sbuf.append("</font>");   
            
        } else if (event.getLevel().equals(Level.ERROR)) {  
        	
            sbuf.append("<font color=\"red\"><strong>");   
            sbuf.append(Transform.escapeTags(String.valueOf(event.getLevel())));   
            sbuf.append("</strong></font>");   
            
        } else if (event.getLevel().equals(Level.WARN)) {
        	
        	sbuf.append("<font color=\"blue\"><strong>");   
            sbuf.append("Verified");   
            sbuf.append("</strong></font>"); 
        	
        } else {   
        	
            sbuf.append("<font color=\"green\">");   
            sbuf.append(Transform.escapeTags(String.valueOf(event.getLevel())));   
            sbuf.append("</font>");   
            
        }   
        
        sbuf.append("</td>" + Layout.LINE_SEP);   
           
        if (event.getLevel().equals(Level.FATAL)) { 
        	
            String link = event.getRenderedMessage();
            sbuf.append("<td>");
            sbuf.append("<a href=\"file:../Screenshots" + File.separator + Screenshot.name + "\">");  
            sbuf.append(Transform.escapeTags(link));    
            sbuf.append("</a>" + Layout.LINE_SEP);  
            sbuf.append("</td>" + Layout.LINE_SEP); 
            
            sbuf.append("<td>");   
            sbuf.append("Link to the snapshot");   
            sbuf.append("</td>" + Layout.LINE_SEP);   
            sbuf.append("</tr>" + Layout.LINE_SEP); 
            
        }else{
        	
        	sbuf.append("<td>");   
            sbuf.append("/");   
            sbuf.append("</td>" + Layout.LINE_SEP);
        	
        	sbuf.append("<td>");   
            sbuf.append(Transform.escapeTags(event.getRenderedMessage()));   
            sbuf.append("</td>" + Layout.LINE_SEP);   
            sbuf.append("</tr>" + Layout.LINE_SEP); 
            
        }
             
        if (event.getNDC() != null) {   
        	
            sbuf.append("<tr><td bgcolor=\"#EEEEEE\" style=\"font-size : xx-small;\" colspan=\"6\" title=\"Nested Diagnostic Context\">");   
            sbuf.append("NDC: " + Transform.escapeTags(event.getNDC()));   
            sbuf.append("</td></tr>" + Layout.LINE_SEP);   
            
        }   
  
        String[] s = event.getThrowableStrRep();   
        
        if (s != null) {   
        	
            sbuf.append("<tr><td bgcolor=\"#993300\" style=\"color:White; font-size : xx-small;\" colspan=\"4\">");   
            appendThrowableAsHTML(s, sbuf);   
            sbuf.append("</td></tr>" + Layout.LINE_SEP); 
            
        }   
        
        return sbuf.toString();
        
    }   
  
    private void appendThrowableAsHTML(String[] s, StringBuffer sbuf) { 
    	
        if (s != null) {   
        	
            int len = s.length; 
            
            if (len == 0)   return;  
            
            sbuf.append(Transform.escapeTags(s[0]));   
            sbuf.append(Layout.LINE_SEP);   
            
            for (int i = 1; i < len; i++) { 
            	
                sbuf.append(TRACE_PREFIX);   
                sbuf.append(Transform.escapeTags(s[i]));   
                sbuf.append(Layout.LINE_SEP);  
                
            }   
            
        }   
        
    }   
  
    public String getHeader() {   
    	
        StringBuffer sbuf = new StringBuffer();   
        
        sbuf.append("<html>" + Layout.LINE_SEP);   
        sbuf.append("<head>" + Layout.LINE_SEP);   
    
        sbuf.append("<title>" + title + "</title>" + Layout.LINE_SEP);   
        sbuf.append("<style type=\"text/css\">" + Layout.LINE_SEP);   
        sbuf.append("<!--" + Layout.LINE_SEP);   
        sbuf.append("body, table {font-family: '??',arial,sans-serif; font-size: 12px;}" + Layout.LINE_SEP);   
        sbuf.append("th {background: #336699; color: #FFFFFF; text-align: left;}" + Layout.LINE_SEP);   
        sbuf.append("-->" + Layout.LINE_SEP);   
        sbuf.append("</style>" + Layout.LINE_SEP);   
        sbuf.append("</head>" + Layout.LINE_SEP);   
        sbuf.append("<body bgcolor=\"#FFFFFF\" topmargin=\"6\" leftmargin=\"6\">" + Layout.LINE_SEP);   
    
        sbuf.append("<table cellspacing=\"0\" cellpadding=\"4\" border=\"1\" bordercolor=\"#224466\" width=\"100%\">" + Layout.LINE_SEP);   
        sbuf.append("<tr>" + Layout.LINE_SEP);   
       
        sbuf.append("<tr>" + Layout.LINE_SEP);
        sbuf.append("<th colspan=\"4\">Browser: " + BrowserSetup.BrowserTypeOutput + "</th>" + Layout.LINE_SEP);
        sbuf.append("</tr>" + Layout.LINE_SEP);
        
        sbuf.append("<tr>" + Layout.LINE_SEP);
        sbuf.append("<th colspan=\"4\">Quickr Domino Server: " + BrowserSetup.QuickrURLOutput + "</th>" + Layout.LINE_SEP);
        sbuf.append("</tr>" + Layout.LINE_SEP);
        
        sbuf.append("<tr>" + Layout.LINE_SEP);
        sbuf.append("<th colspan=\"4\">Scenario: " + title + " runs for " + Times.Times + " time(s)" + "</th>" + Layout.LINE_SEP);
        sbuf.append("</tr>" + Layout.LINE_SEP); 
        
        sbuf.append("<th>Time</th>" + Layout.LINE_SEP);   
        sbuf.append("<th>Level</th>" + Layout.LINE_SEP);   
      
        if (locationInfo) {   
        	
            sbuf.append("<th>Link</th>" + Layout.LINE_SEP); 
            
        }   
   
        sbuf.append("<th>Message</th>" + Layout.LINE_SEP);   
        sbuf.append("</tr>" + Layout.LINE_SEP);   
        sbuf.append("<br></br>" + Layout.LINE_SEP); 
        
        return sbuf.toString();   
        
    }   
  
}  