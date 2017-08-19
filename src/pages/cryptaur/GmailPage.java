package pages.cryptaur;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class GmailPage extends BasePage{
	
	private By emailFldLocator = By.id("identifierId");
	private By passwordFldLocator = By.name("password");
	private By nextBtnLocator = By.xpath("//span[text()='Next']");
	private By verifyCryptaurEmailLocator = By.xpath("//span[@name='Cryptaur']/following-sibling::b[1]"); //span[@name='Cryptaur']/following-sibling::b[1]/parent::div
	
	public void enterEmailFld(WebDriver driver, String email) {
		waitForElementVisibility(driver, emailFldLocator, 30);
		driver.findElement(emailFldLocator).sendKeys(email);		
	}
	
	public void enterPasswordFld(WebDriver driver, String password) {
		waitForElementVisibility(driver, passwordFldLocator, 30);
		driver.findElement(passwordFldLocator).sendKeys(password);
	}
	
	public void clickOnNextBtn(WebDriver driver) {
		waitForElementVisibility(driver, nextBtnLocator, 30);
		driver.findElement(nextBtnLocator).click();		
	}
	
	public String getActivationMailCount(WebDriver driver) {
		waitForElementVisibility(driver, verifyCryptaurEmailLocator, 30);
		String mailCount = driver.findElement(verifyCryptaurEmailLocator).getText();
		return mailCount;
	}
}
