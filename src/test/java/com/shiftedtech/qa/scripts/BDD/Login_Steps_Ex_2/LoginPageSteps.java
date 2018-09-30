package com.shiftedtech.qa.scripts.BDD.Login_Steps_Ex_2;

import com.shiftedtech.qa.scripts.BDD.Login_Steps_Ex_2.Model.LoginPageModel;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

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

        verifyText(LoginPageModel.ERROR_MSG, "Invalid email or password. XX"); //Intentionally failing the test to see the SS in the report
    }

}
