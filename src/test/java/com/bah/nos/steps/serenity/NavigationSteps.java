package com.bah.nos.steps.serenity;

import com.bah.nos.pages.HomePage;
import com.bah.nos.pages.NosBasePage;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;

public class NavigationSteps {

    private NosBasePage currentPage;

    @Step
    public void navigateToHomePage() {
        currentPage = new HomePage(currentPage.getDriver());
        currentPage.open();
    }

    @Step
    public void navigate(String navBarButton) throws ReflectiveOperationException {
        currentPage = currentPage.navigate(navBarButton);
    }

    @Step
    public void verifyTitle(String pageTitle) {
        Assert.assertEquals(pageTitle, currentPage.getPageTitle());
    }

}
