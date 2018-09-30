package com.shiftedtech.qa.scripts.Junit;

import com.shiftedtech.qa.framework.composite_functions.Browser;
import com.shiftedtech.qa.framework.composite_functions.ObjectRepo;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class SpreeLogin_Browser{
    protected static WebDriver driver;
    protected Browser browser;

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

        browser = new Browser(driver);
    }

    @Test
    public void validEmailValidPassword(){
        browser.navigateTo("http://spree.shiftedtech.com/");
        browser.clickElement(ObjectRepo.HOME_LOGIN_LINK);
        browser.typeText(ObjectRepo.EMAIL_BOX, "deba007190@gmail.com");
        browser.typeText(ObjectRepo.PASSWORD_BOX, "deb0119");
        browser.clickElement(By.xpath("//input[@class='btn btn-lg btn-success btn-block']"));
        browser.verifyText(By.xpath("//div[@class='alert alert-success']"), "Logged in successfully");
    }

    @Test
    public void invalidEmailValidPassword(){
        browser.navigateTo(ObjectRepo.SPREE_WEBSITE_URL);
        browser.clickElement(ObjectRepo.HOME_LOGIN_LINK);
        browser.typeText(ObjectRepo.EMAIL_BOX, "deba007190xx@gmail.com");
        browser.typeText(ObjectRepo.PASSWORD_BOX, "deb0119");
        browser.clickElement(ObjectRepo.LOGIN_BUTTON);
        browser.verifyText(ObjectRepo.LOGIN_FAILURE_MSG, "Invalid email or password.");
    }

    @Test
    public void validEmailInvalidPassword(){
        browser.navigateTo(ObjectRepo.SPREE_WEBSITE_URL);
        browser.clickElement(ObjectRepo.HOME_LOGIN_LINK);
        browser.typeText(ObjectRepo.EMAIL_BOX, "deba007190@gmail.com");
        browser.typeText(ObjectRepo.PASSWORD_BOX, "invalid");
        browser.clickElement(ObjectRepo.LOGIN_BUTTON);
        browser.verifyText(ObjectRepo.LOGIN_FAILURE_MSG, "Invalid email or password.");
    }

    @Test
    public void invalidEmailInvalidPassword(){
        browser.navigateTo(ObjectRepo.SPREE_WEBSITE_URL);
        browser.clickElement(ObjectRepo.HOME_LOGIN_LINK);
        browser.typeText(ObjectRepo.EMAIL_BOX, "deba007190xx@gmail.com");
        browser.typeText(ObjectRepo.PASSWORD_BOX, "invalid");
        browser.clickElement(ObjectRepo.LOGIN_BUTTON);
        browser.verifyText(ObjectRepo.LOGIN_FAILURE_MSG, "Invalid email or password.");    }

    @After
    public void tearDown(){
        driver.close();
        browser = null;
    }

    @AfterClass
    public static void afterClass(){
        driver.quit();
    }

    public void delay(int timeInMilliSeconds){
        try {
            Thread.sleep(timeInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
