package com.bah.nos.steps;

import com.bah.nos.steps.serenity.NavigationSteps;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class Navigation {

    @Steps
    NavigationSteps navigationSteps;

    @Given("I am on the Native One Stop home page")
    public void givenHomePage() {
        navigationSteps.navigateToHomePage();
    }

    @When("I click on <navBarButton> on the top navigation bar")
    public void clickNavBarButton(@Named("navBarButton") String navBarButton) throws ReflectiveOperationException {
        navigationSteps.navigate(navBarButton);
}

    @Then("the <pageName> page should open correctly")
    public void thenTheyShouldSeeTheTitle(@Named("pageName") String pageTitle) {
        navigationSteps.verifyTitle(pageTitle);
    }
}
