package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ButtonsPage {

    @FindBy(id = "doubleClickBtn")
    private WebElement doubleClickButton;

    @FindBy(id = "rightClickBtn")
    private WebElement rightClickButton;

    @FindBy(xpath = "//button[text()='Click Me']")
    private WebElement clickMeButton;

    public ButtonsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public boolean areButtonsDisplayed() {
        return doubleClickButton.isDisplayed() && rightClickButton.isDisplayed() && clickMeButton.isDisplayed();
    }

    public void clickButtons() {
        doubleClickButton.click();
        rightClickButton.click();
        clickMeButton.click();
    }
}