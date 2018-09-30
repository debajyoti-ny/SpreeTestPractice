package com.shiftedtech.qa.scripts.CrossBrowserTestNg.Parallel_Exec_Methods;

import com.shiftedtech.qa.framework.pages.HomePage;
import com.shiftedtech.qa.framework.pages.LoginPage;
import com.shiftedtech.qa.framework.utils.DriverFactory;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * This class is used for "crossBrowserParallel_Cloud.xml" where it is used to show how
 * the different methods(i.e tests) run in parallel on different browsers simultaneously. So, if
 * thread-count is 6 and there is a class with 3 methods, then the 6 threads will execute the
 * three methods in 2 different browsers at the same time.
 *
 * Here, we have used the concept of ThreadLocal Class because when we execute parallel via
 * methods, then all the threads point to the same global web driver "Driver" at the same time,
 * hence, only the last test get executed whereas the other two fails because of the driver being
 * "null".
 *
 * ThreadLocal class's variables are both global and local at the same time. It simply
 * means that once an object (for example, a Homepage object) is set to the ThreadLocal class,
 * like...      ThreadLocal<Homepage> object1 = new ThreadLocal<>();
 *              object1.set((new Homepage(driver));
 * then "object1" is local to the thread with which it is doing all the activities(say, Thread1).
 * So, if any other thread tries to access "object1" it won't be able to do so. However, all the
 * other methods and classes will be able to see "object1" when performing some operations through
 * Thread1 because it is global to those methods and classes only through Thread1.
 *
 * Internally, the ThreadLocal works as a HashMap, where the "key" is the current thread and "value"
 * is the object (like, Homepage object) we are passing via the "set" method of the ThreadLocal class.
 */

public class ScriptBaseTestNG_CrossBrowser_Methods {
//    protected WebDriver driver;
//    protected HomePage homePage;
//    protected LoginPage loginPage;

    private ThreadLocal<HomePage> homePageThreadLocal = new ThreadLocal<>();
    private ThreadLocal<LoginPage> loginPageThreadLocal = new ThreadLocal<>();

    @BeforeClass
    public void beforeClass(){
        System.out.println("************** Starting Spree Test **************");
    }

    @BeforeMethod
    @Parameters({"browserName"})
    public void setUp(@Optional(value = "chrome") String browserName){
        WebDriver driver = DriverFactory.getInstance(browserName).getDriver();  //DriverFactory Instance has to
                                                                    // be created in PageBase, LoginPage and Homepage as well
//        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(45, TimeUnit.SECONDS);

        homePageThreadLocal.set(new HomePage(driver)); //new HomePage(driver) is an anonymous object of Homepage class
        loginPageThreadLocal.set(new LoginPage(driver)); //new LoginPage(driver) is an anonymous object of LoginPage class

        driver.navigate().to("http://spree.shiftedtech.com/");
    }

    public HomePage homePage(){
        return homePageThreadLocal.get();
    }

    public LoginPage loginPage(){
        return loginPageThreadLocal.get();
    }

    @AfterMethod
    public void tearDown(){
//        driver.close();
        DriverFactory.getInstance().removeDriver();
        homePageThreadLocal.remove();
        loginPageThreadLocal.remove();
    }

    @AfterClass
    public void afterClass(){

    }
}
