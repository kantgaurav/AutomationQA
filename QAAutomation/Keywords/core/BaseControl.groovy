package core
import java.util.concurrent.TimeUnit
import org.openqa.selenium.By
import org.openqa.selenium.ElementClickInterceptedException
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.Keys
import org.openqa.selenium.StaleElementReferenceException
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.ui.ExpectedCondition
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

import com.google.common.base.Strings
import com.google.common.util.concurrent.Uninterruptibles
import com.kms.katalon.core.webui.util.OSUtil
import configs.Timeout
import groovy.inspect.swingui.ObjectBrowser
public class BaseControl extends Browser{
	protected WebElement element;
	protected Actions action;
	protected WebDriverWait wait;
	public String xpath =""
	public String cssSelector="";
	public By by = null;
	public BaseControl(){
		action = new Actions(Browser.getDriverContext());
	}
	public BaseControl(By by){
		this.by = by;
		if(by.toString().toLowerCase().contains("xpath")) {
			this.xpath = by.toString().substring(by.toString().indexOf(':')+1);
		}else {
			this.cssSelector = by.toString().substring(by.toString().indexOf(':')+1);
		}
		action = new Actions(Browser.getDriverContext());
	}
	public BaseControl(String locator){
		if(locator.trim().startsWith("//")) {
			this.xpath = locator;
			this.by = By.xpath(locator);
		}else {
			this.cssSelector = locator;
			this.by = By.cssSelector(cssSelector)
		}
		action = new Actions(Browser.getDriverContext());
	}
	public BaseControl(WebElement element){
		this.element = element;
		action = new Actions(Browser.getDriverContext());
	}
	public void loadControl(){
		if(this.by!=null){
			waitForControlDisplay(Timeout.WAIT_TIMEOUT_MEDIUM_SECONDS);
			this.element = highlightControl(this.by);
		}
	}

	public void loadDOMControl(){
		if(this.by!=null){
			waitForControlPresentOnDOM(Timeout.WAIT_TIMEOUT_MEDIUM_SECONDS);
			this.element = highlightControl(this.by);
		}
	}

	public WebElement highlightControl(By by){
		WebDriver d = Browser.getDriverContext();
		JavascriptExecutor js = (JavascriptExecutor)d;
		WebElement e;
		int attempts = 0;
		while(attempts < 2) {
			try {
				e = d.findElement(by);
				break;
			}catch(StaleElementReferenceException ex) {
				return;
			}
			attempts++;
		}
		Browser.scrollToView(d, e);
		js.executeScript("arguments[0].style.border='2px dashed green'", e);
		return e;
	}
	public setValueByJS(String value){
		loadControl();
		WebDriver driver = Browser.getDriverContext();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("arguments[0].value=arguments[1];", this.element,value);
	}
	// ========================= Wait Control method ZONE  =========================
	public void waitForPageReady(int timeoutInSeconds) {
		WebDriver driver = Browser.getDriverContext();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		this.wait = new WebDriverWait(driver, timeoutInSeconds);
		this.wait.until(js.executeScript("return document.readyState").equals("complete"));
	}
	public void waitForControlPresentOnDOM(int timeoutInSeconds){
		try{
			this.wait = new WebDriverWait(Browser.getDriverContext(), timeoutInSeconds);
			this.wait.until(ExpectedConditions.presenceOfElementLocated(this.by));
		}catch(Throwable e){
			Logger.logINFO(String.format("No element %s presents on DOM: %s", this.by,e.getMessage()));
			return;
		}
	}
	public void waitForControlDisplay(int timeoutInSeconds){
		try{
			this.wait = new WebDriverWait(Browser.getDriverContext(), timeoutInSeconds);
			this.wait.until(ExpectedConditions.visibilityOfElementLocated(this.by));
		}catch(Throwable e){
			Logger.logINFO(String.format("No element %s displayes: %s", this.by,e.getMessage()));
			return;
		}
	}
	public void waitForControlDoesNotDisplay(int timeoutInSeconds){
		try{
			this.wait = new WebDriverWait(Browser.getDriverContext(), timeoutInSeconds);
			this.wait.until(ExpectedConditions.invisibilityOfElementLocated(this.by));
		}catch(Throwable e){
			Logger.logINFO(String.format("The element %s still displayes: %s", this.by,e.getMessage()));
			return;
		}
	}
	public void waitForControlAttributeChange(String attribute, String expectedValue, int timeoutInSeconds){
		try{
			this.wait = new WebDriverWait(Browser.getDriverContext(), timeoutInSeconds);
			this.wait.until(new ExpectedCondition<Boolean>() {
						public Boolean apply(WebDriver driver) {
							WebElement element = driver.findElement(By.xpath("xpath"));
							String actualValue = element.getAttribute(attribute);
							if(actualValue.equals(expectedValue))
								return true;
							else
								return false;
						}
					});
		}catch(Throwable e){
			Logger.logINFO(String.format("The element %s does not change attributes: %s", this.by,e.getMessage()));
			return;
		}
	}

	public void waitForTextChange(int timeoutInSeconds) {
		try {
			String text = getText();
			this.wait = new WebDriverWait(Browser.getDriverContext(), timeoutInSeconds);
			wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElementLocated(this.by, text)));
		}catch(Throwable e){
			Logger.logINFO(String.format("The text element %s does not change text: %s", this.by,e.getMessage()));
			return;
		}
	}
	// ========================= Control action method ZONE  =========================
	public void click(){
		loadControl();
		int attempts = 0;
		while(attempts < 3) {
			try {
				this.element.click();
				break;
			}catch(ElementClickInterceptedException ex) {
				return;
			}catch(StaleElementReferenceException ex) {
				return;
			}
			attempts++;
		}

	}
	
	public void click(int tries){
		loadControl();
		int attempts = 0;
		while(attempts < tries) {
			try {
				this.element.click();
				break;
			}catch(ElementClickInterceptedException ex) {
				return;
			}catch(StaleElementReferenceException ex) {
				return;
			}
			attempts++;
		}

	}
	
	
	
	//*[(normalize-space(text())="CAR Approval")]
	public void clickByText(String text) {
		String locator = String.format("//*[(normalize-space(text())=\"%s\")]",text.trim())
		this.by = By.xpath(locator);
		click();
	}

	public void clickByJS(){
		loadControl();
		((JavascriptExecutor)Browser.getDriverContext()).executeScript('arguments[0].click()', this.element)
	}

	public void clickByAction() {
		loadDOMControl();
		action.moveToElement(this.element).click().build().perform();
	}

	public void clickToOffsetByAction(int xOffset, int yOffset) {
		loadDOMControl();
		action.moveToElement(this.element,xOffset,yOffset).click().build().perform();
	}

	public void sendKeyByAction(CharSequence keys) {
		loadDOMControl();
		action.sendKeys(keys).build().perform();
	}

	public void sendControlWithKey(String key) {

		if(OSUtil.isMac()) {
			action.keyDown(Keys.COMMAND);
			action.sendKeys(key);
			action.keyUp(Keys.COMMAND);
			action.build().perform();
		}else {
			action.keyDown(Keys.CONTROL);
			action.sendKeys(key);
			action.keyUp(Keys.CONTROL);
			action.build().perform();
		}
	}

	public void clearText(){
		loadControl();
		this.element.sendKeys(Keys.END);
		this.element.sendKeys(Keys.SHIFT+Keys.HOME);
		this.element.sendKeys(Keys.DELETE);
		this.element.clear();
	}
	public List<WebElement> findElements(By by = null){
		//loadControl();
		if(by == null) {
			by = this.by
		}
		
		WebDriver d = Browser.getDriverContext();
		int attempts = 0;
		while(attempts < 2) {
			try {
				return d.findElements(by);
				break;
			}catch(Exception e) {
				return;
			}
			attempts++;
		}
	}
	public String getText(){
		loadControl();
		return this.element.getText();
	}
	public String getValue(){
		loadControl();
		JavascriptExecutor js = (JavascriptExecutor) Browser.getDriverContext();
		return (String) js.executeScript("return arguments[0].value;", this.element);
	}

	public String getAttribute(String attributeName){
		loadControl();
		return this.element.getAttribute(attributeName);
	}
	public void openLinkInNewTab() {
		loadControl();
		String selectLinkOpeninNewTab;
		if(OSUtil.isMac()) {
			selectLinkOpeninNewTab = Keys.chord(Keys.COMMAND,Keys.RETURN);
		}else {
			selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN);
		}
		this.element.sendKeys(selectLinkOpeninNewTab);
		Browser.switchToNewOpenedWindow();
	}
	public void sendKeys(CharSequence text){
		click();
		for(char c: text){
			Uninterruptibles.sleepUninterruptibly(Timeout.SEND_KEY_TIMEOUT_MILISECONDS, TimeUnit.MILLISECONDS);
			this.element.sendKeys(""+ c);
		}
	}
	public void submit(){
		loadControl();
		this.element.submit();
	}
	public void uploadFile(String path){
		if(isExisted()){
			loadControl();
			this.element.sendKeys(path);
		}
	}
	// ========================= Check status of control method ZONE  =========================
	public boolean isExisted(){
		try{
			if(by!=null){
				//Browser.setImplicitlyWait(timeOut);
				int elementFound = findElements(this.by).size();
				return elementFound > 0 ? true : false;
			}
			return element != null ? true : false;
		}catch(Throwable e){
			//throw new HMException("The %s control is NOT existed in %s with %s",this.by,Browser.getCurrentURL(),e);
			return false;
		}
		//		finally{
		//			Browser.setImplicitlyWait(timeOut);
		//		}
	}
	public boolean isDisplayed(){
		if(isExisted()){
			try {
				//loadControl();
				this.element = highlightControl(this.by);
				return this.element.displayed;
			}catch (Exception e) {
				return false;
			}
		}else{
			return false;
		}
	}
	public boolean isEnabled(){
		if(isDisplayed()){
			return this.element.enabled;
		}else{
			return false;
		}
	}


}
