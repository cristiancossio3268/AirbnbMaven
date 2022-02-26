package pages.airbnb;

import core.BasePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;

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

    //Back arrow icon
    @AndroidFindBy(accessibility = "Navigate Up")
    private AndroidElement backArrow;

    //Methods

    /**
     * Check if Explore page is present
     * @return
     */
    public boolean isExplorePagePresent(){
        reporter("Trying to check if Explore page is present...");
        System.out.println("Trying to check if Explore page is present...");
        return notSureTx.isDisplayed();
    }

    /**
     * Click on Where are you going method
     */
    public void whereAreYouGoingClick () {
        reporter("Trying to click on Where are you going method...");
        System.out.println("Trying to click on Where are you going method...");
        whereText.click();
        reporter("Where are you going clicked!");
        System.out.println("Where are you going clicked!");
    }

    /**
     * Enter destination Method
     * @param destination
     */
    public void enterDestination (String destination) {
        reporter("Trying to enter destination field...");
        System.out.println("Trying to enter destination field...");
        destinationTxBx.sendKeys(destination);
        reporter("Destination entered");
        System.out.println("Destination entered");
    }

    /**
     * Se le entrega un parámetro de búsueda y guarda los datos encontrados en una lista y poder recorrer esa lista de datos y hacer click en el parámetro
     * @param searchParameter
     */
    public void findDestinationClick(String searchParameter){
        reporter("Trying to click final destination...");
        System.out.println("Trying to click final destination...");
        waitForElementToBeClickable(By.id("com.airbnb.android:id/title"));
        List <AndroidElement> elementsSearch = driver.findElementsById("com.airbnb.android:id/title");
        reporter("List size: "+elementsSearch.size());
        System.out.println("List size: "+elementsSearch.size());
        //For to see elements from List - imprime todos los elementos de la lista
        reporter("Values found:");
        System.out.println("Values found:");
        for (AndroidElement element:elementsSearch) {
            reporter(element.getText());
            System.out.println(element.getText());
        }
        for (int i= 0; i < elementsSearch.size(); i++){
            if (elementsSearch.get(i).getText().equalsIgnoreCase(searchParameter)){
                reporter("Search parameter found");
                System.out.println("Search parameter found");
                elementsSearch.get(i).click();
                break;
            }else
                reporter("Current value is not matching: "+elementsSearch.get(i).getText());
                System.out.println("Current value is not matching: "+elementsSearch.get(i).getText());
        }
    }

    /**
     * Back arrow click
     */
    public void clickBackArrow(){
        reporter("Trying to click back arrow...");
        System.out.println("Trying to click back arrow...");
        backArrow.click();
        reporter("Back arrow was clicked!");
        System.out.println("Back arrow was clicked!");
    }

    /**
     * Look for a destination method
     * @param destination
     */
    public void lookForDestination(String destination){
        whereAreYouGoingClick();
        enterDestination(destination);
        hideKeyboard();
    }
}