package pages

import org.openqa.selenium.WebElement

import com.google.common.base.Strings
import com.sun.org.apache.bcel.internal.generic.LSTORE

import configs.CMSStrings
import configs.PageLocatorName
import controls.ComboBox
import core.AssertSteps
import core.BaseControl
import utils.Utilities

public class CARApprovalSetupPageSteps extends CommonPageSteps{


	public static void verifyPageIsLoaded() {
		getBaseSteps().verifyPageTitleIsCorrect(CMSStrings.CAR_APPROVAL_SETUP_PAGE_TITLE)
	}

	public static String createApproval(String approvalType, String localeSpecificity, String approvalModeType,
			String region, String location, String costCenters, String specialty, String positionsName, def lstApprover = ["Super"],
			def lstNotifier=["Super"], String template="",
			boolean isPreActionChecked=false, boolean isPosActionChecked=false,def numberApprovers="", boolean isApprovalOverrideChecked=false) {
		String number = Utilities.generateRandomNumber()
		String apprType = approvalType.replaceAll("\\s","")
		String approvalName = String.format("%s_%s",apprType,number )

		getBaseSteps().clickToControl("Create_Approval_Button", PageLocatorName.CARAPPROVAL_PAGE)
		getBaseSteps().setTextToControl("Name_Textbox",approvalName,PageLocatorName.CARAPPROVAL_PAGE)
		getBaseSteps().clickToControlByText(approvalType)
		getBaseSteps().clickToControlByReplacedTextInXpath("//span[normalize-space(text())=\"%s\"]", localeSpecificity)
		getBaseSteps().clickToControlByText(approvalModeType)

		switch(localeSpecificity.toUpperCase()) {
			case "REGION":
				getBaseSteps().selectItemByTextInComboBoxUsingAction("Regions_Combobox", region, true,true,PageLocatorName.CARAPPROVAL_PAGE)
				getBaseSteps().selectItemByTextInComboBoxUsingAction("Speciality_Combobox", specialty, true,true,PageLocatorName.CARAPPROVAL_PAGE)
				getBaseSteps().selectItemByTextInComboBoxUsingAction("Position_Combobox", positionsName, true,true,PageLocatorName.CARAPPROVAL_PAGE)
				break;
			case "LOCATION":
				getBaseSteps().selectItemByTextInComboBoxUsingAction("Regions_Combobox", region,PageLocatorName.CARAPPROVAL_PAGE)
				getBaseSteps().selectItemByTextInComboBoxUsingAction("Locations_Combobox", location, true,true,PageLocatorName.CARAPPROVAL_PAGE)
				getBaseSteps().selectItemByTextInComboBoxUsingAction("Speciality_Combobox", specialty, true,true,PageLocatorName.CARAPPROVAL_PAGE)
				getBaseSteps().selectItemByTextInComboBoxUsingAction("Position_Combobox", positionsName, true,true,PageLocatorName.CARAPPROVAL_PAGE)
				break;
			case "COST CENTER":
				getBaseSteps().selectItemByTextInComboBoxUsingAction("Regions_Combobox", region,PageLocatorName.CARAPPROVAL_PAGE)
				getBaseSteps().selectItemByTextInComboBoxUsingAction("Locations_Combobox", location, PageLocatorName.CARAPPROVAL_PAGE)
				getBaseSteps().selectItemByTextInComboBoxUsingAction("Cost_Centers_Combobox", costCenters, true,true,PageLocatorName.CARAPPROVAL_PAGE)
				getBaseSteps().selectItemByTextInComboBoxUsingAction("Speciality_Combobox", specialty, true,true,PageLocatorName.CARAPPROVAL_PAGE)
				getBaseSteps().selectItemByTextInComboBoxUsingAction("Position_Combobox", positionsName, true,true,PageLocatorName.CARAPPROVAL_PAGE)
				break;
			default:
				break;
		}

		if(approvalModeType.toUpperCase()=="MINIMUM APPROVER") {
			getBaseSteps().setTextToControl("Number_Of_Approvers_Textbox", numberApprovers, PageLocatorName.CARAPPROVAL_PAGE)
		}

		if(!Strings.isNullOrEmpty(template)) {
			getBaseSteps().clickToControl("Template_Specific_Checkbox", PageLocatorName.CARAPPROVAL_PAGE)
			getBaseSteps().setTextToControl("Search_CAR_Template_Textbox", template, PageLocatorName.CARAPPROVAL_PAGE)
			getBaseSteps().selectItemByTextInComboBoxUsingAction("Search_CAR_Template_Combobox", template,false,true, PageLocatorName.CARAPPROVAL_PAGE)
		}


		String xpathApprovalTextbox = getBaseSteps().getXpathFromControl("Approval_Textbox", PageLocatorName.CARAPPROVAL_PAGE)
		String xpathApprovalCombobox = getBaseSteps().getXpathFromControl("Approval_Combobox", PageLocatorName.CARAPPROVAL_PAGE)
		String xpathApprovalOverrideCheckbox = getBaseSteps().getXpathFromControl("Approval_Override_Checkbox", PageLocatorName.CARAPPROVAL_PAGE)

		lstApprover.eachWithIndex { item, idx ->
			BaseControl textBox = new BaseControl(String.format(xpathApprovalTextbox,(idx+1)))
			textBox.sendKeys(item)
			ComboBox combox = new ComboBox(String.format(xpathApprovalCombobox,(idx+1)))
			combox.selectByTextUsingAction(item, false, false)

			if(isApprovalOverrideChecked) {
				BaseControl overrideCheckbox = new BaseControl(String.format(xpathApprovalOverrideCheckbox,(idx+1)))
				overrideCheckbox.click()
			}
			if(idx<(lstApprover.size()-1)) {
				getBaseSteps().clickToControl("Add_Approval_Level_Button", PageLocatorName.CARAPPROVAL_PAGE)
			}
		}

		String xpathNotifierTextbox = getBaseSteps().getXpathFromControl("Notifier_Textbox", PageLocatorName.CARAPPROVAL_PAGE)
		String xpathNotifierCombobox = getBaseSteps().getXpathFromControl("Notifier_Combobox", PageLocatorName.CARAPPROVAL_PAGE)
		String xpathNotifierPreActionCheckbox = getBaseSteps().getXpathFromControl("Notifier_PreAction_Checkbox", PageLocatorName.CARAPPROVAL_PAGE)
		String xpathNotifierPostActionCheckbox = getBaseSteps().getXpathFromControl("Notifier_PostAction_Checkbox", PageLocatorName.CARAPPROVAL_PAGE)

		lstNotifier.eachWithIndex { item, idx ->
			BaseControl textBox = new BaseControl(String.format(xpathNotifierTextbox,(idx+1)))
			textBox.sendKeys(item)
			ComboBox combox = new ComboBox(String.format(xpathNotifierCombobox,(idx+1)))
			combox.selectByTextUsingAction(item, false, false)

			if(isPreActionChecked) {
				BaseControl preActionCheckbox = new BaseControl(String.format(xpathNotifierPreActionCheckbox,(idx+1)))
				preActionCheckbox.click()
			}
			if(isPosActionChecked) {
				BaseControl postActionCheckbox = new BaseControl(String.format(xpathNotifierPostActionCheckbox,(idx+1)))
				postActionCheckbox.click()
			}

			if(idx<(lstApprover.size()-1)) {
				getBaseSteps().clickToControl("Add_Notifiers_Button", PageLocatorName.CARAPPROVAL_PAGE)
			}
		}

		getBaseSteps().clickToControl("Save_Button", PageLocatorName.CARAPPROVAL_PAGE)

		return approvalName
	}

	public static String createApprovalWithBasicData(String approvalType, String localeSpecificity, String approvalModeType,
			String region, String location, String costCenters, String specialty, String positionsName) {
		String number = Utilities.generateRandomNumber()
		String apprType = approvalType.replaceAll("\\s","")
		String approvalName = String.format("%s_%s",apprType,number )

		getBaseSteps().setTextToControl("Name_Textbox",approvalName,PageLocatorName.CARAPPROVAL_PAGE)


		getBaseSteps().clickToControlByText(approvalType)
		getBaseSteps().clickToControlByReplacedTextInXpath("//span[normalize-space(text())=\"%s\"]", localeSpecificity)
		getBaseSteps().clickToControlByText(approvalModeType)


		getBaseSteps().selectItemByTextInComboBoxUsingAction("Regions_Combobox", region, PageLocatorName.CARAPPROVAL_PAGE)
		//		getBaseSteps().clickToControl("Regions_Label", PageLocatorName.CARAPPROVAL_PAGE)

		getBaseSteps().selectItemByTextInComboBoxUsingAction("Locations_Combobox", location, PageLocatorName.CARAPPROVAL_PAGE)
		//		getBaseSteps().clickToControl("Locations_Label", PageLocatorName.CARAPPROVAL_PAGE)

		getBaseSteps().selectItemByTextInComboBoxUsingAction("Cost_Centers_Combobox", costCenters, true,true,PageLocatorName.CARAPPROVAL_PAGE)
		//		getBaseSteps().clickToControl("Cost_Centers_Label", PageLocatorName.CARAPPROVAL_PAGE)

		getBaseSteps().selectItemByTextInComboBoxUsingAction("Speciality_Combobox", specialty, true,true,PageLocatorName.CARAPPROVAL_PAGE)
		//		getBaseSteps().clickToControl("Specialties_Label", PageLocatorName.CARAPPROVAL_PAGE)

		getBaseSteps().selectItemByTextInComboBoxUsingAction("Position_Combobox", positionsName, true,true,PageLocatorName.CARAPPROVAL_PAGE)
		//		getBaseSteps().clickToControl("Positions_Label", PageLocatorName.CARAPPROVAL_PAGE)


		return approvalName
	}


	public static void searchByName(String approvalName) {
		getBaseSteps().clickToControl("Filter_ListBox", PageLocatorName.CARAPPROVAL_PAGE)
		getBaseSteps().selectItemByTextInListoBoxUsingAction("Filter_ListBox", "Name", true, PageLocatorName.CARAPPROVAL_PAGE)
		getBaseSteps().setTextToControl("Name_Filter_Textbox", approvalName, PageLocatorName.CARAPPROVAL_PAGE)
		getBaseSteps().clickToControl("Apply_Filter_Button", PageLocatorName.CARAPPROVAL_PAGE)
		getBaseSteps().waitForTableDataLoaded("Search_Table", PageLocatorName.COMMONLOCATORS_PAGE)
	}

	public static void deleteConfiguredApprovals(approvalName) {
		searchByName(approvalName)
		getBaseSteps().clickToCellTableButton("Search_Table", "last()",CMSStrings.DELETE_BUTTON_IN_TABLE, PageLocatorName.COMMONLOCATORS_PAGE)//last is Action column
		getBaseSteps().waitForControlDisplay("Modal_Content_Label", PageLocatorName.COMMONLOCATORS_PAGE)
		getBaseSteps().clickToControl("Modal_Content_OK_Button", PageLocatorName.COMMONLOCATORS_PAGE)
	}


	//	public static void SearchApprovalsByName(String approvalName) {
	//		getBaseSteps().verifyControlDisplayed("Filter_Button", PageLocatorName.CARAPPROVAL_PAGE)
	//		getBaseSteps().clickToControl("Filter_Button", PageLocatorName.CARAPPROVAL_PAGE)
	//		getBaseSteps().verifyControlDisplayed("Search_Field", PageLocatorName.CARAPPROVAL_PAGE)
	//		getBaseSteps().clickToControl("Name_Filter", PageLocatorName.CARAPPROVAL_PAGE)
	//		getBaseSteps().clickToControl("Close_Icon", PageLocatorName.CARAPPROVAL_PAGE)
	//		getBaseSteps().setTextToControl("Name_FilterField", approvalName, PageLocatorName.CARAPPROVAL_PAGE)
	//		getBaseSteps().clickToControl("ApplyFilter_Button", PageLocatorName.CARAPPROVAL_PAGE)
	//		String columnText = getBaseSteps().getCellTableTextByRowAndColumnIndex("SearchApprovals_TableResult","1", "2", PageLocatorName.CARAPPROVAL_PAGE)
	//		AssertSteps.verifyExpectedResult(columnText.equals(approvalName), "The '" + columnText + "' did not match expected value of '" + approvalName + "'")
	//	}
	//
	//	public static void SearchApprovalsByRegion(String approvalName, String regionName) {
	//		getBaseSteps().clickToControl("Filter_Button", PageLocatorName.CARAPPROVAL_PAGE)
	//		getBaseSteps().verifyControlDisplayed("Search_Field", PageLocatorName.CARAPPROVAL_PAGE)
	//		getBaseSteps().clickToControl("Region_Filter", PageLocatorName.CARAPPROVAL_PAGE)
	//		getBaseSteps().clickToControl("Close_Icon", PageLocatorName.CARAPPROVAL_PAGE)
	//		getBaseSteps().selectItemByTextInComboBoxUsingAction("Region_FilterDropDown", regionName, PageLocatorName.CARAPPROVAL_PAGE)
	//		getBaseSteps().clickToControl("ApplyFilter_Button", PageLocatorName.CARAPPROVAL_PAGE)
	//		String columnText = getBaseSteps().getCellTableTextByRowAndColumnIndex("SearchApprovals_TableResult","1", "2", PageLocatorName.CARAPPROVAL_PAGE)
	//		AssertSteps.verifyExpectedResult(columnText.equals(approvalName), "The '" + columnText + "' did not match expected value of '" + approvalName + "'")
	//	}
	//
	//
	//	public static void DeleteApproval(String approvalName) {
	//		clickToDelete("SearchApprovals_TableResult",PageLocatorName.CARAPPROVAL_PAGE )
	//		getBaseSteps().waitForControlDisplay("NoRecordFound_Text", PageLocatorName.CARAPPROVAL_PAGE)
	//	}
	//
	//	private static void clickToDelete(String controlName, String pageName) {
	//		List<WebElement> webElements = getBaseSteps().getCellTableElementsByRowAndColumnIndex(controlName,"1", "12", pageName, "button")
	//		webElements[1].click()
	//	}
}


