package tumblr.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BlogPage extends BasePage {

    private final By blogContent = By.xpath(
            "//main | //article | //div[contains(@class,'blog')]"
    );

    public BlogPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return driver.getCurrentUrl().contains("yungrussia.tumblr.com")
                || isVisible(blogContent);
    }
}