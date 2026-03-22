package tumblr.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginChoiceModal extends BasePage {

    private final By modalContainer = By.xpath("//form[@action and .//button[@aria-label='Продолжить с эл. почтой' or @aria-label='Continue with email']]");

    private final By continueWithGoogleButton = By.xpath(
            "//a[contains(@href,'/auth/google') and (contains(.,'Google') or contains(.,'google'))]"
    );

    private final By continueWithAppleButton = By.xpath(
            "//a[contains(@href,'/auth/apple') and (contains(.,'Apple') or contains(.,'apple'))]"
    );

    private final By continueWithEmailButton = By.xpath(
            "//button[@aria-label='Продолжить с эл. почтой' or @aria-label='Continue with email' or contains(.,'Продолжить с эл. почтой') or contains(.,'Continue with email')]"
    );

    public LoginChoiceModal(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return isVisible(modalContainer) || isVisible(continueWithEmailButton);
    }

    public boolean hasContinueWithGoogleButton() {
        return isVisible(continueWithGoogleButton);
    }

    public boolean hasContinueWithAppleButton() {
        return isVisible(continueWithAppleButton);
    }

    public boolean hasContinueWithEmailButton() {
        return isVisible(continueWithEmailButton);
    }

    public void clickContinueWithEmail() {
        click(continueWithEmailButton);
    }
}