package com.shiftedtech.qa.scripts.CrossBrowserTestNg.Parallel_Exec_Classes;

import org.testng.annotations.Test;

public class SpreeLoginTestNg_CB_Class2 extends ScriptBaseTestNG_CrossBrowser_Classes {

    @Test
    public void validLogin2(){
        homePage.navigateToLoginPage();
        loginPage.loginDetails("debajyoti1990@gmail.com", "deb0119");
        homePage.signInSuccessMsg();
    }
}
