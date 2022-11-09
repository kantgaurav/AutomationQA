package pages

import configs.CMSStrings

public class OutlierReportPageSteps extends CommonPageSteps{
	public static void verifyPageIsLoaded() {
		getBaseSteps().verifyPageTitleIsCorrect(CMSStrings.OUTLIER_REPORT_PAGE_TITLE)
	}
}
