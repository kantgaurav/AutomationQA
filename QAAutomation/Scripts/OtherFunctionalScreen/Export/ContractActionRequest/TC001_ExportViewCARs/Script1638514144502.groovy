import com.kms.katalon.core.annotation.TearDown

import configs.PageLocatorName
import core.Browser
import internal.GlobalVariable
import pages.HeaderPageSteps
import pages.LoginPageSteps
import pages.MenuPageSteps
import pages.RegionPageSteps
import pages.ViewCARPageSteps
import utils.PlatformUtil
import configs.PageLocatorName
import core.AssertSteps
import pages.CARApprovalSetupPageSteps
import pages.CARTemplatesPageSteps
import pages.CostCenterPageSteps
import pages.CreateCARPageSteps
import pages.LocationPageSteps
import pages.MailTempTopPageSteps
import pages.PositionPageSteps
import pages.ProvidersPageSteps
import pages.SpecialtyPageSteps
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
ProvidersPageSteps.getBaseSteps().verifyMainPopUpHasContent("Provider Added Successfully")
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

'Create CAR Templates'
MenuPageSteps.selectConntractActionRequestMenu("CAR Templates")
String createdTemplateName = CARTemplatesPageSteps.createCarTemplateForCAR(createdRegionName, createdLocationName, createdCCName, createdSpecialtyName, createdPositionName)
'CAR Templates is created successfully'
CARTemplatesPageSteps.getBaseSteps().verifyMainPopUpHasContent("Template information has been saved.")

'Step 4:'
'Select CAR Approval Setup '
MenuPageSteps.selectConntractActionRequestMenu("CAR Approval Setup")
'ER:'
'The CAR Approval Setup page is loaded'
CARApprovalSetupPageSteps.verifyPageIsLoaded()

'Step 5:'
'Create a CAR Approval Setup with Region, Template and Hierachical'
String createdCARApprovalName = CARApprovalSetupPageSteps.createApproval("CAR Approval", "Region", "Hierarchical", 
	createdRegionName, createdLocationName, createdCCName, createdSpecialtyName, createdPositionName, [GlobalVariable.APPROVER_USER1,GlobalVariable.APPROVER_USER2], [GlobalVariable.NOTIFIER_USER1], createdTemplateName,true,true)
'ER:'
'CAR Approval Setup is created successfully'
CARApprovalSetupPageSteps.getBaseSteps().verifyMainPopUpHasContent("Approval information has been saved!")

'Step 6:'
'Click to Return to Approvals button'
CARApprovalSetupPageSteps.getBaseSteps().clickToControl("Return_To_Approvals_Button", PageLocatorName.CARAPPROVAL_PAGE)
'ER:'
'The CAR Approval Setup page is loaded'
CARApprovalSetupPageSteps.verifyPageIsLoaded()

'Step 7:'
'Search the created approval name'
CARApprovalSetupPageSteps.searchByName(createdCARApprovalName)
'ER:'
'The created approval name is diplayed'
CARApprovalSetupPageSteps.verifyDataIsDisplayedAfterSearch("2", createdCARApprovalName)//2 is Create CAR approval name

'Step 8: '
'Create a Car'
MenuPageSteps.selectConntractActionRequestMenu("Create CAR")
CreateCARPageSteps.createCARForApproval(createdRegionName, createdLocationName, createdCCName, createdSpecialtyName, createdPositionName, createdTemplateName, createdProvidersName)
'ER:'
'A success message is displayed'
CreateCARPageSteps.getBaseSteps().verifyMainPopUpHasContent("CAR has been sent for Approval.")

'Step 9:'
'Select View CARs'
MenuPageSteps.selectConntractActionRequestMenu("View CARs")
'ER:'
'Verify Page title is correct'
ViewCARPageSteps.getBaseSteps().verifyPageTitleIsCorrect("Contract Action Requests")

'Step 10:'
'Filter on View CARs by Region'
ViewCARPageSteps.getBaseSteps().clickToControl("Filter_Button", PageLocatorName.VIEWCAR_PAGE)
ViewCARPageSteps.filterWith(createdRegionName)

'Step 4:'
'Select Export button'
ViewCARPageSteps.getBaseSteps().clickToControl("Export_Button", PageLocatorName.COMMONLOCATORS_PAGE)
Thread.sleep(2000)

'ER:'
'Verify the attached is downloaded'
String prefix = "Contract Action Requests Report"
String section = "Contract Action Requests"
String fileNameAfterExported = RegionPageSteps.verifyFileIsDownloadedSuccessfully(prefix, section)

'Step 5: '
String excelFilePath = PlatformUtil.getFilePath(PlatformUtil.getDownloadPath(),fileNameAfterExported)
//For column NOT in order
ViewCARPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Provider")
ViewCARPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Region")
ViewCARPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Location")
ViewCARPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Cost Center")
ViewCARPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Specialty")
ViewCARPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Position")
ViewCARPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Template")
ViewCARPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Created DateTime")
ViewCARPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Approvals Complete")
ViewCARPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, "Status")

'Verify the fist row data in the excel exported file'
//For column NOT in order
String dataProvider = ViewCARPageSteps.getCellTextAtFirstRow(3) //3 is the index of column
String dataRegion = ViewCARPageSteps.getCellTextAtFirstRow(5)  //4 is the index of column
String dataLocation = ViewCARPageSteps.getCellTextAtFirstRow(6) //5 is the index of column
String dataCostCenter = ViewCARPageSteps.getCellTextAtFirstRow(7)  //6 is the index of column
String dataSpecialty = ViewCARPageSteps.getCellTextAtFirstRow(8) //8 is the index of column
String dataPosition = ViewCARPageSteps.getCellTextAtFirstRow(9) //9 is the index of column
String dataTemplate = ViewCARPageSteps.getCellTextAtFirstRow(10) //10 is the index of column
String dataCreatedDateTime = ViewCARPageSteps.getCellTextAtFirstRow(11) //11 is the index of column
String dataApprovalsComplete = ViewCARPageSteps.getCellTextAtFirstRow(12) //13 is the index of column
String dataStatus = ViewCARPageSteps.getCellTextAtFirstRow(13) //13 is the index of column
ViewCARPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataProvider)
ViewCARPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataRegion)
ViewCARPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataLocation)
ViewCARPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataCostCenter)
ViewCARPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataSpecialty)
ViewCARPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataPosition)
ViewCARPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataTemplate)
//ViewCARPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataCreatedDateTime) //INCORRECT FORMAT OF DATETIME: 12/10/2021 12:58 PM and 10/12/2021 12:58 PM
ViewCARPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataApprovalsComplete)
ViewCARPageSteps.getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataStatus)

'Step 6: Clean up data'
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
RegionPageSteps.searchAndDelete(createdRegionName)

@TearDown
def closeBrowser(){
Browser.quitDriver();
}

