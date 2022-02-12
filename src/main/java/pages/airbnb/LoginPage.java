package pages.airbnb;

import core.BasePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginPage extends BasePage {

    //Constructor
    public LoginPage(AndroidDriver driver) {
        super(driver);
    }

    //Locators

    //Continue with email button
    @AndroidFindBy (uiAutomator = "new UiSelector().text(\"Continue with Email\")")
    private AndroidElement continueWithEmailBtn;

    //Email TextBox
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Email\")")
    private AndroidElement editTextEmail;

    //Continue button
    @AndroidFindBy (uiAutomator = "new UiSelector().text(\"Continue\")")
    private AndroidElement continueBtn;

    //Password TextBox
    @AndroidFindBy (uiAutomator = "new UiSelector().text(\"Password\")")
    private AndroidElement passwordTbx;


    //Methods

    /**
     * Click continue with email button
     */
    public void clickContinueWithEmail(){
        System.out.println("Trying to click continue with email button...");
        continueWithEmailBtn.click();
        System.out.println("Continue with email button clicked!");
    }

    /**
     * Enter email method
     * @param inputEmail - Email value
     */
    public void completeEditTextEmail(String inputEmail){
        System.out.println("before fill email");
        editTextEmail.sendKeys(inputEmail);
        System.out.println("after complete email");
    }

    /**
     * Click continue button
     */
    public void clickContinue(){
        System.out.println("Trying to click continue button");
        continueBtn.click();
        System.out.println("Continue button clicked!");
    }

    /**
     * Send password method
     * @param password - Password value
     */
    public void sendPassword(String password){
        System.out.println("Trying to enter password...");
        passwordTbx.sendKeys("Siete777Siete888");
        System.out.println("Password value entered!");
    }
}
