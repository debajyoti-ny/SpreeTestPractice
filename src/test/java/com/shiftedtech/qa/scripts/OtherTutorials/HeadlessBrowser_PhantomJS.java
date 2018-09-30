package com.shiftedtech.qa.scripts.OtherTutorials;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

public class HeadlessBrowser_PhantomJS {

    @Test
    public void test(){
        System.setProperty("phantomjs.binary.path", System.getProperty("user.dir") + "/src/main/resources/driver/phantomjs.exe");
        WebDriver driver = new PhantomJSDriver();
        driver.navigate().to("http://www.facebook.com");
        String actualTitle = driver.getTitle();
        String expectedTitle = "Facebook - Log In or Sign Up";
        Assert.assertEquals(expectedTitle, actualTitle);
    }
}
