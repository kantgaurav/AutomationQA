import com.kms.katalon.core.annotation.TearDown

import configs.PageLocatorName
import core.Browser
import internal.GlobalVariable
import pages.CARApprovalSetupPageSteps
import pages.CostCenterPageSteps
import pages.HeaderPageSteps
import pages.LocationPageSteps
import pages.LoginPageSteps
import pages.MenuPageSteps
import pages.PositionPageSteps
import pages.RegionPageSteps
import pages.SpecialtyPageSteps
import utils.DataUtil

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
'Select Specialty '
MenuPageSteps.selectConntractActionRequestMenu("CAR Approval Setup")
'ER:'
'The CAR Approval Setup page is loaded'
CARApprovalSetupPageSteps.verifyPageIsLoaded()

'Step 4:'
'Create a CAR Approval Setup'
String createdCARApprovalName = CARApprovalSetupPageSteps.createApproval("CAR Approval", "Cost Center", "Hierarchical", 
	DataUtil.getVariable().region, DataUtil.getVariable().location, DataUtil.getVariable().costCenter, 
	DataUtil.getVariable().specialty, DataUtil.getVariable().position)
'ER:'
'CAR Approval Setup is created successfully'
CARApprovalSetupPageSteps.getBaseSteps().verifyMainPopUpHasContent("Approval information has been saved!")

'Step 5:'
'Click to Return to Approvals button'
CARApprovalSetupPageSteps.getBaseSteps().clickToControl("Return_To_Approvals_Button", PageLocatorName.CARAPPROVAL_PAGE)
'ER:'
'The CAR Approval Setup page is loaded'
CARApprovalSetupPageSteps.verifyPageIsLoaded()

'Step 6:'
'Search the created approval name'
CARApprovalSetupPageSteps.searchByName(createdCARApprovalName)
'ER:'
'The created approval name is diplayed'
CARApprovalSetupPageSteps.verifyDataIsDisplayedAfterSearch("2", createdCARApprovalName)//2 is Create CAR approval name

'Step 7: Clean up data'
'Delete the created approval'
MenuPageSteps.selectConntractActionRequestMenu("CAR Templates") //Refresh page
MenuPageSteps.selectConntractActionRequestMenu("CAR Approval Setup")
CARApprovalSetupPageSteps.deleteConfiguredApprovals(createdCARApprovalName)
//'Delete the created Position'
//MenuPageSteps.selectOrganizationAdminMenu("Position")
//PositionPageSteps.searchAndDelete(DataUtil.getVariable().position)
//'Delete the created Specialty'
//MenuPageSteps.selectOrganizationAdminMenu("Specialty")
//SpecialtyPageSteps.searchAndDelete(DataUtil.getVariable().specialty)
//'Delete the created Cost Center'
//MenuPageSteps.selectOrganizationAdminMenu("Cost Center")
//CostCenterPageSteps.searchAndDelete(createdCCName)
//'Delete the created location'
//MenuPageSteps.selectOrganizationAdminMenu("Location")
//LocationPageSteps.searchAndDelete(createdLocationName)
//'Delete the created region'
//MenuPageSteps.selectOrganizationAdminMenu("Region")
//RegionPageSteps.searchAndDelete(DataUtil.getVariable().region)

@TearDown
def closeBrowser(){
Browser.quitDriver();
}