package com.shiftedtech.qa.scripts.ShiftTestTutorials;

import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * Created by Debajyoti Paul on 3/16/2018 at 10:33 PM
 */
public class Drag_n_Drop extends BaseClass {

    @Test
    public void test1(){
        driver.navigate().to("http://demo.guru99.com/test/drag_drop.html");
        WebElement from = driver.findElement(By.xpath("//li[@id='credit2']/a"));
        WebElement to = driver.findElement(By.xpath("//ol[@id='bank']/li"));

        Actions actions = new Actions(driver);
        actions.dragAndDrop(from, to).build().perform();
        delay(2000);

    }

    public void delay(int timeInMilli){
        try {
            Thread.sleep(timeInMilli);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

}
