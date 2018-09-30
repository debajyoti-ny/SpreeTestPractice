package com.shiftedtech.qa.scripts.OtherTutorials;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by Debajyoti Paul on 3/21/2018 at 2:56 AM
 */
public class MultipleWindow extends BaseClassOT {

    @Test
    public void test1() throws InterruptedException {
        driver.navigate().to("http://seleniumpractise.blogspot.in/");
        WebElement link = driver.findElement(By.xpath("//a[contains(@href, 'yahoo.com')]"));
        link.click();

        String parentWindow = driver.getWindowHandle();
        Set<String> tabs = driver.getWindowHandles();
        Iterator<String> it = tabs.iterator();
        while (it.hasNext()){
            String nextWindow = it.next();
            if(!nextWindow.contains(parentWindow)){
                driver.switchTo().window(nextWindow);
                Thread.sleep(3000);
                driver.close();
            }

        }

    }
}
