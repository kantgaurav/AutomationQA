import com.kms.katalon.core.annotation.TearDown

import configs.PageLocatorName
import core.Browser
import internal.GlobalVariable
import pages.ActivityLogPageSteps
import pages.DashboardPageSteps
import pages.HeaderPageSteps
import pages.LoginPageSteps
import pages.UpcomingRenewalsPageSteps
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
'Click on Upcoming Renewals Tab'
DashboardPageSteps.getBaseSteps().clickToControl("Upcoming_Renewals_Label", PageLocatorName.DASHBOARD_PAGE)
'ER:'
'Verify page is loaded'
UpcomingRenewalsPageSteps.verifyPageIsLoaded()

'Step 4:'
'Click on Export'
UpcomingRenewalsPageSteps.getBaseSteps().clickToControl("Export_Button", PageLocatorName.COMMONLOCATORS_PAGE)
'ER:'
'Verify the attached is downloaded'
String prefix = "Upcoming Renewals Report"
String section = "Upcoming Renewals"
String fileNameAfterExported = UpcomingRenewalsPageSteps.verifyFileIsDownloadedSuccessfully(prefix, section)

'Step 5: '
'Veriy table data is included in excel file'
String excelFilePath = PlatformUtil.getFilePath(PlatformUtil.getDownloadPath(),fileNameAfterExported)
'Verify that the column headers are the same on the UI and the exported file'
UpcomingRenewalsPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Provider Name")
UpcomingRenewalsPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Document Name")
UpcomingRenewalsPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Region")
UpcomingRenewalsPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Location")
UpcomingRenewalsPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Cost Center")
UpcomingRenewalsPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Specialty")
UpcomingRenewalsPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Position")
UpcomingRenewalsPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Start Date")
UpcomingRenewalsPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "End Date")

'Verify the fist row data in the excel exported file'
String providerName = UpcomingRenewalsPageSteps.getCellTextAtFirstRow(2) //2 is Provider Name
String documentName = UpcomingRenewalsPageSteps.getCellTextAtFirstRow(3) //3 is Document Name
String dataRegion = UpcomingRenewalsPageSteps.getCellTextAtFirstRow(4)  //4 is Region
String dataLocation = UpcomingRenewalsPageSteps.getCellTextAtFirstRow(5) //5 is Location
String dataCostCenter = UpcomingRenewalsPageSteps.getCellTextAtFirstRow(6)  //6 is Cost Center
String dataSpecialty = UpcomingRenewalsPageSteps.getCellTextAtFirstRow(7) //7 is Specialty
String dataPosition = UpcomingRenewalsPageSteps.getCellTextAtFirstRow(8) //8 is Position
String dataStartDate = UpcomingRenewalsPageSteps.getCellTextAtFirstRow(9) //9 is Start Date
String dataEndDate = UpcomingRenewalsPageSteps.getCellTextAtFirstRow(10) //10 is End Date


UpcomingRenewalsPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, providerName)
UpcomingRenewalsPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, documentName)
UpcomingRenewalsPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataRegion)
UpcomingRenewalsPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataLocation)
UpcomingRenewalsPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataCostCenter)
UpcomingRenewalsPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataSpecialty)
UpcomingRenewalsPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataPosition)
UpcomingRenewalsPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataStartDate)
UpcomingRenewalsPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataEndDate)


'Step 6: Clean up data'
'Delete file '
PlatformUtil.deleteAllFilesWithPrefix(PlatformUtil.getDownloadPath(), prefix)

@TearDown
def closeBrowser(){
Browser.quitDriver();
}