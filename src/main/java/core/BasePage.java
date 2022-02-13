package core;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;

public class BasePage {

    protected AndroidDriver driver;

    //Constructor
    public BasePage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(60L)), this);
    }

    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT;
    }

    //Methods

    /**
     * Wait for element - Explicit wait - ElementNotVisibleException
     * @param locator
     */
    public void waitForElementToBeVisible(MobileBy locator){
        WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Tap back method - teclear botón de volver atras
     */
    public void tapBack() {
        System.out.println("Trying to tap back on device...");
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        System.out.println("Back button tapped.");
    }

    /**
     * Method for read JSON file
     * Retreives value selected
     * @return value
     */
    public String getValueJSON(String path, String param){
        JSONParser parser = new JSONParser();
        String value ="";
        System.out.println("Trying to get value of "+param+"...");
        try {
            Object obj = parser.parse(new FileReader(path));

            // A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
            JSONObject jsonObject = (JSONObject) obj;

            value = (String) jsonObject.get(param);

        } catch (Exception e) {
            System.out.println("Entró al catch");
            e.printStackTrace();
        }
        System.out.println("Value obtanied: "+ value);
        return value;
    }

    /**
     * Method for read Excel file
     * Get value from excel method
     * @param filename - File name
     * @param value - Value to look for
     * @return value to use
     */
    public String getValueFromExcel(String filename, String value){
        try{
            //Getting excel file
            FileInputStream file = new FileInputStream(filename);

            //Getting WorkBook
            //Workbook wb = new HSSFWorkbook(file); - old excel format .xls
            Workbook wb = new XSSFWorkbook(file); //.xlsx

            //Getting sheet
            Sheet sheet = wb.getSheet("hoja");

            //Getting row
            Row row = sheet.getRow(0);

            //Column
            int colNum = 0;
            String valueFromExcel = null;

            //Getting cell
            for(int i = 0; i < row.getLastCellNum(); i++){
                if(row.getCell(i).getStringCellValue().trim().equalsIgnoreCase(value)){
                    colNum = i;
                    System.out.println("Value found!");
                    break;
                }
            }

            row = sheet.getRow(1);
            valueFromExcel = row.getCell(colNum).getStringCellValue();
            if(!valueFromExcel.isEmpty())
                System.out.println("Value from excel: "+valueFromExcel);
            else
                System.out.println("Value NOT found.");
            return valueFromExcel;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method for take screenshots
     * Take screenshot of the page
     * @param name - Name of the file
     * @throws IOException - Exception
     */
    public void takeScreenShot(String path, String name) throws IOException {
        //Screenshots variables
        LocalDateTime date = LocalDateTime.now();
        name =name+"_"+date+".jpg";
        System.out.println("Capturing the snapshot of the page...");
        File srcFiler=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFiler, new File(path+name));
        System.out.println("Snapshot saved!");
    }

    /**
     * Method to swipe on the mobile screen
     * Performs swipe from the center of screen
     *
     * @param dir the direction of swipe
     * @version java-client: 7.3.0
     **/
    public void swipeScreen(Direction dir) {
        System.out.println("swipeScreen(): dir: '" + dir + "'"); // always log your actions

        // Animation default time:
        //  - Android: 300 ms
        //  - iOS: 200 ms
        // Final value depends on your app and could be greater
        final int ANIMATION_TIME = 300; // ms

        final int PRESS_TIME = 300; // ms

        int edgeBorder = 10; // better avoid edges
        PointOption pointOptionStart, pointOptionEnd;

        // init screen variables
        Dimension dims = driver.manage().window().getSize();

        // init start point = center of screen
        pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);

        switch (dir) {
            case DOWN: // center of footer
                pointOptionEnd = PointOption.point(dims.width / 2, dims.height - edgeBorder);
                break;
            case UP: // center of header
                pointOptionEnd = PointOption.point(dims.width / 2, edgeBorder);
                break;
            case LEFT: // center of left side
                pointOptionEnd = PointOption.point(edgeBorder, dims.height / 2);
                break;
            case RIGHT: // center of right side
                pointOptionEnd = PointOption.point(dims.width - edgeBorder, dims.height / 2);
                break;
            default:
                throw new IllegalArgumentException("swipeScreen(): dir: '" + dir + "' NOT supported");
        }

        // execute swipe using TouchAction
        try {
            new TouchAction(driver)
                    .press(pointOptionStart)
                    // a bit more reliable when we add small wait
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                    .moveTo(pointOptionEnd)
                    .release().perform();
        } catch (Exception e) {
            System.err.println("swipeScreen(): TouchAction FAILED\n" + e.getMessage());
            return;
        }

        // always allow swipe action to complete
        try {
            Thread.sleep(ANIMATION_TIME);
        } catch (InterruptedException e) {
            System.err.println("swipeScreen(): TouchAction FAILED\n" + e.getMessage());

        }

    }

    /**
     * Method to swipes to an element
     * @param swipes - # of swipes
     */
    public void swipeToElement(int swipes, Direction dir){
        for (int i = 0; i < swipes; i++){
            swipeScreen(dir);
        }
    }

}
