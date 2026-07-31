package base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import config.ConfigReader;

public class BaseTest {
	
	 @BeforeMethod
	    public void setup() {

	        DriverFactory.initializeDriver();

	        DriverFactory.getDriver().get(
	                ConfigReader.get("base.url"));

	    }

	    @AfterMethod
	    public void tearDown() {

	        DriverFactory.quitDriver();

	    }


}
