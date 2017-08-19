package steps.orocrypt;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import pages.orocrypt.KycSubmissionPage;

public class KycSubmissionSteps extends KycSubmissionPage{
	
	public void uploadingKycDocuments(WebDriver driver) throws InterruptedException {
		clickOnUploadButtonForIdProof(driver);
		clickOnUploadButtonForAddressProof(driver);
	}
	
	public void verifyIdProofUploadedSuccessfully(WebDriver driver, SoftAssert softAssert) {
		String idProof = getUploadedDocumentIdProofName(driver);
		System.out.println(idProof);
		softAssert.assertEquals(idProof, "DRIVING_LICEN...");
	}
	
	public void verifyAdressProofUploadedSuccessfully(WebDriver driver, SoftAssert softAssert) {
		String addressProof = getUploadedDocumentAddressProofName(driver);
		System.out.println(addressProof);
		softAssert.assertEquals(addressProof, "ADDRESSPROOF....");
	}
}
