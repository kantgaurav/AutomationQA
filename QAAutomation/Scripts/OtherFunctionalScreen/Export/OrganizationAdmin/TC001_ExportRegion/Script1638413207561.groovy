import com.kms.katalon.core.annotation.TearDown

import configs.PageLocatorName
import core.Browser
import internal.GlobalVariable
import pages.HeaderPageSteps
import pages.LoginPageSteps
import pages.MenuPageSteps
import pages.RegionPageSteps
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
'Select Region'
MenuPageSteps.selectOrganizationAdminMenu("Region")
'ER:'
'The Region page is loaded'
RegionPageSteps.verifyPageIsLoaded()

'Step 4:'
'Select Export button'
RegionPageSteps.getBaseSteps().clickToControl("Export_Button", PageLocatorName.COMMONLOCATORS_PAGE)
'ER:'
'Verify the attached is downloaded'
String prefix = "Region Report"
String section = "Region"
String fileNameAfterExported = RegionPageSteps.verifyFileIsDownloadedSuccessfully(prefix, section)

'Step 5: '
String excelFilePath = PlatformUtil.getFilePath(PlatformUtil.getDownloadPath(),fileNameAfterExported)
'Verify that the column header are the same on the UI and the exported file'
//For column NOT in order
RegionPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Region Name")
RegionPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Region ID Used By Organization")
'Verify the fist row data in the excel exported file'
//For column NOT in order
String dataRegionName = RegionPageSteps.getCellTextAtFirstRow(2) //2 is the index of column
String dataRegionID = RegionPageSteps.getCellTextAtFirstRow(3) //3 is the index of column
RegionPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataRegionName)
RegionPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataRegionID)

'Step 6: Clean up data'
'Delete file '
PlatformUtil.deleteAllFilesWithPrefix(PlatformUtil.getDownloadPath(), prefix)

@TearDown
def closeBrowser(){
Browser.quitDriver();
}
