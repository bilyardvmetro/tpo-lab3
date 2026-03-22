package tumblr.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoggedInHomePage extends BasePage {

    // CHK-12
    private final By accountButton = By.xpath(
            "//button[@id='account_button' and @aria-label='Учетная запись']" +
                    " | //button[@id='account_button']" +
                    " | //button[@aria-label='Учетная запись']"
    );

    public LoggedInHomePage(WebDriver driver) {
        super(driver);
    }

    // CHK-11
    public boolean isLoaded() {
        return isVisible(accountButton);
    }
}