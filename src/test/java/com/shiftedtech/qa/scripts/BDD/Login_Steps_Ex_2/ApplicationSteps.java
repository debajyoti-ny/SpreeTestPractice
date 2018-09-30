package com.shiftedtech.qa.scripts.BDD.Login_Steps_Ex_2;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

/**
 * This class and/or full package is used to show how BDD is executed with "Background" in feature file
 * and also how tests run as per specific tags.
 * Used in this class is the "LoginStepsEx2.feature" file.
 *
 * Also, in this class, it is shown how to add screenshots for failed tests. The method is written in the
 * @AfterMethod method...
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
