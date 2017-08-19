package pages.orocrypt;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class SignupPage extends BasePage{

	private By firstNameFieldLocator = By.id("id_first_name"); 
	private By lastNameFieldLocator = By.id("id_last_name"); 
	private By emailFieldLocator = By.id("id_email"); 
	private By passwordFieldLocator = By.id("id_password1");
	private By confirmPasswordFieldLocator = By.id("id_password2"); 
	private By checkUserAgreementCbLocator = By.id("terms_check"); 
	private By createAccountBtnLocator = By.id("register-btn");
	private By passwordSaveOkBtnLocator = By.xpath("//div[@class='btn-wrapper']/button[2]");
	
	public void enterFirstName(WebDriver driver, String FirstName) {
		waitForElementVisibility(driver, firstNameFieldLocator, 30);
		driver.findElement(firstNameFieldLocator).sendKeys(FirstName);
	}

	public void enterLastName(WebDriver driver, String LastName) {
		waitForElementVisibility(driver, lastNameFieldLocator, 30);
		driver.findElement(lastNameFieldLocator).sendKeys(LastName);
	}

	public void enterEmail(WebDriver driver, String Email) {
		waitForElementVisibility(driver, emailFieldLocator, 30);
		driver.findElement(emailFieldLocator).sendKeys(Email);
	}

	public void enterPassword(WebDriver driver, String Password) {
		waitForElementVisibility(driver, passwordFieldLocator, 30);
		driver.findElement(passwordFieldLocator).sendKeys(Password);
	}

	public void enterRepeatPassword(WebDriver driver, String ConfirmPassword) {
		waitForElementVisibility(driver, confirmPasswordFieldLocator, 30);
		driver.findElement(confirmPasswordFieldLocator).sendKeys(ConfirmPassword);
	}

	public void checkUserAgreement(WebDriver driver) {
		waitForElementVisibility(driver, checkUserAgreementCbLocator, 30);
		driver.findElement(checkUserAgreementCbLocator).click();
	}

	public void clickOnCreateAccount(WebDriver driver) {
		waitForElementVisibility(driver, createAccountBtnLocator, 30);
		driver.findElement(createAccountBtnLocator).click();
	}
	
	public void clickOnPasswordSavedOkBtn(WebDriver driver) {
		waitForElementVisibility(driver, passwordSaveOkBtnLocator, 30);
		driver.findElement(passwordSaveOkBtnLocator).click();
	}
	
}
