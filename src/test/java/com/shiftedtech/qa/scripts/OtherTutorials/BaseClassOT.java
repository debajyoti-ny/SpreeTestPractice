package com.shiftedtech.qa.scripts.OtherTutorials;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class BaseClassOT {
    protected static WebDriver driver;

    @BeforeClass
    public static void setUp(){
        System.out.println("**************** Starting Test ****************");
        String driverPath = System.getProperty("user.dir");

        String firefoxDriverLocation = driverPath + "/src/main/resources/driver/geckodriver.exe";
//        String chromeDriverLocation = driverPath + "/src/main/resources/driver/chromedriver.exe";

        File file = new File(firefoxDriverLocation);
        if(file.exists()){
            System.setProperty("webdriver.gecko.driver", firefoxDriverLocation);
            //driver = new FirefoxDriver();
        } else{
            throw new RuntimeException("Driver not found in: " + firefoxDriverLocation);
        }

        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
//        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

        driver.navigate().to("https://heatclinic.shiftedtech.com/login");

    }

    public void delay(int timeInMilli){
        try {
            Thread.sleep(timeInMilli);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
