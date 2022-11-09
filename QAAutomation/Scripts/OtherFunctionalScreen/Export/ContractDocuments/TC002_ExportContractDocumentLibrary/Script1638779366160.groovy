import com.kms.katalon.core.annotation.TearDown

import configs.PageLocatorName
import core.Browser
import internal.GlobalVariable
import pages.ContractDocumentLibraryPageSteps
import pages.HeaderPageSteps
import pages.LoginPageSteps
import pages.MenuPageSteps
import pages.RegionPageSteps
import pages.ViewCARPageSteps
import utils.PlatformUtil

'Step 1:'
'Enter the Application URL in browser.'
Browser.start(GlobalVariable.URL)
'ER:'
'System should display CMS Login page.'
LoginPageSteps.verifyPageIsLoaded()

'Step 2:'
'Enter the Username and Password and Click on Login button.'
LoginPageSteps.login(GlobalVariable.SUPER_USER, GlobalVariable.SUPER_PW, "Automation", "System Administrator")
'ER:'
'System should allow the user logged successfully'
HeaderPageSteps.verifyPageIsLoaded()

'Step 3:'
'Select Contract Document Library'
MenuPageSteps.selectConntractActionRequestMenu("Contract Document Library")

'Step 4:'
'Select Download button at first item'
String userAtFirstItem = ContractDocumentLibraryPageSteps.getBaseSteps().getTextFromControl("User_First_Item_Label", PageLocatorName.CONTRACTDOCUMENTLIBRARY_PAGE)
String contractName = ContractDocumentLibraryPageSteps.getBaseSteps().getTextFromControl("First_Document_Name", PageLocatorName.CONTRACTDOCUMENTLIBRARY_PAGE)
ContractDocumentLibraryPageSteps.downloadTemplateAtFirstItem()
'ER:'
'Verify the attached is downloaded'
String fileAfterExported = String.format("%s.docx",contractName);
ContractDocumentLibraryPageSteps.getBaseSteps().verifyFileIsDownloaded(PlatformUtil.getDownloadPath(), fileAfterExported)

println(userAtFirstItem)

'Step 5: '
String filePath = PlatformUtil.getFilePath(PlatformUtil.getDownloadPath(),fileAfterExported)
'Verify that user data is displayed in the exported file'
ContractDocumentLibraryPageSteps.getBaseSteps().verifyWordFileContentIsCorrect(filePath, userAtFirstItem.replaceAll(" ", ""))

'Step 6: Clean up data'
'Delete file '
PlatformUtil.deleteAllFilesWithPrefix(PlatformUtil.getDownloadPath(), contractName)

@TearDown
def closeBrowser(){
Browser.quitDriver();
}



