package com.shiftedtech.qa.scripts.ReportGeneration.Paxo_Reports;

import com.paxovision.execution.reporter.interceptor.ProxyGenerator;
import com.paxovision.execution.reporter.listener.ReporterTestListener;
import com.paxovision.execution.reporter.service.ReporterService;
import com.shiftedtech.qa.scripts.ReportGeneration.Paxo_Reports.pages.HomePage_Paxo;
import com.shiftedtech.qa.scripts.ReportGeneration.Paxo_Reports.pages.LoginPage_Paxo;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * We need the @Listeners annotation with "ReporterTestListener.class" here...
 * The "ReporterTestListener.class" is written as a part of the Paxo Report Dependency, so, we
 * don't have to manually write them here Unlike the Extent Reporting...
 * P.S: Changes are made in the ScriptBase, Homepage, LoginPage and PageBase(only ReporterService
 * object is created) classes only.
 */

@Listeners({ReporterTestListener.class})
public class ScriptBaseTestNG_PaxoReport {
    protected WebDriver driver;
    protected ReporterService reporter = ReporterService.reporter();
    protected HomePage_Paxo homePage;
    protected LoginPage_Paxo loginPage;

    @BeforeClass
    public void beforeClass(){
        System.out.println("************** Starting Spree Test **************");

        reporter.setReportPath(System.getProperty("user.dir") + "/Paxo_Reports/Html_Reports/");
        reporter.setReportName("Paxo Report");
        reporter.setReportTitle("Spree Login Report");
        reporter.setCreateUniqueFileName(false);  //False - It will over-write the existing reports each time the test is run
    }

    @BeforeMethod
    @Parameters({"browserName"})
    public void setUp(@Optional(value = "chrome") String browserName){   //If no parameter is specified, then chrome will open as default browser
        if(browserName.equalsIgnoreCase("chrome")){
            ChromeDriverManager.getInstance().setup();
            driver = new ChromeDriver();
        }else if(browserName.equalsIgnoreCase("firefox")){
            FirefoxDriverManager.getInstance().setup();
            driver = new FirefoxDriver();
        }else if(browserName.equalsIgnoreCase("ie")){
            System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "/src/main/resources/driver/IEDriverServer32bit.exe");
            driver = new InternetExplorerDriver();
        }else{

        }

        driver.manage().window().maximize();   // Not being able to use with Firefox at this moment
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(45, TimeUnit.SECONDS);

//        homePage = new HomePage_Extent(driver);
//        loginPage = new LoginPage_Extent(driver);

        homePage = ProxyGenerator.getEnhancedObject(HomePage_Paxo.class, driver); //For Paxo-Reporting
        loginPage = ProxyGenerator.getEnhancedObject(LoginPage_Paxo.class, driver); //For Paxo-Reporting
        //Homepage and Loginpage are in this package NOT from "main" because of some modifications that are needed in those classes

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
