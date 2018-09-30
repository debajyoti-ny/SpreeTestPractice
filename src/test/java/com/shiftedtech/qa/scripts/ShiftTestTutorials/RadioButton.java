package com.shiftedtech.qa.scripts.ShiftTestTutorials;

import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class RadioButton extends BaseClass {

    @Test
    public void test1(){
        WebElement rButton = driver.findElement(By.xpath("html/body/div[3]/div/div[2]/div[1]/div[2]/div/form/input[1]"));
        rButton.click();
        delayTime(3000);

        // To see if the button is actually checked or not, we need to go to DOM(Document Object Model)
        String value = rButton.getAttribute("checked");
        System.out.println("Value: " + value);
    }

    @Test
    public void test2(){
        WebElement radioButton = driver.findElement(By.xpath("html/body/div[3]/div/div[2]/div[2]/div[2]/div/form/div[1]"));
        List<WebElement> labels = radioButton.findElements(By.tagName("label"));
        System.out.println("Count: " + labels.size());

        for(WebElement item: labels){
            System.out.println(item.getText());
            if(item.getText().contentEquals("Option 2")){
                WebElement rButton = driver.findElement(By.id("radio-group-2"));
                rButton.click();
                break;
            }
        }

        delayTime(4000);
    }

    @Test
    public void test3(){
        WebElement radioButton = driver.findElement(By.xpath("html/body/div[3]/div/div[2]/div[2]/div[2]/div/form/div[2]"));
        String item = "Option 2";

        clickRadioButton(item, radioButton);
        delayTime(4000);
    }

    @AfterClass
    public static void teardown(){
        if(driver != null){
            driver.close();
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

    public void clickRadioButton(String itemToClick, WebElement inputButton){
        List<WebElement> labels = inputButton.findElements(By.tagName("label"));
        System.out.println("Count: " + labels.size());

        for(WebElement item: labels){
            System.out.println(item.getText());
            if(item.getText().contentEquals(itemToClick)){
                WebElement rButton = driver.findElement(By.id("css-radio-btn-02"));
                rButton.click();
                break;
            }
        }

        delayTime(4000);
    }

}
