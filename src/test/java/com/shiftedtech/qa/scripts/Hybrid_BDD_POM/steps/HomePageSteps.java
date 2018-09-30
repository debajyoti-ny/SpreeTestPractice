package com.shiftedtech.qa.scripts.Hybrid_BDD_POM.steps;

import com.shiftedtech.qa.framework.Hybrid_Framework.hooks.BaseSteps_Hybrid;
import com.shiftedtech.qa.framework.Hybrid_Framework.pages.HomePageFramework;
import com.shiftedtech.qa.framework.Hybrid_Framework.pages.LoginPageFramework;
import com.shiftedtech.qa.scripts.BDD.Login_Steps_Ex.BaseSteps;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by Debajyoti Paul on 3/8/2018 at 6:35 PM
 */
public class HomePageSteps extends BaseSteps_Hybrid {

    HomePageFramework homePage = new HomePageFramework();

    @Then("^Spree Homepage should display$")
    public void spree_Homepage_should_display() throws Throwable {
//        String pageTitle = driver.getTitle();
//        Assert.assertEquals("Spree Demo Site", pageTitle);

        homePage.verifyPageTitle("Spree Demo Site");
    }

    @When("^User clicks on the login link$")
    public void user_clicks_on_the_login_link() throws Throwable {
//        WebElement loginLink = driver.findElement(By.linkText("Login"));
//        loginLink.click();

        homePage.navigateToLoginPage();
    }

    @Then("^Login success message should display$")
    public void login_success_message_should_display() throws Throwable {
//        WebElement loginSuccessLabel = driver.findElement(By.xpath("//div[@id='content']/div[contains(@class,'alert-success')]"));
//        String successText = loginSuccessLabel.getText();
//        Assert.assertEquals("Logged in successfully",successText);

        homePage.signInSuccessMsg();
    }

}
