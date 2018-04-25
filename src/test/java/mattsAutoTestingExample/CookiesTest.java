package mattsAutoTestingExample;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


public class CookiesTest {
	
	
	private WebDriver driver;
    private static final String BASE_URL = "https://bbc.co.uk";
    private static ExtentReports report;
    private static ExtentTest test;
    private Actions mouseDriver;

	 @BeforeClass
	    public static void init() {
		 report = new ExtentReports();
	        String fileName = "CookiesReport" + ".html";
	        String filePath = System.getProperty("user.dir")
	                + File.separatorChar + fileName;
	        report.attachReporter(new ExtentHtmlReporter(filePath));
	        test = report.createTest("MyFirstTest");
	    }
	 
	 
	 @Before
	    public void setUp(){
	        driver = new ChromeDriver();
	        mouseDriver = new Actions(driver);
	        driver.navigate().to(BASE_URL);
	        test.log(Status.INFO, "Browser lunched Successfully");
	    }

	 
	 

	@After
	public void teardown() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
	}

	@Test
	public void cookieTest() {
		
		login();
		createCookie();
		logout();

		driver.get("https://www.google.co.uk/");
		
		loadCookie();
	}

	public void login() {
		
		test = report.createTest("Verify login");

		driver.get(BASE_URL);

		driver.findElement(By.id("idcta-link")).click();
		driver.findElement(By.id("user-identifier-input")).sendKeys("");

	
		driver.findElement(By.id("password-input")).sendKeys("");
		driver.findElement(By.id("submit-button")).click();

		test.log(Status.INFO, "Attempted to login");

		checkLogin();

//		report.endTest(test);
		report.flush();
	}

	public void createCookie() {

		File f;
		BufferedWriter buf = null;

		try {
			f = new File("browser.data");
			f.delete();
			f.createNewFile();
			buf = new BufferedWriter(new FileWriter(f));

			for (Cookie ck : driver.manage().getCookies()) {
				buf.write(ck.getName() + ";" + ck.getValue() + ";" + ck.getDomain() + ";" + ck.getPath() + ";"
						+ ck.getExpiry() + ";" + ck.isSecure());
				buf.newLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (buf != null) {
					buf.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public void logout() {
		test = report.createTest("Verify logout");

		driver.findElement(By.id("idcta-link")).click();
		driver.findElement(By.cssSelector("#orb-modules > div.___iso-html___ > div > div > "
				+ "div.background.background--attenborough > div.u-background-transparent-black > "
				+ "div > div > div > div > nav > ul > li:nth-child(3) > a")).click();

		test.log(Status.INFO, "Attempted to logout");

		checkLogout();

//		report.endTest(test);
		report.flush();
	}

	public void loadCookie() {

		test = report.createTest("Cookie login");

		try {
			File f = new File("browser.data");
			BufferedReader br = new BufferedReader(new FileReader(f));
			String line;

			while ((line = br.readLine()) != null) {
				StringTokenizer str = new StringTokenizer(line, ";");
				while (str.hasMoreTokens()) {
					String name = str.nextToken();
					String value = str.nextToken();
					String domain = str.nextToken();
					String path = str.nextToken();
					Date expiry = null;
					String dt;

					if (!(dt = str.nextToken()).equals("null")) {
						expiry = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy").parse(dt);
					}
					boolean isSecure = new Boolean(str.nextToken()).booleanValue();
					Cookie ck = new Cookie(name, value, domain, path, expiry, isSecure);
					driver.manage().addCookie(ck);
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		test.log(Status.INFO, "Attempted to login with cookie");

		driver.get(BASE_URL);
		checkLogin();

//		report.endTest(test);
		report.flush();
	}

	public void checkLogin() {

		driver.findElement(By.id("idcta-link")).click();
		driver.findElement(By.id("idcta-link")).click();

		String bodyText = driver.findElement(By.tagName("body")).getText();

		if (bodyText.contains("Your account")) {
			test.log(Status.PASS, "Login valid");
		} else {
			test.log(Status.FAIL, "Login failed");
		}
	}

	public void checkLogout() {

		String check = driver.findElement(By.id("idcta-username")).getText();

		if (check.equalsIgnoreCase("Sign in")) {
			test.log(Status.PASS, "Login valid");
		} else {
			test.log(Status.FAIL, "Login failed");
		}
	}

}