import com.kms.katalon.core.annotation.TearDown

import configs.PageLocatorName
import core.AssertSteps
import core.Browser
import internal.GlobalVariable
import pages.CARApprovalSetupPageSteps
import pages.CARTemplatesPageSteps
import pages.CostCenterPageSteps
import pages.CreateCARPageSteps
import pages.HeaderPageSteps
import pages.LocationPageSteps
import pages.LoginPageSteps
import pages.MenuPageSteps
import pages.PositionPageSteps
import pages.ProvidersPageSteps
import pages.RegionPageSteps
import pages.SpecialtyPageSteps
import pages.ViewCARPageSteps
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

'Step 3: Prepare Data'
'Create a Region'
MenuPageSteps.selectOrganizationAdminMenu("Region")
String createdRegionName = RegionPageSteps.createRegion()
RegionPageSteps.getBaseSteps().verifyMainPopUpHasContent("Region saved")
'Create Location'
MenuPageSteps.selectOrganizationAdminMenu("Location")
String createdLocationName = LocationPageSteps.createLocation(createdRegionName)
LocationPageSteps.getBaseSteps().verifyMainPopUpHasContent("Location saved")
'Create Cost Center'
MenuPageSteps.selectOrganizationAdminMenu("Cost Center")
String createdCCName = CostCenterPageSteps.createCostCenter(createdRegionName, createdLocationName)
LocationPageSteps.getBaseSteps().verifyMainPopUpHasContent("Cost Center saved")
'Create a Specialty'
MenuPageSteps.selectOrganizationAdminMenu("Specialty")
String createdSpecialtyName = SpecialtyPageSteps.createSpecialty()
SpecialtyPageSteps.getBaseSteps().verifyMainPopUpHasContent("Specialty saved")
'Create a Position'
MenuPageSteps.selectOrganizationAdminMenu("Position")
String createdPositionName = PositionPageSteps.createPosition()
PositionPageSteps.getBaseSteps().verifyMainPopUpHasContent("Position saved")
'Create CAR Templates'
MenuPageSteps.selectConntractActionRequestMenu("CAR Templates")
String createdTemplateName = CARTemplatesPageSteps.createCarTemplateForCAR(createdRegionName, createdLocationName, createdCCName, createdSpecialtyName, createdPositionName)
CARTemplatesPageSteps.getBaseSteps().verifyMainPopUpHasContent("Template information has been saved.")
'Create a Providers'
MenuPageSteps.selectOrganizationAdminMenu("Providers")
String createdProvidersName = ProvidersPageSteps.createProvider()
ProvidersPageSteps.getBaseSteps().verifyMainPopUpHasContent("Provider Added Successfully")
'Create a Contract Approvals'
MenuPageSteps.selectConntractActionRequestMenu("CAR Approval Setup")
String createdCTApprovalName = CARApprovalSetupPageSteps.createApproval("CAR Approval", "Cost Center", "Hierarchical", createdRegionName, createdLocationName, createdCCName, createdSpecialtyName, createdPositionName,[GlobalVariable.APPROVER_USER1],[GlobalVariable.NOTIFIER_USER1])
CARApprovalSetupPageSteps.getBaseSteps().verifyMainPopUpHasContent("Approval information has been saved!")
CARApprovalSetupPageSteps.getBaseSteps().clickToControl("Return_To_Approvals_Button", PageLocatorName.CARAPPROVAL_PAGE)

'Step 4:'
'Select Create CAR'
MenuPageSteps.selectConntractActionRequestMenu("Create CAR")
'ER:'
'Create CAR page is loaded'
CreateCARPageSteps.getBaseSteps().verifyControlDisplayed("Region_Combobox", "CreateCARPage")

'Step 5:'
'Complete Initiate CAR page'
CreateCARPageSteps.completeInitiateCARFields(createdRegionName, createdLocationName, createdCCName, createdSpecialtyName, createdPositionName)
CreateCARPageSteps.getBaseSteps().clickToControl("Initiate_CAR_Next_Button", "CreateCARPage")
'ER:'
'Verify Selecte Template is loaded'
CreateCARPageSteps.getBaseSteps().verifyControlDisplayed("Create_New_Template_Button", "CreateCARPage")

'Step 6:'
'Complete Select Template'
CreateCARPageSteps.completeSelectTemplate(createdTemplateName)
'ER:'
'A Provider screen displays'
CreateCARPageSteps.getBaseSteps().verifyControlDisplayed("Provider_Search_Textbox", "CreateCARPage")

'Step 7:'
'Complete select Provider'
CreateCARPageSteps.completeProviders(createdProvidersName)
'ER:'
'CAR Information is loaded'
CreateCARPageSteps.getBaseSteps().verifyControlDisplayed("Contract_Start_Date_Textbox", "CreateCARPage")

'Step 8:'
'Complete CAR Information'
CreateCARPageSteps.completeCARInformation()
'ER:'
'Review and Finalize screen displays'
CreateCARPageSteps.getBaseSteps().verifyControlContainedTextDisplayed("Text_Center_Label", "Approval from the following users will be required for Contract to be executed:", "CreateCARPage")

'Step 9:'
'Click Next button'
CreateCARPageSteps.getBaseSteps().clickToControl("Approvers_Next_Button", "CreateCARPage")
'ER:'
'Review and Finalize page is loaded'
CreateCARPageSteps.getBaseSteps().verifyControlDisplayed("Contract_Action_Request_Content_Label", "CreateCARPage")
'Contract Action Request is loaded correctly'
CreateCARPageSteps.getBaseSteps().verifyControlContainedTextDisplayed("Contract_Action_Request_Content_Label", "New Hire", "CreateCARPage")

'Step 10:'
'Click Submit for Approval'
CreateCARPageSteps.getBaseSteps().clickToControl("Submit_For_Approval_Button", "CreateCARPage")
'ER:'
'A success message is displayed'
CreateCARPageSteps.getBaseSteps().verifyMainPopUpHasContent("CAR has been sent for Approval.")
CreateCARPageSteps.getBaseSteps().verifyControlDisabled("Submit_For_Approval_Button", "CreateCARPage")

'Step 11:'
'Select View CAR'
MenuPageSteps.selectConntractActionRequestMenu("View CARs")
'ER:'
'Verify Page title is correct'
ViewCARPageSteps.getBaseSteps().verifyPageTitleIsCorrect("Contract Action Requests")

'Step 12:'
'Search the createdCAR' // Common is first row
ViewCARPageSteps.search(createdProvidersName)
String statusCAR= ViewCARPageSteps.getBaseSteps().getCellTextBaseOnAnotherCellDataTable("CAR_Table", "3", createdRegionName, "13", "ViewCARPage")//13 is status, 12 is Approvals Complete
'ER:'
'The status of CAR is Sent For Approval'
AssertSteps.verifyExpectedResult(statusCAR.equals("Sent For Approval"),String.format("The status if CAR is %s",statusCAR),String.format("The status if CAR is NOT apporved, the current status is %s",statusCAR))


'Step 4:'
'Select Export button'
ViewCARPageSteps.getBaseSteps().clickToControl("Export_Button", PageLocatorName.COMMONLOCATORS_PAGE)
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


'Step 13: Clean up data'
'Delete the created CAR'
MenuPageSteps.selectConntractActionRequestMenu("Create CAR") //Refresh page
MenuPageSteps.selectConntractActionRequestMenu("View CARs")
ViewCARPageSteps.deleteACar(createdProvidersName)
'Delete the created approval name'
MenuPageSteps.selectConntractActionRequestMenu("CAR Templates") //Refresh page
MenuPageSteps.selectConntractActionRequestMenu("CAR Approval Setup")
CARApprovalSetupPageSteps.deleteConfiguredApprovals(createdCTApprovalName)
'Delete the created CAR Templates'
MenuPageSteps.selectConntractActionRequestMenu("CAR Templates")
CARTemplatesPageSteps.searchAndDelete(createdTemplateName)
'Delete the created Providers'
MenuPageSteps.selectOrganizationAdminMenu("Providers")
ProvidersPageSteps.searchAndDelete(createdProvidersName)
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

