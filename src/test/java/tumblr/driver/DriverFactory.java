package tumblr.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    public static WebDriver createDriver(String browser) {
        if (browser.equalsIgnoreCase("firefox")) {
            return new FirefoxDriver();
        }
        return new ChromeDriver();
    }
}