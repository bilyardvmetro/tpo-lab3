package tests;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.Test;
import tumblr.base.BaseTest;
import tumblr.pages.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchTest extends BaseTest {

    @Test
    void shouldSearchAfterLogin() {
        Dotenv dotenv = Dotenv.load();

        String email = dotenv.get("tumblr.email");
        String password = dotenv.get("tumblr.password");

        HomePage homePage = new HomePage(driver);
        LoginChoiceModal loginChoiceModal = new LoginChoiceModal(driver);
        LoginPage loginPage = new LoginPage(driver);
        LoggedInHomePage loggedInHomePage = new LoggedInHomePage(driver);
        SearchPage searchPage = new SearchPage(driver);

        homePage.open();
        homePage.clickLoginButton();

        loginChoiceModal.clickContinueWithEmail();

        loginPage.enterEmail(email);
        loginPage.submitEmailWithEnter();

        loginPage.enterPassword(password);
        loginPage.submitPasswordWithEnter();

        assertTrue(loggedInHomePage.isLoaded(),
                "Не удалось авторизоваться");

        assertTrue(searchPage.isSearchAvailable(),
                "Поле поиска не найдено");

        searchPage.search("yungrussia");

        assertTrue(searchPage.hasResults(),
                "Результаты поиска не отображаются");
    }
}