package com.shiftedtech.qa.scripts.ShiftTestTutorials;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Debajyoti Paul on 3/16/2018 at 10:51 PM
 */
public class UntrustedCertificate {


    WebDriver driver;

    @BeforeMethod
    public void setup(){

        DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver(cap);

        driver.navigate().to("https://www.cacert.org/");

    }

//    @BeforeMethod
//    public void setUp(){
//        //It create firefox profile
//        FirefoxProfile profile = new FirefoxProfile();
//
//        // This will set the true value
//        profile.setAcceptUntrustedCertificates(true);
//
//        // This will open firefox browser using above created profile
//        FirefoxDriverManager.getInstance().setup();
//        driver = new FirefoxDriver(profile);   //Unable to open firefox with a new profile for some reason
//        driver.navigate().to("https://www.cacert.org/");
//    }

//    @BeforeMethod
//    public void setUp(){
//        // Create object of DesiredCapabilities class
//        DesiredCapabilities cap=DesiredCapabilities.internetExplorer();
//
//        // Set ACCEPT_SSL_CERTS  variable to true
//        cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true); //Not working for IE 11
//
//        // Set the driver path
//        System.setProperty("webdriver.ie.driver", "C:\\Users\\DEBAJYOTI007\\IdeaProjects\\SpreeTestPractice\\src\\main\\resources\\driver\\IEDriverServer32bit.exe");
//
//        // Open browser with capability
//        WebDriver driver = new InternetExplorerDriver(cap);
//        driver.navigate().to("https://www.cacert.org/");
//    }

    @Test
    public void test1(){

    }
}
