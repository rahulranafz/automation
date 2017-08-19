package pages.orocrypt;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class LoginPage extends BasePage{
	private By emailFieldLocator = By.id("id_username"); 
	private By passwordFieldLocator = By.id("id_password"); 
	private By logInBtnLocator = By.xpath("//button[text()='Login']"); 
	private By signUpBtnLocator = By.xpath("//a[text()='Sign up']"); 
	private By authorizationCodeFieldLocator = By.id("login_2fa_code"); 
	private By authorizationLoginBtnLocator = By.xpath("//button[contains(text(),'Login')]"); 
	private By errorMessageLblLoctor = By.xpath("//ul[@class='errorlist']/li");
	private By validationMsgCredentialsLblLocator = By.xpath("//ul[@class='errorlist nonfield']/li");
	private By loginLnkLocator = By.className("pointer"); 
	private By loginLnkAfterLogoutLocator = By.xpath("//a[contains(text(),'Login')]");
	
	public void enterEmailField(WebDriver driver, String Email) {
		waitForElementVisibility(driver, emailFieldLocator, 30);
		driver.findElement(emailFieldLocator).clear();		
		driver.findElement(emailFieldLocator).sendKeys(Email);
	}
	
	public void enterPasswordField(WebDriver driver, String Password) {
		waitForElementVisibility(driver, passwordFieldLocator, 30);
		driver.findElement(passwordFieldLocator).clear();	
		driver.findElement(passwordFieldLocator).sendKeys(Password);
	}
	
	public void clickOnLoginBtn(WebDriver driver) {
		waitForElementVisibility(driver, logInBtnLocator, 30);
		driver.findElement(logInBtnLocator).click();
	}
	
	public void clickOnSignupBtn(WebDriver driver) {
		waitForElementVisibility(driver, signUpBtnLocator, 30);
		driver.findElement(signUpBtnLocator).click();
	}
	
	public void enterAuthorizationCode(WebDriver driver, String activationCode) {
		waitForElementVisibility(driver, authorizationCodeFieldLocator, 30);
		driver.findElement(authorizationCodeFieldLocator).sendKeys(activationCode);
	}
	
	public void clickOnAuthorizationLoginBtn(WebDriver driver) {
		waitForElementVisibility(driver, authorizationLoginBtnLocator, 30);
		driver.findElement(authorizationLoginBtnLocator).click();
	}
	
	public void clickOnLoginLnk(WebDriver driver) {
		waitForElementVisibility(driver, loginLnkLocator, 30);
		driver.findElement(loginLnkLocator).click();
	}
	
	public String getValidationMsgLoginWithoutCredentials(WebDriver driver){
		String validationMsg = driver.findElement(errorMessageLblLoctor).getText();
		return validationMsg;
	}
	
	public String getValidationMsgLoginWithWrongCredentials(WebDriver driver) {
		String validationMsg = driver.findElement(validationMsgCredentialsLblLocator).getText();
		return validationMsg;
	}
	
	public void clickOnLoginLnkAfterLogout(WebDriver driver) {
		waitForElementVisibility(driver, loginLnkAfterLogoutLocator, 30);
		driver.findElement(loginLnkAfterLogoutLocator).click();
	}
	
}
