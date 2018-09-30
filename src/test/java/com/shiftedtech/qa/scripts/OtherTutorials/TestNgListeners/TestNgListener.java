package com.shiftedtech.qa.scripts.OtherTutorials.TestNgListeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * Created by Debajyoti Paul on 3/21/2018 at 11:58 PM
 */
public class TestNgListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test case '" + result.getName() + "' started...");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test case '" + result.getName() + "' passed...");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test case '" + result.getName() + "' failed...");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test case '" + result.getName() + "' skipped...");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}
