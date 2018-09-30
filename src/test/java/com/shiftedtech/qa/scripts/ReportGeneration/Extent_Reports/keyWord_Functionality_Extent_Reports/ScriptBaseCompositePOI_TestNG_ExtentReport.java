package com.shiftedtech.qa.scripts.ReportGeneration.Extent_Reports.keyWord_Functionality_Extent_Reports;

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
 * This is used with the Page Object Model (TestNG framework)
 */

@Listeners(ExtentTestNGITestListener.class)
public class ScriptBaseCompositePOI_TestNG_ExtentReport {
    protected WebDriver driver;
    protected HomePage_Extent homePage;
    protected LoginPage_Extent loginPage;

    protected ExtentManager extentManager = null;


    @BeforeClass
    public void beforeClass(){
        System.out.println("*************** Starting Test *****************");

        extentManager = ExtentManager.getInstance();  //Creating the object so that it could be used by the log() method in the "keywordDrivenScriptBase class"
    }

    @BeforeMethod
    @Parameters({"browserName"})
    public void setUp(@Optional(value = "chrome") String browserName) throws Exception {
        if(browserName.equalsIgnoreCase("chrome")) {
            //System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "/src/main/resources/drivers/chromedriver.exe");
            ChromeDriverManager.getInstance().setup();
            driver = new ChromeDriver();
        }else if(browserName.equalsIgnoreCase("firefox")) {
            FirefoxDriverManager.getInstance().setup();
            driver = new FirefoxDriver();
        }else if(browserName.equalsIgnoreCase("ie")) {
            System.setProperty("webdriver.ie.driver",System.getProperty("user.dir") + "/src/main/resources/drivers/IEDriverServer.exe");
            //InternetExplorerDriverManager.getInstance().arch64().setup();
            driver = new InternetExplorerDriver();
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(30,TimeUnit.SECONDS);

        homePage = new HomePage_Extent(driver);
        loginPage = new LoginPage_Extent(driver);

        driver.navigate().to("http://spree.shiftedtech.com");

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
