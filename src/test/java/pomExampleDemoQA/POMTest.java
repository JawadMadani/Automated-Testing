package pomExampleDemoQA;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class POMTest {
	
	private WebDriver driver;
	
	 @Given("^go to the Demo Site$")
	 public void goToSite() {
	 driver = new ChromeDriver();
//	 driver.get("http://bing.com/");
	  }
	 
	 @When("^the page is launched$")
	 public void bingSearch() {
		 driver.get("https://bing.com/");
		 BingPage bingSearch = PageFactory.initElements(driver, BingPage.class);
		 bingSearch.searchFor("Java 8");
	 }
	 
	 @When("^this page is also launched$")
	 public void demoQAClicks() {
		 driver.get("http://demoqa.com/");
		 PageDemoQAPage demoQA = PageFactory.initElements(driver, PageDemoQAPage.class);
		 demoQA.clickOn();
		 
		 DemoQAAboutUs demoQAAboutUs = PageFactory.initElements(driver, DemoQAAboutUs.class);
		 demoQAAboutUs.aboutUsView();
	 }
	 
	 

	@Then("^quit the page$")
	public void check() {
//		driver.quit();
	}

}
