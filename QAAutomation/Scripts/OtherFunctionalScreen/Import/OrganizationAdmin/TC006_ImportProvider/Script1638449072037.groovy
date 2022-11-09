import com.kms.katalon.core.annotation.TearDown

import configs.Path
import core.Browser
import internal.GlobalVariable
import pages.HeaderPageSteps
import pages.LoginPageSteps
import pages.MenuPageSteps
import pages.PositionPageSteps
import pages.ProvidersPageSteps
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

'Step 3: Prepared data'
'Create a Position'
MenuPageSteps.selectOrganizationAdminMenu("Position")
String createdPositionName = PositionPageSteps.createPosition()
'Position is created successfully'
PositionPageSteps.getBaseSteps().verifyMainPopUpHasContent("Position saved")

'Step 4:'
'Select Providers '
MenuPageSteps.selectOrganizationAdminMenu("Providers")
'ER:'
'The Providers page is loaded'
ProvidersPageSteps.verifyPageIsLoaded()

'Step 5:'
'Import file'
String filePath = PlatformUtil.getFileProjectPath(String.format("%s%s",Path.DATAFILE_PATH,providerFileName))
String createdProvidersName = ProvidersPageSteps.writeValueToRegionFile(filePath, createdPositionName)
ProvidersPageSteps.importFile(filePath)
'ER:'
'Import file successfully'
ProvidersPageSteps.getBaseSteps().verifyMainModalHasContent("Providers have been imported successfully.")

'Step 6:'
'Search the created Providers'
ProvidersPageSteps.search(createdProvidersName)
'ER:'
'The created Providers is diplayed'
ProvidersPageSteps.verifyDataIsDisplayedAfterSearch("3", createdProvidersName)//6 is Providers email

'Step 7: Clean up data'
'Delete the created position'
MenuPageSteps.selectOrganizationAdminMenu("Position")
PositionPageSteps.searchAndDelete(createdPositionName)
'Delete the created Providers'
MenuPageSteps.selectOrganizationAdminMenu("Providers")
ProvidersPageSteps.searchAndDelete(createdProvidersName)

@TearDown
def closeBrowser(){
Browser.quitDriver();
}






