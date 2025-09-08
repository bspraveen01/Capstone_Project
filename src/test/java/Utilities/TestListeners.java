package Utilities;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import TestCases.BaseTest;

public class TestListeners implements ITestListener{
	@Override
    public void onTestFailure(ITestResult result) {
        Object testClass = result.getInstance();
        WebDriver driver = ((BaseTest) testClass).getDriver();

        String methodName = result.getName();
        try {
            String screenshotPath = Screenshots.takeScreenshot(driver, methodName);
            System.out.println("Screenshot attached to report: " + screenshotPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	@Override
	public void onTestStart(ITestResult result) {
	    System.out.println("===== TEST STARTED: " + result.getName() + " =====");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
	    System.out.println("===== TEST PASSED: " + result.getName() + " =====");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
	    System.out.println("===== TEST SKIPPED: " + result.getName() + " =====");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	    System.out.println("===== TEST FAILED BUT WITHIN SUCCESS %: " + result.getName() + " =====");
	}

	@Override
	public void onStart(ITestContext context) {
	    System.out.println("===== TEST SUITE STARTED: " + context.getName() + " =====");
	}

	@Override
	public void onFinish(ITestContext context) {
	    System.out.println("===== TEST SUITE FINISHED: " + context.getName() + " =====");
	}

}
