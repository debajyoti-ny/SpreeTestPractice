package com.shiftedtech.qa.scripts.OtherTutorials;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

public class VerifyTitle extends BaseClassOT {

    @Test
    // To show that test(assertion) passes
    public void test1(){
        String actualPageTitle = driver.getTitle();
        String expectedPageTitle = "Broadleaf Commerce Demo Store - Heat Clinic - Login";

        Assert.assertEquals(actualPageTitle,expectedPageTitle);
        System.out.println("Test completed - Page verified");
    }

    @Test
    // To show that test(assertion) fails
    public void test2(){
        String actualPageTitle = driver.getTitle();
        String expectedPageTitle = "Broadleaf Commerce Demo Store - Heat Clinic ";  // Took out the "- Login" part
        Assert.assertEquals(actualPageTitle,expectedPageTitle);
        System.out.println("Test completed - Page verified");
    }

    @Test
    // To show that test(assertion) passes with contains(String) method
    public void test3(){
        String actualPageTitle = driver.getTitle();
        //String expectedPageTitle = "Broadleaf Commerce Demo Store - Heat Clinic ";  // Took out the "- Login" part
        Assert.assertTrue(actualPageTitle.contains("Broadleaf Commerce Demo Store"));
        System.out.println("Test completed - Page verified");
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
