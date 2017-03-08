package com.bah.nos.pages;

import com.bah.nos.model.NosPageEnum;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
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

    public NosBasePage navigate(String navBarPath) throws ReflectiveOperationException {
        // Provided path is comma separated list of button names, separate them
        String[] navButtons = navBarPath.split("\\,");

        StringBuilder xpathBuilder = new StringBuilder(NAV_XPATH_BASE);

        // Hover over Menu buttons until last (the button to click)
        for (int i = 0; i < navButtons.length - 1; i++) {
            xpathBuilder.append(String.format(NAV_XPATH_EXTENSION, navButtons[i]));
            WebElementFacade hoverButton = find(By.xpath(xpathBuilder.toString()));
            validateElement(navButtons[i], hoverButton);
            hoverElement(hoverButton);
        }

        // Select the last button in the list
        xpathBuilder.append(String.format(NAV_XPATH_EXTENSION, navButtons[navButtons.length - 1]));
        WebElementFacade navButton = find(By.xpath(xpathBuilder.toString()));

        validateElement(navButtons[navButtons.length - 1], navButton);

        navButton.click();

        sleep(1000L);

        return NosPageEnum.getPageByNavPath(navBarPath)
                .getPageClass()
                .getDeclaredConstructor(WebDriver.class)
                .newInstance(this.getDriver());
    }

    public String getPageTitle() {
        return pageTitle.getText();
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

    protected void sleep(long duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            log.error("Sleep interrupted");
        }
    }

    /**
     * This is only necessary because scroll to element that is called automatically puts some objects
     * behind the page header
     */
    protected void scrollToTop() {
        ((JavascriptExecutor) getDriver()).executeScript("window.scrollTo(0,0);");
        sleep(500);
    }

    /**
     * Format input to xPath statement
     * Wraps all inputs with single quotes (') in a concat statement using double quotes
     * http://www.seleniumtests.com/2010/08/xpath-and-single-quotes.html
     * @param input the string to be verified and changed if necessary - comparison string not wrapped in quotes
     * @return formatted string wrapped in single quotes if no xPath function necessary,
     *         xPath function if input contains a single quote
     */
    protected String formatXpathStringInput(String input) {
        StringBuilder result = new StringBuilder();

        if (input.contains("'")) {
            result.append("concat(\"");
            result.append(input.replaceAll("'", "\", \"'\", \""));
            result.append("\")");
        } else {
            result.append("'");
            result.append(input);
            result.append("'");
        }

        return result.toString();
    }

}

