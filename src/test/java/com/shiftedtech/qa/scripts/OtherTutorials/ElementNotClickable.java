package com.shiftedtech.qa.scripts.OtherTutorials;

import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ElementNotClickable extends BaseClassOT {

    /**
     * When an Element is not clickable (i.e, it shows "ElementNotClickable Exception" -- usually happens
     * in Chrome and IE browsers), we can handle it by two ways:
     *
     * 1) By changing the X-path.
     * 2) By using the Actions class.
     */

    @Test
    public void test1() {
        driver.navigate().to("https://login.yahoo.com/");
//        WebElement staySignInBox = driver.findElement(By.id("persistent"));   //This doesn't works -- Gives "ElementNotClickable Exception"...
        // so, we change the xpath and then it works
        WebElement staySignInBox = driver.findElement(By.xpath(".//span[@class='one-half column stay-signed-in']/label"));
        staySignInBox.click();
        delay(3000);
    }

    @Test
    public void test2() {
        driver.navigate().to("https://login.yahoo.com/");
        WebElement staySignInBox2 = driver.findElement(By.id("persistent"));

        Actions actions = new Actions(driver);
        actions.moveToElement(staySignInBox2).click().build().perform();    //We use build() method because we are doing multiple
                                                                            //operations here(i.e, move to element and then click it)
    }


    @AfterClass
    public static void tearDown(){
        driver.quit();
    }

}
