import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class MobileTests {
    AndroidDriver<MobileElement> driver;

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("deviceName", "emulator-5554");
        caps.setCapability("app", "path/to/app.apk");

        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), caps);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testMobileLogin() {
        MobileElement usernameField = driver.findElementById("com.example:id/username");
        MobileElement passwordField = driver.findElementById("com.example:id/password");
        MobileElement loginButton = driver.findElementById("com.example:id/login");

        usernameField.sendKeys("testUser");
        passwordField.sendKeys("password123");
        loginButton.click();

        Assert.assertTrue(driver.findElementById("com.example:id/homePage").isDisplayed());
    }
}