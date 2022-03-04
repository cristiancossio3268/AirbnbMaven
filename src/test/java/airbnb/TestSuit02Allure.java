package airbnb;

import core.BaseTest;
import core.utils.listeners.AllureListener;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.airbnb.ExplorePage;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static core.utils.extentreports.ExtentTestManager.startTest;

@Listeners(AllureListener.class)
@Epic("Regression Tests")
@Feature("ExplorePage Tests")
public class TestSuit02Allure extends BaseTest {

    //Class objects
    ExplorePage exploreObj;

    LocalDateTime date = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH:mm:ss");
    String formattedLocalDateTime = date.format(formatter);
    String pathSS = "src/main/resources/airbnb/evidences/" + formattedLocalDateTime + "/";
    //pathSS = pathSS.replaceAll(":",".");


    @Test(priority = 1, description="Scenario Look for destination whit JSON", groups = "destinations")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Look for destination whit JSON")
    @Story("Search")
    public void TC001_lookForDestinationWhitJSON(Method method){

        //Method required for allure
        startTest(method.getName(),"Look for destination whit JSON");

        //Class objects
        exploreObj = new ExplorePage(getDriver());

        //Verify explore page
        Assert.assertTrue(exploreObj.isExplorePagePresent(), "Explore page is NOT displayed");

        //Look for destination whit JSON
        exploreObj.lookForDestination(exploreObj.getValueJSON("src/main/resources/airbnb/destinations.json", "Playa"));
        exploreObj.clickBackArrow();
    }

    @Test(priority = 1, description="Scenario Look for destination whit Excel", groups = "destinations")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Look for destination whit Excel")
    @Story("Search")
    public void TC002_lookForDestinationWhitExcel(Method method){

        startTest(method.getName(),"Look for destination whit Excel");

        //Class objects
        exploreObj = new ExplorePage(getDriver());

        //Verify explore page
        Assert.assertTrue(exploreObj.isExplorePagePresent(), "Explore page is NOT displayed");

        //Look for destination whit Excel
        exploreObj.lookForDestination(exploreObj.getValueFromExcel("src/main/resources/airbnb/destinations.xlsx", "Cataratas"));
        exploreObj.clickBackArrow();
    }

    @Test(priority = 1, description="Scenario Find a Restaurant whit JSON", groups = "destinations")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Find a Restaurant whit JSON")
    @Story("Search")
    public void TC003_findRestaurantWhitJSON(Method method){

        startTest(method.getName(),"Find a Restaurant whit JSON");

        //Class objects
        exploreObj = new ExplorePage(getDriver());

        //Verify explore page
        Assert.assertTrue(exploreObj.isExplorePagePresent(), "Explore page is NOT displayed");

        //Find a Restaurant whit JSON
        exploreObj.lookForDestination(exploreObj.getValueJSON("src/main/resources/airbnb/destinations.json", "Restaurante"));
        exploreObj.clickBackArrow();
        Assert.fail("FAILURE ON PURPOSE");
    }

    @Test(priority = 1, description="Scenario Select a value from result search based on a text", groups = "destinations")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Select a value from result search based on a text")
    @Story("Selection")
    public void TC004_selectValueFromResultSearchBasedOnText(Method method){

        startTest(method.getName(),"Select a value from result search based on a text");

        //Class objects
        exploreObj = new ExplorePage(getDriver());

        //Verify explore page
        Assert.assertTrue(exploreObj.isExplorePagePresent(), "Explore page is NOT displayed");

        //Select a value from result search based on a text
        exploreObj.lookForDestination(exploreObj.getValueJSON("src/main/resources/airbnb/destinations.json", "Playa"));
        exploreObj.findDestinationClick("Cancun, Cancún");
        exploreObj.clickBackArrow();
        exploreObj.clickBackArrow();
    }
}