package tests.orocrypt;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.Driver;
import pages.orocrypt.LoginPage;
import steps.orocrypt.LoginSteps;
import utilities.MailReader;

public class LoginTest extends LoginPage {

	private WebDriver driver = null;
	Driver driverObj = new Driver();
	SoftAssert softAssert = new SoftAssert();
	MailReader mailReader = new MailReader();
	LoginSteps loginSteps = new LoginSteps();
	
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
	 * <h1>Login into application with valid credentials</h1>
	 * <p>
	 * This test case verify that user is able to login by entering
	 * credentials
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@Test(description = "Login into application with valid credentials", priority = 0)
	public void loginIntoApplication() throws IOException, InterruptedException {
		driver=startTest();
		loginSteps.fillLoginDetails(driver, driverObj.getOrocryptUsername(), driverObj.getPassword());
		loginSteps.verifyUsingActivationCode(driver);	
		endTest();
	}

	/**
	 * <h1>Login into application without entering credentials</h1>
	 * <p>
	 * This test case verify that user is not able to login without entering
	 * credentials
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@Test(description = "Login into application without entering credentials", priority = 1)
	public void LoginApplicationWithoutEnteringCredentials() throws IOException, InterruptedException {
		driver = startTest();
		loginSteps.fillLoginDetails(driver, " ", "");
		Thread.sleep(5000);
		loginSteps.verifyLoginWithoutCredentials(driver, softAssert);
		endTest();
	}

	/**
	 * <h1>Login into application By entering wrong credentials</h1>
	 * <p>
	 * This test case verify that user is not able to login with invalid credentials
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@Test(description = "Login into application with entering invalid credentials", priority = 2)
	public void LoginApplicationEnteringWrongCredentials() throws IOException, InterruptedException {
		driver = startTest();
		loginSteps.fillLoginDetails(driver, driverObj.getOrocryptUsername(), driverObj.getInvalidPassword());
		Thread.sleep(5000);
		loginSteps.verifyLoginWithInvalidCredentials(driver, softAssert);
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
