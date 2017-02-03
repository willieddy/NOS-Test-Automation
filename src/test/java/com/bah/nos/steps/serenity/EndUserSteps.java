package com.bah.nos.steps.serenity;

import com.bah.nos.pages.HomePage;
import com.bah.nos.pages.ResourceFinderPage;
import net.thucydides.core.annotations.Step;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class EndUserSteps {

    HomePage homePage;

    ResourceFinderPage resoruceFinderPage;

    @Step
    public void navigateToResourceFinder() {
        homePage.startResourceFinder();
    }

    @Step
    public void shouldSeeResourceFinder() {
        assertThat(resoruceFinderPage.getPageTitle(), is("Resource Finder"));
    }

    @Step
    public void is_the_home_page() {
        homePage.open();
    }

}