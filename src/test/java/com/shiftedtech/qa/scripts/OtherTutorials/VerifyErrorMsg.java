package com.shiftedtech.qa.scripts.OtherTutorials;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class VerifyErrorMsg extends BaseClassOT{
    @Test
    public void test1(){
        WebElement login = driver.findElement(By.xpath(".//input[@value='Login']"));
        login.click();

        // Way #1
        String actualErrorMsg = driver.findElement(By.xpath(".//span[@class='error']//span")).getText();

        // Way #2
        String actualErrorMsg2 = driver.findElement(By.xpath(".//span[@class='error']//span")).getAttribute("innerHTML");

        String expectedErrorMsg = "The e-mail address and/or password entered do not match our records. Please try again";

        Assert.assertEquals(actualErrorMsg,expectedErrorMsg);

        System.out.println("--------------------------------");

       // Assert.assertEquals(actualErrorMsg2,expectedErrorMsg);

    }

    @AfterClass
    public static void tearDown(){
        driver.quit();
    }
}
