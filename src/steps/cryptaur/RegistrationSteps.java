package steps.cryptaur;

import org.openqa.selenium.WebDriver;

import pages.cryptaur.RegistrationPage;

public class RegistrationSteps extends RegistrationPage{
	
	public void acceptingAgreementSteps(WebDriver driver) {
		checkReadTermsAndConditionsCheckbox(driver);
		checkNotUSCitizenCheckbox(driver);
		checkCertifyingCheckbox(driver);
		clickOnConfirmButton(driver);
	}
}
