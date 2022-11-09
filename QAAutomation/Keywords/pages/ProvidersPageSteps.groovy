package pages

import com.google.common.base.Strings

import configs.CMSStrings
import configs.PageLocatorName
import utils.ExcelUtil
import utils.Utilities


public class ProvidersPageSteps extends CommonPageSteps{

	public static void verifyPageIsLoaded() {
		getBaseSteps().verifyPageTitleIsCorrect(CMSStrings.PROVIDER_PAGE_TITLE)
	}

	public static String createProvider(String positionName = "") {
		String number = Utilities.generateRandomNumber()
		String firstName = String.format("FN_%s",number)
		String lastName = String.format("LN_%s",number)
		String email = String.format("%s@hhc.com",number)
		String address = String.format("adr_%s street",number)
		String city = String.format("City_%s",number)
		String state = "Alaska"
		String zipCode = Utilities.generateRandomNumber()
		String SSN = Utilities.generateRandomNumber(10)
		String NPI = Utilities.generateRandomNumber(10)

		getBaseSteps().clickToControl("Add_Provider_Button", PageLocatorName.PROVIDERS_PAGE)
		getBaseSteps().waitForControlDisplay("Modal_Content_Label", PageLocatorName.COMMONLOCATORS_PAGE);
		getBaseSteps().setTextToControl("First_Name_Textbox",firstName,PageLocatorName.PROVIDERS_PAGE)
		getBaseSteps().setTextToControl("Last_Name_Textbox",lastName,PageLocatorName.PROVIDERS_PAGE)
		getBaseSteps().setTextToControl("Email_Textbox",email,PageLocatorName.PROVIDERS_PAGE)

		getBaseSteps().setTextToControl("Address1_Textbox",address,PageLocatorName.PROVIDERS_PAGE)
		getBaseSteps().setTextToControl("City_Textbox",city,PageLocatorName.PROVIDERS_PAGE)

		getBaseSteps().selectItemByTextInComboBoxUsingAction("State_Textbox", state, PageLocatorName.PROVIDERS_PAGE)
		getBaseSteps().setTextToControl("Zip_Code_Textbox",zipCode,PageLocatorName.PROVIDERS_PAGE)
		if(!Strings.isNullOrEmpty(positionName)) {
			getBaseSteps().setTextToControl("Position_Textbox", positionName, PageLocatorName.PROVIDERS_PAGE)
			getBaseSteps().selectItemByTextInComboBoxUsingAction("Position_Combobox", positionName, PageLocatorName.PROVIDERS_PAGE)
		}

		getBaseSteps().setTextToControl("SSN_Textbox", SSN, PageLocatorName.PROVIDERS_PAGE)
		getBaseSteps().setTextToControl("NPI_Textbox", NPI, PageLocatorName.PROVIDERS_PAGE)
		getBaseSteps().clickToControl("Save_Button", PageLocatorName.PROVIDERS_PAGE)

		return firstName
	}
	
	public static String writeValueToRegionFile(String filePath, String position) {
		String number = Utilities.generateRandomNumber()
		String firstName = String.format("FN_%s",number)
		String midName = "M"
		String lastName = String.format("LN_%s",number)
		String email = String.format("%s@hhc.com",number)
		String phone = "123-456-7890"
		String date = "12/31/1980"
		String address1 = String.format("adr1_%s street",number)
		String address2 = String.format("adr2_%s street",number)
		String city = String.format("City_%s",number)
		String state = "Alaska"
		String zipCode = Utilities.generateRandomNumber()
		String SSN = "123-45-6789"
		String NPI = Utilities.generateRandomNumber(10)
		String comment = "Comments"
		String orgID = "01"
		
		
		
		ExcelUtil.writeContentToFile(filePath, 1, 0, firstName)
		ExcelUtil.writeContentToFile(filePath, 1, 1, midName)
		ExcelUtil.writeContentToFile(filePath, 1, 2, lastName)
		ExcelUtil.writeContentToFile(filePath, 1, 3, position)
		ExcelUtil.writeContentToFile(filePath, 1, 4, email)
		ExcelUtil.writeContentToFile(filePath, 1, 5, phone)
		ExcelUtil.writeContentToFile(filePath, 1, 6, date)
		ExcelUtil.writeContentToFile(filePath, 1, 7, address1)
		ExcelUtil.writeContentToFile(filePath, 1, 8, address2)
		ExcelUtil.writeContentToFile(filePath, 1, 9, city)
		ExcelUtil.writeContentToFile(filePath, 1, 10, state)
		ExcelUtil.writeContentToFile(filePath, 1, 11, zipCode)
		ExcelUtil.writeContentToFile(filePath, 1, 12, SSN)
		ExcelUtil.writeContentToFile(filePath, 1, 13, NPI)
		ExcelUtil.writeContentToFile(filePath, 1, 14, comment)
		ExcelUtil.writeContentToFile(filePath, 1, 15, orgID)
		
		return firstName
	}
	
	public static verifyColumnHearInExportedFile(String excelFilePath) {
		String xpath = "//tr[contains(@class,'e-columnheader')]/th[%s]"
		for(int i = 3; i<=14; i++) {
			if(i == 13) {
				break;
			}else {
				String dataExpected = getBaseSteps().getTextFromControlByReplacedTextInXpath(xpath,i.toString())
				getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, dataExpected)
			}
		}
	}
	
	public static verifyColumnHeaderProvidersFile(String excelFilePath, int startIndexColumn, int endIndexColumn) {
		String xpath = getBaseSteps().getXpathFromControl("Column_Header", PageLocatorName.COMMONLOCATORS_PAGE)
		String listdata = ""
		for(int i = startIndexColumn; i<= endIndexColumn; i++) {
			if(i == 13) { //13 is not available on table
				break;
			}else {
				listdata += getBaseSteps().getTextFromControlByReplacedTextInXpath(xpath,i.toString()) + " "
			}
		}
		getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, listdata)
	}
	
	public static verifyFirstRowProvidersFile(String excelFilePath, int startIndexColumn, int endIndexColumn) {
		String xpath = getBaseSteps().getXpathFromControl("First_Row", PageLocatorName.COMMONLOCATORS_PAGE)
		String listdata = ""
		for(int i = startIndexColumn; i<= endIndexColumn; i++) {
			if(i == 13) { //13 is not available on table
				break;
			}else {
				listdata += getBaseSteps().getTextFromControlByReplacedTextInXpath(xpath,i.toString()) + " "
			}
		}
		getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, listdata)
	}

}
