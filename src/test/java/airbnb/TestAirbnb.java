package airbnb;

import core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.airbnb.BarraPage;
import pages.airbnb.ExplorePage;
import pages.airbnb.LoginPage;
import pages.airbnb.ProfilePage;

public class TestAirbnb extends BaseTest {

    @Test
    public void testAirbnbApp() throws InterruptedException {

        //Class objects
        LoginPage loginObj = new LoginPage(getDriver());
        ExplorePage exploreObj = new ExplorePage(getDriver());
        BarraPage barraObj = new BarraPage(getDriver());
        ProfilePage profileObj = new ProfilePage(getDriver());

        //Code

        //1-Continue with email
        loginObj.clickContinueWithEmail();

        //2-Enter credentials
        //loginObj.completeEditTextEmail("ivan.rivas@cooltesters.com");
        loginObj.completeEditTextEmail("criss.appium@gmail.com");
        loginObj.clickContinue();
        Thread.sleep(6000);

        loginObj.sendPassword("Siete777Siete888");
        loginObj.clickContinue();

        //3-Captcha
        Thread.sleep(50000);

        //4-Verify explore page
        //Assert.assertTrue(exploreObj.);

        //5-Click on Profile
        barraObj.profileButtonClick();

        //6-Verify Profile page
        Assert.assertTrue(profileObj.verifyProfileText());

    }
}
