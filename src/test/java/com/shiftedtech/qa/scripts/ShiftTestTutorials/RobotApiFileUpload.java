package com.shiftedtech.qa.scripts.ShiftTestTutorials;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

public class RobotApiFileUpload {

    @Test
    public void test1() throws Exception {

        Robot robot = new Robot();
        ChromeDriverManager.getInstance().setup();
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.navigate().to("file:///C:/MyDevelopment/SSMB5/newClone/SeleniumReference/pages/fileUpload.html");

        driver.findElement(By.xpath("//input[@id='fu1']")).click();

        robot.setAutoDelay(1000);

        //C:\MyDevelopment\SSMB5\newClone\SeleniumReference\pages\file.txt

        // Copying the file path
        StringSelection stringSelection = new StringSelection("C:\\MyDevelopment\\SSMB5\\newClone\\SeleniumReference\\pages\\file.txt");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

        robot.setAutoDelay(2000);

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);

        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);

        robot.setAutoDelay(2000);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        robot.setAutoDelay(3000);

    }
    


}
