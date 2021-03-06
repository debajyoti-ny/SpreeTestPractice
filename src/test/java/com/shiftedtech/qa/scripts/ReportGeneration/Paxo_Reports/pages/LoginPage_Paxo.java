package com.shiftedtech.qa.scripts.ReportGeneration.Paxo_Reports.pages;

import com.paxovision.execution.annotations.LogReport;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage_Paxo extends PageBase {
    @FindBy(id = "spree_user_email")
    private WebElement emailBox;

    @FindBy(id = "spree_user_password")
    private WebElement passwordBox;

    @FindBy(xpath = "//input[@class='btn btn-lg btn-success btn-block']")
    private WebElement login;

    @FindBy(xpath = "//div[@class='alert alert-error']")
    private WebElement signInFailure;

    public LoginPage_Paxo(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @LogReport(name="Login Credentials", description = "Login to to the application")
    public void loginDetails(String email, String password) {
        emailBox.sendKeys(email);
        passwordBox.sendKeys(password);
        login.click();
    }

    @LogReport(name="Login Failure", description = "To verify the failure message after login")
    public void signInFailureMsg() {
        String actualText = signInFailure.getText();
        String expectedText = "Invalid email or password.";
        Assert.assertEquals(expectedText,actualText);
    }
}
