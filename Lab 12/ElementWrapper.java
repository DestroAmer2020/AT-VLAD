import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;

public class ElementWrapper {
    WebDriver driver;
    WebElement element;

    public ElementWrapper(WebDriver driver, WebElement element) {
        this.driver = driver;
        this.element = element;
    }

    public String getText() {
        System.out.println("Getting text from element: " + element);
        return element.getText();
    }

    public void waitForText(String text) {
        System.out.println("Waiting for text: " + text);
        new org.openqa.selenium.support.ui.WebDriverWait(driver, 10)
            .until(org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement(element, text));
    }

    public boolean verifyLabelExists() {
        boolean exists = element.isDisplayed();
        System.out.println("Verifying if label exists: " + exists);
        return exists;
    }
}