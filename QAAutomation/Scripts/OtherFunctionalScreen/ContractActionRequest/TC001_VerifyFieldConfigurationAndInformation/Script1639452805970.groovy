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
import utils.DateTimeUtil

import com.kms.katalon.core.annotation.TearDown

import configs.PageLocatorName

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
String createdTemplateName = CARTemplatesPageSteps.createCarTemplateWithLengthOptions(createdRegionName, createdLocationName, createdCCName, createdSpecialtyName, createdPositionName)
CARTemplatesPageSteps.getBaseSteps().verifyMainPopUpHasContent("Template information has been saved.")
'Create a Providers'
MenuPageSteps.selectOrganizationAdminMenu("Providers")
String createdProvidersName = ProvidersPageSteps.createProvider()
ProvidersPageSteps.getBaseSteps().verifyMainPopUpHasContent("Provider Added Successfully")
'Create CAR Approvals'
MenuPageSteps.selectConntractActionRequestMenu("CAR Approval Setup")
'Create a CAR Approval Setup with Region, Template and Hierachical'
String createdCARApprovalName = CARApprovalSetupPageSteps.createApproval("CAR Approval", "Region", "Hierarchical", 
	createdRegionName, createdLocationName, createdCCName, createdSpecialtyName, createdPositionName, [GlobalVariable.APPROVER_USER1], [GlobalVariable.NOTIFIER_USER1], createdTemplateName,true,true)
'CAR Approval Setup is created successfully'
CARApprovalSetupPageSteps.getBaseSteps().verifyMainPopUpHasContent("Approval information has been saved!")
'Select Create CAR'
MenuPageSteps.selectConntractActionRequestMenu("Create CAR")
'Create CAR page is loaded'
CreateCARPageSteps.getBaseSteps().verifyControlDisplayed("Region_Combobox", PageLocatorName.CREATECAR_PAGE)
'Complete Initiate CAR page'
CreateCARPageSteps.completeInitiateCARFields(createdRegionName, createdLocationName, createdCCName, createdSpecialtyName, createdPositionName)
CreateCARPageSteps.getBaseSteps().clickToControl("Initiate_CAR_Next_Button", PageLocatorName.CREATECAR_PAGE)
'Verify Selecte Template is loaded'
CreateCARPageSteps.getBaseSteps().verifyControlDisplayed("Create_New_Template_Button", PageLocatorName.CREATECAR_PAGE)
'Complete Select Template'
CreateCARPageSteps.completeSelectTemplate(createdTemplateName)
'A Provider screen displays'
CreateCARPageSteps.getBaseSteps().verifyControlDisplayed("Provider_Search_Textbox", PageLocatorName.CREATECAR_PAGE)
'Complete select Provider'
CreateCARPageSteps.completeProviders(createdProvidersName)
'CAR Information is loaded'
CreateCARPageSteps.getBaseSteps().verifyControlDisplayed("Contract_Start_Date_Textbox", PageLocatorName.CREATECAR_PAGE)

'Step 4:'
'Verify the default value for Date Picker'
CreateCARPageSteps.getBaseSteps().verifyControlContainedValueDisplayed("Seven_Date_Info_Textbox", DateTimeUtil.getDateTime(), PageLocatorName.CREATECAR_PAGE) //DateTimeUtil.getDateTime() = the date is filled in Default_Date_Textbox(CAR TEMPLALE)

'Step 5: '
'Fill Contract Start Date and Contract End Date'
CreateCARPageSteps.getBaseSteps().setTextToControl("Contract_Start_Date_Textbox", DateTimeUtil.getDateTime(), PageLocatorName.CREATECAR_PAGE)
CreateCARPageSteps.getBaseSteps().setTextToControl("Contract_End_Date_Textbox", DateTimeUtil.nextDateTime(5), PageLocatorName.CREATECAR_PAGE)
CreateCARPageSteps.getBaseSteps().clearTextInControl("Seven_Date_Info_Textbox", PageLocatorName.CREATECAR_PAGE)
CreateCARPageSteps.getBaseSteps().clickToControl("Save_Button", PageLocatorName.CREATECAR_PAGE) //HAVE TO click on another control to click "Save" button successful
CreateCARPageSteps.getBaseSteps().clickToControl("Save_Button", PageLocatorName.CREATECAR_PAGE)
'ER'
'Get error message'
CreateCARPageSteps.getBaseSteps().verifyMainPopUpHasContent("Invalid Data")
CreateCARPageSteps.getBaseSteps().verifyControlWithTextDisplayed("Error_First_Textbox(Text)_Label","Textbox (Text)_01 is required.", PageLocatorName.CREATECAR_PAGE)
CreateCARPageSteps.getBaseSteps().verifyControlWithTextDisplayed("Error_Second_Textbox(Number)_Label","Textbox (Number)_02 is required.", PageLocatorName.CREATECAR_PAGE)
CreateCARPageSteps.getBaseSteps().verifyControlWithTextDisplayed("Error_Third_TextArea_Label","TextArea_03 is required.", PageLocatorName.CREATECAR_PAGE)
CreateCARPageSteps.getBaseSteps().verifyControlWithTextDisplayed("Error_Fourth_Dropdown_Label","Dropdown_04 is required.", PageLocatorName.CREATECAR_PAGE)
CreateCARPageSteps.getBaseSteps().verifyControlWithTextDisplayed("Error_Fifth_Radio_Label","Radio_05 requires a choice.", PageLocatorName.CREATECAR_PAGE)
CreateCARPageSteps.getBaseSteps().verifyControlWithTextDisplayed("Error_Sixth_Checkbox_Label","Checkbox_06 is required.", PageLocatorName.CREATECAR_PAGE)
CreateCARPageSteps.getBaseSteps().verifyControlWithTextDisplayed("Error_Seventh_DatePicker_Label","Date Picker_07 is required.", PageLocatorName.CREATECAR_PAGE)
String errorDate = String.format("Date Picker_07 must be a date after %s.", DateTimeUtil.getDateTime()) //DateTimeUtil.getDateTime() = the date is filled in Minimum_Date_Textbox(CAR TEMPLALE)
CreateCARPageSteps.getBaseSteps().verifyControlWithTextDisplayed("Error_Seventh_DatePicker2_Label",errorDate, PageLocatorName.CREATECAR_PAGE)
//
//MISSING ERROR MESSAGE OF Currency, UPDATE LATER
//

'Step 6: '
'Fill invalid data for Textbox (Text)'
String dataForTextboxText = "hello word1"  //minTextBox = "1" , maxTextBox = "10", set up in CAR Template page
CreateCARPageSteps.getBaseSteps().setTextToControl("First_Info_Textbox", dataForTextboxText, PageLocatorName.CREATECAR_PAGE)
'ER:'
'Verify the error message at Textbox (Text)'
CreateCARPageSteps.getBaseSteps().verifyControlWithTextDisplayed("Error_First_Textbox(Text)_Label","Textbox (Text)_01 must be less than 10 characters long.", PageLocatorName.CREATECAR_PAGE)

'Step 7: '
'Fill invalid data for Textbox (Number)'
String dataForTextboxNumber = "11"  //minTextBox = "1" , maxTextBox = "10", set up in CAR Template page
CreateCARPageSteps.getBaseSteps().setTextToControl("Second_Info_Textbox", dataForTextboxNumber, PageLocatorName.CREATECAR_PAGE)
'ER:'
'Verify the error message at Textbox (Number)'
CreateCARPageSteps.getBaseSteps().verifyControlWithTextDisplayed("Error_Second_Textbox(Number)_Label","Textbox (Number)_02 must be less than 10.", PageLocatorName.CREATECAR_PAGE)

'Step 8: '
'Fill invalid data for TextArea'
String dataForTextArea = "12345abcdef" //minTextBox = "1" , maxTextBox = "10", set up in CAR Template page
CreateCARPageSteps.getBaseSteps().setTextToControl("Third_Info_Textbox", dataForTextArea, PageLocatorName.CREATECAR_PAGE)
'ER:'
'Verify the error message at TextArea'
CreateCARPageSteps.getBaseSteps().verifyControlWithTextDisplayed("Error_Third_TextArea_Label","TextArea_03 must be less than 10 characters long.", PageLocatorName.CREATECAR_PAGE)

'Step 9:'
'Verify that Alabama is displayed in dropdown menu'
String dropdownValue = "Alabama" //Data Source = US States in CAR Template page
CreateCARPageSteps.getBaseSteps().selectItemByTextInComboBoxUsingAction("Fourth_Infor1_Combobox", dropdownValue, PageLocatorName.CREATECAR_PAGE)
'ER'
'Verify the error message at dropdown'
CreateCARPageSteps.getBaseSteps().verifyControlWithTextDisplayed("Dropdown_Textbox",dropdownValue, PageLocatorName.CREATECAR_PAGE)

'Step 10:'
'Verify that 2 options are displayed in radio section' 
String xpathRadioLabel = CreateCARPageSteps.getBaseSteps().getXpathFromControl("Radio_Label", PageLocatorName.CREATECAR_PAGE)
CreateCARPageSteps.getBaseSteps().verifyControlByReplacedTextInXpathDisplayed(xpathRadioLabel, "Radio_Opt1") //Radio_Opt1 is set up in CAR Template page
CreateCARPageSteps.getBaseSteps().verifyControlByReplacedTextInXpathDisplayed(xpathRadioLabel, "Radio_Opt2") //Radio_Opt1 is set up in CAR Template page

'Step 11:'
'Verify that 2 options are displayed in checkbox section'
String xpathCheckboxLabel = CreateCARPageSteps.getBaseSteps().getXpathFromControl("Checkbox_Label", PageLocatorName.CREATECAR_PAGE)
CreateCARPageSteps.getBaseSteps().verifyControlByReplacedTextInXpathDisplayed(xpathCheckboxLabel, "Checkbox_Opt1") //Checkbox_Opt1 is set up in CAR Template page
CreateCARPageSteps.getBaseSteps().verifyControlByReplacedTextInXpathDisplayed(xpathCheckboxLabel, "Checkbox_Opt2") //Checkbox_Opt1 is set up in CAR Template page

'Step 12: '
'Fill invalid data for Date Picker'
String invalidDate = DateTimeUtil.nextDateTime(6);
String errorDateDislayed = String.format("Date Picker_07 must be a date before %s.", DateTimeUtil.nextDateTime(5)) //DateTimeUtil.nextDateTime(5) = the date is filled in Maximum_Date_Textbox (CAR TEMPLALE)
CreateCARPageSteps.getBaseSteps().setTextToControl("Seven_Date_Info_Textbox", invalidDate, PageLocatorName.CREATECAR_PAGE)
CreateCARPageSteps.getBaseSteps().clickToControl("Save_Button", PageLocatorName.CREATECAR_PAGE)
'ER:'
'Verify the error message at Date Picker'
CreateCARPageSteps.getBaseSteps().verifyControlWithTextDisplayed("Error_Seventh_DatePicker_Label",errorDateDislayed, PageLocatorName.CREATECAR_PAGE)

'Step 13: '
'Fill invalid data for Currency'
String invalidCurrency = "5,000,000,000,000,000" //minCurrency = "1" , maxCurrency = "1000", set up in CAR Template page
CreateCARPageSteps.getBaseSteps().setTextToControl("Eight_Currency_Info_Textbox", invalidCurrency, PageLocatorName.CREATECAR_PAGE)
'ER:'
'Verify the error message at Currency'
//
//MISSING ERROR MESSAGE OF Currency, UPDATE LATER
//

'Step 13: Clean up data'
'Delete the created approval name'
MenuPageSteps.selectConntractActionRequestMenu("CAR Templates") //Refresh page
MenuPageSteps.selectConntractActionRequestMenu("CAR Approval Setup")
CARApprovalSetupPageSteps.deleteConfiguredApprovals(createdCARApprovalName)
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


