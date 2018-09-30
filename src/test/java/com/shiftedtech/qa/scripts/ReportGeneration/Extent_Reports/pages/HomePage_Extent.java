package com.shiftedtech.qa.scripts.ReportGeneration.Extent_Reports.pages;

import com.shiftedtech.qa.scripts.ReportGeneration.Paxo_Reports.pages.PageBase;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage_Extent extends PageBase {

    @FindBy(xpath = "//nav[@id='top-nav-bar']//a")
    private WebElement loginBtn;

    @FindBy(xpath = "//div[@class='alert alert-success']")
    private WebElement signInSuccess;

    public HomePage_Extent(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void navigateToLoginPage(){
       // reporter.logInfo("NavigateToLoginPage","Navigation to login page by clicking login link");
        extentManager.log("Navigation to login page by clicking login link"); //Takes only 1 parameter
        loginBtn.click();
    }

   // @LogReport(name= "Login Success", description = "To verify the success message after login")
    public void signInSuccessMsg() {
//        String actualText = signInSuccess.getText();  //Not working for headless browsers
        String actualText = signInSuccess.getAttribute("innerHTML");
        String expectedText = "Logged in successfully";
        Assert.assertEquals(expectedText,actualText);
        extentManager.log("To verify the success message after login");

    }
}
