package pages.orderbook;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import base.BasePage;
import base.Driver;
import pages.orderbook.LoginPage;


public class SignUpPage extends BasePage{

	private By usernameFldLocator = By.xpath("//div[@class='SignUpForm']/div/div[1]//input");
	private By passwordFldLocator = By.xpath("//div[@class='SignUpForm']/div/div[2]//input");
	private By confirmPasswordFldlocator = By.xpath("//div[@class='SignUpForm']/div/div[3]//input");
	private By userAgreementFldLocator = By.xpath("//input[@type='checkbox']");
	private By signUpBtnLocator = By.className("SignUpForm__signUpButton");
	private By okThanksBtnLocator = By.xpath("//button[contains(text(),'Ok, Thanks!')]");
	private By activateLaterBtnLocator = By.xpath("//span[text()='Activate later']");
	private By firstNameFieldLocator = By.xpath("//div[@class='SignUpForm']/div/div[4]//input");
	private By lastNameFieldLocator = By.xpath("//div[@class='SignUpForm']/div/div[5]//input");
	private By validationMsgOnEmailLocator = By.xpath("//div[@class='SignUpForm']/div/div[1]//div[@class='Input2__error ']");
	private By validationMsgOnPasswordLocator = By.xpath("//div[@class='SignUpForm']/div/div[2]//div[@class='Input2__error ']");
	private By validationMsgOnConfirmPasswordLocator = By.xpath("//div[@class='SignUpForm']/div/div[3]//div[@class='Input2__error ']");
	private By validationMsgOnFirstNameLocator = By.xpath("//div[@class='SignUpForm']/div/div[4]//div[@class='Input2__error ']");
	private By validationMsgOnLastNameLocator = By.xpath("//div[@class='SignUpForm']/div/div[5]//div[@class='Input2__error ']");
	private By validatonMsgOnUserAgreementLocator = By.xpath("//div[@class='SignUpForm']/div/div[7]/div/div[2]");
	private By countryDropdownFieldLocator = By.xpath("//span[@class='Select-arrow']");
	private By countryInputFieldLocator = By.xpath("//div[@class='Select-input']/input");
	
	HomePage homepage = new HomePage();
	LoginPage loginpage = new LoginPage();
	Driver driverObj= new Driver();
	
	public void enterUserName(WebDriver driver, String username) {
		waitForElementVisibility(driver, usernameFldLocator, 30);
		driver.findElement(usernameFldLocator).sendKeys(username);
	}
	
	public void enterPassword(WebDriver driver, String password) {
		waitForElementVisibility(driver, passwordFldLocator, 30);
		driver.findElement(passwordFldLocator).sendKeys(password);
	}
	
	public void enterConfirmPassword(WebDriver driver, String password) {
		waitForElementVisibility(driver, confirmPasswordFldlocator, 30);
		driver.findElement(confirmPasswordFldlocator).sendKeys(password);
	}
	
	public void enterFirstName(WebDriver driver, String firstName) {
		waitForElementVisibility(driver, firstNameFieldLocator, 30);
		driver.findElement(firstNameFieldLocator).sendKeys(firstName);
	}
	
	public void enterLastName(WebDriver driver, String lastName) {
		waitForElementVisibility(driver, lastNameFieldLocator, 30);
		driver.findElement(lastNameFieldLocator).sendKeys(lastName);
	}
	
	public void selectCountry(WebDriver driver, String country) {
		waitForElementVisibility(driver, countryDropdownFieldLocator, 30);
		driver.findElement(countryDropdownFieldLocator).click();
		driver.findElement(countryInputFieldLocator).sendKeys(country);
		driver.findElement(countryInputFieldLocator).sendKeys(Keys.ENTER);
	}
	
	public void checkUserAgreement(WebDriver driver) {
		waitForElementVisibility(driver, userAgreementFldLocator, 30);
		driver.findElement(userAgreementFldLocator).click();
	}
	
	public void clickOnSignUpBtn(WebDriver driver) {
		waitForElementVisibility(driver, signUpBtnLocator, 30);
		driver.findElement(signUpBtnLocator).click();
	}
	
	public void clickOnOkThanksBtn(WebDriver driver) {
		waitForElementVisibility(driver, okThanksBtnLocator, 30);
		driver.findElement(okThanksBtnLocator).click();
	}
	
	public void clickOnActivateLaterBtn(WebDriver driver) {
		waitForElementVisibility(driver, activateLaterBtnLocator, 30);
		driver.findElement(activateLaterBtnLocator).click();
	}
	
	public String getValidationMsgOnEmail(WebDriver driver) {
		waitForElementVisibility(driver, validationMsgOnEmailLocator, 30);
		String validationMsg = driver.findElement(validationMsgOnEmailLocator).getText();
		return validationMsg;
	}
	
	public String getValidationMsgOnPassword(WebDriver driver) {
		waitForElementVisibility(driver, validationMsgOnPasswordLocator, 30);
		String validationMsg = driver.findElement(validationMsgOnPasswordLocator).getText();
		return validationMsg;
	}
	
	public String getValidationMsgOnConfirmPassword(WebDriver driver) {
		waitForElementVisibility(driver, validationMsgOnConfirmPasswordLocator, 30);
		String validationMsg = driver.findElement(validationMsgOnConfirmPasswordLocator).getText();
		return validationMsg;
	}
	
	public String getValidationMsgOnFirstName(WebDriver driver) {
		waitForElementVisibility(driver, validationMsgOnFirstNameLocator, 30);
		String validationMsg = driver.findElement(validationMsgOnFirstNameLocator).getText();
		return validationMsg;
	}
		
	public String getValidationMsgOnLastName(WebDriver driver) {
		waitForElementVisibility(driver, validationMsgOnLastNameLocator, 30);
		String validationMsg = driver.findElement(validationMsgOnLastNameLocator).getText();
		return validationMsg;
	}
	
	public String getValidationMsgOnUserAgreement(WebDriver driver) {
		waitForElementVisibility(driver, validatonMsgOnUserAgreementLocator, 30);
		String validationMsg = driver.findElement(validatonMsgOnUserAgreementLocator).getText();
		return validationMsg;
	}
}
