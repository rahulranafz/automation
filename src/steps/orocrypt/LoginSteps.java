package steps.orocrypt;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import pages.orocrypt.LoginPage;
import utilities.MailReader;

public class LoginSteps extends LoginPage{
	
	MailReader mailReader = new MailReader();
	String activationCode;
	String host = "imap.gmail.com";
	String userName = "ambisafetest@gmail.com";
	String password = "ronnit1029";
	String activationCodeMailSub = "Authentication verification";
	
	public void fillLoginDetails(WebDriver driver, String userName, String password) throws InterruptedException {
		enterEmailField(driver, userName);
		enterPasswordField(driver, password);
		clickOnLoginBtn(driver);
	}
	
	public void verifyUsingActivationCode(WebDriver driver) {
		activationCode = mailReader.FetchMails(host, userName, password, activationCodeMailSub);
		enterAuthorizationCode(driver, activationCode);
		clickOnAuthorizationLoginBtn(driver);
		System.out.println("Successfully Logged In");
	}
	
	public void clickOnSignupLnk(WebDriver driver) {
		clickOnSignupBtn(driver);
	}
	
	public void clickOnLoginLnkOnActivationPage(WebDriver driver) {
		clickOnLoginLnk(driver);
	}
	
	public void verifyLoginWithoutCredentials(WebDriver driver, SoftAssert softAssert) {
		String validationMsg = getValidationMsgLoginWithoutCredentials(driver);
		softAssert.assertEquals(validationMsg, "This field is required");
	}
	
	public void verifyLoginWithInvalidCredentials(WebDriver driver, SoftAssert softAssert) {
		String validationMsg = getValidationMsgLoginWithWrongCredentials(driver);
		softAssert.assertEquals(validationMsg, "Please enter a correct username and password. Note that both fields may be case-sensitive.");
	}
	
	public void clickOnLoginLnk(WebDriver driver) {
		clickOnLoginLnkAfterLogout(driver);
	}
}
