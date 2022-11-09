package pages

import configs.CMSStrings
import configs.PageLocatorName
import utils.ExcelUtil
import utils.Utilities


public class LocationPageSteps extends CommonPageSteps {

	public static void verifyPageIsLoaded() {
		getBaseSteps().verifyPageTitleIsCorrect(CMSStrings.LOCATION_PAGE_TITLE)
	}
	
	public static String createLocation(String regionName) {
		String number = Utilities.generateRandomNumber();
		String locationName = String.format("Loc_%s",number)
		String locationID = String.format("LID_%s",number)
		String payrollID = String.format("PID_%s",number)
		getBaseSteps().clickToControl("Add_Location_Button", PageLocatorName.LOCATION_PAGE)
		getBaseSteps().selectItemByTextInComboBoxUsingAction("Region_Name_Combobox", regionName, PageLocatorName.LOCATION_PAGE)
		getBaseSteps().setTextToControl("Location_Name_Textbox",locationName,PageLocatorName.LOCATION_PAGE)
		getBaseSteps().setTextToControl("Location_ID_Textbox",locationID, PageLocatorName.LOCATION_PAGE)
		getBaseSteps().setTextToControl("Payroll_Loc_ID_Textbox",payrollID, PageLocatorName.LOCATION_PAGE)
		getBaseSteps().clickToControl("Save_Button", PageLocatorName.LOCATION_PAGE)
		getBaseSteps().waitForControlDisplay("Confirmation_Message_Label", PageLocatorName.COMMONLOCATORS_PAGE)
		return locationName
	}	
	
	public static String writeValueToRegionFile(String filePath, String region) {
		String number = Utilities.generateRandomNumber();
		String locationName = String.format("Loc_%s",number)
		String locationID = String.format("LID_%s",number)
		String payrollID = String.format("PID_%s",number)
		
		ExcelUtil.writeContentToFile(filePath, 1, 0, region)
		ExcelUtil.writeContentToFile(filePath, 1, 1, locationName)
		ExcelUtil.writeContentToFile(filePath, 1, 2, locationID)
		ExcelUtil.writeContentToFile(filePath, 1, 3, payrollID)
		return locationName
	}
}
