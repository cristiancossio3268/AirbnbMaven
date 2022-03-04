package airbnb;

import core.BaseTest;
import core.utils.listeners.AllureListener;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.airbnb.ExplorePage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Listeners(AllureListener.class)
public class TestSuit02C extends BaseTest {

    //Class objects
    ExplorePage exploreObj;

    LocalDateTime date = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH:mm:ss");
    String formattedLocalDateTime = date.format(formatter);
    String pathSS = "src/main/resources/airbnb/evidences/" + formattedLocalDateTime + "/";
    //pathSS = pathSS.replaceAll(":",".");

    @Test(priority = 1, groups = "destinations")
    public void TC001_lookForDestinationWhitJSON() {

        Reporter.log("Look for destination whit JSON");

        //Class objects
        exploreObj = new ExplorePage(getDriver());

        //Verify explore page
        Assert.assertTrue(exploreObj.isExplorePagePresent(), "Explore page is NOT displayed");

        //Look for destination whit JSON
        //exploreObj.lookForDestination(exploreObj.getValueJSON("src/main/resources/airbnb/testdata/destinations.json", "Playa"));
        exploreObj.lookForDestination(exploreObj.getValueJSON("src/main/resources/airbnb/destinations.json", "Playa"));
        //exploreObj.lookForDestination(exploreObj.getJSONValue("destinations", "Playa"));
        exploreObj.takeScreenshots("Look for destination whit JSON");
        exploreObj.clickBackArrow();
    }

    @Test(priority = 1, groups = "destinations")
    public void TC002_lookForDestinationWhitExcel(){

        Reporter.log("Look for destination whit Excel");

        //Class objects
        exploreObj = new ExplorePage(getDriver());

        //Verify explore page
        Assert.assertTrue(exploreObj.isExplorePagePresent(), "Explore page is NOT displayed");

        //Look for destination whit Excel
        //exploreObj.lookForDestination(exploreObj.getValueFromExcel("src/main/resources/airbnb/destinations.xlsx", "Cataratas"));
        exploreObj.lookForDestination(exploreObj.getCellData("destinations",1,0));
        exploreObj.takeScreenshots("Look for destination whit Excel");
        exploreObj.clickBackArrow();
    }

    @Test(priority = 1, groups = "destinations")
    public void TC003_findRestaurantWhitJSON(){

        Reporter.log("Find a Restaurant whit JSON");

        //Class objects
        exploreObj = new ExplorePage(getDriver());

        //Verify explore page
        Assert.assertTrue(exploreObj.isExplorePagePresent(), "Explore page is NOT displayed");

        //Find a Restaurant whit JSON
        exploreObj.lookForDestination(exploreObj.getValueJSON("src/main/resources/airbnb/destinations.json", "Restaurante"));
        exploreObj.takeScreenshots("Find a Restaurant whit JSON");
        exploreObj.clickBackArrow();
    }

    @Test(priority = 1, groups = "destinations")
    public void TC004_selectValueFromResultSearchBasedOnText(){

        Reporter.log("Select a value from result search based on a text");

        //Class objects
        exploreObj = new ExplorePage(getDriver());

        //Verify explore page
        Assert.assertTrue(exploreObj.isExplorePagePresent(), "Explore page is NOT displayed");

        //Select a value from result search based on a text
        exploreObj.lookForDestination(exploreObj.getValueJSON("src/main/resources/airbnb/destinations.json", "Playa"));
        exploreObj.findDestinationClick("Cancun, Cancún");
        exploreObj.takeScreenshots("Select a value from result search based on a text");
        exploreObj.clickBackArrow();
        exploreObj.clickBackArrow();
    }
}