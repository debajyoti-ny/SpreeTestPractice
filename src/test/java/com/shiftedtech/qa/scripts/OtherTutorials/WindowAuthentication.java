package com.shiftedtech.qa.scripts.OtherTutorials;

import org.junit.Test;

import java.io.IOException;

/**
 * Test will pass but since I don't have valid authentication, I won't be able to log in to mentioned website.
 * Check the AutoIT script (window_authentication.au3) in the AutoIT_Selenium folder...
 */
public class WindowAuthentication extends BaseClassOT {

    @Test
    public void test1() throws IOException {
        driver.navigate().to("https://www.engprod-charter.net");
        Runtime.getRuntime().exec("C:\\Users\\DEBAJYOTI007\\IdeaProjects\\SpreeTestPractice\\src\\test\\Resources\\AutoIT_Selenium\\window_Authentication.exe");
    }
}
