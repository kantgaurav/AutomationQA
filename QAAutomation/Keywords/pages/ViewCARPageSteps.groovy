package pages

import configs.CMSStrings
import configs.PageLocatorName
import core.BaseSteps
import core.ControlFactory
import internal.GlobalVariable
import core.AssertSteps

public class ViewCARPageSteps extends CommonPageSteps{
	protected static BaseSteps getBaseSteps() {
		return new BaseSteps(new ControlFactory());
	}

	public static void verifyPageIsLoaded() {
		getBaseSteps().verifyControlDisplayed("PageTitle_Label", "ViewCARPage");
	}

	public static void deleteACar(String deletedData) {

		search(deletedData)
		if(getBaseSteps().isTableHasData("Search_Table", PageLocatorName.COMMONLOCATORS_PAGE)) {
			getBaseSteps().clickToCellTableButton("Search_Table", "last()",CMSStrings.DELETE_BUTTON_IN_TABLE, PageLocatorName.COMMONLOCATORS_PAGE)//last is Action column
			getBaseSteps().waitForControlDisplay("Modal_Content_Label", PageLocatorName.COMMONLOCATORS_PAGE)
		}
	}

	public static void approvingCAR(String providerFName) {
		search(providerFName)
		getBaseSteps().clickToCellTableButton("Search_Table", "last()","Approve", PageLocatorName.COMMONLOCATORS_PAGE)//last is Action column
	}

	public static void verifyStatusAtApprovalsCompleteColumn(String createdProvidersName, def expectedStatus = [], String statusDisplayed) {
		search(createdProvidersName)
		getBaseSteps().clickToControl("Approval_Link", PageLocatorName.COMMONLOCATORS_PAGE)
		String actualStatus = getBaseSteps().getTextFromControl("Approval_Link", PageLocatorName.COMMONLOCATORS_PAGE)

		AssertSteps.verifyExpectedResult(actualStatus.equals(statusDisplayed))

		getBaseSteps().waitForTextControlChange("Modal_Content_Label", PageLocatorName.COMMONLOCATORS_PAGE);
		String modalContent = getBaseSteps().getTextFromControl("Modal_Content_Label", PageLocatorName.COMMONLOCATORS_PAGE);
		String msg = String.format("The modal content has content as >>>%s<<<",modalContent)
		expectedStatus.eachWithIndex { value, idx ->
			AssertSteps.verifyExpectedResult(modalContent.contains(value.trim()),msg,msg);
		}
		getBaseSteps().clickToControl("Modal_Content_X_Button", PageLocatorName.COMMONLOCATORS_PAGE)
	}

	public static void verifyNameOfApprover(String createdProvidersName, def nameList = []) {
		search(createdProvidersName)
		getBaseSteps().clickToControl("Approval_Link", PageLocatorName.COMMONLOCATORS_PAGE)

		getBaseSteps().waitForTextControlChange("Modal_Content_Label", PageLocatorName.COMMONLOCATORS_PAGE);
		String modalContent = getBaseSteps().getTextFromControl("Modal_Content_Label", PageLocatorName.COMMONLOCATORS_PAGE);
		String msg = String.format("The modal content has content as >>>%s<<<",modalContent)
		nameList.eachWithIndex { name, idx ->
			AssertSteps.verifyExpectedResult(modalContent.contains(name.trim()),msg,msg);
		}
		getBaseSteps().clickToControl("Modal_Content_X_Button", PageLocatorName.COMMONLOCATORS_PAGE)
	}

	public static void filterWith(String region) {

		getBaseSteps().clickToControl("Filter_Region", PageLocatorName.VIEWCAR_PAGE)
		getBaseSteps().selectItemByTextInComboBoxUsingAction("Filter_Region_Combobox", region,true,true, PageLocatorName.CONTRACTDOCUMENTLIBRARY_PAGE)
		//				getBaseSteps().selectItemByTextInComboBoxUsingAction("Filter_Location_Combobox", location,true,true, PageLocatorName.CONTRACTDOCUMENTLIBRARY_PAGE)
		//				getBaseSteps().selectItemByTextInComboBoxUsingAction("Filter_Cost_Center_Combobox", ccenter,true,true, PageLocatorName.CONTRACTDOCUMENTLIBRARY_PAGE)
		//				getBaseSteps().selectItemByTextInComboBoxUsingAction("Filter_Specialty_Combobox", specialty,true,true, PageLocatorName.CONTRACTDOCUMENTLIBRARY_PAGE)
		//				getBaseSteps().selectItemByTextInComboBoxUsingAction("Filter_Position_Combobox", position,true,true, PageLocatorName.CONTRACTDOCUMENTLIBRARY_PAGE)
		getBaseSteps().clickToControl("Filter_Apply_Filter_Button", PageLocatorName.CONTRACTDOCUMENTLIBRARY_PAGE)
		getBaseSteps().waitForProgressBarDisappear()
	}
}
