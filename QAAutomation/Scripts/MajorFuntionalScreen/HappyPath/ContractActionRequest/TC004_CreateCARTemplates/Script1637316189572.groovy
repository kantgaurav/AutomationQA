import com.kms.katalon.core.annotation.TearDown

import configs.PageLocatorName
import core.Browser
import internal.GlobalVariable
import pages.CARTemplatesPageSteps
import pages.CostCenterPageSteps
import pages.HeaderPageSteps
import pages.LocationPageSteps
import pages.LoginPageSteps
import pages.MenuPageSteps
import pages.PositionPageSteps
import pages.RegionPageSteps
import pages.SpecialtyPageSteps

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

'Step 4:'
'Select CAR Templates '
MenuPageSteps.selectConntractActionRequestMenu("CAR Templates")
'ER:'
'The CAR Templates page is loaded'
CARTemplatesPageSteps.verifyPageIsLoaded()

'Step 5:'
'Create CAR Templates'
String createdTemplateName = CARTemplatesPageSteps.createCarTemplate(createdRegionName, createdLocationName, createdCCName, createdSpecialtyName, createdPositionName)
'ER:'
'CAR Templates is created successfully'
CARTemplatesPageSteps.getBaseSteps().verifyMainPopUpHasContent("Template information has been saved.")

'Step 6:'
'Click to Back to CAR Template list'
CARTemplatesPageSteps.getBaseSteps().clickToControl("Back_To_CAR_Template_List_Button", PageLocatorName.CARTEMPLATES_PAGE)
'ER:'
'The CAR Templates page is loaded'
CARTemplatesPageSteps.verifyPageIsLoaded()

'Step 7:'
'Search the created CAR Templates'
CARTemplatesPageSteps.search(createdTemplateName)
'ER:'
'The created CAR Templates is diplayed'
CARTemplatesPageSteps.verifyDataIsDisplayedAfterSearch("2", createdTemplateName)//2 is CAR Templates name

'Step 8: Clean up data'
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

@TearDown
def closeBrowser(){
Browser.quitDriver();
}



