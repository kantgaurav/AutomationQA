package pages

import configs.CMSStrings
import configs.PageLocatorName

public class ExecuteContractPageSteps extends CommonPageSteps {

	public static void verifyPageIsLoaded() {
		getBaseSteps().verifyPageTitleIsCorrect(CMSStrings.EXECUTE_CONTRACT_PAGE_TITLE)
	}

	public static void selectTemplate(String templateName) {
		String xpath = "//div[div/div/b[normalize-space(text())='%s']]/following-sibling::div//button"
		getBaseSteps().clickToControlByReplacedTextInXpath(xpath, templateName)
	}

	public static void searchProvider(String name) {
		getBaseSteps().setTextToControl("Search_Providers_Textbox",name,PageLocatorName.EXECUTECONTRACT_PAGE)
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void searchCARRequest(String name) {
		getBaseSteps().setTextToControl("Search_CAR_Request_Textbox",name,PageLocatorName.EXECUTECONTRACT_PAGE)
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void searchTemplate(String name) {
		getBaseSteps().setTextToControl("Search_Template_Textbox",name,PageLocatorName.EXECUTECONTRACT_PAGE)
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static String  executingCAR(String providerFName, String createdCARRequest, String createdTemplateName) {
		'Select Execute Contract '
		MenuPageSteps.selectContractDocumentsMenu("Execute Contract")
		'Select Provider'
		searchProvider(providerFName)

		String firstName = getBaseSteps().getCellTableText("Search_Provider_Table", "2", PageLocatorName.EXECUTECONTRACT_PAGE)
		String lastName = getBaseSteps().getCellTableText("Search_Provider_Table", "3", PageLocatorName.EXECUTECONTRACT_PAGE)
		String contractDocumentName = String.format("%s-%s %s", createdTemplateName, firstName, lastName )

		getBaseSteps().clickToCellTableButtonBaseOnAnotherCellDataTable("Search_Provider_Table", "2", providerFName, "last()", "Select", PageLocatorName.EXECUTECONTRACT_PAGE)

		'Select CAR Request'
		searchCARRequest(createdCARRequest)
		getBaseSteps().clickToCellTableButtonBaseOnAnotherCellDataTable("Search_CAR_Request_Table", "1", createdCARRequest, "last()", "Select", PageLocatorName.EXECUTECONTRACT_PAGE)
		'Select Template'
		selectTemplate(createdTemplateName)
		getBaseSteps().waitForDataGenerationSpinnerDisappear()
		getBaseSteps().waitForControlDoesNotDisplay("Confirmation_Message_Label", PageLocatorName.COMMONLOCATORS_PAGE)

		if(getBaseSteps().isControlDisplayed("Confirmation_Message_Label", PageLocatorName.COMMONLOCATORS_PAGE)) {
			getBaseSteps().clickToControl("Confirmation_Message_Close_Button", PageLocatorName.COMMONLOCATORS_PAGE)
		}

		//Added by sunita as per the new change
		getBaseSteps().clickToControl("SelectFolder_Button", PageLocatorName.EXECUTECONTRACT_PAGE)
		getBaseSteps().clickToControl("CreateFolder_Button", PageLocatorName.EXECUTECONTRACT_PAGE)
		getBaseSteps().setTextToControl("Folder_Field","SS_NewFolder",PageLocatorName.EXECUTECONTRACT_PAGE)
		getBaseSteps().clickToControl("SaveFolderChanges_Button", PageLocatorName.EXECUTECONTRACT_PAGE)
		getBaseSteps().waitForControlDoesNotDisplay("Confirmation_Message_Label", PageLocatorName.COMMONLOCATORS_PAGE)
		Thread.sleep(2000)
		getBaseSteps().clickToControl("SelectCreatedFolder", PageLocatorName.EXECUTECONTRACT_PAGE)
		getBaseSteps().clickToControl("SaveDocOnFolder_Button", PageLocatorName.EXECUTECONTRACT_PAGE)
		getBaseSteps().waitForControlDoesNotDisplay("Confirmation_Message_Label", PageLocatorName.COMMONLOCATORS_PAGE)
		Thread.sleep(2000)
		'Click Next button'
		getBaseSteps().clickToControl("Next_Button", PageLocatorName.EXECUTECONTRACT_PAGE)
		//To be get confirmation from Sunita
		//getBaseSteps().clickToControl("Modal_Content_Yes_Button", PageLocatorName.COMMONLOCATORS_PAGE)
		Thread.sleep(2000)
		//getBaseSteps().clickToControl("SendPoviderForSigning_Button", PageLocatorName.EXECUTECONTRACT_PAGE) to rework later 
		'Click Save and Send For approval button'
		getBaseSteps().clickToControl("Save_And_Send_For_Approval_Button", PageLocatorName.EXECUTECONTRACT_PAGE)

		return contractDocumentName
	}
}
