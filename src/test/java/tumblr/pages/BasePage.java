package tumblr.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected WebElement find(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected WebElement findClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected WebElement findVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void click(By locator) {
        findClickable(locator).click();
    }

    protected void type(By locator, String text) {
        WebElement element = findVisible(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected boolean isVisible(By locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected void acceptCookiesIfPresent() {
        if (acceptCookiesInCurrentContext()) {
            driver.switchTo().defaultContent();
            return;
        }

        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
        for (WebElement iframe : iframes) {
            try {
                driver.switchTo().defaultContent();
                driver.switchTo().frame(iframe);

                if (acceptCookiesInCurrentContext()) {
                    driver.switchTo().defaultContent();
                    return;
                }
            } catch (Exception ignored) {
            }
        }

        driver.switchTo().defaultContent();
    }

    private boolean acceptCookiesInCurrentContext() {
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));

        By cookieDialog = By.cssSelector(".frame-content .cmp__dialog");
        By agreeButton = By.xpath(
                "//*[contains(@class,'cmp__dialog')]//button[.//span[normalize-space()='I Agree!'] or normalize-space()='I Agree!']"
        );

        try {
            WebElement dialog = shortWait.until(
                    ExpectedConditions.visibilityOfElementLocated(cookieDialog)
            );

            WebElement button = dialog.findElement(
                    By.xpath(".//button[.//span[normalize-space()='I Agree!'] or normalize-space()='I Agree!']")
            );

            try {
                button.click();
            } catch (Exception e) {
//                jsClick(button);
            }

            shortWait.until(ExpectedConditions.invisibilityOfElementLocated(cookieDialog));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}