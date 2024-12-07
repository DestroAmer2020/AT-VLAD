package task_20;

import org.testng.Assert;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class Task20 {
    public static void main(String[] args) throws MalformedURLException {
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("platformName", "Android");
        dc.setCapability("deviceName", "emulator-5554");
        dc.setCapability("appPackage", "com.android.calculator2");
        dc.setCapability("appActivity", "com.android.calculator2.Calculator");

        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), dc);

        try {
            // Input the number 50
            driver.findElement(AppiumBy.id("com.android.calculator2:id/digit_5")).click();
            driver.findElement(AppiumBy.id("com.android.calculator2:id/digit_0")).click(); // 50

            // Press multiplication
            driver.findElement(AppiumBy.accessibilityId("multiply")).click();

            // Input the number 20
            driver.findElement(AppiumBy.id("com.android.calculator2:id/digit_2")).click();
            driver.findElement(AppiumBy.id("com.android.calculator2:id/digit_0")).click(); // 20

            // Press equals
            driver.findElement(AppiumBy.accessibilityId("equals")).click();

            // Press division
            driver.findElement(AppiumBy.accessibilityId("divide")).click();

            // Input the number 100
            driver.findElement(AppiumBy.id("com.android.calculator2:id/digit_1")).click();
            driver.findElement(AppiumBy.id("com.android.calculator2:id/digit_0")).click();
            driver.findElement(AppiumBy.id("com.android.calculator2:id/digit_0")).click(); // 100

            // Press equals
            driver.findElement(AppiumBy.accessibilityId("equals")).click();

            // Capture and validate the result
            String actualResult = driver.findElement(AppiumBy.id("com.android.calculator2:id/result")).getText();
            String expectedResult = "10"; // 20% of 50 is 10

            Assert.assertEquals(actualResult, expectedResult, "Percentage calculation result is incorrect!");
            System.out.println("Test Passed: The percentage result is correct: " + actualResult);

        } finally {
            driver.quit();
        }
    }
}