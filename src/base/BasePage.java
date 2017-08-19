package base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	/** 
	 * <h1>Wait for element visible </h1>
	 * <p>
	 * This method wait for element visible 
	 * @author Gaurav 
	 * @param driver - Current web driver object
	 * @param elem -   Element locator
	 * @param time - waiting time
	 */
	public void waitForElementVisibility(WebDriver driver, By elem ,int time )	{
		WebDriverWait webDriverWait =  new WebDriverWait(driver, time);
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(elem));
	}
	
	/** 
	 * <h1>Wait for element Invisible by locator </h1>
	 * <p>
	 * This method wait for element Invisible by locator
	 * @author Gaurav 
	 * @param driver - Current web driver object
	 * @param elem -   Element locator
	 * @param time - waiting time
	 */
	public void waitForElementInVisibility(WebDriver driver, By elem ,int time )	{
		WebDriverWait webDriverWait =  new WebDriverWait(driver, time);
		webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(elem));
	}
	
	/** 
	 * <h1>Wait for element Invisible  </h1>
	 * <p>
	 * This method wait for element Invisible
	 * @author Gaurav 
	 * @param driver - Current web driver object
	 * @param elem -   Element locator
	 * @param time - waiting time
	 */
	public void waitForWebElementInVisibility(WebDriver driver, WebElement element ,int time )	{
		WebDriverWait webDriverWait =  new WebDriverWait(driver, time);
		webDriverWait.until(ExpectedConditions.invisibilityOf(element));
	}

	public void waitForElementClickable(WebDriver driver, By elem ,int time )	{
		WebDriverWait webDriverWait =  new WebDriverWait(driver, time);
		webDriverWait.until(ExpectedConditions.elementToBeClickable(elem));
	}


	/** 
	 * <h1>Wait for web element visible </h1>
	 * <p>
	 * This method wait for element visible 
	 * @author Gaurav 
	 * @param driver - Current web driver object
	 * @param elem -   Element locator
	 * @param time - waiting time
	 */
	public void waitForWebElementVisibility(WebDriver driver, WebElement elem ,int time )	{
		WebDriverWait webDriverWait = new WebDriverWait(driver, time);
		webDriverWait.until(ExpectedConditions.visibilityOf(elem));
	}
	/**
	 * <h1> Scroll the page</h1>
	 * <p>
	 * This method scroll to specific control
	 * @author Gaurav
	 * @param driver - Current web driver object
	 * @param elem -   Element locator
	 * @param time waiting time
	 */
	public void scrollToElement(WebDriver driver, By elem,int time) {
		waitForElementVisibility(driver, elem,time);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",driver.findElement(elem));
	}

	/**
	 * <h1> Scroll the page to specific web element</h1>
	 * <p>
	 * This method scroll to specific control
	 * @author Gaurav
	 * @param driver - Current web driver object
	 * @param elem -   Element locator
	 * @param time waiting time
	 * @throws InterruptedException 
	 */
	public  void scrollToWebElement(WebDriver driver, WebElement elem,int time) throws InterruptedException {
		waitForWebElementVisibility(driver,elem,30);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",elem);
	}

	
}
