package com.shiftedtech.qa.scripts.BDD.Login_Steps_Ex_3;

import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

/**
 * Created by Debajyoti Paul on 3/30/2018 at 2:06 PM
 */
public class SearchResultsSteps extends BaseSteps {

    @Then("^following products should display$")
    public void following_products_should_display(DataTable dataTable) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
        // E,K,V must be a scalar (String, Integer, Date, enum etc)

        List<List<String>> expectedSearchResults = dataTable.raw();
        int arraySize = expectedSearchResults.get(0).size();
        String[] expectedProductsAfterSearch = new String[arraySize];

        System.out.println("Expected products after search: \n");
        for(int i=0; i < expectedSearchResults.size(); i++){
            for(int j=0; j < expectedSearchResults.get(i).size(); j++ ){
                String productName = expectedSearchResults.get(i).get(j);
                expectedProductsAfterSearch[j] = productName;
                System.out.println(expectedProductsAfterSearch[j]);
            }
        }

        List<WebElement> actualSearchResults = driver.findElements(By.xpath("//div[@id='products']//a[@class='info']"));
        String[] actualProductsAfterSearch = new String[actualSearchResults.size()];

        System.out.println("\nActual products after search: \n");
        for(int i=0; i < actualSearchResults.size(); i++){
            String productName = actualSearchResults.get(i).getText();
            actualProductsAfterSearch[i] = productName;
            System.out.println(actualProductsAfterSearch[i]);
        }

        delay(3000);

        Assert.assertEquals(expectedProductsAfterSearch, actualProductsAfterSearch);

        delay(2000);

    }
}
