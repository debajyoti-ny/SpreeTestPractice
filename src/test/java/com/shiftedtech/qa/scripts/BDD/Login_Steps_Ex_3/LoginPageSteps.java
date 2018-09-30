package com.shiftedtech.qa.scripts.BDD.Login_Steps_Ex_3;

import com.shiftedtech.qa.scripts.BDD.Login_Steps_Ex_3.Model.LoginPageModel;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

/**
 * Created by Debajyoti Paul on 3/8/2018 at 6:35 PM
 */
public class LoginPageSteps extends BaseSteps {

    @Then("^Spree Login page should display$")
    public void spree_Login_page_should_display() throws Throwable {
        String pageTitle = driver.getTitle();
        Assert.assertEquals("Login - Spree Demo Site", pageTitle);
    }

    @When("^User enter user email as \"([^\"]*)\"$")
    public void user_enter_user_email_as(String email) throws Throwable {
//        WebElement emailElement = driver.findElement(By.id("spree_user_email"));
//        emailElement.sendKeys(email);

        typeText(LoginPageModel.EMAIL_BOX, email);
    }

    @When("^User enter user password as \"([^\"]*)\"$")
    public void user_enter_user_password_as(String password) throws Throwable {
//        WebElement passwordElement = driver.findElement(By.id("spree_user_password"));
//        passwordElement.sendKeys(password);

        typeText(LoginPageModel.PASSWORD_BOX, password);
    }

    @When("^User clicks on login button$")
    public void user_clicks_on_login_button() throws Throwable {
//        WebElement loginElement = driver.findElement(By.xpath("//input[@name='commit']"));
//        loginElement.click();

        clickBy(LoginPageModel.LOGIN_BUTTON);
    }

    @Then("^Login error message should display$")
    public void login_error_message_should_display() throws Throwable {
//        WebElement errorMsg = driver.findElement(By.xpath("//div[@class='alert alert-error']"));
//        String text = errorMsg.getText();
//        Assert.assertEquals("Invalid email or password.", text);

//        verifyText(LoginPageModel.ERROR_MSG, "Invalid email or password. XX"); //Intentionally failing the test to see the SS in the report
        verifyText(LoginPageModel.ERROR_MSG, "Invalid email or password.");  //same as above...just without the "XX" so that the last 3 scenarios could pass
    }

    // The following method is used to show the importance of data table in cucumber
    @When("^User login with invalid email and password$")
    public void user_login_with_invalid_email_and_password(DataTable dataTable) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
        // E,K,V must be a scalar (String, Integer, Date, enum etc)
        List<List<String>> auth = dataTable.raw();
        typeText(LoginPageModel.EMAIL_BOX, auth.get(0).get(0));   //First row(i.e, first list's) first column
        typeText(LoginPageModel.PASSWORD_BOX, auth.get(0).get(1)); //First row(i.e, first list's) second oolumn
        clickBy(LoginPageModel.LOGIN_BUTTON);
    }

    // The following method is used to show the importance of data table as a HashMap in cucumber for the edge cases of the login functionality
    @When("^User login with email and password$")
    public void user_login_with_email_and_password(DataTable dataTable) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
        // E,K,V must be a scalar (String, Integer, Date, enum etc)
        Map<String, String> map = dataTable.asMap(String.class, String.class);
        if(map.containsKey("EMAIL")){
            typeText(LoginPageModel.EMAIL_BOX, map.get("EMAIL"));
        }
        if(map.containsKey("PASSWORD")){
            typeText(LoginPageModel.PASSWORD_BOX, map.get("PASSWORD"));

        }
        clickBy(LoginPageModel.LOGIN_BUTTON);
    }

}
