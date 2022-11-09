import com.kms.katalon.core.annotation.TearDown

import configs.PageLocatorName
import core.Browser
import internal.GlobalVariable
import pages.HeaderPageSteps
import pages.LoginPageSteps
import pages.MenuPageSteps
import pages.PositionPageSteps
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
'Select Position'
MenuPageSteps.selectOrganizationAdminMenu("Position")
'ER:'
'The Position page is loaded'
PositionPageSteps.verifyPageIsLoaded()

'Step 4:'
'Select Export button'
PositionPageSteps.getBaseSteps().clickToControl("Export_Button", PageLocatorName.COMMONLOCATORS_PAGE)
'ER:'
'Verify the attached is downloaded'
String prefix = "Position Report"
String section = "Position"
String fileNameAfterExported = PositionPageSteps.verifyFileIsDownloadedSuccessfully(prefix, section)
	
'Step 5: '
String excelFilePath = PlatformUtil.getFilePath(PlatformUtil.getDownloadPath(),fileNameAfterExported)
//For column NOT in order
PositionPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Position Name")
PositionPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Position ID Used By Organization")
'Verify the fist row data in the excel exported file'
//For column NOT in order
String dataPositionName = PositionPageSteps.getCellTextAtFirstRow(2)  //2 is the index of column
String dataPositionID = PositionPageSteps.getCellTextAtFirstRow(3)  //3 is the index of column
PositionPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataPositionName)
PositionPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataPositionID)

'Step 6: Clean up data'
'Delete file '
PlatformUtil.deleteAllFilesWithPrefix(PlatformUtil.getDownloadPath(), prefix)

@TearDown
def closeBrowser(){
Browser.quitDriver();
}

