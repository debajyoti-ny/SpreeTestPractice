package com.shiftedtech.qa.scripts.OtherTutorials.TestNgListeners;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * We can use the TestNgListener class at the class level or at the suite level from the .xml file
 */

//@Listeners(TestNgListener.class)  //No need to use it here if running from the .xml file
public class SampleTest {

    @Test
    public void testPass(){
        System.out.println("This test will pass...");
        Assert.assertTrue(true);
    }

    @Test
    public void testFail(){
        System.out.println("This test will fail...");
        Assert.assertTrue(false);
    }
}
