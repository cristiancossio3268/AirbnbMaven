package pages.airbnb;

import core.BasePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;

public class BarraPage extends BasePage {

    //Constructor
    public BarraPage(AndroidDriver driver) {
        super(driver);
    }

    //Locators

    //Explore Icon 2
    @AndroidFindBy(accessibility = "Explore")
    private AndroidElement exploreButtonTwo;

    //Profile Icon
    @AndroidFindBy(accessibility = "Profile, tab 5 out of 5")
    private AndroidElement profileButton;

    //Profile Icon 2
    @AndroidFindBy(accessibility = "Profile")
    private AndroidElement profileButtonTwo;

    //Methods

    //Explore Icon
    public void exploreButtonClickTwo () {
        System.out.println("Trying to click on Explore Icon...");
        exploreButtonTwo.click();
        System.out.println("Explore screen is displayed");
    }

    //Profile Icon
    @Step("Click on Profile Icon")
    public void profileButtonClick () {
        System.out.println("Trying to click on Profile Icon...");
        profileButton.click();
        System.out.println("Profile screen is displayed");
    }

    //Profile Icon 2
    public void profileButtonClickTwo () {
        System.out.println("Trying to click on Profile Icon...");
        profileButtonTwo.click();
        System.out.println("Profile screen is displayed");
    }

}
