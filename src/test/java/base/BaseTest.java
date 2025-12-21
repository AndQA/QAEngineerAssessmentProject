package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import selenium.framework.SeleniumWebDriverFactory;
import utils.DriverManager;
import selenium.properties.ConfigManager;

public abstract class BaseTest {

    protected WebDriver driver;
    protected SoftAssert softAssert;
    protected String baseUrl;

    @BeforeMethod
    public void setup() {

        driver = SeleniumWebDriverFactory.getWebDriver();
        DriverManager.set(driver);

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

        softAssert = new SoftAssert();

        baseUrl = ConfigManager.urls.baseUrl();
    }

    @AfterMethod(alwaysRun = true)
    public void teardown() {
        try {
            if (softAssert != null) {
                softAssert.assertAll();
            }
        } finally {
            DriverManager.unload();
        }
    }

    protected WebDriver getDriver() {
        return DriverManager.get();
    }
}
