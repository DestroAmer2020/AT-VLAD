package task_14;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static task_14.Task_14DriverProvider.getDriver;

public class AllureListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test failed: " + result.getName());
        saveErrorDetails(result);
        makeScreenShot();
        savePageSource();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test skipped: " + result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("Test partially failed: " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test suite started: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test suite finished: " + context.getName());
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    private byte[] makeScreenShot() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Error Details", type = "text/plain")
    private String saveErrorDetails(ITestResult result) {
        return result.getThrowable() != null ? result.getThrowable().toString() : "No error details available";
    }

    @Attachment(value = "Page Source", type = "text/html")
    private String savePageSource() {
        return getDriver().getPageSource();
    }
}