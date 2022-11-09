package pages

import configs.CMSStrings

public class UpcomingRenewalsPageSteps extends CommonPageSteps{
	public static void verifyPageIsLoaded() {
		getBaseSteps().verifyPageTitleIsCorrect(CMSStrings.UPCOMING_RENEWALS_PAGE_TITLE)
	}
}
