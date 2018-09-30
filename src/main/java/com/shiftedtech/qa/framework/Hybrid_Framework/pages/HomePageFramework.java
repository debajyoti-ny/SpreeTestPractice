package com.shiftedtech.qa.framework.Hybrid_Framework.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageFramework extends PageBaseFramework {

    @FindBy(xpath = "//nav[@id='top-nav-bar']//a")
    private WebElement loginBtn;

    @FindBy(xpath = "//div[@class='alert alert-success']")
    private WebElement signInSuccess;

    public HomePageFramework(){
        //driver = DriverFactory.getInstance().getDriver();  //Used for parallel execution of methods
        super();  // super() instead of super(driver) is used because PageBaseFramework is initiating the driver with DriverFactory
        PageFactory.initElements(driver, this);
    }

    public HomePageFramework(WebDriver driver) {
        super(driver);   //Here the driver is passed as a parameter because parent class's constructor gets invoked as soon as the child class's object is created
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
