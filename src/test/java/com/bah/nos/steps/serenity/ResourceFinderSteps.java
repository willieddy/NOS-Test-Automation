package com.bah.nos.steps.serenity;

import com.bah.nos.pages.ResourceFinderPage;
import net.thucydides.core.annotations.Step;

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
