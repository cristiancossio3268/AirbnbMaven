package airbnb;

import core.BasePage;
import core.BaseTest;
import core.utils.listeners.AllureListener;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.airbnb.BarraPage;
import pages.airbnb.ProfilePage;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static core.utils.extentreports.ExtentTestManager.startTest;

@Listeners(AllureListener.class)
@Epic("Regression Tests")
@Feature("ProfilePage Tests")
public class TestSuit03Allure extends BaseTest {

    //Class objects
    BarraPage barraObj;
    ProfilePage profileObj;

    LocalDateTime date = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH:mm:ss");
    String formattedLocalDateTime = date.format(formatter);
    String pathSS = "src/main/resources/airbnb/evidences/" + formattedLocalDateTime + "/";


    @Test(priority = 1, description="Scenario Click on Profile", groups = "profile")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Click on Profile")
    @Story("Click")
    public void TC001_clickOnProfile(Method method){

        //Method required for allure
        startTest(method.getName(),"Look for destination whit JSON");

        //Class objects
        barraObj = new BarraPage(getDriver());

        //Verify explore page

        //Click on Profile
        barraObj.profileButtonClick();
    }

    @Test(priority = 1, description="Scenario Verify Profile page", groups = "profile")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify Profile page")
    @Story("Verify")
    public void TC002_verifyProfilePage(Method method){

        //Method required for allure
        startTest(method.getName(),"Look for destination whit JSON");

        //Class objects
        profileObj = new ProfilePage(getDriver());

        //Searching text in Profile page
        profileObj.scrollToText("Account Settings");

        //Verify Profile page
        Assert.assertTrue(profileObj.verifyProfileText(),"Profile page is NOT displayed");
    }

    @Test(priority = 1, description="Scenario Swipe to get bottom of the screen", groups = "profile")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Swipe to get bottom of the screen")
    @Story("Swipe")
    public void TC003_swipeToGetBottomOfTheScreen(Method method){

        //Method required for allure
        startTest(method.getName(),"Look for destination whit JSON");

        //Class objects
        profileObj = new ProfilePage(getDriver());

        //Verify Profile page
        profileObj.scrollToText("Account Settings");
        Assert.assertTrue(profileObj.verifyProfileText(),"Profile page is NOT displayed");

        //Swipe to get bottom of the screen
        profileObj.swipeToElement(3, BasePage.Direction.UP);

    }

    @Test(priority = 1, description="Scenario Go to Profile and validate Legal", groups = "profile")
    @Severity(SeverityLevel.MINOR)
    @Description("Go to Profile and validate Legal")
    @Story("Verify")
    public void TC004_goToProfileAndValidateLegal(Method method){

        //Class objects
        profileObj = new ProfilePage(getDriver());

        //Method required for allure
        startTest(method.getName(),"Look for destination whit JSON");

        //Verify Profile page
        profileObj.scrollToText("Account Settings");
        Assert.assertTrue(profileObj.verifyProfileText(),"Profile page is NOT displayed");

        //Go to Profile and validate Legal
        Assert.assertTrue(profileObj.verifyLegalText(),"Legal on profile page  is NOT displayed");

    }

    @Test(priority = 1, description="Scenario Scroll to exactly text Learn about hosting", groups = "profile")
    @Severity(SeverityLevel.TRIVIAL)
    @Description("Scroll to exactly text Learn about hosting")
    @Story("Scroll")
    public void TC005_scrollToExactlyText(Method method){

        //Method required for allure
        startTest(method.getName(),"Look for destination whit JSON");

        //Class objects
        profileObj = new ProfilePage(getDriver());

        //Verify Profile page
        profileObj.scrollToText("Account Settings");
        Assert.assertTrue(profileObj.verifyProfileText(),"Profile page is NOT displayed");

        //Scroll to exactly text
        profileObj.scrollToText("Learn about hosting");

    }

    @Test(priority = 1, description="Scenario Scroll to partial text money", groups = "profile")
    @Severity(SeverityLevel.TRIVIAL)
    @Description("Scroll to partial text money")
    @Story("Scroll")
    public void TC006_scrollToPartialText(Method method){

        //Method required for allure
        startTest(method.getName(),"Look for destination whit JSON");

        //Class objects
        profileObj = new ProfilePage(getDriver());

        //Verify Profile page
        profileObj.scrollToText("Account Settings");
        Assert.assertTrue(profileObj.verifyProfileText(),"Profile page is NOT displayedx");

        //Scroll to partial text
        profileObj.scrollToPartialText("money");

        Assert.fail("FAILURE ON PURPOSE");

    }

    @Test(priority = 1, description="Scenario Screenshot of Profile bottom page", groups = "profile")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Screenshot of Profile bottom page")
    @Story("Screenshot")
    public void TC007_screenShotOfProfileBottomPage(Method method) throws IOException {

        //Method required for allure
        startTest(method.getName(),"Look for destination whit JSON");

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