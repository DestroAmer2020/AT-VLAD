import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TextBoxPage extends BasePage {

    public TextBoxPage(WebDriver driver) {
        super(driver);
    }

    @FindBy.id("userName")
    private WebElement fullNameInput;

    @FindBy.id("userEmail")
    private WebElement emailInput;

    @FindBy.id("currentAddress")
    private WebElement currentAddressInput;

    @FindBy.id("permanentAddress")
    private WebElement permanentAddressInput;

    @FindBy.id("submit")
    private WebElement submitButton;

    @FindBy.id("name")
    private WebElement nameOutput;

    @FindBy.id("email")
    private WebElement emailOutput;

    public void fillForm(String fullName, String email, String currentAddress, String permanentAddress) {
        fullNameInput.sendKeys(fullName);
        emailInput.sendKeys(email);
        currentAddressInput.sendKeys(currentAddress);
        permanentAddressInput.sendKeys(permanentAddress);
    }

    public void submitForm() {
        submitButton.click();
    }

    public String getNameOutput() {
        return nameOutput.getText();
    }

    public String getEmailOutput() {
        return emailOutput.getText();
    }
}