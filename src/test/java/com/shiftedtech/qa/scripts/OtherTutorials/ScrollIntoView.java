package com.shiftedtech.qa.scripts.OtherTutorials;

import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class ScrollIntoView extends BaseClassOT {

    @Test
    public void scrollElement(){
        driver.navigate().to("http://manos.malihu.gr/repository/custom-scrollbar/demo/examples/complete_examples.html");
        WebElement element = driver.findElement(By.xpath("(.//div[@id='mCSB_3_container']/p/input)[last()]"));

        JavascriptExecutor js = (JavascriptExecutor)driver;
//        js.executeScript("scroll(0,400)");  //It will scroll 0px horizontally and 400px vertically(down)
        js.executeScript("arguments[0].scrollIntoView(true);", element);

        element.clear();
        element.sendKeys("Debajyoti");

        delay(3000);

    }

    @AfterClass
    public static void tearDown(){
        driver.quit();
    }

    public void delay(int timeInMilli){
        try {
            Thread.sleep(timeInMilli);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
