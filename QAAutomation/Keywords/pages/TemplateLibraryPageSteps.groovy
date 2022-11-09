package pages

import configs.CMSStrings
import configs.PageLocatorName
import utils.Utilities


public class TemplateLibraryPageSteps extends CommonPageSteps{


	public static void verifyPageIsLoaded() {
		getBaseSteps().verifyControlDisplayed("Search_Template_Textbox", PageLocatorName.TEMPLATELIBRARY_PAGE)
	}


	public static void openTemplate(String templateName) {
		String xpath = "//div[contains(@class,'card-body')]/div/div[contains(.,'%s')]/following-sibling::div//button"
		getBaseSteps().clickToControlByReplacedTextInXpath(xpath, templateName)
	}

	public static String createTemplateLibrary(String carTemplateName, boolean isApprovalRequireChecked = true, boolean isSignatureRequiredChecked=true) 
	{

		String number = Utilities.generateRandomNumber();
		String templateLibraryName = String.format("tln_%s",number)

		getBaseSteps().clickToControl("Create_Contract_Template_Button", PageLocatorName.TEMPLATELIBRARY_PAGE)
		getBaseSteps().setTextToControl("Name_Textbox",templateLibraryName,PageLocatorName.TEMPLATELIBRARY_PAGE)
		getBaseSteps().setTextToControl("Description_Textbox", templateLibraryName,PageLocatorName.TEMPLATELIBRARY_PAGE)

		if(!isApprovalRequireChecked) {
			getBaseSteps().clickToControl("Approval_Required_Checkbox", PageLocatorName.TEMPLATELIBRARY_PAGE)
		}
		if(!isSignatureRequiredChecked) {
			getBaseSteps().clickToControl("Signature_Required_Checkbox", PageLocatorName.TEMPLATELIBRARY_PAGE)
		}
		
		getBaseSteps().clickToControl("Document_Fields_Button", PageLocatorName.TEMPLATELIBRARY_PAGE)
		getBaseSteps().clickToControlByText("First Name")
		getBaseSteps().clickToControlByText("Middle Initals")
		getBaseSteps().clickToControlByText("Last Name")
		getBaseSteps().clickToControlByText("Address Line 1")
		getBaseSteps().clickToControlByText("City")
		getBaseSteps().clickToControlByText("State")
		getBaseSteps().clickToControlByText("Zip")
		getBaseSteps().clickToControl("Document_Field_Position_Button", PageLocatorName.TEMPLATELIBRARY_PAGE)
		getBaseSteps().clickToControlByText("Email")
		getBaseSteps().clickToControlByText("PhoneNumber")

		getBaseSteps().clickToControl("Template_Fields_Button", PageLocatorName.TEMPLATELIBRARY_PAGE)
		getBaseSteps().clickToControlByText("Region Name")
		getBaseSteps().clickToControlByText("Location Name")
		getBaseSteps().clickToControlByText("Cost Center Name")
		getBaseSteps().clickToControlByText("Specialty Name")
		getBaseSteps().clickToControlByText("Position Name")
		//Signature field
		getBaseSteps().clickToControlByText("Provider Signature")
		getBaseSteps().clickToControlByText("Provider Date Signed")
		getBaseSteps().clickToControlByText("Contract Executed Date")
		getBaseSteps().clickToControlByText("Contract Executed Date (Long)")
		getBaseSteps().clickToControlByText("Add Check Box")
		getBaseSteps().clickToControlByText("Provider 1 Check Box")
		getBaseSteps().clickToControlByText("Add Text Box")
		getBaseSteps().clickToControlByText("Provider 1 Text Box")
		getBaseSteps().clickToControlByText("Add Optional Provider Sign")
		getBaseSteps().clickToControlByText("Provider 1 Optional Sign")
		getBaseSteps().clickToControlByText("Add Signer")
		getBaseSteps().clickToControlByText("Signer 1 First Name")
		getBaseSteps().clickToControlByText("Signer 1 Last Name")
		getBaseSteps().clickToControlByText("Signer 1 Signature")
		getBaseSteps().clickToControlByText("Signer 1 Date Signed")


		//User Fields
		getBaseSteps().clickToControlByText("Current User")
		getBaseSteps().clickToControlByText("Current User's Role")

		//CAR Template
		getBaseSteps().selectItemByTextInComboBoxUsingAction("CAR_Template_Combobox", carTemplateName, PageLocatorName.TEMPLATELIBRARY_PAGE)
		getBaseSteps().clickToControl("CAR_Field_Button", PageLocatorName.TEMPLATELIBRARY_PAGE)
		getBaseSteps().clickToControlByText("Textbox (Text)_01")
		getBaseSteps().clickToControlByText("Textbox (Number)_02")
		getBaseSteps().clickToControlByText("TextArea_03")
		getBaseSteps().clickToControlByText("Dropdown_04")
		getBaseSteps().clickToControlByText("Dropdown_05")
		getBaseSteps().clickToControlByText("Dropdown_06")
		getBaseSteps().clickToControlByText("Radio_05")
		getBaseSteps().clickToControlByText("Checkbox_06")
		getBaseSteps().clickToControlByText("Date Picker_07")
		getBaseSteps().clickToControlByText("Currency_08")

		if(getBaseSteps().isControlDisplayed("Scroll_Right_Menu_Button", PageLocatorName.TEMPLATELIBRARY_PAGE)) {
			getBaseSteps().clickToControl("Scroll_Right_Menu_Button", PageLocatorName.TEMPLATELIBRARY_PAGE)
		}
		getBaseSteps().clickToControl("Save_Menu_Button", PageLocatorName.TEMPLATELIBRARY_PAGE)

		return templateLibraryName
	}

	public static void SearchTemplate(String templateName) {
		getBaseSteps().verifyControlDisplayed("SearchTemplate_Input", PageLocatorName.TEMPLATELIBRARY_PAGE)
		getBaseSteps().setTextToControl("SearchTemplate_Input", templateName, PageLocatorName.TEMPLATELIBRARY_PAGE)

		getBaseSteps().clickToControlByAction("SearchTemplate_Result", PageLocatorName.TEMPLATELIBRARY_PAGE)
		getBaseSteps().doubleClickToControl("SearchTemplate_Result", PageLocatorName.TEMPLATELIBRARY_PAGE)
		getBaseSteps().clickToControl("SearchTemplate_Result", PageLocatorName.TEMPLATELIBRARY_PAGE)
		//		AssertSteps.verifyExpectedResult(columnText.equals(approvalName), "The '" + columnText + "' did not match expected value of '" + approvalName + "'")
	}

	public static void filterWith(String region, String location, String ccenter,String specialty, String position) {
		getBaseSteps().clickToControl("Filter_Button", PageLocatorName.TEMPLATELIBRARY_PAGE)
		getBaseSteps().selectItemByTextInComboBoxUsingAction("Filter_Region_Combobox", region,true,true, PageLocatorName.TEMPLATELIBRARY_PAGE)
		getBaseSteps().selectItemByTextInComboBoxUsingAction("Filter_Location_Combobox", location,true,true, PageLocatorName.TEMPLATELIBRARY_PAGE)
		getBaseSteps().selectItemByTextInComboBoxUsingAction("Filter_Cost_Center_Combobox", ccenter,true,true, PageLocatorName.TEMPLATELIBRARY_PAGE)
		getBaseSteps().selectItemByTextInComboBoxUsingAction("Filter_Specialty_Combobox", specialty,true,true, PageLocatorName.TEMPLATELIBRARY_PAGE)
		getBaseSteps().selectItemByTextInComboBoxUsingAction("Filter_Position_Combobox", position,true,true, PageLocatorName.TEMPLATELIBRARY_PAGE)
		getBaseSteps().clickToControl("Filter_Apply_Filter_Button", PageLocatorName.TEMPLATELIBRARY_PAGE)
		getBaseSteps().waitForProgressBarDisappear()
		getBaseSteps().clickToControl("Fitler_Close_Button", PageLocatorName.TEMPLATELIBRARY_PAGE)
	}

	public static void deleteTemplate(String region, String location, String ccenter,String specialty, String position) {
		String items = "//div[contains(@x-placement,'bottom-right')]//button[contains(text(),'%s')]"
		filterWith( region,  location,  ccenter, specialty,  position)
		'Click on Delete'
		if(getBaseSteps().isControlDisplayed("Arrow_Button", PageLocatorName.TEMPLATELIBRARY_PAGE)) {
			getBaseSteps().clickToControl("Arrow_Button", PageLocatorName.TEMPLATELIBRARY_PAGE)
			getBaseSteps().clickToControlByReplacedTextInXpath(items, "Delete")
		}
	}

	public static void downloadTemplateAtFirstItem() {
		String items = "//div[contains(@x-placement,'bottom-right')]//button[contains(text(),'%s')]"
		getBaseSteps().clickToControl("First_Dropdown_Menu", PageLocatorName.TEMPLATELIBRARY_PAGE)
		getBaseSteps().clickToControlByReplacedTextInXpath(items, "Download as Word Document")
	}
	
	public static void verifyContentData(String filePath) {
		String firsName = "Provider First Name"
		String middleName = "Provider Middle Initals"
		String lastName = "Provider Last Name"
		String expectedValue = String.format("«%s»«%s»«%s»",firsName, middleName,lastName)
		getBaseSteps().verifyWordFileContentIsCorrect(filePath,expectedValue )
	}
}
