package core.utils.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("--------------------------------------------------------------");
        System.out.println("Start of execution(TEST)-> "+iTestResult.getName());
        System.out.println("--------------------------------------------------------------");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("--------------------------------------------------------------");
        System.out.println("Test Pass-> "+iTestResult.getName());
        System.out.println("--------------------------------------------------------------");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("--------------------------------------------------------------");
        System.out.println("Test Failed-> "+iTestResult.getName());
        System.out.println("--------------------------------------------------------------");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("--------------------------------------------------------------");
        System.out.println("Test Skipped-> "+iTestResult.getName());
        System.out.println("--------------------------------------------------------------");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("--------------------------------------------------------------");
        System.out.println("Start of Execution-> "+iTestContext.getName());
        System.out.println("--------------------------------------------------------------");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("--------------------------------------------------------------");
        System.out.println("End of Execution-> "+iTestContext.getName());
        System.out.println("--------------------------------------------------------------");
    }
}