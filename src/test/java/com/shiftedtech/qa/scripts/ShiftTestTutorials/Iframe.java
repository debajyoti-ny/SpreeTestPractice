package com.shiftedtech.qa.scripts.ShiftTestTutorials;

import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Iframe extends BaseClass {

    @Test
    public void switchFrame(){
        scrollToElement(driver.findElement(By.xpath(".//h2[text()='Iframe 01']"))); //Just scrolling to the header "IFrame 01"
//        driver.switchTo().frame("iframe-01"); //We could have switched to Iframe 01 using this method
                                                // but let's do it the other way by finding how many iframes we have in the webpage

        List<WebElement> iframeList = driver.findElements(By.tagName("iframe"));
        System.out.println("Number of Iframes is: " + iframeList.size());

        driver.switchTo().frame(iframeList.get(0)); //Switching to the 1st Iframe (Index 0...since it's in a List)

        WebElement element = driver.findElement(By.xpath(".//button[@class='navbar-toggle']"));
        highlight(element);
        element.click();

        delayTime(3000);
    }

    @Test
    public void switchFrame_SecondWay(){
        scrollToElement(driver.findElement(By.xpath(".//h2[text()='Iframe 01']"))); //Just scrolling to the header "IFrame 01"
//        driver.switchTo().frame("iframe-01"); //We could have switched to Iframe 01 using this method
                                                // but let's do it the other way by finding how many iframes we have in the webpage

        List<WebElement> iframeList = driver.findElements(By.tagName("iframe"));
        System.out.println("Number of Iframes is: " + iframeList.size());

//        driver.switchTo().frame(iframeList.get(0)); //Switching to the 1st Iframe (Index 0...since it's in a List)

        driver.switchTo().frame(0); //Using in-built selenium method to switch frames by "Index"
        WebElement element = driver.findElement(By.xpath(".//button[@class='navbar-toggle']"));
        highlight(element);
        element.click();
        delayTime(3000);

        driver.switchTo().defaultContent(); //To come out of the Iframe OR, Go back to the top level Iframe
        driver.switchTo().frame(1); //Using in-built selenium method to switch frames by "Index"
        element = driver.findElement(By.xpath(".//button[@class='navbar-toggle']"));
        highlight(element);
        element.click();
        delayTime(3000);
    }

    @Test
    public void switchFrameByName() {
        scrollToElement(driver.findElement(By.xpath(".//h2[text()='Iframe 01']"))); //Just scrolling to the header "IFrame 01"
        driver.switchTo().frame("iframe-01"); //Using in-built selenium method to switch frames by "Name"
        WebElement element = driver.findElement(By.xpath(".//button[@class='navbar-toggle']"));
        highlight(element);
        element.click();
        delayTime(3000);
    }

    @Test
    public void switchFrameByIndex() {
        scrollToElement(driver.findElement(By.xpath(".//h2[text()='Iframe 01']"))); //Just scrolling to the header "IFrame 01"
        driver.switchTo().frame(0); //Using in-built selenium method to switch frames by "Index"
        WebElement element = driver.findElement(By.xpath(".//button[@class='navbar-toggle']"));
        highlight(element);
        element.click();
        delayTime(3000);
    }

    @Test
    public void switchFrameByWebElement() {
        scrollToElement(driver.findElement(By.xpath(".//h2[text()='Iframe 01']"))); //Just scrolling to the header "IFrame 01"
        List<WebElement> iFrameList = driver.findElements(By.tagName("iframe"));
        for(WebElement iFrame : iFrameList){
            String src = iFrame.getAttribute("src"); //.getAttribute method is used to retrieve the text from assigned to each attributes like "src", "id", "name", "value", etc.
            System.out.println("Src: " + src);
            if(src.contains("/components")){
                driver.switchTo().frame(iFrame); //If "src" contains "/components", then switch to that iFrame
                break;
            }
        }

        WebElement element = driver.findElement(By.xpath(".//button[@class='navbar-toggle']"));
        highlight(element);
        element.click();
        delayTime(3000);

        //Move back to Original Page
        driver.switchTo().defaultContent();
        delayTime(3000);
        scrollToElement(driver.findElement(By.xpath(".//h2[text()='Iframe 01']"))); //Just scrolling to the header "IFrame 01"

    }

    @Test
    public void numberOfFrames(){
        //By executing a javascript
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Integer numberOfFrames = Integer.parseInt(js.executeScript("return window.length").toString());
        //The js executor gives the length of the window as a String and then it's converted back to Integer by the parseInt method
        System.out.println("Number of IFrames in this page is: " + numberOfFrames);

        //By finding all the web elements using iframe tag
        List<WebElement> iFrameElements = driver.findElements(By.tagName("iframe"));
        System.out.println("Number of IFrames in this page is: " + iFrameElements.size());
    }

    public void scrollToElement(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        delayTime(2000);
    }

    public void highlight(WebElement element){
        for(int i=0; i<2; i++){
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "border: 5px solid red");
            delayTime(1000);
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
            delayTime(1000);
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
