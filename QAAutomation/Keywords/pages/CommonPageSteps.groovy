package pages

import configs.CMSStrings
import configs.PageLocatorName
import core.AssertSteps
import core.BaseSteps
import core.Browser
import core.ControlFactory
import internal.GlobalVariable
import utils.DateTimeUtil
import utils.PlatformUtil



public class CommonPageSteps {

	protected static BaseSteps getBaseSteps() {
		return new BaseSteps(new ControlFactory());
	}


	public static void search(String name) {
		getBaseSteps().setTextToControl("Search_Textbox",name,PageLocatorName.COMMONLOCATORS_PAGE)
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void searchAndDelete(String deletedData) {

		search(deletedData)
		delete()
	}

	public static void delete() {
		if(getBaseSteps().isTableHasData("Search_Table", PageLocatorName.COMMONLOCATORS_PAGE)) {
			getBaseSteps().clickToCellTableButton("Search_Table", "last()",CMSStrings.DELETE_BUTTON_IN_TABLE, PageLocatorName.COMMONLOCATORS_PAGE)//last is Action column
			getBaseSteps().waitForControlDisplay("Modal_Content_Label", PageLocatorName.COMMONLOCATORS_PAGE)
			getBaseSteps().clickToControl("Modal_Content_OK_Button", PageLocatorName.COMMONLOCATORS_PAGE)
			getBaseSteps().waitForControlDisplay("Confirmation_Message_Label", PageLocatorName.COMMONLOCATORS_PAGE)
		}
	}

	public static void importFile(String filePath) {
		RegionPageSteps.getBaseSteps().clickToControl("Import_Button", PageLocatorName.COMMONLOCATORS_PAGE)
		RegionPageSteps.getBaseSteps().uploadFile("Upload_File_Textbox", filePath, PageLocatorName.COMMONLOCATORS_PAGE)
		RegionPageSteps.getBaseSteps().waitForControlDisplay("Modal_Content_Data_Summary_OK_Lable", PageLocatorName.COMMONLOCATORS_PAGE)
		RegionPageSteps.getBaseSteps().clickToControl("Modal_Content_Import_Button", PageLocatorName.COMMONLOCATORS_PAGE)
	}

	public static void verifyDataIsDisplayedAfterSearch(String columIndexToGetData, String expectedData) {
		String actualData = getBaseSteps().getCellTableText("Search_Table", columIndexToGetData, PageLocatorName.COMMONLOCATORS_PAGE)//4 is Location Name col
		AssertSteps.verifyExpectedResult(actualData.equals(expectedData),String.format("The %s data is created",expectedData),String.format("The %s data is created",expectedData))
	}

	public static verifyColumnHeaderInExportedFile(String excelFilePath, int startIndexColumn, int endIndexColumn) {
		String xpath = getBaseSteps().getXpathFromControl("Column_Header", PageLocatorName.COMMONLOCATORS_PAGE)
		String listdata = ""
		for(int i = startIndexColumn; i<= endIndexColumn; i++) {
			listdata += getBaseSteps().getTextFromControlByReplacedTextInXpath(xpath,i.toString()) + " "
		}
		getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, listdata)
	}

	public static String verifyFileIsDownloadedSuccessfully(String prefixFile, String section = "") {
		String datetimeDownload = DateTimeUtil.getDateTime("yyyyMMdd")
		String fileNameAfterExported = String.format("%s-%s.xlsx",prefixFile,datetimeDownload);
		if(section != "") {
			getBaseSteps().verifyMainPopUpHasContent("${section} data exported successfully.")
		}

		getBaseSteps().verifyFileIsDownloaded(PlatformUtil.getDownloadPath(), fileNameAfterExported)

		return fileNameAfterExported
	}
	
	public static String verifyFileIsDownloadedSuccessfullyForCARDataReport(String prefixFile, String section = "") {
		String datetimeDownload = DateTimeUtil.getDateTime("yyyyMMdd")
		String fileNameAfterExported = String.format("%s-%s.xlsx",prefixFile,datetimeDownload);
		if(section != "") {
			getBaseSteps().verifyMainPopUpHasContent("${section} exported successfully.")
		}

		getBaseSteps().verifyFileIsDownloaded(PlatformUtil.getDownloadPath(), fileNameAfterExported)

		return fileNameAfterExported
	}

	public static verifyFirstRowInExportedFile(String excelFilePath, int startIndexColumn, int endIndexColumn) {
		String xpath = getBaseSteps().getXpathFromControl("First_Row", PageLocatorName.COMMONLOCATORS_PAGE)
		String listdata = ""
		for(int i = startIndexColumn; i<= endIndexColumn; i++) {
			listdata += getBaseSteps().getTextFromControlByReplacedTextInXpath(xpath,i.toString()) + " "
		}
		getBaseSteps().verifyExcelFileContentIsCorrect(excelFilePath, listdata)
	}

	public static String getCellTextAtFirstRow(int indexColumn) {
		String xpath = getBaseSteps().getXpathFromControl("First_Row", PageLocatorName.COMMONLOCATORS_PAGE)
		return getBaseSteps().getTextFromControlByReplacedTextInXpath(xpath,indexColumn.toString())
	}

	public static approvalCARbyAnotherAccount(String createdProvidersName) {
		MenuPageSteps.selectDashboardMenu()
		Browser.refreshCurrentPage()
		DashboardPageSteps.viewAssignedTask(createdProvidersName)
		Browser.switchToNewOpenedWindow()
		getBaseSteps().verifyPageTitleIsCorrect("Contract Action Requests")
		getBaseSteps().clickToCellTableButton("Search_Table", "last()","Approve", PageLocatorName.COMMONLOCATORS_PAGE)//last is Action column
	}
}
