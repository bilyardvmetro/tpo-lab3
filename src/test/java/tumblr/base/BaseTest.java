package tumblr.base;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import tumblr.driver.DriverFactory;

public class BaseTest {

    protected WebDriver driver;

    @BeforeEach
    void setUp() {
        Dotenv dotenv = Dotenv.load();

        String browser = dotenv.get("browser");
        driver = DriverFactory.createDriver(browser);
        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}