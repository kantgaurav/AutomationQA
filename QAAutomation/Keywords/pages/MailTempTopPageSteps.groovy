package pages

import configs.PageLocatorName
import configs.Timeout
import core.BaseSteps
import core.ControlFactory
import utils.Utilities
import internal.GlobalVariable
import core.Browser

public class MailTempTopPageSteps {
	protected static BaseSteps getBaseSteps() {
		return new BaseSteps(new ControlFactory());
	}
	public static String getContentCARmail(String mailSubject, String providerName) {

		String listMailXpath = "//div[@id='message-list']/button[contains(@class,'message-list-entry') and .//div[normalize-space()='%s']]"
		String mailContent=""

		int mailQuantity = getBaseSteps().getElementSizeByReplacedTextInXpath(listMailXpath, mailSubject)

		listMailXpath =String.format(listMailXpath,mailSubject).concat("[%s]")

		for(int i = 1; i<=mailQuantity; i++) {
			getBaseSteps().clickToControlByReplacedTextInXpath(listMailXpath, i.toString())
			Utilities.waitForAWhile(Timeout.IMPLICIT_WAIT_TIME_OUT_SECONDS)
			String content = getBaseSteps().getTextFromControl("Mail_Content_Label",PageLocatorName.MAILTEMPTOP_PAGE)
			if(content.contains(providerName)) {
				mailContent = content;
				getBaseSteps().clickToControl("Delete_Mail_Button", PageLocatorName.MAILTEMPTOP_PAGE)
				break;
			}
		}
		return mailContent;
	}

	public static void signDocument(String contractDocumentName, String singerMail) {
		'Open yopmail email'
		Browser.navigateToURL(GlobalVariable.MAIL_TEMP_TOP_URL)
		'Mail is loaded'
		getBaseSteps().verifyControlDisplayed("Email_Textbox", PageLocatorName.MAILTEMPTOP_PAGE)
		'Open email inbox'
		getBaseSteps().setTextToControl("Email_Textbox",singerMail, PageLocatorName.MAILTEMPTOP_PAGE)
		getBaseSteps().clickToControl("Create_Button", PageLocatorName.MAILTEMPTOP_PAGE)
		'Inbox is loaded'
		getBaseSteps().verifyControlDisplayed("Refresh_Button", PageLocatorName.MAILTEMPTOP_PAGE)

		String signedEmailName = String.format("Signature Requested - %s",contractDocumentName)
		String wd = Browser.getCurrentWindowHandle()

		//
		//
		//DO NOT COMPLETE, Due to the inbox mail is not received the mail with title: Signature Requested
		//
		//

		'Back to mail and refresh the mail box'
		Browser.switchToWindow(wd)
	}
}
