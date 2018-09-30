package com.shiftedtech.qa.scripts.BDD.Login_Steps_Ex_3;

import com.shiftedtech.qa.scripts.BDD.Login_Steps_Ex_3.Model.LoginPageModel;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by Debajyoti Paul on 3/8/2018 at 6:35 PM
 */
public class HomePageSteps extends BaseSteps {
    @Then("^Spree Homepage should display$")
    public void spree_Homepage_should_display() throws Throwable {
        String pageTitle = driver.getTitle();
        Assert.assertEquals("Spree Demo Site", pageTitle);
    }

    @When("^User clicks on the login link$")
    public void user_clicks_on_the_login_link() throws Throwable {
//        WebElement loginLink = driver.findElement(By.linkText("Login"));
//        loginLink.click();

        clickBy(LoginPageModel.LOGIN_LINK);
    }

    @Then("^Spree Home page should display$")
    public void home_page_should_display() throws Throwable {
        String pageTitle = driver.getTitle();
        Assert.assertEquals("Spree Demo Site",pageTitle);
    }

    @Then("^Login success message should display$")
    public void login_success_message_should_display() throws Throwable {
//        WebElement loginSuccessLabel = driver.findElement(By.xpath("//div[@id='content']/div[contains(@class,'alert-success')]"));
//        String successText = loginSuccessLabel.getText();
//        Assert.assertEquals("Logged in successfully",successText);

        verifyText(LoginPageModel.SUCCESS_MSG, "Logged in successfully");
    }

//    ************************ Search Box Functionality ************************

    @When("^User type the product name \"([^\"]*)\" in the search box$")
    public void user_type_the_product_name_in_the_search_box(String text) throws Throwable {
        WebElement searchBox = driver.findElement(By.xpath("//input[@id='keywords']"));
        searchBox.sendKeys(text);
    }

    @When("^Clicks the search button$")
    public void clicks_the_search_button() throws Throwable {
        WebElement searchButton = driver.findElement(By.xpath("//input[@class='btn btn-success']"));
        searchButton.click();
    }

}
