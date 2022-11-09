package pages

import core.BaseSteps
import core.ControlFactory



public class SearchContractsPageSteps {
	protected static BaseSteps getBaseSteps() {
		return new BaseSteps(new ControlFactory());
	}

	public static void verifyPageIsLoaded() {
		getBaseSteps().verifyControlDisplayed("SearchDocument_Textbox", "SearchContractsPage");
	}
}
