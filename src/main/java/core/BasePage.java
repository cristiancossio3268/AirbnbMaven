package core;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;

import javax.imageio.ImageIO;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class BasePage {

    public static final String PATH_JSON_DATA = "./src/main/resources/airbnb/testdata/json/";
    public static final String PATH_EXCEL_DATA = "./src/main/resources/airbnb/testdata/excel/";
    public static final String PATH_SCREENSHOTS = System.getProperty("user.dir")+"/test-output/screenshots/";


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
     * Wait for element to be Clickable
     * @param locator
     */
    public void waitForElementToBeClickable(By locator){
        WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Tap back method - teclear bot??n de volver atras
     */
    public void tapBack() {
        System.out.println("Trying to tap back on device...");
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        System.out.println("Back button tapped.");
    }

    /**
     * Remove Keyboard method - M??todo para remover el teclado
     * Hide keyboard method
     */
    public void hideKeyboard() {
        System.out.println("Trying to close the keyboard...");
        driver.hideKeyboard();
        System.out.println("Keyboard is closed!.");
    }

    /**
     * Method for read JSON file
     * Retreives value selected
     * @return value
     */
    @Step("Read JSON file")
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
            System.out.println("Entr?? al catch");
            e.printStackTrace();
        }
        System.out.println("Value obtanied: "+ value);
        return value;
    }


    /**
     * Get Data from JSON file (Directly)
     *
     * @author
     * @param jsonFileObj, jsonKey
     * @return jsonValue
     * @throws FileNotFoundException
     */
    /*
    public String getJSONValue(String jsonFileObj, String jsonKey) throws FileNotFoundException {
        try {

            // JSON Data
            InputStream inputStream = new FileInputStream(PATH_JSON_DATA + jsonFileObj + ".json");
            JSONObject jsonObject = new JSONObject(new JSONTokener(inputStream));

            // Get Data
            String jsonValue = (String) jsonObject.getJSONObject(jsonFileObj).get(jsonKey);
            return jsonValue;

        } catch (FileNotFoundException e) {
            Assert.fail("JSON file is not found");
            return null;
        }
    }
     */

    /**
     * Method for read Excel file
     * Get value from excel method
     * @param filename - File name
     * @param value - Value to look for
     * @return value to use
     */
    @Step("Read Excel file")
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
     * Get Value from Excel for coordinates
     *
     * @author
     * @date 02/18/2019
     */
    public String getCellData(String excelName, int row, int column) {
        try {
            // Reading Data
            FileInputStream fis = new FileInputStream(PATH_EXCEL_DATA + excelName + ".xlsx");
            // Constructs an XSSFWorkbook object
            // notaci??n para evitar warnings que son redundantes.
            @SuppressWarnings("resource")
            // Objeto que manejara todo el archivo de excel, genera el objeto hoja (Sheet)
            Workbook wb = new XSSFWorkbook(fis);
            // Objeto que maneja las hojas del documento exel
            Sheet sheet = wb.getSheetAt(0);
            // Objeto que maneja las filas del archivo excel
            Row rowObj = sheet.getRow(row);
            // Objeto que maneja las celdas del archivo excel
            Cell cell = rowObj.getCell(column);
            // Obtiene el valor de acuerdo al row y column
            String value = cell.getStringCellValue();
            System.out.println(value);
            return value;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e1) {
            e1.printStackTrace();
            return null;
        }
    }

    /**
     * Method for take screenshots
     * Take screenshot of the page
     * @param name - Name of the file
     * @throws IOException - Exception
     */
    @Step("Take screenshots")
    public void takeScreenShot(String path, String name) throws IOException {
        //Screenshots variables
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH:mm:ss");
        String formattedLocalDateTime = date.format(formatter);
        name =name+"_"+formattedLocalDateTime+".png";
        name = name.replaceAll(":",".");

        System.out.println("Capturing the snapshot of the page...");
        File srcFiler=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFiler, new File(path+name));
        System.out.println("Snapshot saved!");
    }

    /**
     * Take screenshot
     *
     * @author
     * @throws IOException
     */
    public String takeScreenshots(String fileName){
        try {
            String pathFileName= PATH_SCREENSHOTS + fileName + ".png";
            Screenshot screenshot = new AShot().takeScreenshot(driver);
            ImageIO.write(screenshot.getImage(), "PNG", new File(pathFileName));
            return pathFileName;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
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
    @Step("Swipes to an element")
    public void swipeToElement(int swipes, Direction dir){
        for (int i = 0; i < swipes; i++){
            swipeScreen(dir);
        }
    }

    /**
     * M??todo para buscar un texto exacto en una interfaz con los swipes necesarios, se le entrga un texto exacto
     * https://appium.io/docs/en/writing-running-appium/tutorial/swipe/android-simple/
     * Android 'UIScrollable' swipe: Simple example
     * Scroll to an element by exact text
     * @param exactText
     */
    @Step("Scroll to exact text")
    public void scrollToText(String exactText){
        System.out.println("Trying to scroll to exact text...");
        try {
            WebElement element = driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()." +
                    "scrollable(true)).scrollIntoView(new UiSelector().text(\"" + exactText + "\"))");
            System.out.println("Element was found!");
        }catch (Exception e){
            System.out.println("Element was not found");
        }
    }

    /**
     * M??todo para buscar parte de un texto en una interfaz con los swipes necesarios, se le entrga parte de un texto
     * https://appium.io/docs/en/writing-running-appium/tutorial/swipe/android-simple/
     * Android 'UIScrollable' swipe: Simple example
     * Scroll to an element by partial text
     * @param partialText
     */
    @Step("Scroll to partial text")
    public void scrollToPartialText(String partialText){
        System.out.println("Trying to scroll to partial text...");
        try {
            WebElement element = driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()." +
                    "scrollable(true)).scrollIntoView(new UiSelector().textContains(\""+partialText+"\"))");
            System.out.println("Element was found!");
        }catch (Exception e){
            System.out.println("Element was not found");
        }
    }

    /**
     * method for execute command in shell
     * @param shellCmd
     */
    public static void executeShellCmd(String shellCmd) {
        try {
            Process process = Runtime.getRuntime().exec(shellCmd);
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in Executing the command " + shellCmd);
        }
    }

    /**
     *
     */
    @Step("Generaci??n de allure report")
    public static void generateAllureReport() {
        String pattern = "dd-MM-yyyy_HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String reportfolder = "allure-report_" + simpleDateFormat.format(new Date());
        executeShellCmd("allure generate allure-results");
        executeShellCmd("mv allure-report " + reportfolder);
        executeShellCmd("cp -R src/main/resources/config/allure-2.9.0 " + reportfolder);
        executeShellCmd("cp src/main/resources/config/open_report_mac.sh " + reportfolder);
        executeShellCmd("cp src/main/resources/config/open_report_windows.bat " + reportfolder);
    }
}