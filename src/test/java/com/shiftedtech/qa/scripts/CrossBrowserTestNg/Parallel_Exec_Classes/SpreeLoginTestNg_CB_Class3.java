package com.shiftedtech.qa.scripts.CrossBrowserTestNg.Parallel_Exec_Classes;

import org.testng.annotations.Test;

public class SpreeLoginTestNg_CB_Class3 extends ScriptBaseTestNG_CrossBrowser_Classes {

    @Test
    public void validLogin3(){
        homePage.navigateToLoginPage();
        loginPage.loginDetails("steveemmet@yahoo.com", "Pickles123");
        homePage.signInSuccessMsg();
    }
}
