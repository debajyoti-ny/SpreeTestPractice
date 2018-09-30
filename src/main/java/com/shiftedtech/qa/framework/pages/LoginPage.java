package com.shiftedtech.qa.framework.pages;

import com.shiftedtech.qa.framework.utils.DriverFactory;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends PageBase {
    @FindBy(id = "spree_user_email")
    private WebElement emailBox;

    @FindBy(id = "spree_user_password")
    private WebElement passwordBox;

    @FindBy(xpath = "//input[@class='btn btn-lg btn-success btn-block']")
    private WebElement login;

    @FindBy(xpath = "//div[@class='alert alert-error']")
    private WebElement signInFailure;

    public LoginPage(){
        //driver = DriverFactory.getInstance().getDriver(); //Used for Method Parallel
        super();
        PageFactory.initElements(driver, this);
    }

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void loginDetails(String email, String password) {
        emailBox.sendKeys(email);
        passwordBox.sendKeys(password);
        login.click();
    }

    public void signInFailureMsg() {
        String actualText = signInFailure.getText();
        String expectedText = "Invalid email or password.";
        Assert.assertEquals(expectedText,actualText);
    }
}
