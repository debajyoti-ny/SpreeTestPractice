package com.shiftedtech.qa.scripts.Java_Self_Tutorials;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.Set;

/**
 * Created by Debajyoti Paul on 4/24/2018 at 1:42 AM
 */
    public class Iexplorer {

        public static void main(String[] args) throws InterruptedException {

            WebDriver driver = null;

            System.setProperty("webdriver.ie.driver","C:\\Users\\Public\\jishan\\drivers\\IEDriverServer32.exe" );
            driver = new InternetExplorerDriver();

            //driver.get("http://www.google.com");

            driver.manage().deleteAllCookies();

            driver.get("http://sparshv2/Pages/Home.aspx");
            WebElement element = driver.findElement(By.xpath("//a[@href='https://iscls2apps.ad.infosys.com/hmyplus/aspx/common/HMYLogin.aspx']"));

            Actions action = new Actions(driver);
            action.moveToElement(element).build().perform();

            Thread.sleep(4000);

            driver.findElement(By.xpath("//a[@href='http://iscls2apps/Attendance/aspx/User/AttMainPage.aspx']")).click();


            //-----------------------------------------------------------------//
            Thread.sleep(4000);

            Set<String> window_handles = driver.getWindowHandles();

            System.out.println(window_handles.size());

            String win = window_handles.toArray()[window_handles.size()-1].toString();
            driver.switchTo().window(win);
            Thread.sleep(4000);

            driver.findElement(By.xpath("//a[@id='hypl_3_1']")).click();
        }

}