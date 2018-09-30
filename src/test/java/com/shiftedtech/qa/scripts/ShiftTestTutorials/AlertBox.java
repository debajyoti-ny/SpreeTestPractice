package com.shiftedtech.qa.scripts.ShiftTestTutorials;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class AlertBox extends BaseClass {

    @Test
    public void test1(){
        WebElement element = driver.findElement(By.xpath(".//button[@id='alert-02']")); //Left(2nd button)
        delayTime(2000); //The delay is used here to help selenium find the element(button)
        element.click();
//     // Another way to make the click work is by the javascript executor
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript( "alert(\"I am an alert box!\");");
        delayTime(3000);

        String message = driver.switchTo().alert().getText();
        System.out.println("Message: " + message);
        driver.switchTo().alert().accept();
//       driver.switchTo().alert().dismiss();  //To dismiss(cancel) an alert box instead of accepting it(clicking OK)
        delayTime(3000);

    }

    @Test
    public void test2() {
        WebElement element = driver.findElement(By.xpath(".//button[contains(text(),'Click')]")); //Right bootstrap button
        delayTime(3000); //The delay is used here to help selenium find the element(button)
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform(); //Another way to click the element

        // element.click();
        delayTime(2000);

        WebElement element2 = driver.findElement(By.xpath(".//button[contains(text(), 'Close')]"));
        actions.moveToElement(element2).click().perform();
        //element2.click();

        delayTime(2000);

    }

    @Test
    public void test3(){
        WebElement element = driver.findElement(By.xpath(".//button[@id='alert-03']")); //Left(3rd button)
        delayTime(3000); //The delay is used here to help selenium find the element(button)
        element.click();
        delayTime(3000);

        String message = driver.switchTo().alert().getText();
        System.out.println("Message: " + message);
        driver.switchTo().alert().sendKeys("Debajyoti");
        driver.switchTo().alert().accept();
        delayTime(3000);

        String actualText = driver.findElement(By.xpath(".//p[@id='demo3']")).getText();
        String expectedText = "Hello debajyoti! How are you today?";

        Assert.assertEquals(expectedText, actualText); //Test fails because it's "debajyoti" instead of "Debajyoti"

    }

    @Test
    public void test4() {

        //To show the highlighted color when clicking
        WebElement element = driver.findElement(By.xpath(".//button[contains(text(),'Click')]")); //Right bootstrap button

        for(int i=0; i<2; i++){
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript(
                    "arguments[0].setAttribute('style', arguments[1]);" ,
                    element, "border: 5px solid yellow;");
            delayTime(700);
            js.executeScript(
                    "arguments[0].setAttribute('style', arguments[1]);" ,
                    element, "");
            delayTime(700);
        }
        delayTime(3000);
    }

    @AfterClass
    public static void teardown(){
        if(driver != null){
           // driver.close();
            driver.quit();
        }
    }

    // Method used to delay the execution time
    public void delayTime(int timeInMili){
        try {
            Thread.sleep(timeInMili);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
