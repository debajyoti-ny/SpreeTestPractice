package com.shiftedtech.qa.scripts.SpreeKeyWord;

import com.shiftedtech.qa.framework.scriptbase.ScriptBaseCompositePOI_TestNG;
import com.shiftedtech.qa.framework.utils.ExcelReader;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SpreeDriverEx3 extends ScriptBaseCompositePOI_TestNG {

    /**
     * To run certain tests only... but this time, instead of hardcoding it inside the script as an Array,
     * it will be written in an Excel file. So, it's like one excel file(ex, "KeywordScriptList.xlsx")
     * will have a column with locations of other excel files whose scripts/data we need to run(provided, they
     * have a "Y" as the "runscript" option next to it).
     *
     * The index, scriptFile, scriptName, runScript all come from the "KeywordScriptList.xlsx" excel file
     * which was converted into an array("scriptNames") and then passed onto this method.
     *
     * P.S: Note that, even though "index" as a parameter is never used, we still have to keep it
     * as one of the parameters, otherwise, it will show a "data-provider mismatch error"
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
    public static Object[][] scriptNames() {
        Object[][] data = null;
        String fileLocation = System.getProperty("user.dir") + "/src/test/Resources/KW-Scripts/KeyWordScriptList.xlsx";

        ExcelReader excelReader = new ExcelReader(fileLocation);
        data = excelReader.getExcelSheetData("ScriptList", true);

        return data;
    }

}
