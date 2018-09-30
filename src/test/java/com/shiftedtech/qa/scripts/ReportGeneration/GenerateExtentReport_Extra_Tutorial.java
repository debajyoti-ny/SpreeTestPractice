package com.shiftedtech.qa.scripts.ReportGeneration;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * We can provide below information to be displayed in the HTML report:
 *
 * Report Title
 * Report Name
 * PIE Chart Visibility
 * PIE Chart Location
 * Document Theme
 * Operating System Name
 * Hostname
 * Environment Name
 * User Name etc…
 *
 * Important points to be remember:
 *
 * ExtentHtmlReporter will create the report file and set the configuration to the report.
 * ExtentReports will set the system/custom information to the report.
 * ExtentTest will log the information in the report.
 * createTest() method of ExtentReports class is the starting point of the test and it will return the ExtentTest object.
 * We need to capture that object into ExtentTest object.
 * Used this reference to log the information into the report.
 * ExtentHtmlReporter object will be used to add the report information like Title, Header and Theme etc..
 * flush() method of ExtentReports will push/write everything to the document.

 */

public class GenerateExtentReport_Extra_Tutorial
{
    protected ExtentHtmlReporter htmlReporter;
    protected ExtentReports extent;
    protected ExtentTest test;

    @BeforeTest  //Runs before the <test> tag in XML (doesn't have to do anything with @Test)
    public void startReport()
    {
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/Other_Extent_Reports.html");
        extent = new ExtentReports();

        extent.setSystemInfo("OS", "Windows 8.1");  //Just providing system info
        extent.setSystemInfo("Host Name", "Home");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User Name", "Debajyoti Paul");

        // Setting the style/format of the Html report
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("AutomationTesting.in Demo Report");
        htmlReporter.config().setReportName("My Own Report");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.DARK);

        extent.attachReporter(htmlReporter); //Attaching the html report to the extent report
    }

    @Test
    public void demoTestPass()
    {
        // Using the ExtentTest class to create the test
        test = extent.createTest("demoTestPass", "This test will demonstrate the PASS test case");
        Assert.assertTrue(true);
    }

    @Test
    public void demoTestFail()
    {
        test = extent.createTest("demoTestFail", "This test will demonstrate the FAIL test case");
        Assert.assertTrue(false);
    }

    @Test
    public void demoTestSkip()
    {
        test = extent.createTest("demoTestSkip", "This test will demonstrate the SKIP test case");
        throw new SkipException("This test case not ready for execution");
    }

    @AfterMethod  //Runs after each @Test methods
    public void getResult(ITestResult result)
    {
        if(result.getStatus() == ITestResult.FAILURE)
        {
            //logs the status(FAIL) and then gets the Test name and display a message with "RED" color
            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" Test case FAILED due to below issues:", ExtentColor.RED));
            test.fail(result.getThrowable());  //prints the Stack trace(reason as to why the test failed)
        }
        else if(result.getStatus() == ITestResult.SUCCESS)
        {
            //logs the status(PASS) and then gets the Test name and display a message with "GREEN" color
            test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
        }
        else
        {
            //logs the status(SKIPPED) and then gets the Test name and display a message with "YELLOW" color
            test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" Test Case SKIPPED", ExtentColor.ORANGE));
            test.skip(result.getThrowable());
        }
    }

    @AfterTest
    public void tearDown()
    {
        extent.flush(); // push/write everything to the document
    }
}
