package steps.cryptaur;

import org.openqa.selenium.WebDriver;

import pages.cryptaur.SignupPage;

public class SignupSteps extends SignupPage{
	
	public void fillSignUpDetails(WebDriver driver, String email, String password) {
		enterFirstName(driver, "Gaurav");
		enterLastName(driver, "Test");
		enterEmail(driver, email);
		enterPassword(driver, password);
		enterRepeatPassword(driver, password);
		clickOnCreateAccountBtn(driver);
	}
}
