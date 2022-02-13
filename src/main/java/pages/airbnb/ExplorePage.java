package pages.airbnb;

import core.BasePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ExplorePage extends BasePage {

    //Constructor
    public ExplorePage(AndroidDriver driver) {
        super(driver);
    }

    //Locators

    //NotSure text field
    @AndroidFindBy(id = "com.airbnb.android:id/text")
    private AndroidElement notSureTx;

    //Where are you going text
    @AndroidFindBy(accessibility = "Where are you going? Navigate to start your search.")
    private AndroidElement whereText;

    //Destination field
    @AndroidFindBy (uiAutomator = "new UiSelector().text(\"Where are you going?Navigate forward to access search suggestions.\")")
    private AndroidElement destinationTxBx;

    //Destination results
    @AndroidFindBy(id = "com.airbnb.android:id/title")
    private AndroidElement destinationResults;

    //Methods

    /**
     * Check if Explore page is present
     * @return
     */
    public boolean isExplorePagePresent(){
        System.out.println("Trying to check if Explore page is present...");
        return notSureTx.isDisplayed();
    }

    /**
     * Click on Where are you going method
     */
    public void whereAreYouGoingClick () {
        System.out.println("Trying to click on Where are you going method...");
        whereText.click();
        System.out.println("Where are you going clicked!");
    }

    /**
     * Enter destination Method
     * @param destination
     */
    public void enterDestination (String destination) {
        System.out.println("Trying to enter destination field...");
        destinationTxBx.sendKeys(destination);
        System.out.println("Destination entered");
    }
}
