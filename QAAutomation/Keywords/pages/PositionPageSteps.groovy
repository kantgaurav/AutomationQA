package pages

import configs.CMSStrings
import configs.PageLocatorName
import utils.ExcelUtil
import utils.Utilities

public class PositionPageSteps extends CommonPageSteps{

	public static void verifyPageIsLoaded() {
		getBaseSteps().verifyPageTitleIsCorrect(CMSStrings.POSITION_PAGE_TITLE)
	}


	public static String createPosition() {

		String number = Utilities.generateRandomNumber()
		String positionName = String.format("Pos_%s",number)
		String positionID = String.format("PosID_%s",number)

		getBaseSteps().clickToControl("Add_Position_Button", PageLocatorName.POSITION_PAGE)
		getBaseSteps().setTextToControl("Position_Name_Textbox",positionName,PageLocatorName.POSITION_PAGE)
		getBaseSteps().setTextToControl("Position_ID_Textbox",positionID, PageLocatorName.POSITION_PAGE)
		getBaseSteps().clickToControl("Save_Button", PageLocatorName.POSITION_PAGE)
		getBaseSteps().waitForControlDisplay("Confirmation_Message_Label", PageLocatorName.COMMONLOCATORS_PAGE)
		return positionName
	}


	public static String writeValueToRegionFile(String filePath) {
		String number = Utilities.generateRandomNumber()
		String positionName = String.format("Pos_%s",number)

		ExcelUtil.writeContentToFile(filePath, 1, 0, positionName)
		ExcelUtil.writeContentToFile(filePath, 1, 1, String.format("PosID_%s",number))
		return positionName
	}
}
