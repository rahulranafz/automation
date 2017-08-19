package tests.orocrypt;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.Driver;
import pages.orocrypt.LoginPage;
import pages.orocrypt.SignupPage;
import steps.orocrypt.LoginSteps;
import steps.orocrypt.SignupSteps;
import utilities.MailReader;
import utilities.SearchUrl;
import utilities.UtilityMethods;

public class SignupTest extends SignupPage {
	private WebDriver driver = null;
	Driver driverObj = new Driver();
	LoginPage loginPage = new LoginPage();
	SoftAssert softAssert = new SoftAssert();
	MailReader mailReader = new MailReader();
	SearchUrl searchUrl = new SearchUrl();
	LoginSteps loginSteps = new LoginSteps();
	SignupSteps signUpSteps = new SignupSteps();

	String signupUsername;
	String firstName = "Gaurav";
	String lastName = "Singh";

	/**
	 * <h1>Launching the browser and opening the Url</h1>
	 * <p>
	 * This test case making the connection with respective browser and launch the browser and open the url.
	 * 	  
	 * @throws IOException
	 */
	private WebDriver startTest() throws IOException{
		driver = driverObj.createDriver();
		driver.get(driverObj.getOrocryptUrl());
		return driver;
	}
	
	/**
	 * <h1>Creating Account on Orocrypt</h1>
	 * <p>
	 * This test case verify that user is able to Sign Up with valid credentials
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */

	@Test(description="Creating Account on Orocrypt", priority = 0)
	public void CreateAccount() throws IOException, InterruptedException {
		driver = startTest();
		loginSteps.clickOnSignupLnk(driver);
		signupUsername = "ambisafetest+copy" + UtilityMethods.getRandomInteger() + "@gmail.com";
		signUpSteps.fillSignupDetails(driver, signupUsername, driverObj.getPassword(), firstName, lastName);
		endTest();
	}

	/**
	 * <h1>Activating the Account by Reading Mails on Registered account</h1>
	 * <p>
	 * This test case verify that user is able to activate the newly created account
	 * by hitting the activation Url.
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@Test(description = "Activating the Account by Reading Mails on Registered account", priority = 1)
	public void activatingNewAccount() throws IOException, InterruptedException {
		String activationUrl = signUpSteps.fetchingActivationUrl();
		driver = driverObj.createDriver();
		driver.get(activationUrl);
		loginSteps.clickOnLoginLnkOnActivationPage(driver);
		loginSteps.fillLoginDetails(driver, signupUsername, driverObj.getPassword());
		loginSteps.verifyUsingActivationCode(driver);
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
		driver = null;
		softAssert.assertAll();
	}
}
