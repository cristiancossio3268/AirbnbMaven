package pages.airbnb;

import core.BasePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class BarraPage extends BasePage {

    //Constructor
    public BarraPage(AndroidDriver driver) {
        super(driver);
    }

    //Locators

    //Profile Icon
    @AndroidFindBy(accessibility = "Profile, tab 5 out of 5")
    private AndroidElement profileButton;

    //Methods

    //Profile Icon
    public void profileButtonClick () {
        System.out.println("Trying to click on Profile Icon...");
        profileButton.click();
        System.out.println("Profile screen is displayed");
    }
}
