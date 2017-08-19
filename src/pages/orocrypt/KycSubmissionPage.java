package pages.orocrypt;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class KycSubmissionPage extends BasePage{

	private By uploadFldForIdProofBtnLocator = By.name("national_id");
	private By uploadFldForAddressProofBtnLocator = By.name("address_confirm");
	private By uploadBtnForIdProofLocator = By.id("id_national_id_visible");
	private By uploadBtnForAddressProofLocator = By.id("id_address_confirm_visible");

	public void clickOnUploadButtonForIdProof(WebDriver driver) throws InterruptedException {
		String path = System.getProperty("user.dir");
		String idImagePath = path + "\\temporary\\Driving_License.jpg";
		driver.findElement(uploadFldForIdProofBtnLocator).sendKeys(idImagePath);
	}

	public void clickOnUploadButtonForAddressProof(WebDriver driver) {
		String path = System.getProperty("user.dir");
		String addressProofImagePath = path + "\\temporary\\AddressProof.jpg";
		driver.findElement(uploadFldForAddressProofBtnLocator).sendKeys(addressProofImagePath);
	}
	
	public String getUploadedDocumentIdProofName(WebDriver driver) {
		waitForElementVisibility(driver, uploadBtnForIdProofLocator, 30);
		String name = driver.findElement(uploadBtnForIdProofLocator).getText();
		return name;
	}
	
	public String getUploadedDocumentAddressProofName(WebDriver driver) {
		waitForElementVisibility(driver, uploadBtnForAddressProofLocator, 30);
		String name = driver.findElement(uploadBtnForAddressProofLocator).getText();
		return name;
	}
}
