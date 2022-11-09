import com.kms.katalon.core.annotation.TearDown

import configs.PageLocatorName
import core.Browser
import internal.GlobalVariable
import pages.ActivityLogPageSteps
import pages.ContractStatusPageSteps
import pages.DashboardPageSteps
import pages.HeaderPageSteps
import pages.LoginPageSteps
import utils.DateTimeUtil
import utils.PlatformUtil

'Step 1:'
'Enter the Application URL in browser.'
Browser.start(GlobalVariable.URL)
'ER:'
'System should display VMS Login page.'
LoginPageSteps.verifyPageIsLoaded()

'Step 2:'
'Enter the Username and Password and Click on Login button.'
LoginPageSteps.login(GlobalVariable.SUPER_USER, GlobalVariable.SUPER_PW, GlobalVariable.ORG_AUTOMATION, GlobalVariable.ROLE_SYSTEM_ADMIN)
'ER:'
'System should allow the user logged successfully'
HeaderPageSteps.verifyPageIsLoaded()

'Step 3: '
'Click on Activity log tab'
DashboardPageSteps.getBaseSteps().clickToControl("Activity_Log_Label", PageLocatorName.DASHBOARD_PAGE)
'ER:'
'Verify page is loaded'
ActivityLogPageSteps.verifyPageIsLoaded()

'Step 4: '
'Fill value in filter dropdown list'
ActivityLogPageSteps.filterActivityLog()
'ER'
'Search table is displayed'
ActivityLogPageSteps.getBaseSteps().verifyControlDisabled("Search Table", PageLocatorName.COMMONLOCATORS_PAGE)

'Step 5:'
'Click on Export'
ActivityLogPageSteps.getBaseSteps().clickToControl("Export_Button", PageLocatorName.COMMONLOCATORS_PAGE)
'ER:'
'Verify the attached is downloaded'
String prefix = "Activity Log Report"
String section = ""
String fileNameAfterExported = ActivityLogPageSteps.verifyFileIsDownloadedSuccessfully(prefix, section)


'Step 6: '
'Veriy table data is included in excel file'
String excelFilePath = PlatformUtil.getFilePath(PlatformUtil.getDownloadPath(),fileNameAfterExported)
'Verify that the column headers are the same on the UI and the exported file'
ActivityLogPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Time Stamp")
ActivityLogPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Function Type")
ActivityLogPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Action Type")
//ActivityLogPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Values")

'Verify the fist row data in the excel exported file'
String dataTimeStamp = ActivityLogPageSteps.getCellTextAtFirstRow(1) //1 is Time Stamp
String dataFunctionType = ActivityLogPageSteps.getCellTextAtFirstRow(2) //2 is Function Type
String dataActionType = ActivityLogPageSteps.getCellTextAtFirstRow(3)  //3 is Action Type

ActivityLogPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataTimeStamp)
ActivityLogPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataFunctionType)
ActivityLogPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataActionType)

'Step 7: Clean up data'
'Delete file '
PlatformUtil.deleteAllFilesWithPrefix(PlatformUtil.getDownloadPath(), prefix)

@TearDown
def closeBrowser(){
Browser.quitDriver();
}