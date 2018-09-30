package com.shiftedtech.qa.scripts.Junit;

import com.shiftedtech.qa.framework.scriptbase.ScriptBase;
import com.shiftedtech.qa.framework.scriptbase.ScriptBaseComposite;
import org.junit.Test;

public class SpreeLoginWithScriptBaseComposite extends ScriptBaseComposite{

    @Test
    public void validEmailValidPassword (){
        spreeFunctions.loginDetails("deba007190@gmail.com", "deb0119");
        spreeFunctions.signInSuccessMsg();
    }

    @Test
    public void validEmailInvalidPassword(){
        spreeFunctions.loginDetails("deba007190@gmail.com", "invalid");
        spreeFunctions.signInFailureMsg();
    }

    @Test
    public void invalidEmailValidPassword(){
        spreeFunctions.loginDetails("deba007190xx@gmail.com", "deb0119");
        spreeFunctions.signInFailureMsg();
    }

    @Test
    public void invalidEmailInvalidPassword(){
        spreeFunctions.loginDetails("deba007190xx@gmail.com", "invalid");
        spreeFunctions.signInFailureMsg();
    }
}
