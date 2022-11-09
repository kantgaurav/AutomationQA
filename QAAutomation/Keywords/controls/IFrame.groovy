package controls

import org.openqa.selenium.By
import org.openqa.selenium.WebElement

import core.BaseControl
import core.Browser



public class IFrame extends BaseControl {
	public IFrame(){}
	public IFrame(String xpath){
		super(By.xpath(xpath));
	}
	public IFrame(By by){
		super(by);
	}
	public IFrame(WebElement element){
		super(element);
	}
	public int getTotalIFrameQuantity(){
		return Browser.getDriverContext().findElements(By.tagName("iframe"));
	}
	public void clickIFrControl(String iframeXpath){
		WebElement eFrame = Browser.getDriverContext().findElement(By.xpath(iframeXpath));
		Browser.getDriverContext().switchTo().frame(eFrame);
		click();
		Browser.switchToDefaultWindow();
	}
	
	public void clickIFrControlbyText (String iframeXpath, String text){
		WebElement eFrame = Browser.getDriverContext().findElement(By.xpath(iframeXpath));
		Browser.getDriverContext().switchTo().frame(eFrame);
		clickByText(text)
		Browser.switchToDefaultWindow();
	}
	
	public boolean isIFrControlDisplayed(String iframeXpath){
		WebElement eFrame = Browser.getDriverContext().findElement(By.xpath(iframeXpath));
		boolean isDisplay;
		Browser.getDriverContext().switchTo().frame(eFrame);
		isDisplay = isDisplayed();
		Browser.switchToDefaultWindow();
		return isDisplay;
	}
	
	public String getIFrcontrolText(String iframeXpath) {
		WebElement eFrame = Browser.getDriverContext().findElement(By.xpath(iframeXpath));
		Browser.getDriverContext().switchTo().frame(eFrame);
		String result = getText()
		Browser.switchToDefaultWindow();
		return result;
	}	
	
	public int getIFrElementSize(String iframeXpath) {
		int i =0;
		WebElement eFrame = Browser.getDriverContext().findElement(By.xpath(iframeXpath));
		Browser.getDriverContext().switchTo().frame(eFrame);
		i = findElements().size()
		Browser.switchToDefaultWindow();
		return i;
	}
	
	public void waitForIFrControlDisplayed(String iframeXpath, int timeInSeconds){
		WebElement eFrame = Browser.getDriverContext().findElement(By.xpath(iframeXpath));
		Browser.getDriverContext().switchTo().frame(eFrame);
		waitForControlDisplay(timeInSeconds);
		Browser.switchToDefaultWindow();
	}
}
