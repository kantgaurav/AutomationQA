import com.kms.katalon.core.annotation.TearDown

import configs.PageLocatorName
import core.Browser
import internal.GlobalVariable
import pages.HeaderPageSteps
import pages.LocationPageSteps
import pages.LoginPageSteps
import pages.MenuPageSteps
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
'Select Location'
MenuPageSteps.selectOrganizationAdminMenu("Location")
'ER:'
'The Location page is loaded'
LocationPageSteps.verifyPageIsLoaded()

'Step 4:'
'Select Export button'
LocationPageSteps.getBaseSteps().clickToControl("Export_Button", PageLocatorName.COMMONLOCATORS_PAGE)
'ER:'
'Verify the attached is downloaded'
String prefix = "Location Report"
String section = "Location"
String fileNameAfterExported = LocationPageSteps.verifyFileIsDownloadedSuccessfully(prefix, section)
		 	
'Step 5: '
String excelFilePath = PlatformUtil.getFilePath(PlatformUtil.getDownloadPath(),fileNameAfterExported)
//For column NOT in order
LocationPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Region Name")
LocationPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Location Name")
LocationPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Location ID Used By Organization")
LocationPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Payroll Location ID")
'Verify the fist row data in the excel exported file'
//For column NOT in order
String dataRegionName = LocationPageSteps.getCellTextAtFirstRow(3)  //3 is the index of column
String dataLocationName = LocationPageSteps.getCellTextAtFirstRow(4)  //4 is the index of column
String dataLocationID = LocationPageSteps.getCellTextAtFirstRow(5)  //5 is the index of column
String dataPayroll = LocationPageSteps.getCellTextAtFirstRow(6)  //6 is the index of column
LocationPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataRegionName)
LocationPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataLocationName)
LocationPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataLocationID)
LocationPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataPayroll)

'Step 6: Clean up data'
'Delete file '
PlatformUtil.deleteAllFilesWithPrefix(PlatformUtil.getDownloadPath(), prefix)

@TearDown
def closeBrowser(){
Browser.quitDriver();
}


