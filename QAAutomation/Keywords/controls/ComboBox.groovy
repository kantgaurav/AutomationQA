package controls

import org.openqa.selenium.By
import org.openqa.selenium.WebElement

import core.BaseControl



public class ComboBox extends BaseControl {
	public ComboBox(){}
	public ComboBox(String xpath){
		super(By.xpath(xpath));
	}
	public ComboBox(By by){
		super(by);
	}
	public ComboBox(WebElement element){
		super(element);
	}

	public void selectByTextUsingAction(String text,boolean isClickToOpen,boolean isClickToClose){
		By byy = this.by;
		if(isClickToOpen){
			clickByAction();
		}
		this.by = By.xpath(String.format("//div[contains(@class,'dropdown')]//*[@role='option' and contains(normalize-space(),'%s')]",text.trim()));
		clickByAction();
		if(isClickToClose) {
			this.by = byy;
			clickByAction();
		}
	}

	public void selectByIndexUsingAction(int index,boolean isClickToOpen){

		if(isClickToOpen){
			clickByAction();
		}
		this.by = By.xpath(String.format("//div[contains(@class,'dropdown')]//*[@role='option'][%s]",index));
		clickByAction();
	}

	public int getOptionQuantity(boolean isClickToOpen,boolean isClickToClose) {
		By byy = this.by;
		if(isClickToOpen){
			clickByAction();
		}
		this.by = By.xpath("//div[contains(@class,'dropdown')]//*[@role='option']");
		List<WebElement> lstElement = findElements(this.by)

		if(isClickToClose) {
			this.by = byy;
			clickByAction();
		}
		return lstElement.size();
	}

	public String getSelectedText(boolean isClickToOpen,boolean isClickToClose){
		By byy = this.by;
		if(isClickToOpen){
			clickByAction();
		}
		this.by = By.xpath("//div[contains(@class,'dropdown')]//*[@role='option' and @aria-selected='true']");
		String selectedText = getText()
		if(isClickToClose) {
			this.by = byy;
			clickByAction();
		}

		return selectedText;
	}

	public boolean isOptionDefault(String optName){
		return getSelectedText(true,true).contains(optName);
	}
}
