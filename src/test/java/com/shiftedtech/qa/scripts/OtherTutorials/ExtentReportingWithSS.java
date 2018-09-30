package com.shiftedtech.qa.scripts.OtherTutorials;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 * This class isn't working... Need to fix it later
 */

public class ExtentReportingWithSS{

    ExtentReports report;       //Import from RelevantCodes... Not from AventStack
    ExtentTest logger;          //Import from RelevantCodes... Not from AventStack
    WebDriver driver;

    @Test
    public void verifyPageTitle()
    {
        report=new ExtentReports("./src/ExtentReports/Reports.html");

        logger=report.startTest("VerifyBlogTitle");

        ChromeDriverManager.getInstance().setup();
        driver=new ChromeDriver();

        driver.manage().window().maximize();

        logger.log(LogStatus.INFO, "Browser started... ");

        driver.get("http://www.learn-automation.com");

        logger.log(LogStatus.INFO, "Application is up and running...");

        String title=driver.getTitle();

        Assert.assertTrue(title.contains("Google"));

        logger.log(LogStatus.PASS, "Title verified");

    }


    @AfterMethod
    public void tearDown(ITestResult result) throws Exception
    {
        if(result.getStatus()== ITestResult.FAILURE)
        {
            ScreenShot.captureSS(driver, result.getName());
        }

        report.endTest(logger);
        report.flush();
    }

}

