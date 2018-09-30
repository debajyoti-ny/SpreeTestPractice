package com.shiftedtech.qa.scripts.TestNg;

import com.shiftedtech.qa.framework.scriptbase.ScriptBaseCompositePOI_TestNG;
import com.shiftedtech.qa.framework.utils.ExcelReader;
import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

/**
 * We can mix match both Junit and TestNg frameworks...
 * The second data-provider is written with Junit excel reader and used with TestNg annotations...
 */

public class SpreeLoginWithDataDrivenTestNg2 extends ScriptBaseCompositePOI_TestNG{

    @Test(dataProvider = "loginDataProviderAsExcel")
    public void validEmailValidPassword(LoginData loginData){
        System.out.println("Running Test: " + loginData.getId());

        if(loginData.isSkipped()){
            throw new SkipException("Skipping this Test...");
        }

        homePage.navigateToLoginPage();
        loginPage.loginDetails(loginData.getEmail().trim(), loginData.getPassword().trim());

        if(loginData.getDescription().contains("Invalid"))
        {
            loginPage.signInFailureMsg();
        }
        else
        {
            homePage.signInSuccessMsg();
        }
    }

    @DataProvider
    public static Object[][] loginDataProviderAsArray(){
        Object[][] data = new Object[4][1];
        data[0][0] = new LoginData("TC001", "Basic Data", "shiftqa01@gmail.com","shiftedtech");
        data[1][0] = new LoginData("TC002","Basic data","debajyoti1990@gmail.com","deb0119");

            LoginData data1 = new LoginData();
            data1.setId("TC003");
            data1.setDescription("Basic Data");
            data1.setEmail("hema0ahmad@gmail.com");
            data1.setPassword("hema123");
            data1.setSkipped(true);
        data[2][0] = data1;

        data[3][0] = new LoginData("TC004","Invalid User","invalid007@gmail.com","deb0119");

        return data;
    }

    @DataProvider
    public static Object[][] loginDataProviderAsExcel(){
        Object[][] data = null;
        String fileLocation = System.getProperty("user.dir") + "/src/test/Resources/DataDriven_LoginData/SpreeLoginData2.xls";
        File file = new File(fileLocation);
        if(file.exists()){
            try {
                Workbook workbook = Workbook.getWorkbook(file);
                Sheet sheet = workbook.getSheet("Sheet1");
                data = new Object[sheet.getRows()-1][sheet.getColumns()];
                String cellData = null;
                for(int j=0; j<sheet.getColumns(); j++){
                    for(int i=1; i<sheet.getRows(); i++){
                        Cell cell = sheet.getCell(j,i);
                        CellType cellType = cell.getType();
                        if(cellType == CellType.LABEL){
                            cellData = cell.getContents();
                        }else if(cellType == CellType.NUMBER){
                            cellData = cell.getContents().toString();
                        }else if(cellType == CellType.BOOLEAN) {
                            cellData = cell.getContents().toString();
                        }else{
                            System.out.println("Unknown Type -- Currently not supported...");
                        }
                        data[i-1][j] = cellData;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (BiffException e) {
                e.printStackTrace();
            }
        }
//        return data;
        Object[][] modifiedData = new Object[data.length][1];
        for(int i=0; i<data.length; i++){
            LoginData data1 = new LoginData();
            data1.setId(data[i][0].toString());
            data1.setDescription(data[i][1].toString());
            data1.setEmail(data[i][2].toString());
            data1.setPassword(data[i][3].toString());
            data1.setSkipped(Boolean.parseBoolean(data[i][4].toString().toLowerCase()));

            modifiedData[i][0] = data1;
        }

        return modifiedData;

    }

    @DataProvider
    public static Object[][] loginDataProviderAsExcelPOI(){
        Object[][] data = null;

        String fileLocation = System.getProperty("user.dir") + "/src/test/Resources/DataDriven_LoginData/SpreeLoginData2.xls";
        ExcelReader excelReader = new ExcelReader(fileLocation);
        data = excelReader.getExcelSheetData("Sheet1", true);

//        return data;

        Object[][] modifiedData = new Object[data.length][1];
        for(int i=0; i<data.length; i++) {
            LoginData data1 = new LoginData();
            data1.setId(data[i][0].toString());
            data1.setDescription(data[i][1].toString());
            data1.setEmail(data[i][2].toString());
            data1.setPassword(data[i][3].toString());
//            String str = data[i][4].toString().toLowerCase();
            data1.setSkipped(Boolean.parseBoolean(data[i][4].toString().toLowerCase()));

            modifiedData[i][0] = data1;
        }
        return modifiedData;
    }

}
