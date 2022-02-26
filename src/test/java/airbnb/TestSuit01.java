package airbnb;

import core.BasePage;
import core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.airbnb.BarraPage;
import pages.airbnb.ExplorePage;
import pages.airbnb.LoginPage;
import pages.airbnb.ProfilePage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestSuit01 extends BaseTest {

    @Test(priority = 1, groups = "login")
    public void testCase01() throws InterruptedException, IOException {

        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH:mm:ss");
        String formattedLocalDateTime = date.format(formatter);
        String pathSS = "src/main/resources/airbnb/evidences/" + formattedLocalDateTime + "/";
        pathSS = pathSS.replaceAll(":",".");

        //Class objects

        LoginPage loginObj = new LoginPage(getDriver());
        ExplorePage exploreObj = new ExplorePage(getDriver());
        BarraPage barraObj = new BarraPage(getDriver());
        ProfilePage profileObj = new ProfilePage(getDriver());

        //Code

        /*
        //1-Continue with email
        Reporter.log("1-Continue with email");
        loginObj.clickContinueWithEmail();

        //2-Enter credentials
        Reporter.log("2-Enter credentials");
        //loginObj.completeEditTextEmail("ivan.rivas@cooltesters.com");
        loginObj.completeEditTextEmail("criss.appium@gmail.com");
        loginObj.clickContinue();
        Thread.sleep(6000);

        loginObj.sendPassword("Siete777Siete888");
        loginObj.clickContinue();

        //3-Captcha
        Reporter.log("3-Captcha");
        Thread.sleep(50000);

        //4-Verify explore page
        Assert.assertTrue(exploreObj.isExplorePagePresent(), "Explore page is NOT displayed");
        exploreObj.takeScreenShot(pathSS,"4-Verify explore page OK");
        */

        //5-Look for destination whit JSON
        exploreObj.lookForDestination(exploreObj.getValueJSON("src/main/resources/airbnb/destination.json", "Playa"));
        exploreObj.takeScreenShot(pathSS,"5-Look for destination whit JSON OK");
        exploreObj.clickBackArrow();

        //6-Look for destination whit Excel
        exploreObj.lookForDestination(exploreObj.getValueFromExcel("src/main/resources/airbnb/destinations.xlsx", "Cataratas"));
        exploreObj.takeScreenShot(pathSS,"6-Look for destination whit Excel OK");
        exploreObj.clickBackArrow();

        //7-Click on Profile
        barraObj.profileButtonClick();
        barraObj.takeScreenShot(pathSS,"7-Click on Profile OK");

        //8-Verify Profile page
        Assert.assertTrue(profileObj.verifyProfileText(),"Profile page is NOT displayed");
        barraObj.takeScreenShot(pathSS,"8-Verify Profile page OK");

        //9-Swipe to get bottom of the screen
        profileObj.swipeToElement(3, BasePage.Direction.UP);
        profileObj.takeScreenShot(pathSS,"9-Swipe to get bottom of the screen OK");

        //10-Find a Restaurant whit JSON
        barraObj.exploreButtonClickTwo();
        exploreObj.lookForDestination(exploreObj.getValueJSON("src/main/resources/airbnb/destination.json", "Restaurante"));
        exploreObj.takeScreenShot(pathSS,"10-Find a Restaurant whit JSON OK");
        exploreObj.clickBackArrow();

        //11-Selección del valor en base a parámetro
        exploreObj.lookForDestination(exploreObj.getValueJSON("src/main/resources/airbnb/destination.json", "Playa"));
        exploreObj.findDestinationClick("Cancun, Cancún");
        exploreObj.takeScreenShot(pathSS,"11-Selección del valor en base a parámetro OK");
        exploreObj.clickBackArrow();
        exploreObj.clickBackArrow();


        //12-Go to Profile and validate Legal
        //--Click on Profile
        barraObj.profileButtonClickTwo();

        //--Verify Profile page
        profileObj.scrollToText("Account Settings");
        Assert.assertTrue(profileObj.verifyProfileText(),"Profile page is NOT displayed");

        //--Swipe to get bottom of the screen
        //profileObj.swipeToElement(3, BasePage.Direction.UP);

        //barraObj.profileButtonClickTwo();
        Assert.assertTrue(profileObj.verifyLegalText(),"Legal on profile page  is NOT displayed");
        profileObj.takeScreenShot(pathSS,"12-Go to Profile and validate Legal OK");

        //13-Scroll to exactly text
        profileObj.scrollToText("Learn about hosting");
        profileObj.takeScreenShot(pathSS,"13-Scroll to exactly text OK");

        //14-Scroll to partial text
        profileObj.scrollToPartialText("money");
        profileObj.takeScreenShot(pathSS,"14-Scroll to partial text OK");

        //Find locations and validate

    }
}
