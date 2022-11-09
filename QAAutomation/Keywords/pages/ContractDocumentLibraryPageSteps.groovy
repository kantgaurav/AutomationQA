package pages
import configs.PageLocatorName
import core.BaseControl
import core.Browser
import core.AssertSteps



public class ContractDocumentLibraryPageSteps extends CommonPageSteps{

	public static void filterWith(String region, String location, String ccenter,String specialty, String position) {

		getBaseSteps().selectItemByTextInComboBoxUsingAction("Filter_Region_Combobox", region,true,true, PageLocatorName.CONTRACTDOCUMENTLIBRARY_PAGE)
		getBaseSteps().selectItemByTextInComboBoxUsingAction("Filter_Location_Combobox", location,true,true, PageLocatorName.CONTRACTDOCUMENTLIBRARY_PAGE)
		getBaseSteps().selectItemByTextInComboBoxUsingAction("Filter_Cost_Center_Combobox", ccenter,true,true, PageLocatorName.CONTRACTDOCUMENTLIBRARY_PAGE)
		getBaseSteps().selectItemByTextInComboBoxUsingAction("Filter_Specialty_Combobox", specialty,true,true, PageLocatorName.CONTRACTDOCUMENTLIBRARY_PAGE)
		getBaseSteps().selectItemByTextInComboBoxUsingAction("Filter_Position_Combobox", position,true,true, PageLocatorName.CONTRACTDOCUMENTLIBRARY_PAGE)
		getBaseSteps().clickToControl("Filter_Apply_Filter_Button", PageLocatorName.CONTRACTDOCUMENTLIBRARY_PAGE)
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void verifyPageIsLoaded() {
		getBaseSteps().verifyControlDisplayed("Search_Textbox", PageLocatorName.CONTRACTDOCUMENTLIBRARY_PAGE);
	}

	public static void clickOnStatusCheckbox(String statusName) {
		String xpath = "//*[contains(@class,'p-listbox p-component')]//span[contains(text(),'%s')]/preceding-sibling::div"
		getBaseSteps().clickToControlByReplacedTextInXpath(xpath, statusName)
	}

	public static filterStatus(String statusName) {
		'CLick on Status Filter'
		getBaseSteps().clickToControl("Status_Filter_Button", PageLocatorName.CONTRACTDOCUMENTLIBRARY_PAGE)
		'Uncheck all checkbox'
		getBaseSteps().clickToControl("All_Status_Checkbox", PageLocatorName.CONTRACTDOCUMENTLIBRARY_PAGE)
		'Wait for No_Contract_Label display'
		getBaseSteps().waitForControlDisplay("No_Contract_Label", PageLocatorName.CONTRACTDOCUMENTLIBRARY_PAGE)
		'Select status Approval'
		clickOnStatusCheckbox(statusName)
	}

	public static void clickOnSubmitForSigning(String contractName) {
		String xpath = "//div[contains(@class,'card-body')]//div[div/b[contains(.,'%s')]]/following-sibling::div//button[normalize-space(text())='Submit for Signing']"
		getBaseSteps().clickToControlByReplacedTextInXpath(xpath, contractName)
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void clickOnViewButton(String contractName) {
		String xpath = "//div[contains(@class,'card-body')]//div/div[b[contains(.,'%s')]]/following-sibling::div//button[normalize-space(text())='View']"
		getBaseSteps().clickToControlByReplacedTextInXpath(xpath, contractName)
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void clickOnTabName(String tabName) {
		String xpath = "//span[contains(text(),'%s')]"
		getBaseSteps().clickToControlByReplacedTextInXpath(xpath, tabName)
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void clickOnApproversLink(String contractName) {
		String xpath = "//div[contains(@class,'card-body')]//div/div[b[contains(.,'%s')]]/following-sibling::div//b[contains(text(),'Approvers')]"
		getBaseSteps().clickToControlByReplacedTextInXpath(xpath, contractName)
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void clickOnSignersLink(String contractName) {
		String xpath = "//div[contains(@class,'card-body')]//div/div[b[contains(.,'%s')]]/following-sibling::div//b[contains(text(),'Signers')]"
		getBaseSteps().clickToControlByReplacedTextInXpath(xpath, contractName)
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void clickOnActivateButton(String contractName) {
		String xpath = "//div[contains(@class,'card-body')]//div[div[b[contains(.,'%s')]]]/following-sibling::div//button[contains(text(),'Activate')]"
		getBaseSteps().clickToControlByReplacedTextInXpath(xpath, contractName)
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static String getStatusDocument(String docName) {
		String xpath = String.format("//div[contains(@class,'card-body')]//div/div[b[contains(.,'%s')]]/following-sibling::div[1]",docName)
		BaseControl control = new BaseControl(xpath)
		return control.getText().trim()
	}

	public static void approvingContract(String contractDocumentName,  String createdRegionName, String createdLocationName, String createdCCName, String createdSpecialtyName, String createdPositionName, boolean doFilter = true) {
		if(doFilter == true) {
			'Do filters'
			getBaseSteps().clickToControl("Filter_Button", PageLocatorName.CONTRACTDOCUMENTLIBRARY_PAGE)
			filterWith(createdRegionName, createdLocationName, createdCCName, createdSpecialtyName, createdPositionName)
		}
		'Approve the document'
		clickOnViewButton(contractDocumentName)
		getBaseSteps().waitForProgressBarDisappear()
		clickOnTabName("Signing Document")
		getBaseSteps().clickToControl("Modal_Content_Approve_Contract_Document_Button", PageLocatorName.COMMONLOCATORS_PAGE)
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void submittingForSigning(String contractDocumentName, String createdRegionName, String createdLocationName, String createdCCName, String createdSpecialtyName, String createdPositionName) {
		'Do filters'
		getBaseSteps().clickToControl("Filter_Button", PageLocatorName.CONTRACTDOCUMENTLIBRARY_PAGE)
		filterWith(createdRegionName, createdLocationName, createdCCName, createdSpecialtyName, createdPositionName)
		'Click Submit for Signing button'
		clickOnSubmitForSigning(contractDocumentName)
		getBaseSteps().waitForProgressBarDisappear()
	}


	public static void abandonContractDocument(String contractDocumentName, String createdRegionName, String createdLocationName, String createdCCName, String createdSpecialtyName, String createdPositionName, String abandonValue = "Abandon") {
		String arrow = "//div[contains(@class,'card-body')]//div/div[b[contains(.,'%s')]]/following-sibling::div//button[normalize-space(text())='View']/following-sibling::div"
		String items = "//div[contains(@class,'dropdown-menu')]//button[contains(text(),'%s')]"
		'Do filters'
		getBaseSteps().clickToControl("Filter_Button", PageLocatorName.CONTRACTDOCUMENTLIBRARY_PAGE)
		filterWith(createdRegionName, createdLocationName, createdCCName, createdSpecialtyName, createdPositionName)
		'Click on Abandon'
		getBaseSteps().clickToControlByReplacedTextInXpath(arrow, contractDocumentName)
		getBaseSteps().clickToControlByReplacedTextInXpath(items, abandonValue)
	}

	public static void refreshToWaitActivateButton(String contractName) {

		int count = 1;
		String activateButtonXpath = "//div[contains(@class,'card-body')]//div[div[b[contains(.,'%s')]]]/following-sibling::div//button[contains(text(),'Activate')]"


		while(!getBaseSteps().isControlByReplacedTextInXpathDisplayed(activateButtonXpath, "Activate"))	{
			Browser.refreshCurrentPage()
			getBaseSteps().waitForProgressBarDisappear()
			count++;
			if(count==4) {
				break;
			}
		}
	}

	public static void downloadTemplateAtFirstItem() {
		String items = "//div[contains(@x-placement,'bottom-right')]//button[contains(text(),'%s')]"
		getBaseSteps().clickToControl("Infor_Bar_Button", PageLocatorName.CONTRACTDOCUMENTLIBRARY_PAGE)
		getBaseSteps().clickToControl("First_Dropdown_Menu", PageLocatorName.CONTRACTDOCUMENTLIBRARY_PAGE)
		getBaseSteps().clickToControlByReplacedTextInXpath(items, "Download")
	}

	public static void verifyNameOfApprover(String contractDocumentName, def nameList = []) {
		clickOnApproversLink(contractDocumentName)
		getBaseSteps().waitForTextControlChange("Modal_Content_Label", PageLocatorName.COMMONLOCATORS_PAGE);
		String modalContent = getBaseSteps().getTextFromControl("Modal_Content_Label", PageLocatorName.COMMONLOCATORS_PAGE);
		String msg = String.format("The modal content has content as >>>%s<<<",modalContent)
		nameList.eachWithIndex { name, idx ->
			AssertSteps.verifyExpectedResult(modalContent.contains(name.trim()),msg,msg);
		}
		getBaseSteps().clickToControl("Modal_Content_X_Button", PageLocatorName.COMMONLOCATORS_PAGE)
	}

	public static void verifyStatusApprovals(String contractDocumentName, def expectedStatus = []) {
		clickOnApproversLink(contractDocumentName)
		getBaseSteps().waitForTextControlChange("Modal_Content_Label", PageLocatorName.COMMONLOCATORS_PAGE);
		String modalContent = getBaseSteps().getTextFromControl("Modal_Content_Label", PageLocatorName.COMMONLOCATORS_PAGE);
		String msg = String.format("The modal content has content as >>>%s<<<",modalContent)
		expectedStatus.eachWithIndex { value, idx ->
			AssertSteps.verifyExpectedResult(modalContent.contains(value.trim()),msg,msg);
		}
		getBaseSteps().clickToControl("Modal_Content_X_Button", PageLocatorName.COMMONLOCATORS_PAGE)
	}

	public static void verifyNumberOfApproval(String contractName, String expectedStatus) {
		String xpath = "//div[contains(@class,'card-body')]//div/div[b[contains(.,'%s')]]/following-sibling::div[span/b[normalize-space(text()) = 'Approvers']]"
		String actualStatus = getBaseSteps().getTextFromControlByReplacedTextInXpath(xpath, contractName).replaceAll(" ", "")
		AssertSteps.verifyExpectedResult(actualStatus.contains(expectedStatus.replaceAll(" ", "")))
	}

	public static void verifyStatusSigner(String contractDocumentName, def expectedStatus = []) {
		clickOnSignersLink(contractDocumentName)
		getBaseSteps().waitForTextControlChange("Modal_Content_Label", PageLocatorName.COMMONLOCATORS_PAGE);
		String modalContent = getBaseSteps().getTextFromControl("Modal_Content_Label", PageLocatorName.COMMONLOCATORS_PAGE);
		String msg = String.format("The modal content has content as >>>%s<<<",modalContent)
		expectedStatus.eachWithIndex { value, idx ->
			AssertSteps.verifyExpectedResult(modalContent.contains(value.trim()),msg,msg);
		}
		getBaseSteps().clickToControl("Modal_Content_X_Button", PageLocatorName.COMMONLOCATORS_PAGE)
	}

	public static void verifyNumberOfSigner(String contractName, String expectedStatus) {
		String xpath = "//div[contains(@class,'card-body')]//div/div[b[contains(.,'%s')]]/following-sibling::div[span/b[normalize-space(text()) = 'Signers']]"
		String actualStatus = getBaseSteps().getTextFromControlByReplacedTextInXpath(xpath, contractName).replaceAll(" ", "")
		AssertSteps.verifyExpectedResult(actualStatus.contains(expectedStatus.replaceAll(" ", "")))
	}

	public static void verifyNameOfSigner(String contractDocumentName, def nameList = []) {
		clickOnSignersLink(contractDocumentName)
		getBaseSteps().waitForTextControlChange("Modal_Content_Label", PageLocatorName.COMMONLOCATORS_PAGE);
		String modalContent = getBaseSteps().getTextFromControl("Modal_Content_Label", PageLocatorName.COMMONLOCATORS_PAGE);
		String msg = String.format("The modal content has content as >>>%s<<<",modalContent)
		nameList.eachWithIndex { name, idx ->
			AssertSteps.verifyExpectedResult(modalContent.contains(name.trim()),msg,msg);
		}
		getBaseSteps().clickToControl("Modal_Content_X_Button", PageLocatorName.COMMONLOCATORS_PAGE)
	}
}