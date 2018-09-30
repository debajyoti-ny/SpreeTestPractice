package com.shiftedtech.qa.scripts.ShiftTestTutorials;

import org.junit.AfterClass;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class BrowserObjectTest extends BaseClass {

    @Test
    public void test1(){
        driver.get("http://shifttest.shiftedtech.com/components/dropdown_menu");
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        // Selenium will wait 20 seconds and then close if it's not able to find an element.
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        // Selenium will wait 60 seconds for the page to load. If the page fails to load within 60 seconds, it will exit out.
        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
        // Selenium will break the execution of any javascript on the web page after 30 seconds.

        String url = driver.getCurrentUrl();
        System.out.println("URL: " + url);

        String title = driver.getTitle();
        System.out.println("Title: " + title);

        String source = driver.getPageSource();
        System.out.println("Page Source: \n" + source);

    }

    @AfterClass
    public static void teardown(){
        if(driver != null){
            driver.close();
            driver.quit();
        }
    }

    // Method used to delay the execution time
    public void delayTime(int timeInMili){
        try {
            Thread.sleep(timeInMili);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
