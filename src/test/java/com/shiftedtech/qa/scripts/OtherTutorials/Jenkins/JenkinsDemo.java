package com.shiftedtech.qa.scripts.OtherTutorials.Jenkins;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by Debajyoti Paul on 3/23/2018 at 2:33 PM
 */
public class JenkinsDemo {

    WebDriver driver = null;

    @BeforeMethod
    public void setUp(){
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(45, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test
    public void test1(){
        driver.navigate().to("https://www.google.com/");
        String title = driver.getTitle();
        Assert.assertEquals("Google", title);
    }
}
