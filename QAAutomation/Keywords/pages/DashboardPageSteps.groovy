package pages

import configs.PageLocatorName
import core.AssertSteps
import core.Browser

public class DashboardPageSteps extends CommonPageSteps{


	public static void viewAssignedTask(String CARName) {
		String xpath = "//div[starts-with(normalize-space(text()),'%s')]/parent::div/following-sibling::div/button"
		getBaseSteps().clickToControlByReplacedTextInXpath(xpath, CARName)
	}

	public static void verifyAssignedTaskNotDisplayed(String CARName) {
		String xpath = "//div[starts-with(normalize-space(text()),'%s')]/parent::div/following-sibling::div/button"
		getBaseSteps().verifyControlByReplacedTextInXpathNotDisplayed(xpath, CARName)
	}
	
	public static String getFirstStatusLabel() {
		String xpathStatusLabel = getBaseSteps().getXpathFromControl("Contract_Status_Form_Contract_Status_Label", PageLocatorName.DASHBOARD_PAGE)
		return getBaseSteps().getTextFromControlByReplacedTextInXpath(xpathStatusLabel,"1")
	}
}
