import com.kms.katalon.core.annotation.TearDown

import core.AssertSteps
import core.Browser
import internal.GlobalVariable
import pages.CreateCARPageSteps
import pages.HeaderPageSteps
import pages.LoginPageSteps
import pages.MenuPageSteps
import pages.ViewCARPageSteps
import utils.Utilities

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
'Select Create CAR'
MenuPageSteps.selectConntractActionRequestMenu("Create CAR")
'ER:'
'Create CAR page is loaded'
CreateCARPageSteps.getBaseSteps().verifyControlDisplayed("Region_Combobox", "CreateCARPage")

'Step 4:'
'Complete Initiate CAR page'
CreateCARPageSteps.completeInitiateCARFields(region, location, costCenter, specialty, position)
CreateCARPageSteps.getBaseSteps().clickToControl("Initiate_CAR_Next_Button", "CreateCARPage")
'ER:'
'Verify Selecte Template is loaded'
CreateCARPageSteps.getBaseSteps().verifyControlDisplayed("Create_New_Template_Button", "CreateCARPage")

'Step 5:'
'Complete Select Template'
CreateCARPageSteps.completeSelectTemplate(templateName)
'ER:'
'A success message is displayed'
CreateCARPageSteps.getBaseSteps().verifyMainPopUpHasContent("A matching approval configuration was found.")

'Step 6:'
'Complete select Provider'
CreateCARPageSteps.completeProviders(emailProvider)
'ER:'
'CAR Information is loaded'
CreateCARPageSteps.getBaseSteps().verifyControlDisplayed("Contract_Start_Date_Textbox", "CreateCARPage")

'Step 7:'
'Complete CAR Information'
String employeeName = "empl_".concat(Utilities.generateRandomNumber())
CreateCARPageSteps.completeCARInformation(employeeName, "sup_".concat(Utilities.generateRandomNumber()), Utilities.generateRandomNumber(2), 
	Utilities.generateRandomNumber(2), "newEmpl_".concat(Utilities.generateRandomNumber()), "Full Plan", "New Hire", "M", "Bay Area", "1000")
CreateCARPageSteps.getBaseSteps().clickToControl("Save_Button", "CreateCARPage")

'ER:'
'A success message is displayed'
CreateCARPageSteps.getBaseSteps().verifyMainPopUpHasContent("CAR information has been saved.")
'Approval Next button is loaded'
CreateCARPageSteps.getBaseSteps().clickToControl("CAR_Information_Next_Button", "CreateCARPage")
CreateCARPageSteps.getBaseSteps().verifyControlDisplayed("Approvers_Next_Button", "CreateCARPage")

'Step 8:'
'Click Next button'
CreateCARPageSteps.getBaseSteps().clickToControl("Approvers_Next_Button", "CreateCARPage")
'ER:'
'Review and Finalize page is loaded'
CreateCARPageSteps.getBaseSteps().verifyControlDisplayed("Contract_Action_Request_Content_Label", "CreateCARPage")
'Contract Action Request is loaded correctly'
CreateCARPageSteps.getBaseSteps().verifyControlContainedTextDisplayed("Contract_Action_Request_Content_Label", employeeName, "CreateCARPage")

'Step 9:'
'Click Submit for Approval'
CreateCARPageSteps.getBaseSteps().clickToControl("Submit_For_Approval_Button", "CreateCARPage")
'ER:'
'A success message is displayed'
CreateCARPageSteps.getBaseSteps().verifyMainPopUpHasContent("CAR has been sent for Approval.")
CreateCARPageSteps.getBaseSteps().verifyControlDisabled("Submit_For_Approval_Button", "CreateCARPage")

'Step 10:'
'Select View CAR'
MenuPageSteps.selectConntractActionRequestMenu("View CARs")
'ER:'
'Verify Page title is correct'
ViewCARPageSteps.getBaseSteps().verifyPageTitleIsCorrect("Contract Action Requests")

'Step 11:'
'Click to the created CAR' // Common is first row
ViewCARPageSteps.getBaseSteps().clickToCellTableLinkBaseOnAnotherCellDataTable("CAR_Table", "13", "Sent For Approval", "12", "ViewCARPage")//13 is status, 12 is Approvals Complete
'ER:'
'Approvers modal displays'
ViewCARPageSteps.getBaseSteps().verifyMainModalHasContent("Awaiting Approval")

'Step 12:'
'Delete the CARs by change status to Abdandoned'
ViewCARPageSteps.getBaseSteps().clickToCellTableButtonBaseOnAnotherCellDataTable("CAR_Table", "13", "Sent For Approval", "1", "ViewCARPage")//13 is status, 1 is 3 dots
ViewCARPageSteps.getBaseSteps().selectItemInThreeDotCombobox("Delete")
'ER:'
'A message is displayed and status change to abandoned'
ViewCARPageSteps.getBaseSteps().verifyMainPopUpHasContent("CAR has been abandoned.")
String status = ViewCARPageSteps.getBaseSteps().getCellTableText("CAR_Table", "13", "ViewCARPage")
AssertSteps.verifyExpectedResult(status.equals("Abandoned"),"The status is changed to Abandoned","The status is not changed to Abandoned")


@TearDown
def closeBrowser(){
Browser.quitDriver();
}







