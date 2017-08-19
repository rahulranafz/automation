package steps.orderbook;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;

import pages.orderbook.BalancesPage;
import pages.orderbook.HomePage;

public class BalancesSteps extends BalancesPage{
	ArrayList<String> balanceList = new ArrayList<String> ();
	HomePage homePage = new HomePage();
	
	public ArrayList<String> getBalancesSteps(WebDriver driver) throws InterruptedException {
		homePage.clickOnBalancesLnk(driver);
		balanceList = getBalances(driver);
		return balanceList;
	}
}
