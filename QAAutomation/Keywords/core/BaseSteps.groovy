package core
import java.awt.Toolkit
import java.awt.datatransfer.DataFlavor

import com.google.common.base.Strings

import configs.PageLocatorName
import configs.Timeout
import controls.CheckBox
import controls.ComboBox
import controls.IFrame
import controls.ListBox
import controls.Menu
import controls.RadioButton
import controls.Table
import utils.EncodeUtil
import utils.ExcelUtil
import utils.PlatformUtil
import utils.Utilities
import utils.WordUtil

public class BaseSteps {
	private IControlFactory controlFactory;
	public BaseSteps(IControlFactory controlFactory){
		this.controlFactory = controlFactory;
	}
	public IControlFactory getControlFactory(){
		return this.controlFactory;
	}
	// ===================== COMMON ACTIONS STEPS =====================
	public void clearTextInControl(String controlName, String pageName){
		BaseControl control = controlFactory.initControl(BaseControl, controlName,pageName);
		control.clearText();
	}
	public void clickToControl(String controlName, String pageName){
		BaseControl control = controlFactory.initControl(BaseControl, controlName,pageName);
		control.click();
	}
	
	public void clickToControl(String controlName, String pageName, int tries){
		BaseControl control = controlFactory.initControl(BaseControl, controlName,pageName);
		control.click(tries);
	}
	
	public void clickToControlByText(String textControl){
		BaseControl control = new BaseControl()
		control.clickByText(textControl);
	}

	public String getTextFromControl(String controlName, String pageName){
		BaseControl control = controlFactory.initControl(BaseControl, controlName, pageName);
		return control.getText();
	}
	public String getValueFromControl(String controlName, String pageName){
		BaseControl control = controlFactory.initControl(BaseControl, controlName, pageName);
		return control.getValue();
	}
	public String getAttributeFromControl(String controlName, String attributeName, String pageName){
		BaseControl control = controlFactory.initControl(BaseControl, controlName, pageName);
		return control.getAttribute(attributeName)
	}
	public String getXpathFromControl(String controlName, String pageName){
		BaseControl control = controlFactory.initControl(BaseControl, controlName, pageName);
		return control.xpath;
	}
	public void openLinkInNewTab(String controlName, String pageName) {
		BaseControl control = controlFactory.initControl(BaseControl, controlName, pageName);
		control.openLinkInNewTab();
	}
	//	public void openSubMenuInNewTab(String controlName, String subMenu, String pageName) {
	//		Menu control = controlFactory.initControl(Menu, controlName, pageName);
	//		control.openSubMenuInNewTab(subMenu);
	//	}
	public void setTextToControl(String controlName, String inputText, String pageName){
		BaseControl control = controlFactory.initControl(BaseControl, controlName,pageName);
		if(!Strings.isNullOrEmpty(control.getValue())){
			control.setValueByJS("");
		}
		control.clearText();
		control.sendKeys(inputText);
	}
	public void setEncodeTextToControl(String controlName, String encodeText, String pageName){
		BaseControl control = controlFactory.initControl(BaseControl, controlName,pageName);
		if(!Strings.isNullOrEmpty(control.getValue())){
			control.setValueByJS("");
		}
		control.clearText();
		control.sendKeys(EncodeUtil.decode(EncodeUtil.getDefault(encodeText)));
	}
	public void selectMenu(String controlName, String subMenu, String subItemMenu="", String pageName) {
		Menu control = controlFactory.initControl(Menu, controlName, pageName);
		control.selectMenu(subMenu,subItemMenu);
	}
	//	public void selectItemByTextInComboBox(String controlName, String itemText, boolean isClickToOpen=true, boolean isDeselectAll =false,String pageName){
	//		ComboBox control = controlFactory.initControl(ComboBox, controlName, pageName);
	//		control.selectByText(itemText,isClickToOpen,isDeselectAll);
	//	}
	//	public void selectItemByContainedTextInComboBox(String controlName, String itemText,boolean isClickToOpen=true, boolean isClickByJS =false, String pageName){
	//		ComboBox control = controlFactory.initControl(ComboBox, controlName, pageName);
	//		control.selectByContainedText(itemText,isClickToOpen,isClickByJS);
	//	}
	//	public void selectCheckboxByContainedTextInComboBox(String controlName, String itemText, String pageName){
	//		ComboBox control = controlFactory.initControl(ComboBox, controlName, pageName);
	//		control.selectCheckboxByContainedText(itemText);
	//	}
	//	public void selectCheckboxByContainedTextInComboBoxHasAList(String controlName, String itemText,boolean isClickToOpen=true, String pageName){
	//		ComboBox control = controlFactory.initControl(ComboBox, controlName, pageName);
	//		control.selectCheckboxByContainedTextInAList(itemText,isClickToOpen);
	//	}




	public void submitToControl(String controlName, String pageName){
		BaseControl control = controlFactory.initControl(BaseControl, controlName,pageName);
		control.submit();
	}
	public void waitForControlDoesNotDisplay(String controlName, int timeOut = Timeout.WAIT_TIMEOUT_LONG_SECONDS, String pageName){
		BaseControl control = controlFactory.initControl(BaseControl, controlName,pageName);
		control.waitForControlDoesNotDisplay(timeOut);
	}
	public void waitForControlDisplay(String controlName, int timeOut = Timeout.WAIT_TIMEOUT_MEDIUM_SECONDS, String pageName){
		BaseControl control = controlFactory.initControl(BaseControl, controlName,pageName);
		control.waitForControlDisplay(timeOut);
	}

	public void uploadFile(String controlName, String filePath, String pageName){
		BaseControl control = controlFactory.initControl(BaseControl, controlName,pageName);
		control.uploadFile(filePath);
	}
	// ===================== COMMON ASSERTION STEPS =====================
	public void verifyPageIsLoaded(String url, String errMsg=String.format("The URL >>>%s<<< is NOT changed",url)){
		String passMsg = String.format("The page is loaded with >>>%s<<<",url)
		Browser.waitForUrlChange(url, Timeout.WAIT_TIMEOUT_SHORT_SECONDS);
		AssertSteps.verifyExpectedResult(Browser.getCurrentURL().toLowerCase().contains(url.toLowerCase()),passMsg,errMsg);
	}
	public void verifyPageHeaderCorrect(String expectedHeader, String errMsg="The page header is NOT correct"){
		String passMsg = String.format("The current page header is >>>%s<<<",expectedHeader)
		AssertSteps.verifyExpectedResult(Browser.getPageHeader().trim().equalsIgnoreCase(expectedHeader),passMsg,errMsg);
	}
	public void verifyControlDisplayed(String controlName, String pageName, String errMsg="The control does NOT Display"){
		String passMsg = String.format("The control >>>%s<<< is displayed on page >>>%s<<<",controlName,pageName)
		BaseControl control = controlFactory.initControl(BaseControl, controlName,pageName);
		control.waitForControlDisplay(Timeout.WAIT_TIMEOUT_SHORT_SECONDS)
		AssertSteps.verifyExpectedResult(control.isDisplayed(),passMsg,errMsg);
	}
	public void verifyControlNotDisplayed(String controlName, String pageName, String errMsg="The control is Displayed"){
		String passMsg = String.format("The control >>>%s<<< is not displayed on page >>>%s<<<",controlName,pageName)
		BaseControl control = controlFactory.initControl(BaseControl, controlName,pageName);
		AssertSteps.verifyExpectedResult(!control.isDisplayed(),passMsg,errMsg);
	}
	public void verifyControlNotDisplayedORDisplayWithoutText(String controlName,String expectedText, String pageName, String errMsg="The control is Displayed"){
		String passMsg = String.format("The control >>>%s<<< is not displayed on page >>>%s<<<",controlName,pageName)
		BaseControl control = controlFactory.initControl(BaseControl, controlName,pageName);
		if(control.isDisplayed()) {
			String controlText = control.getText();
			AssertSteps.verifyExpectedResult(!controlText.equals(expectedText),passMsg,errMsg);

		}else {
			AssertSteps.verifyExpectedResult(!control.isDisplayed(),passMsg,errMsg);
		}
	}
	public void verifyControlDisabled(String controlName, String pageName, String errMsg="The control does NOT Disable"){
		String passMsg = String.format("The control >>>%s<<< is disabled on page >>>%s<<<",controlName,pageName)
		BaseControl control = controlFactory.initControl(BaseControl, controlName,pageName);
		AssertSteps.verifyExpectedResult(!control.isEnabled(),passMsg,errMsg);
	}
	public void verifyControlEnabled(String controlName, String pageName, String errMsg="The control does NOT Enable"){
		String passMsg = String.format("The control >>>%s<<< is enabled on page >>>%s<<<",controlName,pageName)
		BaseControl control = controlFactory.initControl(BaseControl, controlName,pageName);
		AssertSteps.verifyExpectedResult(control.isEnabled(),passMsg,errMsg);
	}

	public void verifyMainPopUpMatchedContent(String wildcardContent,String errMsg="The pop up does NOT show correct content") {
		String popUpContent=""
		try {
			popUpContent = getTextFromControl("Main_Popup", PageLocatorName.COMMONLOCATORS_PAGE);
		}catch(Throwable e) {
			Logger.logERROR(String.format("Pop up does NOT display: >>>%s<<<",e.getMessage()))
		}
		String passMsg = String.format("The pop up content has content as >>>%s<<<",popUpContent)
		AssertSteps.verifyExpectedResult(Utilities.isStringMatchedWithWildcard(wildcardContent, popUpContent),passMsg,errMsg);
		clickToControl("Close_Popup_Button", PageLocatorName.COMMONLOCATORS_PAGE)
	}


	public void verifyCheckBoxIsChecked(String controlName, String pageName, String errMsg="The control is NOT checked"){
		String passMsg = String.format("The check box >>>%s<<< is checked",controlName)
		CheckBox control = controlFactory.initControl(CheckBox, controlName,pageName);
		AssertSteps.verifyExpectedResult(control.isChecked(),passMsg,errMsg);
	}
	public void verifyPseudoCheckBoxIsChecked(String controlName, String pageName, String errMsg="The control is NOT checked"){
		String passMsg = String.format("The check box >>>%s<<< is checked",controlName)
		CheckBox control = controlFactory.initControl(CheckBox, controlName,pageName);
		AssertSteps.verifyExpectedResult(control.isCheckedByJS(),passMsg,errMsg);
	}
	public void verifyControlWithTextDisplayed(String controlName, String expectedText, String pageName, String errMsg="Displayed and Text does NOT meet the verification"){
		String passMsg = String.format("The control >>>%s<<< with text >>>%s<<< is displayed",controlName,expectedText)
		BaseControl control = controlFactory.initControl(BaseControl, controlName,pageName );
		String controlText = control.getText();
		errMsg = String.format("The control >>>%s<<< has a current >>>%s<<<  text",controlName,controlText)
		AssertSteps.verifyExpectedResult(control.isDisplayed()&&controlText.equals(expectedText),passMsg,errMsg);
	}
	public void verifyControlContainedTextDisplayed(String controlName, String expectedText, String pageName, String errMsg="Displayed and Text does NOT meet the verification"){
		String passMsg = String.format("The control >>>%s<<< contains text >>>%s<<< is displayed",controlName,expectedText)
		BaseControl control = controlFactory.initControl(BaseControl, controlName,pageName );
		String controlText = control.getText();
		AssertSteps.verifyExpectedResult(control.isDisplayed()&&controlText.contains(expectedText),passMsg,errMsg);
	}
	public void verifyControlNotContainsExpectedText(String controlName, String expectedText, String pageName, String errMsg="Still match with text"){
		String passMsg = String.format("The control >>>%s<<< does NOT contain text >>>%s<<<",controlName,expectedText)
		BaseControl control = controlFactory.initControl(BaseControl, controlName,pageName);
		String controlText = control.getText();
		AssertSteps.verifyExpectedResult(!controlText.equals(expectedText),passMsg,errMsg);
	}
	public void verifyControlContainedValueDisplayed(String controlName, String expectedValue, String pageName, String errMsg="Displayed and Text does NOT meet the verification"){
		String passMsg = String.format("The control >>>%s<<< contains text >>>%s<<< is displayed",controlName,expectedValue)
		BaseControl control = controlFactory.initControl(BaseControl, controlName,pageName );
		String controlValue = control.getValue();
		AssertSteps.verifyExpectedResult(control.isDisplayed()&&controlValue.contains(expectedValue),passMsg,errMsg);
	}
	public void verifyOptionIsDefaultInComboBox(String controlName, String optionName, String pageName, String errMsg="The option is not displayed in the combo box"){
		String passMsg = String.format("The combo box >>>%s<<< has default value as >>>%s<<<",controlName,optionName)
		ComboBox control = controlFactory.initControl(ComboBox, controlName,pageName);
		AssertSteps.verifyExpectedResult(control.isOptionDefault(optionName),passMsg,errMsg);
	}


	//	public void verifyOptionIsContainedInComboBox(String controlName, String optionName, String pageName, String errMsg="The option is not displayed in the combo box"){
	//		String passMsg = String.format("The combo box >>>%s<<< contains >>>%s<<< option ",controlName,optionName)
	//		ComboBox control = controlFactory.initControl(ComboBox, controlName,pageName);
	//		AssertSteps.verifyExpectedResult(control.isOptionIncluded(optionName),passMsg,errMsg);
	//	}
	//
	//	public void verifyOptionIsNOTContainedInComboBox(String controlName, String optionName, String pageName, String errMsg="The option is still displayed in the combo box"){
	//		String passMsg = String.format("The combo box >>>%s<<< does NOT contains >>>%s<<< option ",controlName,optionName)
	//		ComboBox control = controlFactory.initControl(ComboBox, controlName,pageName);
	//		AssertSteps.verifyExpectedResult(!control.isOptionIncluded(optionName),passMsg,errMsg);
	//	}
	//	public void verifyOptionIsCheckedInComboBox(String controlName, String optionName, String pageName, String errMsg="The option is unchecked in the combo box"){
	//		String passMsg = String.format("The option >>>%s<<< is checked in >>>%s<<< combobox ",optionName,controlName)
	//		ComboBox control = controlFactory.initControl(ComboBox, controlName,pageName);
	//		AssertSteps.verifyExpectedResult(control.isCheckBoxChecked(optionName),passMsg,errMsg);
	//	}
	//	public void verifyOptionIsUnCheckedInComboBox(String controlName, String optionName, String pageName, String errMsg="The option is checked in the combo box"){
	//		String passMsg = String.format("The option >>>%s<<< is unchecked in >>>%s<<< combobox ",optionName,controlName)
	//		ComboBox control = controlFactory.initControl(ComboBox, controlName,pageName);
	//		AssertSteps.verifyExpectedResult(!control.isCheckBoxChecked(optionName),passMsg,errMsg);
	//	}
	//	public void verifyOptionIsEnabledInComboBox(String controlName, String optionName, String pageName, String errMsg="The option is checked in the combo box"){
	//		String passMsg = String.format("The option >>>%s<<< is unchecked in >>>%s<<< combobox ",optionName,controlName)
	//		ComboBox control = controlFactory.initControl(ComboBox, controlName,pageName);
	//		AssertSteps.verifyExpectedResult(control.isCheckBoxEnabled(optionName),passMsg,errMsg);
	//	}
	//	public void verifyOptionIsDisabledInComboBox(String controlName, String optionName, String pageName, String errMsg="The option is checked in the combo box"){
	//		String passMsg = String.format("The option >>>%s<<< is unchecked in >>>%s<<< combobox ",optionName,controlName)
	//		ComboBox control = controlFactory.initControl(ComboBox, controlName,pageName);
	//		AssertSteps.verifyExpectedResult(!control.isCheckBoxEnabled(optionName),passMsg,errMsg);
	//	}
	public void verifyFileIsDownloaded(String downloadPath, String fileName){
		String passMsg = String.format("The file %s is downloaded at the folder %s",fileName,downloadPath);
		String errMsg = String.format("The file %s is NOT downloaded at the folder %s",fileName, downloadPath)
		boolean a = PlatformUtil.isFileDownloaded(downloadPath, fileName)
		AssertSteps.verifyFileIsDownloaded(PlatformUtil.isFileDownloaded(downloadPath, fileName),passMsg,errMsg);
	}
	// ===================== TABLE STEPS =====================
	// ===================== TABLE COMMON ACTION STEPS =====================
	public String getRowTableCounts(String tableName, String pageName){
		waitForTableDataLoaded(tableName,pageName);
		Table control = controlFactory.initControl(Table, tableName, pageName);
		return control.getRowCounts().toString();
	}
	public String getRowTableText(String tableName, String rowIndex="1", String pageName){
		waitForTableDataLoaded(tableName,pageName);
		Table control = controlFactory.initControl(Table, tableName, pageName);
		return control.getRowText(rowIndex);
	}
	public String getCellLinkTableAttributeBasedOnCellContainsText(String tableName, String conditionColumn, String conditionData, String colName, String attribute, String pageName){
		waitForTableDataLoaded(tableName,pageName);
		Table control = controlFactory.initControl(Table, tableName, pageName);
		int rows =Integer.parseInt(getRowTableCounts(tableName,pageName));

		for(int i=1;i<=rows;i++){
			if(getCellTableText(tableName,conditionColumn, i.toString(),pageName).contains(conditionData)) {
				return control.getCellLinkAttribute(i.toString(), colName, attribute);
				break;
			}
		}
	}
	public String getCellTableValue(String tableName, String rowIndex="1", String colName, String pageName){
		waitForTableDataLoaded(tableName,pageName);
		Table control = controlFactory.initControl(Table, tableName, pageName);
		return control.getCellValue(rowIndex, colName);
	}
	public String getCellFooterTableText(String tableName, String rowIndex="1", String colName, String pageName){
		waitForTableDataLoaded(tableName,pageName);
		Table control = controlFactory.initControl(Table, tableName, pageName);
		return control.getCellTextFromFooter(rowIndex, colName)
	}
	public String getCheckBoxCellTableValue(String tableName, String rowIndex="1", String colName, String pageName){
		waitForTableDataLoaded(tableName,pageName);
		Table control = controlFactory.initControl(Table, tableName, pageName);
		if(control.getCheckBoxCellValue(rowIndex, colName)) {
			return "checked";
		}else {
			return "unchecked";
		}
	}
	public String getBodyTableText(String tableName, String pageName){
		waitForTableDataLoaded(tableName,pageName);
		Table control = controlFactory.initControl(Table, tableName, pageName);
		return control.getBodyText();
	}


	public void clickToCellTableCheckBoxControl(String tableName, String rowIndex="1", String colName, String pageName){
		waitForTableDataLoaded(tableName,pageName);
		Table control = controlFactory.initControl(Table, tableName, pageName);
		control.clickToCellCheckBox(rowIndex, colName);
	}
	public void clickToCellTableCheckBoxWithoutColumnNameControl(String tableName, String rowIndex="1", String pageName){
		waitForTableDataLoaded(tableName,pageName);
		Table control = controlFactory.initControl(Table, tableName, pageName);
		control.clickToCellCheckBoxWithoutColumnName(rowIndex);
	}
	public void clickToHeaderTableCheckBoxControl(String tableName, String pageName){
		waitForTableDataLoaded(tableName,pageName);
		Table control = controlFactory.initControl(Table, tableName, pageName);
		control.clickToHeaderCheckbox();
	}
	public void clickToCellTableSpan(String tableName, String rowIndex="1", String colName, String pageName){
		waitForTableDataLoaded(tableName,pageName);
		Table control = controlFactory.initControl(Table, tableName, pageName);
		control.clickToCellSpan(rowIndex, colName);
	}
	public void clickToCellTableIcon(String tableName, String rowIndex="1", String colName, String pageName){
		waitForTableDataLoaded(tableName,pageName);
		Table control = controlFactory.initControl(Table, tableName, pageName);
		control.clickToCellIcon(rowIndex, colName);
	}
	public void clickToCellTableText(String tableName, String rowIndex="1", String colName, String pageName){
		waitForTableDataLoaded(tableName,pageName);
		Table control = controlFactory.initControl(Table, tableName, pageName);
		control.clickToCellText(rowIndex, colName);
	}
	public void clickToCellTableLinkBaseOnAnotherCellDataTable(String tableName, String conditionColumn, String conditionData, String colNameToClick, String pageName){
		waitForTableDataLoaded(tableName,pageName);
		int rows =Integer.parseInt(getRowTableCounts(tableName,pageName));
		for(int i=1;i<=rows;i++){
			if(getCellTableText(tableName,conditionColumn, i.toString(),pageName).contains(conditionData)){
				clickToCellTableSpan(tableName, i.toString(), colNameToClick,pageName);
				break;
			}
		}
	}
	public void clickToCellTableLinkBaseOnMultipleCellDataTable(String tableName, HashMap<String, String> dataMap,
			String colNameToClick, String pageName){
		waitForTableDataLoaded(tableName,pageName);
		int rows =Integer.parseInt(getRowTableCounts(tableName,pageName));
		for(int i=1;i<=rows;i++){
			boolean status = false;
			for (Map.Entry<Integer, String> entry : dataMap.entrySet()) {
				if(entry.getValue().contains("checked")) {
					status = getCheckBoxCellTableValue(tableName, i.toString(), entry.getKey(), pageName).contains(entry.getValue().trim())
				}else {
					status = getCellTableText(tableName,entry.getKey() , i.toString(),pageName).contains(entry.getValue().trim())
				}
				if(status ==false) {break;}
			}
			if(status) {
				clickToCellTableSpan(tableName, i.toString(),colNameToClick,pageName);
				break;
			}
		}
	}
	public void clickToCellTableTextBaseOnAnotherCellDataTable(String tableName, String conditionColumn, String conditionData, String colNameToClick, String pageName){
		waitForTableDataLoaded(tableName,pageName);
		int rows =Integer.parseInt(getRowTableCounts(tableName,pageName));
		for(int i=1;i<=rows;i++){
			if(getCellTableText(tableName,conditionColumn, i.toString(),pageName).contains(conditionData)){
				clickToCellTableText(tableName, i.toString(), colNameToClick,pageName);
				break;
			}
		}
	}
	public void clickToCellTableTextBaseOnMultipleCellDataTable(String tableName, HashMap<String, String> dataMap,
			String colNameToClick, String pageName){
		waitForTableDataLoaded(tableName,pageName);
		int rows =Integer.parseInt(getRowTableCounts(tableName,pageName));
		for(int i=1;i<=rows;i++){
			boolean status = false;
			for (Map.Entry<Integer, String> entry : dataMap.entrySet()) {
				if(entry.getValue().contains("checked")) {
					status = getCheckBoxCellTableValue(tableName, i.toString(), entry.getKey(), pageName).contains(entry.getValue().trim())
				}else {
					status = getCellTableText(tableName,entry.getKey(), i.toString() ,pageName).contains(entry.getValue().trim())
				}
				if(status ==false) {break;}
			}
			if(status) {
				clickToCellTableText(tableName, i.toString(),colNameToClick,pageName);
				break;
			}
		}
	}
	public void clickToCellTableCheckboxBaseOnAnotherCellDataTable(String tableName, String conditionColumn, String conditionData, String colNameToClick ="",String pageName){
		waitForTableDataLoaded(tableName,pageName);
		int rows =Integer.parseInt(getRowTableCounts(tableName,pageName));
		for(int i=1;i<=rows;i++){
			if(getCellTableText(tableName,conditionColumn, i.toString(),pageName).contains(conditionData)){
				if(Strings.isNullOrEmpty(colNameToClick)) {
					clickToCellTableCheckBoxWithoutColumnNameControl(tableName, i.toString(),pageName);
				}else {
					clickToCellTableCheckBoxControl(tableName, i.toString(), colNameToClick, pageName);
				}
				break;
			}
		}
	}

	public void clickToCellTableCheckboxWithoutFirstRowBaseOnAnotherCellDataTable(String tableName, String conditionColumn, String conditionData, String colNameToClick ="",String pageName){
		waitForTableDataLoaded(tableName,pageName);
		int rows =Integer.parseInt(getRowTableCounts(tableName,pageName));
		for(int i=2;i<=rows;i++){
			if(getCellTableText(tableName,conditionColumn, i.toString(),pageName).contains(conditionData)){
				if(Strings.isNullOrEmpty(colNameToClick)) {
					clickToCellTableCheckBoxWithoutColumnNameControl(tableName, i.toString(),pageName);
				}else {
					clickToCellTableCheckBoxControl(tableName, i.toString(), colNameToClick, pageName);
				}
				break;
			}
		}
	}
	public String getCellTextBaseOnAnotherCellDataTable(String tableName, String conditionColumn, String conditionData, String colNameToGetText, String pageName){
		waitForTableDataLoaded(tableName,pageName);
		int rows =Integer.parseInt(getRowTableCounts(tableName,pageName));
		for(int i=1;i<=rows;i++){
			if(getCellTableText(tableName,conditionColumn, i.toString(),pageName).contains(conditionData)){
				return getCellTableText(tableName,colNameToGetText, i.toString(),pageName);
			}
		}
	}
	public String getCellTextBaseOnAnotherCellValueTable(String tableName, String conditionColumn, String conditionData, String colNameToGetText, String pageName){
		waitForTableDataLoaded(tableName,pageName);
		int rows =Integer.parseInt(getRowTableCounts(tableName,pageName));
		for(int i=1;i<=rows;i++){
			if(getCellTableValue(tableName, i.toString(),conditionColumn,pageName).contains(conditionData)){
				return getCellTableText(tableName,colNameToGetText, i.toString(),pageName);
			}
		}
	}
	public String getCellTextOfFooterBaseOnAnotherCellDataTable(String tableName, String conditionColumn, String conditionData, String colNameToGetText, String pageName){
		waitForTableDataLoaded(tableName,pageName);
		int rows =Integer.parseInt(getRowTableCounts(tableName,pageName));
		for(int i=1;i<=rows;i++){
			if(getCellFooterTableText(tableName, i.toString(),conditionColumn,pageName).contains(conditionData)){
				return getCellFooterTableText(tableName, i.toString(),colNameToGetText,pageName);
			}
		}
	}
	public String getCellTextBaseOnAnotherCellValueAndCellTextTable(String tableName, String conditionColumn1, String conditionData1, String conditionColumn2, String conditionData2, String colNameToGetText, String pageName){
		waitForTableDataLoaded(tableName,pageName);
		int rows =Integer.parseInt(getRowTableCounts(tableName,pageName));
		for(int i=1;i<=rows;i++){
			if(getCellTableValue(tableName, i.toString(),conditionColumn1,pageName).contains(conditionData1) && getCellTableText(tableName, i.toString(),conditionColumn2,pageName).contains(conditionData2)){
				return getCellTableText(tableName,colNameToGetText, i.toString(),pageName);
			}
		}
	}
	public String getCellValueBaseOnAnotherCellDataTable(String tableName, String conditionColumn, String conditionData, String colNameToGetText, String pageName){
		waitForTableDataLoaded(tableName,pageName);
		int rows =Integer.parseInt(getRowTableCounts(tableName,pageName));
		for(int i=1;i<=rows;i++){
			if(getCellTableText(tableName,conditionColumn, i.toString(),pageName).contains(conditionData)){
				return getCellTableValue(tableName, i.toString(),colNameToGetText,pageName);
			}
		}
	}
	public String getCellTextBaseOnMultipleCellDataTable(String tableName, HashMap<String, String> dataMap,
			String colNameToGetText, String pageName){
		waitForTableDataLoaded(tableName,pageName);
		int rows =Integer.parseInt(getRowTableCounts(tableName,pageName));
		for(int i=1;i<=rows;i++){
			boolean status = false;
			for (Map.Entry<Integer, String> entry : dataMap.entrySet()) {
				if(entry.getValue().contains("checked")) {
					status = getCheckBoxCellTableValue(tableName, i.toString(), entry.getKey(), pageName).contains(entry.getValue().trim())
				}else {
					status = getCellTableText(tableName,entry.getKey() , i.toString(),pageName).contains(entry.getValue().trim())
				}
				if(status ==false) {break;}
			}
			if(status) {
				return getCellTableText(tableName,colNameToGetText, i.toString(),pageName);
			}
		}
	}
	public void setTextToCellTableControl(String tableName, String rowIndex="1", String colName, String text, String pageName){
		waitForTableDataLoaded(tableName,pageName);
		Table control = controlFactory.initControl(Table, tableName, pageName);
		control.setCellText(text, rowIndex, colName);
	}
	public void setCellTextBaseOnAnotherCellDataTable(String tableName, String conditionColumn, String conditionData, String text, String colNameToSetText, String pageName){
		waitForTableDataLoaded(tableName,pageName);
		int rows =Integer.parseInt(getRowTableCounts(tableName,pageName));
		for(int i=1;i<=rows;i++){
			if(getCellTableText(tableName,conditionColumn, i.toString(),pageName).contains(conditionData)){
				setTextToCellTableControl(tableName, i.toString(),colNameToSetText,text,pageName);
				break;
			}
		}
	}
	public void selectCellItemComboboxTableControl(String tableName, String rowIndex="1", String colName, String itemName,boolean isClickToOpen = true, String pageName){
		waitForTableDataLoaded(tableName,pageName);
		Table control = controlFactory.initControl(Table, tableName, pageName);
		control.selectItemInCombobox(itemName, rowIndex, colName,isClickToOpen);
	}
	//	public void selectCellItemComboboxBaseOnAnotherCellDataTable(String tableName, String conditionColumn, String conditionData,
	//		String item, String colNameToSelect, String pageName){
	//		waitForTableDataLoaded(tableName,pageName);
	//		int rows =Integer.parseInt(getRowTableCounts(tableName,pageName));
	//
	//		for(int i=1;i<=rows;i++){
	//			if(getCellTableText(tableName,conditionColumn, i.toString(),pageName).contains(conditionData)){
	//				setTextToCellTableControl(tableName, i.toString(),colNameToSelect,item,pageName);
	//				break;
	//			}
	//		}
	//	}
	public void waitForTableDataLoaded(String tableName, int timeoutInSeconds = Timeout.WAIT_TIMEOUT_SHORT_SECONDS, String pageName){
		Table control = controlFactory.initControl(Table,tableName,pageName);
		control.waitForDataLoaded(timeoutInSeconds);
	}

	public boolean isTableHasData(String tableName, int timeoutInSeconds = Timeout.WAIT_TIMEOUT_SHORT_SECONDS, String pageName) {
		Table control = controlFactory.initControl(Table,tableName,pageName);
		return control.isTableHasData(timeoutInSeconds);
	}
	// ===================== TABLE COMMON Table VERIFY STEPS =====================
	public void verifyTableBodyContainData(String controlName, String expText, String pageName, String errMsg="The expected text is not include in the table"){
		String passMsg = String.format("The table >>>%s<<< contains >>>%s<<< data ",controlName,expText)
		String tableBody = getBodyTableText(controlName, pageName).toLowerCase();
		AssertSteps.verifyExpectedResult(tableBody.contains(expText.toLowerCase()), passMsg, errMsg)
	}
	public void verifyCellValueBaseOnAnotherCellDataTable(String tableName, String conditionColumn, String conditionData, String colNameToGetValue, String expectedText, String pageName){
		waitForTableDataLoaded(tableName,pageName);
		int rows =Integer.parseInt(getRowTableCounts(tableName,pageName));
		String result="";
		String msg = String.format("The >>>%s<<< row contains >>>%s<<< value at >>>%s<<< column",conditionData,expectedText,colNameToGetValue)
		for(int i=1;i<=rows;i++){
			if(getCellTableText(tableName,conditionColumn, i.toString(),pageName).contains(conditionData)){
				result=  getCellTableValue(tableName, i.toString(),colNameToGetValue,pageName);
				break;
			}
		}
		AssertSteps.verifyExpectedResult(expectedText.contains(result), msg, msg)
	}
	public void verifyCellTextBaseOnAnotherCellDataTable(String tableName, String conditionColumn, String conditionData, String colNameToGetText, String expectedText, String pageName){
		waitForTableDataLoaded(tableName,pageName);
		int rows =Integer.parseInt(getRowTableCounts(tableName,pageName));
		String result="";
		String msg = String.format("The >>>%s<<< row contains >>>%s<<< text at >>>%s<<< column",conditionData,expectedText,colNameToGetText)
		for(int i=1;i<=rows;i++){
			if(getCellTableText(tableName,conditionColumn, i.toString(),pageName).contains(conditionData)){
				result=  getCellTableText(tableName,colNameToGetText, i.toString(),pageName);
				break;
			}
		}
		AssertSteps.verifyExpectedResult(expectedText.contains(result), msg, msg)
	}
	public void verifyCellValueBaseOn2CellsDataTable(String tableName, String conditionColumn1, String conditionData1, String conditionColumn2, String conditionData2,String colNameToGetValue, String expectedText, String pageName){
		waitForTableDataLoaded(tableName,pageName);
		int rows =Integer.parseInt(getRowTableCounts(tableName,pageName));
		String result="";
		String msg = String.format("The >>>%s<<< row contains >>>%s<<< value",conditionData1,expectedText)
		for(int i=1;i<=rows;i++){
			if(getCellTableText(tableName,conditionColumn1, i.toString(),pageName).contains(conditionData1) && getCellTableText(tableName, i.toString(),conditionColumn2,pageName).contains(conditionData2)){
				result =  getCellTableValue(tableName, i.toString(),colNameToGetValue,pageName);
				break;
			}
		}
		AssertSteps.verifyExpectedResult(expectedText.contains(result), msg, msg)
	}
	public void verifyTableDisplayed(String tableName, String pageName, String errMsg="The table is not displayed") {
		waitForTableDataLoaded(tableName,pageName);
		verifyControlDisplayed(tableName, pageName)
	}
	public void verifyTableBodyNotContainData(String controlName, String expText, String pageName, String errMsg="The expected text is not include in the table"){
		String passMsg = String.format("The table >>>%s<<< not contains >>>%s<<< data ",controlName,expText)
		String tableBody = getBodyTableText(controlName, pageName);
		AssertSteps.verifyExpectedResult(!tableBody.contains(expText), passMsg, errMsg)
	}

	///////////////CMS

	///new

	public String copyTextInControlbyAction(String controlName, String pageName) {
		BaseControl control = controlFactory.initControl(BaseControl, controlName,pageName);
		control.click()
		control.sendControlWithKey("a")
		control.sendControlWithKey("c")

		String data = (String) Toolkit.getDefaultToolkit()
				.getSystemClipboard().getData(DataFlavor.stringFlavor);
		return data;


	}

	public String getSelectedOptionInComboBox(String controlName, boolean isClickToOpen=true,boolean isClickToClose=false,String pageName){
		ComboBox control = controlFactory.initControl(ComboBox, controlName,pageName);
		return control.getSelectedText(isClickToOpen,isClickToClose)
	}
	public void selectItemByTextInComboBoxUsingAction(String controlName, String itemText, boolean isClickToOpen=true,boolean isClickToClose=false,String pageName){
		ComboBox control = controlFactory.initControl(ComboBox, controlName, pageName);
		control.selectByTextUsingAction(itemText,isClickToOpen,isClickToClose);

	}

	public void selectItemByIndexInComboBoxUsingAction(String controlName, int itemIndex, boolean isClickToOpen=true,String pageName){
		ComboBox control = controlFactory.initControl(ComboBox, controlName, pageName);
		control.selectByIndexUsingAction(itemIndex,isClickToOpen);

	}

	public void selectItemByTextInListoBoxUsingAction(String controlName, String itemText,boolean isClickToClose=false, String pageName){
		ListBox control = controlFactory.initControl(ListBox, controlName, pageName);
		control.selectByTextUsingAction(itemText,isClickToClose);

	}



	public void selectCheckboxOptionByText(String controlName, String option, String pageName) {
		CheckBox checkbox = controlFactory.initControl(CheckBox, controlName,pageName);
		checkbox.selectCheckBoxByText(option)
	}

	public int getOptionQuantityInCombobox(String controlName,  boolean isClickToOpen=true,boolean isClickToClose=false, String pageName) {
		ComboBox combobox = controlFactory.initControl(CheckBox, controlName,pageName);
		combobox.getOptionQuantity(isClickToOpen,isClickToClose)
	}

	public void selectRadioOptionByText(String controlName, String option, String pageName) {
		RadioButton radio = controlFactory.initControl(RadioButton, controlName,pageName);
		radio.selectOptionByText(option)
	}

	public void selectItemInThreeDotCombobox(String item) {
		String xpath = String.format(getXpathFromControl("Three_Dots_Combobox", PageLocatorName.COMMONLOCATORS_PAGE),item.trim());
		BaseControl control = new BaseControl(xpath.trim());
		control.click();
	}

	public void waitForTextControlChange(String controlName, int timeOut = Timeout.WAIT_TIMEOUT_SHORT_SECONDS, String pageName){
		BaseControl control = controlFactory.initControl(BaseControl, controlName,pageName);
		control.waitForTextChange(timeOut);
	}

	//Table
	public String getCellTableText(String tableName, String colName, String rowIndex="1",String pageName){
		waitForTableDataLoaded(tableName,pageName);
		Table control = controlFactory.initControl(Table, tableName, pageName);
		return control.getCellText(rowIndex, colName);
	}

	public void clickToCellTableButton(String tableName, String colName, String buttonName="", String rowIndex="1",String pageName){
		waitForTableDataLoaded(tableName,pageName);
		Table control = controlFactory.initControl(Table, tableName, pageName);
		control.clickToCellButton(rowIndex, colName,buttonName);
	}

	public void clickToCellTableButtonBaseOnAnotherCellDataTable(String tableName, String conditionColumn, String conditionData, String colNameToClick,String buttonName="", String pageName){
		waitForTableDataLoaded(tableName,pageName);
		int rows =Integer.parseInt(getRowTableCounts(tableName,pageName));
		for(int i=1;i<=rows;i++){
			if(getCellTableText(tableName,conditionColumn, i.toString(),pageName).contains(conditionData)){
				clickToCellTableButton(tableName, colNameToClick,buttonName, i.toString(),pageName);
				break;
			}
		}
	}

	public void clickToControlByReplacedTextInXpath(String xpath, String text) {
		String locator = String.format(xpath,text.trim())
		BaseControl control = new BaseControl(locator)
		control.click()
	}

	public  boolean isControlByReplacedTextInXpathDisplayed(String xpath, String text) {
		String locator = String.format(xpath,text.trim())
		BaseControl control = new BaseControl(locator)
		control.waitForControlDisplay(Timeout.WAIT_TIMEOUT_SHORT_SECONDS)
		return control.isDisplayed()
	}

	public boolean isControlDisplayed(String controlName, String pageName){
		BaseControl control = controlFactory.initControl(BaseControl, controlName,pageName);
		control.waitForControlDisplay(Timeout.WAIT_TIMEOUT_SHORT_SECONDS)
		return control.isDisplayed()
	}

	public void waitForProgressBarDisappear() {
		waitForControlDoesNotDisplay("Progress_Image",PageLocatorName.COMMONLOCATORS_PAGE);
	}

	public void waitForDataGenerationSpinnerDisappear() {
		waitForControlDoesNotDisplay("Data_Generation_Spinner",PageLocatorName.COMMONLOCATORS_PAGE);
	}

	public void waitForIFrameControlDisplay(String iframeXpath, String controlName, String pageName){
		IFrame control = controlFactory.initControl(IFrame, controlName, pageName);
		control.waitForIFrControlDisplayed(iframeXpath, Timeout.WAIT_TIMEOUT_SHORT_SECONDS);
	}

	public void clickToIFrameControl(String iframeXpath, String controlName, String pageName){
		IFrame control = controlFactory.initControl(IFrame, controlName, pageName);
		control.clickIFrControl(iframeXpath);
	}

	public void clickToIFrameControlByText(String iframeXpath, String text){
		IFrame control = new IFrame()
		control.clickIFrControlbyText(iframeXpath, text)
	}

	public void clickToIFrameControlByReplacedTextInXpath(String iframeXpath,String xpath, String text) {
		String locator = String.format(xpath,text.trim())
		IFrame control = new IFrame(locator)
		control.clickIFrControl(iframeXpath)
	}

	public String getTextFromIFrControl(String iframeXpath, String controlName, String pageName) {
		IFrame control = controlFactory.initControl(IFrame, controlName, pageName);
		return control.getIFrcontrolText(iframeXpath)
	}

	public void clickToOffsetByAction(String controlName, int xOffset, int yOffset=0,String pageName) {
		BaseControl control = controlFactory.initControl(BaseControl, controlName,pageName);
		control.clickToOffsetByAction(xOffset, yOffset)
	}

	public  boolean isIFrameControlByReplacedTextInXpathDisplayed(String iframeXpath, String xpath, String text) {
		String locator = String.format(xpath,text.trim())
		IFrame control = new IFrame(locator)
		return control.isIFrControlDisplayed(iframeXpath)
	}

	public void verifyIFrameControlDisplayed(String iframeXpath, String controlName, String pageName, String errMsg="The control is NOT displayed"){
		IFrame control = controlFactory.initControl(IFrame, controlName, pageName);
		AssertSteps.verifyExpectedResult(control.isIFrControlDisplayed(iframeXpath), errMsg);
	}


	public void verifyMainModalHasContent(String content,String errMsg="The pop up does NOT show correct content") {
		waitForTextControlChange("Modal_Content_Label", PageLocatorName.COMMONLOCATORS_PAGE);
		String modalContent = getTextFromControl("Modal_Content_Label", PageLocatorName.COMMONLOCATORS_PAGE);
		String msg = String.format("The modal content has content as >>>%s<<<",modalContent)
		AssertSteps.verifyExpectedResult(modalContent.contains(content.trim()),msg,msg);
		clickToControl("Modal_Content_X_Button", PageLocatorName.COMMONLOCATORS_PAGE)
	}

	public void verifyPageTitleIsCorrect(String pageTitle) {
		String title = getTextFromControl("Page_Title_Label", PageLocatorName.COMMONLOCATORS_PAGE);
		String msg = String.format("The page title has content as >>>%s<<<",title)
		AssertSteps.verifyExpectedResult(title.contains(pageTitle.trim()),msg,msg);
	}

	public void verifyMainPopUpHasContent(String content) {
		String popUpContent = getTextFromControl("Confirmation_Message_Label", PageLocatorName.COMMONLOCATORS_PAGE);
		String msg = String.format("The pop up content has content as >>>%s<<<",popUpContent)
		AssertSteps.verifyExpectedResult(popUpContent.contains(content.trim()),msg,msg);
		waitForControlDoesNotDisplay("Confirmation_Message_Label", PageLocatorName.COMMONLOCATORS_PAGE);
	}


	public void verifyControlByReplacedTextInXpathNotDisplayed(String xpath, String text) {
		String locator = String.format(xpath,text.trim())
		BaseControl control = new BaseControl(locator)
		AssertSteps.verifyExpectedResult(!control.isDisplayed());
	}

	public void verifyExcelFileContentIsCorrect(String filePath, String sheetIndex="0", String expectedContent){

		String excelContent = ExcelUtil.getContentFromFile(filePath, sheetIndex).trim().replaceAll("[\\t\\n\\r\\s]+"," ");
		expectedContent = expectedContent.trim().replaceAll("[\\t\\n\\r]+"," ")
		String msg = String.format("The expected content is: %s. The excel file content is: %s", expectedContent, excelContent )

		//		if(Strings.isNullOrEmpty(expectedContent)) {
		//			AssertSteps.verifyExpectedResult(xlsContent.equals(expectedContent),passMsg,errMsg);
		//		}else {
		AssertSteps.verifyExpectedResult(excelContent.contains(expectedContent),msg,msg);
		//}
	}

	public String getTextFromControlByReplacedTextInXpath(String xpath, String text) {
		String locator = String.format(xpath,text.trim())
		BaseControl control = new BaseControl(locator)
		return control.getText();
	}

	public int getIFrameElementsSizeByReplacedTextInXpath(String iframeXpath,String xpath, String text) {
		String locator = String.format(xpath,text.trim())
		IFrame control = new IFrame(locator)
		return control.getIFrElementSize(iframeXpath)
	}

	public int getElementsSize(String controlName, String pageName) {
		BaseControl control = controlFactory.initControl(BaseControl, controlName, pageName);
		return control.findElements().size();
	}

	public int getElementSizeByReplacedTextInXpath(String xpath, String text) {
		String locator = String.format(xpath,text.trim())
		BaseControl control = new BaseControl(locator);
		return control.findElements().size();
	}

	public void verifyWordFileContentIsCorrect(String filePath, String expectedContent,String errMsg="The word file does NOT contain the expected data"){
		String passMsg = "The excel file does contain the expected data"
		String docxContent = WordUtil.getContentFromFile(filePath).trim();
		if(Strings.isNullOrEmpty(expectedContent)) {
			AssertSteps.verifyExpectedResult(docxContent.equals(expectedContent),passMsg,errMsg);
		}else {
			AssertSteps.verifyExpectedResult(docxContent.contains(expectedContent),passMsg,errMsg);
		}
	}

	public void verifyControlByReplacedTextInXpathDisplayed(String xpath, String text) {
		String locator = String.format(xpath,text.trim())
		BaseControl control = new BaseControl(locator)
		AssertSteps.verifyExpectedResult(control.isDisplayed());
	}
}