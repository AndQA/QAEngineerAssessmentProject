package pages;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.List;

public class HomePage extends BasePage {

    public HomePage() {
        super();
    }

    private static final By MAIN_HEADER = By.id("navigation");

    private static final By PLATFORM_BUTTON = By.xpath("//button[@data-text='Platform']");
    private static final By INDUSTRIES_BUTTON = By.xpath("//button[@data-text='Industries']");
    private static final By RESOURCES_BUTTON = By.xpath("//button[@data-text='Resources']");

    private static final By HERO_SECTION = By.cssSelector("section.homepage-hero");
    private static final By HERO_TITLE = By.cssSelector(".homepage-hero-content-title h1");

    private static final By SOCIAL_PROOF_SECTION = By.cssSelector("section.homepage-social-proof");
    private static final By CORE_DIFFERENTIATORS_SECTION = By.cssSelector("section.homepage-core-differentiators");

    private static final List<By> MENU_BUTTONS = List.of(
            PLATFORM_BUTTON,
            INDUSTRIES_BUTTON,
            RESOURCES_BUTTON
    );


    @Step("Check header is visible")
    public boolean isHeaderVisible() {
        return isVisible(MAIN_HEADER);
    }

    @Step("Check all menu buttons are visible")
    public boolean areAllMenuButtonsVisible() {
        return MENU_BUTTONS.stream().allMatch(this::isVisible);
    }


    @Step("Check Hero section is visible")
    public boolean isHeroSectionVisible() {
        return isVisible(HERO_SECTION) && isVisible(HERO_TITLE);
    }

    @Step("Check Social Proof section is visible")
    public boolean isSocialProofSectionVisible() {
        return isVisible(SOCIAL_PROOF_SECTION);
    }

    @Step("Check Core Differentiators section is visible")
    public boolean isCoreDifferentiatorsSectionVisible() {
        return isVisible(CORE_DIFFERENTIATORS_SECTION);
    }
}