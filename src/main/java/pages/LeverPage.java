package pages;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LeverPage extends BasePage {

    private static final By ERROR_BLOCK = By.cssSelector(".error, .not-found, .error-page");

    @Step("Check that Lever page loaded without errors")
    public boolean isPageLoadedWithoutErrors() {
        waitForPageLoad();
        try {
            waitFor().until(ExpectedConditions.presenceOfElementLocated(ERROR_BLOCK));
            return false;
        } catch (Exception ignored) {
            return true;
        }
    }
}