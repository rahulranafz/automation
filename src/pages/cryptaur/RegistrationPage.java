package pages.cryptaur;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class RegistrationPage extends BasePage{
	
	private By readTermsAndConditionsCheckboxLocator = By.id("i_agree");
	private By notUSCitizenCheckboxLocator = By.id("not_us");
	private By certifyingCheckboxLocator = By.id("i_understand");
	private By confirmBtnLocator = By.xpath("//button[contains(text(),'Confirm')]");
	
	public void checkReadTermsAndConditionsCheckbox(WebDriver driver) {
		driver.findElement(notUSCitizenCheckboxLocator).click();
	}
	
	public void checkNotUSCitizenCheckbox(WebDriver driver) {
		driver.findElement(readTermsAndConditionsCheckboxLocator).click();
	}
	
	public void checkCertifyingCheckbox(WebDriver driver) {
		driver.findElement(certifyingCheckboxLocator).click();
	}
	
	public void clickOnConfirmButton(WebDriver driver) {
		driver.findElement(confirmBtnLocator).click();
	}
	

}
