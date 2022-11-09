package pages

import configs.CMSStrings
import configs.PageLocatorName
import utils.ExcelUtil
import utils.Utilities

public class SpecialtyPageSteps extends CommonPageSteps{

	public static void verifyPageIsLoaded() {
		getBaseSteps().verifyPageTitleIsCorrect(CMSStrings.SPECIALTY_PAGE_TITLE)
	}

	public static String createSpecialty() {
		String number = Utilities.generateRandomNumber()
		String specialtyName = String.format("Spec_%s",number)
		String specialtyID = String.format("SpecID_%s",number)
		String cross = String.format("Cross_%s",number)
		getBaseSteps().clickToControl("Add_Specialty_Button", PageLocatorName.SPECIALTY_PAGE)
		getBaseSteps().setTextToControl("Specialty_Name_Textbox",specialtyName,PageLocatorName.SPECIALTY_PAGE)
		getBaseSteps().setTextToControl("Specialty_ID_Textbox",specialtyID, PageLocatorName.SPECIALTY_PAGE)
		getBaseSteps().setTextToControl("Crosswalk_Textbox",cross, PageLocatorName.SPECIALTY_PAGE)
		getBaseSteps().clickToControl("Save_Button", PageLocatorName.SPECIALTY_PAGE)
		getBaseSteps().waitForControlDisplay("Confirmation_Message_Label", PageLocatorName.COMMONLOCATORS_PAGE)
		return specialtyName
	}

	public static String writeValueToRegionFile(String filePath) {
		String number = Utilities.generateRandomNumber()
		String specName = String.format("SpecN_%s",number)

		ExcelUtil.writeContentToFile(filePath, 1, 0, specName)
		ExcelUtil.writeContentToFile(filePath, 1, 1, String.format("SpecID_%s",number))
		ExcelUtil.writeContentToFile(filePath, 1, 2, String.format("Cross_%s",number))
		ExcelUtil.writeContentToFile(filePath, 1, 3, String.format("Spec_%s",number))

		return specName
	}
}
