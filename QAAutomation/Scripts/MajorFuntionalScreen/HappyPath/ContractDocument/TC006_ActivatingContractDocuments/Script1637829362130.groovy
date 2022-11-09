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
CreateCARPageSteps.createCARForApproval(createdRegionName, createdLocationName, createdCCName, createdSpecialtyName, createdPositionName, createdCARRequest, createdProvidersName)
'A success message is displayed'
CreateCARPageSteps.getBaseSteps().verifyMainPopUpHasContent("CAR has been sent for Approval.")
'Approving a CAR'
MenuPageSteps.selectConntractActionRequestMenu("View CARs")
ViewCARPageSteps.approvingCAR(createdProvidersName)
'Executing CAR'
String contractDocumentName = ExecuteContractPageSteps.executingCAR(createdProvidersName, createdCARRequest, createdTemplateName)
//ExecuteContractPageSteps.getBaseSteps().verifyMainPopUpHasContent("Successfully sent for Approval.")
Thread.sleep(3000)
'Approving Contract Document'
MenuPageSteps.selectContractDocumentsMenu("Contract Document Library")
ContractDocumentLibraryPageSteps.approvingContract(contractDocumentName, createdRegionName, createdLocationName, createdCCName, createdSpecialtyName, createdPositionName)
ContractDocumentLibraryPageSteps.clickOnApproversLink(contractDocumentName)
ContractDocumentLibraryPageSteps.getBaseSteps().verifyMainModalHasContent("(Approved)")
'Clicking Template Contract'
MenuPageSteps.selectContractDocumentsMenu("Template Library") //Refresh Page
'Submitting For Signing'
MenuPageSteps.selectConntractActionRequestMenu("Contract Document Library")
ContractDocumentLibraryPageSteps.submittingForSigning(contractDocumentName, createdRegionName, createdLocationName, createdCCName, createdSpecialtyName, createdPositionName)
'The status is change to Pending For Signature'
String approveStatusSignature = ContractDocumentLibraryPageSteps.getStatusDocument(contractDocumentName)
AssertSteps.verifyExpectedResult(approveStatusSignature.equals("Pending For Signature"),String.format("The status is %s",approveStatusSignature),String.format("The status is %s",approveStatusSignature))

'Sign Document'
'Open gmail email'
Browser.navigateToURL(GlobalVariable.GMAIL_URL)
'ER:'
'The email is sent'
GmailPageSteps.LogintoGmail(GlobalVariable.SIGNER_MAIL_USER1,GlobalVariable.GMAIL_PWD)
//Sign the docu Sign as a signer
GmailPageSteps.signDocument(createdProvidersName)

//YopmailPageSteps.signDocument(contractDocumentName, GlobalVariable.SIGNER_MAIL_USER1)
//MailTempTopPageSteps.signDocument(contractDocumentName, GlobalVariable.SIGNER_MAIL_USER1)
'Navigate back the CMS'
Browser.navigateToURL(GlobalVariable.URL)
'Make sure that Activate button is displayed'
MenuPageSteps.selectContractDocumentsMenu("Contract Document Library")
ContractDocumentLibraryPageSteps.refreshToWaitActivateButton(contractDocumentName)

'Step 4: '
'Do filters'
ContractDocumentLibraryPageSteps.getBaseSteps().clickToControl("Infor_Bar_Button", PageLocatorName.CONTRACTDOCUMENTLIBRARY_PAGE)
ContractDocumentLibraryPageSteps.getBaseSteps().clickToControl("Filter_Button", PageLocatorName.CONTRACTDOCUMENTLIBRARY_PAGE)
ContractDocumentLibraryPageSteps.filterWith(createdRegionName, createdLocationName, createdCCName, createdSpecialtyName, createdPositionName)

'Step 5: Review Approvers'
'Clicking on Approvers link'
ContractDocumentLibraryPageSteps.clickOnApproversLink(contractDocumentName)
ContractDocumentLibraryPageSteps.getBaseSteps().waitForControlDisplay("Model_Page", PageLocatorName.CONTRACTDOCUMENTLIBRARY_PAGE)
'ER:'
'Verify that Approve status = Approved'
ContractDocumentLibraryPageSteps.getBaseSteps().verifyControlContainedTextDisplayed("Status_Label", "(Approved)", PageLocatorName.CONTRACTDOCUMENTLIBRARY_PAGE)

'Step 6: Review Signers'
'Clicking on Signers link'
ContractDocumentLibraryPageSteps.getBaseSteps().clickToControl("Close_Model_Button", PageLocatorName.CONTRACTDOCUMENTLIBRARY_PAGE)
ContractDocumentLibraryPageSteps.clickOnSignersLink(contractDocumentName)
ContractDocumentLibraryPageSteps.getBaseSteps().waitForControlDisplay("Model_Page", PageLocatorName.CONTRACTDOCUMENTLIBRARY_PAGE)
'ER:'
'Verify that Approve status = Signed'
ContractDocumentLibraryPageSteps.getBaseSteps().verifyControlContainedTextDisplayed("Status_Label", "(Signed)", PageLocatorName.CONTRACTDOCUMENTLIBRARY_PAGE)

'Step 7: Close Model'
ContractDocumentLibraryPageSteps.getBaseSteps().clickToControl("Close_Model_Button", PageLocatorName.CONTRACTDOCUMENTLIBRARY_PAGE)

'Step 8:'
'Click Active button'
ContractDocumentLibraryPageSteps.clickOnActivateButton(contractDocumentName)
'ER:'
'The document is Executed'
String approveStatusExecuted = ContractDocumentLibraryPageSteps.getStatusDocument(contractDocumentName)
AssertSteps.verifyExpectedResult(approveStatusExecuted.equals("Executed"),String.format("The status is %s",approveStatusExecuted),String.format("The status is %s",approveStatusExecuted))
/**
'Step 9: Clean up data'
'Abandon Contract Document'
MenuPageSteps.selectContractDocumentsMenu("Template Library")//refresh page
MenuPageSteps.selectConntractActionRequestMenu("Contract Document Library")
ContractDocumentLibraryPageSteps.abandonContractDocument(contractDocumentName, createdRegionName, createdLocationName, createdCCName, createdSpecialtyName, createdPositionName)
'Delete Template Libary'
MenuPageSteps.selectConntractActionRequestMenu("Contract Document Library") //refresh page
MenuPageSteps.selectContractDocumentsMenu("Template Library")
TemplateLibraryPageSteps.deleteTemplate(createdRegionName, createdLocationName, createdCCName, createdSpecialtyName, createdPositionName)
'Delete the created CAR'
MenuPageSteps.selectConntractActionRequestMenu("Create CAR") //Refresh page
MenuPageSteps.selectConntractActionRequestMenu("View CARs")
ViewCARPageSteps.deleteACar(createdProvidersName)
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
ProvidersPageSteps.searchAndDelete(createdProvidersName)
**/
@TearDown
def closeBrowser(){
Browser.quitDriver();
}
