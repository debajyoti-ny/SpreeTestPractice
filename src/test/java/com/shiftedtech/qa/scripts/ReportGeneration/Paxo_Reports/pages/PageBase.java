package com.shiftedtech.qa.scripts.ReportGeneration.Paxo_Reports.pages;

import com.google.common.base.Function;
import com.paxovision.execution.reporter.service.ReporterService;
import com.shiftedtech.qa.framework.utils.ExtentManager;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class PageBase {
    protected WebDriver driver;
    protected ReporterService reporter = ReporterService.reporter(); //Object created so that it could be used in Homepage and LoginPage classes
    protected ExtentManager extentManager = ExtentManager.getInstance(); //Object created so that it could be used in Homepage and LoginPage classes

    public static final int DEFAULT_WAIT_TIME = 5;

    public PageBase(WebDriver driver){
        this.driver = driver;
    }

    // The following 4 methods are used in KeyWord Driven Testing...
    public void typeText(By by, String text){
        WebElement element = driver.findElement(by);
        element.clear();
        element.sendKeys(text);
    }

    public void clickElement(By by){
        WebElement element = driver.findElement(by);
        element.click();
    }

    public void verifyText(By by, String expectedText){
        WebElement element = driver.findElement(by);
        String actualText = element.getText();
        Assert.assertEquals(expectedText, actualText);
    }

    public void verifyPageTitle(String expectedPageTitle){
        String actualPageTitle = driver.getTitle();
        Assert.assertEquals(expectedPageTitle, actualPageTitle);
        System.out.println("Page Title verified...");
    }

    public By getFindBy(String by, String using){
        if(by.trim().toUpperCase().contentEquals("ID")){
            return By.id(using);
        }else if(by.trim().toUpperCase().contentEquals("NAME")){
            return By.name(using);
        }else if(by.trim().toUpperCase().contentEquals("XPATH")){
            return By.xpath(using);
        }else if(by.trim().toUpperCase().contentEquals("CSS")){
            return By.cssSelector(using);
        }else if(by.trim().toUpperCase().contentEquals("CLASS_NAME")){
            return By.className(using);
        }else if(by.trim().toUpperCase().contentEquals("LINK_TEXT")){
            return By.linkText(using);
        }else if(by.trim().toUpperCase().contentEquals("PARTIAL_LINK_TEXT")){
            return By.partialLinkText(using);
        }else if(by.trim().toUpperCase().contentEquals("TAG_NAME")){
            return By.tagName(using);
        }else{
            return null;
        }

    }

    // All the basic utilities methods are written below...

    public void delay(int timeInMilliSeconds){
        try {
            Thread.sleep(timeInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void highlight(WebElement element){
        for(int i=0; i<2; i++){
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "border: 3px solid red");
            delay(300);
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
            delay(300);
        }
    }

    public void jsClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("var el=arguments[0]; setTimeout(function() {e1.click();}, 100);", element);
    }

    public void actionClick(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().build().perform();
    }

    public FluentWait<WebDriver> fluentWait(){
        return fluentWait(DEFAULT_WAIT_TIME, TimeUnit.SECONDS);
    }

    public FluentWait<WebDriver> fluentWait(int duration, TimeUnit timeUnit){
        return new FluentWait<WebDriver>(driver)
                            .withTimeout(duration, timeUnit)
                            .pollingEvery(200, TimeUnit.MILLISECONDS)
                            .ignoreAll(new ArrayList<Class<? extends Throwable>>(){
                                {
                                    add(StaleElementReferenceException.class);
                                    add(NoSuchElementException.class);
                                }
                            }).withMessage("Selenium Timeout Exception");
    }

    public WebElement waitForElement(final By locator, int timeToWaitInSeconds){

        driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                                .withTimeout(timeToWaitInSeconds, TimeUnit.SECONDS)
                                .pollingEvery(50, TimeUnit.MILLISECONDS)
                                .ignoring(NoSuchElementException.class);

        WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver){
                return driver.findElement(locator);
            }
        });

        driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT_TIME, TimeUnit.SECONDS);

        return foo;
    }

    public WebElement waitForElementDisplayed(final By locator, int timeToWaitInSeconds){

        driver.manage().timeouts().implicitlyWait(100,TimeUnit.MILLISECONDS);
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                                .withTimeout(timeToWaitInSeconds, TimeUnit.SECONDS)
                                .pollingEvery(50, TimeUnit.MILLISECONDS)
                                .ignoring(NoSuchElementException.class);

        WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver){
                WebElement element = driver.findElement(locator);
                if(element != null && element.isDisplayed()){
                    highlight(element);
                    return element;
                }else{
                    return null;
                }
            }
        });

        driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT_TIME, TimeUnit.SECONDS);
        return foo;
    }

    public WebElement textToBePresentInElementLocated(By by, String textToWait, int timeToWait, TimeUnit timeUnit){
        Boolean found = false;
        WebElement element = null;
        try {
            element = driver.findElement(by);
            found = fluentWait(timeToWait, timeUnit).until(ExpectedConditions.textToBePresentInElementLocated(by, textToWait));
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        if(!found) {
            System.out.println("Element with the text '" + textToWait + "' not found");
            return  null;
        }

        return element;
    }

    public void waitForVisibilityOfElement(WebElement element){
        FluentWait<WebDriver> wait = fluentWait();
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void waitForVisibilityOfElement(By locator){
        WebElement element = driver.findElement(locator);
        waitForVisibilityOfElement(element);
    }

    public void waitForPageTitle(String title){
        FluentWait<WebDriver> wait = fluentWait();
        wait.until(ExpectedConditions.titleIs(title));
    }

    public void waitForPageTitleContains(String title){
        FluentWait<WebDriver> wait = fluentWait();
        wait.until(ExpectedConditions.titleContains(title));
    }

    public void waitForInvisibilityOfElement(By locator){
        FluentWait<WebDriver> wait = fluentWait();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void waitForElementAttributeContains(WebElement element,String attributeName, String attributeValue){
        FluentWait<WebDriver> wait = fluentWait();
        wait.until(ExpectedConditions.attributeContains(element,attributeName,attributeValue));
    }
    public void waitForElementAttributeContains(By locator,String attributeName, String attributeValue){
        WebElement element = driver.findElement(locator);
        waitForElementAttributeContains(element,attributeName,attributeValue);
    }

    public void waitForElementTextToBe(By locator, String text){
        FluentWait<WebDriver> wait = fluentWait();
        wait.until(ExpectedConditions.textToBe(locator,text));
    }


    public void scrollToElement(WebElement element){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        delay(2000);
    }


    public String getLastWIndowHandle(){
        Set<String> winHdls = driver.getWindowHandles();
        return winHdls.toArray()[winHdls.size() - 1].toString();
    }

    public void switchToLastWindow(){
        String win = getLastWIndowHandle();
        driver.switchTo().window(win);
    }

    public void closeLastWindow(){
        switchToLastWindow();
        driver.close();
    }

    public void switchToWindow(String winTitle){
        Set<String> winHdls = driver.getWindowHandles();
        Iterator<String> iterator = winHdls.iterator();
        while (iterator.hasNext()){
            String win = iterator.next();
            driver.switchTo().window(win);
            String currentTitle = driver.getTitle();
            if(currentTitle.contains(winTitle)){
                return;
            }
        }
        throw new RuntimeException("Window with the title contain '" + winTitle + "' was not found.");
    }
    public void switchToWindowByURL(String url){
        Set<String> winHdls = driver.getWindowHandles();
        Iterator<String> iterator = winHdls.iterator();
        while (iterator.hasNext()){
            String win = iterator.next();
            driver.switchTo().window(win);
            String currentUrl = driver.getCurrentUrl();
            if(currentUrl.contains(url)){
                return;
            }
        }
        throw new RuntimeException("Window with the URL contain '" + url + "' was not found.");
    }

    public void switchToWindow(int winIndex){
        Set<String> winHdls = driver.getWindowHandles();

        if(winIndex < winHdls.size()) {
            String win = winHdls.toArray()[winIndex].toString();
            driver.switchTo().window(win);
        }
        else
        {
            throw new RuntimeException("Window with the index '" + winIndex + "' not found.");
        }
    }

    public void closeWindow(String title){
        switchToWindow(title);
        driver.close();
        switchToLastWindow();
    }

    public void closeWindow(int winIndex){
        switchToWindow(winIndex);
        driver.close();
        switchToLastWindow();
    }

    public void closeAllOpenWindowExceptCurrent(){
        String currentWindowHnd = driver.getWindowHandle();
        Set<String> windowList = driver.getWindowHandles();
        for(String window : windowList){
            if(!currentWindowHnd.contentEquals(window)){
                driver.switchTo().window(window);
                driver.close();
            }
        }
        driver.switchTo().window(currentWindowHnd);
    }
}
