package com.shiftedtech.qa.scripts.CrossBrowserTestNg.Parallel_Exec_Methods;

import com.shiftedtech.qa.framework.utils.ExcelReader;
import com.shiftedtech.qa.scripts.CrossBrowserTestNg.Parallel_Exec_Classes.ScriptBaseTestNG_CrossBrowser_Classes;
import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class SpreeLoginTestNg_CB_Methods extends ScriptBaseTestNG_CrossBrowser_Methods {

    @Test
    public void validLogin1(){
        homePage().navigateToLoginPage();
        loginPage().loginDetails("shiftqa01@gmail.com", "shiftedtech");
        homePage().signInSuccessMsg();
    }

    @Test
    public void validLogin2(){
        homePage().navigateToLoginPage();
        loginPage().loginDetails("debajyoti1990@gmail.com", "deb0119");
        homePage().signInSuccessMsg();
    }

    @Test
    public void validLogin3(){
        homePage().navigateToLoginPage();
        loginPage().loginDetails("steveemmet@yahoo.com", "Pickles123");
        homePage().signInSuccessMsg();
    }

}
