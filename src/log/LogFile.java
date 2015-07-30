package log;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Layout;
import org.apache.log4j.xml.DOMConfigurator;

import config.Init;

import util.Datefuncs;
import util.ScenarioRunning;
import util.Times;

import junit.TestAll;

public class LogFile {

	private File file, fileAll;
	private Calendar cal = Calendar.getInstance();
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
	private String time = sdf.format(cal.getTime());
    private SimpleDateFormat dayF = new SimpleDateFormat("yyyyMMdd");
    private String day = dayF.format(cal.getTime());  
    private static int num = 0, pass = 0, fail = 0;
    private static PrintStream printStream = null;
    private static StringBuilder sb = new StringBuilder();
    private static long start, finish;
    
    public void loadLogger()
	{
		DOMConfigurator.configure("log4j.xml");
	}
    
	public void changeLogName(String name){
			    		
		if(Times.st){
		
			try {

				addStatus(ScenarioRunning.scenarioRunning, true);
				
			} catch (IOException e) {
				
			}
			
		}
		
	    if (!(new File("logs").isDirectory())) { 
	    	
	        new File("logs").mkdir();  
	        
	    }
	    
	    if (!(new File("logs/" + day).isDirectory())) { 
	    	
	        new File("logs/" + day).mkdir();  
	        
	    }
	    
		file = new File("logfile.html");
		
		file.renameTo(new File("logs/" + day + File.separator + time + "_" + name + ".html"));

	} 
		
	public void start() throws IOException{
		
		file = new File("logfile.html");
		if(file.exists())file.delete();
		
		if ((new File("logs/" + day).isDirectory())){
	    	
	    	new File("logs/" + day).renameTo(new File("logs/" + day + " " + Datefuncs.genDateBasedRandVal()));
	    	
	    }
		start = System.currentTimeMillis();
		
		sb.append("<html><head><title>Result of " + day + "</title>" + Layout.LINE_SEP);
	    sb.append("<style type=\"text/css\">" + Layout.LINE_SEP);
	    sb.append("<!--" + Layout.LINE_SEP);
	    sb.append("body, table {font-family: '??',arial,sans-serif; font-size: 12px;}" + Layout.LINE_SEP);
	    sb.append("th {background: #336699; color: #FFFFFF; text-align: left;}" + Layout.LINE_SEP);
	    sb.append("-->\")" + Layout.LINE_SEP);
	    sb.append("</style></head>" +
	    		"<body bgcolor=\"#FFFFFF\" topmargin=\"6\" leftmargin=\"6\">" +
	    				"<table cellspacing=\"0\" cellpadding=\"4\" border=\"1\" bordercolor=\"#224466\" width=\"100%\">" +
	    						"<tr><tr>" + Layout.LINE_SEP);
	    sb.append("<th colspan=\"4\"><font size=24>Result of " + day + "</font></th>" + Layout.LINE_SEP);

	    sb.append("<tr>" + Layout.LINE_SEP);
        sb.append("<th colspan=\"4\">Browser: " + Init.BrowserTypeStart + "</th>" + Layout.LINE_SEP);
        sb.append("</tr>" + Layout.LINE_SEP);
        
        sb.append("<tr>" + Layout.LINE_SEP);
        sb.append("<th colspan=\"4\">Quickr Domino Server: " + Init.QuickrURLStart + "</th>" + Layout.LINE_SEP);
        sb.append("</tr>" + Layout.LINE_SEP);
	    
	    sb.append("</tr>" +
	    		"<th>Status</th>" +
	    		"<th colspan=\"3\">Scenario</th>" +
	    		"</tr><br></br>" + Layout.LINE_SEP);
		
	}
	
	public void addStatus(String name, boolean status) throws IOException {
		
		num++;
		
		if(status){
			
			pass++;
			
			sb.append("<tr><td><font color=\"green\"><strong>Pass</strong></font></td>" +
					"<td colspan=\"3\" ><a href=\"file:" + day + File.separator + time + "_" + name + ".html" + "\">" + name + "</a></td></tr>" + Layout.LINE_SEP);
			
		}else{
			
			fail++;
			
			sb.append("<tr><td><font color=\"red\"><strong>Fail</strong></font></td>" +
					"<td colspan=\"3\" ><a href=\"file:" + day + File.separator + time + "_" + name + ".html" + "\">" + name + "</a><td></tr>" + Layout.LINE_SEP);
			
		}
		
		if(num == TestAll.count)finish();
		
	}
	
	public void finish() throws IOException{ 
		
		sb.append("</table>" + Layout.LINE_SEP);
		
		sb.append("<br>&nbsp;</br><br>&nbsp;</br><br>&nbsp;</br><br>&nbsp;</br>" + Layout.LINE_SEP);
		
		sb.append("</style></head>" +
	    		"<body bgcolor=\"#FFFFFF\" topmargin=\"6\" leftmargin=\"6\">" +
	    				"<table cellspacing=\"0\" cellpadding=\"4\" border=\"1\" bordercolor=\"#224466\" width=\"100%\">" +
	    						"<tr><tr>" + Layout.LINE_SEP);
		
		sb.append("<th colspan=\"4\"><font size=24>Summary</font></th>" + Layout.LINE_SEP);
		sb.append("<tr><td><font color=\"red\"><strong>Total Scenarios</strong></font></td>" +
				"<td colspan=\"3\"><strong>" + num + "</strong></td></tr>" + Layout.LINE_SEP); 
		sb.append("<tr><td><font color=\"red\"><strong>Success</strong></font></td>" +
				"<td colspan=\"3\"><strong>" + pass + "</strong></td></tr>" + Layout.LINE_SEP); 
		sb.append("<tr><td><font color=\"red\"><strong>Fail</strong></font></td>" +
				"<td colspan=\"3\"><strong>" + fail + "</strong></td></tr>" + Layout.LINE_SEP);
		
		DecimalFormat dcmFmt = new DecimalFormat("00.00");
		float s = 0;
		
		s = (float)pass / (float)num * 100;
		
		sb.append("<tr><td><font color=\"red\"><strong>Success Rate</strong></font></td>" +
				"<td colspan=\"3\"><strong>" + dcmFmt.format(s) + "%</strong></td></tr>" + Layout.LINE_SEP); 
		
		finish = System.currentTimeMillis();
		
		long runTime = (finish - start) / 1000, hours, minutes, seconds;		
		hours = runTime / 3600;
		minutes = (runTime % 3600) / 60;
		seconds = runTime % 60;
		
		sb.append("<tr><td><font color=\"red\"><strong>Scenarios Running Time</strong></font></td>" +
				"<td colspan=\"3\"><strong>" + hours + " Hours " + minutes + " Minutes " + seconds + " Secounds " + "</strong></td></tr>" + Layout.LINE_SEP); 
		
		sb.append("</table></body></html>" + Layout.LINE_SEP);
		
		fileAll = new File("logs/Log_" + day + "_Result.html");
		if(!fileAll.exists())fileAll.createNewFile();
		
		printStream = new PrintStream(new FileOutputStream(fileAll));
		
		printStream.println(sb.toString());
		
	}

	public void end() {

		
		
	}
	
}
	

