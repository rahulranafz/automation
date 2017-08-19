package pages.orocrypt;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class HomePage extends BasePage{

	private By usernameLnkLocator = By.className("title"); 
	private By accountSettingLnkLocator = By.linkText("Account settings");
	private By accountConfirmationLnkLocator = By.linkText("Account confirmation");
	private By logOutLnkLocator = By.linkText("Logout");
	
	public void clickOnUsernameLnk(WebDriver driver) {
		waitForElementVisibility(driver, usernameLnkLocator, 30);
		driver.findElement(usernameLnkLocator).click();
	}
	
	public void clickOnAccountSettingLnk(WebDriver driver) {
		driver.findElement(accountSettingLnkLocator).click();
	}
	
	public void clickOnAccountConfirmationLnk(WebDriver driver) {
		driver.findElement(accountConfirmationLnkLocator).click();
	}
	
	public void clickOnLogOutLnk(WebDriver driver) {
		driver.findElement(logOutLnkLocator).click();
	}
}
