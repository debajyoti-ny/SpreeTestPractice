package com.shiftedtech.qa.scripts.Junit;

import com.shiftedtech.qa.framework.scriptbase.ScriptBaseCompositePOI;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@RunWith(DataProviderRunner.class)
public class SpreeLoginWithDataDrivenWithApachePOI extends ScriptBaseCompositePOI{

    @Test
    @UseDataProvider(value = "loginDataProviderAsExcelPOI")
    public void validEmailValidPassword(String email, String password){
        homePage.navigateToLoginPage();
        loginPage.loginDetails(email, password);
        homePage.signInSuccessMsg();
    }

    @DataProvider
    public static Object[][] loginDataProviderAsArray(){
        Object[][] data = {
                                {"shiftqa01@gmail.com","shiftedtech"},
                                {"debajyoti1990@gmail.com", "deb0119"},
                                {"hema0ahmad@gmail.com", "hema123"}
                          };
        return data;
    }

    @DataProvider
    public static Object[][] loginDataProviderAsExcelPOI(){
        Object[][] data = null;
        Workbook workbook = null;

        String fileLocation = System.getProperty("user.dir") + "/src/test/Resources/DataDriven_LoginData/SpreeLoginData.xls";

        File file = new File(fileLocation);
        if(file.exists()){
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                if(FilenameUtils.getExtension(fileLocation).contains("xls")){
                    workbook = new HSSFWorkbook(fileInputStream);
                }else if(FilenameUtils.getExtension(fileLocation).contains("xlsx")){
                    workbook = new XSSFWorkbook(fileInputStream);
                }else{
                    throw new RuntimeException("Unknown file type...");
                }

                Sheet sheet = workbook.getSheet("Sheet1");
                int rows = sheet.getLastRowNum();
                int cols = sheet.getRow(sheet.getLastRowNum()).getPhysicalNumberOfCells();
                data = new Object[rows][cols];
                Object cellData = null;
                for(int i=1; i<=rows; i++){
                    for(int j=0; j<cols; j++){
                        Cell cell = sheet.getRow(i).getCell(j);
                        CellType cellType = cell.getCellTypeEnum();
                        if(cellType == CellType.STRING){
                            cellData = cell.getStringCellValue();
                        }else if(cellType == CellType.NUMERIC){
                            cellData = cell.getNumericCellValue();
                        }else if(cellType == CellType.BOOLEAN){
                            cellData = cell.getBooleanCellValue();
                        }else{
                            System.out.println("Unknown cell type...");
                        }
                        String cellDataConverted = cellData.toString();
                        data[i-1][j] = cellDataConverted;
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return data;

    }

}
