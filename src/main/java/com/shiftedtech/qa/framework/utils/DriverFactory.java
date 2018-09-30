package com.shiftedtech.qa.framework.utils;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Thread Local is a special class in Java which is used to store objects based on Threads.
 * So, every time a Thread calls the method to create an Object, this class will check and see if
 * there exists any objects for that thread... If the object is not there, then it creates an object
 * and supplies it to the respective thread.
 *
 * We know that there are 3 types of scope in Java --
 * 1) Local (Variable inside methods)
 * 2) Instance (Variable outside of the methods)
 * 3) Static (Class level variable)
 *
 *    The 4th one is --
 * 4) ThreadLocal (Variables specific to one thread)
 *
 * So, an object of ThreadLocal could be global or local to a particular thread (in which it was created)
 * but if any other thread, say Thread 2, tries to access it, then it won't be given access to the threadLocal
 * variable/object created in Thread 1.
 *
 * Internally, ThreadLocal works as a HashMap (key - value) pair. Since, the key in HashMap cannot be
 * duplicated, so, it guarantees that object created in ThreadLocal class is only one.
 *
 */

public class DriverFactory {

    private static DriverFactory instance = null;

    public static final String USERNAME = "debajyotipaul1";
    public static final String AUTOMATE_KEY = "UuAM1bBykFHmyTsF4efK";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    // For running on grid with Shift's server -- Node as my computer(local machine) and hub as shift's server
//     public static final String LOCAL_GRID_URL = "http://10.10.20.70:4444/wd/hub";

    // For running on grid with my own computer -- Both the Node & Hub works from my computer(local machine)
    public static final String LOCAL_GRID_URL = "http://192.168.1.4:4444/wd/hub";

    private DriverFactory() {
        //Do-nothing..Do not allow to initialize this class from outside
    }

    public static DriverFactory getInstance()
    {
        if(instance == null){
            instance = new DriverFactory();   //Singleton design pattern
        }
        return instance;
    }

    public static DriverFactory getInstance(String browserName)
    {
        if(instance == null) {
            instance = new DriverFactory();
        }

        if(instance.driver.get() == null) {
            if (browserName.equalsIgnoreCase("chrome")) {
                ChromeDriverManager.getInstance().setup();
                instance.driver.set(new ChromeDriver());
            }
            else if (browserName.equalsIgnoreCase("firefox")) {
                FirefoxDriverManager.getInstance().setup();
                instance.driver.set(new FirefoxDriver());
            }
            else if (browserName.equalsIgnoreCase("cloud_win8.1_chrome_64")) {
                DesiredCapabilities caps = new DesiredCapabilities();
                caps.setCapability("browser", "Chrome");
                caps.setCapability("browser_version", "64.0");
                caps.setCapability("os", "Windows");
                caps.setCapability("os_version", "8.1");
                caps.setCapability("resolution", "1920x1080");

                try {
                    instance.driver.set(new RemoteWebDriver(new URL(URL), caps));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
            else if (browserName.equalsIgnoreCase("cloud_win8.1_firefox_54")) {
                DesiredCapabilities caps = new DesiredCapabilities();
                caps.setCapability("browser", "Firefox");
                caps.setCapability("browser_version", "54.0");
                caps.setCapability("os", "Windows");
                caps.setCapability("os_version", "8.1");
                caps.setCapability("resolution", "1920x1080");

                try {
                    instance.driver.set(new RemoteWebDriver(new URL(URL), caps));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
            else if (browserName.equalsIgnoreCase("cloud_win8.1_ie_11")) {
                DesiredCapabilities caps = new DesiredCapabilities();
                caps.setCapability("browser", "IE");
                caps.setCapability("browser_version", "11.0");
                caps.setCapability("os", "Windows");
                caps.setCapability("os_version", "8.1");
                caps.setCapability("resolution", "1920x1080");

                try {
                    instance.driver.set(new RemoteWebDriver(new URL(URL), caps));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
            else if (browserName.equalsIgnoreCase("grid_chrome_32")) {
                DesiredCapabilities caps = new DesiredCapabilities();
                caps.setPlatform(Platform.ANY);
                caps.setBrowserName("chrome");

                try {
                    instance.driver.set(new RemoteWebDriver(new URL(LOCAL_GRID_URL), caps));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
            else if (browserName.equalsIgnoreCase("grid_firefox_32")) {
                DesiredCapabilities caps = new DesiredCapabilities();
                caps.setPlatform(Platform.ANY);
                caps.setBrowserName("firefox");

                try {
                    instance.driver.set(new RemoteWebDriver(new URL(LOCAL_GRID_URL), caps));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
            else if (browserName.equalsIgnoreCase("grid_ie_32")) {
                DesiredCapabilities caps = new DesiredCapabilities();
                caps.setPlatform(Platform.ANY);
                caps.setBrowserName("internet explorer");

                try {
                    instance.driver.set(new RemoteWebDriver(new URL(LOCAL_GRID_URL), caps));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        }
        return instance;
    }

    ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>()
    {
        // thread local driver object for webDriver
        @Override
        protected WebDriver initialValue()
        {
//            ChromeDriverManager.getInstance().setup();
//            return new ChromeDriver();
            return null;  //Have to make it null to run on cloud/grid, otherwise it runs on chrome by default
        }
    };

    public WebDriver getDriver() // call this method to get the driver object and launch the browser
    {
        return driver.get();
    }

    public void removeDriver() // Quits the driver and closes the browser
    {
        driver.get().quit();
        driver.remove();
    }
}
