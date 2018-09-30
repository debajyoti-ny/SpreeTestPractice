package com.shiftedtech.qa.framework.scriptbase;

import com.shiftedtech.qa.framework.pages.HomePage;
import com.shiftedtech.qa.framework.pages.LoginPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * This is used with the Page Object Model (TestNG framework)
 */

public class ScriptBaseCompositePOI_TestNG {
    protected WebDriver driver;
    protected HomePage homePage;
    protected LoginPage loginPage;

    @BeforeClass
    public void beforeClass(){
        System.out.println("************** Starting Spree Test **************");

        String driverLocation = System.getProperty("user.dir") + "/src/main/resources/driver/chromedriver.exe";
        File file = new File(driverLocation);
        if(file.exists()){
            System.setProperty("webdriver.chrome.driver", driverLocation);
        }else {
            throw new RuntimeException("Unable to locate driver in " + driverLocation);
        }

    }

    @BeforeMethod
    public void setUp(){
//        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();

        driver.manage().window().maximize();
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
