import org.junit.Assert

import com.kms.katalon.core.annotation.TearDown

import configs.PageLocatorName
import core.Browser
import internal.GlobalVariable
import pages.CARStatusReportPageSteps
import pages.DashboardPageSteps
import pages.HeaderPageSteps
import pages.LoginPageSteps
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

'Step 3: '
'Click on CAR_Status_Label'
DashboardPageSteps.getBaseSteps().clickToControl("CAR_Status_Label", PageLocatorName.DASHBOARD_PAGE)
'ER:'
'Verify page is loaded'
CARStatusReportPageSteps.verifyPageIsLoaded()

'Step 4: '
'Select Approved in Status dropdown list'
CARStatusReportPageSteps.filterCARStatus()
'ER'
'Search table is displayed'
CARStatusReportPageSteps.getBaseSteps().verifyControlDisabled("Search Table", PageLocatorName.COMMONLOCATORS_PAGE)

'Step 5:'
'Click on Export'
CARStatusReportPageSteps.getBaseSteps().clickToControl("Export_Button", PageLocatorName.COMMONLOCATORS_PAGE)
'ER:'
'Verify the attached is downloaded'
String prefix = "CAR Status Report"
//FAILED: This step will be failed due on "Success! Upcoming Renewals data exported successfully." on Automation Org vs "Success! CAR Status Report data exported successfully. on other Org"
String fileNameAfterExported = CARStatusReportPageSteps.verifyFileIsDownloadedSuccessfully(prefix, "CAR Status")

'Step 6: '
'Veriy table data is included in excel file'
String excelFilePath = PlatformUtil.getFilePath(PlatformUtil.getDownloadPath(),fileNameAfterExported)
'Verify that the column headers are the same on the UI and the exported file'
//For column in order
CARStatusReportPageSteps.verifyColumnHeaderInExportedFile(excelFilePath, 2, 7) 
//For column NOT in order
CARStatusReportPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "CreatedAt")
CARStatusReportPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Template")
CARStatusReportPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Approvals Complete")
CARStatusReportPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Status")
'Verify the fist row data in the excel exported file'
//For column in order
CARStatusReportPageSteps.verifyFirstRowInExportedFile(excelFilePath, 2, 7)
//For column NOT in order
String dataAtTempale = CARStatusReportPageSteps.getCellTextAtFirstRow(8)
String dataCreatedAt = CARStatusReportPageSteps.getCellTextAtFirstRow(9)
String dataApprovalsComplete = CARStatusReportPageSteps.getCellTextAtFirstRow(10)
String dataStatus = CARStatusReportPageSteps.getCellTextAtFirstRow(11)
dataCreatedAt = dataCreatedAt.substring(0,dataCreatedAt.indexOf(' '))
CARStatusReportPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataAtTempale)
CARStatusReportPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataCreatedAt)
CARStatusReportPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataApprovalsComplete)
CARStatusReportPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataStatus)

'Step 7: Clean up data'
'Delete file '
PlatformUtil.deleteAllFilesWithPrefix(PlatformUtil.getDownloadPath(), prefix)

@TearDown
def closeBrowser(){
Browser.quitDriver();
}

