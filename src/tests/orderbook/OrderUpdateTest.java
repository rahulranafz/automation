package tests.orderbook;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.Driver;
import pages.orderbook.TradingPage;
import steps.orderbook.HomeSteps;
import steps.orderbook.LoginSteps;
import steps.orderbook.TradingSteps;
import utilities.RestApi;

public class OrderUpdateTest extends TradingPage {
	
	private WebDriver driver = null;
	Driver driverObj = new Driver();
	SoftAssert softAssert = new SoftAssert();
	LoginSteps loginSteps = new LoginSteps();
	TradingSteps tradingSteps = new TradingSteps();
	HomeSteps homeSteps = new HomeSteps();
	
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
		driver.get(driverObj.getOrderbookLocalUrl());
		return driver;
	}
			
	@Test(priority = 0)
	public void orderUpdateBasedOnETHPerUSD() throws JSONException, InterruptedException, IOException {
		
		RestApi rest = new RestApi();
		ArrayList<String> prices = new ArrayList<String>();
		double lastPriceCheck = 0;
		// $ price per token
		double price = 1;
		// % money fee
		double fee = 0;
		double diff = 1; // 1% difference
		double lastSetPrice = 0; 
		
		for(int i =0; i<=1000; i++) {
			double lastPrice = Double.parseDouble(rest.getRequestFindPrice());
			System.out.println("Last Price : " + lastPrice);
				
			// Check for last price change
			if(lastPrice != lastPriceCheck) {
				lastPriceCheck = lastPrice;
				// Check for size of array list price
				if(prices.size()>10) {
					prices.remove(0);
					prices.add(Double.toString(lastPrice));
				}
				else {
					prices.add(Double.toString(lastPrice));
				}
				System.out.println("Size of Price List : " + prices.size());
				// Check for size of price list
				if (prices.size() > 1) {
					double sumOfPrices = 0;
					for (int j = 0; j < prices.size(); j++) {
						sumOfPrices = sumOfPrices + Double.parseDouble(prices.get(j));
					}
					double avgPrice = sumOfPrices/prices.size();
					System.out.println("Average Price : " + avgPrice);	
					double result = price / avgPrice / (1 - (fee / 100));
					System.out.println("Result :" + result);
				    if ((Math.abs(result - lastSetPrice) * 100 / lastSetPrice) > diff) {
				    	lastSetPrice = result;
				        // Update the order
				        
				    	driver = startTest();
				        // Login into account
				        loginSteps.clickOnLoginLnk(driver);
				        loginSteps.fillLoginDetails(driver, driverObj.getAmbisafeUsername(),
				    				driverObj.getMasterPassword());
				    	// Moving to trading page 
				        homeSteps.moveToTradeTab(driver);
				    	if(tradingSteps.buyOrderCount(driver) > 0)
				    	{
				    		for(int k = 0; k < tradingSteps.buyOrderCount(driver); k++) {   				
				    			int countBefore = tradingSteps.getCountMyOrders(driver);
				    			// Canceling previous buy Order
				    			tradingSteps.cancelBuyOrder(driver);
				    			int countAfter = tradingSteps.getCountMyOrders(driver);
				    			//System.out.println("MyOrders Count Before Submit:" + countBefore + "\n" + "MyOrders Count After Submit:" + countAfter);
				    			for (int l = 0; l < 100; l++) {
				    				if (countBefore == countAfter) {
				    					countAfter = tradingSteps.getCountMyOrders(driver);
				    				} else
				    					break;
				    			}
				    				
				    			// Waiting for changing the status of the order
				    			//tradingSteps.waitForClearingOrder(driver, "Canceled");
				    		}
				    			
				    	}
				    	if(tradingSteps.sellOrderCount(driver) > 0) {
				    		for(int k = 0; k < tradingSteps.sellOrderCount(driver); k++) {   				
				    			int countBefore = tradingSteps.getCountMyOrders(driver);
				    			// Canceling previous sell order
				    			tradingSteps.cancelSellOrder(driver);
				    			int countAfter = tradingSteps.getCountMyOrders(driver);
				    			//System.out.println("MyOrders Count Before Submit:" + countBefore + "\n" + "MyOrders Count After Submit:" + countAfter);
				    			for (int l = 0; l < 100; l++) {
				    				if (countBefore == countAfter) {
				    						countAfter = tradingSteps.getCountMyOrders(driver);
				    				} else
				    					break;
				    			}
				    				
				    			// Waiting for changing the status of the order
				    			//tradingSteps.waitForClearingOrder(driver, "Canceled");
				    		}
				    			
				    	}
				    	// Making order by entering order price and order amount manually.
						tradingSteps.createOrder(driver, result, 10.0);
						int countBefore = tradingSteps.getCountMyOrders(driver);
						// Select sell button
						tradingSteps.selectSell(driver);
						tradingSteps.clickOnSubmitOrder(driver);
						int countAfter = tradingSteps.getCountMyOrders(driver);
						//System.out.println("MyOrders Count Before Submit:" + countBefore + "\n" + "MyOrders Count After Submit:" + countAfter);
						for (int k = 0; k < 100; k++) {
							if (countAfter > countBefore)
								break;
							else
								countAfter = tradingSteps.getCountMyOrders(driver);
						}
					    // Waiting for changing the status of the order
						tradingSteps.waitForClearingOrder(driver, "Sell, Pending");
				    		
				    	// Logout from the account
				    	homeSteps.logoutApplication(driver);
				    	endTest();
				            
				    }
				}
				Thread.sleep(30000);
			}
			else {
						
				Thread.sleep(30000);
			}						
		
	   }
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
