package com.shiftedtech.qa.scripts.SpreeKeyWord;

import com.shiftedtech.qa.framework.scriptbase.ScriptBaseCompositePOI_TestNG;
import com.shiftedtech.qa.framework.utils.ExcelReader;
import org.testng.annotations.Test;

public class SpreeDriver extends ScriptBaseCompositePOI_TestNG{

    /**
     * To verify Login Successful with valid credentials
     */

    @Test
    public void driverScript(){
        String[][] data = null;
        String fileLocation = System.getProperty("user.dir") + "/src/test/Resources/KW-Scripts/KWScript1.xlsx";

        ExcelReader excelReader = new ExcelReader(fileLocation);
        data = excelReader.getExcelSheetData("Login01", true);

        for(int i=0; i<data.length; i++){
            String stepNumber = data[i][0].toString();
            String keyWord = data[i][1];
            String keyWordData = data[i][2];

            System.out.println("Performing Step #" + stepNumber + " with KeyWord as: " + keyWord);

            if(keyWord.trim().equalsIgnoreCase("verifyPageTitle")){
                homePage.verifyPageTitle(keyWordData);
            }else if(keyWord.trim().equalsIgnoreCase("navigateToLoginPage")) {
                homePage.navigateToLoginPage();
            }else if(keyWord.trim().equalsIgnoreCase("login")) {
                String loginData[] = keyWordData.split("\\|");
                loginPage.loginDetails(loginData[0], loginData[1]);
            }else if(keyWord.trim().equalsIgnoreCase("verifyLoginSuccess")) {
                homePage.signInSuccessMsg();
            }else if(keyWord.trim().equalsIgnoreCase("verifyLoginNotSuccess")){
                loginPage.signInFailureMsg();
            }else {
                System.out.println("KeyWord is not matching...");
            }

        }
    }

    /**
     * To verify Login Successful with Invalid credentials
     */

    @Test
    public void driverScript2(){
        String data[][] = null;
        String fileLocation = System.getProperty("user.dir") + "/src/test/Resources/KW-Scripts/KWScript2.xlsx";
        ExcelReader excelReader = new ExcelReader(fileLocation);
        data = excelReader.getExcelSheetData("Login02", true);

        for(int i=0; i<data.length; i++){
            String stepNumber = data[i][0];
            String keyWord = data[i][1];
            String keyWordData = data[i][2];

            System.out.println("Performing Step #" + stepNumber + " with KeyWord as: " + keyWord);

            if(keyWord.trim().equalsIgnoreCase("verifyPageTitle")){
                homePage.verifyPageTitle(keyWordData);
            }else if(keyWord.trim().equalsIgnoreCase("navigateToLoginPage")){
                homePage.navigateToLoginPage();
            }else if(keyWord.trim().equalsIgnoreCase("login")){
                String loginData[] = keyWordData.split("\\|");
                loginPage.loginDetails(loginData[0], loginData[1]);
            }else if(keyWord.trim().equalsIgnoreCase("verifyLoginSuccess")) {
                homePage.signInSuccessMsg();
            }else if(keyWord.trim().equalsIgnoreCase("verifyLoginNotSuccess")){
                loginPage.signInFailureMsg();
            }else{
                System.out.println("KeyWord is not matching...");
            }
        }

    }

    /**
     * To verify Successful/Unsuccessful Login when excel contains both valid and invalid credentials.
     * Just need to change the Sheet Number.
     *
     * The test below(Login03) will fail because of incorrect password.
     */

    @Test
    public void driverScript3(){
        String data[][] = null;
        String fileLocation = System.getProperty("user.dir") + "/src/test/Resources/KW-Scripts/KWScript3.xlsx";
        ExcelReader excelReader = new ExcelReader(fileLocation);
        data = excelReader.getExcelSheetData("Login03", true);

        for(int i=0; i<data.length; i++){
            String stepNumber = data[i][0];
            String keyWord = data[i][1];
            String keyWordData = data[i][2];

            System.out.println("Performing Step #" + stepNumber + " with KeyWord as: " + keyWord);

            if(keyWord.trim().equalsIgnoreCase("verifyPageTitle")){
                homePage.verifyPageTitle(keyWordData);
            }else if(keyWord.trim().equalsIgnoreCase("navigateToLoginPage")){
                homePage.navigateToLoginPage();
            }else if(keyWord.trim().equalsIgnoreCase("login")){
                String loginData[] = keyWordData.split("\\|");
                loginPage.loginDetails(loginData[0], loginData[1]);
            }else if(keyWord.trim().equalsIgnoreCase("verifyLoginSuccess")) {
                homePage.signInSuccessMsg();
            }else if(keyWord.trim().equalsIgnoreCase("verifyLoginNotSuccess")){
                loginPage.signInFailureMsg();
            }else{
                System.out.println("KeyWord is not matching...");
            }
        }

    }

}
