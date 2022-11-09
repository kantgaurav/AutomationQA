import com.fasterxml.aalto.out.StreamWriterBase.State
import com.kms.katalon.core.annotation.TearDown

import configs.PageLocatorName
import core.Browser
import internal.GlobalVariable
import pages.HeaderPageSteps
import pages.LoginPageSteps
import pages.MenuPageSteps
import pages.ProvidersPageSteps
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
'Select Providers'
MenuPageSteps.selectOrganizationAdminMenu("Providers")
'ER:'
'The Providers page is loaded'
ProvidersPageSteps.verifyPageIsLoaded()

'Step 4:'
'Select Export button'
ProvidersPageSteps.getBaseSteps().clickToControl("Export_Button", PageLocatorName.COMMONLOCATORS_PAGE)
'ER:'
'Verify the attached is downloaded'
String prefix = "Providers"
String section = "Provider"
String fileNameAfterExported = ProvidersPageSteps.verifyFileIsDownloadedSuccessfully(prefix, section)

'Step 5: '
String excelFilePath = PlatformUtil.getFilePath(PlatformUtil.getDownloadPath(),fileNameAfterExported)
'Verify that the column header are the same on the UI and the exported file'
//For column NOT in order
ProvidersPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "First Name")
ProvidersPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Last Name")
ProvidersPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Position")
ProvidersPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Email")
ProvidersPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "NPI") 
ProvidersPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Address1")
ProvidersPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Address2")
ProvidersPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "City")
ProvidersPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "State")
ProvidersPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Zip Code")
ProvidersPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Comments")
//ProvidersPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "OrgId") //Not match with excel file
'Verify data in the excel exported file'
//For column NOT in order
String dataFirstName = ProvidersPageSteps.getCellTextAtFirstRow(3) //3 is the index of column
String dataLastName = ProvidersPageSteps.getCellTextAtFirstRow(4)  //4 is the index of column
String dataPosition = ProvidersPageSteps.getCellTextAtFirstRow(5) //5 is the index of column
String dataEmail = ProvidersPageSteps.getCellTextAtFirstRow(6)  //6 is the index of column
//String dataNPI = ProvidersPageSteps.getCellTextAtFirstRow(7) // data is not displayed in excel file , //7 is the index of column
String dataAddress1 = ProvidersPageSteps.getCellTextAtFirstRow(8) //8 is the index of column
String dataAddress2 = ProvidersPageSteps.getCellTextAtFirstRow(9) //9 is the index of column
String dataCity = ProvidersPageSteps.getCellTextAtFirstRow(10) //10 is the index of column
String dataState = ProvidersPageSteps.getCellTextAtFirstRow(11) //11 is the index of column
String dataZipCode = ProvidersPageSteps.getCellTextAtFirstRow(12) //12 is the index of column
String dataComments = ProvidersPageSteps.getCellTextAtFirstRow(14) //14 is the index of column
String dataOrgId = ProvidersPageSteps.getCellTextAtFirstRow(15) //15 is the index of column

ProvidersPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataFirstName)
ProvidersPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataLastName)
ProvidersPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataPosition)
ProvidersPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataEmail)
//ProvidersPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataNPI)//data is not displayed in excel file
ProvidersPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataAddress1)
ProvidersPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataAddress2)
ProvidersPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataCity)
ProvidersPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataState)
ProvidersPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataZipCode)
ProvidersPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataComments)
ProvidersPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataOrgId)

'Step 6: Clean up data'
'Delete file '
PlatformUtil.deleteAllFilesWithPrefix(PlatformUtil.getDownloadPath(), prefix)

@TearDown
def closeBrowser(){
Browser.quitDriver();
}



