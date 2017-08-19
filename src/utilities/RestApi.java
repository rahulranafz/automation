package utilities;

import static com.jayway.restassured.RestAssured.get;
import com.jayway.restassured.response.Response;
import org.json.JSONException;
import org.json.JSONObject;


public class RestApi {
	
		
	
	public String getRequestFindPrice() throws JSONException, InterruptedException {
		
		//make get request to fetch price of ETH/USD
		Response resp = get("https://api.gdax.com/products/eth-usd/ticker");
				
		//Fetching response in JSON
		JSONObject jsonResponse = new JSONObject(resp.asString());
				
		//Fetching value of price parameter
		String price = jsonResponse.getString("price");
		
		//System.out.println("Price:" + price);
		
		return price;			
		
	}

}
