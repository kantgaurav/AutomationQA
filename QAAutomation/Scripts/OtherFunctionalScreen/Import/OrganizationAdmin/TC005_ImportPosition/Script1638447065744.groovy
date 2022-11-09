import com.kms.katalon.core.annotation.TearDown

import configs.Path
import core.Browser
import internal.GlobalVariable
import pages.HeaderPageSteps
import pages.LoginPageSteps
import pages.MenuPageSteps
import pages.PositionPageSteps
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
'Select Position '
MenuPageSteps.selectOrganizationAdminMenu("Position")
'ER:'
'The Position page is loaded'
PositionPageSteps.verifyPageIsLoaded()

'Step 4:'
'Import file'
String filePath = PlatformUtil.getFileProjectPath(String.format("%s%s",Path.DATAFILE_PATH,positionFileName))
String createdPositionName = PositionPageSteps.writeValueToRegionFile(filePath)
PositionPageSteps.importFile(filePath)
'ER:'
'Import file successfully'
RegionPageSteps.getBaseSteps().verifyMainModalHasContent("Position have been imported successfully.")

'Step 5:'
'Search the created Position'
PositionPageSteps.search(createdPositionName)
'ER:'
'The created Position is diplayed'
PositionPageSteps.verifyDataIsDisplayedAfterSearch("2", createdPositionName)//2 is Position name

'Step 6: Clean up data - Delete the created Position'
MenuPageSteps.selectOrganizationAdminMenu("Position")
PositionPageSteps.searchAndDelete(createdPositionName)

@TearDown
def closeBrowser(){
Browser.quitDriver();
}






