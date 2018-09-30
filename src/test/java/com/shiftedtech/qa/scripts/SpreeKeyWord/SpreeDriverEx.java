package com.shiftedtech.qa.scripts.SpreeKeyWord;

import com.shiftedtech.qa.framework.scriptbase.ScriptBaseCompositePOI_TestNG;
import com.shiftedtech.qa.framework.utils.ExcelReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SpreeDriverEx extends ScriptBaseCompositePOI_TestNG {

    /**
     * To run multiple sheets(like, "Login01", "Login02", etc.) from a single excel file
     * (like, "KWScript3.xlsx")... We have to use data-provider and the test method will take
     * one input parameter (i.e. "SheetName")
     */

    @Test(dataProvider = "scriptNames")
    public void driverScript1(String sheetName) {
        String data[][] = null;
        String fileLocation = System.getProperty("user.dir") + "/src/test/Resources/KW-Scripts/KWScript3.xlsx";
        ExcelReader excelReader = new ExcelReader(fileLocation);
        data = excelReader.getExcelSheetData(sheetName, true);

        for (int i = 0; i < data.length; i++) {
            String stepNumber = data[i][0];
            String keyWord = data[i][1];
            String keyWordData = data[i][2];

            System.out.println("Performing Step #" + stepNumber + " with KeyWord as: " + keyWord);

            if (keyWord.trim().equalsIgnoreCase("verifyPageTitle")) {
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
    public static Object[][] scriptNames(){
        Object[][] data = {
                            {"Login01"},
                            {"Login02"},
                            {"Login03"},
                            {"Login04"}
                          };
        return data;
    }

}
