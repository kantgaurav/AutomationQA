package pages

import com.google.common.base.Strings

import configs.CMSStrings
import configs.PageLocatorName
import configs.Path
import utils.ExcelUtil
import utils.PlatformUtil
import utils.Utilities


public class RegionPageSteps extends CommonPageSteps{

	public static void verifyPageIsLoaded() {
		getBaseSteps().verifyPageTitleIsCorrect(CMSStrings.REGION_PAGE_TITLE)
	}

	public static String createRegion() {
		String number = Utilities.generateRandomNumber();
		String regionName = String.format("Reg_%s",number)
		
		String regionID = String.format("RID_%s",number)
		getBaseSteps().clickToControl("Add_Region_Button", PageLocatorName.REGION_PAGE)
		getBaseSteps().setTextToControl("Region_Name_Textbox",regionName,PageLocatorName.REGION_PAGE)
		getBaseSteps().setTextToControl("Region_ID_Textbox",regionID, PageLocatorName.REGION_PAGE)
		getBaseSteps().clickToControl("Save_Button", PageLocatorName.REGION_PAGE)
		getBaseSteps().waitForControlDisplay("Confirmation_Message_Label", PageLocatorName.COMMONLOCATORS_PAGE)
		return regionName
	}
	
	public static String writeValueToRegionFile(String filePath) {
		String number = Utilities.generateRandomNumber()
		String regionName = String.format("Reg_%s",number)

		ExcelUtil.writeContentToFile(filePath, 1, 0, regionName)
		ExcelUtil.writeContentToFile(filePath, 1, 1, String.format("RegID_%s",number))
		return regionName
	}
	
}
