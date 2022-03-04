package pages.airbnb;

import core.BasePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;


public class ProfilePage extends BasePage {

    //Constructor
    public ProfilePage(AndroidDriver driver) {
        super(driver);
    }

    //Locators
    //Account settings title
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Account Settings\")")
    private AndroidElement profileTitle;

    //Legal title
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Legal\")")
    private AndroidElement legalTitle;

    //Locator que realiza swipe en toda la pagina hasta encontrar el texto indicado, se puede realizar la búsqueda por exactitud o por partes.
    //https://appium.io/docs/en/writing-running-appium/tutorial/swipe/android-simple/#search-by-text
    @AndroidFindBy(uiAutomator = "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"Legal\"))")
    private AndroidElement legalTextScroll;

    //Methods

    /**
     * Verify Profile Title
     * @return True if Alert is present
     * @throws InterruptedException
     */
    @Step("Verify Profile Title")
    public boolean verifyProfileText(){
        System.out.println("Validating if Profile page is present...");
        return profileTitle.isDisplayed();
    }

    /**
     * Verify Legal Title
     * @return True if Alert is present
     * @throws InterruptedException
     */
    public boolean verifyLegalText(){
        System.out.println("Validating if Legal title is present...");
        return legalTextScroll.isDisplayed();
    }
}