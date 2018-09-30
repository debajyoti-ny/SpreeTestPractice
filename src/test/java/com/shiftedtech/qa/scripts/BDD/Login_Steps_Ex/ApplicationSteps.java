package com.shiftedtech.qa.scripts.BDD.Login_Steps_Ex;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

/**
 * Created by Debajyoti Paul on 3/8/2018 at 6:34 PM
 */
public class ApplicationSteps extends BaseSteps {

    @Given("^Not a validated user$")
    public void not_a_validated_user() throws Throwable {
        driver.manage().deleteAllCookies();
    }

    @When("^User browse to the site$")
    public void user_browse_to_the_site() throws Throwable {
        driver.navigate().to("http://spree.shiftedtech.com/");
    }
}
