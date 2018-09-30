package com.shiftedtech.qa.framework.scriptbase;

import com.shiftedtech.qa.framework.composite_functions.SpreeFunctions;
import com.shiftedtech.qa.framework.composite_functions.SpreeHomePage;
import com.shiftedtech.qa.framework.composite_functions.SpreeLoginPage;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * This is used with the "SpreeHomePage and SpreeLoginPage class"
 */

public class ScriptBaseComposite_Extra {
    protected static WebDriver driver;
    protected SpreeHomePage homePage;
    protected SpreeLoginPage loginPage;

    @BeforeClass
    public static void beforeClass(){
        System.out.println("************** Starting Spree Test **************");

        String driverLocation = System.getProperty("user.dir") + "/src/main/resources/driver/chromedriver.exe";
        File file = new File(driverLocation);
        if(file.exists()){
            System.setProperty("webdriver.chrome.driver", driverLocation);
        }else {
            throw new RuntimeException("Unable to locate driver in " + driverLocation);
        }

    }

    @Before
    public void setUp(){
//        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(45, TimeUnit.SECONDS);

        homePage = new SpreeHomePage(driver);
        loginPage = new SpreeLoginPage(driver);
        driver.navigate().to("http://spree.shiftedtech.com/");
    }

    @After
    public void tearDown(){
        driver.close();
        homePage = null;
        loginPage = null;
    }

    @AfterClass
    public static void afterClass(){
        driver.quit();
    }
}
