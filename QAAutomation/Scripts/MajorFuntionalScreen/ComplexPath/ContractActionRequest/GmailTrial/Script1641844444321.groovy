import com.kms.katalon.core.annotation.TearDown

import configs.PageLocatorName
import core.AssertSteps
import core.Browser
import internal.GlobalVariable
import pages.CARApprovalSetupPageSteps
import pages.CARTemplatesPageSteps
import pages.CostCenterPageSteps
import pages.CreateCARPageSteps
import pages.ExecuteContractPageSteps
import pages.ContractDocumentLibraryPageSteps
import pages.HeaderPageSteps
import pages.LocationPageSteps
import pages.LoginPageSteps
import pages.MailTempTopPageSteps
import pages.MenuPageSteps
import pages.PositionPageSteps
import pages.ProvidersPageSteps
import pages.RegionPageSteps
import pages.SpecialtyPageSteps
import pages.TemplateLibraryPageSteps
import pages.ViewCARPageSteps
import pages.YopmailPageSteps
import pages.GmailPageSteps
import core.Logger
import core.BaseSteps
import utils.PlatformUtil
import pages.DashboardPageSteps
import pages.CARDataReportPageSteps




// code for email login , logout and sign in to a different account
'Step 13: Check email of approver 1 send:'
'Open gmail email'
Browser.start(GlobalVariable.GMAIL_URL)
String createdProvidersName = "FN_73258"
String contractDocumentName = "tln_73289-FN_73258 LN_73258"

'ER:'
'The email is sent'
GmailPageSteps.LogintoGmail(GlobalVariable.SIGNER_USER1,GlobalVariable.GMAIL_PWD)
GmailPageSteps.SearchOnGmail(createdProvidersName +  ' and "signature requested"' )
'ER:'
'Verify that first approver has received the email'
String expectedApprover1EmailContent = String.format("Please DocuSign %s",contractDocumentName)
Logger.logINFO("Expected Result: " + expectedApprover1EmailContent);
String emailAppr1Content = GmailPageSteps.getBaseSteps().getTextFromControl("Mail_Content_LabelForSigner",PageLocatorName.GMAIL_PAGE)
Logger.logINFO("Actual Result: " + emailAppr1Content);
AssertSteps.verifyExpectedResult(emailAppr1Content.contains(expectedApprover1EmailContent))
//GmailPageSteps.GmailLogoutAndSignBackNoiframe()
GmailPageSteps.GmailLogoutAndSignBackiframe()

// add code here for second signer after the first signs it

'Step 17: Check email of notifier 1:'
'Open gmail email and login as the notifier'
GmailPageSteps.LogintoGmail(GlobalVariable.NOTIFIER_MAIL_USER1,GlobalVariable.GMAIL_PWD)
'ER:'
'The preAction email is sent'
Browser.refreshCurrentPage()
GmailPageSteps.SearchOnGmail(createdProvidersName + " and Contract Sent for Signing")
String expectedPreActionEmailContent = String.format("The Contract for %s",createdProvidersName)
Logger.logINFO("Expected Result: " + expectedPreActionEmailContent);
String preActionEmailContent = GmailPageSteps.GetEmailContent(createdProvidersName)
Logger.logINFO("Actual Result: " + preActionEmailContent);
AssertSteps.verifyExpectedResult(preActionEmailContent.contains(expectedPreActionEmailContent))
/**


Browser.start(GlobalVariable.GMAIL_URL)
'ER:'
'The email is sent'
GmailPageSteps.LogintoGmail(GlobalVariable.SIGNER_USER1,GlobalVariable.GMAIL_PWD)
GmailPageSteps.GmailLogoutAndSignBackiframe()
'Step 15: Check email of approver 2 send:'
'ER:'
'Login to gmail email using second approver'
GmailPageSteps.LogintoGmail(GlobalVariable.APPROVER_MAIL_USER2,GlobalVariable.GMAIL_PWD)
GmailPageSteps.GmailLogoutAndSignBackiframe()

/**
'Step 16: Check email of signer 1 send:'
'Open gmail email'
Browser.start(GlobalVariable.GMAIL_URL)
String createdProvidersName = "FN_49880"
String contractDocumentName = "tln_13230-FN_49880 LN_49880"

'ER:'
'The email is sent'
GmailPageSteps.LogintoGmail(GlobalVariable.SIGNER_USER1,GlobalVariable.GMAIL_PWD)
GmailPageSteps.SearchOnGmail(createdProvidersName +  ' and "signature requested"' )
'ER:'
'Verify that first approver has received the email'
String expectedApprover1EmailContent = String.format("Please DocuSign %s",contractDocumentName)
Logger.logINFO("Expected Result: " + expectedApprover1EmailContent);
String emailAppr1Content = GmailPageSteps.getBaseSteps().getTextFromControl("Mail_Content_LabelForSigner",PageLocatorName.GMAIL_PAGE)
Logger.logINFO("Actual Result: " + emailAppr1Content);
AssertSteps.verifyExpectedResult(emailAppr1Content.contains(expectedApprover1EmailContent))
GmailPageSteps.GmailLogoutAndSignBackNoiframe()

// add code here for second signer after the first signs it

'Step 17: Check email of notifier 1:'
'Open gmail email and login as the notifier'
GmailPageSteps.LogintoGmail(GlobalVariable.NOTIFIER_MAIL_USER1,GlobalVariable.GMAIL_PWD)
'ER:'
'The preAction email is sent'
Browser.refreshCurrentPage()
GmailPageSteps.SearchOnGmail(createdProvidersName + " and Contract Sent for Signing")
String expectedPreActionEmailContent = String.format("The Contract for %s",createdProvidersName)
Logger.logINFO("Expected Result: " + expectedPreActionEmailContent);
String preActionEmailContent = GmailPageSteps.GetEmailContent(createdProvidersName)
Logger.logINFO("Actual Result: " + preActionEmailContent);
AssertSteps.verifyExpectedResult(preActionEmailContent.contains(expectedPreActionEmailContent))

// add code here for second signer after the first signs it


**/

/**
'Step 1:'
'Enter the Application URL in browser.'
Browser.start(GlobalVariable.URL)
'ER:'
'System should display CMS Login page.'
LoginPageSteps.verifyPageIsLoaded()

'Step 2:'
'Enter the Username and Password and Click on Login button.'
LoginPageSteps.login(GlobalVariable.APPROVER_MAIL_USER1, GlobalVariable.COMMON_PW, GlobalVariable.ORG_AUTOMATION, GlobalVariable.ROLE_SYSTEM_ADMIN)

'ER:'
'System should allow the user logged successfully'
HeaderPageSteps.verifyPageIsLoaded()

String createdProvidersName = 'FN_27721'
String createdCARRequest = 'Template_12128'
String createdTemplateName = 'tln_15357'
String createdRegionName = 'Reg_82987'
String createdLocationName  = 'Loc_41451'
String createdSpecialtyName  = 'Spec_15298'
String createdPositionName  = 'Pos_27826'
String createdCCName = 'CC_15283'

'Step 12: '
'Executing CAR'
String contractDocumentName = ExecuteContractPageSteps.executingCAR(createdProvidersName, createdCARRequest, createdTemplateName)
'ER:'
'Send and Save successfully'
//ExecuteContractPageSteps.getBaseSteps().waitForControlDisplay("Confirmation_Message_Label", PageLocatorName.COMMONLOCATORS_PAGE);
//snExecuteContractPageSteps.getBaseSteps().verifyMainPopUpHasContent("Successfully sent for Approval.")
//ExecuteContractPageSteps.getBaseSteps().verifyMainPopUpHasContent("Successfully sent.")
Thread.sleep(3000)

'Step 13: Check email of approver 1 send:'
'Open gmail email'
//Browser.start(GlobalVariable.GMAIL_URL)
Browser.navigateToURL(GlobalVariable.GMAIL_URL)
'ER:'
'The email is sent'
GmailPageSteps.LogintoGmail(GlobalVariable.APPROVER_MAIL_USER1,GlobalVariable.GMAIL_PWD)
GmailPageSteps.SearchOnGmail(createdProvidersName +  ' and "contract approval"' )
'ER:'
'Verify that first approver has received the email'
String expectedApprover1EmailContent = String.format("Contract for %s",createdProvidersName)
Logger.logINFO("Expected Result: " + expectedApprover1EmailContent);
String emailAppr1Content = GmailPageSteps.GetEmailContent(createdProvidersName)
Logger.logINFO("Actual Result: " + emailAppr1Content);
AssertSteps.verifyExpectedResult(emailAppr1Content.contains(expectedApprover1EmailContent))




//code added by sunita so approver 1 can approve and then approver 2 can get the email
'Step 14: Approver1 approves from Email'
GmailPageSteps.ApprovingCAROrContract()
'Step 2:'
'Enter the Username and Password and Click on Login button.'
//LoginPageSteps.login(GlobalVariable.APPROVER_MAIL_USER1,  GlobalVariable.COMMON_PW, GlobalVariable.ORG_AUTOMATION, GlobalVariable.ROLE_SYSTEM_ADMIN)
'ER:'
'System should allow the user logged successfully'
ContractDocumentLibraryPageSteps.verifyPageIsLoaded()
'Do filters'
ContractDocumentLibraryPageSteps.getBaseSteps().clickToControl("Filter_Button", PageLocatorName.CONTRACTDOCUMENTLIBRARY_PAGE)
ContractDocumentLibraryPageSteps.filterWith(createdRegionName, createdLocationName, createdCCName, createdSpecialtyName, createdPositionName)
'View and Approve the document'
ContractDocumentLibraryPageSteps.clickOnViewButton(contractDocumentName)
ContractDocumentLibraryPageSteps.getBaseSteps().waitForProgressBarDisappear()
'Approve the document'
ContractDocumentLibraryPageSteps.clickOnTabName("Signing Document")
ContractDocumentLibraryPageSteps.getBaseSteps().clickToControl("Modal_Content_Approve_Contract_Document_Button", PageLocatorName.COMMONLOCATORS_PAGE)
ContractDocumentLibraryPageSteps.getBaseSteps().waitForProgressBarDisappear()
'ER:'
'The document is approved'
String approveStatus = ContractDocumentLibraryPageSteps.getStatusDocument(contractDocumentName)
AssertSteps.verifyExpectedResult(approveStatus.equals("Pending For Approval"),String.format("The status is %s",approveStatus),String.format("The status is %s",approveStatus))
//end of code added by sunita

'Step 15: Check email of approver 2 send:'
'Open gmail email'
Browser.navigateToURL(GlobalVariable.GMAIL_URL)
GmailPageSteps.getBaseSteps().verifyControlDisplayed("Logout_Button", PageLocatorName.GMAIL_PAGE)
GmailPageSteps.GmailLogoutAndSignBackiframe()
'ER:'
'Login to gmail email using second approver'
GmailPageSteps.LogintoGmail(GlobalVariable.APPROVER_MAIL_USER2,GlobalVariable.GMAIL_PWD)
GmailPageSteps.SearchOnGmail(createdProvidersName)
'ER:'
'Verify that second approver has received the email'
String expectedApprover2EmailContent = String.format("Contract for %s",createdProvidersName)
Logger.logINFO("Expected Result: " + expectedApprover2EmailContent);
String emailAppr2Content = GmailPageSteps.GetEmailContent(createdProvidersName)
Logger.logINFO("Actual Result: " + emailAppr2Content);
AssertSteps.verifyExpectedResult(emailAppr2Content.contains(expectedApprover2EmailContent))
GmailPageSteps.GmailLogoutAndSignBackNoiframe()


'Step 15: Check email of notifier 1:'
'Open gmail email and login as the notifier'
GmailPageSteps.LogintoGmail(GlobalVariable.NOTIFIER_MAIL_USER1,GlobalVariable.GMAIL_PWD)
'ER:'
'The preAction email is sent'
GmailPageSteps.SearchOnGmail(createdProvidersName + " and CAR Created")
String expectedPreActionEmailContent = String.format("The CAR for %s",createdProvidersName)
Logger.logINFO("Expected Result: " + expectedPreActionEmailContent);
String preActionEmailContent = GmailPageSteps.GetEmailContent(createdProvidersName)
Logger.logINFO("Actual Result: " + preActionEmailContent);
AssertSteps.verifyExpectedResult(preActionEmailContent.contains(expectedPreActionEmailContent))

'ER:'
'The postAction email is sent'
GmailPageSteps.SearchOnGmail(createdProvidersName + " and CAR Approved")
String expectedPosActionEmailContent = String.format("The CAR for %s",createdProvidersName)
Logger.logINFO("Expected Result: " + expectedPosActionEmailContent);
String posActionEmailContent = GmailPageSteps.GetEmailContent(createdProvidersName)
Logger.logINFO("Actual Result: " + posActionEmailContent);
AssertSteps.verifyExpectedResult(posActionEmailContent.contains(expectedPosActionEmailContent))
Browser.navigateToURL(GlobalVariable.URL)

**/

//@TearDown
def closeBrowser(){
Browser.quitDriver();

}



