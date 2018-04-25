package features;

import java.util.Stack;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

public class RegisterPage {
	
	Stack<Object> some;
	
	@Given("^an empty stack$")
	public void given() {
		some = new Stack();

	}
	
	@When("^I push an item into the stack$")
	public void when() {
		some.push("some");
	}
	
	@Then("^the stack contains one item$")
	public void then() {
		some.size();
		
		Assert.assertEquals(1, some.size());
	}
	
	
}
