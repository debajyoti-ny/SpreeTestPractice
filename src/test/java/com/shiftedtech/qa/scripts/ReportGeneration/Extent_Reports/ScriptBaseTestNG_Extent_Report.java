package com.shiftedtech.qa.scripts.ReportGeneration.Extent_Reports;

import com.shiftedtech.qa.framework.utils.ExtentManager;
import com.shiftedtech.qa.framework.utils.ExtentTestNGITestListener;
import com.shiftedtech.qa.scripts.ReportGeneration.Extent_Reports.pages.HomePage_Extent;
import com.shiftedtech.qa.scripts.ReportGeneration.Extent_Reports.pages.LoginPage_Extent;
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
 * P.S: Changes are made in the ScriptBase, Homepage, LoginPage and PageBase(only object
 * is created) classes only.
 */

@Listeners({ExtentTestNGITestListener.class})
public class ScriptBaseTestNG_Extent_Report {
    protected WebDriver driver;
    protected HomePage_Extent homePage;
    protected LoginPage_Extent loginPage;

    protected ExtentManager extentManager = null;

    @BeforeClass
    public void beforeClass(){
        System.out.println("************** Starting Spree Test **************");
        extentManager = ExtentManager.getInstance();
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

        homePage = new HomePage_Extent(driver);
        loginPage = new LoginPage_Extent(driver);

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
