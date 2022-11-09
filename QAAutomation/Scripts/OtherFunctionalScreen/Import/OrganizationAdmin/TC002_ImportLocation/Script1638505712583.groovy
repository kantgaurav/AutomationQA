import com.kms.katalon.core.annotation.TearDown

import configs.Path
import core.Browser
import internal.GlobalVariable
import pages.HeaderPageSteps
import pages.LocationPageSteps
import pages.LoginPageSteps
import pages.MenuPageSteps
import pages.RegionPageSteps
import utils.PlatformUtil

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

'Step 3:'
'Prepare Region Data'
MenuPageSteps.selectOrganizationAdminMenu("Region")
String createdRegionName = RegionPageSteps.createRegion()
RegionPageSteps.getBaseSteps().verifyMainPopUpHasContent("Region saved")

'Step 4:'
'Select Location'
MenuPageSteps.selectOrganizationAdminMenu("Location")
'ER:'
'The Location page is loaded'
LocationPageSteps.verifyPageIsLoaded()

'Step 5:'
'Import file'
String filePath = PlatformUtil.getFileProjectPath(String.format("%s%s",Path.DATAFILE_PATH,locationFileName))
String createdLocationName = LocationPageSteps.writeValueToRegionFile(filePath, createdRegionName)
LocationPageSteps.importFile(filePath)
'ER:'
'Import file successfully'
LocationPageSteps.getBaseSteps().verifyMainModalHasContent("Locations have been imported successfully.")

'Step 6:'
'Search the created Location'
LocationPageSteps.search(createdLocationName)
'ER:'
'The created Location is displayed'
LocationPageSteps.verifyDataIsDisplayedAfterSearch("4", createdLocationName)

'Step 7: Clean up data '
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






