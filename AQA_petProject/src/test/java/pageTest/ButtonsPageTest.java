package pageTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.ButtonsPage;

public class ButtonsPageTest {
    private WebDriver driver;
    private ButtonsPage buttonsPage;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/buttons");
        buttonsPage = new ButtonsPage(driver);
    }

    @Test
    public void testButtonsDisplayed() {
        Assert.assertTrue(buttonsPage.areButtonsDisplayed(), "Buttons are not displayed on the page.");
    }

    @Test
    public void testClickButtons() {
        buttonsPage.clickButtons();
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}