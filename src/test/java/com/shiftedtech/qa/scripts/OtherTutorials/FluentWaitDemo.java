package com.shiftedtech.qa.scripts.OtherTutorials;

import com.google.common.base.Function;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class FluentWaitDemo extends BaseClassOT {

    /**
     * Definition of Fluentwait in selenium webDriver..
     * <p>
     * In simple words, Fluent wait is just a plain class and using this class we can wait until
     * specific conditions are met.
     * In Fluent wait, we can change the default polling period based on our requirement.
     * <p>
     * Technical details
     * 1-Fluent wait is a class and is part of org.openqa.selenium.support.ui Package
     * 2-It is an implementation of Wait interface.
     * 3-Each fluent wait instance defines the maximum amount of time to wait for a condition and we
     * can give the frequency with which to check the condition.
     * 4- We can also ignore any exception while polling element such as NoSuchElement exception in
     * Selenium.
     * <p>
     * Now we have discussed enough on Fluent wait and its usage so let’s talk about the implementation
     * part of it and how we can use in our project or in our framework.
     * <p>
     * We will also get questions in your interviews that what is the difference between explicit wait
     * and Fluent wait.
     * <p>
     * Ans- In explicit wait, you can use some set of existing precondition to wait like
     * Wait till element is not visible, wait till element is not clickable, Wait till presence of
     * element located and so on.
     * <p>
     * In Fluent wait, you can customize the apply method and you can write your own conditions based on your requirement.
     */

    @Test
    public void fluentWait() {

        driver.navigate().to("http://seleniumpractise.blogspot.in/2016/08/how-to-use-explicit-wait-in-selenium.html");

        driver.findElement(By.xpath("//button[text()='Click me to start timer']")).click();

        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(2, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);

        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver arg0) {
                // find the element
                WebElement ele = arg0.findElement(By.xpath("//p[@id='demo']"));

                // Will capture the inner Text and will compare will WebDriver
                // If condition is true then it will return the element and wait will be over

                if (ele.getAttribute("innerHTML").equalsIgnoreCase("WebDriver")) {
                    System.out.println("Value is >>> " + ele.getAttribute("innerHTML"));
                    return ele;
                }
                // If condition is not true then it will return null and it will keep checking until condition is not true
                else {
                    System.out.println("Value is >>> " + ele.getAttribute("innerHTML"));
                    return null;
                }
            }
        });

        // If element is found then it will display the status
        System.out.println("Final visible status is >>>>> " + element.isDisplayed());
    }

    @AfterClass
    public static void tearDown(){
//        driver.close();
        driver.quit();
    }
}

