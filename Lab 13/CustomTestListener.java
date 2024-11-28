import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Date;

public class CustomTestListener implements ITestListener {
    private static final Logger logger = LogManager.getLogger(CustomTestListener.class);
    private WebDriver driver;
    private Connection connection;

    @Override
    public void onStart() {
        try {
            logger.info("Setting up browser...");
            driver = new WebDriverSetup().initializeDriver();

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "user", "password");
            logger.info("Database connection established.");
        } catch (Exception e) {
            logger.error("Error in onStart: ", e);
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("Test started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Test Passed: " + result.getName());
        logTestResult(result, "PASS");

        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("INSERT INTO test_results (test_name, result) VALUES ('" + result.getName() + "', 'PASS')");
        } catch (Exception e) {
            logger.error("Error saving to DB: ", e);
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("Test Failed: " + result.getName());
        logTestResult(result, "FAIL");

        takeScreenshot(result);

        saveHTMLContent(result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("Test Skipped: " + result.getName());
        logger.warn("Skip Reason: " + result.getThrowable().getMessage());
    }

    @Override
    public void onFinish() {
        try {
            logger.info("Closing browser and database connections...");
            driver.quit();
            connection.close();
        } catch (IOException e) {
            logger.error("Error closing resources: ", e);
        }
    }

    private void takeScreenshot(ITestResult result) {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileHandler.copy(screenshot, new File("screenshots/" + result.getName() + ".png"));
            logger.info("Screenshot taken for failed test: " + result.getName());
        } catch (IOException e) {
            logger.error("Error taking screenshot: ", e);
        }
    }

    private void saveHTMLContent(ITestResult result) {
        try {
            String htmlContent = driver.getPageSource();
            String fileName = result.getName() + "_" + new Date().getTime() + ".html";
            FileHandler.write(new File(fileName), htmlContent.getBytes());
            logger.info("HTML content saved for failed test: " + result.getName());
        } catch (IOException e) {
            logger.error("Error saving HTML content: ", e);
        }
    }

    private void logTestResult(ITestResult result, String status) {
        logger.info("Test Result for " + result.getName() + ": " + status);
    }
}