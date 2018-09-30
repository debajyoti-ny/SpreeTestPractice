package com.shiftedtech.qa.scripts.OtherTutorials;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;

/**
 * Created by Debajyoti Paul on 3/17/2018 at 12:09 AM
 */
public class AutoIT_SingleFile extends BaseClassOT {

    @Test
    public void test1() throws IOException{
        driver.navigate().to("http://demo.guru99.com/test/upload/");
        WebElement uploadBox = driver.findElement(By.xpath("//input[@id='uploadfile_0']"));
        uploadBox.click();
        delay(2000);
        Runtime.getRuntime().exec("C:\\Users\\DEBAJYOTI007\\IdeaProjects\\SpreeTestPractice\\src\\test\\Resources\\AutoIT_Selenium\\sample.exe");

        WebElement terms = driver.findElement(By.xpath("//span[@class='field_title']/input"));
        terms.click();
        delay(2000);

        WebElement submit = driver.findElement(By.xpath("//button[@id='submitbutton']"));
        submit.click();
        delay(2000);
    }

}
