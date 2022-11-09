import com.kms.katalon.core.annotation.TearDown

import configs.PageLocatorName
import core.AssertSteps
import core.Browser
import internal.GlobalVariable
import pages.ContractDocumentLibraryPageSteps
import pages.DashboardPageSteps
import pages.HeaderPageSteps
import pages.LoginPageSteps
import pages.ViewCARPageSteps

'Step 1:'
'Enter the Application URL in browser.'
Browser.start(GlobalVariable.URL)
'ER:'
'System should display VMS Login page.'
LoginPageSteps.verifyPageIsLoaded()

'Step 2:'
'Enter the Username and Password and Click on Login button.'
LoginPageSteps.login(GlobalVariable.APPROVER_MAIL_USER1, GlobalVariable.COMMON_PW, GlobalVariable.ORG_AUTOMATION, GlobalVariable.ROLE_SYSTEM_ADMIN)
'ER:'
'System should allow the user logged successfully'
HeaderPageSteps.verifyPageIsLoaded()

'Step 3:'
'ER:'
'The forms are displayed: CAR Status, Contract Status, Assigned to me, My Report, Progress Tracker'
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("CAR_Status_Form", PageLocatorName.DASHBOARD_PAGE)
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Contract_Status_Form", PageLocatorName.DASHBOARD_PAGE)
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Assigned_To_Me_Form", PageLocatorName.DASHBOARD_PAGE)
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("My_Reports_Form", PageLocatorName.DASHBOARD_PAGE)
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Progress_Tracker_Form", PageLocatorName.DASHBOARD_PAGE)

'Step 4:'
'Click to Created chart in CAR Status'
String wd = Browser.getCurrentWindowHandle()
DashboardPageSteps.getBaseSteps().clickToOffsetByAction("CAR_Status_Form_Created_Chart_Label",24, PageLocatorName.DASHBOARD_PAGE)
'ER:'
'Contract Action Request page is loaded and Filter is applied Created status'
Browser.switchToNewOpenedWindow()
ViewCARPageSteps.getBaseSteps().verifyPageTitleIsCorrect("Contract Action Requests")
//ViewCARPageSteps.getBaseSteps().verifyOptionIsDefaultInComboBox("Filter_Status_Combobox", "Created", PageLocatorName.VIEWCAR_PAGE)
Browser.switchToWindow(wd)


'Step 5:'
'Click to first space in Contract Status'
String statusLabel = DashboardPageSteps.getFirstStatusLabel()
DashboardPageSteps.getBaseSteps().clickToOffsetByAction("Contract_Status_Form_First_Label",35, PageLocatorName.DASHBOARD_PAGE)
Thread.sleep(3000)

'ER:'
'Contract Documents Library page is loaded and status is match with dashboard'
Browser.switchToNewOpenedWindow()
ContractDocumentLibraryPageSteps.verifyPageIsLoaded()
String statusDocument = ContractDocumentLibraryPageSteps.getBaseSteps().getTextFromControl("Status_First_Item_Label", PageLocatorName.CONTRACTDOCUMENTLIBRARY_PAGE)
AssertSteps.verifyExpectedResult(statusDocument.equals(statusLabel),String.format("The status is %s",statusDocument),String.format("The status is %s",statusLabel))
Browser.switchToWindow(wd)			

'Step 6: '
'Click to the first progress bar in Progress Tracker'
String name = DashboardPageSteps.getBaseSteps().getTextFromControl("Progress_Tracker_Fist_Contract_Name_Label", PageLocatorName.DASHBOARD_PAGE)
String contractName = name.substring(23, name.length())
String statusContract = DashboardPageSteps.getBaseSteps().getTextFromControl("Progress_Tracker_First_Status_Label", PageLocatorName.DASHBOARD_PAGE)
statusContract = statusContract.substring(0, statusContract.indexOf(' '))
ContractDocumentLibraryPageSteps.getBaseSteps().clickToControl("Progress_Tracker_First_Progress_Bar", PageLocatorName.DASHBOARD_PAGE)
'ER:'
Browser.switchToNewOpenedWindow()
'Contract Documents Library page is loaded and status is match with dashboard'
ContractDocumentLibraryPageSteps.verifyPageIsLoaded()
String statusAfterClickingTracker = ContractDocumentLibraryPageSteps.getBaseSteps().getTextFromControl("Status_First_Item_Label", PageLocatorName.CONTRACTDOCUMENTLIBRARY_PAGE)
String contractNameAfterClickingTracker = ContractDocumentLibraryPageSteps.getBaseSteps().getTextFromControl("Document_Name_First_Item_Label", PageLocatorName.CONTRACTDOCUMENTLIBRARY_PAGE)
AssertSteps.verifyExpectedResult(statusAfterClickingTracker.contains(statusContract),String.format("The status is %s",statusAfterClickingTracker),String.format("%s is not match with : %s",statusAfterClickingTracker,statusContract))
AssertSteps.verifyExpectedResult(contractNameAfterClickingTracker.contains(contractName),String.format("The contract name is %s, contains the name: %s",contractNameAfterClickingTracker, contractName),String.format("%s is not contain name : %s",contractNameAfterClickingTracker,contractName))
Browser.switchToWindow(wd)

@TearDown
def closeBrowser(){
Browser.quitDriver();
}
