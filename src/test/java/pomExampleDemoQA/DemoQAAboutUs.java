package pomExampleDemoQA;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class DemoQAAboutUs {
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"menu-item-158\"]/a")
	private WebElement aboutus;
	@FindBy(how = How.XPATH, using = "//*[@id=\"menu-item-146\"]/a")
	private WebElement datepicker;
	@FindBy(how = How.XPATH, using = "//*[@id=\"ui-id-3\"]")
	private WebElement displaymonthandyear;
	@FindBy(how = How.XPATH, using = "//*[@id=\"datepicker3\"]")
	private WebElement pickdate;
	@FindBy(how = How.XPATH, using = "//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[3]/td[4]/a")
	private WebElement dategoto;
	
	public void aboutUsView() {
		aboutus.click();
		datepicker.click();
		displaymonthandyear.click();
		pickdate.click();
		dategoto.click();
	}

}
