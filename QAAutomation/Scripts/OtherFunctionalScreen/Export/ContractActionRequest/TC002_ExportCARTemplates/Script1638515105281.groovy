import com.kms.katalon.core.annotation.TearDown

import configs.PageLocatorName
import core.Browser
import internal.GlobalVariable
import pages.CARTemplatesPageSteps
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
'Select CAR Templates'
MenuPageSteps.selectConntractActionRequestMenu("CAR Templates")

'Step 4:'
'Select Export button'
CARTemplatesPageSteps.getBaseSteps().clickToControl("Export_Button", PageLocatorName.COMMONLOCATORS_PAGE)
'ER:'
'Verify the attached is downloaded'
String prefix = "CAR Template Report"
String section = "CAR Template"
String fileNameAfterExported = RegionPageSteps.verifyFileIsDownloadedSuccessfully(prefix, section)

'Step 5: '
String excelFilePath = PlatformUtil.getFilePath(PlatformUtil.getDownloadPath(),fileNameAfterExported)
'Verify that the column header are the same on the UI and the exported file'
//For column NOT in order
CARTemplatesPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Name")
CARTemplatesPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Region")
CARTemplatesPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Location")
CARTemplatesPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Cost Center")
CARTemplatesPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Specialty")
CARTemplatesPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Position")
'Verify the fist row data in the excel exported file'
//For column NOT in order
String dataName = CARTemplatesPageSteps.getCellTextAtFirstRow(2) //2 is the index of column
String dataRegion = CARTemplatesPageSteps.getCellTextAtFirstRow(3) //3 is the index of column
String dataLocation = CARTemplatesPageSteps.getCellTextAtFirstRow(4) //4 is the index of column
String dataCostCenter= CARTemplatesPageSteps.getCellTextAtFirstRow(5) //5 is the index of column
String dataSpecialty = CARTemplatesPageSteps.getCellTextAtFirstRow(6) //6 is the index of column
String dataPosition = CARTemplatesPageSteps.getCellTextAtFirstRow(7) //7 is the index of column
CARTemplatesPageSteps.verifyExcelFileContentOfCARTemplate(excelFilePath, dataName)
CARTemplatesPageSteps.verifyExcelFileContentOfCARTemplate(excelFilePath, dataRegion)
CARTemplatesPageSteps.verifyExcelFileContentOfCARTemplate(excelFilePath, dataLocation)
CARTemplatesPageSteps.verifyExcelFileContentOfCARTemplate(excelFilePath, dataCostCenter)
CARTemplatesPageSteps.verifyExcelFileContentOfCARTemplate(excelFilePath, dataSpecialty)
CARTemplatesPageSteps.verifyExcelFileContentOfCARTemplate(excelFilePath, dataPosition)

'Step 6: Clean up data'
'Delete file '
PlatformUtil.deleteAllFilesWithPrefix(PlatformUtil.getDownloadPath(), prefix)

@TearDown
def closeBrowser(){
Browser.quitDriver();
}


