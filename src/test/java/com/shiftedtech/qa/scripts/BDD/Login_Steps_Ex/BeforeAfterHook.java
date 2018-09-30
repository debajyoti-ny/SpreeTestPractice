package com.shiftedtech.qa.scripts.BDD.Login_Steps_Ex;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by Debajyoti Paul on 3/8/2018 at 6:35 PM
 */
public class BeforeAfterHook extends BaseSteps{
    @Before
    public void setUp(){
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @After
    public void tearDown(){
        driver.close();
        driver.quit();
    }

}
