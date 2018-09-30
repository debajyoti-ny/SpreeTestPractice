package com.shiftedtech.qa.scripts.OtherTutorials;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.python.antlr.ast.Str;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;


/**
 * Created by Debajyoti Paul on 4/18/2018 at 1:47 AM
 */
public class Extra_Example extends BaseClassOT{

    // @Test is from TestNg in this class
    @Test
    public void test(){
        driver.navigate().to("https://whois.net/");
        WebElement element = driver.findElement(By.xpath("//input[@id='domain_search']"));
        element.sendKeys("syngenta.co.uk");
        element.sendKeys(Keys.ENTER);
        delay(3000);

        WebElement text = driver.findElement(By.xpath("//div[@id='whois_result_data']"));
        String actualText = text.getText();
        System.out.println(actualText);
        writeToExcel("Sheet3", actualText);
    }

    String fileLocation = System.getProperty("user.dir") + "/src/test/Resources/LinkTestResults.xlsx";
    int row = 1;

    public void writeToExcel(String sheetName, String text){
        File file = new File(fileLocation);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);

            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheet(sheetName);
            sheet.createRow(row).createCell(0).setCellValue(text);
            row++;

            FileOutputStream fileOutputStream = new FileOutputStream(file);
            workbook.write(fileOutputStream);
            fileOutputStream.close();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Test
    public void compareMaps()
    {
        Map<String, String> mapA = new HashMap<String, String>();
        mapA.put("1", "abc");
        mapA.put("2", "def");
        Map<String, String> mapB = new HashMap<String, String>();
        mapB.put("1", "abc");
        mapB.put("2", "def");
        Assert.assertEquals(mapA, mapB);
    }

    @Test
    public void abercrombie(){
        ChromeDriverManager.getInstance().setup();
        WebDriver driver = new ChromeDriver();
        driver.navigate().to("https://www.abercrombie.com");

        try{
            WebElement firstPopUp = driver.findElement(By.xpath("(//a[@class='modalCloseImg simplemodal-close'])[last()]"));
            //WebElement secondPopUp =  driver.findElement(By.cssSelector("div[class='modal--show cam is-active']  div[class='modal-label']>a"));
            WebElement secondPopUpModal = driver.findElement(By.xpath("//div[@id='cam']//div[@class='modal-label']"));
            WebElement secondPopUp = secondPopUpModal.findElement(By.xpath("/a"));

            int height = secondPopUpModal.getSize().getHeight();
            int width = secondPopUpModal.getSize().getWidth();
            System.out.println("Height -- " + height);
            System.out.println("Width -- " + width);
            if(firstPopUp.isDisplayed()) {
                firstPopUp.click();
            }else if(secondPopUp.isDisplayed()){
                Actions actions = new Actions(driver);
                actions.moveToElement(secondPopUp).click().build().perform();
//                JavascriptExecutor js = (JavascriptExecutor) driver;
//                js.executeScript("return window.getComputedStyle(arguments[0], ':after')
            }
        }catch (Exception ex){
            System.out.println("Element not found..!!");
        }

    }

    public void jsClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("var el=arguments[0]; setTimeout(function() {e1.click();}, 100);", element);
    }

    @Test
    public void countElements() throws InterruptedException {
        ChromeDriverManager.getInstance().setup();
        WebDriver driver = new ChromeDriver();

        driver.navigate().to("http://www.google.com/");

        List<WebElement> totalElementsInGoogleHomepage = driver.findElements(By.xpath("//*"));
        System.out.println("Total Elements in the page is :" + totalElementsInGoogleHomepage.size());

        ArrayList<String> tagNames = createArrayList(totalElementsInGoogleHomepage);
        searchForDuplicateElements(tagNames);

        Thread.sleep(1500);
        System.out.println("************** Now Moving To GMail Homepage To Find Its Elements **************");
        driver.navigate().to("http://www.gmail.com/");

        List<WebElement> totalElementsInGMail = driver.findElements(By.xpath("//*"));
        System.out.println("Total Elements in the page is :" + totalElementsInGMail.size());

        ArrayList<String> tagNames2 = createArrayList(totalElementsInGMail);
        searchForDuplicateElements(tagNames2);

       try{
       // ***** Easiest way to check if both the maps are equal or not is by using Assert.assertEquals method from TestNg ****
           Assert.assertEquals(searchForDuplicateElements(tagNames), searchForDuplicateElements(tagNames2));  // Assert will fail as expected

       // ***** Another way to check if both the maps are equal or not is by using "equals" method from the List Interface ****
           // boolean result = searchForDuplicateElements(tagNames).equals(searchForDuplicateElements(tagNames2));
           // System.out.println("\nIf both the maps are equal? -- " + result);   // Result will fail as expected

       // ***** Another way to check if both the maps are equal or not is by using your own custom built method with For Loops ****
           // boolean result = mapsAreEqual(searchForDuplicateElements(tagNames), searchForDuplicateElements(tagNames2));
           // System.out.println("If both the maps are equal? -- " + result);   // Result will fail as expected
       }catch (Exception ex){
           System.out.println("Just used try-catch/finally block to finish the test & close the browser since I know that this test will fail..!!");
       }finally {
           Thread.sleep(1500);
           driver.close();   //Close browser
           driver.quit();    //Quit the browser -- Thank you!! [P.S: Don't forget to send the check! ;-)]
       }
    }

    public ArrayList<String> createArrayList(List<WebElement> totalElements) {
        ArrayList<String> tagNames = new ArrayList<>();
        for (WebElement c : totalElements) {
            String text = c.getAttribute("tagName");
            tagNames.add(text);
        }
        return tagNames;
    }

    public LinkedHashMap<String, Integer> searchForDuplicateElements(ArrayList<String> tagNames) {
        LinkedHashMap<String, Integer> elementCountMap = new LinkedHashMap<>();
        for(String tags: tagNames){
            if(elementCountMap.containsKey(tags)){
                elementCountMap.put(tags, elementCountMap.get(tags)+1);
            } else {
               elementCountMap.put(tags, 1);
            }
        }

        Set<String> elements = elementCountMap.keySet();
        System.out.println("Duplicate Elements In " + elements);

        for (String ch : elements){
            if(elementCountMap.get(ch) > 1) {
                System.out.println(ch +" : "+ elementCountMap.get(ch));
            }
        }

        return elementCountMap;
    }

    public boolean mapsAreEqual(Map<String, Integer> firstMap, Map<String, Integer> secondMap) {
        try{
            for (String k : secondMap.keySet()){
                if (!firstMap.get(k).equals(secondMap.get(k))) {
                    return false;
                }
            }
            for (String y : firstMap.keySet()){
                if (!secondMap.containsKey(y)) {
                    return false;
                }
            }
        } catch (NullPointerException np) {
            return false;
        }
        return true;
    }

}
