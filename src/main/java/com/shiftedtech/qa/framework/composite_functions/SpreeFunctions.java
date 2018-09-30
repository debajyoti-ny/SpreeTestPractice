package com.shiftedtech.qa.framework.composite_functions;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class SpreeFunctions {
    protected WebDriver driver;

    public SpreeFunctions(WebDriver driver){
        this.driver = driver;
    }

    public void loginDetails(String email, String password) {
        WebElement loginBtn = driver.findElement(By.xpath("//nav[@id='top-nav-bar']//a"));
        loginBtn.click();

        WebElement emailBox = driver.findElement(By.id("spree_user_email"));
        emailBox.sendKeys(email);

        WebElement passwordBox = driver.findElement(By.id("spree_user_password"));
        passwordBox.sendKeys(password);

        WebElement login = driver.findElement(By.xpath("//input[@class='btn btn-lg btn-success btn-block']"));
        login.click();
    }

    public void signInSuccessMsg() {
        WebElement signInSuccess = driver.findElement(By.xpath("//div[@class='alert alert-success']"));
        String actualText = signInSuccess.getText();
        String expectedText = "Logged in successfully";
        Assert.assertEquals(expectedText,actualText);
    }

    public void signInFailureMsg() {
        WebElement signInFailure = driver.findElement(By.xpath("//div[@class='alert alert-error']"));
        String actualText = signInFailure.getText();
        String expectedText = "Invalid email or password.";
        Assert.assertEquals(expectedText,actualText);
    }

    public void delay(int timeInMilliSeconds){
        try {
            Thread.sleep(timeInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void highlight(WebElement element){
        for(int i=0; i<2; i++){
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "border: 3px solid red");
            delay(300);
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
            delay(300);
        }
    }

    public void jsClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("var el=arguments[0]; setTimeout(function() {e1.click();}, 100);", element);
    }

    public void actionClick(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().build().perform();
    }

}
