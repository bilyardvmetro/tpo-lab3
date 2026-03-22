package tumblr.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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

    protected void jsClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    protected void acceptCookiesIfPresent() {
        driver.switchTo().defaultContent();

        if (acceptCookiesInCurrentContext()) {
            driver.switchTo().defaultContent();
            return;
        }

        acceptCookiesInIframe();

        driver.switchTo().defaultContent();
    }

    private boolean acceptCookiesInCurrentContext() {
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));

        By cookieDialog = By.cssSelector(".frame-content .cmp__dialog");
        By agreeButton = By.xpath(
                "//*[contains(@class,'cmp__dialog')]//button[" +
                        "normalize-space()='I Agree!' " +
                        "or normalize-space()='Согласен' " +
                        "or .//*[normalize-space()='I Agree!'] " +
                        "or .//*[normalize-space()='Согласен']" +
                        "]"
        );

        try {
            shortWait.until(ExpectedConditions.visibilityOfElementLocated(cookieDialog));
            WebElement button = shortWait.until(ExpectedConditions.presenceOfElementLocated(agreeButton));
            jsClick(button);
            shortWait.until(ExpectedConditions.invisibilityOfElementLocated(cookieDialog));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean acceptCookiesInIframe() {
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        By modalIframe = By.cssSelector(".cmp-components-modal__frame iframe");
        By cookieDialog = By.cssSelector(".frame-content .cmp__dialog");
        By agreeButton = By.xpath(
                "//*[contains(@class,'cmp__dialog')]//button[" +
                        "normalize-space()='I Agree!' " +
                        "or normalize-space()='Согласен' " +
                        "or .//*[normalize-space()='I Agree!'] " +
                        "or .//*[normalize-space()='Согласен']" +
                        "]"
        );

        try {
            WebElement iframe = shortWait.until(
                    ExpectedConditions.presenceOfElementLocated(modalIframe)
            );

            driver.switchTo().frame(iframe);

            shortWait.until(ExpectedConditions.visibilityOfElementLocated(cookieDialog));
            WebElement button = shortWait.until(ExpectedConditions.presenceOfElementLocated(agreeButton));

            jsClick(button);

            shortWait.until(ExpectedConditions.invisibilityOfElementLocated(cookieDialog));
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            driver.switchTo().defaultContent();
        }
    }
}