package com.shiftedtech.qa.scripts.ShiftTestTutorials;

import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Iterator;
import java.util.Set;

public class MultipleWindow extends BaseClass {

    @Test
    public void test1(){
        String winHandle = driver.getWindowHandle(); //Tells you the current window you're on now
        System.out.println("Current window handle: " + winHandle);

        Set<String> winList = driver.getWindowHandles(); //Returns a set of all the windows
        System.out.println("Window count: " + winList.size()); //It will print out 1 window since there is only one window which is open

        WebElement element = driver.findElement(By.xpath(".//button[text()='Open New Window']"));
        element.click();

        Set<String> winListNew = driver.getWindowHandles(); //It will return a set of two windows
        System.out.println("Window count after clicking the button: " + winListNew.size()); //It will print out 2 windows since there is only two window that are open

//        Object[] winArray = winList.toArray();
//        String lastOpenedWindow1 = winArray[winArray.length-1].toString(); // Same could be written as one line as shown below
        String lastOpenedWindow = winListNew.toArray()[winListNew.size()-1].toString(); //Returns the last window that opened
        System.out.println("New window handle that opened after clicking the button is: " + lastOpenedWindow);

        driver.switchTo().window(lastOpenedWindow); //Code to switch to the new window that has opened
        System.out.println("Window Title: " + driver.getTitle());
        driver.close(); //to close the current window

//        driver.switchTo().window(winHandle); //switch back to the 1st window after closing the new one
                                                //Could also be done in the "AfterClass" method
    }

    @Test
    public void test2(){

        WebElement element = driver.findElement(By.xpath(".//button[text()='Open New Window']"));
        element.click();

        switchToLastWindow(); //Switches to the new window

        System.out.println("Win Title (After clicking the button) is: " + driver.getTitle()); //Prints out the title of the new window which has just opened
        closeLastWindow(); //Close the window that just opened

        switchToLastWindow(); //Switch back to the previous page (first page in this case)

    }


    @Test
    public void test3() {

        WebElement element = driver.findElement(By.xpath(".//button[text()='Open New Window']"));
        element.click();

        switchToWindow("Form Testing – Shift Education of Technology"); //Switch to window with the following Title

        System.out.println("Window Title: " + driver.getTitle()); //Verify that the switched to page contains the same title

        driver.close(); //Close the current window
        switchToLastWindow(); //Get back to the previous window

    }

    @Test
    public void test3_URL() {

        WebElement element = driver.findElement(By.xpath(".//button[text()='Open New Window']"));
        element.click();

        switchToWindowbyURL("http://shifttest.shiftedtech.com/components/form"); //Switch to window with the following URL

        System.out.println("Window URL: " + driver.getCurrentUrl()); //Verify that the switched to page contains the same URL

        driver.close(); //Close the current window
        switchToLastWindow(); //Get back to the previous window

    }

    @Test
    public void test4() {

        String winHandle = driver.getWindowHandle();
        System.out.println("Current Window Handle: " + winHandle);

        WebElement element = driver.findElement(By.xpath(".//button[text()='Open Windows']")); //This opens multiple windows
        element.click();

        System.out.println("close All Open Window Except Current");
        closeAllOpenWindowExceptCurrent();

        String winHandle2 = driver.getWindowHandle();
        System.out.println("Current Window Handle: " + winHandle2);
    }

    public String getLastWindowHandle(){
        Set<String> listOfWindows = driver.getWindowHandles();
        return listOfWindows.toArray()[listOfWindows.size()-1].toString(); //Returns the last windows (#2 in this case) as a string
    }

    public void switchToLastWindow(){
        String win = getLastWindowHandle(); //Calls the previous method to get the last window
        driver.switchTo().window(win); //Switches to the "win"(the last window -- #2 in this case) window
    }

    public void closeLastWindow(){
        switchToLastWindow(); //Switch to last window using the previous method
        driver.close();
    }

    //Method to switch to a window by using the "page title"
    public void switchToWindow(String winTitle){
        Set<String> winHandles = driver.getWindowHandles(); //Provides a set of all the windows that has opened
        Iterator<String> iterator = winHandles.iterator();  //To iterate through the Set of elements
        while (iterator.hasNext()){
            String win = iterator.next(); //To get the next window as a String
            driver.switchTo().window(win); //Switch to the next window
            String currentPageTitle = driver.getTitle();
            if(currentPageTitle.contains(winTitle)){
                return;
            }
        }
        throw new RuntimeException("Window with the Title contain '" + winTitle + "' not found.");
    }

    //Method to switch to a window by using the "page URL"
    public void switchToWindowbyURL(String url){
        Set<String> winHandles = driver.getWindowHandles(); //Provides a set of all the windows that has opened
        Iterator<String> iterator = winHandles.iterator();  //To iterate through the Set of elements
        while (iterator.hasNext()){
            String win = iterator.next(); //To get the next window as a String
            driver.switchTo().window(win); //Switch to the next window
            String currentURL = driver.getCurrentUrl(); //Get the current page's URL
            if(currentURL.contains(url)){
                return;
            }
        }
        throw new RuntimeException("Window with the URL '" + url + "' not found.");
    }

    //Method to switch to a window by using the "page Index" (Overloaded Method) (But how to get the index -- Ask teacher... P.S: 0 and 1 isn't working)
    public void switchToWindow(int winIndex){
        Set<String> winHandles = driver.getWindowHandles(); //Provides a set of all the windows that has opened

        if(winIndex < winHandles.size()){
            String win = winHandles.toArray()[winIndex].toString();
            driver.switchTo().window(win);
        }

        throw new RuntimeException("Window with the index '" + winIndex + "' not found.");
    }

    //Method to close a window with a given Title
    public void closeWindow(String title){
        switchToWindow(title); //Switch to the window with the given title
        driver.close();
        switchToLastWindow();
    }

    //Method to close a window with a given Index (But how to get the index -- Ask teacher... P.S: 0 and 1 isn't working)
    public void closeWindow(int winIndex){ //Overloaded Method
        switchToWindow(winIndex); //Switch to the window with the given Index
        driver.close();
        switchToLastWindow();
    }

    public void closeAllOpenWindowExceptCurrent(){
        String currentWindow = driver.getWindowHandle();
        Set<String> windowList = driver.getWindowHandles();
        for(String x : windowList){
            if(!currentWindow.contentEquals(x)){ //If "x" is not the current window, then switch to window "x" and then close it
                driver.switchTo().window(x);
                driver.close();
            }
        }
        driver.switchTo().window(currentWindow); //Switch back to the first window
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
    public static void teardown() {
        if (driver != null) {
            // driver.close();
            driver.switchTo().window(mainWindowHandle);
            driver.quit();
        }
    }
}
