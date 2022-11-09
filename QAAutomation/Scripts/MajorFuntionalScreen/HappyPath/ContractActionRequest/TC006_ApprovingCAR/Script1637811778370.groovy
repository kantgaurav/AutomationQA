import com.kms.katalon.core.annotation.TearDown

import configs.PageLocatorName
import core.AssertSteps
import core.Browser
import internal.GlobalVariable
import pages.CARApprovalSetupPageSteps
import pages.CARTemplatesPageSteps
import pages.CostCenterPageSteps
import pages.CreateCARPageSteps
import pages.DashboardPageSteps
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
LoginPageSteps.login(GlobalVariable.SUPER_USER, GlobalVariable.SUPER_PW, GlobalVariable.ORG_AUTOMATION, GlobalVariable.ROLE_SYSTEM_ADMIN)
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
'Create a Car'
MenuPageSteps.selectConntractActionRequestMenu("Create CAR")
CreateCARPageSteps.createCARForApproval(createdRegionName, createdLocationName, createdCCName, createdSpecialtyName, createdPositionName, createdTemplateName, createdProvidersName)

'Step 4:'
'Navigate back to Dashboard page and Select Task Assigned to me'
MenuPageSteps.selectDashboardMenu()
Browser.refreshCurrentPage()
DashboardPageSteps.viewAssignedTask(createdProvidersName)
'ER:'
'View CAR page displays'
Browser.switchToNewOpenedWindow()
ViewCARPageSteps.getBaseSteps().verifyPageTitleIsCorrect("Contract Action Requests")

'Step 5:'
'Click to Approvals Complete link'
ViewCARPageSteps.getBaseSteps().clickToCellTableLinkBaseOnAnotherCellDataTable("CAR_Table", "3", createdProvidersName, "12", PageLocatorName.VIEWCAR_PAGE)
'ER:'
'Verify Approvers dialog displays'
ViewCARPageSteps.getBaseSteps().verifyMainModalHasContent("Awaiting Approval")

'Step 6:'
'Click to approve'
ViewCARPageSteps.getBaseSteps().clickToCellTableButton("Search_Table", "last()","Approve", PageLocatorName.COMMONLOCATORS_PAGE)//last is Action column
'ER:'
'Approve success'
ViewCARPageSteps.getBaseSteps().verifyMainPopUpHasContent("CAR has been approved")
String status = ViewCARPageSteps.getBaseSteps().getCellTableText("Search_Table", "13", PageLocatorName.COMMONLOCATORS_PAGE)//13 is status col
AssertSteps.verifyExpectedResult(status.equals(status.trim()), String.format("The current status is %s", status),String.format("The current status is %s", status))

'Step 7:'
'Click to Approvals Complete link'
ViewCARPageSteps.getBaseSteps().clickToCellTableLinkBaseOnAnotherCellDataTable("CAR_Table", "3", createdProvidersName, "12", PageLocatorName.VIEWCAR_PAGE)
'ER:'
'Verify Approvers dialog displays'
ViewCARPageSteps.getBaseSteps().verifyMainModalHasContent("Approved")

'Step 8: '
'Back to Dashboard and verify that the CAR is not displayed'
MenuPageSteps.selectDashboardMenu()
Browser.refreshCurrentPage()
DashboardPageSteps.verifyAssignedTaskNotDisplayed(createdProvidersName)

/**
'Step 9: Clean up data'
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

