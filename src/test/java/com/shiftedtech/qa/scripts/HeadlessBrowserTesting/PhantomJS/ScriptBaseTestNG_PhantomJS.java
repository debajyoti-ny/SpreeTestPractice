package com.shiftedtech.qa.scripts.HeadlessBrowserTesting.PhantomJS;

import com.shiftedtech.qa.framework.pages.HomePage;
import com.shiftedtech.qa.framework.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

/**
 * In order to use PhantomJS, we need to import the dependency and also download the binary zip
 * file from http://phantomjs.org/download.html and then add the .exe file to the resources folder.
 * Also, we need to set the property as "phantomjs.binary.path".
 */

public class ScriptBaseTestNG_PhantomJS {
    protected WebDriver driver;
    protected HomePage homePage;
    protected LoginPage loginPage;

    @BeforeClass
    public void beforeClass(){
        System.out.println("************** Starting Spree Test **************");

        //Have to do this (unlike Html Unit Driver) or else it won't work...
        System.setProperty("phantomjs.binary.path", System.getProperty("user.dir") + "/src/main/resources/driver/phantomjs.exe");

    }

    @BeforeMethod
    public void setUp(){
//        driver = new HtmlUnitDriver(); //Headless Browser (Runs internally)
        driver = new PhantomJSDriver(); //Internally, the PhantomJS implements the Ghost Driver

//        driver.manage().window().maximize();   // Not being able to use with Firefox at this moment
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(45, TimeUnit.SECONDS);

        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        driver.navigate().to("http://spree.shiftedtech.com/");
    }

    @AfterMethod
    public void tearDown(){
        driver.close();
        homePage = null;
        loginPage = null;
    }

    @AfterClass
    public void afterClass(){
      //  driver.quit();
    }
}
