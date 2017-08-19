package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class SeleniumCommanMethods {

	/**
	 * <h1> Switch the browser window</h1>
	 * <p>
	 * This method is switch windows one
	 * @throws InterruptedException 
	 * */
	public void swichToWindow(WebDriver driver,int index) throws InterruptedException{
		Object[] wh=driver.getWindowHandles().toArray();
		driver.switchTo().window(wh[index].toString());
	}
	
	/**
	 * <h1> Switch the parent browser window</h1>
	 * <p>
	 * This method is switch windows one
	 * @throws InterruptedException 
	 * */
	public void swichToParentWindow(WebDriver driver ){ 
		driver.switchTo().defaultContent();
	}
	
	/**
	 * <h1> mouse hover</h1>
	 * <p>
	 * This method is used to mouse hover on any element
	 * @param driver current browser driver
	 * @param elem by type xpath
	 * @throws InterruptedException 
	 * */
	public void mouseHoverToElement(WebDriver driver,WebElement elem) throws InterruptedException{
		Actions action=new Actions(driver);
		action.moveToElement(elem).build().perform();
	}	
}