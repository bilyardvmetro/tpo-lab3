package tumblr.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class SearchPage extends BasePage {

    private final By searchInput = By.xpath(
            "//input[@name='q' and (@aria-label='Поиск' or contains(@placeholder,'Tumblr'))]"
    );

    private final By resultsContainer = By.xpath(
            "//main | //div[contains(@class,'search')]"
    );

    private final By blogsTab = By.xpath(
            "//a[@id='blog' and contains(@href,'v=blog')]" +
                    " | //a[@role='tab' and @id='blog']" +
                    " | //a[.//div[normalize-space()='Блоги']]"
    );

    private final By targetBlogLink = By.xpath(
            "//a[contains(@href,'yungrussia.tumblr.com')]"
    );

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public boolean isSearchAvailable() {
        return isVisible(searchInput);
    }

    public void search(String query) {
        type(searchInput, query);
        findVisible(searchInput).sendKeys(Keys.ENTER);
    }

    public boolean hasResults() {
        return isVisible(resultsContainer);
    }

    public boolean hasBlogsTab() {
        return isVisible(blogsTab);
    }

    public void openBlogsTab() {
        click(blogsTab);
    }

    public boolean hasTargetBlogLink() {
        return isVisible(targetBlogLink);
    }

    public void openTargetBlog() {
        click(targetBlogLink);
    }
}