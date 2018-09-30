package com.shiftedtech.qa.scripts.Junit;

import com.shiftedtech.qa.framework.scriptbase.ScriptBaseComposite;
import com.shiftedtech.qa.framework.scriptbase.ScriptBaseCompositePOI;
import org.junit.Test;

public class SpreeLoginWithScriptBaseComposite_POI extends ScriptBaseCompositePOI{

    @Test
    public void validEmailValidPassword (){
        homePage.navigateToLoginPage();
        loginPage.loginDetails("deba007190@gmail.com", "deb0119");
        homePage.signInSuccessMsg();
    }

    @Test
    public void validEmailInvalidPassword(){
        homePage.navigateToLoginPage();
        loginPage.loginDetails("deba007190@gmail.com", "invalid");
        loginPage.signInFailureMsg();
    }

    @Test
    public void invalidEmailValidPassword(){
        homePage.navigateToLoginPage();
        loginPage.loginDetails("deba007190xx@gmail.com", "deb0119");
        loginPage.signInFailureMsg();
    }

    @Test
    public void invalidEmailInvalidPassword(){
        homePage.navigateToLoginPage();
        loginPage.loginDetails("deba007190xx@gmail.com", "invalid");
        loginPage.signInFailureMsg();
    }
}
