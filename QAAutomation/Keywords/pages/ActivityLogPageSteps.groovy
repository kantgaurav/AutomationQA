package pages

import configs.CMSStrings
import configs.PageLocatorName
import utils.DateTimeUtil

public class ActivityLogPageSteps extends CommonPageSteps{
	public static void verifyPageIsLoaded() {
		getBaseSteps().verifyPageTitleIsCorrect(CMSStrings.ACTIVITY_LOG_PAGE_TITLE)
	}

	public static void filterActivityLog() {

		String functionType = "Approval"
		String actionType =  "Create"
		String years = "2022"
		String startDate = "1/1/2022"
		String endDate = "12/31/2022"

		getBaseSteps().selectItemByTextInComboBoxUsingAction("Function_Type_Combobox", functionType, true, true, PageLocatorName.ACTIVITYLOG_PAGE)
		getBaseSteps().selectItemByTextInComboBoxUsingAction("Action_Type_Combobox", actionType, true,true, PageLocatorName.ACTIVITYLOG_PAGE)
		getBaseSteps().selectItemByTextInComboBoxUsingAction("Years_Combobox", years, true,true, PageLocatorName.ACTIVITYLOG_PAGE)

		getBaseSteps().setTextToControl("From_Date_Textbox", startDate, PageLocatorName.ACTIVITYLOG_PAGE)
		getBaseSteps().clickToControl("From_Date_Label", PageLocatorName.ACTIVITYLOG_PAGE)

		getBaseSteps().setTextToControl("To_Date_Textbox", endDate, PageLocatorName.ACTIVITYLOG_PAGE)
		getBaseSteps().clickToControl("To_Date_Label", PageLocatorName.ACTIVITYLOG_PAGE)

		getBaseSteps().clickToControl("Get_Details_Button", PageLocatorName.ACTIVITYLOG_PAGE)
	}
}
