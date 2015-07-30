package junit;

import java.io.IOException;

import testcases.uat.*;
import util.Times;

import config.Init;

import junit.extensions.TestSetup;
import junit.framework.*;
import log.LogFile;

public class Test0 extends TestCase {
	
	public static int count = 0;
	
	public static Test suite() {
		
		Init init = new Init();
		
		LogFile logFile = new LogFile();
		
		TestSuite suite = new TestSuite("test0");			
		
		/*
		 * Sripts running first
		 */

/////////////WU JUN///////////////
//		suite.addTest(new GUAT_PlaceCatalog());
/////////////WU JUN///////////////
//		suite.addTest(new GUAT_ECM());
//		suite.addTest(new GUAT_ECMRoom());
	
/////////////WU JUN///////////////
//
//		/*
//		 * Sripts only for IE
//		 */
//		
//		suite.addTest(new GUAT_Forms());
//
//		
//		/*
//		 * Sripts using AutoIT
//		 */
//		
//		suite.addTest(new GUAT_Feeds());
//		suite.addTest(new GUAT_Page());
//		suite.addTest(new GUAT_PageACL());
//		suite.addTest(new GUAT_Statistics());
//		
//		/*
//		 * Sripts using Admin Console
//		 */
//		
//		suite.addTest(new GUAT_Authentication());
//		suite.addTest(new GUAT_PlaceSecurity());
//		suite.addTest(new GUAT_PlacesList());
//		suite.addTest(new GUAT_Policy());
//		suite.addTest(new GUAT_SiteAdmin());
//		suite.addTest(new GUAT_TemplateAdmin());
//		suite.addTest(new GUAT_Templates());
//		
//		/*
//		 * Sripts needing switching window
//	     */
//		
//		suite.addTest(new GUAT_Customize());
//		suite.addTest(new GUAT_StandardPlaceBasic());
//		suite.addTest(new GUAT_Links());		
//		
//		/*
//		 * Other scripts
//		 */
//		
//		suite.addTest(new GUAT_EditorBasic());
//		suite.addTest(new GUAT_EditorIconCheck());
//		suite.addTest(new GUAT_Blog());
//		suite.addTest(new GUAT_Calendar());
//		suite.addTest(new GUAT_Folders());
//		suite.addTest(new GUAT_FolderACL());
//		suite.addTest(new GUAT_Forum());
//		suite.addTest(new GUAT_Framework());
//		suite.addTest(new GUAT_Home());
//		suite.addTest(new GUAT_ImportedPage());
//		suite.addTest(new GUAT_Index());
//		suite.addTest(new GUAT_LiteEditorIconCheck());
//		suite.addTest(new GUAT_MemberPicker());
//		suite.addTest(new GUAT_Membership());
//		suite.addTest(new GUAT_Minitest());
//		suite.addTest(new GUAT_Room());
//		suite.addTest(new GUAT_RoomTrash());
//		suite.addTest(new GUAT_SpellCheck());
//		suite.addTest(new GUAT_Trash());
//		suite.addTest(new GUAT_Upload());
//		suite.addTest(new GUAT_WhatsNew());
//		suite.addTest(new GUAT_Wiki());
/////////////WU JUN///////////////

//		suite.addTest(new GUAT_Task());
//		suite.addTest(new GUAT_CopyMove());
//		suite.addTest(new GUAT_PageACL());
		
		
		//mary
//     	suite.addTest(new GUAT_Authentication());
//		suite.addTest(new GUAT_Blog());
//		suite.addTest(new GUAT_Calendar());
		suite.addTest(new GUAT_CopyMove());
//		suite.addTest(new GUAT_Customize());
		suite.addTest(new GUAT_EditorBasic());
//		suite.addTest(new GUAT_EditorIconCheck());
		suite.addTest(new GUAT_FolderACL());
//		suite.addTest(new GUAT_Folders());
//		suite.addTest(new GUAT_Forms());
//		suite.addTest(new GUAT_Forum());
//		suite.addTest(new GUAT_Framework());
//		suite.addTest(new GUAT_Home());
//		suite.addTest(new GUAT_ImportedPage());
		suite.addTest(new GUAT_Index());
		suite.addTest(new GUAT_Links());
//		suite.addTest(new GUAT_LiteEditorIconCheck());
		suite.addTest(new GUAT_MemberPicker());
//		suite.addTest(new GUAT_Membership());
//		suite.addTest(new GUAT_Page());
//		suite.addTest(new GUAT_PageACL());
		suite.addTest(new GUAT_PlaceCatalog());
		suite.addTest(new GUAT_PlaceSecurity());
//		suite.addTest(new GUAT_PlacesList());
//		suite.addTest(new GUAT_Policy());
//		suite.addTest(new GUAT_Room());
//		suite.addTest(new GUAT_RoomTrash());
//		suite.addTest(new GUAT_SiteAdmin());
//		suite.addTest(new GUAT_SpellCheck());
		suite.addTest(new GUAT_StandardPlaceBasic());
//		suite.addTest(new GUAT_Statistics());
//		suite.addTest(new GUAT_Task());
		suite.addTest(new GUAT_TemplateAdmin());
		suite.addTest(new GUAT_Templates());
		suite.addTest(new GUAT_Trash());
//		suite.addTest(new GUAT_Upload());
//		suite.addTest(new GUAT_WhatsNew());
//		suite.addTest(new GUAT_Wiki());
//		suite.addTest(new GUAT_Feeds());
		
		
		
		
		
		try {
			
			init.init();
			logFile.start();
			
		} catch (IOException e) {
			
		}
		
		TestAll.count = suite.countTestCases();
		
		TestSetup wrapper = new TestSetup(suite) {
      
            protected void tearDown() {
            	
                oneTimeTearDown();
                
            }
            
        };
        
		return wrapper;
		
	}
	
	public static void oneTimeTearDown() {
		
		Times.Times ++;
		
	}
	
}
