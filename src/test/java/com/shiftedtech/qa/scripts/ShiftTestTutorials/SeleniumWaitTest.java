package com.shiftedtech.qa.scripts.ShiftTestTutorials;

import com.google.common.base.Function;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
//import java.util.function.Function; //Don't import this one for FluentWait - I spend 7 hours trying to figure out the problem; Take from google...


public class SeleniumWaitTest {

    protected static WebDriver driver = null;
    public static final int DEFAULT_WAIT_TIME = 2000;

    @BeforeClass
    public static void setUp(){
        FirefoxDriverManager.getInstance().setup();
        driver =  new FirefoxDriver();

        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT_TIME, TimeUnit.MILLISECONDS);

        driver.navigate().to("http://seleniumpractise.blogspot.in/2016/08/how-to-use-explicit-wait-in-selenium.html");
    }

    @Test
    // Test to find an element with implicitly Wait Timeout
    public void test1(){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //Changing the global timeout of 2 secs to 10 secs
        WebElement clickMeButton = driver.findElement(By.xpath(".//button[text()='Click me to start timer']"));
        clickMeButton.click();

        WebElement hiddenText = driver.findElement(By.xpath(".//p[text()='WebDriver']"));
        hiddenText.click(); //Won't work without the implicitlyWait Timeout because element won't be visible before 6 seconds

        driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS); //Changing it back to the global time of 2 secs for other tests
    }

    @Test
    // Test to show how the fluent wait works (Other sources)
    public void test2(){
        WebElement clickMeButton = driver.findElement(By.xpath(".//button[text()='Click me to start timer']"));
        clickMeButton.click();

        WebElement element = toShowHowFluentWaitWorks();
        element.click();
    }


    @Test
    // Test to find out an element with Explicitly Wait Timeout
    public void test3() {
        WebElement clickMeButton = driver.findElement(By.xpath(".//button[text()='Click me to start timer']"));
        clickMeButton.click();

        WebElement element =  waitForElement(By.xpath("//p[@id='demo']"), "WebDriver", 60); // Wait 10 secs for the element to be found
        element.click();
    }


    @Test
    // Test to find out an element with Explicitly Wait Timeout (Wait until element is displayed)
    public void test4() {
        WebElement clickMeButton = driver.findElement(By.xpath(".//button[text()='Click me to start timer']"));
        clickMeButton.click();

        WebElement element =  waitForElementDisplayed(By.xpath("//p[@id='demo']"), "WebDriver", 60); // Wait 10 secs for the element to be found
        element.click();
    }

    @Test
    // Test to use in-built selenium methods to wait explicitly and then find the element
    // Test not running (Unable to locate element for some reasons) -- Ask Shift Teacher
    public void test5(){
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS); //Changing the global timeout of 2 secs to 0.1 secs

        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                                        .pollingEvery(1, TimeUnit.SECONDS)
                                        .withTimeout(10, TimeUnit.SECONDS)
                                        .ignoreAll(new ArrayList<Class<? extends Throwable>>(){
                                            {
                                                add(StaleElementReferenceException.class); //add the exceptions into the arraylist
                                                add(NoSuchElementException.class);         //Import from org.selenium... NOT from Java library
                                            }
                                        }).withMessage("Selenium TimeoutException");

        WebElement clickMeButton = driver.findElement(By.xpath("//button[text()='Click me to start timer']"));
        clickMeButton.click();

        WebElement element =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='WebDriver']")));
        element.click();

        driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT_TIME, TimeUnit.MILLISECONDS);

    }

    @Test
    // Test to find out an element with delayTime method(Thread.sleep)

        public void test6() {
        WebElement clickMeButton = driver.findElement(By.xpath(".//button[text()='Click me to start timer']"));
        clickMeButton.click();

        delayTime(10000); //Here, we wait for 10 secs; doesn't matter if it finds the element before
        // (unlike, previous cases, where the script continues as soon as the element is found)
        WebElement hiddenText = driver.findElement(By.xpath(".//p[text()='WebDriver']"));
        hiddenText.click();
    }

    public WebElement toShowHowFluentWaitWorks(){

        driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS); //Changing the global timeout of 2 secs to 0.1 secs

        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
        wait.pollingEvery(1, TimeUnit.SECONDS);
        wait.withTimeout(10, TimeUnit.SECONDS);
        wait.ignoring(NoSuchElementException.class);

        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver arg0)
            {
                WebElement ele = arg0.findElement(By.xpath("//p[@id='demo']"));
                if (ele.getAttribute("innerHTML").equalsIgnoreCase("WebDriver"))
                {
                    System.out.println("Value is >>> " + ele.getAttribute("innerHTML"));
                    return ele;
                }
                else {
                    System.out.println("Value is >>> " + ele.getAttribute("innerHTML"));
                    return null;
                }
            }
        });
        System.out.println("Final visible status is >>>>> " + element.isDisplayed());
        driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT_TIME, TimeUnit.MILLISECONDS); //Changing the global timeout of 2 secs to 0.1 secs

        return element;
    }

    public WebElement waitForElement(final By locator, final String hiddenText, int timeToWaitInSecs){
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS); //Changing the global timeout of 2 secs to 0.1 secs

        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                                .withTimeout(timeToWaitInSecs, TimeUnit.SECONDS)     //Tries to find out the element within given (input by user) time
                                .pollingEvery(100, TimeUnit.MILLISECONDS)   //Looks for the element every 0.1 secs
                                .ignoring(NoSuchElementException.class);            //Ignores the exception if element is not found

        WebElement element = wait.until(new Function<WebDriver, WebElement>() { //The "until" method takes an anonymous function (that takes WebDriver as input and WebElement as output(returns)) as its parameter
            public WebElement apply(WebDriver arg0) {
                WebElement ele = arg0.findElement(locator);
                if (ele.getAttribute("innerHTML").equalsIgnoreCase(hiddenText)){
                    return ele;
                }
                    return null;
            }
        });

        driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT_TIME, TimeUnit.MILLISECONDS); //Change back to default wait Time
        return element;
    }

    public WebElement waitForElementDisplayed(final By locator, final String hiddenText, int timeToWaitInSecs){
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS); //Changing the global timeout of 2 secs to 0.1 secs

        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(timeToWaitInSecs, TimeUnit.SECONDS)     //Tries to find out the element within given (input by user) time
                .pollingEvery(100, TimeUnit.MILLISECONDS)   //Looks for the element every 0.1 secs
                .ignoring(NoSuchElementException.class);            //Ignores the exception if element is not found

        WebElement element = wait.until(new Function<WebDriver, WebElement>() { //The "until" method takes an anonymous function (that takes WebDriver as input and WebElement as output(returns)) as its parameter
            public WebElement apply(WebDriver arg0) {
                WebElement ele = arg0.findElement(locator);
                if (ele.getAttribute("innerHTML").equalsIgnoreCase(hiddenText) && ele != null && ele.isDisplayed() ){  // Waits for the element to be displayed; Previous returns ele if it only exists (doesn't wait for display)
                    return ele;
                }
                    return null;
            }
        });

        driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT_TIME, TimeUnit.MILLISECONDS); //Change back to default wait Time
        return element;
    }

    public void delayTime(int timeInMilli){
        try{
            Thread.sleep(timeInMilli);
        }  catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void tearDown(){
       // driver.close();
        driver.quit();
    }

}
