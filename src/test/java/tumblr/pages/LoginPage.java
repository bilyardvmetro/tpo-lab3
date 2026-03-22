package tumblr.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By usernameOrEmailField = By.xpath(
            "//input[" +
                    "@type='email' or " +
                    "@type='text' or " +
                    "contains(@autocomplete,'username') or " +
                    "contains(translate(@name,'EMAILUSERNAME','emailusername'),'email') or " +
                    "contains(translate(@name,'EMAILUSERNAME','emailusername'),'user') or " +
                    "contains(translate(@id,'EMAILUSERNAME','emailusername'),'email') or " +
                    "contains(translate(@id,'EMAILUSERNAME','emailusername'),'user') or " +
                    "contains(translate(@placeholder,'EMAILUSERNAME','emailusername'),'email') or " +
                    "contains(translate(@placeholder,'EMAILUSERNAME','emailusername'),'user') or " +
                    "contains(translate(@aria-label,'EMAILUSERNAME','emailusername'),'email') or " +
                    "contains(translate(@aria-label,'EMAILUSERNAME','emailusername'),'user')" +
                    "]"
    );

    private final By passwordField = By.xpath(
            "//input[" +
                    "@type='password' or " +
                    "contains(translate(@name,'PASSWORD','password'),'password') or " +
                    "contains(translate(@id,'PASSWORD','password'),'password') or " +
                    "contains(translate(@placeholder,'PASSWORD','password'),'password') or " +
                    "contains(translate(@aria-label,'PASSWORD','password'),'password')" +
                    "]"
    );

    private final By anyVisibleInput = By.xpath("//input[not(@type='hidden') and not(@disabled)]");

    private final By nextOrLoginButton = By.xpath(
            "//button[" +
                    ".//span[normalize-space()='Next' or normalize-space()='Log In' or normalize-space()='Log in' or normalize-space()='Войти'] " +
                    "or normalize-space()='Next' " +
                    "or normalize-space()='Log In' " +
                    "or normalize-space()='Log in' " +
                    "or normalize-space()='Войти' " +
                    "or normalize-space(@aria-label)='Войти' " +
                    "or normalize-space(@aria-label)='Log In'" +
                    "]"
    );

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return isVisible(usernameOrEmailField)
                || isVisible(passwordField)
                || isVisible(anyVisibleInput)
                || driver.getCurrentUrl().contains("login");
    }

    public boolean hasUsernameOrEmailField() {
        return isVisible(usernameOrEmailField);
    }

    public boolean hasPasswordField() {
        return isVisible(passwordField);
    }

    public boolean hasAnyInputField() {
        return isVisible(anyVisibleInput);
    }

    public void enterUsernameOrEmail(String value) {
        type(usernameOrEmailField, value);
    }

    public void enterPassword(String value) {
        type(passwordField, value);
    }

    public void clickNextOrLogin() {
        click(nextOrLoginButton);
    }

    public void submitPassword() {
        findVisible(passwordField).sendKeys(Keys.ENTER);
    }
}