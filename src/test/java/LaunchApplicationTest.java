import org.testng.Assert;
import org.testng.annotations.Test;

import base.DriverFactory;

public class LaunchApplicationTest {
	  @Test
	    public void verifyApplicationLaunch() {

	        String actualTitle =
	                DriverFactory.getDriver().getTitle();

	        Assert.assertEquals(
	                actualTitle,
	                "Swag Labs");

	    }

}
