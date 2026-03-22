package tumblr.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PostPage extends BasePage {

    private final By postArticle = By.xpath("//article");

    private final By hashtags = By.xpath(
            "//article//a[@data-testid='tag-link']"
    );

    private final By commentButton = By.xpath(
            "//article//button[@aria-label='Комментировать']"
    );

    private final By likeButton = By.xpath(
            "//article//button[@aria-label='Нравится']"
    );

    private final By reblogButton = By.xpath(
            "//article//button[@aria-label='Реблог']"
    );

    public PostPage(WebDriver driver) {
        super(driver);
    }

    // CHK-20
    public boolean isLoaded() {
        return isVisible(postArticle);
    }

    // CHK-21
    public boolean hasHashtags() {
        return isVisible(hashtags);
    }

    // CHK-22
    public boolean hasCommentButton() {
        return isVisible(commentButton);
    }

    // CHK-22
    public boolean hasLikeButton() {
        return isVisible(likeButton);
    }

    // CHK-22
    public boolean hasReblogButton() {
        return isVisible(reblogButton);
    }
}