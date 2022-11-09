package controls

import org.openqa.selenium.By
import org.openqa.selenium.WebElement

import core.BaseControl



public class ListBox extends BaseControl {
	public ListBox(){}
	public ListBox(String xpath){
		super(By.xpath(xpath));
	}
	public ListBox(By by){
		super(by);
	}
	public ListBox(WebElement element){
		super(element);
	}


	public void selectByTextUsingAction(String text, boolean isClickToClose){
		By byy = this.by;

		this.by = By.xpath(String.format("//div[contains(@class,'listbox')]//*[@role='option' and contains(normalize-space(),'%s')]",text.trim()));
		clickByAction();
		if(isClickToClose) {
			this.by = byy;
			clickByAction();
		}
	}
}