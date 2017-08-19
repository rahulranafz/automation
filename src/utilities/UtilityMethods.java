package utilities;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class UtilityMethods {
	
	/**
	 * <h1>Change anything into number</h1>
	 * <p>
	 * This methods convert into integer number
	 * @author Gaurav
	 * @param str Any string value that contains special character and numeric 
	 * @return numberOnly it return only numeric value from the str
	 */
	public int getOnlyNumber(String str) {
		int numberOnly=0;
		try {
			numberOnly= Integer.parseInt(str.replaceAll("[^0-9]", ""));
			return numberOnly;
		} catch (NumberFormatException e) {
			return numberOnly;
		}
		
	}
	/**
	 * <h1>This method split string by any character</h1>
	 * <p>
	 * This method split string by character basis and return into array format
	 * @author Gaurav
	 * @param  str this one is string variable which one pass at the time of method calling
	 * @return it return string array after split
	 */
	public String[] getArrayAfterSplit(String str,String splitChar) {
		String[] parts = str.split(splitChar);
		return parts;
	}
	
	/**
	 * <h1>This method generate Random Integers</h1>
	 * <p>
	 * This method generate Random Integer and return it
	 * @author Gaurav
	 * @return it return integer
	 */
	public static int getRandomInteger() {

		Random rand = new Random();

		int randomNum = rand.nextInt((999 - 0) + 1) + 0;

		return randomNum;
	}
	
	/**
	 * <h1>This method round off double value</h1>
	 * <p>
	 * This method round off double value upto nth place
	 * @author Gaurav
	 * @param  double value and integer places
	 * @return it a double by rounding off
	 */
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
}
