package pages.orderbook;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class LoginPage extends BasePage {
	
	private By usernameFldLocator=By.xpath("//div[@class='Popup']//input[@type='email']");
	private By passwordFldLocator=By.xpath("//input[@type='password']");
	private By loginBtnFldLocator=By.className("LogInForm__loginButton");
	private By validationMsgEmail = By.xpath("//div[@class='LogInForm']/div[1]/div/div");
	private By validationMsgPassword = By.xpath("//div[@class='LogInForm']/div[2]/div/div");
	
	public void enterUserName(WebDriver driver, String username) {
		waitForElementVisibility(driver, usernameFldLocator, 30);
		driver.findElement(usernameFldLocator).sendKeys(username);
	}
	
	public void enterPassword(WebDriver driver, String password) {
		driver.findElement(passwordFldLocator).sendKeys(password);
	}
	
	public void clickOnLoginBtn(WebDriver driver) {
		driver.findElement(loginBtnFldLocator).click();
	}
	
	public String getValidationMsgEmail(WebDriver driver) {
		String validationMsg = driver.findElement(validationMsgEmail).getText();
		return validationMsg;
	}
	
	public String getValidationMsgPassword(WebDriver driver) {
		String validationMsg = driver.findElement(validationMsgPassword).getText();
		return validationMsg;
	}
	
}
