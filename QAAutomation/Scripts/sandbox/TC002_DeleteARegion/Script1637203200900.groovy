import com.kms.katalon.core.annotation.TearDown

import configs.PageName
import core.Browser
import internal.GlobalVariable
import pages.HeaderPageSteps
import pages.LoginPageSteps
import pages.MenuPageSteps
import pages.RegionPageSteps

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
'Delete a region'
String deletedRegion = RegionPageSteps.deleteRegion()
'ER:'
'The region is deleted'
RegionPageSteps.getBaseSteps().verifyMainPopUpHasContent("Region deleted")

'Step 5:'
'Search the created Region'
RegionPageSteps.searchRegion(deletedRegion)
'ER:'
'The deleted Region is NOT diplayed'
RegionPageSteps.getBaseSteps().verifyTableBodyContainData("Search_Regions_Table", "No records to display", PageName.REGION_PAGE)

@TearDown
def closeBrowser(){
Browser.quitDriver();
}





