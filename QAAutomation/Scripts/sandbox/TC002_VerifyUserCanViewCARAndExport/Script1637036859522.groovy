import com.kms.katalon.core.annotation.SetUp
import com.kms.katalon.core.annotation.TearDown

import core.Browser
import internal.GlobalVariable
import pages.HeaderPageSteps
import pages.LoginPageSteps
import pages.MenuPageSteps
import pages.ViewCARPageSteps
import utils.PlatformUtil

@SetUp
def setUp() {
	PlatformUtil.deleteAllFilesWithPrefix(PlatformUtil.getDownloadPath(), fileNameAfterExported)
}


'Step 1:'
'Enter the Application URL in browser.'
Browser.start(GlobalVariable.URL)
'ER:'
'System should display CMS Login page.'
LoginPageSteps.verifyPageIsLoaded()

'Step 2:'
'Enter the Username and Password and Click on Login button.'
LoginPageSteps.login(GlobalVariable.SUPER_USER, GlobalVariable.SUPER_PW, GlobalVariable.ORG_AUTOMATION, GlobalVariable.ROLE_SYSTEM_ADMIN)
'ER:'
'System should allow the user logged successfully'
HeaderPageSteps.verifyPageIsLoaded()

'Step 3:'
'Select View CARs'
MenuPageSteps.selectConntractActionRequestMenu("View CARs")
'ER:'
'Verify Page title is correct'
ViewCARPageSteps.getBaseSteps().verifyPageTitleIsCorrect("Contract Action Requests")

'Step 4:'
'Search a provider'
ViewCARPageSteps.getBaseSteps().setTextToControl("Search_Textbox", provider, "ViewCARPage")
ViewCARPageSteps.getBaseSteps().waitForProgressBarDisappear()
'ER:'
'CAR table is displayed'
ViewCARPageSteps.getBaseSteps().verifyControlDisplayed("CAR_Table", "ViewCARPage")

'Step 5:'
'Open Preview tab'
ViewCARPageSteps.getBaseSteps().clickToCellTableButtonBaseOnAnotherCellDataTable("CAR_Table", "3", provider, "1", "ViewCARPage")//3 is Provider, 1 is 3 dots
ViewCARPageSteps.getBaseSteps().selectItemInThreeDotCombobox("Preview")
'ER:'
'CAR Detail is loaded correctly'
ViewCARPageSteps.getBaseSteps().verifyMainModalHasContent(CARDetails)

'Step 6:'
'Click to Export button'
ViewCARPageSteps.getBaseSteps().clickToControl("Export_Button", "ViewCARPage")
'ER:'
'Verify excel file is downloaded'
ViewCARPageSteps.getBaseSteps().verifyMainPopUpHasContent("Contract Action Requests data exported successfully.")
ViewCARPageSteps.getBaseSteps().verifyFileIsDownloaded(PlatformUtil.getDownloadPath(),fileNameAfterExported)


@TearDown
def closeBrowser(){
Browser.quitDriver();
}




