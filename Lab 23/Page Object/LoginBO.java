package business;

import org.openqa.selenium.WebDriver;
import pages.LoginPage;

public class LoginBO {
    private WebDriver driver;
    private LoginPage loginPage;

    public LoginBO(WebDriver driver) {
        this.driver = driver;
        loginPage = new LoginPage(driver);
    }

    public void login(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
    }
}