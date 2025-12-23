package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import selenium.framework.SeleniumWebDriverFactory;
import utils.DriverManager;
import selenium.properties.ConfigManager;

public abstract class BaseTest {

    protected String baseUrl;
    protected String careersQaUrl;

    @BeforeMethod
    public void setup() {

        WebDriver driver = SeleniumWebDriverFactory.getWebDriver();
        DriverManager.set(driver);

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

        baseUrl = ConfigManager.urls.baseUrl();
        careersQaUrl = ConfigManager.urls.careersQaUrl();
    }

    @AfterMethod(alwaysRun = true)
    public void teardown() {
        DriverManager.unload();
    }
}