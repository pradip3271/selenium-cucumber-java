package env;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by tom on 24/02/17.
 */
public class DriverUtil {
	public static long DEFAULT_WAIT = 20;
	protected static WebDriver driver;

	public static WebDriver getDefaultDriver() {

		if (driver != null) {
			return driver;
		}

		driver = chooseDriver();
		driver.manage().timeouts().setScriptTimeout(DEFAULT_WAIT, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}

	/**
	 * By default to web driver will be firefox
	 *
	 * Override it by passing -Dbrowser=chrome to the command line arguments
	 * 
	 * Override it by passing -Dheadless=true to the command line argument
	 * 
	 * @param capabilities
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private static WebDriver chooseDriver() {
		DesiredCapabilities capabilities = null;
		String preferredDriver = System.getProperty("browser", "firefox");
		boolean headless = System.getProperty("headless", "false").equals("true");

		switch (preferredDriver.toLowerCase()) {
		case "chrome":
			capabilities = DesiredCapabilities.chrome();
			capabilities.setJavascriptEnabled(true);
			capabilities.setCapability("takesScreenshot", true);
			final ChromeOptions chromeOptions = new ChromeOptions();
			if (headless) {
				chromeOptions.addArguments("--headless");
			}
			capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			System.setProperty("webdriver.chrome.driver", "./chromedriver");
			System.out.println("********************* loading chrome driver");
			driver = new ChromeDriver();
			return driver;
		// case "phantomjs":
		// return new PhantomJSDriver(capabilities);
		default:
			capabilities = DesiredCapabilities.firefox();
			capabilities.setJavascriptEnabled(true);
			capabilities.setCapability("takesScreenshot", true);
			FirefoxOptions options = new FirefoxOptions();
			if (headless) {
				options.addArguments("-headless", "-safe-mode");
			}
			capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);
			System.setProperty("webdriver.gecko.driver", "./geckodriver");
			System.out.println("********************* loading firefox driver");
			driver = new FirefoxDriver(capabilities);
			return driver;
		}
	}

	public static WebElement waitAndGetElementByCssSelector(WebDriver driver, String selector, int seconds) {
		By selection = By.cssSelector(selector);
		return (new WebDriverWait(driver, seconds)).until( // ensure element is visible!
				ExpectedConditions.visibilityOfElementLocated(selection));
	}

	public static void closeDriver() {
		if (driver != null) {
			try {
				driver.close();
				driver.quit(); // fails in current geckodriver! TODO: Fixme
			} catch (NoSuchMethodError nsme) { // in case quit fails
			} catch (NoSuchSessionException nsse) { // in case close fails
			} catch (SessionNotCreatedException snce) {
			} // in case close fails
			driver = null;
		}
	}
}
