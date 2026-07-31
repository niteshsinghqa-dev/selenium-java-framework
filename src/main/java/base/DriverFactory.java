package base;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import config.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
public class DriverFactory {
	 private DriverFactory() {}

	    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	    public static void initializeDriver() {

	        String browser = ConfigReader.get("browser").toLowerCase();
	        boolean headless = ConfigReader.getBoolean("headless");

	        switch (browser) {

	            case "chrome":

	                WebDriverManager.chromedriver().setup();

	                ChromeOptions chromeOptions = new ChromeOptions();

	                if (headless) {
	                    chromeOptions.addArguments("--headless=new");
	                }

	                chromeOptions.addArguments("--start-maximized");

	                driver.set(new ChromeDriver(chromeOptions));

	                break;

	            case "firefox":

	                WebDriverManager.firefoxdriver().setup();

	                FirefoxOptions firefoxOptions = new FirefoxOptions();

	                if (headless) {
	                    firefoxOptions.addArguments("-headless");
	                }

	                driver.set(new FirefoxDriver(firefoxOptions));

	                break;

	            case "edge":

	                WebDriverManager.edgedriver().setup();

	                EdgeOptions edgeOptions = new EdgeOptions();

	                if (headless) {
	                    edgeOptions.addArguments("--headless=new");
	                }

	                driver.set(new EdgeDriver(edgeOptions));

	                break;

	            default:

	                throw new RuntimeException("Unsupported browser : " + browser);

	        }

	        getDriver().manage().timeouts().implicitlyWait(
	                Duration.ofSeconds(ConfigReader.getInt("implicit.wait")));

	        getDriver().manage().timeouts().pageLoadTimeout(
	                Duration.ofSeconds(ConfigReader.getInt("page.load.timeout")));

	    }

	    public static WebDriver getDriver() {

	        return driver.get();

	    }

	    public static void quitDriver() {

	        if (driver.get() != null) {

	            driver.get().quit();

	            driver.remove();

	        }

	    }


}
