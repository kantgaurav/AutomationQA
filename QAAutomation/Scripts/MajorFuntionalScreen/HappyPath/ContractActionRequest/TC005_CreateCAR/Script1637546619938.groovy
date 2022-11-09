import com.kms.katalon.core.annotation.TearDown

import configs.PageLocatorName
import core.AssertSteps
import core.Browser
import internal.GlobalVariable
import pages.CARApprovalSetupPageSteps
import pages.CARTemplatesPageSteps
import pages.CostCenterPageSteps
import pages.CreateCARPageSteps
import pages.HeaderPageSteps
import pages.LocationPageSteps
import pages.LoginPageSteps
import pages.MenuPageSteps
import pages.PositionPageSteps
import pages.ProvidersPageSteps
import pages.RegionPageSteps
import pages.SpecialtyPageSteps
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
'ER:'
'System should allow the user logged successfully'
HeaderPageSteps.verifyPageIsLoaded()

'Step 3: Prepare Data'
'Create a Region'
MenuPageSteps.selectOrganizationAdminMenu("Region")
String createdRegionName = RegionPageSteps.createRegion()
RegionPageSteps.getBaseSteps().verifyMainPopUpHasContent("Region saved")
'Create Location'
MenuPageSteps.selectOrganizationAdminMenu("Location")
String createdLocationName = LocationPageSteps.createLocation(createdRegionName)
LocationPageSteps.getBaseSteps().verifyMainPopUpHasContent("Location saved")
'Create Cost Center'
MenuPageSteps.selectOrganizationAdminMenu("Cost Center")
String createdCCName = CostCenterPageSteps.createCostCenter(createdRegionName, createdLocationName)
LocationPageSteps.getBaseSteps().verifyMainPopUpHasContent("Cost Center saved")
'Create a Specialty'
MenuPageSteps.selectOrganizationAdminMenu("Specialty")
String createdSpecialtyName = SpecialtyPageSteps.createSpecialty()
SpecialtyPageSteps.getBaseSteps().verifyMainPopUpHasContent("Specialty saved")
'Create a Position'
MenuPageSteps.selectOrganizationAdminMenu("Position")
String createdPositionName = PositionPageSteps.createPosition()
PositionPageSteps.getBaseSteps().verifyMainPopUpHasContent("Position saved")
'Create CAR Templates'
MenuPageSteps.selectConntractActionRequestMenu("CAR Templates")
String createdTemplateName = CARTemplatesPageSteps.createCarTemplateForCAR(createdRegionName, createdLocationName, createdCCName, createdSpecialtyName, createdPositionName)
CARTemplatesPageSteps.getBaseSteps().verifyMainPopUpHasContent("Template information has been saved.")
'Create a Providers'
MenuPageSteps.selectOrganizationAdminMenu("Providers")
String createdProvidersName = ProvidersPageSteps.createProvider()
ProvidersPageSteps.getBaseSteps().verifyMainPopUpHasContent("Provider Added Successfully")
'Create a Contract Approvals'
MenuPageSteps.selectConntractActionRequestMenu("CAR Approval Setup")
String createdCTApprovalName = CARApprovalSetupPageSteps.createApproval("CAR Approval", "Cost Center", "Hierarchical", createdRegionName, createdLocationName, createdCCName, createdSpecialtyName, createdPositionName)
CARApprovalSetupPageSteps.getBaseSteps().verifyMainPopUpHasContent("Approval information has been saved!")
CARApprovalSetupPageSteps.getBaseSteps().clickToControl("Return_To_Approvals_Button", PageLocatorName.CARAPPROVAL_PAGE)

'Step 4:'
'Select Create CAR'
MenuPageSteps.selectConntractActionRequestMenu("Create CAR")
'ER:'
'Create CAR page is loaded'
CreateCARPageSteps.getBaseSteps().verifyControlDisplayed("Region_Combobox", "CreateCARPage")

'Step 5:'
'Complete Initiate CAR page'
CreateCARPageSteps.completeInitiateCARFields(createdRegionName, createdLocationName, createdCCName, createdSpecialtyName, createdPositionName)
CreateCARPageSteps.getBaseSteps().clickToControl("Initiate_CAR_Next_Button", "CreateCARPage")
'ER:'
'Verify Selecte Template is loaded'
CreateCARPageSteps.getBaseSteps().verifyControlDisplayed("Create_New_Template_Button", "CreateCARPage")

'Step 6:'
'Complete Select Template'
CreateCARPageSteps.completeSelectTemplate(createdTemplateName)
'ER:'
'A Provider screen displays'
CreateCARPageSteps.getBaseSteps().verifyControlDisplayed("Provider_Search_Textbox", "CreateCARPage")

'Step 7:'
'Complete select Provider'
CreateCARPageSteps.completeProviders(createdProvidersName)
'ER:'
'CAR Information is loaded'
CreateCARPageSteps.getBaseSteps().verifyControlDisplayed("Contract_Start_Date_Textbox", "CreateCARPage")

'Step 8:'
'Complete CAR Information'
CreateCARPageSteps.completeCARInformation()
'ER:'
'Review and Finalize screen displays'
CreateCARPageSteps.getBaseSteps().verifyControlContainedTextDisplayed("Text_Center_Label", "Approval from the following users will be required for Contract to be executed:", "CreateCARPage")

'Step 9:'
'Click Next button'
CreateCARPageSteps.getBaseSteps().clickToControl("Approvers_Next_Button", "CreateCARPage")
'ER:'
'Review and Finalize page is loaded'
CreateCARPageSteps.getBaseSteps().verifyControlDisplayed("Contract_Action_Request_Content_Label", "CreateCARPage")
'Contract Action Request is loaded correctly'
CreateCARPageSteps.getBaseSteps().verifyControlContainedTextDisplayed("Contract_Action_Request_Content_Label", "New Hire", "CreateCARPage")

'Step 10:'
'Click Submit for Approval'
CreateCARPageSteps.getBaseSteps().clickToControl("Submit_For_Approval_Button", "CreateCARPage")
'ER:'
'A success message is displayed'
CreateCARPageSteps.getBaseSteps().verifyMainPopUpHasContent("CAR has been sent for Approval.")
CreateCARPageSteps.getBaseSteps().verifyControlDisabled("Submit_For_Approval_Button", "CreateCARPage")

'Step 11:'
'Select View CAR'
MenuPageSteps.selectConntractActionRequestMenu("View CARs")
'ER:'
'Verify Page title is correct'
ViewCARPageSteps.getBaseSteps().verifyPageTitleIsCorrect("Contract Action Requests")

'Step 12:'
'Search the createdCAR' // Common is first row
ViewCARPageSteps.search(createdProvidersName)
String statusCAR= ViewCARPageSteps.getBaseSteps().getCellTextBaseOnAnotherCellDataTable("CAR_Table", "3", createdProvidersName, "13", "ViewCARPage")//13 is status, 12 is Approvals Complete
'ER:'
'The status of CAR is Sent For Approval'
AssertSteps.verifyExpectedResult(statusCAR.equals("Sent For Approval"),String.format("The status if CAR is %s",statusCAR),String.format("The status if CAR is NOT apporved, the current status is %s",statusCAR))

/**
'Step 13: Clean up data'
'Delete the created CAR'
MenuPageSteps.selectConntractActionRequestMenu("Create CAR") //Refresh page
MenuPageSteps.selectConntractActionRequestMenu("View CARs")
ViewCARPageSteps.deleteACar(createdProvidersName)
'Delete the created approval name'
MenuPageSteps.selectConntractActionRequestMenu("CAR Templates") //Refresh page
MenuPageSteps.selectConntractActionRequestMenu("CAR Approval Setup")
CARApprovalSetupPageSteps.deleteConfiguredApprovals(createdCTApprovalName)
'Delete the created CAR Templates'
MenuPageSteps.selectConntractActionRequestMenu("CAR Templates")
CARTemplatesPageSteps.searchAndDelete(createdTemplateName)
'Delete the created Providers'
MenuPageSteps.selectOrganizationAdminMenu("Providers")
ProvidersPageSteps.searchAndDelete(createdProvidersName)
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
//@TearDown
def closeBrowser(){
Browser.quitDriver();
}

