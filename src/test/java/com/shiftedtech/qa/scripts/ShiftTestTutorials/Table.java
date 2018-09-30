package com.shiftedtech.qa.scripts.ShiftTestTutorials;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

public class Table extends BaseClass {

    @Test
    // Test to get the text from a particular cell
    public void test1(){
        WebElement cellElement = driver.findElement(By.xpath(".//table[@class='table-bordered']/tbody/tr[2]/td[2]"));
        String text = cellElement.getText();
        System.out.println("Cell Text: " + text);
    }

    @Test
    // Test to get the text from all the cells
    public void test2(){
        for(int i=1; i<=3; i++){
            for(int j=1; j<=3; j++){
                WebElement cellElement = driver.findElement(By.xpath(".//table[@class='table-bordered']/tbody/tr[" + i + "]/td[" + j + "]")); // X-path changes every time along with the change in i and j
                String text = cellElement.getText();
                System.out.println("Cell Text for # [" + i + "][" + j + "] " + text);
            }
        }
    }

    @Test
    // Test to retrieve individual cell text when input is given by user
    public void test3(){
        getCellText(2,3);
    }

    @Test
    // Test to retrieve individual cell text when input is given by user
    public void test4(){
        getAllCellText();
    }

    @Test
    // Test to retrieve text from all the cells using an Array
    public void test5(){
        String result[][] = getAllCellTextArray();
        System.out.println(Arrays.deepToString(result));

        System.out.println(result[2][2]); //If we want to know the text for a specific cell
    }

    @Test
    // Test to retrieve text from all the cells using an Array and passing the WebElement (X-Path)
    public void test6(){
        WebElement element = driver.findElement(By.xpath(".//div[@id='html-table']/table"));
        String result[][] = getAllCellTextArray(element);       //Calling the overloaded method
        System.out.println(Arrays.deepToString(result));

        WebElement element2 = driver.findElement(By.xpath(".//div[@id='striped-table']")); // This is a Striped Table
        String result2[][] = getAllCellTextArray(element2);       //Calling the overloaded method
        System.out.println(Arrays.deepToString(result2));
    }

    @Test
    // Test to verify a cell's Data
    public void test7(){
        WebElement element = driver.findElement(By.xpath(".//div[@id='striped-table']"));
        String[][] tableText = getAllCellTextArray(element); // Done with selenium and the "getAllCellTextArray" method here...
        String actualCellText = tableText[1][1]; //Retrieving actual Cell Text info from tableText
        String expectedCellText = "Moe";

        Assert.assertEquals(expectedCellText, actualCellText);
    }

    @Test
    // Test to verify a Row's Data
    public void test8(){
        WebElement element = driver.findElement(By.xpath(".//div[@id='striped-table']"));
        String[][] tableText = getAllCellTextArray(element); // Done with selenium and the "getAllCellTextArray" method here...
        String[] actualCellText = tableText[1]; //Retrieving actual Cell Text info from tableText
        String[] expectedCellText = {"Mary2", "Moe", "mary@example.com"}; // Test will fail because I wrote "Mary2" instead of "Mary"

        Assert.assertArrayEquals(expectedCellText, actualCellText);
    }

    @Test
    // Test to verify a Table's Data
    public void test9(){
        WebElement element = driver.findElement(By.xpath(".//div[@id='striped-table']"));
        String[][] actualCellText = getAllCellTextArray(element); // Done with selenium and the "getAllCellTextArray" method here...
        String[][] expectedCellText =  {{"John",	"Doe", "john@example.com"},
                                        {"Mary", "Moe", "mary@example.com"},
                                        {"July", "Dooley", "july@example.com"}
                                        };

        Assert.assertArrayEquals(expectedCellText, actualCellText);
    }

    // Method to retrieve individual cell text
    public void getCellText(int row, int col){
        WebElement cellElement = driver.findElement(By.xpath(".//table[@class='table-bordered']/tbody/tr[" + row + "]/td[" + col + "]"));
        String text = cellElement.getText();
        System.out.println("Cell Text for # [" + row + "][" + col + "] " + text);
    }

    // Method to retrieve texts from all the cells (All rows & columns)
    public void getAllCellText(){
        List<WebElement> rows = driver.findElements(By.xpath(".//table[@class='table-bordered']/tbody/tr"));
        for(int i=0; i<rows.size(); i++){
            WebElement row = rows.get(i);
            List<WebElement> cols = row.findElements(By.tagName("td"));
            for(int j=0; j<cols.size(); j++){
                WebElement col = cols.get(j);
                String text = col.getText();
                System.out.println("Cell Text: " + text);
            }
        }
    }

    // Method to retrieve texts from all the cells as an Array (All rows & columns)
    public String[][] getAllCellTextArray(){
        String[][] tableText = null;    // declare an array called tableText
        List<WebElement> rows = driver.findElements(By.xpath(".//table[@class='table-bordered']/tbody/tr")); //find all the rows
        tableText = new String[rows.size()][];   //Initialize (size) the array with the total number of rows

        for(int i=0; i<rows.size(); i++){
            WebElement row = rows.get(i); //Retrieve what's in row[0]
            List<WebElement> cols = row.findElements(By.tagName("td"));
            tableText[i] = new String[cols.size()]; //Initialize (size) the array with the total number of columns for that particular row[i]
            for(int j=0; j<cols.size(); j++){
                WebElement col = cols.get(j);
                String text = col.getText();
                tableText[i][j] = text;
            }
        }
        return tableText;
    }

    // Method to retrieve texts from all the cells as an Array (All rows & columns) (Overloaded method)
    public String[][] getAllCellTextArray(WebElement element){
        String[][] tableText = null;    // declare an array called tableText
        List<WebElement> rows = element.findElements(By.xpath(".//tbody/tr")); //find all the rows
        tableText = new String[rows.size()][];   //Initialize (size) the array with the total number of rows

        for(int i=0; i<rows.size(); i++){
            WebElement row = rows.get(i); //Retrieve what's in row[0]
            List<WebElement> cols = row.findElements(By.tagName("td"));
            tableText[i] = new String[cols.size()]; //Initialize (size) the array with the total number of columns for that particular row[i]
            for(int j=0; j<cols.size(); j++){
                WebElement col = cols.get(j);
                String text = col.getText();
                tableText[i][j] = text;
            }
        }
        return tableText;
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
