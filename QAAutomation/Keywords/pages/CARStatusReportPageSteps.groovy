package pages

import configs.CMSStrings
import configs.PageLocatorName

public class CARStatusReportPageSteps extends CommonPageSteps{
	public static void verifyPageIsLoaded() {
		getBaseSteps().verifyPageTitleIsCorrect(CMSStrings.CAR_STATUS_REPORT_PAGE_TITLE)
	}

	public static void verifyCARReportTable(String providerFName, String createdRegionName, String createdLocationName, String createdCCName, String createdSpecialtyName, String createdPositionName, String createdTemplateName) {
		List valueList = [
			providerFName,
			createdRegionName,
			createdLocationName,
			createdCCName,
			createdSpecialtyName,
			createdPositionName,
			createdTemplateName
		]

		for(int i = 2; i <= 8; i++) {
			verifyDataIsDisplayedAfterSearch(i.toString(), valueList[i-2])
		}
	}

	public static void filterCARStatus() {
		getBaseSteps().selectItemByTextInComboBoxUsingAction("Status_Combobox", "Approved", true, true, PageLocatorName.CARSTATUSREPORT_PAGE)
		getBaseSteps().clickToControl("Get_Details_Button", PageLocatorName.CARSTATUSREPORT_PAGE)
	}
}
