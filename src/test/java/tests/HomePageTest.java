package tests;

import org.junit.jupiter.api.Test;
import tumblr.base.BaseTest;
import tumblr.pages.HomePage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomePageTest extends BaseTest {

    @Test
    void shouldOpenHomePage() {
        HomePage homePage = new HomePage(driver);

        homePage.open();

        assertTrue(homePage.isLoaded(), "Главная страница Tumblr не открылась");
    }

    @Test
    void shouldDisplayPrimaryActionOnHomePage() {
        HomePage homePage = new HomePage(driver);

        homePage.open();

        assertTrue(homePage.hasAnyPrimaryAction(),
                "На главной странице не найден основной элемент интерфейса");
    }
}