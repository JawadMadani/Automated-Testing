import java.io.File;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReport {

    private WebDriver webDriver;
    private static final String BASE_URL = "http://thedemosite.co.uk";
    private static ExtentReports extent;
    private static ExtentTest test;



    @BeforeClass
    public static void init() {
        extent = new ExtentReports();
        String fileName = "MyReport" + ".html";
        String filePath = System.getProperty("user.dir")
                + File.separatorChar + fileName;
        extent.attachReporter(new ExtentHtmlReporter(filePath));
    }



    @Before
    public void setUp(){
        webDriver = new ChromeDriver();
        webDriver.navigate().to(BASE_URL);
//        webDriver.navigate().to("https://facebook.com");
//        test.log(Status.INFO, "Browser lunched Successfully");
    }


//    @Test
//    public void myFFirstTest() {
//    	webDriver.get("https://www.guerrillamail.com/compose");
//    	
//    	webDriver.findElement(By.xpath("//*[@id=\"send-form\"]/div[2]/div/input[1]")).sendKeys("Anirban.Biswas@qa.com");
//    	webDriver.findElement(By.xpath("//*[@id=\"send-form\"]/div[4]/textarea")).sendKeys("hey");
//    	webDriver.findElement(By.xpath("//*[@id=\"send-form\"]/div[2]/div/input[2]")).sendKeys("heyyyyyyyy");
//    	webDriver.findElement(By.xpath("//*[@id=\"send-button\"]")).click();
//    }
    
    @Test
    public void myFirstTest(){

        SpreadSheetReader sheetReader = new SpreadSheetReader(System.getProperty("user.dir") + "/src/test/java/Example_Spreadsheet.xlsx");
        List<String> row = sheetReader.readRow(1, "Input Data");


        String[] newArray = row.toArray(new String[0]);
        String usernameInput1 = newArray[0];
        String passwordInput1 = newArray[1];


        test = extent.createTest("MyFirstTest");
        test.log(Status.INFO, "My First Test is Starting ");


        WebElement savePage = webDriver.findElement(By.cssSelector("body > div > center > table > tbody > tr:nth-child(2) > td > div > center > table > tbody > tr > td:nth-child(2) > p > small > a:nth-child(6)"));
        savePage.click();

        test.log(Status.INFO, "Clicking on add new user");

        WebElement usernameInput = webDriver.findElement(By.name("username"));
        usernameInput.sendKeys(usernameInput1);

        test.log(Status.INFO, "creating a new user");

        WebElement passwordInput = webDriver.findElement(By.name("password"));
        passwordInput.sendKeys(passwordInput1);

        WebElement saveButton = webDriver.findElement(By.name("FormsButton2"));
        saveButton.click();

        ////////

        WebElement loginPage = webDriver.findElement(By.cssSelector("body > div > center > table > tbody > tr:nth-child(2) > td > div > center > table > tbody > tr > td:nth-child(2) > p > small > a:nth-child(7)"));
        loginPage.click();


        test.log(Status.INFO, "Clicking on login page");

        WebElement loginUsernameInput = webDriver.findElement(By.name("username"));
        loginUsernameInput.sendKeys(usernameInput1);

        WebElement loginPasswordInput = webDriver.findElement(By.name("password"));
        loginPasswordInput.sendKeys(passwordInput1);


        WebElement loginButton = webDriver.findElement(By.name("FormsButton2"));
        loginButton.click();


        /////////

        // this is the successful
        WebElement successful = webDriver.findElement(By.cssSelector("body > table > tbody > tr > td.auto-style1 > big > blockquote > blockquote > font > center > b"));

        String actualresult = successful.getText();
        String expectedresult  = "**Successful Login**";

        if(actualresult.equals(expectedresult)){
            test.log(Status.PASS,"Login Successful");
        }
        else
        {
            test.log(Status.FAIL, "Login Fail");
        }

    }

    @After
    public void cleanUp(){
//        webDriver.quit();
        //test.log(Status.INFO,"Browser closed successfully");
    }

    @AfterClass
    public static void tearDown() {
        extent.flush();
    }

}