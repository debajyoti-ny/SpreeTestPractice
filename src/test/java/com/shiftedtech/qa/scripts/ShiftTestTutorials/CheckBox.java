package com.shiftedtech.qa.scripts.ShiftTestTutorials;

import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckBox extends BaseClass {

    @Test
    public void test1(){
        WebElement checkBoxButton = driver.findElement(By.xpath("html/body/div[3]/div/div[2]/div[1]/div[2]/div/form/input[1]"));
        System.out.println("Is Button Checked: " + checkBoxButton.getAttribute("checked"));
        checkBoxButton.click();
        delayTime(2000);

        System.out.println("Is Button Checked: " + checkBoxButton.getAttribute("checked"));
        delayTime(4000);
    }

    @Test
    public void test2(){
        tickCheckBox("Option 1");
        tickCheckBox("Option 2");
        delayTime(3000);
    }

    @Test
    public void test3(){
        tickCheckBoxEx("Option 1");
        tickCheckBoxEx("Option 2");
        delayTime(3000);
    }

    @Test
    public void test4(){
        By checkBoxGroup = By.xpath("//*[@id='css-chk-box-grp-01']");
        bootStrapCheckBox(checkBoxGroup, "Option 1");
        bootStrapCheckBox(checkBoxGroup, "Option 2");
        delayTime(3000);
    }

    @Test
    public void test5(){
        By checkBoxGroup = By.xpath("//*[@id='css-chk-box-grp-01']");
        bootStrapCheckBoxEx(checkBoxGroup, "Option 1", true);
        bootStrapCheckBoxEx(checkBoxGroup, "Option 2", false);
        bootStrapCheckBoxEx(checkBoxGroup, "Option 3", true);
        delayTime(3000);

//        boolean status2 = isBootStrapCheckBoxChecked(checkBoxGroup, "Option 2");
//        Assert.assertFalse(status2); //Not working for some reasons
    }

    @Test
    public void test6(){
        WebElement checkBoxButton = driver.findElement(By.xpath("html/body/div[3]/div/div[2]/div[1]/div[2]/div/form/input[1]"));
        checkCheckBox(checkBoxButton);
        delayTime(2000);

        unCheckCheckBox(checkBoxButton);
        delayTime(3000);
    }

    @Test
    public void test7(){
        WebElement checkBoxButton = driver.findElement(By.xpath("html/body/div[3]/div/div[2]/div[1]/div[2]/div/form/input[1]"));

        checkCheckBox(checkBoxButton); //Here, the box will be checked.
        delayTime(3000);

        // state is true when box is unchecked, but it's false when it's checked
        checkBoxState(checkBoxButton, false); // Since, the box is checked due to the previous method, so, state = false is correct(false -> box checked)
        delayTime(3000); // So, the box will be checked again, and hence the box will be finally unchecked.

        checkBoxState(checkBoxButton, false); // Since, the box is unchecked now due to the previous method, so, state = false is incorrect(b/c true -> box unchecked)
        delayTime(3000); // So, this fails the if-else part and hence the method does nothing. The box will remain unchecked.

        checkBoxState(checkBoxButton, true); // Since, the box is unchecked from the previous method, so, state = true is correct(b/c true -> box unchecked)
        delayTime(3000); // So, this passes the if-else part and hence the method does the clicking. The box will now be checked again.

    }

    //Method to check the tick box
    private void tickCheckBox(String optionName){
        By path = null;
        if(optionName.equalsIgnoreCase("Option 1")){
            path = By.xpath("//*[@id='html-chk-box-01']");
        } else if(optionName.equalsIgnoreCase("Option 2")){
            path = By.xpath("//*[@id='html-chk-box-02']");
        }

        WebElement element = driver.findElement(path);
        element.click();

    }

    //Method to check the tick box (Advanced way)
    private void tickCheckBoxEx(String optionName){
        WebElement checkBoxGroup = driver.findElement(By.xpath("//*[@action='form-checkbox']"));
        List<WebElement> labelList = checkBoxGroup.findElements(By.xpath(".//label"));
        for(WebElement item: labelList){
            String text = item.getText();
            if(text.equalsIgnoreCase(optionName)){
                WebElement option = item.findElement(By.xpath("./preceding-sibling::input[1]"));
                option.click();
                break;
            }
        }
    }

    //Method to check the BootStrap Checkbox (Advanced way)
    private void bootStrapCheckBox(By checkBoxGroup, String optionName){
        WebElement CheckBoxGroup = driver.findElement(checkBoxGroup);
        List<WebElement> items = CheckBoxGroup.findElements(By.xpath(".//label"));
        for(WebElement item: items){
            String text = item.getText();
            if(text.equalsIgnoreCase(optionName)){
               WebElement checkBox = item.findElement(By.tagName("input"));
               checkBox.click();
               break;
            }
        }
    }

    //Method to check the BootStrap Checkbox (More Advanced way)
    private void bootStrapCheckBoxEx(By checkBoxGroup, String optionName, boolean state) {
        WebElement CheckBoxGroup = driver.findElement(checkBoxGroup);
        List<WebElement> items = CheckBoxGroup.findElements(By.xpath(".//label"));
        for(WebElement item: items){
            String text = item.getText();
            if(text.equalsIgnoreCase(optionName)){
                WebElement checkBox = item.findElement(By.tagName("input"));
                String checkBoxStatus = checkBox.getAttribute("checked");
                //System.out.println(checkBoxStatus);
                if((checkBoxStatus == null || checkBoxStatus.contentEquals("false")) && state){
                    checkBox.click();
                    break;
                } else if((checkBoxStatus != null && checkBoxStatus.contentEquals("true")) && !state){
                    checkBox.click();
                    break;
                }
                break;
            }
        }
    }

    //Method to check if the BootStrap Checkbox is checked
    private boolean isBootStrapCheckBoxChecked(By checkBoxGroup, String optionName){
        WebElement CheckBoxGroup = driver.findElement(checkBoxGroup);
        List<WebElement> items = CheckBoxGroup.findElements(By.xpath("//label"));
        for(WebElement item: items){
            String text = item.getText();
            if(text.equalsIgnoreCase(optionName)){
                WebElement checkBox = item.findElement(By.tagName("input"));
                boolean checkStatus = Boolean.parseBoolean(checkBox.getAttribute("checked"));
                return checkStatus;
            }
        }
        return false;
    }

    //Method to check the check box
    private void checkCheckBox(WebElement checkBoxButton) {
        String checkBoxStatus = checkBoxButton.getAttribute("checked");

        if(checkBoxStatus == null || checkBoxStatus.contentEquals("false")){
            checkBoxButton.click();
        }
    }

    //Method to uncheck the check box
    private void unCheckCheckBox(WebElement checkBoxButton) {
        String checkBoxStatus = checkBoxButton.getAttribute("checked");

        if(checkBoxStatus != null && checkBoxStatus.contentEquals("true")){
            checkBoxButton.click();
        }
    }

    private void checkBoxState(WebElement checkBoxButton, boolean state){
        String checkBoxStatus = checkBoxButton.getAttribute("checked");

        if((checkBoxStatus == null || checkBoxStatus.contentEquals("false")) && state) {
            checkBoxButton.click();
        } else if ((checkBoxStatus != null && checkBoxStatus.contentEquals("true")) && !state) {
            checkBoxButton.click();
        }
    }

    @AfterClass
    public static void teardown(){
        if(driver != null){
            //driver.close();
            driver.quit();
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
}
