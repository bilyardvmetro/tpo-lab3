package tumblr.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PostPage extends BasePage {

    private final By openedPost = By.xpath(
            "//article" +
                    " | //main//article" +
                    " | //*[@role='dialog']//article"
    );

    public PostPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return isVisible(openedPost) || driver.getCurrentUrl().contains("/post/");
    }
}