import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CalculatorTests {

    private WebDriver driver;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        // Navigate to the HTML file
        driver.get("src\\main\\java\\com\\example\\index.html");
    }

    @Test
    public void testAddition() {
        performCalculation("5+3");
        assertResult("8");
    }

    @Test
    public void testSubtraction() {
        performCalculation("10-4");
        assertResult("6");
    }

    @Test
    public void testMultiplication() {
        performCalculation("6*7");
        assertResult("42");
    }

    @Test
    public void testDivision() {
        performCalculation("20/4");
        assertResult("5");
    }

    @Test
    public void testComplexExpression() {
        performCalculation("5+3*2-6/2");
        assertResult("10");
    }

    // Helper methods

    private void performCalculation(String expression) {
        WebElement display = driver.findElement(By.id("display"));
        display.clear();
        display.sendKeys(expression);

        // Click the '=' button to calculate the result
        driver.findElement(By.xpath("//button[text()='=']")).click();
    }

    private void assertResult(String expected) {
        WebElement display = driver.findElement(By.id("display"));
        String actual = display.getAttribute("value");
        // Assert the result
        assert expected.equals(actual);
    }

    @After
    public void tearDown() {
        // Close the WebDriver after tests
        driver.quit();
    }
}
