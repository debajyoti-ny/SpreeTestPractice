package com.shiftedtech.qa.scripts.CrossBrowserTestNg.Test_Run_On_Cloud_Grid;

import org.testng.annotations.Test;

public class SpreeLoginTestNg_CB_Cloud extends ScriptBaseTestNG_CrossBrowser_Cloud {

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
