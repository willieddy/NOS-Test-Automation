package com.bah.nos.model.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.WebDriver;

public abstract class ResourceFinderPage extends NosBasePage {

    @FindBy(xpath = "//div[@id='questionnaire-id']/div[@id='mtid']/ul/li/a[text()='Answer Questions']")
    private WebElementFacade questionnaireNavAnswer;

    @FindBy(xpath = "//div[@id='questionnaire-id']/div[@id='mtid']/ul/li/a[text()='View Results']")
    private WebElementFacade questionnaireNavResults;

    @FindBy(xpath = "//div[@id='questionnaire-id']/div[@id='mtid']/ul/li/a[text()='Favorites']")
    private WebElementFacade questionnaireNavFavorites;

    @FindBy(xpath = "//div[@id='bc-value']")
    private WebElementFacade benefitTotal;

    public ResourceFinderPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void clickQuestionnaireNavAnswer() {
        scrollToTop();
        questionnaireNavAnswer.click();
    }

    public void clickQuestionnaireNavResults() {
        scrollToTop();
        questionnaireNavResults.click();
    }

    public void clickQuestionnaireNavFavorites() {
        scrollToTop();
        questionnaireNavFavorites.click();
    }

    public Integer getBenefitTotal() {
        return Integer.parseInt(benefitTotal.getText());
    }

}
