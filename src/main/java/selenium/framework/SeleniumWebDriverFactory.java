package selenium.framework;

import selenium.properties.EnvironmentManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SeleniumWebDriverFactory {

    public static WebDriver getWebDriver() {

        boolean headless = EnvironmentManager.envConf.headless();

        switch (EnvironmentManager.envConf.browser().toUpperCase()) {

            case "CHROME":
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver(buildChromeOptions(headless));

            default:
                throw new UnsupportedOperationException(
                        "Unsupported browser: " + EnvironmentManager.envConf.browser()
                );
        }
    }

    private static ChromeOptions buildChromeOptions(boolean headless) {
        ChromeOptions options = new ChromeOptions();

        // базові стабільні аргументи
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--dns-prefetch-disable");
        options.setAcceptInsecureCerts(true);
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        // headless fullscreen
        if (headless) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--disable-dev-shm-usage");
        }

        return options;
    }
}
