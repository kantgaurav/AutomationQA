package pages

import configs.CMSStrings
import configs.PageLocatorName
import utils.ExcelUtil
import utils.Utilities

public class PayElementsPageSteps extends CommonPageSteps{


	public static void verifyPageIsLoaded() {
		getBaseSteps().verifyPageTitleIsCorrect(CMSStrings.PAY_ELEMENTS_PAGE_TITLE)
	}

	public static String createPayElements() {
		String number = Utilities.generateRandomNumber()
		
		String payElementCode = String.format("PE_%s",number)
		String description = String.format("DES_%s",number)
		String type = "Fixed"
		String frequency = "Pay"
		String prDescription = String.format("This is a description for %s",number)
		String rate = "Rate"
		
		getBaseSteps().clickToControl("Add_Pay_Elements_Button", "PayElementsPage")
		getBaseSteps().waitForControlDisplay("Modal_Content_Label", PageLocatorName.COMMONLOCATORS_PAGE)
		getBaseSteps().setTextToControl("Code_Textbox",payElementCode,"PayElementsPage")
		getBaseSteps().setTextToControl("Description_Textbox",description,"PayElementsPage")
		getBaseSteps().selectItemByTextInComboBoxUsingAction("Type_Combobox", type, "PayElementsPage")
		
		getBaseSteps().setTextToControl("Frequency_Textbox", frequency, "PayElementsPage")
		getBaseSteps().selectItemByTextInComboBoxUsingAction("Frequency_Combobox", frequency, "PayElementsPage")
		getBaseSteps().setTextToControl("Payroll_Description_Textbox",prDescription , "PayElementsPage")
		getBaseSteps().selectItemByTextInComboBoxUsingAction("Classification_Combobox", rate, "PayElementsPage")
		getBaseSteps().clickToControl("Save_Button", "PayElementsPage")

		return payElementCode
	}
	
	public static String writeValueToRegionFile(String filePath) {
		String number = Utilities.generateRandomNumber()
		
		String payElementCode = String.format("PE_%s",number)
		String description = String.format("DES_%s",number)
		String type = "Fixed"
		String prDescription = String.format("This is a description for %s",number)
		String frequency = "Pay"
		String classification = "Rate"
		
		ExcelUtil.writeContentToFile(filePath, 1, 0, payElementCode)
		ExcelUtil.writeContentToFile(filePath, 1, 1, description)
		ExcelUtil.writeContentToFile(filePath, 1, 2, type)
		ExcelUtil.writeContentToFile(filePath, 1, 3, prDescription)
		ExcelUtil.writeContentToFile(filePath, 1, 4, frequency)
		ExcelUtil.writeContentToFile(filePath, 1, 5, classification)
		
		return payElementCode;
	}
}
