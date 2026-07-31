import org.testng.annotations.Test;

import config.ConfigReader;

public class ConfigTest {
	 @Test
	    public void verifyConfiguration() {

	        System.out.println(ConfigReader.get("browser"));

	        System.out.println(ConfigReader.get("base.url"));

	        System.out.println(ConfigReader.get("username"));
	 }
}
