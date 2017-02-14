package com.bah.nos.pages;

import com.bah.nos.util.NosConstants;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@DefaultUrl("https://www.nativeonestop.gov")
public class NosBasePage extends PageObject {

    private static final Logger log = LoggerFactory.getLogger(NosBasePage.class);

    private static final String NAV_XPATH_BASE = "//nav[@id='nav']";
    private static final String NAV_XPATH_EXTENSION = "//li[contains(.,'%s')]";

    @FindBy(xpath = "//*[@class='page__title title']")
    private WebElementFacade pageTitle;

    public NosBasePage(WebDriver webDriver) {
        super(webDriver);
    }

    /**
     * Search nodes in nav menu and press one with matching name
     * findFirst on stream will return random value if multiple match
     * @param navButtons - title of button in nav menu to press
     *                  if comma separated list, hover over each element until last, click last
     */
    public NosBasePage navigate(String navButtons) throws ReflectiveOperationException {
        String[] navButtonArray = navButtons.split("\\,");

        StringBuilder xpathBuilder = new StringBuilder(NAV_XPATH_BASE);

        // Hover over Menu buttons until last (the button to click)
        for (int i = 0; i < navButtonArray.length - 1; i++) {
            xpathBuilder.append(String.format(NAV_XPATH_EXTENSION, navButtonArray[i]));
            WebElementFacade hoverButton = find(By.xpath(xpathBuilder.toString()));
            validateElement(navButtonArray[i], hoverButton);
            hoverElement(hoverButton);
        }

        // Select the last button in the list
        xpathBuilder.append(String.format(NAV_XPATH_EXTENSION, navButtonArray[navButtonArray.length - 1]));
        WebElementFacade navButton = find(By.xpath(xpathBuilder.toString()));

        validateElement(navButtonArray[navButtonArray.length - 1], navButton);

        navButton.click();

        sleep(1000L);

        return getPageClass(navButtons).getDeclaredConstructor(WebDriver.class).newInstance(this.getDriver());
    }

    public String getPageTitle() {
        return pageTitle.getText();
    }

    private Class<? extends NosBasePage> getPageClass(String nodeTitle) throws IllegalArgumentException {
        switch (nodeTitle) {
            case NosConstants.HOME_NAV_TITLE:
                return HomePage.class;
            case NosConstants.ABOUT_NAV_TITLE:
            case NosConstants.OVERVIEW_NAV_TITLE:
            case NosConstants.PRIVACY_POLICY_NAV_TITLE:
            case NosConstants.HELP_NAV_TITLE:
            case NosConstants.FAQS_INDIVIDUALS_NAV_TITLE:
            case NosConstants.FAQS_TRIBES_NAV_TITLE:
            case NosConstants.RESOURCE_CATEGORY_NAV_TITLE:
            case NosConstants.RESOURCE_AGENCY_NAV_TITLE:
                return NosBasePage.class;
            case NosConstants.RESOURCES_NAV_TITLE:
            case NosConstants.RESOURCE_FINDER_NAV_TITLE:
                return ResourceFinderPage.class;
            default:
                throw new IllegalArgumentException("NavButton not found for name " + nodeTitle);
        }
    }

    private void validateElement(String buttonText, WebElementFacade navButton) {
        if (navButton == null || !navButton.isVisible()) {
            throw new ElementNotVisibleException(
                    "Button not visible with text: " + buttonText);
        }
    }

    private void hoverElement(WebElementFacade element) {
        Actions builder = new Actions(getDriver());
        Actions hoverOverLocationSelector = builder.moveToElement(element);
        hoverOverLocationSelector.perform();

        sleep(1000L);
    }

    private void sleep(long duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            log.error("Sleep interrupted after hovering");
        }
    }
}

