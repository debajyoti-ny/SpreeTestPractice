package com.shiftedtech.qa.scripts.OtherTutorials;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;

public class ScreenShot extends BaseClassOT {

    @Test
    public void captureScreenShot() throws IOException {
        WebElement username = driver.findElement(By.xpath(".//input[@name='j_username']"));
        username.sendKeys("debajyoti007@gmail.com");

        TakesScreenshot ts = (TakesScreenshot)driver;       // Typecasting (Down Casting) to the TakesScreenshot interface
        File source = ts.getScreenshotAs(OutputType.FILE);  // Getting the SS as a file
        FileUtils.copyFile(source, new File("./src/ScreenShots/heatclinic.png")); //Copying the file from "source" to our preferred "destination"

        System.out.println("ScreenShot taken...");
    }

    @Test
    public void captureSsWithMehod() throws IOException {
        WebElement username = driver.findElement(By.xpath(".//input[@name='j_username']"));
        username.sendKeys("debajyoti007@gmail.com");

        WebElement password = driver.findElement(By.xpath(".//input[@name='j_password']"));
        password.sendKeys("deb0119");

        captureSS(driver, "heatclinicPassword");
    }

    //Method to capture ScreenShot
    public static void captureSS(WebDriver driver, String ssName) throws IOException {
        TakesScreenshot ts = (TakesScreenshot)driver;       // Typecasting (Down Casting) to the TakesScreenshot interface
        File source = ts.getScreenshotAs(OutputType.FILE);  // Getting the SS as a file
        FileUtils.copyFile(source, new File("./src/ScreenShots/" + ssName + ".png")); //Copying the file from "source" to our preferred "destination"

        System.out.println("ScreenShot taken...");
    }

    @AfterClass
    public static void tearDown(){
        driver.quit();
    }
}
