package pages;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class JobsPage extends BasePage {

    private static final Logger log = LoggerFactory.getLogger(JobsPage.class);

    private static final By LOCATION_FILTER = By.id("filter-by-location");
    private static final By DEPARTMENT_FILTER = By.id("filter-by-department");
    private static final By JOB_LIST = By.cssSelector("div.position-list-item");
    private static final By VIEW_ROLE_BTN = By.xpath(".//a[contains(@href, 'jobs.lever.co')]");

    private static final By POSITION_TITLE = By.cssSelector(".position-title");
    private static final By POSITION_DEPARTMENT = By.cssSelector(".position-department");
    private static final By POSITION_LOCATION = By.cssSelector(".position-location");

    @Step("Filter by location: {locationClass}")
    public JobsPage filterByLocation(String locationClass) {
        closeCookieBannerIfPresent();

        WebElement selectElement = waitFor()
                .until(ExpectedConditions.elementToBeClickable(LOCATION_FILTER));

        scrollToElement(selectElement);

        Select select = new Select(selectElement);

        waitFor().until(driver ->
                select.getOptions().stream()
                        .anyMatch(o -> {
                            String cls = o.getAttribute("class");
                            return cls != null && cls.contains(locationClass);
                        })
        );

        select.getOptions().stream()
                .filter(o -> {
                    String cls = o.getAttribute("class");
                    return cls != null && cls.contains(locationClass);
                })
                .findFirst()
                .ifPresent(WebElement::click);

        return this;
    }

    @Step("Filter by department: {department}")
    public JobsPage filterByDepartment(String department) {
        closeCookieBannerIfPresent();

        WebElement selectElement = waitFor()
                .until(ExpectedConditions.elementToBeClickable(DEPARTMENT_FILTER));

        new Select(selectElement).selectByVisibleText(department);

        return this;
    }

    @Step("Check that job list is visible")
    public boolean isJobListVisible() {
        return !driver.findElements(JOB_LIST).isEmpty();
    }

    private String normalize(String text) {
        if (text == null) return "";

        String noDiacritics = java.text.Normalizer
                .normalize(text, java.text.Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");

        return noDiacritics
                .toLowerCase()
                .replace("turkiye", "turkey")
                .replace("türkiye", "turkey")
                .trim();
    }

    @Step("Validate each job card contains correct Department and Location")
    public boolean validateJobCards(String expectedDepartment, String expectedLocation) {

        List<WebElement> jobs = driver.findElements(JOB_LIST);
        if (jobs.isEmpty()) {
            log.error("Job list is empty — nothing to validate");
            return false;
        }

        String normalizedExpectedDept = normalize(expectedDepartment);
        String normalizedExpectedLoc = normalize(expectedLocation);

        int index = 1;

        for (WebElement job : jobs) {
            String position = job.findElement(POSITION_TITLE).getText();
            String department = job.findElement(POSITION_DEPARTMENT).getText();
            String location = job.findElement(POSITION_LOCATION).getText();

            String normPosition = normalize(position);
            String normDepartment = normalize(department);
            String normLocation = normalize(location);

            if (!normPosition.contains(normalizedExpectedDept)) {
                log.error("Job card #{} failed: position does not contain expected department. Position='{}', Expected='{}'",
                        index, position, expectedDepartment);
                return false;
            }

            if (!normDepartment.contains(normalizedExpectedDept)) {
                log.error("Job card #{} failed: department mismatch. Department='{}', Expected='{}'",
                        index, department, expectedDepartment);
                return false;
            }

            if (!normLocation.contains(normalizedExpectedLoc)) {
                log.error("Job card #{} failed: location mismatch. Location='{}', Expected='{}', Normalized='{}'",
                        index, location, expectedLocation, normLocation);
                return false;
            }
            index++;
        }
        return true;
    }

    @Step("Click 'View Role' button for the first job")
    public void clickViewRole() {
        List<WebElement> jobs = driver.findElements(JOB_LIST);
        if (jobs.isEmpty()) {
            throw new IllegalStateException("No jobs found to click 'View Role'");
        }

        WebElement firstJob = jobs.get(0);
        WebElement viewRoleBtn = firstJob.findElement(VIEW_ROLE_BTN);

        scrollToElement(viewRoleBtn);
        viewRoleBtn.click();
    }
}