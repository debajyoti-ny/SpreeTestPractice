package com.shiftedtech.qa.scripts.OtherTutorials;

import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExplicitWait extends BaseClassOT{

    /**
     * Condition 1- I have a web page which has some login form and after login, it takes a lot
     * of time to load Account page or Home page. This page is dynamic it means sometimes
     * it takes 10 seconds to load the homepage, sometimes its 15 second and so on. In this situation,
     * the Explicit wait can help us which will wait until specific page/page title is not present
     * it will keep waiting.
     *
     * Condition 2- You are working on travel application and you have filled the web form and clicked on
     * submit button. Now you have to wait until complete data is not loaded or specific data is not loaded.
     * In this case, again we can use Explicit wait in which we can give wait till specific or set of
     * elements are not loaded.
     *
     * Condition 3- There are some elements on a web page which are hidden and it will be displayed only
     * when specific conditions get true, so we have to wait until these elements are not visible. In this
     * case, again explicit wait will help in which we can specify wait till the element or elements
     * are not visible.
     */

    /**
     * Implicit wait– It only checks the presence of element on WebPage that’s all if elements are
     * hidden or any other condition then it will not handle and it will fail your script.
     *
     * It is applicable for all the element after initialization.
     *
     * Explicit wait– It has so much capability which we already discussed and it is applicable to the
     * specific element.
     *
     * We have one more wait which is FluentWait which is more advance is nature. In interviews,
     * you will definitely get this questions very frequently that what is the difference between
     * Implicit wait, Explicit wait and Fluent Wait in Selenium WebDriver.
     */


    @Test
    public void explicitWaitTest(){
        // Start application
        driver.navigate().to("http://seleniumpractise.blogspot.in/2016/08/how-to-use-explicit-wait-in-selenium.html");

        // Click on timer button to start
        driver.findElement(By.xpath("//button[text()='Click me to start timer']")).click();

        // Create object of WebDriverWait class and it will wait max of 20 seconds.
        // By default it will accepts in Seconds
        WebDriverWait wait = new WebDriverWait(driver, 20); //It won't work if I put 5 seconds because the element appears after 6 seconds

        // Here we will wait until element is not visible, if element is visible then it will return web element
        // or else it will throw exception
        WebElement element = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='WebDriver']")));

        // if element found then we will use- In this example I just called isDisplayed method
        boolean status = element.isDisplayed();

        // if else condition
        if (status) {
            System.out.println("===== WebDriver is visible ======");
        } else {
            System.out.println("===== WebDriver is not visible ======");
        }
    }

    @AfterClass
    public static void tearDown(){
        driver.quit();
    }

}


