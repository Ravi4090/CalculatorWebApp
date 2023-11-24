import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    private WebDriver driver;

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Run Chrome in headless mode
        driver = new ChromeDriver(options);

        // Navigate to the HTML file using an absolute path
        String absolutePath = System.getProperty("user.dir") + "/src/main/resources/webapp/index.html";
        driver.get("file://" + absolutePath);
    }

    @Test
    public void testAddition() {
        clickButton("btn1");
        clickButton("btnPlus");
        clickButton("btn2");
        clickButton("btnEquals");
        assertResult("3");
    }

    @Test
    public void testSubtraction() {
        clickButton("btn5");
        clickButton("btnMinus");
        clickButton("btn2");
        clickButton("btnEquals");
        assertResult("3");
    }

    @Test
    public void testMultiplication() {
        clickButton("btn3");
        clickButton("btnMultiply");
        clickButton("btn4");
        clickButton("btnEquals");
        assertResult("12");
    }

    @Test
    public void testDivision() {
        clickButton("btn8");
        clickButton("btnDivide");
        clickButton("btn2");
        clickButton("btnEquals");
        assertResult("4");
    }

    @Test
    public void testComplexExpression() {
        clickButton("btn1");
        clickButton("btnPlus");
        clickButton("btn2");
        clickButton("btnMultiply");
        clickButton("btn3");
        clickButton("btnMinus");
        clickButton("btn4");
        clickButton("btnDivide");
        clickButton("btn2");
        clickButton("btnEquals");
        assertResult("5");
    }

    // Helper methods

    private void clickButton(String buttonId) {
        WebElement button = driver.findElement(By.id(buttonId));
        button.click();
    }

    private void assertResult(String expected) {
        WebElement display = driver.findElement(By.id("display"));
        String actual = display.getAttribute("value");
        assertEquals(expected, actual);
    }

    @AfterEach
    public void tearDown() {
        // Close the WebDriver after tests
        driver.quit();
    }
}
