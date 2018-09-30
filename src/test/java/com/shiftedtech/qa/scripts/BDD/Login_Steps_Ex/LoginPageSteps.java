package com.shiftedtech.qa.scripts.BDD.Login_Steps_Ex;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by Debajyoti Paul on 3/8/2018 at 6:35 PM
 */
public class LoginPageSteps extends BaseSteps{

    @Then("^Spree Login page should display$")
    public void spree_Login_page_should_display() throws Throwable {
        String pageTitle = driver.getTitle();
        Assert.assertEquals("Login - Spree Demo Site", pageTitle);
    }

    @When("^User enter user email as \"([^\"]*)\"$")
    public void user_enter_user_email_as(String email) throws Throwable {
        WebElement emailElement = driver.findElement(By.id("spree_user_email"));
        emailElement.sendKeys(email);
    }

    @When("^User enter user password as \"([^\"]*)\"$")
    public void user_enter_user_password_as(String password) throws Throwable {
        WebElement passwordElement = driver.findElement(By.id("spree_user_password"));
        passwordElement.sendKeys(password);
    }

    @When("^User clicks on login button$")
    public void user_clicks_on_login_button() throws Throwable {
        WebElement loginElement = driver.findElement(By.xpath("//input[@name='commit']"));
        loginElement.click();
    }

}
