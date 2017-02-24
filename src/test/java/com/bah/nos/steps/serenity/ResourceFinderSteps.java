package com.bah.nos.steps.serenity;

import com.bah.nos.pages.ResourceFinderPage;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.annotations.Step;

@DefaultUrl("https://www.nativeonestop.gov/resources/resource-finder")
public class ResourceFinderSteps {

    private ResourceFinderPage currentPage;

    @Step
    public void completeResourceFinder() {
        currentPage = currentPage.answerQuestions();
    }

    @Step
    public void verifyBenefitsDisplayed() {
    }
}
