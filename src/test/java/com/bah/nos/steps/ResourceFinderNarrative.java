package com.bah.nos.steps;

import com.bah.nos.steps.serenity.ResourceFinderSteps;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import java.io.IOException;

public class ResourceFinderNarrative {

    @Steps
    private ResourceFinderSteps resourceFinderSteps;

    @Given("I am on the Native One Stop resource finder page")
    public void onResourceFinderPage() {
        resourceFinderSteps.openPage();
    }

    @When("I enter my <information> into the questionnaire")
    public void completeResourceFinder(@Named("information") String testCaseFileName) throws IOException {
        resourceFinderSteps.completeResourceFinder(testCaseFileName);
    }

    @Then("I should see available benefits related to my <information>")
    public void thenBenefitsDisplayed(@Named("information") String testCaseFileName) {
        resourceFinderSteps.verifyBenefitsDisplayed(testCaseFileName);
    }
}
