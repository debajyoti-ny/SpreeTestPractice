package com.shiftedtech.qa.scripts.ShiftTestTutorials;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class ComboBox extends BaseClass {

    @Test
    public void test1() throws MalformedURLException {
        //driver.navigate().to("https://www.hscripts.com/tutorials/html/form-combobox.php");
        driver.navigate().to( new URL("https://www.hscripts.com/tutorials/html/form-combobox.php"));
        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().refresh();

        // Above method is just to show the navigate() method's functionality

    }

    @Test
    public void test2() throws MalformedURLException {
        driver.navigate().to( new URL("https://www.hscripts.com/tutorials/html/form-combobox.php"));
        WebElement element = driver.findElement(By.xpath("(//select[@name=\"mytextarea\"])[1]"));

        Select cBoxSelect = new Select(element);
        cBoxSelect.selectByVisibleText("three");
        delayTime(3000);
        cBoxSelect.selectByIndex(1); // Remember: Index starts from 0. So, index 1 = "two"
        delayTime(3000);
        cBoxSelect.selectByValue("one"); //Above three methods does the same thing in a different way.
        delayTime(3000);
    }

    @Test
    public void test3() throws MalformedURLException {

        // ComboBox/DropDown Box Test
        driver.navigate().to( new URL("https://www.hscripts.com/tutorials/html/form-combobox.php"));
        WebElement element = driver.findElement(By.xpath("(//select[@name='mytextarea'])[1]"));
        // element.click(); // For some reason, element is not able to get clicked

        List<WebElement> options = element.findElements(By.tagName("option"));
        for(WebElement items: options){
            System.out.println(items.getText());
            if(items.getText().trim().contentEquals("three")){
                items.click();
            }
        }

        delayTime(4000);
    }

    @Test
    public void test4() throws MalformedURLException {

        // ComboBox/DropDown Box Test with a method
        driver.navigate().to( new URL("https://www.hscripts.com/tutorials/html/form-combobox.php"));
        WebElement element = driver.findElement(By.xpath("(//select[@name='mytextarea'])[1]"));

        comboBoxSelect(element, "two");

        delayTime(4000);
    }

    @Test
    public void test5() throws MalformedURLException {

        // ComboBox/ScrollBox Test with a method
        driver.navigate().to( new URL("https://www.hscripts.com/tutorials/html/form-combobox.php"));
        WebElement element = driver.findElement(By.xpath("(//select[@name='mytextarea'])[2]"));

        comboBoxSelect(element, "three");

        delayTime(4000);
    }

    @Test
    public void test6() throws MalformedURLException {

        // ComboBox/ScrollBox Test(Multi-Select Option) with a method
        driver.navigate().to( new URL("https://www.hscripts.com/tutorials/html/form-combobox.php"));
        WebElement element = driver.findElement(By.xpath("(//select[@name='mytextarea'])[3]"));

        comboBoxSelect(element, "two");
        element.sendKeys(Keys.CONTROL); // For selecting multiple options
        comboBoxSelect(element, "one");

        delayTime(4000);
    }

    @Test
    public void test7() throws MalformedURLException {

        // To test the select and de-select functionality
        driver.navigate().to( new URL("https://www.hscripts.com/tutorials/html/form-combobox.php"));
        WebElement element = driver.findElement(By.xpath("(//select[@name=\"mytextarea\"])[3]"));

        Select cBoxSelect = new Select(element);
        cBoxSelect.selectByVisibleText("three");
        delayTime(3000);
        cBoxSelect.selectByIndex(1); // Remember: Index starts from 0. So, index 1 = "two"
        delayTime(3000);
        cBoxSelect.selectByValue("one"); //Above three methods does the same thing in a different way.
        delayTime(3000);
        cBoxSelect.deselectByIndex(0);
        delayTime(3000);
    }

    @Test
    public void test8() throws MalformedURLException {

        // To test if the output (we see) is same as the internal values(values retrieved by selenium)

        driver.navigate().to(new URL("https://www.hscripts.com/tutorials/html/form-combobox.php"));
        WebElement element = driver.findElement(By.xpath("(//select[@name='mytextarea'])[1]"));

        Select comboBox = new Select(element);
        List<WebElement> options = comboBox.getOptions(); // Creating a List with the "options"

        String[] items = new String[options.size()]; // Creating an empty array of size equal to "options.size()"
        for (int i = 0; i < options.size(); i++) {          // Filling up the array now
            items[i] = options.get(i).getText().trim();
        }

        String[] expectedText = {"one", "two", "three"}; // Creating an array for the expected text
        Assert.assertArrayEquals(expectedText, items); // Testing if the expected and actual are same or not.

        delayTime(3000);
    }

    @Test
    public void test9(){
        // Test to verify if anything is pre-selected
        driver.navigate().to("https://www.hscripts.com/tutorials/html/form-combobox.php");
        WebElement element = driver.findElement(By.xpath("(//select[@name='mytextarea'])[1]"));

        Select comboBox = new Select(element);
        List<WebElement> options = comboBox.getOptions();
        comboBox.selectByVisibleText("two"); // Select option "two"
        delayTime(2000);

        WebElement itemSelected = comboBox.getFirstSelectedOption();
        String expectedText = "two";
        String actualText = itemSelected.getText().trim();

        Assert.assertEquals(expectedText,actualText);
        delayTime(3000);

    }

    public void comboBoxSelect(WebElement element, String itemToSelect) {
        List<WebElement> options = element.findElements(By.tagName("option"));
        for(WebElement items: options){
            System.out.println(items.getText());
            if(items.getText().trim().contentEquals(itemToSelect)){
                items.click();
            }
        }
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
}
