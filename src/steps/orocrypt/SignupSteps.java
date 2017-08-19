package steps.orocrypt;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import pages.orocrypt.SignupPage;
import utilities.MailReader;

public class SignupSteps extends SignupPage{
	
	MailReader mailReader = new MailReader();
	String host = "imap.gmail.com";
	String userName = "ambisafetest@gmail.com";
	String password = "ronnit1029";
	String activationUrlMailSub = "Please verify your email address";
	
	public void fillSignupDetails(WebDriver driver, String userName, String password, String firstName, String lastName) throws InterruptedException {
		enterFirstName(driver, firstName);
		enterLastName(driver, lastName);
		enterEmail(driver, userName);
		enterPassword(driver, password);
		enterRepeatPassword(driver, password);
		checkUserAgreement(driver);
		clickOnCreateAccount(driver);
		Thread.sleep(2000);
		clickOnPasswordSavedOkBtn(driver);
	}
	
	public String fetchingActivationUrl() throws IOException {
		 String activationUrl = mailReader.FetchMails(host, userName, password, activationUrlMailSub);
		 return activationUrl;
	}
	
}
