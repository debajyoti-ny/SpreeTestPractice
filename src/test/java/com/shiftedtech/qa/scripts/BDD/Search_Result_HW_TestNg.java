package com.shiftedtech.qa.scripts.BDD;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Debajyoti Paul on 3/30/2018 at 1:43 AM
 */
public class Search_Result_HW_TestNg {
    WebDriver driver = null;

    @BeforeMethod
    public void setUp(){
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(45, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.navigate().to("http://spree.shiftedtech.com/");
    }

    @Test
    public void searchResult(){
        WebElement searchBox = driver.findElement(By.xpath("//input[@id='keywords']"));
        searchBox.sendKeys("Ruby");

        WebElement searchButton = driver.findElement(By.xpath("//input[@class='btn btn-success']"));
        searchButton.click();

        String[] expectedProductsAfterSearch = {"Ruby on Rails Tote",
                                                "Ruby on Rails Bag",
                                                "Ruby on Rails Baseball Jersey",
                                                "Ruby on Rails Jr. Spaghetti",
                                                "Ruby on Rails Ringer T-Shirt yy",
                                                "Ruby Baseball Jersey",
                                                "Ruby on Rails Mug",
                                                "Ruby on Rails Stein xx"
                                                };

        List<WebElement> productsAfterSearch = driver.findElements(By.xpath("//div[@id='products']//a[@class='info']"));
        String[] actualProductsAfterSearch = new String[8];
        for(int i=0; i < productsAfterSearch.size(); i++){
            String productName = productsAfterSearch.get(i).getText();
            actualProductsAfterSearch[i] = productName;
            System.out.println(actualProductsAfterSearch[i] + "  ");
        }

        delay(3000);

        Assert.assertEquals(expectedProductsAfterSearch, actualProductsAfterSearch);

        delay(2000);

    }

    public void delay(int timeInMilli){
        try {
            Thread.sleep(timeInMilli);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void tearDown(){
        driver.close();
        driver.quit();
    }

}
