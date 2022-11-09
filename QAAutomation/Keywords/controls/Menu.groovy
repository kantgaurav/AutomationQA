package controls

import org.openqa.selenium.By
import org.openqa.selenium.WebElement

import core.BaseControl



public class Menu extends BaseControl {
	public Menu(){}
	public Menu(String xpath){
		super(By.xpath(xpath));
	}
	public Menu(By by){
		super(by);
	}
	public Menu(WebElement element){
		super(element);
	}

	public void selectMenu(String mainMenu, String subMenu) {

		String xpath=String.format(this.xpath,mainMenu,subMenu);
		this.by= By.xpath(xpath)
		clickByAction();
	}
}
