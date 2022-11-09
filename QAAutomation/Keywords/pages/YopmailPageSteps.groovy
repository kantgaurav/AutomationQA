package pages

import configs.PageLocatorName
import core.BaseSteps
import core.Browser
import core.ControlFactory
import internal.GlobalVariable



public class YopmailPageSteps {

	protected static BaseSteps getBaseSteps() {
		return new BaseSteps(new ControlFactory());
	}

	public static void refreshMailBoxToWaitEmail(String emailName) {

		int count = 1;
		while(!getBaseSteps().isIFrameControlByReplacedTextInXpathDisplayed("//iframe[@id='ifinbox']","//*[starts-with(normalize-space(),\"%s\")]",emailName)) {
			getBaseSteps().clickToControl("Refresh_Button", PageLocatorName.YOPMAIL_PAGE)
			count++;
			if(count==3) {
				break;
			}
		}
	}

	public static void signDocument(String contractDocumentName, String singerMail) {
		'Open yopmail email'
		Browser.navigateToURL(GlobalVariable.EMAIL_URL)
		'Open email inbox'
		getBaseSteps().setTextToControl("Email_Textbox",singerMail, PageLocatorName.YOPMAIL_PAGE)
		getBaseSteps().clickToControl("Check_Inbox_Button", PageLocatorName.YOPMAIL_PAGE)
		String signedEmailName = String.format("Signature Requested - %s",contractDocumentName)
		String wd = Browser.getCurrentWindowHandle()
		'Open Signature Requested email and click Review document'
		getBaseSteps().clickToIFrameControlByText("//iframe[@id='ifinbox']",signedEmailName)
		getBaseSteps().clickToIFrameControl("//iframe[@id='ifmail']","Review_Document_Button", PageLocatorName.YOPMAIL_PAGE)
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
	}

	public static void deleteCompletedMail(String completedEmailName) {

		refreshMailBoxToWaitEmail(completedEmailName)
		getBaseSteps().clickToIFrameControlByText("//iframe[@id='ifinbox']",completedEmailName)
		getBaseSteps().clickToIFrameControl("//iframe[@id='ifmail']","Delete_Button", PageLocatorName.YOPMAIL_PAGE)
	}

	public static String getContentCARmail(String mailSubject, String providerName) {

		String listMailXpath = "//div[contains(@id,'e_') and .//div[normalize-space()='%s']]"
		String mailContent=""

		int mailQuantity = getBaseSteps().getIFrameElementsSizeByReplacedTextInXpath("//iframe[@id='ifinbox']",listMailXpath, mailSubject)

		listMailXpath =String.format(listMailXpath,mailSubject).concat("[%s]")

		for(int i = 1; i<=mailQuantity; i++) {
			getBaseSteps().clickToIFrameControlByReplacedTextInXpath("//iframe[@id='ifinbox']",listMailXpath, i.toString())

			String content = getBaseSteps().getTextFromIFrControl("//iframe[@id='ifmail']","Mail_Content_Label",PageLocatorName.YOPMAIL_PAGE)
			if(content.contains(providerName)) {
				mailContent = content;
				getBaseSteps().clickToIFrameControl("//iframe[@id='ifmail']","Delete_Button", PageLocatorName.YOPMAIL_PAGE)
				break;
			}
		}
		return mailContent;
	}
}
