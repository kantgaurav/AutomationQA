import com.kms.katalon.core.annotation.TearDown

import configs.PageLocatorName
import core.AssertSteps
import core.Browser
import internal.GlobalVariable
import pages.CARApprovalSetupPageSteps
import pages.CARTemplatesPageSteps
import pages.CostCenterPageSteps
import pages.CreateCARPageSteps
import pages.ExecuteContractPageSteps
import pages.ContractDocumentLibraryPageSteps
import pages.HeaderPageSteps
import pages.LocationPageSteps
import pages.LoginPageSteps
import pages.MailTempTopPageSteps
import pages.MenuPageSteps
import pages.PositionPageSteps
import pages.ProvidersPageSteps
import pages.RegionPageSteps
import pages.SpecialtyPageSteps
import pages.TemplateLibraryPageSteps
import pages.ViewCARPageSteps
import pages.YopmailPageSteps
import pages.GmailPageSteps
import core.Logger

'Step 1:'
'Enter the Application URL in browser.'
Browser.start(GlobalVariable.URL)
'ER:'
'System should display CMS Login page.'
LoginPageSteps.verifyPageIsLoaded()

'Step 2:'
'Enter the Username and Password and Click on Login button.'
LoginPageSteps.login(GlobalVariable.APPROVER_MAIL_USER1,  GlobalVariable.COMMON_PW, GlobalVariable.ORG_AUTOMATION, GlobalVariable.ROLE_SYSTEM_ADMIN)
'ER:'
'System should allow the user logged successfully'
HeaderPageSteps.verifyPageIsLoaded()

/**
'Step 3: Prepared data'
'Create a Providers'
MenuPageSteps.selectOrganizationAdminMenu("Providers")
String createdProvidersName = ProvidersPageSteps.createProvider()
'Providers is created successfully'
ProvidersPageSteps.getBaseSteps().verifyMainPopUpHasContent("Provider Added Successfully")
'Create a Position'
MenuPageSteps.selectOrganizationAdminMenu("Position")
String createdPositionName = PositionPageSteps.createPosition()
'Position is created successfully'
PositionPageSteps.getBaseSteps().verifyMainPopUpHasContent("Position saved")
'Create a Specialty'
MenuPageSteps.selectOrganizationAdminMenu("Specialty")
String createdSpecialtyName = SpecialtyPageSteps.createSpecialty()
'Specialty is created successfully'
SpecialtyPageSteps.getBaseSteps().verifyMainPopUpHasContent("Specialty saved")
'Create a Region'
MenuPageSteps.selectOrganizationAdminMenu("Region")
String createdRegionName = RegionPageSteps.createRegion()
'Region is created successfully'
RegionPageSteps.getBaseSteps().verifyMainPopUpHasContent("Region saved")
'Create Location'
MenuPageSteps.selectOrganizationAdminMenu("Location")
String createdLocationName = LocationPageSteps.createLocation(createdRegionName)
'The Location is created'
LocationPageSteps.getBaseSteps().verifyMainPopUpHasContent("Location saved")
'Create Cost Center'
MenuPageSteps.selectOrganizationAdminMenu("Cost Center")
String createdCCName = CostCenterPageSteps.createCostCenter(createdRegionName, createdLocationName)
'The Cost Center is created'
LocationPageSteps.getBaseSteps().verifyMainPopUpHasContent("Cost Center saved")
**/

String createdProvidersName = "FN_63888"
String createdRegionName = "Reg_66881"
String createdLocationName = "	Loc_58478"
String createdCCName = "CC_38314"
String createdSpecialtyName = "Spec_60484"
String createdPositionName = "Pos_32696"
//String createdCARRequest = "Template_98833"


'Create CAR Templates'
MenuPageSteps.selectConntractActionRequestMenu("CAR Templates")
String createdCARRequest = CARTemplatesPageSteps.createCarTemplateForCAR(createdRegionName, createdLocationName, createdCCName, createdSpecialtyName, createdPositionName)
'CAR Templates is created successfully'
CARTemplatesPageSteps.getBaseSteps().verifyMainPopUpHasContent("Template information has been saved.")
'Create a Template Contract'
MenuPageSteps.selectContractDocumentsMenu("Template Library")
String createdTemplateName = TemplateLibraryPageSteps.createTemplateLibrary(createdCARRequest)
'Verify the template librabries can be created'
TemplateLibraryPageSteps.getBaseSteps().verifyMainPopUpHasContent("Document has been saved")

'Step 4:'
'Select CAR Approval Setup '
MenuPageSteps.selectConntractActionRequestMenu("CAR Approval Setup")
'ER:'
'The CAR Approval Setup page is loaded'
CARApprovalSetupPageSteps.verifyPageIsLoaded()

'Step 5:'
'Create a CT Approval Setup with Location, Template and Hierachical'
MenuPageSteps.selectConntractActionRequestMenu("CAR Approval Setup")
String createdCTApprovalName = CARApprovalSetupPageSteps.createApproval("CT Approval", "Location", "Hierarchical",
	createdRegionName, createdLocationName, createdCCName, createdSpecialtyName, createdPositionName, [GlobalVariable.APPROVER_USER1,GlobalVariable.APPROVER_USER2], [GlobalVariable.NOTIFIER_USER1], createdTemplateName,true,true)
'ER:'
'CAR Approval Setup is created successfully'
CARApprovalSetupPageSteps.getBaseSteps().verifyMainPopUpHasContent("Approval information has been saved!")

'Step 6:'
'Select CAR Approval Setup '
MenuPageSteps.selectConntractActionRequestMenu("CAR Approval Setup")
'ER:'
'The CAR Approval Setup page is loaded'
CARApprovalSetupPageSteps.verifyPageIsLoaded()

'Step 7:'
'Create a CAR Approval Setup with Region, Template and Hierachical'
String createdCARApprovalName = CARApprovalSetupPageSteps.createApproval("CAR Approval", "Region", "Hierarchical",
	createdRegionName, createdLocationName, createdCCName, createdSpecialtyName, createdPositionName, [GlobalVariable.APPROVER_USER1], [GlobalVariable.NOTIFIER_USER1], createdCARRequest,true,true)
'ER:'
'CAR Approval Setup is created successfully'
CARApprovalSetupPageSteps.getBaseSteps().verifyMainPopUpHasContent("Approval information has been saved!")

'Step 8:'
'Click to Return to Approvals button'
MenuPageSteps.selectConntractActionRequestMenu("CAR Approval Setup")
'ER:'
'The CAR Approval Setup page is loaded'
CARApprovalSetupPageSteps.verifyPageIsLoaded()

'Step 9:'
'Search the created approval name'
CARApprovalSetupPageSteps.searchByName(createdCTApprovalName)
'ER:'
'The created approval name is diplayed'
CARApprovalSetupPageSteps.verifyDataIsDisplayedAfterSearch("2", createdCTApprovalName)//2 is Create CAR approval name

'Step 10: '
'Create a Car'
MenuPageSteps.selectConntractActionRequestMenu("Create CAR")
CreateCARPageSteps.createCARForApproval(createdRegionName, createdLocationName, createdCCName, createdSpecialtyName, createdPositionName, createdCARRequest, createdProvidersName)
'ER:'
'A success message is displayed'
CreateCARPageSteps.getBaseSteps().verifyMainPopUpHasContent("CAR has been sent for Approval.")

'Step 11:'
'Approval the request'
MenuPageSteps.selectConntractActionRequestMenu("View CARs")
ViewCARPageSteps.search(createdProvidersName)
ViewCARPageSteps.getBaseSteps().clickToCellTableButton("Search_Table", "last()","Approve", PageLocatorName.COMMONLOCATORS_PAGE)//last is Action column

'Step 12: '
'Executing CAR'
String contractDocumentName = ExecuteContractPageSteps.executingCAR(createdProvidersName, createdCARRequest, createdTemplateName)
'ER:'
'Send and Save successfully'
//ExecuteContractPageSteps.getBaseSteps().verifyMainPopUpHasContent("Successfully sent for Approval.")
Thread.sleep(3000)

'Step 13: Check email of approver 1 send:'
'Open gmail email'
Browser.navigateToURL(GlobalVariable.GMAIL_URL)
'ER:'
'The email is sent'
GmailPageSteps.LogintoGmail(GlobalVariable.APPROVER_MAIL_USER1,GlobalVariable.GMAIL_PWD)
GmailPageSteps.SearchOnGmail(createdProvidersName +  ' and "contract approval"' )
'ER:'
'Verify that first approver has received the email'
String expectedApprover1EmailContent = String.format("Contract for %s",createdProvidersName)
Logger.logINFO("Expected Result: " + expectedApprover1EmailContent);
String emailAppr1Content = GmailPageSteps.GetEmailContent(createdProvidersName)
Logger.logINFO("Actual Result: " + emailAppr1Content);
AssertSteps.verifyExpectedResult(emailAppr1Content.contains(expectedApprover1EmailContent))


//code added by sunita so approver 1 can approve and then approver 2 can get the email
'Step 14: Approver1 approves from Email'
GmailPageSteps.ApprovingCAROrContract()
'Step 2:'
'Enter the Username and Password and Click on Login button.'
//LoginPageSteps.login(GlobalVariable.APPROVER_MAIL_USER1,  GlobalVariable.COMMON_PW, GlobalVariable.ORG_AUTOMATION, GlobalVariable.ROLE_SYSTEM_ADMIN)
'ER:'
'System should allow the user logged successfully'
ContractDocumentLibraryPageSteps.verifyPageIsLoaded()
'Do filters'
ContractDocumentLibraryPageSteps.getBaseSteps().clickToControl("Filter_Button", PageLocatorName.CONTRACTDOCUMENTLIBRARY_PAGE)
ContractDocumentLibraryPageSteps.filterWith(createdRegionName, createdLocationName, createdCCName, createdSpecialtyName, createdPositionName)
'View and Approve the document'
ContractDocumentLibraryPageSteps.clickOnViewButton(contractDocumentName)
ContractDocumentLibraryPageSteps.getBaseSteps().waitForProgressBarDisappear()
'Approve the document'
ContractDocumentLibraryPageSteps.clickOnTabName("Signing Document")
ContractDocumentLibraryPageSteps.getBaseSteps().clickToControl("Modal_Content_Approve_Contract_Document_Button", PageLocatorName.COMMONLOCATORS_PAGE)
ContractDocumentLibraryPageSteps.getBaseSteps().waitForProgressBarDisappear()
'ER:'
'The document is approved'
String approveStatus = ContractDocumentLibraryPageSteps.getStatusDocument(contractDocumentName)
AssertSteps.verifyExpectedResult(approveStatus.equals("Pending For Approval"),String.format("The status is %s",approveStatus),String.format("The status is %s",approveStatus))
//end of code added by sunita

'Step 15: Check email of approver 2 send:'
'Open gmail email'
Browser.navigateToURL(GlobalVariable.GMAIL_URL)
GmailPageSteps.GmailLogoutAndSignBackiframe()
'ER:'
'Login to gmail email using second approver'
GmailPageSteps.LogintoGmail(GlobalVariable.APPROVER_MAIL_USER2,GlobalVariable.GMAIL_PWD)
GmailPageSteps.SearchOnGmail(createdProvidersName)
'ER:'
'Verify that second approver has received the email'
String expectedApprover2EmailContent = String.format("Contract for %s",createdProvidersName)
Logger.logINFO("Expected Result: " + expectedApprover2EmailContent);
String emailAppr2Content = GmailPageSteps.GetEmailContent(createdProvidersName)
Logger.logINFO("Actual Result: " + emailAppr2Content);
AssertSteps.verifyExpectedResult(emailAppr2Content.contains(expectedApprover2EmailContent))
GmailPageSteps.GmailLogoutAndSignBackiframe()


'Step 15: Check email of notifier 1:'
'Open gmail email and login as the notifier'
GmailPageSteps.LogintoGmail(GlobalVariable.NOTIFIER_MAIL_USER1,GlobalVariable.GMAIL_PWD)
'ER:'
'The preAction email is sent'
GmailPageSteps.SearchOnGmail(createdProvidersName + " and CAR Created")
String expectedPreActionEmailContent = String.format("The CAR for %s",createdProvidersName)
Logger.logINFO("Expected Result: " + expectedPreActionEmailContent);
String preActionEmailContent = GmailPageSteps.GetEmailContent(createdProvidersName)
Logger.logINFO("Actual Result: " + preActionEmailContent);
AssertSteps.verifyExpectedResult(preActionEmailContent.contains(expectedPreActionEmailContent))

'ER:'
'The postAction email is sent'
GmailPageSteps.SearchOnGmail(createdProvidersName + " and CAR Approved")
String expectedPosActionEmailContent = String.format("The CAR for %s",createdProvidersName)
Logger.logINFO("Expected Result: " + expectedPosActionEmailContent);
String posActionEmailContent = GmailPageSteps.GetEmailContent(createdProvidersName)
Logger.logINFO("Actual Result: " + posActionEmailContent);
AssertSteps.verifyExpectedResult(posActionEmailContent.contains(expectedPosActionEmailContent))
Browser.navigateToURL(GlobalVariable.URL)
/**
'Step 11: Clean up data'
'Delete Template Libary'
MenuPageSteps.selectConntractActionRequestMenu("Contract Document Library") //refresh page
MenuPageSteps.selectContractDocumentsMenu("Template Library")
TemplateLibraryPageSteps.deleteTemplate(createdRegionName, createdLocationName, createdCCName, createdSpecialtyName, createdPositionName)
'Delete the created CAR'
MenuPageSteps.selectConntractActionRequestMenu("Create CAR") //Refresh page
MenuPageSteps.selectConntractActionRequestMenu("View CARs")
ViewCARPageSteps.deleteACar(createdProvidersName)
'Delete the CT approval'
MenuPageSteps.selectConntractActionRequestMenu("CAR Templates") //Refresh page
MenuPageSteps.selectConntractActionRequestMenu("CAR Approval Setup")
CARApprovalSetupPageSteps.deleteConfiguredApprovals(createdCTApprovalName)
'Delete the CAR approval'
MenuPageSteps.selectConntractActionRequestMenu("CAR Templates") //Refresh page
MenuPageSteps.selectConntractActionRequestMenu("CAR Approval Setup")
CARApprovalSetupPageSteps.deleteConfiguredApprovals(createdCARApprovalName)
'Delete the created CAR Templates'
MenuPageSteps.selectConntractActionRequestMenu("CAR Templates")
CARTemplatesPageSteps.searchAndDelete(createdCARRequest)
'Delete the created Position'
MenuPageSteps.selectOrganizationAdminMenu("Position")
PositionPageSteps.searchAndDelete(createdPositionName)
'Delete the created Specialty'
MenuPageSteps.selectOrganizationAdminMenu("Specialty")
SpecialtyPageSteps.searchAndDelete(createdSpecialtyName)
'Delete the created Cost Center'
MenuPageSteps.selectOrganizationAdminMenu("Cost Center")
CostCenterPageSteps.searchAndDelete(createdCCName)
'Delete the created location'
MenuPageSteps.selectOrganizationAdminMenu("Location")
LocationPageSteps.searchAndDelete(createdLocationName)
'Delete the created region'
MenuPageSteps.selectOrganizationAdminMenu("Region")
RegionPageSteps.searchAndDelete(createdRegionName)
**/
@TearDown
def closeBrowser(){
Browser.quitDriver();
}



