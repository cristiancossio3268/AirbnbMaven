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

public class TestAirbnb extends BaseTest {

    @Test
    public void testAirbnbApp() throws InterruptedException, IOException {

        //Class objects

        LoginPage loginObj = new LoginPage(getDriver());
        ExplorePage exploreObj = new ExplorePage(getDriver());
        BarraPage barraObj = new BarraPage(getDriver());
        ProfilePage profileObj = new ProfilePage(getDriver());
        String pathSS = "src/main/resources/airbnb/ss/";

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
        Assert.assertTrue(exploreObj.isExplorePagePresent(),"Explore page is NOT displayed");
        //exploreObj.takeScreenShot(pathSS,"4-Verify explore page OK");

        //5-Look for destination whit JSON
        exploreObj.whereAreYouGoingClick();
        exploreObj.enterDestination(exploreObj.getValueJSON("src/main/resources/airbnb/destination.json","Playa"));
        //exploreObj.takeScreenShot(pathSS,"5-Look for destination whit JSON OK");
        exploreObj.tapBack();
        exploreObj.tapBack();

        //6-Look for destination whit Excel
        exploreObj.whereAreYouGoingClick();
        exploreObj.enterDestination(exploreObj.getValueFromExcel("src/main/resources/airbnb/destinations.xlsx","Cataratas"));
        //exploreObj.takeScreenShot(pathSS,"6-Look for destination whit Excel OK");
        exploreObj.tapBack();
        exploreObj.tapBack();

        //7-Click on Profile
        barraObj.profileButtonClick();
        //barraObj.takeScreenShot(pathSS,"7-Click on Profile OK");

        //8-Verify Profile page
        Assert.assertTrue(profileObj.verifyProfileText());
        //barraObj.takeScreenShot(pathSS,"8-Verify Profile page OK");

        //Swipes
        profileObj.swipeToElement(5, BasePage.Direction.UP);

        //profileObj.takeScreenShot(pathSS,"profile");

        //Find a Restaurant

        //Selección del valor en base a parámetro - Raul

        //Find locations and validate

        //Go to Profile and validate Legal

    }
}
