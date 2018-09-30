package com.shiftedtech.qa.scripts.OtherTutorials;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class HeadlessBrowser_HtmlUnit {
    @Test
    public void test(){
        WebDriver driver = new HtmlUnitDriver();
        driver.navigate().to("http://www.facebook.com");
        String actualTitle = driver.getTitle();
        String expectedTitle = "Facebook - Log In or Sign Up";
        Assert.assertEquals(expectedTitle, actualTitle);
    }

}
