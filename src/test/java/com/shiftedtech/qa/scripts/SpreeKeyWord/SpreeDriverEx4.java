package com.shiftedtech.qa.scripts.SpreeKeyWord;

import com.shiftedtech.qa.framework.scriptbase.ScriptBaseCompositePOI_TestNG;
import com.shiftedtech.qa.framework.utils.ExcelReader;
import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SpreeDriverEx4 extends ScriptBaseCompositePOI_TestNG {

    /**
     * Another form of KeyWord Driven Testing...More atomic(smaller details like click, typetext, etc. -- For these,
     * small(atomic) keywords, we need to find/locate the WebElement using Xpath, Id, etc., hence, the excel
     * sheet contains 2 extra columns --  i.e., By and Using) rather than composite/bigger (like navigateToLoginPage,
     * Login, etc) keywords.
     *
     * Used here is "KWScripts4.xlsx" and its "Script1" (It has 2 extra columns compared to "KWScripts3.xlsx"...
     * i.e., "By" and "Using").
     *
     * P.S: Need to close all excel files before running the scripts.
     */

    @Test(dataProvider = "scriptNames")
    public void driverScript1(String index, String scriptFile, String sheetName, String runScript) {
        String data[][] = null;

        if (runScript.equalsIgnoreCase("N")) {
            throw new SkipException("Skipping Test with ScriptName: " + sheetName);
        }

        String fileLocation = System.getProperty("user.dir") + scriptFile;
        ExcelReader excelReader = new ExcelReader(fileLocation);
        data = excelReader.getExcelSheetData(sheetName, true);

        for (int i = 0; i < data.length; i++) {
            String stepNumber = data[i][0];
            String keyWord = data[i][1];
            String by = data[i][2];
            String using = data[i][3];
            String keyWordData = data[i][4];

            System.out.println("Performing Step #" + stepNumber + " with KeyWord as: " + keyWord);

            if(keyWord.trim().equalsIgnoreCase("Click")){
                By byObject = homePage.getFindBy(by, using);
                homePage.clickElement(byObject);
            }else if(keyWord.trim().equalsIgnoreCase("TypeText")){
                By byObject = homePage.getFindBy(by, using);
                homePage.typeText(byObject, keyWordData);
            }else if(keyWord.trim().equalsIgnoreCase("verifyText")){
                By byObject = homePage.getFindBy(by, using);
                homePage.verifyText(byObject, keyWordData);   //Will not work for Invalid Login
            }else if (keyWord.trim().equalsIgnoreCase("verifyPageTitle")) {
                homePage.verifyPageTitle(keyWordData);
            } else if (keyWord.trim().equalsIgnoreCase("navigateToLoginPage")) {
                homePage.navigateToLoginPage();
            } else if (keyWord.trim().equalsIgnoreCase("login")) {
                String loginData[] = keyWordData.split("\\|");
                loginPage.loginDetails(loginData[0], loginData[1]);
            } else if (keyWord.trim().equalsIgnoreCase("verifyLoginSuccess")) {
                homePage.signInSuccessMsg();
            } else if (keyWord.trim().equalsIgnoreCase("verifyLoginNotSuccess")) {
                loginPage.signInFailureMsg();
            } else {
                System.out.println("KeyWord is not matching...");
            }
        }

    }

    @DataProvider
    public static Object[][] scriptNames() {
        Object[][] data = null;
        String fileLocation = System.getProperty("user.dir") + "/src/test/Resources/KW-Scripts/KeyWordScriptList2.xlsx";

        ExcelReader excelReader = new ExcelReader(fileLocation);
        data = excelReader.getExcelSheetData("ScriptList", true);

        return data;
    }

}
