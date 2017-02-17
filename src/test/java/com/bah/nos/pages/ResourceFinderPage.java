package com.bah.nos.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class ResourceFinderPage extends NosBasePage {

    @FindBy(xpath = "//fieldset[@class='ui-question-fieldset']")
    private List<WebElementFacade> questions;

    public ResourceFinderPage(WebDriver webDriver) {
        super(webDriver);
    }

    public ResourceFinderPage answerQuestions() {

        return new ResourceFinderPage(getDriver());
    }
}
