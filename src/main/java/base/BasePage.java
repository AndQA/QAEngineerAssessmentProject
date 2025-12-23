package base;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverManager;

import java.time.Duration;
import java.util.ArrayList;

import static utils.WaitUtils.TIMEOUT;

public abstract class BasePage {

    protected WebDriver driver;

    private static final By COOKIE_ACCEPT_BTN = By.id("wt-cli-accept-all-btn");

    protected BasePage() {
        this.driver = DriverManager.get();
        PageFactory.initElements(driver, this);
    }

    protected WebDriverWait waitFor() {
        return new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
    }

    public void openUrl(String url) {
        driver.get(url);
    }

    public void waitForPageLoad() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT))
                    .until(webDriver -> ((JavascriptExecutor) webDriver)
                            .executeScript("return document.readyState").equals("complete"));
        } catch (Exception e) {
            System.out.println("Page did not fully load: " + e.getMessage());
        }
    }

    public void closeCookieBannerIfPresent() {
        try {
            WebElement acceptBtn = waitFor().until(
                    ExpectedConditions.elementToBeClickable(COOKIE_ACCEPT_BTN)
            );
            acceptBtn.click();
        } catch (TimeoutException | NoSuchElementException ignored) {
        }
    }

    public void scrollToElement(WebElement element) {
        try {
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].scrollIntoView({block:'center'});", element);
        } catch (JavascriptException ignored) {
        }
    }

    public void switchToNewTab() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        if (tabs.size() < 2) {
            throw new IllegalStateException("No new tab was opened");
        }
        driver.switchTo().window(tabs.get(1));
    }

    public boolean isVisible(By locator) {
        try {
            waitFor().until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
