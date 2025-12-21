package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import utils.DriverManager;

public abstract class BasePage {

    protected WebDriver driver;

    protected BasePage() {
        this.driver = DriverManager.get();
        PageFactory.initElements(driver, this);
    }

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void open(String url) {
        driver.get(url);
    }

    protected WebDriver getDriver() {
        return driver;
    }

    public void hover(By locator) {
        Actions actions = new Actions(driver);
        WebElement element = driver.findElement(locator);
        actions.moveToElement(element).perform();
    }

}
