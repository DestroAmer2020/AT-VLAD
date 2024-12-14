import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.ConcurrentHashMap;

public class DriverPool {
    // ThreadLocal для багатопоточності
    private static ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();
    private static ConcurrentHashMap<String, WebDriver> drivers = new ConcurrentHashMap<>();

    // Метод для отримання драйвера
    public static WebDriver getDriver(String browser) {
        if (driverPool.get() == null) {
            driverPool.set(createDriver(browser));
        }
        return driverPool.get();
    }

    // Метод для створення драйвера
    private static WebDriver createDriver(String browser) {
        WebDriver driver;
        switch (browser.toLowerCase()) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
                driver = new ChromeDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "path/to/geckodriver");
                driver = new FirefoxDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        drivers.put(browser, driver);
        return driver;
    }

    // Метод для закриття драйвера
    public static void quitDriver() {
        WebDriver driver = driverPool.get();
        if (driver != null) {
            driver.quit();
            driverPool.remove();
        }
    }

    // Метод для закриття всіх драйверів
    public static void quitAllDrivers() {
        drivers.values().forEach(WebDriver::quit);
        drivers.clear();
    }
}