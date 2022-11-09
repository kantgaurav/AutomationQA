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
import pages.MailTempTopPageSteps
import pages.MenuPageSteps
import pages.PositionPageSteps
import pages.ProvidersPageSteps
import pages.RegionPageSteps
import pages.SpecialtyPageSteps
import pages.ViewCARPageSteps
import pages.YopmailPageSteps
import pages.GmailPageSteps

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
/**
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
**/

String createdProvidersName = "FN_63888"
String createdRegionName = "Reg_66881"
String createdLocationName = "	Loc_58478"
String createdCCName = "CC_38314"
String createdSpecialtyName = "Spec_60484"
String createdPositionName = "Pos_32696"
//String createdCARRequest = "Template_98833"

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
'Select View CAR'
MenuPageSteps.selectConntractActionRequestMenu("View CARs")
'ER:'
'Verify Page title is correct'
ViewCARPageSteps.getBaseSteps().verifyPageTitleIsCorrect("Contract Action Requests")

'Step 10:'
'Approvers modal displays'
ViewCARPageSteps.search(createdProvidersName)
String statusCAR= ViewCARPageSteps.getBaseSteps().getCellTextBaseOnAnotherCellDataTable("CAR_Table", "3", createdProvidersName, "13", "ViewCARPage")//13 is status, 12 is Approvals Complete
AssertSteps.verifyExpectedResult(statusCAR.equals("Sent For Approval"),String.format("The status if CAR is %s",statusCAR),String.format("The status if CAR is NOT apporved, the current status is %s",statusCAR))
'Verify thay users are displayed correctly'
ViewCARPageSteps.verifyNameOfApprover(createdProvidersName, [GlobalVariable.APPROVER_USER1,GlobalVariable.APPROVER_USER2])
'Verify that 2 users have the status = Sent For Approval at Approval Complete column'
ViewCARPageSteps.verifyStatusAtApprovalsCompleteColumn(createdProvidersName, ["Awaiting Approval"], "0/2")

'Step 11:'
'First user approval the request'
ViewCARPageSteps.search(createdProvidersName)
ViewCARPageSteps.getBaseSteps().clickToCellTableButton("Search_Table", "last()","Approve", PageLocatorName.COMMONLOCATORS_PAGE)//last is Action column
'ER:'
'Verify that the status have been change'
ViewCARPageSteps.verifyStatusAtApprovalsCompleteColumn(createdProvidersName, ["Approved","Awaiting Approval"], "1/2")

'Step 12: Second user approval the request'
LoginPageSteps.logout()
LoginPageSteps.login(GlobalVariable.APPROVER_MAIL_USER2, GlobalVariable.COMMON_PW, GlobalVariable.ORG_AUTOMATION, GlobalVariable.ROLE_SYSTEM_ADMIN)
ViewCARPageSteps.approvalCARbyAnotherAccount(createdProvidersName)
LoginPageSteps.logout()
LoginPageSteps.login(GlobalVariable.APPROVER_MAIL_USER1, GlobalVariable.COMMON_PW, GlobalVariable.ORG_AUTOMATION, GlobalVariable.ROLE_SYSTEM_ADMIN)
MenuPageSteps.selectConntractActionRequestMenu("View CARs")
'ER:'
'Verify that The status have been change'
String statusCARUpdate= ViewCARPageSteps.getBaseSteps().getCellTextBaseOnAnotherCellDataTable("CAR_Table", "3", createdProvidersName, "13", "ViewCARPage")
AssertSteps.verifyExpectedResult(statusCARUpdate.equals("Approved"),String.format("The status if CAR is %s",statusCARUpdate),String.format("The status if CAR is NOT apporved, the current status is %s",statusCARUpdate))
ViewCARPageSteps.verifyStatusAtApprovalsCompleteColumn(createdProvidersName, ["Approved"],"2/2")


'Step 13: Check email of approver 1 send:'
'Open gmail email'
Browser.navigateToURL(GlobalVariable.GMAIL_URL)
'ER:'
'The email is sent'
GmailPageSteps.LogintoGmail(GlobalVariable.APPROVER_MAIL_USER1,GlobalVariable.GMAIL_PWD)
GmailPageSteps.SearchOnGmail(createdProvidersName)
'ER:'
'Verify that first approver has received the email'
String expectedApprover1EmailContent = String.format("Contract Action Request for %s",createdProvidersName)
String emailAppr1Content = GmailPageSteps.GetEmailContent(createdProvidersName)
AssertSteps.verifyExpectedResult(emailAppr1Content.contains(expectedApprover1EmailContent))
GmailPageSteps.GmailLogoutAndSignBackiframe()


'Step 14: Check email of approver 2 send:'
'Login to gmail email using second approver'
GmailPageSteps.LogintoGmail(GlobalVariable.APPROVER_MAIL_USER2,GlobalVariable.GMAIL_PWD)
GmailPageSteps.SearchOnGmail(createdProvidersName)
'ER:'
'Verify that second approver has received the email'
String expectedApprover2EmailContent = String.format("Contract Action Request for %s",createdProvidersName)
String emailAppr2Content = GmailPageSteps.GetEmailContent(createdProvidersName)
AssertSteps.verifyExpectedResult(emailAppr2Content.contains(expectedApprover2EmailContent))
GmailPageSteps.GmailLogoutAndSignBackiframe()

'Step 15: Check email of notifier 1:'
'Open gmail email and login as the notifier'
GmailPageSteps.LogintoGmail(GlobalVariable.NOTIFIER_MAIL_USER1,GlobalVariable.GMAIL_PWD)
'ER:'
'The preAction email is sent'
GmailPageSteps.SearchOnGmail(createdProvidersName + " and CAR Created")
String expectedPreActionEmailContent = String.format("The CAR for %s",createdProvidersName)
String preActionEmailContent = GmailPageSteps.GetEmailContent(createdProvidersName)
AssertSteps.verifyExpectedResult(preActionEmailContent.contains(expectedPreActionEmailContent))

'ER:'
'The postAction email is sent'
GmailPageSteps.SearchOnGmail(createdProvidersName + " and CAR Approved")
String expectedPosActionEmailContent = String.format("The CAR for %s",createdProvidersName)
String posActionEmailContent = GmailPageSteps.GetEmailContent(createdProvidersName)
AssertSteps.verifyExpectedResult(posActionEmailContent.contains(expectedPosActionEmailContent))
Browser.navigateToURL(GlobalVariable.URL)

/**
'Step 16: Clean up data'
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
**/

@TearDown
def closeBrowser(){
Browser.quitDriver();
}