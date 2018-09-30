package com.shiftedtech.qa.scripts.ShiftTestTutorials;

import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class TextBox extends BaseClass {

    @Test
    public void userName(){
        WebElement username = driver.findElement(By.id("username-text"));
        username.sendKeys("debajyoti");
    }

    @Test
    public void passWord(){
        WebElement password = driver.findElement(By.id("password-text"));
        password.sendKeys("deb0119");
    }

    @Test
    public void date(){
        WebElement dateInput = driver.findElement(By.id("date"));
        dateInput.sendKeys("10262017");
    }

    @Test
    public void dateTime(){
        WebElement dateTimeInput = driver.findElement(By.id("datetime-local"));
        dateTimeInput.sendKeys("10262017");
        dateTimeInput.sendKeys(Keys.TAB);
        dateTimeInput.sendKeys("0426PM");

        // To stop the selenium execution for 5 seconds and see what was done before!!
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void number(){
        WebElement numberInput = driver.findElement(By.id("number"));
        for(int i=0; i<100; i++){
            numberInput.sendKeys(Keys.UP);
        }
    }

    @AfterClass
    public static void terminate(){
         driver.close();
         driver.quit();
    }

}
