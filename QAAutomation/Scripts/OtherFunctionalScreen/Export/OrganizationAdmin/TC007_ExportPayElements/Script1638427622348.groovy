import com.kms.katalon.core.annotation.TearDown

import configs.PageLocatorName
import core.Browser
import internal.GlobalVariable
import pages.HeaderPageSteps
import pages.LoginPageSteps
import pages.MenuPageSteps
import pages.PayElementsPageSteps
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
'Select Pay Elements'
MenuPageSteps.selectOrganizationAdminMenu("Pay Elements")
'ER:'
'The Pay Elements page is loaded'
PayElementsPageSteps.verifyPageIsLoaded()

'Step 4:'
'Select Export button'
PayElementsPageSteps.getBaseSteps().clickToControl("Export_Button", PageLocatorName.COMMONLOCATORS_PAGE)
'ER:'
'Verify the attached is downloaded'
String prefix = "Pay Elements Report"
String section = "Pay Elements"
String fileNameAfterExported = PayElementsPageSteps.verifyFileIsDownloadedSuccessfully(prefix, section)
	
'Step 5: '
String excelFilePath = PlatformUtil.getFilePath(PlatformUtil.getDownloadPath(),fileNameAfterExported)
'Verify that the column header are the same on the UI and the exported file'
//For column NOT in order
PayElementsPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Code")
PayElementsPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Description")
PayElementsPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Type")
PayElementsPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Classification")
PayElementsPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Frequency")
PayElementsPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Payroll Description")
'Verify data in the excel exported file'
//For column NOT in order
String dataCode = PayElementsPageSteps.getCellTextAtFirstRow(3)
String dataDescription = PayElementsPageSteps.getCellTextAtFirstRow(4)
String dataType = PayElementsPageSteps.getCellTextAtFirstRow(5)
String dataClassification = PayElementsPageSteps.getCellTextAtFirstRow(6)
String dataPayrollDescription = PayElementsPageSteps.getCellTextAtFirstRow(7)
String dataFrequency = PayElementsPageSteps.getCellTextAtFirstRow(8)
PayElementsPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataCode)
PayElementsPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataDescription)
PayElementsPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataType)
PayElementsPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataClassification)
PayElementsPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataPayrollDescription)
PayElementsPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataFrequency)

'Step 6: Clean up data'
'Delete file '
PlatformUtil.deleteAllFilesWithPrefix(PlatformUtil.getDownloadPath(), prefix)

@TearDown
def closeBrowser(){
Browser.quitDriver();
}





