package com.shiftedtech.qa.framework.composite_functions;

import com.shiftedtech.qa.framework.pages.PageBase;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SpreeHomePage extends PageBase {

    public SpreeHomePage(WebDriver driver) {
        super(driver);
    }

    public void navigateToLoginPage(){
        By by = By.xpath("//nav[@id='top-nav-bar']//a");
        WebElement loginBtn = waitForElementDisplayed(by, 15);
        loginBtn.click();
    }

    public void signInSuccessMsg() {
        WebElement signInSuccess = waitForElementDisplayed(By.xpath("//div[@class='alert alert-success']"), 15);
        String actualText = signInSuccess.getText();
        String expectedText = "Logged in successfully";
        Assert.assertEquals(expectedText,actualText);
    }
}
