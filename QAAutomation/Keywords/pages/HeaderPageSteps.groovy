package pages

import configs.PageLocatorName
import core.BaseSteps
import core.ControlFactory

public class HeaderPageSteps {

	protected static BaseSteps getBaseSteps() {
		return new BaseSteps(new ControlFactory());
	}

	public static void logOut() {
		getBaseSteps().clickToControl("User_Image", "HeaderPage")
		getBaseSteps().clickToControl("Logout_Link", "HeaderPage")
		LoginPageSteps.verifyPageIsLoaded()
	}


	public static void verifyPageIsLoaded() {
		getBaseSteps().verifyControlDisplayed("User_Image", PageLocatorName.HEADER_PAGE);
	}
}
