import com.kms.katalon.core.annotation.TearDown

import configs.PageLocatorName
import core.Browser
import internal.GlobalVariable
import pages.HeaderPageSteps
import pages.LoginPageSteps
import pages.MenuPageSteps
import pages.SpecialtyPageSteps
import utils.DateTimeUtil
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
'Select Specialty'
MenuPageSteps.selectOrganizationAdminMenu("Specialty")
'ER:'
'The Specialty page is loaded'
SpecialtyPageSteps.verifyPageIsLoaded()

'Step 4:'
'Select Export button'
SpecialtyPageSteps.getBaseSteps().clickToControl("Export_Button", PageLocatorName.COMMONLOCATORS_PAGE)
'ER:'
'Verify the attached is downloaded'
String prefix = "Specialty Report"
String section = "Specialty"
String fileNameAfterExported = SpecialtyPageSteps.verifyFileIsDownloadedSuccessfully(prefix, section)
		
'Step 5: '
String excelFilePath = PlatformUtil.getFilePath(PlatformUtil.getDownloadPath(),fileNameAfterExported)
//For column NOT in order
SpecialtyPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Specialty Name")
SpecialtyPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Specialty ID Used By Organization")
SpecialtyPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Crosswalk Specialty")
'Verify the fist row data in the excel exported file'
//For column NOT in order
String dataSpecialtyName = SpecialtyPageSteps.getCellTextAtFirstRow(2)  //2 is the index of column
String dataSpecialtyNameID = SpecialtyPageSteps.getCellTextAtFirstRow(3)  //3 is the index of column
String dataCrosswalk = SpecialtyPageSteps.getCellTextAtFirstRow(4)  //4 is the index of column
SpecialtyPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataSpecialtyName)
SpecialtyPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataSpecialtyNameID)
SpecialtyPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataCrosswalk)

'Step 6: Clean up data'
'Delete file '
PlatformUtil.deleteAllFilesWithPrefix(PlatformUtil.getDownloadPath(), prefix)

@TearDown
def closeBrowser(){
Browser.quitDriver();
}






