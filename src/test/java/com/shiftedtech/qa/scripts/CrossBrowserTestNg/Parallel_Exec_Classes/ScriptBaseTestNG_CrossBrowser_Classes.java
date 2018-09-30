package com.shiftedtech.qa.scripts.CrossBrowserTestNg.Parallel_Exec_Classes;

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
 * This class is used for "crossBrowserParallel_Classes.xml" where it is used to show how
 * the different classes run in parallel on different browsers simultaneously. So, if
 * thread-count is 1, then 1 class will execute for the firefox browsers then it will move to
 * the next class for FF and then will be followed by chrome(3 classes one by one) and then IE.
 *
 * If thread count is 3(same happened when done with 6), then, all classes (i.e 3 classes)
 * will start for  Firefox and then it will move proceed to Chrome and eventually to IE. This happens
 * because when .xml file is ran then testNG creates 3 different threads and all those threads
 * create individual WebDriver instances (object of FF).
 */

public class ScriptBaseTestNG_CrossBrowser_Classes {
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
