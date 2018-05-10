package stepDefinitions;

import java.util.Locale;

import com.github.javafaker.Faker;

import cucumber.api.java.en.Then;
import info.seleniumcucumber.methods.BaseTest;

public class UserStepDefinitions implements BaseTest {

	private String firstName;

	Faker faker = new Faker(Locale.UK);

	// enter random email into input field steps
	@Then("^I enter random email address into input field having (.+) \"([^\"]*)\"$")
	public void enter_random_email(String type, String accessName) throws Exception {
		String email = faker.internet().emailAddress();
		miscmethodObj.validateLocator(type);
		inputObj.enterText(type, email, accessName);
	}

	// enter random first name into input field steps
	@Then("^I enter random first name into input field having (.+) \"([^\"]*)\"$")
	public void enter_random_first_name(String type, String accessName) throws Exception {
		firstName = faker.name().firstName();
		System.out.println("<<<<< First name >> : " + firstName);
		miscmethodObj.validateLocator(type);
		inputObj.enterText(type, firstName, accessName);
	}

	// enter random last name into input field steps
	@Then("^I enter random last name into input field having (.+) \"([^\"]*)\"$")
	public void enter_random_last_name(String type, String accessName) throws Exception {
		String lname = faker.name().lastName();
		miscmethodObj.validateLocator(type);
		inputObj.enterText(type, lname, accessName);
	}

	// enter random name into input field steps
	@Then("^I enter random name into input field having (.+) \"([^\"]*)\"$")
	public void enter_random_name(String type, String accessName) throws Exception {
		String uname = faker.bothify("####???");
		miscmethodObj.validateLocator(type);
		inputObj.enterText(type, uname, accessName);
	}

	// enter random password into input field steps
	@Then("^I enter random password into input field having (.+) \"([^\"]*)\"$")
	public void enter_random_password(String type, String accessName) throws Exception {
		String password = faker.internet().password();
		miscmethodObj.validateLocator(type);
		inputObj.enterText(type, password, accessName);
	}

	// enter random non numeric into input field steps
	@Then("^I enter random non numeric into input field having (.+) \"([^\"]*)\"$")
	public void enter_random_non_numeric(String type, String accessName) throws Exception {
		String nonNumeric = faker.letterify("3?0?");
		miscmethodObj.validateLocator(type);
		inputObj.enterText(type, nonNumeric, accessName);
	}

	// enter random amount into input field steps
	@Then("^I enter random amount into input field having (.+) \"([^\"]*)\"$")
	public void enter_random_amount(String type, String accessName) throws Exception {
		String amount = faker.commerce().price(1.00, 10000.00);
		miscmethodObj.validateLocator(type);
		inputObj.enterText(type, amount, accessName);
	}

	// enter organisation name into input field steps
	@Then("^I enter random organisation name into input field having (.+) \"([^\"]*)\"$")
	public void enter_random_organisation(String type, String accessName) throws Exception {
		String organisation = faker.company().name();
		miscmethodObj.validateLocator(type);
		inputObj.enterText(type, organisation, accessName);
	}

	@Then("^element having (.+) \"([^\"]*)\" should have first name$")
	public void element_having_css_should_have_first_name(String type, String accessName) throws Throwable {
		miscmethodObj.validateLocator(type);
		firstName = firstName + "'s Giving Page";
		assertionObj.checkElementText(type, firstName, accessName, true);
	}

}
