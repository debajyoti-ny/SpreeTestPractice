package com.shiftedtech.qa.scripts.ShiftTestTutorials;

import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class TextBox2 extends BaseClass {

    @Test
    public void textBox(){
        WebElement plainTextBox = driver.findElement(By.id("plain-text"));
        plainTextBox.sendKeys("debajyoti007");
    }

    @Test
    public void passwordBox(){
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("deb0119");
    }

    @Test
    public void emailBox(){
        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys("debajyoti007@gmail.com");
    }


    @Test
    public void urlBox(){
        WebElement url = driver.findElement(By.id("url"));
        url.sendKeys("http://www.google.com");
    }

    @Test
    public void dateBox(){
        WebElement date = driver.findElement(By.xpath("//div[5]/input"));
        date.sendKeys("11012017");
    }

    @Test
    public void dateTimeBox(){
        WebElement dateTime = driver.findElement(By.xpath("//form/div[6]/input"));
        dateTime.sendKeys("11012017");
        dateTime.sendKeys(Keys.TAB);
        dateTime.sendKeys("0222PM");
    }

    @Test
    public void numberBox(){
        WebElement number = driver.findElement(By.xpath("(//*[@id=\"number\"])[2]"));
        for(int i=0; i<100; i++){
            number.sendKeys(Keys.UP);
        }
    }

    @Test
    public void range(){
        delay(3000);
        WebElement rangeElement = driver.findElement(By.id("range"));

        highlight(rangeElement);
        rangeMethod(rangeElement, 90);
        delay(3000);
    }

    @Test
    public void phoneNumber(){
        WebElement areaCode = driver.findElement(By.id("areaCode"));
        WebElement phonePre = driver.findElement(By.id("phonePre"));
        WebElement phoneSuf = driver.findElement(By.id("phoneSuf"));

        areaCode.sendKeys("818");
        phonePre.sendKeys("308");
        phoneSuf.sendKeys("0119");

    }

    public void rangeMethod(WebElement element, int percent){

        int height = element.getSize().getHeight();
        int width = element.getSize().getWidth();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        delay(1000);

        Actions actionBuilder = new Actions(driver);
        Action action = actionBuilder.clickAndHold(element)
                                    .moveByOffset(-(width/2), 0)
                                    .moveByOffset((width/100)*percent,0)
                                    .release()
                                    .build();

        action.perform();
    }

    public void highlight(WebElement element){
        for(int i=0; i<2; i++){
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "border: 10px solid red");
            delay(3000);
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
            delay(1000);
        }
    }

    public void delay(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void terminate(){
        driver.close();
        driver.quit();
    }
}
