import com.kms.katalon.core.annotation.TearDown

import configs.Path
import core.Browser
import internal.GlobalVariable
import pages.HeaderPageSteps
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
'Select Region'
MenuPageSteps.selectOrganizationAdminMenu("Region")
'ER:'
'The Region page is loaded'
RegionPageSteps.verifyPageIsLoaded()

'Step 4:'
'Import file'
String filePath = PlatformUtil.getFileProjectPath(String.format("%s%s",Path.DATAFILE_PATH,regionFileName))
String createdRegionName = RegionPageSteps.writeValueToRegionFile(filePath)
RegionPageSteps.importFile(filePath)
'ER:'
'Import file successfully'
RegionPageSteps.getBaseSteps().verifyMainModalHasContent("Regions have been imported successfully.")

'Step 5:'
'Search the imported data'
RegionPageSteps.search(createdRegionName)
'ER:'
'The created Region is diplayed'
RegionPageSteps.verifyDataIsDisplayedAfterSearch("2", createdRegionName)//2 is region name

'Step 6: Clean up data - Delete the created region'
MenuPageSteps.selectOrganizationAdminMenu("Region")
RegionPageSteps.searchAndDelete(createdRegionName)

@TearDown
def closeBrowser(){
Browser.quitDriver();
}






