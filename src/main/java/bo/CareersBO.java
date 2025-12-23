package bo;

import io.qameta.allure.Step;
import pages.JobsPage;
import pages.LeverPage;
import pages.QAPage;

public class CareersBO extends BaseBO<QAPage> {

    private JobsPage jobsPage;
    private LeverPage leverPage;

    public CareersBO() {
        super(new QAPage());
    }

    @Step("Open QA Careers page: {url}")
    public CareersBO openQAPage(String url) {
        openUrl(url);
        return this;
    }

    @Step("Open all QA jobs")
    public CareersBO openAllQAJobs() {
        jobsPage = page.clickSeeAllQAJobs();
        return this;
    }

    @Step("Apply filters: location={location}, department={department}")
    public CareersBO applyFilters(String location, String department) {
        jobsPage
                .filterByLocation(location)
                .filterByDepartment(department);
        return this;
    }

    @Step("Verify job list is visible")
    public CareersBO verifyJobList() {
        softAssert.assertTrue(
                jobsPage.isJobListVisible(),
                "Job list is NOT visible after filtering"
        );
        return this;
    }

    @Step("Verify all job cards contain correct Department and Location")
    public CareersBO verifyJobCardDetails(String department, String location) {
        softAssert.assertTrue(
                jobsPage.validateJobCards(department, location),
                "Some job cards do NOT contain expected values"
        );
        return this;
    }

    @Step("Open first job details")
    public CareersBO openFirstJobDetails() {
        jobsPage.clickViewRole();
        jobsPage.switchToNewTab();
        leverPage = new LeverPage();
        leverPage.waitForPageLoad();
        return this;
    }

    @Step("Verify Lever page loaded without errors")
    public CareersBO verifyLeverPageLoaded() {
        softAssert.assertTrue(
                leverPage.isPageLoadedWithoutErrors(),
                "Lever page contains error layout!"
        );
        return this;
    }
}