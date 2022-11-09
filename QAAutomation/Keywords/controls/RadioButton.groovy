package controls

import org.openqa.selenium.By
import org.openqa.selenium.WebElement

import core.BaseControl



public class RadioButton extends BaseControl {
	public RadioButton(){}
	public RadioButton(String locator){
		super(locator);
	}
	public RadioButton(By by){
		super(by);
	}
	public RadioButton(WebElement element){
		super(element);
	}

	public void selectOptionByText(String text) {

		String radElement = String.format("//label[normalize-space()='%s']/preceding-sibling::*//div[contains(@class,'p-radiobutton-box')]",text.trim())
		String locator = String.format("%s%s",this.xpath,radElement);
		this.by = By.xpath(locator);
		click();
	}
}