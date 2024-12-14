import org.openqa.selenium.WebDriver;

public class TestExample {
    public static void main(String[] args) {
        // Ініціалізація Chrome-драйвера
        WebDriver chromeDriver = DriverPool.getDriver("chrome");
        chromeDriver.get("https://example.com");
        System.out.println("Title in Chrome: " + chromeDriver.getTitle());

        // Ініціалізація Firefox-драйвера
        WebDriver firefoxDriver = DriverPool.getDriver("firefox");
        firefoxDriver.get("https://example.com");
        System.out.println("Title in Firefox: " + firefoxDriver.getTitle());

        // Закриття драйверів
        DriverPool.quitDriver();
        DriverPool.quitAllDrivers();
    }
}