package tests.orocrypt;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.Driver;
import pages.orocrypt.AccountSettingPage;
import pages.orocrypt.HomePage;
import pages.orocrypt.LoginPage;
import steps.orocrypt.AccountSettingSteps;
import steps.orocrypt.HomeSteps;
import steps.orocrypt.LoginSteps;
import utilities.MailReader;

public class AccountSettingTest extends AccountSettingPage{
	private WebDriver driver = null;
	Driver driverObj = new Driver();
	LoginPage loginPage = new LoginPage();
	HomePage homePage = new HomePage();
	HomeSteps homeSteps = new HomeSteps();
	SoftAssert softAssert = new SoftAssert();
	LoginSteps loginSteps = new LoginSteps();
	MailReader mailReader = new MailReader();
	AccountSettingSteps accountSettingSteps = new AccountSettingSteps();
	
	String oldPassword = "ronnit1029";
	String newPassword = "ronnit9201";
	
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
	 * <h1>Change Password</h1>
	 * <p>
	 * This method change the old password under Account Settings. 
	 * </p>
	 * @throws InterruptedException , IOException
	 */
	@Test(description="Change Password", priority = 0)
	public void changePassword() throws IOException, InterruptedException {
		driver = startTest();
		loginSteps.fillLoginDetails(driver, "ambisafetest+copy1@gmail.com", oldPassword);
		loginSteps.verifyUsingActivationCode(driver);
		homeSteps.goToAccountSettings(driver);
		accountSettingSteps.changePasswordSteps(driver, oldPassword, newPassword);
		homeSteps.logOut(driver);
		loginSteps.clickOnLoginLnkAfterLogout(driver);
		loginSteps.fillLoginDetails(driver, "ambisafetest+copy1@gmail.com", newPassword);
		loginSteps.verifyUsingActivationCode(driver);
		homeSteps.goToAccountSettings(driver);
		accountSettingSteps.changePasswordSteps(driver, newPassword, oldPassword);
		endTest();
	}
	
	/**
	 * <h1>Changing Password by entering Wrong Old Password</h1>
	 * <p>
	 * This method validates user is not able to change password by entering the wrong old password under Account Settings. 
	 * </p>
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	@Test(description = "Changing Password by entering Wrong Old Password", priority = 1)
	public void changingPasswordByEnteringWrongOldPassword() throws IOException, InterruptedException {
		driver = startTest();
		loginSteps.fillLoginDetails(driver, "ambisafetest+copy1@gmail.com", oldPassword);
		loginSteps.verifyUsingActivationCode(driver);
		homeSteps.goToAccountSettings(driver);
		accountSettingSteps.changePasswordSteps(driver, driverObj.getInvalidPassword(), oldPassword);
		accountSettingSteps.verifyErrorMessage(driver, softAssert);
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
