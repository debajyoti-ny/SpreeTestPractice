package com.shiftedtech.qa.scripts.Junit;

import com.shiftedtech.qa.framework.scriptbase.ScriptBase;
import org.junit.Test;

public class SpreeLoginWithScriptBase extends ScriptBase{

    @Test
    public void validEmailValidPassword (){
        loginDetails("deba007190@gmail.com", "deb0119");
        signInSuccessMsg();
    }

    @Test
    public void validEmailInvalidPassword(){
        loginDetails("deba007190@gmail.com", "invalid");
        signInFailureMsg();
    }

    @Test
    public void invalidEmailValidPassword(){
        loginDetails("deba007190xx@gmail.com", "deb0119");
        signInFailureMsg();
    }

    @Test
    public void invalidEmailInvalidPassword(){
        loginDetails("deba007190xx@gmail.com", "invalid");
        signInFailureMsg();
    }
}
