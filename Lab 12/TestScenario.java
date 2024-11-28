import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestScenario {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://example.com/login");

            LoginPage loginPage = new LoginPage(driver);
            loginPage.login("testUser", "password123");

            DashboardPage dashboardPage = new DashboardPage(driver);
            ElementWrapper welcomeLabel = new ElementWrapper(driver, dashboardPage.welcomeMessage);

            welcomeLabel.waitForText("Welcome, testUser!");
            System.out.println("Label Text: " + welcomeLabel.getText());
            if (welcomeLabel.verifyLabelExists()) {
                System.out.println("Test Passed: Label exists.");
            } else {
                System.out.println("Test Failed: Label not found.");
            }
        } finally {
            driver.quit();
        }
    }
}