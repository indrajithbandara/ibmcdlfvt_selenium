Global Const $MyDocument_EN="My Documents"	
Global Const $NoRightToAccessTitle_EN="no right"
Global Const $NewCreateFolderTitle_EN="New Folder"
Global Const $FolderExistTitle_EN="already exists"
Global Const $Separator=Chr(9)
;for set library properties
Global Const $LibProperty="- Properties"
Global Const $LibPreferences="Preferences"
Global Const $LibPreferencesText="Show Views"
Global Const $LibPropertyText="Show views";"This local directory stores the files being transferred"
Global Const $DraftView="Drafts Views"
Global Const $PersonalView="Personal Views"
Global Const $SharedView="Shared Views"

Global Const $addPlaceTitle="Add Team Places to IBM Connectors"
Global Const $addToPlace="Add To Place"
Global Const $openFromPlace="Open From Place"
Global Const $addPlaceText="Choose or enter a Lotus Quickr server"
Global Const $selectLibrary="Select from the list, then click Finish"


Global Const $FolderInvalidText_EN="Invalid characters"
Global Const $DeleteText_EN="delete"
Global Const $DeleteLibText_EN="Are you sure you want to delete"; used for delete the content of library
Global Const $HaveAccessText_EN="you might not have access to the content"
Global Const $MaxLength_EN="InvalidPathSegmentLengthException"
Global Const $MaxLength_CN="InvalidPathSegmentLengthException"
Global Const $ServerError_EN="Error from server"
Global Const $ReNameText_EN="Rename"
Global Const $Path_EN="Path"; when you rename a folder ,then it will appear in the rename dialog
Global Const $LoginText_EN="Login"
Global Const $DocumentAdd="Select Document Add Option"
Global Const $LOTUSQUICKR="Lotus Quickr"
Global Const $LOTUSQUICKRNew="Team Places"
Global Const $NullString="Null"
Global Const $Copying_EN="Copying"
Global Const $Upload_EN="Upload"
Global Const $ConfirmUpload_EN="Confirm Upload Files"
Global Const $UploadComplete_EN="Upload complete"
Global Const $FileExists_EN="A file with the same name already exists"
Global Const $PluginDialog_EN="Document Manager Plug-in"
Global Const $Moving_EN="Moving"
Global Const $NewCreateWord_EN="New Microsoft Word Document"
Global Const $MicrosoftWord_EN="Microsoft Word"
Global Const $NewCreateTxt_EN="New Text Document"
Global Const $CheckInGood_EN="The document has been checked in"
Global Const $preCheckOut_EN="Would you like to work with the current open document"
Global Const $CheckOutGood_EN="Document Check out was successful"

Global Const $BeginInstallText_EN="File Download - Security Warning"
Global Const $BeginInstallTextFF_EN="Opening qkrconn.exe"

Global Const $InstallDITitle_EN="Lotus Quickr connectors - InstallShield Wizard"

Global Const $InstallDomino_EN="Lotus Domino Installer"
Global Const $LotusDominoServer_EN="Lotus Domino Server"
Global Const $InstallTitle="Lotus Quickr connectors - InstallShield Wizard"
Global Const $step1Text = "Welcome to the InstallShield Wizard for Lotus Quickr Connectors"
Global Const $step2Text = "License Agreement"
Global Const $step3Text = "Custom Setup"
Global Const $step4Text = "Ready to Install the Program"
Global Const $step5Text = "Installing Lotus Quickr Connectors"
Global Const $step6Text = "InstallShield Wizard Completed"
Global Const $nextText = "Next"
;Types in DI
Global Const $LibraryType=0
Global Const $ViewType=-1
Global Const $FileType=1
Global Const $FolderType=2
#comments-start
Global Const $FileName_EN="Name"

Global Const $FileDescription_EN="Description"

Global Const $FileSize_EN="Size"

Global Const $FileAuthor_EN="Added by"
Global Const $FileDate_EN="Modified"
#comments-end
Global Const $ResponseTime=60

Global Const $Success=0
Global Const $Failed=-1
Global Const $NoTitle=-2
Global Const $NoEnoughParameter=1

Global Const $NoResponse=17
;create folder
Global const $PathError=2
Global const $NoRightError=3
Global const $ExistedError=4
Global Const $InvalidCharError=5
Global Const $MaxLengthError=6
Global Const $ImpossibleError=7
;delete folder
Global Const $FileNotExisted=8
Global Const $NoDeleteDlg=9
Global Const $DeletedButExistedYet=10
;rename folder
Global Const $SameNameError=11
Global Const $ServerError=12
Global Const $NoRenameDlg=13
;drage folder
Global Const $DragedButNoFound=14
;open folder
Global Const $NoRootWindow=15
Global Const $NoFolderWindow=16
;copy and paste fodler and file 
Global const $FileAlreadyExisted=17
;sort the folder
Global Const $NoSuchSortType=18