package airbnb;

import core.BasePage;
import core.BaseTest;
import core.utils.listeners.AllureListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.airbnb.BarraPage;
import pages.airbnb.ProfilePage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Listeners(AllureListener.class)
public class TestSuit03 extends BaseTest {

    //Class objects
    BarraPage barraObj;
    ProfilePage profileObj;

    LocalDateTime date = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH:mm:ss");
    String formattedLocalDateTime = date.format(formatter);
    String pathSS = "src/main/resources/airbnb/evidences/" + formattedLocalDateTime + "/";


    @Test(priority = 1, groups = "profile")
    public void TC001_clickOnProfile(){

        //Class objects
        barraObj = new BarraPage(getDriver());

        //Verify explore page

        //Click on Profile
        barraObj.profileButtonClick();
    }

    @Test(priority = 1, groups = "profile")
    public void TC002_verifyProfilePage(){

        //Class objects
        profileObj = new ProfilePage(getDriver());

        //Searching text in Profile page
        profileObj.scrollToText("Account Settings");

        //Verify Profile page
        Assert.assertTrue(profileObj.verifyProfileText(),"Profile page is NOT displayed");
    }

    @Test(priority = 1, groups = "profile")
    public void TC003_swipeToGetBottomOfTheScreen(){

        //Class objects
        profileObj = new ProfilePage(getDriver());

        //Verify Profile page
        profileObj.scrollToText("Account Settings");
        Assert.assertTrue(profileObj.verifyProfileText(),"Profile page is NOT displayed");

        //Swipe to get bottom of the screen
        profileObj.swipeToElement(3, BasePage.Direction.UP);

    }

    @Test(priority = 1, groups = "profile")
    public void TC004_goToProfileAndValidateLegal(){

        //Class objects
        profileObj = new ProfilePage(getDriver());

        //Verify Profile page
        profileObj.scrollToText("Account Settings");
        Assert.assertTrue(profileObj.verifyProfileText(),"Profile page is NOT displayed");

        //Go to Profile and validate Legal
        Assert.assertTrue(profileObj.verifyLegalText(),"Legal on profile page  is NOT displayed");

    }

    @Test(priority = 1, groups = "profile")
    public void TC005_scrollToExactlyText(){

        //Class objects
        profileObj = new ProfilePage(getDriver());

        //Verify Profile page
        profileObj.scrollToText("Account Settings");
        Assert.assertTrue(profileObj.verifyProfileText(),"Profile page is NOT displayed");

        //Scroll to exactly text
        profileObj.scrollToText("Learn about hosting");

    }

    @Test(priority = 1, groups = "profile")
    public void TC006_scrollToPartialText(){

        //Class objects
        profileObj = new ProfilePage(getDriver());

        //Verify Profile page
        profileObj.scrollToText("Account Settings");
        Assert.assertTrue(profileObj.verifyProfileText(),"Profile page is NOT displayedx");

        //Scroll to partial text
        profileObj.scrollToPartialText("money");

    }

    @Test(priority = 1, groups = "profile")
    public void TC007_screenShotOfProfileBottomPage() throws IOException {

        pathSS = pathSS.replaceAll(":",".");

        //Class objects
        profileObj = new ProfilePage(getDriver());

        //Verify Profile page
        profileObj.scrollToText("Account Settings");
        Assert.assertTrue(profileObj.verifyProfileText(),"Profile page is NOT displayed");

        //Screenshot of Profile bottom page

        profileObj.takeScreenShot(pathSS,"profile_2");

    }
}