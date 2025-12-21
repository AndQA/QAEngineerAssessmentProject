package bo;

import io.qameta.allure.Step;
import pages.HomePage;

public class InsiderCareerBO extends BaseBO {

    private final HomePage homePage = new HomePage();

    @Step("Open Home Page with URL: {baseUrl}")
    public InsiderCareerBO openHomePage(String baseUrl) {
        homePage.openHome(baseUrl);
        return this;
    }

    @Step("Verify Home Page is loaded and all elements are visible")
    public InsiderCareerBO navigateToHomePage() {
        homePage.areAllMenuButtonsVisible();
        homePage.isPlatformDropdownVisibleAfterHover();
        return this;
    }

}
