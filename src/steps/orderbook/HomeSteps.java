package steps.orderbook;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import pages.orderbook.HomePage;

public class HomeSteps extends HomePage{
	
	public void clickOnSignupLnk(WebDriver driver) {
		clickOnSignUpLink(driver);
	}
	
	public void verifySignup(WebDriver driver, SoftAssert softAssert) {
		String text = getTextFromUserIcon(driver);
		softAssert.assertEquals(text, "Log Out", "On clicking user icon no text is displayed");
	}
	
	public void moveToBalanceTab(WebDriver driver) throws InterruptedException {
		Thread.sleep(6000);
		clickOnBalancesLnk(driver);
	}
	
	public void moveToTradeTab(WebDriver driver) throws InterruptedException {
		Thread.sleep(6000);
		clickOnTradeLnk(driver);
	}
	
	public void logoutApplication(WebDriver driver) {
		clickOnUserIcon(driver);
		clickOnLogoutBtn(driver);
	}
}
