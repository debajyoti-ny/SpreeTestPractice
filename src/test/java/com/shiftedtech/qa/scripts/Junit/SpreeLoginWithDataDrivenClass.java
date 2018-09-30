package com.shiftedtech.qa.scripts.Junit;

import com.shiftedtech.qa.framework.scriptbase.ScriptBaseCompositePOI;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(DataProviderRunner.class)
public class SpreeLoginWithDataDrivenClass extends ScriptBaseCompositePOI{

    @Test
    @UseDataProvider(location = JUnitDataProvider.class, value = "loginDataProviderAsExcelPOI")
    public void validEmailValidPassword(String email, String password){
        homePage.navigateToLoginPage();
        loginPage.loginDetails(email, password);
        homePage.signInSuccessMsg();
    }

}
