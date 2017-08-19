package tests.cryptaur;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.Driver;
import pages.cryptaur.SignupPage;
import steps.cryptaur.RegistrationSteps;
import steps.cryptaur.SignupSteps;
import utilities.UtilityMethods;

public class SignupTest extends SignupPage{
	
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
		driver.get(driverObj.getCryptaurRefferalUrl());
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
	public void SignUpApplication() throws IOException {
		for(int i=0; i<100; i++) {
			driver = startTest();
			signupUsername = "ambisafetest+copy" + UtilityMethods.getRandomInteger() + "@gmail.com";
			System.out.println(signupUsername);
			signupSteps.fillSignUpDetails(driver, signupUsername, driverObj.getMasterPassword());
			registrationSteps.acceptingAgreementSteps(driver);
			endTest();
		}
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
