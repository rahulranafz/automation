package steps.orderbook;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import pages.orderbook.HomePage;
import pages.orderbook.LoginPage;

public class LoginSteps extends LoginPage{
	HomePage homePage = new HomePage();
	
	public void fillLoginDetails(WebDriver driver, String username,String password) {
		enterUserName(driver, username);
		enterPassword(driver,password);
		clickOnLoginBtn(driver);
			
	}
	
	public void clickOnLoginLnk(WebDriver driver) {
		homePage.clickOnLoginLink(driver);
	}
	
	public void verifyLogin(WebDriver driver, SoftAssert softAssert) {
		homePage.verifylogin(driver, softAssert);
	}
	
	public void verifyLoginWithoutCredentials(WebDriver driver, SoftAssert softAssert, String validationMsg) {
		String validationMsgEmail = getValidationMsgEmail(driver);
		String validationMsgPassword = getValidationMsgPassword(driver);
		softAssert.assertEquals(validationMsgEmail, validationMsg);
		softAssert.assertEquals(validationMsgPassword, validationMsg);
	}
	
	public void verifyLoginWithInvalidCredentials(WebDriver driver, SoftAssert softAssert, String Msg) {
		String validationMsg = getValidationMsgEmail(driver);	
		softAssert.assertEquals(validationMsg, Msg);
	}

}
