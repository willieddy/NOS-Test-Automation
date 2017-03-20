package com.bah.nos.model.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.WebDriver;

@DefaultUrl("http://local.nativeonestop.gov")
public class HomePage extends NosBasePage {

    @FindBy(xpath = "//div[@id='welcome']/h3")
    private WebElementFacade pageTitle;


    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public String getPageTitle() {
        return pageTitle.getText();
    }
}