package com.shiftedtech.qa.framework.composite_functions;

import com.shiftedtech.qa.framework.pages.PageBase;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Browser extends PageBase {

    public Browser(WebDriver driver) {
        super(driver);
    }

    public void navigateTo(String url){
        driver.navigate().to(url);
    }

    public void typeText(By by, String text){
        WebElement element = driver.findElement(by);
        element.clear();
        element.sendKeys(text);
    }

    public void clickElement(By by){
        WebElement element = driver.findElement(by);
        element.click();
    }

    public void verifyText(By by, String expectedText){
        WebElement element = driver.findElement(by);
        String actualText = element.getText();
        Assert.assertEquals(expectedText, actualText);
    }
}
