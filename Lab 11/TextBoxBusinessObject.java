import org.openqa.selenium.WebDriver;

public class TextBoxBusinessObject {
    private WebDriver driver;
    private TextBoxPage textBoxPage;

    public TextBoxBusinessObject(WebDriver driver) {
        this.driver = driver;
        textBoxPage = new TextBoxPage(driver);
    }

    public void fillAndSubmitForm(String fullName, String email, String currentAddress, String permanentAddress) {
        textBoxPage.fillForm(fullName, email, currentAddress, permanentAddress);
        textBoxPage.submitForm();
    }

    public boolean verifyOutput(String expectedName, String expectedEmail) {
        return textBoxPage.getNameOutput().contains(expectedName) &&
               textBoxPage.getEmailOutput().contains(expectedEmail);
    }
}