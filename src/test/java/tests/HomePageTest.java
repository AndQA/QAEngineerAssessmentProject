package tests;

import base.BaseTest;
import bo.HomePageBO;
import org.testng.annotations.Test;
import selenium.framework.RetryAnalyzer;

public class HomePageTest extends BaseTest {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void verifyHomePageFlow() {

        new HomePageBO()
                .openHomePage(baseUrl)
                .verifyNavigation()
                .verifyMainSections()
                .assertAll();
    }
}