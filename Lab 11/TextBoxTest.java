import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TextBoxTest {
    private WebDriver driver;
    private TextBoxBusinessObject textBoxBO;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://demoqa.com/text-box");
        driver.manage().window().maximize();
        textBoxBO = new TextBoxBusinessObject(driver);
    }

    @Test
    public void testTextBoxForm() {
        String fullName = "John Doe";
        String email = "johndoe@example.com";
        String currentAddress = "123 Main Street";
        String permanentAddress = "456 Elm Street";

        textBoxBO.fillAndSubmitForm(fullName, email, currentAddress, permanentAddress);
        boolean isOutputCorrect = textBoxBO.verifyOutput(fullName, email);

        Assert.assertTrue(isOutputCorrect, "The output data does not match the input!");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}