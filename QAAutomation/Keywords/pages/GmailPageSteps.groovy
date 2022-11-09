package pages
import core.Logger
import configs.PageLocatorName
import core.BaseSteps
import core.Browser
import core.ControlFactory
import internal.GlobalVariable
import configs.Timeout
import core.BaseControl
import org.openqa.selenium.WebElement
import org.openqa.selenium.By
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI



public class GmailPageSteps {
	protected static BaseSteps getBaseSteps() {
		return new BaseSteps(new ControlFactory());
	}

	public static void LogintoGmail(String username, String pwd) {
		getBaseSteps().setTextToControl("Email_Textbox",username, PageLocatorName.GMAIL_PAGE)
		getBaseSteps().clickToControl("Next_Button", PageLocatorName.GMAIL_PAGE)
		getBaseSteps().setTextToControl("Password_Textbox",pwd, PageLocatorName.GMAIL_PAGE)
		getBaseSteps().clickToControl("Next_Button", PageLocatorName.GMAIL_PAGE)
		getBaseSteps().waitForControlDoesNotDisplay("Inbox_Link",Timeout.WAIT_TIMEOUT_FEW_SECONDS ,PageLocatorName.GMAIL_PAGE)
	}
	public static void SearchOnGmail(String providerName) {
		getBaseSteps().setTextToControl("Search_Textbox",providerName, PageLocatorName.GMAIL_PAGE)	
		//Code added because sometimes there is a delay in getting e-mails so keep searching till email is found
		boolean done = false;
		int count = 0;
		while (!done && count < 10) {
			try {
				getBaseSteps().clickToControl("Search_Button", PageLocatorName.GMAIL_PAGE)
				Thread.sleep(2000)		 
				getBaseSteps().clickToControl("ApprovalEmail", PageLocatorName.GMAIL_PAGE, 3)
				done = !done
			} catch (Exception e) {
				count++
			}
		}
		
		getBaseSteps().waitForControlDoesNotDisplay("OpenEmail",Timeout.WAIT_TIMEOUT_FEW_SECONDS ,PageLocatorName.GMAIL_PAGE)
		}


			
			
	public static String GetEmailContent(String providerName) {
		String CONT = getBaseSteps().getTextFromControl("Mail_Content_Label",PageLocatorName.GMAIL_PAGE)
		return CONT;
	}

	public static void GmailLogoutAndSignBackiframe() {
		getBaseSteps().waitForControlDisplay("Logout_Button", PageLocatorName.GMAIL_PAGE)
		Thread.sleep(2000)
		getBaseSteps().clickToControl("Logout_Button", PageLocatorName.GMAIL_PAGE)
		//code added because the signout button keeps changing for accounts. Sometime they are part of iframe and sometime they aren't 
		try {
			getBaseSteps().clickToIFrameControl("//header[@id='gb']//iframe[contains(@src, 'account')]", "Signout_Button",  PageLocatorName.GMAIL_PAGE)
		} catch (Exception e) {
			//ignore
			getBaseSteps().clickToControl("Signout_Button", PageLocatorName.GMAIL_PAGE)
		}
		
		Thread.sleep(2000)
		getBaseSteps().clickToControl("UserAnotherAccount_Link", PageLocatorName.GMAIL_PAGE)
	}

	public static void GmailLogoutAndSignBackNoiframe() {
		getBaseSteps().waitForControlDisplay("Logout_Button", PageLocatorName.GMAIL_PAGE)
		getBaseSteps().clickToControl("Logout_Button", PageLocatorName.GMAIL_PAGE)
		
		try {
			getBaseSteps().clickToControl("Signout_Button", PageLocatorName.GMAIL_PAGE)
		} catch (Exception e) {
			//ignore
			getBaseSteps().clickToIFrameControl("//header[@id='gb']//iframe[contains(@src, 'account')]", "Signout_Button",  PageLocatorName.GMAIL_PAGE)	
		}
		
		Thread.sleep(2000)
		getBaseSteps().clickToControl("UserAnotherAccount_Link", PageLocatorName.GMAIL_PAGE)



		//		//solution3
		//		getBaseSteps().clickToIFrameControlByText("//iframe[@role='presentation'][contains(@src,'account')]']","Sign out")
		//		Thread.sleep(2000)
		//		getBaseSteps().clickToControl("Logout_Link", PageLocatorName.GMAIL_PAGE)
		//		WebUI.switchToFrame(findTestObject('Logout_Frame'), 5)
		//	    WORKING getBaseSteps().clickToIFrameControl("//header[@id='gb']/div[2]/div[3]/div[3]/iframe", "Signout_Button",  PageLocatorName.GMAIL_PAGE)
		//		WORKING getBaseSteps().clickToIFrameControl("//header[@id='gb']//iframe[contains(@src, 'account')]", "Signout_Button",  PageLocatorName.GMAIL_PAGE)
		//		getBaseSteps().clickToIFrameControlByText(String iframeXpath, String text)
		//		getBaseSteps().clickToControl("Signout_Button", PageLocatorName.GMAIL_PAGE)
		//		Working Code
		//		getBaseSteps().clickToIFrameControl("//iframe[contains(@src, 'account')]", "Signout_Button",  PageLocatorName.GMAIL_PAGE)
		//		getBaseSteps().clickToControl("UserAnotherAccount_Link", PageLocatorName.GMAIL_PAGE)
	}

	public static void DeleteGmail() {
		WebElement eFrame = Browser.getDriverContext().findElement(By.xpath("//link[contains(@id,'embedded_data_iframe')]"));
		Browser.getDriverContext().switchTo().frame(eFrame);
		getBaseSteps().clickToControl("Delete_Button", PageLocatorName.GMAIL_PAGE)
		//Browser.switchToDefaultWindow();
	}

	public static void signDocument(String ProvidersName) {

		'Search on gmail inbox'
		SearchOnGmail(ProvidersName)
		String wd = Browser.getCurrentWindowHandle()
		getBaseSteps().clickToControl("DocuSign_Link", PageLocatorName.GMAIL_PAGE)
		Browser.switchToNewOpenedWindow()
		getBaseSteps().waitForControlDoesNotDisplay("Loading_Image", PageLocatorName.DOCUSIGN_PAGE)
		'Sign the document'
		getBaseSteps().clickToControl("I_Agree_Checkbox", PageLocatorName.DOCUSIGN_PAGE)
		getBaseSteps().clickToControl("Continue_Button", PageLocatorName.DOCUSIGN_PAGE)
		getBaseSteps().clickToControl("Sign_Button", PageLocatorName.DOCUSIGN_PAGE)
		getBaseSteps().clickToControl("Adopt_And_Sign_Button", PageLocatorName.DOCUSIGN_PAGE)
		getBaseSteps().clickToControl("Finish_Button", PageLocatorName.DOCUSIGN_PAGE)
		'Back to mail and refresh the mail box'
		Browser.switchToWindow(wd)

		//		'Open email inbox'
		//		getBaseSteps().setTextToControl("Email_Textbox",singerMail, PageLocatorName.YOPMAIL_PAGE)
		//		getBaseSteps().clickToControl("Check_Inbox_Button", PageLocatorName.YOPMAIL_PAGE)
		//		String signedEmailName = String.format("Signature Requested - %s",contractDocumentName)
		//		String wd = Browser.getCurrentWindowHandle()
		//		'Open Signature Requested email and click Review document'
		//		getBaseSteps().clickToIFrameControlByText("//iframe[@id='ifinbox']",signedEmailName)
		//		getBaseSteps().clickToIFrameControl("//iframe[@id='ifmail']","Review_Document_Button", PageLocatorName.YOPMAIL_PAGE)
		//		Browser.switchToNewOpenedWindow()
		//		getBaseSteps().waitForControlDoesNotDisplay("Loading_Image", PageLocatorName.DOCUSIGN_PAGE)
		//		'Sign the document'
		//		getBaseSteps().clickToControl("I_Agree_Checkbox", PageLocatorName.DOCUSIGN_PAGE)
		//		getBaseSteps().clickToControl("Continue_Button", PageLocatorName.DOCUSIGN_PAGE)
		//		getBaseSteps().clickToControl("Sign_Button", PageLocatorName.DOCUSIGN_PAGE)
		//		getBaseSteps().clickToControl("Adopt_And_Sign_Button", PageLocatorName.DOCUSIGN_PAGE)
		//		getBaseSteps().clickToControl("Finish_Button", PageLocatorName.DOCUSIGN_PAGE)
		//		'Back to mail and refresh the mail box'
		//		Browser.switchToWindow(wd)
	}

	public static void ApprovingCAROrContract() {
		getBaseSteps().waitForControlDisplay("ClickHere_Link", PageLocatorName.GMAIL_PAGE)
		'Get the current window'
		String wd = Browser.getCurrentWindowHandle()
		getBaseSteps().clickToControl("ClickHere_Link", PageLocatorName.GMAIL_PAGE)
		Browser.switchToNewOpenedWindow()
	}
}
