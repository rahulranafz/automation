package pages.orderbook;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import base.BasePage;

public class HomePage extends BasePage {

	private By loginLnkFldLocator = By.xpath("//a[contains(text(),'Log In')]");
	private By userIconFldLocator = By.xpath("//img[@src='./assets/media/usericon.a9001b35.svg']");
	private By signUpLnkFldLocator = By.xpath("//a[contains(text(),'Sign Up')]");
	private By emailconfirmationBtnLocator = By.className("EmailConfirmationForm__button");
	private By emailconfirmationCloseBtnLocator = By.className("Popup__close");
	private By dashboardLnkLocator = By.xpath("//div[@class='header__menu-group']/a[1]");
	private By tradeLnkLocator = By.xpath("//div[@class='header__menu-group']/a[2]");
	private By balancesLnkLocator = By.xpath("//div[@class='header__menu-group']/a[3]");
	private By settingsLnkLocator = By.xpath("//div[@class='header__menu-group']/a[4]");
	private By logOutBtnLocator = By.className("Menu__logout-text");

	SoftAssert softassert = new SoftAssert();

	public void clickOnLoginLink(WebDriver driver) {
		waitForElementVisibility(driver, loginLnkFldLocator, 30);
		driver.findElement(loginLnkFldLocator).click();
	}

	public void verifylogin(WebDriver driver, SoftAssert softAssert) {
		waitForElementVisibility(driver, userIconFldLocator, 30);
		softAssert.assertTrue(driver.findElement(userIconFldLocator).isDisplayed());
	}

	public void clickOnSignUpLink(WebDriver driver) {
		waitForElementVisibility(driver, signUpLnkFldLocator, 30);
		driver.findElement(signUpLnkFldLocator).click();
	}

	public void clickOnEmailCnfGotItBtn(WebDriver driver) {
		waitForElementVisibility(driver, emailconfirmationBtnLocator, 30);
		driver.findElement(emailconfirmationBtnLocator).click();
	}

	public void clickOnEmailCnfCloseBtn(WebDriver driver) {
		waitForElementVisibility(driver, emailconfirmationCloseBtnLocator, 30);
		driver.findElement(emailconfirmationCloseBtnLocator).click();
	}

	public void clickOnUserIcon(WebDriver driver) {
		waitForElementVisibility(driver, userIconFldLocator, 30);
		driver.findElement(userIconFldLocator).click();
	}
	
	public void clickOnLogoutBtn(WebDriver driver) {
		waitForElementVisibility(driver, logOutBtnLocator, 30);
		driver.findElement(logOutBtnLocator).click();
	}

	public String getTextFromUserIcon(WebDriver driver) {
		waitForElementVisibility(driver, userIconFldLocator, 30);
		driver.findElement(userIconFldLocator).click();
		String text = driver.findElement(logOutBtnLocator).getText();
		return text;
	}

	public void clickOnDashboardLnk(WebDriver driver) throws InterruptedException {
		driver.findElement(dashboardLnkLocator).click();
	}

	public void clickOnTradeLnk(WebDriver driver) throws InterruptedException {
		waitForElementClickable(driver, balancesLnkLocator, 30);
		driver.findElement(tradeLnkLocator).click();
	}

	public void clickOnBalancesLnk(WebDriver driver) throws InterruptedException {
		waitForElementClickable(driver, balancesLnkLocator, 30);
		driver.findElement(balancesLnkLocator).click();
	}

	public void clickOnSettingsLnk(WebDriver driver) throws InterruptedException {
		driver.findElement(settingsLnkLocator).click();
	}
	
	
}
