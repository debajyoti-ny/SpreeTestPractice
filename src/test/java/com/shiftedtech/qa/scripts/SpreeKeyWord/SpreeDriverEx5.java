package com.shiftedtech.qa.scripts.SpreeKeyWord;

import com.shiftedtech.qa.framework.keyWord_Functionality.KeywordDrivenScriptBase;
import com.shiftedtech.qa.framework.utils.ExcelReader;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SpreeDriverEx5 extends KeywordDrivenScriptBase {
    public static String fileName = System.getProperty("user.dir") + "/src/test/Resources/KW-Scripts/KWScript5.xlsx";

    @Test(dataProvider = "scriptList")
    public void driverScript(String testId, String testDescription, String sheetName, String runScript){
        System.out.println("Test Case Id: " + testId);
        System.out.println("Test Description: " + testDescription);

        if(runScript.trim().equalsIgnoreCase("N")){
            throw new SkipException("Skipping Test Case Id: " + testId);
        }

        keywordProcess(fileName, sheetName);

    }

    @DataProvider
    public static Object[][] scriptList(){
        Object[][] data = null;
        ExcelReader excelReader = new ExcelReader(fileName);
        data = excelReader.getExcelSheetData("TestCase", true);
        return data;
    }
}
