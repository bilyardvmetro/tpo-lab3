package tumblr.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BlogPage extends BasePage {

    private final By blogHeader = By.xpath(
            "//header[.//a[contains(@href,'/yungrussia')] and .//h1[normalize-space()='YUNGRUSSIA']]"
    );

    private final By blogTitle = By.xpath(
            "//header//p[.//a[normalize-space()='@' or contains(.,'yungrussia')]]" +
                    " | //header//*[contains(normalize-space(), '@yungrussia')]"
    );

    private final By timelinePosts = By.xpath(
            "//*[@data-testid='timelinePosts']"
    );

    private final By firstPostArticle = By.xpath(
            "(//*[@data-testid='timelinePosts']//article)[1]"
    );

    private final By firstPostContainer = By.xpath(
            "(//*[@data-testid='timelinePosts']//article/ancestor::div[@tabindex='-1' or @data-id][1])[1]"
    );

    public BlogPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return isVisible(blogHeader) || isVisible(timelinePosts);
    }

    public boolean hasBlogTitle() {
        return isVisible(blogTitle);
    }

    public boolean hasPosts() {
        return isVisible(firstPostArticle);
    }

    public void openFirstPost() {
        click(firstPostContainer);
    }
}