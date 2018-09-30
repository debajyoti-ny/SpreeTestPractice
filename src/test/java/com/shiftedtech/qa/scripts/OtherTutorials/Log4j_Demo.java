package com.shiftedtech.qa.scripts.OtherTutorials;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.logging.Logger;

/**
 * Created by Debajyoti Paul on 3/21/2018 at 4:32 PM
 */
public class Log4j_Demo {

    WebDriver driver = null;

    // Here we need to create logger instance so we need to pass Class name for
    // which  we want to create log file in my case Google is classname
    Logger loggerObj = Logger.getLogger("Log4j_Demo");


    @BeforeMethod
    public void setUp(){

        // configure log4j properties file
        PropertyConfigurator.configure("Log4j.properties");

        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();

        driver.navigate().to("http://heatclinic.shiftedtech.com/");
        loggerObj.info("Application opened");

    }

    @Test
    public void loginTest() throws InterruptedException {
        WebElement loginLink = driver.findElement(By.xpath("//div[@id='cart_info']/a[@href='/login']"));
        loginLink.click();
        loggerObj.info("Navigate to login page");

        WebElement emailBox = driver.findElement(By.xpath("//input[@name='j_username']"));
        emailBox.sendKeys("debajyoti1990@gmail.com");
        loggerObj.info("Email entered");

        WebElement passwordBox = driver.findElement(By.xpath("//input[@name='j_password']"));
        passwordBox.sendKeys("deb0119");
        loggerObj.info("Password entered");

        WebElement loginBtn = driver.findElement(By.xpath("//input[@value='Login']"));
        loginBtn.click();
        loggerObj.info("Login button clicked");
        Thread.sleep(2000);

        WebElement successMsg = driver.findElement(By.xpath("//div[@id='cart_info']//a[@class='my-account']"));
        String actualMsg = successMsg.getText();
        String expectedMsg = "Debajyoti";
        Assert.assertEquals(expectedMsg,actualMsg);
        loggerObj.info("Login success verified");
        Thread.sleep(2000);
    }
}
