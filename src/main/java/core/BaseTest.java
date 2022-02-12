package core;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {

    AndroidDriver<AndroidElement> driver;

    @BeforeTest
    public void setup() throws MalformedURLException {
        //Desired Capabilities
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "12");
        capabilities.setCapability("appActivity", "com.airbnb.android.feat.homescreen.HomeActivity");
        capabilities.setCapability("appPackage", "com.airbnb.android");
        //capabilities.setCapability("app", "/Users/ivanrivas/AirbnbMaven/src/main/resources/Selendroid/selendroid-test-app.apk");

        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }

    /**
     * Get Driver method
     */
    public AndroidDriver getDriver(){
        return driver;
    }
}