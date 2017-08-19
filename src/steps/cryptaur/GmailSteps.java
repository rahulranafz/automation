package steps.cryptaur;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.Driver;
import pages.cryptaur.GmailPage;

public class GmailSteps extends GmailPage{
	

	private WebDriver driver = null;
	Driver driverObj = new Driver();
	SoftAssert softAssert = new SoftAssert();
	SignupSteps signupSteps = new SignupSteps();
	RegistrationSteps registrationSteps = new RegistrationSteps();
	String signupUsername;
	
	/**
	 * <h1>Launching the browser and opening the Url</h1>
	 * <p>
	 * This test case making the connection with respective browser and launch the browser and open the url.
	 * 	  
	 * @throws IOException
	 */
	private WebDriver startTest() throws IOException{
		driver = driverObj.createDriver();
		driver.get("https://www.gmail.com");
		return driver;
	}
	
	/**
	 * <h1>SignUp for Account</h1>
	 * <p>
	 * This test case verify that user is able to Sign Up with valid credentials
	 * 
	 * @throws IOException
	 */

	@Test(description = "SignUp for Account", priority = 0)
	public void ReadingGmail() throws IOException {
			driver = startTest();
			enterEmailFld(driver, "ambisafetest@gmail.com");
			clickOnNextBtn(driver);
			enterPasswordFld(driver, "ronnit1029");
			clickOnNextBtn(driver);
			String mailCount = getActivationMailCount(driver);
			System.out.println("Mail Count :" + mailCount);
			endTest();
	}
	
	/**
	 * <h1>End Test</h1>
	 * <p>
	 * This method close the browser after the execution of all tests and assert all
	 * </p>
	 */
	private void endTest() {
		driver.quit();
		softAssert.assertAll();
	}
	
}
