package pomExampleDemoQA;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class PageDemoQAPage {
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"ui-id-2\"]")
	private WebElement tab2;
	@FindBy(how = How.XPATH, using = "//*[@id=\"ui-id-3\"]")
	private WebElement tab3;
	@FindBy(how = How.XPATH, using = "//*[@id=\"ui-id-4\"]")
	private WebElement tab4;
	@FindBy(how = How.XPATH, using = "//*[@id=\"ui-id-5\"]")
	private WebElement tab5;
	public void clickOn() {
		tab2.click();
		tab3.click();
		tab4.click();
		tab5.click();
	}
}
