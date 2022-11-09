package pages

import configs.CMSStrings
import configs.PageLocatorName
import utils.ExcelUtil
import utils.Utilities

public class CostCenterPageSteps extends CommonPageSteps{

	public static void verifyPageIsLoaded() {
		getBaseSteps().verifyPageTitleIsCorrect(CMSStrings.COST_CENTER_PAGE_TITLE)
	}

	//done
	public static String createCostCenter(String regionName, String locationName) {

		String number = Utilities.generateRandomNumber();
		String costcenterName = String.format("CC_%s",number)
		String H2ID = String.format("H2ID_%s",number)
		String PID = String.format("PID_%s",number)
		String HRID = String.format("HRID_%s",number)
		String EMRID = String.format("EMRID_%s",number)

		getBaseSteps().clickToControl("Add_Cost_Center_Button", PageLocatorName.COSTCENTER_PAGE)
		getBaseSteps().selectItemByTextInComboBoxUsingAction("Region_Combobox", regionName, PageLocatorName.COSTCENTER_PAGE)
		getBaseSteps().selectItemByTextInComboBoxUsingAction("Location_Combobox", locationName, PageLocatorName.COSTCENTER_PAGE)
		getBaseSteps().setTextToControl("Cost_Center_Name_Textbox",costcenterName,PageLocatorName.COSTCENTER_PAGE)
		getBaseSteps().setTextToControl("H2ID_Textbox",H2ID, PageLocatorName.COSTCENTER_PAGE)
		getBaseSteps().setTextToControl("Payroll_CCID_Textbox",PID, PageLocatorName.COSTCENTER_PAGE)
		getBaseSteps().setTextToControl("HRCCID_Textbox",HRID, PageLocatorName.COSTCENTER_PAGE)
		getBaseSteps().setTextToControl("EMRCCID_Textbox",EMRID, PageLocatorName.COSTCENTER_PAGE)
		getBaseSteps().clickToControl("Save_Button", PageLocatorName.COSTCENTER_PAGE)
		getBaseSteps().waitForControlDisplay("Confirmation_Message_Label", PageLocatorName.COMMONLOCATORS_PAGE)
		return costcenterName
	}

	public static String writeValueToRegionFile(String filePath, String regionName, String locationName) {

		String number = Utilities.generateRandomNumber();
		String costcenterName = String.format("CC_%s",number)
		String H2ID = String.format("H2ID_%s",number)
		String PID = String.format("PID_%s",number)
		String HRID = String.format("HRID_%s",number)
		String EMRID = String.format("EMRID_%s",number)

		ExcelUtil.writeContentToFile(filePath, 1, 0, regionName)
		ExcelUtil.writeContentToFile(filePath, 1, 1, locationName)
		ExcelUtil.writeContentToFile(filePath, 1, 2, costcenterName)
		ExcelUtil.writeContentToFile(filePath, 1, 3, H2ID)
		ExcelUtil.writeContentToFile(filePath, 1, 4, PID)
		ExcelUtil.writeContentToFile(filePath, 1, 5, HRID)
		ExcelUtil.writeContentToFile(filePath, 1, 6, EMRID)
		return costcenterName

	}

	public static void verifyColumnHeaderCostCenterFile(String excelFilePath, int startIndexColumn, int endIndexColumn) {
		String xpath = getBaseSteps().getXpathFromControl("Column_Header", PageLocatorName.COMMONLOCATORS_PAGE)
		String listdata = ""
		for(int i = startIndexColumn; i<= endIndexColumn; i++) {
			if(i == 4) {
				break;
			}else {
				listdata += getBaseSteps().getTextFromControlByReplacedTextInXpath(xpath,i.toString()) + " "
			}
		}
		getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, listdata)
	}
}
