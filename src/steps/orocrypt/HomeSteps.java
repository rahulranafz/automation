package steps.orocrypt;

import org.openqa.selenium.WebDriver;

import pages.orocrypt.HomePage;

public class HomeSteps extends HomePage{
	
	public void goToAccounConfirmation(WebDriver driver) {
		clickOnUsernameLnk(driver);
		clickOnAccountConfirmationLnk(driver);
	}
	
	public void goToAccountSettings(WebDriver driver) {
		clickOnUsernameLnk(driver);
		clickOnAccountSettingLnk(driver);
	}
	
	public void logOut(WebDriver driver) {
		clickOnUsernameLnk(driver);
		clickOnLogOutLnk(driver);
	}
}
