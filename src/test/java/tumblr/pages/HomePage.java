package tumblr.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private static final String URL = "https://www.tumblr.com/";

    private final By loginButton = By.xpath(
            "//button[@type='button' and (" +
                    "normalize-space(@aria-label)='Войти' or " +
                    "normalize-space(@aria-label)='Log In'" +
                    ")]" +
                    " | //button[.//span[normalize-space()='Log In' or normalize-space()='Log in' or normalize-space()='Войти']]"
    );

    private final By signupButton = By.xpath(
            "//button[@type='button' and (" +
                    "normalize-space(@aria-label)='Зарегистрироваться' or " +
                    "normalize-space(@aria-label)='Sign up'" +
                    ")]" +
                    " | //button[.//span[normalize-space()='Sign up' or normalize-space()='Зарегистрироваться']]"
    );

    public HomePage(WebDriver driver) {
        super(driver);
    }

    // CHK-01
    public void open() {
        driver.get(URL);
        acceptCookiesIfPresent();
    }

    // CHK-04
    public boolean isLoaded() {
        return driver.getCurrentUrl().contains("tumblr");
    }

    // CHK-03
    public boolean hasLoginButton() {
        return isVisible(loginButton);
    }

    // CHK-02
    public boolean hasAnyPrimaryAction() {
        return hasLoginButton() || isVisible(signupButton);
    }

    // CHK-05
    public void clickLoginButton() {
        acceptCookiesIfPresent();
        click(loginButton);
    }
}