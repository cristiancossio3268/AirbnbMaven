package pages.airbnb;

import core.BasePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * Se le entrega un parámetro de búsueda y guarda los datos encontrados en una lista y poder recorrer esa lista de datos y hacer click en el parámetro
     * @param searchParameter
     */
    public void findDestinationClick(String searchParameter){
        System.out.println("Trying to click final destination...");
        List <AndroidElement> elementsSearch = driver.findElementsById("com.airbnb.android:id/title");
        System.out.println("List size: "+elementsSearch.size());
        waitForElementToBeClickable(By.id("com.airbnb.android:id/title"));
        for (int i= 0; i < elementsSearch.size(); i++){
            if (elementsSearch.get(i).getText().equalsIgnoreCase(searchParameter)){
                System.out.println("Search parameter found");
                elementsSearch.get(i).click();
                break;
            }else
                System.out.println("Current value is not matching: "+elementsSearch.get(i).getText());
        }
    }
}
