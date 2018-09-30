package com.shiftedtech.qa.framework.Hybrid_Framework.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageFramework extends PageBaseFramework {
    @FindBy(id = "spree_user_email")
    private WebElement emailBox;

    @FindBy(id = "spree_user_password")
    private WebElement passwordBox;

    @FindBy(xpath = "//input[@class='btn btn-lg btn-success btn-block']")
    private WebElement login;

    @FindBy(xpath = "//div[@class='alert alert-error']")
    private WebElement signInFailure;

    public LoginPageFramework(){
        //driver = DriverFactory.getInstance().getDriver(); //Used for Method Parallel
        super();  // super() instead of super(driver) is used because PageBaseFramework is initiating the driver with DriverFactory
        PageFactory.initElements(driver, this);
    }

    public LoginPageFramework(WebDriver driver) {
        super(driver);    //Here the driver is passed as a parameter because parent class's constructor gets invoked as soon as the child class's object is created        PageFactory.initElements(driver, this);
    }

    public void typeEmail(String email) {
        emailBox.sendKeys(email);
    }

    public void typePassword(String password){
        passwordBox.sendKeys(password);
    }

    public void clickLoginBtn(){
        login.click();
    }

    public void signInFailureMsg() {
        String actualText = signInFailure.getText();
        String expectedText = "Invalid email or password.";
        Assert.assertEquals(expectedText,actualText);
    }
}
