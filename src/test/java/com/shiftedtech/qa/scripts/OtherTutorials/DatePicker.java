package com.shiftedtech.qa.scripts.OtherTutorials;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by Debajyoti Paul on 3/16/2018 at 11:14 PM
 */
public class DatePicker extends BaseClassOT {

    @Test
    public void test(){
        driver.navigate().to("http://www.seleniumeasy.com/test/bootstrap-date-picker-demo.html");
        WebElement dateBoxOpener = driver.findElement(By.xpath("//span[@class='input-group-addon']/i"));
        dateBoxOpener.click();
        datePicker("16");
    }

    protected void datePicker(String chosenDate) {
        List<WebElement> rows = driver.findElements(By.xpath("//table[@class='table-condensed']/tbody/tr"));
        for(WebElement row : rows){
            List<WebElement> cols = row.findElements(By.tagName("td"));
            for(WebElement col : cols){
                String date = col.getText();
                if(date.contains(chosenDate)){
                    System.out.println(col.getText());
                    col.click();
                    break;
                }
            }
        }
    }

}
