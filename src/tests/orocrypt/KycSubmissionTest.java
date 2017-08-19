package tests.orocrypt;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.Driver;
import pages.orocrypt.KycSubmissionPage;
import pages.orocrypt.HomePage;
import pages.orocrypt.LoginPage;
import steps.orocrypt.HomeSteps;
import steps.orocrypt.KycSubmissionSteps;
import steps.orocrypt.LoginSteps;
import utilities.MailReader;

public class KycSubmissionTest extends KycSubmissionPage{
	
	private WebDriver driver = null;
	Driver driverObj = new Driver();
	LoginPage loginPage = new LoginPage();
	HomePage homePage = new HomePage();
	SoftAssert softAssert = new SoftAssert();
	LoginSteps loginSteps = new LoginSteps();
	HomeSteps homeSteps = new HomeSteps();
	MailReader mailReader = new MailReader();
	KycSubmissionSteps kycSubmissionSteps = new KycSubmissionSteps();
	
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
	 * <h1>Uploading Kyc Document</h1>
	 * <p>
	 * This method test that user is able to upload their KYC Documents Successfully. 
	 * </p>
	 * @throws InterruptedException , IOException
	 */
	@Test(description = "Uploading Kyc Document", priority = 0)
	public void uploadingKycDocuments() throws IOException, InterruptedException {
		driver = startTest();
		loginSteps.fillLoginDetails(driver, driverObj.getOrocryptUsername(), driverObj.getPassword());
		loginSteps.verifyUsingActivationCode(driver);
		homeSteps.goToAccounConfirmation(driver);
		kycSubmissionSteps.uploadingKycDocuments(driver);
		kycSubmissionSteps.verifyIdProofUploadedSuccessfully(driver, softAssert);
		kycSubmissionSteps.verifyAdressProofUploadedSuccessfully(driver, softAssert);
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
