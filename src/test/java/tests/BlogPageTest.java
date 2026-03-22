package tests;

import org.junit.jupiter.api.Test;
import tumblr.base.BaseTest;
import tumblr.pages.BlogPage;
import tumblr.pages.SearchPage;
import tumblr.util.AuthHelper;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BlogPageTest extends BaseTest {

    @Test
    void shouldOpenBlogFromBlogsSearchTab() {
        SearchPage searchPage = new SearchPage(driver);
        BlogPage blogPage = new BlogPage(driver);

        AuthHelper.login(driver);

        assertTrue(searchPage.isSearchAvailable(), "Поле поиска не найдено");

        searchPage.search("yungrussia");

        assertTrue(searchPage.hasResults(), "Результаты поиска не отображаются");
        assertTrue(searchPage.hasBlogsTab(), "Вкладка 'Блоги' не найдена");

        searchPage.openBlogsTab();

        assertTrue(searchPage.hasTargetBlogLink(), "Ссылка на блог yungrussia не найдена");

        searchPage.openTargetBlog();

        assertTrue(blogPage.isLoaded(), "Страница блога не открылась");
    }
}