package com.shiftedtech.qa.scripts;

import org.openqa.selenium.By;

/**
 * Created by Debajyoti Paul on 5/6/2018 at 12:21 AM
 */
public class SamplePOJOtry {
    public static By getItemHeaderText() {
        return itemHeaderText;
    }

    public static By getMissingCityErrorMsg() {
        return missingCityErrorMsg;
    }

    public static By getFirstNameBox() {
        return firstNameBox;
    }

    public static By getLastNameBox() {
        return lastNameBox;
    }

    public static By getAddress1Box() {
        return address1Box;
    }

    public static By getCityBox() {
        return cityBox;
    }

    private static By itemHeaderText = By.xpath("//p[@class='minibag-item-short-description']");
    private static By missingCityErrorMsg = By.xpath("//div[@id='shipCity-wrap']//p[@class='errorMsg']");
    private static By firstNameBox = By.id("shipFirst");
    private static By lastNameBox = By.id("shipLast");
    private static By address1Box = By.id("shipAddress1");
    private static By cityBox = By.id("shipCity");


}
