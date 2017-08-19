package pages.orocrypt;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class AccountSettingPage extends BasePage{

	private By oldPasswordFldLocator = By.name("old_password"); 
	private By newPasswordFldLocator = By.name("password1");
	private By repeatNewPasswordFldLocator = By.name("password2");
	private By changePasswordBtnLocator = By.className("btn-uline");  
	private By errorMessageLblLocator = By.xpath("//ul[@class='errorlist']/li"); 
	
	public void enterOldPassword(WebDriver driver, String oldPassword) {
		waitForElementVisibility(driver, oldPasswordFldLocator, 30);
		driver.findElement(oldPasswordFldLocator).sendKeys(oldPassword);
	}
	
	public void enterNewPassword(WebDriver driver, String newPassword) {
		driver.findElement(newPasswordFldLocator).sendKeys(newPassword);
	}
	
	public void enterRepeatNewPassword(WebDriver driver, String repeatNewPassword) {
		driver.findElement(repeatNewPasswordFldLocator).sendKeys(repeatNewPassword);
	}
	
	public void clickOnChangePassword(WebDriver driver) {
		driver.findElement(changePasswordBtnLocator).click();
	}
	
	public String getValidationMsg(WebDriver driver) {
		String validationMsg = driver.findElement(errorMessageLblLocator).getText();
		return validationMsg;
	}
}
