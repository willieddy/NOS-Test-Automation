package com.bah.nos.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.WebDriver;

public class ResourceFinderPage extends NosBasePage {

    @FindBy(id = "content-header")
    private WebElementFacade pageTitle;

    public ResourceFinderPage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getPageTitle() {
        return pageTitle.getText();
    }
}
