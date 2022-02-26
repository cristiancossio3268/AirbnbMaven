package core;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    AndroidDriver<AndroidElement> driver;

    @BeforeClass(groups = "setup")
    public void setup() throws MalformedURLException {
        //Desired Capabilities
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "12");
        capabilities.setCapability("appActivity", "com.airbnb.android.feat.homescreen.HomeActivity");
        capabilities.setCapability("appPackage", "com.airbnb.android");
        //capabilities.setCapability("app", "/Users/ivanrivas/AirbnbMaven/src/main/resources/Selendroid/selendroid-test-app.apk");

        System.out.println("Creando el driver...");
        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
        System.out.println("Driver creado!");

        //Implicit wait
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        System.out.println("Implicit wait set");
    }

    @AfterClass(groups = "setup")
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