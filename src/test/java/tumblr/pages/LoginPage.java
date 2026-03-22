package tumblr.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By emailStepContainer = By.xpath(
            "//*[contains(@class,'m4lSb') and contains(@class,'email')]"
    );

    private final By passwordStepContainer = By.xpath(
            "//*[contains(@class,'m4lSb') and contains(@class,'password')]"
    );

    private final By emailField = By.xpath(
            "//input[@name='email' and @type='email']"
    );

    private final By nextButton = By.xpath(
            "//button[@type='submit' and @aria-label='Далее']" +
                    " | //button[@type='submit' and .//span[normalize-space()='Далее']]"
    );

    private final By passwordField = By.xpath(
            "//input[@name='password' and @type='password']"
    );

    private final By loginButton = By.xpath(
            "//button[@type='submit' and @aria-label='Войти']" +
                    " | //button[@type='submit' and .//span[normalize-space()='Войти']]"
    );

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean isEmailStepLoaded() {
        return isVisible(emailStepContainer) && isVisible(emailField);
    }

    public boolean isPasswordStepLoaded() {
        return isVisible(passwordStepContainer) && isVisible(passwordField);
    }

    public void enterEmail(String email) {
        type(emailField, email);
    }

    public void submitEmailWithEnter() {
        findVisible(emailField).sendKeys(Keys.ENTER);
    }

    public void clickNext() {
        click(nextButton);
    }

    public void enterPassword(String password) {
        type(passwordField, password);
    }

    public void submitPasswordWithEnter() {
        findVisible(passwordField).sendKeys(Keys.ENTER);
    }

    public void clickLogin() {
        click(loginButton);
    }
}