package config;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Users {
	
	public static String PlaceName;
	
	public String LDAP, LDAPName;
	
	public String UIDCreator,CreatorPW, CreatorSearch, FilterSearch;
	
	public String ReaderInputName, ReaderTitle, ReaderName, ReaderPW, ReaderSearch;
	public String AuthorInputName, AuthorTitle, AuthorName, AuthorPW, AuthorSearch;
	public String EditorInputName, EditorTitle, EditorName, EditorPW, EditorSearch;
	public String ManagerInputName, ManagerTitle, ManagerName, ManagerPW, ManagerSearch;
	public String OwnerInputName, OwnerTitle, OwnerName, OwnerPW, OwnerSearch;
	
	public String Reader1InputName, Reader1Title, Reader1Name, Reader1PW, Reader1Search;
	public String Author1InputName, Author1Title, Author1Name, Author1PW, Author1Search;
	public String Editor1InputName, Editor1Title, Editor1Name, Editor1PW, Editor1Search;
	public String Manager1InputName, Manager1Title, Manager1Name, Manager1PW, Manager1Search;
	public String Owner1InputName, Owner1Title, Owner1Name, Owner1PW, Owner1Search;
	
	public String Reader2InputName, Reader2Title, Reader2Name, Reader2PW, Reader2Search;
	public String Author2InputName, Author2Title, Author2Name, Author2PW, Author2Search;
	public String Editor2InputName, Editor2Title, Editor2Name, Editor2PW, Editor2Search;
	public String Manager2InputName, Manager2Title, Manager2Name, Manager2PW, Manager2Search;
	public String Owner2InputName, Owner2Title, Owner2Name, Owner2PW, Owner2Search;
	
	public String Reader3InputName, Reader3Title, Reader3Name, Reader3PW, Reader3Search;
	public String Author3InputName, Author3Title, Author3Name, Author3PW, Author3Search;
	public String Editor3InputName, Editor3Title, Editor3Name, Editor3PW, Editor3Search;
	public String Manager3InputName, Manager3Title, Manager3Name, Manager3PW, Manager3Search;
	public String Owner3InputName, Owner3Title, Owner3Name, Owner3PW, Owner3Search;
	
	public String Reader4InputName, Reader4Title, Reader4Name, Reader4PW, Reader4Search;
	public String Author4InputName, Author4Title, Author4Name, Author4PW, Author4Search;
	public String Editor4InputName, Editor4Title, Editor4Name, Editor4PW, Editor4Search;
	public String Manager4InputName, Manager4Title, Manager4Name, Manager4PW, Manager4Search;
	public String Owner4InputName, Owner4Title, Owner4Name, Owner4PW, Owner4Search;
	
	public String GroupInputName, GroupName, GroupUserName, GroupUserPW, AdminName, AdminPW;
	
	public String PageName, PageDraftName, RoomName, Desc, LinkName, CusLibName,URLIBM, FolderName, SubFolderName, ListName, ListItemTitle;
	public String ForumName, TopicName, EventName, TaskName;
	
	public String WordPath, WordName, Word1Path, Word1Name, ExcelPath, ExcelName, ImgPath, ImgName;
	
	public String ECMURL, ECMUser, ECMPW, ECMLibName;
	
	public void setUsers(){
		
		Properties props = new Properties();
		try {
			
			InputStream in = new BufferedInputStream (new FileInputStream("config/ServerConfig.properties"));
			props.load(in);
			
			ECMUser = props.getProperty("sECMUserID");
			ECMPW = props.getProperty("sECMPW");
			ECMURL = props.getProperty("sECMURL");
			LDAP = (props.getProperty("sLDAPType")) + ".properties";
			
	    }catch (Exception e) {
	    	
	    }
		
	    PlaceName = "";
	    PageName = "Test Page"; 
	    PageDraftName = "Test Page Draft";
	    Desc = "Test Text";
	    LinkName = "Test Link";
	    FolderName = "Test Folder";
	    CusLibName = "Test Lib";
	    ECMLibName = "ECM Lib";
	    ListName = "Test List";
	    ListItemTitle = "Item";
	    RoomName = "Test Room";
	    ForumName = "Test Forum";
	    TopicName = "Test Topic";
	    EventName = "TestEvent";
	    TaskName = "TestTask";
	    URLIBM = "http://www.ibm.com";
	    SubFolderName = "Test Sub Folder";
	    
	    WordPath = new File("SupportFiles/Word_BVT_Doc1.doc").getAbsolutePath();
	    Word1Path = new File("SupportFiles/WordDocs/1ExecutiveSummary.doc").getAbsolutePath();
	    ExcelPath = new File("SupportFiles/Excel_BVT.xls").getAbsolutePath();
	    ImgPath = new File("SupportFiles/Winter.jpg").getAbsolutePath();
	    
	    WordName = "Word_BVT_Doc1";
	    Word1Name = "1ExecutiveSummary";
	    ExcelName = "Excel_BVT";
	    ImgName = "Winter";
	    
	    try {
			
			InputStream in = new BufferedInputStream (new FileInputStream("config/" + LDAP));
			props.load(in);
			
			LDAPName = props.getProperty("sLDAPName");
			
			UIDCreator = props.getProperty("sUIDCreator");
			CreatorPW = props.getProperty("sCreatorPW");
			CreatorSearch = props.getProperty("sCreatorSearch");
			FilterSearch = props.getProperty("sFilterSearch");
			
			AdminName = props.getProperty("sAdminName");
			AdminPW = props.getProperty("sAdminPW");
			
			ReaderName = props.getProperty("sReaderName");
			ReaderPW = props.getProperty("sReaderPW");
			ReaderInputName = props.getProperty("sReaderInputName");
			ReaderTitle = props.getProperty("sReaderTitle");
			ReaderSearch = props.getProperty("sReaderSearch");
			AuthorName = props.getProperty("sAuthorName");
			AuthorPW = props.getProperty("sAuthorPW");
			AuthorInputName = props.getProperty("sAuthorInputName");
			AuthorTitle = props.getProperty("sAuthorTitle");
			AuthorSearch = props.getProperty("sAuthorSearch");
			EditorName = props.getProperty("sEditorName");
			EditorPW = props.getProperty("sEditorPW");
			EditorInputName = props.getProperty("sEditorInputName");
			EditorTitle = props.getProperty("sEditorTitle");
			EditorSearch = props.getProperty("sEditorSearch");
			ManagerName = props.getProperty("sManagerName");
			ManagerPW = props.getProperty("sManagerPW");
			ManagerInputName = props.getProperty("sManagerInputName");
			ManagerTitle = props.getProperty("sManagerTitle");
			ManagerSearch = props.getProperty("sManagerSearch");
			OwnerName = props.getProperty("sOwnerName");
			OwnerPW = props.getProperty("sOwnerPW");
			OwnerInputName = props.getProperty("sOwnerInputName");
			OwnerTitle = props.getProperty("sOwnerTitle");
			OwnerSearch = props.getProperty("sOwnerSearch");
			
			Reader1Name = props.getProperty("sReader1Name");
			Reader1PW = props.getProperty("sReader1PW");
			Reader1InputName = props.getProperty("sReader1InputName");
			Reader1Title = props.getProperty("sReader1Title");
			Reader1Search = props.getProperty("sReader1Search");
			Author1Name = props.getProperty("sAuthor1Name");
			Author1PW = props.getProperty("sAuthor1PW");
			Author1InputName = props.getProperty("sAuthor1InputName");
			Author1Title = props.getProperty("sAuthor1Title");
			Author1Search = props.getProperty("sAuthor1Search");
			Editor1Name = props.getProperty("sEditor1Name");
			Editor1PW = props.getProperty("sEditor1PW");
			Editor1InputName = props.getProperty("sEditor1InputName");
			Editor1Title = props.getProperty("sEditor1Title");
			Editor1Search = props.getProperty("sEditor1Search");
			Manager1Name = props.getProperty("sManager1Name");
			Manager1PW = props.getProperty("sManager1PW");
			Manager1InputName = props.getProperty("sManager1InputName");
			Manager1Title = props.getProperty("sManager1Title");
			Manager1Search = props.getProperty("sManager1Search");
			Owner1Name = props.getProperty("sOwner1Name");
			Owner1PW = props.getProperty("sOwner1PW");
			Owner1InputName = props.getProperty("sOwner1InputName");
			Owner1Title = props.getProperty("sOwner1Title");
			Owner1Search = props.getProperty("sOwner1Search");
			
			Reader2Name = props.getProperty("sReader2Name");
			Reader2PW = props.getProperty("sReader2PW");
			Reader2InputName = props.getProperty("sReader2InputName");
			Reader2Title = props.getProperty("sReader2Title");
			Reader2Search = props.getProperty("sReader2Search");
			Author2Name = props.getProperty("sAuthor2Name");
			Author2PW = props.getProperty("sAuthor2PW");
			Author2InputName = props.getProperty("sAuthor2InputName");
			Author2Title = props.getProperty("sAuthor2Title");
			Author2Search = props.getProperty("sAuthor2Search");
			Editor2Name = props.getProperty("sEditor2Name");
			Editor2PW = props.getProperty("sEditor2PW");
			Editor2InputName = props.getProperty("sEditor2InputName");
			Editor2Title = props.getProperty("sEditor2Title");
			Editor2Search = props.getProperty("sEditor2Search");
			Manager2Name = props.getProperty("sManager2Name");
			Manager2PW = props.getProperty("sManager2PW");
			Manager2InputName = props.getProperty("sManager2InputName");
			Manager2Title = props.getProperty("sManager2Title");
			Manager2Search = props.getProperty("sManager2Search");
			Owner2Name = props.getProperty("sOwner2Name");
			Owner2PW = props.getProperty("sOwner2PW");
			Owner2InputName = props.getProperty("sOwner2InputName");
			Owner2Title = props.getProperty("sOwner2Title");
			Owner2Search = props.getProperty("sOwner2Search");
			
			Reader3Name = props.getProperty("sReader3Name");
			Reader3PW = props.getProperty("sReader3PW");
			Reader3InputName = props.getProperty("sReader3InputName");
			Reader3Title = props.getProperty("sReader3Title");
			Reader3Search = props.getProperty("sReader3Search");
			Author3Name = props.getProperty("sAuthor3Name");
			Author3PW = props.getProperty("sAuthor3PW");
			Author3InputName = props.getProperty("sAuthor3InputName");
			Author3Title = props.getProperty("sAuthor3Title");
			Author3Search = props.getProperty("sAuthor3Search");
			Editor3Name = props.getProperty("sEditor3Name");
			Editor3PW = props.getProperty("sEditor3PW");
			Editor3InputName = props.getProperty("sEditor3InputName");
			Editor3Title = props.getProperty("sEditor3Title");
			Editor3Search = props.getProperty("sEditor3Search");
			Manager3Name = props.getProperty("sManager3Name");
			Manager3PW = props.getProperty("sManager3PW");
			Manager3InputName = props.getProperty("sManager3InputName");
			Manager3Title = props.getProperty("sManager3Title");
			Manager3Search = props.getProperty("sManager3Search");
			Owner3Name = props.getProperty("sOwner3Name");
			Owner3PW = props.getProperty("sOwner3PW");
			Owner3InputName = props.getProperty("sOwner3InputName");
			Owner3Title = props.getProperty("sOwner3Title");
			Owner3Search = props.getProperty("sOwner3Search");
			
			Reader4Name = props.getProperty("sReader4Name");
			Reader4PW = props.getProperty("sReader4PW");
			Reader4InputName = props.getProperty("sReader4InputName");
			Reader4Title = props.getProperty("sReader4Title");
			Reader4Search = props.getProperty("sReader4Search");
			Author4Name = props.getProperty("sAuthor4Name");
			Author4PW = props.getProperty("sAuthor4PW");
			Author4InputName = props.getProperty("sAuthor4InputName");
			Author4Title = props.getProperty("sAuthor4Title");
			Author4Search = props.getProperty("sAuthor4Search");
			Editor4Name = props.getProperty("sEditor4Name");
			Editor4PW = props.getProperty("sEditor4PW");
			Editor4InputName = props.getProperty("sEditor4InputName");
			Editor4Title = props.getProperty("sEditor4Title");
			Editor4Search = props.getProperty("sEditor4Search");
			Manager4Name = props.getProperty("sManager4Name");
			Manager4PW = props.getProperty("sManager4PW");
			Manager4InputName = props.getProperty("sManager4InputName");
			Manager4Title = props.getProperty("sManager4Title");
			Manager4Search = props.getProperty("sManager4Search");
			Owner4Name = props.getProperty("sOwner4Name");
			Owner4PW = props.getProperty("sOwner4PW");
			Owner4InputName = props.getProperty("sOwner4InputName");
			Owner4Title = props.getProperty("sOwner4Title");
			Owner4Search = props.getProperty("sOwner4Search");
			
			GroupInputName = props.getProperty("sGroupInputName");
			GroupName = props.getProperty("sGroupName");
			GroupUserName = props.getProperty("sGroupUserName");
			GroupUserPW = props.getProperty("sGroupUserPW");
			
	    }catch (Exception e) {
	    	
	    }
	    
	}
	
}
