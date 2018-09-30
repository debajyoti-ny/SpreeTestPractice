package com.shiftedtech.qa.scripts.ShiftTestTutorials;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Button extends BaseClass {

    @Test
    public void test1(){
        WebElement button = driver.findElement(By.id("html-button-01"));
         button.click();
        // jsClick(button); // This click isn't working for some reason
        WebElement text = driver.findElement(By.id("alert-btn-clicked"));
        Assert.assertTrue(text.getText().contains("Button is clicked"));
    }

    @Test
    public void test2(){
        List<WebElement> listOfObjects = driver.findElements(By.xpath("//div[@class='col-md-6']/*[@type='button']"));
        for(WebElement element: listOfObjects){
            String label = element.getText();
            if(label.trim().contentEquals("")){
                label = element.getAttribute("value");
            }
            System.out.println("Label: " + label);

        }

    }

    @Test
    public void test3(){
        // Split Button Dropdown Test -- Works with FF but not with chrome
        WebElement element = driver.findElement(By.xpath("//*[@id='split-button-01']/following-sibling::button"));
        element.click();
        delayTime(2000);
        WebElement subElement = driver.findElement(By.xpath("//*[@id='split-button-01']/following-sibling::ul//a[text()='Another action']"));
        System.out.println(subElement.getText());
        subElement.click();
    }

    @Test
    public void test4(){
        buttonDropDown(By.xpath("//*[@id='split-button-01']"), "Another action");
        buttonDropDown(By.xpath("//*[@id='split-button-01']"), "Something else here");
    }

    @Test
    public void test5(){
        buttonDropDownEx(By.xpath("//*[@id='split-button-01']"), "Another action");
       // buttonDropDown(By.xpath("//*[@id='split-button-01']"), "Something else here");
    }

    @Test
    public void test6() {
        // Test to show if a button is disabled or not. If it's disabled, output will be "true" else "null"
        WebElement item = driver.findElement(By.xpath("html/body/div[3]/div/div[3]/div[2]/form/div[5]/button[1]"));
        String value = item.getAttribute("disabled");
        System.out.println("Value: " + value);

        // This will show "null"/"false" as it's not a disabled button
        WebElement item1 = driver.findElement(By.xpath("html/body/div[3]/div/div[3]/div[2]/form/div[4]/div[1]/button[2]"));
        String value2 = item1.getAttribute("disabled");
        System.out.println("Value: " + value2);

        System.out.println("\n\n");

        // Testing if button is disabled using a method
        System.out.println("Button status: " + isButtonDisabled(item));
        System.out.println("Button status: " + isButtonDisabled(item1));
    }

    @AfterClass
    public static void teardown(){
        if(driver != null){
          //  driver.close();
          //  driver.quit();
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

    // Javascript Click for a button
    public void jsClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("var el=arguments[0]; setTimeout(function() {e1.click();}, 100);", element);
    }

    public void highlight(WebElement element){
        for(int i=0; i<2; i++){
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "border: 4px solid red");
            delayTime(500);
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
            delayTime(500);
        }
    }

    // Split Button Dropdown Test Method
    public void buttonDropDown(By by, String itemToClick){
        WebElement element = driver.findElement(by); // "element" finds the "Primary button" while "subElement" finds the dropdown button next to it.
        WebElement subElement = element.findElement(By.xpath("./following-sibling::button")); // the dot in "./following" means current item
        subElement.click();
        delayTime(3000);
        WebElement dropDownItem = element.findElement(By.xpath("./following-sibling::ul//a[text()='" + itemToClick + "']"));
        highlight(dropDownItem); // To highlight the selected dropdown choice (enables us to know which one are we clicking)
        dropDownItem.click();
        delayTime(2000);
    }

    // Split Button Dropdown Test Method (Better method/option)
    public void buttonDropDownEx(By by, String itemToClick){
        WebElement element = driver.findElement(by); // "element" finds the "Primary button" while "subElement" finds the dropdown button next to it.
        WebElement subElement = element.findElement(By.xpath("./following-sibling::button")); // the dot in "./following" means current item
        subElement.click();
        delayTime(3000);
        List<WebElement> dropDownItem = element.findElements(By.xpath("./following-sibling::ul//a"));
        for(WebElement option : dropDownItem){
            String text = option.getText();
            if(text.contentEquals(itemToClick)){
                highlight(option); // To highlight the selected dropdown choice (enables us to know which one are we clicking)
                option.click();
                break;
            }
        }
    }

    // Method used to test if a button is disabled or not.
    public boolean isButtonDisabled(WebElement button){
        String value = button.getAttribute("disabled");
        if(value == null || value.contentEquals("false")){
            return false;
        }
        else if(value.contentEquals("true")){
            return true;
        }
        else {
            return false;
        }
    }
}
