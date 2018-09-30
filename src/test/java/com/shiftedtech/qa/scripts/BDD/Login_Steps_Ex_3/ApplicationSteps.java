package com.shiftedtech.qa.scripts.BDD.Login_Steps_Ex_3;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

/**
 * This class and/or full package is used to show how BDD is executed with "Background" in feature file
 * and also how tests run as per specific tags.
 * Used in this class is the "LoginStepsEx3.feature" file.
 *
 * In this class, the concept of Data Table is used. The data table is used to take many values(parameters)
 * as a List of Lists. "Scenario 3" is used for data table. However, it will take only 1 set of email and
 * password because that's how our method is written with the "typetext" method. Here, the test will only
 * run once because it can't take the next set of email and password as the parameters. At first, we need
 * to run the test in "dry run" mode and then implement the missing method in the LoginPageSteps class.
 *
 * Next, in order to take multiple sets of emails and passwords, the data table is used with data driven
 * framework. "Scenario Outline 4" is used to show this example. So, the test will run each time for a
 * new set of email and password.
 *
 * Next, the concept of data table as a Map(HashMap) is shown where the "Email" is kept as a Key and "Password"
 * is kept as a value. Using this, we can cover all the edge cases of the login functionality like the
 * "No email but valid password", "Valid email but No password" and "No email with no password" scenarios.
 *
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

    //    ************************ Search Box Functionality ************************

    @Given("^Not a validated search box$")
    public void not_a_validated_search_box() throws Throwable {
        driver.manage().deleteAllCookies();
    }

}
