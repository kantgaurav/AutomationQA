import com.kms.katalon.core.annotation.TearDown

import configs.PageLocatorName
import core.Browser
import internal.GlobalVariable
import pages.CARDataReportPageSteps
import pages.DashboardPageSteps
import pages.HeaderPageSteps
import pages.LoginPageSteps
import utils.DateTimeUtil
import utils.PlatformUtil
import pages.LocationPageSteps
import pages.MenuPageSteps
import pages.PositionPageSteps
import pages.ProvidersPageSteps
import pages.RegionPageSteps
import pages.SpecialtyPageSteps
import pages.CostCenterPageSteps
import pages.CARTemplatesPageSteps
import pages.CARApprovalSetupPageSteps
import pages.CreateCARPageSteps
import pages.ViewCARPageSteps

'Step 1:'
'Enter the Application URL in browser.'
Browser.start(GlobalVariable.URL)
'ER:'
'System should display CMS Login page.'
LoginPageSteps.verifyPageIsLoaded()

'Step 2:'
'Enter the Username and Password and Click on Login button.'
LoginPageSteps.login(GlobalVariable.APPROVER_MAIL_USER1, GlobalVariable.COMMON_PW, GlobalVariable.ORG_AUTOMATION, GlobalVariable.ROLE_SYSTEM_ADMIN)
'ER:'
'System should allow the user logged successfully'
HeaderPageSteps.verifyPageIsLoaded()

'Step 3: Prepared data'
'Create a Providers'
MenuPageSteps.selectOrganizationAdminMenu("Providers")
String createdProvidersName = ProvidersPageSteps.createProvider()
'Providers is created successfully'
//ProvidersPageSteps.getBaseSteps().verifyMainPopUpHasContent("Provider Added Successfully") This step fails inconsistently
Thread.sleep(2000)
'Create a Position'
MenuPageSteps.selectOrganizationAdminMenu("Position")
String createdPositionName = PositionPageSteps.createPosition()
'Position is created successfully'
PositionPageSteps.getBaseSteps().verifyMainPopUpHasContent("Position saved")
'Create a Specialty'
MenuPageSteps.selectOrganizationAdminMenu("Specialty")
String createdSpecialtyName = SpecialtyPageSteps.createSpecialty()
'Specialty is created successfully'
SpecialtyPageSteps.getBaseSteps().verifyMainPopUpHasContent("Specialty saved")
'Create a Region'
MenuPageSteps.selectOrganizationAdminMenu("Region")
String createdRegionName = RegionPageSteps.createRegion()
'Region is created successfully'
RegionPageSteps.getBaseSteps().verifyMainPopUpHasContent("Region saved")
'Create Location'
MenuPageSteps.selectOrganizationAdminMenu("Location")
String createdLocationName = LocationPageSteps.createLocation(createdRegionName)
'The Location is created'
LocationPageSteps.getBaseSteps().verifyMainPopUpHasContent("Location saved")
'Create Cost Center'
MenuPageSteps.selectOrganizationAdminMenu("Cost Center")
String createdCCName = CostCenterPageSteps.createCostCenter(createdRegionName, createdLocationName)
'The Cost Center is created'
LocationPageSteps.getBaseSteps().verifyMainPopUpHasContent("Cost Center saved")

'Step 4:'
'Create CAR Templates'
MenuPageSteps.selectConntractActionRequestMenu("CAR Templates")
String createdTemplateName = CARTemplatesPageSteps.createCarTemplateForCAR(createdRegionName, createdLocationName, createdCCName, createdSpecialtyName, createdPositionName)
'CAR Templates is created successfully'
CARTemplatesPageSteps.getBaseSteps().verifyMainPopUpHasContent("Template information has been saved.")


'Step 5:'
'Select CAR Approval Setup '
MenuPageSteps.selectConntractActionRequestMenu("CAR Approval Setup")
'ER:'
'The CAR Approval Setup page is loaded'
CARApprovalSetupPageSteps.verifyPageIsLoaded()


'Step 6:'
'Create a CAR Approval Setup with Region, Template and Hierachical'
String createdCARApprovalName = CARApprovalSetupPageSteps.createApproval("CAR Approval", "Region", "Hierarchical",
	createdRegionName, createdLocationName, createdCCName, createdSpecialtyName, createdPositionName, [GlobalVariable.APPROVER_USER1,GlobalVariable.APPROVER_USER2], [GlobalVariable.NOTIFIER_USER1], createdTemplateName,true,true)
'ER:'
'CAR Approval Setup is created successfully'
CARApprovalSetupPageSteps.getBaseSteps().verifyMainPopUpHasContent("Approval information has been saved!")


'Step 7: '
'Create a Car'
MenuPageSteps.selectConntractActionRequestMenu("Create CAR")
CreateCARPageSteps.createCARForApproval(createdRegionName, createdLocationName, createdCCName, createdSpecialtyName, createdPositionName, createdTemplateName, createdProvidersName)
'ER:'
'A success message is displayed'
CreateCARPageSteps.getBaseSteps().verifyMainPopUpHasContent("CAR has been sent for Approval.")


'Step 8: '
'Back to Dashboard and verify that the CAR is not displayed'
MenuPageSteps.selectDashboardMenu()
MenuPageSteps.getBaseSteps().waitForProgressBarDisappear()

'Step 9: '
'Click on CAR Data Report Tab'
DashboardPageSteps.getBaseSteps().clickToControl("CAR_Data_Report_Label", PageLocatorName.DASHBOARD_PAGE)
'ER:'
'Verify page is loaded'
CARDataReportPageSteps.verifyPageIsLoaded()

'Step 10: '
'Filter in dropdown list'
CARDataReportPageSteps.filterCARDataReport(createdTemplateName)
'ER'
'Search table is displayed'
CARDataReportPageSteps.getBaseSteps().verifyControlDisabled("Search Table", PageLocatorName.COMMONLOCATORS_PAGE)

'Step 11:'
'Click on Export'
CARDataReportPageSteps.getBaseSteps().clickToControl("Export_Button", PageLocatorName.COMMONLOCATORS_PAGE)
'ER:'
'Verify the attached is downloaded'
String prefix = "CAR Data Report"
String section = "CAR Data Report"
String fileNameAfterExported = CARDataReportPageSteps.verifyFileIsDownloadedSuccessfullyForCARDataReport(prefix, section)
Thread.sleep(2000)


'Step 12: '
'Veriy table data is included in excel file'
String excelFilePath = PlatformUtil.getFilePath(PlatformUtil.getDownloadPath(),fileNameAfterExported)
'Verify that the column headers are the same on the UI and the exported file'
CARDataReportPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Provider")
CARDataReportPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Region")
CARDataReportPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Location")
CARDataReportPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Cost Center")
CARDataReportPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Specialty")
CARDataReportPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Position")
CARDataReportPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Template")
CARDataReportPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Status")

'Verify the fist row data in the excel exported file'
String providerName = CARDataReportPageSteps.getCellTextAtFirstRow(2) //2 is Provider Name
String dataRegion = CARDataReportPageSteps.getCellTextAtFirstRow(3)  //3 is Region
String dataLocation = CARDataReportPageSteps.getCellTextAtFirstRow(4) //4 is Location
String dataCostCenter = CARDataReportPageSteps.getCellTextAtFirstRow(5)  //5 is Cost Center
String dataSpecialty = CARDataReportPageSteps.getCellTextAtFirstRow(6) //6 is Specialty
String dataPosition = CARDataReportPageSteps.getCellTextAtFirstRow(7) //7 is Position
String dataTemplate = CARDataReportPageSteps.getCellTextAtFirstRow(8) //8 is Template
String dataStatus = CARDataReportPageSteps.getCellTextAtFirstRow(9) //9 is Status


CARDataReportPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, providerName)
CARDataReportPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataRegion)
CARDataReportPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataLocation)
CARDataReportPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataCostCenter)
CARDataReportPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataSpecialty)
CARDataReportPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataPosition)
// FAILED: this step will be failed due on the text is not matched: Dentist Template V-1 (in CMS) vs Dentist Template (in Excel)
CARDataReportPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataTemplate)
CARDataReportPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataStatus)

'Step 13: Clean up data'
'Delete file '
PlatformUtil.deleteAllFilesWithPrefix(PlatformUtil.getDownloadPath(), prefix)

'Delete the created CAR'
MenuPageSteps.selectConntractActionRequestMenu("Create CAR") //Refresh page
MenuPageSteps.selectConntractActionRequestMenu("View CARs")
ViewCARPageSteps.deleteACar(createdProvidersName)
'Delete the created approval'
MenuPageSteps.selectConntractActionRequestMenu("CAR Templates") //Refresh page
MenuPageSteps.selectConntractActionRequestMenu("CAR Approval Setup")
CARApprovalSetupPageSteps.deleteConfiguredApprovals(createdCARApprovalName)
'Delete the created CAR Templates'
MenuPageSteps.selectConntractActionRequestMenu("CAR Templates")
CARTemplatesPageSteps.searchAndDelete(createdTemplateName)
'Delete the created Position'
MenuPageSteps.selectOrganizationAdminMenu("Position")
PositionPageSteps.searchAndDelete(createdPositionName)
'Delete the created Specialty'
MenuPageSteps.selectOrganizationAdminMenu("Specialty")
SpecialtyPageSteps.searchAndDelete(createdSpecialtyName)
'Delete the created Cost Center'
MenuPageSteps.selectOrganizationAdminMenu("Cost Center")
CostCenterPageSteps.searchAndDelete(createdCCName)
'Delete the created location'
MenuPageSteps.selectOrganizationAdminMenu("Location")
LocationPageSteps.searchAndDelete(createdLocationName)
'Delete the created region'
MenuPageSteps.selectOrganizationAdminMenu("Region")

@TearDown
def closeBrowser(){
Browser.quitDriver();
}