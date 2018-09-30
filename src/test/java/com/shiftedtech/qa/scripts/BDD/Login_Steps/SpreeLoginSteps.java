package com.shiftedtech.qa.scripts.BDD.Login_Steps;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by Debajyoti Paul on 3/8/2018 at 5:32 PM
 */

public class SpreeLoginSteps {

    private WebDriver driver;

    @Before
    public void setUp(){
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @After
    public void tearDown(){
        driver.close();
        driver.quit();
    }

    @Given("^Not a validated user$")
    public void not_a_validated_user() throws Throwable {
        driver.manage().deleteAllCookies();
    }

    @When("^User browse to the site$")
    public void user_browse_to_the_site() throws Throwable {
        driver.navigate().to("http://spree.shiftedtech.com/");
    }

    @Then("^Spree Homepage should display$")
    public void spree_Homepage_should_display() throws Throwable {
        String pageTitle = driver.getTitle();
        Assert.assertEquals("Spree Demo Site", pageTitle);
    }

    @When("^User clicks on the login link$")
    public void user_clicks_on_the_login_link() throws Throwable {
        WebElement loginLink = driver.findElement(By.linkText("Login"));
        loginLink.click();
    }

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

    @Then("^Spree Home page should display$")
    public void home_page_should_display() throws Throwable {
        String pageTitle = driver.getTitle();
        Assert.assertEquals("Spree Demo Site",pageTitle);
    }

    @Then("^Login success message should display$")
    public void login_success_message_should_display() throws Throwable {
        WebElement loginSuccessLabel = driver.findElement(By.xpath("//div[@id='content']/div[contains(@class,'alert-success')]"));
        String successText = loginSuccessLabel.getText();
        Assert.assertEquals("Logged in successfully",successText);
    }

}
