package tests.orderbook;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.Driver;
import pages.orderbook.HomePage;
import pages.orderbook.SignUpPage;
import steps.orderbook.HomeSteps;
import steps.orderbook.LoginSteps;
import steps.orderbook.SignUpSteps;
import utilities.MailReader;
import utilities.SearchUrl;
import utilities.UtilityMethods;

public class SignUpTest extends SignUpPage {

	private WebDriver driver = null;
	Driver driverObj = new Driver();
	SignUpPage signuppage = new SignUpPage();
	HomePage homePage = new HomePage();
	LoginSteps loginsteps = new LoginSteps();
	SoftAssert softAssert = new SoftAssert();
	SignUpSteps signUpSteps = new SignUpSteps();
	MailReader mailreader = new MailReader();
	SearchUrl searchurl = new SearchUrl();
	String signupUsername;
	HomeSteps homeSteps = new HomeSteps();

	/**
	 * <h1>Launching the browser and opening the Url</h1>
	 * <p>
	 * This test case making the connection with respective browser and launch the browser and open the url.
	 * 	  
	 * @throws IOException
	 */
	private WebDriver startTest() throws IOException{
		driver = driverObj.createDriver();
		driver.get(driverObj.getOrderbookUrl());
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
		driver = startTest();
		homeSteps.clickOnSignupLnk(driver);
		signupUsername = "gaurav+test" + UtilityMethods.getRandomInteger() + "@ambisafe.co";
		System.out.println(signupUsername);
		signUpSteps.fillSignupDetails(driver, signupUsername, driverObj.getMasterPassword(), driverObj.getPassword(), "Gaurav", "Test" + Integer.toString(UtilityMethods.getRandomInteger()));
		signUpSteps.activateAccountLater(driver);
		homeSteps.verifySignup(driver, softAssert);
		endTest();
	}
	
	/**
	 * <h1>SignUp for Account without Filling all the Mandatory Fields</h1>
	 * <p>
	 * This test case verify that user is not able to Sign Up without entering Mandatory Fields.
	 * 
	 * @throws IOException
	 */
	
	@Test(description = "Signup for an account without filling Mandatory Fields", priority = 1)
	public void SignupWithoutMandatoryFields() throws IOException {
		driver = startTest();
		homeSteps.clickOnSignupLnk(driver);
		signUpSteps.fillSignupDetails(driver, "", "", "", "", "");
		signUpSteps.verifySignupWithoutMandatoryFields(driver, softAssert, "Required field");
		endTest();		
	}
	
	/**
	 * <h1>SignUp for Account with Invalid Email Address</h1>
	 * <p>
	 * This test case verify that user is not able to Sign Up with Invalid  Email Fields.
	 * 
	 * @throws IOException
	 */

	@Test(description = "Signup for an account with Invalid Email", priority = 2)
	public void SignupWithInvalidEmail() throws IOException {
		driver = startTest();
		homeSteps.clickOnSignupLnk(driver);
		signUpSteps.fillSignupDetails(driver, "username", driverObj.getMasterPassword(), driverObj.getMasterPassword(), "Gaurav", "Singh");
		signUpSteps.verifyInvalidEmailId(driver, softAssert, "Invalid email");
		endTest();
	}
	
	/**
	 * <h1>SignUp for Account with Registered Email Address</h1>
	 * <p>
	 * This test case verify that user is not able to Sign Up with Already registered Email Fields.
	 * 
	 * @throws IOException
	 */

	@Test(description = "Signup for an account which is already registered", priority = 3 )
	public void SignupWithRegisteredEmail() throws IOException {
		driver = startTest();
		homeSteps.clickOnSignupLnk(driver);
		signUpSteps.fillSignupDetails(driver, driverObj.getOrderbookUsername(), driverObj.getMasterPassword(), driverObj.getMasterPassword(), "Gaurav", "Singh");
		signUpSteps.verifyInvalidEmailId(driver, softAssert, "Email is already taken");
		endTest();
	}
	
	/**
	 * <h1>SignUp for Account without Accepting the User Agreement</h1>
	 * <p>
	 * This test case verify that user is not able to Sign Up without accepting the User agreement .
	 * 
	 * @throws IOException
	 */
	
	@Test(description = "Signup for an account without accepting the terms and services", priority = 4)
	public void SignupWithoutExceptingTermsServices() throws IOException {
		driver = startTest();
		homeSteps.clickOnSignupLnk(driver);
		signUpSteps.fillDetailsWithoutCheckingUserAgreement(driver, driverObj.getOrderbookUsername(), driverObj.getMasterPassword(), driverObj.getMasterPassword(), "Gaurav", "Singh");
		signUpSteps.verifyUserAgreement(driver, softAssert, "The Terms of Service must be accepted");
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
