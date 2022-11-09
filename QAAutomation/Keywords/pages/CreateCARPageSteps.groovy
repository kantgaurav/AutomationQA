package pages
import configs.PageLocatorName
import configs.Timeout
import utils.DateTimeUtil
import utils.Utilities


public class CreateCARPageSteps extends CommonPageSteps {


	public static void completeInitiateCARFields(String region, String location, String costCenter, String specialty, String position) {
		getBaseSteps().selectItemByTextInComboBoxUsingAction("Region_Combobox", region, "CreateCARPage")
		getBaseSteps().selectItemByTextInComboBoxUsingAction("Location_Combobox", location, "CreateCARPage")
		getBaseSteps().selectItemByTextInComboBoxUsingAction("Cost_Center_Combobox", costCenter, "CreateCARPage")
		getBaseSteps().selectItemByTextInComboBoxUsingAction("Specialty_Combobox", specialty, "CreateCARPage")
		getBaseSteps().selectItemByTextInComboBoxUsingAction("Position_Combobox", position, "CreateCARPage")
	}

	public static void completeSelectTemplate(String templateName) {
		getBaseSteps().setTextToControl("Select_Template_Search_Textbox", templateName, "CreateCARPage")
		getBaseSteps().waitForProgressBarDisappear()
		getBaseSteps().clickToControl("Select_Template_Button", "CreateCARPage")
	}

	public static void completeProviders(String providerName) {
		getBaseSteps().setTextToControl("Provider_Search_Textbox", providerName, "CreateCARPage")
		getBaseSteps().waitForProgressBarDisappear()
		getBaseSteps().clickToCellTableButton("Provider_Table", "7","Select", "CreateCARPage")//7 is last col
	}

	public static void completeCARInformation() {


		String contractStartDate = DateTimeUtil.getDateTime();
		String contractEndDate = DateTimeUtil.nextDateTime(5);
		String employeeName = "empl_".concat(Utilities.generateRandomNumber())
		String supervisorName =  "sup_".concat(Utilities.generateRandomNumber())
		String emplNumber = Utilities.generateRandomNumber(2)
		String compensation = "New Hire"
		String gender ="Radio_Opt1"
		String hospitalBranch = "Checkbox_Opt1"
		String salary = "1000"
		String dropdown1Value = 'User defined 1'
		String dropdown2Value = 'New Hire'
		String dropdown3Value = 'Alaska'

		getBaseSteps().setTextToControl("Contract_Start_Date_Textbox", contractStartDate, "CreateCARPage")
		getBaseSteps().setTextToControl("Contract_End_Date_Textbox", contractEndDate, "CreateCARPage")

		getBaseSteps().setTextToControl("First_Info_Textbox", employeeName, "CreateCARPage")
		getBaseSteps().setTextToControl("Second_Info_Textbox", emplNumber, "CreateCARPage")
		getBaseSteps().setTextToControl("Third_Info_Textbox", supervisorName, "CreateCARPage")

		getBaseSteps().selectItemByTextInComboBoxUsingAction("Fourth_Infor1_Combobox", dropdown1Value, "CreateCARPage")
		getBaseSteps().selectItemByTextInComboBoxUsingAction("Fourth_Infor2_Combobox", dropdown2Value, "CreateCARPage")
		getBaseSteps().selectItemByTextInComboBoxUsingAction("Fourth_Infor3_Combobox", dropdown3Value, "CreateCARPage")

		getBaseSteps().selectRadioOptionByText("Fifth_Info_RadioButton", gender, "CreateCARPage")
		getBaseSteps().selectCheckboxOptionByText("Six_Info_Checkbox", hospitalBranch, "CreateCARPage")
		getBaseSteps().setTextToControl("Seven_Date_Info_Textbox", contractStartDate, "CreateCARPage")
		getBaseSteps().setTextToControl("Eight_Currency_Info_Textbox", salary, "CreateCARPage")

		getBaseSteps().clickToControl("Save_Button", "CreateCARPage")
		getBaseSteps().waitForControlDoesNotDisplay("Confirmation_Message_Label",Timeout.WAIT_TIMEOUT_FEW_SECONDS ,PageLocatorName.COMMONLOCATORS_PAGE)// flacky pop up error, set to 80s to monitor

		if(getBaseSteps().isControlDisplayed("Confirmation_Message_Label", PageLocatorName.COMMONLOCATORS_PAGE)) {
			getBaseSteps().clickToControl("Confirmation_Message_Close_Button", PageLocatorName.COMMONLOCATORS_PAGE)
		}

		getBaseSteps().clickToControl("CAR_Information_Next_Button", "CreateCARPage")
	}

	public static String createCARForApproval(String createdRegionName, String createdLocationName, String createdCCName,
			String createdSpecialtyName, String createdPositionName, String createdTemplateName, String createdProvidersName ) {

		'Complete Initiate CAR page'
		completeInitiateCARFields(createdRegionName, createdLocationName, createdCCName, createdSpecialtyName, createdPositionName)
		getBaseSteps().clickToControl("Initiate_CAR_Next_Button", "CreateCARPage")
		'Complete Select Template'
		completeSelectTemplate(createdTemplateName)
		'Complete select Provider'
		completeProviders(createdProvidersName)
		'Complete CAR Information'
		completeCARInformation()
		'Click Next button'
		getBaseSteps().clickToControl("Approvers_Next_Button", "CreateCARPage")
		'Click Submit for Approval'
		getBaseSteps().clickToControl("Submit_For_Approval_Button", "CreateCARPage")
	}
}

