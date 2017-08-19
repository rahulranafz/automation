package steps.orderbook;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import pages.orderbook.HomePage;
import pages.orderbook.SignUpPage;

public class SignUpSteps extends SignUpPage{
	HomePage homePage = new HomePage();
	
	public void fillSignupDetails(WebDriver driver, String username, String password, String confirmpassword, String firstName, String lastName) {
		enterUserName(driver, username);
		enterPassword(driver, password);
		enterConfirmPassword(driver, confirmpassword);
		enterFirstName(driver, firstName);
		enterLastName(driver, lastName);
		selectCountry(driver,"India");
		checkUserAgreement(driver);
		clickOnSignUpBtn(driver);
	}
	
	public void activateAccountLater(WebDriver driver) {
		//clickOnEmailPopUpGotItBtn(driver);
		clickOnActivateLaterBtn(driver);
		clickOnOkThanksBtn(driver);
	}
	
	public void fillDetailsWithoutCheckingUserAgreement(WebDriver driver, String username, String password, String confirmpassword, String firstName, String lastName) {
		enterUserName(driver, username);
		enterPassword(driver, password);
		enterConfirmPassword(driver, confirmpassword);
		enterFirstName(driver, firstName);
		enterLastName(driver, lastName);
		selectCountry(driver,"India");
		clickOnSignUpBtn(driver);
	}
	
	public void verifySignupWithoutMandatoryFields(WebDriver driver, SoftAssert softAssert, String Msg) {
		softAssert.assertEquals(getValidationMsgOnEmail(driver), Msg, "Validation Message On Email Assertion Fails");
		softAssert.assertEquals(getValidationMsgOnPassword(driver), Msg, "Validation Message On Password Assertion Fails");
		softAssert.assertEquals(getValidationMsgOnConfirmPassword(driver), Msg, "Validation Message On Confirm Password Assertion Fails");
		softAssert.assertEquals(getValidationMsgOnFirstName(driver), Msg, "Validation Message On First Name Assertion Fails");
		softAssert.assertEquals(getValidationMsgOnLastName(driver), Msg, "Validation Message On Last Name Assertion Fails");
	}
	
	public void verifyInvalidEmailId(WebDriver driver, SoftAssert softAssert, String Msg) {
		softAssert.assertEquals(getValidationMsgOnEmail(driver), Msg, "Assertion Failed on Email Field");
	}
	
	public void verifyUserAgreement(WebDriver driver, SoftAssert softAssert, String Msg) {
		softAssert.assertEquals(getValidationMsgOnUserAgreement(driver), Msg, "Assertion Failed on User Agreement Field");
	}
	
}
