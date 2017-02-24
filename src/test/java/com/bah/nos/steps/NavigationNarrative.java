package com.bah.nos.steps;

import com.bah.nos.pages.NosPageEnum;
import com.bah.nos.steps.serenity.NavigationSteps;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class NavigationNarrative {

    @Steps
    private NavigationSteps navigationSteps;

    @Given("I am on the Native One Stop home page")
    public void onHomePage() throws ReflectiveOperationException {
        navigationSteps.openPage(NosPageEnum.HOME);
    }

//    @Given("I am on the Native One Stop resource finder page")
//    public void onResourceFinderPage() {
//        resourceFinderSteps.navigate();
//    }

    @When("I click on <navBarPath> on the top navigation bar")
    public void clickNavBarButton(@Named("navBarPath") String navBarPath) throws ReflectiveOperationException {
        navigationSteps.navigate(navBarPath);
    }

    @Then("I should see the <navBarPath> page")
    public void thenTheyShouldSeeTheTitle(@Named("navBarPath") String navBarPath) {
        navigationSteps.verifyTitle(navBarPath);
    }
}
