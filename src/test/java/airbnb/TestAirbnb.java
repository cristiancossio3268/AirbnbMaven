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

public class TestAirbnb extends BaseTest {

    @Test
    public void testAirbnbApp() throws InterruptedException, IOException {

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
         */

        //4-Verify explore page
        Assert.assertTrue(exploreObj.isExplorePagePresent(), "Explore page is NOT displayed");
        exploreObj.takeScreenShot(pathSS,"4-Verify explore page OK");

        //5-Look for destination whit JSON
        exploreObj.whereAreYouGoingClick();
        exploreObj.enterDestination(exploreObj.getValueJSON("src/main/resources/airbnb/destination.json", "Playa"));
        exploreObj.takeScreenShot(pathSS,"5-Look for destination whit JSON OK");
        exploreObj.tapBack();
        exploreObj.tapBack();

        //6-Look for destination whit Excel
        exploreObj.whereAreYouGoingClick();
        exploreObj.enterDestination(exploreObj.getValueFromExcel("src/main/resources/airbnb/destinations.xlsx", "Cataratas"));
        exploreObj.takeScreenShot(pathSS,"6-Look for destination whit Excel OK");
        exploreObj.tapBack();
        exploreObj.tapBack();

        //7-Click on Profile
        barraObj.profileButtonClick();
        barraObj.takeScreenShot(pathSS,"7-Click on Profile OK");

        //8-Verify Profile page
        Assert.assertTrue(profileObj.verifyProfileText(),"Profile page is NOT displayed");
        barraObj.takeScreenShot(pathSS,"8-Verify Profile page OK");

        //9-Swipes
        profileObj.swipeToElement(3, BasePage.Direction.UP);
        profileObj.takeScreenShot(pathSS,"9-Swipes OK");

        //10-Find a Restaurant whit JSON
        barraObj.exploreButtonClickTwo();
        exploreObj.whereAreYouGoingClick();
        exploreObj.enterDestination(exploreObj.getValueJSON("src/main/resources/airbnb/destination.json", "Restaurante"));
        exploreObj.takeScreenShot(pathSS,"10-Find a Restaurant whit JSON OK");
        exploreObj.tapBack();
        exploreObj.tapBack();

        //11-Selección del valor en base a parámetro
        exploreObj.whereAreYouGoingClick();
        exploreObj.enterDestination(exploreObj.getValueJSON("src/main/resources/airbnb/destination.json", "Playa"));
        exploreObj.removeKeyboard();
        exploreObj.findDestinationClick("Cancun, Cancún");
        exploreObj.takeScreenShot(pathSS,"11-Selección del valor en base a parámetro OK");
        exploreObj.tapBack();
        exploreObj.tapBack();
        exploreObj.tapBack();

        //12-Go to Profile and validate Legal
        barraObj.profileButtonClickTwo();
        profileObj.swipeToElement(3, BasePage.Direction.UP);
        Assert.assertTrue(profileObj.verifyLegalText(),"Legal on profile page  is NOT displayed");
        profileObj.takeScreenShot(pathSS,"12-Go to Profile and validate Legal OK");

        /*
        //Find locations and validate

         */
    }
}
