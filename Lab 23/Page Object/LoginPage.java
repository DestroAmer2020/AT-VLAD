import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    // Локатори для елементів сторінки
    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By loginButton = By.id("loginButton");
    private By loginError = By.id("loginError");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Методи для взаємодії зі сторінкою
    public void enterUsername(String username) {
        driver.findElement(usernameField).clear();
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public boolean isLoggedIn() {
        // Логіка перевірки успішного логіну
        // Наприклад, перевіряємо URL або присутність елемента
        return driver.getCurrentUrl().contains("dashboard");
    }

    public boolean isLoginErrorDisplayed() {
        // Перевіряємо, чи з’являється повідомлення про помилку
        return driver.findElement(loginError).isDisplayed();
    }
}