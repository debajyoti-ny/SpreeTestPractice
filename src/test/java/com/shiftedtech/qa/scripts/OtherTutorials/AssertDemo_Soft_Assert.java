package com.shiftedtech.qa.scripts.OtherTutorials;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * Hard assert --> When test fails, it stops the execution of the program then and there.
 * Soft assert --> When test fails, it finishes the entire execution of the program and then shows
 * where the test failed.
 */
public class AssertDemo_Soft_Assert {

    @Test
    public void test1(){
        SoftAssert softAssert = new SoftAssert();
        System.out.println("Test 1 started...");
        softAssert.assertEquals(12,14);
        System.out.println("Test 1 finished...");
        softAssert.assertAll();     //have to use this method otherwise it won't work
    }

    @Test
    public void test2(){
        System.out.println("Test 2 started...");
        Assert.assertEquals(12,14);
        System.out.println("Test 2 finished...");
    }
}
