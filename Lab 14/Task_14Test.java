package task_14;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static task_14.DriverProvider.getDriver;
import static task_14.DriverProvider.quitDriver;

@Listeners({
        AllureListener.class
})
public class Task_14Test {

    @BeforeClass
    public void setUp() {
        System.out.println("Tests started");
    }

    @AfterClass
    public void tearDown() {
        quitDriver();
        System.out.println("Tests finished");
    }

    @Test
    public void googleTest() {
        getDriver().get("https://www.google.com/");
        WebElement searchBox = getDriver().findElement(By.xpath("//*[@title='Пошук']"));
        searchBox.sendKeys("Cakes");

        // Перевіряємо, чи існує кнопка перед кліком
        WebElement searchButton = getDriver().findElements(By.xpath("//*[@value='Пошук Google']")).size() > 1
                ? getDriver().findElements(By.xpath("//*[@value='Пошук Google']")).get(1)
                : null;

        Assert.assertNotNull(searchButton, "Search button is not available");
        searchButton.click();
    }

    @Test
    public void googleFailTest() {
        getDriver().get("https://www.google.com/");
        WebElement searchBox = getDriver().findElement(By.xpath("//*[@title='Пошук']"));
        searchBox.sendKeys("Cakes");

        // Перевіряємо, чи існує кнопка перед кліком
        WebElement searchButton = getDriver().findElements(By.xpath("//*[@value='Пошук Google']")).size() > 1
                ? getDriver().findElements(By.xpath("//*[@value='Пошук Google']")).get(1)
                : null;

        Assert.assertNotNull(searchButton, "Search button is not available");
        searchButton.click();

        // Навмисна помилка для перевірки AllureListener
        Assert.fail("Intentionally failing this test");
    }
}