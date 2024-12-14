import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeClass
    public void setUp() {
        // Ініціалізація WebDriver
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();

        // Відкриваємо сторінку логіну
        driver.get("https://example.com/login");

        // Ініціалізуємо Page Object
        loginPage = new LoginPage(driver);
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][] {
            {"validUser", "validPass", true},       // Успішний логін
            {"invalidUser", "validPass", false},   // Невірний логін
            {"validUser", "invalidPass", false},   // Невірний пароль
            {"", "", false}                        // Порожні поля
        };
    }

    @Test(dataProvider = "loginData")
    public void testLogin(String username, String password, boolean isSuccessExpected) {
        // Вводимо дані в поля логіну через Page Object
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();

        // Додаємо перевірку успішності логіну
        if (isSuccessExpected) {
            Assert.assertTrue(loginPage.isLoggedIn(), "Login should be successful for: " + username);
        } else {
            Assert.assertTrue(loginPage.isLoginErrorDisplayed(), "Error message should be displayed for: " + username);
        }
    }

    @AfterClass
    public void tearDown() {
        // Закриваємо браузер
        if (driver != null) {
            driver.quit();
        }
    }
}