package pages

import configs.PageLocatorName
import core.BaseSteps
import core.ControlFactory

public class LoginPageSteps {

	private static String url = "/login/";

	protected static BaseSteps getBaseSteps() {
		return new BaseSteps(new ControlFactory());
	}

	public static void loginWithUser(String userName, String pwd) {
		getBaseSteps().setTextToControl("Email_Textbox",userName, PageLocatorName.LOGIN_PAGE)
		getBaseSteps().setEncodeTextToControl("Password_Textbox",pwd , PageLocatorName.LOGIN_PAGE)
		getBaseSteps().clickToControl("Login_Button", PageLocatorName.LOGIN_PAGE)
	}

	public static void selectOrgAndRole(String org, String role) {
		getBaseSteps().selectItemByTextInComboBoxUsingAction("Organization_Combobox",org, PageLocatorName.LOGIN_PAGE)
		getBaseSteps().selectItemByTextInComboBoxUsingAction("Role_Combobox",role, PageLocatorName.LOGIN_PAGE)
		getBaseSteps().clickToControl("Login_Button", PageLocatorName.LOGIN_PAGE)
	}
	
	public static void login( String userName, String pwd, String org, String role) {
		loginWithUser(userName,pwd);
		selectOrgAndRole(org,role);
	}
	
	public static void logout() {
		getBaseSteps().clickToControl("User_Image", PageLocatorName.HEADER_PAGE);
		getBaseSteps().clickToControl("Logout_Link", PageLocatorName.HEADER_PAGE);
	}


	public static void verifyPageIsLoaded() {
		getBaseSteps().verifyPageIsLoaded(url)
	}



}


