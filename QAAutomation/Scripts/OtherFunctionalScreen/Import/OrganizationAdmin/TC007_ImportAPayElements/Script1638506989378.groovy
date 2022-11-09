import com.kms.katalon.core.annotation.TearDown

import configs.Path
import core.Browser
import internal.GlobalVariable
import pages.HeaderPageSteps
import pages.LoginPageSteps
import pages.MenuPageSteps
import pages.PayElementsPageSteps
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
'Select Pay Elements '
MenuPageSteps.selectOrganizationAdminMenu("Pay Elements")
'ER:'
'The Pay Elements page is loaded'
PayElementsPageSteps.verifyPageIsLoaded()

'Step 4:'
'Import file'
String filePath = PlatformUtil.getFileProjectPath(String.format("%s%s",Path.DATAFILE_PATH,payElementsFileName))
String createdPayElementsName = PayElementsPageSteps.writeValueToRegionFile(filePath)
PayElementsPageSteps.importFile(filePath)
'ER:'
'Import file successfully'
PayElementsPageSteps.getBaseSteps().verifyMainModalHasContent("Pay Elements have been imported successfully.")

'Step 5:'
'Search the created Pay Elements'
PayElementsPageSteps.search(createdPayElementsName)
'ER:'
'The created Pay Elements is diplayed'
PayElementsPageSteps.verifyDataIsDisplayedAfterSearch("3", createdPayElementsName)//3 is Pay Elements name

'Step 6: Clean up data - Delete the created Pay Elements'
MenuPageSteps.selectOrganizationAdminMenu("Pay Elements")
PayElementsPageSteps.searchAndDelete(createdPayElementsName)

@TearDown
def closeBrowser(){
Browser.quitDriver();
}
