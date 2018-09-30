package com.shiftedtech.qa.scripts.ShiftTestTutorials;

import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.interactions.internal.Locatable;

import java.util.List;
import java.util.NoSuchElementException;

public class MouseHover extends BaseClass {

    @Test
    public void dropDownHoverTest(){
        WebElement element = driver.findElement(By.xpath(".//button[@id='dropdown-menu-hover-01']"));
//        delayTime(3000);
//        element.click();
        hoverAction(element);
        delayTime(3000);
    }

    @Test
    public void clickElementsOfDropDownHoverTest() {
        WebElement element = driver.findElement(By.xpath(".//button[@id='dropdown-menu-hover-01']"));
        clickElementsOfDropDownHover(element, "Something else here");
    }

    @Test
    public void linkHoverTest(){
        WebElement element = driver.findElement(By.xpath(".//div[@id='hover-02']/div/a"));
        hoverAction(element);

//        delayTime(3000);
//        mouseHoverJScript(element); //JavaScript Method not working for reasons unknown

        delayTime(3000);
    }

    @Test
    public void navBarHoverTest(){
        WebElement element = driver.findElement(By.xpath(".//a[@id='hover-nav-01']"));
        hoverAction(element);
//        hoverLocatable(element); //Not working for some reasons
        delayTime(3000);
    }

//    *********************** Methods used in this class are defined below **********************

    //Method to Hover over an element
    public void hoverAction(WebElement element){
        delayTime(3000); //To delay the process so that selenium can locate/find the element
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    //Method to hover and then click on a particular item
    public void clickElementsOfDropDownHover(WebElement element, String itemToClick){
        delayTime(3000);
        element.click();
        List<WebElement> listOfElements = element.findElements(By.xpath(".//following-sibling::ul/li/a"));
        for(WebElement option: listOfElements){
            String text = option.getText();
            System.out.println("Items: " + text);
            if(text.trim().contentEquals(itemToClick)){
                highlight(option);
                option.click();
                break;
            }
        }
    }

    //Mouse Hover element with Locatable and Mouse class
    public void hoverLocatable(WebElement element){
        Locatable hoverItem = (Locatable) element;
        Mouse mouse = ((HasInputDevices) driver).getMouse();
        mouse.mouseMove(hoverItem.getCoordinates());
    }

    //Method to hover over an element using JavaScript Executor
    public void mouseHoverJScript(WebElement HoverElement) {
        try {
            if (isElementPresent(HoverElement)) {
                String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
                ((JavascriptExecutor) driver).executeScript(mouseOverScript, HoverElement);
            } else {
                System.out.println("Element was not visible to hover " + "\n");
            }

        } catch (StaleElementReferenceException e) {
            System.out.println("Element with " + HoverElement
                    + "is not attached to the page document"
                    + e.getStackTrace());
        }
    }

    public static boolean isElementPresent(WebElement element) {
        boolean flag = false;
        try {
            if (element.isDisplayed()
                    || element.isEnabled())
                flag = true;
        } catch (NoSuchElementException e) {
            flag = false;
        } catch (StaleElementReferenceException e) {
            flag = false;
        }
        return flag;
    }


    //Method to highlight an element
    public void highlight(WebElement element){
        for(int i=0; i<2; i++){
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "border: 4px solid red");
            delayTime(500);
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
            delayTime(500);
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

    @AfterClass
    public static void teardown(){
        if(driver != null){
            // driver.close();
            driver.quit();
        }
    }
}
