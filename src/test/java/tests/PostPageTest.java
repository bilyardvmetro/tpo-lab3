package tests;

import org.junit.jupiter.api.Test;
import tumblr.base.BaseTest;
import tumblr.pages.BlogPage;
import tumblr.pages.PostPage;
import tumblr.pages.SearchPage;
import tumblr.util.AuthHelper;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PostPageTest extends BaseTest {

    @Test
    void shouldOpenFirstPostFromBlogModal() {
        SearchPage searchPage = new SearchPage(driver);
        BlogPage blogPage = new BlogPage(driver);
        PostPage postPage = new PostPage(driver);

        AuthHelper.login(driver);

        assertTrue(searchPage.isSearchAvailable(), "Поле поиска не найдено");

        searchPage.search("yungrussia");

        assertTrue(searchPage.hasBlogsTab(), "Вкладка 'Блоги' не найдена");
        searchPage.openBlogsTab();

        assertTrue(searchPage.hasTargetBlogLink(), "Ссылка на блог yungrussia не найдена");
        searchPage.openTargetBlog();

        assertTrue(blogPage.isLoaded(), "Модалка блога не открылась");
        assertTrue(blogPage.hasPosts(), "В блоге не найдено ни одного поста");

        blogPage.openFirstPost();

        assertTrue(postPage.isLoaded(), "Пост не открылся");
    }
}