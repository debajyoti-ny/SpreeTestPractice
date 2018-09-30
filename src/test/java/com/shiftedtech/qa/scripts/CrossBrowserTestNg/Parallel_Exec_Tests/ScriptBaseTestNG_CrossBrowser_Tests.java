package com.shiftedtech.qa.scripts.CrossBrowserTestNg.Parallel_Exec_Tests;

import com.shiftedtech.qa.framework.pages.HomePage;
import com.shiftedtech.qa.framework.pages.LoginPage;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * This class is used for "crossBrowser.xml" where it is used to show how the "tests"
 * run on different browsers one after the other. So, it will execute the three (or, two data
 * driven tests) on firefox, then chrome and then IE(as written in the .xml file)
 *
 * This class is also used for "crossBrowserParallel_Classes.xml" where it is used to show how the
 * "tests" run in parallel on different browsers simultaneously. So, if thread-count is 3, then
 * 1 test each for the 3 browsers will execute at the same time and then it will repeat for
 * the following tests.
 * If thread count is 1 then, one test from Firefox will start followed by the other two tests from
 * Firefox and then only it will proceed to Chrome and eventually to IE.
 */

public class ScriptBaseTestNG_CrossBrowser_Tests {
    protected WebDriver driver;
    protected HomePage homePage;
    protected LoginPage loginPage;

    @BeforeClass
    public void beforeClass(){
        System.out.println("************** Starting Spree Test **************");

//        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/driver/chromedriver.exe");
//        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/main/resources/driver/geckodriver.exe");
//        System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "/src/main/resources/driver/IEDriverServer32bit.exe");

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
