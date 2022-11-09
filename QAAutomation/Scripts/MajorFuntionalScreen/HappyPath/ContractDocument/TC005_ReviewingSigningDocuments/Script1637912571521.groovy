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
import pages.DocuSignPageSteps
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


'Step 1:'
'Enter the Application URL in browser.'
Browser.start(GlobalVariable.URL)
'ER:'
'System should display CMS Login page.'
LoginPageSteps.verifyPageIsLoaded()

'Step 2:'
'Enter the Username and Password and Click on Login button.'
LoginPageSteps.login(GlobalVariable.SUPER_USER, GlobalVariable.SUPER_PW, GlobalVariable.ORG_AUTOMATION, GlobalVariable.ROLE_SYSTEM_ADMIN)
'ER:'
'System should allow the user logged successfully'
HeaderPageSteps.verifyPageIsLoaded()

'Step 3: Prepared data'
'Create a Providers'
MenuPageSteps.selectOrganizationAdminMenu("Providers")
String providerFName = ProvidersPageSteps.createProvider()
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
'Create CAR Templates'
MenuPageSteps.selectConntractActionRequestMenu("CAR Templates")
String createdCARRequest = CARTemplatesPageSteps.createCarTemplateForCAR(createdRegionName, createdLocationName, createdCCName, createdSpecialtyName, createdPositionName)
'CAR Templates is created successfully'
CARTemplatesPageSteps.getBaseSteps().verifyMainPopUpHasContent("Template information has been saved.")
'Create a CAR Approvals'
MenuPageSteps.selectConntractActionRequestMenu("CAR Approval Setup")
String createdCARApprovalName = CARApprovalSetupPageSteps.createApproval("CAR Approval", "Cost Center", "Hierarchical", createdRegionName, createdLocationName, createdCCName, createdSpecialtyName, createdPositionName)
'CAR Approval Setup is created successfully'
CARApprovalSetupPageSteps.getBaseSteps().verifyMainPopUpHasContent("Approval information has been saved!")
'Click to Return to Approvals button'
CARApprovalSetupPageSteps.getBaseSteps().clickToControl("Return_To_Approvals_Button", PageLocatorName.CARAPPROVAL_PAGE)
'Create a CT Approvals'
MenuPageSteps.selectConntractActionRequestMenu("CAR Approval Setup")
String createdCTApprovalName = CARApprovalSetupPageSteps.createApproval("CT Approval", "Cost Center", "Hierarchical", createdRegionName, createdLocationName, createdCCName, createdSpecialtyName, createdPositionName)
'CAR Approval Setup is created successfully'
CARApprovalSetupPageSteps.getBaseSteps().verifyMainPopUpHasContent("Approval information has been saved!")
'Click to Return to Approvals button'
CARApprovalSetupPageSteps.getBaseSteps().clickToControl("Return_To_Approvals_Button", PageLocatorName.CARAPPROVAL_PAGE)
'Create a E-Signature Approvals'
MenuPageSteps.selectConntractActionRequestMenu("CAR Approval Setup")
String createdESignApprovalName = CARApprovalSetupPageSteps.createApproval("E-Signature", "Cost Center", "Hierarchical", createdRegionName, createdLocationName, createdCCName, createdSpecialtyName, createdPositionName, [GlobalVariable.SIGNER_USER1], [GlobalVariable.SIGNER_USER1])
'CAR Approval Setup is created successfully'
CARApprovalSetupPageSteps.getBaseSteps().verifyMainPopUpHasContent("Approval information has been saved!")
'Click to Return to Approvals button'
CARApprovalSetupPageSteps.getBaseSteps().clickToControl("Return_To_Approvals_Button", PageLocatorName.CARAPPROVAL_PAGE)
'Create a Template Contract'
MenuPageSteps.selectContractDocumentsMenu("Template Library")
String createdTemplateName = TemplateLibraryPageSteps.createTemplateLibrary(createdCARRequest)
'Verify the template librabries can be created'
TemplateLibraryPageSteps.getBaseSteps().verifyMainPopUpHasContent("Document has been saved")
'Create a CAR'
MenuPageSteps.selectConntractActionRequestMenu("Create CAR")
CreateCARPageSteps.createCARForApproval(createdRegionName, createdLocationName, createdCCName, createdSpecialtyName, createdPositionName, createdCARRequest, providerFName)
'A success message is displayed'
CreateCARPageSteps.getBaseSteps().verifyMainPopUpHasContent("CAR has been sent for Approval.")
'Approving a CAR'
MenuPageSteps.selectConntractActionRequestMenu("View CARs")
ViewCARPageSteps.approvingCAR(providerFName)
'Executing CAR'
String contractDocumentName = ExecuteContractPageSteps.executingCAR(providerFName, createdCARRequest, createdTemplateName)
'Send and Save successfully'
ExecuteContractPageSteps.getBaseSteps().verifyMainPopUpHasContent("Successfully sent for Approval.")
'Approving Contract Document'
MenuPageSteps.selectContractDocumentsMenu("Contract Document Library")
ContractDocumentLibraryPageSteps.approvingContract(contractDocumentName, createdRegionName, createdLocationName, createdCCName, createdSpecialtyName, createdPositionName)
'The document is approved'
ContractDocumentLibraryPageSteps.clickOnApproversLink(contractDocumentName)
ContractDocumentLibraryPageSteps.getBaseSteps().verifyMainModalHasContent("(Approved)")
'Clicking Template Contract'
MenuPageSteps.selectContractDocumentsMenu("Template Library")
'Submitting For Signing'
MenuPageSteps.selectConntractActionRequestMenu("Contract Document Library")
ContractDocumentLibraryPageSteps.submittingForSigning(contractDocumentName, createdRegionName, createdLocationName, createdCCName, createdSpecialtyName, createdPositionName)
'The status is change to Pending For Signature'
String approveStatus = ContractDocumentLibraryPageSteps.getStatusDocument(contractDocumentName)
AssertSteps.verifyExpectedResult(approveStatus.equals("Pending For Signature"),String.format("The status is %s",approveStatus),String.format("The status is %s",approveStatus))

'Step 4:'
'Open mailtemp email'
Browser.navigateToURL(GlobalVariable.MAIL_TEMP_TOP_URL)
'ER:'
'mailtemp is loaded'
MailTempTopPageSteps.getBaseSteps().verifyControlDisplayed("Email_Textbox", PageLocatorName.MAILTEMPTOP_PAGE)

'Step 5:'
'Open email inbox'
MailTempTopPageSteps.getBaseSteps().setTextToControl("Email_Textbox",GlobalVariable.SIGNER_MAIL_USER1, PageLocatorName.MAILTEMPTOP_PAGE)
MailTempTopPageSteps.getBaseSteps().clickToControl("Create_Button", PageLocatorName.MAILTEMPTOP_PAGE)
'ER:'
'Inbox is loaded'
MailTempTopPageSteps.getBaseSteps().verifyControlDisplayed("Refresh_Button", PageLocatorName.MAILTEMPTOP_PAGE)

'Step 6:'
String signedEmailName = String.format("Signature Requested - %s",contractDocumentName)
String wd = Browser.getCurrentWindowHandle()
'Open Signature Requested email and click Review document'

//'Step 4:'
//'Open yopmail email'
//Browser.navigateToURL(GlobalVariable.EMAIL_URL)
//'ER:'
//'Yop mail is loaded'
//YopmailPageSteps.getBaseSteps().verifyControlDisplayed("Email_Textbox", PageLocatorName.YOPMAIL_PAGE)
//'Step 5:'
//'Open email inbox'
//YopmailPageSteps.getBaseSteps().setTextToControl("Email_Textbox",GlobalVariable.SIGNER_MAIL_USER1, PageLocatorName.YOPMAIL_PAGE)
//YopmailPageSteps.getBaseSteps().clickToControl("Check_Inbox_Button", PageLocatorName.YOPMAIL_PAGE)
//'ER:'
//'Inbox is loaded'
//YopmailPageSteps.getBaseSteps().verifyControlDisplayed("Three_Dots_Button", PageLocatorName.YOPMAIL_PAGE)
//'Step 6:'
//String signedEmailName = String.format("Signature Requested - %s",contractDocumentName)
//String wd = Browser.getCurrentWindowHandle()
//'Open Signature Requested email and click Review document'
//YopmailPageSteps.getBaseSteps().clickToIFrameControlByText("//iframe[@id='ifinbox']",signedEmailName)
//YopmailPageSteps.getBaseSteps().clickToIFrameControl("//iframe[@id='ifmail']","Review_Document_Button", PageLocatorName.YOPMAIL_PAGE)
//Browser.switchToNewOpenedWindow()
//DocuSignPageSteps.getBaseSteps().waitForControlDoesNotDisplay("Loading_Image", PageLocatorName.DOCUSIGN_PAGE)
//'ER:'
//'DocuSign is loaded'
//DocuSignPageSteps.getBaseSteps().verifyControlDisplayed("DocuSign_Page_Title", PageLocatorName.DOCUSIGN_PAGE)
//
//YopmailPageSteps.getBaseSteps().clickToIFrameControlByText("//iframe[@id='ifinbox']",signedEmailName)
//YopmailPageSteps.getBaseSteps().clickToIFrameControl("//iframe[@id='ifmail']","Review_Document_Button", PageLocatorName.YOPMAIL_PAGE)
//Browser.switchToNewOpenedWindow()
//DocuSignPageSteps.getBaseSteps().waitForControlDoesNotDisplay("Loading_Image", PageLocatorName.DOCUSIGN_PAGE)
//'ER:'
//'DocuSign is loaded'
//DocuSignPageSteps.getBaseSteps().verifyControlDisplayed("DocuSign_Page_Title", PageLocatorName.DOCUSIGN_PAGE)
//
//'Step 7:'
//'Sign the document'
//DocuSignPageSteps.getBaseSteps().clickToControl("I_Agree_Checkbox", PageLocatorName.DOCUSIGN_PAGE)
//DocuSignPageSteps.getBaseSteps().clickToControl("Continue_Button", PageLocatorName.DOCUSIGN_PAGE)
//DocuSignPageSteps.getBaseSteps().clickToControl("Sign_Button", PageLocatorName.DOCUSIGN_PAGE)
//DocuSignPageSteps.getBaseSteps().clickToControl("Adopt_And_Sign_Button", PageLocatorName.DOCUSIGN_PAGE)
//DocuSignPageSteps.getBaseSteps().clickToControl("Finish_Button", PageLocatorName.DOCUSIGN_PAGE)

'Step 8:'
'Back to mail and refresh the mail box'
Browser.switchToWindow(wd)
YopmailPageSteps.getBaseSteps().clickToIFrameControl("//iframe[@id='ifmail']","Delete_Button", PageLocatorName.YOPMAIL_PAGE)

'Step 9: '
'Navigate back the sysment'
Browser.navigateToURL(GlobalVariable.URL)

'Step 10: Clean up data'
'Abandon Contract Document'
MenuPageSteps.selectConntractActionRequestMenu("Contract Document Library")
ContractDocumentLibraryPageSteps.abandonContractDocument(contractDocumentName, createdRegionName, createdLocationName, createdCCName, createdSpecialtyName, createdPositionName)
'Delete Template Libary'
MenuPageSteps.selectConntractActionRequestMenu("Contract Document Library") //refresh page
MenuPageSteps.selectContractDocumentsMenu("Template Library")
TemplateLibraryPageSteps.deleteTemplate(createdRegionName, createdLocationName, createdCCName, createdSpecialtyName, createdPositionName)
'Delete the created CAR'
MenuPageSteps.selectConntractActionRequestMenu("Create CAR") //Refresh page
MenuPageSteps.selectConntractActionRequestMenu("View CARs")
ViewCARPageSteps.deleteACar(providerFName)
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
'Delete the created Providers'
MenuPageSteps.selectOrganizationAdminMenu("Providers")
ProvidersPageSteps.searchAndDelete(providerFName)

@TearDown
def cleanData() {
	Browser.quitDriver();
}

