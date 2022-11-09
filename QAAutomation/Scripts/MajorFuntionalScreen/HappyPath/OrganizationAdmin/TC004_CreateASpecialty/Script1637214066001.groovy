import com.kms.katalon.core.annotation.TearDown

import core.Browser
import internal.GlobalVariable
import pages.HeaderPageSteps
import pages.LoginPageSteps
import pages.MenuPageSteps
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

'Step 3:'
'Select Specialty '
MenuPageSteps.selectOrganizationAdminMenu("Specialty")
'ER:'
'The Specialty page is loaded'
SpecialtyPageSteps.verifyPageIsLoaded()

'Step 4:'
'Create a Specialty'
String createdSpecialtyName = SpecialtyPageSteps.createSpecialty()
'ER:'
'Specialty is created successfully'
SpecialtyPageSteps.getBaseSteps().verifyMainPopUpHasContent("Specialty saved")

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







