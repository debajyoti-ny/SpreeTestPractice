package com.shiftedtech.qa.scripts.OtherTutorials.FailedTest_ReRun;

import org.testng.annotations.Test;

/**
 * So, in this case, we will create 3 different classes each having one very basic test(method).
 * We will create a TestNg.xml file to run the 3 classes. Out of the 3 classes, 2 classes will pass
 * and 1 will fail. TestNg by default will create another testng-failed.xml file which will have only failed
 * classes. We can run the file again to re-run our failed tests manually or create a Java Runner class
 * to run our failed tests automatically.
 *
 * P.S: The testng-failed.xml file is not getting created for some reasons.
 */
public class SampleTest1 {
    @Test
    public void facebookTest(){
        System.out.println("Facebook works...");  // Just to print a statement
    }
}
