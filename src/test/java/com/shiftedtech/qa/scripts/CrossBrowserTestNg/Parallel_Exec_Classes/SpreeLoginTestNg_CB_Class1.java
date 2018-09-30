package com.shiftedtech.qa.scripts.CrossBrowserTestNg.Parallel_Exec_Classes;

import org.testng.annotations.Test;

/**
 * This class is used to show testNG parallel execution with classes.
 */

public class SpreeLoginTestNg_CB_Class1 extends ScriptBaseTestNG_CrossBrowser_Classes {

    @Test
    public void validLogin1(){
        homePage.navigateToLoginPage();
        loginPage.loginDetails("shiftqa01@gmail.com", "shiftedtech");
        homePage.signInSuccessMsg();
    }
}
