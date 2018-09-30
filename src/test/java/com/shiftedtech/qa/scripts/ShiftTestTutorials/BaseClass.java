package com.shiftedtech.qa.scripts.ShiftTestTutorials;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class BaseClass {

    protected static WebDriver driver = null;
    protected static String mainWindowHandle; //For Window Handle

    @BeforeClass
    public static void setUp(){
        System.out.println("********** Shift Test Project ***********");
        String driverPath = System.getProperty("user.dir");

//        WebDriverManager.getInstance().setup();
//        driver = new InternetExplorerDriver();

        // ************************ Basic Google Chrome Path Settings ************************* //

//        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/driver/chromedriver.exe");
//
//        ChromeDriverManager.getInstance().setup(); //No need for the above line if the use this (This comes from the BoniGarcia WebDriverManager in pom.xml)
//        driver = new ChromeDriver();


        // ************************************************************************************ //

        // ************************ Standard Google Chrome Path Settings ************************* //

//        String chromeDriverLocation = driverPath + "/src/main/resources/driver/chromedriver.exe";
//        System.out.println("Chrome Driver Path/Location: " + chromeDriverLocation);
//
//        File file = new File(chromeDriverLocation);
//        if(file.exists()){
//            System.setProperty("webdriver.chrome.driver", chromeDriverLocation);
//            driver = new ChromeDriver();
//        }
//        else{
//            throw new RuntimeException("Driver not found in " + chromeDriverLocation);
//        }
//
//        driver.manage().window().maximize(); // Firefox doesn't need/supports this
//        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
//        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

        // ************************************************************************************ //

                /* ************ Extra features to play with the browser *********** */

//         driver.manage().window().setSize(new Dimension(400, 600)); // To inc/dec the size of the browser
//         driver.manage().window().setPosition(new Point(400,400)); // To move the browser

//         Dimension dim = driver.manage().window().getSize();
//         System.out.println("Browser Size: " + dim.toString());
//         driver.manage().window().setSize(new Dimension(dim.getWidth()/2, dim.getHeight()/2));
//         driver.manage().window().setPosition(new Point(400,400)); // To move the browser

                /* ************ Extra features to play with the browser *********** */

        // ************************ Standard FireFox Path Settings ************************* //

        String FireFoxDriverLocation = driverPath + "/src/main/resources/driver/geckodriver.exe";
        System.out.println("FireFox Driver Path/Location: " + FireFoxDriverLocation);

        File file = new File(FireFoxDriverLocation);
          if(file.exists()){
              System.setProperty("webdriver.gecko.driver", FireFoxDriverLocation);
              //FirefoxDriverManager.getInstance().setup(); // //No need for the above line if the use this (This comes from the BoniGarcia WebDriverManager in pom.xml)
              driver = new FirefoxDriver();
          }
        else{
            throw new RuntimeException("Driver not found in " + FireFoxDriverLocation);
        }

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);  // Wait for the driver to find the element within 5 secs
        // (if found earlier, proceed... no need to wait for the entire 5 secs)
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS); // Wait for the page to load in 30 secs
        driver.manage().timeouts().setScriptTimeout(45, TimeUnit.SECONDS); // Wait for JS scripts to execute within 45 secs (Default is 30 seconds)

        // ************************************************************************************ //

//        // ************************ WebPage Navigation ************************* //

//         driver.navigate().to("http://shifttest.shiftedtech.com/components/text_box");
//         driver.navigate().to("http://shifttest.shiftedtech.com/components/button");
//         driver.navigate().to("http://shifttest.shiftedtech.com/components/radio_button");
//         driver.navigate().to("http://shifttest.shiftedtech.com/components/check_box");
//         driver.navigate().to("http://shifttest.shiftedtech.com/components/dropdown_menu");
//         driver.navigate().to("http://shifttest.shiftedtech.com/components/table");
//         driver.navigate().to("https://editor.datatables.net/examples/styling/bootstrap.html");
//         driver.navigate().to("http://shifttest.shiftedtech.com/components/windows");
//         driver.navigate().to("http://shifttest.shiftedtech.com/components/alert");
//         driver.navigate().to("http://shifttest.shiftedtech.com/components/iframe");
        driver.navigate().to("http://shifttest.shiftedtech.com/components/hover");

        // ************************************************************************************ //

            // ************************ Getting back to main window ************************* //

//                mainWindowHandle = driver.getWindowHandle();

        // ************************************************************************************ //

    }

}
