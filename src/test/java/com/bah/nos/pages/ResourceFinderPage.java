package com.bah.nos.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class ResourceFinderPage extends PageObject {

    @FindBy(id = "content-header")
    private WebElementFacade pageTitle;

    public String getPageTitle() {
        return pageTitle.getText();
    }
}
