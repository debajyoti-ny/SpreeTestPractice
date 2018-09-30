package com.shiftedtech.qa.scripts.OtherTutorials.FailedTest_ReRun;

import org.testng.TestNG;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Debajyoti Paul on 8/18/2018 at 5:33 PM
 * When we run our test from the "FailedTestReRun.xml", 2 tests pass and 1 fail. So, testng by default
 * creates a testng-failed.xml file inside the Target folder of our project (Unable to create it in
 * this project for some unknown reasons). We can manually re-run this new .xml file or we can create
 * a "Runner" class like this to execute our "testng-failed.xml" file.
 *
 */
public class FailedTestRunner {

    @Test
    public void failedItemsReRun(){
        TestNG runner = new TestNG();
        List<String> failedItems = new ArrayList<String>();
        failedItems.add(""); //path/paths(could have multiple files) of the "testng-failed.xml" file
        runner.setTestSuites(failedItems); //this method takes a "List"
        runner.run();
    }

}
