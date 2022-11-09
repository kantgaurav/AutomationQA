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
import pages.DashboardPageSteps
import pages.ExecuteContractPageSteps
import pages.HeaderPageSteps
import pages.LocationPageSteps
import pages.LoginPageSteps
import pages.MenuPageSteps
import pages.PositionPageSteps
import pages.ProvidersPageSteps
import pages.RegionPageSteps
import pages.SpecialtyPageSteps
import pages.TemplateLibraryPageSteps
import pages.ViewCARPageSteps

'Step 1:'
'Enter the Application URL in browser.'
Browser.start(GlobalVariable.URL)
'ER:'
'System should display CMS Login page.'
LoginPageSteps.verifyPageIsLoaded()

'Step 2:'
'Enter the Username and Password and Click on Login button.'
LoginPageSteps.login(GlobalVariable.APPROVER_MAIL_USER1, GlobalVariable.COMMON_PW, GlobalVariable.ORG_AUTOMATION, GlobalVariable.ROLE_SYSTEM_ADMIN)
//LoginPageSteps.login(GlobalVariable.SUPER_USER, GlobalVariable.SUPER_PW, GlobalVariable.ORG_AUTOMATION, GlobalVariable.ROLE_SYSTEM_ADMIN) 
'ER:'
'System should allow the user logged successfully'
HeaderPageSteps.verifyPageIsLoaded()

'Step 3: Prepared data'
'Create a Providers'
MenuPageSteps.selectOrganizationAdminMenu("Providers")
String createdProvidersName = ProvidersPageSteps.createProvider() 
'Providers is created successfully'
//ProvidersPageSteps.getBaseSteps().verifyMainPopUpHasContent("Provider Added Successfully")
Thread.sleep(2000)
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
String createdCARApprovalName = CARApprovalSetupPageSteps.createApproval("CAR Approval", "Cost Center", "Hierarchical", createdRegionName, createdLocationName, createdCCName, createdSpecialtyName, createdPositionName,[GlobalVariable.APPROVER_USER1])
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
String createdESignApprovalName = CARApprovalSetupPageSteps.createApproval("E-Signature", "Cost Center", "Hierarchical", createdRegionName, createdLocationName, createdCCName, createdSpecialtyName, createdPositionName, [GlobalVariable.APPROVER_USER1], [GlobalVariable.SIGNER_USER1])
'CAR Approval Setup is created successfully'
CARApprovalSetupPageSteps.getBaseSteps().verifyMainPopUpHasContent("Approval information has been saved!")
'Click to Return to Approvals button'
CARApprovalSetupPageSteps.getBaseSteps().clickToControl("Return_To_Approvals_Button", PageLocatorName.CARAPPROVAL_PAGE)
'Create a Template Contract'
MenuPageSteps.selectContractDocumentsMenu("Template Library")
String createdTemplateName = TemplateLibraryPageSteps.createTemplateLibrary(createdCARRequest, false,false)
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
'Send and Save successfully'
//ExecuteContractPageSteps.getBaseSteps().verifyMainPopUpHasContent("Successfully sent for Approval.")
//ExecuteContractPageSteps.getBaseSteps().verifyMainPopUpHasContent("Successfully sent.")
Thread.sleep(8000)

'Step 4: '
'Select Contract Document Library'
MenuPageSteps.selectConntractActionRequestMenu("Contract Document Library")
'ER:'
'The Execute Contract page is loaded'
ContractDocumentLibraryPageSteps.verifyPageIsLoaded()

'Step 5: '
'Do filters'
ContractDocumentLibraryPageSteps.getBaseSteps().clickToControl("Filter_Button", PageLocatorName.CONTRACTDOCUMENTLIBRARY_PAGE)
ContractDocumentLibraryPageSteps.filterWith(createdRegionName, createdLocationName, createdCCName, createdSpecialtyName, createdPositionName)

'Step 6:'
'The status is change to Pending For Signature'
String approveStatus = ContractDocumentLibraryPageSteps.getStatusDocument(contractDocumentName)
AssertSteps.verifyExpectedResult(approveStatus.equals("Pending For Signature"),String.format("The status is %s",approveStatus),String.format("The status is %s",approveStatus))

'Step 7: '
'Back to Dashboard and verify that the CAR is not displayed'
MenuPageSteps.selectDashboardMenu()
DashboardPageSteps.verifyAssignedTaskNotDisplayed(createdProvidersName)

'Step 8: Clean Data'
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

@TearDown
def closeBrowser(){
Browser.quitDriver();
}



