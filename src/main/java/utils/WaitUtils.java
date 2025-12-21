package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.properties.EnvironmentManager;

import java.time.Duration;

public class WaitUtils {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private static final int TIMEOUT =
            EnvironmentManager.envConf.explicitWait() > 0
                    ? EnvironmentManager.envConf.explicitWait()
                    : 10;

    public WaitUtils() {
        this.driver = DriverManager.get();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
    }

    public WaitUtils(int timeoutSeconds) {
        this.driver = DriverManager.get();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
    }

    public boolean waitForVisibility(By locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean waitForClickable(By locator) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean waitForPresence(By locator) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void waitForPageLoad() {
        try {
            wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                    .executeScript("return document.readyState").equals("complete"));
        } catch (Exception ignored) {}
    }
}
