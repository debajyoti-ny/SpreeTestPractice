package com.shiftedtech.qa.scripts.OtherTutorials;

import com.shiftedtech.qa.framework.utils.ExcelReader;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Set;

@RunWith(DataProviderRunner.class)
public class VerifyLink extends BaseClassOT {

    String fileLocation = System.getProperty("user.dir") + "/src/test/Resources/LinkTestResults.xlsx";
    int i=1;

    @Test
    public void testLinks() throws IOException{
        //driver.navigate().to("https://google.com");
        driver.navigate().to("https://www.syngenta.co.uk/");
        driver.manage().deleteAllCookies();

        List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println("Total links are " + links.size());

        for(int i=0;i<links.size();i++){
            WebElement ele= links.get(i);
            String url=ele.getAttribute("href");
            System.out.println("Url #" + (i+1) + " is : " + url);
            verifyLinkActive(url, "Sheet4");
        }
    }

    @Test
    public void brokenImageTest() throws Exception {
        driver.navigate().to("http://spree.shiftedtech.com/");

        List<WebElement> imageLinks = driver.findElements(By.tagName("img"));
        System.out.println("Total Image: " + imageLinks.size());

        for(int i = 0; i < imageLinks.size(); i++){

            WebElement element = imageLinks.get(i);
            String link = element.getAttribute("src");
            verifyLinkActive(link, "Sheet1");
        }
    }

    public void verifyLinkActive(String linkUrl, String excelSheetName) throws IOException
    {
        try
        {
            URL url = new URL(linkUrl);
            HttpURLConnection httpURLConnect = (HttpURLConnection)url.openConnection();  //To open the connection
            httpURLConnect.setConnectTimeout(3000);
            httpURLConnect.connect();
            if(httpURLConnect.getResponseCode() == 200){   //Code 200 means connections is fine (Link NOT Broken)
                System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage());
                writeToExcel(excelSheetName, linkUrl.toString(), httpURLConnect.getResponseMessage());
            }if(httpURLConnect.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND){
                System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage() + " - " + HttpURLConnection.HTTP_NOT_FOUND);
                writeToExcel(excelSheetName, linkUrl.toString(), httpURLConnect.getResponseMessage());
            }if(httpURLConnect.getResponseCode() != 200 || httpURLConnect.getResponseCode() != 404){
                System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage());
                writeToExcel(excelSheetName, linkUrl.toString(), httpURLConnect.getResponseMessage());
            }
        } catch (Exception e) {

        }
    }

    public void verifyLinkActiveEx(String linkUrl, String excelSheetName) throws IOException
    {
        try
        {
            URL url = new URL(linkUrl);
            HttpURLConnection httpURLConnect = (HttpURLConnection)url.openConnection();  //To open the connection
            httpURLConnect.setConnectTimeout(3000);
            Set<Cookie> cookies = driver.manage().getCookies();
            String cookieString = "";
            for (Cookie cookie: cookies) {
                cookieString += cookie.getName() + "=" + cookie.getValue() + ";";
            }
            httpURLConnect.addRequestProperty("Cookies", cookieString);
            httpURLConnect.setRequestMethod("HEAD");
            httpURLConnect.connect();
            if(httpURLConnect.getResponseCode() == 200){   //Code 200 means connections is fine (Link NOT Broken)
                System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage());
                writeToExcel(excelSheetName, linkUrl.toString(), httpURLConnect.getResponseMessage());
            }if(httpURLConnect.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND){
                System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage() + " - " + HttpURLConnection.HTTP_NOT_FOUND);
                writeToExcel(excelSheetName, linkUrl.toString(), httpURLConnect.getResponseMessage());
            }if(httpURLConnect.getResponseCode() != 200 || httpURLConnect.getResponseCode() != 404){
                System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage());
                writeToExcel(excelSheetName, linkUrl.toString(), httpURLConnect.getResponseMessage());
            }
        } catch (Exception e) {

        }
    }

    public void writeToExcel(String sheetName, String link, String result) throws IOException {
        File file = new File(fileLocation);
        FileInputStream fileInputStream = new FileInputStream(file);
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheet(sheetName);
        sheet.createRow(i).createCell(0).setCellValue(link);
        sheet.getRow(i).createCell(1).setCellValue(result);
        i++;

        FileOutputStream fileOutputStream = new FileOutputStream(file);
        workbook.write(fileOutputStream);
        fileOutputStream.close();
    }

    @Test
    @UseDataProvider(value = "linkURLs")
    public void testLinks2(String url) throws IOException {
        driver.manage().deleteAllCookies();
        driver.navigate().to("https://www.syngenta.co.uk/");
        verifyLinkActive(url, "Sheet5");
    }

    @Test
    public void testLink3() throws IOException {
        FirefoxDriverManager.getInstance().setup();
        driver = new FirefoxDriver();
        driver.manage().deleteAllCookies();
        String url_1 = "https://www.syngenta.fr";
        String url_2 = "https://www.syngenta.de";
        verifyLinkActiveEx(url_1, "Sheet5");
        verifyLinkActiveEx(url_2, "Sheet5");
    }

    @DataProvider
    public static Object[][] linkURLs(){
        Object[][] data = null;

        String fileLocation = System.getProperty("user.dir") + "/src/test/Resources/sitename.xlsx";
        ExcelReader excelReader = new ExcelReader(fileLocation);
        data = excelReader.getExcelSheetData("Sheet1", false);

        return data;
    }

    @AfterClass
    public static void tearDown(){
//        driver.close();
        driver.quit();
    }

}
