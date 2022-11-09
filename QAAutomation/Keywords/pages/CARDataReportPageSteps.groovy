package pages

import configs.CMSStrings
import configs.PageLocatorName

public class CARDataReportPageSteps extends CommonPageSteps{
	public static void verifyPageIsLoaded() {
		getBaseSteps().verifyPageTitleIsCorrect(CMSStrings.CAR_DATA_REPORT_PAGE_TITLE)
	}

	public static void filterCARDataReport() {
		getBaseSteps().selectItemByIndexInComboBoxUsingAction("Template_Combobox", 2 ,true, PageLocatorName.CARDATAREPORT_PAGE)
		getBaseSteps().clickToControl("Search_Button", PageLocatorName.CARDATAREPORT_PAGE)
	}

	public static void filterCARDataReport(String template) {
		getBaseSteps().selectItemByTextInComboBoxUsingAction("Template_Combobox", template,true,true, PageLocatorName.CARDATAREPORT_PAGE)
		getBaseSteps().clickToControl("Search_Button", PageLocatorName.CARDATAREPORT_PAGE)
	}
}
