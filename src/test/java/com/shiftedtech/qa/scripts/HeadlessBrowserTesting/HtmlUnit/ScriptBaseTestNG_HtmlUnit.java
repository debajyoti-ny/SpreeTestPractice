package com.shiftedtech.qa.scripts.HeadlessBrowserTesting.HtmlUnit;

import com.shiftedtech.qa.framework.pages.HomePage;
import com.shiftedtech.qa.framework.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

/**
 * This is used with the Page Object Model (TestNG framework)
 */

public class ScriptBaseTestNG_HtmlUnit {
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
    public void setUp(){
//        ChromeDriverManager.getInstance().setup();

//        driver = new ChromeDriver();
//        driver = new FirefoxDriver();
//        driver = new InternetExplorerDriver();
         driver = new HtmlUnitDriver(); //Headless Browser (Runs internally)

//        driver.manage().window().maximize();   // Not being able to use with Firefox at this moment
//        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//        driver.manage().timeouts().setScriptTimeout(45, TimeUnit.SECONDS);

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
