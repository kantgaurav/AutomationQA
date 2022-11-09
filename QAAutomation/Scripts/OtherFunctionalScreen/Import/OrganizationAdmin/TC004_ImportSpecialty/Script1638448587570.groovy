import com.kms.katalon.core.annotation.TearDown

import configs.Path
import core.Browser
import internal.GlobalVariable
import pages.HeaderPageSteps
import pages.LoginPageSteps
import pages.MenuPageSteps
import pages.PositionPageSteps
import pages.RegionPageSteps
import pages.SpecialtyPageSteps
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
'Select Specialty '
MenuPageSteps.selectOrganizationAdminMenu("Specialty")
'ER:'
'The Specialty page is loaded'
SpecialtyPageSteps.verifyPageIsLoaded()

'Step 4:'
'Import file'
String filePath = PlatformUtil.getFileProjectPath(String.format("%s%s",Path.DATAFILE_PATH,specialtyFileName))
String createdSpecialtyName = SpecialtyPageSteps.writeValueToRegionFile(filePath)
SpecialtyPageSteps.importFile(filePath)
'ER:'
'Import file successfully'
SpecialtyPageSteps.getBaseSteps().verifyMainModalHasContent("Specialty have been imported successfully.")

'Step 5:'
'Search the created Specialty'
SpecialtyPageSteps.search(createdSpecialtyName)
'ER:'
'The created Specialty is diplayed'
SpecialtyPageSteps.verifyDataIsDisplayedAfterSearch("2", createdSpecialtyName)//2 is specialty name

'Step 6: Clean up data - Delete the created Specialty'
MenuPageSteps.selectOrganizationAdminMenu("Specialty")
SpecialtyPageSteps.searchAndDelete(createdSpecialtyName)

@TearDown
def closeBrowser(){
Browser.quitDriver();
}






