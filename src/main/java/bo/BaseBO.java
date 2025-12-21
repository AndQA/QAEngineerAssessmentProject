package bo;

import io.qameta.allure.Step;
import org.testng.asserts.SoftAssert;
import utils.WaitUtils;

public abstract class BaseBO {

    protected final WaitUtils wait;
    protected final SoftAssert softAssert;

    protected BaseBO() {
        this.wait = new WaitUtils();
        this.softAssert = new SoftAssert();
    }

    @Step("Open URL: {url}")
    protected void openUrl(String url) {
        wait.waitForPageLoad();
    }

    protected void assertAll() {
        softAssert.assertAll();
    }
}
