package hariKrishna.stepdefinition;

import java.io.IOException;

import org.testng.Assert;

import hariKrishna.TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class Stepdefinition extends BaseTest{
	
	@Given("I Landed on Website")
	public void I_Landed_on_Website() throws IOException {
		landngPage();
	}
	
	@Given("I gave {string} and {string}")
	public void I_gave_user_and_pass(String user, String pass) {
		loginPage.login(user, pass);
	}
	
	@Given("^I given (.+) and (.+)$")
	public void I_given_user_and_pass(String user, String pass) {
		loginPage.login(user, pass);
	}
	
	@Then("I validate the outcomes {string}")
	public void I_validate_the_outcomes_message(String message) {
		Assert.assertEquals(message, loginPage.getError());
		close();
	}
	

}
