package pages;

import base.BasePage;
import org.openqa.selenium.By;
import utils.WaitUtils;

public class HomePage extends BasePage {

    private final WaitUtils wait = new WaitUtils();

    public HomePage() {
        super();
    }

    private static final By MAIN_HEADER = By.id("navigation");

    private static final By PLATFORM_BUTTON =
            By.xpath("//div[contains(@class,'header-menu-item')]//button[@data-text='Platform']");

    private static final By INDUSTRIES_BUTTON =
            By.xpath("//div[contains(@class,'header-menu-item')]//button[@data-text='Industries']");

    private static final By RESOURCES_BUTTON =
            By.xpath("//div[contains(@class,'header-menu-item')]//button[@data-text='Resources']");

    private static final By PLATFORM_DROPDOWN =
            By.xpath("//button[@data-text='Platform']/following-sibling::div[contains(@class,'header-menu-item-dropdown')]");

    private static final By HERO_SECTION = By.cssSelector("section.homepage-hero");
    private static final By HERO_TITLE = By.cssSelector(".homepage-hero-content-title h1");
    private static final By SOCIAL_PROOF_SECTION = By.cssSelector("section.homepage-social-proof");
    private static final By CORE_DIFFERENTIATORS_SECTION = By.cssSelector("section.homepage-core-differentiators");

    private static final By[] MENU_BUTTONS = {
            PLATFORM_BUTTON,
            INDUSTRIES_BUTTON,
            RESOURCES_BUTTON
    };

    public HomePage openHome(String baseUrl) {
        open(baseUrl);
        wait.waitForPageLoad();
        return this;
    }

    public boolean isHeaderVisible() {
        return wait.waitForVisibility(MAIN_HEADER);
    }

    public boolean areAllMenuButtonsVisible() {
        for (By button : MENU_BUTTONS) {
            if (!wait.waitForVisibility(button)) {
                return false;
            }
        }
        return true;
    }

    public boolean isPlatformDropdownVisibleAfterHover() {
        hover(PLATFORM_BUTTON);
        return wait.waitForVisibility(PLATFORM_DROPDOWN);
    }

    public boolean isHeroSectionVisible() {
        return wait.waitForVisibility(HERO_SECTION)
                && wait.waitForVisibility(HERO_TITLE);
    }

    public boolean isSocialProofSectionVisible() {
        return wait.waitForVisibility(SOCIAL_PROOF_SECTION);
    }

    public boolean isCoreDifferentiatorsSectionVisible() {
        return wait.waitForVisibility(CORE_DIFFERENTIATORS_SECTION);
    }
}
