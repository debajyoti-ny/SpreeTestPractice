package com.shiftedtech.qa.scripts.ReportGeneration.Paxo_Reports.pages;

import com.paxovision.execution.annotations.LogReport;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage_Paxo extends PageBase {

    @FindBy(xpath = "//nav[@id='top-nav-bar']//a")
    private WebElement loginBtn;

    @FindBy(xpath = "//div[@class='alert alert-success']")
    private WebElement signInSuccess;

    public HomePage_Paxo(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void navigateToLoginPage(){
        reporter.logInfo("NavigateToLoginPage","Navigation to login page by clicking login link");
        loginBtn.click();
    }

    //Instead of using the "logInfo()" method to pass the "name" and "description" field, we can
    // do so by using the "LogReport" annotation

    @LogReport(name= "Login Success", description = "To verify the success message after login")
    public void signInSuccessMsg() {
//        String actualText = signInSuccess.getText();  //Not working for headless browsers
        String actualText = signInSuccess.getAttribute("innerHTML");
        String expectedText = "Logged in successfully";
        Assert.assertEquals(expectedText,actualText);
    }
}
