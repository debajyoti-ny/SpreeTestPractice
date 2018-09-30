package com.shiftedtech.qa.scripts.ShiftTestTutorials;

import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class DropDownBox extends BaseClass {

    @Test
    public void test1(){
        //Test to select an item from a single-select HTML DropDown Box
        WebElement element = driver.findElement(By.cssSelector("#html-select-01>select"));
        element.click();
        delayTime(2000);

        WebElement parent = element.findElement(By.xpath("//*[@id='html-select-01']/select")); //current xpath (css selector used in the top)
        List<WebElement> items = parent.findElements(By.xpath("./option"));
        for(WebElement item: items){
            String text = item.getText();
            System.out.println("Items: " + text);
            if(text.trim().equalsIgnoreCase("Option 2")){
               item.click();
            }
        }
        delayTime(3000);
    }

    @Test
    public void test2() {
        // Test to select a BootStrap DropDown Box
        WebElement element = driver.findElement(By.xpath("//*[@class='btn dropdown-toggle form-control selectpicker btn-primary']"));
        element.click(); // Works fine w/Firefox, but not with Chrome

//        Actions actions = new Actions(driver); // This lines is to make sure the element could be clicked by selenium
//        actions.moveToElement(element).click().perform(); // properly in chrome (Doesn't work with FF for some reason)

        delayTime(2000);

        WebElement parent = element.findElement(By.xpath("//*[@class='dropdown-menu open']")); // To get all the elements of the dropdown menu
        List<WebElement> items = parent.findElements(By.xpath(".//ul/li/a"));
        for(WebElement item : items){
            System.out.println("Items: " + item.getText());
              if(item.getText().trim().contentEquals("Relish")){
                item.click();
                // actions.moveToElement(item).click().perform();
                break;
            }
        }

        delayTime(3000);
    }

    @Test
    public void test3(){
        // Test to select multiple items in a HTML DropDown Box
        WebElement element = driver.findElement(By.xpath("//*[@id='html-select-03']/select"));
        if(element != null) { // Fancy/Pro way of writing the same code
            element.click();

            WebElement parent = element.findElement(By.xpath("//*[@id='html-select-03']/select"));
            List<WebElement> items = parent.findElements(By.xpath("./option"));
            for (WebElement item : items) {
                System.out.println("Items: " + item.getText());
                item.click();
            }

            delayTime(4000);
        }
    }

    @Test
    public void test4(){
        // Test to select from a BootStrap DropDown
        WebElement element = driver.findElement(By.xpath("//*[@class='btn btn-success dropdown-toggle']"));
        element.click();

        List<WebElement> items = element.findElements(By.xpath(".//following-sibling::ul/li/a"));
        for(WebElement item: items){
            System.out.println("Items: " + item.getText());
            if(item.getText().trim().equalsIgnoreCase("Another Action")){
                item.click();
                continue;
            }
        }
        delayTime(3000);
    }

    @Test
    public void test5(){
        // Test to select multiple items in a BootStrap DropDown Box
        WebElement element = driver.findElement(By.xpath("(//div[@class='bs-docs-example']/div/button)[2]"));
        element.click();

        WebElement parent = element.findElement(By.xpath(".."));
        List<WebElement> items = parent.findElements(By.xpath(".//ul/li/a"));
        for(WebElement item: items){
            System.out.println("Items: " + item.getText());
            item.click();
        }

        delayTime(4000);

    }

    @Test
    public void test6() {
        //Test to select multiple items from a multi-select bootstrap DropDown Box using a method
        WebElement element = driver.findElement(By.xpath("(//div[@class='bs-docs-example']/div/button)[2]"));

        BootstrapDropDownMultiSelect(element,"Ketchup", "Relish");
    }

    @Test
    public void test7(){
        //Test to select multi-select items using the "Select" class (Works fine for HTML Dropdown)
        WebElement element = driver.findElement(By.xpath("//*[@id='html-select-03']/select"));
        Select selectItem = new Select(element);

        selectItem.selectByVisibleText("Option 1");
        selectItem.selectByIndex(2);
        delayTime(3000);

//        // BootStrap Multi-Select Box
//        WebElement element1 = driver.findElement(By.xpath("(//*[@class='btn dropdown-toggle form-control selectpicker btn-default'])[1]"));
//        element1.click();
//        delayTime(3000);
//        WebElement element2 = element1.findElement(By.xpath("(//*[@class='btn dropdown-toggle form-control selectpicker btn-default'])[1]/following-sibling::div"));
//        Select selectItem2 = new Select(element2); //Error here -- Maybe problem with locating the xpath
//
//        selectItem2.selectByIndex(1);
//        selectItem2.selectByVisibleText("Relish");
//        delayTime(2000);

    }

    public void BootstrapDropDownMultiSelect(WebElement element, String... itemsToSelect){
        WebElement parent = element.findElement(By.xpath("..")); // ".." gives me the parent
        element.click();

        List<WebElement> items = parent.findElements(By.xpath(".//ul/li/a"));
        for(String itemToSelect: itemsToSelect){
            for(WebElement item: items){
                System.out.println("Items:" + item.getText());
                if(item.getText().trim().contentEquals(itemToSelect)){
                    item.click();
                }
            }
        }
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
