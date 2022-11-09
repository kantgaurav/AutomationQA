package controls
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebElement
import core.BaseControl
import core.Browser
public class CheckBox extends BaseControl {
	public CheckBox(){}
	public CheckBox(String locator){
		super(locator);
	}
	public CheckBox(By by){
		super(by);
	}
	public CheckBox(WebElement element){
		super(element);
	}

	public void selectCheckBoxByText(String text) {
		String radElement = String.format("//label[normalize-space()='%s']/preceding-sibling::*//div[contains(@class,'p-checkbox-box')]",text.trim())
		String locator = String.format("%s%s",this.xpath,radElement);
		this.by = By.xpath(locator);
		click();
	}







	//This method only uses for pseudo element, so the locator must be CSS Selector
	public boolean isCheckedByJS() {
		loadControl();
		JavascriptExecutor js = (JavascriptExecutor) Browser.getDriverContext();
		String a = (String) js.executeScript(String.format("return window.getComputedStyle(document.querySelector('%s'), ':before').getPropertyValue('content')",this.cssSelector))
		return a;
	}
	public boolean isChecked(){
		if(isDisplayed()){
			return this.element.selected;
		}else{
			return false;
		}
	}
}