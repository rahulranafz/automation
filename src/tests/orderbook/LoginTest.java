package tests.orderbook;

import org.openqa.selenium.WebDriver;
import java.io.IOException;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import base.Driver;
import pages.orderbook.HomePage;
import pages.orderbook.LoginPage;
import steps.orderbook.LoginSteps;

public class LoginTest extends LoginPage {

	private WebDriver driver = null;
	Driver driverObj = new Driver();
	LoginSteps loginStep = new LoginSteps();
	SoftAssert softAssert = new SoftAssert();
	HomePage homePage = new HomePage();

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
	 * <h1>Login with the valid credentials</h1>
	 * <p>
	 * This test case verify that user is able to login with valid credentials
	 */
	@Test(description = "Login with the valid credentials", priority = 0)
	public void LoginApplication() throws IOException {
		
		driver = startTest();
		loginStep.clickOnLoginLnk(driver);
		loginStep.fillLoginDetails(driver, driverObj.getOrderbookUsername(), driverObj.getMasterPassword());
		loginStep.verifyLogin(driver, softAssert);		
		endTest();
		
	}
	
	/**
	 * <h1>Login with no credentials</h1>
	 * <p>
	 * This test case verify that user is not able to login without entering any credentials.
	 */
	@Test(description = "Login Without Credentials", priority = 1)
	public void LoginWithoutCredentials() throws IOException {
		
		driver = startTest();
		loginStep.clickOnLoginLnk(driver);
		loginStep.fillLoginDetails(driver, "", "");
		loginStep.verifyLoginWithoutCredentials(driver, softAssert, "Required field");		
		endTest();
	}
	
	/**
	 * <h1>Login with Invalid credentials</h1>
	 * <p>
	 * This test case verify that user is not able to login with Invalid credentials
	 */
	@Test(description = "Login With Invalid Credentials", priority = 2)
	public void LoginWithInvalidCredentials() throws IOException {
		
		driver = startTest();
		loginStep.clickOnLoginLnk(driver);
		loginStep.fillLoginDetails(driver, driverObj.getOrderbookUsername(), driverObj.getInvalidPassword());
		loginStep.verifyLoginWithInvalidCredentials(driver, softAssert, "Incorrect username or password");	
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
