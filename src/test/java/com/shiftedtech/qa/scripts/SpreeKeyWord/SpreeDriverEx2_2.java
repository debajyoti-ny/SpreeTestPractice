package com.shiftedtech.qa.scripts.SpreeKeyWord;

import com.shiftedtech.qa.framework.scriptbase.ScriptBaseCompositePOI_TestNG;
import com.shiftedtech.qa.framework.utils.ExcelReader;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SpreeDriverEx2_2 extends ScriptBaseCompositePOI_TestNG {

    /**
     * To run all the different files and their corresponding sheets(which is written in an array below)
     * in one RUN (provided it has a "Y" next to it as the "runscript" option)
     *  We have to use data-provider and the test method will take three input parameters
     * (i.e. "ScriptFile", "SheetName" and "runScript")
     */

    @Test(dataProvider = "scriptNames")
    public void driverScript1(String scriptFile, String sheetName, String runScript) {
        String data[][] = null;

        if(runScript.equalsIgnoreCase("N")){
            throw new SkipException("Skipping Test with ScriptName: " + sheetName);
        }

        String fileLocation = System.getProperty("user.dir") + scriptFile;
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
                            {"/src/test/Resources/KW-Scripts/KWScript1.xlsx", "Login01", "Y"},
                            {"/src/test/Resources/KW-Scripts/KWScript2.xlsx", "Login02", "Y"},
                            {"/src/test/Resources/KW-Scripts/KWScript3.xlsx", "Login01", "N"},
                            {"/src/test/Resources/KW-Scripts/KWScript3.xlsx", "Login02", "N"},
                            {"/src/test/Resources/KW-Scripts/KWScript3.xlsx", "Login03", "Y"},
                            {"/src/test/Resources/KW-Scripts/KWScript3.xlsx", "Login04", "N"}
                          };
        return data;
    }

}
