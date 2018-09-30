package com.shiftedtech.qa.scripts.ShiftTestTutorials;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Table_Bootstrap extends BaseClass {

    @Test
    public void test1(){
        WebElement table = driver.findElement(By.xpath(".//table[@id='example']"));
        List<WebElement> rows = table.findElements(By.xpath("./tbody/tr"));
        WebElement row = rows.get(1); //Get the second row's x-path

        List<WebElement> cols = row.findElements(By.tagName("td"));
        WebElement col = cols.get(0); //Get the 1st column's x-path
        col.click(); //Click the "2nd Row - 1st Column" Cell

        WebElement editButton = driver.findElement(By.xpath(".//button[@class='btn btn-default buttons-selected buttons-edit']"));
        editButton.click(); // Find and then click the Edit Button

        WebElement salaryBox = driver.findElement(By.xpath(".//input[@id='DTE_Field_salary']"));
        salaryBox.clear(); // Find and then clear the salary field
        salaryBox.sendKeys("2000"); //Enter the new salary

        WebElement updateButton = driver.findElement(By.xpath(".//button[@class='btn btn-default btn-primary']"));
        updateButton.click(); // Find and then click the Update Button

        WebElement salaryColumn = cols.get(5); //Since salary's column is 6, hence index is 5
        String actualText = salaryColumn.getText();
        System.out.println("Updated salary is: " + actualText);
        String expectedText = "$2,000";

        Assert.assertEquals(expectedText, actualText);

    }

    @Test
    // Test for Simple Inline Editing (May show some error after running (code -1) but it's because of selenium)
    public void test2(){
        driver.navigate().to("https://editor.datatables.net/examples/inline-editing/simple");
        WebElement table = driver.findElement(By.xpath(".//table[@id='example']"));
        List<WebElement> rows = table.findElements(By.xpath(".//tbody/tr"));
        WebElement row = rows.get(1); //For row #2

        List<WebElement> cols = row.findElements(By.tagName("td"));
        WebElement col = cols.get(6); //For column #7
        col.click();

//        Actions actions = new Actions(driver);
//        actions.doubleClick().build().perform(); //If it needs to be double-clicked for some reason

        WebElement textBox = col.findElement(By.tagName("input"));
        textBox.clear();
        textBox.sendKeys("2000");
        textBox.sendKeys(Keys.ENTER);

        delayTime(3000);
    }

    @Test
    // Test for Edit Icon Editing (May show some error after running (code -1) but it's because of selenium)
    public void test3(){
        driver.navigate().to("https://editor.datatables.net/examples/inline-editing/editIcon.html");
        WebElement table = driver.findElement(By.xpath(".//table[@id='example']"));
        List<WebElement> rows = table.findElements(By.xpath(".//tbody/tr"));
        WebElement row = rows.get(1); //For row #2

        List<WebElement> cols = row.findElements(By.tagName("td"));
        WebElement col = cols.get(5); //For column #6

        col.click();
        WebElement editIcon = col.findElement(By.tagName("i"));
        editIcon.click();

        WebElement textBox = col.findElement(By.tagName("input"));
        textBox.clear();
        textBox.sendKeys("2000");
        textBox.sendKeys(Keys.ENTER);

        delayTime(3000);
    }

    // Method used to delay the execution time
    public void delayTime(int timeInMili){
        try {
            Thread.sleep(timeInMili);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void teardown(){
        if(driver != null){
            // driver.close();
            driver.quit();
        }
    }
}
