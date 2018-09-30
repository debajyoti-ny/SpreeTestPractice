package com.shiftedtech.qa.scripts.OtherTutorials.ObjectRepo_Config_File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * It is a very common practice in Selenium Automation to use a Configuration/Object_Repo/Properties File.
 * This file could basically have paths to all the necessary driver locations, excel file's locations,
 * application URL (P.S: url should start with http://), etc.
 *
 * We have to create a "config.properties" file and also a "ConfigReader" class to load and read the
 * properties file. Then we can use the config reader class to access all the materials properties file
 * in any of our test cases/frameworks.
 */

public class ConfigDemo {

    WebDriver driver = null;
    ConfigReader configReader = new ConfigReader();

    @BeforeMethod
    public void setUp(){
        System.out.println("********** Starting Test ************");
        System.setProperty("webdriver.chrome.driver", configReader.getChromeDriverPath());
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(45, TimeUnit.SECONDS);

        driver.navigate().to(configReader.getApplicationURL());
    }

    @Test
    public void test1(){

    }
}
