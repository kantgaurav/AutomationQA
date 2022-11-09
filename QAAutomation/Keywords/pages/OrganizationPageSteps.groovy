package pages

import core.BaseSteps
import core.ControlFactory

public class OrganizationPageSteps {
	protected static BaseSteps getBaseSteps() {
		return new BaseSteps(new ControlFactory());
	}

	public static void verifyPageIsLoaded() {
		getBaseSteps().verifyControlDisplayed("PageTitle_Label", "OrganizationPage");
	}
}
