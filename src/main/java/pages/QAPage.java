package pages;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class QAPage extends BasePage {

    private static final By SEE_ALL_QA_JOBS = By.xpath("//a[contains(text(),'See all QA jobs')]");

    @Step("Click 'See all QA jobs'")
    public JobsPage clickSeeAllQAJobs() {
        closeCookieBannerIfPresent();
        WebElement button = waitFor().until(ExpectedConditions.elementToBeClickable(SEE_ALL_QA_JOBS));
        scrollToElement(button);
        button.click();
        return new JobsPage();
    }
}
