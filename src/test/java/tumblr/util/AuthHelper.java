package tumblr.util;

import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.WebDriver;
import tumblr.pages.HomePage;
import tumblr.pages.LoggedInHomePage;
import tumblr.pages.LoginChoiceModal;
import tumblr.pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AuthHelper {

    public static void login(WebDriver driver) {
        Dotenv dotenv = Dotenv.load();

        String email = dotenv.get("tumblr.email");
        String password = dotenv.get("tumblr.password");

        assertTrue(email != null && !email.isBlank(), "Не задан tumblr.email");
        assertTrue(password != null && !password.isBlank(), "Не задан tumblr.password");

        HomePage homePage = new HomePage(driver);
        LoginChoiceModal loginChoiceModal = new LoginChoiceModal(driver);
        LoginPage loginPage = new LoginPage(driver);
        LoggedInHomePage loggedInHomePage = new LoggedInHomePage(driver);

        homePage.open();
        homePage.clickLoginButton();

        assertTrue(loginChoiceModal.isLoaded(), "Модалка выбора способа входа не открылась");

        loginChoiceModal.clickContinueWithEmail();

        assertTrue(loginPage.isEmailStepLoaded(), "Шаг ввода email не открылся");

        loginPage.enterEmail(email);
        loginPage.submitEmailWithEnter();

        assertTrue(loginPage.isPasswordStepLoaded(), "Шаг ввода пароля не открылся");

        loginPage.enterPassword(password);
        loginPage.submitPasswordWithEnter();

        assertTrue(loggedInHomePage.isLoaded(), "Вход не выполнен");
    }
}