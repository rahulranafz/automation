package steps.orocrypt;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import pages.orocrypt.AccountSettingPage;

public class AccountSettingSteps extends AccountSettingPage {
		
	public void changePasswordSteps(WebDriver driver, String oldPassword, String newPassword) throws InterruptedException {	
		enterOldPassword(driver, oldPassword);
		enterNewPassword(driver, newPassword);
		enterRepeatNewPassword(driver, newPassword);
		clickOnChangePassword(driver);
		System.out.println("Password Changed");
		Thread.sleep(5000);
	}
	
	public void verifyErrorMessage(WebDriver driver, SoftAssert softAssert) {
		String validationMsg = getValidationMsg(driver);
		softAssert.assertEquals(validationMsg, "Wrong old password");
	}
}
