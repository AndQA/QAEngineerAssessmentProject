package bo;

import base.BasePage;
import io.qameta.allure.Step;
import org.testng.asserts.SoftAssert;

public abstract class BaseBO<T extends BasePage> {

    protected final SoftAssert softAssert;
    protected final T page;

    protected BaseBO(T page) {
        this.softAssert = new SoftAssert();
        this.page = page;
    }

    @Step("Open URL: {url}")
    protected void openUrl(String url) {
        page.openUrl(url);
        page.waitForPageLoad();
    }

    public void assertAll() {
        softAssert.assertAll();
    }
}
