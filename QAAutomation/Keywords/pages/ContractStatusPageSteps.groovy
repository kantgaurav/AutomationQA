package pages

import configs.CMSStrings
import configs.PageLocatorName
public class ContractStatusPageSteps extends CommonPageSteps{
	public static void verifyPageIsLoaded() {
		getBaseSteps().verifyPageTitleIsCorrect(CMSStrings.CONTRACT_STATUS_PAGE_TITLE)
	}
	
	public static void verifyContractReportTable(String contractDocumentName, String providerFName, String createdRegionName, String createdLocationName, String createdCCName, String createdSpecialtyName, String createdPositionName) {
		List valueList = [
			contractDocumentName,
			providerFName,
			createdRegionName,
			createdLocationName,
			createdCCName,
			createdSpecialtyName,
			createdPositionName
		]

		for(int i = 2; i <= 8; i++) {
			verifyDataIsDisplayedAfterSearch(i.toString(), valueList[i-2])
		}
	}
	
	public static void filterContractStatus() {
		getBaseSteps().selectItemByIndexInComboBoxUsingAction("Status_Combobox", 2, true, PageLocatorName.CONTRACTSTATUS_PAGE)
		getBaseSteps().clickToControl("Get_Details_Button", PageLocatorName.CONTRACTSTATUS_PAGE)
	}
}
