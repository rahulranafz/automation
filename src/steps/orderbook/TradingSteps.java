package steps.orderbook;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import pages.orderbook.TradingPage;

public class TradingSteps extends TradingPage {
		ArrayList<String> balanceList = new ArrayList<String> ();
		String baseCurrency;
		String counterCurrency;
		String orderPrice;
		String orderAmount;
		String orderTotal;
		String askSellPrice;
		String askSellAmount;
		String bidBuyPrice;
		String bidBuyAmount;
		
		public void moveToAntEthMarket(WebDriver driver) {
			clickOnAntToEthBtn(driver);
		}
		
		public void createOrder(WebDriver driver, Double op, Double oa) {
			orderPrice = Double.toString(op);
			enterOrderPrice(driver, orderPrice);
			orderAmount = Double.toString(oa);
			enterOrderAmount(driver, orderAmount);
		}
		
		//Total (incl. fee 0): 0 ETH
		public String getOrderTotalStep(WebDriver driver)
		{
			String ot = getOrderTotal(driver);
			int i1 = ot.indexOf("fee") + 8;
			int i2 = ot.indexOf("ETH");
			orderTotal = ot.substring(i1, i2);
			System.out.println("Order Total (inc. fee 0):" + orderTotal + "ETH");
			return orderTotal;
		}
		
		public void clickOnSubmitOrder(WebDriver driver) {
			clickOnSubmitBtn(driver);
		}
		
		public void verifyValidationMsgNotEnoughBalance(WebDriver driver, SoftAssert softAssert, String expected) {
			String validationMsg = getValidationMsgNotEnoughBalance(driver);
			softAssert.assertEquals(validationMsg, expected, "Validation Message for Not Enough Balnace not match");
		}
		
		public int countOfBuyRows(WebDriver driver) {
			int buyRows = getCountOfBuyRows(driver);
			System.out.println("No. Buy Rows :" + buyRows);
			return buyRows;
		}
		
		public int countOfSellRows(WebDriver driver) {
			int sellRows = getCountOfSellRows(driver);
			System.out.println("No. Sell Rows :" + sellRows);
			return sellRows;
		}
		
		public void selectBuy(WebDriver driver) {
			clickOnBuyBtn(driver);
		}
		
		public void selectSell(WebDriver driver) {
			clickOnSellBtn(driver);
		}
		
		public void verifyCurrencies(WebDriver driver, SoftAssert softAssert, double actCurrency, double expCurrency, String msg) {
			String actual = String.format("%.8f", actCurrency);
			String expected = String.format("%.8f", expCurrency);
			softAssert.assertEquals(actual, expected, msg);
		}
		
		public void verifyBuyRowOrderStatus(WebDriver driver, SoftAssert softAssert, String expected) {
			softAssert.assertEquals(getBuyOrderSatus(driver), expected);
		}
		
		public void verifySellRowOrderStatus(WebDriver driver, SoftAssert softAssert, String expected) {
			softAssert.assertEquals(getSellOrderSatus(driver), expected);
		}
		
		public void verifyAskSellingAmount(WebDriver driver, SoftAssert softAssert, double actual_asa, double expected_asa) {
			String actAskSellAmount = String.format("%.6f", actual_asa);
			String expAskSellAmount = String.format("%.6f", expected_asa);
			softAssert.assertEquals(actAskSellAmount, expAskSellAmount);
		}
				
		public void cancelTransaction(WebDriver driver) {
			clickOnCancelTxnBtn(driver);
		}
		
		public String askSellPrice(WebDriver driver) {
			return getAskSellPrice(driver);
		}
		
		public String bidBuyPrice(WebDriver driver) {
			return getBidBuyPrice(driver);
		}
		
		public String askSellAmount(WebDriver driver) {
			return getAskSellAmount(driver);
		}
		
		public String bidBuyAmount(WebDriver driver) {
			return getBidBuyAmount(driver);
		}
		
		public void verifyBidBuyAmount(WebDriver driver,SoftAssert softAssert, double actual_bba, double expected_bba) {
			String actBidBuyAmount  = String.format("%.6f", actual_bba);
			String expBidBuyAmount  = String.format("%.6f", expected_bba);
			softAssert.assertEquals(actBidBuyAmount, expBidBuyAmount, "Bid Buy Amount Not Matched");
		}
		
		public void waitForClearingOrder(WebDriver driver, String pendingMessage) {
			waitForInvisiblityOfOrderRow(driver, pendingMessage);
		}
		
		public void cancelBuyOrder(WebDriver driver) {
			cancelLatestBuyOrder(driver);
		}
		
		public void cancelSellOrder(WebDriver driver) {
			cancelLatestSellOrder(driver);
		}
		
		public int buyOrderCount(WebDriver driver) {
			int count = getCountBuyOrders(driver);
			return count;
		}
		
		public int sellOrderCount(WebDriver driver) {
			int count = getCountSellOrders(driver);
			return count;
		}
		
		
}
