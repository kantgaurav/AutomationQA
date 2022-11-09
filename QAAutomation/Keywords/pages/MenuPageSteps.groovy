package pages

import configs.PageLocatorName
import core.BaseSteps
import core.ControlFactory

public class MenuPageSteps {

	private final static String CONTRACT_ACTION_REQUEST_MENU= "Contract Action Request"
	private final static String CONTRACT_DOCUMENTS_MENU= "Contract Documents"
	private final static String ORGANIZATION_ADMIN_MENU= "Organization Admin"
	private final static String SYSTEM_ADMIN_MENU= "System Admin"
	private final static String HELP_DOCUMENT_MENU= "Help Documentation"


	protected static BaseSteps getBaseSteps() {
		return new BaseSteps(new ControlFactory());
	}


	public static void selectConntractActionRequestMenu(String subMenu) {
		getBaseSteps().selectMenu("Menu_Link", CONTRACT_ACTION_REQUEST_MENU, subMenu, PageLocatorName.MENU_PAGE)
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void selectContractDocumentsMenu(String subMenu) {
		getBaseSteps().selectMenu("Menu_Link", CONTRACT_DOCUMENTS_MENU, subMenu, PageLocatorName.MENU_PAGE)
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void selectOrganizationAdminMenu(String subMenu) {
		getBaseSteps().selectMenu("Menu_Link", ORGANIZATION_ADMIN_MENU, subMenu, PageLocatorName.MENU_PAGE)
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void selectSystemAdminMenu(String subMenu) {
		getBaseSteps().selectMenu("Menu_Link", SYSTEM_ADMIN_MENU, subMenu, PageLocatorName.MENU_PAGE)
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void selectHelpDocumentationMenu() {
		getBaseSteps().clickToControl("Help_Document_Menu_Link", PageLocatorName.MENU_PAGE)
		getBaseSteps().waitForProgressBarDisappear()
	}
	
	public static void selectDashboardMenu() {
		getBaseSteps().clickToControl("Dashboard_Menu_Link", PageLocatorName.MENU_PAGE)
		getBaseSteps().waitForProgressBarDisappear()
	}

}
