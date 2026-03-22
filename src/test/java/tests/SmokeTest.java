package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SmokeTest {

    @Test
    void chromeShouldOpenTumblr() {
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://www.tumblr.com/");
            assertTrue(driver.getCurrentUrl().contains("tumblr"));
        } finally {
            driver.quit();
        }
    }
}