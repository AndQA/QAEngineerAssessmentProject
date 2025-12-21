package utils;

import org.openqa.selenium.WebDriver;

public class DriverManager {
    private static final ThreadLocal<WebDriver> webdriver = new ThreadLocal<>();

    private DriverManager() {
    }

    public static void set(WebDriver driver) {
        DriverManager.webdriver.set(driver);
    }

    public static WebDriver get() {
        return webdriver.get();
    }

    public static void unload() {
        WebDriver wd = webdriver.get();
        if (wd != null) {
            wd.quit();
            webdriver.remove();
        }
    }
}