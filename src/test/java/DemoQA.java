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

public class DemoQA {

    private WebDriver driver;
//    private static final String BASE_URL = "http://thedemosite.co.uk";
    private static ExtentReports extent;
    private static ExtentTest test;
    private Actions mouseDriver;



    @BeforeClass
    public static void init() {
        extent = new ExtentReports();
        String fileName = "DemoQAReport" + ".html";
        String filePath = System.getProperty("user.dir")
                + File.separatorChar + fileName;
        extent.attachReporter(new ExtentHtmlReporter(filePath));
        test = extent.createTest("MyFirstTest");
    }



    @Before
    public void setUp(){
        driver = new ChromeDriver();
        mouseDriver = new Actions(driver);
//        webDriver.navigate().to("http://www.demoqa.com/");
        test.log(Status.INFO, "Browser lunched Successfully");
    }


    @Test
    public void testTabs() {
      driver.get("http://www.demoqa.com/");
      test.log(Status.INFO, "Website opened Successfully");
      driver.findElement(By.cssSelector("img")).click();
      test.log(Status.INFO, "Clicking on different tabs");
      driver.findElement(By.id("ui-id-2")).click();
      driver.findElement(By.id("ui-id-3")).click();
      driver.findElement(By.id("ui-id-4")).click();
      driver.findElement(By.id("ui-id-5")).click();
      test.log(Status.INFO, "clicking were done Successfully");
      
      
      driver.findElement(By.cssSelector("#menu-item-140 > a")).click();
      WebElement box = driver.findElement(By.xpath("//*[@id=\"draggable\"]/p"));
      mouseDriver.moveToElement(box);
//      mouseDriver.dragAndDropBy(box, 450, 0).pause(1000).dragAndDropBy(box, -450, 200).pause(1000).dragAndDropBy(box, 450, 0).pause(1000).perform();
      mouseDriver.dragAndDropBy(box, 52, 209).perform();
      test.log(Status.INFO, "Box was dragged to a different location successfully");
      
      
      driver.findElement(By.xpath("//*[@id=\"ui-id-2\"]")).click();
      WebElement boxHorizontal=driver.findElement(By.xpath("//*[@id=\"draggabl2\"]"));
      WebElement boxVertical = driver.findElement(By.xpath("//*[@id=\"draggabl\"]/p"));
      mouseDriver.dragAndDropBy(boxVertical,100,100).dragAndDropBy(boxHorizontal,100,100).perform();
      test.log(Status.INFO, "One boxes moved horizentally and the other one vertically");
      
      
      driver.findElement(By.xpath("//*[@id=\"menu-item-141\"]")).click();
      WebElement boxMoveFrom = driver.findElement(By.xpath("//*[@id=\"draggableview\"]"));
      WebElement boxMoveTo = driver.findElement(By.xpath("//*[@id=\"droppableview\"]"));
      mouseDriver.dragAndDrop(boxMoveFrom, boxMoveTo).perform();
      test.log(Status.INFO, "Box has been dragged to the required location successfully");
      
    }
    

    @After
    public void cleanUp(){
        driver.quit();
        test.log(Status.INFO,"Browser closed successfully");
    }

    @AfterClass
    public static void tearDown() {
        extent.flush();
    }

}