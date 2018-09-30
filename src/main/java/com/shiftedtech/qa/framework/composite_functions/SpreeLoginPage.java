package com.shiftedtech.qa.framework.composite_functions;

import com.shiftedtech.qa.framework.pages.PageBase;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SpreeLoginPage extends PageBase {
    public SpreeLoginPage(WebDriver driver) {
        super(driver);
    }


    public void loginDetails(String email, String password) {
        By emailBoxBy = By.id("spree_user_email");
        WebElement emailBox = waitForElementDisplayed(emailBoxBy, 15);
        emailBox.sendKeys(email);

        By passwordBy = By.id("spree_user_password");
        WebElement passwordBox = waitForElementDisplayed(passwordBy, 15);
        passwordBox.sendKeys(password);

        By loginBy = By.xpath("//input[@class='btn btn-lg btn-success btn-block']");
        WebElement login = waitForElementDisplayed(loginBy, 15);
        login.click();
    }

    public void signInFailureMsg() {
        WebElement signInFailure = waitForElementDisplayed(By.xpath("//div[@class='alert alert-error']"), 15);
        String actualText = signInFailure.getText();
        String expectedText = "Invalid email or password.";
        Assert.assertEquals(expectedText,actualText);
    }

}
