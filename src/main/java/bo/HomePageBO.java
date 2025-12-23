package bo;

import io.qameta.allure.Step;
import pages.HomePage;

public class HomePageBO extends BaseBO<HomePage> {

    public HomePageBO() {
        super(new HomePage());
    }

    @Step("Open Home Page: {url}")
    public HomePageBO openHomePage(String url) {
        openUrl(url);
        return this;
    }

    @Step("Verify navigation elements are visible")
    public HomePageBO verifyNavigation() {
        softAssert.assertTrue(page.isHeaderVisible(), "Header is NOT visible");
        softAssert.assertTrue(page.areAllMenuButtonsVisible(), "Menu buttons are NOT visible");
        return this;
    }

    @Step("Verify main sections of Home Page")
    public HomePageBO verifyMainSections() {
        softAssert.assertTrue(page.isHeroSectionVisible(), "Hero section is NOT visible");
        softAssert.assertTrue(page.isSocialProofSectionVisible(), "Social Proof section is NOT visible");
        softAssert.assertTrue(page.isCoreDifferentiatorsSectionVisible(),
                "Core Differentiators section is NOT visible");
        return this;
    }
}