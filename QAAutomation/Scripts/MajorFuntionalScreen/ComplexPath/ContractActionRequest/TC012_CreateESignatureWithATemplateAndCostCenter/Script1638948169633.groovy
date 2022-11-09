import com.kms.katalon.core.annotation.TearDown

import configs.PageLocatorName
import core.AssertSteps
import core.Browser
import internal.GlobalVariable
import pages.CARApprovalSetupPageSteps
import pages.CARTemplatesPageSteps
import pages.ContractDocumentLibraryPageSteps
import pages.CostCenterPageSteps
import pages.CreateCARPageSteps
import pages.ExecuteContractPageSteps
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
/**


String createdProvidersName = "FN_63888"
String createdRegionName = "Reg_66881"
String createdLocationName = "	Loc_58478"
String createdCCName = "CC_38314"
String createdSpecialtyName = "Spec_60484"
String createdPositionName = "Pos_32696"
//String createdCARRequest = "Template_98833"
**/

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
'Create a CT Approval Setup with Region, Template and Hierachical'
MenuPageSteps.selectConntractActionRequestMenu("CAR Approval Setup")
String createdCTApprovalName = CARApprovalSetupPageSteps.createApproval("CT Approval", "Region", "Hierarchical",
	createdRegionName, createdLocationName, createdCCName, createdSpecialtyName, createdPositionName, [GlobalVariable.APPROVER_USER1], [GlobalVariable.NOTIFIER_USER1], createdTemplateName,true,true)
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

'Step 8: '
'Create a E-Signature Approvals Setup with Cost Center, Template and Hierachical'
MenuPageSteps.selectConntractActionRequestMenu("CAR Approval Setup")
String createdESignApprovalName = CARApprovalSetupPageSteps.createApproval("E-Signature", "Cost Center", "Hierarchical",
	createdRegionName, createdLocationName, createdCCName, createdSpecialtyName, createdPositionName, [GlobalVariable.SIGNER_USER1, GlobalVariable.SIGNER_USER1], [GlobalVariable.NOTIFIER_USER1], createdTemplateName,true,true)
'ER:'
'CAR Approval Setup is created successfully'
CARApprovalSetupPageSteps.getBaseSteps().verifyMainPopUpHasContent("Approval information has been saved!")

'Step 9:'
'Click to Return to Approvals button'
MenuPageSteps.selectConntractActionRequestMenu("CAR Approval Setup")
'ER:'
'The CAR Approval Setup page is loaded'
CARApprovalSetupPageSteps.verifyPageIsLoaded()

'Step 10:'
'Search the created approval name'
CARApprovalSetupPageSteps.searchByName(createdESignApprovalName)
'ER:'
'The created approval name is diplayed'
CARApprovalSetupPageSteps.verifyDataIsDisplayedAfterSearch("2", createdESignApprovalName)//2 is Create CAR approval name

'Step 11: '
'Create a Car'
MenuPageSteps.selectConntractActionRequestMenu("Create CAR")
CreateCARPageSteps.createCARForApproval(createdRegionName, createdLocationName, createdCCName, createdSpecialtyName, createdPositionName, createdCARRequest, createdProvidersName)
'ER:'
'A success message is displayed'
CreateCARPageSteps.getBaseSteps().verifyMainPopUpHasContent("CAR has been sent for Approval.")

'Step 12:'
'Approval the request'
MenuPageSteps.selectConntractActionRequestMenu("View CARs")
ViewCARPageSteps.search(createdProvidersName)
ViewCARPageSteps.getBaseSteps().clickToCellTableButton("Search_Table", "last()","Approve", PageLocatorName.COMMONLOCATORS_PAGE)//last is Action column

'Step 13: '
'Executing CAR'
String contractDocumentName = ExecuteContractPageSteps.executingCAR(createdProvidersName, createdCARRequest, createdTemplateName)
'ER:'
'Send and Save successfully'
//ExecuteContractPageSteps.getBaseSteps().verifyMainPopUpHasContent("Successfully sent for Approval.")
Thread.sleep(3000)


'Step 14: '
'Approving Contract Document'
MenuPageSteps.selectContractDocumentsMenu("Contract Document Library")
ContractDocumentLibraryPageSteps.approvingContract(contractDocumentName, createdRegionName, createdLocationName, createdCCName, createdSpecialtyName, createdPositionName)
ContractDocumentLibraryPageSteps.clickOnApproversLink(contractDocumentName)
'ER:'
'Verify that Contract Document is Approved'
ContractDocumentLibraryPageSteps.getBaseSteps().verifyMainModalHasContent("(Approved)")

'Step 15: '
'Submitting For Signing'
ContractDocumentLibraryPageSteps.clickOnSubmitForSigning(contractDocumentName)

'Step 16: Check email of signer 1 send:'
'Open gmail email'
Browser.navigateToURL(GlobalVariable.GMAIL_URL)
'ER:'
'The email is sent'
GmailPageSteps.LogintoGmail(GlobalVariable.SIGNER_USER1,GlobalVariable.GMAIL_PWD)
GmailPageSteps.SearchOnGmail(createdProvidersName +  ' and "signature requested"' )
'ER:'
'Verify that first approver has received the email'
String expectedApprover1EmailContent = String.format("Please DocuSign %s",contractDocumentName)
Logger.logINFO("Expected Result: " + expectedApprover1EmailContent);
String emailAppr1Content = GmailPageSteps.getBaseSteps().getTextFromControl("Mail_Content_LabelForSigner",PageLocatorName.GMAIL_PAGE)
Logger.logINFO("Actual Result: " + emailAppr1Content);
AssertSteps.verifyExpectedResult(emailAppr1Content.contains(expectedApprover1EmailContent))
GmailPageSteps.GmailLogoutAndSignBackiframe()

// add code here for second signer after the first signs it

'Step 17: Check email of notifier 1:'
'Open gmail email and login as the notifier'
GmailPageSteps.LogintoGmail(GlobalVariable.NOTIFIER_MAIL_USER1,GlobalVariable.GMAIL_PWD)
'ER:'
'The preAction email is sent'
Browser.refreshCurrentPage()
GmailPageSteps.SearchOnGmail(createdProvidersName + " and Contract Sent for Signing")
String expectedPreActionEmailContent = String.format("The Contract for %s",createdProvidersName)
Logger.logINFO("Expected Result: " + expectedPreActionEmailContent);
String preActionEmailContent = GmailPageSteps.GetEmailContent(createdProvidersName)
Logger.logINFO("Actual Result: " + preActionEmailContent);
AssertSteps.verifyExpectedResult(preActionEmailContent.contains(expectedPreActionEmailContent))

// Add post action code aftersigner 1 and 2 signs the docusign

Browser.navigateToURL(GlobalVariable.URL)

'Step 20: Clean up data'
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
'Delete the Esign approval'
MenuPageSteps.selectConntractActionRequestMenu("CAR Templates") //Refresh page
MenuPageSteps.selectConntractActionRequestMenu("CAR Approval Setup")
CARApprovalSetupPageSteps.deleteConfiguredApprovals(createdESignApprovalName)
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

@TearDown
def closeBrowser(){
Browser.quitDriver();
}



