package pages.orderbook;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class BalancesPage extends BasePage {

	public ArrayList<String> getBalances(WebDriver driver) {

		ArrayList<String> balanceList = new ArrayList<String>();
		for (int i = 2; i <= 8; i++) {
			String balances = driver
					.findElement(By.xpath("//div[@class='Balances__content']/div[" + i + "]/div[1]/div[2]/div/div[1]"))
					.getText()
					+ driver.findElement(
							By.xpath("//div[@class='Balances__content']/div[" + i + "]/div[1]/div[2]/div/div[2]"))
							.getText();
			balanceList.add(balances);
		}
		return balanceList;
	}
}
