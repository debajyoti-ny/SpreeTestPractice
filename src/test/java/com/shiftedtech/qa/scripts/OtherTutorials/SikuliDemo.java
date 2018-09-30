package com.shiftedtech.qa.scripts.OtherTutorials;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * We need to download and add the sikuli jar file to our project (add by going to project structure)
 * File is kept on Sikuli folder. Also, when it runs for the first time, it creates a lib folder and sets
 * an environment variable/path. So, we need to restart the computer in order to run our test successfully.
 */

public class SikuliDemo {

    WebDriver driver = null;

    @BeforeMethod
    public void setUp(){
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(45, TimeUnit.SECONDS);

        driver.navigate().to("http://spree.shiftedtech.com/");
    }

    @Test
    public void test1() throws Exception {
        Screen screen = new Screen();
        Pattern loginLink = new Pattern("./SikuliAPI/LoginLink.JPG");
        Pattern emailBox = new Pattern("./SikuliAPI/EmailBox.JPG");
        Pattern passwordBox = new Pattern("./SikuliAPI/passwordBox.JPG");
        Pattern loginButton = new Pattern("./SikuliAPI/loginButton.JPG");

        screen.wait(loginLink, 10);

        screen.click(loginLink);
        screen.type(emailBox, "debajyoti1990@gmail.com");
        screen.type(passwordBox, "deb0119");
        screen.click(loginButton);
    }
}
