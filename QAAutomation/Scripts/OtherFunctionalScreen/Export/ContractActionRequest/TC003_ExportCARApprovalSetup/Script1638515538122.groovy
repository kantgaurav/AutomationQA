import com.kms.katalon.core.annotation.TearDown

import configs.PageLocatorName
import core.Browser
import internal.GlobalVariable
import pages.CARApprovalSetupPageSteps
import pages.HeaderPageSteps
import pages.LoginPageSteps
import pages.MenuPageSteps
import pages.RegionPageSteps
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
'Select CAR Approval Setup'
MenuPageSteps.selectConntractActionRequestMenu("CAR Approval Setup")

'Step 4:'
'Select Export button'
CARApprovalSetupPageSteps.getBaseSteps().clickToControl("Export_Button", PageLocatorName.COMMONLOCATORS_PAGE)
'ER:'
'Verify the attached is downloaded'
String prefix = "CAR Approvals Report"
String section = "CAR Approvals"
String fileNameAfterExported = RegionPageSteps.verifyFileIsDownloadedSuccessfully(prefix, section)
							

'Step 5: '
String excelFilePath = PlatformUtil.getFilePath(PlatformUtil.getDownloadPath(),fileNameAfterExported)
'Verify that the column header are the same on the UI and the exported file'
//For column NOT in order
//CARApprovalSetupPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Name") //MISSING IN EXCEL FILE
CARApprovalSetupPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Approval Type")
CARApprovalSetupPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Region")
CARApprovalSetupPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Location")
CARApprovalSetupPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Cost Center")
CARApprovalSetupPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Specialty")
CARApprovalSetupPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Position")
CARApprovalSetupPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Date Created")
CARApprovalSetupPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Date Modified")
//CARApprovalSetupPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Template Specific?" //MISSING IN EXCEL FILE

'Verify the fist row data in the excel exported file'
//For column NOT in order
//String dataName = CARApprovalSetupPageSteps.getCellTextAtFirstRow(2) //2 is the index of column , //MISSING IN EXCEL FILE
String dataApprovalType = CARApprovalSetupPageSteps.getCellTextAtFirstRow(3) //3 is the index of column
String dataRegion = CARApprovalSetupPageSteps.getCellTextAtFirstRow(4)  //4 is the index of column
String dataLocation = CARApprovalSetupPageSteps.getCellTextAtFirstRow(5) //5 is the index of column
String dataCostCenter = CARApprovalSetupPageSteps.getCellTextAtFirstRow(6)  //6 is the index of column
String dataSpecialty = CARApprovalSetupPageSteps.getCellTextAtFirstRow(7)  //7 is the index of column
String dataPosition = CARApprovalSetupPageSteps.getCellTextAtFirstRow(8) //8 is the index of column
//String dataTemplateSpecific = CARApprovalSetupPageSteps.getCellTextAtFirstRow(9)  //9 is the index of column, //MISSING IN EXCEL FILE
String dataDateCreated = CARApprovalSetupPageSteps.getCellTextAtFirstRow(10)  //10 is the index of column
String dataDateModified = CARApprovalSetupPageSteps.getCellTextAtFirstRow(11) //11 is the index of column

//CARApprovalSetupPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataName) //MISSING IN EXCEL FILE
CARApprovalSetupPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataApprovalType)
CARApprovalSetupPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataRegion)
CARApprovalSetupPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataLocation)
CARApprovalSetupPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataCostCenter)
CARApprovalSetupPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataSpecialty)
CARApprovalSetupPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataPosition)
//CARApprovalSetupPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataTemplateSpecific) //MISSING IN EXCEL FILE
CARApprovalSetupPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataDateCreated)
CARApprovalSetupPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataDateModified)

'Step 6: Clean up data'
'Delete file '
PlatformUtil.deleteAllFilesWithPrefix(PlatformUtil.getDownloadPath(), prefix)

@TearDown
def closeBrowser(){
Browser.quitDriver();
}



