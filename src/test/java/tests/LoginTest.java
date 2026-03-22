package tests;

import org.junit.jupiter.api.Test;
import tumblr.base.BaseTest;
import tumblr.pages.HomePage;
import tumblr.pages.LoginChoiceModal;
import tumblr.pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends BaseTest {

    @Test
    void shouldOpenLoginChoiceModalFromHomePage() {
        HomePage homePage = new HomePage(driver);
        LoginChoiceModal loginChoiceModal = new LoginChoiceModal(driver);

        homePage.open();
        homePage.clickLoginButton();

        assertTrue(loginChoiceModal.isLoaded(),
                "Модалка выбора способа входа не открылась");
    }

    @Test
    void shouldDisplayContinueWithEmailButton() {
        HomePage homePage = new HomePage(driver);
        LoginChoiceModal loginChoiceModal = new LoginChoiceModal(driver);

        homePage.open();
        homePage.clickLoginButton();

        assertTrue(loginChoiceModal.hasContinueWithEmailButton(),
                "Кнопка Continue with email не найдена");
    }

    @Test
    void shouldOpenEmailStepAfterChoosingEmailLogin() {
        HomePage homePage = new HomePage(driver);
        LoginChoiceModal loginChoiceModal = new LoginChoiceModal(driver);
        LoginPage loginPage = new LoginPage(driver);

        homePage.open();
        homePage.clickLoginButton();

        loginChoiceModal.clickContinueWithEmail();

        assertTrue(loginPage.isEmailStepLoaded(),
                "Шаг ввода email не открылся");
    }
}