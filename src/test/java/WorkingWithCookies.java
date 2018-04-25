import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class WorkingWithCookies {

    private WebDriver driver;
    private static final String BASE_URL = "https://bbc.co.uk";
    private static ExtentReports extent;
    private static ExtentTest test;
    private Actions mouseDriver;



    @BeforeClass
    public static void init() {
        extent = new ExtentReports();
        String fileName = "CookiesReport" + ".html";
        String filePath = System.getProperty("user.dir")
                + File.separatorChar + fileName;
        extent.attachReporter(new ExtentHtmlReporter(filePath));
        test = extent.createTest("MyFirstTest");
    }



    @Before
    public void setUp(){
        driver = new ChromeDriver();
        mouseDriver = new Actions(driver);
        driver.navigate().to(BASE_URL);
        test.log(Status.INFO, "Browser lunched Successfully");
    }


    @Test
    public void testTabs() {
    	
      
      
    }
    

    @After
    public void cleanUp(){
//        webDriver.quit();
        test.log(Status.INFO,"Browser closed successfully");
    }

    @AfterClass
    public static void tearDown() {
        extent.flush();
    }

}