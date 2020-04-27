package login;

import Core.TestBase;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.annotations.Test;

public class EmailLogin extends TestBase {

    @Given("^I have an existing account$")
    @Test (priority = 0)
    public void iHaveAnExistingAccount() {
        System.out.print("TEST -1");
    }

    @Test (priority = 1)
    @When("I submit a registered email along with the correct password")
    public void i_submit_a_registered_email_along_with_the_correct_password() {
        System.out.print("TEST -2");
    }

    @Test (priority = 2)
    @Then("I should get an authentication token")
    public void i_should_get_an_authentication_token() {

        System.out.print("TEST -3");
    }
}
