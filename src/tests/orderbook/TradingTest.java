package tests.orderbook;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.Driver;
import pages.orderbook.HomePage;
import pages.orderbook.TradingPage;
import steps.orderbook.BalancesSteps;
import steps.orderbook.HomeSteps;
import steps.orderbook.LoginSteps;
import steps.orderbook.TradingSteps;
import utilities.UtilityMethods;

public class TradingTest extends TradingPage {
	private WebDriver driver = null;
	Driver driverObj = new Driver();
	SoftAssert softAssert = new SoftAssert();
	HomePage homePage = new HomePage();
	TradingPage tradepage = new TradingPage();
	LoginSteps loginSteps = new LoginSteps();
	BalancesSteps balancesStep = new BalancesSteps();
	TradingSteps tradingSteps = new TradingSteps();
	HomeSteps homeSteps = new HomeSteps();

	ArrayList<String> balanceList = new ArrayList<String>();
	String baseCurrency;
	String counterCurrency;
	String orderPrice;
	String orderAmount;
	String orderTotal;
	String askSellPrice;
	String askSellAmount;
	String bidBuyPrice;
	String bidBuyAmount;
	double doubleBaseCurrency, doubleCounterCurrency, doubleOrderPrice, doubleOrderAmount, doubleOrderTotal,
			doubleAskSellingPrice, doubleAskSellingAmount, doubleBidBuyPrice, doubleBidBuyAmount;

	/**
	 * <h1>Launching the browser and opening the Url</h1>
	 * <p>
	 * This test case making the connection with respective browser and launch the
	 * browser and open the url.
	 * 
	 * @throws IOException
	 */
	
	private WebDriver startTest() throws IOException {
		driver = driverObj.createDriver();
		driver.get(driverObj.getOrderbookUrl());
		return driver;
	}

	/**
	 * <h1>Trading Buying First Scenario</h1>
	 * <p>
	 * This test case verify that all the buying trading scenario's are working fine
	 * when order price is less than the ask sell price.
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */

	@Test(description = "Trading Buying Scenario when Order Price is less than Ask Sell Price", priority = 0)
	public void buyingFirstScenario() throws IOException, InterruptedException {
		driver = startTest();
		loginSteps.clickOnLoginLnk(driver);
		loginSteps.fillLoginDetails(driver, driverObj.getOrderbookUsername(),
				driverObj.getMasterPassword());
		homeSteps.moveToBalanceTab(driver);
		balanceList = balancesStep.getBalances(driver);
		baseCurrency = balanceList.get(2);
		counterCurrency = balanceList.get(4);
		System.out.println("Base Currency :" + baseCurrency);
		System.out.println("Counter Currency :" + counterCurrency);
		doubleCounterCurrency = Double.parseDouble(counterCurrency);
		doubleBaseCurrency = Double.parseDouble(baseCurrency);
		homeSteps.moveToTradeTab(driver);
		tradingSteps.moveToAntEthMarket(driver);
		tradingSteps.selectBuy(driver);

		if (tradingSteps.countOfSellRows(driver) == 0) {
			doubleOrderPrice = 0.001;
			doubleOrderAmount = 0.002;
			tradingSteps.createOrder(driver, doubleOrderPrice, doubleOrderAmount);
			orderTotal = tradingSteps.getOrderTotalStep(driver);
			doubleOrderTotal = Double.parseDouble(orderTotal);
			int countBefore = tradingSteps.getCountMyOrders(driver);
			tradingSteps.clickOnSubmitOrder(driver);
			int countAfter = tradingSteps.getCountMyOrders(driver);
			System.out.println(
					"MyOrders Count Before Submit:" + countBefore + "\n" + "MyOrders Count After Submit:" + countAfter);
			for (int i = 0; i < 100; i++) {
				if (countBefore == countAfter) {
					countAfter = tradingSteps.getCountMyOrders(driver);
				} else
					break;
			}
			// Waiting for changing the status of the order
			tradingSteps.waitForClearingOrder(driver, "Buy, Pending");

			if (doubleOrderTotal < doubleCounterCurrency) {
				
				// Asserting for Order Status
				tradingSteps.verifyBuyRowOrderStatus(driver, softAssert, "Open");								
				homeSteps.moveToBalanceTab(driver);
				balanceList = balancesStep.getBalances(driver);
				counterCurrency = balanceList.get(4);
				baseCurrency = balanceList.get(2);
				double actual_cc = Double.parseDouble(counterCurrency);
				double expected_cc = doubleCounterCurrency - doubleOrderTotal;
				
				// Asserting for Counter Currency
				tradingSteps.verifyCurrencies(driver, softAssert, actual_cc, expected_cc, "Counter Currency Assertion Failed");
				double actual_bc = Double.parseDouble(baseCurrency);
				double expected_bc = doubleBaseCurrency;
				
				// Asserting for Base Currency
				tradingSteps.verifyCurrencies(driver, softAssert, actual_bc, expected_bc, "Base Currency Assertion Failed");
			} else {
				// Assertion for validating pop-up i.e., you have not enough Eth.
				tradingSteps.verifyValidationMsgNotEnoughBalance(driver, softAssert, "Not enough ETH");
			}

		} else {
			
			askSellPrice = tradingSteps.askSellPrice(driver);
			doubleAskSellingPrice = Double.parseDouble(askSellPrice);
			askSellAmount = tradingSteps.askSellAmount(driver);
			doubleAskSellingAmount = Double.parseDouble(askSellAmount);
			doubleOrderPrice = UtilityMethods.round(doubleAskSellingPrice / 10, 6);
			doubleOrderAmount = UtilityMethods.round(doubleAskSellingAmount / 1000, 6);
			tradingSteps.createOrder(driver, doubleOrderPrice, doubleOrderAmount);
			orderTotal = tradingSteps.getOrderTotalStep(driver);
			doubleOrderTotal = Double.parseDouble(orderTotal);
			
			int countBefore = tradingSteps.getCountMyOrders(driver);
			// Submit the order.
			tradingSteps.clickOnSubmitOrder(driver);
			int countAfter = tradingSteps.getCountMyOrders(driver);
			System.out.println(
					"MyOrders Count Before Submit:" + countBefore + "\n" + "MyOrders Count After Submit:" + countAfter);
			for (int i = 0; i < 100; i++) {
				if (countAfter > countBefore)
					break;
				else
					countAfter = tradingSteps.getCountMyOrders(driver);
			}
			// Waiting for changing the status of the order
			tradingSteps.waitForClearingOrder(driver, "Buy, Pending");

			if (doubleOrderTotal < doubleCounterCurrency) {
				
				//Asserting for Order Status
				tradingSteps.verifyBuyRowOrderStatus(driver, softAssert, "Open");
				
				homeSteps.moveToBalanceTab(driver);
				balanceList = balancesStep.getBalances(driver);
				counterCurrency = balanceList.get(4);
				baseCurrency = balanceList.get(2);
				
				double actual_cc = Double.parseDouble(counterCurrency);
				doubleOrderTotal = UtilityMethods.round(doubleOrderTotal, 8);
				double expected_cc = doubleCounterCurrency - doubleOrderTotal;				
				//Asserting for Counter Currency
				tradingSteps.verifyCurrencies(driver, softAssert, actual_cc, expected_cc, "Counter Currency Assertion Failed");
				
				double actual_bc = Double.parseDouble(baseCurrency);
				double expected_bc = doubleBaseCurrency;				
				// Asserting for Base Currency
				tradingSteps.verifyCurrencies(driver, softAssert, actual_bc, expected_bc, "Base Currency Assertion Failed");
				
			} else {
				// Assertion for validating pop-up i.e., you have not enough Eth.
				tradingSteps.verifyValidationMsgNotEnoughBalance(driver, softAssert, "Not enough ETH");
			}

		}
		endTest();
	}

	/**
	 * <h1>Trading Buying Second Scenario</h1>
	 * <p>
	 * This test case verify that all the buying trading scenario's are working fine
	 * when order price is equals to ask sell price. Order Price == Ask Sell Price
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */

	@Test(description = "Trading Buying Scenario when Order Price is equal to Ask Sell Price", priority = 1)
	public void buyingSecondScenario() throws IOException, InterruptedException {
		driver = startTest();
		loginSteps.clickOnLoginLnk(driver);
		loginSteps.fillLoginDetails(driver, driverObj.getOrderbookUsername(),
				driverObj.getMasterPassword());
		homeSteps.moveToBalanceTab(driver);
		balanceList = balancesStep.getBalances(driver);
		baseCurrency = balanceList.get(2);
		counterCurrency = balanceList.get(4);
		System.out.println("Base Currency :" + baseCurrency);
		System.out.println("Counter Currency :" + counterCurrency);
		doubleCounterCurrency = Double.parseDouble(counterCurrency);
		doubleBaseCurrency = Double.parseDouble(baseCurrency);
		homeSteps.moveToTradeTab(driver);
		tradingSteps.moveToAntEthMarket(driver);
		tradingSteps.selectBuy(driver);
		// This block executes only if there is no previous transactions in transaction
		// table.
		if (tradingSteps.countOfSellRows(driver) == 0) {
			
			doubleOrderPrice = 0.001;
			doubleOrderAmount = 0.002;
			// Creating order by entering Order Price and Order Amount manually.
			tradingSteps.createOrder(driver, doubleOrderPrice, doubleOrderAmount);
			orderTotal = tradingSteps.getOrderTotalStep(driver);
			doubleOrderTotal = Double.parseDouble(orderTotal);
			
			int countBefore = tradingSteps.getCountMyOrders(driver);
			tradingSteps.clickOnSubmitOrder(driver);
			int countAfter = tradingSteps.getCountMyOrders(driver);
			System.out.println(
					"MyOrders Count Before Submit:" + countBefore + "\n" + "MyOrders Count After Submit:" + countAfter);
			for (int i = 0; i < 100; i++) {
				if (countBefore == countAfter) {
					countAfter = tradingSteps.getCountMyOrders(driver);
				} else
					break;
			}
			
			// Waiting for changing the status of the order
			tradingSteps.waitForClearingOrder(driver, "Buy, Pending");
			
			// Buying must condition
			if (doubleOrderTotal < doubleCounterCurrency) {
				
				// Asserting Order Status.
				tradingSteps.verifyBuyRowOrderStatus(driver, softAssert, "Open");
				
				homeSteps.moveToBalanceTab(driver);
				balanceList = balancesStep.getBalances(driver);
				counterCurrency = balanceList.get(4);
				baseCurrency = balanceList.get(2);
				double actual_cc = Double.parseDouble(counterCurrency);
				double expected_cc = doubleCounterCurrency - doubleOrderTotal;
				// Asserting Counter Currency
				tradingSteps.verifyCurrencies(driver, softAssert, actual_cc, expected_cc, "Counter Currency Assertion Failed");
				
				double actual_bc = Double.parseDouble(baseCurrency);
				double expected_bc = doubleBaseCurrency;
				// Asserting Base Currency.
				tradingSteps.verifyCurrencies(driver, softAssert, actual_bc, expected_bc, "Base Currency Assertion Failed");
			
			} else {
				// Assertion for validating pop-up i.e., you have not enough Eth.
				tradingSteps.verifyValidationMsgNotEnoughBalance(driver, softAssert, "Not enough ETH");
			}

		} else {
			
			askSellPrice = tradingSteps.askSellPrice(driver);
			doubleAskSellingPrice = Double.parseDouble(askSellPrice);
			askSellAmount = tradingSteps.askSellAmount(driver);
			doubleAskSellingAmount = Double.parseDouble(askSellAmount);
			doubleOrderPrice = doubleAskSellingPrice;
			doubleOrderAmount = UtilityMethods.round(doubleAskSellingAmount / 1000, 6);
			tradingSteps.createOrder(driver, doubleOrderPrice, doubleOrderAmount);
			orderTotal = tradingSteps.getOrderTotalStep(driver);
			doubleOrderTotal = Double.parseDouble(orderTotal);
			
			// Waiting for count increment.
			int countBefore = tradingSteps.getCountMyOrders(driver);
			tradingSteps.clickOnSubmitOrder(driver);
			int countAfter = tradingSteps.getCountMyOrders(driver);
			System.out.println(
					"MyOrders Count Before Submit:" + countBefore + "\n" + "MyOrders Count After Submit:" + countAfter);
			for (int i = 0; i < 150; i++) {
				if (countBefore == countAfter) {
					countAfter = tradingSteps.getCountMyOrders(driver);
				} else
					break;
			}
			// Waiting for changing the status of the order
			tradingSteps.waitForClearingOrder(driver, "Buy, Pending");

			// Buying must condition
			if (doubleOrderTotal < doubleCounterCurrency) {
				
				double actual_asa = Double.parseDouble(tradingSteps.askSellAmount(driver));
				double expected_asa = doubleAskSellingAmount - doubleOrderAmount;
				// Asserting Ask Sell Amount
				tradingSteps.verifyAskSellingAmount(driver, softAssert, actual_asa, expected_asa);
				homeSteps.moveToBalanceTab(driver);
				balanceList = balancesStep.getBalances(driver);
				counterCurrency = balanceList.get(4);
				baseCurrency = balanceList.get(2);
				double actual_cc = Double.parseDouble(counterCurrency);
				doubleOrderTotal = UtilityMethods.round(doubleOrderTotal, 8);
				double expected_cc = doubleCounterCurrency - doubleOrderTotal;
				tradingSteps.verifyCurrencies(driver, softAssert, actual_cc, expected_cc, "Counter Currency Assertion Failed");
				double actual_bc = Double.parseDouble(baseCurrency);
				doubleOrderAmount = UtilityMethods.round(doubleOrderAmount, 8);
				double expected_bc = doubleBaseCurrency + doubleOrderAmount;
				tradingSteps.verifyCurrencies(driver, softAssert, actual_bc, expected_bc, "Base Currency Assertion Failed");
			
			} else {
				// Assertion for validating pop-up i.e., you have not enough Eth.
				tradingSteps.verifyValidationMsgNotEnoughBalance(driver, softAssert, "Not enough ETH");
			}

		}
		endTest();
	}

	/**
	 * <h1>Trading Buying Third Scenario</h1>
	 * <p>
	 * This test case verify that all the buying trading scenario's are working fine
	 * when order price is greater than ask sell price. Order Price > Ask Sell Price
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */

	@Test(description = "Trading Buying Scenario when Order Price is greater than Ask Sell Price", priority = 2)
	public void buyingThirdScenario() throws IOException, InterruptedException {
		
		driver = startTest();
		
		loginSteps.clickOnLoginLnk(driver);
		loginSteps.fillLoginDetails(driver, driverObj.getOrderbookUsername(),
				driverObj.getMasterPassword());
		
		homeSteps.moveToBalanceTab(driver);
		balanceList = balancesStep.getBalances(driver);
		baseCurrency = balanceList.get(2);
		counterCurrency = balanceList.get(4);
		System.out.println("Base Currency :" + baseCurrency);
		System.out.println("Counter Currency :" + counterCurrency);
		doubleCounterCurrency = Double.parseDouble(counterCurrency);
		doubleBaseCurrency = Double.parseDouble(baseCurrency);
		
		homeSteps.moveToTradeTab(driver);
		tradingSteps.moveToAntEthMarket(driver);
		tradingSteps.selectBuy(driver);

		// This block executes only if there is no previous transactions in transaction table
		if (tradingSteps.countOfSellRows(driver) == 0) {
			
			doubleOrderPrice = 0.001;
			doubleOrderAmount = 0.002;
			// Creating order by entering Order Price and Order Amount manually.
			tradingSteps.createOrder(driver, doubleOrderPrice, doubleOrderAmount);
			orderTotal = tradingSteps.getOrderTotalStep(driver);
			doubleOrderTotal = Double.parseDouble(orderTotal);
			
			int countBefore = tradingSteps.getCountMyOrders(driver);
			tradingSteps.clickOnSubmitOrder(driver);
			int countAfter = tradingSteps.getCountMyOrders(driver);
			System.out.println("MyOrders Count Before Submit :" + countBefore + "\n" + "MyOrders Count After Submit :"
					+ countAfter);
			for (int i = 0; i < 100; i++) {
				if (countAfter > countBefore)
					break;
				else
					countAfter = tradingSteps.getCountMyOrders(driver);
			}
			// Waiting for changing the status of the order
			tradingSteps.waitForClearingOrder(driver, "Buy, Pending");

			// Buying must condition
			if (doubleOrderTotal < doubleCounterCurrency) {
				
				// Asserting Order Status
				tradingSteps.verifyBuyRowOrderStatus(driver, softAssert, "Open");
				
				homeSteps.moveToBalanceTab(driver);
				balanceList = balancesStep.getBalances(driver);
				counterCurrency = balanceList.get(4);
				baseCurrency = balanceList.get(2);
				double actual_cc = Double.parseDouble(counterCurrency);
				double expected_cc = doubleCounterCurrency - doubleOrderTotal;
				// Asserting Counter Currency
				tradingSteps.verifyCurrencies(driver, softAssert, actual_cc, expected_cc, "Counter Currency Assertion Failed");
				
				double actual_bc = Double.parseDouble(baseCurrency);
				double expected_bc = doubleBaseCurrency;
				// Asserting Base Currency.
				tradingSteps.verifyCurrencies(driver, softAssert, actual_bc, expected_bc, "Base Currency Assertion Failed");
			
			} else {
				// Assertion for validating pop-up i.e., you have not enough Eth.
				tradingSteps.verifyValidationMsgNotEnoughBalance(driver, softAssert, "Not enough ETH");
			}

		} else {
			
			askSellPrice = tradingSteps.askSellPrice(driver);
			doubleAskSellingPrice = Double.parseDouble(askSellPrice);
			askSellAmount = tradingSteps.askSellAmount(driver);
			doubleAskSellingAmount = Double.parseDouble(askSellAmount);
			doubleOrderPrice = UtilityMethods.round(doubleAskSellingPrice + 0.01, 6);
			doubleOrderAmount = UtilityMethods.round(doubleAskSellingAmount / 1000, 6);
			tradingSteps.createOrder(driver, doubleOrderPrice, doubleOrderAmount);
			orderTotal = tradingSteps.getOrderTotalStep(driver);
			doubleOrderTotal = Double.parseDouble(orderTotal);
			
			int countBefore = tradingSteps.getCountMyOrders(driver);
			// Submit the Order
			tradingSteps.clickOnSubmitOrder(driver);
			int countAfter = tradingSteps.getCountMyOrders(driver);
			System.out.println("MyOrders Count Before Submit: " + countBefore + "\n" + "MyOrders Count After Submit :"
					+ countAfter);
			for (int i = 0; i < 100; i++) {
				if (countAfter > countBefore)
					break;
				else
					countAfter = tradingSteps.getCountMyOrders(driver);
			}
			// Waiting for changing the status of the order
			tradingSteps.waitForClearingOrder(driver, "Buy, Pending");

			// Buying must condition
			if (doubleOrderTotal < doubleCounterCurrency) {
				
				double actual_asa = Double.parseDouble(tradingSteps.askSellAmount(driver));
				double expected_asa = doubleAskSellingAmount - doubleOrderAmount;
				// Asserting Ask Selling Amount
				tradingSteps.verifyAskSellingAmount(driver, softAssert, actual_asa, expected_asa);
				homeSteps.moveToBalanceTab(driver);
				balanceList = balancesStep.getBalances(driver);
				counterCurrency = balanceList.get(4);
				baseCurrency = balanceList.get(2);
				double actual_cc = Double.parseDouble(counterCurrency);
				//mul = UtilityMethods.round((mul), 8);
				double expected_cc = UtilityMethods.round(doubleCounterCurrency - (doubleAskSellingPrice * doubleOrderAmount), 8);
				// Asserting Counter Currency
				tradingSteps.verifyCurrencies(driver, softAssert, actual_cc, expected_cc, "Counter Currency Assertion Failed");
				
				double actual_bc = Double.parseDouble(baseCurrency);
				doubleOrderAmount = UtilityMethods.round(doubleOrderAmount, 8);
				double expected_bc = doubleBaseCurrency + doubleOrderAmount;
				// Asserting for Base Currency.
				tradingSteps.verifyCurrencies(driver, softAssert, actual_bc, expected_bc, "Base Currency Assertion Failed");
			} else {
				// Assertion for validating pop-up i.e., you have not enough Eth.
				tradingSteps.verifyValidationMsgNotEnoughBalance(driver, softAssert, "Not enough ETH");
			}

		}
		endTest();
	}

	 /**
	 * <h1>Trading Selling First Scenario</h1>
	 * <p>
	 * This test case verify that all the Selling trading scenario's are working
	 * fine when order price is less than Bid Buy price. Order Price < Bid Buy Price
	 *
	 * @throws IOException
	 * @throws InterruptedException
	 */

	@Test(description = "Trading Selling Scenario when Order Price is less than Bid Buy Price", priority = 3)
	public void sellingFirstScenario() throws IOException, InterruptedException {
		
		driver = startTest();
	    
		loginSteps.clickOnLoginLnk(driver);
		loginSteps.fillLoginDetails(driver, driverObj.getOrderbookUsername(),
				driverObj.getMasterPassword());
		
		
		homeSteps.moveToBalanceTab(driver);
		balanceList = balancesStep.getBalances(driver);
		baseCurrency = balanceList.get(2);
		counterCurrency = balanceList.get(4);
		System.out.println("Base Currency :" + baseCurrency);
		System.out.println("Counter Currency :" + counterCurrency);
		doubleCounterCurrency = Double.parseDouble(counterCurrency);
		doubleBaseCurrency = Double.parseDouble(baseCurrency);
		
		homeSteps.moveToTradeTab(driver);
		tradingSteps.moveToAntEthMarket(driver);

		if (tradingSteps.countOfBuyRows(driver) == 0) {
			
			doubleOrderPrice = 0.001;
			doubleOrderAmount = 0.002;
			// Making order by entering order price and order amount manually.
			tradingSteps.createOrder(driver, doubleOrderPrice, doubleOrderAmount);
			orderTotal = tradingSteps.getOrderTotalStep(driver);
			doubleOrderTotal = Double.parseDouble(orderTotal);
			
			int countBefore = tradingSteps.getCountMyOrders(driver);
			// Select sell button
			tradingSteps.selectSell(driver);
			tradingSteps.clickOnSubmitOrder(driver);
			int countAfter = tradingSteps.getCountMyOrders(driver);
			System.out.println(
					"MyOrders Count Before Submit:" + countBefore + "\n" + "MyOrders Count After Submit:" + countAfter);
			for (int i = 0; i < 150; i++) {
				if (countAfter > countBefore)
					break;
				else
					countAfter = tradingSteps.getCountMyOrders(driver);
			}
			// Waiting for changing the status of the order
			tradingSteps.waitForClearingOrder(driver, "Sell, Pending");

			if (doubleOrderAmount < doubleBaseCurrency) {
				
				// Asserting for Order Status
				tradingSteps.verifySellRowOrderStatus(driver, softAssert, "Open");
				
				homeSteps.moveToBalanceTab(driver);
				balanceList = balancesStep.getBalances(driver);
				counterCurrency = balanceList.get(4);
				baseCurrency = balanceList.get(2);
				double actual_cc = Double.parseDouble(counterCurrency);
				double expected_cc = doubleCounterCurrency;
				// Asserting for Counter Currency.
				tradingSteps.verifyCurrencies(driver, softAssert, actual_cc, expected_cc, "Counter Currency Assertion Failed");
				
				double actual_bc = Double.parseDouble(baseCurrency);
				doubleOrderAmount = UtilityMethods.round(doubleOrderAmount, 8);
				double expected_bc = doubleBaseCurrency - doubleOrderAmount;
				// Asserting for Base Currency.
				tradingSteps.verifyCurrencies(driver, softAssert, actual_bc, expected_bc, "Base Currency Assertion Failed");
			
			} else {
				// Assertion for validating pop-up i.e., you have not enough Eth.
				tradingSteps.verifyValidationMsgNotEnoughBalance(driver, softAssert, "Not enough GNO");
			}

		} else {
			
			bidBuyPrice = tradingSteps.bidBuyPrice(driver);
			doubleBidBuyPrice = Double.parseDouble(bidBuyPrice);
			bidBuyAmount = tradingSteps.bidBuyAmount(driver);
			doubleBidBuyAmount = Double.parseDouble(bidBuyAmount);
			doubleOrderPrice = UtilityMethods.round(doubleBidBuyPrice / 100, 6);
			doubleOrderAmount = UtilityMethods.round(doubleBidBuyAmount / 100, 6);
			tradingSteps.createOrder(driver, doubleOrderPrice, doubleOrderAmount);
			orderTotal = tradingSteps.getOrderTotalStep(driver);
			doubleOrderTotal = Double.parseDouble(orderTotal);
			
			int countBefore = tradingSteps.getCountMyOrders(driver);
			// Select sell button
			tradingSteps.selectSell(driver);
			tradingSteps.clickOnSubmitOrder(driver);
			int countAfter = tradingSteps.getCountMyOrders(driver);
			System.out.println(
					"MyOrders Count Before Submit:" + countBefore + "\n" + "MyOrders Count After Submit:" + countAfter);
			for (int i = 0; i < 150; i++) {
				if (countAfter > countBefore)
					break;
				else
					countAfter = tradingSteps.getCountMyOrders(driver);
			}
			// Waiting for changing the status of the order
			tradingSteps.waitForClearingOrder(driver, "Sell, Pending");

			if (doubleOrderAmount < doubleBaseCurrency) {
				
				double actual_bba = Double.parseDouble(tradingSteps.bidBuyAmount(driver));
				double expected_bba = doubleBidBuyAmount - doubleOrderAmount;
				tradingSteps.verifyBidBuyAmount(driver, softAssert, actual_bba, expected_bba);
				
				homeSteps.moveToBalanceTab(driver);
				balanceList = balancesStep.getBalances(driver);
				counterCurrency = balanceList.get(4);
				baseCurrency = balanceList.get(2);
				double actual_cc = Double.parseDouble(counterCurrency);
				doubleOrderTotal = UtilityMethods.round(doubleOrderTotal, 8);
				double expected_cc = doubleCounterCurrency + doubleOrderTotal;
				// Asserting for Counter Currency.
				tradingSteps.verifyCurrencies(driver, softAssert, actual_cc, expected_cc, "Counter Currency Assertion Failed");
				
				double actual_bc = Double.parseDouble(baseCurrency);
				doubleOrderAmount = UtilityMethods.round(doubleOrderAmount, 8);
				double expected_bc = doubleBaseCurrency - doubleOrderAmount;
				// Asserting for Base Currency
				tradingSteps.verifyCurrencies(driver, softAssert, actual_bc, expected_bc, "Base Currency Assertion Failed");
			
			} else {
				// Assertion for validating pop-up i.e., you have not enough Eth.
				tradingSteps.verifyValidationMsgNotEnoughBalance(driver, softAssert, "Not enough GNO");
			}
		}
	}

	 /**
	 * <h1>Trading Selling Second Scenario</h1>
	 * <p>
	 * This test case verify that all the selling trading scenario's are working
	 * fine when order price is equals to bid buy price. Order Price == Bid Buy
	 * Price
	 *
	 * @throws IOException
	 * @throws InterruptedException
	 */

	@Test(description = "Trading Selling Scenario when Order Price is equals to Bid Buy Price", priority = 0)
	public void sellingSecondScenario() throws IOException, InterruptedException {
		
		driver = startTest();
	    
		loginSteps.clickOnLoginLnk(driver);
		loginSteps.fillLoginDetails(driver, driverObj.getOrderbookUsername(),
				driverObj.getMasterPassword());
		
		homeSteps.moveToBalanceTab(driver);
		balanceList = balancesStep.getBalances(driver);
		baseCurrency = balanceList.get(2);
		counterCurrency = balanceList.get(4);
		System.out.println("Base Currency :" + baseCurrency);
		System.out.println("Counter Currency :" + counterCurrency);
		doubleCounterCurrency = Double.parseDouble(counterCurrency);
		doubleBaseCurrency = Double.parseDouble(baseCurrency);
		
		homeSteps.moveToTradeTab(driver);
		tradingSteps.moveToAntEthMarket(driver);
		
		if (tradingSteps.countOfBuyRows(driver) == 0) {
			
			doubleOrderPrice = 0.001; 
			doubleOrderAmount = 0.002; 
			// Creating order by manually entering Order Price, Order Amount
			tradingSteps.createOrder(driver, doubleOrderPrice, doubleOrderAmount);
			orderTotal = tradingSteps.getOrderTotalStep(driver);
			doubleOrderTotal = Double.parseDouble(orderTotal);
			
			int countBefore = tradingSteps.getCountMyOrders(driver);
			// Select sell button
			tradingSteps.selectSell(driver);
			// Submit the Order.
			tradingSteps.clickOnSubmitOrder(driver);
			int countAfter = tradingSteps.getCountMyOrders(driver);
			System.out.println(
					"MyOrders Count Before Submit:" + countBefore + "\n" + "MyOrders Count After Submit:" + countAfter);
			for (int i = 0; i < 150; i++) {
				if (countAfter > countBefore)
					break;
				else
					countAfter = tradingSteps.getCountMyOrders(driver);
			}
			// Waiting for changing the status of the order
			tradingSteps.waitForClearingOrder(driver, "Sell, Pending");

			if (doubleOrderAmount < doubleBaseCurrency) {
				
				// Asserting Order Status.
				tradingSteps.verifySellRowOrderStatus(driver, softAssert, "Open");
				
				homeSteps.moveToBalanceTab(driver);
				balanceList = balancesStep.getBalances(driver);
				counterCurrency = balanceList.get(4);
				baseCurrency = balanceList.get(2);
				double actual_cc = Double.parseDouble(counterCurrency);
				double expected_cc = doubleCounterCurrency;
				// Asserting Counter Currency
				tradingSteps.verifyCurrencies(driver, softAssert, actual_cc, expected_cc, "Counter Currency Assertion Failed");
				
				double actual_bc = Double.parseDouble(baseCurrency);
				doubleOrderAmount = UtilityMethods.round(doubleOrderAmount, 8);
				double expected_bc = doubleBaseCurrency - doubleOrderAmount;
				// Asserting Base Currency
				tradingSteps.verifyCurrencies(driver, softAssert, actual_bc, expected_bc, "Base Currency Assertion Failed");
			
			} else {
				// Assertion for validating pop-up i.e., you have not enough Eth.
				tradingSteps.verifyValidationMsgNotEnoughBalance(driver, softAssert, "Not enough GNO");
			}

		}

		else {
			
			bidBuyPrice = tradingSteps.bidBuyPrice(driver);
			doubleBidBuyPrice = Double.parseDouble(bidBuyPrice);
			bidBuyAmount = tradingSteps.bidBuyAmount(driver);
			doubleBidBuyAmount = Double.parseDouble(bidBuyAmount);
			doubleOrderPrice = doubleBidBuyPrice;
			doubleOrderAmount = UtilityMethods.round((doubleBidBuyAmount / 1000), 6);
			tradingSteps.createOrder(driver, doubleOrderPrice, doubleOrderAmount);
			orderTotal = tradingSteps.getOrderTotalStep(driver);
			doubleOrderTotal = Double.parseDouble(orderTotal);
			
			int countBefore = tradingSteps.getCountMyOrders(driver);
			// Select sell button
			tradingSteps.selectSell(driver);
			// Submit the Order
			tradingSteps.clickOnSubmitOrder(driver);
			int countAfter = tradingSteps.getCountMyOrders(driver);
			System.out.println(
					"MyOrders Count Before Submit:" + countBefore + "\n" + "MyOrders Count After Submit:" + countAfter);
			for (int i = 0; i < 150; i++) {
				if (countAfter > countBefore)
					break;
				else
					countAfter = tradingSteps.getCountMyOrders(driver);
			}
			// Waiting for changing the status of the order
			tradingSteps.waitForClearingOrder(driver, "Sell, Pending");

			if (doubleOrderAmount < doubleBaseCurrency) {
				
				double actual_bba = Double.parseDouble(tradingSteps.bidBuyAmount(driver));
				double expected_bba = doubleBidBuyAmount - doubleOrderAmount;
				tradingSteps.verifyBidBuyAmount(driver, softAssert, actual_bba, expected_bba);
				homeSteps.moveToBalanceTab(driver);
				balanceList = balancesStep.getBalances(driver);
				counterCurrency = balanceList.get(4);
				baseCurrency = balanceList.get(2);
				double actual_cc = Double.parseDouble(counterCurrency);
				double expected_cc = UtilityMethods.round(doubleCounterCurrency + (doubleBidBuyAmount * doubleOrderAmount), 8);
				// Asserting for Counter Currency.
				tradingSteps.verifyCurrencies(driver, softAssert, actual_cc, expected_cc, "Counter Currency Assertion Failed");
				
				double actual_bc = Double.parseDouble(baseCurrency);
				//doubleOrderAmount = UtilityMethods.round(doubleOrderAmount, 8);
				double expected_bc = doubleBaseCurrency - doubleOrderAmount;
				expected_bc = UtilityMethods.round(expected_bc, 8);
				// Asserting for Base Currency.
				tradingSteps.verifyCurrencies(driver, softAssert, actual_bc, expected_bc, "Base Currency Assertion Failed");
			
			} else {
				// Assertion for validating pop-up i.e., you have not enough Eth.
				tradingSteps.verifyValidationMsgNotEnoughBalance(driver, softAssert, "Not enough GNO");
			}
		}
		endTest();
	}

	/**
	 * <h1>Trading Selling Third Scenario</h1>
	 * <p>
	 * This test case verify that all the selling trading scenario's are working
	 * fine when order price is greater than bid buy price. Order Price > Bid Buy
	 * Price
	 *
	 * @throws IOException
	 * @throws InterruptedException
	 */

	@Test(description = "Trading Selling Scenario when Order Price is greater than Bid Buy Price", priority = 2)
	public void sellingThirdScenario() throws IOException, InterruptedException {
		
		driver = startTest();
	    
		loginSteps.clickOnLoginLnk(driver);
		loginSteps.fillLoginDetails(driver, driverObj.getOrderbookUsername(),
				driverObj.getMasterPassword());
		
		homeSteps.moveToBalanceTab(driver);
		balanceList = balancesStep.getBalances(driver);
		baseCurrency = balanceList.get(2);
		counterCurrency = balanceList.get(4);
		System.out.println("Base Currency :" + baseCurrency);
		System.out.println("Counter Currency :" + counterCurrency);
		doubleCounterCurrency = Double.parseDouble(counterCurrency);
		doubleBaseCurrency = Double.parseDouble(baseCurrency);
		
		homeSteps.moveToTradeTab(driver);
		tradingSteps.moveToAntEthMarket(driver);
		// Select sell button
		tradingSteps.selectSell(driver);

		if (tradingSteps.countOfBuyRows(driver) == 0) {
			doubleOrderPrice = 0.001; // Making order by manually entering order price
			doubleOrderAmount = 0.002; // Making order by manually entering order amount
			tradingSteps.createOrder(driver, doubleOrderPrice, doubleOrderAmount);
			orderTotal = tradingSteps.getOrderTotalStep(driver);
			doubleOrderTotal = Double.parseDouble(orderTotal);
			
			int countBefore = tradingSteps.getCountMyOrders(driver);
			tradingSteps.clickOnSubmitOrder(driver);
			int countAfter = tradingSteps.getCountMyOrders(driver);
			System.out.println(
					"MyOrders Count Before Submit:" + countBefore + "\n" + "MyOrders Count After Submit:" + countAfter);
			for (int i = 0; i < 150; i++) {
				if (countAfter > countBefore)
					break;
				else
					countAfter = tradingSteps.getCountMyOrders(driver);
			}
			// Waiting for changing the status of the order
			tradingSteps.waitForClearingOrder(driver, "Sell, Pending");

			if (doubleOrderAmount < doubleBaseCurrency) {
				
				// Asserting for Order Status
				tradingSteps.verifySellRowOrderStatus(driver, softAssert, "Open");
				
				homeSteps.moveToBalanceTab(driver);
				balanceList = balancesStep.getBalances(driver);
				counterCurrency = balanceList.get(4);
				baseCurrency = balanceList.get(2);
				double actual_cc = Double.parseDouble(counterCurrency);
				double expected_cc = doubleCounterCurrency;
				// Asserting for Counter Currency
				tradingSteps.verifyCurrencies(driver, softAssert, actual_cc, expected_cc, "Counter Currency Assertion Failed");
				
				double actual_bc = Double.parseDouble(baseCurrency);
				doubleOrderAmount = UtilityMethods.round(doubleOrderAmount, 8);
				double expected_bc = doubleBaseCurrency - doubleOrderAmount;
				// Asserting for Base Currency
				tradingSteps.verifyCurrencies(driver, softAssert, actual_bc, expected_bc, "Base Currency Assertion Failed");
			
			} else {
				// Assertion for validating pop-up i.e., you have not enough Eth.
				tradingSteps.verifyValidationMsgNotEnoughBalance(driver, softAssert, "Not enough GNO");
			}

		} else {
			
			bidBuyPrice = tradingSteps.bidBuyPrice(driver);
			doubleBidBuyPrice = Double.parseDouble(askSellPrice);
			bidBuyAmount = tradingSteps.bidBuyAmount(driver);
			doubleBidBuyAmount = Double.parseDouble(askSellAmount);
			doubleOrderPrice = UtilityMethods.round((doubleBidBuyPrice + 0.01), 6);
			doubleOrderAmount = UtilityMethods.round((doubleBidBuyAmount / 1000), 6);
			tradingSteps.createOrder(driver, doubleOrderPrice, doubleOrderAmount);
			orderTotal = tradingSteps.getOrderTotalStep(driver);
			doubleOrderTotal = Double.parseDouble(orderTotal);
			
			int countBefore = tradingSteps.getCountMyOrders(driver);
			tradingSteps.clickOnSubmitOrder(driver);
			int countAfter = tradingSteps.getCountMyOrders(driver);
			System.out.println(
					"MyOrders Count Before Submit:" + countBefore + "\n" + "MyOrders Count After Submit:" + countAfter);
			for (int i = 0; i < 150; i++) {
				if (countAfter > countBefore)
					break;
				else
					countAfter = tradingSteps.getCountMyOrders(driver);
			}
			// Waiting for changing the status of the order
			tradingSteps.waitForClearingOrder(driver, "Sell, Pending");

			if (doubleOrderAmount < doubleBaseCurrency) {
				
				// Asserting for Order Status
				tradingSteps.verifySellRowOrderStatus(driver, softAssert, "Open");
				
				homeSteps.moveToBalanceTab(driver);
				balanceList = balancesStep.getBalances(driver);
				counterCurrency = balanceList.get(4);
				baseCurrency = balanceList.get(2);
				double actual_cc = Double.parseDouble(counterCurrency);
				double mul = UtilityMethods.round((doubleBidBuyPrice * doubleOrderTotal), 8);
				double expected_cc = doubleCounterCurrency - mul;
				// Asserting for Counter Currency 
				tradingSteps.verifyCurrencies(driver, softAssert, actual_cc, expected_cc, "Counter Currency Assertion Failed");
				
				double actual_bc = Double.parseDouble(baseCurrency);
				doubleOrderAmount = UtilityMethods.round(doubleOrderAmount, 8);
				double expected_bc = doubleBaseCurrency + doubleOrderAmount;
				//Asserting for Base Currency
				tradingSteps.verifyCurrencies(driver, softAssert, actual_bc, expected_bc, "Base Currency Assertion Failed");
			
			} else {
				// Assertion for validating pop-up i.e., you have not enough Eth.
				tradingSteps.verifyValidationMsgNotEnoughBalance(driver, softAssert, "Not enough GNO");
			}
		}
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
		driver = null;
	}
}