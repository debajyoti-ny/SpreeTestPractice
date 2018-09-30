package com.shiftedtech.qa.scripts.HeadlessBrowserTesting.PhantomJS;

import com.shiftedtech.qa.framework.utils.ExcelReader;
import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

/**
 * We can mix match both Junit and TestNg frameworks...
 * The second dataprovider is written with Junit excel reader and used with TestNg annotations...
 */

public class SpreeLoginWithDataDrivenTestNg_Phantom extends ScriptBaseTestNG_PhantomJS {

    @Test(dataProvider = "loginDataProviderAsArray")
    public void validEmailValidPassword(String email, String password){
        homePage.navigateToLoginPage();
        loginPage.loginDetails(email, password);
        homePage.signInSuccessMsg();
    }

    @DataProvider
    public static Object[][] loginDataProviderAsArray(){
        Object[][] data = {
                                {"shiftqa01@gmail.com","shiftedtech"},
                                {"debajyoti1990@gmail.com", "deb0119"}
//                                ,{"hema0ahmad@gmail.com", "hema123"}
                          };
        return data;
    }

    @DataProvider
    public static Object[][] loginDataProviderAsExcel(){
        Object[][] data = null;
        String fileLocation = System.getProperty("user.dir") + "/src/test/Resources/DataDriven_LoginData/SpreeLoginData.xls";
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
        return data;
    }

    @DataProvider
    public static Object[][] loginDataProviderAsExcelPOI(){
        Object[][] data = null;

        String fileLocation = System.getProperty("user.dir") + "/src/test/Resources/DataDriven_LoginData/SpreeLoginData.xls";
        ExcelReader excelReader = new ExcelReader(fileLocation);
        data = excelReader.getExcelSheetData("Sheet1", true);

        return data;
    }

}
