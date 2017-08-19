package base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import constants.Constants;
import utilities.FileOperations;

public class Driver {

	private String detectedOS = null;
	private WebDriver webdriver = null;
	public final String USERNAME = "";
	public final String AUTOMATE_KEY = "";
	private String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

	/**
	 * <h1>Operating System</h1>
	 * <p>
	 * This method find the operating system name
	 * 
	 * @return Operating system name
	 */
	public String getOperatingSystemType() {

		String OS = System.getProperty("os.name", "generic").toLowerCase();
		if ((OS.indexOf("mac") >= 0)) {
			detectedOS = "MacOS";
		} else if (OS.indexOf("win") >= 0) {
			detectedOS = "Windows";
		} else if (OS.indexOf("nux") >= 0) {
			detectedOS = "Linux";
		} else {
			detectedOS = "Other";
		}
		return detectedOS;
	}

	/**
	 * <h1>Browser Type</h1>
	 * <p>
	 * This method return the browsers name so that {@link Driver} create a driver
	 * for test
	 * 
	 * @return Browser name
	 * 
	 */
	public String getBrowserType() {
		FileOperations fileOperations = new FileOperations();
		Constants constants = new Constants();
		return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "browserName");
	}

	/**
	 * <h1>Orderbook URL</h1>
	 * <p>
	 * This method find the application URL
	 * 
	 * @return Application URL
	 * 
	 */
	public String getOrderbookUrl() {
		FileOperations fileOperations = new FileOperations();
		Constants constants = new Constants();
		return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "orderbookUrl");
	}
	
	/**
	 * <h1>Orderbook Local URL</h1>
	 * <p>
	 * This method find the application URL
	 * 
	 * @return Application URL
	 * 
	 */
	public String getOrderbookLocalUrl() {
		FileOperations fileOperations = new FileOperations();
		Constants constants = new Constants();
		return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "orderbookUrlLocal");
	}

	/**
	 * <h1>Orocrypt URL</h1>
	 * <p>
	 * This method find the application URL
	 * 
	 * @return Application URL
	 * 
	 */
	public String getOrocryptUrl() {
		FileOperations fileOperations = new FileOperations();
		Constants constants = new Constants();
		return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "orocryptUrl");
	}
	
	/**
	 * <h1>Cryptaur Refferal URL</h1>
	 * <p>
	 * This method find the application refferal URL
	 * 
	 * @return Application URL
	 * 
	 */
	public String getCryptaurRefferalUrl() {
		FileOperations fileOperations = new FileOperations();
		Constants constants = new Constants();
		return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "cryptaurRefferalUrl");
	}

	/**
	 * <h1>Application Username</h1>
	 * <p>
	 * This method find the username
	 * 
	 * @return Username
	 */
	public String getOrderbookUsername() {
		FileOperations fileOperations = new FileOperations();
		Constants constants = new Constants();
		return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "orderbookUsername");
	}
	
	/**
	 * <h1>Application Username</h1>
	 * <p>
	 * This method find the username
	 * 
	 * @return Username
	 */
	public String getOrocryptUsername() {
		FileOperations fileOperations = new FileOperations();
		Constants constants = new Constants();
		return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "orocryptUsername");
	}
	
	/**
	 * <h1>Application Username</h1>
	 * <p>
	 * This method find the username
	 * 
	 * @return Username
	 */
	public String getAmbisafeUsername() {
		FileOperations fileOperations = new FileOperations();
		Constants constants = new Constants();
		return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "ambisafeUsername");
	}

	/**
	 * <h1>Application Password</h1>
	 * <p>
	 * This method find the password
	 * 
	 * @return Password
	 */
	public String getMasterPassword() {
		FileOperations fileOperations = new FileOperations();
		Constants constants = new Constants();
		return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "masterPassword");
	}
	
	/**
	 * <h1>Application Password</h1>
	 * <p>
	 * This method find the password
	 * 
	 * @return Password
	 */
	public String getPassword() {
		FileOperations fileOperations = new FileOperations();
		Constants constants = new Constants();
		return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "password");
	}

	/**
	 * <h1>Application Invalid Password</h1>
	 * <p>
	 * This method find the player's invalid password
	 * 
	 * @return Invalid password of the player
	 */
	public String getInvalidPassword() {
		FileOperations fileOperations = new FileOperations();
		Constants constants = new Constants();
		return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "invalidPassword");
	}

	/**
	 * <h1>Web driver for browser</h1>
	 * <p>
	 * This method create a web driver for test case execution.
	 * 
	 * @exception Input
	 *                output exception
	 * 
	 * @return WebDriver
	 */
	public WebDriver createDriver() throws IOException {
		String osType = getOperatingSystemType();
		String browserName = getBrowserType();

		if (browserName.equalsIgnoreCase("firefox") || browserName.equalsIgnoreCase("ff")) {
			webdriver = new FirefoxDriver();
			webdriver.manage().window().maximize();
		} else if (browserName.equalsIgnoreCase("chrome") || browserName.equalsIgnoreCase("ch")) {
			if (osType.equals("Linux")) {
				System.setProperty("webdriver.chrome.driver", "src/../libs/chromedriver");
			} else if (osType.equals("MacOS")) {
				System.setProperty("webdriver.chrome.driver", "src/../libs/chromedrivermac");
			} else {
				System.setProperty("webdriver.chrome.driver", "src/../libs/chromedriver.exe");
			}
			webdriver = new ChromeDriver();
			webdriver.manage().window().maximize();

		} else if (browserName.equalsIgnoreCase("internet explorer") || browserName.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", "src/../libs/IEDriverServer.exe");
			webdriver = new InternetExplorerDriver();
			webdriver.manage().window().maximize();
		} else if (browserName.equalsIgnoreCase("BrowserStack") || browserName.equalsIgnoreCase("bs")) {
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("browser", "Chrome");
			caps.setCapability("browser_version", "58.0");
			caps.setCapability("os", "Windows");
			caps.setCapability("os_version", "10");
			caps.setCapability("resolution", "1366x768");
			caps.setCapability("browserstack.debug", "true");
			webdriver = new RemoteWebDriver(new java.net.URL(URL), caps);
			webdriver.manage().window().setSize(new Dimension(412, 700));
		} else if (browserName.equalsIgnoreCase("safari")) {
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("browser", "Safari");
			caps.setCapability("browser_version", "9.1");
			caps.setCapability("os", "OS X");
			caps.setCapability("os_version", "El Capitan");
			caps.setCapability("resolution", "1280x960");
			webdriver = new RemoteWebDriver(new java.net.URL(URL), caps);
		}
		return webdriver;
	}

	/**
	 * <h1>Screenshot</h1>
	 * <p>
	 * This method take a screen shot
	 * 
	 * @param driver
	 *            - Current Webdriver object
	 * @param name
	 *            - Screenshot name
	 * @exception Input
	 *                output exception
	 */
	public void takeScreenshot(WebDriver driver, String name) {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		Date currDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM_dd_yy_HH_mm_ss");
		String dateAndTime = dateFormat.format(currDate);

		try {
			FileUtils.copyFile(scrFile, new File("screenshots/" + name + "_" + dateAndTime + ".png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}