package config;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import tasks.common.*;
import tasks.ecm.*;
import tasks.library.*;
import tasks.toc.*;

public class Task {

	public QFD_CreatePlaceTask qfd_CreatePlaceTask;
	public QFD_LoginTask qfd_LoginTask;
	public QFD_MembersTask qfd_MembersTask;
	public QFD_LibraryTask qfd_LibraryTask;
	public QFD_PageTask qfd_PageTask;
	public QFD_IndexTask qfd_IndexTask;
	public QFD_ECMTask qfd_ECMTask;
	public QFD_RoomTask qfd_RoomTask;
	public QFD_ForumTask qfd_ForumTask;
	public QFD_CalendarTask qfd_CalendarTask;
	public QFD_TaskTask qfd_TaskTask;
	public QFD_HomeTask qfd_HomeTask;
	public QFD_FolderTask qfd_FolderTask;
	public QFD_PlaceTask qfd_PlaceTask;
	public QFD_AdminTask qfd_AdminTask;
	public QFD_TrashTask qfd_TrashTask;
	public QFD_BlogWikiTask qfd_BlogWikiTask;
	
	public Task(WebDriver driver, Logger log, Users user){
		
		qfd_CreatePlaceTask = new QFD_CreatePlaceTask(driver, log);
		qfd_LoginTask = new QFD_LoginTask(driver, log);
		qfd_MembersTask = new QFD_MembersTask(driver, log, user);
		qfd_LibraryTask = new QFD_LibraryTask(driver, log);
		qfd_PageTask = new QFD_PageTask(driver, log);
		qfd_IndexTask = new QFD_IndexTask(driver, log);
		qfd_ECMTask = new QFD_ECMTask(driver, log);
		qfd_RoomTask = new QFD_RoomTask(driver, log);
		qfd_ForumTask = new QFD_ForumTask(driver, log);
		qfd_CalendarTask = new QFD_CalendarTask(driver, log);
		qfd_TaskTask = new QFD_TaskTask(driver, log);
		qfd_HomeTask = new QFD_HomeTask(driver, log);
		qfd_FolderTask = new QFD_FolderTask(driver, log);
		qfd_PlaceTask = new QFD_PlaceTask(driver, log);
		qfd_AdminTask = new QFD_AdminTask(driver, log);
		qfd_TrashTask = new QFD_TrashTask(driver, log);
		qfd_BlogWikiTask = new QFD_BlogWikiTask(driver, log);
		
	}
	
}
