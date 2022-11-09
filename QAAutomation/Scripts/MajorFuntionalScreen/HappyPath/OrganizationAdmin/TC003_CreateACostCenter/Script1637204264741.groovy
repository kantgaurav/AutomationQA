import com.kms.katalon.core.annotation.TearDown

import core.Browser
import internal.GlobalVariable
import pages.CostCenterPageSteps
import pages.HeaderPageSteps
import pages.LocationPageSteps
import pages.LoginPageSteps
import pages.MenuPageSteps
import pages.RegionPageSteps 

'PRE-CONDITION STEPS:'
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
'Create Region Data'
MenuPageSteps.selectOrganizationAdminMenu("Region")
String createdRegionName = RegionPageSteps.createRegion()
RegionPageSteps.getBaseSteps().verifyMainPopUpHasContent("Region saved")
'Create Location Data'
MenuPageSteps.selectOrganizationAdminMenu("Location")
String createdLocationName = LocationPageSteps.createLocation(createdRegionName)
LocationPageSteps.getBaseSteps().verifyMainPopUpHasContent("Location saved")

'Step 4:'
'Select Cost Center'
MenuPageSteps.selectOrganizationAdminMenu("Cost Center")
'ER:'
'The Cost Center page is loaded'
CostCenterPageSteps.verifyPageIsLoaded()

'Step 5:'
'Create Cost Center'
String createdCCName = CostCenterPageSteps.createCostCenter(createdRegionName, createdLocationName)
'ER:'
'The Cost Center is created'
LocationPageSteps.getBaseSteps().verifyMainPopUpHasContent("Cost Center saved")

'Step 6:'
'Search the created Cost Center'
LocationPageSteps.search(createdLocationName)
'ER:'
'The created Cost Center is displayed'
LocationPageSteps.verifyDataIsDisplayedAfterSearch("6", createdCCName)//6 is CC Name

'Step 7: Clean up data'
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