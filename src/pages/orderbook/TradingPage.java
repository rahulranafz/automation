package pages.orderbook;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import base.BasePage;

public class TradingPage extends BasePage{
	
	private By taasEthBtnLocator = By.xpath("//div[@class='MarketSelector__tabs']/div[1]/button");
	private By gnoEthBtnLocator = By.xpath("//div[@class='MarketSelector__tabs']/div[2]/button");
	private By antEthBtnLocator  = By.xpath("//div[@class='MarketSelector__tabs']/div[3]/button");
	private By firstBloodEthBtnLocator = By.xpath("//div[@class='MarketSelector__tabs']/div[4]/button");
	private By minDateFldLocator = By.name("min");
	private By maxDateFldLocator = By.name("max");
	private By aboutTokenBtnLocator = By.xpath("//button[contains(text(),'About Token')]");
	private By icoRatingReportLnkLocator = By.xpath("//div[@class='TokenAbout__button-row']/a[2]");
	private By buyBtnLocator = By.xpath("//div[@class='OrderForm']/div[1]/div[2]/button[1]");
	private By sellBtnLocator = By.xpath("//div[@class='OrderForm']/div[1]/div[2]/button[2]");
	private By submitBtnLocator = By.xpath("//button[contains(text(),'Submit')]");
	private By bidBuyAmountLocator = By.xpath("//div[@class='Orderbook__body']/div[1]/div/div/div[1]/div/div[@class='amount']");
	private By bidBuyPriceLocator = By.xpath("//div[@class='Orderbook__body']/div[1]/div/div/div[1]/div/div[@class='price']");
	private By askSellingPriceLocator = By.xpath("//div[@class='Orderbook__body']/div[2]//div[@class='price']");
	private By askSellingAmountLocator = By.xpath("//div[@class='Orderbook__body']/div[2]/div/div/div[1]/div/div[@class='amount']");
	private By orderTotalLblLocator = By.xpath("//div[@class='OrderForm__total']/div");
	private By orderPriceFldLocator = By.xpath("//div[@class='OrderForm__price']/div/input");
	private By orderAmountFldLocator = By.xpath("//div[@class='OrderForm__amount']/div/input");
	private By validationNotEnoughBalance = By.className("Input2__error ");
	private By sellRowsContainerLocator = By.xpath("//div[@class='Orderbook__body']/div[2]//div[@class='sellRow']");
	private By buyRowsContainerLocator = By.xpath("//div[@class='Orderbook__body']/div[1]//div[@class='buyRow']");
	private By myOrdersListLocator = By.xpath("//div[@class='MyOrders__body']/div/div/div");
	private By myBuyOrdersListLocator = By.xpath("//div[@class='MyOrders__body']//div[@class='buyRow']");
	private By mySellOrdersListLocator = By.xpath("//div[@class='MyOrders__body']//div[@class='sellRow']");
	
	public void clickOnTaasToEthBtn(WebDriver driver) {
		waitForElementVisibility(driver, taasEthBtnLocator, 30);
		driver.findElement(taasEthBtnLocator).click();
	}
	
	public void clickOnGnoToEthBtn(WebDriver driver) {
		waitForElementVisibility(driver, gnoEthBtnLocator, 30);
		driver.findElement(gnoEthBtnLocator).click();
	}
	
	public void clickOnAntToEthBtn(WebDriver driver) {
		waitForElementVisibility(driver, antEthBtnLocator, 30);
		driver.findElement(antEthBtnLocator).click();
	}
	
	public void clickOnFirstBloodToEthBtn(WebDriver driver) {
		waitForElementVisibility(driver, firstBloodEthBtnLocator, 30);
		driver.findElement(firstBloodEthBtnLocator).click();
	}
	
	public void selectFromDate(WebDriver driver) {
		driver.findElement(minDateFldLocator).click();
		driver.findElement(minDateFldLocator).sendKeys("2017-06-26");
	}
	
	public void selectToaDate(WebDriver driver) {
		driver.findElement(maxDateFldLocator).click();
		driver.findElement(maxDateFldLocator).sendKeys("2017-07-06");
	}
	
	public void clickOnAboutTokenBtn(WebDriver driver) {
		driver.findElement(aboutTokenBtnLocator).click();
	}

	public void clickOnIcoRatingReportLnk(WebDriver driver) {
		driver.findElement(icoRatingReportLnkLocator).click();
	}
	
	public void clickOnBuyBtn(WebDriver driver) {
		waitForElementVisibility(driver, buyBtnLocator, 30);
		driver.findElement(buyBtnLocator).click();
	}
	
	public void clickOnSellBtn(WebDriver driver) {
		waitForElementVisibility(driver, sellBtnLocator, 30);
		driver.findElement(sellBtnLocator).click();
	}
	
	public void clickOnSubmitBtn(WebDriver driver) {
		waitForElementVisibility(driver, submitBtnLocator, 30);
		driver.findElement(submitBtnLocator).click();
	}
	
	public String getBidBuyAmount(WebDriver driver) {
		return driver.findElement(bidBuyAmountLocator).getText();
	}
	
	public String getBidBuyPrice(WebDriver driver) {
		return driver.findElement(bidBuyPriceLocator).getText();
	}
	
	public String getAskSellAmount(WebDriver driver) {
		return driver.findElement(askSellingAmountLocator).getText();
	}
	
	public String getAskSellPrice(WebDriver driver) {
		return driver.findElement(askSellingPriceLocator).getText();
	}
	
	public String getOrderTotal(WebDriver driver) {
		waitForElementVisibility(driver, orderTotalLblLocator, 30);
		return driver.findElement(orderTotalLblLocator).getText();
	}
	
	public void verifyOrderTotal(WebDriver driver, SoftAssert softAssert, double expected, double actual) {
		softAssert.assertEquals(expected, actual);
	}
	
	public void enterOrderPrice(WebDriver driver, String orderPrice) {
		waitForElementVisibility(driver, orderPriceFldLocator, 30);
		driver.findElement(orderPriceFldLocator).sendKeys(orderPrice);
	}
	
	public void enterOrderAmount(WebDriver driver, String orderPrice) {
		waitForElementVisibility(driver, orderAmountFldLocator, 30);
		driver.findElement(orderAmountFldLocator).sendKeys(orderPrice);
	}
	
	public String getValidationMsgNotEnoughBalance(WebDriver driver) {
		String validationMsg = driver.findElement(validationNotEnoughBalance).getText();
		return validationMsg;
	}
	
	public void clickOnCancelTxnBtn(WebDriver driver) {
		waitForElementVisibility(driver, myOrdersListLocator, 30);
		int listSize = driver.findElements(myOrdersListLocator).size();
		driver.findElement(By.xpath("//div[@class='MyOrders__body']/div/div/div["+listSize+"]/div[3]/button")).click();		
	}
	
	public int getCountOfBuyRows(WebDriver driver) {
		waitForElementVisibility(driver, buyRowsContainerLocator, 30);
		int count = driver.findElements(buyRowsContainerLocator).size();
		return count;
	}
	
	public int getCountOfSellRows(WebDriver driver) {
		waitForElementVisibility(driver, sellRowsContainerLocator, 30);
		int count = driver.findElements(sellRowsContainerLocator).size();
		return count;
	}
	
	public int getCountMyOrders(WebDriver driver) {
		int count = driver.findElements(myOrdersListLocator).size();
		return count;
	}
	
	public String getBuyOrderSatus(WebDriver driver) {
		int buyRowListSize = driver.findElements(myBuyOrdersListLocator).size();
		String orderStatus = driver.findElement(By.xpath("//div[@class='MyOrders__body']/div/div/div["+buyRowListSize+"]/div[3]/div")).getText();
		return orderStatus;
	}
	
	public String getSellOrderSatus(WebDriver driver) {
		int buyRowListSize = driver.findElements(myBuyOrdersListLocator).size();
		int sellRowListSize = driver.findElements(mySellOrdersListLocator).size() + buyRowListSize;
		String orderStatus = driver.findElement(By.xpath("//div[@class='MyOrders__body']/div/div/div["+sellRowListSize+"]/div[3]/div")).getText();
		return orderStatus;
	}
	
	public void waitForInvisiblityOfOrderRow(WebDriver driver, String pendingMessage) {
		WebElement pendingOrderStatus = driver.findElement(By.xpath("//div[@class='MyOrders__body']//div[contains(text(),'"+pendingMessage+"')]"));
		waitForWebElementVisibility(driver, pendingOrderStatus, 30);
		waitForWebElementInVisibility(driver, pendingOrderStatus, 300);
	}
	
	public void cancelLatestBuyOrder(WebDriver driver) {
		int buyRowListSize = driver.findElements(myBuyOrdersListLocator).size();
		driver.findElement(By.xpath("//div[@class='MyOrders__body']//div[@class='sellRow']["+buyRowListSize+"]//button")).click();
	}
	
	public void cancelLatestSellOrder(WebDriver driver) {
		int sellRowListSize = driver.findElements(mySellOrdersListLocator).size();
		driver.findElement(By.xpath("//div[@class='MyOrders__body']//div[@class='sellRow']["+sellRowListSize+"]//button")).click();
	}
	
	public int getCountBuyOrders(WebDriver driver) {
		int count = driver.findElements(myBuyOrdersListLocator).size();
		return count;
	}
	
	public int getCountSellOrders(WebDriver driver) {
		int count = driver.findElements(mySellOrdersListLocator).size();
		return count;
	}
}
