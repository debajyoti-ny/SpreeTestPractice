package com.shiftedtech.qa.scripts.BDD.Login_Steps_Ex_2;

import com.shiftedtech.qa.framework.utils.DriverFactory;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by Debajyoti Paul on 3/8/2018 at 6:35 PM
 */
public class BeforeAfterHook extends BaseSteps {
    @Before
    public void setUp(){
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @After
    public void tearDown(Scenario scenario){
//        driver.close();
//        driver.quit();

        if(scenario.isFailed()){
            System.out.println("Failed: " + scenario.getName());
            byte[] screenshot = ((TakesScreenshot) this.driver).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        }

        DriverFactory.getInstance().removeDriver();
    }

}
