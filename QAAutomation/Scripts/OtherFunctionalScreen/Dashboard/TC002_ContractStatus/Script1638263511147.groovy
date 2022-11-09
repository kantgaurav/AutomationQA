import com.kms.katalon.core.annotation.TearDown

import configs.PageLocatorName
import core.Browser
import internal.GlobalVariable
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
'System should display CMS Login page.'
LoginPageSteps.verifyPageIsLoaded()

'Step 2:'
'Enter the Username and Password and Click on Login button.'
LoginPageSteps.login(GlobalVariable.SUPER_USER, GlobalVariable.SUPER_PW, GlobalVariable.ORG_AUTOMATION, GlobalVariable.ROLE_SYSTEM_ADMIN)
'ER:'
'System should allow the user logged successfully'
HeaderPageSteps.verifyPageIsLoaded()

'Step 3 : '
'Click on Contract status tab'
DashboardPageSteps.getBaseSteps().clickToControl("Contract_Status_Label", PageLocatorName.DASHBOARD_PAGE)
'ER:'
'Verify page is loaded'
ContractStatusPageSteps.verifyPageIsLoaded()

'Step 4: '
'Select Approved in Status dropdown list'
ContractStatusPageSteps.filterContractStatus()
'ER'
'Search table is displayed'
ContractStatusPageSteps.getBaseSteps().verifyControlDisabled("Search Table", PageLocatorName.COMMONLOCATORS_PAGE)

'Step 5:'
'Click on Export'
ContractStatusPageSteps.getBaseSteps().clickToControl("Export_Button", PageLocatorName.COMMONLOCATORS_PAGE)
'ER:'
'Verify the attached is downloaded'
prefix = "Contract Status Report"
String section = ""
String fileNameAfterExported = ContractStatusPageSteps.verifyFileIsDownloadedSuccessfully(prefix, section)

'Step 6: '
'Veriy table data is included in excel file'
String excelFilePath = PlatformUtil.getFilePath(PlatformUtil.getDownloadPath(),fileNameAfterExported)
'Verify that the column headers are the same on the UI and the exported file'
ContractStatusPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Contract Document Name")
ContractStatusPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Provider Name")
ContractStatusPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Region")
ContractStatusPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Location")
ContractStatusPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Cost Center")
ContractStatusPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Specialty")
ContractStatusPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Position")
ContractStatusPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Status")
ContractStatusPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Approvers")
ContractStatusPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Signers")
ContractStatusPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Created At")
ContractStatusPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Updated At")
'Verify the fist row data in the excel exported file'
String providerName = ContractStatusPageSteps.getCellTextAtFirstRow(2) //2 is Provider Name
String contractDocumentName = ContractStatusPageSteps.getCellTextAtFirstRow(3) //3 is Contract Document Name
String dataRegion = ContractStatusPageSteps.getCellTextAtFirstRow(4)  //4 is Region
String dataLocation = ContractStatusPageSteps.getCellTextAtFirstRow(5) //5 is Location
String dataCostCenter = ContractStatusPageSteps.getCellTextAtFirstRow(6)  //6 is Cost Center
String dataSpecialty = ContractStatusPageSteps.getCellTextAtFirstRow(7) //7 is Specialty
String dataPosition = ContractStatusPageSteps.getCellTextAtFirstRow(8) //8 is Position
String dataStatus = ContractStatusPageSteps.getCellTextAtFirstRow(9) //9 is status
String dataApprovers = ContractStatusPageSteps.getCellTextAtFirstRow(10) //10 is Providers
String dataSigners = ContractStatusPageSteps.getCellTextAtFirstRow(11) //11 is Signers

ContractStatusPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, providerName)
ContractStatusPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, contractDocumentName)
ContractStatusPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataRegion)
ContractStatusPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataLocation)
ContractStatusPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataCostCenter)
ContractStatusPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataSpecialty)
ContractStatusPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataPosition)
ContractStatusPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataStatus)
ContractStatusPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataApprovers)
ContractStatusPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataSigners)



'Step 7: Clean up data'
'Delete file '
PlatformUtil.deleteAllFilesWithPrefix(PlatformUtil.getDownloadPath(), prefix)

@TearDown
def closeBrowser(){
	PlatformUtil.deleteAllFilesWithPrefix(PlatformUtil.getDownloadPath(), prefix)
	Browser.quitDriver();
}