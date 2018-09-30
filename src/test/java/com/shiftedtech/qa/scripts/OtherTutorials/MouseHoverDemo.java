package com.shiftedtech.qa.scripts.OtherTutorials;

import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class MouseHoverDemo extends BaseClassOT{

    @Test
    public void hover(){
        driver.navigate().to("http://seleniumpractise.blogspot.in/2016/08/how-to-perform-mouse-hover-in-selenium.html");
        WebElement element = driver.findElement(By.xpath(".//button[text()='Automation Tools']"));

        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();

        List<WebElement> links = driver.findElements(By.xpath(".//button[text()='Automation Tools']/following-sibling::div/a"));
        int count = 1;
        for(WebElement link: links){
           String text = link.getAttribute("innerHTML");
           System.out.println("Link " + count + "'s text is: " + text);
           if(text.trim().equalsIgnoreCase("TestNG")){
               link.click();
               break;
            }
            count++;
        }

        delay(3000);
    }

    @AfterClass
    public static void tearDown(){
        driver.quit();
    }
}
