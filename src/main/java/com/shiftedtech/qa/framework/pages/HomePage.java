package com.shiftedtech.qa.framework.pages;

import com.shiftedtech.qa.framework.utils.DriverFactory;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends PageBase {

    @FindBy(xpath = "//nav[@id='top-nav-bar']//a")
    private WebElement loginBtn;

    @FindBy(xpath = "//div[@class='alert alert-success']")
    private WebElement signInSuccess;

    public HomePage(){
        //driver = DriverFactory.getInstance().getDriver();  //Used for parallel execution of methods
        super();
        PageFactory.initElements(driver, this);
    }

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void navigateToLoginPage(){
        loginBtn.click();
    }

    public void signInSuccessMsg() {
//        String actualText = signInSuccess.getText();  //Not working for headless browsers
        String actualText = signInSuccess.getAttribute("innerHTML");
        String expectedText = "Logged in successfully";
        Assert.assertEquals(expectedText,actualText);
    }
}
