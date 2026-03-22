package tumblr.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoggedInHomePage extends BasePage {

    private final By accountButton = By.xpath(
            "//button[@id='account_button' and @aria-label='Учетная запись']" +
                    " | //button[@id='account_button']" +
                    " | //button[@aria-label='Учетная запись']"
    );

    public LoggedInHomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return isVisible(accountButton);
    }
}