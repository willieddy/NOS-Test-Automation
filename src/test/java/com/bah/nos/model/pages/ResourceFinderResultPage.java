package com.bah.nos.model.pages;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.stream.Collectors;

@DefaultUrl("http://local.nativeonestop.gov/resources/resource-finder#results")
public class ResourceFinderResultPage extends ResourceFinderPage {

    private static final String FAVORITE_BUTTON_XPATH = "//td[./h2/a[" +
            String.format(XPATH_TO_UPPERCASE, "text()") +
            "=%s]]/div/a[text() = 'Add to Favorites']";

    @FindBy(xpath = "//tbody[@id='resultTableBody']/tr/td/h2/a")
    private List<WebElementFacade> resourceTitles;

    public ResourceFinderResultPage(WebDriver webDriver) {
        super(webDriver);
    }

    public List<String> getResourceTitles() {
        return resourceTitles.stream().map(WebElementFacade::getText).collect(Collectors.toList());
    }

    public void addToFavorites(String resourceTitle) {
        scrollToTop();
        String answerXpath = String.format(FAVORITE_BUTTON_XPATH, formatXpathStringInput(resourceTitle));
        WebElementFacade button = find(By.xpath(answerXpath));
        button.click();
    }

}
