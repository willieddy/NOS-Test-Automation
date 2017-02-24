package com.bah.nos.steps;

import com.bah.nos.steps.serenity.NavigationSteps;
import com.bah.nos.steps.serenity.ResourceFinderSteps;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class ResourceFinderNarrative {

    @Steps
    private ResourceFinderSteps resourceFinderSteps;

    @When("I complete the resource finder questionnaire")
    public void completeResourceFinder() {
        resourceFinderSteps.completeResourceFinder();
    }

    @Then("I should see available benefits")
    public void thenBenefitsDisplayed() {
        resourceFinderSteps.verifyBenefitsDisplayed();
    }
}
