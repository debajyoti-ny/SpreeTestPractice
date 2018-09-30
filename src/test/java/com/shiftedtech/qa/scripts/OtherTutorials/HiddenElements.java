package com.shiftedtech.qa.scripts.OtherTutorials;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * There are 2 radio buttons with the same id... one is visible and the other isn't.
 * We want to click on the visible element. Let's see how...
 */
public class HiddenElements extends BaseClassOT{

    @Test
    public void test1(){
        driver.navigate().to("http://seleniumpractise.blogspot.in/2016/08/how-to-automate-radio-button-in.html");

        List<WebElement> radios = driver.findElements(By.id("male"));
        System.out.println("Radio buttons (male) present in this page are: " + radios.size());

        for(WebElement radio : radios){
            int x = radio.getLocation().getX(); //Gives the X-cordinate of the element. (x-cord = 0 for hidden elements)
          //  int y = radio.getLocation().getY(); //Gives the Y-cordinate of the element. (y-cord = 0 for hidden elements)

            if(x != 0){
                radio.click();
                break;
            }

        }

    }

}
