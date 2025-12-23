package tests;

import base.BaseTest;
import bo.CareersBO;
import org.testng.annotations.Test;
import selenium.framework.RetryAnalyzer;

public class CareersTest extends BaseTest {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void verifyQAJobsFiltering() {

        String locationForFilter = "istanbulturkiye";
        String location = "Istanbul, Turkey";
        String department = "Quality Assurance";

        new CareersBO()
                .openQAPage(careersQaUrl)
                .openAllQAJobs()
                .applyFilters(locationForFilter, department)
                .verifyJobList()
                .verifyJobCardDetails(department, location)
                .openFirstJobDetails()
                .verifyLeverPageLoaded()
                .assertAll();
    }



}