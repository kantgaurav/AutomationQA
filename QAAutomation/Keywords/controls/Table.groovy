package controls
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

import com.google.common.base.Strings

import configs.Timeout
import core.BaseControl
import core.Browser
import core.HMException
import core.Logger
import utils.Utilities
public class Table extends BaseControl {
	public Table(){}
	public Table(String xpath){
		super(By.xpath(xpath));
	}
	public Table(By by){
		super(by);
	}
	public Table(WebElement element){
		super(element);
	}
	public void clickToCellSpan(String rowIndex, String colName){
		String cellLocator = String.format("%s//tr[%s]/td[%s]/span"
				,xpath,rowIndex.trim(),colName.trim());

		this.by = By.xpath(cellLocator);
		click();
	}

	public void clickToCellButton(String rowIndex, String colName, String buttonName=""){

		String cellLocator=""
		if(Strings.isNullOrEmpty(buttonName)) {
			cellLocator=String.format("%s//tr[%s]/td[%s]//button"
					,xpath,rowIndex.trim(),colName.trim());
		}else {
			cellLocator=String.format("%s//tr[%s]/td[%s]//button[starts-with(normalize-space(),'%s') or contains(@class,'%s')]"
					,xpath,rowIndex.trim(),colName.trim(),buttonName,buttonName);
		}
		this.by = By.xpath(cellLocator);
		click();
	}


	public void clickToCellText(String rowIndex, String colName){
		String cellLocator="";
		if(Utilities.tryToParseStringToInt(colName)) {
			cellLocator = String.format("%s/tbody/tr[%s]/td[%s]"
					,xpath,rowIndex.trim(),colName.trim());
		}else{
			cellLocator = String.format("%s/tbody/tr[%s]/*[count(%s/thead/tr/*[descendant-or-self::node()[starts-with(normalize-space(),'%s')]]/preceding-sibling::*)+1]"
					,xpath,rowIndex.trim(),xpath,colName.trim());
		}
		this.by = By.xpath(cellLocator);
		click();
	}
	public void clickToCellCheckBox(String rowIndex, String colName){
		String cellLocator ="";
		if(Utilities.tryToParseStringToInt(colName)) {
			cellLocator = String.format("%s/tbody/tr[%s]/td[%s]/*"
					,xpath,rowIndex.trim(),colName.trim());
		}else{
			cellLocator = String.format("%s//tr[%s]/td[count(%s/thead//*[starts-with(normalize-space(),'%s')]/preceding-sibling::th)+1]//*[@type='checkbox']"
					,xpath,rowIndex.trim(),xpath,colName.trim());
		}
		this.by = By.xpath(cellLocator);
		clickByJS();
	}
	public void clickToCellCheckBoxWithoutColumnName(String rowIndex){
		String cellLocator = String.format("%s//tr[%s]/td[count(%s/thead//*[./input]/preceding-sibling::*)+1]/*"
				,xpath,rowIndex.trim(),xpath);
		this.by = By.xpath(cellLocator);
		click();
	}
	public void clickToCellIcon(String rowIndex, String colName){
		String cellLocator="";
		if(Utilities.tryToParseStringToInt(colName)) {
			cellLocator = String.format("%s//tr[%s]/td[%s]/input"
					,xpath,rowIndex.trim(),colName.trim());
		}else {
			cellLocator = String.format("%s//tr[%s]/*[count(%s/thead/tr/*[descendant-or-self::node()[starts-with(normalize-space(),'%s')]]/preceding-sibling::*)+1]/input"
					,xpath,rowIndex.trim(),xpath,colName.trim());
		}
		this.by = By.xpath(cellLocator);
		//click();
		clickByJS();//support table link - flaky
	}
	public void clickToHeaderCheckbox() {
		String cellLocator = String.format("%s/thead//th//input[contains(@type,'checkbox')]"
				,xpath);
		this.by = By.xpath(cellLocator);
		click();
	}
	public String getBodyText(){
		String tableLocator = String.format("%s/tbody",xpath);
		this.by = By.xpath(tableLocator);
		List<WebElement> lstElement = findElements(by);
		String text ="";
		if(lstElement.size()==1){
			text = getText().trim().replaceAll("[\\t\\n\\r]+"," ");
		}else{
			for(WebElement e: lstElement){
				text += e.getText();
			}
			text = text.trim().replaceAll("[\\t\\n\\r]+"," ");
		}
		return text;
	}

	public String getRowText(String rowIndex){
		String rowLocator = String.format("%s/tbody/tr[/*][%s]"
				,xpath,rowIndex.trim());
		this.by = By.xpath(rowLocator);
		return getText().trim().replaceAll("[\\t\\n\\r]+"," ");
	}
	public int getRowCounts(){
		String rowLocator = String.format("%s/tbody/tr",xpath);
		return findElements(By.xpath(rowLocator)).size();
	}
	public String getCellText(String rowIndex, String colName){
		String cellLocator=String.format("%s/tbody/tr[%s]/td[%s]"
				,xpath,rowIndex.trim(),colName.trim());

		this.by = By.xpath(cellLocator.trim());
		return getText().trim().replaceAll("[\\t\\n\\r]+"," ");
	}

	public String getCellLinkAttribute(String rowIndex, String colName, String attribute){
		String cellLocator="";
		if(Utilities.tryToParseStringToInt(colName)) {
			cellLocator =String.format("%s/tbody/tr[%s]/td[%s]/a"
					,xpath,rowIndex.trim(),colName.trim());
		}else {
			cellLocator = String.format("%s/tbody/tr[%s]/*[count(%s/thead/tr/*[descendant-or-self::node()[starts-with(normalize-space(),'%s')]]/preceding-sibling::*)+1]"
					,xpath,rowIndex.trim(),xpath,colName.trim());
		}
		this.by = By.xpath(cellLocator);
		return getAttribute(attribute).trim().replaceAll("[\\t\\n\\r]+"," ");
	}
	public String getCellTextFromFooter(String rowIndex, String colName){
		String cellLocator="";
		if(Utilities.tryToParseStringToInt(colName)) {
			cellLocator =String.format("%s/tfoot/tr[%s]/td[%s]"
					,xpath,rowIndex.trim(),colName.trim());
		}else {
			cellLocator = String.format("%s/tfoot/tr[%s]/*[count(%s/thead/tr/*[descendant-or-self::node()[starts-with(normalize-space(),'%s')]]/preceding-sibling::*)+1]"
					,xpath,rowIndex.trim(),xpath,colName.trim());
		}
		this.by = By.xpath(cellLocator);
		return getText().trim().replaceAll("[\\t\\n\\r]+"," ");
	}
	public String getCellValue(String rowIndex, String colName){
		String cellLocator="";
		if(Utilities.tryToParseStringToInt(colName)) {
			cellLocator =String.format("%s/tbody/tr[%s]/td[%s]//input"
					,xpath,rowIndex.trim(),colName.trim());
		}else {
			cellLocator = String.format("%s/tbody/tr[%s]/*[count(%s/thead/tr/*[descendant-or-self::node()[starts-with(normalize-space(),'%s')]]/preceding-sibling::*)+1]//input"
					,xpath,rowIndex.trim(),xpath,colName.trim());
		}
		this.by = By.xpath(cellLocator);
		return getValue().trim().replaceAll("[\\t\\n\\r]+"," ");
	}
	public boolean getCheckBoxCellValue(String rowIndex, String colName){
		String cellLocator = String.format("%s/tbody/tr[%s]/*[count(%s/thead/tr/*[descendant-or-self::node()[starts-with(normalize-space(),'%s')]]/preceding-sibling::*)+1]//input"
				,xpath,rowIndex.trim(),xpath,colName.trim());
		this.by = By.xpath(cellLocator);
		CheckBox control = new CheckBox(this.by);
		return control.isChecked();
	}
	public void setCellText(String text, String rowIndex, String colName){
		String cellLocator = "";
		if(Utilities.tryToParseStringToInt(colName)) {
			cellLocator =String.format("%s//tbody/tr[%s]/td[%s]"
					,xpath,rowIndex.trim(),colName.trim());
		}else {
			cellLocator = String.format("%s/tbody/tr[%s]/td[count(%s/thead//*[starts-with(normalize-space(),'%s')]/preceding-sibling::th)+1]"
					,xpath,rowIndex.trim(),xpath,colName.trim());
		}
		this.by = By.xpath(cellLocator);
		click();
		cellLocator = String.format("%s%s",cellLocator,"//*[(@type='text' and not(contains(@style,'hidden')) or contains(@class,'riTextBox'))]");
		this.by = By.xpath(cellLocator);
		clearText();
		sendKeys(text);
	}
	public void selectItemInCombobox(String text, String rowIndex, String colName, boolean isClickToOpen=true){
		String cellLocator = "";
		if(Utilities.tryToParseStringToInt(colName)) {
			cellLocator =String.format("%s//tbody/tr[%s]/td[%s]"
					,xpath,rowIndex.trim(),colName.trim());
		}else {
			cellLocator = String.format("%s/tbody/tr[%s]/td[count(%s/thead//*[starts-with(normalize-space(),'%s')]/preceding-sibling::th)+1]"
					,xpath,rowIndex.trim(),xpath,colName.trim());
		}
		this.by = By.xpath(cellLocator);
		click();
		cellLocator = String.format("%s%s",cellLocator,"//button[contains(@data-toggle,'dropdown')]");
		this.by = By.xpath(cellLocator);
		ComboBox combobox = new ComboBox(this.by);
		combobox.selectByContainedText(text,isClickToOpen)
	}
	public void waitForDataLoaded(int timeoutInSeconds){
		try{
			loadControl();
			String cellLocator = String.format("%s/tbody/tr/td",xpath);
			this.wait = new WebDriverWait(Browser.getDriverContext(), timeoutInSeconds);
			this.wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(cellLocator),2));//2 is minium cell of table row
		}catch(Throwable e){
			Logger.logDEBUG(String.format("The table with locator : %s has NO data. Error: %s",xpath, e.getMessage()));
			return;
		}
	}
	
	public boolean isTableHasData(int timeoutInSeconds) {
		
		try{
			loadControl();
			String cellLocator = String.format("%s/tbody/tr/td",xpath);
			this.wait = new WebDriverWait(Browser.getDriverContext(), timeoutInSeconds);
			return this.wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(cellLocator),2));//2 is minium cell of table row
		}catch(Throwable e){
			Logger.logDEBUG(String.format("The table with locator : %s has NO data. Error: %s",xpath, e.getMessage()));
			return false;
		}
	}
}