package com.shiftedtech.qa.scripts.Hybrid_BDD_POM.steps;

import com.shiftedtech.qa.framework.Hybrid_Framework.hooks.BaseSteps_Hybrid;
import com.shiftedtech.qa.scripts.BDD.Login_Steps_Ex.BaseSteps;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

/**
 * Created by Debajyoti Paul on 3/8/2018 at 6:34 PM
 */
public class ApplicationSteps extends BaseSteps_Hybrid {

    @Given("^Not a validated user$")
    public void not_a_validated_user() throws Throwable {
        driver.manage().deleteAllCookies();
    }

    @When("^User browse to the site$")
    public void user_browse_to_the_site() throws Throwable {
        driver.navigate().to("http://spree.shiftedtech.com/");
    }
}
