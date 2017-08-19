package pages.cryptaur;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class SignupPage extends BasePage{
	
	private By firstNameFldLocator = By.id("id_first_name");
	private By lastNameFldLocator = By.id("id_last_name");
	private By emailFldLocator = By.id("id_email");
	private By passwordFldLocator = By.id("id_password1");
	private By repeatPasswordFldLocator = By.id("id_password2");
	private By createAccountBtnLocator = By.id("create-account-btn");
	
	public void enterFirstName(WebDriver driver, String firstName) {
		driver.findElement(firstNameFldLocator).sendKeys(firstName);
	}
	
	public void enterLastName(WebDriver driver, String lastName) {
		driver.findElement(lastNameFldLocator).sendKeys(lastName);
	}
	
	public void enterEmail(WebDriver driver, String email) {
		driver.findElement(emailFldLocator).sendKeys(email);
	}
	
	public void enterPassword(WebDriver driver, String password) {
		driver.findElement(passwordFldLocator).sendKeys(password);
	}
	
	public void enterRepeatPassword(WebDriver driver, String password) {
		driver.findElement(repeatPasswordFldLocator).sendKeys(password);
	}
	
	public void clickOnCreateAccountBtn(WebDriver driver) {
		driver.findElement(createAccountBtnLocator).click();
	}
	
}
