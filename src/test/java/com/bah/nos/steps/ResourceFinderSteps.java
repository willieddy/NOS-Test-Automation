package com.bah.nos.steps;

import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import com.bah.nos.steps.serenity.EndUserSteps;

public class ResourceFinderSteps {

    @Steps
    EndUserSteps endUser;

    @Given("the user is on the Native One Stop home page")
    public void givenTheUserIsOnTheNOSHomePage() {
        endUser.is_the_home_page();
    }

    @When("the user navigates to the ResourceFinder page")
    public void whenTheUserNavigatesToRF() {
        endUser.navigateToResourceFinder();
    }

    @Then("they should see the title Resource Finder")
    public void thenTheyShouldSeeTheTitle() {
        endUser.shouldSeeResourceFinder();
    }

}
