package account;

import Core.BaseService;
import Core.TestBase;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.annotations.Test;

public class CreateAccount extends BaseService {
    @Given("^I have a valid name and a job role$")
    @Test(priority = 0)
    public void enterNameAndRole() {
        String name="Pantelis";
        String jobRole="AutomationTester";
        System.out.printf("%nMy name is..: "+ name + " and my job is..: "+ jobRole);
    }

    @When("^I submit a new user application form$")
    @Test(priority = 1)
    public void sumbMitNewAppForm() {
        System.out.printf("%nPOSTING application..");
    }


    @Then("^I should get a unique account id$")
    @Test(priority = 2)
    public void getAccId() {
        System.out.printf("%nMy account id is..: 1234567890");          }


}
