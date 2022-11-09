import com.kms.katalon.core.annotation.TearDown

import configs.PageLocatorName
import core.Browser
import internal.GlobalVariable
import pages.CostCenterPageSteps
import pages.HeaderPageSteps
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
'Select Cost Center'
MenuPageSteps.selectOrganizationAdminMenu("Cost Center")
'ER:'
'The Cost Center page is loaded'
CostCenterPageSteps.verifyPageIsLoaded()

'Step 4:'
'Select Export button'
CostCenterPageSteps.getBaseSteps().clickToControl("Export_Button", PageLocatorName.COMMONLOCATORS_PAGE)
'ER:'
'Verify the attached is downloaded'
String prefix = "Cost Center Report"
String section = "Cost Center"
String fileNameAfterExported = CostCenterPageSteps.verifyFileIsDownloadedSuccessfully(prefix, section)
						

'Step 5: '
String excelFilePath = PlatformUtil.getFilePath(PlatformUtil.getDownloadPath(),fileNameAfterExported)
//For column NOT in order
CostCenterPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Region")
CostCenterPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Location")
CostCenterPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Cost Center Name")
CostCenterPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "HII CC ID")
CostCenterPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Payroll CC ID")
CostCenterPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "HRCCID")
CostCenterPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "EMR CC ID")
'Verify the fist row data in the excel exported file'
//For column NOT in order
String dataRegion = CostCenterPageSteps.getCellTextAtFirstRow(3)  //3 is the index of column
String dataLocation = CostCenterPageSteps.getCellTextAtFirstRow(5)  //5 is the index of column
String dataCostCenter = CostCenterPageSteps.getCellTextAtFirstRow(6)  //6 is the index of column
String dataHII = CostCenterPageSteps.getCellTextAtFirstRow(9)  //7 is the index of column
String dataPayroll = CostCenterPageSteps.getCellTextAtFirstRow(8)  //8 is the index of column
String dataHRCCID = CostCenterPageSteps.getCellTextAtFirstRow(9)  //9 is the index of column
String dataEMR = CostCenterPageSteps.getCellTextAtFirstRow(10)  //10 is the index of column

CostCenterPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataRegion)
CostCenterPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataLocation)
CostCenterPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataCostCenter)
CostCenterPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataHII)
CostCenterPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataPayroll)
CostCenterPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataHRCCID)
CostCenterPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataEMR)

'Step 6: Clean up data'
'Delete file '
PlatformUtil.deleteAllFilesWithPrefix(PlatformUtil.getDownloadPath(), prefix)

@TearDown
def closeBrowser(){
Browser.quitDriver();
}





